package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

public class RELAY_FRAME_DATA {
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
         public Long index;
      }

      public static class NOTI {
         @Protobuf(
            fieldType = FieldType.UINT64,
            order = 1,
            required = false
         )
         public Long roomId;
         @Protobuf(
            fieldType = FieldType.BYTES,
            order = 2,
            required = false
         )
         public byte[] data;
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
      public Long index;
   }

   public static class NOTI {
      @Protobuf(
         fieldType = FieldType.UINT64,
         order = 1,
         required = false
      )
      public Long roomId;
      @Protobuf(
         fieldType = FieldType.BYTES,
         order = 2,
         required = false
      )
      public byte[] data;
   }
}
