package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import java.util.List;

public class PT_PARTY_DUNGEON_MAP {
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 1,
      required = false
   )
   public Integer guid;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 2,
      required = false
   )
   public Integer index;
   @Protobuf(
      fieldType = FieldType.FLOAT,
      order = 3,
      required = false
   )
   public Float clearTime;
   @Protobuf(
      order = 4
   )
   public List<PT_PARTY_DUNGEON_OBJECT> objects;
}
