package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;

@MessageMeta(
   module = 26003,
   cmd = 0
)
public class REQ_LOADING_PROGRESS extends Message {
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 1,
      required = false
   )
   public Long matchingguid;
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 2,
      required = false
   )
   public Long charguid;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 3,
      required = false
   )
   public Integer value;
}
