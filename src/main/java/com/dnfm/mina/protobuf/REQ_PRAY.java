package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;
import java.util.List;

@MessageMeta(
   module = 15402,
   cmd = 0
)
public class REQ_PRAY extends Message {
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 1,
      required = false
   )
   public Integer areaindex;
   @Protobuf(
      order = 2
   )
   public List<PT_PRAY_MATERIAL_INFO> material;
}
