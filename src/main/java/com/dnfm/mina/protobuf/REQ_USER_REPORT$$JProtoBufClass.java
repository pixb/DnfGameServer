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

public class REQ_USER_REPORT$$JProtoBufClass implements Codec<REQ_USER_REPORT>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(REQ_USER_REPORT var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public REQ_USER_REPORT decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(REQ_USER_REPORT var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.picurl)) {
         String var19 = var1.picurl;
         var2 += CodedOutputStream.computeStringSize(1, var19);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.targetguid)) {
         Long var20 = var1.targetguid;
         var2 += CodedOutputStream.computeUInt64Size(2, var20);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.targetname)) {
         String var21 = var1.targetname;
         var2 += CodedOutputStream.computeStringSize(3, var21);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.reportscene)) {
         Integer var22 = var1.reportscene;
         var2 += CodedOutputStream.computeInt32Size(4, var22);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.reportentrance)) {
         Integer var23 = var1.reportentrance;
         var2 += CodedOutputStream.computeInt32Size(5, var23);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.reporttype)) {
         Integer var24 = var1.reporttype;
         var2 += CodedOutputStream.computeInt32Size(6, var24);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.reportsubtype)) {
         Long var25 = var1.reportsubtype;
         var2 += CodedOutputStream.computeInt64Size(7, var25);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.reportdesc)) {
         String var26 = var1.reportdesc;
         var2 += CodedOutputStream.computeStringSize(8, var26);
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.reportchatcontents)) {
         String var27 = var1.reportchatcontents;
         var2 += CodedOutputStream.computeStringSize(9, var27);
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.reportautograph)) {
         String var28 = var1.reportautograph;
         var2 += CodedOutputStream.computeStringSize(10, var28);
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.reportpicurl)) {
         String var29 = var1.reportpicurl;
         var2 += CodedOutputStream.computeStringSize(11, var29);
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.reportpicurllist)) {
         String var30 = var1.reportpicurllist;
         var2 += CodedOutputStream.computeStringSize(12, var30);
      }

      Object var15 = null;
      if (!CodedConstant.isNull(var1.blacklist)) {
         Boolean var31 = var1.blacklist;
         var2 += CodedOutputStream.computeBoolSize(13, var31);
      }

      Object var16 = null;
      if (!CodedConstant.isNull(var1.partyguid)) {
         Long var32 = var1.partyguid;
         var2 += CodedOutputStream.computeUInt64Size(14, var32);
      }

      Object var17 = null;
      if (!CodedConstant.isNull(var1.dungeonguid)) {
         Long var33 = var1.dungeonguid;
         var2 += CodedOutputStream.computeUInt64Size(15, var33);
      }

      Object var18 = null;
      if (!CodedConstant.isNull(var1.reportchatsubstance)) {
         String var34 = var1.reportchatsubstance;
         var2 += CodedOutputStream.computeStringSize(16, var34);
      }

      return var2;
   }

   public void doWriteTo(REQ_USER_REPORT var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.picurl)) {
         String var19 = var1.picurl;
         if (var19 != null) {
            var2.writeString(1, var19);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.targetguid)) {
         Long var20 = var1.targetguid;
         if (var20 != null) {
            var2.writeUInt64(2, var20);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.targetname)) {
         String var21 = var1.targetname;
         if (var21 != null) {
            var2.writeString(3, var21);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.reportscene)) {
         Integer var22 = var1.reportscene;
         if (var22 != null) {
            var2.writeInt32(4, var22);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.reportentrance)) {
         Integer var23 = var1.reportentrance;
         if (var23 != null) {
            var2.writeInt32(5, var23);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.reporttype)) {
         Integer var24 = var1.reporttype;
         if (var24 != null) {
            var2.writeInt32(6, var24);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.reportsubtype)) {
         Long var25 = var1.reportsubtype;
         if (var25 != null) {
            var2.writeInt64(7, var25);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.reportdesc)) {
         String var26 = var1.reportdesc;
         if (var26 != null) {
            var2.writeString(8, var26);
         }
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.reportchatcontents)) {
         String var27 = var1.reportchatcontents;
         if (var27 != null) {
            var2.writeString(9, var27);
         }
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.reportautograph)) {
         String var28 = var1.reportautograph;
         if (var28 != null) {
            var2.writeString(10, var28);
         }
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.reportpicurl)) {
         String var29 = var1.reportpicurl;
         if (var29 != null) {
            var2.writeString(11, var29);
         }
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.reportpicurllist)) {
         String var30 = var1.reportpicurllist;
         if (var30 != null) {
            var2.writeString(12, var30);
         }
      }

      Object var15 = null;
      if (!CodedConstant.isNull(var1.blacklist)) {
         Boolean var31 = var1.blacklist;
         if (var31 != null) {
            var2.writeBool(13, var31);
         }
      }

      Object var16 = null;
      if (!CodedConstant.isNull(var1.partyguid)) {
         Long var32 = var1.partyguid;
         if (var32 != null) {
            var2.writeUInt64(14, var32);
         }
      }

      Object var17 = null;
      if (!CodedConstant.isNull(var1.dungeonguid)) {
         Long var33 = var1.dungeonguid;
         if (var33 != null) {
            var2.writeUInt64(15, var33);
         }
      }

      Object var18 = null;
      if (!CodedConstant.isNull(var1.reportchatsubstance)) {
         String var34 = var1.reportchatsubstance;
         if (var34 != null) {
            var2.writeString(16, var34);
         }
      }

   }

   public void writeTo(REQ_USER_REPORT var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public REQ_USER_REPORT readFrom(CodedInputStream var1) throws IOException {
      REQ_USER_REPORT var2 = new REQ_USER_REPORT();

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 10) {
               var2.picurl = var1.readString();
            } else if (var5 == 16) {
               var2.targetguid = var1.readUInt64();
            } else if (var5 == 26) {
               var2.targetname = var1.readString();
            } else if (var5 == 32) {
               var2.reportscene = var1.readInt32();
            } else if (var5 == 40) {
               var2.reportentrance = var1.readInt32();
            } else if (var5 == 48) {
               var2.reporttype = var1.readInt32();
            } else if (var5 == 56) {
               var2.reportsubtype = var1.readInt64();
            } else if (var5 == 66) {
               var2.reportdesc = var1.readString();
            } else if (var5 == 74) {
               var2.reportchatcontents = var1.readString();
            } else if (var5 == 82) {
               var2.reportautograph = var1.readString();
            } else if (var5 == 90) {
               var2.reportpicurl = var1.readString();
            } else if (var5 == 98) {
               var2.reportpicurllist = var1.readString();
            } else if (var5 == 104) {
               var2.blacklist = var1.readBool();
            } else if (var5 == 112) {
               var2.partyguid = var1.readUInt64();
            } else if (var5 == 120) {
               var2.dungeonguid = var1.readUInt64();
            } else if (var5 == 130) {
               var2.reportchatsubstance = var1.readString();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(REQ_USER_REPORT.class);
         return this.descriptor = var1;
      }
   }
}
