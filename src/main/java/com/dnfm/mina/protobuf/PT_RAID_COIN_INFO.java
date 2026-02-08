package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import java.util.List;

public class PT_RAID_COIN_INFO {
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 1,
      required = false
   )
   public Integer count;
   @Protobuf(
      order = 2
   )
   public List<PT_RAID_PRIVATE_COIN_INFO> privateinfo;
}
