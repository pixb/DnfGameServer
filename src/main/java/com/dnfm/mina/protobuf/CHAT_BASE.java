package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import java.util.List;

public class CHAT_BASE {
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 1,
      required = false
   )
   public Integer mType;
   @Protobuf(
      fieldType = FieldType.STRING,
      order = 2,
      required = false
   )
   public String mMsg;
   @Protobuf(
      fieldType = FieldType.STRING,
      order = 3,
      required = false
   )
   public String mVoice;
   @Protobuf(
      fieldType = FieldType.STRING,
      order = 4,
      required = false
   )
   public String mVoidcetime;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 5,
      required = false
   )
   public Integer hyperlinktype;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 6,
      required = false
   )
   public Integer hyperlinksubtype;
   @Protobuf(
      order = 7
   )
   public List<SUBSYSTEM.Types.PT_HYPERLINK_DATA> hyperlinkdatas;
   @Protobuf(
      order = 8
   )
   public List<SUBSYSTEM.Types.PT_HYPERLINK_SYSTEMMESSAGE_SUB> sub;
}
