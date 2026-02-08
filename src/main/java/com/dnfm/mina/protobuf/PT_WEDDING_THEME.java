package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import java.util.List;

public class PT_WEDDING_THEME {
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 1,
      required = false
   )
   public Integer weddinghall;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 2,
      required = false
   )
   public Integer officiant;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 3
   )
   public List<Integer> guestlist;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 4,
      required = false
   )
   public Integer dress;
   @Protobuf(
      order = 5
   )
   public List<PT_WEDDING_THEME_CEREMONY> ceremonylist;
}
