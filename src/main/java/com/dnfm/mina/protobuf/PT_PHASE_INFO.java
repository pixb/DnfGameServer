package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import java.util.List;

public class PT_PHASE_INFO {
   @Protobuf(
      fieldType = FieldType.INT64,
      order = 1,
      required = false
   )
   public Long starttime;
   @Protobuf(
      fieldType = FieldType.INT64,
      order = 2,
      required = false
   )
   public Long playtime;
   @Protobuf(
      order = 3,
      required = false
   )
   public ENUM_RAID_PHASE.T status;
   @Protobuf(
      order = 4
   )
   public List<PT_RAID_NODE_INFO> supernodes;
}
