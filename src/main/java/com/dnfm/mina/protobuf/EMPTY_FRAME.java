package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import java.util.List;

public class EMPTY_FRAME {
   public static class Types {
      public static class RES {
         @Protobuf(
            fieldType = FieldType.INT32,
            order = 1,
            required = false
         )
         public Integer error;
         @Protobuf(
            order = 2
         )
         public List<FRAME> frame;
      }

      public static class FRAME_RANGE {
         @Protobuf(
            fieldType = FieldType.INT32,
            order = 1,
            required = false
         )
         public Integer begin;
         @Protobuf(
            fieldType = FieldType.INT32,
            order = 2,
            required = false
         )
         public Integer end;
      }

      public static class REQ {
         @Protobuf(
            order = 1
         )
         public List<FRAME_RANGE> frames;
      }
   }

   public static class RES {
      @Protobuf(
         fieldType = FieldType.INT32,
         order = 1,
         required = false
      )
      public Integer error;
      @Protobuf(
         order = 2
      )
      public List<FRAME> frame;
   }

   public static class FRAME_RANGE {
      @Protobuf(
         fieldType = FieldType.INT32,
         order = 1,
         required = false
      )
      public Integer begin;
      @Protobuf(
         fieldType = FieldType.INT32,
         order = 2,
         required = false
      )
      public Integer end;
   }

   public static class REQ {
      @Protobuf(
         order = 1
      )
      public List<Types.FRAME_RANGE> frames;
   }
}
