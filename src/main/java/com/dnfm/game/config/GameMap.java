package com.dnfm.game.config;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

@Table("p_gamemap")
public class GameMap {
   @Name
   private String name;
   @Column
   private int id;
   @Column
   private boolean safe_zone;
   @Column
   private int telePortX;
   @Column
   private int telePortY;

   public String getName() {
      return this.name;
   }

   public int getId() {
      return this.id;
   }

   public boolean isSafe_zone() {
      return this.safe_zone;
   }

   public int getTelePortX() {
      return this.telePortX;
   }

   public int getTelePortY() {
      return this.telePortY;
   }

   public void setName(String name) {
      this.name = name;
   }

   public void setId(int id) {
      this.id = id;
   }

   public void setSafe_zone(boolean safe_zone) {
      this.safe_zone = safe_zone;
   }

   public void setTelePortX(int telePortX) {
      this.telePortX = telePortX;
   }

   public void setTelePortY(int telePortY) {
      this.telePortY = telePortY;
   }
}
