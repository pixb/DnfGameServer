package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;
import java.util.List;

@MessageMeta(
   module = -1,
   cmd = 0
)
public class REQ_WARDROBE_SET_SLOT_MANNEQUIN extends Message {
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 1
   )
   public List<Integer> beforeslots;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 2
   )
   public List<Integer> afterslots;
}
