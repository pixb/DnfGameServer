package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;
import java.util.List;

@MessageMeta(
   module = 18502,
   cmd = 0
)
public class REQ_MELTING_CRACK extends Message {
   @Protobuf(
      order = 1
   )
   public List<PT_METING_CRACK_INFO> crack;
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 2
   )
   public List<Long> crackequips;
}
