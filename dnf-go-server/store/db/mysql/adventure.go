package mysql

import (
	"context"
	"fmt"
	"strings"
	"time"

	"github.com/pixb/DnfGameServer/dnf-go-server/store"
)

// ==================== 冒险数据 CRUD ====================

// GetAdventureData 获取冒险数据
func (d *DB) GetAdventureData(ctx context.Context, find *store.FindAdventureData) (*store.AdventureData, error) {
	query := `SELECT id, role_id, adventure_level, adventure_exp, energy, max_energy, last_energy_recovery, created_at, updated_at FROM adventure_data WHERE row_status = 'NORMAL'`

	var where []string
	var args []interface{}

	if find.RoleID != nil {
		where = append(where, "role_id = ?")
		args = append(args, *find.RoleID)
	}
	if find.ID != nil {
		where = append(where, "id = ?")
		args = append(args, *find.ID)
	}

	if len(where) > 0 {
		query += " WHERE " + strings.Join(where, " AND ")
	}

	query += " LIMIT 1"

	var data store.AdventureData
	err := d.db.QueryRowContext(ctx, query, args...).Scan(
		&data.ID, &data.RoleID, &data.AdventureLevel, &data.AdventureExp,
		&data.Energy, &data.MaxEnergy, &data.LastEnergyRecovery,
		&data.CreatedAt, &data.UpdatedAt,
	)
	if err != nil {
		if err.Error() == "sql: no rows in result set" {
			return nil, store.ErrNotFound
		}
		return nil, fmt.Errorf("failed to get adventure data: %w", err)
	}

	return &data, nil
}

// CreateAdventureData 创建冒险数据
func (d *DB) CreateAdventureData(ctx context.Context, create *store.CreateAdventureData) (*store.AdventureData, error) {
	query := `
		INSERT INTO adventure_data (role_id, adventure_level, adventure_exp, energy, max_energy, last_energy_recovery, created_at, updated_at)
		VALUES (?, ?, ?, ?, ?, ?, ?, ?)
	`

	now := time.Now().Unix()
	result, err := d.db.ExecContext(ctx, query,
		create.RoleID, create.AdventureLevel, create.AdventureExp,
		create.Energy, create.MaxEnergy, now, now, now,
	)
	if err != nil {
		return nil, fmt.Errorf("failed to create adventure data: %w", err)
	}

	id, _ := result.LastInsertId()
	return &store.AdventureData{
		ID:                 uint64(id),
		RoleID:             create.RoleID,
		AdventureLevel:     create.AdventureLevel,
		AdventureExp:       create.AdventureExp,
		Energy:             create.Energy,
		MaxEnergy:          create.MaxEnergy,
		LastEnergyRecovery: now,
		CreatedAt:          now,
		UpdatedAt:          now,
	}, nil
}

// UpdateAdventureData 更新冒险数据
func (d *DB) UpdateAdventureData(ctx context.Context, update *store.UpdateAdventureData) error {
	var sets []string
	var args []interface{}

	if update.AdventureLevel != nil {
		sets = append(sets, "adventure_level = ?")
		args = append(args, *update.AdventureLevel)
	}
	if update.AdventureExp != nil {
		sets = append(sets, "adventure_exp = ?")
		args = append(args, *update.AdventureExp)
	}
	if update.Energy != nil {
		sets = append(sets, "energy = ?")
		args = append(args, *update.Energy)
	}
	if update.MaxEnergy != nil {
		sets = append(sets, "max_energy = ?")
		args = append(args, *update.MaxEnergy)
	}

	if len(sets) == 0 {
		return nil
	}

	sets = append(sets, "updated_at = ?")
	args = append(args, time.Now().Unix())
	args = append(args, update.ID)

	query := fmt.Sprintf("UPDATE adventure_data SET %s WHERE id = ?", strings.Join(sets, ", "))
	_, err := d.db.ExecContext(ctx, query, args...)
	if err != nil {
		return fmt.Errorf("failed to update adventure data: %w", err)
	}

	return nil
}

// ==================== 冒险存储物品 CRUD ====================

// CreateAdventureStorageItem 创建冒险存储物品
func (d *DB) CreateAdventureStorageItem(ctx context.Context, create *store.CreateAdventureStorageItem) (*store.AdventureStorageItem, error) {
	query := `
		INSERT INTO adventure_storage_item (role_id, item_id, count, is_bound, storage_time, created_at, updated_at)
		VALUES (?, ?, ?, ?, ?, ?, ?)
	`

	now := time.Now().Unix()
	result, err := d.db.ExecContext(ctx, query,
		create.RoleID, create.ItemID, create.Count, create.IsBound, create.StorageTime, now, now,
	)
	if err != nil {
		return nil, fmt.Errorf("failed to create adventure storage item: %w", err)
	}

	id, _ := result.LastInsertId()
	return &store.AdventureStorageItem{
		ID:          uint64(id),
		RoleID:      create.RoleID,
		ItemID:      create.ItemID,
		Count:       create.Count,
		IsBound:     create.IsBound,
		StorageTime: create.StorageTime,
		CreatedAt:   now,
		UpdatedAt:   now,
	}, nil
}

// UpdateAdventureStorageItem 更新冒险存储物品
func (d *DB) UpdateAdventureStorageItem(ctx context.Context, update *store.UpdateAdventureStorageItem) error {
	var sets []string
	var args []interface{}

	if update.Count != nil {
		sets = append(sets, "count = ?")
		args = append(args, *update.Count)
	}
	if update.IsBound != nil {
		sets = append(sets, "is_bound = ?")
		args = append(args, *update.IsBound)
	}

	if len(sets) == 0 {
		return nil
	}

	sets = append(sets, "updated_at = ?")
	args = append(args, time.Now().Unix())
	args = append(args, update.ID)

	query := fmt.Sprintf("UPDATE adventure_storage_item SET %s WHERE id = ?", strings.Join(sets, ", "))
	_, err := d.db.ExecContext(ctx, query, args...)
	if err != nil {
		return fmt.Errorf("failed to update adventure storage item: %w", err)
	}

	return nil
}

// ListAdventureStorageItems 获取冒险存储物品列表
func (d *DB) ListAdventureStorageItems(ctx context.Context, find *store.FindAdventureStorageItem) ([]*store.AdventureStorageItem, error) {
	query := `SELECT id, role_id, item_id, count, is_bound, storage_time, created_at, updated_at FROM adventure_storage_item WHERE row_status = 'NORMAL'`

	var where []string
	var args []interface{}

	if find.RoleID != nil {
		where = append(where, "role_id = ?")
		args = append(args, *find.RoleID)
	}
	if find.ItemID != nil {
		where = append(where, "item_id = ?")
		args = append(args, *find.ItemID)
	}

	if len(where) > 0 {
		query += " WHERE " + strings.Join(where, " AND ")
	}
	query += " ORDER BY storage_time DESC"

	if find.Limit != nil {
		query += fmt.Sprintf(" LIMIT %d", *find.Limit)
	}
	if find.Offset != nil {
		query += fmt.Sprintf(" OFFSET %d", *find.Offset)
	}

	rows, err := d.db.QueryContext(ctx, query, args...)
	if err != nil {
		return nil, fmt.Errorf("failed to query adventure storage items: %w", err)
	}
	defer rows.Close()

	var items []*store.AdventureStorageItem
	for rows.Next() {
		var item store.AdventureStorageItem
		err := rows.Scan(&item.ID, &item.RoleID, &item.ItemID, &item.Count,
			&item.IsBound, &item.StorageTime, &item.CreatedAt, &item.UpdatedAt)
		if err != nil {
			return nil, fmt.Errorf("failed to scan adventure storage item: %w", err)
		}
		items = append(items, &item)
	}

	return items, nil
}

// DeleteAdventureStorageItem 删除冒险存储物品
func (d *DB) DeleteAdventureStorageItem(ctx context.Context, id uint64) error {
	query := "UPDATE adventure_storage_item SET row_status = 'DELETED', updated_at = ? WHERE id = ?"
	_, err := d.db.ExecContext(ctx, query, time.Now().Unix(), id)
	if err != nil {
		return fmt.Errorf("failed to delete adventure storage item: %w", err)
	}
	return nil
}

// ==================== 冒险收获 CRUD ====================

// CreateAdventureReap 创建冒险收获
func (d *DB) CreateAdventureReap(ctx context.Context, create *store.CreateAdventureReap) (*store.AdventureReap, error) {
	query := `
		INSERT INTO adventure_reap (role_id, reap_id, progress, total, is_completed, start_time, end_time, created_at, updated_at)
		VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
	`

	now := time.Now().Unix()
	result, err := d.db.ExecContext(ctx, query,
		create.RoleID, create.ReapID, create.Progress, create.Total,
		false, create.StartTime, create.EndTime, now, now,
	)
	if err != nil {
		return nil, fmt.Errorf("failed to create adventure reap: %w", err)
	}

	id, _ := result.LastInsertId()
	return &store.AdventureReap{
		ID:          uint64(id),
		RoleID:      create.RoleID,
		ReapID:      create.ReapID,
		Progress:    create.Progress,
		Total:       create.Total,
		IsCompleted: false,
		StartTime:   create.StartTime,
		EndTime:     create.EndTime,
		CreatedAt:   now,
		UpdatedAt:   now,
	}, nil
}

// UpdateAdventureReap 更新冒险收获
func (d *DB) UpdateAdventureReap(ctx context.Context, update *store.UpdateAdventureReap) error {
	var sets []string
	var args []interface{}

	if update.Progress != nil {
		sets = append(sets, "progress = ?")
		args = append(args, *update.Progress)
	}
	if update.IsCompleted != nil {
		sets = append(sets, "is_completed = ?")
		args = append(args, *update.IsCompleted)
	}

	if len(sets) == 0 {
		return nil
	}

	sets = append(sets, "updated_at = ?")
	args = append(args, time.Now().Unix())
	args = append(args, update.ID)

	query := fmt.Sprintf("UPDATE adventure_reap SET %s WHERE id = ?", strings.Join(sets, ", "))
	_, err := d.db.ExecContext(ctx, query, args...)
	if err != nil {
		return fmt.Errorf("failed to update adventure reap: %w", err)
	}

	return nil
}

// ListAdventureReaps 获取冒险收获列表
func (d *DB) ListAdventureReaps(ctx context.Context, roleID uint64) ([]*store.AdventureReap, error) {
	query := `
		SELECT id, role_id, reap_id, progress, total, is_completed, start_time, end_time, created_at, updated_at
		FROM adventure_reap WHERE row_status = 'NORMAL' AND role_id = ? ORDER BY created_at DESC
	`

	rows, err := d.db.QueryContext(ctx, query, roleID)
	if err != nil {
		return nil, fmt.Errorf("failed to query adventure reaps: %w", err)
	}
	defer rows.Close()

	var reaps []*store.AdventureReap
	for rows.Next() {
		var reap store.AdventureReap
		err := rows.Scan(&reap.ID, &reap.RoleID, &reap.ReapID, &reap.Progress,
			&reap.Total, &reap.IsCompleted, &reap.StartTime, &reap.EndTime,
			&reap.CreatedAt, &reap.UpdatedAt)
		if err != nil {
			return nil, fmt.Errorf("failed to scan adventure reap: %w", err)
		}
		reaps = append(reaps, &reap)
	}

	return reaps, nil
}

// DeleteAdventureReap 删除冒险收获
func (d *DB) DeleteAdventureReap(ctx context.Context, id uint64) error {
	query := "UPDATE adventure_reap SET row_status = 'DELETED', updated_at = ? WHERE id = ?"
	_, err := d.db.ExecContext(ctx, query, time.Now().Unix(), id)
	if err != nil {
		return fmt.Errorf("failed to delete adventure reap: %w", err)
	}
	return nil
}

// ==================== 冒险书 CRUD ====================

// CreateAdventureBook 创建冒险书
func (d *DB) CreateAdventureBook(ctx context.Context, create *store.CreateAdventureBook) (*store.AdventureBook, error) {
	query := `
		INSERT INTO adventure_book (role_id, book_id, name, level, experience, created_at, updated_at)
		VALUES (?, ?, ?, ?, ?, ?, ?)
	`

	now := time.Now().Unix()
	result, err := d.db.ExecContext(ctx, query,
		create.RoleID, create.BookID, create.Name, create.Level, create.Experience, now, now,
	)
	if err != nil {
		return nil, fmt.Errorf("failed to create adventure book: %w", err)
	}

	id, _ := result.LastInsertId()
	return &store.AdventureBook{
		ID:         uint64(id),
		RoleID:     create.RoleID,
		BookID:     create.BookID,
		Name:       create.Name,
		Level:      create.Level,
		Experience: create.Experience,
		CreatedAt:  now,
		UpdatedAt:  now,
	}, nil
}

// UpdateAdventureBook 更新冒险书
func (d *DB) UpdateAdventureBook(ctx context.Context, update *store.UpdateAdventureBook) error {
	var sets []string
	var args []interface{}

	if update.Level != nil {
		sets = append(sets, "level = ?")
		args = append(args, *update.Level)
	}
	if update.Experience != nil {
		sets = append(sets, "experience = ?")
		args = append(args, *update.Experience)
	}

	if len(sets) == 0 {
		return nil
	}

	sets = append(sets, "updated_at = ?")
	args = append(args, time.Now().Unix())
	args = append(args, update.ID)

	query := fmt.Sprintf("UPDATE adventure_book SET %s WHERE id = ?", strings.Join(sets, ", "))
	_, err := d.db.ExecContext(ctx, query, args...)
	if err != nil {
		return fmt.Errorf("failed to update adventure book: %w", err)
	}

	return nil
}

// GetAdventureBook 获取冒险书
func (d *DB) GetAdventureBook(ctx context.Context, find *store.FindAdventureBook) (*store.AdventureBook, error) {
	query := `SELECT id, role_id, book_id, name, level, experience, created_at, updated_at FROM adventure_book WHERE row_status = 'NORMAL'`

	var where []string
	var args []interface{}

	if find.RoleID != nil {
		where = append(where, "role_id = ?")
		args = append(args, *find.RoleID)
	}
	if find.BookID != nil {
		where = append(where, "book_id = ?")
		args = append(args, *find.BookID)
	}

	if len(where) > 0 {
		query += " WHERE " + strings.Join(where, " AND ")
	}
	query += " LIMIT 1"

	var book store.AdventureBook
	err := d.db.QueryRowContext(ctx, query, args...).Scan(
		&book.ID, &book.RoleID, &book.BookID, &book.Name,
		&book.Level, &book.Experience, &book.CreatedAt, &book.UpdatedAt,
	)
	if err != nil {
		if err.Error() == "sql: no rows in result set" {
			return nil, store.ErrNotFound
		}
		return nil, fmt.Errorf("failed to get adventure book: %w", err)
	}

	return &book, nil
}

// ListAdventureBooks 获取冒险书列表
func (d *DB) ListAdventureBooks(ctx context.Context, roleID uint64) ([]*store.AdventureBook, error) {
	query := `
		SELECT id, role_id, book_id, name, level, experience, created_at, updated_at
		FROM adventure_book WHERE row_status = 'NORMAL' AND role_id = ? ORDER BY created_at DESC
	`

	rows, err := d.db.QueryContext(ctx, query, roleID)
	if err != nil {
		return nil, fmt.Errorf("failed to query adventure books: %w", err)
	}
	defer rows.Close()

	var books []*store.AdventureBook
	for rows.Next() {
		var book store.AdventureBook
		err := rows.Scan(&book.ID, &book.RoleID, &book.BookID, &book.Name,
			&book.Level, &book.Experience, &book.CreatedAt, &book.UpdatedAt)
		if err != nil {
			return nil, fmt.Errorf("failed to scan adventure book: %w", err)
		}
		books = append(books, &book)
	}

	return books, nil
}

// CreateAdventureBookCondition 创建冒险书条件
func (d *DB) CreateAdventureBookCondition(ctx context.Context, create *store.CreateAdventureBookCondition) (*store.AdventureBookCondition, error) {
	query := `
		INSERT INTO adventure_book_condition (book_id, condition_id, current, target, is_completed, created_at, updated_at)
		VALUES (?, ?, ?, ?, ?, ?, ?)
	`

	now := time.Now().Unix()
	result, err := d.db.ExecContext(ctx, query,
		create.BookID, create.ConditionID, create.Current, create.Target, false, now, now,
	)
	if err != nil {
		return nil, fmt.Errorf("failed to create adventure book condition: %w", err)
	}

	id, _ := result.LastInsertId()
	return &store.AdventureBookCondition{
		ID:          uint64(id),
		BookID:      create.BookID,
		ConditionID: create.ConditionID,
		Current:     create.Current,
		Target:      create.Target,
		IsCompleted: false,
		CreatedAt:   now,
		UpdatedAt:   now,
	}, nil
}

// UpdateAdventureBookCondition 更新冒险书条件
func (d *DB) UpdateAdventureBookCondition(ctx context.Context, update *store.UpdateAdventureBookCondition) error {
	var sets []string
	var args []interface{}

	if update.Current != nil {
		sets = append(sets, "current = ?")
		args = append(args, *update.Current)
	}
	if update.IsCompleted != nil {
		sets = append(sets, "is_completed = ?")
		args = append(args, *update.IsCompleted)
	}

	if len(sets) == 0 {
		return nil
	}

	sets = append(sets, "updated_at = ?")
	args = append(args, time.Now().Unix())
	args = append(args, update.ID)

	query := fmt.Sprintf("UPDATE adventure_book_condition SET %s WHERE id = ?", strings.Join(sets, ", "))
	_, err := d.db.ExecContext(ctx, query, args...)
	if err != nil {
		return fmt.Errorf("failed to update adventure book condition: %w", err)
	}

	return nil
}

// CreateAdventureBookReward 创建冒险书奖励
func (d *DB) CreateAdventureBookReward(ctx context.Context, create *store.CreateAdventureBookReward) (*store.AdventureBookReward, error) {
	query := `
		INSERT INTO adventure_book_reward (book_id, reward_id, reward_type, amount, is_claimed, created_at, updated_at)
		VALUES (?, ?, ?, ?, ?, ?, ?)
	`

	now := time.Now().Unix()
	result, err := d.db.ExecContext(ctx, query,
		create.BookID, create.RewardID, create.RewardType, create.Amount, false, now, now,
	)
	if err != nil {
		return nil, fmt.Errorf("failed to create adventure book reward: %w", err)
	}

	id, _ := result.LastInsertId()
	return &store.AdventureBookReward{
		ID:         uint64(id),
		BookID:     create.BookID,
		RewardID:   create.RewardID,
		RewardType: create.RewardType,
		Amount:     create.Amount,
		IsClaimed:  false,
		CreatedAt:  now,
		UpdatedAt:  now,
	}, nil
}

// ClaimAdventureBookReward 领取冒险书奖励
func (d *DB) ClaimAdventureBookReward(ctx context.Context, claim *store.ClaimAdventureBookReward) error {
	query := "UPDATE adventure_book_reward SET is_claimed = ?, claimed_at = ?, updated_at = ? WHERE id = ?"
	now := time.Now().Unix()
	_, err := d.db.ExecContext(ctx, query, true, claim.ClaimedAt.Unix(), now, claim.ID)
	if err != nil {
		return fmt.Errorf("failed to claim adventure book reward: %w", err)
	}
	return nil
}

// ListAdventureBookRewards 获取冒险书奖励列表
func (d *DB) ListAdventureBookRewards(ctx context.Context, bookID int32) ([]*store.AdventureBookReward, error) {
	query := `
		SELECT id, book_id, reward_id, reward_type, amount, is_claimed, claimed_at, created_at, updated_at
		FROM adventure_book_reward WHERE row_status = 'NORMAL' AND book_id = ? ORDER BY reward_id ASC
	`

	rows, err := d.db.QueryContext(ctx, query, bookID)
	if err != nil {
		return nil, fmt.Errorf("failed to query adventure book rewards: %w", err)
	}
	defer rows.Close()

	var rewards []*store.AdventureBookReward
	for rows.Next() {
		var reward store.AdventureBookReward
		err := rows.Scan(&reward.ID, &reward.BookID, &reward.RewardID,
			&reward.RewardType, &reward.Amount, &reward.IsClaimed,
			&reward.ClaimedAt, &reward.CreatedAt, &reward.UpdatedAt)
		if err != nil {
			return nil, fmt.Errorf("failed to scan adventure book reward: %w", err)
		}
		rewards = append(rewards, &reward)
	}

	return rewards, nil
}

// ==================== 冒险联盟相关实现 ====================

func (d *DB) GetAdventureUnionInfo(ctx context.Context, roleID uint64) (*store.AdventureUnionInfo, error) {
	query := `
		SELECT role_id, name, exp, level, day, typical_character_guid,
		       last_change_name_time, shareboard_background, shareboard_frame,
		       shareboard_show_antievil_score, auto_search_count,
		       shareboard_total_antievil_score, shareboard_antievil_score_refresh,
		       is_adventure_condition, created_at, updated_at
		FROM t_adventure_union
		WHERE role_id = ? AND row_status = 'NORMAL'
	`

	var info store.AdventureUnionInfo
	var createTime, updateTime, lastChangeNameTime int64

	err := d.db.QueryRowContext(ctx, query, roleID).Scan(
		&info.Name, &info.Exp, &info.Level, &info.Day, &info.TypicalCharacterGUID,
		&lastChangeNameTime, &info.ShareboardBackground, &info.ShareboardFrame,
		&info.ShareboardShowAntiEvilScore, &info.AutoSearchCount,
		&info.ShareboardTotalAntiEvilScore, &info.ShareboardAntiEvilScoreRefresh,
		&info.IsAdventureCondition, &createTime, &updateTime,
	)

	if err != nil {
		if err.Error() == "sql: no rows in result set" {
			return nil, store.ErrNotFound
		}
		return nil, fmt.Errorf("failed to get adventure union info: %w", err)
	}

	info.UpdateTime = time.Unix(updateTime, 0)
	info.LastChangeNameTime = time.Unix(lastChangeNameTime, 0)

	return &info, nil
}

func (d *DB) ChangeAdventureUnionName(ctx context.Context, roleID uint64, name string) error {
	query := `
		UPDATE t_adventure_union
		SET name = ?, last_change_name_time = ?, updated_at = ?
		WHERE role_id = ? AND row_status = 'NORMAL'
	`

	now := time.Now().Unix()
	result, err := d.db.ExecContext(ctx, query, name, now, now, roleID)
	if err != nil {
		return fmt.Errorf("failed to change adventure union name: %w", err)
	}

	rowsAffected, err := result.RowsAffected()
	if err != nil {
		return fmt.Errorf("failed to get rows affected: %w", err)
	}

	if rowsAffected == 0 {
		return store.ErrNotFound
	}

	return nil
}

func (d *DB) StartAdventureUnionExpedition(ctx context.Context, roleID uint64, expeditionID uint32, expeditionType uint32) error {
	query := `
		INSERT INTO t_adventure_union_expedition
		(role_id, expedition_id, expedition_type, status, start_time, created_at, updated_at)
		VALUES (?, ?, ?, 0, ?, ?, ?)
	`

	now := time.Now().Unix()
	_, err := d.db.ExecContext(ctx, query, roleID, expeditionID, expeditionType, now, now, now)
	if err != nil {
		return fmt.Errorf("failed to start adventure union expedition: %w", err)
	}

	return nil
}

func (d *DB) CancelAdventureUnionExpedition(ctx context.Context, roleID uint64, expeditionID uint32) error {
	query := `
		UPDATE t_adventure_union_expedition
		SET status = 2, end_time = ?, updated_at = ?
		WHERE role_id = ? AND expedition_id = ? AND status = 0 AND row_status = 'NORMAL'
	`

	now := time.Now().Unix()
	result, err := d.db.ExecContext(ctx, query, now, now, roleID, expeditionID)
	if err != nil {
		return fmt.Errorf("failed to cancel adventure union expedition: %w", err)
	}

	rowsAffected, err := result.RowsAffected()
	if err != nil {
		return fmt.Errorf("failed to get rows affected: %w", err)
	}

	if rowsAffected == 0 {
		return fmt.Errorf("expedition not found or not in progress")
	}

	return nil
}

func (d *DB) ClaimAdventureUnionExpeditionReward(ctx context.Context, roleID uint64, expeditionID uint32) error {
	query := `
		UPDATE t_adventure_union_expedition
		SET reward_claimed = 1, updated_at = ?
		WHERE role_id = ? AND expedition_id = ? AND status = 1 AND row_status = 'NORMAL'
	`

	now := time.Now().Unix()
	result, err := d.db.ExecContext(ctx, query, now, roleID, expeditionID)
	if err != nil {
		return fmt.Errorf("failed to claim adventure union expedition reward: %w", err)
	}

	rowsAffected, err := result.RowsAffected()
	if err != nil {
		return fmt.Errorf("failed to get rows affected: %w", err)
	}

	if rowsAffected == 0 {
		return fmt.Errorf("expedition not found or not completed")
	}

	return nil
}

func (d *DB) StartAdventureUnionSubdue(ctx context.Context, roleID uint64, subdueID uint32, subdueType uint32, characterGUID uint64) error {
	query := `
		INSERT INTO t_adventure_union_subdue
		(role_id, subdue_id, subdue_type, character_guid, status, start_time, created_at, updated_at)
		VALUES (?, ?, ?, ?, 0, ?, ?, ?)
	`

	now := time.Now().Unix()
	_, err := d.db.ExecContext(ctx, query, roleID, subdueID, subdueType, characterGUID, now, now, now)
	if err != nil {
		return fmt.Errorf("failed to start adventure union subdue: %w", err)
	}

	return nil
}

func (d *DB) ClaimAdventureUnionSubdueReward(ctx context.Context, roleID uint64, subdueID uint32) error {
	query := `
		UPDATE t_adventure_union_subdue
		SET reward_claimed = 1, updated_at = ?
		WHERE role_id = ? AND subdue_id = ? AND status = 1 AND row_status = 'NORMAL'
	`

	now := time.Now().Unix()
	result, err := d.db.ExecContext(ctx, query, now, roleID, subdueID)
	if err != nil {
		return fmt.Errorf("failed to claim adventure union subdue reward: %w", err)
	}

	rowsAffected, err := result.RowsAffected()
	if err != nil {
		return fmt.Errorf("failed to get rows affected: %w", err)
	}

	if rowsAffected == 0 {
		return fmt.Errorf("subdue not found or not completed")
	}

	return nil
}

func (d *DB) OpenAdventureUnionShareboardSlot(ctx context.Context, roleID uint64, slotID uint32) error {
	query := `
		INSERT INTO t_adventure_union_shareboard_slot
		(role_id, slot_id, slot_type, created_at, updated_at)
		VALUES (?, ?, 1, ?, ?)
	`

	now := time.Now().Unix()
	_, err := d.db.ExecContext(ctx, query, roleID, slotID, now, now)
	if err != nil {
		return fmt.Errorf("failed to open adventure union shareboard slot: %w", err)
	}

	return nil
}

func (d *DB) SetAdventureUnionShareboard(ctx context.Context, roleID uint64, slotID uint32, slotType uint32, show bool) error {
	query := `
		UPDATE t_adventure_union_shareboard_slot
		SET slot_type = ?, show = ?, updated_at = ?
		WHERE role_id = ? AND slot_id = ? AND row_status = 'NORMAL'
	`

	now := time.Now().Unix()
	result, err := d.db.ExecContext(ctx, query, slotType, show, now, roleID, slotID)
	if err != nil {
		return fmt.Errorf("failed to set adventure union shareboard: %w", err)
	}

	rowsAffected, err := result.RowsAffected()
	if err != nil {
		return fmt.Errorf("failed to get rows affected: %w", err)
	}

	if rowsAffected == 0 {
		return fmt.Errorf("shareboard slot not found")
	}

	return nil
}

func (d *DB) ClaimAdventureReapReward(ctx context.Context, roleID uint64, reapID uint32) error {
	query := `
		UPDATE t_adventure_reap
		SET claimed = 1, claim_time = ?, updated_at = ?
		WHERE role_id = ? AND reap_id = ? AND row_status = 'NORMAL'
	`

	now := time.Now().Unix()
	result, err := d.db.ExecContext(ctx, query, now, now, roleID, reapID)
	if err != nil {
		return fmt.Errorf("failed to claim adventure reap reward: %w", err)
	}

	rowsAffected, err := result.RowsAffected()
	if err != nil {
		return fmt.Errorf("failed to get rows affected: %w", err)
	}

	if rowsAffected == 0 {
		return fmt.Errorf("adventure reap not found")
	}

	return nil
}

func (d *DB) StartAdventureUnionSearch(ctx context.Context, roleID uint64) error {
	query := `
		UPDATE t_adventure_union
		SET auto_search_count = auto_search_count + 1, updated_at = ?
		WHERE role_id = ? AND row_status = 'NORMAL'
	`

	now := time.Now().Unix()
	result, err := d.db.ExecContext(ctx, query, now, roleID)
	if err != nil {
		return fmt.Errorf("failed to start adventure union search: %w", err)
	}

	rowsAffected, err := result.RowsAffected()
	if err != nil {
		return fmt.Errorf("failed to get rows affected: %w", err)
	}

	if rowsAffected == 0 {
		return store.ErrNotFound
	}

	return nil
}

func (d *DB) ClaimAdventureUnionCollectionReward(ctx context.Context, roleID uint64, collectionID uint32) error {
	query := `
		UPDATE t_adventure_union_collection
		SET reward_claimed = 1, updated_at = ?
		WHERE role_id = ? AND collection_id = ? AND completed = 1 AND row_status = 'NORMAL'
	`

	now := time.Now().Unix()
	result, err := d.db.ExecContext(ctx, query, now, roleID, collectionID)
	if err != nil {
		return fmt.Errorf("failed to claim adventure union collection reward: %w", err)
	}

	rowsAffected, err := result.RowsAffected()
	if err != nil {
		return fmt.Errorf("failed to get rows affected: %w", err)
	}

	if rowsAffected == 0 {
		return fmt.Errorf("collection not found or not completed")
	}

	return nil
}

func (d *DB) ClaimAdventureUnionLevelReward(ctx context.Context, roleID uint64, level uint32) error {
	tx, err := d.db.BeginTx(ctx, nil)
	if err != nil {
		return fmt.Errorf("failed to begin transaction: %w", err)
	}
	defer tx.Rollback()

	var unionLevel uint32
	err = tx.QueryRowContext(ctx, "SELECT level FROM t_adventure_union WHERE role_id = ? AND row_status = 'NORMAL'", roleID).Scan(&unionLevel)
	if err != nil {
		if err.Error() == "sql: no rows in result set" {
			return store.ErrNotFound
		}
		return fmt.Errorf("failed to get adventure union level: %w", err)
	}

	if unionLevel < level {
		return fmt.Errorf("level not reached")
	}

	now := time.Now().Unix()
	result, err := tx.ExecContext(ctx, `
		UPDATE t_adventure_union_level_reward
		SET claimed = 1, claim_time = ?, updated_at = ?
		WHERE role_id = ? AND level = ? AND row_status = 'NORMAL'
	`, now, now, roleID, level)
	if err != nil {
		return fmt.Errorf("failed to claim level reward: %w", err)
	}

	rowsAffected, err := result.RowsAffected()
	if err != nil {
		return fmt.Errorf("failed to get rows affected: %w", err)
	}

	if rowsAffected == 0 {
		_, err = tx.ExecContext(ctx, `
			INSERT INTO t_adventure_union_level_reward
			(role_id, level, reward_type, reward_index, reward_count, claimed, claim_time, created_at, updated_at)
			VALUES (?, ?, 1, 1, 100, 1, ?, ?, ?)
		`, roleID, level, now, now, now)
		if err != nil {
			return fmt.Errorf("failed to create level reward: %w", err)
		}
	}

	if err := tx.Commit(); err != nil {
		return fmt.Errorf("failed to commit transaction: %w", err)
	}

	return nil
}
