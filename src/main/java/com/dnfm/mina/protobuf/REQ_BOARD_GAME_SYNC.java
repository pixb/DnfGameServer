package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;

@MessageMeta(
   module = 19012,
   cmd = 0
)
public class REQ_BOARD_GAME_SYNC extends Message {
   @Protobuf(
      fieldType = FieldType.ENUM,
      order = 1,
      required = false
   )
   public ENUM_BOARD_GAME_SYNC_TYPE.T type;
}
