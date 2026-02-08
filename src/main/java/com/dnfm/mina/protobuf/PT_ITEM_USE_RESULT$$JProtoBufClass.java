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

public class PT_ITEM_USE_RESULT$$JProtoBufClass implements Codec<PT_ITEM_USE_RESULT>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(PT_ITEM_USE_RESULT var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public PT_ITEM_USE_RESULT decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(PT_ITEM_USE_RESULT var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.fatigue)) {
         Integer var24 = var1.fatigue;
         var2 += CodedOutputStream.computeInt32Size(1, var24);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.growth)) {
         Integer var25 = var1.growth;
         var2 += CodedOutputStream.computeInt32Size(2, var25);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.secondgrowtype)) {
         Integer var26 = var1.secondgrowtype;
         var2 += CodedOutputStream.computeInt32Size(3, var26);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.totalexp)) {
         Integer var27 = var1.totalexp;
         var2 += CodedOutputStream.computeInt32Size(4, var27);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.exp)) {
         Integer var28 = var1.exp;
         var2 += CodedOutputStream.computeInt32Size(5, var28);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.level)) {
         Integer var29 = var1.level;
         var2 += CodedOutputStream.computeInt32Size(6, var29);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.addmentorslot)) {
         Integer var30 = var1.addmentorslot;
         var2 += CodedOutputStream.computeInt32Size(7, var30);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.infos)) {
         List var31 = var1.infos;
         var2 += CodedConstant.computeListSize(8, var31, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.result)) {
         Boolean var32 = var1.result;
         var2 += CodedOutputStream.computeBoolSize(9, var32);
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.upgrade)) {
         Integer var33 = var1.upgrade;
         var2 += CodedOutputStream.computeInt32Size(10, var33);
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.enchantindex)) {
         Integer var34 = var1.enchantindex;
         var2 += CodedOutputStream.computeInt32Size(11, var34);
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.enchant)) {
         Integer var35 = var1.enchant;
         var2 += CodedOutputStream.computeInt32Size(12, var35);
      }

      Object var15 = null;
      if (!CodedConstant.isNull(var1.changeindex)) {
         Integer var36 = var1.changeindex;
         var2 += CodedOutputStream.computeInt32Size(13, var36);
      }

      Object var16 = null;
      if (!CodedConstant.isNull(var1.changecount)) {
         Integer var37 = var1.changecount;
         var2 += CodedOutputStream.computeInt32Size(14, var37);
      }

      Object var17 = null;
      if (!CodedConstant.isNull(var1.option)) {
         Integer var38 = var1.option;
         var2 += CodedOutputStream.computeInt32Size(15, var38);
      }

      Object var18 = null;
      if (!CodedConstant.isNull(var1.changeguid)) {
         Long var39 = var1.changeguid;
         var2 += CodedOutputStream.computeUInt64Size(16, var39);
      }

      Object var19 = null;
      if (!CodedConstant.isNull(var1.adventureunionlevel)) {
         Integer var40 = var1.adventureunionlevel;
         var2 += CodedOutputStream.computeInt32Size(17, var40);
      }

      Object var20 = null;
      if (!CodedConstant.isNull(var1.adventureunionexp)) {
         Long var41 = var1.adventureunionexp;
         var2 += CodedOutputStream.computeInt64Size(18, var41);
      }

      Object var21 = null;
      if (!CodedConstant.isNull(var1.upgradepoint)) {
         Integer var42 = var1.upgradepoint;
         var2 += CodedOutputStream.computeInt32Size(19, var42);
      }

      Object var22 = null;
      if (!CodedConstant.isNull(var1.scount)) {
         Integer var43 = var1.scount;
         var2 += CodedOutputStream.computeInt32Size(20, var43);
      }

      Object var23 = null;
      if (!CodedConstant.isNull(var1.tcount)) {
         Integer var44 = var1.tcount;
         var2 += CodedOutputStream.computeInt32Size(21, var44);
      }

      return var2;
   }

   public void doWriteTo(PT_ITEM_USE_RESULT var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.fatigue)) {
         Integer var24 = var1.fatigue;
         if (var24 != null) {
            var2.writeInt32(1, var24);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.growth)) {
         Integer var25 = var1.growth;
         if (var25 != null) {
            var2.writeInt32(2, var25);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.secondgrowtype)) {
         Integer var26 = var1.secondgrowtype;
         if (var26 != null) {
            var2.writeInt32(3, var26);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.totalexp)) {
         Integer var27 = var1.totalexp;
         if (var27 != null) {
            var2.writeInt32(4, var27);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.exp)) {
         Integer var28 = var1.exp;
         if (var28 != null) {
            var2.writeInt32(5, var28);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.level)) {
         Integer var29 = var1.level;
         if (var29 != null) {
            var2.writeInt32(6, var29);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.addmentorslot)) {
         Integer var30 = var1.addmentorslot;
         if (var30 != null) {
            var2.writeInt32(7, var30);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.infos)) {
         List var31 = var1.infos;
         if (var31 != null) {
            CodedConstant.writeToList(var2, 8, FieldType.OBJECT, var31, false);
         }
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.result)) {
         Boolean var32 = var1.result;
         if (var32 != null) {
            var2.writeBool(9, var32);
         }
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.upgrade)) {
         Integer var33 = var1.upgrade;
         if (var33 != null) {
            var2.writeInt32(10, var33);
         }
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.enchantindex)) {
         Integer var34 = var1.enchantindex;
         if (var34 != null) {
            var2.writeInt32(11, var34);
         }
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.enchant)) {
         Integer var35 = var1.enchant;
         if (var35 != null) {
            var2.writeInt32(12, var35);
         }
      }

      Object var15 = null;
      if (!CodedConstant.isNull(var1.changeindex)) {
         Integer var36 = var1.changeindex;
         if (var36 != null) {
            var2.writeInt32(13, var36);
         }
      }

      Object var16 = null;
      if (!CodedConstant.isNull(var1.changecount)) {
         Integer var37 = var1.changecount;
         if (var37 != null) {
            var2.writeInt32(14, var37);
         }
      }

      Object var17 = null;
      if (!CodedConstant.isNull(var1.option)) {
         Integer var38 = var1.option;
         if (var38 != null) {
            var2.writeInt32(15, var38);
         }
      }

      Object var18 = null;
      if (!CodedConstant.isNull(var1.changeguid)) {
         Long var39 = var1.changeguid;
         if (var39 != null) {
            var2.writeUInt64(16, var39);
         }
      }

      Object var19 = null;
      if (!CodedConstant.isNull(var1.adventureunionlevel)) {
         Integer var40 = var1.adventureunionlevel;
         if (var40 != null) {
            var2.writeInt32(17, var40);
         }
      }

      Object var20 = null;
      if (!CodedConstant.isNull(var1.adventureunionexp)) {
         Long var41 = var1.adventureunionexp;
         if (var41 != null) {
            var2.writeInt64(18, var41);
         }
      }

      Object var21 = null;
      if (!CodedConstant.isNull(var1.upgradepoint)) {
         Integer var42 = var1.upgradepoint;
         if (var42 != null) {
            var2.writeInt32(19, var42);
         }
      }

      Object var22 = null;
      if (!CodedConstant.isNull(var1.scount)) {
         Integer var43 = var1.scount;
         if (var43 != null) {
            var2.writeInt32(20, var43);
         }
      }

      Object var23 = null;
      if (!CodedConstant.isNull(var1.tcount)) {
         Integer var44 = var1.tcount;
         if (var44 != null) {
            var2.writeInt32(21, var44);
         }
      }

   }

   public void writeTo(PT_ITEM_USE_RESULT var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public PT_ITEM_USE_RESULT readFrom(CodedInputStream var1) throws IOException {
      PT_ITEM_USE_RESULT var2 = new PT_ITEM_USE_RESULT();
      var2.infos = new ArrayList();

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 8) {
               var2.fatigue = var1.readInt32();
            } else if (var5 == 16) {
               var2.growth = var1.readInt32();
            } else if (var5 == 24) {
               var2.secondgrowtype = var1.readInt32();
            } else if (var5 == 32) {
               var2.totalexp = var1.readInt32();
            } else if (var5 == 40) {
               var2.exp = var1.readInt32();
            } else if (var5 == 48) {
               var2.level = var1.readInt32();
            } else if (var5 == 56) {
               var2.addmentorslot = var1.readInt32();
            } else if (var5 == 66) {
               Codec var10 = ProtobufProxy.create(PT_ITEM_USE_INVEN_SLOT.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.infos == null) {
                  var2.infos = new ArrayList();
               }

               var2.infos.add((PT_ITEM_USE_INVEN_SLOT)var10.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var7);
            } else if (var5 == 72) {
               var2.result = var1.readBool();
            } else if (var5 == 80) {
               var2.upgrade = var1.readInt32();
            } else if (var5 == 88) {
               var2.enchantindex = var1.readInt32();
            } else if (var5 == 96) {
               var2.enchant = var1.readInt32();
            } else if (var5 == 104) {
               var2.changeindex = var1.readInt32();
            } else if (var5 == 112) {
               var2.changecount = var1.readInt32();
            } else if (var5 == 120) {
               var2.option = var1.readInt32();
            } else if (var5 == 128) {
               var2.changeguid = var1.readUInt64();
            } else if (var5 == 136) {
               var2.adventureunionlevel = var1.readInt32();
            } else if (var5 == 144) {
               var2.adventureunionexp = var1.readInt64();
            } else if (var5 == 152) {
               var2.upgradepoint = var1.readInt32();
            } else if (var5 == 160) {
               var2.scount = var1.readInt32();
            } else if (var5 == 168) {
               var2.tcount = var1.readInt32();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(PT_ITEM_USE_RESULT.class);
         return this.descriptor = var1;
      }
   }
}
