package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;

@MessageMeta(
   module = 18000,
   cmd = 0
)
public class REQ_USER_REPORT extends Message {
   @Protobuf(
      fieldType = FieldType.STRING,
      order = 1,
      required = false
   )
   public String picurl;
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 2,
      required = false
   )
   public Long targetguid;
   @Protobuf(
      fieldType = FieldType.STRING,
      order = 3,
      required = false
   )
   public String targetname;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 4,
      required = false
   )
   public Integer reportscene;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 5,
      required = false
   )
   public Integer reportentrance;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 6,
      required = false
   )
   public Integer reporttype;
   @Protobuf(
      fieldType = FieldType.INT64,
      order = 7,
      required = false
   )
   public Long reportsubtype;
   @Protobuf(
      fieldType = FieldType.STRING,
      order = 8,
      required = false
   )
   public String reportdesc;
   @Protobuf(
      fieldType = FieldType.STRING,
      order = 9,
      required = false
   )
   public String reportchatcontents;
   @Protobuf(
      fieldType = FieldType.STRING,
      order = 10,
      required = false
   )
   public String reportautograph;
   @Protobuf(
      fieldType = FieldType.STRING,
      order = 11,
      required = false
   )
   public String reportpicurl;
   @Protobuf(
      fieldType = FieldType.STRING,
      order = 12,
      required = false
   )
   public String reportpicurllist;
   @Protobuf(
      fieldType = FieldType.BOOL,
      order = 13,
      required = false
   )
   public Boolean blacklist;
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 14,
      required = false
   )
   public Long partyguid;
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 15,
      required = false
   )
   public Long dungeonguid;
   @Protobuf(
      fieldType = FieldType.STRING,
      order = 16,
      required = false
   )
   public String reportchatsubstance;
}
