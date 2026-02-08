package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import java.util.List;

public class PT_PACK_LOTTERY_REWARD {
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
      order = 17
   )
   public List<PT_MONEY_ITEM> currency;
   @Protobuf(
      order = 18
   )
   public List<PT_MONEY_ITEM> accountcurrency;
}
