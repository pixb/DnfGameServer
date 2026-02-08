package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.Codec;
import com.baidu.bjf.remoting.protobuf.code.CodedConstant;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.InvalidProtocolBufferException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;

public class PT_RANKING_DUNGEON_SEASON_INFO$$JProtoBufClass implements Codec<PT_RANKING_DUNGEON_SEASON_INFO>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(PT_RANKING_DUNGEON_SEASON_INFO var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public PT_RANKING_DUNGEON_SEASON_INFO decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(PT_RANKING_DUNGEON_SEASON_INFO var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.type)) {
         Integer var8 = var1.type;
         var2 += CodedOutputStream.computeInt32Size(1, var8);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.trycount)) {
         Integer var9 = var1.trycount;
         var2 += CodedOutputStream.computeInt32Size(2, var9);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.rank)) {
         Long var10 = var1.rank;
         var2 += CodedOutputStream.computeInt64Size(3, var10);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.score)) {
         Long var11 = var1.score;
         var2 += CodedOutputStream.computeUInt64Size(4, var11);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.count)) {
         Long var12 = var1.count;
         var2 += CodedOutputStream.computeInt64Size(5, var12);
      }

      return var2;
   }

   public void doWriteTo(PT_RANKING_DUNGEON_SEASON_INFO var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.type)) {
         Integer var8 = var1.type;
         if (var8 != null) {
            var2.writeInt32(1, var8);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.trycount)) {
         Integer var9 = var1.trycount;
         if (var9 != null) {
            var2.writeInt32(2, var9);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.rank)) {
         Long var10 = var1.rank;
         if (var10 != null) {
            var2.writeInt64(3, var10);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.score)) {
         Long var11 = var1.score;
         if (var11 != null) {
            var2.writeUInt64(4, var11);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.count)) {
         Long var12 = var1.count;
         if (var12 != null) {
            var2.writeInt64(5, var12);
         }
      }

   }

   public void writeTo(PT_RANKING_DUNGEON_SEASON_INFO var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public PT_RANKING_DUNGEON_SEASON_INFO readFrom(CodedInputStream var1) throws IOException {
      PT_RANKING_DUNGEON_SEASON_INFO var2 = new PT_RANKING_DUNGEON_SEASON_INFO();

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 8) {
               var2.type = var1.readInt32();
            } else if (var5 == 16) {
               var2.trycount = var1.readInt32();
            } else if (var5 == 24) {
               var2.rank = var1.readInt64();
            } else if (var5 == 32) {
               var2.score = var1.readUInt64();
            } else if (var5 == 40) {
               var2.count = var1.readInt64();
            } else {
               var1.skipField(var5);
            }
         }

         return var2;
      } catch (InvalidProtocolBufferException var6) {
         throw var6;
      } catch (IOException var7) {
         throw var7;
      }
   }

   public Descriptors.Descriptor getDescriptor() throws IOException {
      if (this.descriptor != null) {
         return this.descriptor;
      } else {
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(PT_RANKING_DUNGEON_SEASON_INFO.class);
         return this.descriptor = var1;
      }
   }
}
