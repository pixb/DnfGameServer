package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import java.util.List;

public class CONTENT_STREAM {
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 1,
      required = false
   )
   public Long index;
   @Protobuf(
      order = 2
   )
   public List<USER_INFO> users;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 3,
      required = false
   )
   public Integer roomState;
   @Protobuf(
      fieldType = FieldType.STRING,
      order = 4,
      required = false
   )
   public String version;
   @Protobuf(
      fieldType = FieldType.UINT32,
      order = 5,
      required = false
   )
   public Integer playFrame;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 6,
      required = false
   )
   public Integer winnerTeam;
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 7
   )
   public List<Long> disconnectUsers;
}
