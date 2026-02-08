package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import java.util.List;

public class PT_SEASONPASS {
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 1,
      required = false
   )
   public Integer index;
   @Protobuf(
      fieldType = FieldType.INT64,
      order = 2,
      required = false
   )
   public Long dateStart;
   @Protobuf(
      fieldType = FieldType.INT64,
      order = 3,
      required = false
   )
   public Long dateEnd;
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 4
   )
   public List<Long> targetGuids;
}
