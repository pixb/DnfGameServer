package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;
import java.util.List;

@MessageMeta(
   module = 27172,
   cmd = 1
)
public class RES_HISTORICSITE_CAPTURE extends Message {
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 1,
      required = false
   )
   public Integer error;
   @Protobuf(
      order = 2
   )
   public List<PT_HISTORICSITE_CAPTURE> captures;
   @Protobuf(
      order = 3
   )
   public List<PT_HISTORICSITE_BATTLEFIELD_CAPTURE> battlefieldcaptures;
   @Protobuf(
      order = 4
   )
   public List<PT_HISTORICSITE_ALTAR_CAPTURE> altarcaptures;
}
