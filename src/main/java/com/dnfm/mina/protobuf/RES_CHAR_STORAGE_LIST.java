package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;
import java.util.List;

@MessageMeta(
   module = 14100,
   cmd = 1
)
public class RES_CHAR_STORAGE_LIST extends Message {
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 1,
      required = false
   )
   public Integer error;
   @Protobuf(
      order = 2
   )
   public List<PT_EQUIP> equipitems;
   @Protobuf(
      order = 3
   )
   public List<PT_EQUIP> titleitems;
   @Protobuf(
      order = 4
   )
   public List<PT_EQUIP> flagitems;
   @Protobuf(
      order = 5
   )
   public List<PT_STACKABLE> materialitems;
   @Protobuf(
      order = 6
   )
   public List<PT_STACKABLE> consumeitems;
   @Protobuf(
      order = 7
   )
   public List<PT_STACKABLE> emblemitems;
   @Protobuf(
      order = 8
   )
   public List<PT_STACKABLE> carditems;
   @Protobuf(
      order = 9
   )
   public List<PT_STACKABLE> epicpieceitems;
   @Protobuf(
      order = 10
   )
   public List<PT_ARTIFACT> cartifactitems;
   @Protobuf(
      order = 11
   )
   public List<PT_CREATURE> creatureitems;
   @Protobuf(
      order = 12
   )
   public List<PT_AVATAR_ITEM> avataritems;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 13,
      required = false
   )
   public Integer line;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 14,
      required = false
   )
   public Integer page;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 15,
      required = false
   )
   public Integer storagegold;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 16,
      required = false
   )
   public Integer storagetera;
}
