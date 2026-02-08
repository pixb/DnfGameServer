package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;
import java.util.List;

@MessageMeta(
   module = 15306,
   cmd = 1
)
public class RES_SYSTEM_BUFF_APPENDAGE extends Message {
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 1,
      required = false
   )
   public Integer error;
   @Protobuf(
      fieldType = FieldType.INT64,
      order = 2,
      required = false
   )
   public Long time;
   @Protobuf(
      fieldType = FieldType.INT64,
      order = 3,
      required = false
   )
   public Long endtime;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 4,
      required = false
   )
   public Integer type;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 5,
      required = false
   )
   public Integer index;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 6
   )
   public List<Integer> values;
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 7,
      required = false
   )
   public Long targetguid;
}
