package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;
import java.util.List;

@MessageMeta(
   module = 10800,
   cmd = 1
)
public class RES_EVENT_LIST extends Message {
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 1,
      required = false
   )
   public Integer error;
   @Protobuf(
      order = 2
   )
   public List<PT_MAIN_EVENT_DATA> account;
   @Protobuf(
      order = 3
   )
   public List<PT_EVENT_DATA_FOR_CHARACTER> characters;
   @Protobuf(
      order = 4
   )
   public List<PT_EVENT_SCHEDULER> scheduler;
   @Protobuf(
      order = 5
   )
   public List<PT_EVENT_ON_TIME> ontimeEvents;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 6,
      required = false
   )
   public Integer page;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 7,
      required = false
   )
   public Integer maxpage;
}
