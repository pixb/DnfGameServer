package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;
import java.util.List;

@MessageMeta(
   module = 27158,
   cmd = 1
)
public class RES_HISTORICSITE_ROOM_LIST extends Message {
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 1,
      required = false
   )
   public Integer error;
   @Protobuf(
      order = 2
   )
   public List<PT_HISTORICSITE_ROOM_INFO> list;
   @Protobuf(
      order = 3
   )
   public List<PT_HISTORICSITE_ALTAR_ROOM_INFO> altars;
   @Protobuf(
      order = 4
   )
   public List<PT_HISTORICSITE_ROOM_INFO> excavations;
}
