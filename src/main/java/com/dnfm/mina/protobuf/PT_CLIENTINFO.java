package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

public class PT_CLIENTINFO {
   @Protobuf(
      fieldType = FieldType.UINT32,
      order = 1,
      required = false
   )
   public Integer platID;
   @Protobuf(
      fieldType = FieldType.STRING,
      order = 2,
      required = false
   )
   public String deviceSoft;
   @Protobuf(
      fieldType = FieldType.STRING,
      order = 3,
      required = false
   )
   public String deviceHard;
   @Protobuf(
      fieldType = FieldType.STRING,
      order = 4,
      required = false
   )
   public String teleOper;
   @Protobuf(
      fieldType = FieldType.STRING,
      order = 5,
      required = false
   )
   public String network;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 6,
      required = false
   )
   public Integer scrWidth;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 7,
      required = false
   )
   public Integer scrHeight;
   @Protobuf(
      fieldType = FieldType.FLOAT,
      order = 8,
      required = false
   )
   public Float density;
   @Protobuf(
      fieldType = FieldType.STRING,
      order = 9,
      required = false
   )
   public String cpu;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 10,
      required = false
   )
   public Integer memory;
   @Protobuf(
      fieldType = FieldType.STRING,
      order = 11,
      required = false
   )
   public String glRender;
   @Protobuf(
      fieldType = FieldType.STRING,
      order = 12,
      required = false
   )
   public String glVersion;
   @Protobuf(
      fieldType = FieldType.STRING,
      order = 13,
      required = false
   )
   public String deviceID;
   @Protobuf(
      fieldType = FieldType.STRING,
      order = 14,
      required = false
   )
   public String clientIP;
   @Protobuf(
      fieldType = FieldType.UINT32,
      order = 15,
      required = false
   )
   public Integer type;
   @Protobuf(
      fieldType = FieldType.STRING,
      order = 16,
      required = false
   )
   public String oAID;
   @Protobuf(
      fieldType = FieldType.ENUM,
      order = 17,
      required = false
   )
   public ENUM_CLIENT_BUILD_TYPE.T buildType;
   @Protobuf(
      fieldType = FieldType.STRING,
      order = 18,
      required = false
   )
   public String deviceLanguage;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 19,
      required = false
   )
   public Integer deviceUTC;
}
