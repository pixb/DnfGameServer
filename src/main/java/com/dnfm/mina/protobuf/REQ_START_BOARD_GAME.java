package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;

@MessageMeta(
   module = 19101,
   cmd = 0
)
public class REQ_START_BOARD_GAME extends Message {
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 1,
      required = false
   )
   public Long boardguid;
   @Protobuf(
      order = 2,
      required = false
   )
   public ENUM_BOARD_TYPE.T boardtype;
}
