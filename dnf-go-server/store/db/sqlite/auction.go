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
      INSERT INTO auction_item (created_at, updated_at, row_status, seller_id, seller_name, item_id, count, price, buyout_price, total_price, duration, status, bidder_id, bidder_name, bid_price, bid_count, attributes, end_time)
      VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
   `

	now := time.Now().Unix()
	endTime := now + int64(create.Duration*3600)

	result, err := d.db.ExecContext(ctx, query,
		now, now, store.RowStatusNormal,
		create.SellerID, create.SellerName, create.ItemID, create.Count,
		create.Price, create.BuyoutPrice, create.TotalPrice, create.Duration, create.Status,
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

	query := `SELECT id, created_at, updated_at, row_status, seller_id, seller_name, item_id, count, price, buyout_price, total_price, duration, status, bidder_id, bidder_name, bid_price, bid_count, attributes, end_time FROM auction_item`
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
			&item.Price, &item.BuyoutPrice, &item.TotalPrice, &item.Duration, &item.Status,
			&item.BidderID, &item.BidderName, &item.BidPrice, &item.BidCount,
			&item.Attributes, &item.EndTime)
		if err != nil {
			return nil, fmt.Errorf("failed to scan auction item: %w", err)
		}
		items = append(items, &item)
	}

	return items, nil
}

// CountAuctionItems 统计拍卖物品条数(2026-09-07 第六十五轮, 与 ListAuctionItems 同 where 不含分页)
func (d *DB) CountAuctionItems(ctx context.Context, find *store.FindAuctionItem) (int, error) {
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

	query := `SELECT COUNT(*) FROM auction_item`
	if len(where) > 0 {
		query += " WHERE " + strings.Join(where, " AND ")
	}
	var total int
	if err := d.db.QueryRowContext(ctx, query, args...).Scan(&total); err != nil {
		return 0, fmt.Errorf("failed to count auction items: %w", err)
	}
	return total, nil
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

// SettleExpiredAuctions 到期结算(2026-09-07 第五十七轮, sqlite 版):
// 过期(status=Selling 且 end_time<=now) → 状态 Expired; 有最高出价者 → 退还冻结金; 物品退回卖家背包
func (d *DB) SettleExpiredAuctions(ctx context.Context) (int, error) {
	now := time.Now().Unix()
	rows, err := d.db.QueryContext(ctx,
		"SELECT id, seller_id, item_id, count, bidder_id, bid_price FROM auction_item WHERE status = ? AND end_time <= ?",
		store.AuctionStatusSelling, now)
	if err != nil {
		return 0, fmt.Errorf("failed to query expired auctions: %w", err)
	}
	defer rows.Close()

	type expiredItem struct {
		id       uint64
		sellerID uint64
		itemID   int32
		count    int32
		bidderID uint64
		bidPrice int64
	}
	var expired []expiredItem
	for rows.Next() {
		var e expiredItem
		if err := rows.Scan(&e.id, &e.sellerID, &e.itemID, &e.count, &e.bidderID, &e.bidPrice); err != nil {
			return 0, fmt.Errorf("failed to scan expired auction: %w", err)
		}
		expired = append(expired, e)
	}
	if len(expired) == 0 {
		return 0, nil
	}

	tx, err := d.db.BeginTx(ctx, nil)
	if err != nil {
		return 0, fmt.Errorf("failed to begin settle transaction: %w", err)
	}
	defer tx.Rollback()

	for _, e := range expired {
		if _, err := tx.ExecContext(ctx,
			"UPDATE auction_item SET status = ?, updated_at = ? WHERE id = ?",
			store.AuctionStatusExpired, now, e.id); err != nil {
			return 0, fmt.Errorf("failed to expire auction %d: %w", e.id, err)
		}
		// 退还最高出价者冻结金
		if e.bidderID != 0 && e.bidPrice > 0 {
			if _, err := tx.ExecContext(ctx,
				"UPDATE role_currency SET gold = gold + ? WHERE role_id = ?", e.bidPrice, e.bidderID); err != nil {
				return 0, fmt.Errorf("failed to refund bidder %d: %w", e.bidderID, err)
			}
		}
		// 物品退回卖家背包(自动分配空槽)
		if _, err := tx.ExecContext(ctx,
			`INSERT INTO bag_item (role_id, item_id, grid_index, count)
			 SELECT ?, ?, COALESCE(MAX(grid_index), -1) + 1, ? FROM bag_item WHERE role_id = ?`,
			e.sellerID, e.itemID, e.count, e.sellerID); err != nil {
			return 0, fmt.Errorf("failed to return item to seller %d: %w", e.sellerID, err)
		}
	}

	if err := tx.Commit(); err != nil {
		return 0, fmt.Errorf("failed to commit settle transaction: %w", err)
	}
	return len(expired), nil
}

// TryBidAuction 原子抢锁出价(2026-09-07 第五十九轮, sqlite 版)
func (d *DB) TryBidAuction(ctx context.Context, auctionID, bidderID uint64, bidderName string, bidPrice int64) (bool, error) {
	res, err := d.db.ExecContext(ctx,
		`UPDATE auction_item SET bidder_id = ?, bidder_name = ?, bid_price = ?, bid_count = bid_count + 1, updated_at = ?
		 WHERE id = ? AND status = ? AND bid_price < ?`,
		bidderID, bidderName, bidPrice, time.Now().Unix(),
		auctionID, store.AuctionStatusSelling, bidPrice)
	if err != nil {
		return false, fmt.Errorf("failed to try bid auction: %w", err)
	}
	n, _ := res.RowsAffected()
	return n > 0, nil
}

// TryBuyoutAuction 原子抢锁买断(2026-09-07 第五十九轮, sqlite 版): 置 Sold 并落成交买家
func (d *DB) TryBuyoutAuction(ctx context.Context, auctionID, buyerID uint64, buyerName string, bidPrice int64) (bool, error) {
	res, err := d.db.ExecContext(ctx,
		`UPDATE auction_item SET status = ?, bidder_id = ?, bidder_name = ?, bid_price = ?, bid_count = bid_count + 1, updated_at = ?
		 WHERE id = ? AND status = ?`,
		store.AuctionStatusSold, buyerID, buyerName, bidPrice, time.Now().Unix(),
		auctionID, store.AuctionStatusSelling)
	if err != nil {
		return false, fmt.Errorf("failed to try buyout auction: %w", err)
	}
	n, _ := res.RowsAffected()
	return n > 0, nil
}
