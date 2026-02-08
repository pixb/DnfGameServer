package com.dnfm.game.bag.model;

import com.dnfm.mina.protobuf.AchievementInfoPacketData;
import java.util.ArrayList;
import java.util.List;

public class AchievementBox {
   private List<AchievementInfoPacketData> list = new ArrayList();

   public List<AchievementInfoPacketData> getList() {
      return this.list;
   }

   public void setList(List<AchievementInfoPacketData> list) {
      this.list = list;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof AchievementBox)) {
         return false;
      } else {
         AchievementBox other = (AchievementBox)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$list = this.getList();
            Object other$list = other.getList();
            if (this$list == null) {
               if (other$list != null) {
                  return false;
               }
            } else if (!this$list.equals(other$list)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof AchievementBox;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $list = this.getList();
      result = result * 59 + ($list == null ? 43 : $list.hashCode());
      return result;
   }

   public String toString() {
      return "AchievementBox(list=" + this.getList() + ")";
   }
}
