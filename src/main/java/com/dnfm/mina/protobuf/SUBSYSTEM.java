package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

public class SUBSYSTEM {
   public static class Types {
      public static class PT_HYPERLINK_SYSTEMMESSAGE_SUB {
         @Protobuf(
            fieldType = FieldType.STRING,
            order = 1,
            required = false
         )
         public String msg;
      }

      public static class PT_HYPERLINK_DATA {
         @Protobuf(
            fieldType = FieldType.UINT64,
            order = 1,
            required = false
         )
         public Long index;
         @Protobuf(
            fieldType = FieldType.BOOL,
            order = 2,
            required = false
         )
         public Boolean bind;
      }
   }

   public static class PT_HYPERLINK_SYSTEMMESSAGE_SUB {
      @Protobuf(
         fieldType = FieldType.STRING,
         order = 1,
         required = false
      )
      public String msg;
   }

   public static class PT_HYPERLINK_DATA {
      @Protobuf(
         fieldType = FieldType.UINT64,
         order = 1,
         required = false
      )
      public Long index;
      @Protobuf(
         fieldType = FieldType.BOOL,
         order = 2,
         required = false
      )
      public Boolean bind;
   }
}
