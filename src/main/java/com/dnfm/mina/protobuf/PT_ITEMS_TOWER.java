package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import java.util.List;

public class PT_ITEMS_TOWER {
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
      fieldType = FieldType.INT32,
      order = 12,
      required = false
   )
   public Integer index;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 13,
      required = false
   )
   public Integer count;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 14,
      required = false
   )
   public Integer floor;
}
