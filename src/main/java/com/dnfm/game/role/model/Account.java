package com.dnfm.game.role.model;

import com.dnfm.common.db.BaseEntity;
import com.dnfm.common.db.Db4AccountService;
import com.dnfm.game.activity.model.ActivityBox;
import com.dnfm.game.adventure.model.AdventureReapInfo;
import com.dnfm.game.adventure.model.AdventureUnionInfo;
import com.dnfm.game.bag.model.AccShopInfoBox;
import com.dnfm.game.bag.model.AccountMoneyBox;
import com.dnfm.game.bag.model.AdStorageBox;
import com.dnfm.game.bag.model.AdvBookBox;
import com.dnfm.game.bag.model.AdvUnionSubInfoBox;
import com.dnfm.game.bag.model.EpicPieceBox;
import com.dnfm.game.mail.AccountMailBox;
import com.dnfm.game.role.serializer.AccountSerializerUtil;
import java.util.Date;
import org.nutz.dao.entity.annotation.ColDefine;
import org.nutz.dao.entity.annotation.ColType;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Comment;
import org.nutz.dao.entity.annotation.Default;
import org.nutz.dao.entity.annotation.Index;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;
import org.nutz.dao.entity.annotation.TableIndexes;

@Table("t_account")
@TableIndexes({@Index(
   fields = {"userID"},
   unique = false
), @Index(
   fields = {"id"},
   unique = false
)})
public class Account extends BaseEntity<String> {
   @Name
   @Comment("openid")
   private String id;
   @Column
   @Comment("accountkey")
   private String accountkey;
   @Column
   private long accumulatecera;
   @Column
   private String userID;
   @Column
   private String passwd;
   @Column
   private int roleMaxCount;
   @Column
   private boolean isStop;
   @Column
   private short privilege;
   @Column
   @Default("0")
   private int score = 0;
   @Column
   private String channelNo;
   @Column
   private Date createTime = new Date();
   @Column
   private int zhanlingexp;
   @Column
   @ColDefine(
      type = ColType.MYSQL_JSON
   )
   @Comment("账户名下的各种货币")
   private AccountMoneyBox moneyBox;
   @Column
   @ColDefine(
      type = ColType.MYSQL_JSON
   )
   @Comment("装备碎片")
   private EpicPieceBox epicPieceBox = new EpicPieceBox();
   @Column
   @ColDefine(
      type = ColType.MYSQL_JSON
   )
   @Comment("账户邮箱")
   private AccountMailBox mailBox = new AccountMailBox();
   @Column
   @Default("1")
   private int storageline = 1;
   @Column
   @ColDefine(
      type = ColType.MYSQL_JSON
   )
   @Comment("角色购买记录")
   private AccShopInfoBox accShopInfoBox = new AccShopInfoBox();
   @Column
   @ColDefine(
      type = ColType.MYSQL_JSON
   )
   private AdventureReapInfo adventureReapInfo = new AdventureReapInfo();
   @Column
   @ColDefine(
      type = ColType.MYSQL_JSON
   )
   private AdventureUnionInfo adventureUnionInfo = new AdventureUnionInfo();
   @Column
   @ColDefine(
      type = ColType.MYSQL_JSON
   )
   @Comment("账号金库")
   private AdStorageBox adStorageBox = new AdStorageBox();
   @Column
   @ColDefine(
      type = ColType.MYSQL_JSON
   )
   private AdvBookBox advBookBox = new AdvBookBox();
   @Column
   @ColDefine(
      type = ColType.MYSQL_JSON
   )
   private AdvUnionSubInfoBox advUnionSubInfoBox = new AdvUnionSubInfoBox();
   @Column
   @ColDefine(
      type = ColType.MYSQL_JSON
   )
   @Comment("账号活动")
   private ActivityBox activityBox = new ActivityBox();
   @Column
   private long lastLoginTime;

   public void doAfterInit() {
      AccountSerializerUtil.deserialize(this);
   }

   public void doBeforeSave() {
      AccountSerializerUtil.serialize(this);
   }

   public void save() {
      Db4AccountService.getInstance().add2Queue(this);
   }

   public boolean getIsStop() {
      return this.isStop;
   }

   public void setIsStop(boolean isStop) {
      this.isStop = isStop;
   }

   public void setId(String id) {
      this.id = id;
   }

   public void setAccountkey(String accountkey) {
      this.accountkey = accountkey;
   }

   public void setAccumulatecera(long accumulatecera) {
      this.accumulatecera = accumulatecera;
   }

   public void setUserID(String userID) {
      this.userID = userID;
   }

   public void setPasswd(String passwd) {
      this.passwd = passwd;
   }

   public void setRoleMaxCount(int roleMaxCount) {
      this.roleMaxCount = roleMaxCount;
   }

   public void setPrivilege(short privilege) {
      this.privilege = privilege;
   }

   public void setScore(int score) {
      this.score = score;
   }

   public void setChannelNo(String channelNo) {
      this.channelNo = channelNo;
   }

   public void setCreateTime(Date createTime) {
      this.createTime = createTime;
   }

   public void setZhanlingexp(int zhanlingexp) {
      this.zhanlingexp = zhanlingexp;
   }

   public void setMoneyBox(AccountMoneyBox moneyBox) {
      this.moneyBox = moneyBox;
   }

   public void setEpicPieceBox(EpicPieceBox epicPieceBox) {
      this.epicPieceBox = epicPieceBox;
   }

   public void setMailBox(AccountMailBox mailBox) {
      this.mailBox = mailBox;
   }

   public void setStorageline(int storageline) {
      this.storageline = storageline;
   }

   public void setAccShopInfoBox(AccShopInfoBox accShopInfoBox) {
      this.accShopInfoBox = accShopInfoBox;
   }

   public void setAdventureReapInfo(AdventureReapInfo adventureReapInfo) {
      this.adventureReapInfo = adventureReapInfo;
   }

   public void setAdventureUnionInfo(AdventureUnionInfo adventureUnionInfo) {
      this.adventureUnionInfo = adventureUnionInfo;
   }

   public void setAdStorageBox(AdStorageBox adStorageBox) {
      this.adStorageBox = adStorageBox;
   }

   public void setAdvBookBox(AdvBookBox advBookBox) {
      this.advBookBox = advBookBox;
   }

   public void setAdvUnionSubInfoBox(AdvUnionSubInfoBox advUnionSubInfoBox) {
      this.advUnionSubInfoBox = advUnionSubInfoBox;
   }

   public void setActivityBox(ActivityBox activityBox) {
      this.activityBox = activityBox;
   }

   public void setLastLoginTime(long lastLoginTime) {
      this.lastLoginTime = lastLoginTime;
   }

   public String getId() {
      return this.id;
   }

   public String getAccountkey() {
      return this.accountkey;
   }

   public long getAccumulatecera() {
      return this.accumulatecera;
   }

   public String getUserID() {
      return this.userID;
   }

   public String getPasswd() {
      return this.passwd;
   }

   public int getRoleMaxCount() {
      return this.roleMaxCount;
   }

   public short getPrivilege() {
      return this.privilege;
   }

   public int getScore() {
      return this.score;
   }

   public String getChannelNo() {
      return this.channelNo;
   }

   public Date getCreateTime() {
      return this.createTime;
   }

   public int getZhanlingexp() {
      return this.zhanlingexp;
   }

   public AccountMoneyBox getMoneyBox() {
      return this.moneyBox;
   }

   public EpicPieceBox getEpicPieceBox() {
      return this.epicPieceBox;
   }

   public AccountMailBox getMailBox() {
      return this.mailBox;
   }

   public int getStorageline() {
      return this.storageline;
   }

   public AccShopInfoBox getAccShopInfoBox() {
      return this.accShopInfoBox;
   }

   public AdventureReapInfo getAdventureReapInfo() {
      return this.adventureReapInfo;
   }

   public AdventureUnionInfo getAdventureUnionInfo() {
      return this.adventureUnionInfo;
   }

   public AdStorageBox getAdStorageBox() {
      return this.adStorageBox;
   }

   public AdvBookBox getAdvBookBox() {
      return this.advBookBox;
   }

   public AdvUnionSubInfoBox getAdvUnionSubInfoBox() {
      return this.advUnionSubInfoBox;
   }

   public ActivityBox getActivityBox() {
      return this.activityBox;
   }

   public long getLastLoginTime() {
      return this.lastLoginTime;
   }
}
