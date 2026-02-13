package adventure_service

import (
	"context"
	"fmt"
	"time"

	"github.com/pixb/DnfGameServer/dnf-go-server/internal/db"
	"github.com/pixb/DnfGameServer/dnf-go-server/internal/db/models"
	"github.com/pixb/DnfGameServer/dnf-go-server/internal/utils/logger"
)

type AdventureService struct {
	db *db.DB
}

func NewAdventureService(db *db.DB) *AdventureService {
	return &AdventureService{
		db: db,
	}
}

type AdventureUnionInfo struct {
	Exp                            uint64    `json:"exp"`
	Level                          uint32    `json:"level"`
	Day                            uint32    `json:"day"`
	TypicalCharacterGUID           uint64    `json:"typicalCharacterGuid"`
	Name                           string    `json:"name"`
	UpdateTime                     time.Time `json:"updateTime"`
	LastChangeNameTime             time.Time `json:"lastChangeNameTime"`
	ShareboardBackground           uint32    `json:"shareboardBackground"`
	ShareboardFrame                uint32    `json:"shareboardFrame"`
	ShareboardShowAntiEvilScore    bool      `json:"shareboardShowAntiEvilScore"`
	AutoSearchCount                uint32    `json:"autoSearchCount"`
	ShareboardTotalAntiEvilScore   uint32    `json:"shareboardTotalAntiEvilScore"`
	ShareboardAntiEvilScoreRefresh bool      `json:"shareboardAntiEvilScoreRefresh"`
	IsAdventureCondition           bool      `json:"isAdventureCondition"`
}

func (s *AdventureService) GetAdventureUnionInfo(ctx context.Context, roleID uint64) (*AdventureUnionInfo, error) {
	logger.Info("get adventure union info",
		logger.Uint64("role_id", roleID),
	)

	var union models.AdventureUnion
	if err := s.db.DB.Where("role_id = ?", roleID).First(&union).Error; err != nil {
		logger.Error("adventure union not found, creating default",
			logger.ErrorField(err),
			logger.Uint64("role_id", roleID),
		)

		newUnion := &models.AdventureUnion{
			RoleID:     roleID,
			Name:       fmt.Sprintf("冒险联盟%d", roleID),
			Exp:        0,
			Level:      1,
			Day:        1,
			CreateTime: time.Now(),
			UpdateTime: time.Now(),
		}

		if err := s.db.DB.Create(newUnion).Error; err != nil {
			logger.Error("failed to create adventure union",
				logger.ErrorField(err),
			)
			return nil, err
		}

		union = *newUnion
	}

	info := &AdventureUnionInfo{
		Exp:                            union.Exp,
		Level:                          union.Level,
		Day:                            union.Day,
		TypicalCharacterGUID:           union.TypicalCharacterGUID,
		Name:                           union.Name,
		UpdateTime:                     union.UpdateTime,
		LastChangeNameTime:             union.LastChangeNameTime,
		ShareboardBackground:           union.ShareboardBackground,
		ShareboardFrame:                union.ShareboardFrame,
		ShareboardShowAntiEvilScore:    union.ShareboardShowAntiEvilScore,
		AutoSearchCount:                union.AutoSearchCount,
		ShareboardTotalAntiEvilScore:   union.ShareboardTotalAntiEvilScore,
		ShareboardAntiEvilScoreRefresh: union.ShareboardAntiEvilScoreRefresh,
		IsAdventureCondition:           union.IsAdventureCondition,
	}

	return info, nil
}

func (s *AdventureService) ChangeAdventureUnionName(ctx context.Context, roleID uint64, name string) error {
	logger.Info("change adventure union name",
		logger.Uint64("role_id", roleID),
		logger.String("name", name),
	)

	if name == "" {
		return fmt.Errorf("name cannot be empty")
	}

	var union models.AdventureUnion
	if err := s.db.DB.Where("role_id = ?", roleID).First(&union).Error; err != nil {
		logger.Error("adventure union not found",
			logger.ErrorField(err),
			logger.Uint64("role_id", roleID),
		)
		return fmt.Errorf("adventure union not found")
	}

	now := time.Now()
	union.Name = name
	union.LastChangeNameTime = now
	union.UpdateTime = now

	if err := s.db.DB.Save(&union).Error; err != nil {
		logger.Error("failed to update adventure union name",
			logger.ErrorField(err),
		)
		return err
	}

	return nil
}

func (s *AdventureService) StartAdventureUnionExpedition(ctx context.Context, roleID uint64, expeditionID, expeditionType uint32) error {
	logger.Info("start adventure union expedition",
		logger.Uint64("role_id", roleID),
		logger.Uint32("expedition_id", expeditionID),
		logger.Uint32("expedition_type", expeditionType),
	)

	var existingExpedition models.AdventureUnionExpedition
	err := s.db.DB.Where("role_id = ? AND status = ?", roleID, 0).First(&existingExpedition).Error
	if err == nil {
		return fmt.Errorf("expedition already in progress")
	}

	expedition := &models.AdventureUnionExpedition{
		RoleID:         roleID,
		ExpeditionID:   expeditionID,
		ExpeditionType: expeditionType,
		Status:         0,
		StartTime:      time.Now(),
		CreateTime:     time.Now(),
		UpdateTime:     time.Now(),
	}

	if err := s.db.DB.Create(expedition).Error; err != nil {
		logger.Error("failed to create adventure union expedition",
			logger.ErrorField(err),
		)
		return err
	}

	return nil
}

func (s *AdventureService) CancelAdventureUnionExpedition(ctx context.Context, roleID uint64, expeditionID uint32) error {
	logger.Info("cancel adventure union expedition",
		logger.Uint64("role_id", roleID),
		logger.Uint32("expedition_id", expeditionID),
	)

	var expedition models.AdventureUnionExpedition
	if err := s.db.DB.Where("role_id = ? AND expedition_id = ? AND status = ?", roleID, expeditionID, 0).First(&expedition).Error; err != nil {
		logger.Error("expedition not found or not in progress",
			logger.ErrorField(err),
			logger.Uint64("role_id", roleID),
			logger.Uint32("expedition_id", expeditionID),
		)
		return fmt.Errorf("expedition not found or not in progress")
	}

	expedition.Status = 2
	expedition.EndTime = time.Now()
	expedition.UpdateTime = time.Now()

	if err := s.db.DB.Save(&expedition).Error; err != nil {
		logger.Error("failed to cancel adventure union expedition",
			logger.ErrorField(err),
		)
		return err
	}

	return nil
}

func (s *AdventureService) ClaimAdventureUnionExpeditionReward(ctx context.Context, roleID uint64, expeditionID uint32) error {
	logger.Info("claim adventure union expedition reward",
		logger.Uint64("role_id", roleID),
		logger.Uint32("expedition_id", expeditionID),
	)

	var expedition models.AdventureUnionExpedition
	if err := s.db.DB.Where("role_id = ? AND expedition_id = ?", roleID, expeditionID).First(&expedition).Error; err != nil {
		logger.Error("expedition not found",
			logger.ErrorField(err),
			logger.Uint64("role_id", roleID),
			logger.Uint32("expedition_id", expeditionID),
		)
		return fmt.Errorf("expedition not found")
	}

	if expedition.Status != 1 {
		return fmt.Errorf("expedition not completed")
	}

	if expedition.RewardClaimed {
		return fmt.Errorf("reward already claimed")
	}

	expedition.RewardClaimed = true
	expedition.UpdateTime = time.Now()

	if err := s.db.DB.Save(&expedition).Error; err != nil {
		logger.Error("failed to claim adventure union expedition reward",
			logger.ErrorField(err),
		)
		return err
	}

	return nil
}

func (s *AdventureService) StartAdventureUnionSubdue(ctx context.Context, roleID uint64, subdueID, subdueType uint32, characterGUID uint64) error {
	logger.Info("start adventure union subdue",
		logger.Uint64("role_id", roleID),
		logger.Uint32("subdue_id", subdueID),
		logger.Uint32("subdue_type", subdueType),
		logger.Uint64("character_guid", characterGUID),
	)

	var existingSubdue models.AdventureUnionSubdue
	err := s.db.DB.Where("role_id = ? AND status = ?", roleID, 0).First(&existingSubdue).Error
	if err == nil {
		return fmt.Errorf("subdue already in progress")
	}

	subdue := &models.AdventureUnionSubdue{
		RoleID:        roleID,
		SubdueID:      subdueID,
		SubdueType:    subdueType,
		CharacterGUID: characterGUID,
		Status:        0,
		StartTime:     time.Now(),
		CreateTime:    time.Now(),
		UpdateTime:    time.Now(),
	}

	if err := s.db.DB.Create(subdue).Error; err != nil {
		logger.Error("failed to create adventure union subdue",
			logger.ErrorField(err),
		)
		return err
	}

	return nil
}

func (s *AdventureService) ClaimAdventureUnionSubdueReward(ctx context.Context, roleID uint64, subdueID uint32) error {
	logger.Info("claim adventure union subdue reward",
		logger.Uint64("role_id", roleID),
		logger.Uint32("subdue_id", subdueID),
	)

	var subdue models.AdventureUnionSubdue
	if err := s.db.DB.Where("role_id = ? AND subdue_id = ?", roleID, subdueID).First(&subdue).Error; err != nil {
		logger.Error("subdue not found",
			logger.ErrorField(err),
			logger.Uint64("role_id", roleID),
			logger.Uint32("subdue_id", subdueID),
		)
		return fmt.Errorf("subdue not found")
	}

	if subdue.Status != 1 {
		return fmt.Errorf("subdue not completed")
	}

	if subdue.RewardClaimed {
		return fmt.Errorf("reward already claimed")
	}

	subdue.RewardClaimed = true
	subdue.UpdateTime = time.Now()

	if err := s.db.DB.Save(&subdue).Error; err != nil {
		logger.Error("failed to claim adventure union subdue reward",
			logger.ErrorField(err),
		)
		return err
	}

	return nil
}

func (s *AdventureService) OpenAdventureUnionShareboardSlot(ctx context.Context, roleID uint64, slotID uint32) error {
	logger.Info("open adventure union shareboard slot",
		logger.Uint64("role_id", roleID),
		logger.Uint32("slot_id", slotID),
	)

	var existingSlot models.AdventureUnionShareboardSlot
	err := s.db.DB.Where("role_id = ? AND slot_id = ?", roleID, slotID).First(&existingSlot).Error
	if err == nil {
		return fmt.Errorf("slot already opened")
	}

	slot := &models.AdventureUnionShareboardSlot{
		RoleID:     roleID,
		SlotID:     slotID,
		SlotType:   1,
		CreateTime: time.Now(),
		UpdateTime: time.Now(),
	}

	if err := s.db.DB.Create(slot).Error; err != nil {
		logger.Error("failed to open adventure union shareboard slot",
			logger.ErrorField(err),
		)
		return err
	}

	return nil
}

func (s *AdventureService) SetAdventureUnionShareboard(ctx context.Context, roleID uint64, slotID, slotType uint32, show bool) error {
	logger.Info("set adventure union shareboard",
		logger.Uint64("role_id", roleID),
		logger.Uint32("slot_id", slotID),
		logger.Uint32("slot_type", slotType),
		logger.Bool("show", show),
	)

	var slot models.AdventureUnionShareboardSlot
	if err := s.db.DB.Where("role_id = ? AND slot_id = ?", roleID, slotID).First(&slot).Error; err != nil {
		logger.Error("shareboard slot not found",
			logger.ErrorField(err),
			logger.Uint64("role_id", roleID),
			logger.Uint32("slot_id", slotID),
		)
		return fmt.Errorf("shareboard slot not found")
	}

	slot.SlotType = slotType
	slot.Show = show
	slot.UpdateTime = time.Now()

	if err := s.db.DB.Save(&slot).Error; err != nil {
		logger.Error("failed to set adventure union shareboard",
			logger.ErrorField(err),
		)
		return err
	}

	return nil
}

func (s *AdventureService) ClaimAdventureReapReward(ctx context.Context, roleID uint64, reapID uint32) error {
	logger.Info("claim adventure reap reward",
		logger.Uint64("role_id", roleID),
		logger.Uint32("reap_id", reapID),
	)

	var reap models.AdventureReap
	if err := s.db.DB.Where("role_id = ? AND reap_id = ?", roleID, reapID).First(&reap).Error; err != nil {
		logger.Error("adventure reap not found",
			logger.ErrorField(err),
			logger.Uint64("role_id", roleID),
			logger.Uint32("reap_id", reapID),
		)
		return fmt.Errorf("adventure reap not found")
	}

	if reap.Claimed {
		return fmt.Errorf("reward already claimed")
	}

	reap.Claimed = true
	reap.ClaimTime = time.Now()

	if err := s.db.DB.Save(&reap).Error; err != nil {
		logger.Error("failed to claim adventure reap reward",
			logger.ErrorField(err),
		)
		return err
	}

	return nil
}

func (s *AdventureService) StartAdventureUnionSearch(ctx context.Context, roleID uint64) error {
	logger.Info("start adventure union search",
		logger.Uint64("role_id", roleID),
	)

	var union models.AdventureUnion
	if err := s.db.DB.Where("role_id = ?", roleID).First(&union).Error; err != nil {
		logger.Error("adventure union not found",
			logger.ErrorField(err),
			logger.Uint64("role_id", roleID),
		)
		return fmt.Errorf("adventure union not found")
	}

	union.AutoSearchCount++
	union.UpdateTime = time.Now()

	if err := s.db.DB.Save(&union).Error; err != nil {
		logger.Error("failed to start adventure union search",
			logger.ErrorField(err),
		)
		return err
	}

	return nil
}

func (s *AdventureService) ClaimAdventureUnionCollectionReward(ctx context.Context, roleID uint64, collectionID uint32) error {
	logger.Info("claim adventure union collection reward",
		logger.Uint64("role_id", roleID),
		logger.Uint32("collection_id", collectionID),
	)

	var collection models.AdventureUnionCollection
	if err := s.db.DB.Where("role_id = ? AND collection_id = ?", roleID, collectionID).First(&collection).Error; err != nil {
		logger.Error("collection not found",
			logger.ErrorField(err),
			logger.Uint64("role_id", roleID),
			logger.Uint32("collection_id", collectionID),
		)
		return fmt.Errorf("collection not found")
	}

	if !collection.Completed {
		return fmt.Errorf("collection not completed")
	}

	if collection.RewardClaimed {
		return fmt.Errorf("reward already claimed")
	}

	collection.RewardClaimed = true
	collection.UpdateTime = time.Now()

	if err := s.db.DB.Save(&collection).Error; err != nil {
		logger.Error("failed to claim adventure union collection reward",
			logger.ErrorField(err),
		)
		return err
	}

	return nil
}

func (s *AdventureService) ClaimAdventureUnionLevelReward(ctx context.Context, roleID uint64, level uint32) error {
	logger.Info("claim adventure union level reward",
		logger.Uint64("role_id", roleID),
		logger.Uint32("level", level),
	)

	var union models.AdventureUnion
	if err := s.db.DB.Where("role_id = ?", roleID).First(&union).Error; err != nil {
		logger.Error("adventure union not found",
			logger.ErrorField(err),
			logger.Uint64("role_id", roleID),
		)
		return fmt.Errorf("adventure union not found")
	}

	if union.Level < level {
		return fmt.Errorf("level not reached")
	}

	var reward models.AdventureUnionLevelReward
	err := s.db.DB.Where("role_id = ? AND level = ?", roleID, level).First(&reward).Error
	if err == nil && reward.Claimed {
		return fmt.Errorf("reward already claimed")
	}

	tx := s.db.DB.Begin()

	now := time.Now()
	if reward.ID == 0 {
		newReward := &models.AdventureUnionLevelReward{
			RoleID:      roleID,
			Level:       level,
			RewardType:  1,
			RewardIndex: 1,
			RewardCount: 100,
			Claimed:     true,
			ClaimTime:   now,
			CreateTime:  now,
		}
		if err := tx.Create(newReward).Error; err != nil {
			tx.Rollback()
			logger.Error("failed to create level reward record",
				logger.ErrorField(err),
			)
			return err
		}
	} else {
		reward.Claimed = true
		reward.ClaimTime = now
		if err := tx.Save(&reward).Error; err != nil {
			tx.Rollback()
			logger.Error("failed to update level reward record",
				logger.ErrorField(err),
			)
			return err
		}
	}

	if err := tx.Commit().Error; err != nil {
		logger.Error("failed to commit transaction",
			logger.ErrorField(err),
		)
		return err
	}

	return nil
}
