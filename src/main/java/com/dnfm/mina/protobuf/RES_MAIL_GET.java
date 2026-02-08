package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;
import java.util.List;

@MessageMeta(
   module = 15002,
   cmd = 1
)
public class RES_MAIL_GET extends Message {
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
   public Long guid;
   @Protobuf(
      fieldType = FieldType.BOOL,
      order = 3,
      required = false
   )
   public Boolean limit;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 4,
      required = false
   )
   public Integer type;
   @Protobuf(
      order = 5
   )
   public List<PT_SELECTED_ITEM> remaineditems;
   @Protobuf(
      order = 6
   )
   public List<PT_POST_PACKAGE> remainedpackages;
   @Protobuf(
      order = 7,
      required = false
   )
   public PT_CONTENTS_REWARD_INFO rewards;
}
