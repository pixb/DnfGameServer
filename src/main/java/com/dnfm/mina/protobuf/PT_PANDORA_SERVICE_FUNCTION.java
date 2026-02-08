package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

public class PT_PANDORA_SERVICE_FUNCTION {
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 1,
      required = false
   )
   public Integer functionid;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 2,
      required = false
   )
   public Integer isopen;
}
