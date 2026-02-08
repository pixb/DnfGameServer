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

public class REQ_ADVENTURE_UNION_SET_SHAREBOARD$$JProtoBufClass implements Codec<REQ_ADVENTURE_UNION_SET_SHAREBOARD>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(REQ_ADVENTURE_UNION_SET_SHAREBOARD var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public REQ_ADVENTURE_UNION_SET_SHAREBOARD decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(REQ_ADVENTURE_UNION_SET_SHAREBOARD var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.shareboardbackground)) {
         Integer var7 = var1.shareboardbackground;
         var2 += CodedOutputStream.computeInt32Size(1, var7);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.shareboardframe)) {
         Integer var8 = var1.shareboardframe;
         var2 += CodedOutputStream.computeInt32Size(2, var8);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.shareboardslotlist)) {
         List var9 = var1.shareboardslotlist;
         var2 += CodedConstant.computeListSize(3, var9, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.shareboardshowantievilscore)) {
         Boolean var10 = var1.shareboardshowantievilscore;
         var2 += CodedOutputStream.computeBoolSize(4, var10);
      }

      return var2;
   }

   public void doWriteTo(REQ_ADVENTURE_UNION_SET_SHAREBOARD var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.shareboardbackground)) {
         Integer var7 = var1.shareboardbackground;
         if (var7 != null) {
            var2.writeInt32(1, var7);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.shareboardframe)) {
         Integer var8 = var1.shareboardframe;
         if (var8 != null) {
            var2.writeInt32(2, var8);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.shareboardslotlist)) {
         List var9 = var1.shareboardslotlist;
         if (var9 != null) {
            CodedConstant.writeToList(var2, 3, FieldType.OBJECT, var9, false);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.shareboardshowantievilscore)) {
         Boolean var10 = var1.shareboardshowantievilscore;
         if (var10 != null) {
            var2.writeBool(4, var10);
         }
      }

   }

   public void writeTo(REQ_ADVENTURE_UNION_SET_SHAREBOARD var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public REQ_ADVENTURE_UNION_SET_SHAREBOARD readFrom(CodedInputStream var1) throws IOException {
      REQ_ADVENTURE_UNION_SET_SHAREBOARD var2 = new REQ_ADVENTURE_UNION_SET_SHAREBOARD();
      var2.shareboardslotlist = new ArrayList();

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 8) {
               var2.shareboardbackground = var1.readInt32();
            } else if (var5 == 16) {
               var2.shareboardframe = var1.readInt32();
            } else if (var5 == 26) {
               Codec var10 = ProtobufProxy.create(PT_ADVENTURE_UNION_SHAREBOARD_SLOT.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.shareboardslotlist == null) {
                  var2.shareboardslotlist = new ArrayList();
               }

               var2.shareboardslotlist.add((PT_ADVENTURE_UNION_SHAREBOARD_SLOT)var10.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var7);
            } else if (var5 == 32) {
               var2.shareboardshowantievilscore = var1.readBool();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(REQ_ADVENTURE_UNION_SET_SHAREBOARD.class);
         return this.descriptor = var1;
      }
   }
}
