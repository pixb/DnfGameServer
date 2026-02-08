package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;
import java.util.List;

@MessageMeta(
   module = 11040,
   cmd = 1
)
public class RES_TONIC_INIT extends Message {
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
      order = 3
   )
   public List<PT_TONIC_INFO> crystaltonic;
   @Protobuf(
      order = 4
   )
   public List<PT_TONIC_MATERIAL_USAGE> crystaltonicpoint;
   @Protobuf(
      order = 5
   )
   public List<PT_MONEY_ITEM> currency;
}
