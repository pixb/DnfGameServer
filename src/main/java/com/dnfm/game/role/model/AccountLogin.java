package com.dnfm.game.role.model;

import java.io.Serializable;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Comment;
import org.nutz.dao.entity.annotation.Default;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

@Table("t_account")
public class AccountLogin implements Serializable {
   @Name
   @Comment("openId")
   private String id;
   @Column
   private String userID;
   @Column
   private String passwd;
   @Column
   private boolean isStop;
   @Column
   private short privilege;
   @Column
   @Default("0")
   private int score = 0;
   @Column
   private String channelNo;

   public boolean getIsStop() {
      return this.isStop;
   }

   public void setIsStop(boolean isStop) {
      this.isStop = isStop;
   }

   public String getId() {
      return this.id;
   }

   public String getUserID() {
      return this.userID;
   }

   public String getPasswd() {
      return this.passwd;
   }

   public short getPrivilege() {
      return this.privilege;
   }

   public int getScore() {
      return this.score;
   }

   public String getChannelNo() {
      return this.channelNo;
   }

   public void setId(String id) {
      this.id = id;
   }

   public void setUserID(String userID) {
      this.userID = userID;
   }

   public void setPasswd(String passwd) {
      this.passwd = passwd;
   }

   public void setPrivilege(short privilege) {
      this.privilege = privilege;
   }

   public void setScore(int score) {
      this.score = score;
   }

   public void setChannelNo(String channelNo) {
      this.channelNo = channelNo;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof AccountLogin)) {
         return false;
      } else {
         AccountLogin other = (AccountLogin)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$id = this.getId();
            Object other$id = other.getId();
            if (this$id == null) {
               if (other$id != null) {
                  return false;
               }
            } else if (!this$id.equals(other$id)) {
               return false;
            }

            Object this$userID = this.getUserID();
            Object other$userID = other.getUserID();
            if (this$userID == null) {
               if (other$userID != null) {
                  return false;
               }
            } else if (!this$userID.equals(other$userID)) {
               return false;
            }

            Object this$passwd = this.getPasswd();
            Object other$passwd = other.getPasswd();
            if (this$passwd == null) {
               if (other$passwd != null) {
                  return false;
               }
            } else if (!this$passwd.equals(other$passwd)) {
               return false;
            }

            if (this.getIsStop() != other.getIsStop()) {
               return false;
            } else if (this.getPrivilege() != other.getPrivilege()) {
               return false;
            } else if (this.getScore() != other.getScore()) {
               return false;
            } else {
               Object this$channelNo = this.getChannelNo();
               Object other$channelNo = other.getChannelNo();
               if (this$channelNo == null) {
                  if (other$channelNo != null) {
                     return false;
                  }
               } else if (!this$channelNo.equals(other$channelNo)) {
                  return false;
               }

               return true;
            }
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof AccountLogin;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $id = this.getId();
      result = result * 59 + ($id == null ? 43 : $id.hashCode());
      Object $userID = this.getUserID();
      result = result * 59 + ($userID == null ? 43 : $userID.hashCode());
      Object $passwd = this.getPasswd();
      result = result * 59 + ($passwd == null ? 43 : $passwd.hashCode());
      result = result * 59 + (this.getIsStop() ? 79 : 97);
      result = result * 59 + this.getPrivilege();
      result = result * 59 + this.getScore();
      Object $channelNo = this.getChannelNo();
      result = result * 59 + ($channelNo == null ? 43 : $channelNo.hashCode());
      return result;
   }

   public String toString() {
      return "AccountLogin(id=" + this.getId() + ", userID=" + this.getUserID() + ", passwd=" + this.getPasswd() + ", isStop=" + this.getIsStop() + ", privilege=" + this.getPrivilege() + ", score=" + this.getScore() + ", channelNo=" + this.getChannelNo() + ")";
   }
}
