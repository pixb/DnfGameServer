package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;
import java.util.List;

@MessageMeta(
   module = 10202,
   cmd = 1
)
public class RES_REWARD_QUEST extends Message {
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
   public Integer exp;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 3,
      required = false
   )
   public Integer level;
   @Protobuf(
      order = 4
   )
   public List<PT_QUEST_INFO> quest;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 5,
      required = false
   )
   public Integer adventureunionlevel;
   @Protobuf(
      fieldType = FieldType.INT64,
      order = 6,
      required = false
   )
   public Long adventureunionexp;
   @Protobuf(
      order = 7,
      required = false
   )
   public PT_CONTENTS_REWARD_INFO rewards;
}
