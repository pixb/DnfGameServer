package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import java.util.List;

public class PT_ATTACK_SQUAD_DETAIL_INFO {
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 1,
      required = false
   )
   public Long rpguid;
   @Protobuf(
      fieldType = FieldType.STRING,
      order = 2,
      required = false
   )
   public String rpname;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 3,
      required = false
   )
   public Integer membercount;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 4,
      required = false
   )
   public Integer antievilscore;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 5,
      required = false
   )
   public Integer publictype;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 6,
      required = false
   )
   public Integer status;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 7,
      required = false
   )
   public Integer phasestatus;
   @Protobuf(
      fieldType = FieldType.INT64,
      order = 8,
      required = false
   )
   public Long changetime;
   @Protobuf(
      fieldType = FieldType.STRING,
      order = 9,
      required = false
   )
   public String leadername;
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 10,
      required = false
   )
   public Long leaderguid;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 11,
      required = false
   )
   public Integer world;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 12,
      required = false
   )
   public Integer channel;
   @Protobuf(
      fieldType = FieldType.STRING,
      order = 13,
      required = false
   )
   public String ip;
   @Protobuf(
      fieldType = FieldType.UINT32,
      order = 14,
      required = false
   )
   public Integer port;
   @Protobuf(
      order = 15
   )
   public List<PT_ATTACK_SQUAD_MEMBER_INFO> users;
   @Protobuf(
      fieldType = FieldType.BOOL,
      order = 16,
      required = false
   )
   public Boolean started;
}
