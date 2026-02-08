package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;
import java.util.List;

@MessageMeta(
   module = -1,
   cmd = 1
)
public class RES_ARTIFACT_INVEN_OPTION_CHANGE extends Message {
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 1,
      required = false
   )
   public Integer error;
   @Protobuf(
      order = 2
   )
   public List<PT_ARTIFACT_BASE_OPTION> boption;
   @Protobuf(
      order = 3,
      required = false
   )
   public PT_REMOVEITEMS removeitems;
   @Protobuf(
      order = 4
   )
   public List<PT_MONEY_ITEM> currency;
}
