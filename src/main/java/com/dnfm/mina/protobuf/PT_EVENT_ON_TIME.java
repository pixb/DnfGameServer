package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import java.util.List;

public class PT_EVENT_ON_TIME {
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 1,
      required = false
   )
   public Integer index;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 2,
      required = false
   )
   public Integer active;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 3
   )
   public List<Integer> days;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 4,
      required = false
   )
   public Integer startHour;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 5,
      required = false
   )
   public Integer startMinute;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 6,
      required = false
   )
   public Integer endHour;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 7,
      required = false
   )
   public Integer endMinute;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 8,
      required = false
   )
   public Integer rewardItemId;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 9,
      required = false
   )
   public Integer rewardItemCount;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 10,
      required = false
   )
   public Integer rewardBuffId;
}
