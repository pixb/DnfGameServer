package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

public class PT_MINIGAME_SPECIAL_CLASS_STUDENT_INFO {
   @Protobuf(
      fieldType = FieldType.BOOL,
      order = 1,
      required = false
   )
   public Boolean opening;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 2,
      required = false
   )
   public Integer exp;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 3,
      required = false
   )
   public Integer friendship;
   @Protobuf(
      fieldType = FieldType.INT64,
      order = 4,
      required = false
   )
   public Long lastfriendshipdate;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 5,
      required = false
   )
   public Integer repeatedcount;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 6,
      required = false
   )
   public Integer rewardstep;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 7,
      required = false
   )
   public Integer mazeitem;
   @Protobuf(
      fieldType = FieldType.INT64,
      order = 8,
      required = false
   )
   public Long lastcleardate;
}
