package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;
import java.util.List;

@MessageMeta(
   module = 14090,
   cmd = 1
)
public class RES_CREATURE_SKILL_LEARN extends Message {
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 1,
      required = false
   )
   public Integer error;
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 2,
      required = false
   )
   public Long guid;
   @Protobuf(
      order = 3,
      required = false
   )
   public PT_REMOVEITEMS removeitems;
   @Protobuf(
      order = 4
   )
   public List<PT_CREATURE_SKILL> skill;
}
