package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;
import java.util.List;

@MessageMeta(
   module = 10000,
   cmd = 1
)
public class RES_LOGIN extends Message {
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 1,
      required = false
   )
   public Integer error;
   @Protobuf(
      fieldType = FieldType.STRING,
      order = 2,
      required = false
   )
   public String authkey;
   @Protobuf(
      fieldType = FieldType.STRING,
      order = 3,
      required = false
   )
   public String accountkey;
   @Protobuf(
      fieldType = FieldType.BOOL,
      order = 4,
      required = false
   )
   public Boolean encrypt;
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 5,
      required = false
   )
   public Long servertime;
   @Protobuf(
      fieldType = FieldType.STRING,
      order = 6,
      required = false
   )
   public String localtime;
   @Protobuf(
      fieldType = FieldType.UINT32,
      order = 7,
      required = false
   )
   public Integer authority;
   @Protobuf(
      fieldType = FieldType.STRING,
      order = 8,
      required = false
   )
   public String key;
   @Protobuf(
      order = 9
   )
   public List<PT_CHANNEL_INFO> channel;
   @Protobuf(
      order = 10,
      required = false
   )
   public PT_INTRUDE_INFO intrudeinfo;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 11,
      required = false
   )
   public Integer worldid;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 12
   )
   public List<Integer> seeds;
}
