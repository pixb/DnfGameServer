package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;
import java.util.List;

@MessageMeta(
   module = 28122,
   cmd = 1
)
public class RES_MINIGAME_ADVENTURER_MAKER_LECTURE extends Message {
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 1,
      required = false
   )
   public Integer error;
   @Protobuf(
      order = 2,
      required = false
   )
   public PT_MINIGAME_ADVENTURER_MAKER_BABY_INFO info;
   @Protobuf(
      order = 3
   )
   public List<PT_MINIGAME_ADVENTURER_MAKER_JOB_INFO> jobinfos;
   @Protobuf(
      order = 4
   )
   public List<PT_MINIGAME_ADVENTURER_MAKER_TICKET_INFO> ticketinfos;
}
