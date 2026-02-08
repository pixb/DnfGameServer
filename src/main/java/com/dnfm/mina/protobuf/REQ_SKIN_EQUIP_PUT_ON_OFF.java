package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;
import java.util.List;

@MessageMeta(
   module = 14095,
   cmd = 0
)
public class REQ_SKIN_EQUIP_PUT_ON_OFF extends Message {
   @Protobuf(
      order = 1
   )
   public List<PT_SKIN_EQUIP_PUT_ON_OFF> list;
}
