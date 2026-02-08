package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import java.util.List;

public class PT_TICKET_REWARD_INFO {
   @Protobuf(
      order = 1
   )
   public List<PT_TICKET> character;
   @Protobuf(
      order = 2
   )
   public List<PT_TICKET> account;
}
