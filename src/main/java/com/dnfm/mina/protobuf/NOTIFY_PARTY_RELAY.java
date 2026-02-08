package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import java.util.List;

public class NOTIFY_PARTY_RELAY {
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 1,
      required = false
   )
   public Integer error;
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 2,
      required = false
   )
   public Long partyguid;
   @Protobuf(
      order = 3,
      required = false
   )
   public PT_PARTY_RELAY relay;
   @Protobuf(
      order = 4
   )
   public List<PT_GROUP_MEMBER> users;
}
