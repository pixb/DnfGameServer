package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;

@MessageMeta(
   module = 19101,
   cmd = 1
)
public class RES_START_BOARD_GAME extends Message {
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
   public Long boardguid;
   @Protobuf(
      order = 3,
      required = false
   )
   public ENUM_BOARD_TYPE.T boardtype;
   @Protobuf(
      fieldType = FieldType.INT64,
      order = 4,
      required = false
   )
   public Long starttime;
}
