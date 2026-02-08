package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;

@MessageMeta(
   module = 10000,
   cmd = 0
)
public class REQ_LOGIN extends Message {
   @Protobuf(
      fieldType = FieldType.STRING,
      order = 1,
      required = false
   )
   public String openid;
   @Protobuf(
      fieldType = FieldType.UINT32,
      order = 2,
      required = false
   )
   public Integer type;
   @Protobuf(
      fieldType = FieldType.STRING,
      order = 3,
      required = false
   )
   public String token;
   @Protobuf(
      fieldType = FieldType.UINT32,
      order = 4,
      required = false
   )
   public Integer platID;
   @Protobuf(
      fieldType = FieldType.STRING,
      order = 5,
      required = false
   )
   public String clientIP;
   @Protobuf(
      fieldType = FieldType.STRING,
      order = 6,
      required = false
   )
   public String version;
   @Protobuf(
      fieldType = FieldType.STRING,
      order = 7,
      required = false
   )
   public String friendopenid;
   @Protobuf(
      fieldType = FieldType.UINT32,
      order = 8,
      required = false
   )
   public Integer cancelunregist;
   @Protobuf(
      fieldType = FieldType.STRING,
      order = 9,
      required = false
   )
   public String countrycode;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 10,
      required = false
   )
   public Integer toyplatid;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 11,
      required = false
   )
   public Integer agetype;
}
