package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;

@MessageMeta(
   module = 18100,
   cmd = 0
)
public class REQ_CHARACTER_STAT_INFO extends Message {
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 1,
      required = false
   )
   public Integer lightattribute;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 2,
      required = false
   )
   public Integer iceattribute;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 3,
      required = false
   )
   public Integer fireattribute;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 4,
      required = false
   )
   public Integer darkattribute;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 5,
      required = false
   )
   public Integer lightattributeResistance;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 6,
      required = false
   )
   public Integer iceattributeResistance;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 7,
      required = false
   )
   public Integer fireattributeResistance;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 8,
      required = false
   )
   public Integer darkattributeResistance;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 9,
      required = false
   )
   public Integer intelligencevalue;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 10,
      required = false
   )
   public Integer powervalue;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 11,
      required = false
   )
   public Integer strengthvalue;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 12,
      required = false
   )
   public Integer spiritvalue;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 13,
      required = false
   )
   public Integer magicattack;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 14,
      required = false
   )
   public Integer magiccri;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 15,
      required = false
   )
   public Integer magiccrirate;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 16,
      required = false
   )
   public Integer physicalattack;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 17,
      required = false
   )
   public Integer physicalcrit;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 18,
      required = false
   )
   public Integer physicalcritrate;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 19,
      required = false
   )
   public Integer attackspeed;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 20,
      required = false
   )
   public Integer castingspeed;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 21,
      required = false
   )
   public Integer movingspeed;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 22,
      required = false
   )
   public Integer defyingdefenseattack;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 23,
      required = false
   )
   public Integer accuracyrate;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 24,
      required = false
   )
   public Integer accuracyvariablerate;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 25,
      required = false
   )
   public Integer stuckvariablerate;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 26,
      required = false
   )
   public Integer stuck;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 27,
      required = false
   )
   public Integer hpregenrate;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 28,
      required = false
   )
   public Integer mpregenrate;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 29,
      required = false
   )
   public Integer ridgidity;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 30,
      required = false
   )
   public Integer hitrecovery;
}
