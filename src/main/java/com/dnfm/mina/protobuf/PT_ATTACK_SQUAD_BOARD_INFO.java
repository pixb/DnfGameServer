package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import java.util.List;

public class PT_ATTACK_SQUAD_BOARD_INFO {
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
   public Integer raidindex;
   @Protobuf(
      fieldType = FieldType.BOOL,
      order = 8,
      required = false
   )
   public Boolean chivalry;
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 9,
      required = false
   )
   public Long leaderguid;
   @Protobuf(
      fieldType = FieldType.BOOL,
      order = 10,
      required = false
   )
   public Boolean started;
   @Protobuf(
      order = 11
   )
   public List<PT_ATTACK_SQUAD_BOARD_USER_INFO> memberinfos;
}
