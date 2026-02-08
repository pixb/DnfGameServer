package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import java.util.List;

public class PT_ADVENTURE_UNION_EXPEDITION {
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 1,
      required = false
   )
   public Integer area;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 2,
      required = false
   )
   public Integer time;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 3,
      required = false
   )
   public Integer consumefatigue;
   @Protobuf(
      fieldType = FieldType.INT64,
      order = 4,
      required = false
   )
   public Long regdate;
   @Protobuf(
      order = 5
   )
   public List<PT_CHARGUID_FATIGUE> characters;
   @Protobuf(
      order = 6
   )
   public List<PT_ADVENTURE_UNION_EXPEDITION_REWARDS> rewards;
}
