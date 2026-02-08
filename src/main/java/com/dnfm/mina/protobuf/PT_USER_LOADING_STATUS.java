package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import java.util.List;

public class PT_USER_LOADING_STATUS {
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 1,
      required = false
   )
   public Long charguid;
   @Protobuf(
      fieldType = FieldType.BOOL,
      order = 2,
      required = false
   )
   public Boolean ready;
   @Protobuf(
      fieldType = FieldType.BOOL,
      order = 3,
      required = false
   )
   public Boolean waiting;
   @Protobuf(
      fieldType = FieldType.BOOL,
      order = 4,
      required = false
   )
   public Boolean disconnect;
   @Protobuf(
      fieldType = FieldType.BOOL,
      order = 5,
      required = false
   )
   public Boolean leave;
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 6
   )
   public List<Long> kickoutvotes;
}
