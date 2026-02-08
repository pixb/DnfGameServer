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

public class RES_DUNGEON_TICKETS$$JProtoBufClass implements Codec<RES_DUNGEON_TICKETS>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(RES_DUNGEON_TICKETS var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public RES_DUNGEON_TICKETS decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(RES_DUNGEON_TICKETS var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var25 = var1.error;
         var2 += CodedOutputStream.computeInt32Size(1, var25);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.ticket)) {
         List var26 = var1.ticket;
         var2 += CodedConstant.computeListSize(2, var26, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.adventureticket)) {
         List var27 = var1.adventureticket;
         var2 += CodedConstant.computeListSize(3, var27, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.fatiguetime)) {
         Integer var28 = var1.fatiguetime;
         var2 += CodedOutputStream.computeInt32Size(4, var28);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.helpercount)) {
         Integer var29 = var1.helpercount;
         var2 += CodedOutputStream.computeInt32Size(5, var29);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.jarofgreed)) {
         Integer var30 = var1.jarofgreed;
         var2 += CodedOutputStream.computeInt32Size(6, var30);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.lotteryfreecount)) {
         Integer var31 = var1.lotteryfreecount;
         var2 += CodedOutputStream.computeInt32Size(7, var31);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.lotterychargecount)) {
         Integer var32 = var1.lotterychargecount;
         var2 += CodedOutputStream.computeInt32Size(8, var32);
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.recvfriendcount)) {
         Integer var33 = var1.recvfriendcount;
         var2 += CodedOutputStream.computeInt32Size(9, var33);
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.sendfriendcount)) {
         Integer var34 = var1.sendfriendcount;
         var2 += CodedOutputStream.computeInt32Size(10, var34);
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.mentoringquestpublishcount)) {
         Integer var35 = var1.mentoringquestpublishcount;
         var2 += CodedOutputStream.computeInt32Size(11, var35);
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.ftimestps)) {
         List var36 = var1.ftimestps;
         var2 += CodedConstant.computeListSize(12, var36, FieldType.INT32, false, (File)ProtobufProxy.OUTPUT_PATH.get(), true);
      }

      Object var15 = null;
      if (!CodedConstant.isNull(var1.sddeathrewardcount)) {
         Integer var37 = var1.sddeathrewardcount;
         var2 += CodedOutputStream.computeInt32Size(13, var37);
      }

      Object var16 = null;
      if (!CodedConstant.isNull(var1.recvplatformfriendcount)) {
         Integer var38 = var1.recvplatformfriendcount;
         var2 += CodedOutputStream.computeInt32Size(14, var38);
      }

      Object var17 = null;
      if (!CodedConstant.isNull(var1.sendplatformfriendcount)) {
         Integer var39 = var1.sendplatformfriendcount;
         var2 += CodedOutputStream.computeInt32Size(15, var39);
      }

      Object var18 = null;
      if (!CodedConstant.isNull(var1.guildredpacketrecvcount)) {
         Integer var40 = var1.guildredpacketrecvcount;
         var2 += CodedOutputStream.computeInt32Size(16, var40);
      }

      Object var19 = null;
      if (!CodedConstant.isNull(var1.guildredpacketsendcount)) {
         Integer var41 = var1.guildredpacketsendcount;
         var2 += CodedOutputStream.computeInt32Size(17, var41);
      }

      Object var20 = null;
      if (!CodedConstant.isNull(var1.battleleaguerewardlimit)) {
         Integer var42 = var1.battleleaguerewardlimit;
         var2 += CodedOutputStream.computeInt32Size(18, var42);
      }

      Object var21 = null;
      if (!CodedConstant.isNull(var1.todaycleardungeonlist)) {
         List var43 = var1.todaycleardungeonlist;
         var2 += CodedConstant.computeListSize(19, var43, FieldType.INT32, false, (File)ProtobufProxy.OUTPUT_PATH.get(), true);
      }

      Object var22 = null;
      if (!CodedConstant.isNull(var1.clearsweepdungeonlist)) {
         List var44 = var1.clearsweepdungeonlist;
         var2 += CodedConstant.computeListSize(20, var44, FieldType.INT32, false, (File)ProtobufProxy.OUTPUT_PATH.get(), true);
      }

      Object var23 = null;
      if (!CodedConstant.isNull(var1.gauges)) {
         List var45 = var1.gauges;
         var2 += CodedConstant.computeListSize(21, var45, FieldType.OBJECT, false, (File)ProtobufProxy.OUTPUT_PATH.get(), false);
      }

      Object var24 = null;
      if (!CodedConstant.isNull(var1.battleleagueguildrewardlimit)) {
         Integer var46 = var1.battleleagueguildrewardlimit;
         var2 += CodedOutputStream.computeInt32Size(22, var46);
      }

      return var2;
   }

   public void doWriteTo(RES_DUNGEON_TICKETS var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.error)) {
         Integer var25 = var1.error;
         if (var25 != null) {
            var2.writeInt32(1, var25);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.ticket)) {
         List var26 = var1.ticket;
         if (var26 != null) {
            CodedConstant.writeToList(var2, 2, FieldType.OBJECT, var26, false);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.adventureticket)) {
         List var27 = var1.adventureticket;
         if (var27 != null) {
            CodedConstant.writeToList(var2, 3, FieldType.OBJECT, var27, false);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.fatiguetime)) {
         Integer var28 = var1.fatiguetime;
         if (var28 != null) {
            var2.writeInt32(4, var28);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.helpercount)) {
         Integer var29 = var1.helpercount;
         if (var29 != null) {
            var2.writeInt32(5, var29);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.jarofgreed)) {
         Integer var30 = var1.jarofgreed;
         if (var30 != null) {
            var2.writeInt32(6, var30);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.lotteryfreecount)) {
         Integer var31 = var1.lotteryfreecount;
         if (var31 != null) {
            var2.writeInt32(7, var31);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.lotterychargecount)) {
         Integer var32 = var1.lotterychargecount;
         if (var32 != null) {
            var2.writeInt32(8, var32);
         }
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.recvfriendcount)) {
         Integer var33 = var1.recvfriendcount;
         if (var33 != null) {
            var2.writeInt32(9, var33);
         }
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.sendfriendcount)) {
         Integer var34 = var1.sendfriendcount;
         if (var34 != null) {
            var2.writeInt32(10, var34);
         }
      }

      Object var13 = null;
      if (!CodedConstant.isNull(var1.mentoringquestpublishcount)) {
         Integer var35 = var1.mentoringquestpublishcount;
         if (var35 != null) {
            var2.writeInt32(11, var35);
         }
      }

      Object var14 = null;
      if (!CodedConstant.isNull(var1.ftimestps)) {
         List var36 = var1.ftimestps;
         if (var36 != null) {
            CodedConstant.writeToList(var2, 12, FieldType.INT32, var36, true);
         }
      }

      Object var15 = null;
      if (!CodedConstant.isNull(var1.sddeathrewardcount)) {
         Integer var37 = var1.sddeathrewardcount;
         if (var37 != null) {
            var2.writeInt32(13, var37);
         }
      }

      Object var16 = null;
      if (!CodedConstant.isNull(var1.recvplatformfriendcount)) {
         Integer var38 = var1.recvplatformfriendcount;
         if (var38 != null) {
            var2.writeInt32(14, var38);
         }
      }

      Object var17 = null;
      if (!CodedConstant.isNull(var1.sendplatformfriendcount)) {
         Integer var39 = var1.sendplatformfriendcount;
         if (var39 != null) {
            var2.writeInt32(15, var39);
         }
      }

      Object var18 = null;
      if (!CodedConstant.isNull(var1.guildredpacketrecvcount)) {
         Integer var40 = var1.guildredpacketrecvcount;
         if (var40 != null) {
            var2.writeInt32(16, var40);
         }
      }

      Object var19 = null;
      if (!CodedConstant.isNull(var1.guildredpacketsendcount)) {
         Integer var41 = var1.guildredpacketsendcount;
         if (var41 != null) {
            var2.writeInt32(17, var41);
         }
      }

      Object var20 = null;
      if (!CodedConstant.isNull(var1.battleleaguerewardlimit)) {
         Integer var42 = var1.battleleaguerewardlimit;
         if (var42 != null) {
            var2.writeInt32(18, var42);
         }
      }

      Object var21 = null;
      if (!CodedConstant.isNull(var1.todaycleardungeonlist)) {
         List var43 = var1.todaycleardungeonlist;
         if (var43 != null) {
            CodedConstant.writeToList(var2, 19, FieldType.INT32, var43, true);
         }
      }

      Object var22 = null;
      if (!CodedConstant.isNull(var1.clearsweepdungeonlist)) {
         List var44 = var1.clearsweepdungeonlist;
         if (var44 != null) {
            CodedConstant.writeToList(var2, 20, FieldType.INT32, var44, true);
         }
      }

      Object var23 = null;
      if (!CodedConstant.isNull(var1.gauges)) {
         List var45 = var1.gauges;
         if (var45 != null) {
            CodedConstant.writeToList(var2, 21, FieldType.OBJECT, var45, false);
         }
      }

      Object var24 = null;
      if (!CodedConstant.isNull(var1.battleleagueguildrewardlimit)) {
         Integer var46 = var1.battleleagueguildrewardlimit;
         if (var46 != null) {
            var2.writeInt32(22, var46);
         }
      }

   }

   public void writeTo(RES_DUNGEON_TICKETS var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public RES_DUNGEON_TICKETS readFrom(CodedInputStream var1) throws IOException {
      RES_DUNGEON_TICKETS var2 = new RES_DUNGEON_TICKETS();
      var2.ticket = new ArrayList();
      var2.adventureticket = new ArrayList();
      var2.ftimestps = new ArrayList();
      var2.todaycleardungeonlist = new ArrayList();
      var2.clearsweepdungeonlist = new ArrayList();
      var2.gauges = new ArrayList();

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
            } else if (var5 == 18) {
               Codec var12 = ProtobufProxy.create(PT_TICKET.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var23 = var1.readRawVarint32();
               int var34 = var1.pushLimit(var23);
               if (var2.ticket == null) {
                  var2.ticket = new ArrayList();
               }

               var2.ticket.add((PT_TICKET)var12.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var34);
            } else if (var5 == 26) {
               Codec var11 = ProtobufProxy.create(PT_ACCOUNT_TICKET.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var22 = var1.readRawVarint32();
               int var33 = var1.pushLimit(var22);
               if (var2.adventureticket == null) {
                  var2.adventureticket = new ArrayList();
               }

               var2.adventureticket.add((PT_ACCOUNT_TICKET)var11.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var33);
            } else if (var5 == 32) {
               var2.fatiguetime = var1.readInt32();
            } else if (var5 == 40) {
               var2.helpercount = var1.readInt32();
            } else if (var5 == 48) {
               var2.jarofgreed = var1.readInt32();
            } else if (var5 == 56) {
               var2.lotteryfreecount = var1.readInt32();
            } else if (var5 == 64) {
               var2.lotterychargecount = var1.readInt32();
            } else if (var5 == 72) {
               var2.recvfriendcount = var1.readInt32();
            } else if (var5 == 80) {
               var2.sendfriendcount = var1.readInt32();
            } else if (var5 == 88) {
               var2.mentoringquestpublishcount = var1.readInt32();
            } else if (var5 == 96) {
               if (var2.ftimestps == null) {
                  var2.ftimestps = new ArrayList();
               }

               var2.ftimestps.add(var1.readInt32());
            } else if (var5 == 98) {
               int var21 = var1.readRawVarint32();
               int var32 = var1.pushLimit(var21);
               if (var2.ftimestps == null) {
                  var2.ftimestps = new ArrayList();
               }

               while(var1.getBytesUntilLimit() > 0) {
                  var2.ftimestps.add(var1.readInt32());
               }

               var1.popLimit(var32);
            } else if (var5 == 104) {
               var2.sddeathrewardcount = var1.readInt32();
            } else if (var5 == 112) {
               var2.recvplatformfriendcount = var1.readInt32();
            } else if (var5 == 120) {
               var2.sendplatformfriendcount = var1.readInt32();
            } else if (var5 == 128) {
               var2.guildredpacketrecvcount = var1.readInt32();
            } else if (var5 == 136) {
               var2.guildredpacketsendcount = var1.readInt32();
            } else if (var5 == 144) {
               var2.battleleaguerewardlimit = var1.readInt32();
            } else if (var5 == 152) {
               if (var2.todaycleardungeonlist == null) {
                  var2.todaycleardungeonlist = new ArrayList();
               }

               var2.todaycleardungeonlist.add(var1.readInt32());
            } else if (var5 == 98) {
               int var20 = var1.readRawVarint32();
               int var31 = var1.pushLimit(var20);
               if (var2.ftimestps == null) {
                  var2.ftimestps = new ArrayList();
               }

               while(var1.getBytesUntilLimit() > 0) {
                  var2.ftimestps.add(var1.readInt32());
               }

               var1.popLimit(var31);
            } else if (var5 == 154) {
               int var19 = var1.readRawVarint32();
               int var30 = var1.pushLimit(var19);
               if (var2.todaycleardungeonlist == null) {
                  var2.todaycleardungeonlist = new ArrayList();
               }

               while(var1.getBytesUntilLimit() > 0) {
                  var2.todaycleardungeonlist.add(var1.readInt32());
               }

               var1.popLimit(var30);
            } else if (var5 == 160) {
               if (var2.clearsweepdungeonlist == null) {
                  var2.clearsweepdungeonlist = new ArrayList();
               }

               var2.clearsweepdungeonlist.add(var1.readInt32());
            } else if (var5 == 98) {
               int var18 = var1.readRawVarint32();
               int var29 = var1.pushLimit(var18);
               if (var2.ftimestps == null) {
                  var2.ftimestps = new ArrayList();
               }

               while(var1.getBytesUntilLimit() > 0) {
                  var2.ftimestps.add(var1.readInt32());
               }

               var1.popLimit(var29);
            } else if (var5 == 154) {
               int var17 = var1.readRawVarint32();
               int var28 = var1.pushLimit(var17);
               if (var2.todaycleardungeonlist == null) {
                  var2.todaycleardungeonlist = new ArrayList();
               }

               while(var1.getBytesUntilLimit() > 0) {
                  var2.todaycleardungeonlist.add(var1.readInt32());
               }

               var1.popLimit(var28);
            } else if (var5 == 162) {
               int var16 = var1.readRawVarint32();
               int var27 = var1.pushLimit(var16);
               if (var2.clearsweepdungeonlist == null) {
                  var2.clearsweepdungeonlist = new ArrayList();
               }

               while(var1.getBytesUntilLimit() > 0) {
                  var2.clearsweepdungeonlist.add(var1.readInt32());
               }

               var1.popLimit(var27);
            } else if (var5 != 170) {
               if (var5 == 176) {
                  var2.battleleagueguildrewardlimit = var1.readInt32();
               } else {
                  var1.skipField(var5);
               }
            } else if (var5 == 98) {
               int var15 = var1.readRawVarint32();
               int var26 = var1.pushLimit(var15);
               if (var2.ftimestps == null) {
                  var2.ftimestps = new ArrayList();
               }

               while(var1.getBytesUntilLimit() > 0) {
                  var2.ftimestps.add(var1.readInt32());
               }

               var1.popLimit(var26);
            } else if (var5 == 154) {
               int var14 = var1.readRawVarint32();
               int var25 = var1.pushLimit(var14);
               if (var2.todaycleardungeonlist == null) {
                  var2.todaycleardungeonlist = new ArrayList();
               }

               while(var1.getBytesUntilLimit() > 0) {
                  var2.todaycleardungeonlist.add(var1.readInt32());
               }

               var1.popLimit(var25);
            } else if (var5 != 162) {
               Codec var10 = ProtobufProxy.create(PT_GAUGE.class, false, (File)ProtobufProxy.OUTPUT_PATH.get());
               int var13 = var1.readRawVarint32();
               int var24 = var1.pushLimit(var13);
               if (var2.gauges == null) {
                  var2.gauges = new ArrayList();
               }

               var2.gauges.add((PT_GAUGE)var10.readFrom(var1));
               var1.checkLastTagWas(0);
               var1.popLimit(var24);
            } else {
               int var6 = var1.readRawVarint32();
               int var7 = var1.pushLimit(var6);
               if (var2.clearsweepdungeonlist == null) {
                  var2.clearsweepdungeonlist = new ArrayList();
               }

               while(var1.getBytesUntilLimit() > 0) {
                  var2.clearsweepdungeonlist.add(var1.readInt32());
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(RES_DUNGEON_TICKETS.class);
         return this.descriptor = var1;
      }
   }
}
