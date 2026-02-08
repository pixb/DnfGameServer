package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

public class PACKET_RESTORE {
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
            fieldType = FieldType.UINT32,
            order = 1,
            required = false
         )
         public Integer sequence;
         @Protobuf(
            fieldType = FieldType.UINT64,
            order = 2,
            required = false
         )
         public Long authIndex;
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
         fieldType = FieldType.UINT32,
         order = 1,
         required = false
      )
      public Integer sequence;
      @Protobuf(
         fieldType = FieldType.UINT64,
         order = 2,
         required = false
      )
      public Long authIndex;
   }
}
