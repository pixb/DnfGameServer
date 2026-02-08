package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import java.util.List;

public class PT_ADVENTURE_REWARD_INFO {
   @Protobuf(
      order = 1,
      required = false
   )
   public PT_CONTENTS_REWARD_INFO rewards;
   @Protobuf(
      order = 2,
      required = false
   )
   public PT_ADVENTURE_EXP_INFO adventureexp;
   @Protobuf(
      order = 3
   )
   public List<PT_EXP_INFO> charexps;
}
