package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import java.util.List;

public class PT_STATE_OBJECT_DROP {
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 1
   )
   public List<Integer> golds;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 2
   )
   public List<Integer> items;
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 3,
      required = false
   )
   public Long charguid;
}
