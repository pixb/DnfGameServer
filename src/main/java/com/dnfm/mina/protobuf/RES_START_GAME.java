package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;
import java.util.List;

@MessageMeta(
   module = 10005,
   cmd = 1
)
public class RES_START_GAME extends Message {
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
   public Integer growchangecount;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 3,
      required = false
   )
   public Integer world;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 4,
      required = false
   )
   public Integer currentworld;
   @Protobuf(
      fieldType = FieldType.UINT32,
      order = 5,
      required = false
   )
   public Integer town;
   @Protobuf(
      fieldType = FieldType.UINT32,
      order = 6,
      required = false
   )
   public Integer area;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 7,
      required = false
   )
   public Integer level;
   @Protobuf(
      fieldType = FieldType.UINT32,
      order = 8,
      required = false
   )
   public Integer exp;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 9,
      required = false
   )
   public Integer fatigue;
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 10,
      required = false
   )
   public Long gguid;
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 11,
      required = false
   )
   public Long masterguid;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 12,
      required = false
   )
   public Integer ismaster;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 13,
      required = false
   )
   public Integer cera;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 14,
      required = false
   )
   public Integer goldcoin;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 15,
      required = false
   )
   public Integer helpercount;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 16,
      required = false
   )
   public Integer guide;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 17,
      required = false
   )
   public Integer groupjoined;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 18,
      required = false
   )
   public Integer lupgrade;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 19,
      required = false
   )
   public Integer lamplify;
   @Protobuf(
      order = 20
   )
   public List<PT_DINING_FOOD_BUFF_INFO> bufflist;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 21,
      required = false
   )
   public Integer index;
   @Protobuf(
      fieldType = FieldType.INT64,
      order = 22,
      required = false
   )
   public Long endtime;
   @Protobuf(
      fieldType = FieldType.INT64,
      order = 23,
      required = false
   )
   public Long fatiguetime;
   @Protobuf(
      fieldType = FieldType.INT64,
      order = 24,
      required = false
   )
   public Long recoveryfatiguetime;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 25,
      required = false
   )
   public Integer partydisturb;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 26,
      required = false
   )
   public Integer battletutorial;
   @Protobuf(
      fieldType = FieldType.STRING,
      order = 27,
      required = false
   )
   public String gname;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 28,
      required = false
   )
   public Integer qindex;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 29,
      required = false
   )
   public Integer areaId;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 30,
      required = false
   )
   public Integer expratio;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 31,
      required = false
   )
   public Integer fatigueratio;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 32,
      required = false
   )
   public Integer chatchnidx;
   @Protobuf(
      fieldType = FieldType.INT64,
      order = 33,
      required = false
   )
   public Long createcount;
   @Protobuf(
      order = 34,
      required = false
   )
   public PT_TSSLIGHTSIG_LIST lightsig;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 35,
      required = false
   )
   public Integer queststate;
   @Protobuf(
      order = 36,
      required = false
   )
   public PT_RETURN_USER_INFO returnUserInfo;
   @Protobuf(
      order = 37,
      required = false
   )
   public PT_BATTLE_PASS_INFO battlePassInfo;
   @Protobuf(
      order = 38,
      required = false
   )
   public PT_BATTLE_PASS_INFO pvpBattlePassInfo;
   @Protobuf(
      fieldType = FieldType.BOOL,
      order = 39,
      required = false
   )
   public Boolean iosreviewsystem;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 40,
      required = false
   )
   public Integer mt6;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 41,
      required = false
   )
   public Integer intervaltowncharacterinfoms;
   @Protobuf(
      fieldType = FieldType.BOOL,
      order = 42,
      required = false
   )
   public Boolean needinitskill;
   @Protobuf(
      order = 43,
      required = false
   )
   public PT_INIT_SKILL initskillversion;
   @Protobuf(
      order = 44
   )
   public List<PT_TOWN_INTERVAL> towninterval;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 45,
      required = false
   )
   public Integer maritalstatus;
   @Protobuf(
      fieldType = FieldType.BOOL,
      order = 46,
      required = false
   )
   public Boolean adventurebookOpen;
   @Protobuf(
      fieldType = FieldType.INT64,
      order = 47,
      required = false
   )
   public Long lastkicktime;
   @Protobuf(
      fieldType = FieldType.ENUM,
      order = 48,
      required = false
   )
   public ENUM_MESSAGE_ASSISTANT_SWITCH_STATUS.T switchstatus;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 49,
      required = false
   )
   public Integer svnrevision;
   @Protobuf(
      fieldType = FieldType.STRING,
      order = 50,
      required = false
   )
   public String gitrevision;
   @Protobuf(
      order = 51,
      required = false
   )
   public PT_EVENT_SELECT_INFO eventSelectInfo;
}
