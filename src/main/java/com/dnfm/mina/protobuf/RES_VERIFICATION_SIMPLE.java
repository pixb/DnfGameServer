package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;
import java.util.List;

@MessageMeta(
   module = 22510,
   cmd = 1
)
public class RES_VERIFICATION_SIMPLE extends Message {
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 1,
      required = false
   )
   public Integer error;
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 2,
      required = false
   )
   public Long charguid;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 3,
      required = false
   )
   public Integer dungeonindex;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 4,
      required = false
   )
   public Integer mapindex;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 5,
      required = false
   )
   public Integer gameworld;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 6,
      required = false
   )
   public Integer gchannel;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 7,
      required = false
   )
   public Integer packetid;
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
      fieldType = FieldType.BYTES,
      order = 10,
      required = false
   )
   public byte[] packetmsg;
   @Protobuf(
      order = 11
   )
   public List<PT_USER_INFO_VERIFICATION> characinfos;
   @Protobuf(
      order = 12,
      required = false
   )
   public PT_MAP_INFO map;
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 13,
      required = false
   )
   public Long featureswitch;
   @Protobuf(
      fieldType = FieldType.BYTES,
      order = 14,
      required = false
   )
   public byte[] dungeontypeswitch;
}
