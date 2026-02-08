package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;

@MessageMeta(
   module = 27150,
   cmd = 1
)
public class RES_HISTORICSITE_SCRAMBLE_RESULT extends Message {
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 1,
      required = false
   )
   public Integer error;
   @Protobuf(
      order = 2,
      required = false
   )
   public ENUM_TEAM.T win;
   @Protobuf(
      fieldType = FieldType.INT64,
      order = 3,
      required = false
   )
   public Long endtime;
   @Protobuf(
      order = 4,
      required = false
   )
   public PT_TEAM_RESULT red;
   @Protobuf(
      order = 5,
      required = false
   )
   public PT_TEAM_RESULT blue;
   @Protobuf(
      order = 6,
      required = false
   )
   public PT_HISTORICSITE_EXCAVATION_RESULT excavation;
}
