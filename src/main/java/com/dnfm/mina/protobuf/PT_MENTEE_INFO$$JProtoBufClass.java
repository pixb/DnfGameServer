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

public class PT_MENTEE_INFO$$JProtoBufClass implements Codec<PT_MENTEE_INFO>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(PT_MENTEE_INFO var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public PT_MENTEE_INFO decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(PT_MENTEE_INFO var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.guid)) {
         Long var23 = var1.guid;
         var2 += CodedOutputStream.computeUInt64Size(1, var23);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.level)) {
         Integer var24 = var1.level;
         var2 += CodedOutputStream.computeInt32Size(2, var24);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.name)) {
         String var25 = var1.name;
         var2 += CodedOutputStream.computeStringSize(3, var25);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.job)) {
         Integer var26 = var1.job;
         var2 += CodedOutputStream.computeInt32Size(4, var26);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.growtype)) {
         Integer var27 = var1.growtype;
         var2 += CodedOutputStream.computeInt32Size(5, var27);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.secondgrowtype)) {
         Integer var28 = var1.secondgrowtype;
         var2 += CodedOutputStream.computeInt32Size(6, var28);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.active)) {
         Integer var29 = var1.active;
         var2 += CodedOutputStream.computeInt32Size(7, var29);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.online)) {
         Integer var30 = var1.online;
         var2 += CodedOutputStream.computeInt32Size(8, var30);
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.followerallowgraduate)) {
         Integer var31 = var1.followerallowgraduate;
         var2 += CodedOutputStream.computeInt32Size(9, var31);
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.masterallowgraduate)) {
         Integer var32 = var1.masterallowgraduate;
         var2 += CodedOutputStream.computeInt32Size(10, var32);
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.mentordungeonticket)) {
         Integer var33 = var1.mentordungeonticket;
         var2 += CodedOutputStream.computeInt32Size(11, var33);
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.registlevel)) {
         Integer var34 = var1.registlevel;
         var2 += CodedOutputStream.computeInt32Size(12, var34);
      }

      Object var15 = null;
      if (!CodedConstant.isNull(var1.regdate)) {
         Long var35 = var1.regdate;
         var2 += CodedOutputStream.computeInt64Size(13, var35);
      }

      Object var16 = null;
      if (!CodedConstant.isNull(var1.seqlevelgift)) {
         String var36 = var1.seqlevelgift;
         var2 += CodedOutputStream.computeStringSize(14, var36);
      }

      Object var17 = null;
      if (!CodedConstant.isNull(var1.seqlevelgiftmaster)) {
         String var37 = var1.seqlevelgiftmaster;
         var2 += CodedOutputStream.computeStringSize(15, var37);
      }

      Object var18 = null;
      if (!CodedConstant.isNull(var1.sendgreeting)) {
         Long var38 = var1.sendgreeting;
         var2 += CodedOutputStream.computeInt64Size(16, var38);
      }

      Object var19 = null;
      if (!CodedConstant.isNull(var1.recvgreeting)) {
         Long var39 = var1.recvgreeting;
         var2 += CodedOutputStream.computeInt64Size(17, var39);
      }

      Object var20 = null;
      if (!CodedConstant.isNull(var1.questcount)) {
         Integer var40 = var1.questcount;
         var2 += CodedOutputStream.computeInt32Size(18, var40);
      }

      Object var21 = null;
      if (!CodedConstant.isNull(var1.quest)) {
         List var41 = var1.quest;
         var2 += CodedConstant.computeListSize(19, var41, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var22 = null;
      if (!CodedConstant.isNull(var1.creditscore)) {
         Integer var42 = var1.creditscore;
         var2 += CodedOutputStream.computeInt32Size(20, var42);
      }

      return var2;
   }

   public void doWriteTo(PT_MENTEE_INFO var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.guid)) {
         Long var23 = var1.guid;
         if (var23 != null) {
            var2.writeUInt64(1, var23);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.level)) {
         Integer var24 = var1.level;
         if (var24 != null) {
            var2.writeInt32(2, var24);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.name)) {
         String var25 = var1.name;
         if (var25 != null) {
            var2.writeString(3, var25);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.job)) {
         Integer var26 = var1.job;
         if (var26 != null) {
            var2.writeInt32(4, var26);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.growtype)) {
         Integer var27 = var1.growtype;
         if (var27 != null) {
            var2.writeInt32(5, var27);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.secondgrowtype)) {
         Integer var28 = var1.secondgrowtype;
         if (var28 != null) {
            var2.writeInt32(6, var28);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.active)) {
         Integer var29 = var1.active;
         if (var29 != null) {
            var2.writeInt32(7, var29);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.online)) {
         Integer var30 = var1.online;
         if (var30 != null) {
            var2.writeInt32(8, var30);
         }
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.followerallowgraduate)) {
         Integer var31 = var1.followerallowgraduate;
         if (var31 != null) {
            var2.writeInt32(9, var31);
         }
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.masterallowgraduate)) {
         Integer var32 = var1.masterallowgraduate;
         if (var32 != null) {
            var2.writeInt32(10, var32);
         }
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.mentordungeonticket)) {
         Integer var33 = var1.mentordungeonticket;
         if (var33 != null) {
            var2.writeInt32(11, var33);
         }
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.registlevel)) {
         Integer var34 = var1.registlevel;
         if (var34 != null) {
            var2.writeInt32(12, var34);
         }
      }

      Object var15 = null;
      if (!CodedConstant.isNull(var1.regdate)) {
         Long var35 = var1.regdate;
         if (var35 != null) {
            var2.writeInt64(13, var35);
         }
      }

      Object var16 = null;
      if (!CodedConstant.isNull(var1.seqlevelgift)) {
         String var36 = var1.seqlevelgift;
         if (var36 != null) {
            var2.writeString(14, var36);
         }
      }

      Object var17 = null;
      if (!CodedConstant.isNull(var1.seqlevelgiftmaster)) {
         String var37 = var1.seqlevelgiftmaster;
         if (var37 != null) {
            var2.writeString(15, var37);
         }
      }

      Object var18 = null;
      if (!CodedConstant.isNull(var1.sendgreeting)) {
         Long var38 = var1.sendgreeting;
         if (var38 != null) {
            var2.writeInt64(16, var38);
         }
      }

      Object var19 = null;
      if (!CodedConstant.isNull(var1.recvgreeting)) {
         Long var39 = var1.recvgreeting;
         if (var39 != null) {
            var2.writeInt64(17, var39);
         }
      }

      Object var20 = null;
      if (!CodedConstant.isNull(var1.questcount)) {
         Integer var40 = var1.questcount;
         if (var40 != null) {
            var2.writeInt32(18, var40);
         }
      }

      Object var21 = null;
      if (!CodedConstant.isNull(var1.quest)) {
         List var41 = var1.quest;
         if (var41 != null) {
            CodedConstant.writeToList(var2, 19, FieldType.OBJECT, var41, false);
         }
      }

      Object var22 = null;
      if (!CodedConstant.isNull(var1.creditscore)) {
         Integer var42 = var1.creditscore;
         if (var42 != null) {
            var2.writeInt32(20, var42);
         }
      }

   }

   public void writeTo(PT_MENTEE_INFO var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public PT_MENTEE_INFO readFrom(CodedInputStream var1) throws IOException {
      PT_MENTEE_INFO var2 = new PT_MENTEE_INFO();
      var2.quest = new ArrayList();

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 8) {
               var2.guid = var1.readUInt64();
            } else if (var5 == 16) {
               var2.level = var1.readInt32();
            } else if (var5 == 26) {
               var2.name = var1.readString();
            } else if (var5 == 32) {
               var2.job = var1.readInt32();
            } else if (var5 == 40) {
               var2.growtype = var1.readInt32();
            } else if (var5 == 48) {
               var2.secondgrowtype = var1.readInt32();
            } else if (var5 == 56) {
               var2.active = var1.readInt32();
            } else if (var5 == 64) {
               var2.online = var1.readInt32();
            } else if (var5 == 72) {
               var2.followerallowgraduate = var1.readInt32();
            } else if (var5 == 80) {
               var2.masterallowgraduate = var1.readInt32();
            } else if (var5 == 88) {
               var2.mentordungeonticket = var1.readInt32();
            } else if (var5 == 96) {
               var2.registlevel = var1.readInt32();
            } else if (var5 == 104) {
               var2.regdate = var1.readInt64();
            } else if (var5 == 114) {
               var2.seqlevelgift = var1.readString();
            } else if (var5 == 122) {
               var2.seqlevelgiftmaster = var1.readString();
            } else if (var5 == 128) {
               var2.sendgreeting = var1.readInt64();
            } else if (var5 == 136) {
               var2.recvgreeting = var1.readInt64();
            } else if (var5 == 144) {
               var2.questcount = var1.readInt32();
            } else if (var5 == 154) {
               Codec var10 = ProtobufProxy.create(PT_MENTOR_QUEST_INFO.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.quest == null) {
                  var2.quest = new ArrayList();
               }

               var2.quest.add((PT_MENTOR_QUEST_INFO)var10.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var7);
            } else if (var5 == 160) {
               var2.creditscore = var1.readInt32();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(PT_MENTEE_INFO.class);
         return this.descriptor = var1;
      }
   }
}
