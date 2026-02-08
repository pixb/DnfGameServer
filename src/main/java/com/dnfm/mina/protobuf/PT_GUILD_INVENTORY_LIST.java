package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import java.util.List;

public class PT_GUILD_INVENTORY_LIST {
   @Protobuf(
      order = 1
   )
   public List<PT_STACKABLE> inventory;
}
