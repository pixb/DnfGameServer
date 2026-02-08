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

public class REQ_GAME_FLOW_TLOG$$JProtoBufClass implements Codec<REQ_GAME_FLOW_TLOG>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(REQ_GAME_FLOW_TLOG var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public REQ_GAME_FLOW_TLOG decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(REQ_GAME_FLOW_TLOG var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.dungeonguid)) {
         Long var10 = var1.dungeonguid;
         var2 += CodedOutputStream.computeUInt64Size(1, var10);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.stageguid)) {
         Integer var11 = var1.stageguid;
         var2 += CodedOutputStream.computeInt32Size(2, var11);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.sectlogtype)) {
         Integer var12 = var1.sectlogtype;
         var2 += CodedOutputStream.computeUInt32Size(3, var12);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.sectlogvalue)) {
         String var13 = var1.sectlogvalue;
         var2 += CodedOutputStream.computeStringSize(4, var13);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.gamesafedata)) {
         String var14 = var1.gamesafedata;
         var2 += CodedOutputStream.computeStringSize(5, var14);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.cursequence)) {
         Integer var15 = var1.cursequence;
         var2 += CodedOutputStream.computeUInt32Size(6, var15);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.endsequence)) {
         Integer var16 = var1.endsequence;
         var2 += CodedOutputStream.computeUInt32Size(7, var16);
      }

      return var2;
   }

   public void doWriteTo(REQ_GAME_FLOW_TLOG var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.dungeonguid)) {
         Long var10 = var1.dungeonguid;
         if (var10 != null) {
            var2.writeUInt64(1, var10);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.stageguid)) {
         Integer var11 = var1.stageguid;
         if (var11 != null) {
            var2.writeInt32(2, var11);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.sectlogtype)) {
         Integer var12 = var1.sectlogtype;
         if (var12 != null) {
            var2.writeUInt32(3, var12);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.sectlogvalue)) {
         String var13 = var1.sectlogvalue;
         if (var13 != null) {
            var2.writeString(4, var13);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.gamesafedata)) {
         String var14 = var1.gamesafedata;
         if (var14 != null) {
            var2.writeString(5, var14);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.cursequence)) {
         Integer var15 = var1.cursequence;
         if (var15 != null) {
            var2.writeUInt32(6, var15);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.endsequence)) {
         Integer var16 = var1.endsequence;
         if (var16 != null) {
            var2.writeUInt32(7, var16);
         }
      }

   }

   public void writeTo(REQ_GAME_FLOW_TLOG var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public REQ_GAME_FLOW_TLOG readFrom(CodedInputStream var1) throws IOException {
      REQ_GAME_FLOW_TLOG var2 = new REQ_GAME_FLOW_TLOG();

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 8) {
               var2.dungeonguid = var1.readUInt64();
            } else if (var5 == 16) {
               var2.stageguid = var1.readInt32();
            } else if (var5 == 24) {
               var2.sectlogtype = var1.readUInt32();
            } else if (var5 == 34) {
               var2.sectlogvalue = var1.readString();
            } else if (var5 == 42) {
               var2.gamesafedata = var1.readString();
            } else if (var5 == 48) {
               var2.cursequence = var1.readUInt32();
            } else if (var5 == 56) {
               var2.endsequence = var1.readUInt32();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(REQ_GAME_FLOW_TLOG.class);
         return this.descriptor = var1;
      }
   }
}
