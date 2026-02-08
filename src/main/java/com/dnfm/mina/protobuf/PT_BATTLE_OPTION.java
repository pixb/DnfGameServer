package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

public class PT_BATTLE_OPTION {
   @Protobuf(
      order = 1,
      required = false
   )
   public ENUM_BATTLE_OPTION.T type;
   @Protobuf(
      fieldType = FieldType.BOOL,
      order = 2,
      required = false
   )
   public Boolean value;
}
