package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import java.util.List;

public class PT_SYSTEM_COMMAND {
   @Protobuf(
      order = 1,
      required = false
   )
   public PT_SYSCMD_COMMAND commands;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 2,
      required = false
   )
   public Integer dungeon;
   @Protobuf(
      fieldType = FieldType.BYTES,
      order = 3,
      required = false
   )
   public byte[] value;
   @Protobuf(
      fieldType = FieldType.STRING,
      order = 4,
      required = false
   )
   public String reason;
   @Protobuf(
      fieldType = FieldType.UINT32,
      order = 5,
      required = false
   )
   public Integer type;
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 6,
      required = false
   )
   public Long time;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 7,
      required = false
   )
   public Integer idip;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 8,
      required = false
   )
   public Integer platID;
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 9,
      required = false
   )
   public Long charguid;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 10,
      required = false
   )
   public Integer subtype;
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 11,
      required = false
   )
   public Long addtime;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 12,
      required = false
   )
   public Integer zeroBenefit;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 13,
      required = false
   )
   public Integer unPlay;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 14,
      required = false
   )
   public Integer unBanRank;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 15,
      required = false
   )
   public Integer unGag;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 16,
      required = false
   )
   public Integer unTitle;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 17,
      required = false
   )
   public Integer cera;
   @Protobuf(
      fieldType = FieldType.STRING,
      order = 18,
      required = false
   )
   public String title;
   @Protobuf(
      fieldType = FieldType.STRING,
      order = 19,
      required = false
   )
   public String sender;
   @Protobuf(
      fieldType = FieldType.STRING,
      order = 20,
      required = false
   )
   public String content;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 21,
      required = false
   )
   public Integer itemflag;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 22,
      required = false
   )
   public Integer subreason;
   @Protobuf(
      fieldType = FieldType.BOOL,
      order = 23,
      required = false
   )
   public Boolean systemreq;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 24,
      required = false
   )
   public Integer exp;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 25,
      required = false
   )
   public Integer growtype;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 26,
      required = false
   )
   public Integer secgrowtype;
   @Protobuf(
      fieldType = FieldType.BOOL,
      order = 27,
      required = false
   )
   public Boolean reset;
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
   public Integer qtype;
   @Protobuf(
      fieldType = FieldType.BOOL,
      order = 30,
      required = false
   )
   public Boolean flag;
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 31,
      required = false
   )
   public Long fguid;
   @Protobuf(
      fieldType = FieldType.INT64,
      order = 32,
      required = false
   )
   public Long fromsendtime;
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 33,
      required = false
   )
   public Long closenessguid;
   @Protobuf(
      order = 34
   )
   public List<PT_SYSCMD_ITEMJSON> items;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 35,
      required = false
   )
   public Integer cmd;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 36,
      required = false
   )
   public Integer source;
   @Protobuf(
      fieldType = FieldType.STRING,
      order = 37,
      required = false
   )
   public String serial;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 38,
      required = false
   )
   public Integer dungeonindex;
   @Protobuf(
      fieldType = FieldType.INT64,
      order = 39,
      required = false
   )
   public Long withdrawaltime;
   @Protobuf(
      fieldType = FieldType.BOOL,
      order = 40,
      required = false
   )
   public Boolean kickout;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 41,
      required = false
   )
   public Integer expireday;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 42,
      required = false
   )
   public Integer matchtype;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 43,
      required = false
   )
   public Integer result;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 44,
      required = false
   )
   public Integer status;
   @Protobuf(
      fieldType = FieldType.BOOL,
      order = 45,
      required = false
   )
   public Boolean ison;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 46,
      required = false
   )
   public Integer shopid;
   @Protobuf(
      order = 47
   )
   public List<PT_SYSCMD_CERASHOP> lists;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 48,
      required = false
   )
   public Integer goodsid;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 49,
      required = false
   )
   public Integer amount;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 50,
      required = false
   )
   public Integer count;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 51,
      required = false
   )
   public Integer buycount;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 52,
      required = false
   )
   public Integer packageindex;
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 53,
      required = false
   )
   public Long guid;
   @Protobuf(
      fieldType = FieldType.STRING,
      order = 54,
      required = false
   )
   public String msg;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 55,
      required = false
   )
   public Integer itemindex;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 56,
      required = false
   )
   public Integer index;
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 57,
      required = false
   )
   public Long receiverguid;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 58,
      required = false
   )
   public Integer boardtype;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 59,
      required = false
   )
   public Integer reactiontype;
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 60,
      required = false
   )
   public Long noteguid;
   @Protobuf(
      fieldType = FieldType.STRING,
      order = 61,
      required = false
   )
   public String fopenid;
   @Protobuf(
      fieldType = FieldType.STRING,
      order = 62,
      required = false
   )
   public String openid;
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 63,
      required = false
   )
   public Long accountkey;
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 64,
      required = false
   )
   public Long itemguid;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 65,
      required = false
   )
   public Integer inventype;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 66,
      required = false
   )
   public Integer upgrade;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 67,
      required = false
   )
   public Integer reforge;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 68,
      required = false
   )
   public Integer endurance;
   @Protobuf(
      fieldType = FieldType.STRING,
      order = 69,
      required = false
   )
   public String changename;
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 70,
      required = false
   )
   public Long gguid;
   @Protobuf(
      fieldType = FieldType.INT64,
      order = 71,
      required = false
   )
   public Long endtime;
   @Protobuf(
      order = 72
   )
   public List<PT_SYSCMD_PACKAGES> packages;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 73,
      required = false
   )
   public Integer world;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 74,
      required = false
   )
   public Integer channel;
   @Protobuf(
      order = 75,
      required = false
   )
   public PT_SYSCMD_COMMAND_TAKE_ITEM takeitem;
   @Protobuf(
      fieldType = FieldType.INT64,
      order = 76,
      required = false
   )
   public Long createtime;
   @Protobuf(
      order = 77,
      required = false
   )
   public PT_SYSCMD_PARTY party;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 78,
      required = false
   )
   public Integer importance;
   @Protobuf(
      fieldType = FieldType.BOOL,
      order = 79,
      required = false
   )
   public Boolean needcare;
   @Protobuf(
      fieldType = FieldType.BOOL,
      order = 80,
      required = false
   )
   public Boolean faileddelete;
   @Protobuf(
      fieldType = FieldType.STRING,
      order = 81,
      required = false
   )
   public String orderId;
   @Protobuf(
      fieldType = FieldType.STRING,
      order = 82,
      required = false
   )
   public String reasonOpt;
   @Protobuf(
      order = 83
   )
   public List<PT_SYSCMD_MASTERBUFF> masterbuff;
   @Protobuf(
      fieldType = FieldType.STRING,
      order = 84,
      required = false
   )
   public String mailType;
}
