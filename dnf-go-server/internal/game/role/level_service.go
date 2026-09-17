package role

import (
	"context"
	"fmt"

	"github.com/pixb/DnfGameServer/dnf-go-server/store"
)

// 升级规则(2026-09-07 第四十五轮):
// 升级所需经验 = 当前等级 * ExpPerLevel; 每升一级派发 SPPerLevel 技能点。
const (
	// ExpPerLevel 升级经验基数(level→level+1 需 level*ExpPerLevel)
	ExpPerLevel = int64(100)
	// SPPerLevel 每升一级派发技能点
	SPPerLevel = int32(20)
	// MaxLevel 等级上限(DNF 经典上限, 防止异常大经验值死循环)
	MaxLevel = int32(156)
)

// LevelUpResult 升级结果
type LevelUpResult struct {
	LevelUps int32 // 本次连升级数
	NewLevel int32
	NewExp   int64
	NewSP    int32
}

// AddRoleExp 增加角色经验并处理升级(循环扣减经验/提升等级/派发 SP)。
// 读实时角色(NoCache)防缓存遮蔽; 返回升级结果, 无升级时 LevelUps=0。
func AddRoleExp(ctx context.Context, s *store.Store, roleID uint64, amount int64) (*LevelUpResult, error) {
	if amount <= 0 {
		// 非正经验: 仅返回当前状态
		r, err := s.GetRole(ctx, &store.FindRole{FindBase: store.FindBase{ID: &roleID}, NoCache: true})
		if err != nil {
			return nil, fmt.Errorf("failed to get role: %w", err)
		}
		return &LevelUpResult{NewLevel: r.Level, NewExp: r.Exp, NewSP: r.SP}, nil
	}

	role, err := s.GetRole(ctx, &store.FindRole{FindBase: store.FindBase{ID: &roleID}, NoCache: true})
	if err != nil {
		return nil, fmt.Errorf("failed to get role: %w", err)
	}

	exp := role.Exp + amount
	level := role.Level
	sp := role.SP
	ups := int32(0)
	for level < MaxLevel && exp >= int64(level)*ExpPerLevel {
		exp -= int64(level) * ExpPerLevel
		level++
		sp += SPPerLevel
		ups++
	}

	if ups == 0 && exp == role.Exp {
		return &LevelUpResult{NewLevel: level, NewExp: exp, NewSP: sp}, nil
	}

	if _, err := s.UpdateRole(ctx, &store.UpdateRole{
		ID:    roleID,
		Level: &level,
		Exp:   &exp,
		SP:    &sp,
	}); err != nil {
		return nil, fmt.Errorf("failed to update role after exp gain: %w", err)
	}

	return &LevelUpResult{LevelUps: ups, NewLevel: level, NewExp: exp, NewSP: sp}, nil
}
