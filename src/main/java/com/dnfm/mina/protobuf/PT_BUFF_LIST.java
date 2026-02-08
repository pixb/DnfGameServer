package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import java.util.List;

public class PT_BUFF_LIST {
   @Protobuf(
      fieldType = FieldType.INT64,
      order = 1,
      required = false
   )
   public Long time;
   @Protobuf(
      order = 2
   )
   public List<PT_SYSTEM_BUFF_APPENDAGE> appendages;
}
