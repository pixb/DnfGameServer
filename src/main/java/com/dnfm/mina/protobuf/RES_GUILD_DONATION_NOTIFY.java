package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;

@MessageMeta(
   module = 10972,
   cmd = 1
)
public class RES_GUILD_DONATION_NOTIFY extends Message {
   @Protobuf(
      order = 1,
      required = false
   )
   public PT_GUILD_DONATION_HELP_INFO requestinfo;
   @Protobuf(
      order = 2,
      required = false
   )
   public PT_GUILD_DONATION_HELP_INFO responseinfo;
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 3,
      required = false
   )
   public Long delrequestguid;
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 4,
      required = false
   )
   public Long delresponseguid;
}
