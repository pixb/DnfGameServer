package store

import (
	"context"

	dnfv1 "github.com/pixb/DnfGameServer/dnf-go-server/proto/gen/dnf/v1"
)

type PartyInfo struct {
	PartyGuid    uint64
	LeaderGuid   uint64
	Name         string
	MaxMembers   uint32
	Members      []*dnfv1.GroupMember
	DungeonIndex uint32
	RoomID       uint64
	MinLevel     uint32
	MaxLevel     uint32
	Area         uint32
	SubType      uint32
	StageIndex   uint32
	PublicType   uint32
}

type StartMultiPlayResult struct {
	MatchingGuid uint64
	DungeonGuid  uint64
	IP           string
	Port         uint32
	Users        []uint64
	Detail       []*dnfv1.UserMinimumInfo
}

// SearchPartyList 搜索队伍列表
func (s *Store) SearchPartyList(ctx context.Context, dungeonIndex, minLevel, maxLevel uint32) ([]*PartyInfo, error) {
	return s.driver.SearchPartyList(ctx, dungeonIndex, minLevel, maxLevel)
}

// RecommendGroup 推荐队伍
func (s *Store) RecommendGroup(ctx context.Context, dungeonIndex uint32) ([]*PartyInfo, error) {
	return s.driver.RecommendGroup(ctx, dungeonIndex)
}

// ControlGroup 控制队伍
func (s *Store) ControlGroup(ctx context.Context, roleID uint64, action uint32, targetGuid uint64, partyGuid uint64) error {
	return s.driver.ControlGroup(ctx, roleID, action, targetGuid, partyGuid)
}

// StartMultiPlay 开始多人游戏
func (s *Store) StartMultiPlay(ctx context.Context, roleID uint64, partyGuid uint64) (*StartMultiPlayResult, error) {
	return s.driver.StartMultiPlay(ctx, roleID, partyGuid)
}

// SyncDungeon 同步多人副本
func (s *Store) SyncDungeon(ctx context.Context, roleID uint64, stageID, progress uint32) error {
	return s.driver.SyncDungeon(ctx, roleID, stageID, progress)
}

// MultiPlayDungeonEnterComplete 多人副本进入完成
func (s *Store) MultiPlayDungeonEnterComplete(ctx context.Context, roleID uint64, stageID uint32) error {
	return s.driver.MultiPlayDungeonEnterComplete(ctx, roleID, stageID)
}

// GetPartyLoadingStatus 获取队伍加载状态
func (s *Store) GetPartyLoadingStatus(ctx context.Context, roleID uint64) ([]uint64, []uint64, error) {
	return s.driver.GetPartyLoadingStatus(ctx, roleID)
}

// ReadyToLockstep 准备锁定步骤
func (s *Store) ReadyToLockstep(ctx context.Context, roleID uint64, ready bool) error {
	return s.driver.ReadyToLockstep(ctx, roleID, ready)
}

// VoteKickOut 投票踢出用户
func (s *Store) VoteKickOut(ctx context.Context, roleID, targetGuid uint64) error {
	return s.driver.VoteKickOut(ctx, roleID, targetGuid)
}

// ConnectBattleServer 连接战斗服务器
func (s *Store) ConnectBattleServer(ctx context.Context, matchingGuid, charguid uint64, authKey string) (string, uint32, error) {
	return s.driver.ConnectBattleServer(ctx, matchingGuid, charguid, authKey)
}

// CheckProhibitedWord 检查禁用词
func (s *Store) CheckProhibitedWord(ctx context.Context, word string) (bool, error) {
	return s.driver.CheckProhibitedWord(ctx, word)
}

// HalfOpenPartyAccept 半公开队伍接受
func (s *Store) HalfOpenPartyAccept(ctx context.Context, roleID, partyGuid uint64) error {
	return s.driver.HalfOpenPartyAccept(ctx, roleID, partyGuid)
}

// HalfOpenPartyRefuse 半公开队伍拒绝
func (s *Store) HalfOpenPartyRefuse(ctx context.Context, roleID, partyGuid uint64) error {
	return s.driver.HalfOpenPartyRefuse(ctx, roleID, partyGuid)
}

// ControlGroupCustom 控制队伍自定义
func (s *Store) ControlGroupCustom(ctx context.Context, roleID uint64, customData []byte) error {
	return s.driver.ControlGroupCustom(ctx, roleID, customData)
}

// ControlGroupQueryarea 控制队伍查询区域
func (s *Store) ControlGroupQueryarea(ctx context.Context, roleID uint64) error {
	return s.driver.ControlGroupQueryarea(ctx, roleID)
}

// HalfOpenPartyJoin 半公开队伍加入
func (s *Store) HalfOpenPartyJoin(ctx context.Context, roleID, partyGuid uint64) error {
	return s.driver.HalfOpenPartyJoin(ctx, roleID, partyGuid)
}

// PartyDungeonCondition 多人游戏副本条件
func (s *Store) PartyDungeonCondition(ctx context.Context, roleID uint64, dungeonIndex uint32) error {
	return s.driver.PartyDungeonCondition(ctx, roleID, dungeonIndex)
}

// MultiPlayStartDungeon 多人游戏开始副本
func (s *Store) MultiPlayStartDungeon(ctx context.Context, roleID uint64, stageID uint32) error {
	return s.driver.MultiPlayStartDungeon(ctx, roleID, stageID)
}

// RequestToReEnterAcceptDungeon 请求进入副本接受
func (s *Store) RequestToReEnterAcceptDungeon(ctx context.Context, roleID, charguid uint64) error {
	return s.driver.RequestToReEnterAcceptDungeon(ctx, roleID, charguid)
}

// RequestToReEnterDungeon 请求进入副本
func (s *Store) RequestToReEnterDungeon(ctx context.Context, roleID, charguid uint64) error {
	return s.driver.RequestToReEnterDungeon(ctx, roleID, charguid)
}

// SuggestMoveParty 建议移动队伍
func (s *Store) SuggestMoveParty(ctx context.Context, roleID uint64, area uint32) error {
	return s.driver.SuggestMoveParty(ctx, roleID, area)
}

// TargetUserPartyInfo 目标用户队伍信息
func (s *Store) TargetUserPartyInfo(ctx context.Context, roleID, targetGuid uint64) (*PartyInfo, error) {
	return s.driver.TargetUserPartyInfo(ctx, roleID, targetGuid)
}

// WaitinigToUsersLoading 等待用户加载
func (s *Store) WaitinigToUsersLoading(ctx context.Context, roleID uint64) error {
	return s.driver.WaitinigToUsersLoading(ctx, roleID)
}
