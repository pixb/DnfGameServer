package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import java.util.List;

public class NOTIFY_GUILD_WAREHOUSE_LOAD {
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
   public ENUM_GUILD_WAREHOUSE_TYPES.T notitype;
   @Protobuf(
      order = 3,
      required = false
   )
   public PT_GUILD_INVENTORY_LIST inventory;
   @Protobuf(
      order = 4
   )
   public List<PT_WAREHOUSE_STRUCTURE> structurelist;
   @Protobuf(
      order = 5
   )
   public List<PT_WAREHOUSE_NPC> npclist;
}
