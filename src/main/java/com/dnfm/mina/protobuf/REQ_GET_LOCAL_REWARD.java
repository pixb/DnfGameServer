package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;
import java.util.List;

@MessageMeta(
   module = 11047,
   cmd = 0
)
public class REQ_GET_LOCAL_REWARD extends Message {
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 1
   )
   public List<Integer> rewards;
}
