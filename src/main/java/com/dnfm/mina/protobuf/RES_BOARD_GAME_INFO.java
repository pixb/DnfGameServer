package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;
import java.util.List;

@MessageMeta(
   module = 19100,
   cmd = 1
)
public class RES_BOARD_GAME_INFO extends Message {
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
      fieldType = FieldType.INT32,
      order = 3,
      required = false
   )
   public Integer matchtype;
   @Protobuf(
      order = 4,
      required = false
   )
   public ENUM_BOARD_TYPE.T boardtype;
   @Protobuf(
      order = 5
   )
   public List<PT_BOARD_USER_MINIMUM_INFO> users;
   @Protobuf(
      order = 6,
      required = false
   )
   public PT_BOARD_GAME_INFO boardinfo;
}
