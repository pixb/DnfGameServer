package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;
import java.util.List;

@MessageMeta(
   module = 15301,
   cmd = 0
)
public class REQ_RANK_TRAINING_FINISH extends Message {
   @Protobuf(
      order = 1
   )
   public List<PT_DAMAGE_ANALYSIS> damageanalysis;
   @Protobuf(
      fieldType = FieldType.INT64,
      order = 2,
      required = false
   )
   public Long totaldamage;
   @Protobuf(
      fieldType = FieldType.BOOL,
      order = 3,
      required = false
   )
   public Boolean trainingstop;
}
