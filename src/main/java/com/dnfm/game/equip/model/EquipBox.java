package com.dnfm.game.equip.model;

import com.dnfm.common.thread.IdGenerator;
import com.dnfm.mina.protobuf.PT_EQUIP;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class EquipBox implements Serializable {
   private int maxcount = 40;
   private Map<Long, PT_EQUIP> equips = new ConcurrentHashMap<>();

   public Map<Long, PT_EQUIP> getEquips() {
      if (this.equips == null) {
         this.equips = new ConcurrentHashMap<>();
      }

      return this.equips;
   }

   public PT_EQUIP getEquip(long equipGuid) {
      return (PT_EQUIP)this.equips.get(equipGuid);
   }

   public PT_EQUIP removeEquip(long equipGuid) {
      return (PT_EQUIP)this.equips.remove(equipGuid);
   }

   public PT_EQUIP getEquipByIndex(int index) {
      for(PT_EQUIP equip : new ArrayList<>(this.equips.values())) {
         if (equip.getIndex() == index) {
            return equip;
         }
      }

      return null;
   }

   public void addEquip(PT_EQUIP pt_equip) {
      this.equips.put(pt_equip.guid, pt_equip);
   }

   public void addEquip(int index, int upgrade) {
      PT_EQUIP pt_equip = new PT_EQUIP();
      pt_equip.index = index;
      pt_equip.guid = IdGenerator.getNextId();
      if (upgrade != 0) {
         pt_equip.upgrade = upgrade;
      }

      pt_equip.quality = 100;
      pt_equip.endurance = 30;
      this.equips.put(pt_equip.guid, pt_equip);
   }

   public List<PT_EQUIP> getEquipList() {
      return new ArrayList(this.equips.values());
   }

   public int getMaxcount() {
      return this.maxcount;
   }

   public void setMaxcount(int maxcount) {
      this.maxcount = maxcount;
   }

   public void setEquips(Map<Long, PT_EQUIP> equips) {
      this.equips = equips;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof EquipBox)) {
         return false;
      } else {
         EquipBox other = (EquipBox)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.getMaxcount() != other.getMaxcount()) {
            return false;
         } else {
            Object this$equips = this.getEquips();
            Object other$equips = other.getEquips();
            if (this$equips == null) {
               if (other$equips != null) {
                  return false;
               }
            } else if (!this$equips.equals(other$equips)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof EquipBox;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + this.getMaxcount();
      Object $equips = this.getEquips();
      result = result * 59 + ($equips == null ? 43 : $equips.hashCode());
      return result;
   }

   public String toString() {
      return "EquipBox(maxcount=" + this.getMaxcount() + ", equips=" + this.getEquips() + ")";
   }
}
