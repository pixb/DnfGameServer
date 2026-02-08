package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;
import java.util.List;

@MessageMeta(
   module = 13039,
   cmd = 1
)
public class RES_WATING_LIST_INFO_SYNC extends Message {
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
   public Long recruitmenttime;
   @Protobuf(
      order = 3
   )
   public List<PT_WATING_LIST_USER_INFO> users;
   @Protobuf(
      fieldType = FieldType.BOOL,
      order = 4,
      required = false
   )
   public Boolean enablematchping;
}
