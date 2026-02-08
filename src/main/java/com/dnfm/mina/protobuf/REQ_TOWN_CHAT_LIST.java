package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;
import java.util.List;

@MessageMeta(
   module = 10125,
   cmd = 0
)
public class REQ_TOWN_CHAT_LIST extends Message {
   @Protobuf(
      order = 1
   )
   public List<PT_CHAT_SYNC> chat;
}
