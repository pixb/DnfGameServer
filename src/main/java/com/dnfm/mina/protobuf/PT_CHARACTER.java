package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

public class PT_CHARACTER {
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 1,
      required = false
   )
   public Long charguid;
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 2,
      required = false
   )
   public Long lastlogout;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 3,
      required = false
   )
   public Integer growtype;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 4,
      required = false
   )
   public Integer secgrowtype;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 5,
      required = false
   )
   public Integer job;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 6,
      required = false
   )
   public Integer level;
   @Protobuf(
      fieldType = FieldType.STRING,
      order = 7,
      required = false
   )
   public String name;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 8,
      required = false
   )
   public Integer fatigue;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 9,
      required = false
   )
   public Integer equipscore;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 10,
      required = false
   )
   public Integer characterframe;
   @Protobuf(
      order = 11,
      required = false
   )
   public PT_CHARACTER_EQUIP_ONLY_INDEX equips;
   @Protobuf(
      fieldType = FieldType.UINT32,
      order = 12,
      required = false
   )
   public Integer avatarVisibleFlags;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 13,
      required = false
   )
   public Integer deletionstatus;
   @Protobuf(
      fieldType = FieldType.INT64,
      order = 14,
      required = false
   )
   public Long deletiontime;
   @Protobuf(
      fieldType = FieldType.INT64,
      order = 15,
      required = false
   )
   public Long createtime;
   @Protobuf(
      fieldType = FieldType.BOOL,
      order = 16,
      required = false
   )
   public Boolean changename;
}
