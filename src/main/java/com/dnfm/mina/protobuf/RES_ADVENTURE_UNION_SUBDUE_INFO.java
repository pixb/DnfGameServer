package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;
import java.util.List;

@MessageMeta(
   module = 17206,
   cmd = 1
)
public class RES_ADVENTURE_UNION_SUBDUE_INFO extends Message {
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 1,
      required = false
   )
   public Integer error;
   @Protobuf(
      order = 2
   )
   public List<PT_SUBDUE_INFO> list;
   @Protobuf(
      order = 3
   )
   public List<PT_CHARGUID_FATIGUE> fatigues;
   @Protobuf(
      order = 4
   )
   public List<PT_CHARGUID_TICKET> tickets;
   @Protobuf(
      order = 5
   )
   public List<PT_CHARGUID_ENTRANCE_ITEM> entranceitems;
}
