package main

import (
	"fmt"
	"log"

	dnfv1 "dnf-go-client/gen/go/dnf/v1"

	"google.golang.org/protobuf/proto"
)

func main() {
	fmt.Println("===== 批次08编解码测试 =====")

	testEnterTown()
	testLeaveFromTown()
	testCharacterInfo()
	testTownUserGuidList()
	testTargetUserDetailInfo()
	testInteractionMenu()

	fmt.Println("===== 批次08编解码测试完成 =====")
}

func testEnterTown() {
	fmt.Println("\n--- 测试进入城镇消息 ---")

	req := &dnfv1.EnterTownRequest{
		Authkey: "test_authkey",
		Town:    1001,
		Area:    1,
		Posx:    100,
		Posy:    200,
	}

	data, err := proto.Marshal(req)
	if err != nil {
		log.Fatalf("Failed to marshal EnterTownRequest: %v", err)
	}
	fmt.Printf("EnterTownRequest 序列化成功，长度: %d 字节\n", len(data))

	unmarshaled := &dnfv1.EnterTownRequest{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		log.Fatalf("Failed to unmarshal EnterTownRequest: %v", err)
	}
	fmt.Printf("EnterTownRequest 反序列化成功: authkey=%s, town=%d, area=%d, posx=%d, posy=%d\n",
		unmarshaled.Authkey, unmarshaled.Town, unmarshaled.Area, unmarshaled.Posx, unmarshaled.Posy)

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
		log.Fatalf("Failed to marshal EnterTownResponse: %v", err)
	}
	fmt.Printf("EnterTownResponse 序列化成功，长度: %d 字节\n", len(data))

	unmarshaledResp := &dnfv1.EnterTownResponse{}
	if err := proto.Unmarshal(data, unmarshaledResp); err != nil {
		log.Fatalf("Failed to unmarshal EnterTownResponse: %v", err)
	}
	fmt.Printf("EnterTownResponse 反序列化成功: error=%d, town=%d, partyguid=%d\n",
		unmarshaledResp.Error, unmarshaledResp.Town, unmarshaledResp.Partyguid)
}

func testLeaveFromTown() {
	fmt.Println("\n--- 测试离开城镇消息 ---")

	req := &dnfv1.LeaveFromTownRequest{}

	data, err := proto.Marshal(req)
	if err != nil {
		log.Fatalf("Failed to marshal LeaveFromTownRequest: %v", err)
	}
	fmt.Printf("LeaveFromTownRequest 序列化成功，长度: %d 字节\n", len(data))

	unmarshaled := &dnfv1.LeaveFromTownRequest{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		log.Fatalf("Failed to unmarshal LeaveFromTownRequest: %v", err)
	}
	fmt.Println("LeaveFromTownRequest 反序列化成功")

	resp := &dnfv1.LeaveFromTownResponse{
		Error: 0,
	}

	data, err = proto.Marshal(resp)
	if err != nil {
		log.Fatalf("Failed to marshal LeaveFromTownResponse: %v", err)
	}
	fmt.Printf("LeaveFromTownResponse 序列化成功，长度: %d 字节\n", len(data))

	unmarshaledResp := &dnfv1.LeaveFromTownResponse{}
	if err := proto.Unmarshal(data, unmarshaledResp); err != nil {
		log.Fatalf("Failed to unmarshal LeaveFromTownResponse: %v", err)
	}
	fmt.Printf("LeaveFromTownResponse 反序列化成功: error=%d\n", unmarshaledResp.Error)
}

func testCharacterInfo() {
	fmt.Println("\n--- 测试角色信息消息 ---")

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
		log.Fatalf("Failed to marshal CharacterInfoRequest: %v", err)
	}
	fmt.Printf("CharacterInfoRequest 序列化成功，长度: %d 字节\n", len(data))

	unmarshaled := &dnfv1.CharacterInfoRequest{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		log.Fatalf("Failed to unmarshal CharacterInfoRequest: %v", err)
	}
	fmt.Printf("CharacterInfoRequest 反序列化成功: authkey=%s, option=%d, charlist数量=%d\n",
		unmarshaled.Authkey, unmarshaled.Option, len(unmarshaled.Charlist))

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
		log.Fatalf("Failed to marshal CharacterInfoResponse: %v", err)
	}
	fmt.Printf("CharacterInfoResponse 序列化成功，长度: %d 字节\n", len(data))

	unmarshaledResp := &dnfv1.CharacterInfoResponse{}
	if err := proto.Unmarshal(data, unmarshaledResp); err != nil {
		log.Fatalf("Failed to unmarshal CharacterInfoResponse: %v", err)
	}
	fmt.Printf("CharacterInfoResponse 反序列化成功: error=%d, charlist数量=%d, 第一个角色名称=%s\n",
		unmarshaledResp.Error, len(unmarshaledResp.Charlist), unmarshaledResp.Charlist[0].Name)
}

func testTownUserGuidList() {
	fmt.Println("\n--- 测试城镇用户GUID列表消息 ---")

	req := &dnfv1.TownUserGuidListRequest{
		Count: 10,
		Posx:  100,
		Posy:  200,
	}

	data, err := proto.Marshal(req)
	if err != nil {
		log.Fatalf("Failed to marshal TownUserGuidListRequest: %v", err)
	}
	fmt.Printf("TownUserGuidListRequest 序列化成功，长度: %d 字节\n", len(data))

	unmarshaled := &dnfv1.TownUserGuidListRequest{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		log.Fatalf("Failed to unmarshal TownUserGuidListRequest: %v", err)
	}
	fmt.Printf("TownUserGuidListRequest 反序列化成功: count=%d, posx=%d, posy=%d\n",
		unmarshaled.Count, unmarshaled.Posx, unmarshaled.Posy)

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
		log.Fatalf("Failed to marshal TownUserGuidListResponse: %v", err)
	}
	fmt.Printf("TownUserGuidListResponse 序列化成功，长度: %d 字节\n", len(data))

	unmarshaledResp := &dnfv1.TownUserGuidListResponse{}
	if err := proto.Unmarshal(data, unmarshaledResp); err != nil {
		log.Fatalf("Failed to unmarshal TownUserGuidListResponse: %v", err)
	}
	fmt.Printf("TownUserGuidListResponse 反序列化成功: error=%d, charlist数量=%d\n",
		unmarshaledResp.Error, len(unmarshaledResp.Charlist))
}

func testTargetUserDetailInfo() {
	fmt.Println("\n--- 测试目标用户详情消息 ---")

	req := &dnfv1.TargetUserDetailInfoRequest{
		Targetguid: 1234567890,
	}

	data, err := proto.Marshal(req)
	if err != nil {
		log.Fatalf("Failed to marshal TargetUserDetailInfoRequest: %v", err)
	}
	fmt.Printf("TargetUserDetailInfoRequest 序列化成功，长度: %d 字节\n", len(data))

	unmarshaled := &dnfv1.TargetUserDetailInfoRequest{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		log.Fatalf("Failed to unmarshal TargetUserDetailInfoRequest: %v", err)
	}
	fmt.Printf("TargetUserDetailInfoRequest 反序列化成功: targetguid=%d\n", unmarshaled.Targetguid)

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
		log.Fatalf("Failed to marshal TargetUserDetailInfoResponse: %v", err)
	}
	fmt.Printf("TargetUserDetailInfoResponse 序列化成功，长度: %d 字节\n", len(data))

	unmarshaledResp := &dnfv1.TargetUserDetailInfoResponse{}
	if err := proto.Unmarshal(data, unmarshaledResp); err != nil {
		log.Fatalf("Failed to unmarshal TargetUserDetailInfoResponse: %v", err)
	}
	fmt.Printf("TargetUserDetailInfoResponse 反序列化成功: error=%d, name=%s, level=%d\n",
		unmarshaledResp.Error, unmarshaledResp.Name, unmarshaledResp.Level)
}

func testInteractionMenu() {
	fmt.Println("\n--- 测试交互菜单消息 ---")

	req := &dnfv1.InteractionMenuRequest{
		Charguid:     1234567890,
		Openmenutype: 1,
	}

	data, err := proto.Marshal(req)
	if err != nil {
		log.Fatalf("Failed to marshal InteractionMenuRequest: %v", err)
	}
	fmt.Printf("InteractionMenuRequest 序列化成功，长度: %d 字节\n", len(data))

	unmarshaled := &dnfv1.InteractionMenuRequest{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		log.Fatalf("Failed to unmarshal InteractionMenuRequest: %v", err)
	}
	fmt.Printf("InteractionMenuRequest 反序列化成功: charguid=%d, openmenutype=%d\n",
		unmarshaled.Charguid, unmarshaled.Openmenutype)

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
		log.Fatalf("Failed to marshal InteractionMenuResponse: %v", err)
	}
	fmt.Printf("InteractionMenuResponse 序列化成功，长度: %d 字节\n", len(data))

	unmarshaledResp := &dnfv1.InteractionMenuResponse{}
	if err := proto.Unmarshal(data, unmarshaledResp); err != nil {
		log.Fatalf("Failed to unmarshal InteractionMenuResponse: %v", err)
	}
	fmt.Printf("InteractionMenuResponse 反序列化成功: error=%d, name=%s, level=%d\n",
		unmarshaledResp.Error, unmarshaledResp.Name, unmarshaledResp.Level)
}
