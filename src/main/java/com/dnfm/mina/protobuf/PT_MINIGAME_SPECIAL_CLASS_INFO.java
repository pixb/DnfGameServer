package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import java.util.List;

public class PT_MINIGAME_SPECIAL_CLASS_INFO {
   @Protobuf(
      order = 1,
      required = false
   )
   public PT_MINIGAME_SPECIAL_CLASS_STUDENT_INFO studentinfo;
   @Protobuf(
      order = 2
   )
   public List<PT_MINIGAME_SPECIAL_CLASS_JOB_INFO> jobinfos;
   @Protobuf(
      order = 3
   )
   public List<PT_MINIGAME_SPECIAL_CLASS_TICKET_INFO> ticketinfos;
   @Protobuf(
      order = 4
   )
   public List<PT_MINIGAME_SPECIAL_CLASS_CLEAR_HISTORY> history;
}
