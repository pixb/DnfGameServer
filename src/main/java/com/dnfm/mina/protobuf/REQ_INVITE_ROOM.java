package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;
import java.util.List;

@MessageMeta(
   module = 32003,
   cmd = 0
)
public class REQ_INVITE_ROOM extends Message {
   @Protobuf(
      order = 1
   )
   public List<PT_INVITE_ROOM_USERLIST> list;
}
