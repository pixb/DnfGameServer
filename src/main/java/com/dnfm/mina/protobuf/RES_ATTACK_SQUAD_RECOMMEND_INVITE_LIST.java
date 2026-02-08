package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;
import java.util.List;

@MessageMeta(
   module = 11212,
   cmd = 1
)
public class RES_ATTACK_SQUAD_RECOMMEND_INVITE_LIST extends Message {
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 1,
      required = false
   )
   public Integer error;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 2,
      required = false
   )
   public Integer page;
   @Protobuf(
      order = 3
   )
   public List<PT_GROUP_MEMBER> friend;
   @Protobuf(
      order = 4
   )
   public List<PT_GROUP_MEMBER> guild;
   @Protobuf(
      order = 5
   )
   public List<PT_GROUP_MEMBER> recommand;
   @Protobuf(
      order = 6
   )
   public List<PT_GROUP_MEMBER> playwith;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 7,
      required = false
   )
   public Integer lastfriendseq;
}
