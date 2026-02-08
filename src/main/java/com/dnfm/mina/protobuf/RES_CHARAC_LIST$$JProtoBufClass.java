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

public class RES_CHARAC_LIST$$JProtoBufClass implements Codec<RES_CHARAC_LIST>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(RES_CHARAC_LIST var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public RES_CHARAC_LIST decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(RES_CHARAC_LIST var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var15 = var1.error;
         var2 += CodedOutputStream.computeInt32Size(1, var15);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.version)) {
         Integer var16 = var1.version;
         var2 += CodedOutputStream.computeInt32Size(2, var16);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.limits)) {
         List var17 = var1.limits;
         var2 += CodedConstant.computeListSize(3, var17, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.characlist)) {
         List var18 = var1.characlist;
         var2 += CodedConstant.computeListSize(4, var18, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.count)) {
         Integer var19 = var1.count;
         var2 += CodedOutputStream.computeInt32Size(5, var19);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.adventureunionlevel)) {
         Integer var20 = var1.adventureunionlevel;
         var2 += CodedOutputStream.computeInt32Size(6, var20);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.adventureunionexp)) {
         Long var21 = var1.adventureunionexp;
         var2 += CodedOutputStream.computeInt64Size(7, var21);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.dailycreatecharcount)) {
         Integer var22 = var1.dailycreatecharcount;
         var2 += CodedOutputStream.computeInt32Size(8, var22);
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.dailycreatecharmaxcount)) {
         Integer var23 = var1.dailycreatecharmaxcount;
         var2 += CodedOutputStream.computeInt32Size(9, var23);
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.adventureunionname)) {
         String var24 = var1.adventureunionname;
         var2 += CodedOutputStream.computeStringSize(10, var24);
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.maxcount)) {
         Integer var25 = var1.maxcount;
         var2 += CodedOutputStream.computeInt32Size(11, var25);
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.ignorecontentsblacklist)) {
         String var26 = var1.ignorecontentsblacklist;
         var2 += CodedOutputStream.computeStringSize(12, var26);
      }

      return var2;
   }

   public void doWriteTo(RES_CHARAC_LIST var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var15 = var1.error;
         if (var15 != null) {
            var2.writeInt32(1, var15);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.version)) {
         Integer var16 = var1.version;
         if (var16 != null) {
            var2.writeInt32(2, var16);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.limits)) {
         List var17 = var1.limits;
         if (var17 != null) {
            CodedConstant.writeToList(var2, 3, FieldType.OBJECT, var17, false);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.characlist)) {
         List var18 = var1.characlist;
         if (var18 != null) {
            CodedConstant.writeToList(var2, 4, FieldType.OBJECT, var18, false);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.count)) {
         Integer var19 = var1.count;
         if (var19 != null) {
            var2.writeInt32(5, var19);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.adventureunionlevel)) {
         Integer var20 = var1.adventureunionlevel;
         if (var20 != null) {
            var2.writeInt32(6, var20);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.adventureunionexp)) {
         Long var21 = var1.adventureunionexp;
         if (var21 != null) {
            var2.writeInt64(7, var21);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.dailycreatecharcount)) {
         Integer var22 = var1.dailycreatecharcount;
         if (var22 != null) {
            var2.writeInt32(8, var22);
         }
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.dailycreatecharmaxcount)) {
         Integer var23 = var1.dailycreatecharmaxcount;
         if (var23 != null) {
            var2.writeInt32(9, var23);
         }
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.adventureunionname)) {
         String var24 = var1.adventureunionname;
         if (var24 != null) {
            var2.writeString(10, var24);
         }
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.maxcount)) {
         Integer var25 = var1.maxcount;
         if (var25 != null) {
            var2.writeInt32(11, var25);
         }
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.ignorecontentsblacklist)) {
         String var26 = var1.ignorecontentsblacklist;
         if (var26 != null) {
            var2.writeString(12, var26);
         }
      }

   }

   public void writeTo(RES_CHARAC_LIST var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public RES_CHARAC_LIST readFrom(CodedInputStream var1) throws IOException {
      RES_CHARAC_LIST var2 = new RES_CHARAC_LIST();
      var2.limits = new ArrayList();
      var2.characlist = new ArrayList();

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
               var2.version = var1.readInt32();
            } else if (var5 == 26) {
               Codec var10 = ProtobufProxy.create(PT_JOB_LIMIT_INFO.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.limits == null) {
                  var2.limits = new ArrayList();
               }

               var2.limits.add((PT_JOB_LIMIT_INFO)var10.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var7);
            } else if (var5 == 34) {
               Codec var11 = ProtobufProxy.create(PT_CHARACTER.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var12 = var1.readRawVarint32();
               int var13 = var1.pushLimit(var12);
               if (var2.characlist == null) {
                  var2.characlist = new ArrayList();
               }

               var2.characlist.add((PT_CHARACTER)var11.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var13);
            } else if (var5 == 40) {
               var2.count = var1.readInt32();
            } else if (var5 == 48) {
               var2.adventureunionlevel = var1.readInt32();
            } else if (var5 == 56) {
               var2.adventureunionexp = var1.readInt64();
            } else if (var5 == 64) {
               var2.dailycreatecharcount = var1.readInt32();
            } else if (var5 == 72) {
               var2.dailycreatecharmaxcount = var1.readInt32();
            } else if (var5 == 82) {
               var2.adventureunionname = var1.readString();
            } else if (var5 == 88) {
               var2.maxcount = var1.readInt32();
            } else if (var5 == 98) {
               var2.ignorecontentsblacklist = var1.readString();
            } else {
               var1.skipField(var5);
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(RES_CHARAC_LIST.class);
         return this.descriptor = var1;
      }
   }
}
