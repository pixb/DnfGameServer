package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;

@MessageMeta(
   module = -1,
   cmd = 1
)
public class RES_ADVENTURE_AUTO_SEARCH_REWARD extends Message {
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
   public Integer remaincount;
   @Protobuf(
      order = 3,
      required = false
   )
   public PT_CONTENTS_REWARD_INFO info;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 4,
      required = false
   )
   public Integer adventureunionlevel;
   @Protobuf(
      fieldType = FieldType.INT64,
      order = 5,
      required = false
   )
   public Long adventureunionexp;
}
