package com.dnfm.game.friend.model;

import org.nutz.dao.entity.annotation.ColDefine;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

@Table("t_offline")
public class RoleOfflineData {
   @Name
   protected String type;
   @Column
   @ColDefine(
      customType = "longtext"
   )
   private String data;

   public RoleOfflineData() {
   }

   public RoleOfflineData(String type, String data) {
      this.type = type;
      this.data = data;
   }

   public String getType() {
      return this.type;
   }

   public String getData() {
      return this.data;
   }
}
