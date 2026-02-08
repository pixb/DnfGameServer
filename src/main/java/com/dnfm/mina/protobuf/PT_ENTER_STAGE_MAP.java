package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import java.util.List;

public class PT_ENTER_STAGE_MAP {
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 1,
      required = false
   )
   public Integer guid;
   @Protobuf(
      order = 2
   )
   public List<PT_ENTER_STAGE_OBJECT> objects;
}
