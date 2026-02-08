package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;
import java.util.List;

@MessageMeta(
   module = 32006,
   cmd = 1
)
public class RES_ROOM_USER_INFO extends Message {
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 1,
      required = false
   )
   public Integer error;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 2,
      required = false
   )
   public Integer channel;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 3,
      required = false
   )
   public Integer count;
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 4,
      required = false
   )
   public Long gchatguid;
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 5,
      required = false
   )
   public Long guid;
   @Protobuf(
      fieldType = FieldType.STRING,
      order = 6,
      required = false
   )
   public String ip;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 7,
      required = false
   )
   public Integer port;
   @Protobuf(
      fieldType = FieldType.ENUM,
      order = 8,
      required = false
   )
   public ENUM_ROOM_TYPE.T type;
   @Protobuf(
      fieldType = FieldType.INT64,
      order = 9,
      required = false
   )
   public Long date;
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 10,
      required = false
   )
   public Long leader;
   @Protobuf(
      order = 11
   )
   public List<PT_ROOM_USER_INFO> users;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 12,
      required = false
   )
   public Integer world;
}
