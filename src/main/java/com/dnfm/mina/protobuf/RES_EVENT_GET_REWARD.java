package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;
import java.util.List;

@MessageMeta(
   module = 10802,
   cmd = 1
)
public class RES_EVENT_GET_REWARD extends Message {
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
   public Integer index;
   @Protobuf(
      order = 3
   )
   public List<PT_EVENT_REWARD_INFO> subindexes;
   @Protobuf(
      order = 4,
      required = false
   )
   public PT_CONTENTS_REWARD_INFO rewardinfo;
   @Protobuf(
      order = 5
   )
   public List<PT_MONEY_ITEM> currency;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 6,
      required = false
   )
   public Integer fatigue;
}
