package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import java.util.List;

public class PT_CERA_SHOP_INFO {
   @Protobuf(
      order = 1
   )
   public List<PT_SHOP_BUY_COUNT> buy;
   @Protobuf(
      order = 2
   )
   public List<PT_SHOP_TAB_RESET> reset;
   @Protobuf(
      order = 3
   )
   public List<PT_SHOP_GROUP_RESET> group;
}
