package sqlite

import (
	"context"
	"database/sql"
	"fmt"
	"time"

	"github.com/pixb/DnfGameServer/dnf-go-server/store"
)

// ==================== 冒险联盟相关实现 ====================

func (d *DB) GetAdventureUnionInfo(ctx context.Context, roleID uint64) (*store.AdventureUnionInfo, error) {
	query := `
		SELECT name, exp, level, day, typical_character_guid,
		       last_change_name_time, shareboard_background, shareboard_frame,
		       shareboard_show_antievil_score, auto_search_count,
		       shareboard_total_antievil_score, shareboard_antievil_score_refresh,
		       is_adventure_condition, create_time, update_time
		FROM t_adventure_union
		WHERE role_id = ?
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

	if err == sql.ErrNoRows {
		now := time.Now().Unix()
		insertQuery := `
			INSERT INTO t_adventure_union
			(role_id, name, exp, level, day, last_change_name_time, 
			 shareboard_background, shareboard_frame, shareboard_show_antievil_score,
			 auto_search_count, shareboard_total_antievil_score, shareboard_antievil_score_refresh,
			 is_adventure_condition, create_time, update_time)
			VALUES (?, ?, 0, 1, 1, ?, 0, 0, 0, 0, 0, 0, 0, ?, ?)
		`
		_, err := d.db.ExecContext(ctx, insertQuery, roleID, fmt.Sprintf("冒险联盟%d", roleID), now, now, now)
		if err != nil {
			return nil, fmt.Errorf("failed to create adventure union: %w", err)
		}

		info = store.AdventureUnionInfo{
			Exp:                            0,
			Level:                          1,
			Day:                            1,
			TypicalCharacterGUID:           0,
			Name:                           fmt.Sprintf("冒险联盟%d", roleID),
			UpdateTime:                     time.Unix(now, 0),
			LastChangeNameTime:             time.Unix(now, 0),
			ShareboardBackground:           0,
			ShareboardFrame:                0,
			ShareboardShowAntiEvilScore:    false,
			AutoSearchCount:                0,
			ShareboardTotalAntiEvilScore:   0,
			ShareboardAntiEvilScoreRefresh: false,
			IsAdventureCondition:           false,
		}
		return &info, nil
	}
	if err != nil {
		return nil, fmt.Errorf("failed to get adventure union info: %w", err)
	}

	info.UpdateTime = time.Unix(updateTime, 0)
	info.LastChangeNameTime = time.Unix(lastChangeNameTime, 0)

	return &info, nil
}

func (d *DB) ChangeAdventureUnionName(ctx context.Context, roleID uint64, name string) error {
	query := `
		UPDATE t_adventure_union
		SET name = ?, last_change_name_time = ?, update_time = ?
		WHERE role_id = ?
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
		insertQuery := `
			INSERT INTO t_adventure_union
			(role_id, name, exp, level, day, last_change_name_time, 
			 shareboard_background, shareboard_frame, shareboard_show_antievil_score,
			 auto_search_count, shareboard_total_antievil_score, shareboard_antievil_score_refresh,
			 is_adventure_condition, create_time, update_time)
			VALUES (?, ?, 0, 1, 1, ?, 0, 0, 0, 0, 0, 0, 0, ?, ?)
		`
		_, err := d.db.ExecContext(ctx, insertQuery, roleID, name, now, now, now)
		if err != nil {
			return fmt.Errorf("failed to create adventure union: %w", err)
		}
	}

	return nil
}

func (d *DB) StartAdventureUnionExpedition(ctx context.Context, roleID uint64, expeditionID uint32, expeditionType uint32) error {
	query := `
		INSERT INTO t_adventure_union_expedition
		(role_id, expedition_id, expedition_type, status, start_time, create_time, update_time)
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
		SET status = 2, end_time = ?, update_time = ?
		WHERE role_id = ? AND expedition_id = ? AND status = 0
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
		SET reward_claimed = 1, update_time = ?
		WHERE role_id = ? AND expedition_id = ? AND status = 1
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
		(role_id, subdue_id, subdue_type, character_guid, status, start_time, create_time, update_time)
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
		SET reward_claimed = 1, update_time = ?
		WHERE role_id = ? AND subdue_id = ? AND status = 1
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
		(role_id, slot_id, slot_type, create_time, update_time)
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
		SET slot_type = ?, show = ?, update_time = ?
		WHERE role_id = ? AND slot_id = ?
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
		SET claimed = 1, claim_time = ?
		WHERE role_id = ? AND reap_id = ?
	`

	now := time.Now().Unix()
	result, err := d.db.ExecContext(ctx, query, now, roleID, reapID)
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
		SET auto_search_count = auto_search_count + 1, update_time = ?
		WHERE role_id = ?
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
		SET reward_claimed = 1, update_time = ?
		WHERE role_id = ? AND collection_id = ? AND completed = 1
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
	err = tx.QueryRowContext(ctx, "SELECT level FROM t_adventure_union WHERE role_id = ?", roleID).Scan(&unionLevel)
	if err == sql.ErrNoRows {
		return store.ErrNotFound
	}
	if err != nil {
		return fmt.Errorf("failed to get adventure union level: %w", err)
	}

	if unionLevel < level {
		return fmt.Errorf("level not reached")
	}

	now := time.Now().Unix()
	result, err := tx.ExecContext(ctx, `
		UPDATE t_adventure_union_level_reward
		SET claimed = 1, claim_time = ?
		WHERE role_id = ? AND level = ?
	`, now, roleID, level)
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
			(role_id, level, reward_type, reward_index, reward_count, claimed, claim_time, create_time)
			VALUES (?, ?, 1, 1, 100, 1, ?, ?)
		`, roleID, level, now, now)
		if err != nil {
			return fmt.Errorf("failed to create level reward: %w", err)
		}
	}

	if err := tx.Commit(); err != nil {
		return fmt.Errorf("failed to commit transaction: %w", err)
	}

	return nil
}

// ==================== 冒险数据 CRUD (存根) ====================

func (d *DB) GetAdventureData(ctx context.Context, find *store.FindAdventureData) (*store.AdventureData, error) {
	return nil, fmt.Errorf("not implemented in sqlite")
}

func (d *DB) CreateAdventureData(ctx context.Context, create *store.CreateAdventureData) (*store.AdventureData, error) {
	return nil, fmt.Errorf("not implemented in sqlite")
}

func (d *DB) UpdateAdventureData(ctx context.Context, update *store.UpdateAdventureData) error {
	return fmt.Errorf("not implemented in sqlite")
}

// ==================== 冒险存储物品 CRUD (存根) ====================

func (d *DB) CreateAdventureStorageItem(ctx context.Context, create *store.CreateAdventureStorageItem) (*store.AdventureStorageItem, error) {
	return nil, fmt.Errorf("not implemented in sqlite")
}

func (d *DB) UpdateAdventureStorageItem(ctx context.Context, update *store.UpdateAdventureStorageItem) error {
	return fmt.Errorf("not implemented in sqlite")
}

func (d *DB) ListAdventureStorageItems(ctx context.Context, find *store.FindAdventureStorageItem) ([]*store.AdventureStorageItem, error) {
	return nil, fmt.Errorf("not implemented in sqlite")
}

func (d *DB) DeleteAdventureStorageItem(ctx context.Context, id uint64) error {
	return fmt.Errorf("not implemented in sqlite")
}

// ==================== 冒险收获 CRUD (存根) ====================

func (d *DB) CreateAdventureReap(ctx context.Context, create *store.CreateAdventureReap) (*store.AdventureReap, error) {
	return nil, fmt.Errorf("not implemented in sqlite")
}

func (d *DB) UpdateAdventureReap(ctx context.Context, update *store.UpdateAdventureReap) error {
	return fmt.Errorf("not implemented in sqlite")
}

func (d *DB) ListAdventureReaps(ctx context.Context, roleID uint64) ([]*store.AdventureReap, error) {
	return nil, fmt.Errorf("not implemented in sqlite")
}

func (d *DB) DeleteAdventureReap(ctx context.Context, id uint64) error {
	return fmt.Errorf("not implemented in sqlite")
}

// ==================== 冒险书 CRUD (存根) ====================

func (d *DB) CreateAdventureBook(ctx context.Context, create *store.CreateAdventureBook) (*store.AdventureBook, error) {
	return nil, fmt.Errorf("not implemented in sqlite")
}

func (d *DB) UpdateAdventureBook(ctx context.Context, update *store.UpdateAdventureBook) error {
	return fmt.Errorf("not implemented in sqlite")
}

func (d *DB) GetAdventureBook(ctx context.Context, find *store.FindAdventureBook) (*store.AdventureBook, error) {
	return nil, fmt.Errorf("not implemented in sqlite")
}

func (d *DB) ListAdventureBooks(ctx context.Context, roleID uint64) ([]*store.AdventureBook, error) {
	return nil, fmt.Errorf("not implemented in sqlite")
}

// ==================== 冒险书条件 CRUD (存根) ====================

func (d *DB) CreateAdventureBookCondition(ctx context.Context, create *store.CreateAdventureBookCondition) (*store.AdventureBookCondition, error) {
	return nil, fmt.Errorf("not implemented in sqlite")
}

func (d *DB) UpdateAdventureBookCondition(ctx context.Context, update *store.UpdateAdventureBookCondition) error {
	return fmt.Errorf("not implemented in sqlite")
}

// ==================== 冒险书奖励 CRUD (存根) ====================

func (d *DB) CreateAdventureBookReward(ctx context.Context, create *store.CreateAdventureBookReward) (*store.AdventureBookReward, error) {
	return nil, fmt.Errorf("not implemented in sqlite")
}

func (d *DB) ClaimAdventureBookReward(ctx context.Context, claim *store.ClaimAdventureBookReward) error {
	return fmt.Errorf("not implemented in sqlite")
}

func (d *DB) ListAdventureBookRewards(ctx context.Context, bookID int32) ([]*store.AdventureBookReward, error) {
	return nil, fmt.Errorf("not implemented in sqlite")
}
