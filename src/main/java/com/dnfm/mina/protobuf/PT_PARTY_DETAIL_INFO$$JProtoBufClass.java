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

public class PT_PARTY_DETAIL_INFO$$JProtoBufClass implements Codec<PT_PARTY_DETAIL_INFO>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(PT_PARTY_DETAIL_INFO var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public PT_PARTY_DETAIL_INFO decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(PT_PARTY_DETAIL_INFO var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.stageindex)) {
         Integer var20 = var1.stageindex;
         var2 += CodedOutputStream.computeInt32Size(1, var20);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.subtype)) {
         Integer var21 = var1.subtype;
         var2 += CodedOutputStream.computeInt32Size(2, var21);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.area)) {
         Integer var22 = var1.area;
         var2 += CodedOutputStream.computeInt32Size(3, var22);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.dungeonindex)) {
         Integer var23 = var1.dungeonindex;
         var2 += CodedOutputStream.computeInt32Size(4, var23);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.partyguid)) {
         Long var24 = var1.partyguid;
         var2 += CodedOutputStream.computeUInt64Size(5, var24);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.partyleaderguid)) {
         Long var25 = var1.partyleaderguid;
         var2 += CodedOutputStream.computeUInt64Size(6, var25);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.world)) {
         Integer var26 = var1.world;
         var2 += CodedOutputStream.computeInt32Size(7, var26);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.channel)) {
         Integer var27 = var1.channel;
         var2 += CodedOutputStream.computeInt32Size(8, var27);
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.partyname)) {
         String var28 = var1.partyname;
         var2 += CodedOutputStream.computeStringSize(9, var28);
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.publictype)) {
         Integer var29 = var1.publictype;
         var2 += CodedOutputStream.computeInt32Size(10, var29);
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.grade)) {
         Integer var30 = var1.grade;
         var2 += CodedOutputStream.computeInt32Size(11, var30);
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.minlevel)) {
         Integer var31 = var1.minlevel;
         var2 += CodedOutputStream.computeInt32Size(12, var31);
      }

      Object var15 = null;
      if (!CodedConstant.isNull(var1.maxlevel)) {
         Integer var32 = var1.maxlevel;
         var2 += CodedOutputStream.computeInt32Size(13, var32);
      }

      Object var16 = null;
      if (!CodedConstant.isNull(var1.ip)) {
         String var33 = var1.ip;
         var2 += CodedOutputStream.computeStringSize(14, var33);
      }

      Object var17 = null;
      if (!CodedConstant.isNull(var1.port)) {
         Integer var34 = var1.port;
         var2 += CodedOutputStream.computeInt32Size(15, var34);
      }

      Object var18 = null;
      if (!CodedConstant.isNull(var1.users)) {
         List var35 = var1.users;
         var2 += CodedConstant.computeListSize(16, var35, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var19 = null;
      if (!CodedConstant.isNull(var1.partyping)) {
         Integer var36 = var1.partyping;
         var2 += CodedOutputStream.computeInt32Size(17, var36);
      }

      return var2;
   }

   public void doWriteTo(PT_PARTY_DETAIL_INFO var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.stageindex)) {
         Integer var20 = var1.stageindex;
         if (var20 != null) {
            var2.writeInt32(1, var20);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.subtype)) {
         Integer var21 = var1.subtype;
         if (var21 != null) {
            var2.writeInt32(2, var21);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.area)) {
         Integer var22 = var1.area;
         if (var22 != null) {
            var2.writeInt32(3, var22);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.dungeonindex)) {
         Integer var23 = var1.dungeonindex;
         if (var23 != null) {
            var2.writeInt32(4, var23);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.partyguid)) {
         Long var24 = var1.partyguid;
         if (var24 != null) {
            var2.writeUInt64(5, var24);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.partyleaderguid)) {
         Long var25 = var1.partyleaderguid;
         if (var25 != null) {
            var2.writeUInt64(6, var25);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.world)) {
         Integer var26 = var1.world;
         if (var26 != null) {
            var2.writeInt32(7, var26);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.channel)) {
         Integer var27 = var1.channel;
         if (var27 != null) {
            var2.writeInt32(8, var27);
         }
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.partyname)) {
         String var28 = var1.partyname;
         if (var28 != null) {
            var2.writeString(9, var28);
         }
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.publictype)) {
         Integer var29 = var1.publictype;
         if (var29 != null) {
            var2.writeInt32(10, var29);
         }
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.grade)) {
         Integer var30 = var1.grade;
         if (var30 != null) {
            var2.writeInt32(11, var30);
         }
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.minlevel)) {
         Integer var31 = var1.minlevel;
         if (var31 != null) {
            var2.writeInt32(12, var31);
         }
      }

      Object var15 = null;
      if (!CodedConstant.isNull(var1.maxlevel)) {
         Integer var32 = var1.maxlevel;
         if (var32 != null) {
            var2.writeInt32(13, var32);
         }
      }

      Object var16 = null;
      if (!CodedConstant.isNull(var1.ip)) {
         String var33 = var1.ip;
         if (var33 != null) {
            var2.writeString(14, var33);
         }
      }

      Object var17 = null;
      if (!CodedConstant.isNull(var1.port)) {
         Integer var34 = var1.port;
         if (var34 != null) {
            var2.writeInt32(15, var34);
         }
      }

      Object var18 = null;
      if (!CodedConstant.isNull(var1.users)) {
         List var35 = var1.users;
         if (var35 != null) {
            CodedConstant.writeToList(var2, 16, FieldType.OBJECT, var35, false);
         }
      }

      Object var19 = null;
      if (!CodedConstant.isNull(var1.partyping)) {
         Integer var36 = var1.partyping;
         if (var36 != null) {
            var2.writeInt32(17, var36);
         }
      }

   }

   public void writeTo(PT_PARTY_DETAIL_INFO var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public PT_PARTY_DETAIL_INFO readFrom(CodedInputStream var1) throws IOException {
      PT_PARTY_DETAIL_INFO var2 = new PT_PARTY_DETAIL_INFO();
      var2.users = new ArrayList();

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 8) {
               var2.stageindex = var1.readInt32();
            } else if (var5 == 16) {
               var2.subtype = var1.readInt32();
            } else if (var5 == 24) {
               var2.area = var1.readInt32();
            } else if (var5 == 32) {
               var2.dungeonindex = var1.readInt32();
            } else if (var5 == 40) {
               var2.partyguid = var1.readUInt64();
            } else if (var5 == 48) {
               var2.partyleaderguid = var1.readUInt64();
            } else if (var5 == 56) {
               var2.world = var1.readInt32();
            } else if (var5 == 64) {
               var2.channel = var1.readInt32();
            } else if (var5 == 74) {
               var2.partyname = var1.readString();
            } else if (var5 == 80) {
               var2.publictype = var1.readInt32();
            } else if (var5 == 88) {
               var2.grade = var1.readInt32();
            } else if (var5 == 96) {
               var2.minlevel = var1.readInt32();
            } else if (var5 == 104) {
               var2.maxlevel = var1.readInt32();
            } else if (var5 == 114) {
               var2.ip = var1.readString();
            } else if (var5 == 120) {
               var2.port = var1.readInt32();
            } else if (var5 == 130) {
               Codec var10 = ProtobufProxy.create(PT_GROUP_MEMBER.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.users == null) {
                  var2.users = new ArrayList();
               }

               var2.users.add((PT_GROUP_MEMBER)var10.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var7);
            } else if (var5 == 136) {
               var2.partyping = var1.readInt32();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(PT_PARTY_DETAIL_INFO.class);
         return this.descriptor = var1;
      }
   }
}
