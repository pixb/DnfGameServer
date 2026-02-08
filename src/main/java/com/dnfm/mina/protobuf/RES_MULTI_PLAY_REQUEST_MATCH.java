package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;

@MessageMeta(
   module = 13000,
   cmd = 1
)
public class RES_MULTI_PLAY_REQUEST_MATCH extends Message {
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
   public Integer dungeonindex;
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 3,
      required = false
   )
   public Long enemycharguid;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 4,
      required = false
   )
   public Integer aimatchingindex;
   @Protobuf(
      fieldType = FieldType.BOOL,
      order = 5,
      required = false
   )
   public Boolean retry;
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 6,
      required = false
   )
   public Long estimatedtime;
   @Protobuf(
      fieldType = FieldType.BOOL,
      order = 7,
      required = false
   )
   public Boolean iskeyboard;
}
