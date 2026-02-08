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

public class REQ_GUILD_BINGO_GET_REWARD$$JProtoBufClass implements Codec<REQ_GUILD_BINGO_GET_REWARD>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(REQ_GUILD_BINGO_GET_REWARD var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public REQ_GUILD_BINGO_GET_REWARD decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(REQ_GUILD_BINGO_GET_REWARD var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.maplevel)) {
         Integer var5 = var1.maplevel;
         var2 += CodedOutputStream.computeInt32Size(1, var5);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.rewardindices)) {
         List var6 = var1.rewardindices;
         var2 += CodedConstant.computeListSize(2, var6, FieldType.INT32, false, (File)ProtobufProxy.OUTPUT_PATH.get(), true);
      }

      return var2;
   }

   public void doWriteTo(REQ_GUILD_BINGO_GET_REWARD var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.maplevel)) {
         Integer var5 = var1.maplevel;
         if (var5 != null) {
            var2.writeInt32(1, var5);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.rewardindices)) {
         List var6 = var1.rewardindices;
         if (var6 != null) {
            CodedConstant.writeToList(var2, 2, FieldType.INT32, var6, true);
         }
      }

   }

   public void writeTo(REQ_GUILD_BINGO_GET_REWARD var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public REQ_GUILD_BINGO_GET_REWARD readFrom(CodedInputStream var1) throws IOException {
      REQ_GUILD_BINGO_GET_REWARD var2 = new REQ_GUILD_BINGO_GET_REWARD();
      var2.rewardindices = new ArrayList();

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 8) {
               var2.maplevel = var1.readInt32();
            } else if (var5 == 16) {
               if (var2.rewardindices == null) {
                  var2.rewardindices = new ArrayList();
               }

               var2.rewardindices.add(var1.readInt32());
            } else if (var5 != 18) {
               var1.skipField(var5);
            } else {
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.rewardindices == null) {
                  var2.rewardindices = new ArrayList();
               }

               while(var1.getBytesUntilLimit() > 0) {
                  var2.rewardindices.add(var1.readInt32());
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(REQ_GUILD_BINGO_GET_REWARD.class);
         return this.descriptor = var1;
      }
   }
}
