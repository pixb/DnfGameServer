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

public class RES_GUILD_BINGO_LOAD_DETAIL$$JProtoBufClass implements Codec<RES_GUILD_BINGO_LOAD_DETAIL>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(RES_GUILD_BINGO_LOAD_DETAIL var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public RES_GUILD_BINGO_LOAD_DETAIL decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(RES_GUILD_BINGO_LOAD_DETAIL var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var9 = var1.error;
         var2 += CodedOutputStream.computeInt32Size(1, var9);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.maplevel)) {
         Integer var10 = var1.maplevel;
         var2 += CodedOutputStream.computeInt32Size(2, var10);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.squarelist)) {
         List var11 = var1.squarelist;
         var2 += CodedConstant.computeListSize(3, var11, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.rewardlist)) {
         List var12 = var1.rewardlist;
         var2 += CodedConstant.computeListSize(4, var12, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.undisclosed)) {
         List var13 = var1.undisclosed;
         var2 += CodedConstant.computeListSize(5, var13, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.iscompleted)) {
         Boolean var14 = var1.iscompleted;
         var2 += CodedOutputStream.computeBoolSize(6, var14);
      }

      return var2;
   }

   public void doWriteTo(RES_GUILD_BINGO_LOAD_DETAIL var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var9 = var1.error;
         if (var9 != null) {
            var2.writeInt32(1, var9);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.maplevel)) {
         Integer var10 = var1.maplevel;
         if (var10 != null) {
            var2.writeInt32(2, var10);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.squarelist)) {
         List var11 = var1.squarelist;
         if (var11 != null) {
            CodedConstant.writeToList(var2, 3, FieldType.OBJECT, var11, false);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.rewardlist)) {
         List var12 = var1.rewardlist;
         if (var12 != null) {
            CodedConstant.writeToList(var2, 4, FieldType.OBJECT, var12, false);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.undisclosed)) {
         List var13 = var1.undisclosed;
         if (var13 != null) {
            CodedConstant.writeToList(var2, 5, FieldType.OBJECT, var13, false);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.iscompleted)) {
         Boolean var14 = var1.iscompleted;
         if (var14 != null) {
            var2.writeBool(6, var14);
         }
      }

   }

   public void writeTo(RES_GUILD_BINGO_LOAD_DETAIL var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public RES_GUILD_BINGO_LOAD_DETAIL readFrom(CodedInputStream var1) throws IOException {
      RES_GUILD_BINGO_LOAD_DETAIL var2 = new RES_GUILD_BINGO_LOAD_DETAIL();
      var2.squarelist = new ArrayList();
      var2.rewardlist = new ArrayList();
      var2.undisclosed = new ArrayList();

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
               var2.maplevel = var1.readInt32();
            } else if (var5 == 26) {
               Codec var10 = ProtobufProxy.create(PT_GUILD_BINGO_SQUARE_INFO.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.squarelist == null) {
                  var2.squarelist = new ArrayList();
               }

               var2.squarelist.add((PT_GUILD_BINGO_SQUARE_INFO)var10.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var7);
            } else if (var5 == 34) {
               Codec var11 = ProtobufProxy.create(PT_GUILD_BINGO_REWARD_INFO.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var13 = var1.readRawVarint32();
               int var15 = var1.pushLimit(var13);
               if (var2.rewardlist == null) {
                  var2.rewardlist = new ArrayList();
               }

               var2.rewardlist.add((PT_GUILD_BINGO_REWARD_INFO)var11.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var15);
            } else if (var5 == 42) {
               Codec var12 = ProtobufProxy.create(PT_GUILD_BINGO_UNDIS_CLOSED_INFO.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var14 = var1.readRawVarint32();
               int var16 = var1.pushLimit(var14);
               if (var2.undisclosed == null) {
                  var2.undisclosed = new ArrayList();
               }

               var2.undisclosed.add((PT_GUILD_BINGO_UNDIS_CLOSED_INFO)var12.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var16);
            } else if (var5 == 48) {
               var2.iscompleted = var1.readBool();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(RES_GUILD_BINGO_LOAD_DETAIL.class);
         return this.descriptor = var1;
      }
   }
}
