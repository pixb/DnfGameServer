package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import java.util.List;

public class NOTIFY_BATTLELEAGUE_OTHER_PVE_RESULT {
   @Protobuf(
      order = 1
   )
   public List<PT_BATTLELEAGUE_PVE_RECORD> battleleaguepverecord;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 2,
      required = false
   )
   public Integer hp;
}
