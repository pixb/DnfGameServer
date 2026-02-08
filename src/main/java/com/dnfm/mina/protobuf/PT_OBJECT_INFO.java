package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import java.util.List;

public class PT_OBJECT_INFO {
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 1,
      required = false
   )
   public Long guid;
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 2,
      required = false
   )
   public Long ownerguid;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 3,
      required = false
   )
   public Integer level;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 4,
      required = false
   )
   public Integer teamtype;
   @Protobuf(
      fieldType = FieldType.BOOL,
      order = 5,
      required = false
   )
   public Boolean die;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 6,
      required = false
   )
   public Integer type;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 7,
      required = false
   )
   public Integer index;
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 8,
      required = false
   )
   public Long hp;
   @Protobuf(
      order = 9
   )
   public List<PT_DROP_OBJECT_GOLD> golds;
   @Protobuf(
      order = 10
   )
   public List<PT_DROP_OBJECT_ITEM> items;
   @Protobuf(
      order = 11
   )
   public List<PT_MONSTER_EXP> exps;
   @Protobuf(
      order = 12
   )
   public List<PT_APPENDAGE> appendages;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 13,
      required = false
   )
   public Integer waveindex;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 14,
      required = false
   )
   public Integer spawnindex;
}
