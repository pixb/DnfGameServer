package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

public class PT_ACCOUNT_TICKET {
   @Protobuf(
      fieldType = FieldType.STRING,
      order = 1,
      required = false
   )
   public String dungeontype;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 2,
      required = false
   )
   public Integer count;
}
