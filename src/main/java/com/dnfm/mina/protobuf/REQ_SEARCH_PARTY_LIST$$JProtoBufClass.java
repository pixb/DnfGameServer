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

public class REQ_SEARCH_PARTY_LIST$$JProtoBufClass implements Codec<REQ_SEARCH_PARTY_LIST>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(REQ_SEARCH_PARTY_LIST var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public REQ_SEARCH_PARTY_LIST decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(REQ_SEARCH_PARTY_LIST var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.type)) {
         Integer var14 = var1.type;
         var2 += CodedOutputStream.computeInt32Size(1, var14);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.area)) {
         Integer var15 = var1.area;
         var2 += CodedOutputStream.computeInt32Size(2, var15);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.subtype)) {
         Integer var16 = var1.subtype;
         var2 += CodedOutputStream.computeInt32Size(3, var16);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.stageindex)) {
         Integer var17 = var1.stageindex;
         var2 += CodedOutputStream.computeInt32Size(4, var17);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.grade)) {
         Integer var18 = var1.grade;
         var2 += CodedOutputStream.computeInt32Size(5, var18);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.dungeonindex)) {
         Integer var19 = var1.dungeonindex;
         var2 += CodedOutputStream.computeInt32Size(6, var19);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.detail)) {
         Integer var20 = var1.detail;
         var2 += CodedOutputStream.computeInt32Size(7, var20);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.containobserver)) {
         Boolean var21 = var1.containobserver;
         var2 += CodedOutputStream.computeBoolSize(8, var21);
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.manualrefreshcount)) {
         Integer var22 = var1.manualrefreshcount;
         var2 += CodedOutputStream.computeInt32Size(9, var22);
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.autorefreshcount)) {
         Integer var23 = var1.autorefreshcount;
         var2 += CodedOutputStream.computeInt32Size(10, var23);
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.name)) {
         String var24 = var1.name;
         var2 += CodedOutputStream.computeStringSize(11, var24);
      }

      return var2;
   }

   public void doWriteTo(REQ_SEARCH_PARTY_LIST var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.type)) {
         Integer var14 = var1.type;
         if (var14 != null) {
            var2.writeInt32(1, var14);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.area)) {
         Integer var15 = var1.area;
         if (var15 != null) {
            var2.writeInt32(2, var15);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.subtype)) {
         Integer var16 = var1.subtype;
         if (var16 != null) {
            var2.writeInt32(3, var16);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.stageindex)) {
         Integer var17 = var1.stageindex;
         if (var17 != null) {
            var2.writeInt32(4, var17);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.grade)) {
         Integer var18 = var1.grade;
         if (var18 != null) {
            var2.writeInt32(5, var18);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.dungeonindex)) {
         Integer var19 = var1.dungeonindex;
         if (var19 != null) {
            var2.writeInt32(6, var19);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.detail)) {
         Integer var20 = var1.detail;
         if (var20 != null) {
            var2.writeInt32(7, var20);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.containobserver)) {
         Boolean var21 = var1.containobserver;
         if (var21 != null) {
            var2.writeBool(8, var21);
         }
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.manualrefreshcount)) {
         Integer var22 = var1.manualrefreshcount;
         if (var22 != null) {
            var2.writeInt32(9, var22);
         }
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.autorefreshcount)) {
         Integer var23 = var1.autorefreshcount;
         if (var23 != null) {
            var2.writeInt32(10, var23);
         }
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.name)) {
         String var24 = var1.name;
         if (var24 != null) {
            var2.writeString(11, var24);
         }
      }

   }

   public void writeTo(REQ_SEARCH_PARTY_LIST var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public REQ_SEARCH_PARTY_LIST readFrom(CodedInputStream var1) throws IOException {
      REQ_SEARCH_PARTY_LIST var2 = new REQ_SEARCH_PARTY_LIST();

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
               var2.area = var1.readInt32();
            } else if (var5 == 24) {
               var2.subtype = var1.readInt32();
            } else if (var5 == 32) {
               var2.stageindex = var1.readInt32();
            } else if (var5 == 40) {
               var2.grade = var1.readInt32();
            } else if (var5 == 48) {
               var2.dungeonindex = var1.readInt32();
            } else if (var5 == 56) {
               var2.detail = var1.readInt32();
            } else if (var5 == 64) {
               var2.containobserver = var1.readBool();
            } else if (var5 == 72) {
               var2.manualrefreshcount = var1.readInt32();
            } else if (var5 == 80) {
               var2.autorefreshcount = var1.readInt32();
            } else if (var5 == 90) {
               var2.name = var1.readString();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(REQ_SEARCH_PARTY_LIST.class);
         return this.descriptor = var1;
      }
   }
}
