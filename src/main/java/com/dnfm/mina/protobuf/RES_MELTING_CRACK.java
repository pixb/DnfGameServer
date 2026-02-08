package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;
import java.util.List;

@MessageMeta(
   module = 18502,
   cmd = 1
)
public class RES_MELTING_CRACK extends Message {
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 1,
      required = false
   )
   public Integer error;
   @Protobuf(
      order = 2
   )
   public List<PT_METING_CRACK_INFO> crack;
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 3
   )
   public List<Long> crackequips;
   @Protobuf(
      order = 4,
      required = false
   )
   public PT_CONTENTS_REWARD_INFO rewards;
}
