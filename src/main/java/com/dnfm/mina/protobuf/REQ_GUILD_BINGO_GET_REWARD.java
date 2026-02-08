package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import java.util.List;

public class REQ_GUILD_BINGO_GET_REWARD {
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 1,
      required = false
   )
   public Integer maplevel;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 2
   )
   public List<Integer> rewardindices;
}
