package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import java.util.Map;

public class PT_CREATURE_COMMUNION {
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 1,
      required = false
   )
   public Integer level;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 2,
      required = false
   )
   public Integer selectedslot;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 3,
      required = false
   )
   public Integer extentionslot;
   @Protobuf(
      fieldType = FieldType.MAP,
      order = 4,
      required = false
   )
   public Map<Integer, PT_CREATURE_LEARN_SKILL_INFOS> slotInfos;
}
