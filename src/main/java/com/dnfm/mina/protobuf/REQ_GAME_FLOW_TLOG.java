package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;

@MessageMeta(
   module = 10162,
   cmd = 0
)
public class REQ_GAME_FLOW_TLOG extends Message {
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 1,
      required = false
   )
   public Long dungeonguid;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 2,
      required = false
   )
   public Integer stageguid;
   @Protobuf(
      fieldType = FieldType.UINT32,
      order = 3,
      required = false
   )
   public Integer sectlogtype;
   @Protobuf(
      fieldType = FieldType.STRING,
      order = 4,
      required = false
   )
   public String sectlogvalue;
   @Protobuf(
      fieldType = FieldType.STRING,
      order = 5,
      required = false
   )
   public String gamesafedata;
   @Protobuf(
      fieldType = FieldType.UINT32,
      order = 6,
      required = false
   )
   public Integer cursequence;
   @Protobuf(
      fieldType = FieldType.UINT32,
      order = 7,
      required = false
   )
   public Integer endsequence;
}
