package com.dnfm.game.config;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Comment;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

@Table("p_skill")
public class Skill {
   @Id
   private int id;
   @Column("Skill_ID")
   @Comment("技能id")
   private int skillId;
   @Column("Skill_Name")
   @Comment("技能名称")
   private String skillName;
   @Column("Skill_Job")
   @Comment("技能职业")
   private int skillJob;
   @Column("Skill_Target")
   @Comment("技能目标")
   private int skillTarget;
   @Column("Skill_Life")
   @Comment("")
   private int skillLife;
   @Column("Skill_The")
   @Comment("技能类型")
   private int skillType;
   @Column("Skill_Color")
   @Comment("技能颜色")
   private int skillColor;
   @Column("Skill_req_Level")
   @Comment("技能所需等级")
   private int reqLevel;
   @Column("Skill_Front")
   @Comment("先学习此技能到达等级")
   private int skillFront;
   @Column("Front_Skill")
   @Comment("先学习此技能")
   private int frontSkill;
   @Column("Max_Target")
   @Comment("技能最大目标数量")
   private int maxTarget;
   @Column("jeishu")
   @Comment("技能阶数")
   private byte jeishu;

   public byte getJeishu() {
      return this.jeishu;
   }

   public void setJeishu(byte jeishu) {
      this.jeishu = jeishu;
   }

   public int getSkillType() {
      return this.skillType;
   }

   public void setSkillType(int skillType) {
      this.skillType = skillType;
   }

   public int getId() {
      return this.id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public int getSkillId() {
      return this.skillId;
   }

   public void setSkillId(int skillId) {
      this.skillId = skillId;
   }

   public String getSkillName() {
      return this.skillName;
   }

   public void setSkillName(String skillName) {
      this.skillName = skillName;
   }

   public int getSkillJob() {
      return this.skillJob;
   }

   public void setSkillJob(int skillJob) {
      this.skillJob = skillJob;
   }

   public int getSkillTarget() {
      return this.skillTarget;
   }

   public void setSkillTarget(int skillTarget) {
      this.skillTarget = skillTarget;
   }

   public int getSkillLife() {
      return this.skillLife;
   }

   public void setSkillLife(int skillLife) {
      this.skillLife = skillLife;
   }

   public int getSkillColor() {
      return this.skillColor;
   }

   public void setSkillColor(int skillColor) {
      this.skillColor = skillColor;
   }

   public int getReqLevel() {
      return this.reqLevel;
   }

   public void setReqLevel(int reqLevel) {
      this.reqLevel = reqLevel;
   }

   public int getSkillFront() {
      return this.skillFront;
   }

   public void setSkillFront(int skillFront) {
      this.skillFront = skillFront;
   }

   public int getFrontSkill() {
      return this.frontSkill;
   }

   public void setFrontSkill(int frontSkill) {
      this.frontSkill = frontSkill;
   }

   public int getMaxTarget() {
      return this.maxTarget;
   }

   public void setMaxTarget(int maxTarget) {
      this.maxTarget = maxTarget;
   }

   public boolean isMagicSkill() {
      return this.skillType == 146 && this.skillId != 501;
   }
}
