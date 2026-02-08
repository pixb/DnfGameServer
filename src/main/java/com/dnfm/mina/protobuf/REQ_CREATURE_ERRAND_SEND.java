package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;
import java.util.List;

@MessageMeta(
   module = 17301,
   cmd = 0
)
public class REQ_CREATURE_ERRAND_SEND extends Message {
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 1
   )
   public List<Integer> errandid;
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 2
   )
   public List<Long> list;
}
