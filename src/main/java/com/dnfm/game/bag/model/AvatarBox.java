package com.dnfm.game.bag.model;

import com.dnfm.common.thread.IdGenerator;
import com.dnfm.game.config.Equip;
import com.dnfm.game.equip.EquipDataPool;
import com.dnfm.mina.protobuf.PT_AVATAR_ITEM;
import com.dnfm.mina.protobuf.PT_EQUIPPED;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AvatarBox {
   private int maxcount = 40;
   private Map<Long, PT_AVATAR_ITEM> avatarMap = new HashMap();

   public void addAvatar(PT_AVATAR_ITEM avatar) {
      if (this.avatarMap == null) {
         this.avatarMap = new HashMap();
      }

      long key = avatar.guid;
      this.avatarMap.put(key, avatar);
   }

   public PT_AVATAR_ITEM addAvatar(int index) {
      Equip equip = (Equip)EquipDataPool.index2Equip.get(index);
      PT_AVATAR_ITEM avatar = new PT_AVATAR_ITEM();
      avatar.score = equip.getScore();
      avatar.index = index;
      avatar.guid = IdGenerator.getNextId();
      this.avatarMap.put(avatar.guid, avatar);
      return avatar;
   }

   public PT_AVATAR_ITEM createAvatar(int index) {
      Equip equip = (Equip)EquipDataPool.index2Equip.get(index);
      PT_AVATAR_ITEM avatar = new PT_AVATAR_ITEM();
      avatar.score = equip.getScore();
      avatar.index = index;
      avatar.guid = IdGenerator.getNextId();
      this.avatarMap.put(avatar.guid, avatar);
      return avatar;
   }

   public PT_AVATAR_ITEM changeAvatar(PT_EQUIPPED ptEquip) {
      PT_AVATAR_ITEM avatar = new PT_AVATAR_ITEM();
      avatar.score = ptEquip.score;
      avatar.index = ptEquip.index;
      avatar.guid = ptEquip.guid;
      return avatar;
   }

   public List<PT_AVATAR_ITEM> getAvatars() {
      return new ArrayList(this.avatarMap.values());
   }

   public PT_AVATAR_ITEM getAvatar(long guid) {
      return (PT_AVATAR_ITEM)this.avatarMap.get(guid);
   }

   public void remove(long guid) {
      this.avatarMap.remove(guid);
   }

   public int getMaxcount() {
      return this.maxcount;
   }

   public Map<Long, PT_AVATAR_ITEM> getAvatarMap() {
      return this.avatarMap;
   }

   public void setMaxcount(int maxcount) {
      this.maxcount = maxcount;
   }

   public void setAvatarMap(Map<Long, PT_AVATAR_ITEM> avatarMap) {
      this.avatarMap = avatarMap;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof AvatarBox)) {
         return false;
      } else {
         AvatarBox other = (AvatarBox)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.getMaxcount() != other.getMaxcount()) {
            return false;
         } else {
            Object this$avatarMap = this.getAvatarMap();
            Object other$avatarMap = other.getAvatarMap();
            if (this$avatarMap == null) {
               if (other$avatarMap != null) {
                  return false;
               }
            } else if (!this$avatarMap.equals(other$avatarMap)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof AvatarBox;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + this.getMaxcount();
      Object $avatarMap = this.getAvatarMap();
      result = result * 59 + ($avatarMap == null ? 43 : $avatarMap.hashCode());
      return result;
   }

   public String toString() {
      return "AvatarBox(maxcount=" + this.getMaxcount() + ", avatarMap=" + this.getAvatarMap() + ")";
   }
}
