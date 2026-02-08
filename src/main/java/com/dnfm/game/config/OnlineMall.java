package com.dnfm.game.config;

import java.util.Date;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Comment;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

@Table("p_onlinemall")
public class OnlineMall {
   @Id
   private int id;
   @Column
   @Comment("物品名称")
   private String name;
   @Column
   @Comment("物品编号")
   private String number;
   @Column
   @Comment("花费金钱类型")
   private short costtype;
   @Column
   @Comment("所在位置")
   private short pos;
   @Column
   @Comment("限购数量")
   private short xiangou;
   @Column
   @Comment("价格")
   private int price;
   @Column
   @Comment("折扣")
   private byte sale;
   @Column
   @Comment("限购时间")
   private Date time;
   @Column
   @Comment("所在页面")
   private byte page;
   @Column
   @Comment("使用类型")
   private int type;
   @Column
   @Comment("物品类型")
   private short itemtype;
   @Column
   @Comment("量词")
   private String unit;

   public String getUnit() {
      return this.unit;
   }

   public void setUnit(String unit) {
      this.unit = unit;
   }

   public short getItemtype() {
      return this.itemtype;
   }

   public void setItemtype(short itemtype) {
      this.itemtype = itemtype;
   }

   public int getType() {
      return this.type;
   }

   public void setType(int type) {
      this.type = type;
   }

   public int getId() {
      return this.id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public String getName() {
      return this.name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getNumber() {
      return this.number;
   }

   public void setNumber(String number) {
      this.number = number;
   }

   public short getCosttype() {
      return this.costtype;
   }

   public void setCosttype(short costtype) {
      this.costtype = costtype;
   }

   public short getPos() {
      return this.pos;
   }

   public void setPos(short pos) {
      this.pos = pos;
   }

   public short getXiangou() {
      return this.xiangou;
   }

   public void setXiangou(short xiangou) {
      this.xiangou = xiangou;
   }

   public int getPrice() {
      return this.price;
   }

   public void setPrice(int price) {
      this.price = price;
   }

   public byte getSale() {
      return this.sale;
   }

   public void setSale(byte sale) {
      this.sale = sale;
   }

   public Date getTime() {
      return this.time;
   }

   public void setTime(Date time) {
      this.time = time;
   }

   public byte getPage() {
      return this.page;
   }

   public void setPage(byte page) {
      this.page = page;
   }
}
