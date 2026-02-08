package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;
import java.util.List;

@MessageMeta(
   module = 27155,
   cmd = 1
)
public class RES_HISTORICSITE_RESULT extends Message {
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
   public Integer win;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 3,
      required = false
   )
   public Integer totalnum;
   @Protobuf(
      order = 4,
      required = false
   )
   public PT_HISTORICSITE_POINT redpoint;
   @Protobuf(
      order = 5,
      required = false
   )
   public PT_HISTORICSITE_POINT bluepoint;
   @Protobuf(
      order = 6
   )
   public List<PT_MEMBER_RESULT> list;
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 7,
      required = false
   )
   public Long targetguid;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 8,
      required = false
   )
   public Integer targetlevel;
   @Protobuf(
      fieldType = FieldType.STRING,
      order = 9,
      required = false
   )
   public String targetname;
   @Protobuf(
      order = 10
   )
   public List<PT_GUILD_SYMBOL> gsymbol;
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 11
   )
   public List<Long> earlybird;
}
