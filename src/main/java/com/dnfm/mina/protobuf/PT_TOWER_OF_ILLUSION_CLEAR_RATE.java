package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

public class PT_TOWER_OF_ILLUSION_CLEAR_RATE {
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 1,
      required = false
   )
   public Integer floor;
   @Protobuf(
      fieldType = FieldType.FLOAT,
      order = 2,
      required = false
   )
   public Float percent;
}
