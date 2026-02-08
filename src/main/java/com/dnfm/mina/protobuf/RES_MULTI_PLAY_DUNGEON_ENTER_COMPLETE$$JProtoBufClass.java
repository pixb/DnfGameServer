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

public class RES_MULTI_PLAY_DUNGEON_ENTER_COMPLETE$$JProtoBufClass implements Codec<RES_MULTI_PLAY_DUNGEON_ENTER_COMPLETE>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(RES_MULTI_PLAY_DUNGEON_ENTER_COMPLETE var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public RES_MULTI_PLAY_DUNGEON_ENTER_COMPLETE decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(RES_MULTI_PLAY_DUNGEON_ENTER_COMPLETE var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var11 = var1.error;
         var2 += CodedOutputStream.computeInt32Size(1, var11);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.fatigue)) {
         Integer var12 = var1.fatigue;
         var2 += CodedOutputStream.computeInt32Size(2, var12);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.consumefatigue)) {
         Integer var13 = var1.consumefatigue;
         var2 += CodedOutputStream.computeInt32Size(3, var13);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.gold)) {
         Integer var14 = var1.gold;
         var2 += CodedOutputStream.computeInt32Size(4, var14);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.tera)) {
         Integer var15 = var1.tera;
         var2 += CodedOutputStream.computeInt32Size(5, var15);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.partyguid)) {
         Long var16 = var1.partyguid;
         var2 += CodedOutputStream.computeUInt64Size(6, var16);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.adventureticket)) {
         List var17 = var1.adventureticket;
         var2 += CodedConstant.computeListSize(7, var17, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.isrewardlimit)) {
         Boolean var18 = var1.isrewardlimit;
         var2 += CodedOutputStream.computeBoolSize(8, var18);
      }

      return var2;
   }

   public void doWriteTo(RES_MULTI_PLAY_DUNGEON_ENTER_COMPLETE var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var11 = var1.error;
         if (var11 != null) {
            var2.writeInt32(1, var11);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.fatigue)) {
         Integer var12 = var1.fatigue;
         if (var12 != null) {
            var2.writeInt32(2, var12);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.consumefatigue)) {
         Integer var13 = var1.consumefatigue;
         if (var13 != null) {
            var2.writeInt32(3, var13);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.gold)) {
         Integer var14 = var1.gold;
         if (var14 != null) {
            var2.writeInt32(4, var14);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.tera)) {
         Integer var15 = var1.tera;
         if (var15 != null) {
            var2.writeInt32(5, var15);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.partyguid)) {
         Long var16 = var1.partyguid;
         if (var16 != null) {
            var2.writeUInt64(6, var16);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.adventureticket)) {
         List var17 = var1.adventureticket;
         if (var17 != null) {
            CodedConstant.writeToList(var2, 7, FieldType.OBJECT, var17, false);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.isrewardlimit)) {
         Boolean var18 = var1.isrewardlimit;
         if (var18 != null) {
            var2.writeBool(8, var18);
         }
      }

   }

   public void writeTo(RES_MULTI_PLAY_DUNGEON_ENTER_COMPLETE var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public RES_MULTI_PLAY_DUNGEON_ENTER_COMPLETE readFrom(CodedInputStream var1) throws IOException {
      RES_MULTI_PLAY_DUNGEON_ENTER_COMPLETE var2 = new RES_MULTI_PLAY_DUNGEON_ENTER_COMPLETE();
      var2.adventureticket = new ArrayList();

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
               var2.fatigue = var1.readInt32();
            } else if (var5 == 24) {
               var2.consumefatigue = var1.readInt32();
            } else if (var5 == 32) {
               var2.gold = var1.readInt32();
            } else if (var5 == 40) {
               var2.tera = var1.readInt32();
            } else if (var5 == 48) {
               var2.partyguid = var1.readUInt64();
            } else if (var5 == 58) {
               Codec var10 = ProtobufProxy.create(PT_ACCOUNT_TICKET.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.adventureticket == null) {
                  var2.adventureticket = new ArrayList();
               }

               var2.adventureticket.add((PT_ACCOUNT_TICKET)var10.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var7);
            } else if (var5 == 64) {
               var2.isrewardlimit = var1.readBool();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(RES_MULTI_PLAY_DUNGEON_ENTER_COMPLETE.class);
         return this.descriptor = var1;
      }
   }
}
