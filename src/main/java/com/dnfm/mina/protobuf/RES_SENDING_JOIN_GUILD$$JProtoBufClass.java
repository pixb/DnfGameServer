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

public class RES_SENDING_JOIN_GUILD$$JProtoBufClass implements Codec<RES_SENDING_JOIN_GUILD>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(RES_SENDING_JOIN_GUILD var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public RES_SENDING_JOIN_GUILD decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(RES_SENDING_JOIN_GUILD var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var14 = var1.error;
         var2 += CodedOutputStream.computeInt32Size(1, var14);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.gguid)) {
         Long var15 = var1.gguid;
         var2 += CodedOutputStream.computeUInt64Size(2, var15);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.gname)) {
         String var16 = var1.gname;
         var2 += CodedOutputStream.computeStringSize(3, var16);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.glevel)) {
         Integer var17 = var1.glevel;
         var2 += CodedOutputStream.computeInt32Size(4, var17);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.gsymbol)) {
         List var18 = var1.gsymbol;
         var2 += CodedConstant.computeListSize(5, var18, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.gmastername)) {
         String var19 = var1.gmastername;
         var2 += CodedOutputStream.computeStringSize(6, var19);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.gmembercount)) {
         Integer var20 = var1.gmembercount;
         var2 += CodedOutputStream.computeInt32Size(7, var20);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.gboard)) {
         String var21 = var1.gboard;
         var2 += CodedOutputStream.computeStringSize(8, var21);
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.freejoin)) {
         Boolean var22 = var1.freejoin;
         var2 += CodedOutputStream.computeBoolSize(9, var22);
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.minlevel)) {
         Integer var23 = var1.minlevel;
         var2 += CodedOutputStream.computeInt32Size(10, var23);
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.lastkicktime)) {
         Long var24 = var1.lastkicktime;
         var2 += CodedOutputStream.computeInt64Size(11, var24);
      }

      return var2;
   }

   public void doWriteTo(RES_SENDING_JOIN_GUILD var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var14 = var1.error;
         if (var14 != null) {
            var2.writeInt32(1, var14);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.gguid)) {
         Long var15 = var1.gguid;
         if (var15 != null) {
            var2.writeUInt64(2, var15);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.gname)) {
         String var16 = var1.gname;
         if (var16 != null) {
            var2.writeString(3, var16);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.glevel)) {
         Integer var17 = var1.glevel;
         if (var17 != null) {
            var2.writeInt32(4, var17);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.gsymbol)) {
         List var18 = var1.gsymbol;
         if (var18 != null) {
            CodedConstant.writeToList(var2, 5, FieldType.OBJECT, var18, false);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.gmastername)) {
         String var19 = var1.gmastername;
         if (var19 != null) {
            var2.writeString(6, var19);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.gmembercount)) {
         Integer var20 = var1.gmembercount;
         if (var20 != null) {
            var2.writeInt32(7, var20);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.gboard)) {
         String var21 = var1.gboard;
         if (var21 != null) {
            var2.writeString(8, var21);
         }
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.freejoin)) {
         Boolean var22 = var1.freejoin;
         if (var22 != null) {
            var2.writeBool(9, var22);
         }
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.minlevel)) {
         Integer var23 = var1.minlevel;
         if (var23 != null) {
            var2.writeInt32(10, var23);
         }
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.lastkicktime)) {
         Long var24 = var1.lastkicktime;
         if (var24 != null) {
            var2.writeInt64(11, var24);
         }
      }

   }

   public void writeTo(RES_SENDING_JOIN_GUILD var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public RES_SENDING_JOIN_GUILD readFrom(CodedInputStream var1) throws IOException {
      RES_SENDING_JOIN_GUILD var2 = new RES_SENDING_JOIN_GUILD();
      var2.gsymbol = new ArrayList();

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
               var2.gguid = var1.readUInt64();
            } else if (var5 == 26) {
               var2.gname = var1.readString();
            } else if (var5 == 32) {
               var2.glevel = var1.readInt32();
            } else if (var5 == 42) {
               Codec var10 = ProtobufProxy.create(PT_GUILD_SYMBOL.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.gsymbol == null) {
                  var2.gsymbol = new ArrayList();
               }

               var2.gsymbol.add((PT_GUILD_SYMBOL)var10.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var7);
            } else if (var5 == 50) {
               var2.gmastername = var1.readString();
            } else if (var5 == 56) {
               var2.gmembercount = var1.readInt32();
            } else if (var5 == 66) {
               var2.gboard = var1.readString();
            } else if (var5 == 72) {
               var2.freejoin = var1.readBool();
            } else if (var5 == 80) {
               var2.minlevel = var1.readInt32();
            } else if (var5 == 88) {
               var2.lastkicktime = var1.readInt64();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(RES_SENDING_JOIN_GUILD.class);
         return this.descriptor = var1;
      }
   }
}
