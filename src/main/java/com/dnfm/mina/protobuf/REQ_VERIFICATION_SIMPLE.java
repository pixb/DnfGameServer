package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;
import java.util.List;

@MessageMeta(
   module = 22510,
   cmd = 0
)
public class REQ_VERIFICATION_SIMPLE extends Message {
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 1,
      required = false
   )
   public Long charguid;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 2,
      required = false
   )
   public Integer dungeonindex;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 3,
      required = false
   )
   public Integer mapindex;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 4,
      required = false
   )
   public Integer gameworld;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 5,
      required = false
   )
   public Integer gchannel;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 6,
      required = false
   )
   public Integer triggerpacketid;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 7
   )
   public List<Integer> error;
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 8,
      required = false
   )
   public Long verifyguid;
   @Protobuf(
      fieldType = FieldType.STRING,
      order = 9,
      required = false
   )
   public String clientIP;
   @Protobuf(
      fieldType = FieldType.BOOL,
      order = 10,
      required = false
   )
   public Boolean gmclient;
   @Protobuf(
      fieldType = FieldType.STRING,
      order = 11,
      required = false
   )
   public String clientversion;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 12,
      required = false
   )
   public Integer verifytype;
}
