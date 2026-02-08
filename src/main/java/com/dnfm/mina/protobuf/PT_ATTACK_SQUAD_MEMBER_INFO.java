package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

public class PT_ATTACK_SQUAD_MEMBER_INFO {
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 1,
      required = false
   )
   public Long partyguid;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 2,
      required = false
   )
   public Integer partyindex;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 3,
      required = false
   )
   public Integer partyslot;
   @Protobuf(
      order = 4,
      required = false
   )
   public PT_GROUP_MEMBER user;
   @Protobuf(
      fieldType = FieldType.INT64,
      order = 5,
      required = false
   )
   public Long disconnecttime;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 6,
      required = false
   )
   public Integer rewardconditioncount;
}
