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

public class PT_GUILD_REDPACKET_INFO$$JProtoBufClass implements Codec<PT_GUILD_REDPACKET_INFO>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(PT_GUILD_REDPACKET_INFO var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public PT_GUILD_REDPACKET_INFO decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(PT_GUILD_REDPACKET_INFO var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.guid)) {
         Long var15 = var1.guid;
         var2 += CodedOutputStream.computeUInt64Size(1, var15);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.senderguid)) {
         Long var16 = var1.senderguid;
         var2 += CodedOutputStream.computeUInt64Size(2, var16);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.sendername)) {
         String var17 = var1.sendername;
         var2 += CodedOutputStream.computeStringSize(3, var17);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.senderjob)) {
         Integer var18 = var1.senderjob;
         var2 += CodedOutputStream.computeInt32Size(4, var18);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.sendergrowth)) {
         Integer var19 = var1.sendergrowth;
         var2 += CodedOutputStream.computeInt32Size(5, var19);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.sendersecondgrowth)) {
         Integer var20 = var1.sendersecondgrowth;
         var2 += CodedOutputStream.computeInt32Size(6, var20);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.achvindex)) {
         Integer var21 = var1.achvindex;
         var2 += CodedOutputStream.computeInt32Size(7, var21);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.recvcount)) {
         Integer var22 = var1.recvcount;
         var2 += CodedOutputStream.computeInt32Size(8, var22);
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.itemindex)) {
         Integer var23 = var1.itemindex;
         var2 += CodedOutputStream.computeInt32Size(9, var23);
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.totalvalue)) {
         Integer var24 = var1.totalvalue;
         var2 += CodedOutputStream.computeInt32Size(10, var24);
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.alreadyrecv)) {
         Integer var25 = var1.alreadyrecv;
         var2 += CodedOutputStream.computeInt32Size(11, var25);
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.createtime)) {
         Long var26 = var1.createtime;
         var2 += CodedOutputStream.computeInt64Size(12, var26);
      }

      return var2;
   }

   public void doWriteTo(PT_GUILD_REDPACKET_INFO var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.guid)) {
         Long var15 = var1.guid;
         if (var15 != null) {
            var2.writeUInt64(1, var15);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.senderguid)) {
         Long var16 = var1.senderguid;
         if (var16 != null) {
            var2.writeUInt64(2, var16);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.sendername)) {
         String var17 = var1.sendername;
         if (var17 != null) {
            var2.writeString(3, var17);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.senderjob)) {
         Integer var18 = var1.senderjob;
         if (var18 != null) {
            var2.writeInt32(4, var18);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.sendergrowth)) {
         Integer var19 = var1.sendergrowth;
         if (var19 != null) {
            var2.writeInt32(5, var19);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.sendersecondgrowth)) {
         Integer var20 = var1.sendersecondgrowth;
         if (var20 != null) {
            var2.writeInt32(6, var20);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.achvindex)) {
         Integer var21 = var1.achvindex;
         if (var21 != null) {
            var2.writeInt32(7, var21);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.recvcount)) {
         Integer var22 = var1.recvcount;
         if (var22 != null) {
            var2.writeInt32(8, var22);
         }
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.itemindex)) {
         Integer var23 = var1.itemindex;
         if (var23 != null) {
            var2.writeInt32(9, var23);
         }
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.totalvalue)) {
         Integer var24 = var1.totalvalue;
         if (var24 != null) {
            var2.writeInt32(10, var24);
         }
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.alreadyrecv)) {
         Integer var25 = var1.alreadyrecv;
         if (var25 != null) {
            var2.writeInt32(11, var25);
         }
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.createtime)) {
         Long var26 = var1.createtime;
         if (var26 != null) {
            var2.writeInt64(12, var26);
         }
      }

   }

   public void writeTo(PT_GUILD_REDPACKET_INFO var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public PT_GUILD_REDPACKET_INFO readFrom(CodedInputStream var1) throws IOException {
      PT_GUILD_REDPACKET_INFO var2 = new PT_GUILD_REDPACKET_INFO();

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 8) {
               var2.guid = var1.readUInt64();
            } else if (var5 == 16) {
               var2.senderguid = var1.readUInt64();
            } else if (var5 == 26) {
               var2.sendername = var1.readString();
            } else if (var5 == 32) {
               var2.senderjob = var1.readInt32();
            } else if (var5 == 40) {
               var2.sendergrowth = var1.readInt32();
            } else if (var5 == 48) {
               var2.sendersecondgrowth = var1.readInt32();
            } else if (var5 == 56) {
               var2.achvindex = var1.readInt32();
            } else if (var5 == 64) {
               var2.recvcount = var1.readInt32();
            } else if (var5 == 72) {
               var2.itemindex = var1.readInt32();
            } else if (var5 == 80) {
               var2.totalvalue = var1.readInt32();
            } else if (var5 == 88) {
               var2.alreadyrecv = var1.readInt32();
            } else if (var5 == 96) {
               var2.createtime = var1.readInt64();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(PT_GUILD_REDPACKET_INFO.class);
         return this.descriptor = var1;
      }
   }
}
