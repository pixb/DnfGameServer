package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import java.util.List;

public class PT_GACHA_RESULT {
   public int grade;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 1,
      required = false
   )
   public Integer recipe;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 2,
      required = false
   )
   public Integer gachaindex;
   @Protobuf(
      order = 3
   )
   public List<PT_EQUIP> equip;
   @Protobuf(
      order = 4
   )
   public List<PT_STACKABLE> material;
   @Protobuf(
      order = 5
   )
   public List<PT_AVATAR_ITEM> avatar;
   @Protobuf(
      order = 6
   )
   public List<PT_STACKABLE> emblem;
   @Protobuf(
      order = 7
   )
   public List<PT_STACKABLE> card;
   @Protobuf(
      order = 8
   )
   public List<PT_CREATURE> creature;
   @Protobuf(
      order = 9
   )
   public List<PT_ARTIFACT> cartifact;
   @Protobuf(
      order = 10
   )
   public List<PT_STACKABLE> consume;
   @Protobuf(
      order = 11
   )
   public List<PT_STACKABLE> crack;
   @Protobuf(
      order = 12
   )
   public List<PT_EQUIP> crackequip;
   @Protobuf(
      order = 13
   )
   public List<PT_AVATAR_ITEM> sdavatar;
   @Protobuf(
      order = 14
   )
   public List<PT_STACKABLE> bookmark;
   @Protobuf(
      order = 15
   )
   public List<PT_EQUIP> scroll;
}
