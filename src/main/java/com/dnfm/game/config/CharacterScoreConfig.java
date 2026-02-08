package com.dnfm.game.config;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Table;

@Table("cnf_characterscoreconfig")
public class CharacterScoreConfig {
   @Column
   private int jobid;
   @Column
   private String role;
   @Column
   private String groupname;
   @Column
   private double val1;
   @Column
   private double val2;
   @Column
   private double val3;
   @Column
   private double val4;
   @Column
   private double val5;
   @Column
   private double val6;

   public void setJobid(int jobid) {
      this.jobid = jobid;
   }

   public void setRole(String role) {
      this.role = role;
   }

   public void setGroupname(String groupname) {
      this.groupname = groupname;
   }

   public void setVal1(double val1) {
      this.val1 = val1;
   }

   public void setVal2(double val2) {
      this.val2 = val2;
   }

   public void setVal3(double val3) {
      this.val3 = val3;
   }

   public void setVal4(double val4) {
      this.val4 = val4;
   }

   public void setVal5(double val5) {
      this.val5 = val5;
   }

   public void setVal6(double val6) {
      this.val6 = val6;
   }

   public int getJobid() {
      return this.jobid;
   }

   public String getRole() {
      return this.role;
   }

   public String getGroupname() {
      return this.groupname;
   }

   public double getVal1() {
      return this.val1;
   }

   public double getVal2() {
      return this.val2;
   }

   public double getVal3() {
      return this.val3;
   }

   public double getVal4() {
      return this.val4;
   }

   public double getVal5() {
      return this.val5;
   }

   public double getVal6() {
      return this.val6;
   }
}
