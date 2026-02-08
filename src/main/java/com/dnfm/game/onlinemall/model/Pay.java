package com.dnfm.game.onlinemall.model;

import java.util.Date;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Comment;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

@Table("t_paydata")
public class Pay {
   @Id
   private int id;
   @Column("userid")
   @Comment("用户ID")
   private String userid;
   @Column("orderid")
   @Comment("订单号")
   private String orderid;
   @Column("pkg")
   @Comment("包名")
   private String pkg;
   @Column("money")
   @Comment("金额")
   private String money;
   @Column("gamename")
   @Comment("游戏名称")
   private String gamename;
   @Column("createtime")
   @Comment("时间")
   private Date createtime = new Date();
   @Column("app_channel")
   @Comment("游戏渠道码")
   private String app_channel;
   @Column("userchannel")
   @Comment("用户渠道")
   private String userchannel;
   @Column("status")
   @Comment("支付状态")
   private String status;

   public String getUserid() {
      return this.userid;
   }

   public void setUserid(String userid) {
      this.userid = userid;
   }

   public String getOrderid() {
      return this.orderid;
   }

   public void setOrderid(String orderid) {
      this.orderid = orderid;
   }

   public String getPkg() {
      return this.pkg;
   }

   public void setPkg(String pkg) {
      this.pkg = pkg;
   }

   public String getMoney() {
      return this.money;
   }

   public void setMoney(String money) {
      this.money = money;
   }

   public String getGamename() {
      return this.gamename;
   }

   public void setGamename(String gamename) {
      this.gamename = gamename;
   }

   public Date getCreatetime() {
      return this.createtime;
   }

   public void setCreatetime(Date createtime) {
      this.createtime = createtime;
   }

   public String getApp_channel() {
      return this.app_channel;
   }

   public void setApp_channel(String app_channel) {
      this.app_channel = app_channel;
   }

   public String getUserchannel() {
      return this.userchannel;
   }

   public void setUserchannel(String userchannel) {
      this.userchannel = userchannel;
   }

   public String getStatus() {
      return this.status;
   }

   public void setStatus(String status) {
      this.status = status;
   }

   public int getId() {
      return this.id;
   }

   public void setId(int id) {
      this.id = id;
   }
}
