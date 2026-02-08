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

public class PT_ADVENTUREBOOK_INFO$$JProtoBufClass implements Codec<PT_ADVENTUREBOOK_INFO>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(PT_ADVENTUREBOOK_INFO var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public PT_ADVENTUREBOOK_INFO decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(PT_ADVENTUREBOOK_INFO var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.bindex)) {
         Integer var12 = var1.bindex;
         var2 += CodedOutputStream.computeInt32Size(1, var12);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.bstate)) {
         Integer var13 = var1.bstate;
         var2 += CodedOutputStream.computeInt32Size(2, var13);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.charguid)) {
         Long var14 = var1.charguid;
         var2 += CodedOutputStream.computeUInt64Size(3, var14);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.charname)) {
         String var15 = var1.charname;
         var2 += CodedOutputStream.computeStringSize(4, var15);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.growtype)) {
         Integer var16 = var1.growtype;
         var2 += CodedOutputStream.computeInt32Size(5, var16);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.growsecondtype)) {
         Integer var17 = var1.growsecondtype;
         var2 += CodedOutputStream.computeInt32Size(6, var17);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.job)) {
         Integer var18 = var1.job;
         var2 += CodedOutputStream.computeInt32Size(7, var18);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.rewardedcharguid)) {
         List var19 = var1.rewardedcharguid;
         var2 += CodedConstant.computeListSize(8, var19, FieldType.UINT64, false, (File)ProtobufProxy.OUTPUT_PATH.get(), true);
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.terareward)) {
         Boolean var20 = var1.terareward;
         var2 += CodedOutputStream.computeBoolSize(9, var20);
      }

      return var2;
   }

   public void doWriteTo(PT_ADVENTUREBOOK_INFO var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.bindex)) {
         Integer var12 = var1.bindex;
         if (var12 != null) {
            var2.writeInt32(1, var12);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.bstate)) {
         Integer var13 = var1.bstate;
         if (var13 != null) {
            var2.writeInt32(2, var13);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.charguid)) {
         Long var14 = var1.charguid;
         if (var14 != null) {
            var2.writeUInt64(3, var14);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.charname)) {
         String var15 = var1.charname;
         if (var15 != null) {
            var2.writeString(4, var15);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.growtype)) {
         Integer var16 = var1.growtype;
         if (var16 != null) {
            var2.writeInt32(5, var16);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.growsecondtype)) {
         Integer var17 = var1.growsecondtype;
         if (var17 != null) {
            var2.writeInt32(6, var17);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.job)) {
         Integer var18 = var1.job;
         if (var18 != null) {
            var2.writeInt32(7, var18);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.rewardedcharguid)) {
         List var19 = var1.rewardedcharguid;
         if (var19 != null) {
            CodedConstant.writeToList(var2, 8, FieldType.UINT64, var19, true);
         }
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.terareward)) {
         Boolean var20 = var1.terareward;
         if (var20 != null) {
            var2.writeBool(9, var20);
         }
      }

   }

   public void writeTo(PT_ADVENTUREBOOK_INFO var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public PT_ADVENTUREBOOK_INFO readFrom(CodedInputStream var1) throws IOException {
      PT_ADVENTUREBOOK_INFO var2 = new PT_ADVENTUREBOOK_INFO();
      var2.rewardedcharguid = new ArrayList();

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 8) {
               var2.bindex = var1.readInt32();
            } else if (var5 == 16) {
               var2.bstate = var1.readInt32();
            } else if (var5 == 24) {
               var2.charguid = var1.readUInt64();
            } else if (var5 == 34) {
               var2.charname = var1.readString();
            } else if (var5 == 40) {
               var2.growtype = var1.readInt32();
            } else if (var5 == 48) {
               var2.growsecondtype = var1.readInt32();
            } else if (var5 == 56) {
               var2.job = var1.readInt32();
            } else if (var5 == 64) {
               if (var2.rewardedcharguid == null) {
                  var2.rewardedcharguid = new ArrayList();
               }

               var2.rewardedcharguid.add(var1.readUInt64());
            } else if (var5 != 66) {
               if (var5 == 72) {
                  var2.terareward = var1.readBool();
               } else {
                  var1.skipField(var5);
               }
            } else {
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.rewardedcharguid == null) {
                  var2.rewardedcharguid = new ArrayList();
               }

               while(var1.getBytesUntilLimit() > 0) {
                  var2.rewardedcharguid.add(var1.readUInt64());
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(PT_ADVENTUREBOOK_INFO.class);
         return this.descriptor = var1;
      }
   }
}
