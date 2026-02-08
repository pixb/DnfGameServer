package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;
import java.util.List;

@MessageMeta(
   module = 14102,
   cmd = 0
)
public class REQ_SEND_STORAGE extends Message {
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 1,
      required = false
   )
   public Integer type;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 2,
      required = false
   )
   public Integer storage;
   @Protobuf(
      order = 3
   )
   public List<SendItem_GuidInfo> guids;
   @Protobuf(
      order = 4
   )
   public List<SendItem_Info> indexes;
}
