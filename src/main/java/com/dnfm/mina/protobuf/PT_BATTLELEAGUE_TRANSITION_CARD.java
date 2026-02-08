package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import java.util.List;

public class PT_BATTLELEAGUE_TRANSITION_CARD {
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 1,
      required = false
   )
   public Long charguid;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 2
   )
   public List<Integer> items;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 3
   )
   public List<Integer> buffs;
   @Protobuf(
      order = 4
   )
   public List<PT_BATTLELEAGUE_BUFF> buff;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 5,
      required = false
   )
   public Integer pvepoint;
   @Protobuf(
      order = 6
   )
   public List<PT_BATTLELEAGUE_REWARD_INFO> pverewards;
}
