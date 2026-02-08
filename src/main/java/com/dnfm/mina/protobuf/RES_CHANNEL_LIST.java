package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;
import java.util.List;

@MessageMeta(
   module = 10008,
   cmd = 1
)
public class RES_CHANNEL_LIST extends Message {
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 1,
      required = false
   )
   public Integer error;
   @Protobuf(
      order = 2
   )
   public List<PT_CHANNEL> list;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 3,
      required = false
   )
   public Integer count;
   @Protobuf(
      order = 4
   )
   public List<PT_INTEGRATION_WORLD> integrations;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 5,
      required = false
   )
   public Integer type;
   @Protobuf(
      order = 6
   )
   public List<PT_CHANNEL> integrationrecommands;
   @Protobuf(
      order = 7
   )
   public List<PT_CHANNEL> worldrecommands;
}
