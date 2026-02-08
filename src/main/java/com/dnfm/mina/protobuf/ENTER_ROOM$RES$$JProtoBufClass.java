package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.Codec;
import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.ProtobufProxy;
import com.baidu.bjf.remoting.protobuf.code.CodedConstant;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.InvalidProtocolBufferException;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ENTER_ROOM$RES$$JProtoBufClass implements Codec<ENTER_ROOM.RES>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(ENTER_ROOM.RES var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public ENTER_ROOM.RES decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(ENTER_ROOM.RES var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var14 = var1.error;
         var2 += CodedOutputStream.computeInt32Size(1, var14);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.index)) {
         Long var15 = var1.index;
         var2 += CodedOutputStream.computeUInt64Size(2, var15);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.roomState)) {
         Integer var16 = var1.roomState;
         var2 += CodedOutputStream.computeInt32Size(3, var16);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.version)) {
         String var17 = var1.version;
         var2 += CodedOutputStream.computeStringSize(4, var17);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.matchSuccess)) {
         byte[] var18 = var1.matchSuccess;
         var2 += CodedOutputStream.computeByteArraySize(5, var18);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.syncDungeonInfo)) {
         byte[] var19 = var1.syncDungeonInfo;
         var2 += CodedOutputStream.computeByteArraySize(6, var19);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.startDungeon)) {
         byte[] var20 = var1.startDungeon;
         var2 += CodedOutputStream.computeByteArraySize(7, var20);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.stageInfo)) {
         byte[] var21 = var1.stageInfo;
         var2 += CodedOutputStream.computeByteArraySize(8, var21);
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.playFrame)) {
         Integer var22 = var1.playFrame;
         var2 += CodedOutputStream.computeUInt32Size(9, var22);
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.type)) {
         Integer var23 = var1.type;
         var2 += CodedOutputStream.computeInt32Size(10, var23);
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.disconnectUsers)) {
         List var24 = var1.disconnectUsers;
         var2 += CodedConstant.computeListSize(11, var24, FieldType.UINT64, false, (File)ProtobufProxy.OUTPUT_PATH.get(), true);
      }

      return var2;
   }

   public void doWriteTo(ENTER_ROOM.RES var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var14 = var1.error;
         if (var14 != null) {
            var2.writeInt32(1, var14);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.index)) {
         Long var15 = var1.index;
         if (var15 != null) {
            var2.writeUInt64(2, var15);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.roomState)) {
         Integer var16 = var1.roomState;
         if (var16 != null) {
            var2.writeInt32(3, var16);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.version)) {
         String var17 = var1.version;
         if (var17 != null) {
            var2.writeString(4, var17);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.matchSuccess)) {
         byte[] var18 = var1.matchSuccess;
         if (var18 != null) {
            var2.writeByteArray(5, var18);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.syncDungeonInfo)) {
         byte[] var19 = var1.syncDungeonInfo;
         if (var19 != null) {
            var2.writeByteArray(6, var19);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.startDungeon)) {
         byte[] var20 = var1.startDungeon;
         if (var20 != null) {
            var2.writeByteArray(7, var20);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.stageInfo)) {
         byte[] var21 = var1.stageInfo;
         if (var21 != null) {
            var2.writeByteArray(8, var21);
         }
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.playFrame)) {
         Integer var22 = var1.playFrame;
         if (var22 != null) {
            var2.writeUInt32(9, var22);
         }
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.type)) {
         Integer var23 = var1.type;
         if (var23 != null) {
            var2.writeInt32(10, var23);
         }
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.disconnectUsers)) {
         List var24 = var1.disconnectUsers;
         if (var24 != null) {
            CodedConstant.writeToList(var2, 11, FieldType.UINT64, var24, true);
         }
      }

   }

   public void writeTo(ENTER_ROOM.RES var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public ENTER_ROOM.RES readFrom(CodedInputStream var1) throws IOException {
      ENTER_ROOM.RES var2 = new ENTER_ROOM.RES();
      var2.disconnectUsers = new ArrayList();

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 8) {
               var2.error = var1.readInt32();
            } else if (var5 == 16) {
               var2.index = var1.readUInt64();
            } else if (var5 == 24) {
               var2.roomState = var1.readInt32();
            } else if (var5 == 34) {
               var2.version = var1.readString();
            } else if (var5 == 42) {
               var2.matchSuccess = var1.readBytes().toByteArray();
            } else if (var5 == 50) {
               var2.syncDungeonInfo = var1.readBytes().toByteArray();
            } else if (var5 == 58) {
               var2.startDungeon = var1.readBytes().toByteArray();
            } else if (var5 == 66) {
               var2.stageInfo = var1.readBytes().toByteArray();
            } else if (var5 == 72) {
               var2.playFrame = var1.readUInt32();
            } else if (var5 == 80) {
               var2.type = var1.readInt32();
            } else if (var5 == 88) {
               if (var2.disconnectUsers == null) {
                  var2.disconnectUsers = new ArrayList();
               }

               var2.disconnectUsers.add(var1.readUInt64());
            } else if (var5 != 90) {
               var1.skipField(var5);
            } else {
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.disconnectUsers == null) {
                  var2.disconnectUsers = new ArrayList();
               }

               while(var1.getBytesUntilLimit() > 0) {
                  var2.disconnectUsers.add(var1.readUInt64());
               }

               var1.popLimit(var7);
            }
         }

         return var2;
      } catch (InvalidProtocolBufferException var8) {
         throw var8;
      } catch (IOException var9) {
         throw var9;
      }
   }

   public Descriptors.Descriptor getDescriptor() throws IOException {
      if (this.descriptor != null) {
         return this.descriptor;
      } else {
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(ENTER_ROOM.RES.class);
         return this.descriptor = var1;
      }
   }
}
