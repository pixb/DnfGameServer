package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

public class PT_GUILD_HISTORY_VALUE {
   @Protobuf(
      fieldType = FieldType.STRING,
      order = 1,
      required = false
   )
   public String value;
}
