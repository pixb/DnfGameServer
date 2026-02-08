package com.dnfm.common.bean;

import java.util.Date;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

@Table("pay_gateway")
public class PayGateWay {
   @Id
   private long id;
   @Column
   private String account;
   @Column
   private long money;
   @Column
   private int rmb;
   @Column
   private Date create_time;
   @Column
   private Date update_time;
   @Column
   private int state;
   @Column
   private String orderid;

   public long getId() {
      return this.id;
   }

   public String getAccount() {
      return this.account;
   }

   public long getMoney() {
      return this.money;
   }

   public int getRmb() {
      return this.rmb;
   }

   public Date getCreate_time() {
      return this.create_time;
   }

   public Date getUpdate_time() {
      return this.update_time;
   }

   public int getState() {
      return this.state;
   }

   public String getOrderid() {
      return this.orderid;
   }

   public void setId(long id) {
      this.id = id;
   }

   public void setAccount(String account) {
      this.account = account;
   }

   public void setMoney(long money) {
      this.money = money;
   }

   public void setRmb(int rmb) {
      this.rmb = rmb;
   }

   public void setCreate_time(Date create_time) {
      this.create_time = create_time;
   }

   public void setUpdate_time(Date update_time) {
      this.update_time = update_time;
   }

   public void setState(int state) {
      this.state = state;
   }

   public void setOrderid(String orderid) {
      this.orderid = orderid;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof PayGateWay)) {
         return false;
      } else {
         PayGateWay other = (PayGateWay)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.getId() != other.getId()) {
            return false;
         } else {
            Object this$account = this.getAccount();
            Object other$account = other.getAccount();
            if (this$account == null) {
               if (other$account != null) {
                  return false;
               }
            } else if (!this$account.equals(other$account)) {
               return false;
            }

            if (this.getMoney() != other.getMoney()) {
               return false;
            } else if (this.getRmb() != other.getRmb()) {
               return false;
            } else {
               Object this$create_time = this.getCreate_time();
               Object other$create_time = other.getCreate_time();
               if (this$create_time == null) {
                  if (other$create_time != null) {
                     return false;
                  }
               } else if (!this$create_time.equals(other$create_time)) {
                  return false;
               }

               Object this$update_time = this.getUpdate_time();
               Object other$update_time = other.getUpdate_time();
               if (this$update_time == null) {
                  if (other$update_time != null) {
                     return false;
                  }
               } else if (!this$update_time.equals(other$update_time)) {
                  return false;
               }

               if (this.getState() != other.getState()) {
                  return false;
               } else {
                  Object this$orderid = this.getOrderid();
                  Object other$orderid = other.getOrderid();
                  if (this$orderid == null) {
                     if (other$orderid != null) {
                        return false;
                     }
                  } else if (!this$orderid.equals(other$orderid)) {
                     return false;
                  }

                  return true;
               }
            }
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof PayGateWay;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      long $id = this.getId();
      result = result * 59 + (int)($id >>> 32 ^ $id);
      Object $account = this.getAccount();
      result = result * 59 + ($account == null ? 43 : $account.hashCode());
      long $money = this.getMoney();
      result = result * 59 + (int)($money >>> 32 ^ $money);
      result = result * 59 + this.getRmb();
      Object $create_time = this.getCreate_time();
      result = result * 59 + ($create_time == null ? 43 : $create_time.hashCode());
      Object $update_time = this.getUpdate_time();
      result = result * 59 + ($update_time == null ? 43 : $update_time.hashCode());
      result = result * 59 + this.getState();
      Object $orderid = this.getOrderid();
      result = result * 59 + ($orderid == null ? 43 : $orderid.hashCode());
      return result;
   }

   public String toString() {
      return "PayGateWay(id=" + this.getId() + ", account=" + this.getAccount() + ", money=" + this.getMoney() + ", rmb=" + this.getRmb() + ", create_time=" + this.getCreate_time() + ", update_time=" + this.getUpdate_time() + ", state=" + this.getState() + ", orderid=" + this.getOrderid() + ")";
   }
}
