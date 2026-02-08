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

public class REQ_CONTROL_GROUP$$JProtoBufClass implements Codec<REQ_CONTROL_GROUP>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(REQ_CONTROL_GROUP var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public REQ_CONTROL_GROUP decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(REQ_CONTROL_GROUP var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.type)) {
         Integer var32 = var1.type;
         var2 += CodedOutputStream.computeInt32Size(1, var32);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.partyleaderguid)) {
         Long var33 = var1.partyleaderguid;
         var2 += CodedOutputStream.computeUInt64Size(2, var33);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.targetguid)) {
         Long var34 = var1.targetguid;
         var2 += CodedOutputStream.computeUInt64Size(3, var34);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.partyguid)) {
         Long var35 = var1.partyguid;
         var2 += CodedOutputStream.computeUInt64Size(4, var35);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.charguid)) {
         Long var36 = var1.charguid;
         var2 += CodedOutputStream.computeUInt64Size(5, var36);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.max)) {
         Integer var37 = var1.max;
         var2 += CodedOutputStream.computeInt32Size(6, var37);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.matchtype)) {
         Integer var38 = var1.matchtype;
         var2 += CodedOutputStream.computeInt32Size(7, var38);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.dungeonindex)) {
         Integer var39 = var1.dungeonindex;
         var2 += CodedOutputStream.computeInt32Size(8, var39);
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.input)) {
         Integer var40 = var1.input;
         var2 += CodedOutputStream.computeInt32Size(9, var40);
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.area)) {
         Integer var41 = var1.area;
         var2 += CodedOutputStream.computeInt32Size(10, var41);
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.subtype)) {
         Integer var42 = var1.subtype;
         var2 += CodedOutputStream.computeInt32Size(11, var42);
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.stageindex)) {
         Integer var43 = var1.stageindex;
         var2 += CodedOutputStream.computeInt32Size(12, var43);
      }

      Object var15 = null;
      if (!CodedConstant.isNull(var1.grade)) {
         Integer var44 = var1.grade;
         var2 += CodedOutputStream.computeInt32Size(13, var44);
      }

      Object var16 = null;
      if (!CodedConstant.isNull(var1.partyname)) {
         String var45 = var1.partyname;
         var2 += CodedOutputStream.computeStringSize(14, var45);
      }

      Object var17 = null;
      if (!CodedConstant.isNull(var1.level)) {
         Integer var46 = var1.level;
         var2 += CodedOutputStream.computeInt32Size(15, var46);
      }

      Object var18 = null;
      if (!CodedConstant.isNull(var1.minlevel)) {
         Integer var47 = var1.minlevel;
         var2 += CodedOutputStream.computeInt32Size(16, var47);
      }

      Object var19 = null;
      if (!CodedConstant.isNull(var1.maxlevel)) {
         Integer var48 = var1.maxlevel;
         var2 += CodedOutputStream.computeInt32Size(17, var48);
      }

      Object var20 = null;
      if (!CodedConstant.isNull(var1.publictype)) {
         Integer var49 = var1.publictype;
         var2 += CodedOutputStream.computeInt32Size(18, var49);
      }

      Object var21 = null;
      if (!CodedConstant.isNull(var1.targetguidlist)) {
         List var50 = var1.targetguidlist;
         var2 += CodedConstant.computeListSize(19, var50, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var22 = null;
      if (!CodedConstant.isNull(var1.state)) {
         Integer var51 = var1.state;
         var2 += CodedOutputStream.computeInt32Size(20, var51);
      }

      Object var23 = null;
      if (!CodedConstant.isNull(var1.cardindex)) {
         Integer var52 = var1.cardindex;
         var2 += CodedOutputStream.computeInt32Size(21, var52);
      }

      Object var24 = null;
      if (!CodedConstant.isNull(var1.contentlockstate)) {
         Integer var53 = var1.contentlockstate;
         var2 += CodedOutputStream.computeInt32Size(22, var53);
      }

      Object var25 = null;
      if (!CodedConstant.isNull(var1.customdata)) {
         PT_CUSTOM_DATA var54 = var1.customdata;
         var2 += CodedConstant.computeSize(23, var54, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var26 = null;
      if (!CodedConstant.isNull(var1.autorefuse)) {
         Integer var55 = var1.autorefuse;
         var2 += CodedOutputStream.computeInt32Size(24, var55);
      }

      Object var27 = null;
      if (!CodedConstant.isNull(var1.clientversion)) {
         String var56 = var1.clientversion;
         var2 += CodedOutputStream.computeStringSize(25, var56);
      }

      Object var28 = null;
      if (!CodedConstant.isNull(var1.observer)) {
         Boolean var57 = var1.observer;
         var2 += CodedOutputStream.computeBoolSize(26, var57);
      }

      Object var29 = null;
      if (!CodedConstant.isNull(var1.observerchat)) {
         Boolean var58 = var1.observerchat;
         var2 += CodedOutputStream.computeBoolSize(27, var58);
      }

      Object var30 = null;
      if (!CodedConstant.isNull(var1.isplayer)) {
         Boolean var59 = var1.isplayer;
         var2 += CodedOutputStream.computeBoolSize(28, var59);
      }

      Object var31 = null;
      if (!CodedConstant.isNull(var1.battleoptionlist)) {
         List var60 = var1.battleoptionlist;
         var2 += CodedConstant.computeListSize(29, var60, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      return var2;
   }

   public void doWriteTo(REQ_CONTROL_GROUP var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.type)) {
         Integer var32 = var1.type;
         if (var32 != null) {
            var2.writeInt32(1, var32);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.partyleaderguid)) {
         Long var33 = var1.partyleaderguid;
         if (var33 != null) {
            var2.writeUInt64(2, var33);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.targetguid)) {
         Long var34 = var1.targetguid;
         if (var34 != null) {
            var2.writeUInt64(3, var34);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.partyguid)) {
         Long var35 = var1.partyguid;
         if (var35 != null) {
            var2.writeUInt64(4, var35);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.charguid)) {
         Long var36 = var1.charguid;
         if (var36 != null) {
            var2.writeUInt64(5, var36);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.max)) {
         Integer var37 = var1.max;
         if (var37 != null) {
            var2.writeInt32(6, var37);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.matchtype)) {
         Integer var38 = var1.matchtype;
         if (var38 != null) {
            var2.writeInt32(7, var38);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.dungeonindex)) {
         Integer var39 = var1.dungeonindex;
         if (var39 != null) {
            var2.writeInt32(8, var39);
         }
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.input)) {
         Integer var40 = var1.input;
         if (var40 != null) {
            var2.writeInt32(9, var40);
         }
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.area)) {
         Integer var41 = var1.area;
         if (var41 != null) {
            var2.writeInt32(10, var41);
         }
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.subtype)) {
         Integer var42 = var1.subtype;
         if (var42 != null) {
            var2.writeInt32(11, var42);
         }
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.stageindex)) {
         Integer var43 = var1.stageindex;
         if (var43 != null) {
            var2.writeInt32(12, var43);
         }
      }

      Object var15 = null;
      if (!CodedConstant.isNull(var1.grade)) {
         Integer var44 = var1.grade;
         if (var44 != null) {
            var2.writeInt32(13, var44);
         }
      }

      Object var16 = null;
      if (!CodedConstant.isNull(var1.partyname)) {
         String var45 = var1.partyname;
         if (var45 != null) {
            var2.writeString(14, var45);
         }
      }

      Object var17 = null;
      if (!CodedConstant.isNull(var1.level)) {
         Integer var46 = var1.level;
         if (var46 != null) {
            var2.writeInt32(15, var46);
         }
      }

      Object var18 = null;
      if (!CodedConstant.isNull(var1.minlevel)) {
         Integer var47 = var1.minlevel;
         if (var47 != null) {
            var2.writeInt32(16, var47);
         }
      }

      Object var19 = null;
      if (!CodedConstant.isNull(var1.maxlevel)) {
         Integer var48 = var1.maxlevel;
         if (var48 != null) {
            var2.writeInt32(17, var48);
         }
      }

      Object var20 = null;
      if (!CodedConstant.isNull(var1.publictype)) {
         Integer var49 = var1.publictype;
         if (var49 != null) {
            var2.writeInt32(18, var49);
         }
      }

      Object var21 = null;
      if (!CodedConstant.isNull(var1.targetguidlist)) {
         List var50 = var1.targetguidlist;
         if (var50 != null) {
            CodedConstant.writeToList(var2, 19, FieldType.OBJECT, var50, false);
         }
      }

      Object var22 = null;
      if (!CodedConstant.isNull(var1.state)) {
         Integer var51 = var1.state;
         if (var51 != null) {
            var2.writeInt32(20, var51);
         }
      }

      Object var23 = null;
      if (!CodedConstant.isNull(var1.cardindex)) {
         Integer var52 = var1.cardindex;
         if (var52 != null) {
            var2.writeInt32(21, var52);
         }
      }

      Object var24 = null;
      if (!CodedConstant.isNull(var1.contentlockstate)) {
         Integer var53 = var1.contentlockstate;
         if (var53 != null) {
            var2.writeInt32(22, var53);
         }
      }

      Object var25 = null;
      if (!CodedConstant.isNull(var1.customdata)) {
         PT_CUSTOM_DATA var54 = var1.customdata;
         if (var54 != null) {
            CodedConstant.writeObject(var2, 23, FieldType.OBJECT, var54, false);
         }
      }

      Object var26 = null;
      if (!CodedConstant.isNull(var1.autorefuse)) {
         Integer var55 = var1.autorefuse;
         if (var55 != null) {
            var2.writeInt32(24, var55);
         }
      }

      Object var27 = null;
      if (!CodedConstant.isNull(var1.clientversion)) {
         String var56 = var1.clientversion;
         if (var56 != null) {
            var2.writeString(25, var56);
         }
      }

      Object var28 = null;
      if (!CodedConstant.isNull(var1.observer)) {
         Boolean var57 = var1.observer;
         if (var57 != null) {
            var2.writeBool(26, var57);
         }
      }

      Object var29 = null;
      if (!CodedConstant.isNull(var1.observerchat)) {
         Boolean var58 = var1.observerchat;
         if (var58 != null) {
            var2.writeBool(27, var58);
         }
      }

      Object var30 = null;
      if (!CodedConstant.isNull(var1.isplayer)) {
         Boolean var59 = var1.isplayer;
         if (var59 != null) {
            var2.writeBool(28, var59);
         }
      }

      Object var31 = null;
      if (!CodedConstant.isNull(var1.battleoptionlist)) {
         List var60 = var1.battleoptionlist;
         if (var60 != null) {
            CodedConstant.writeToList(var2, 29, FieldType.OBJECT, var60, false);
         }
      }

   }

   public void writeTo(REQ_CONTROL_GROUP var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public REQ_CONTROL_GROUP readFrom(CodedInputStream var1) throws IOException {
      REQ_CONTROL_GROUP var2 = new REQ_CONTROL_GROUP();
      var2.targetguidlist = new ArrayList();
      var2.battleoptionlist = new ArrayList();

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 8) {
               var2.type = var1.readInt32();
            } else if (var5 == 16) {
               var2.partyleaderguid = var1.readUInt64();
            } else if (var5 == 24) {
               var2.targetguid = var1.readUInt64();
            } else if (var5 == 32) {
               var2.partyguid = var1.readUInt64();
            } else if (var5 == 40) {
               var2.charguid = var1.readUInt64();
            } else if (var5 == 48) {
               var2.max = var1.readInt32();
            } else if (var5 == 56) {
               var2.matchtype = var1.readInt32();
            } else if (var5 == 64) {
               var2.dungeonindex = var1.readInt32();
            } else if (var5 == 72) {
               var2.input = var1.readInt32();
            } else if (var5 == 80) {
               var2.area = var1.readInt32();
            } else if (var5 == 88) {
               var2.subtype = var1.readInt32();
            } else if (var5 == 96) {
               var2.stageindex = var1.readInt32();
            } else if (var5 == 104) {
               var2.grade = var1.readInt32();
            } else if (var5 == 114) {
               var2.partyname = var1.readString();
            } else if (var5 == 120) {
               var2.level = var1.readInt32();
            } else if (var5 == 128) {
               var2.minlevel = var1.readInt32();
            } else if (var5 == 136) {
               var2.maxlevel = var1.readInt32();
            } else if (var5 == 144) {
               var2.publictype = var1.readInt32();
            } else if (var5 == 154) {
               Codec var10 = ProtobufProxy.create(PT_CONTROL_GROUP_INVITE_ITEM.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.targetguidlist == null) {
                  var2.targetguidlist = new ArrayList();
               }

               var2.targetguidlist.add((PT_CONTROL_GROUP_INVITE_ITEM)var10.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var7);
            } else if (var5 == 160) {
               var2.state = var1.readInt32();
            } else if (var5 == 168) {
               var2.cardindex = var1.readInt32();
            } else if (var5 == 176) {
               var2.contentlockstate = var1.readInt32();
            } else if (var5 == 186) {
               Codec var11 = ProtobufProxy.create(PT_CUSTOM_DATA.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var13 = var1.readRawVarint32();
               int var15 = var1.pushLimit(var13);
               var2.customdata = (PT_CUSTOM_DATA)var11.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var15);
            } else if (var5 == 192) {
               var2.autorefuse = var1.readInt32();
            } else if (var5 == 202) {
               var2.clientversion = var1.readString();
            } else if (var5 == 208) {
               var2.observer = var1.readBool();
            } else if (var5 == 216) {
               var2.observerchat = var1.readBool();
            } else if (var5 == 224) {
               var2.isplayer = var1.readBool();
            } else if (var5 == 234) {
               Codec var12 = ProtobufProxy.create(PT_BATTLE_OPTION.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var14 = var1.readRawVarint32();
               int var16 = var1.pushLimit(var14);
               if (var2.battleoptionlist == null) {
                  var2.battleoptionlist = new ArrayList();
               }

               var2.battleoptionlist.add((PT_BATTLE_OPTION)var12.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var16);
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(REQ_CONTROL_GROUP.class);
         return this.descriptor = var1;
      }
   }
}
