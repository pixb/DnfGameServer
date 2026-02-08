package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;
import java.util.List;

@MessageMeta(
   module = 10022,
   cmd = 0
)
public class REQ_SERVER_NOTI_ACK extends Message {
   @Protobuf(
      order = 1
   )
   public List<PT_PROTOCOL_TRANSACTION> request;
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 2,
      required = false
   )
   public Long charguid;
}
