package com.dnfm.game.notice.model;

import org.nutz.dao.entity.annotation.ColDefine;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

@Table("t_notice")
public class Notice {
   @Id
   private int id;
   @Column
   @ColDefine(
      width = 1024
   )
   private String content;

   public int getId() {
      return this.id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public String getContent() {
      return this.content;
   }

   public void setContent(String content) {
      this.content = content;
   }
}
