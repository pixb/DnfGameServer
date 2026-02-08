package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import java.util.List;

public class RES_GUILD_BINGO_LOAD_MAP_CLEAR_REWARD_LIST {
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 1,
      required = false
   )
   public Integer error;
   @Protobuf(
      order = 2
   )
   public List<PT_GUILD_BINGO_MAP_CLEAR_REWARD_INFO> rewardlist;
   @Protobuf(
      order = 3
   )
   public List<PT_GUILD_BINGO_MAP_CLEAR_PAST_REWARD_INFO> pastrewardlist;
}
