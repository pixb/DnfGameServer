package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;
import java.util.List;

@MessageMeta(
   module = 10320,
   cmd = 1
)
public class RES_FRIEND_RECV_ALL_FATIGUE extends Message {
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 1,
      required = false
   )
   public Integer error;
   @Protobuf(
      order = 2
   )
   public List<PT_FRIEND_INFO> flist;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 3,
      required = false
   )
   public Integer ftype;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 4,
      required = false
   )
   public Integer index;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 5,
      required = false
   )
   public Integer count;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 6,
      required = false
   )
   public Integer total;
}
