package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import java.util.List;

public class NOTIFY_BATTLELEAGUE_TRANSITION_CARD {
   @Protobuf(
      order = 1
   )
   public List<PT_BATTLELEAGUE_TRANSITION_CARD> transitions;
}
