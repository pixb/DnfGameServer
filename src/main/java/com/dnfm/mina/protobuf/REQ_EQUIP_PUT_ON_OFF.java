package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;
import java.util.List;

@MessageMeta(
   module = 14003,
   cmd = 0
)
public class REQ_EQUIP_PUT_ON_OFF extends Message {
   @Protobuf(
      order = 1
   )
   public List<PT_EQUIP_PUT_ON_OFF> equip;
   @Protobuf(
      fieldType = FieldType.BOOL,
      order = 2
   )
   public boolean autotransferemblem;
}
