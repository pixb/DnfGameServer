package com.dnfm.game.config;

import com.dnfm.game.pet.model.Pet;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Comment;
import org.nutz.dao.entity.annotation.Default;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;
import org.nutz.lang.util.NutMap;

@Table("p_npc")
public class NPC implements Cloneable {
   @Id
   private int id;
   @Column
   @Comment("npc名称")
   private String name;
   @Column
   @Default("1")
   @Comment("npc类型")
   private byte type;
   @Column
   private short x;
   @Column
   private short y;
   @Column
   private short fangxiang;
   @Column
   @Comment("地图id")
   private int mapId;
   @Column
   @Comment("对话")
   private String content = "";
   @Column
   @Comment("对话2")
   private String content1;
   @Column
   @Comment("外观")
   private int icon;
   @Column
   @Comment("物品")
   @Default("[]")
   private List<String> list;
   private int wuqiId;
   private int taozhuangId;
   private int faguangId;
   private NutMap status;
   private long createTime = System.currentTimeMillis();
   private long endTime = -1L;
   private Lock lock = new ReentrantLock(true);
   private String tempName;
   private int level;
   private long roleUid;
   private NutMap roleInfo;
   private Pet pet;
   private final AtomicBoolean inFight = new AtomicBoolean();
   private String bossSetName;
   private String title;
   private byte polar;

   public NPC clone() throws CloneNotSupportedException {
      return (NPC)super.clone();
   }

   public long getRoleUid() {
      return this.roleUid;
   }

   public void setRoleUid(long roleUid) {
      this.roleUid = roleUid;
   }

   public int getLevel() {
      return this.level;
   }

   public void setLevel(int level) {
      this.level = level;
   }

   public int getWuqiId() {
      return this.wuqiId;
   }

   public void setWuqiId(int wuqiId) {
      this.wuqiId = wuqiId;
   }

   public String getTempName() {
      return this.tempName;
   }

   public void setTempName(String tempName) {
      this.tempName = tempName;
   }

   public long getCreateTime() {
      return this.createTime;
   }

   public void setCreateTime(long createTime) {
      this.createTime = createTime;
   }

   public List<String> getList() {
      return this.list;
   }

   public void setList(List<String> list) {
      this.list = list;
   }

   public int getId() {
      return this.id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public String getName() {
      return this.name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public short getX() {
      return this.x;
   }

   public void setX(short x) {
      this.x = x;
   }

   public short getY() {
      return this.y;
   }

   public void setY(short y) {
      this.y = y;
   }

   public short getFangxiang() {
      return this.fangxiang;
   }

   public void setFangxiang(short fangxiang) {
      this.fangxiang = fangxiang;
   }

   public int getMapId() {
      return this.mapId;
   }

   public void setMapId(int mapId) {
      this.mapId = mapId;
   }

   public String getContent() {
      return this.content;
   }

   public void setContent(String content) {
      this.content = content;
   }

   public int getIcon() {
      return this.icon;
   }

   public void setIcon(int icon) {
      this.icon = icon;
   }

   public String getContent1() {
      return this.content1;
   }

   public void setContent1(String content1) {
      this.content1 = content1;
   }

   public NutMap getRoleInfo() {
      return this.roleInfo;
   }

   public void setRoleInfo(NutMap roleInfo) {
      this.roleInfo = roleInfo;
   }

   public Pet getPet() {
      return this.pet;
   }

   public void setPet(Pet pet) {
      this.pet = pet;
   }

   public NutMap getStatus() {
      return this.status == null ? NutMap.NEW() : this.status;
   }

   public void setStatus(NutMap status) {
      this.status = status;
   }

   public Lock getLock() {
      return this.lock;
   }

   public void setLock(Lock lock) {
      this.lock = lock;
   }

   public int getTaozhuangId() {
      return this.taozhuangId;
   }

   public void setTaozhuangId(int taozhuangId) {
      this.taozhuangId = taozhuangId;
   }

   public int getFaguangId() {
      return this.faguangId;
   }

   public void setFaguangId(int faguangId) {
      this.faguangId = faguangId;
   }

   public byte getType() {
      return this.type;
   }

   public void setType(byte type) {
      this.type = type;
   }

   public long getEndTime() {
      return this.endTime;
   }

   public void setEndTime(long endTime) {
      this.endTime = endTime;
   }

   public boolean isInFight() {
      return this.inFight.get();
   }

   public AtomicBoolean getInFight() {
      return this.inFight;
   }

   public void setInFight(boolean inFight) {
      this.inFight.set(inFight);
   }

   public String getBossSetName() {
      return this.bossSetName;
   }

   public void setBossSetName(String bossSetName) {
      this.bossSetName = bossSetName;
   }

   public boolean isShow() {
      return this.endTime <= 0L || System.currentTimeMillis() <= this.endTime;
   }

   public String getTitle() {
      return this.title;
   }

   public void setTitle(String title) {
      this.title = title;
   }

   public byte getPolar() {
      return this.polar;
   }

   public void setPolar(byte polar) {
      this.polar = polar;
   }

   public boolean isMatchPolar(byte polar) {
      if (this.polar <= 0) {
         return true;
      } else {
         return this.polar == polar;
      }
   }
}
