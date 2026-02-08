package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;

@MessageMeta(
   module = -1,
   cmd = 0
)
public class REQ_QUERY_RANKING_BY_SCORE extends Message {
   @Protobuf(
      fieldType = FieldType.INT64,
      order = 1,
      required = false
   )
   public Long score;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 2,
      required = false
   )
   public Integer type;
}
