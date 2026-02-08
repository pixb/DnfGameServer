package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;
import java.util.List;

@MessageMeta(
   module = 10215,
   cmd = 0
)
public class REQ_ADVENTUREBOOK_UPDATE_CONDITION extends Message {
   @Protobuf(
      fieldType = FieldType.STRING,
      order = 1,
      required = false
   )
   public String authkey;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 2
   )
   public List<Integer> conditionindexes;
}
