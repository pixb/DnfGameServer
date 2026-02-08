package com.dnfm.mina.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

public class PT_STACKABLE {
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 1,
      required = false
   )
   public Integer index;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 2,
      required = false
   )
   public Integer count;
   @Protobuf(
      fieldType = FieldType.BOOL,
      order = 3,
      required = false
   )
   public Boolean bind;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 4,
      required = false
   )
   public Integer scount;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 5,
      required = false
   )
   public Integer tcount;
   @Protobuf(
      fieldType = FieldType.INT64,
      order = 6,
      required = false
   )
   public Long expiretime;
   @Protobuf(
      fieldType = FieldType.INT64,
      order = 7,
      required = false
   )
   public Long acquisitiontime;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 8,
      required = false
   )
   public Integer sindex;
   @Protobuf(
      fieldType = FieldType.INT32,
      order = 9,
      required = false
   )
   public Integer slotindex;

   public Integer getIndex() {
      return this.index;
   }

   public void setIndex(Integer index) {
      this.index = index;
   }

   public Integer getCount() {
      return this.count;
   }

   public void setCount(Integer count) {
      this.count = count;
   }

   public Boolean getBind() {
      return this.bind;
   }

   public void setBind(Boolean bind) {
      this.bind = bind;
   }

   public Integer getScount() {
      return this.scount;
   }

   public void setScount(Integer scount) {
      this.scount = scount;
   }

   public Integer getTcount() {
      return this.tcount;
   }

   public void setTcount(Integer tcount) {
      this.tcount = tcount;
   }

   public Long getExpiretime() {
      return this.expiretime;
   }

   public void setExpiretime(Long expiretime) {
      this.expiretime = expiretime;
   }

   public Long getAcquisitiontime() {
      return this.acquisitiontime;
   }

   public void setAcquisitiontime(Long acquisitiontime) {
      this.acquisitiontime = acquisitiontime;
   }

   public Integer getSindex() {
      return this.sindex;
   }

   public void setSindex(Integer sindex) {
      this.sindex = sindex;
   }

   public Integer getSlotindex() {
      return this.slotindex;
   }

   public void setSlotindex(Integer slotindex) {
      this.slotindex = slotindex;
   }
}
