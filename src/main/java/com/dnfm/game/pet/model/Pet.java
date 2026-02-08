package com.dnfm.game.pet.model;

import com.dnfm.game.skill.model.SkillBox;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.nutz.dao.entity.annotation.Comment;

@JsonIgnoreProperties(
   ignoreUnknown = true
)
public class Pet {
   private int id;
   @Comment("宠物主人")
   private int hostId;
   @Comment("宠物主人名称")
   private String hostName;
   @Comment("宠物名称")
   private String name;
   @Comment("宠物昵称")
   private String nickname;
   @Comment("guid")
   private String gid;
   @Comment("宠物头像")
   private short icon;
   @Comment("宠物时装")
   private int special_icon;
   @Comment("宠物类型")
   private byte type;
   private byte position;
   @Comment("当前经验")
   private int exp;
   private int mapId;
   private SkillBox skillBox = new SkillBox();

   public void setExp(int exp) {
      this.exp = exp;
   }

   public int getId() {
      return this.id;
   }

   public int getHostId() {
      return this.hostId;
   }

   public String getHostName() {
      return this.hostName;
   }

   public String getName() {
      return this.name;
   }

   public String getNickname() {
      return this.nickname;
   }

   public String getGid() {
      return this.gid;
   }

   public short getIcon() {
      return this.icon;
   }

   public int getSpecial_icon() {
      return this.special_icon;
   }

   public byte getType() {
      return this.type;
   }

   public byte getPosition() {
      return this.position;
   }

   public int getExp() {
      return this.exp;
   }

   public int getMapId() {
      return this.mapId;
   }

   public SkillBox getSkillBox() {
      return this.skillBox;
   }

   public void setId(int id) {
      this.id = id;
   }

   public void setHostId(int hostId) {
      this.hostId = hostId;
   }

   public void setHostName(String hostName) {
      this.hostName = hostName;
   }

   public void setName(String name) {
      this.name = name;
   }

   public void setNickname(String nickname) {
      this.nickname = nickname;
   }

   public void setGid(String gid) {
      this.gid = gid;
   }

   public void setIcon(short icon) {
      this.icon = icon;
   }

   public void setSpecial_icon(int special_icon) {
      this.special_icon = special_icon;
   }

   public void setType(byte type) {
      this.type = type;
   }

   public void setPosition(byte position) {
      this.position = position;
   }

   public void setMapId(int mapId) {
      this.mapId = mapId;
   }

   public void setSkillBox(SkillBox skillBox) {
      this.skillBox = skillBox;
   }
}
