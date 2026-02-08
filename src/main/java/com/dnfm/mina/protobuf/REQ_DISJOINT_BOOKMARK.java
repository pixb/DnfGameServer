package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import java.util.List;

public class REQ_DISJOINT_BOOKMARK {
   @Protobuf(
      order = 1
   )
   public List<PT_BOOKMARK_ITEM> bookmarks;
}
