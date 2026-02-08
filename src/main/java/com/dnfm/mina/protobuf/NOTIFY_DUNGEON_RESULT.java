package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;
import java.util.List;

@MessageMeta(
   module = 11016,
   cmd = 1
)
public class NOTIFY_DUNGEON_RESULT extends Message {
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 1,
      required = false
   )
   public Integer error;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 2,
      required = false
   )
   public Integer dungeonindex;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 3,
      required = false
   )
   public Integer matchtype;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 4,
      required = false
   )
   public Integer exp;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 5,
      required = false
   )
   public Integer level;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 6,
      required = false
   )
   public Integer count;
   @Protobuf(
      fieldType = FieldType.INT64,
      order = 7,
      required = false
   )
   public Long time;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 8,
      required = false
   )
   public Integer point;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 9,
      required = false
   )
   public Integer grade;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 10,
      required = false
   )
   public Integer gage;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 11,
      required = false
   )
   public Integer durability;
   @Protobuf(
      order = 12,
      required = false
   )
   public PT_EXP_DETAILINFO clearexp;
   @Protobuf(
      order = 13,
      required = false
   )
   public PT_ESCORT_REWARD escortreward;
   @Protobuf(
      order = 14
   )
   public List<PT_CLEAR_DUNGEON_STARREWARD> starrewards;
   @Protobuf(
      order = 15,
      required = false
   )
   public PT_ADVENTURE_CLEAR_REWARD totalclearreward;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 16,
      required = false
   )
   public Integer battleworld;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 17,
      required = false
   )
   public Integer bchannel;
   @Protobuf(
      fieldType = FieldType.STRING,
      order = 18,
      required = false
   )
   public String bip;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 19,
      required = false
   )
   public Integer bport;
   @Protobuf(
      fieldType = FieldType.STRING,
      order = 20,
      required = false
   )
   public String authkey;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 21,
      required = false
   )
   public Integer type;
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 22,
      required = false
   )
   public Long tick;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 23,
      required = false
   )
   public Integer mileage;
   @Protobuf(
      order = 24,
      required = false
   )
   public PT_TOWER_REWARD_ITEMS firstclearreward;
   @Protobuf(
      order = 25,
      required = false
   )
   public PT_TOWER_REWARD_ITEMS betterclearreward;
   @Protobuf(
      order = 26,
      required = false
   )
   public PT_TOWER_REWARD_ITEMS mileagereward;
   @Protobuf(
      order = 27,
      required = false
   )
   public PT_TOWER_REWARD_ITEMS timecutclearreward;
   @Protobuf(
      order = 28,
      required = false
   )
   public PT_TOWER_REWARD_ITEMS stageclearreward;
   @Protobuf(
      order = 29,
      required = false
   )
   public PT_WORLDRADE_REWARD worldraidreward;
   @Protobuf(
      order = 30
   )
   public List<PT_CLOSENESS_RESULT_INFO> closenessreward;
   @Protobuf(
      order = 31
   )
   public List<PT_ARCADE_PVP_INFO_CURRENCY> arcadereward;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 32,
      required = false
   )
   public Integer performancerating;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 33,
      required = false
   )
   public Integer result;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 34,
      required = false
   )
   public Integer pvpstarcount;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 35,
      required = false
   )
   public Integer pvpstargrade;
   @Protobuf(
      order = 36
   )
   public List<PT_GUARDIAN_ORDER> orderlist;
   @Protobuf(
      order = 37
   )
   public List<PT_GUARDIAN_AUCTION> guildauction;
   @Protobuf(
      order = 38
   )
   public List<PT_SD_DEATH_MATCH_RECORD> deathmatchrecord;
   @Protobuf(
      order = 39
   )
   public List<PT_SD_DEATH_MATCH_RECORD> deathmatchrecord1;
   @Protobuf(
      order = 40
   )
   public List<PT_SD_DEATH_MATCH_RECORD> deathmatchrecord2;
   @Protobuf(
      order = 41
   )
   public List<PT_SD_DEATH_MATCH_REWARD> deathrewards;
   @Protobuf(
      order = 42
   )
   public List<PT_SD_DEATH_MATCH_REWARD> deathrewards1;
   @Protobuf(
      order = 43
   )
   public List<PT_SD_DEATH_MATCH_REWARD> deathrewards2;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 44,
      required = false
   )
   public Integer winpoint;
   @Protobuf(
      order = 45
   )
   public List<PT_SD_DEATH_MATCH_RECORD> record;
   @Protobuf(
      order = 46
   )
   public List<PT_MONEY_ITEM> currency;
   @Protobuf(
      order = 47
   )
   public List<PT_ACCOUNT_TICKET> adventureticket;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 48,
      required = false
   )
   public Integer fatigue;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 49,
      required = false
   )
   public Integer consumefatigue;
   @Protobuf(
      order = 50
   )
   public List<PT_ITEMS_TOWER> rewards;
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 51,
      required = false
   )
   public Long dungeonguid;
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 52,
      required = false
   )
   public Long ancientfirstclearcount;
   @Protobuf(
      order = 53
   )
   public List<PT_ALL_CLEAR_REWARD> list;
   @Protobuf(
      order = 54,
      required = false
   )
   public PT_PVP_GLORY_RESULT gloryinfo;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 55,
      required = false
   )
   public Integer upanddowngradetype;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 56
   )
   public List<Integer> upanddowngraderesults;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 57,
      required = false
   )
   public Integer batteleaguecontribution;
   @Protobuf(
      order = 58
   )
   public List<PT_BATTLELEAGUE_PVE_RECORD> battleleaguepverecord1;
   @Protobuf(
      order = 59
   )
   public List<PT_BATTLELEAGUE_PVE_RECORD> battleleaguepverecord2;
   @Protobuf(
      order = 60
   )
   public List<PT_BATTLELEAGUE_PVP_RECORD> battleleaguepvprecord1;
   @Protobuf(
      order = 61
   )
   public List<PT_BATTLELEAGUE_PVP_RECORD> battleleaguepvprecord2;
   @Protobuf(
      order = 62,
      required = false
   )
   public PT_BATTLELEAGUE_REWARD battleleaguepvereward;
   @Protobuf(
      order = 63
   )
   public List<PT_BATTLELEAGUE_REWARD> battleleaguepvprewards1;
   @Protobuf(
      order = 64
   )
   public List<PT_BATTLELEAGUE_REWARD> battleleaguepvprewards2;
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 65,
      required = false
   )
   public Long battleleaguemvp;
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 66
   )
   public List<Long> battleleaguemvps;
   @Protobuf(
      order = 67,
      required = false
   )
   public PT_BATTLELEAGUE_REWARD blpverewardprivate;
   @Protobuf(
      order = 68,
      required = false
   )
   public PT_BATTLELEAGUE_REWARD blpverewardpublic;
   @Protobuf(
      order = 69
   )
   public List<PT_BATTLELEAGUE_REWARD> blpvpreward;
   @Protobuf(
      fieldType = FieldType.INT64,
      order = 70,
      required = false
   )
   public Long playtime;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 71,
      required = false
   )
   public Integer cardcost;
}
