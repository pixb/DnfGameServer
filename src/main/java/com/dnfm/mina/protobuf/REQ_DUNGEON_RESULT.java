package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;
import java.util.List;

@MessageMeta(
   module = 11012,
   cmd = 0
)
public class REQ_DUNGEON_RESULT extends Message {
   @Protobuf(
      fieldType = FieldType.BYTES,
      order = 1,
      required = false
   )
   public byte[] damagehistory;
   @Protobuf(
      fieldType = FieldType.STRING,
      order = 2,
      required = false
   )
   public String authkey;
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 3,
      required = false
   )
   public Long dungeonguid;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 4,
      required = false
   )
   public Integer stage;
   @Protobuf(
      order = 5
   )
   public List<PT_DUNGEON_RESULT_QUEST_INFO> qindex;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 6,
      required = false
   )
   public Integer rankscore;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 7,
      required = false
   )
   public Integer score;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 8,
      required = false
   )
   public Integer type;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 9,
      required = false
   )
   public Integer result;
   @Protobuf(
      order = 10
   )
   public List<PT_SKILL_USE_COUNT> useskill;
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 11,
      required = false
   )
   public Long cleartime;
   @Protobuf(
      fieldType = FieldType.STRING,
      order = 12,
      required = false
   )
   public String time;
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 13,
      required = false
   )
   public Long matchingguid;
   @Protobuf(
      order = 14
   )
   public List<PT_MONSTER> monster;
   @Protobuf(
      order = 15
   )
   public List<PT_PASSIVE_OBJECT> pobs;
   @Protobuf(
      fieldType = FieldType.INT64,
      order = 16,
      required = false
   )
   public Long guardiandeal;
   @Protobuf(
      fieldType = FieldType.INT64,
      order = 17,
      required = false
   )
   public Long guardianhp;
   @Protobuf(
      order = 18
   )
   public List<PT_GUARDIAN_DEAL> guardiandeallist;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 19,
      required = false
   )
   public Integer gghp;
   @Protobuf(
      order = 20
   )
   public List<PT_GOLD_GOBLIN_HP> gglist;
   @Protobuf(
      fieldType = FieldType.INT64,
      order = 21,
      required = false
   )
   public Long wagondeal;
   @Protobuf(
      fieldType = FieldType.INT64,
      order = 22,
      required = false
   )
   public Long wagonhp;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 23,
      required = false
   )
   public Integer woundedcount;
   @Protobuf(
      fieldType = FieldType.INT64,
      order = 24,
      required = false
   )
   public Long damage;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 25,
      required = false
   )
   public Integer behit;
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 26
   )
   public List<Long> leaveusers;
   @Protobuf(
      order = 27,
      required = false
   )
   public PT_PVP_BATTLE_INFO pvpbattleinfo;
   @Protobuf(
      order = 28,
      required = false
   )
   public PT_PVE_ROUND_INFO pveroundinfo;
   @Protobuf(
      fieldType = FieldType.STRING,
      order = 29,
      required = false
   )
   public String gamesafedata;
   @Protobuf(
      order = 30
   )
   public List<PT_RAID_ROUND_INFO> raidroundinfos;
   @Protobuf(
      order = 31,
      required = false
   )
   public PT_PVP_GLORY_RESULT gloryinfo;
   @Protobuf(
      order = 32,
      required = false
   )
   public PT_ACTION_COUNT_INFO actioncountinfo;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 33,
      required = false
   )
   public Integer garrisonhprate;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 34,
      required = false
   )
   public Integer garrisonplaytime;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 35,
      required = false
   )
   public Integer batteleaguecontribution;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 36
   )
   public List<Integer> bookconditionindexes;
   @Protobuf(
      fieldType = FieldType.BOOL,
      order = 37,
      required = false
   )
   public Boolean isfailedtimecondition;
   @Protobuf(
      fieldType = FieldType.BOOL,
      order = 38,
      required = false
   )
   public Boolean isbanrematch;
}
