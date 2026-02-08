package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

public class SEND_CHAT {
   public static class Types {
      public static class RES {
         @Protobuf(
            fieldType = FieldType.INT32,
            order = 1,
            required = false
         )
         public Integer error;
      }

      public static class REQ {
         @Protobuf(
            fieldType = FieldType.INT32,
            order = 1,
            required = false
         )
         public Integer type;
         @Protobuf(
            fieldType = FieldType.STRING,
            order = 2,
            required = false
         )
         public String chat;
      }

      public static class NOTI {
         @Protobuf(
            fieldType = FieldType.INT32,
            order = 1,
            required = false
         )
         public Integer type;
         @Protobuf(
            fieldType = FieldType.STRING,
            order = 2,
            required = false
         )
         public String chat;
      }
   }

   public static class RES {
      @Protobuf(
         fieldType = FieldType.INT32,
         order = 1,
         required = false
      )
      public Integer error;
   }

   public static class REQ {
      @Protobuf(
         fieldType = FieldType.INT32,
         order = 1,
         required = false
      )
      public Integer type;
      @Protobuf(
         fieldType = FieldType.STRING,
         order = 2,
         required = false
      )
      public String chat;
   }

   public static class NOTI {
      @Protobuf(
         fieldType = FieldType.INT32,
         order = 1,
         required = false
      )
      public Integer type;
      @Protobuf(
         fieldType = FieldType.STRING,
         order = 2,
         required = false
      )
      public String chat;
   }
}
