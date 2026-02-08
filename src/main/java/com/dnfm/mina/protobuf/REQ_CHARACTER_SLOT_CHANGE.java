package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;
import java.util.List;

@MessageMeta(
   module = 13556,
   cmd = 0
)
public class REQ_CHARACTER_SLOT_CHANGE extends Message {
   @Protobuf(
      order = 1
   )
   public List<PT_CHARACTER_SLOT_INFO> characlist;
}
