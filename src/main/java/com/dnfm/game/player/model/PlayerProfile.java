package com.dnfm.game.player.model;

import com.dnfm.game.role.model.Role;
import java.io.Serializable;

public class PlayerProfile implements Serializable {
   private String openid;
   private int roleId;
   private long uid;
   private String name;
   private String distName;
   private int exp;
   private int level;
   private int job;
   private int fatigue;

   public PlayerProfile(String openid, int roleId, long charguid, String name, String distName, int exp, int level, int job, int fatigue) {
      this.openid = openid;
      this.roleId = roleId;
      this.uid = charguid;
      this.name = name;
      this.distName = distName;
      this.exp = exp;
      this.level = level;
      this.job = job;
      this.fatigue = fatigue;
   }

   public PlayerProfile(Role role) {
      this.openid = role.getOpenid();
      this.roleId = role.getRoleId();
      this.uid = role.getUid();
      this.name = role.getName();
      this.distName = role.getDistName();
      this.exp = role.getExp();
      this.level = role.getLevel();
      this.job = role.getJob();
      this.fatigue = role.getFatigue();
   }

   public void setOpenid(String openid) {
      this.openid = openid;
   }

   public void setRoleId(int roleId) {
      this.roleId = roleId;
   }

   public void setUid(long uid) {
      this.uid = uid;
   }

   public void setName(String name) {
      this.name = name;
   }

   public void setDistName(String distName) {
      this.distName = distName;
   }

   public void setExp(int exp) {
      this.exp = exp;
   }

   public void setLevel(int level) {
      this.level = level;
   }

   public void setJob(int job) {
      this.job = job;
   }

   public void setFatigue(int fatigue) {
      this.fatigue = fatigue;
   }

   public String getOpenid() {
      return this.openid;
   }

   public int getRoleId() {
      return this.roleId;
   }

   public long getUid() {
      return this.uid;
   }

   public String getName() {
      return this.name;
   }

   public String getDistName() {
      return this.distName;
   }

   public int getExp() {
      return this.exp;
   }

   public int getLevel() {
      return this.level;
   }

   public int getJob() {
      return this.job;
   }

   public int getFatigue() {
      return this.fatigue;
   }
}
