package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;
import java.util.List;

@MessageMeta(
   module = 27073,
   cmd = 0
)
public class REQ_GUILD_EMBLEM_EQUIP extends Message {
   @Protobuf(
      order = 1
   )
   public List<PT_GUILD_SYMBOL> gsymbol;
}
