package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;
import java.util.List;

@MessageMeta(
   module = 14017,
   cmd = 0
)
public class REQ_ITEM_USE extends Message {
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 1,
      required = false
   )
   public Integer index;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 2,
      required = false
   )
   public Integer count;
   @Protobuf(
      fieldType = FieldType.BOOL,
      order = 3,
      required = false
   )
   public Boolean bind;
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 4,
      required = false
   )
   public Long guid;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 5,
      required = false
   )
   public Integer type;
   @Protobuf(
      fieldType = FieldType.STRING,
      order = 6,
      required = false
   )
   public String input;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 7,
      required = false
   )
   public Integer selectitemindex;
   @Protobuf(
      order = 8
   )
   public List<PT_QUEST> quest;
}
