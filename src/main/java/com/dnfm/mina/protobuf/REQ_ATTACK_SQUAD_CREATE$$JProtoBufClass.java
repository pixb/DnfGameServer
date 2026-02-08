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

public class REQ_ATTACK_SQUAD_CREATE$$JProtoBufClass implements Codec<REQ_ATTACK_SQUAD_CREATE>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(REQ_ATTACK_SQUAD_CREATE var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public REQ_ATTACK_SQUAD_CREATE decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(REQ_ATTACK_SQUAD_CREATE var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.index)) {
         Integer var8 = var1.index;
         var2 += CodedOutputStream.computeInt32Size(1, var8);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.name)) {
         String var9 = var1.name;
         var2 += CodedOutputStream.computeStringSize(2, var9);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.antievilscore)) {
         Integer var10 = var1.antievilscore;
         var2 += CodedOutputStream.computeInt32Size(3, var10);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.publictype)) {
         Integer var11 = var1.publictype;
         var2 += CodedOutputStream.computeInt32Size(4, var11);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.phase)) {
         Integer var12 = var1.phase;
         var2 += CodedOutputStream.computeInt32Size(5, var12);
      }

      return var2;
   }

   public void doWriteTo(REQ_ATTACK_SQUAD_CREATE var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.index)) {
         Integer var8 = var1.index;
         if (var8 != null) {
            var2.writeInt32(1, var8);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.name)) {
         String var9 = var1.name;
         if (var9 != null) {
            var2.writeString(2, var9);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.antievilscore)) {
         Integer var10 = var1.antievilscore;
         if (var10 != null) {
            var2.writeInt32(3, var10);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.publictype)) {
         Integer var11 = var1.publictype;
         if (var11 != null) {
            var2.writeInt32(4, var11);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.phase)) {
         Integer var12 = var1.phase;
         if (var12 != null) {
            var2.writeInt32(5, var12);
         }
      }

   }

   public void writeTo(REQ_ATTACK_SQUAD_CREATE var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public REQ_ATTACK_SQUAD_CREATE readFrom(CodedInputStream var1) throws IOException {
      REQ_ATTACK_SQUAD_CREATE var2 = new REQ_ATTACK_SQUAD_CREATE();

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 8) {
               var2.index = var1.readInt32();
            } else if (var5 == 18) {
               var2.name = var1.readString();
            } else if (var5 == 24) {
               var2.antievilscore = var1.readInt32();
            } else if (var5 == 32) {
               var2.publictype = var1.readInt32();
            } else if (var5 == 40) {
               var2.phase = var1.readInt32();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(REQ_ATTACK_SQUAD_CREATE.class);
         return this.descriptor = var1;
      }
   }
}
