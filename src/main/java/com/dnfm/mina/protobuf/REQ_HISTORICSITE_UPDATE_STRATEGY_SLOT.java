package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;
import java.util.List;

@MessageMeta(
   module = -1,
   cmd = 0
)
public class REQ_HISTORICSITE_UPDATE_STRATEGY_SLOT extends Message {
   @Protobuf(
      order = 1
   )
   public List<PT_HISTORICSITE_STRATEGY_SLOT> slots;
}
