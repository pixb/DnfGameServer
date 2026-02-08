package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;
import java.util.List;

@MessageMeta(
   module = 14042,
   cmd = 1
)
public class RES_ITEM_COMBINE extends Message {
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 1,
      required = false
   )
   public Integer error;
   @Protobuf(
      order = 2,
      required = false
   )
   public PT_EQUIP equip;
   @Protobuf(
      order = 3,
      required = false
   )
   public PT_AVATAR_ITEM avatar;
   @Protobuf(
      order = 4
   )
   public List<PT_CREATURE> creatures;
   @Protobuf(
      order = 5,
      required = false
   )
   public PT_ARTIFACT artifact;
   @Protobuf(
      order = 6,
      required = false
   )
   public PT_STACKABLE material;
   @Protobuf(
      order = 7,
      required = false
   )
   public PT_STACKABLE card;
   @Protobuf(
      order = 8,
      required = false
   )
   public PT_STACKABLE emblem;
   @Protobuf(
      order = 9,
      required = false
   )
   public PT_STACKABLE consume;
   @Protobuf(
      order = 10,
      required = false
   )
   public PT_AVATAR_ITEM sdavatar;
   @Protobuf(
      order = 11,
      required = false
   )
   public PT_REMOVEITEMS removeitems;
}
