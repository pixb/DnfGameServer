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

public class PT_RES_PARTY_LIST$$JProtoBufClass implements Codec<PT_RES_PARTY_LIST>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(PT_RES_PARTY_LIST var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public PT_RES_PARTY_LIST decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(PT_RES_PARTY_LIST var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.ip)) {
         String var24 = var1.ip;
         var2 += CodedOutputStream.computeStringSize(1, var24);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.port)) {
         Integer var25 = var1.port;
         var2 += CodedOutputStream.computeInt32Size(2, var25);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.area)) {
         Integer var26 = var1.area;
         var2 += CodedOutputStream.computeInt32Size(3, var26);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.subtype)) {
         Integer var27 = var1.subtype;
         var2 += CodedOutputStream.computeInt32Size(4, var27);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.stageindex)) {
         Integer var28 = var1.stageindex;
         var2 += CodedOutputStream.computeInt32Size(5, var28);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.dungeonindex)) {
         Integer var29 = var1.dungeonindex;
         var2 += CodedOutputStream.computeInt32Size(6, var29);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.grade)) {
         Integer var30 = var1.grade;
         var2 += CodedOutputStream.computeInt32Size(7, var30);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.level)) {
         Integer var31 = var1.level;
         var2 += CodedOutputStream.computeInt32Size(8, var31);
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.minlevel)) {
         Integer var32 = var1.minlevel;
         var2 += CodedOutputStream.computeInt32Size(9, var32);
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.maxlevel)) {
         Integer var33 = var1.maxlevel;
         var2 += CodedOutputStream.computeInt32Size(10, var33);
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.channel)) {
         Integer var34 = var1.channel;
         var2 += CodedOutputStream.computeInt32Size(11, var34);
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.partyname)) {
         String var35 = var1.partyname;
         var2 += CodedOutputStream.computeStringSize(12, var35);
      }

      Object var15 = null;
      if (!CodedConstant.isNull(var1.publictype)) {
         Integer var36 = var1.publictype;
         var2 += CodedOutputStream.computeInt32Size(13, var36);
      }

      Object var16 = null;
      if (!CodedConstant.isNull(var1.partyguid)) {
         Long var37 = var1.partyguid;
         var2 += CodedOutputStream.computeUInt64Size(14, var37);
      }

      Object var17 = null;
      if (!CodedConstant.isNull(var1.world)) {
         Integer var38 = var1.world;
         var2 += CodedOutputStream.computeInt32Size(15, var38);
      }

      Object var18 = null;
      if (!CodedConstant.isNull(var1.users)) {
         List var39 = var1.users;
         var2 += CodedConstant.computeListSize(16, var39, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var19 = null;
      if (!CodedConstant.isNull(var1.flist)) {
         List var40 = var1.flist;
         var2 += CodedConstant.computeListSize(17, var40, FieldType.UINT64, false, (File)ProtobufProxy.OUTPUT_PATH.get(), true);
      }

      Object var20 = null;
      if (!CodedConstant.isNull(var1.isplayer)) {
         Integer var41 = var1.isplayer;
         var2 += CodedOutputStream.computeInt32Size(18, var41);
      }

      Object var21 = null;
      if (!CodedConstant.isNull(var1.enterobserver)) {
         Boolean var42 = var1.enterobserver;
         var2 += CodedOutputStream.computeBoolSize(19, var42);
      }

      Object var22 = null;
      if (!CodedConstant.isNull(var1.observerchat)) {
         Boolean var43 = var1.observerchat;
         var2 += CodedOutputStream.computeBoolSize(20, var43);
      }

      Object var23 = null;
      if (!CodedConstant.isNull(var1.partyping)) {
         Integer var44 = var1.partyping;
         var2 += CodedOutputStream.computeInt32Size(21, var44);
      }

      return var2;
   }

   public void doWriteTo(PT_RES_PARTY_LIST var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.ip)) {
         String var24 = var1.ip;
         if (var24 != null) {
            var2.writeString(1, var24);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.port)) {
         Integer var25 = var1.port;
         if (var25 != null) {
            var2.writeInt32(2, var25);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.area)) {
         Integer var26 = var1.area;
         if (var26 != null) {
            var2.writeInt32(3, var26);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.subtype)) {
         Integer var27 = var1.subtype;
         if (var27 != null) {
            var2.writeInt32(4, var27);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.stageindex)) {
         Integer var28 = var1.stageindex;
         if (var28 != null) {
            var2.writeInt32(5, var28);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.dungeonindex)) {
         Integer var29 = var1.dungeonindex;
         if (var29 != null) {
            var2.writeInt32(6, var29);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.grade)) {
         Integer var30 = var1.grade;
         if (var30 != null) {
            var2.writeInt32(7, var30);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.level)) {
         Integer var31 = var1.level;
         if (var31 != null) {
            var2.writeInt32(8, var31);
         }
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.minlevel)) {
         Integer var32 = var1.minlevel;
         if (var32 != null) {
            var2.writeInt32(9, var32);
         }
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.maxlevel)) {
         Integer var33 = var1.maxlevel;
         if (var33 != null) {
            var2.writeInt32(10, var33);
         }
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.channel)) {
         Integer var34 = var1.channel;
         if (var34 != null) {
            var2.writeInt32(11, var34);
         }
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.partyname)) {
         String var35 = var1.partyname;
         if (var35 != null) {
            var2.writeString(12, var35);
         }
      }

      Object var15 = null;
      if (!CodedConstant.isNull(var1.publictype)) {
         Integer var36 = var1.publictype;
         if (var36 != null) {
            var2.writeInt32(13, var36);
         }
      }

      Object var16 = null;
      if (!CodedConstant.isNull(var1.partyguid)) {
         Long var37 = var1.partyguid;
         if (var37 != null) {
            var2.writeUInt64(14, var37);
         }
      }

      Object var17 = null;
      if (!CodedConstant.isNull(var1.world)) {
         Integer var38 = var1.world;
         if (var38 != null) {
            var2.writeInt32(15, var38);
         }
      }

      Object var18 = null;
      if (!CodedConstant.isNull(var1.users)) {
         List var39 = var1.users;
         if (var39 != null) {
            CodedConstant.writeToList(var2, 16, FieldType.OBJECT, var39, false);
         }
      }

      Object var19 = null;
      if (!CodedConstant.isNull(var1.flist)) {
         List var40 = var1.flist;
         if (var40 != null) {
            CodedConstant.writeToList(var2, 17, FieldType.UINT64, var40, true);
         }
      }

      Object var20 = null;
      if (!CodedConstant.isNull(var1.isplayer)) {
         Integer var41 = var1.isplayer;
         if (var41 != null) {
            var2.writeInt32(18, var41);
         }
      }

      Object var21 = null;
      if (!CodedConstant.isNull(var1.enterobserver)) {
         Boolean var42 = var1.enterobserver;
         if (var42 != null) {
            var2.writeBool(19, var42);
         }
      }

      Object var22 = null;
      if (!CodedConstant.isNull(var1.observerchat)) {
         Boolean var43 = var1.observerchat;
         if (var43 != null) {
            var2.writeBool(20, var43);
         }
      }

      Object var23 = null;
      if (!CodedConstant.isNull(var1.partyping)) {
         Integer var44 = var1.partyping;
         if (var44 != null) {
            var2.writeInt32(21, var44);
         }
      }

   }

   public void writeTo(PT_RES_PARTY_LIST var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public PT_RES_PARTY_LIST readFrom(CodedInputStream var1) throws IOException {
      PT_RES_PARTY_LIST var2 = new PT_RES_PARTY_LIST();
      var2.users = new ArrayList();
      var2.flist = new ArrayList();

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 10) {
               var2.ip = var1.readString();
            } else if (var5 == 16) {
               var2.port = var1.readInt32();
            } else if (var5 == 24) {
               var2.area = var1.readInt32();
            } else if (var5 == 32) {
               var2.subtype = var1.readInt32();
            } else if (var5 == 40) {
               var2.stageindex = var1.readInt32();
            } else if (var5 == 48) {
               var2.dungeonindex = var1.readInt32();
            } else if (var5 == 56) {
               var2.grade = var1.readInt32();
            } else if (var5 == 64) {
               var2.level = var1.readInt32();
            } else if (var5 == 72) {
               var2.minlevel = var1.readInt32();
            } else if (var5 == 80) {
               var2.maxlevel = var1.readInt32();
            } else if (var5 == 88) {
               var2.channel = var1.readInt32();
            } else if (var5 == 98) {
               var2.partyname = var1.readString();
            } else if (var5 == 104) {
               var2.publictype = var1.readInt32();
            } else if (var5 == 112) {
               var2.partyguid = var1.readUInt64();
            } else if (var5 == 120) {
               var2.world = var1.readInt32();
            } else if (var5 == 130) {
               Codec var10 = ProtobufProxy.create(PT_GROUP_MEMBER.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var11 = var1.readRawVarint32();
               int var12 = var1.pushLimit(var11);
               if (var2.users == null) {
                  var2.users = new ArrayList();
               }

               var2.users.add((PT_GROUP_MEMBER)var10.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var12);
            } else if (var5 == 136) {
               if (var2.flist == null) {
                  var2.flist = new ArrayList();
               }

               var2.flist.add(var1.readUInt64());
            } else if (var5 != 138) {
               if (var5 == 144) {
                  var2.isplayer = var1.readInt32();
               } else if (var5 == 152) {
                  var2.enterobserver = var1.readBool();
               } else if (var5 == 160) {
                  var2.observerchat = var1.readBool();
               } else if (var5 == 168) {
                  var2.partyping = var1.readInt32();
               } else {
                  var1.skipField(var5);
               }
            } else {
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.flist == null) {
                  var2.flist = new ArrayList();
               }

               while(var1.getBytesUntilLimit() > 0) {
                  var2.flist.add(var1.readUInt64());
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(PT_RES_PARTY_LIST.class);
         return this.descriptor = var1;
      }
   }
}
