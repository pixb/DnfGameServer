package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import java.util.List;

public class PT_PROHIBIT {
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 1,
      required = false
   )
   public Long target;
   @Protobuf(
      order = 2,
      required = false
   )
   public ENUM_IDIP_PROHIBIT_TYPE.T type;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 3
   )
   public List<Integer> subtype;
   @Protobuf(
      fieldType = FieldType.INT64,
      order = 4,
      required = false
   )
   public Long endtime;
   @Protobuf(
      fieldType = FieldType.STRING,
      order = 5,
      required = false
   )
   public String reason;
   @Protobuf(
      fieldType = FieldType.INT64,
      order = 6
   )
   public List<Long> times;
}
