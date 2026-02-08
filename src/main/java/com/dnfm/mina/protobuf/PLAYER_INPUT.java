package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import java.util.List;

public class PLAYER_INPUT {
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 1,
      required = false
   )
   public Integer playerId;
   @Protobuf(
      fieldType = FieldType.BYTES,
      order = 2
   )
   public List<byte[]> input;
}
