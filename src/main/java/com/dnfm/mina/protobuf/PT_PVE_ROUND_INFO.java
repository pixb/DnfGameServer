package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

public class PT_PVE_ROUND_INFO {
   @Protobuf(
      fieldType = FieldType.INT64,
      order = 1,
      required = false
   )
   public Long totalDamage;
   @Protobuf(
      fieldType = FieldType.FLOAT,
      order = 2,
      required = false
   )
   public Float totalDamageRate;
   @Protobuf(
      fieldType = FieldType.INT64,
      order = 3,
      required = false
   )
   public Long harmDamage;
   @Protobuf(
      fieldType = FieldType.FLOAT,
      order = 4,
      required = false
   )
   public Float harmDamageRate;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 5,
      required = false
   )
   public Integer supportPoint;
   @Protobuf(
      fieldType = FieldType.INT64,
      order = 6,
      required = false
   )
   public Long playtime;
   @Protobuf(
      fieldType = FieldType.FLOAT,
      order = 7,
      required = false
   )
   public Float skillAccuracyRate;
   @Protobuf(
      fieldType = FieldType.INT64,
      order = 8,
      required = false
   )
   public Long counterDamage;
   @Protobuf(
      fieldType = FieldType.INT64,
      order = 9,
      required = false
   )
   public Long maxComboDamage;
   @Protobuf(
      fieldType = FieldType.INT64,
      order = 10,
      required = false
   )
   public Long maximumDamage;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 11,
      required = false
   )
   public Integer totalCombo;
   @Protobuf(
      fieldType = FieldType.INT64,
      order = 12,
      required = false
   )
   public Long aerialDamage;
   @Protobuf(
      fieldType = FieldType.INT64,
      order = 13,
      required = false
   )
   public Long standDamage;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 14,
      required = false
   )
   public Integer harmCount;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 15,
      required = false
   )
   public Integer monsterKillCount;
}
