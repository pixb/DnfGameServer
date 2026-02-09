package sqlite

import (
	"context"
	"fmt"
	"strings"
	"time"

	"github.com/pixb/DnfGameServer/dnf-go-server/store"
)

// ==================== 拍卖行物品CRUD ====================

// CreateAuctionItem 创建拍卖物品
func (d *DB) CreateAuctionItem(ctx context.Context, create *store.AuctionItem) (*store.AuctionItem, error) {
	query := `
      INSERT INTO auction_item (created_at, updated_at, row_status, seller_id, seller_name, item_id, count, price, total_price, duration, status, bidder_id, bidder_name, bid_price, bid_count, attributes, end_time)
      VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
   `

	now := time.Now().Unix()
	endTime := now + int64(create.Duration*3600)

	result, err := d.db.ExecContext(ctx, query,
		now, now, store.RowStatusNormal,
		create.SellerID, create.SellerName, create.ItemID, create.Count,
		create.Price, create.TotalPrice, create.Duration, create.Status,
		create.BidderID, create.BidderName, create.BidPrice, create.BidCount,
		create.Attributes, endTime,
	)
	if err != nil {
		return nil, fmt.Errorf("failed to create auction item: %w", err)
	}

	id, _ := result.LastInsertId()
	create.ID = uint64(id)
	create.CreatedAt = now
	create.UpdatedAt = now
	create.RowStatus = store.RowStatusNormal
	create.EndTime = endTime
	return create, nil
}

// UpdateAuctionItem 更新拍卖物品
func (d *DB) UpdateAuctionItem(ctx context.Context, update *store.UpdateAuctionItem) error {
	var sets []string
	var args []interface{}

	if update.Status != nil {
		sets = append(sets, "status = ?")
		args = append(args, *update.Status)
	}
	if update.BidderID != nil {
		sets = append(sets, "bidder_id = ?")
		args = append(args, *update.BidderID)
	}
	if update.BidderName != nil {
		sets = append(sets, "bidder_name = ?")
		args = append(args, *update.BidderName)
	}
	if update.BidPrice != nil {
		sets = append(sets, "bid_price = ?")
		args = append(args, *update.BidPrice)
	}
	if update.BidCount != nil {
		sets = append(sets, "bid_count = ?")
		args = append(args, *update.BidCount)
	}
	if update.RowStatus != nil {
		sets = append(sets, "row_status = ?")
		args = append(args, *update.RowStatus)
	}

	if len(sets) == 0 {
		return nil
	}

	sets = append(sets, "updated_at = ?")
	args = append(args, time.Now().Unix())
	args = append(args, update.ID)

	query := fmt.Sprintf("UPDATE auction_item SET %s WHERE id = ?", strings.Join(sets, ", "))
	_, err := d.db.ExecContext(ctx, query, args...)
	if err != nil {
		return fmt.Errorf("failed to update auction item: %w", err)
	}
	return nil
}

// GetAuctionItem 获取拍卖物品
func (d *DB) GetAuctionItem(ctx context.Context, find *store.FindAuctionItem) (*store.AuctionItem, error) {
	items, err := d.ListAuctionItems(ctx, find)
	if err != nil {
		return nil, err
	}
	if len(items) == 0 {
		return nil, store.ErrNotFound
	}
	return items[0], nil
}

// ListAuctionItems 查询拍卖物品列表
func (d *DB) ListAuctionItems(ctx context.Context, find *store.FindAuctionItem) ([]*store.AuctionItem, error) {
	var where []string
	var args []interface{}

	if find.ID != nil {
		where = append(where, "id = ?")
		args = append(args, *find.ID)
	}
	if find.SellerID != nil {
		where = append(where, "seller_id = ?")
		args = append(args, *find.SellerID)
	}
	if find.ItemID != nil {
		where = append(where, "item_id = ?")
		args = append(args, *find.ItemID)
	}
	if find.Status != nil {
		where = append(where, "status = ?")
		args = append(args, *find.Status)
	}
	if find.MinPrice != nil {
		where = append(where, "price >= ?")
		args = append(args, *find.MinPrice)
	}
	if find.MaxPrice != nil {
		where = append(where, "price <= ?")
		args = append(args, *find.MaxPrice)
	}
	if find.BidderID != nil {
		where = append(where, "bidder_id = ?")
		args = append(args, *find.BidderID)
	}
	if find.EndTimeBefore != nil {
		where = append(where, "end_time <= ?")
		args = append(args, *find.EndTimeBefore)
	}
	if find.RowStatus != nil {
		where = append(where, "row_status = ?")
		args = append(args, *find.RowStatus)
	}

	query := `SELECT id, created_at, updated_at, row_status, seller_id, seller_name, item_id, count, price, total_price, duration, status, bidder_id, bidder_name, bid_price, bid_count, attributes, end_time FROM auction_item`
	if len(where) > 0 {
		query += " WHERE " + strings.Join(where, " AND ")
	}
	query += " ORDER BY created_at DESC"
	if find.Limit != nil {
		query += fmt.Sprintf(" LIMIT %d", *find.Limit)
	}
	if find.Offset != nil {
		query += fmt.Sprintf(" OFFSET %d", *find.Offset)
	}

	rows, err := d.db.QueryContext(ctx, query, args...)
	if err != nil {
		return nil, fmt.Errorf("failed to query auction items: %w", err)
	}
	defer rows.Close()

	var items []*store.AuctionItem
	for rows.Next() {
		var item store.AuctionItem
		err := rows.Scan(&item.ID, &item.CreatedAt, &item.UpdatedAt, &item.RowStatus,
			&item.SellerID, &item.SellerName, &item.ItemID, &item.Count,
			&item.Price, &item.TotalPrice, &item.Duration, &item.Status,
			&item.BidderID, &item.BidderName, &item.BidPrice, &item.BidCount,
			&item.Attributes, &item.EndTime)
		if err != nil {
			return nil, fmt.Errorf("failed to scan auction item: %w", err)
		}
		items = append(items, &item)
	}

	return items, nil
}

// ListAuctionItemsBySeller 获取卖家的拍卖物品
func (d *DB) ListAuctionItemsBySeller(ctx context.Context, sellerID uint64) ([]*store.AuctionItem, error) {
	return d.ListAuctionItems(ctx, &store.FindAuctionItem{SellerID: &sellerID})
}

// DeleteAuctionItem 删除拍卖物品
func (d *DB) DeleteAuctionItem(ctx context.Context, delete *store.DeleteAuctionItem) error {
	query := "DELETE FROM auction_item WHERE id = ?"
	_, err := d.db.ExecContext(ctx, query, delete.ID)
	if err != nil {
		return fmt.Errorf("failed to delete auction item: %w", err)
	}
	return nil
}

// CountAuctionItemsBySeller 获取卖家的拍卖物品数量
func (d *DB) CountAuctionItemsBySeller(ctx context.Context, sellerID uint64) (int, error) {
	query := `SELECT COUNT(*) FROM auction_item WHERE seller_id = ? AND status = ? AND row_status = 'NORMAL'`
	var count int
	status := store.AuctionStatusSelling
	err := d.db.QueryRowContext(ctx, query, sellerID, status).Scan(&count)
	if err != nil {
		return 0, fmt.Errorf("failed to count auction items: %w", err)
	}
	return count, nil
}

// ==================== 拍卖历史CRUD ====================

// CreateAuctionHistory 创建拍卖历史
func (d *DB) CreateAuctionHistory(ctx context.Context, create *store.CreateAuctionHistory) (*store.AuctionHistory, error) {
	query := `
      INSERT INTO auction_history (created_at, updated_at, row_status, auction_id, seller_id, buyer_id, item_id, count, final_price, seller_income)
      VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
   `

	now := time.Now().Unix()
	result, err := d.db.ExecContext(ctx, query,
		now, now, store.RowStatusNormal,
		create.AuctionID, create.SellerID, create.BuyerID, create.ItemID,
		create.Count, create.FinalPrice, create.SellerIncome,
	)
	if err != nil {
		return nil, fmt.Errorf("failed to create auction history: %w", err)
	}

	id, _ := result.LastInsertId()
	return &store.AuctionHistory{
		BaseModel: store.BaseModel{
			ID:        uint64(id),
			CreatedAt: now,
			UpdatedAt: now,
			RowStatus: store.RowStatusNormal,
		},
		AuctionID:    create.AuctionID,
		SellerID:     create.SellerID,
		BuyerID:      create.BuyerID,
		ItemID:       create.ItemID,
		Count:        create.Count,
		FinalPrice:   create.FinalPrice,
		SellerIncome: create.SellerIncome,
	}, nil
}

// ListAuctionHistory 查询拍卖历史
func (d *DB) ListAuctionHistory(ctx context.Context, find *store.FindAuctionHistory) ([]*store.AuctionHistory, error) {
	var where []string
	var args []interface{}

	if find.ID != nil {
		where = append(where, "id = ?")
		args = append(args, *find.ID)
	}
	if find.SellerID != nil {
		where = append(where, "seller_id = ?")
		args = append(args, *find.SellerID)
	}
	if find.BuyerID != nil {
		where = append(where, "buyer_id = ?")
		args = append(args, *find.BuyerID)
	}
	if find.ItemID != nil {
		where = append(where, "item_id = ?")
		args = append(args, *find.ItemID)
	}
	if find.RowStatus != nil {
		where = append(where, "row_status = ?")
		args = append(args, *find.RowStatus)
	}

	query := `SELECT id, created_at, updated_at, row_status, auction_id, seller_id, buyer_id, item_id, count, final_price, seller_income FROM auction_history`
	if len(where) > 0 {
		query += " WHERE " + strings.Join(where, " AND ")
	}
	query += " ORDER BY created_at DESC"
	if find.Limit != nil {
		query += fmt.Sprintf(" LIMIT %d", *find.Limit)
	}

	rows, err := d.db.QueryContext(ctx, query, args...)
	if err != nil {
		return nil, fmt.Errorf("failed to query auction history: %w", err)
	}
	defer rows.Close()

	var history []*store.AuctionHistory
	for rows.Next() {
		var h store.AuctionHistory
		err := rows.Scan(&h.ID, &h.CreatedAt, &h.UpdatedAt, &h.RowStatus,
			&h.AuctionID, &h.SellerID, &h.BuyerID, &h.ItemID,
			&h.Count, &h.FinalPrice, &h.SellerIncome)
		if err != nil {
			return nil, fmt.Errorf("failed to scan auction history: %w", err)
		}
		history = append(history, &h)
	}

	return history, nil
}
