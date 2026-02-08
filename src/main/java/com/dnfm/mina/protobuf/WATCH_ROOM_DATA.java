package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

public class WATCH_ROOM_DATA {
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
            fieldType = FieldType.INT64,
            order = 1,
            required = false
         )
         public Long roomId;
      }

      public static class NOTI {
         @Protobuf(
            fieldType = FieldType.STRING,
            order = 1,
            required = false
         )
         public String data;
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
         fieldType = FieldType.INT64,
         order = 1,
         required = false
      )
      public Long roomId;
   }

   public static class NOTI {
      @Protobuf(
         fieldType = FieldType.STRING,
         order = 1,
         required = false
      )
      public String data;
   }
}
