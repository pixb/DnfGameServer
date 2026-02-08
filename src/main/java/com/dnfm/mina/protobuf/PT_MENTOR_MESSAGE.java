package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import java.util.List;

public class PT_MENTOR_MESSAGE {
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 1,
      required = false
   )
   public Long masterguid;
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 2,
      required = false
   )
   public Long guid;
   @Protobuf(
      fieldType = FieldType.STRING,
      order = 3,
      required = false
   )
   public String name;
   @Protobuf(
      fieldType = FieldType.STRING,
      order = 4,
      required = false
   )
   public String msg;
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 5,
      required = false
   )
   public Long date;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 6,
      required = false
   )
   public Integer job;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 7,
      required = false
   )
   public Integer growtype;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 8,
      required = false
   )
   public Integer secondgrowtype;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 9,
      required = false
   )
   public Integer type;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 10,
      required = false
   )
   public Integer level;
   @Protobuf(
      fieldType = FieldType.STRING,
      order = 11,
      required = false
   )
   public String voice;
   @Protobuf(
      fieldType = FieldType.STRING,
      order = 12,
      required = false
   )
   public String voicetime;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 13,
      required = false
   )
   public Integer hyperlinktype;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 14,
      required = false
   )
   public Integer hyperlinksubtype;
   @Protobuf(
      order = 15
   )
   public List<PT_HYPERLINK_DATA> hyperlinkdatas;
   @Protobuf(
      order = 16
   )
   public List<PT_HYPERLINK_SYSTEMMESSAGE_SUB> sub;
   @Protobuf(
      order = 17,
      required = false
   )
   public PT_EQUIP equip;
   @Protobuf(
      order = 18,
      required = false
   )
   public PT_EQUIP title;
   @Protobuf(
      order = 19,
      required = false
   )
   public PT_EQUIP flag;
   @Protobuf(
      order = 20,
      required = false
   )
   public PT_STACKABLE emblem;
   @Protobuf(
      order = 21,
      required = false
   )
   public PT_STACKABLE material;
   @Protobuf(
      order = 22,
      required = false
   )
   public PT_STACKABLE consume;
   @Protobuf(
      order = 23,
      required = false
   )
   public PT_AVATAR_ITEM avatar;
   @Protobuf(
      order = 24,
      required = false
   )
   public PT_SKIN_CHAT_INFO skinchatinfo;
   @Protobuf(
      order = 25,
      required = false
   )
   public PT_STACKABLE card;
   @Protobuf(
      order = 26,
      required = false
   )
   public PT_CREATURE creature;
   @Protobuf(
      order = 27,
      required = false
   )
   public PT_ARTIFACT cartifact;
   @Protobuf(
      fieldType = FieldType.STRING,
      order = 28
   )
   public List<String> list;
}
