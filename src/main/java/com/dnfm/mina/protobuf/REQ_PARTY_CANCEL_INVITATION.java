package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;
import java.util.List;

@MessageMeta(
   module = 14426,
   cmd = 0
)
public class REQ_PARTY_CANCEL_INVITATION extends Message {
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 1,
      required = false
   )
   public Long partyguid;
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 2
   )
   public List<Long> targetguids;
}
