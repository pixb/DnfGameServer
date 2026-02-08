package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

public class PT_ADVENTURE_UNION_SHAREBOARD_SLOT_DETAIL_INFO {
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 1,
      required = false
   )
   public Integer slot;
   @Protobuf(
      fieldType = FieldType.BOOL,
      order = 2,
      required = false
   )
   public Boolean direction;
   @Protobuf(
      order = 3,
      required = false
   )
   public PT_CHARACTER characterinfo;
}
