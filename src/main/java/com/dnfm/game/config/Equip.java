package com.dnfm.game.config;

import java.util.ArrayList;
import java.util.List;
import org.nutz.dao.entity.annotation.ColDefine;
import org.nutz.dao.entity.annotation.ColType;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Table;

@Table("p_equip")
public class Equip {
   @Column
   private int index;
   @Column
   private String name;
   @Column
   private int score;
   @Column
   private int minlevel;
   @Column
   private int equiptype;
   @Column
   private int grade;
   @Column
   private int weight;
   @Column
   private int rarity;
   @Column
   private String groupname;
   @Column
   private int subtype;
   @Column
   @ColDefine(
      type = ColType.MYSQL_JSON
   )
   private List<JointItem> disjointList = new ArrayList();

   public void setIndex(int index) {
      this.index = index;
   }

   public void setName(String name) {
      this.name = name;
   }

   public void setScore(int score) {
      this.score = score;
   }

   public void setMinlevel(int minlevel) {
      this.minlevel = minlevel;
   }

   public void setEquiptype(int equiptype) {
      this.equiptype = equiptype;
   }

   public void setGrade(int grade) {
      this.grade = grade;
   }

   public void setWeight(int weight) {
      this.weight = weight;
   }

   public void setRarity(int rarity) {
      this.rarity = rarity;
   }

   public void setGroupname(String groupname) {
      this.groupname = groupname;
   }

   public void setSubtype(int subtype) {
      this.subtype = subtype;
   }

   public void setDisjointList(List<JointItem> disjointList) {
      this.disjointList = disjointList;
   }

   public int getIndex() {
      return this.index;
   }

   public String getName() {
      return this.name;
   }

   public int getScore() {
      return this.score;
   }

   public int getMinlevel() {
      return this.minlevel;
   }

   public int getEquiptype() {
      return this.equiptype;
   }

   public int getGrade() {
      return this.grade;
   }

   public int getWeight() {
      return this.weight;
   }

   public int getRarity() {
      return this.rarity;
   }

   public String getGroupname() {
      return this.groupname;
   }

   public int getSubtype() {
      return this.subtype;
   }

   public List<JointItem> getDisjointList() {
      return this.disjointList;
   }
}
