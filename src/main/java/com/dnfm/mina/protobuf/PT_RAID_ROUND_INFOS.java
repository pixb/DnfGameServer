package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import java.util.List;

public class PT_RAID_ROUND_INFOS {
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 1,
      required = false
   )
   public Integer dungeonindex;
   @Protobuf(
      order = 2
   )
   public List<PT_CHARACTER_RAID_ROUND_INFO> roundinfos;
}
