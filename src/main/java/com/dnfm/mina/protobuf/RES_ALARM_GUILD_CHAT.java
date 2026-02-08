package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;
import java.util.List;

@MessageMeta(
   module = 27018,
   cmd = 1
)
public class RES_ALARM_GUILD_CHAT extends Message {
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 1,
      required = false
   )
   public Integer error;
   @Protobuf(
      fieldType = FieldType.STRING,
      order = 2,
      required = false
   )
   public String name;
   @Protobuf(
      fieldType = FieldType.STRING,
      order = 3,
      required = false
   )
   public String msg;
   @Protobuf(
      fieldType = FieldType.STRING,
      order = 4,
      required = false
   )
   public String voice;
   @Protobuf(
      fieldType = FieldType.STRING,
      order = 5,
      required = false
   )
   public String voicetime;
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 6,
      required = false
   )
   public Long date;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 7,
      required = false
   )
   public Integer job;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 8,
      required = false
   )
   public Integer level;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 9,
      required = false
   )
   public Integer growtype;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 10,
      required = false
   )
   public Integer secgrowtype;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 11,
      required = false
   )
   public Integer grade;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 12,
      required = false
   )
   public Integer type;
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 13,
      required = false
   )
   public Long charguid;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 14,
      required = false
   )
   public Integer creditscore;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 15,
      required = false
   )
   public Integer hyperlinktype;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 16,
      required = false
   )
   public Integer hyperlinksubtype;
   @Protobuf(
      order = 17
   )
   public List<PT_HYPERLINK_DATA> hyperlinkdatas;
   @Protobuf(
      order = 18
   )
   public List<PT_HYPERLINK_SYSTEMMESSAGE_SUB> sub;
   @Protobuf(
      order = 19,
      required = false
   )
   public PT_EQUIP equip;
   @Protobuf(
      order = 20,
      required = false
   )
   public PT_EQUIP title;
   @Protobuf(
      order = 21,
      required = false
   )
   public PT_EQUIP flag;
   @Protobuf(
      order = 22,
      required = false
   )
   public PT_STACKABLE emblem;
   @Protobuf(
      order = 23,
      required = false
   )
   public PT_STACKABLE material;
   @Protobuf(
      order = 24,
      required = false
   )
   public PT_STACKABLE consume;
   @Protobuf(
      order = 25,
      required = false
   )
   public PT_AVATAR_ITEM avatar;
   @Protobuf(
      order = 26,
      required = false
   )
   public PT_STACKABLE card;
   @Protobuf(
      order = 27,
      required = false
   )
   public PT_CREATURE creature;
   @Protobuf(
      order = 28,
      required = false
   )
   public PT_ARTIFACT cartifact;
   @Protobuf(
      order = 29,
      required = false
   )
   public PT_SKIN_CHAT_INFO skinchatinfo;
   @Protobuf(
      order = 30,
      required = false
   )
   public PT_AVATAR_ITEM sdavatar;
   @Protobuf(
      order = 31,
      required = false
   )
   public PT_RECRUIT_PARTY_INFO info;
   @Protobuf(
      fieldType = FieldType.STRING,
      order = 32
   )
   public List<String> list;
   @Protobuf(
      fieldType = FieldType.INT64,
      order = 33,
      required = false
   )
   public Long targetguid;
   @Protobuf(
      order = 34,
      required = false
   )
   public PT_STACKABLE crack;
   @Protobuf(
      order = 35,
      required = false
   )
   public PT_EQUIP crackequip;
}
