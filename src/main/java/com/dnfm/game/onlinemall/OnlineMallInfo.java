package com.dnfm.game.onlinemall;

public class OnlineMallInfo {
   private String name;
   private String barcode;
   private short for_sale;
   private short show_pos;
   private short rpos;
   private short sale_quota;
   private short recommend;
   private int price;
   private byte discount;
   private int discountTime;
   private byte page;
   private short quota_limit;
   private byte must_vip = 0;
   private byte is_gift = -1;
   private byte follow_pet_type = -1;
   private int deadline;
   private int sale_end_time;

   public String getBarcode() {
      return this.barcode;
   }

   public void setBarcode(String barcode) {
      this.barcode = barcode;
   }

   public short getFor_sale() {
      return this.for_sale;
   }

   public void setFor_sale(short for_sale) {
      this.for_sale = for_sale;
   }

   public String getName() {
      return this.name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public short getShow_pos() {
      return this.show_pos;
   }

   public void setShow_pos(short show_pos) {
      this.show_pos = show_pos;
   }

   public short getRpos() {
      return this.rpos;
   }

   public void setRpos(short rpos) {
      this.rpos = rpos;
   }

   public short getSale_quota() {
      return this.sale_quota;
   }

   public void setSale_quota(short sale_quota) {
      this.sale_quota = sale_quota;
   }

   public short getRecommend() {
      return this.recommend;
   }

   public void setRecommend(short recommend) {
      this.recommend = recommend;
   }

   public int getPrice() {
      return this.price;
   }

   public void setPrice(int price) {
      this.price = price;
   }

   public byte getPage() {
      return this.page;
   }

   public void setPage(byte page) {
      this.page = page;
   }

   public byte getDiscount() {
      return this.discount;
   }

   public void setDiscount(byte discount) {
      this.discount = discount;
   }

   public int getDiscountTime() {
      return this.discountTime;
   }

   public void setDiscountTime(int discountTime) {
      this.discountTime = discountTime;
   }

   public short getQuota_limit() {
      return this.quota_limit;
   }

   public void setQuota_limit(short quota_limit) {
      this.quota_limit = quota_limit;
   }

   public byte getMust_vip() {
      return this.must_vip;
   }

   public void setMust_vip(byte must_vip) {
      this.must_vip = must_vip;
   }

   public byte getIs_gift() {
      return this.is_gift;
   }

   public void setIs_gift(byte is_gift) {
      this.is_gift = is_gift;
   }

   public byte getFollow_pet_type() {
      return this.follow_pet_type;
   }

   public void setFollow_pet_type(byte follow_pet_type) {
      this.follow_pet_type = follow_pet_type;
   }
}
