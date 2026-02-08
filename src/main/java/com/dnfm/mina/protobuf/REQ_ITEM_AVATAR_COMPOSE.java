package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;
import java.util.List;

@MessageMeta(
   module = 14021,
   cmd = 0
)
public class REQ_ITEM_AVATAR_COMPOSE extends Message {
   @Protobuf(
      order = 1
   )
   public List<PT_AVATAR_GUID> guids;
}
