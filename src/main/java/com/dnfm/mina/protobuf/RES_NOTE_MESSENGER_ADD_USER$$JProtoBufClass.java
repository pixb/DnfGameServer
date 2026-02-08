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

public class RES_NOTE_MESSENGER_ADD_USER$$JProtoBufClass implements Codec<RES_NOTE_MESSENGER_ADD_USER>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(RES_NOTE_MESSENGER_ADD_USER var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public RES_NOTE_MESSENGER_ADD_USER decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(RES_NOTE_MESSENGER_ADD_USER var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var15 = var1.error;
         var2 += CodedOutputStream.computeInt32Size(1, var15);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.charguid)) {
         Long var16 = var1.charguid;
         var2 += CodedOutputStream.computeUInt64Size(2, var16);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.deleteguids)) {
         List var17 = var1.deleteguids;
         var2 += CodedConstant.computeListSize(3, var17, FieldType.UINT64, false, (File)ProtobufProxy.OUTPUT_PATH.get(), true);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.name)) {
         String var18 = var1.name;
         var2 += CodedOutputStream.computeStringSize(4, var18);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.job)) {
         Integer var19 = var1.job;
         var2 += CodedOutputStream.computeInt32Size(5, var19);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.growtype)) {
         Integer var20 = var1.growtype;
         var2 += CodedOutputStream.computeInt32Size(6, var20);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.level)) {
         Integer var21 = var1.level;
         var2 += CodedOutputStream.computeInt32Size(7, var21);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.count)) {
         Integer var22 = var1.count;
         var2 += CodedOutputStream.computeInt32Size(8, var22);
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.date)) {
         Long var23 = var1.date;
         var2 += CodedOutputStream.computeInt64Size(9, var23);
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.creditscore)) {
         Integer var24 = var1.creditscore;
         var2 += CodedOutputStream.computeInt32Size(10, var24);
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.skinchatinfo)) {
         PT_SKIN_CHAT_INFO var25 = var1.skinchatinfo;
         var2 += CodedConstant.computeSize(11, var25, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.list)) {
         List var26 = var1.list;
         var2 += CodedConstant.computeListSize(12, var26, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      return var2;
   }

   public void doWriteTo(RES_NOTE_MESSENGER_ADD_USER var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var15 = var1.error;
         if (var15 != null) {
            var2.writeInt32(1, var15);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.charguid)) {
         Long var16 = var1.charguid;
         if (var16 != null) {
            var2.writeUInt64(2, var16);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.deleteguids)) {
         List var17 = var1.deleteguids;
         if (var17 != null) {
            CodedConstant.writeToList(var2, 3, FieldType.UINT64, var17, true);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.name)) {
         String var18 = var1.name;
         if (var18 != null) {
            var2.writeString(4, var18);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.job)) {
         Integer var19 = var1.job;
         if (var19 != null) {
            var2.writeInt32(5, var19);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.growtype)) {
         Integer var20 = var1.growtype;
         if (var20 != null) {
            var2.writeInt32(6, var20);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.level)) {
         Integer var21 = var1.level;
         if (var21 != null) {
            var2.writeInt32(7, var21);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.count)) {
         Integer var22 = var1.count;
         if (var22 != null) {
            var2.writeInt32(8, var22);
         }
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.date)) {
         Long var23 = var1.date;
         if (var23 != null) {
            var2.writeInt64(9, var23);
         }
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.creditscore)) {
         Integer var24 = var1.creditscore;
         if (var24 != null) {
            var2.writeInt32(10, var24);
         }
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.skinchatinfo)) {
         PT_SKIN_CHAT_INFO var25 = var1.skinchatinfo;
         if (var25 != null) {
            CodedConstant.writeObject(var2, 11, FieldType.OBJECT, var25, false);
         }
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.list)) {
         List var26 = var1.list;
         if (var26 != null) {
            CodedConstant.writeToList(var2, 12, FieldType.OBJECT, var26, false);
         }
      }

   }

   public void writeTo(RES_NOTE_MESSENGER_ADD_USER var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public RES_NOTE_MESSENGER_ADD_USER readFrom(CodedInputStream var1) throws IOException {
      RES_NOTE_MESSENGER_ADD_USER var2 = new RES_NOTE_MESSENGER_ADD_USER();
      var2.deleteguids = new ArrayList();
      var2.list = new ArrayList();

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
               var2.charguid = var1.readUInt64();
            } else if (var5 == 24) {
               if (var2.deleteguids == null) {
                  var2.deleteguids = new ArrayList();
               }

               var2.deleteguids.add(var1.readUInt64());
            } else if (var5 == 26) {
               int var14 = var1.readRawVarint32();
               int var17 = var1.pushLimit(var14);
               if (var2.deleteguids == null) {
                  var2.deleteguids = new ArrayList();
               }

               while(var1.getBytesUntilLimit() > 0) {
                  var2.deleteguids.add(var1.readUInt64());
               }

               var1.popLimit(var17);
            } else if (var5 == 34) {
               var2.name = var1.readString();
            } else if (var5 == 40) {
               var2.job = var1.readInt32();
            } else if (var5 == 48) {
               var2.growtype = var1.readInt32();
            } else if (var5 == 56) {
               var2.level = var1.readInt32();
            } else if (var5 == 64) {
               var2.count = var1.readInt32();
            } else if (var5 == 72) {
               var2.date = var1.readInt64();
            } else if (var5 == 80) {
               var2.creditscore = var1.readInt32();
            } else if (var5 != 90) {
               if (var5 == 98) {
                  Codec var11 = ProtobufProxy.create(PT_CHAT.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
                  int var13 = var1.readRawVarint32();
                  int var16 = var1.pushLimit(var13);
                  if (var2.list == null) {
                     var2.list = new ArrayList();
                  }

                  var2.list.add((PT_CHAT)var11.readFrom(var1));
                  var1.checkLastTagWas(0);
                  var1.popLimit(var16);
               } else {
                  var1.skipField(var5);
               }
            } else if (var5 != 26) {
               Codec var10 = ProtobufProxy.create(PT_SKIN_CHAT_INFO.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var12 = var1.readRawVarint32();
               int var15 = var1.pushLimit(var12);
               var2.skinchatinfo = (PT_SKIN_CHAT_INFO)var10.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var15);
            } else {
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.deleteguids == null) {
                  var2.deleteguids = new ArrayList();
               }

               while(var1.getBytesUntilLimit() > 0) {
                  var2.deleteguids.add(var1.readUInt64());
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(RES_NOTE_MESSENGER_ADD_USER.class);
         return this.descriptor = var1;
      }
   }
}
