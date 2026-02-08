package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

public class PT_GACC_REMOVE_RESULT_CHARACTER {
   @Protobuf(
      fieldType = FieldType.UINT32,
      order = 1,
      required = false
   )
   public Integer platID;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 2,
      required = false
   )
   public Integer worldid;
   @Protobuf(
      fieldType = FieldType.STRING,
      order = 3,
      required = false
   )
   public String name;
}
