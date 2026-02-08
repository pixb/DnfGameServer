package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import java.util.List;

public class NOTIFY_FAILED_TO_ACQUIRE_TIMEBOX {
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 1
   )
   public List<Integer> item;
}
