package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

public class PT_REQ_RAID_NODE_VARIABLE {
   @Protobuf(
      fieldType = FieldType.STRING,
      order = 1,
      required = false
   )
   public String nodename;
   @Protobuf(
      fieldType = FieldType.STRING,
      order = 2,
      required = false
   )
   public String name;
}
