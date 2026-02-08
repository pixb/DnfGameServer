package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;
import java.util.List;

@MessageMeta(
   module = 12003,
   cmd = 0
)
public class REQ_SKILL_SLOT extends Message {
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 1,
      required = false
   )
   public Integer type;
   @Protobuf(
      order = 2
   )
   public List<PT_SKILL_SLOT> buff;
   @Protobuf(
      order = 3
   )
   public List<PT_SKILL_SLOT> active;
   @Protobuf(
      order = 4
   )
   public List<PT_SKILL_SLOT> combo;
   @Protobuf(
      order = 5
   )
   public List<PT_SKILL_SLOT_MATCHING> keyboardmatching;
   @Protobuf(
      order = 6
   )
   public List<PT_SKILL_SLOT_MATCHING> padmatching;
}
