package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;
import java.util.List;

@MessageMeta(
   module = 13050,
   cmd = 1
)
public class RES_AI_CHARACTER_INFO extends Message {
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
   public Integer index;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 3,
      required = false
   )
   public Integer group;
   @Protobuf(
      order = 4
   )
   public List<PT_AI_CHARACTER_DETAIL_INFO> highaicharacinfos;
   @Protobuf(
      order = 5
   )
   public List<PT_AI_CHARACTER_DETAIL_INFO> lowaicharacinfos;
}
