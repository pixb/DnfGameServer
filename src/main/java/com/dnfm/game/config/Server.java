package com.dnfm.game.config;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Comment;
import org.nutz.dao.entity.annotation.Default;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

@Table("p_server")
public class Server {
   @Id
   private int id;
   @Column
   @Comment("大区名称")
   private String name;
   @Column
   @Comment("大区端口号")
   private int port;
   @Column
   @Comment("大区ip")
   private String ip;
   @Column
   @Comment("线名称")
   private String sonName;
   @Column
   @Comment("线端口号")
   private int sonPort;
   @Column
   @Comment("线ip")
   private String sonIp;
   @Column
   @Comment("饱和度")
   private int saturation;
   @Column
   @Comment("是否开放")
   @Default("0")
   private boolean open;
   @Column
   private String payUrl;
   @Column
   @Default("1")
   private boolean payEnable;
   @Column
   private int gmPort;

   public int getId() {
      return this.id;
   }

   public String getName() {
      return this.name;
   }

   public int getPort() {
      return this.port;
   }

   public String getIp() {
      return this.ip;
   }

   public String getSonName() {
      return this.sonName;
   }

   public int getSonPort() {
      return this.sonPort;
   }

   public String getSonIp() {
      return this.sonIp;
   }

   public int getSaturation() {
      return this.saturation;
   }

   public boolean isOpen() {
      return this.open;
   }

   public String getPayUrl() {
      return this.payUrl;
   }

   public boolean isPayEnable() {
      return this.payEnable;
   }

   public int getGmPort() {
      return this.gmPort;
   }
}
