package party_service

import (
	"context"

	"github.com/pixb/DnfGameServer/dnf-go-server/store"
)

type PartyService struct {
	store *store.Store
}

func NewPartyService(store *store.Store) *PartyService {
	return &PartyService{
		store: store,
	}
}

// SearchPartyList 搜索队伍列表
func (s *PartyService) SearchPartyList(ctx context.Context, dungeonIndex, minLevel, maxLevel uint32) ([]*store.PartyInfo, error) {
	return s.store.SearchPartyList(ctx, dungeonIndex, minLevel, maxLevel)
}

// RecommendGroup 推荐队伍
func (s *PartyService) RecommendGroup(ctx context.Context, dungeonIndex uint32) ([]*store.PartyInfo, error) {
	return s.store.RecommendGroup(ctx, dungeonIndex)
}

// ControlGroup 控制队伍
func (s *PartyService) ControlGroup(ctx context.Context, roleID uint64, action uint32, targetGuid uint64, partyGuid uint64) error {
	return s.store.ControlGroup(ctx, roleID, action, targetGuid, partyGuid)
}

// StartMultiPlay 开始多人游戏
func (s *PartyService) StartMultiPlay(ctx context.Context, roleID uint64, partyGuid uint64) (*store.StartMultiPlayResult, error) {
	return s.store.StartMultiPlay(ctx, roleID, partyGuid)
}

// SyncDungeon 同步多人副本
func (s *PartyService) SyncDungeon(ctx context.Context, roleID uint64, stageID, progress uint32) error {
	return s.store.SyncDungeon(ctx, roleID, stageID, progress)
}

// MultiPlayDungeonEnterComplete 多人副本进入完成
func (s *PartyService) MultiPlayDungeonEnterComplete(ctx context.Context, roleID uint64, stageID uint32) error {
	return s.store.MultiPlayDungeonEnterComplete(ctx, roleID, stageID)
}

// GetPartyLoadingStatus 获取队伍加载状态
func (s *PartyService) GetPartyLoadingStatus(ctx context.Context, roleID uint64) ([]uint64, []uint64, error) {
	return s.store.GetPartyLoadingStatus(ctx, roleID)
}

// ReadyToLockstep 准备锁定步骤
func (s *PartyService) ReadyToLockstep(ctx context.Context, roleID uint64, ready bool) error {
	return s.store.ReadyToLockstep(ctx, roleID, ready)
}

// VoteKickOut 投票踢出用户
func (s *PartyService) VoteKickOut(ctx context.Context, roleID, targetGuid uint64) error {
	return s.store.VoteKickOut(ctx, roleID, targetGuid)
}

// ConnectBattleServer 连接战斗服务器
func (s *PartyService) ConnectBattleServer(ctx context.Context, matchingGuid, charguid uint64, authKey string) (string, uint32, error) {
	return s.store.ConnectBattleServer(ctx, matchingGuid, charguid, authKey)
}

// CheckProhibitedWord 检查禁用词
func (s *PartyService) CheckProhibitedWord(ctx context.Context, word string) (bool, error) {
	return s.store.CheckProhibitedWord(ctx, word)
}

// HalfOpenPartyAccept 半公开队伍接受
func (s *PartyService) HalfOpenPartyAccept(ctx context.Context, roleID, partyGuid uint64) error {
	return s.store.HalfOpenPartyAccept(ctx, roleID, partyGuid)
}

// HalfOpenPartyRefuse 半公开队伍拒绝
func (s *PartyService) HalfOpenPartyRefuse(ctx context.Context, roleID, partyGuid uint64) error {
	return s.store.HalfOpenPartyRefuse(ctx, roleID, partyGuid)
}

// ControlGroupCustom 控制队伍自定义
func (s *PartyService) ControlGroupCustom(ctx context.Context, roleID uint64, customData []byte) error {
	return s.store.ControlGroupCustom(ctx, roleID, customData)
}

// ControlGroupQueryarea 控制队伍查询区域
func (s *PartyService) ControlGroupQueryarea(ctx context.Context, roleID uint64) error {
	return s.store.ControlGroupQueryarea(ctx, roleID)
}

// HalfOpenPartyJoin 半公开队伍加入
func (s *PartyService) HalfOpenPartyJoin(ctx context.Context, roleID, partyGuid uint64) error {
	return s.store.HalfOpenPartyJoin(ctx, roleID, partyGuid)
}

// PartyDungeonCondition 多人游戏副本条件
func (s *PartyService) PartyDungeonCondition(ctx context.Context, roleID uint64, dungeonIndex uint32) error {
	return s.store.PartyDungeonCondition(ctx, roleID, dungeonIndex)
}

// MultiPlayStartDungeon 多人游戏开始副本
func (s *PartyService) MultiPlayStartDungeon(ctx context.Context, roleID uint64, stageID uint32) error {
	return s.store.MultiPlayStartDungeon(ctx, roleID, stageID)
}

// RequestToReEnterAcceptDungeon 请求进入副本接受
func (s *PartyService) RequestToReEnterAcceptDungeon(ctx context.Context, roleID, charguid uint64) error {
	return s.store.RequestToReEnterAcceptDungeon(ctx, roleID, charguid)
}

// RequestToReEnterDungeon 请求进入副本
func (s *PartyService) RequestToReEnterDungeon(ctx context.Context, roleID, charguid uint64) error {
	return s.store.RequestToReEnterDungeon(ctx, roleID, charguid)
}

// SuggestMoveParty 建议移动队伍
func (s *PartyService) SuggestMoveParty(ctx context.Context, roleID uint64, area uint32) error {
	return s.store.SuggestMoveParty(ctx, roleID, area)
}

// TargetUserPartyInfo 目标用户队伍信息
func (s *PartyService) TargetUserPartyInfo(ctx context.Context, roleID, targetGuid uint64) (*store.PartyInfo, error) {
	return s.store.TargetUserPartyInfo(ctx, roleID, targetGuid)
}

// WaitinigToUsersLoading 等待用户加载
func (s *PartyService) WaitinigToUsersLoading(ctx context.Context, roleID uint64) error {
	return s.store.WaitinigToUsersLoading(ctx, roleID)
}

// CreateParty 创建队伍
func (s *PartyService) CreateParty(ctx context.Context, roleID uint64) error {
	return s.store.ControlGroup(ctx, roleID, 0, 0, 0)
}

// InviteToParty 邀请进入队伍
func (s *PartyService) InviteToParty(ctx context.Context, roleID, targetGuid, partyGuid uint64) error {
	return s.store.ControlGroup(ctx, roleID, 1, targetGuid, partyGuid)
}

// LeaveParty 离开队伍
func (s *PartyService) LeaveParty(ctx context.Context, roleID uint64) error {
	return s.store.ControlGroup(ctx, roleID, 2, 0, 0)
}

// KickFromParty 踢出队伍
func (s *PartyService) KickFromParty(ctx context.Context, roleID, targetGuid uint64) error {
	return s.store.ControlGroup(ctx, roleID, 3, targetGuid, 0)
}

// ChangePartyLeader 更换队长
func (s *PartyService) ChangePartyLeader(ctx context.Context, roleID, targetGuid uint64) error {
	return s.store.ControlGroup(ctx, roleID, 4, targetGuid, 0)
}
