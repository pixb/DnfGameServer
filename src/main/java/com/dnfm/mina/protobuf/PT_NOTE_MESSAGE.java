package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import java.util.List;

public class PT_NOTE_MESSAGE {
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 1,
      required = false
   )
   public Long charguid;
   @Protobuf(
      fieldType = FieldType.STRING,
      order = 2,
      required = false
   )
   public String name;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 3,
      required = false
   )
   public Integer job;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 4,
      required = false
   )
   public Integer growtype;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 5,
      required = false
   )
   public Integer level;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 6,
      required = false
   )
   public Integer count;
   @Protobuf(
      fieldType = FieldType.INT64,
      order = 7,
      required = false
   )
   public Long date;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 8,
      required = false
   )
   public Integer online;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 9,
      required = false
   )
   public Integer creditscore;
   @Protobuf(
      order = 10,
      required = false
   )
   public PT_SKIN_CHAT_INFO skinchatinfo;
   @Protobuf(
      order = 11
   )
   public List<PT_CHAT> list;
}
