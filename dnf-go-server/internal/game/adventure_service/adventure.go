package adventure_service

import (
	"context"

	"github.com/pixb/DnfGameServer/dnf-go-server/store"
)

// AdventureUnionInfo 冒险联盟信息（委托 store.AdventureUnionInfo）
type AdventureUnionInfo = store.AdventureUnionInfo

// AdventureService 冒险联盟服务（委托 store.Store）
type AdventureService struct {
	store *store.Store
}

// NewAdventureService 创建冒险联盟服务
func NewAdventureService(s *store.Store) *AdventureService {
	return &AdventureService{
		store: s,
	}
}

func (s *AdventureService) GetAdventureUnionInfo(ctx context.Context, roleID uint64) (*AdventureUnionInfo, error) {
	return s.store.GetAdventureUnionInfo(ctx, roleID)
}

func (s *AdventureService) ChangeAdventureUnionName(ctx context.Context, roleID uint64, name string) error {
	return s.store.ChangeAdventureUnionName(ctx, roleID, name)
}

func (s *AdventureService) StartAdventureUnionExpedition(ctx context.Context, roleID uint64, expeditionID, expeditionType uint32) error {
	return s.store.StartAdventureUnionExpedition(ctx, roleID, expeditionID, expeditionType)
}

func (s *AdventureService) CancelAdventureUnionExpedition(ctx context.Context, roleID uint64, expeditionID uint32) error {
	return s.store.CancelAdventureUnionExpedition(ctx, roleID, expeditionID)
}

func (s *AdventureService) ClaimAdventureUnionExpeditionReward(ctx context.Context, roleID uint64, expeditionID uint32) error {
	return s.store.ClaimAdventureUnionExpeditionReward(ctx, roleID, expeditionID)
}

func (s *AdventureService) StartAdventureUnionSubdue(ctx context.Context, roleID uint64, subdueID, subdueType uint32, characterGUID uint64) error {
	return s.store.StartAdventureUnionSubdue(ctx, roleID, subdueID, subdueType, characterGUID)
}

func (s *AdventureService) ClaimAdventureUnionSubdueReward(ctx context.Context, roleID uint64, subdueID uint32) error {
	return s.store.ClaimAdventureUnionSubdueReward(ctx, roleID, subdueID)
}

func (s *AdventureService) OpenAdventureUnionShareboardSlot(ctx context.Context, roleID uint64, slotID uint32) error {
	return s.store.OpenAdventureUnionShareboardSlot(ctx, roleID, slotID)
}

func (s *AdventureService) SetAdventureUnionShareboard(ctx context.Context, roleID uint64, slotID, slotType uint32, show bool) error {
	return s.store.SetAdventureUnionShareboard(ctx, roleID, slotID, slotType, show)
}

func (s *AdventureService) ClaimAdventureReapReward(ctx context.Context, roleID uint64, reapID uint32) error {
	return s.store.ClaimAdventureReapReward(ctx, roleID, reapID)
}

func (s *AdventureService) StartAdventureUnionSearch(ctx context.Context, roleID uint64) error {
	return s.store.StartAdventureUnionSearch(ctx, roleID)
}

func (s *AdventureService) ClaimAdventureUnionCollectionReward(ctx context.Context, roleID uint64, collectionID uint32) error {
	return s.store.ClaimAdventureUnionCollectionReward(ctx, roleID, collectionID)
}

func (s *AdventureService) ClaimAdventureUnionLevelReward(ctx context.Context, roleID uint64, level uint32) error {
	return s.store.ClaimAdventureUnionLevelReward(ctx, roleID, level)
}
