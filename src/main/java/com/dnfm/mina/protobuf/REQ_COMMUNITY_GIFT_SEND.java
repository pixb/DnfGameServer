package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;

@MessageMeta(
   module = 29000,
   cmd = 0
)
public class REQ_COMMUNITY_GIFT_SEND extends Message {
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 1,
      required = false
   )
   public Long targetguid;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 2,
      required = false
   )
   public Integer goodsid;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 3,
      required = false
   )
   public Integer count;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 4,
      required = false
   )
   public Integer buycount;
   @Protobuf(
      fieldType = FieldType.STRING,
      order = 5,
      required = false
   )
   public String msg;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 6,
      required = false
   )
   public Integer packagingindex;
   @Protobuf(
      fieldType = FieldType.STRING,
      order = 7,
      required = false
   )
   public String openkey;
   @Protobuf(
      fieldType = FieldType.STRING,
      order = 8,
      required = false
   )
   public String paytoken;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 9,
      required = false
   )
   public Integer itemindex;
}
