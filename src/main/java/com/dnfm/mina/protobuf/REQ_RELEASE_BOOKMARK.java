package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

public class REQ_RELEASE_BOOKMARK {
   @Protobuf(
      order = 1,
      required = false
   )
   public PT_BOOKMARK_ITEM bookmark;
}
