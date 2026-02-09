package test

import (
	"testing"

	"google.golang.org/protobuf/proto"

	dnfv1 "github.com/pixb/DnfGameServer/dnf-go-client/gen/dnf/v1"
)

func TestBatch08EnterTown(t *testing.T) {
	req := &dnfv1.EnterTownRequest{
		Authkey: "test_authkey",
		Town:    1001,
		Area:    1,
		Posx:    100,
		Posy:    200,
	}

	data, err := proto.Marshal(req)
	if err != nil {
		t.Fatalf("Failed to marshal EnterTownRequest: %v", err)
	}

	unmarshaled := &dnfv1.EnterTownRequest{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal EnterTownRequest: %v", err)
	}

	if unmarshaled.Authkey != req.Authkey {
		t.Errorf("Authkey mismatch: got %v, want %v", unmarshaled.Authkey, req.Authkey)
	}
	if unmarshaled.Town != req.Town {
		t.Errorf("Town mismatch: got %v, want %v", unmarshaled.Town, req.Town)
	}
	if unmarshaled.Area != req.Area {
		t.Errorf("Area mismatch: got %v, want %v", unmarshaled.Area, req.Area)
	}
	if unmarshaled.Posx != req.Posx {
		t.Errorf("Posx mismatch: got %v, want %v", unmarshaled.Posx, req.Posx)
	}
	if unmarshaled.Posy != req.Posy {
		t.Errorf("Posy mismatch: got %v, want %v", unmarshaled.Posy, req.Posy)
	}

	resp := &dnfv1.EnterTownResponse{
		Error:        0,
		Town:         1001,
		Area:         1,
		Posx:         100,
		Posy:         200,
		Partyguid:    1234567890,
		Servertime:   1640000000,
		Expratio:     100,
		Fatigueratio: 100,
		Version:      "1.0.0",
	}

	data, err = proto.Marshal(resp)
	if err != nil {
		t.Fatalf("Failed to marshal EnterTownResponse: %v", err)
	}

	unmarshaledResp := &dnfv1.EnterTownResponse{}
	if err := proto.Unmarshal(data, unmarshaledResp); err != nil {
		t.Fatalf("Failed to unmarshal EnterTownResponse: %v", err)
	}

	if unmarshaledResp.Error != resp.Error {
		t.Errorf("Error mismatch: got %v, want %v", unmarshaledResp.Error, resp.Error)
	}
	if unmarshaledResp.Town != resp.Town {
		t.Errorf("Town mismatch: got %v, want %v", unmarshaledResp.Town, resp.Town)
	}
	if unmarshaledResp.Partyguid != resp.Partyguid {
		t.Errorf("Partyguid mismatch: got %v, want %v", unmarshaledResp.Partyguid, resp.Partyguid)
	}
}

func TestBatch08LeaveFromTown(t *testing.T) {
	req := &dnfv1.LeaveFromTownRequest{}

	data, err := proto.Marshal(req)
	if err != nil {
		t.Fatalf("Failed to marshal LeaveFromTownRequest: %v", err)
	}

	unmarshaled := &dnfv1.LeaveFromTownRequest{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal LeaveFromTownRequest: %v", err)
	}

	resp := &dnfv1.LeaveFromTownResponse{
		Error: 0,
	}

	data, err = proto.Marshal(resp)
	if err != nil {
		t.Fatalf("Failed to marshal LeaveFromTownResponse: %v", err)
	}

	unmarshaledResp := &dnfv1.LeaveFromTownResponse{}
	if err := proto.Unmarshal(data, unmarshaledResp); err != nil {
		t.Fatalf("Failed to unmarshal LeaveFromTownResponse: %v", err)
	}

	if unmarshaledResp.Error != resp.Error {
		t.Errorf("Error mismatch: got %v, want %v", unmarshaledResp.Error, resp.Error)
	}
}

func TestBatch08CharacterInfo(t *testing.T) {
	req := &dnfv1.CharacterInfoRequest{
		Authkey: "test_authkey",
		Option:  1,
		Charlist: []*dnfv1.CharacterGuid{
			{
				Charguid: 1234567890,
				Type:     1,
				Posx:     100,
				Posy:     200,
			},
			{
				Charguid: 2345678901,
				Type:     2,
				Posx:     150,
				Posy:     250,
			},
		},
	}

	data, err := proto.Marshal(req)
	if err != nil {
		t.Fatalf("Failed to marshal CharacterInfoRequest: %v", err)
	}

	unmarshaled := &dnfv1.CharacterInfoRequest{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal CharacterInfoRequest: %v", err)
	}

	if unmarshaled.Authkey != req.Authkey {
		t.Errorf("Authkey mismatch: got %v, want %v", unmarshaled.Authkey, req.Authkey)
	}
	if unmarshaled.Option != req.Option {
		t.Errorf("Option mismatch: got %v, want %v", unmarshaled.Option, req.Option)
	}
	if len(unmarshaled.Charlist) != len(req.Charlist) {
		t.Errorf("Charlist length mismatch: got %v, want %v", len(unmarshaled.Charlist), len(req.Charlist))
	}

	resp := &dnfv1.CharacterInfoResponse{
		Error: 0,
		Charlist: []*dnfv1.CharacterInfo{
			{
				Charguid:            1234567890,
				Job:                 1,
				Growtype:            1,
				Secondgrowtype:      0,
				Level:               50,
				Name:                "TestChar1",
				Gguid:               9876543210,
				Posx:                100,
				Posy:                200,
				Gname:               "TestGuild",
				Vip:                 1,
				Vcreature:           1,
				Partydisturb:        0,
				Spoint:              1000,
				Adventureunionlevel: 10,
				Adventureunionexp:   10000,
				Partyguid:           1234567890,
				Partyleaderguid:     1234567890,
				Type:                1,
				Pvpstargrade:        1,
				Pvpstarcount:        5,
				Blackdiamond:        true,
				AvatarVisibleFlags:  0xFFFFFFFF,
				Fairpvpstargrade:    1,
				Fairpvpstarcount:    5,
			},
		},
	}

	data, err = proto.Marshal(resp)
	if err != nil {
		t.Fatalf("Failed to marshal CharacterInfoResponse: %v", err)
	}

	unmarshaledResp := &dnfv1.CharacterInfoResponse{}
	if err := proto.Unmarshal(data, unmarshaledResp); err != nil {
		t.Fatalf("Failed to unmarshal CharacterInfoResponse: %v", err)
	}

	if unmarshaledResp.Error != resp.Error {
		t.Errorf("Error mismatch: got %v, want %v", unmarshaledResp.Error, resp.Error)
	}
	if len(unmarshaledResp.Charlist) != len(resp.Charlist) {
		t.Errorf("Charlist length mismatch: got %v, want %v", len(unmarshaledResp.Charlist), len(resp.Charlist))
	}
	if unmarshaledResp.Charlist[0].Name != resp.Charlist[0].Name {
		t.Errorf("Name mismatch: got %v, want %v", unmarshaledResp.Charlist[0].Name, resp.Charlist[0].Name)
	}
}

func TestBatch08TownUserGuidList(t *testing.T) {
	req := &dnfv1.TownUserGuidListRequest{
		Count: 10,
		Posx:  100,
		Posy:  200,
	}

	data, err := proto.Marshal(req)
	if err != nil {
		t.Fatalf("Failed to marshal TownUserGuidListRequest: %v", err)
	}

	unmarshaled := &dnfv1.TownUserGuidListRequest{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal TownUserGuidListRequest: %v", err)
	}

	if unmarshaled.Count != req.Count {
		t.Errorf("Count mismatch: got %v, want %v", unmarshaled.Count, req.Count)
	}
	if unmarshaled.Posx != req.Posx {
		t.Errorf("Posx mismatch: got %v, want %v", unmarshaled.Posx, req.Posx)
	}
	if unmarshaled.Posy != req.Posy {
		t.Errorf("Posy mismatch: got %v, want %v", unmarshaled.Posy, req.Posy)
	}

	resp := &dnfv1.TownUserGuidListResponse{
		Error: 0,
		Charlist: []*dnfv1.CharacterGuid{
			{
				Charguid: 1234567890,
				Type:     1,
				Posx:     100,
				Posy:     200,
			},
			{
				Charguid: 2345678901,
				Type:     2,
				Posx:     150,
				Posy:     250,
			},
		},
	}

	data, err = proto.Marshal(resp)
	if err != nil {
		t.Fatalf("Failed to marshal TownUserGuidListResponse: %v", err)
	}

	unmarshaledResp := &dnfv1.TownUserGuidListResponse{}
	if err := proto.Unmarshal(data, unmarshaledResp); err != nil {
		t.Fatalf("Failed to unmarshal TownUserGuidListResponse: %v", err)
	}

	if unmarshaledResp.Error != resp.Error {
		t.Errorf("Error mismatch: got %v, want %v", unmarshaledResp.Error, resp.Error)
	}
	if len(unmarshaledResp.Charlist) != len(resp.Charlist) {
		t.Errorf("Charlist length mismatch: got %v, want %v", len(unmarshaledResp.Charlist), len(resp.Charlist))
	}
}

func TestBatch08TargetUserDetailInfo(t *testing.T) {
	req := &dnfv1.TargetUserDetailInfoRequest{
		Targetguid: 1234567890,
	}

	data, err := proto.Marshal(req)
	if err != nil {
		t.Fatalf("Failed to marshal TargetUserDetailInfoRequest: %v", err)
	}

	unmarshaled := &dnfv1.TargetUserDetailInfoRequest{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal TargetUserDetailInfoRequest: %v", err)
	}

	if unmarshaled.Targetguid != req.Targetguid {
		t.Errorf("Targetguid mismatch: got %v, want %v", unmarshaled.Targetguid, req.Targetguid)
	}

	resp := &dnfv1.TargetUserDetailInfoResponse{
		Error:                0,
		Guid:                 1234567890,
		World:                1,
		Gguid:                9876543210,
		Gmastername:          "GuildMaster",
		Name:                 "TestChar",
		Exp:                  1000000,
		Level:                50,
		Job:                  1,
		Growtype:             1,
		Secgrowtype:          0,
		Equipscore:           10000,
		Spoint:               1000,
		Adventureunionlevel:  10,
		Adventureunionexp:    10000,
		Characlistcount:      3,
		Gname:                "TestGuild",
		Gmembergrade:         1,
		Blackdiamond:         1234567890,
		Creditscore:          100,
		Gamecenterinfo:       0,
		QqVipinfo:            0,
		AvatarVisibleFlags:   0xFFFFFFFF,
		Totallike:            100,
		Like:                 10,
		Rank:                 1,
		Communionlevel:       10,
		Fame:                 100,
		Charm:                100,
		Grade:                1,
		GradeFair:            1,
		IsadventureCondition: true,
	}

	data, err = proto.Marshal(resp)
	if err != nil {
		t.Fatalf("Failed to marshal TargetUserDetailInfoResponse: %v", err)
	}

	unmarshaledResp := &dnfv1.TargetUserDetailInfoResponse{}
	if err := proto.Unmarshal(data, unmarshaledResp); err != nil {
		t.Fatalf("Failed to unmarshal TargetUserDetailInfoResponse: %v", err)
	}

	if unmarshaledResp.Error != resp.Error {
		t.Errorf("Error mismatch: got %v, want %v", unmarshaledResp.Error, resp.Error)
	}
	if unmarshaledResp.Name != resp.Name {
		t.Errorf("Name mismatch: got %v, want %v", unmarshaledResp.Name, resp.Name)
	}
	if unmarshaledResp.Level != resp.Level {
		t.Errorf("Level mismatch: got %v, want %v", unmarshaledResp.Level, resp.Level)
	}
}

func TestBatch08InteractionMenu(t *testing.T) {
	req := &dnfv1.InteractionMenuRequest{
		Charguid:     1234567890,
		Openmenutype: 1,
	}

	data, err := proto.Marshal(req)
	if err != nil {
		t.Fatalf("Failed to marshal InteractionMenuRequest: %v", err)
	}

	unmarshaled := &dnfv1.InteractionMenuRequest{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal InteractionMenuRequest: %v", err)
	}

	if unmarshaled.Charguid != req.Charguid {
		t.Errorf("Charguid mismatch: got %v, want %v", unmarshaled.Charguid, req.Charguid)
	}
	if unmarshaled.Openmenutype != req.Openmenutype {
		t.Errorf("Openmenutype mismatch: got %v, want %v", unmarshaled.Openmenutype, req.Openmenutype)
	}

	resp := &dnfv1.InteractionMenuResponse{
		Error:              0,
		Charguid:           1234567890,
		Online:             1,
		Status:             1,
		Lastlogout:         1640000000,
		Gguid:              9876543210,
		Openmenutype:       1,
		Level:              50,
		Job:                1,
		Growtype:           1,
		Secgrowtype:        0,
		Name:               "TestChar",
		Gname:              "TestGuild",
		Grade:              1,
		Qindex:             1,
		Partyguid:          1234567890,
		Partyleader:        1234567890,
		Publictype:         1,
		Creditscore:        100,
		World:              1,
		Openid:             "test_openid",
		Platform:           1,
		Platformserverid:   1001,
		Adventureunionname: "TestUnion",
		Gamecenterinfo:     0,
		QqVipinfo:          0,
		Characterframe:     1,
	}

	data, err = proto.Marshal(resp)
	if err != nil {
		t.Fatalf("Failed to marshal InteractionMenuResponse: %v", err)
	}

	unmarshaledResp := &dnfv1.InteractionMenuResponse{}
	if err := proto.Unmarshal(data, unmarshaledResp); err != nil {
		t.Fatalf("Failed to unmarshal InteractionMenuResponse: %v", err)
	}

	if unmarshaledResp.Error != resp.Error {
		t.Errorf("Error mismatch: got %v, want %v", unmarshaledResp.Error, resp.Error)
	}
	if unmarshaledResp.Name != resp.Name {
		t.Errorf("Name mismatch: got %v, want %v", unmarshaledResp.Name, resp.Name)
	}
	if unmarshaledResp.Level != resp.Level {
		t.Errorf("Level mismatch: got %v, want %v", unmarshaledResp.Level, resp.Level)
	}
}

func TestBatch08CharacterGuid(t *testing.T) {
	charGuid := &dnfv1.CharacterGuid{
		Charguid: 1234567890,
		Type:     1,
		Posx:     100,
		Posy:     200,
	}

	data, err := proto.Marshal(charGuid)
	if err != nil {
		t.Fatalf("Failed to marshal CharacterGuid: %v", err)
	}

	unmarshaled := &dnfv1.CharacterGuid{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal CharacterGuid: %v", err)
	}

	if unmarshaled.Charguid != charGuid.Charguid {
		t.Errorf("Charguid mismatch: got %v, want %v", unmarshaled.Charguid, charGuid.Charguid)
	}
	if unmarshaled.Type != charGuid.Type {
		t.Errorf("Type mismatch: got %v, want %v", unmarshaled.Type, charGuid.Type)
	}
	if unmarshaled.Posx != charGuid.Posx {
		t.Errorf("Posx mismatch: got %v, want %v", unmarshaled.Posx, charGuid.Posx)
	}
	if unmarshaled.Posy != charGuid.Posy {
		t.Errorf("Posy mismatch: got %v, want %v", unmarshaled.Posy, charGuid.Posy)
	}
}

func TestBatch08CharacterInfoMessage(t *testing.T) {
	charInfo := &dnfv1.CharacterInfo{
		Charguid:            1234567890,
		Job:                 1,
		Growtype:            1,
		Secondgrowtype:      0,
		Level:               50,
		Name:                "TestChar",
		Gguid:               9876543210,
		Posx:                100,
		Posy:                200,
		Gname:               "TestGuild",
		Vip:                 1,
		Vcreature:           1,
		Partydisturb:        0,
		Spoint:              1000,
		Adventureunionlevel: 10,
		Adventureunionexp:   10000,
		Partyguid:           1234567890,
		Partyleaderguid:     1234567890,
		Type:                1,
		Pvpstargrade:        1,
		Pvpstarcount:        5,
		Blackdiamond:        true,
		AvatarVisibleFlags:  0xFFFFFFFF,
		Fairpvpstargrade:    1,
		Fairpvpstarcount:    5,
	}

	data, err := proto.Marshal(charInfo)
	if err != nil {
		t.Fatalf("Failed to marshal CharacterInfo: %v", err)
	}

	unmarshaled := &dnfv1.CharacterInfo{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal CharacterInfo: %v", err)
	}

	if unmarshaled.Charguid != charInfo.Charguid {
		t.Errorf("Charguid mismatch: got %v, want %v", unmarshaled.Charguid, charInfo.Charguid)
	}
	if unmarshaled.Name != charInfo.Name {
		t.Errorf("Name mismatch: got %v, want %v", unmarshaled.Name, charInfo.Name)
	}
	if unmarshaled.Level != charInfo.Level {
		t.Errorf("Level mismatch: got %v, want %v", unmarshaled.Level, charInfo.Level)
	}
}
