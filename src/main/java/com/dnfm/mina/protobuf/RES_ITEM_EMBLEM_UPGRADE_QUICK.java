package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;
import java.util.List;

@MessageMeta(
   module = 14079,
   cmd = 1
)
public class RES_ITEM_EMBLEM_UPGRADE_QUICK extends Message {
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
   public Integer target;
   @Protobuf(
      order = 3
   )
   public List<PT_INDEX_COUNT> source;
   @Protobuf(
      order = 4,
      required = false
   )
   public PT_REMOVEITEMS removeitems;
   @Protobuf(
      order = 5,
      required = false
   )
   public PT_CONTENTS_REWARD_INFO rewards;
}
