package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;
import java.util.List;

@MessageMeta(
   module = 17211,
   cmd = 0
)
public class REQ_ADVENTURE_UNION_SET_SHAREBOARD extends Message {
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 1,
      required = false
   )
   public Integer shareboardbackground;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 2,
      required = false
   )
   public Integer shareboardframe;
   @Protobuf(
      order = 3
   )
   public List<PT_ADVENTURE_UNION_SHAREBOARD_SLOT> shareboardslotlist;
   @Protobuf(
      fieldType = FieldType.BOOL,
      order = 4,
      required = false
   )
   public Boolean shareboardshowantievilscore;
}
