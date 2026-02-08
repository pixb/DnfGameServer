package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;
import java.util.List;

@MessageMeta(
   module = -1,
   cmd = 1
)
public class RES_GUILD_WAREHOUSE_LIST extends Message {
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
   public PT_GUILD_WAREHOUSE_PARAMETER parameter;
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
