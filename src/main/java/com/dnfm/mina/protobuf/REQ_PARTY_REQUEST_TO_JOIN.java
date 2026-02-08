package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;

@MessageMeta(
   module = 14401,
   cmd = 0
)
public class REQ_PARTY_REQUEST_TO_JOIN extends Message {
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 1,
      required = false
   )
   public Long partyguid;
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 2,
      required = false
   )
   public Long leaderguid;
   @Protobuf(
      fieldType = FieldType.STRING,
      order = 3,
      required = false
   )
   public String version;
   @Protobuf(
      fieldType = FieldType.BOOL,
      order = 4,
      required = false
   )
   public Boolean observer;
   @Protobuf(
      fieldType = FieldType.BOOL,
      order = 5,
      required = false
   )
   public Boolean player;
}
