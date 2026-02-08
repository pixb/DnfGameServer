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

public class REQ_ADVENTURE_UNION_REPRESENT_CHARACTER_CHANGE$$JProtoBufClass implements Codec<REQ_ADVENTURE_UNION_REPRESENT_CHARACTER_CHANGE>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(REQ_ADVENTURE_UNION_REPRESENT_CHARACTER_CHANGE var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public REQ_ADVENTURE_UNION_REPRESENT_CHARACTER_CHANGE decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(REQ_ADVENTURE_UNION_REPRESENT_CHARACTER_CHANGE var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.charguid)) {
         Long var4 = var1.charguid;
         var2 += CodedOutputStream.computeUInt64Size(1, var4);
      }

      return var2;
   }

   public void doWriteTo(REQ_ADVENTURE_UNION_REPRESENT_CHARACTER_CHANGE var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.charguid)) {
         Long var4 = var1.charguid;
         if (var4 != null) {
            var2.writeUInt64(1, var4);
         }
      }

   }

   public void writeTo(REQ_ADVENTURE_UNION_REPRESENT_CHARACTER_CHANGE var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public REQ_ADVENTURE_UNION_REPRESENT_CHARACTER_CHANGE readFrom(CodedInputStream var1) throws IOException {
      REQ_ADVENTURE_UNION_REPRESENT_CHARACTER_CHANGE var2 = new REQ_ADVENTURE_UNION_REPRESENT_CHARACTER_CHANGE();

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 8) {
               var2.charguid = var1.readUInt64();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(REQ_ADVENTURE_UNION_REPRESENT_CHARACTER_CHANGE.class);
         return this.descriptor = var1;
      }
   }
}
