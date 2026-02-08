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

public class REQ_TLOG_CONTROLLER_DATA$$JProtoBufClass implements Codec<REQ_TLOG_CONTROLLER_DATA>, Serializable {
   public static final long serialVersionUID = 1L;
   private Descriptors.Descriptor descriptor;

   public byte[] encode(REQ_TLOG_CONTROLLER_DATA var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      CodedOutputStream var3 = CodedOutputStream.newInstance(var2);
      this.doWriteTo(var1, var3);
      var3.flush();
      return var2.toByteArray();
   }

   public REQ_TLOG_CONTROLLER_DATA decode(byte[] var1) throws IOException {
      CodedInputStream var2 = CodedInputStream.newInstance(var1, 0, var1.length);
      return this.readFrom(var2);
   }

   public int size(REQ_TLOG_CONTROLLER_DATA var1) throws IOException {
      int var2 = 0;
      Object var3 = null;
      if (!CodedConstant.isNull(var1.movecontroltype)) {
         Integer var13 = var1.movecontroltype;
         var2 += CodedOutputStream.computeInt32Size(1, var13);
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.runplaycondtype)) {
         Integer var14 = var1.runplaycondtype;
         var2 += CodedOutputStream.computeInt32Size(2, var14);
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.slideskilltype)) {
         Integer var15 = var1.slideskilltype;
         var2 += CodedOutputStream.computeInt32Size(3, var15);
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.combocount)) {
         Integer var16 = var1.combocount;
         var2 += CodedOutputStream.computeInt32Size(4, var16);
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.pvpcombocount)) {
         Integer var17 = var1.pvpcombocount;
         var2 += CodedOutputStream.computeInt32Size(5, var17);
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.joystickdirectiontype)) {
         Integer var18 = var1.joystickdirectiontype;
         var2 += CodedOutputStream.computeInt32Size(6, var18);
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.joysticksize)) {
         Integer var19 = var1.joysticksize;
         var2 += CodedOutputStream.computeInt32Size(7, var19);
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.attackguideline)) {
         Integer var20 = var1.attackguideline;
         var2 += CodedOutputStream.computeInt32Size(8, var20);
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.joystickline)) {
         String var21 = var1.joystickline;
         var2 += CodedOutputStream.computeStringSize(9, var21);
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.autoskilltype)) {
         Integer var22 = var1.autoskilltype;
         var2 += CodedOutputStream.computeInt32Size(10, var22);
      }

      return var2;
   }

   public void doWriteTo(REQ_TLOG_CONTROLLER_DATA var1, CodedOutputStream var2) throws IOException {
      Object var3 = null;
      if (!CodedConstant.isNull(var1.movecontroltype)) {
         Integer var13 = var1.movecontroltype;
         if (var13 != null) {
            var2.writeInt32(1, var13);
         }
      }

      Object var4 = null;
      if (!CodedConstant.isNull(var1.runplaycondtype)) {
         Integer var14 = var1.runplaycondtype;
         if (var14 != null) {
            var2.writeInt32(2, var14);
         }
      }

      Object var5 = null;
      if (!CodedConstant.isNull(var1.slideskilltype)) {
         Integer var15 = var1.slideskilltype;
         if (var15 != null) {
            var2.writeInt32(3, var15);
         }
      }

      Object var6 = null;
      if (!CodedConstant.isNull(var1.combocount)) {
         Integer var16 = var1.combocount;
         if (var16 != null) {
            var2.writeInt32(4, var16);
         }
      }

      Object var7 = null;
      if (!CodedConstant.isNull(var1.pvpcombocount)) {
         Integer var17 = var1.pvpcombocount;
         if (var17 != null) {
            var2.writeInt32(5, var17);
         }
      }

      Object var8 = null;
      if (!CodedConstant.isNull(var1.joystickdirectiontype)) {
         Integer var18 = var1.joystickdirectiontype;
         if (var18 != null) {
            var2.writeInt32(6, var18);
         }
      }

      Object var9 = null;
      if (!CodedConstant.isNull(var1.joysticksize)) {
         Integer var19 = var1.joysticksize;
         if (var19 != null) {
            var2.writeInt32(7, var19);
         }
      }

      Object var10 = null;
      if (!CodedConstant.isNull(var1.attackguideline)) {
         Integer var20 = var1.attackguideline;
         if (var20 != null) {
            var2.writeInt32(8, var20);
         }
      }

      Object var11 = null;
      if (!CodedConstant.isNull(var1.joystickline)) {
         String var21 = var1.joystickline;
         if (var21 != null) {
            var2.writeString(9, var21);
         }
      }

      Object var12 = null;
      if (!CodedConstant.isNull(var1.autoskilltype)) {
         Integer var22 = var1.autoskilltype;
         if (var22 != null) {
            var2.writeInt32(10, var22);
         }
      }

   }

   public void writeTo(REQ_TLOG_CONTROLLER_DATA var1, CodedOutputStream var2) throws IOException {
      this.doWriteTo(var1, var2);
   }

   public REQ_TLOG_CONTROLLER_DATA readFrom(CodedInputStream var1) throws IOException {
      REQ_TLOG_CONTROLLER_DATA var2 = new REQ_TLOG_CONTROLLER_DATA();

      try {
         boolean var3 = false;
         Object var4 = null;

         while(!var3) {
            int var5 = var1.readTag();
            if (var5 == 0) {
               break;
            }

            if (var5 == 8) {
               var2.movecontroltype = var1.readInt32();
            } else if (var5 == 16) {
               var2.runplaycondtype = var1.readInt32();
            } else if (var5 == 24) {
               var2.slideskilltype = var1.readInt32();
            } else if (var5 == 32) {
               var2.combocount = var1.readInt32();
            } else if (var5 == 40) {
               var2.pvpcombocount = var1.readInt32();
            } else if (var5 == 48) {
               var2.joystickdirectiontype = var1.readInt32();
            } else if (var5 == 56) {
               var2.joysticksize = var1.readInt32();
            } else if (var5 == 64) {
               var2.attackguideline = var1.readInt32();
            } else if (var5 == 74) {
               var2.joystickline = var1.readString();
            } else if (var5 == 80) {
               var2.autoskilltype = var1.readInt32();
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
         Descriptors.Descriptor var1 = CodedConstant.getDescriptor(REQ_TLOG_CONTROLLER_DATA.class);
         return this.descriptor = var1;
      }
   }
}
