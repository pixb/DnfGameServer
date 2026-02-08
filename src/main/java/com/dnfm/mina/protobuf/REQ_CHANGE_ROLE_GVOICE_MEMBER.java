package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;
import java.util.List;

@MessageMeta(
   module = 10122,
   cmd = 0
)
public class REQ_CHANGE_ROLE_GVOICE_MEMBER extends Message {
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 1,
      required = false
   )
   public Integer voicetype;
   @Protobuf(
      order = 2
   )
   public List<PT_GVOICE_MEMBER_INFO> members;
}
