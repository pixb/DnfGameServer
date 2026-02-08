package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import java.util.List;

public class AchievementInfoPacketData {
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
   public Integer count;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 3,
      required = false
   )
   public Integer clear;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 4,
      required = false
   )
   public Integer rewardcount;
   @Protobuf(
      fieldType = FieldType.INT64,
      order = 5,
      required = false
   )
   public Long cleartime;
   @Protobuf(
      order = 6
   )
   public List<AchievementInfo> values;
}
