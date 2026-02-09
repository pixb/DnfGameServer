package test

import (
	"testing"

	"github.com/pixb/DnfGameServer/dnf-go-client/gen/dnf/v1"
	"google.golang.org/protobuf/proto"
)

// 测试公会符号消息
func TestBatch23GuildSymbol(t *testing.T) {
	guildSymbol := &dnfv1.GuildSymbol{
		Index:      1,
		Colorindex: 2,
	}

	data, err := proto.Marshal(guildSymbol)
	if err != nil {
		t.Fatalf("Failed to marshal GuildSymbol: %v", err)
	}

	unmarshaled := &dnfv1.GuildSymbol{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal GuildSymbol: %v", err)
	}

	if unmarshaled.Index != guildSymbol.Index {
		t.Errorf("Index mismatch: got %d, want %d", unmarshaled.Index, guildSymbol.Index)
	}

	if unmarshaled.Colorindex != guildSymbol.Colorindex {
		t.Errorf("Colorindex mismatch: got %d, want %d", unmarshaled.Colorindex, guildSymbol.Colorindex)
	}
}

// 测试avatar物品消息
func TestBatch23SdAvatarItem(t *testing.T) {
	sdAvatarItem := &dnfv1.SdAvatarItem{
		Index:      1,
		Count:      10,
		Guid:       123456789,
		Expiretime: 987654321,
		Option:     0,
		Locked:     false,
		Scount:     5,
		Tcount:     15,
	}

	data, err := proto.Marshal(sdAvatarItem)
	if err != nil {
		t.Fatalf("Failed to marshal SdAvatarItem: %v", err)
	}

	unmarshaled := &dnfv1.SdAvatarItem{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal SdAvatarItem: %v", err)
	}

	if unmarshaled.Index != sdAvatarItem.Index {
		t.Errorf("Index mismatch: got %d, want %d", unmarshaled.Index, sdAvatarItem.Index)
	}

	if unmarshaled.Count != sdAvatarItem.Count {
		t.Errorf("Count mismatch: got %d, want %d", unmarshaled.Count, sdAvatarItem.Count)
	}

	if unmarshaled.Guid != sdAvatarItem.Guid {
		t.Errorf("Guid mismatch: got %d, want %d", unmarshaled.Guid, sdAvatarItem.Guid)
	}

	if unmarshaled.Locked != sdAvatarItem.Locked {
		t.Errorf("Locked mismatch: got %v, want %v", unmarshaled.Locked, sdAvatarItem.Locked)
	}
}

// 测试排名信息消息
func TestBatch23Ranking(t *testing.T) {
	ranking := &dnfv1.Ranking{
		Guid:           123456789,
		Growtype:       1,
		Secondgrowtype: 0,
		Job:            1,
		Level:          50,
		Name:           "TestUser",
		Rank:           1,
		Score:          10000,
		Gsymbol: []*dnfv1.GuildSymbol{
			{
				Index:      1,
				Colorindex: 2,
			},
		},
		Gmastername:    "GuildMaster",
		Launchinfo:     1,
		Vip:            5,
		Profileurl:     "http://example.com/profile.jpg",
		Profilename:    "profile.jpg",
		Wincount:       100,
		Gwinpoint:      1000,
		Winningrate:    80,
		Spinfos:        []int32{1, 2, 3},
		Creditscore:    1000,
		Characterframe: 1,
		Platform:       1,
		Platformserverid: 1,
		Gname:          "TestGuild",
	}

	data, err := proto.Marshal(ranking)
	if err != nil {
		t.Fatalf("Failed to marshal Ranking: %v", err)
	}

	unmarshaled := &dnfv1.Ranking{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal Ranking: %v", err)
	}

	if unmarshaled.Guid != ranking.Guid {
		t.Errorf("Guid mismatch: got %d, want %d", unmarshaled.Guid, ranking.Guid)
	}

	if unmarshaled.Name != ranking.Name {
		t.Errorf("Name mismatch: got %s, want %s", unmarshaled.Name, ranking.Name)
	}

	if unmarshaled.Rank != ranking.Rank {
		t.Errorf("Rank mismatch: got %d, want %d", unmarshaled.Rank, ranking.Rank)
	}

	if len(unmarshaled.Gsymbol) != len(ranking.Gsymbol) {
		t.Errorf("Gsymbol length mismatch: got %d, want %d", len(unmarshaled.Gsymbol), len(ranking.Gsymbol))
	}

	if len(unmarshaled.Spinfos) != len(ranking.Spinfos) {
		t.Errorf("Spinfos length mismatch: got %d, want %d", len(unmarshaled.Spinfos), len(ranking.Spinfos))
	}
}
