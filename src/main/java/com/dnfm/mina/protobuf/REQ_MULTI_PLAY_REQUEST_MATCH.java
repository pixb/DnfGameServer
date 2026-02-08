package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;

@MessageMeta(
   module = 13000,
   cmd = 0
)
public class REQ_MULTI_PLAY_REQUEST_MATCH extends Message {
   @Protobuf(
      fieldType = FieldType.STRING,
      order = 1,
      required = false
   )
   public String authkey;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 2,
      required = false
   )
   public Integer type;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 3,
      required = false
   )
   public Integer index;
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 4,
      required = false
   )
   public Long partyguid;
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 5,
      required = false
   )
   public Long partyleaderguid;
   @Protobuf(
      fieldType = FieldType.BOOL,
      order = 6,
      required = false
   )
   public Boolean eamplify;
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 7,
      required = false
   )
   public Long customdata;
   @Protobuf(
      fieldType = FieldType.BOOL,
      order = 8,
      required = false
   )
   public Boolean retry;
   @Protobuf(
      fieldType = FieldType.BOOL,
      order = 9,
      required = false
   )
   public Boolean iskeyboard;
}
