package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

public class PT_MENTOR_GET_ALL_GROWTH_REWARD {
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 1,
      required = false
   )
   public Long followerguid;
   @Protobuf(
      fieldType = FieldType.STRING,
      order = 2,
      required = false
   )
   public String seqlevelgiftmaster;
   @Protobuf(
      order = 3,
      required = false
   )
   public PT_MENTOR_GET_ALL_GROWTH_REWARD_QUEST questreward;
}
