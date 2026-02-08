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

public class RES_INQUIRE_PERSONAL_RANKING$$JProtoBufClass implements Codec<RES_INQUIRE_PERSONAL_RANKING>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(RES_INQUIRE_PERSONAL_RANKING var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public RES_INQUIRE_PERSONAL_RANKING decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(RES_INQUIRE_PERSONAL_RANKING var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var17 = var1.error;
         var2 += CodedOutputStream.computeInt32Size(1, var17);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.count)) {
         Long var18 = var1.count;
         var2 += CodedOutputStream.computeUInt64Size(2, var18);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.rank)) {
         Long var19 = var1.rank;
         var2 += CodedOutputStream.computeInt64Size(3, var19);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.maxrank)) {
         Long var20 = var1.maxrank;
         var2 += CodedOutputStream.computeInt64Size(4, var20);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.job)) {
         Integer var21 = var1.job;
         var2 += CodedOutputStream.computeInt32Size(5, var21);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.growtype)) {
         Integer var22 = var1.growtype;
         var2 += CodedOutputStream.computeInt32Size(6, var22);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.score)) {
         Long var23 = var1.score;
         var2 += CodedOutputStream.computeUInt64Size(7, var23);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.ranking)) {
         List var24 = var1.ranking;
         var2 += CodedConstant.computeListSize(8, var24, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.wincount)) {
         Integer var25 = var1.wincount;
         var2 += CodedOutputStream.computeInt32Size(9, var25);
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.gwinpoint)) {
         Long var26 = var1.gwinpoint;
         var2 += CodedOutputStream.computeUInt64Size(10, var26);
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.winningrate)) {
         Integer var27 = var1.winningrate;
         var2 += CodedOutputStream.computeInt32Size(11, var27);
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.gamecenterinfo)) {
         Integer var28 = var1.gamecenterinfo;
         var2 += CodedOutputStream.computeInt32Size(12, var28);
      }

      Object var15 = null;
      if (!CodedConstant.isNull(var1.qqVipinfo)) {
         Integer var29 = var1.qqVipinfo;
         var2 += CodedOutputStream.computeInt32Size(13, var29);
      }

      Object var16 = null;
      if (!CodedConstant.isNull(var1.characterframe)) {
         Integer var30 = var1.characterframe;
         var2 += CodedOutputStream.computeInt32Size(14, var30);
      }

      return var2;
   }

   public void doWriteTo(RES_INQUIRE_PERSONAL_RANKING var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var17 = var1.error;
         if (var17 != null) {
            var2.writeInt32(1, var17);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.count)) {
         Long var18 = var1.count;
         if (var18 != null) {
            var2.writeUInt64(2, var18);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.rank)) {
         Long var19 = var1.rank;
         if (var19 != null) {
            var2.writeInt64(3, var19);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.maxrank)) {
         Long var20 = var1.maxrank;
         if (var20 != null) {
            var2.writeInt64(4, var20);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.job)) {
         Integer var21 = var1.job;
         if (var21 != null) {
            var2.writeInt32(5, var21);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.growtype)) {
         Integer var22 = var1.growtype;
         if (var22 != null) {
            var2.writeInt32(6, var22);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.score)) {
         Long var23 = var1.score;
         if (var23 != null) {
            var2.writeUInt64(7, var23);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.ranking)) {
         List var24 = var1.ranking;
         if (var24 != null) {
            CodedConstant.writeToList(var2, 8, FieldType.OBJECT, var24, false);
         }
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.wincount)) {
         Integer var25 = var1.wincount;
         if (var25 != null) {
            var2.writeInt32(9, var25);
         }
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.gwinpoint)) {
         Long var26 = var1.gwinpoint;
         if (var26 != null) {
            var2.writeUInt64(10, var26);
         }
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.winningrate)) {
         Integer var27 = var1.winningrate;
         if (var27 != null) {
            var2.writeInt32(11, var27);
         }
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.gamecenterinfo)) {
         Integer var28 = var1.gamecenterinfo;
         if (var28 != null) {
            var2.writeInt32(12, var28);
         }
      }

      Object var15 = null;
      if (!CodedConstant.isNull(var1.qqVipinfo)) {
         Integer var29 = var1.qqVipinfo;
         if (var29 != null) {
            var2.writeInt32(13, var29);
         }
      }

      Object var16 = null;
      if (!CodedConstant.isNull(var1.characterframe)) {
         Integer var30 = var1.characterframe;
         if (var30 != null) {
            var2.writeInt32(14, var30);
         }
      }

   }

   public void writeTo(RES_INQUIRE_PERSONAL_RANKING var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public RES_INQUIRE_PERSONAL_RANKING readFrom(CodedInputStream var1) throws IOException {
      RES_INQUIRE_PERSONAL_RANKING var2 = new RES_INQUIRE_PERSONAL_RANKING();
      var2.ranking = new ArrayList();

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
               var2.count = var1.readUInt64();
            } else if (var5 == 24) {
               var2.rank = var1.readInt64();
            } else if (var5 == 32) {
               var2.maxrank = var1.readInt64();
            } else if (var5 == 40) {
               var2.job = var1.readInt32();
            } else if (var5 == 48) {
               var2.growtype = var1.readInt32();
            } else if (var5 == 56) {
               var2.score = var1.readUInt64();
            } else if (var5 == 66) {
               Codec var10 = ProtobufProxy.create(PT_RANKING.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.ranking == null) {
                  var2.ranking = new ArrayList();
               }

               var2.ranking.add((PT_RANKING)var10.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var7);
            } else if (var5 == 72) {
               var2.wincount = var1.readInt32();
            } else if (var5 == 80) {
               var2.gwinpoint = var1.readUInt64();
            } else if (var5 == 88) {
               var2.winningrate = var1.readInt32();
            } else if (var5 == 96) {
               var2.gamecenterinfo = var1.readInt32();
            } else if (var5 == 104) {
               var2.qqVipinfo = var1.readInt32();
            } else if (var5 == 112) {
               var2.characterframe = var1.readInt32();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(RES_INQUIRE_PERSONAL_RANKING.class);
         return this.descriptor = var1;
      }
   }
}
