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

public class RES_ENTER_CHANNEL$$JProtoBufClass implements Codec<RES_ENTER_CHANNEL>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(RES_ENTER_CHANNEL var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public RES_ENTER_CHANNEL decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(RES_ENTER_CHANNEL var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var20 = var1.error;
         var2 += CodedOutputStream.computeInt32Size(1, var20);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.standby)) {
         Integer var21 = var1.standby;
         var2 += CodedOutputStream.computeInt32Size(2, var21);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.encrypt)) {
         Boolean var22 = var1.encrypt;
         var2 += CodedOutputStream.computeBoolSize(3, var22);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.servertime)) {
         Long var23 = var1.servertime;
         var2 += CodedOutputStream.computeUInt64Size(4, var23);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.authkey)) {
         String var24 = var1.authkey;
         var2 += CodedOutputStream.computeStringSize(5, var24);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.launchinfo)) {
         Integer var25 = var1.launchinfo;
         var2 += CodedOutputStream.computeInt32Size(6, var25);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.vip)) {
         Integer var26 = var1.vip;
         var2 += CodedOutputStream.computeInt32Size(7, var26);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.checkup)) {
         Integer var27 = var1.checkup;
         var2 += CodedOutputStream.computeInt32Size(8, var27);
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.checkuptype)) {
         Integer var28 = var1.checkuptype;
         var2 += CodedOutputStream.computeInt32Size(9, var28);
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.key)) {
         String var29 = var1.key;
         var2 += CodedOutputStream.computeStringSize(10, var29);
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.roxytutorial)) {
         Integer var30 = var1.roxytutorial;
         var2 += CodedOutputStream.computeInt32Size(11, var30);
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.gamecenterregdate)) {
         Long var31 = var1.gamecenterregdate;
         var2 += CodedOutputStream.computeInt64Size(12, var31);
      }

      Object var15 = null;
      if (!CodedConstant.isNull(var1.areatype)) {
         Integer var32 = var1.areatype;
         var2 += CodedOutputStream.computeInt32Size(13, var32);
      }

      Object var16 = null;
      if (!CodedConstant.isNull(var1.returnUserInfo)) {
         PT_RETURN_USER_INFO var33 = var1.returnUserInfo;
         var2 += CodedConstant.computeSize(14, var33, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var17 = null;
      if (!CodedConstant.isNull(var1.channelinfo)) {
         PT_CHANNEL var34 = var1.channelinfo;
         var2 += CodedConstant.computeSize(15, var34, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      Object var18 = null;
      if (!CodedConstant.isNull(var1.seeds)) {
         List var35 = var1.seeds;
         var2 += CodedConstant.computeListSize(16, var35, FieldType.INT32, false, (File)ProtobufProxy.OUTPUT_PATH.get(), true);
      }

      Object var19 = null;
      if (!CodedConstant.isNull(var1.eventSelectInfo)) {
         PT_EVENT_SELECT_INFO var36 = var1.eventSelectInfo;
         var2 += CodedConstant.computeSize(17, var36, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get());
      }

      return var2;
   }

   public void doWriteTo(RES_ENTER_CHANNEL var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var20 = var1.error;
         if (var20 != null) {
            var2.writeInt32(1, var20);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.standby)) {
         Integer var21 = var1.standby;
         if (var21 != null) {
            var2.writeInt32(2, var21);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.encrypt)) {
         Boolean var22 = var1.encrypt;
         if (var22 != null) {
            var2.writeBool(3, var22);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.servertime)) {
         Long var23 = var1.servertime;
         if (var23 != null) {
            var2.writeUInt64(4, var23);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.authkey)) {
         String var24 = var1.authkey;
         if (var24 != null) {
            var2.writeString(5, var24);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.launchinfo)) {
         Integer var25 = var1.launchinfo;
         if (var25 != null) {
            var2.writeInt32(6, var25);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.vip)) {
         Integer var26 = var1.vip;
         if (var26 != null) {
            var2.writeInt32(7, var26);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.checkup)) {
         Integer var27 = var1.checkup;
         if (var27 != null) {
            var2.writeInt32(8, var27);
         }
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.checkuptype)) {
         Integer var28 = var1.checkuptype;
         if (var28 != null) {
            var2.writeInt32(9, var28);
         }
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.key)) {
         String var29 = var1.key;
         if (var29 != null) {
            var2.writeString(10, var29);
         }
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.roxytutorial)) {
         Integer var30 = var1.roxytutorial;
         if (var30 != null) {
            var2.writeInt32(11, var30);
         }
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.gamecenterregdate)) {
         Long var31 = var1.gamecenterregdate;
         if (var31 != null) {
            var2.writeInt64(12, var31);
         }
      }

      Object var15 = null;
      if (!CodedConstant.isNull(var1.areatype)) {
         Integer var32 = var1.areatype;
         if (var32 != null) {
            var2.writeInt32(13, var32);
         }
      }

      Object var16 = null;
      if (!CodedConstant.isNull(var1.returnUserInfo)) {
         PT_RETURN_USER_INFO var33 = var1.returnUserInfo;
         if (var33 != null) {
            CodedConstant.writeObject(var2, 14, FieldType.OBJECT, var33, false);
         }
      }

      Object var17 = null;
      if (!CodedConstant.isNull(var1.channelinfo)) {
         PT_CHANNEL var34 = var1.channelinfo;
         if (var34 != null) {
            CodedConstant.writeObject(var2, 15, FieldType.OBJECT, var34, false);
         }
      }

      Object var18 = null;
      if (!CodedConstant.isNull(var1.seeds)) {
         List var35 = var1.seeds;
         if (var35 != null) {
            CodedConstant.writeToList(var2, 16, FieldType.INT32, var35, true);
         }
      }

      Object var19 = null;
      if (!CodedConstant.isNull(var1.eventSelectInfo)) {
         PT_EVENT_SELECT_INFO var36 = var1.eventSelectInfo;
         if (var36 != null) {
            CodedConstant.writeObject(var2, 17, FieldType.OBJECT, var36, false);
         }
      }

   }

   public void writeTo(RES_ENTER_CHANNEL var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public RES_ENTER_CHANNEL readFrom(CodedInputStream var1) throws IOException {
      RES_ENTER_CHANNEL var2 = new RES_ENTER_CHANNEL();
      var2.seeds = new ArrayList();

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
               var2.standby = var1.readInt32();
            } else if (var5 == 24) {
               var2.encrypt = var1.readBool();
            } else if (var5 == 32) {
               var2.servertime = var1.readUInt64();
            } else if (var5 == 42) {
               var2.authkey = var1.readString();
            } else if (var5 == 48) {
               var2.launchinfo = var1.readInt32();
            } else if (var5 == 56) {
               var2.vip = var1.readInt32();
            } else if (var5 == 64) {
               var2.checkup = var1.readInt32();
            } else if (var5 == 72) {
               var2.checkuptype = var1.readInt32();
            } else if (var5 == 82) {
               var2.key = var1.readString();
            } else if (var5 == 88) {
               var2.roxytutorial = var1.readInt32();
            } else if (var5 == 96) {
               var2.gamecenterregdate = var1.readInt64();
            } else if (var5 == 104) {
               var2.areatype = var1.readInt32();
            } else if (var5 == 114) {
               Codec var12 = ProtobufProxy.create(PT_RETURN_USER_INFO.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var16 = var1.readRawVarint32();
               int var20 = var1.pushLimit(var16);
               var2.returnUserInfo = (PT_RETURN_USER_INFO)var12.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var20);
            } else if (var5 == 122) {
               Codec var11 = ProtobufProxy.create(PT_CHANNEL.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var15 = var1.readRawVarint32();
               int var19 = var1.pushLimit(var15);
               var2.channelinfo = (PT_CHANNEL)var11.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var19);
            } else if (var5 == 128) {
               if (var2.seeds == null) {
                  var2.seeds = new ArrayList();
               }

               var2.seeds.add(var1.readInt32());
            } else if (var5 == 130) {
               int var14 = var1.readRawVarint32();
               int var18 = var1.pushLimit(var14);
               if (var2.seeds == null) {
                  var2.seeds = new ArrayList();
               }

               while(var1.getBytesUntilLimit() > 0) {
                  var2.seeds.add(var1.readInt32());
               }

               var1.popLimit(var18);
            } else if (var5 != 138) {
               var1.skipField(var5);
            } else if (var5 != 130) {
               Codec var10 = ProtobufProxy.create(PT_EVENT_SELECT_INFO.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var13 = var1.readRawVarint32();
               int var17 = var1.pushLimit(var13);
               var2.eventSelectInfo = (PT_EVENT_SELECT_INFO)var10.readFrom(var1);
               var1.checkLastTagWas(0);
               var1.popLimit(var17);
            } else {
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.seeds == null) {
                  var2.seeds = new ArrayList();
               }

               while(var1.getBytesUntilLimit() > 0) {
                  var2.seeds.add(var1.readInt32());
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(RES_ENTER_CHANNEL.class);
         return this.descriptor = var1;
      }
   }
}
