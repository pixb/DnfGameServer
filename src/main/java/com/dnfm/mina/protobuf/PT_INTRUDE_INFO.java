package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import java.util.List;

public class PT_INTRUDE_INFO {
   @Protobuf(
      fieldType = FieldType.BOOL,
      order = 1,
      required = false
   )
   public Boolean intrude;
   @Protobuf(
      fieldType = FieldType.STRING,
      order = 2,
      required = false
   )
   public String name;
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 3,
      required = false
   )
   public Long guid;
   @Protobuf(
      order = 4,
      required = false
   )
   public PT_CHANNEL_INFO channel;
   @Protobuf(
      order = 5
   )
   public List<PT_INTRUDE_MEMBER_INFO> partymembers;
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 6,
      required = false
   )
   public Long rpguid;
}
