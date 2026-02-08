package com.dnfm.game.skill.model;

import com.dnfm.common.utils.Util;
import com.dnfm.mina.protobuf.PT_ALL_SKILL_SLOT;
import com.dnfm.mina.protobuf.PT_SKILL_SLOT;
import java.util.ArrayList;
import java.util.List;

public class SkillslotBox {
   private PT_ALL_SKILL_SLOT skillslot = new PT_ALL_SKILL_SLOT();
   private PT_ALL_SKILL_SLOT skillslot_pk = new PT_ALL_SKILL_SLOT();
   private List<PT_SKILL_SLOT> ptSkillSlotList_dungeon = new ArrayList();

   public void addPtSkillSlot_dungeon(PT_SKILL_SLOT pt_skill_slot) {
      this.ptSkillSlotList_dungeon.add(pt_skill_slot);
   }

   public void addActive(PT_SKILL_SLOT pt_skill_slot) {
      if (Util.isEmpty(this.skillslot.active)) {
         this.skillslot.active = new ArrayList();
      }

      this.skillslot.active.add(pt_skill_slot);
   }

   public void addActive(int index, int slot) {
      if (Util.isEmpty(this.skillslot.active)) {
         this.skillslot.active = new ArrayList();
      }

      PT_SKILL_SLOT pt_skill_slot = new PT_SKILL_SLOT();
      pt_skill_slot.index = index;
      pt_skill_slot.slot = slot;
      this.skillslot.active.add(pt_skill_slot);
   }

   public void addBuff(int index, int slot) {
      if (Util.isEmpty(this.skillslot.buff)) {
         this.skillslot.buff = new ArrayList();
      }

      PT_SKILL_SLOT pt_skill_slot = new PT_SKILL_SLOT();
      pt_skill_slot.index = index;
      pt_skill_slot.slot = slot;
      this.skillslot.buff.add(pt_skill_slot);
   }

   public void addBuff_pk(int index, int slot) {
      if (Util.isEmpty(this.skillslot_pk.buff)) {
         this.skillslot_pk.buff = new ArrayList();
      }

      PT_SKILL_SLOT pt_skill_slot = new PT_SKILL_SLOT();
      pt_skill_slot.index = index;
      pt_skill_slot.slot = slot;
      this.skillslot_pk.buff.add(pt_skill_slot);
   }

   public void addActive_pk(PT_SKILL_SLOT pt_skill_slot) {
      if (Util.isEmpty(this.skillslot_pk.active)) {
         this.skillslot_pk.active = new ArrayList();
      }

      this.skillslot_pk.active.add(pt_skill_slot);
   }

   public void addActive_pk(int index, int slot) {
      if (Util.isEmpty(this.skillslot_pk.active)) {
         this.skillslot_pk.active = new ArrayList();
      }

      PT_SKILL_SLOT pt_skill_slot = new PT_SKILL_SLOT();
      pt_skill_slot.index = index;
      pt_skill_slot.slot = slot;
      this.skillslot_pk.active.add(pt_skill_slot);
   }

   public void clearAll() {
      if (!Util.isEmpty(this.skillslot.active)) {
         this.skillslot.active.clear();
      }

      if (!Util.isEmpty(this.skillslot.buff)) {
         this.skillslot.buff.clear();
      }

      if (!Util.isEmpty(this.skillslot_pk.active)) {
         this.skillslot_pk.active.clear();
      }

      if (!Util.isEmpty(this.skillslot_pk.buff)) {
         this.skillslot_pk.buff.clear();
      }

   }

   public PT_ALL_SKILL_SLOT getSkillslot() {
      return this.skillslot;
   }

   public PT_ALL_SKILL_SLOT getSkillslot_pk() {
      return this.skillslot_pk;
   }

   public List<PT_SKILL_SLOT> getPtSkillSlotList_dungeon() {
      return this.ptSkillSlotList_dungeon;
   }

   public void setSkillslot(PT_ALL_SKILL_SLOT skillslot) {
      this.skillslot = skillslot;
   }

   public void setSkillslot_pk(PT_ALL_SKILL_SLOT skillslot_pk) {
      this.skillslot_pk = skillslot_pk;
   }

   public void setPtSkillSlotList_dungeon(List<PT_SKILL_SLOT> ptSkillSlotList_dungeon) {
      this.ptSkillSlotList_dungeon = ptSkillSlotList_dungeon;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof SkillslotBox)) {
         return false;
      } else {
         SkillslotBox other = (SkillslotBox)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$skillslot = this.getSkillslot();
            Object other$skillslot = other.getSkillslot();
            if (this$skillslot == null) {
               if (other$skillslot != null) {
                  return false;
               }
            } else if (!this$skillslot.equals(other$skillslot)) {
               return false;
            }

            Object this$skillslot_pk = this.getSkillslot_pk();
            Object other$skillslot_pk = other.getSkillslot_pk();
            if (this$skillslot_pk == null) {
               if (other$skillslot_pk != null) {
                  return false;
               }
            } else if (!this$skillslot_pk.equals(other$skillslot_pk)) {
               return false;
            }

            Object this$ptSkillSlotList_dungeon = this.getPtSkillSlotList_dungeon();
            Object other$ptSkillSlotList_dungeon = other.getPtSkillSlotList_dungeon();
            if (this$ptSkillSlotList_dungeon == null) {
               if (other$ptSkillSlotList_dungeon != null) {
                  return false;
               }
            } else if (!this$ptSkillSlotList_dungeon.equals(other$ptSkillSlotList_dungeon)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof SkillslotBox;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $skillslot = this.getSkillslot();
      result = result * 59 + ($skillslot == null ? 43 : $skillslot.hashCode());
      Object $skillslot_pk = this.getSkillslot_pk();
      result = result * 59 + ($skillslot_pk == null ? 43 : $skillslot_pk.hashCode());
      Object $ptSkillSlotList_dungeon = this.getPtSkillSlotList_dungeon();
      result = result * 59 + ($ptSkillSlotList_dungeon == null ? 43 : $ptSkillSlotList_dungeon.hashCode());
      return result;
   }

   public String toString() {
      return "SkillslotBox(skillslot=" + this.getSkillslot() + ", skillslot_pk=" + this.getSkillslot_pk() + ", ptSkillSlotList_dungeon=" + this.getPtSkillSlotList_dungeon() + ")";
   }
}
