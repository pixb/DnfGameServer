package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;
import java.util.List;

@MessageMeta(
   module = 19102,
   cmd = 0
)
public class REQ_BOARD_GAME_RESULT extends Message {
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
   @Protobuf(
      order = 3
   )
   public List<PT_USER_BOARD_GAME_RESULT> results;
   @Protobuf(
      fieldType = FieldType.ENUM,
      order = 4,
      required = false
   )
   public ENUM_BOARD_RESULT_TYPE.T type;
}
