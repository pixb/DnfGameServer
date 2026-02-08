package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

public class PT_CUSTOM_DATA {
   @Protobuf(
      fieldType = FieldType.UINT32,
      order = 1,
      required = false
   )
   public Integer type;
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 2,
      required = false
   )
   public Long senderguid;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 3,
      required = false
   )
   public Integer iValue;
   @Protobuf(
      fieldType = FieldType.FLOAT,
      order = 4,
      required = false
   )
   public Float fValue;
}
