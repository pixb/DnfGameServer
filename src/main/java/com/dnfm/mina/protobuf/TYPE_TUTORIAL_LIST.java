package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import java.util.List;

public class TYPE_TUTORIAL_LIST {
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 1,
      required = false
   )
   public Integer type;
   @Protobuf(
      order = 2
   )
   public List<PT_TUTORIAL> tutorial;
}
