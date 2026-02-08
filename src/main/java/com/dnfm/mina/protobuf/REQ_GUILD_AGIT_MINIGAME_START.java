package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;

@MessageMeta(
   module = 27421,
   cmd = 0
)
public class REQ_GUILD_AGIT_MINIGAME_START extends Message {
   @Protobuf(
      fieldType = FieldType.ENUM,
      order = 1,
      required = false
   )
   public ENUM_GUILD_AGIT_MINIGAME_TYPE.T type;
   @Protobuf(
      order = 2,
      required = false
   )
   public PT_ITEM guilditem;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 3,
      required = false
   )
   public Integer areaindex;
}
