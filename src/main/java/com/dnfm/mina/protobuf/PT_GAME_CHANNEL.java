package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import java.util.List;

public class PT_GAME_CHANNEL {
   @Protobuf(
      order = 1
   )
   public List<PT_GAME_CHANNEL_STATUS> status;
}
