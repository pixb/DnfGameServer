package com.dnfm.game.config;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Comment;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

@Table("t_server")
public class LoginServer {
   @Id
   private int id;
   @Column
   @Comment("大区名称")
   private String name;
   @Column
   @Comment("大区ip")
   private String ip;
   @Column
   @Comment("大区http端口")
   private int httpPort;
   @Column
   @Comment("合区目标ID")
   private int merged;

   public String getUrl() {
      return "http://" + this.ip + ":" + this.httpPort;
   }

   public int getId() {
      return this.id;
   }

   public String getName() {
      return this.name;
   }

   public String getIp() {
      return this.ip;
   }

   public int getHttpPort() {
      return this.httpPort;
   }

   public int getMerged() {
      return this.merged;
   }
}
