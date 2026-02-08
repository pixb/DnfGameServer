package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;
import java.util.List;

@MessageMeta(
   module = 28101,
   cmd = 1
)
public class RES_MINIGAME_LOTTERY_OPEN extends Message {
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 1,
      required = false
   )
   public Integer error;
   @Protobuf(
      order = 2
   )
   public List<PACK_LOTTERY> historyvalue;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 3,
      required = false
   )
   public Integer index;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 4,
      required = false
   )
   public Integer count;
   @Protobuf(
      order = 5,
      required = false
   )
   public PT_PACK_LOTTERY_REWARD reward;
}
