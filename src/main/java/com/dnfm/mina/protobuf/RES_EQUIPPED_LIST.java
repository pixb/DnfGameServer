package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;
import java.util.List;

@MessageMeta(
   module = 14000,
   cmd = 1
)
public class RES_EQUIPPED_LIST extends Message {
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 1,
      required = false
   )
   public Integer error;
   @Protobuf(
      order = 2
   )
   public List<PT_EQUIPPED> equiplist;
   @Protobuf(
      order = 3
   )
   public List<PT_AVATAR_ITEM> avatarlist;
   @Protobuf(
      order = 4
   )
   public List<PT_CREATURE> creaturelist;
   @Protobuf(
      order = 5
   )
   public List<PT_ARTIFACT> cartifactlist;
   @Protobuf(
      order = 6
   )
   public List<PT_EQUIPPED> equipskinlist;
   @Protobuf(
      order = 7
   )
   public List<PT_AVATAR_ITEM> avatarskinlist;
   @Protobuf(
      order = 8
   )
   public List<PT_SKIN_ITEM> skinlist;
   @Protobuf(
      order = 9
   )
   public List<PT_AVATAR_ITEM> sdavatarlist;
}
