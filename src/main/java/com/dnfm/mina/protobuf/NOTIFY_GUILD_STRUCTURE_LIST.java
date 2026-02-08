package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import java.util.List;

public class NOTIFY_GUILD_STRUCTURE_LIST {
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 1,
      required = false
   )
   public Integer error;
   @Protobuf(
      order = 2
   )
   public List<PT_GUILD_STRUCTURE_INFO> arrangestructure;
   @Protobuf(
      order = 3
   )
   public List<PT_WAREHOUSE_STRUCTURE> arrangewarehouse;
   @Protobuf(
      order = 4
   )
   public List<PT_GUILD_STRUCTURE_INFO> removestructrue;
   @Protobuf(
      order = 5
   )
   public List<PT_WAREHOUSE_STRUCTURE> removewarehouse;
}
