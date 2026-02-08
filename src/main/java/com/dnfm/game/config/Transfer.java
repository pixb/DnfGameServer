package com.dnfm.game.config;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

@Table("p_transfer")
public class Transfer {
   @Id
   private short id;
   @Column
   private int fromMapId;
   @Column
   private String toMap;
   @Column
   private int toMapId;
   @Column
   private short fromX;
   @Column
   private short fromY;
   @Column
   private short toX;
   @Column
   private short toY;
   @Column
   private short toFangxiang;

   public short getId() {
      return this.id;
   }

   public void setId(short id) {
      this.id = id;
   }

   public int getToMapId() {
      return this.toMapId;
   }

   public void setToMapId(int toMapId) {
      this.toMapId = toMapId;
   }

   public int getFromMapId() {
      return this.fromMapId;
   }

   public void setFromMapId(int fromMapId) {
      this.fromMapId = fromMapId;
   }

   public String getToMap() {
      return this.toMap;
   }

   public void setToMap(String toMap) {
      this.toMap = toMap;
   }

   public short getFromX() {
      return this.fromX;
   }

   public void setFromX(short fromX) {
      this.fromX = fromX;
   }

   public short getFromY() {
      return this.fromY;
   }

   public void setFromY(short fromY) {
      this.fromY = fromY;
   }

   public short getToX() {
      return this.toX;
   }

   public void setToX(short toX) {
      this.toX = toX;
   }

   public short getToY() {
      return this.toY;
   }

   public void setToY(short toY) {
      this.toY = toY;
   }

   public short getToFangxiang() {
      return this.toFangxiang;
   }

   public void setToFangxiang(short toFangxiang) {
      this.toFangxiang = toFangxiang;
   }
}
