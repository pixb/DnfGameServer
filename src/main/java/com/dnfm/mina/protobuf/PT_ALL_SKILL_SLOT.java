package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import java.util.List;

public class PT_ALL_SKILL_SLOT {
   @Protobuf(
      order = 1
   )
   public List<PT_SKILL_SLOT> active;
   @Protobuf(
      order = 2
   )
   public List<PT_SKILL_SLOT> buff;
   @Protobuf(
      order = 3
   )
   public List<PT_SKILL_SLOT> combo;
   @Protobuf(
      order = 4
   )
   public List<PT_SKILL_SLOT_MATCHING> keyboardmatching;
   @Protobuf(
      order = 5
   )
   public List<PT_SKILL_SLOT_MATCHING> padmatching;
}
