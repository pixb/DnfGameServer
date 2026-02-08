package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;
import java.util.List;

@MessageMeta(
   module = 11212,
   cmd = 0
)
public class REQ_ATTACK_SQUAD_RECOMMEND_INVITE_LIST extends Message {
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 1
   )
   public List<Integer> types;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 2,
      required = false
   )
   public Integer friendseq;
}
