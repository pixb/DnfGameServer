package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;
import java.util.List;

@MessageMeta(
   module = 11234,
   cmd = 0
)
public class REQ_RAID_SYNC_NODE_VARIABLE extends Message {
   @Protobuf(
      order = 1
   )
   public List<PT_REQ_RAID_NODE_VARIABLE> symbols;
}
