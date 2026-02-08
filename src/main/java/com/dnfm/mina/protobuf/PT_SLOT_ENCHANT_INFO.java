package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import java.util.List;

public class PT_SLOT_ENCHANT_INFO {
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 1,
      required = false
   )
   public Integer slot;
   @Protobuf(
      order = 2
   )
   public List<PT_SLOT_ENCHANT_EXP> evalue;
}
