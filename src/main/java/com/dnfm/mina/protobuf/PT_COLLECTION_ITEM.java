package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

public class PT_COLLECTION_ITEM {
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 1,
      required = false
   )
   public Integer index;
   @Protobuf(
      fieldType = FieldType.BOOL,
      order = 2,
      required = false
   )
   public Boolean regist;
   @Protobuf(
      fieldType = FieldType.BOOL,
      order = 3,
      required = false
   )
   public Boolean reward;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 4,
      required = false
   )
   public Integer rewardindex;
}
