package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import java.util.List;

public class PT_CHAMPION_INFO {
   @Protobuf(
      fieldType = FieldType.BOOL,
      order = 1,
      required = false
   )
   public Boolean appearance;
   @Protobuf(
      order = 2
   )
   public List<PT_CHAMPION_STAGE_INFO> detail;
}
