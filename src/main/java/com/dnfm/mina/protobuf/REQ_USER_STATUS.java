package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;
import java.util.List;

@MessageMeta(
   module = 10152,
   cmd = 0
)
public class REQ_USER_STATUS extends Message {
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 1
   )
   public List<Long> list;
}
