package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;
import java.util.List;

@MessageMeta(
   module = 29012,
   cmd = 0
)
public class REQ_COMMUNITY_GIFT_RECEIPT extends Message {
   @Protobuf(
      order = 1
   )
   public List<PT_COMMUNITY_GIFT_GUID_INFO> guids;
}
