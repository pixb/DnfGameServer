package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

public class IDIP_NOTICE_INFO {
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 1,
      required = false
   )
   public Integer type;
   @Protobuf(
      fieldType = FieldType.INT64,
      order = 2,
      required = false
   )
   public Long starttime;
   @Protobuf(
      fieldType = FieldType.INT64,
      order = 3,
      required = false
   )
   public Long endtime;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 4,
      required = false
   )
   public Integer frequency;
   @Protobuf(
      fieldType = FieldType.STRING,
      order = 5,
      required = false
   )
   public String content;
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 6,
      required = false
   )
   public Long guid;
   @Protobuf(
      fieldType = FieldType.STRING,
      order = 7,
      required = false
   )
   public String title;
}
