package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;
import java.util.List;

@MessageMeta(
   module = 10130,
   cmd = 0
)
public class REQ_HIDDEN_CHATTING_DELETE extends Message {
   @Protobuf(
      order = 1
   )
   public List<PT_HIDDEN_CHATTING> chatting;
}
