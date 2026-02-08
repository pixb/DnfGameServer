package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import java.util.List;

public class RES_GUILD_BINGO_LOAD_DETAIL {
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
   public Integer maplevel;
   @Protobuf(
      order = 3
   )
   public List<PT_GUILD_BINGO_SQUARE_INFO> squarelist;
   @Protobuf(
      order = 4
   )
   public List<PT_GUILD_BINGO_REWARD_INFO> rewardlist;
   @Protobuf(
      order = 5
   )
   public List<PT_GUILD_BINGO_UNDIS_CLOSED_INFO> undisclosed;
   @Protobuf(
      fieldType = FieldType.BOOL,
      order = 6,
      required = false
   )
   public Boolean iscompleted;
}
