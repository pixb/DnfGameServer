package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import java.util.List;

public class STREAM_DATA {
   public static class Types {
      public static class FRAME_DATA {
         @Protobuf(
            fieldType = FieldType.INT32,
            order = 1,
            required = false
         )
         public Integer frameSize;
         @Protobuf(
            fieldType = FieldType.INT32,
            order = 2,
            required = false
         )
         public Integer splitCount;
         @Protobuf(
            fieldType = FieldType.INT32,
            order = 3,
            required = false
         )
         public Integer splitSeq;
         @Protobuf(
            fieldType = FieldType.BYTES,
            order = 4,
            required = false
         )
         public byte[] frameData;
      }

      public static class RES {
         @Protobuf(
            fieldType = FieldType.INT32,
            order = 1,
            required = false
         )
         public Integer error;
         @Protobuf(
            fieldType = FieldType.INT32,
            order = 2,
            required = false
         )
         public Integer reqFrame;
         @Protobuf(
            fieldType = FieldType.INT32,
            order = 3,
            required = false
         )
         public Integer endFrame;
         @Protobuf(
            order = 4
         )
         public List<FRAME_DATA> frames;
      }

      public static class REQ {
         @Protobuf(
            fieldType = FieldType.INT32,
            order = 1,
            required = false
         )
         public Integer startFrame;
      }
   }

   public static class FRAME_DATA {
      @Protobuf(
         fieldType = FieldType.INT32,
         order = 1,
         required = false
      )
      public Integer frameSize;
      @Protobuf(
         fieldType = FieldType.INT32,
         order = 2,
         required = false
      )
      public Integer splitCount;
      @Protobuf(
         fieldType = FieldType.INT32,
         order = 3,
         required = false
      )
      public Integer splitSeq;
      @Protobuf(
         fieldType = FieldType.BYTES,
         order = 4,
         required = false
      )
      public byte[] frameData;
   }

   public static class RES {
      @Protobuf(
         fieldType = FieldType.INT32,
         order = 1,
         required = false
      )
      public Integer error;
      @Protobuf(
         fieldType = FieldType.INT32,
         order = 2,
         required = false
      )
      public Integer reqFrame;
      @Protobuf(
         fieldType = FieldType.INT32,
         order = 3,
         required = false
      )
      public Integer endFrame;
      @Protobuf(
         order = 4
      )
      public List<Types.FRAME_DATA> frames;
   }

   public static class REQ {
      @Protobuf(
         fieldType = FieldType.INT32,
         order = 1,
         required = false
      )
      public Integer startFrame;
   }
}
