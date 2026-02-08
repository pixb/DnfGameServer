package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import java.util.List;

public class PT_POST_ALL_LIST {
   @Protobuf(
      order = 1
   )
   public List<PT_EQUIP> equipitems;
   @Protobuf(
      order = 2
   )
   public List<PT_EQUIP> titleitems;
   @Protobuf(
      order = 3
   )
   public List<PT_EQUIP> flagitems;
   @Protobuf(
      order = 4
   )
   public List<PT_STACKABLE> materialitems;
   @Protobuf(
      order = 5
   )
   public List<PT_STACKABLE> consumeitems;
   @Protobuf(
      order = 6
   )
   public List<PT_STACKABLE> emblemitems;
   @Protobuf(
      order = 7
   )
   public List<PT_STACKABLE> carditems;
   @Protobuf(
      order = 8
   )
   public List<PT_STACKABLE> epicpieceitems;
   @Protobuf(
      order = 9
   )
   public List<PT_ARTIFACT> cartifactitems;
   @Protobuf(
      order = 10
   )
   public List<PT_CREATURE> creatureitems;
   @Protobuf(
      order = 11
   )
   public List<PT_AVATAR_ITEM> avataritems;
   @Protobuf(
      order = 12
   )
   public List<PT_STACKABLE> crackitems;
   @Protobuf(
      order = 13
   )
   public List<PT_EQUIP> crackequipitems;
   @Protobuf(
      order = 14
   )
   public List<PT_AVATAR_ITEM> sdavataritems;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 15,
      required = false
   )
   public Integer index;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 16,
      required = false
   )
   public Integer count;
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 17,
      required = false
   )
   public Long guid;
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
   public String msg;
   @Protobuf(
      fieldType = FieldType.INT64,
      order = 20,
      required = false
   )
   public Long expiretime;
   @Protobuf(
      order = 21
   )
   public List<PT_POST_PACKAGE> package0;
   @Protobuf(
      order = 22
   )
   public List<PT_LinkText> link;
   @Protobuf(
      fieldType = FieldType.BOOL,
      order = 23,
      required = false
   )
   public Boolean read;
   @Protobuf(
      fieldType = FieldType.BOOL,
      order = 24,
      required = false
   )
   public Boolean receive;
   @Protobuf(
      order = 25
   )
   public List<PT_POST_PACKAGE> remainedpackages;
   @Protobuf(
      order = 26
   )
   public List<PT_SELECTED_ITEM> remaineditems;
   @Protobuf(
      order = 27
   )
   public List<PT_ITEM_TIMEBOX> timeboxitems;
   @Protobuf(
      order = 28
   )
   public List<PT_STACKABLE> bookmarkitems;
   @Protobuf(
      order = 29
   )
   public List<PT_EQUIP> scrollitems;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 30,
      required = false
   )
   public Integer importance;
}
