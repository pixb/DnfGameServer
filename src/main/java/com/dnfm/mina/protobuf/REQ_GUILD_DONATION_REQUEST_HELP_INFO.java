package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;

@MessageMeta(
   module = 10968,
   cmd = 0
)
public class REQ_GUILD_DONATION_REQUEST_HELP_INFO extends Message {
   @Protobuf(
      fieldType = FieldType.INT64,
      order = 1,
      required = false
   )
   public Long date;
}
