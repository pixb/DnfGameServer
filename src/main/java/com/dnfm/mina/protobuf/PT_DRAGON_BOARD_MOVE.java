package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import java.util.List;

public class PT_DRAGON_BOARD_MOVE {
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 1,
      required = false
   )
   public Integer error;
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 2,
      required = false
   )
   public Long charguid;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 3,
      required = false
   )
   public Integer slot;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 4,
      required = false
   )
   public Integer appendage;
   @Protobuf(
      order = 5
   )
   public List<PT_DRAGON_BOARD_REWARD> rewards;
   @Protobuf(
      order = 6,
      required = false
   )
   public PT_DRAGON_BOARD_CHANCE_CARD chancecard;
}
