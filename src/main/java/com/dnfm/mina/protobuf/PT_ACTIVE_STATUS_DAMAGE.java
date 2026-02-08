package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

public class PT_ACTIVE_STATUS_DAMAGE {
   @Protobuf(
      fieldType = FieldType.UINT32,
      order = 1,
      required = false
   )
   public Integer type;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 2,
      required = false
   )
   public Integer damage;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 3,
      required = false
   )
   public Integer skillindex;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 4,
      required = false
   )
   public Integer itemindex;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 5,
      required = false
   )
   public Integer victimguid;
   @Protobuf(
      fieldType = FieldType.UINT32,
      order = 6,
      required = false
   )
   public Integer victimtype;
   @Protobuf(
      fieldType = FieldType.UINT32,
      order = 7,
      required = false
   )
   public Integer tickcount;
}
