package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;
import java.util.List;

@MessageMeta(
   module = 25100,
   cmd = 1
)
public class RES_GM_COMMAND extends Message {
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 1,
      required = false
   )
   public Integer error;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 2,
      required = false
   )
   public Integer type;
   @Protobuf(
      order = 3
   )
   public List<PT_GM_COMMAND_ARG> list;
   @Protobuf(
      fieldType = FieldType.STRING,
      order = 4,
      required = false
   )
   public String time;
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 5,
      required = false
   )
   public Long gguid;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 6,
      required = false
   )
   public Integer level;
   @Protobuf(
      fieldType = FieldType.INT64,
      order = 7,
      required = false
   )
   public Long expiretime;
   @Protobuf(
      order = 8
   )
   public List<PT_ITEM> items;
}
