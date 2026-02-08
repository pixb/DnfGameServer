package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;
import java.util.List;

@MessageMeta(
   module = 14014,
   cmd = 0
)
public class REQ_ITEM_REPAIR extends Message {
   @Protobuf(
      order = 1
   )
   public List<PT_ITEM_GUID> equiplist;
   @Protobuf(
      order = 2
   )
   public List<PT_ITEM_GUID> equip;
   @Protobuf(
      order = 3
   )
   public List<PT_QUEST> quest;
}
