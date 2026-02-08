package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;
import java.util.List;

@MessageMeta(
   module = 27422,
   cmd = 1
)
public class RES_GUILD_AGIT_MINIGAME_FINISH extends Message {
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 1,
      required = false
   )
   public Integer error;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 2,
      required = false
   )
   public Integer myrank;
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 3,
      required = false
   )
   public Long myscore;
   @Protobuf(
      order = 4,
      required = false
   )
   public PT_GUILD_INVENTORY_LIST rewardinfo;
   @Protobuf(
      order = 5
   )
   public List<PT_RANKING_GROUP> rankinginfos;
   @Protobuf(
      order = 6,
      required = false
   )
   public ENUM_GUILD_AGIT_MINIGAME_TYPE.T type;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 7,
      required = false
   )
   public Integer myguildrank;
   @Protobuf(
      order = 8,
      required = false
   )
   public ENUM_GUILD_AGIT_FINISH_TYPE.T finishtype;
   @Protobuf(
      order = 9,
      required = false
   )
   public PT_GUILD_INVENTORY_LIST memberrewardinfo;
}
