package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;
import java.util.List;

@MessageMeta(
   module = 10112,
   cmd = 1
)
public class RES_NOTE_MESSENGER_ADD_USER extends Message {
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 1,
      required = false
   )
   public Integer error;
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 2,
      required = false
   )
   public Long charguid;
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 3
   )
   public List<Long> deleteguids;
   @Protobuf(
      fieldType = FieldType.STRING,
      order = 4,
      required = false
   )
   public String name;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 5,
      required = false
   )
   public Integer job;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 6,
      required = false
   )
   public Integer growtype;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 7,
      required = false
   )
   public Integer level;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 8,
      required = false
   )
   public Integer count;
   @Protobuf(
      fieldType = FieldType.INT64,
      order = 9,
      required = false
   )
   public Long date;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 10,
      required = false
   )
   public Integer creditscore;
   @Protobuf(
      order = 11,
      required = false
   )
   public PT_SKIN_CHAT_INFO skinchatinfo;
   @Protobuf(
      order = 12
   )
   public List<PT_CHAT> list;
}
