package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import java.util.List;

public class PT_MAIL_DISTRIBUTION_ITEMS {
   @Protobuf(
      fieldType = FieldType.UINT64,
      order = 1,
      required = false
   )
   public Long postalguid;
   @Protobuf(
      order = 2
   )
   public List<PT_SELECTED_ITEM> selecteditems;
   @Protobuf(
      order = 3
   )
   public List<PT_POST_PACKAGE> selectedpackages;
}
