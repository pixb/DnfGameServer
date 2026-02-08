package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import java.util.List;

public class REQ_COMPOSE_SCROLL {
   @Protobuf(
      order = 1
   )
   public List<PT_ITEM_GUID> scrolls;
}
