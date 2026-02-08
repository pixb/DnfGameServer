package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;
import java.util.List;

@MessageMeta(
   module = -1,
   cmd = 1
)
public class RES_QUERY_GACC_ACCOUNT_REMOVE extends Message {
   @Protobuf(
      fieldType = FieldType.BOOL,
      order = 1,
      required = false
   )
   public Boolean result;
   @Protobuf(
      order = 2
   )
   public List<PT_GACC_REMOVE_RESULT_CHARACTER> characters;
}
