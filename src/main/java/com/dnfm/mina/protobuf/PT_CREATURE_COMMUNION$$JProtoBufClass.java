package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.Codec;
import com.baidu.bjf.remoting.protobuf.EnumHandler;
import com.baidu.bjf.remoting.protobuf.code.CodedConstant;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.WireFormat.FieldType;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class PT_CREATURE_COMMUNION$$JProtoBufClass implements Codec<PT_CREATURE_COMMUNION>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(PT_CREATURE_COMMUNION var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public PT_CREATURE_COMMUNION decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(PT_CREATURE_COMMUNION var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.level)) {
         Integer var7 = var1.level;
         var2 += CodedOutputStream.computeInt32Size(1, var7);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.selectedslot)) {
         Integer var8 = var1.selectedslot;
         var2 += CodedOutputStream.computeInt32Size(2, var8);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.extentionslot)) {
         Integer var9 = var1.extentionslot;
         var2 += CodedOutputStream.computeInt32Size(3, var9);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.slotInfos)) {
         Map var10 = var1.slotInfos;
         var2 += CodedConstant.computeMapSize(4, var10, FieldType.INT32, 0, FieldType.MESSAGE, new PT_CREATURE_LEARN_SKILL_INFOS());
      }

      return var2;
   }

   public void doWriteTo(PT_CREATURE_COMMUNION var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.level)) {
         Integer var7 = var1.level;
         if (var7 != null) {
            var2.writeInt32(1, var7);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.selectedslot)) {
         Integer var8 = var1.selectedslot;
         if (var8 != null) {
            var2.writeInt32(2, var8);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.extentionslot)) {
         Integer var9 = var1.extentionslot;
         if (var9 != null) {
            var2.writeInt32(3, var9);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.slotInfos)) {
         Map var10 = var1.slotInfos;
         if (var10 != null) {
            CodedConstant.writeToMap(var2, 4, var10, FieldType.INT32, 0, FieldType.MESSAGE, new PT_CREATURE_LEARN_SKILL_INFOS());
         }
      }

   }

   public void writeTo(PT_CREATURE_COMMUNION var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public PT_CREATURE_COMMUNION readFrom(CodedInputStream var1) throws IOException {
      PT_CREATURE_COMMUNION var2 = new PT_CREATURE_COMMUNION();
      var2.slotInfos = new HashMap();

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 8) {
               var2.level = var1.readInt32();
            } else if (var5 == 16) {
               var2.selectedslot = var1.readInt32();
            } else if (var5 == 24) {
               var2.extentionslot = var1.readInt32();
            } else if (var5 == 34) {
               if (var2.slotInfos == null) {
                  var2.slotInfos = new HashMap();
               }

               CodedConstant.putMapValue(var1, var2.slotInfos, FieldType.INT32, 0, FieldType.MESSAGE, new PT_CREATURE_LEARN_SKILL_INFOS(), (EnumHandler)null, (EnumHandler)null);
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(PT_CREATURE_COMMUNION.class);
         return this.descriptor = var1;
      }
   }
}
