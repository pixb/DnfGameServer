package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import java.util.List;

public class PT_CREATURE_LEARN_SKILL_INFOS {
   @Protobuf(
      order = 1
   )
   public List<PT_CREATURE_LEARN_SKILL_INFO> skillInfos;
}
