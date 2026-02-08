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

public class PT_ERRAND_INFO$$JProtoBufClass implements Codec<PT_ERRAND_INFO>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(PT_ERRAND_INFO var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public PT_ERRAND_INFO decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(PT_ERRAND_INFO var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.errandid)) {
         Integer var10 = var1.errandid;
         var2 += CodedOutputStream.computeInt32Size(1, var10);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.state)) {
         Integer var11 = var1.state;
         var2 += CodedOutputStream.computeInt32Size(2, var11);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.starttime)) {
         Long var12 = var1.starttime;
         var2 += CodedOutputStream.computeUInt64Size(3, var12);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.endtime)) {
         Long var13 = var1.endtime;
         var2 += CodedOutputStream.computeUInt64Size(4, var13);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.guidlist)) {
         List var14 = var1.guidlist;
         var2 += CodedConstant.computeListSize(5, var14, FieldType.UINT64, false, (File)ProtobufProxy.OUTPUT_PATH.get(), true);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.istoday)) {
         Boolean var15 = var1.istoday;
         var2 += CodedOutputStream.computeBoolSize(6, var15);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.todayinfo)) {
         PT_ERRAND_TODAY var16 = var1.todayinfo;
         var2 += CodedConstant.computeSize(7, var16, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      return var2;
   }

   public void doWriteTo(PT_ERRAND_INFO var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.errandid)) {
         Integer var10 = var1.errandid;
         if (var10 != null) {
            var2.writeInt32(1, var10);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.state)) {
         Integer var11 = var1.state;
         if (var11 != null) {
            var2.writeInt32(2, var11);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.starttime)) {
         Long var12 = var1.starttime;
         if (var12 != null) {
            var2.writeUInt64(3, var12);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.endtime)) {
         Long var13 = var1.endtime;
         if (var13 != null) {
            var2.writeUInt64(4, var13);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.guidlist)) {
         List var14 = var1.guidlist;
         if (var14 != null) {
            CodedConstant.writeToList(var2, 5, FieldType.UINT64, var14, true);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.istoday)) {
         Boolean var15 = var1.istoday;
         if (var15 != null) {
            var2.writeBool(6, var15);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.todayinfo)) {
         PT_ERRAND_TODAY var16 = var1.todayinfo;
         if (var16 != null) {
            CodedConstant.writeObject(var2, 7, FieldType.OBJECT, var16, false);
         }
      }

   }

   public void writeTo(PT_ERRAND_INFO var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public PT_ERRAND_INFO readFrom(CodedInputStream var1) throws IOException {
      PT_ERRAND_INFO var2 = new PT_ERRAND_INFO();
      var2.guidlist = new ArrayList();

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 8) {
               var2.errandid = var1.readInt32();
            } else if (var5 == 16) {
               var2.state = var1.readInt32();
            } else if (var5 == 24) {
               var2.starttime = var1.readUInt64();
            } else if (var5 == 32) {
               var2.endtime = var1.readUInt64();
            } else if (var5 == 40) {
               if (var2.guidlist == null) {
                  var2.guidlist = new ArrayList();
               }

               var2.guidlist.add(var1.readUInt64());
            } else if (var5 == 42) {
               int var12 = var1.readRawVarint32();
               int var14 = var1.pushLimit(var12);
               if (var2.guidlist == null) {
                  var2.guidlist = new ArrayList();
               }

               while(var1.getBytesUntilLimit() > 0) {
                  var2.guidlist.add(var1.readUInt64());
               }

               var1.popLimit(var14);
            } else if (var5 == 48) {
               var2.istoday = var1.readBool();
            } else if (var5 != 58) {
               var1.skipField(var5);
            } else if (var5 != 42) {
               Codec var10 = ProtobufProxy.create(PT_ERRAND_TODAY.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var11 = var1.readRawVarint32();
               int var13 = var1.pushLimit(var11);
               var2.todayinfo = (PT_ERRAND_TODAY)var10.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var13);
            } else {
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.guidlist == null) {
                  var2.guidlist = new ArrayList();
               }

               while(var1.getBytesUntilLimit() > 0) {
                  var2.guidlist.add(var1.readUInt64());
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(PT_ERRAND_INFO.class);
         return this.descriptor = var1;
      }
   }
}
