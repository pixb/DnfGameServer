package com.dnfm.game.role.model;

import java.io.Serializable;
import java.util.Date;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

@Table("t_invite")
public class Invite implements Serializable {
   @Id
   private int id;
   @Column
   private String account;
   @Column
   private String inviteAccount;
   @Column
   private int rmb;
   @Column
   private Date createtime;
   @Column
   private Date updatetime;
   @Column
   private int state;

   public int getId() {
      return this.id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public String getAccount() {
      return this.account;
   }

   public void setAccount(String account) {
      this.account = account;
   }

   public String getInviteAccount() {
      return this.inviteAccount;
   }

   public void setInviteAccount(String inviteAccount) {
      this.inviteAccount = inviteAccount;
   }

   public int getRmb() {
      return this.rmb;
   }

   public void setRmb(int rmb) {
      this.rmb = rmb;
   }

   public Date getCreatetime() {
      return this.createtime;
   }

   public void setCreatetime(Date createtime) {
      this.createtime = createtime;
   }

   public Date getUpdatetime() {
      return this.updatetime;
   }

   public void setUpdatetime(Date updatetime) {
      this.updatetime = updatetime;
   }

   public int getState() {
      return this.state;
   }

   public void setState(int state) {
      this.state = state;
   }
}
