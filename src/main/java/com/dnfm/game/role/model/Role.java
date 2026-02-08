package com.dnfm.game.role.model;

import com.dnfm.common.db.BaseEntity;
import com.dnfm.common.db.Db4PlayerService;
import com.dnfm.common.model.Pos;
import com.dnfm.game.auction.model.AuctionBox;
import com.dnfm.game.bag.model.AchievementBox;
import com.dnfm.game.bag.model.ArtifactBox;
import com.dnfm.game.bag.model.AvatarBox;
import com.dnfm.game.bag.model.BookmarkBox;
import com.dnfm.game.bag.model.CardBox;
import com.dnfm.game.bag.model.CeraShopBuyInfo;
import com.dnfm.game.bag.model.CharFrameBox;
import com.dnfm.game.bag.model.CharStorageBox;
import com.dnfm.game.bag.model.ChatFrameBox;
import com.dnfm.game.bag.model.ClearDungeonBox;
import com.dnfm.game.bag.model.CollectionBox;
import com.dnfm.game.bag.model.ConsumableBox;
import com.dnfm.game.bag.model.CrackBox;
import com.dnfm.game.bag.model.CreatureBox;
import com.dnfm.game.bag.model.CreatureErrandBox;
import com.dnfm.game.bag.model.DamageBox;
import com.dnfm.game.bag.model.DungeonTicketsBox;
import com.dnfm.game.bag.model.EmblemBox;
import com.dnfm.game.bag.model.EquippedBox;
import com.dnfm.game.bag.model.FlagBox;
import com.dnfm.game.bag.model.LocalRewardBox;
import com.dnfm.game.bag.model.MaterialBox;
import com.dnfm.game.bag.model.MoneyBox;
import com.dnfm.game.bag.model.NoteMsgBox;
import com.dnfm.game.bag.model.QuestInfoBox;
import com.dnfm.game.bag.model.RePurStoItemBox;
import com.dnfm.game.bag.model.RoleShopInfoBox;
import com.dnfm.game.bag.model.SysBuffBox;
import com.dnfm.game.bag.model.TitleBox;
import com.dnfm.game.bag.model.TowerInfoBox;
import com.dnfm.game.bag.model.TutoBox;
import com.dnfm.game.config.ServerSimpleDataBox;
import com.dnfm.game.equip.model.EquipBox;
import com.dnfm.game.friend.model.FriendBox;
import com.dnfm.game.mail.MailBox;
import com.dnfm.game.party.model.DungeonParty;
import com.dnfm.game.skill.model.EssenceBox;
import com.dnfm.game.skill.model.SkillBox;
import com.dnfm.game.skill.model.SkillslotBox;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.nutz.dao.entity.annotation.ColDefine;
import org.nutz.dao.entity.annotation.ColType;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Comment;
import org.nutz.dao.entity.annotation.Default;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Index;
import org.nutz.dao.entity.annotation.Table;
import org.nutz.dao.entity.annotation.TableIndexes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Table("t_role")
@TableIndexes({@Index(
   fields = {"roleId"},
   unique = false
), @Index(
   fields = {"uid"},
   unique = false
), @Index(
   fields = {"openid"},
   unique = false
), @Index(
   fields = {"name"},
   unique = false
)})
@JsonIgnoreProperties(
   ignoreUnknown = true
)
public class Role extends BaseEntity<Long> {
   static Logger logger = LoggerFactory.getLogger(Role.class);
   public int lastNotiTransId = 0;
   @Id(
      auto = false
   )
   private int roleId;
   @Column
   private long uid;
   @Column
   private long lastlogout;
   @Column
   private int growtype;
   @Column
   private int secgrowtype;
   @Column
   @Default("0")
   private int job = 0;
   @Column
   @Default("1")
   private int level = 1;
   @Column
   private String name;
   @Column
   private int fatigue;
   @Column
   @Default("229")
   private int equipscore = 229;
   @Column
   private int characterframe;
   @Column
   private int money;
   @Column
   private int rescoin;
   @Column
   private int contributioncoin;
   @Column
   private int magiccrystal;
   @Column
   private int highmagiccrystal;
   @Column
   private int cerascore;
   @Column
   private int pkcoin;
   @Column
   private int friendpoint;
   @Column
   private int smallcoin;
   @Column
   private int avatarVisibleFlags;
   @Column
   private int deletionstatus;
   @Column
   private long deletiontime;
   @Column
   private long createtime;
   @Column
   private boolean changename;
   @Column
   private String openid;
   @Column
   @Default("200")
   private int exp = 200;
   @Column
   @Default("40")
   private int sp = 40;
   @Column
   private int tp;
   @Column
   private int addsp;
   @Column
   private int addtp;
   @Column
   private int day;
   @Column
   private int score;
   @Column
   @Default("100110")
   private int qindex = 100110;
   @Column
   private String distName;
   @Column
   private String servername;
   @Column
   private Date updateTime;
   @Column
   @Comment("封号时间")
   private long lockTime;
   @Column
   @Comment("坐标数据")
   private Pos pos = new Pos();
   @Column
   @Default("1")
   private int storageline = 1;
   @Column
   @Comment("禁言时间")
   @Default("0")
   private long wordTime;
   @Column
   @Comment("武器index")
   private int weaponIndex;
   @Column
   @Default("100")
   private int expratio = 100;
   @Column
   @Default("100")
   private int fatigueratio = 100;
   @Column
   private String adventurename = "";
   private long sessionId;
   private boolean unlock;
   private DungeonParty dungeonParty = new DungeonParty();
   @Column
   @ColDefine(
      type = ColType.MYSQL_JSON
   )
   private ServerSimpleDataBox serverSimpleDataBox = new ServerSimpleDataBox();
   @Column
   @ColDefine(
      type = ColType.MYSQL_JSON
   )
   private FriendBox friendBox = new FriendBox();
   @Column
   @ColDefine(
      type = ColType.MYSQL_JSON
   )
   private TitleBox titleBox = new TitleBox();
   @Column
   @ColDefine(
      type = ColType.MYSQL_JSON
   )
   @Comment("装扮背包")
   private AvatarBox avatarBox = new AvatarBox();
   @Column
   @ColDefine(
      type = ColType.MYSQL_JSON
   )
   @Comment("徽章背包")
   private EmblemBox emblemBox = new EmblemBox();
   @Column
   @ColDefine(
      type = ColType.MYSQL_JSON
   )
   private CardBox cardBox = new CardBox();
   @Column
   @ColDefine(
      type = ColType.MYSQL_JSON
   )
   @Comment("宠物背包")
   private CreatureBox creatureBox = new CreatureBox();
   @Column
   @ColDefine(
      type = ColType.MYSQL_JSON
   )
   @Comment("宠物装备")
   private ArtifactBox artifactBox = new ArtifactBox();
   @Column
   @ColDefine(
      type = ColType.MYSQL_JSON
   )
   @Comment("装备背包")
   private EquipBox equipBox = new EquipBox();
   @Column
   @ColDefine(
      type = ColType.MYSQL_JSON
   )
   @Comment("已装备")
   private EquippedBox equippedBox = new EquippedBox();
   @Column
   @ColDefine(
      type = ColType.MYSQL_JSON
   )
   @Comment("材料背包")
   private MaterialBox materialBox = new MaterialBox();
   @Column
   @ColDefine(
      type = ColType.MYSQL_JSON
   )
   @Comment("消耗品背包")
   private ConsumableBox consumableBox = new ConsumableBox();
   @Column
   @ColDefine(
      type = ColType.MYSQL_JSON
   )
   @Comment("角色购买记录")
   private RoleShopInfoBox roleShopInfoBox = new RoleShopInfoBox();
   @Comment("Flag背包")
   private FlagBox flagBox = new FlagBox();
   @Column
   @ColDefine(
      type = ColType.MYSQL_JSON
   )
   private EquipBox crackEquipBox = new EquipBox();
   @Column
   @ColDefine(
      type = ColType.MYSQL_JSON
   )
   private CrackBox crackBox = new CrackBox();
   @Column
   @ColDefine(
      type = ColType.MYSQL_JSON
   )
   @Comment("伤害字体")
   private DamageBox damageBox = new DamageBox();
   @Column
   @ColDefine(
      type = ColType.MYSQL_JSON
   )
   @Comment("聊天边框")
   private ChatFrameBox chatFrameBox = new ChatFrameBox();
   @Column
   @ColDefine(
      type = ColType.MYSQL_JSON
   )
   @Comment("人物边框")
   private CharFrameBox charFrameBox = new CharFrameBox();
   @Column
   @ColDefine(
      type = ColType.MYSQL_JSON
   )
   private AvatarBox sdAvatarBox = new AvatarBox();
   @Column
   @ColDefine(
      type = ColType.MYSQL_JSON
   )
   private BookmarkBox bookmarkBox = new BookmarkBox();
   @Column
   @ColDefine(
      type = ColType.MYSQL_JSON
   )
   private EquipBox scrollBox = new EquipBox();
   @Column
   @ColDefine(
      type = ColType.MYSQL_JSON
   )
   @Comment("金钱")
   private MoneyBox moneyBox = new MoneyBox();
   @Column
   @ColDefine(
      type = ColType.MYSQL_JSON
   )
   private CeraShopBuyInfo ceraShopBuyInfo = new CeraShopBuyInfo();
   @Column
   @ColDefine(
      type = ColType.MYSQL_JSON
   )
   private TutoBox tutoBox = new TutoBox();
   @Column
   @ColDefine(
      type = ColType.MYSQL_JSON
   )
   private SkillBox skillBox = new SkillBox();
   @Column
   @ColDefine(
      type = ColType.MYSQL_JSON
   )
   private SkillslotBox skillslotBox = new SkillslotBox();
   @Column
   @ColDefine(
      type = ColType.MYSQL_JSON
   )
   private DungeonTicketsBox dungeonTicketsBox = new DungeonTicketsBox();
   @Column
   @ColDefine(
      type = ColType.MYSQL_JSON
   )
   @Comment("魔力强化信息")
   private TonicBox tonicBox = new TonicBox();
   @Column
   @ColDefine(
      type = ColType.MYSQL_JSON
   )
   private MailBox mailBox = new MailBox();
   @Column
   @ColDefine(
      type = ColType.MYSQL_JSON
   )
   private MailBox sysMailBox = new MailBox();
   @Column
   @ColDefine(
      type = ColType.MYSQL_JSON
   )
   @Comment("角色金库")
   private CharStorageBox charStorageBox = new CharStorageBox();
   @Column
   @ColDefine(
      type = ColType.MYSQL_JSON
   )
   private RePurStoItemBox rePurStoItem = new RePurStoItemBox();
   @Column
   @ColDefine(
      type = ColType.MYSQL_JSON
   )
   private TowerInfoBox towerInfoBox = new TowerInfoBox();
   @Column
   @ColDefine(
      type = ColType.MYSQL_JSON
   )
   private CreatureErrandBox creatureErrandBox = new CreatureErrandBox();
   @Column
   @ColDefine(
      type = ColType.MYSQL_JSON
   )
   private LocalRewardBox localRewardBox = new LocalRewardBox();
   @Column
   @ColDefine(
      type = ColType.MYSQL_JSON
   )
   private QuestInfoBox questInfoBox = new QuestInfoBox();
   @Column
   @ColDefine(
      type = ColType.MYSQL_JSON
   )
   private SysBuffBox sysBuffBox = new SysBuffBox();
   @Column
   @ColDefine(
      type = ColType.MYSQL_JSON
   )
   private ClearDungeonBox clearDungeonBox = new ClearDungeonBox();
   @Column
   @ColDefine(
      type = ColType.MYSQL_JSON
   )
   private AchievementBox achievementBox = new AchievementBox();
   @Column
   @ColDefine(
      type = ColType.MYSQL_JSON
   )
   private CollectionBox collectionBox = new CollectionBox();
   @Column
   @ColDefine(
      type = ColType.MYSQL_JSON
   )
   private NoteMsgBox noteMsgBox = new NoteMsgBox();
   @Column
   @ColDefine(
      type = ColType.MYSQL_JSON
   )
   private EssenceBox essenceBox = new EssenceBox();
   @Column
   @ColDefine(
      type = ColType.MYSQL_JSON
   )
   private AuctionBox auctionBox = new AuctionBox();
   private int distributeKey;
   private transient Map<String, Object> tempCache;
   private Map<Integer, Integer> heartCounts = new HashMap(4);

   public void setPos(int x, int y, int area, int town) {
      this.pos.setX(x);
      this.pos.setY(y);
      this.pos.setArea(area);
      this.pos.setTown(town);
   }

   public void setExp(int exp) {
      if (exp > 999999999) {
         exp = 999999999;
      } else if (exp < 0) {
         exp = 0;
      }

      this.exp = exp;
   }

   @JsonIgnore
   public Long getId() {
      return this.uid;
   }

   public void doAfterInit() {
   }

   public void doBeforeSave() {
   }

   public void save() {
      Db4PlayerService.getInstance().add2Queue(this);
   }

   public int getDistributeKey() {
      return this.distributeKey;
   }

   public void setDistributeKey(int distributeKey) {
      this.distributeKey = distributeKey;
   }

   public Object pushTempCache(String key, Object value) {
      if (this.tempCache == null) {
         this.tempCache = new HashMap(4);
      }

      return this.tempCache.put(key, value);
   }

   public <T> T popTempCache(String key) {
      return (T)(this.tempCache == null ? null : this.tempCache.remove(key));
   }

   public <T> T getTempCache(String key, T defaultValue) {
      return (T)(this.tempCache == null ? defaultValue : this.tempCache.getOrDefault(key, defaultValue));
   }

   @JsonIgnore
   public int getFollowPetId() {
      return (Integer)this.getTempCache("followPet", 0);
   }

   public FriendBox getFriendBox() {
      return this.friendBox;
   }

   public void setFriendBox(FriendBox friendBox) {
      this.friendBox = friendBox;
   }

   public String toString() {
      return "Role{charguid=" + this.uid + ", name='" + this.name + '\'' + '}';
   }

   public int getLastNotiTransId() {
      return this.lastNotiTransId;
   }

   public int getRoleId() {
      return this.roleId;
   }

   public long getUid() {
      return this.uid;
   }

   public long getLastlogout() {
      return this.lastlogout;
   }

   public int getGrowtype() {
      return this.growtype;
   }

   public int getSecgrowtype() {
      return this.secgrowtype;
   }

   public int getJob() {
      return this.job;
   }

   public int getLevel() {
      return this.level;
   }

   public String getName() {
      return this.name;
   }

   public int getFatigue() {
      return this.fatigue;
   }

   public int getEquipscore() {
      return this.equipscore;
   }

   public int getCharacterframe() {
      return this.characterframe;
   }

   public int getMoney() {
      return this.money;
   }

   public int getRescoin() {
      return this.rescoin;
   }

   public int getContributioncoin() {
      return this.contributioncoin;
   }

   public int getMagiccrystal() {
      return this.magiccrystal;
   }

   public int getHighmagiccrystal() {
      return this.highmagiccrystal;
   }

   public int getCerascore() {
      return this.cerascore;
   }

   public int getPkcoin() {
      return this.pkcoin;
   }

   public int getFriendpoint() {
      return this.friendpoint;
   }

   public int getSmallcoin() {
      return this.smallcoin;
   }

   public int getAvatarVisibleFlags() {
      return this.avatarVisibleFlags;
   }

   public int getDeletionstatus() {
      return this.deletionstatus;
   }

   public long getDeletiontime() {
      return this.deletiontime;
   }

   public long getCreatetime() {
      return this.createtime;
   }

   public boolean isChangename() {
      return this.changename;
   }

   public String getOpenid() {
      return this.openid;
   }

   public int getExp() {
      return this.exp;
   }

   public int getSp() {
      return this.sp;
   }

   public int getTp() {
      return this.tp;
   }

   public int getAddsp() {
      return this.addsp;
   }

   public int getAddtp() {
      return this.addtp;
   }

   public int getDay() {
      return this.day;
   }

   public int getScore() {
      return this.score;
   }

   public int getQindex() {
      return this.qindex;
   }

   public String getDistName() {
      return this.distName;
   }

   public String getServername() {
      return this.servername;
   }

   public Date getUpdateTime() {
      return this.updateTime;
   }

   public long getLockTime() {
      return this.lockTime;
   }

   public Pos getPos() {
      return this.pos;
   }

   public int getStorageline() {
      return this.storageline;
   }

   public long getWordTime() {
      return this.wordTime;
   }

   public int getWeaponIndex() {
      return this.weaponIndex;
   }

   public int getExpratio() {
      return this.expratio;
   }

   public int getFatigueratio() {
      return this.fatigueratio;
   }

   public String getAdventurename() {
      return this.adventurename;
   }

   public long getSessionId() {
      return this.sessionId;
   }

   public boolean isUnlock() {
      return this.unlock;
   }

   public DungeonParty getDungeonParty() {
      return this.dungeonParty;
   }

   public ServerSimpleDataBox getServerSimpleDataBox() {
      return this.serverSimpleDataBox;
   }

   public TitleBox getTitleBox() {
      return this.titleBox;
   }

   public AvatarBox getAvatarBox() {
      return this.avatarBox;
   }

   public EmblemBox getEmblemBox() {
      return this.emblemBox;
   }

   public CardBox getCardBox() {
      return this.cardBox;
   }

   public CreatureBox getCreatureBox() {
      return this.creatureBox;
   }

   public ArtifactBox getArtifactBox() {
      return this.artifactBox;
   }

   public EquipBox getEquipBox() {
      return this.equipBox;
   }

   public EquippedBox getEquippedBox() {
      return this.equippedBox;
   }

   public MaterialBox getMaterialBox() {
      return this.materialBox;
   }

   public ConsumableBox getConsumableBox() {
      return this.consumableBox;
   }

   public RoleShopInfoBox getRoleShopInfoBox() {
      return this.roleShopInfoBox;
   }

   public FlagBox getFlagBox() {
      return this.flagBox;
   }

   public EquipBox getCrackEquipBox() {
      return this.crackEquipBox;
   }

   public CrackBox getCrackBox() {
      return this.crackBox;
   }

   public DamageBox getDamageBox() {
      return this.damageBox;
   }

   public ChatFrameBox getChatFrameBox() {
      return this.chatFrameBox;
   }

   public CharFrameBox getCharFrameBox() {
      return this.charFrameBox;
   }

   public AvatarBox getSdAvatarBox() {
      return this.sdAvatarBox;
   }

   public BookmarkBox getBookmarkBox() {
      return this.bookmarkBox;
   }

   public EquipBox getScrollBox() {
      return this.scrollBox;
   }

   public MoneyBox getMoneyBox() {
      return this.moneyBox;
   }

   public CeraShopBuyInfo getCeraShopBuyInfo() {
      return this.ceraShopBuyInfo;
   }

   public TutoBox getTutoBox() {
      return this.tutoBox;
   }

   public SkillBox getSkillBox() {
      return this.skillBox;
   }

   public SkillslotBox getSkillslotBox() {
      return this.skillslotBox;
   }

   public DungeonTicketsBox getDungeonTicketsBox() {
      return this.dungeonTicketsBox;
   }

   public TonicBox getTonicBox() {
      return this.tonicBox;
   }

   public MailBox getMailBox() {
      return this.mailBox;
   }

   public MailBox getSysMailBox() {
      return this.sysMailBox;
   }

   public CharStorageBox getCharStorageBox() {
      return this.charStorageBox;
   }

   public RePurStoItemBox getRePurStoItem() {
      return this.rePurStoItem;
   }

   public TowerInfoBox getTowerInfoBox() {
      return this.towerInfoBox;
   }

   public CreatureErrandBox getCreatureErrandBox() {
      return this.creatureErrandBox;
   }

   public LocalRewardBox getLocalRewardBox() {
      return this.localRewardBox;
   }

   public QuestInfoBox getQuestInfoBox() {
      return this.questInfoBox;
   }

   public SysBuffBox getSysBuffBox() {
      return this.sysBuffBox;
   }

   public ClearDungeonBox getClearDungeonBox() {
      return this.clearDungeonBox;
   }

   public AchievementBox getAchievementBox() {
      return this.achievementBox;
   }

   public CollectionBox getCollectionBox() {
      return this.collectionBox;
   }

   public NoteMsgBox getNoteMsgBox() {
      return this.noteMsgBox;
   }

   public EssenceBox getEssenceBox() {
      return this.essenceBox;
   }

   public AuctionBox getAuctionBox() {
      return this.auctionBox;
   }

   public Map<String, Object> getTempCache() {
      return this.tempCache;
   }

   public Map<Integer, Integer> getHeartCounts() {
      return this.heartCounts;
   }

   public void setLastNotiTransId(int lastNotiTransId) {
      this.lastNotiTransId = lastNotiTransId;
   }

   public void setRoleId(int roleId) {
      this.roleId = roleId;
   }

   public void setUid(long uid) {
      this.uid = uid;
   }

   public void setLastlogout(long lastlogout) {
      this.lastlogout = lastlogout;
   }

   public void setGrowtype(int growtype) {
      this.growtype = growtype;
   }

   public void setSecgrowtype(int secgrowtype) {
      this.secgrowtype = secgrowtype;
   }

   public void setJob(int job) {
      this.job = job;
   }

   public void setLevel(int level) {
      this.level = level;
   }

   public void setName(String name) {
      this.name = name;
   }

   public void setFatigue(int fatigue) {
      this.fatigue = fatigue;
   }

   public void setEquipscore(int equipscore) {
      this.equipscore = equipscore;
   }

   public void setCharacterframe(int characterframe) {
      this.characterframe = characterframe;
   }

   public void setMoney(int money) {
      this.money = money;
   }

   public void setRescoin(int rescoin) {
      this.rescoin = rescoin;
   }

   public void setContributioncoin(int contributioncoin) {
      this.contributioncoin = contributioncoin;
   }

   public void setMagiccrystal(int magiccrystal) {
      this.magiccrystal = magiccrystal;
   }

   public void setHighmagiccrystal(int highmagiccrystal) {
      this.highmagiccrystal = highmagiccrystal;
   }

   public void setCerascore(int cerascore) {
      this.cerascore = cerascore;
   }

   public void setPkcoin(int pkcoin) {
      this.pkcoin = pkcoin;
   }

   public void setFriendpoint(int friendpoint) {
      this.friendpoint = friendpoint;
   }

   public void setSmallcoin(int smallcoin) {
      this.smallcoin = smallcoin;
   }

   public void setAvatarVisibleFlags(int avatarVisibleFlags) {
      this.avatarVisibleFlags = avatarVisibleFlags;
   }

   public void setDeletionstatus(int deletionstatus) {
      this.deletionstatus = deletionstatus;
   }

   public void setDeletiontime(long deletiontime) {
      this.deletiontime = deletiontime;
   }

   public void setCreatetime(long createtime) {
      this.createtime = createtime;
   }

   public void setChangename(boolean changename) {
      this.changename = changename;
   }

   public void setOpenid(String openid) {
      this.openid = openid;
   }

   public void setSp(int sp) {
      this.sp = sp;
   }

   public void setTp(int tp) {
      this.tp = tp;
   }

   public void setAddsp(int addsp) {
      this.addsp = addsp;
   }

   public void setAddtp(int addtp) {
      this.addtp = addtp;
   }

   public void setDay(int day) {
      this.day = day;
   }

   public void setScore(int score) {
      this.score = score;
   }

   public void setQindex(int qindex) {
      this.qindex = qindex;
   }

   public void setDistName(String distName) {
      this.distName = distName;
   }

   public void setServername(String servername) {
      this.servername = servername;
   }

   public void setUpdateTime(Date updateTime) {
      this.updateTime = updateTime;
   }

   public void setLockTime(long lockTime) {
      this.lockTime = lockTime;
   }

   public void setPos(Pos pos) {
      this.pos = pos;
   }

   public void setStorageline(int storageline) {
      this.storageline = storageline;
   }

   public void setWordTime(long wordTime) {
      this.wordTime = wordTime;
   }

   public void setWeaponIndex(int weaponIndex) {
      this.weaponIndex = weaponIndex;
   }

   public void setExpratio(int expratio) {
      this.expratio = expratio;
   }

   public void setFatigueratio(int fatigueratio) {
      this.fatigueratio = fatigueratio;
   }

   public void setAdventurename(String adventurename) {
      this.adventurename = adventurename;
   }

   public void setSessionId(long sessionId) {
      this.sessionId = sessionId;
   }

   public void setUnlock(boolean unlock) {
      this.unlock = unlock;
   }

   public void setDungeonParty(DungeonParty dungeonParty) {
      this.dungeonParty = dungeonParty;
   }

   public void setServerSimpleDataBox(ServerSimpleDataBox serverSimpleDataBox) {
      this.serverSimpleDataBox = serverSimpleDataBox;
   }

   public void setTitleBox(TitleBox titleBox) {
      this.titleBox = titleBox;
   }

   public void setAvatarBox(AvatarBox avatarBox) {
      this.avatarBox = avatarBox;
   }

   public void setEmblemBox(EmblemBox emblemBox) {
      this.emblemBox = emblemBox;
   }

   public void setCardBox(CardBox cardBox) {
      this.cardBox = cardBox;
   }

   public void setCreatureBox(CreatureBox creatureBox) {
      this.creatureBox = creatureBox;
   }

   public void setArtifactBox(ArtifactBox artifactBox) {
      this.artifactBox = artifactBox;
   }

   public void setEquipBox(EquipBox equipBox) {
      this.equipBox = equipBox;
   }

   public void setEquippedBox(EquippedBox equippedBox) {
      this.equippedBox = equippedBox;
   }

   public void setMaterialBox(MaterialBox materialBox) {
      this.materialBox = materialBox;
   }

   public void setConsumableBox(ConsumableBox consumableBox) {
      this.consumableBox = consumableBox;
   }

   public void setRoleShopInfoBox(RoleShopInfoBox roleShopInfoBox) {
      this.roleShopInfoBox = roleShopInfoBox;
   }

   public void setFlagBox(FlagBox flagBox) {
      this.flagBox = flagBox;
   }

   public void setCrackEquipBox(EquipBox crackEquipBox) {
      this.crackEquipBox = crackEquipBox;
   }

   public void setCrackBox(CrackBox crackBox) {
      this.crackBox = crackBox;
   }

   public void setDamageBox(DamageBox damageBox) {
      this.damageBox = damageBox;
   }

   public void setChatFrameBox(ChatFrameBox chatFrameBox) {
      this.chatFrameBox = chatFrameBox;
   }

   public void setCharFrameBox(CharFrameBox charFrameBox) {
      this.charFrameBox = charFrameBox;
   }

   public void setSdAvatarBox(AvatarBox sdAvatarBox) {
      this.sdAvatarBox = sdAvatarBox;
   }

   public void setBookmarkBox(BookmarkBox bookmarkBox) {
      this.bookmarkBox = bookmarkBox;
   }

   public void setScrollBox(EquipBox scrollBox) {
      this.scrollBox = scrollBox;
   }

   public void setMoneyBox(MoneyBox moneyBox) {
      this.moneyBox = moneyBox;
   }

   public void setCeraShopBuyInfo(CeraShopBuyInfo ceraShopBuyInfo) {
      this.ceraShopBuyInfo = ceraShopBuyInfo;
   }

   public void setTutoBox(TutoBox tutoBox) {
      this.tutoBox = tutoBox;
   }

   public void setSkillBox(SkillBox skillBox) {
      this.skillBox = skillBox;
   }

   public void setSkillslotBox(SkillslotBox skillslotBox) {
      this.skillslotBox = skillslotBox;
   }

   public void setDungeonTicketsBox(DungeonTicketsBox dungeonTicketsBox) {
      this.dungeonTicketsBox = dungeonTicketsBox;
   }

   public void setTonicBox(TonicBox tonicBox) {
      this.tonicBox = tonicBox;
   }

   public void setMailBox(MailBox mailBox) {
      this.mailBox = mailBox;
   }

   public void setSysMailBox(MailBox sysMailBox) {
      this.sysMailBox = sysMailBox;
   }

   public void setCharStorageBox(CharStorageBox charStorageBox) {
      this.charStorageBox = charStorageBox;
   }

   public void setRePurStoItem(RePurStoItemBox rePurStoItem) {
      this.rePurStoItem = rePurStoItem;
   }

   public void setTowerInfoBox(TowerInfoBox towerInfoBox) {
      this.towerInfoBox = towerInfoBox;
   }

   public void setCreatureErrandBox(CreatureErrandBox creatureErrandBox) {
      this.creatureErrandBox = creatureErrandBox;
   }

   public void setLocalRewardBox(LocalRewardBox localRewardBox) {
      this.localRewardBox = localRewardBox;
   }

   public void setQuestInfoBox(QuestInfoBox questInfoBox) {
      this.questInfoBox = questInfoBox;
   }

   public void setSysBuffBox(SysBuffBox sysBuffBox) {
      this.sysBuffBox = sysBuffBox;
   }

   public void setClearDungeonBox(ClearDungeonBox clearDungeonBox) {
      this.clearDungeonBox = clearDungeonBox;
   }

   public void setAchievementBox(AchievementBox achievementBox) {
      this.achievementBox = achievementBox;
   }

   public void setCollectionBox(CollectionBox collectionBox) {
      this.collectionBox = collectionBox;
   }

   public void setNoteMsgBox(NoteMsgBox noteMsgBox) {
      this.noteMsgBox = noteMsgBox;
   }

   public void setEssenceBox(EssenceBox essenceBox) {
      this.essenceBox = essenceBox;
   }

   public void setAuctionBox(AuctionBox auctionBox) {
      this.auctionBox = auctionBox;
   }

   public void setTempCache(Map<String, Object> tempCache) {
      this.tempCache = tempCache;
   }

   public void setHeartCounts(Map<Integer, Integer> heartCounts) {
      this.heartCounts = heartCounts;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof Role)) {
         return false;
      } else {
         Role other = (Role)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.getLastNotiTransId() != other.getLastNotiTransId()) {
            return false;
         } else if (this.getRoleId() != other.getRoleId()) {
            return false;
         } else if (this.getUid() != other.getUid()) {
            return false;
         } else if (this.getLastlogout() != other.getLastlogout()) {
            return false;
         } else if (this.getGrowtype() != other.getGrowtype()) {
            return false;
         } else if (this.getSecgrowtype() != other.getSecgrowtype()) {
            return false;
         } else if (this.getJob() != other.getJob()) {
            return false;
         } else if (this.getLevel() != other.getLevel()) {
            return false;
         } else {
            Object this$name = this.getName();
            Object other$name = other.getName();
            if (this$name == null) {
               if (other$name != null) {
                  return false;
               }
            } else if (!this$name.equals(other$name)) {
               return false;
            }

            if (this.getFatigue() != other.getFatigue()) {
               return false;
            } else if (this.getEquipscore() != other.getEquipscore()) {
               return false;
            } else if (this.getCharacterframe() != other.getCharacterframe()) {
               return false;
            } else if (this.getMoney() != other.getMoney()) {
               return false;
            } else if (this.getRescoin() != other.getRescoin()) {
               return false;
            } else if (this.getContributioncoin() != other.getContributioncoin()) {
               return false;
            } else if (this.getMagiccrystal() != other.getMagiccrystal()) {
               return false;
            } else if (this.getHighmagiccrystal() != other.getHighmagiccrystal()) {
               return false;
            } else if (this.getCerascore() != other.getCerascore()) {
               return false;
            } else if (this.getPkcoin() != other.getPkcoin()) {
               return false;
            } else if (this.getFriendpoint() != other.getFriendpoint()) {
               return false;
            } else if (this.getSmallcoin() != other.getSmallcoin()) {
               return false;
            } else if (this.getAvatarVisibleFlags() != other.getAvatarVisibleFlags()) {
               return false;
            } else if (this.getDeletionstatus() != other.getDeletionstatus()) {
               return false;
            } else if (this.getDeletiontime() != other.getDeletiontime()) {
               return false;
            } else if (this.getCreatetime() != other.getCreatetime()) {
               return false;
            } else if (this.isChangename() != other.isChangename()) {
               return false;
            } else {
               Object this$openid = this.getOpenid();
               Object other$openid = other.getOpenid();
               if (this$openid == null) {
                  if (other$openid != null) {
                     return false;
                  }
               } else if (!this$openid.equals(other$openid)) {
                  return false;
               }

               if (this.getExp() != other.getExp()) {
                  return false;
               } else if (this.getSp() != other.getSp()) {
                  return false;
               } else if (this.getTp() != other.getTp()) {
                  return false;
               } else if (this.getAddsp() != other.getAddsp()) {
                  return false;
               } else if (this.getAddtp() != other.getAddtp()) {
                  return false;
               } else if (this.getDay() != other.getDay()) {
                  return false;
               } else if (this.getScore() != other.getScore()) {
                  return false;
               } else if (this.getQindex() != other.getQindex()) {
                  return false;
               } else {
                  Object this$distName = this.getDistName();
                  Object other$distName = other.getDistName();
                  if (this$distName == null) {
                     if (other$distName != null) {
                        return false;
                     }
                  } else if (!this$distName.equals(other$distName)) {
                     return false;
                  }

                  Object this$servername = this.getServername();
                  Object other$servername = other.getServername();
                  if (this$servername == null) {
                     if (other$servername != null) {
                        return false;
                     }
                  } else if (!this$servername.equals(other$servername)) {
                     return false;
                  }

                  Object this$updateTime = this.getUpdateTime();
                  Object other$updateTime = other.getUpdateTime();
                  if (this$updateTime == null) {
                     if (other$updateTime != null) {
                        return false;
                     }
                  } else if (!this$updateTime.equals(other$updateTime)) {
                     return false;
                  }

                  if (this.getLockTime() != other.getLockTime()) {
                     return false;
                  } else {
                     Object this$pos = this.getPos();
                     Object other$pos = other.getPos();
                     if (this$pos == null) {
                        if (other$pos != null) {
                           return false;
                        }
                     } else if (!this$pos.equals(other$pos)) {
                        return false;
                     }

                     if (this.getStorageline() != other.getStorageline()) {
                        return false;
                     } else if (this.getWordTime() != other.getWordTime()) {
                        return false;
                     } else if (this.getWeaponIndex() != other.getWeaponIndex()) {
                        return false;
                     } else if (this.getExpratio() != other.getExpratio()) {
                        return false;
                     } else if (this.getFatigueratio() != other.getFatigueratio()) {
                        return false;
                     } else {
                        Object this$adventurename = this.getAdventurename();
                        Object other$adventurename = other.getAdventurename();
                        if (this$adventurename == null) {
                           if (other$adventurename != null) {
                              return false;
                           }
                        } else if (!this$adventurename.equals(other$adventurename)) {
                           return false;
                        }

                        if (this.getSessionId() != other.getSessionId()) {
                           return false;
                        } else if (this.isUnlock() != other.isUnlock()) {
                           return false;
                        } else {
                           Object this$dungeonParty = this.getDungeonParty();
                           Object other$dungeonParty = other.getDungeonParty();
                           if (this$dungeonParty == null) {
                              if (other$dungeonParty != null) {
                                 return false;
                              }
                           } else if (!this$dungeonParty.equals(other$dungeonParty)) {
                              return false;
                           }

                           Object this$serverSimpleDataBox = this.getServerSimpleDataBox();
                           Object other$serverSimpleDataBox = other.getServerSimpleDataBox();
                           if (this$serverSimpleDataBox == null) {
                              if (other$serverSimpleDataBox != null) {
                                 return false;
                              }
                           } else if (!this$serverSimpleDataBox.equals(other$serverSimpleDataBox)) {
                              return false;
                           }

                           Object this$friendBox = this.getFriendBox();
                           Object other$friendBox = other.getFriendBox();
                           if (this$friendBox == null) {
                              if (other$friendBox != null) {
                                 return false;
                              }
                           } else if (!this$friendBox.equals(other$friendBox)) {
                              return false;
                           }

                           Object this$titleBox = this.getTitleBox();
                           Object other$titleBox = other.getTitleBox();
                           if (this$titleBox == null) {
                              if (other$titleBox != null) {
                                 return false;
                              }
                           } else if (!this$titleBox.equals(other$titleBox)) {
                              return false;
                           }

                           Object this$avatarBox = this.getAvatarBox();
                           Object other$avatarBox = other.getAvatarBox();
                           if (this$avatarBox == null) {
                              if (other$avatarBox != null) {
                                 return false;
                              }
                           } else if (!this$avatarBox.equals(other$avatarBox)) {
                              return false;
                           }

                           Object this$emblemBox = this.getEmblemBox();
                           Object other$emblemBox = other.getEmblemBox();
                           if (this$emblemBox == null) {
                              if (other$emblemBox != null) {
                                 return false;
                              }
                           } else if (!this$emblemBox.equals(other$emblemBox)) {
                              return false;
                           }

                           Object this$cardBox = this.getCardBox();
                           Object other$cardBox = other.getCardBox();
                           if (this$cardBox == null) {
                              if (other$cardBox != null) {
                                 return false;
                              }
                           } else if (!this$cardBox.equals(other$cardBox)) {
                              return false;
                           }

                           Object this$creatureBox = this.getCreatureBox();
                           Object other$creatureBox = other.getCreatureBox();
                           if (this$creatureBox == null) {
                              if (other$creatureBox != null) {
                                 return false;
                              }
                           } else if (!this$creatureBox.equals(other$creatureBox)) {
                              return false;
                           }

                           Object this$artifactBox = this.getArtifactBox();
                           Object other$artifactBox = other.getArtifactBox();
                           if (this$artifactBox == null) {
                              if (other$artifactBox != null) {
                                 return false;
                              }
                           } else if (!this$artifactBox.equals(other$artifactBox)) {
                              return false;
                           }

                           Object this$equipBox = this.getEquipBox();
                           Object other$equipBox = other.getEquipBox();
                           if (this$equipBox == null) {
                              if (other$equipBox != null) {
                                 return false;
                              }
                           } else if (!this$equipBox.equals(other$equipBox)) {
                              return false;
                           }

                           Object this$equippedBox = this.getEquippedBox();
                           Object other$equippedBox = other.getEquippedBox();
                           if (this$equippedBox == null) {
                              if (other$equippedBox != null) {
                                 return false;
                              }
                           } else if (!this$equippedBox.equals(other$equippedBox)) {
                              return false;
                           }

                           Object this$materialBox = this.getMaterialBox();
                           Object other$materialBox = other.getMaterialBox();
                           if (this$materialBox == null) {
                              if (other$materialBox != null) {
                                 return false;
                              }
                           } else if (!this$materialBox.equals(other$materialBox)) {
                              return false;
                           }

                           Object this$consumableBox = this.getConsumableBox();
                           Object other$consumableBox = other.getConsumableBox();
                           if (this$consumableBox == null) {
                              if (other$consumableBox != null) {
                                 return false;
                              }
                           } else if (!this$consumableBox.equals(other$consumableBox)) {
                              return false;
                           }

                           Object this$roleShopInfoBox = this.getRoleShopInfoBox();
                           Object other$roleShopInfoBox = other.getRoleShopInfoBox();
                           if (this$roleShopInfoBox == null) {
                              if (other$roleShopInfoBox != null) {
                                 return false;
                              }
                           } else if (!this$roleShopInfoBox.equals(other$roleShopInfoBox)) {
                              return false;
                           }

                           Object this$flagBox = this.getFlagBox();
                           Object other$flagBox = other.getFlagBox();
                           if (this$flagBox == null) {
                              if (other$flagBox != null) {
                                 return false;
                              }
                           } else if (!this$flagBox.equals(other$flagBox)) {
                              return false;
                           }

                           Object this$crackEquipBox = this.getCrackEquipBox();
                           Object other$crackEquipBox = other.getCrackEquipBox();
                           if (this$crackEquipBox == null) {
                              if (other$crackEquipBox != null) {
                                 return false;
                              }
                           } else if (!this$crackEquipBox.equals(other$crackEquipBox)) {
                              return false;
                           }

                           Object this$crackBox = this.getCrackBox();
                           Object other$crackBox = other.getCrackBox();
                           if (this$crackBox == null) {
                              if (other$crackBox != null) {
                                 return false;
                              }
                           } else if (!this$crackBox.equals(other$crackBox)) {
                              return false;
                           }

                           Object this$damageBox = this.getDamageBox();
                           Object other$damageBox = other.getDamageBox();
                           if (this$damageBox == null) {
                              if (other$damageBox != null) {
                                 return false;
                              }
                           } else if (!this$damageBox.equals(other$damageBox)) {
                              return false;
                           }

                           Object this$chatFrameBox = this.getChatFrameBox();
                           Object other$chatFrameBox = other.getChatFrameBox();
                           if (this$chatFrameBox == null) {
                              if (other$chatFrameBox != null) {
                                 return false;
                              }
                           } else if (!this$chatFrameBox.equals(other$chatFrameBox)) {
                              return false;
                           }

                           Object this$charFrameBox = this.getCharFrameBox();
                           Object other$charFrameBox = other.getCharFrameBox();
                           if (this$charFrameBox == null) {
                              if (other$charFrameBox != null) {
                                 return false;
                              }
                           } else if (!this$charFrameBox.equals(other$charFrameBox)) {
                              return false;
                           }

                           Object this$sdAvatarBox = this.getSdAvatarBox();
                           Object other$sdAvatarBox = other.getSdAvatarBox();
                           if (this$sdAvatarBox == null) {
                              if (other$sdAvatarBox != null) {
                                 return false;
                              }
                           } else if (!this$sdAvatarBox.equals(other$sdAvatarBox)) {
                              return false;
                           }

                           Object this$bookmarkBox = this.getBookmarkBox();
                           Object other$bookmarkBox = other.getBookmarkBox();
                           if (this$bookmarkBox == null) {
                              if (other$bookmarkBox != null) {
                                 return false;
                              }
                           } else if (!this$bookmarkBox.equals(other$bookmarkBox)) {
                              return false;
                           }

                           Object this$scrollBox = this.getScrollBox();
                           Object other$scrollBox = other.getScrollBox();
                           if (this$scrollBox == null) {
                              if (other$scrollBox != null) {
                                 return false;
                              }
                           } else if (!this$scrollBox.equals(other$scrollBox)) {
                              return false;
                           }

                           Object this$moneyBox = this.getMoneyBox();
                           Object other$moneyBox = other.getMoneyBox();
                           if (this$moneyBox == null) {
                              if (other$moneyBox != null) {
                                 return false;
                              }
                           } else if (!this$moneyBox.equals(other$moneyBox)) {
                              return false;
                           }

                           Object this$ceraShopBuyInfo = this.getCeraShopBuyInfo();
                           Object other$ceraShopBuyInfo = other.getCeraShopBuyInfo();
                           if (this$ceraShopBuyInfo == null) {
                              if (other$ceraShopBuyInfo != null) {
                                 return false;
                              }
                           } else if (!this$ceraShopBuyInfo.equals(other$ceraShopBuyInfo)) {
                              return false;
                           }

                           Object this$tutoBox = this.getTutoBox();
                           Object other$tutoBox = other.getTutoBox();
                           if (this$tutoBox == null) {
                              if (other$tutoBox != null) {
                                 return false;
                              }
                           } else if (!this$tutoBox.equals(other$tutoBox)) {
                              return false;
                           }

                           Object this$skillBox = this.getSkillBox();
                           Object other$skillBox = other.getSkillBox();
                           if (this$skillBox == null) {
                              if (other$skillBox != null) {
                                 return false;
                              }
                           } else if (!this$skillBox.equals(other$skillBox)) {
                              return false;
                           }

                           Object this$skillslotBox = this.getSkillslotBox();
                           Object other$skillslotBox = other.getSkillslotBox();
                           if (this$skillslotBox == null) {
                              if (other$skillslotBox != null) {
                                 return false;
                              }
                           } else if (!this$skillslotBox.equals(other$skillslotBox)) {
                              return false;
                           }

                           Object this$dungeonTicketsBox = this.getDungeonTicketsBox();
                           Object other$dungeonTicketsBox = other.getDungeonTicketsBox();
                           if (this$dungeonTicketsBox == null) {
                              if (other$dungeonTicketsBox != null) {
                                 return false;
                              }
                           } else if (!this$dungeonTicketsBox.equals(other$dungeonTicketsBox)) {
                              return false;
                           }

                           Object this$tonicBox = this.getTonicBox();
                           Object other$tonicBox = other.getTonicBox();
                           if (this$tonicBox == null) {
                              if (other$tonicBox != null) {
                                 return false;
                              }
                           } else if (!this$tonicBox.equals(other$tonicBox)) {
                              return false;
                           }

                           Object this$mailBox = this.getMailBox();
                           Object other$mailBox = other.getMailBox();
                           if (this$mailBox == null) {
                              if (other$mailBox != null) {
                                 return false;
                              }
                           } else if (!this$mailBox.equals(other$mailBox)) {
                              return false;
                           }

                           Object this$sysMailBox = this.getSysMailBox();
                           Object other$sysMailBox = other.getSysMailBox();
                           if (this$sysMailBox == null) {
                              if (other$sysMailBox != null) {
                                 return false;
                              }
                           } else if (!this$sysMailBox.equals(other$sysMailBox)) {
                              return false;
                           }

                           Object this$charStorageBox = this.getCharStorageBox();
                           Object other$charStorageBox = other.getCharStorageBox();
                           if (this$charStorageBox == null) {
                              if (other$charStorageBox != null) {
                                 return false;
                              }
                           } else if (!this$charStorageBox.equals(other$charStorageBox)) {
                              return false;
                           }

                           Object this$rePurStoItem = this.getRePurStoItem();
                           Object other$rePurStoItem = other.getRePurStoItem();
                           if (this$rePurStoItem == null) {
                              if (other$rePurStoItem != null) {
                                 return false;
                              }
                           } else if (!this$rePurStoItem.equals(other$rePurStoItem)) {
                              return false;
                           }

                           Object this$towerInfoBox = this.getTowerInfoBox();
                           Object other$towerInfoBox = other.getTowerInfoBox();
                           if (this$towerInfoBox == null) {
                              if (other$towerInfoBox != null) {
                                 return false;
                              }
                           } else if (!this$towerInfoBox.equals(other$towerInfoBox)) {
                              return false;
                           }

                           Object this$creatureErrandBox = this.getCreatureErrandBox();
                           Object other$creatureErrandBox = other.getCreatureErrandBox();
                           if (this$creatureErrandBox == null) {
                              if (other$creatureErrandBox != null) {
                                 return false;
                              }
                           } else if (!this$creatureErrandBox.equals(other$creatureErrandBox)) {
                              return false;
                           }

                           Object this$localRewardBox = this.getLocalRewardBox();
                           Object other$localRewardBox = other.getLocalRewardBox();
                           if (this$localRewardBox == null) {
                              if (other$localRewardBox != null) {
                                 return false;
                              }
                           } else if (!this$localRewardBox.equals(other$localRewardBox)) {
                              return false;
                           }

                           Object this$questInfoBox = this.getQuestInfoBox();
                           Object other$questInfoBox = other.getQuestInfoBox();
                           if (this$questInfoBox == null) {
                              if (other$questInfoBox != null) {
                                 return false;
                              }
                           } else if (!this$questInfoBox.equals(other$questInfoBox)) {
                              return false;
                           }

                           Object this$sysBuffBox = this.getSysBuffBox();
                           Object other$sysBuffBox = other.getSysBuffBox();
                           if (this$sysBuffBox == null) {
                              if (other$sysBuffBox != null) {
                                 return false;
                              }
                           } else if (!this$sysBuffBox.equals(other$sysBuffBox)) {
                              return false;
                           }

                           Object this$clearDungeonBox = this.getClearDungeonBox();
                           Object other$clearDungeonBox = other.getClearDungeonBox();
                           if (this$clearDungeonBox == null) {
                              if (other$clearDungeonBox != null) {
                                 return false;
                              }
                           } else if (!this$clearDungeonBox.equals(other$clearDungeonBox)) {
                              return false;
                           }

                           Object this$achievementBox = this.getAchievementBox();
                           Object other$achievementBox = other.getAchievementBox();
                           if (this$achievementBox == null) {
                              if (other$achievementBox != null) {
                                 return false;
                              }
                           } else if (!this$achievementBox.equals(other$achievementBox)) {
                              return false;
                           }

                           Object this$collectionBox = this.getCollectionBox();
                           Object other$collectionBox = other.getCollectionBox();
                           if (this$collectionBox == null) {
                              if (other$collectionBox != null) {
                                 return false;
                              }
                           } else if (!this$collectionBox.equals(other$collectionBox)) {
                              return false;
                           }

                           Object this$noteMsgBox = this.getNoteMsgBox();
                           Object other$noteMsgBox = other.getNoteMsgBox();
                           if (this$noteMsgBox == null) {
                              if (other$noteMsgBox != null) {
                                 return false;
                              }
                           } else if (!this$noteMsgBox.equals(other$noteMsgBox)) {
                              return false;
                           }

                           Object this$essenceBox = this.getEssenceBox();
                           Object other$essenceBox = other.getEssenceBox();
                           if (this$essenceBox == null) {
                              if (other$essenceBox != null) {
                                 return false;
                              }
                           } else if (!this$essenceBox.equals(other$essenceBox)) {
                              return false;
                           }

                           Object this$auctionBox = this.getAuctionBox();
                           Object other$auctionBox = other.getAuctionBox();
                           if (this$auctionBox == null) {
                              if (other$auctionBox != null) {
                                 return false;
                              }
                           } else if (!this$auctionBox.equals(other$auctionBox)) {
                              return false;
                           }

                           if (this.getDistributeKey() != other.getDistributeKey()) {
                              return false;
                           } else {
                              Object this$heartCounts = this.getHeartCounts();
                              Object other$heartCounts = other.getHeartCounts();
                              if (this$heartCounts == null) {
                                 if (other$heartCounts != null) {
                                    return false;
                                 }
                              } else if (!this$heartCounts.equals(other$heartCounts)) {
                                 return false;
                              }

                              return true;
                           }
                        }
                     }
                  }
               }
            }
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof Role;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + this.getLastNotiTransId();
      result = result * 59 + this.getRoleId();
      long $uid = this.getUid();
      result = result * 59 + (int)($uid >>> 32 ^ $uid);
      long $lastlogout = this.getLastlogout();
      result = result * 59 + (int)($lastlogout >>> 32 ^ $lastlogout);
      result = result * 59 + this.getGrowtype();
      result = result * 59 + this.getSecgrowtype();
      result = result * 59 + this.getJob();
      result = result * 59 + this.getLevel();
      Object $name = this.getName();
      result = result * 59 + ($name == null ? 43 : $name.hashCode());
      result = result * 59 + this.getFatigue();
      result = result * 59 + this.getEquipscore();
      result = result * 59 + this.getCharacterframe();
      result = result * 59 + this.getMoney();
      result = result * 59 + this.getRescoin();
      result = result * 59 + this.getContributioncoin();
      result = result * 59 + this.getMagiccrystal();
      result = result * 59 + this.getHighmagiccrystal();
      result = result * 59 + this.getCerascore();
      result = result * 59 + this.getPkcoin();
      result = result * 59 + this.getFriendpoint();
      result = result * 59 + this.getSmallcoin();
      result = result * 59 + this.getAvatarVisibleFlags();
      result = result * 59 + this.getDeletionstatus();
      long $deletiontime = this.getDeletiontime();
      result = result * 59 + (int)($deletiontime >>> 32 ^ $deletiontime);
      long $createtime = this.getCreatetime();
      result = result * 59 + (int)($createtime >>> 32 ^ $createtime);
      result = result * 59 + (this.isChangename() ? 79 : 97);
      Object $openid = this.getOpenid();
      result = result * 59 + ($openid == null ? 43 : $openid.hashCode());
      result = result * 59 + this.getExp();
      result = result * 59 + this.getSp();
      result = result * 59 + this.getTp();
      result = result * 59 + this.getAddsp();
      result = result * 59 + this.getAddtp();
      result = result * 59 + this.getDay();
      result = result * 59 + this.getScore();
      result = result * 59 + this.getQindex();
      Object $distName = this.getDistName();
      result = result * 59 + ($distName == null ? 43 : $distName.hashCode());
      Object $servername = this.getServername();
      result = result * 59 + ($servername == null ? 43 : $servername.hashCode());
      Object $updateTime = this.getUpdateTime();
      result = result * 59 + ($updateTime == null ? 43 : $updateTime.hashCode());
      long $lockTime = this.getLockTime();
      result = result * 59 + (int)($lockTime >>> 32 ^ $lockTime);
      Object $pos = this.getPos();
      result = result * 59 + ($pos == null ? 43 : $pos.hashCode());
      result = result * 59 + this.getStorageline();
      long $wordTime = this.getWordTime();
      result = result * 59 + (int)($wordTime >>> 32 ^ $wordTime);
      result = result * 59 + this.getWeaponIndex();
      result = result * 59 + this.getExpratio();
      result = result * 59 + this.getFatigueratio();
      Object $adventurename = this.getAdventurename();
      result = result * 59 + ($adventurename == null ? 43 : $adventurename.hashCode());
      long $sessionId = this.getSessionId();
      result = result * 59 + (int)($sessionId >>> 32 ^ $sessionId);
      result = result * 59 + (this.isUnlock() ? 79 : 97);
      Object $dungeonParty = this.getDungeonParty();
      result = result * 59 + ($dungeonParty == null ? 43 : $dungeonParty.hashCode());
      Object $serverSimpleDataBox = this.getServerSimpleDataBox();
      result = result * 59 + ($serverSimpleDataBox == null ? 43 : $serverSimpleDataBox.hashCode());
      Object $friendBox = this.getFriendBox();
      result = result * 59 + ($friendBox == null ? 43 : $friendBox.hashCode());
      Object $titleBox = this.getTitleBox();
      result = result * 59 + ($titleBox == null ? 43 : $titleBox.hashCode());
      Object $avatarBox = this.getAvatarBox();
      result = result * 59 + ($avatarBox == null ? 43 : $avatarBox.hashCode());
      Object $emblemBox = this.getEmblemBox();
      result = result * 59 + ($emblemBox == null ? 43 : $emblemBox.hashCode());
      Object $cardBox = this.getCardBox();
      result = result * 59 + ($cardBox == null ? 43 : $cardBox.hashCode());
      Object $creatureBox = this.getCreatureBox();
      result = result * 59 + ($creatureBox == null ? 43 : $creatureBox.hashCode());
      Object $artifactBox = this.getArtifactBox();
      result = result * 59 + ($artifactBox == null ? 43 : $artifactBox.hashCode());
      Object $equipBox = this.getEquipBox();
      result = result * 59 + ($equipBox == null ? 43 : $equipBox.hashCode());
      Object $equippedBox = this.getEquippedBox();
      result = result * 59 + ($equippedBox == null ? 43 : $equippedBox.hashCode());
      Object $materialBox = this.getMaterialBox();
      result = result * 59 + ($materialBox == null ? 43 : $materialBox.hashCode());
      Object $consumableBox = this.getConsumableBox();
      result = result * 59 + ($consumableBox == null ? 43 : $consumableBox.hashCode());
      Object $roleShopInfoBox = this.getRoleShopInfoBox();
      result = result * 59 + ($roleShopInfoBox == null ? 43 : $roleShopInfoBox.hashCode());
      Object $flagBox = this.getFlagBox();
      result = result * 59 + ($flagBox == null ? 43 : $flagBox.hashCode());
      Object $crackEquipBox = this.getCrackEquipBox();
      result = result * 59 + ($crackEquipBox == null ? 43 : $crackEquipBox.hashCode());
      Object $crackBox = this.getCrackBox();
      result = result * 59 + ($crackBox == null ? 43 : $crackBox.hashCode());
      Object $damageBox = this.getDamageBox();
      result = result * 59 + ($damageBox == null ? 43 : $damageBox.hashCode());
      Object $chatFrameBox = this.getChatFrameBox();
      result = result * 59 + ($chatFrameBox == null ? 43 : $chatFrameBox.hashCode());
      Object $charFrameBox = this.getCharFrameBox();
      result = result * 59 + ($charFrameBox == null ? 43 : $charFrameBox.hashCode());
      Object $sdAvatarBox = this.getSdAvatarBox();
      result = result * 59 + ($sdAvatarBox == null ? 43 : $sdAvatarBox.hashCode());
      Object $bookmarkBox = this.getBookmarkBox();
      result = result * 59 + ($bookmarkBox == null ? 43 : $bookmarkBox.hashCode());
      Object $scrollBox = this.getScrollBox();
      result = result * 59 + ($scrollBox == null ? 43 : $scrollBox.hashCode());
      Object $moneyBox = this.getMoneyBox();
      result = result * 59 + ($moneyBox == null ? 43 : $moneyBox.hashCode());
      Object $ceraShopBuyInfo = this.getCeraShopBuyInfo();
      result = result * 59 + ($ceraShopBuyInfo == null ? 43 : $ceraShopBuyInfo.hashCode());
      Object $tutoBox = this.getTutoBox();
      result = result * 59 + ($tutoBox == null ? 43 : $tutoBox.hashCode());
      Object $skillBox = this.getSkillBox();
      result = result * 59 + ($skillBox == null ? 43 : $skillBox.hashCode());
      Object $skillslotBox = this.getSkillslotBox();
      result = result * 59 + ($skillslotBox == null ? 43 : $skillslotBox.hashCode());
      Object $dungeonTicketsBox = this.getDungeonTicketsBox();
      result = result * 59 + ($dungeonTicketsBox == null ? 43 : $dungeonTicketsBox.hashCode());
      Object $tonicBox = this.getTonicBox();
      result = result * 59 + ($tonicBox == null ? 43 : $tonicBox.hashCode());
      Object $mailBox = this.getMailBox();
      result = result * 59 + ($mailBox == null ? 43 : $mailBox.hashCode());
      Object $sysMailBox = this.getSysMailBox();
      result = result * 59 + ($sysMailBox == null ? 43 : $sysMailBox.hashCode());
      Object $charStorageBox = this.getCharStorageBox();
      result = result * 59 + ($charStorageBox == null ? 43 : $charStorageBox.hashCode());
      Object $rePurStoItem = this.getRePurStoItem();
      result = result * 59 + ($rePurStoItem == null ? 43 : $rePurStoItem.hashCode());
      Object $towerInfoBox = this.getTowerInfoBox();
      result = result * 59 + ($towerInfoBox == null ? 43 : $towerInfoBox.hashCode());
      Object $creatureErrandBox = this.getCreatureErrandBox();
      result = result * 59 + ($creatureErrandBox == null ? 43 : $creatureErrandBox.hashCode());
      Object $localRewardBox = this.getLocalRewardBox();
      result = result * 59 + ($localRewardBox == null ? 43 : $localRewardBox.hashCode());
      Object $questInfoBox = this.getQuestInfoBox();
      result = result * 59 + ($questInfoBox == null ? 43 : $questInfoBox.hashCode());
      Object $sysBuffBox = this.getSysBuffBox();
      result = result * 59 + ($sysBuffBox == null ? 43 : $sysBuffBox.hashCode());
      Object $clearDungeonBox = this.getClearDungeonBox();
      result = result * 59 + ($clearDungeonBox == null ? 43 : $clearDungeonBox.hashCode());
      Object $achievementBox = this.getAchievementBox();
      result = result * 59 + ($achievementBox == null ? 43 : $achievementBox.hashCode());
      Object $collectionBox = this.getCollectionBox();
      result = result * 59 + ($collectionBox == null ? 43 : $collectionBox.hashCode());
      Object $noteMsgBox = this.getNoteMsgBox();
      result = result * 59 + ($noteMsgBox == null ? 43 : $noteMsgBox.hashCode());
      Object $essenceBox = this.getEssenceBox();
      result = result * 59 + ($essenceBox == null ? 43 : $essenceBox.hashCode());
      Object $auctionBox = this.getAuctionBox();
      result = result * 59 + ($auctionBox == null ? 43 : $auctionBox.hashCode());
      result = result * 59 + this.getDistributeKey();
      Object $heartCounts = this.getHeartCounts();
      result = result * 59 + ($heartCounts == null ? 43 : $heartCounts.hashCode());
      return result;
   }
}
