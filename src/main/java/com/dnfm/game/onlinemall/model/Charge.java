package com.dnfm.game.onlinemall.model;

import java.util.Date;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Index;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;
import org.nutz.dao.entity.annotation.TableIndexes;

@Table("t_charge")
@TableIndexes({@Index(
   fields = {"userName"},
   unique = false
)})
public class Charge {
   @Id
   private int id;
   @Name
   private String tradeNo;
   @Column
   private int gold;
   @Column
   private int rmb;
   @Column
   private Date time;
   @Column
   private String userName;
   @Column
   private int status;

   public String getTradeNo() {
      return this.tradeNo;
   }

   public void setTradeNo(String tradeNo) {
      this.tradeNo = tradeNo;
   }

   public int getId() {
      return this.id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public int getGold() {
      return this.gold;
   }

   public void setGold(int gold) {
      this.gold = gold;
   }

   public int getRmb() {
      return this.rmb;
   }

   public void setRmb(int rmb) {
      this.rmb = rmb;
   }

   public Date getTime() {
      return this.time;
   }

   public void setTime(Date time) {
      this.time = time;
   }

   public String getUserName() {
      return this.userName;
   }

   public void setUserName(String userName) {
      this.userName = userName;
   }

   public int getStatus() {
      return this.status;
   }

   public void setStatus(int status) {
      this.status = status;
   }
}
