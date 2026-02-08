package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;

@MessageMeta(
   module = 27422,
   cmd = 0
)
public class REQ_GUILD_AGIT_MINIGAME_FINISH extends Message {
   @Protobuf(
      order = 1,
      required = false
   )
   public ENUM_GUILD_AGIT_MINIGAME_TYPE.T type;
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 2,
      required = false
   )
   public Long score;
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 3,
      required = false
   )
   public Long distance;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 4,
      required = false
   )
   public Integer mineindex;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 5,
      required = false
   )
   public Integer minrank;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 6,
      required = false
   )
   public Integer maxrank;
   @Protobuf(
      order = 7,
      required = false
   )
   public ENUM_GUILD_AGIT_FINISH_TYPE.T finishtype;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 8,
      required = false
   )
   public Integer areaindex;
}
