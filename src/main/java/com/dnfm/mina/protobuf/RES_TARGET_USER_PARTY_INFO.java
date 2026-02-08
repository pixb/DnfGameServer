package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;
import java.util.List;

@MessageMeta(
   module = 13548,
   cmd = 1
)
public class RES_TARGET_USER_PARTY_INFO extends Message {
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
   public String ip;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 3,
      required = false
   )
   public Integer port;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 4,
      required = false
   )
   public Integer publictype;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 5,
      required = false
   )
   public Integer area;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 6,
      required = false
   )
   public Integer subtype;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 7,
      required = false
   )
   public Integer stageindex;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 8,
      required = false
   )
   public Integer dungeonindex;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 9,
      required = false
   )
   public Integer grade;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 10,
      required = false
   )
   public Integer level;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 11,
      required = false
   )
   public Integer minlevel;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 12,
      required = false
   )
   public Integer maxlevel;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 13,
      required = false
   )
   public Integer channel;
   @Protobuf(
      fieldType = FieldType.STRING,
      order = 14,
      required = false
   )
   public String partyname;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 15,
      required = false
   )
   public Integer password;
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 16,
      required = false
   )
   public Long partyguid;
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 17,
      required = false
   )
   public Long partyleaderguid;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 18,
      required = false
   )
   public Integer world;
   @Protobuf(
      order = 19
   )
   public List<PT_GROUP_MEMBER> users;
}
