package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import java.util.List;

public class PT_GUILD_BINGO_MAP_CLEAR_PAST_REWARD_INFO {
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 1,
      required = false
   )
   public Integer maplevel;
   @Protobuf(
      order = 2
   )
   public List<PT_GUILD_BINGO_REWARD_ITEM> rewarditems;
   @Protobuf(
      fieldType = FieldType.BOOL,
      order = 3,
      required = false
   )
   public Boolean received;
}
