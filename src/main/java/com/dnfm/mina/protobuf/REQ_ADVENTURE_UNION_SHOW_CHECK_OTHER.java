package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;

@MessageMeta(
   module = 17218,
   cmd = 0
)
public class REQ_ADVENTURE_UNION_SHOW_CHECK_OTHER extends Message {
   @Protobuf(
      fieldType = FieldType.BOOL,
      order = 1,
      required = false
   )
   public Boolean isshowother;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 2,
      required = false
   )
   public Integer tabtype;
}
