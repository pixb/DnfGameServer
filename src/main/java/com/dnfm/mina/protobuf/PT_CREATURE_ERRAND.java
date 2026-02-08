package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import java.util.List;

public class PT_CREATURE_ERRAND {
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 1,
      required = false
   )
   public Integer level;
   @Protobuf(
      order = 2,
      required = false
   )
   public PT_ERRAND_TODAY todayinfo;
   @Protobuf(
      order = 3
   )
   public List<PT_ERRAND_CLEAR> clearlist;
   @Protobuf(
      order = 4
   )
   public List<PT_ERRAND_INFO> slotlist;
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 5,
      required = false
   )
   public Long lastresettime;
}
