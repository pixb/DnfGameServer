package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;

@MessageMeta(
   module = 10011,
   cmd = 0
)
public class REQ_ENTER_CHANNEL extends Message {
   @Protobuf(
      fieldType = FieldType.STRING,
      order = 1,
      required = false
   )
   public String openid;
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 2,
      required = false
   )
   public Long charguid;
   @Protobuf(
      fieldType = FieldType.STRING,
      order = 3,
      required = false
   )
   public String authkey;
   @Protobuf(
      fieldType = FieldType.STRING,
      order = 4,
      required = false
   )
   public String version;
   @Protobuf(
      fieldType = FieldType.STRING,
      order = 5,
      required = false
   )
   public String accesstoken;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 6,
      required = false
   )
   public Integer launchinfo;
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 7,
      required = false
   )
   public Long dungeonguid;
   @Protobuf(
      order = 8,
      required = false
   )
   public PT_CLIENTINFO deviceinfo;
   @Protobuf(
      fieldType = FieldType.STRING,
      order = 9,
      required = false
   )
   public String registeredchannelid;
   @Protobuf(
      fieldType = FieldType.STRING,
      order = 10,
      required = false
   )
   public String installchannelid;
   @Protobuf(
      fieldType = FieldType.BOOL,
      order = 11,
      required = false
   )
   public Boolean isexternpackage;
   @Protobuf(
      fieldType = FieldType.STRING,
      order = 12,
      required = false
   )
   public String validusercheckcode;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 13,
      required = false
   )
   public Integer toyPlatID;
   @Protobuf(
      fieldType = FieldType.STRING,
      order = 14,
      required = false
   )
   public String countrycode;
   @Protobuf(
      fieldType = FieldType.STRING,
      order = 15,
      required = false
   )
   public String language;
   @Protobuf(
      fieldType = FieldType.STRING,
      order = 16,
      required = false
   )
   public String adid;
   @Protobuf(
      fieldType = FieldType.STRING,
      order = 17,
      required = false
   )
   public String idfv;
   @Protobuf(
      fieldType = FieldType.BOOL,
      order = 18,
      required = false
   )
   public Boolean isadult;
}
