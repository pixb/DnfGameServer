package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

public class PT_PVP_BATTLE_INFO {
   @Protobuf(
      fieldType = FieldType.INT64,
      order = 1,
      required = false
   )
   public Long maxcombodamage;
   @Protobuf(
      fieldType = FieldType.FLOAT,
      order = 2,
      required = false
   )
   public Float startcombosuccessrate;
   @Protobuf(
      fieldType = FieldType.INT64,
      order = 3,
      required = false
   )
   public Long counterdamage;
   @Protobuf(
      fieldType = FieldType.INT64,
      order = 4,
      required = false
   )
   public Long matchtime;
   @Protobuf(
      fieldType = FieldType.FLOAT,
      order = 5,
      required = false
   )
   public Float hprate;
   @Protobuf(
      fieldType = FieldType.INT64,
      order = 6,
      required = false
   )
   public Long totaldamage;
   @Protobuf(
      fieldType = FieldType.FLOAT,
      order = 7,
      required = false
   )
   public Float skilldodgerate;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 8,
      required = false
   )
   public Integer totalcombocount;
   @Protobuf(
      fieldType = FieldType.INT64,
      order = 9,
      required = false
   )
   public Long aerialdamage;
   @Protobuf(
      fieldType = FieldType.INT64,
      order = 10,
      required = false
   )
   public Long standdamage;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 11,
      required = false
   )
   public Integer startcombosuccesscount;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 12,
      required = false
   )
   public Integer startcombototalcount;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 13,
      required = false
   )
   public Integer attackedskillcount;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 14,
      required = false
   )
   public Integer allcharacterskillcount;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 15,
      required = false
   )
   public Integer killcount;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 16,
      required = false
   )
   public Integer killedcount;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 17,
      required = false
   )
   public Integer mortar;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 18,
      required = false
   )
   public Integer deltaKillcount;
   @Protobuf(
      fieldType = FieldType.INT64,
      order = 19,
      required = false
   )
   public Long deltaMaxcombodamage;
   @Protobuf(
      fieldType = FieldType.FLOAT,
      order = 20,
      required = false
   )
   public Float deltaStartcombosuccessrate;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 21,
      required = false
   )
   public Integer deltaStartcombosuccesscount;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 22,
      required = false
   )
   public Integer deltaStartcombototalcount;
   @Protobuf(
      fieldType = FieldType.INT64,
      order = 23,
      required = false
   )
   public Long deltaCounterdamage;
   @Protobuf(
      fieldType = FieldType.INT64,
      order = 24,
      required = false
   )
   public Long deltaTotaldamage;
   @Protobuf(
      fieldType = FieldType.FLOAT,
      order = 25,
      required = false
   )
   public Float deltaSkilldodgerate;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 26,
      required = false
   )
   public Integer deltaAttackedskillcount;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 27,
      required = false
   )
   public Integer deltaAllcharacterskillcount;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 28,
      required = false
   )
   public Integer deltaTotalcombocount;
   @Protobuf(
      fieldType = FieldType.INT64,
      order = 29,
      required = false
   )
   public Long deltaAerialdamage;
   @Protobuf(
      fieldType = FieldType.INT64,
      order = 30,
      required = false
   )
   public Long deltaStanddamage;
}
