package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dnfm.mina.annotation.MessageMeta;
import java.util.List;

@MessageMeta(
   module = 10031,
   cmd = 0
)
public class REQ_LOAD_SERVER_SIMPLE_DATA extends Message {
   @Protobuf(
      order = 1
   )
   public List<PT_LOAD_SERVER_SIMPLE_DATA> list;
}
