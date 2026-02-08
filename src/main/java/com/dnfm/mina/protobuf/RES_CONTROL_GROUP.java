package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;
import java.util.List;

@MessageMeta(
   module = 13025,
   cmd = 1
)
public class RES_CONTROL_GROUP extends Message {
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
   public Integer world;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 3,
      required = false
   )
   public Integer channel;
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
      fieldType = FieldType.INT32,
      order = 8,
      required = false
   )
   public Integer type;
   @Protobuf(
      fieldType = FieldType.STRING,
      order = 9,
      required = false
   )
   public String name;
   @Protobuf(
      fieldType = FieldType.STRING,
      order = 10,
      required = false
   )
   public String gname;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 11,
      required = false
   )
   public Integer equipscore;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 12,
      required = false
   )
   public Integer growtype;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 13,
      required = false
   )
   public Integer secondgrowtype;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 14,
      required = false
   )
   public Integer job;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 15,
      required = false
   )
   public Integer level;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 16,
      required = false
   )
   public Integer minlevel;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 17,
      required = false
   )
   public Integer maxlevel;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 18,
      required = false
   )
   public Integer town;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 19,
      required = false
   )
   public Integer area;
   @Protobuf(
      fieldType = FieldType.BOOL,
      order = 20,
      required = false
   )
   public Boolean friend;
   @Protobuf(
      fieldType = FieldType.BOOL,
      order = 21,
      required = false
   )
   public Boolean guild;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 22,
      required = false
   )
   public Integer creditscore;
   @Protobuf(
      order = 23
   )
   public List<PT_GROUP_MEMBER> list;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 24,
      required = false
   )
   public Integer state;
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 25,
      required = false
   )
   public Long targetguid;
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 26,
      required = false
   )
   public Long charguid;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 27,
      required = false
   )
   public Integer dungeonindex;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 28,
      required = false
   )
   public Integer matchtype;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 29,
      required = false
   )
   public Integer subtype;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 30,
      required = false
   )
   public Integer max;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 31,
      required = false
   )
   public Integer contentlockstate;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 32,
      required = false
   )
   public Integer password;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 33,
      required = false
   )
   public Integer stageindex;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 34,
      required = false
   )
   public Integer grade;
   @Protobuf(
      fieldType = FieldType.STRING,
      order = 35,
      required = false
   )
   public String partyname;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 36,
      required = false
   )
   public Integer publictype;
   @Protobuf(
      order = 37
   )
   public List<PT_GROUP_MEMBER> users;
   @Protobuf(
      order = 38,
      required = false
   )
   public PT_CUSTOM_DATA customdata;
   @Protobuf(
      order = 39
   )
   public List<PT_MEMBER_AREA_INFO> members;
   @Protobuf(
      fieldType = FieldType.STRING,
      order = 40,
      required = false
   )
   public String targetname;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 41,
      required = false
   )
   public Integer characterframe;
   @Protobuf(
      fieldType = FieldType.BOOL,
      order = 42,
      required = false
   )
   public Boolean observer;
   @Protobuf(
      fieldType = FieldType.BOOL,
      order = 43,
      required = false
   )
   public Boolean observerchat;
}
