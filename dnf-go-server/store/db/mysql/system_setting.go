package mysql

import (
	"context"
	"database/sql"
	"encoding/json"
	"fmt"

	"github.com/pixb/DnfGameServer/dnf-go-server/store"
)

// InstanceSettingKey 设置键
type InstanceSettingKey string

const (
	InstanceSettingKeyBasic InstanceSettingKey = "basic"
)

// GetInstanceBasicSetting 获取实例基本设置
func (d *DB) GetInstanceBasicSetting(ctx context.Context) (*store.InstanceBasicSetting, error) {
	setting, err := d.GetSystemSetting(ctx, string(InstanceSettingKeyBasic))
	if err != nil {
		if err == sql.ErrNoRows {
			// 返回默认设置
			return &store.InstanceBasicSetting{
				Version: "1.0.0",
				Name:    "DNF Go Server",
			}, nil
		}
		return nil, err
	}

	var basic store.InstanceBasicSetting
	if err := json.Unmarshal([]byte(setting.Value), &basic); err != nil {
		return nil, fmt.Errorf("failed to unmarshal basic setting: %w", err)
	}
	return &basic, nil
}

// UpsertInstanceSetting 更新或创建设置
func (d *DB) UpsertInstanceSetting(ctx context.Context, setting *store.InstanceSetting) (*store.InstanceSetting, error) {
	query := `
      INSERT INTO system_setting (name, value, description)
      VALUES (?, ?, ?)
      ON DUPLICATE KEY UPDATE
      value = VALUES(value), description = VALUES(description)
   `

	_, err := d.db.ExecContext(ctx, query, setting.Key, setting.Value, setting.Description)
	if err != nil {
		return nil, fmt.Errorf("failed to upsert setting: %w", err)
	}

	return setting, nil
}

// GetSystemSetting 获取系统设置
func (d *DB) GetSystemSetting(ctx context.Context, name string) (*store.InstanceSetting, error) {
	query := `SELECT name, value, description FROM system_setting WHERE name = ?`

	var s store.InstanceSetting
	err := d.db.QueryRowContext(ctx, query, name).Scan(&s.Key, &s.Value, &s.Description)
	if err != nil {
		return nil, err
	}
	return &s, nil
}

// ListSystemSettings 获取所有系统设置
func (d *DB) ListSystemSettings(ctx context.Context) ([]*store.InstanceSetting, error) {
	query := `SELECT name, value, description FROM system_setting`

	rows, err := d.db.QueryContext(ctx, query)
	if err != nil {
		return nil, fmt.Errorf("failed to query settings: %w", err)
	}
	defer rows.Close()

	var settings []*store.InstanceSetting
	for rows.Next() {
		var s store.InstanceSetting
		err := rows.Scan(&s.Key, &s.Value, &s.Description)
		if err != nil {
			return nil, fmt.Errorf("failed to scan setting: %w", err)
		}
		settings = append(settings, &s)
	}

	return settings, nil
}
