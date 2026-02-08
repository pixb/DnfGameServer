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

public class PT_RECOMMEND_GUILD$$JProtoBufClass implements Codec<PT_RECOMMEND_GUILD>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(PT_RECOMMEND_GUILD var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public PT_RECOMMEND_GUILD decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(PT_RECOMMEND_GUILD var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.gguid)) {
         Long var16 = var1.gguid;
         var2 += CodedOutputStream.computeUInt64Size(1, var16);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.gboard)) {
         String var17 = var1.gboard;
         var2 += CodedOutputStream.computeStringSize(2, var17);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.minlevel)) {
         Integer var18 = var1.minlevel;
         var2 += CodedOutputStream.computeInt32Size(3, var18);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.area)) {
         Integer var19 = var1.area;
         var2 += CodedOutputStream.computeInt32Size(4, var19);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.freejoin)) {
         Boolean var20 = var1.freejoin;
         var2 += CodedOutputStream.computeBoolSize(5, var20);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.gname)) {
         String var21 = var1.gname;
         var2 += CodedOutputStream.computeStringSize(6, var21);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.glevel)) {
         Integer var22 = var1.glevel;
         var2 += CodedOutputStream.computeInt32Size(7, var22);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.gsymbol)) {
         List var23 = var1.gsymbol;
         var2 += CodedConstant.computeListSize(8, var23, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.gmastername)) {
         String var24 = var1.gmastername;
         var2 += CodedOutputStream.computeStringSize(9, var24);
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.gmembercount)) {
         Integer var25 = var1.gmembercount;
         var2 += CodedOutputStream.computeInt32Size(10, var25);
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.gmembermax)) {
         Integer var26 = var1.gmembermax;
         var2 += CodedOutputStream.computeInt32Size(11, var26);
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.girankingscore)) {
         Integer var27 = var1.girankingscore;
         var2 += CodedOutputStream.computeInt32Size(12, var27);
      }

      Object var15 = null;
      if (!CodedConstant.isNull(var1.gfacility)) {
         List var28 = var1.gfacility;
         var2 += CodedConstant.computeListSize(13, var28, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      return var2;
   }

   public void doWriteTo(PT_RECOMMEND_GUILD var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.gguid)) {
         Long var16 = var1.gguid;
         if (var16 != null) {
            var2.writeUInt64(1, var16);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.gboard)) {
         String var17 = var1.gboard;
         if (var17 != null) {
            var2.writeString(2, var17);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.minlevel)) {
         Integer var18 = var1.minlevel;
         if (var18 != null) {
            var2.writeInt32(3, var18);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.area)) {
         Integer var19 = var1.area;
         if (var19 != null) {
            var2.writeInt32(4, var19);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.freejoin)) {
         Boolean var20 = var1.freejoin;
         if (var20 != null) {
            var2.writeBool(5, var20);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.gname)) {
         String var21 = var1.gname;
         if (var21 != null) {
            var2.writeString(6, var21);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.glevel)) {
         Integer var22 = var1.glevel;
         if (var22 != null) {
            var2.writeInt32(7, var22);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.gsymbol)) {
         List var23 = var1.gsymbol;
         if (var23 != null) {
            CodedConstant.writeToList(var2, 8, FieldType.OBJECT, var23, false);
         }
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.gmastername)) {
         String var24 = var1.gmastername;
         if (var24 != null) {
            var2.writeString(9, var24);
         }
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.gmembercount)) {
         Integer var25 = var1.gmembercount;
         if (var25 != null) {
            var2.writeInt32(10, var25);
         }
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.gmembermax)) {
         Integer var26 = var1.gmembermax;
         if (var26 != null) {
            var2.writeInt32(11, var26);
         }
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.girankingscore)) {
         Integer var27 = var1.girankingscore;
         if (var27 != null) {
            var2.writeInt32(12, var27);
         }
      }

      Object var15 = null;
      if (!CodedConstant.isNull(var1.gfacility)) {
         List var28 = var1.gfacility;
         if (var28 != null) {
            CodedConstant.writeToList(var2, 13, FieldType.OBJECT, var28, false);
         }
      }

   }

   public void writeTo(PT_RECOMMEND_GUILD var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public PT_RECOMMEND_GUILD readFrom(CodedInputStream var1) throws IOException {
      PT_RECOMMEND_GUILD var2 = new PT_RECOMMEND_GUILD();
      var2.gsymbol = new ArrayList();
      var2.gfacility = new ArrayList();

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 8) {
               var2.gguid = var1.readUInt64();
            } else if (var5 == 18) {
               var2.gboard = var1.readString();
            } else if (var5 == 24) {
               var2.minlevel = var1.readInt32();
            } else if (var5 == 32) {
               var2.area = var1.readInt32();
            } else if (var5 == 40) {
               var2.freejoin = var1.readBool();
            } else if (var5 == 50) {
               var2.gname = var1.readString();
            } else if (var5 == 56) {
               var2.glevel = var1.readInt32();
            } else if (var5 == 66) {
               Codec var10 = ProtobufProxy.create(PT_GUILD_SYMBOL.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.gsymbol == null) {
                  var2.gsymbol = new ArrayList();
               }

               var2.gsymbol.add((PT_GUILD_SYMBOL)var10.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var7);
            } else if (var5 == 74) {
               var2.gmastername = var1.readString();
            } else if (var5 == 80) {
               var2.gmembercount = var1.readInt32();
            } else if (var5 == 88) {
               var2.gmembermax = var1.readInt32();
            } else if (var5 == 96) {
               var2.girankingscore = var1.readInt32();
            } else if (var5 == 106) {
               Codec var11 = ProtobufProxy.create(PT_FACILITY_INFO.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var12 = var1.readRawVarint32();
               int var13 = var1.pushLimit(var12);
               if (var2.gfacility == null) {
                  var2.gfacility = new ArrayList();
               }

               var2.gfacility.add((PT_FACILITY_INFO)var11.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var13);
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(PT_RECOMMEND_GUILD.class);
         return this.descriptor = var1;
      }
   }
}
