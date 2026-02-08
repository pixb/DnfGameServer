package com.dnfm.game.config;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Table;

@Table("cnf_upgradescoreconfig")
public class UpgradeScoreConfig {
   @Column
   private int id;
   @Column
   private double val1;
   @Column
   private double val2;

   public void setId(int id) {
      this.id = id;
   }

   public void setVal1(double val1) {
      this.val1 = val1;
   }

   public void setVal2(double val2) {
      this.val2 = val2;
   }

   public int getId() {
      return this.id;
   }

   public double getVal1() {
      return this.val1;
   }

   public double getVal2() {
      return this.val2;
   }
}
