package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import java.util.List;

public class ENTER_ROOM {
   public static class Types {
      public static class RES {
         @Protobuf(
            fieldType = FieldType.INT32,
            order = 1,
            required = false
         )
         public Integer error;
         @Protobuf(
            fieldType = FieldType.UINT64,
            order = 2,
            required = false
         )
         public Long index;
         @Protobuf(
            fieldType = FieldType.INT32,
            order = 3,
            required = false
         )
         public Integer roomState;
         @Protobuf(
            fieldType = FieldType.STRING,
            order = 4,
            required = false
         )
         public String version;
         @Protobuf(
            fieldType = FieldType.BYTES,
            order = 5,
            required = false
         )
         public byte[] matchSuccess;
         @Protobuf(
            fieldType = FieldType.BYTES,
            order = 6,
            required = false
         )
         public byte[] syncDungeonInfo;
         @Protobuf(
            fieldType = FieldType.BYTES,
            order = 7,
            required = false
         )
         public byte[] startDungeon;
         @Protobuf(
            fieldType = FieldType.BYTES,
            order = 8,
            required = false
         )
         public byte[] stageInfo;
         @Protobuf(
            fieldType = FieldType.UINT32,
            order = 9,
            required = false
         )
         public Integer playFrame;
         @Protobuf(
            fieldType = FieldType.INT32,
            order = 10,
            required = false
         )
         public Integer type;
         @Protobuf(
            fieldType = FieldType.UINT64,
            order = 11
         )
         public List<Long> disconnectUsers;
      }

      public static class REQ {
         @Protobuf(
            fieldType = FieldType.UINT64,
            order = 1,
            required = false
         )
         public Long index;
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
         fieldType = FieldType.UINT64,
         order = 2,
         required = false
      )
      public Long index;
      @Protobuf(
         fieldType = FieldType.INT32,
         order = 3,
         required = false
      )
      public Integer roomState;
      @Protobuf(
         fieldType = FieldType.STRING,
         order = 4,
         required = false
      )
      public String version;
      @Protobuf(
         fieldType = FieldType.BYTES,
         order = 5,
         required = false
      )
      public byte[] matchSuccess;
      @Protobuf(
         fieldType = FieldType.BYTES,
         order = 6,
         required = false
      )
      public byte[] syncDungeonInfo;
      @Protobuf(
         fieldType = FieldType.BYTES,
         order = 7,
         required = false
      )
      public byte[] startDungeon;
      @Protobuf(
         fieldType = FieldType.BYTES,
         order = 8,
         required = false
      )
      public byte[] stageInfo;
      @Protobuf(
         fieldType = FieldType.UINT32,
         order = 9,
         required = false
      )
      public Integer playFrame;
      @Protobuf(
         fieldType = FieldType.INT32,
         order = 10,
         required = false
      )
      public Integer type;
      @Protobuf(
         fieldType = FieldType.UINT64,
         order = 11
      )
      public List<Long> disconnectUsers;
   }

   public static class REQ {
      @Protobuf(
         fieldType = FieldType.UINT64,
         order = 1,
         required = false
      )
      public Long index;
   }
}
