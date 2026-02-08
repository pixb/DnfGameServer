package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;
import java.util.List;

@MessageMeta(
   module = 17004,
   cmd = 1
)
public class RES_IDIP_RELEASE_PANALTY extends Message {
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
   public Long target;
   @Protobuf(
      fieldType = FieldType.ENUM,
      order = 3
   )
   public List<ENUM_IDIP_PROHIBIT_TYPE.T> list;
}
