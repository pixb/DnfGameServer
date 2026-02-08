package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import java.util.List;

public class NOTIFY_ADVENTUREBOOK_ACHIEVEMENT_CONDITION {
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 1,
      required = false
   )
   public Integer error;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 2,
      required = false
   )
   public Integer conditionindex;
   @Protobuf(
      order = 3
   )
   public List<PT_ADVENTUREBOOK_ACHIEVE_CLEAR_CONDITION> achieveclearcondition;
}
