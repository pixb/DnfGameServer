package com.dnfm.game.skill.model;

import com.dnfm.mina.protobuf.PT_SKILL;
import java.util.ArrayList;
import java.util.List;

public class SkillBox {
   private int sp = 40;
   private int tp = 0;
   private int addsp = 0;
   private int addtp = 0;
   private int sp_pk = 40;
   private int tp_pk = 0;
   private int addsp_pk = 0;
   private int addtp_pk = 0;
   private List<PT_SKILL> skilllist = new ArrayList();
   private List<PT_SKILL> skilllist_pk = new ArrayList();
   private List<PT_SKILL> skilllist_dungeon = new ArrayList();

   public void addSkill_dungeon(PT_SKILL pt_skill) {
      this.skilllist_dungeon.add(pt_skill);
   }

   public List<PT_SKILL> getSkills() {
      return this.skilllist;
   }

   public void addSkill(PT_SKILL pt_skill) {
      this.skilllist.add(pt_skill);
   }

   public void addSkill(int index) {
      PT_SKILL pt_skill = new PT_SKILL();
      pt_skill.index = index;
      pt_skill.level = 1;
      this.skilllist.add(pt_skill);
   }

   public void addSkill(int index, int level) {
      PT_SKILL pt_skill = new PT_SKILL();
      pt_skill.index = index;
      pt_skill.level = level;
      this.skilllist.add(pt_skill);
   }

   public void subSkill(int index) {
      for(PT_SKILL pt_skill : this.skilllist) {
         if (pt_skill.index == index) {
            this.skilllist.remove(pt_skill);
            break;
         }
      }

   }

   public void addSkill_pk(PT_SKILL pt_skill) {
      this.skilllist_pk.add(pt_skill);
   }

   public void addSkill_pk(int index) {
      PT_SKILL pt_skill = new PT_SKILL();
      pt_skill.index = index;
      pt_skill.level = 1;
      this.skilllist_pk.add(pt_skill);
   }

   public void addSkill_pk(int index, int level) {
      PT_SKILL pt_skill = new PT_SKILL();
      pt_skill.index = index;
      pt_skill.level = level;
      this.skilllist_pk.add(pt_skill);
   }

   public void learnSkill(PT_SKILL roleSkill) {
      this.skilllist.add(roleSkill);
   }

   public PT_SKILL querySkillBy(int skillId) {
      for(PT_SKILL pt_skill : this.skilllist) {
         if (pt_skill.index == skillId) {
            return pt_skill;
         }
      }

      return null;
   }

   public List<PT_SKILL> queryAllSkills() {
      return this.skilllist;
   }

   public int getSp() {
      return this.sp;
   }

   public int getTp() {
      return this.tp;
   }

   public int getAddsp() {
      return this.addsp;
   }

   public int getAddtp() {
      return this.addtp;
   }

   public int getSp_pk() {
      return this.sp_pk;
   }

   public int getTp_pk() {
      return this.tp_pk;
   }

   public int getAddsp_pk() {
      return this.addsp_pk;
   }

   public int getAddtp_pk() {
      return this.addtp_pk;
   }

   public List<PT_SKILL> getSkilllist() {
      return this.skilllist;
   }

   public List<PT_SKILL> getSkilllist_pk() {
      return this.skilllist_pk;
   }

   public List<PT_SKILL> getSkilllist_dungeon() {
      return this.skilllist_dungeon;
   }

   public void setSp(int sp) {
      this.sp = sp;
   }

   public void setTp(int tp) {
      this.tp = tp;
   }

   public void setAddsp(int addsp) {
      this.addsp = addsp;
   }

   public void setAddtp(int addtp) {
      this.addtp = addtp;
   }

   public void setSp_pk(int sp_pk) {
      this.sp_pk = sp_pk;
   }

   public void setTp_pk(int tp_pk) {
      this.tp_pk = tp_pk;
   }

   public void setAddsp_pk(int addsp_pk) {
      this.addsp_pk = addsp_pk;
   }

   public void setAddtp_pk(int addtp_pk) {
      this.addtp_pk = addtp_pk;
   }

   public void setSkilllist(List<PT_SKILL> skilllist) {
      this.skilllist = skilllist;
   }

   public void setSkilllist_pk(List<PT_SKILL> skilllist_pk) {
      this.skilllist_pk = skilllist_pk;
   }

   public void setSkilllist_dungeon(List<PT_SKILL> skilllist_dungeon) {
      this.skilllist_dungeon = skilllist_dungeon;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof SkillBox)) {
         return false;
      } else {
         SkillBox other = (SkillBox)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.getSp() != other.getSp()) {
            return false;
         } else if (this.getTp() != other.getTp()) {
            return false;
         } else if (this.getAddsp() != other.getAddsp()) {
            return false;
         } else if (this.getAddtp() != other.getAddtp()) {
            return false;
         } else if (this.getSp_pk() != other.getSp_pk()) {
            return false;
         } else if (this.getTp_pk() != other.getTp_pk()) {
            return false;
         } else if (this.getAddsp_pk() != other.getAddsp_pk()) {
            return false;
         } else if (this.getAddtp_pk() != other.getAddtp_pk()) {
            return false;
         } else {
            Object this$skilllist = this.getSkilllist();
            Object other$skilllist = other.getSkilllist();
            if (this$skilllist == null) {
               if (other$skilllist != null) {
                  return false;
               }
            } else if (!this$skilllist.equals(other$skilllist)) {
               return false;
            }

            Object this$skilllist_pk = this.getSkilllist_pk();
            Object other$skilllist_pk = other.getSkilllist_pk();
            if (this$skilllist_pk == null) {
               if (other$skilllist_pk != null) {
                  return false;
               }
            } else if (!this$skilllist_pk.equals(other$skilllist_pk)) {
               return false;
            }

            Object this$skilllist_dungeon = this.getSkilllist_dungeon();
            Object other$skilllist_dungeon = other.getSkilllist_dungeon();
            if (this$skilllist_dungeon == null) {
               if (other$skilllist_dungeon != null) {
                  return false;
               }
            } else if (!this$skilllist_dungeon.equals(other$skilllist_dungeon)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof SkillBox;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + this.getSp();
      result = result * 59 + this.getTp();
      result = result * 59 + this.getAddsp();
      result = result * 59 + this.getAddtp();
      result = result * 59 + this.getSp_pk();
      result = result * 59 + this.getTp_pk();
      result = result * 59 + this.getAddsp_pk();
      result = result * 59 + this.getAddtp_pk();
      Object $skilllist = this.getSkilllist();
      result = result * 59 + ($skilllist == null ? 43 : $skilllist.hashCode());
      Object $skilllist_pk = this.getSkilllist_pk();
      result = result * 59 + ($skilllist_pk == null ? 43 : $skilllist_pk.hashCode());
      Object $skilllist_dungeon = this.getSkilllist_dungeon();
      result = result * 59 + ($skilllist_dungeon == null ? 43 : $skilllist_dungeon.hashCode());
      return result;
   }

   public String toString() {
      return "SkillBox(sp=" + this.getSp() + ", tp=" + this.getTp() + ", addsp=" + this.getAddsp() + ", addtp=" + this.getAddtp() + ", sp_pk=" + this.getSp_pk() + ", tp_pk=" + this.getTp_pk() + ", addsp_pk=" + this.getAddsp_pk() + ", addtp_pk=" + this.getAddtp_pk() + ", skilllist=" + this.getSkilllist() + ", skilllist_pk=" + this.getSkilllist_pk() + ", skilllist_dungeon=" + this.getSkilllist_dungeon() + ")";
   }
}
