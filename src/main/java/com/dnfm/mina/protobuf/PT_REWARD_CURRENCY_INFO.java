package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import java.util.List;

public class PT_REWARD_CURRENCY_INFO {
   @Protobuf(
      order = 1
   )
   public List<PT_MONEY_ITEM> currency;
   @Protobuf(
      order = 2
   )
   public List<PT_MONEY_ITEM> accountcurrency;
}
