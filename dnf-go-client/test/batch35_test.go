package test

import (
	"testing"

	"google.golang.org/protobuf/proto"

	dnfv1 "github.com/pixb/DnfGameServer/dnf-go-client/gen/dnf/v1"
)

func TestBatch35WorldBossInfo(t *testing.T) {
	req := &dnfv1.WorldBossInfoRequest{
		ModuleId: 11038,
		Cmd:      0,
		Index:    1,
	}

	data, err := proto.Marshal(req)
	if err != nil {
		t.Fatalf("Failed to marshal request: %v", err)
	}

	parsedReq := &dnfv1.WorldBossInfoRequest{}
	if err := proto.Unmarshal(data, parsedReq); err != nil {
		t.Fatalf("Failed to unmarshal request: %v", err)
	}

	if parsedReq.ModuleId != req.ModuleId {
		t.Errorf("ModuleId mismatch: got %d, want %d", parsedReq.ModuleId, req.ModuleId)
	}

	if parsedReq.Index != req.Index {
		t.Errorf("Index mismatch: got %d, want %d", parsedReq.Index, req.Index)
	}

	resp := &dnfv1.WorldBossInfoResponse{
		Error: 0,
		Hp:    1000000000,
		Count: 5,
	}

	data, err = proto.Marshal(resp)
	if err != nil {
		t.Fatalf("Failed to marshal response: %v", err)
	}

	parsedResp := &dnfv1.WorldBossInfoResponse{}
	if err := proto.Unmarshal(data, parsedResp); err != nil {
		t.Fatalf("Failed to unmarshal response: %v", err)
	}

	if parsedResp.Error != resp.Error {
		t.Errorf("Error mismatch: got %d, want %d", parsedResp.Error, resp.Error)
	}

	if parsedResp.Hp != resp.Hp {
		t.Errorf("Hp mismatch: got %d, want %d", parsedResp.Hp, resp.Hp)
	}

	if parsedResp.Count != resp.Count {
		t.Errorf("Count mismatch: got %d, want %d", parsedResp.Count, resp.Count)
	}
}

func TestBatch35WorldBossDamage(t *testing.T) {
	req := &dnfv1.WorldBossDamageRequest{
		ModuleId:      11037,
		Cmd:           0,
		DamageHistory: []byte{1, 2, 3, 4, 5},
		Damage:        50000,
		DungeonGuid:   1234567890123456789,
	}

	data, err := proto.Marshal(req)
	if err != nil {
		t.Fatalf("Failed to marshal request: %v", err)
	}

	parsedReq := &dnfv1.WorldBossDamageRequest{}
	if err := proto.Unmarshal(data, parsedReq); err != nil {
		t.Fatalf("Failed to unmarshal request: %v", err)
	}

	if parsedReq.ModuleId != req.ModuleId {
		t.Errorf("ModuleId mismatch: got %d, want %d", parsedReq.ModuleId, req.ModuleId)
	}

	if parsedReq.Damage != req.Damage {
		t.Errorf("Damage mismatch: got %d, want %d", parsedReq.Damage, req.Damage)
	}

	if parsedReq.DungeonGuid != req.DungeonGuid {
		t.Errorf("DungeonGuid mismatch: got %d, want %d", parsedReq.DungeonGuid, req.DungeonGuid)
	}

	resp := &dnfv1.WorldBossDamageResponse{
		Error: 0,
		Hp:    950000000,
	}

	data, err = proto.Marshal(resp)
	if err != nil {
		t.Fatalf("Failed to marshal response: %v", err)
	}

	parsedResp := &dnfv1.WorldBossDamageResponse{}
	if err := proto.Unmarshal(data, parsedResp); err != nil {
		t.Fatalf("Failed to unmarshal response: %v", err)
	}

	if parsedResp.Error != resp.Error {
		t.Errorf("Error mismatch: got %d, want %d", parsedResp.Error, resp.Error)
	}

	if parsedResp.Hp != resp.Hp {
		t.Errorf("Hp mismatch: got %d, want %d", parsedResp.Hp, resp.Hp)
	}
}

func TestBatch35WorldBossRanking(t *testing.T) {
	req := &dnfv1.WorldBossRankingRequest{
		ModuleId: 11511,
		Cmd:      0,
		Score:    100000,
		Type:     1,
		RankMin:  1,
		RankMax:  10,
	}

	data, err := proto.Marshal(req)
	if err != nil {
		t.Fatalf("Failed to marshal request: %v", err)
	}

	parsedReq := &dnfv1.WorldBossRankingRequest{}
	if err := proto.Unmarshal(data, parsedReq); err != nil {
		t.Fatalf("Failed to unmarshal request: %v", err)
	}

	if parsedReq.ModuleId != req.ModuleId {
		t.Errorf("ModuleId mismatch: got %d, want %d", parsedReq.ModuleId, req.ModuleId)
	}

	if parsedReq.Score != req.Score {
		t.Errorf("Score mismatch: got %d, want %d", parsedReq.Score, req.Score)
	}

	resp := &dnfv1.WorldBossRankingResponse{
		Error: 0,
		Rank:  5,
		Ranking: []*dnfv1.WorldBossInfo{
			{
				Name:  "Player1",
				Score: 500000,
				Count: 10,
			},
			{
				Name:  "Player2",
				Score: 450000,
				Count: 9,
			},
			{
				Name:  "Player3",
				Score: 400000,
				Count: 8,
			},
		},
	}

	data, err = proto.Marshal(resp)
	if err != nil {
		t.Fatalf("Failed to marshal response: %v", err)
	}

	parsedResp := &dnfv1.WorldBossRankingResponse{}
	if err := proto.Unmarshal(data, parsedResp); err != nil {
		t.Fatalf("Failed to unmarshal response: %v", err)
	}

	if parsedResp.Error != resp.Error {
		t.Errorf("Error mismatch: got %d, want %d", parsedResp.Error, resp.Error)
	}

	if parsedResp.Rank != resp.Rank {
		t.Errorf("Rank mismatch: got %d, want %d", parsedResp.Rank, resp.Rank)
	}

	if len(parsedResp.Ranking) != len(resp.Ranking) {
		t.Errorf("Ranking length mismatch: got %d, want %d", len(parsedResp.Ranking), len(resp.Ranking))
	}

	for i, info := range resp.Ranking {
		if parsedResp.Ranking[i].Name != info.Name {
			t.Errorf("Ranking[%d] Name mismatch: got %s, want %s", i, parsedResp.Ranking[i].Name, info.Name)
		}
	}
}

func TestBatch35WorldBossAttackLog(t *testing.T) {
	req := &dnfv1.WorldBossAttackLogRequest{
		ModuleId:                  10164,
		Cmd:                       0,
		DungeonGuid:               1234567890123456789,
		PartyGuid:                 9876543210987654321,
		AtkThresholdValue:         10000,
		BossPattern_5SInjuryAvg:   5000,
		BossPattern_5SInjuryMax:   8000,
		BossPattern_5SInjuryMin:   3000,
		BossPatternInjurySequence: "1,2,3,4,5",
		AtkBuffIdList:             "1001,1002,1003",
		AtkBuffCoefficientList:    "1.5,2.0,2.5",
	}

	data, err := proto.Marshal(req)
	if err != nil {
		t.Fatalf("Failed to marshal request: %v", err)
	}

	parsedReq := &dnfv1.WorldBossAttackLogRequest{}
	if err := proto.Unmarshal(data, parsedReq); err != nil {
		t.Fatalf("Failed to unmarshal request: %v", err)
	}

	if parsedReq.ModuleId != req.ModuleId {
		t.Errorf("ModuleId mismatch: got %d, want %d", parsedReq.ModuleId, req.ModuleId)
	}

	if parsedReq.DungeonGuid != req.DungeonGuid {
		t.Errorf("DungeonGuid mismatch: got %d, want %d", parsedReq.DungeonGuid, req.DungeonGuid)
	}

	if parsedReq.BossPatternInjurySequence != req.BossPatternInjurySequence {
		t.Errorf("BossPatternInjurySequence mismatch: got %s, want %s", parsedReq.BossPatternInjurySequence, req.BossPatternInjurySequence)
	}
}
