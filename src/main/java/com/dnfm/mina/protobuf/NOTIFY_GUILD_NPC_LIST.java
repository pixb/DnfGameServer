package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import java.util.List;

public class NOTIFY_GUILD_NPC_LIST {
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 1,
      required = false
   )
   public Integer error;
   @Protobuf(
      order = 2,
      required = false
   )
   public PT_GUILD_NPC_INFO hirednpc;
   @Protobuf(
      order = 3
   )
   public List<PT_WAREHOUSE_NPC> addnpc;
   @Protobuf(
      order = 4
   )
   public List<PT_WAREHOUSE_NPC> removenpc;
}
