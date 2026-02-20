package com.dnfm.game.role.service;

import com.dnfm.common.bean.PayGateWay;
import com.dnfm.common.db.Db4CommonService;
import com.dnfm.common.mysql.TargetDataSource;
import com.dnfm.common.spring.SpringUtils;
import com.dnfm.common.utils.ConcurrentHashSet;
import com.dnfm.game.ServerService;
import com.dnfm.game.bag.model.AccountMoneyBox;
import com.dnfm.game.config.LoginServer;
import com.dnfm.game.role.model.Account;
import com.dnfm.game.role.model.AccountLogin;
import com.dnfm.game.role.model.Invite;
import com.dnfm.game.utils.JsonUtils;
import com.dnfm.mina.protobuf.PT_MONEY_ITEM;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.sql.Sql;
import org.nutz.http.Request;
import org.nutz.http.Response;
import org.nutz.http.Sender;
import org.nutz.http.Request.METHOD;
import org.nutz.lang.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
   private static final ConcurrentHashSet<String> banned = new ConcurrentHashSet<String>();
   Logger logger = LoggerFactory.getLogger(AccountService.class);
   @Autowired
   Dao dao;
   @Autowired
   JdbcTemplate jdbcTemplate;

   public void addMoney(Account account, int index, int cnt) {
      AccountMoneyBox accountMoneyBox = account.getMoneyBox();
      accountMoneyBox.addCnt(index, cnt);
      account.setMoneyBox(accountMoneyBox);
      account.save();
   }

   public void costMoney(Account account, int index, int cnt) {
      AccountMoneyBox accountMoneyBox = account.getMoneyBox();
      accountMoneyBox.subCnt(index, cnt);
      account.setMoneyBox(accountMoneyBox);
      account.save();
   }

   public void addTylor(Account account, int cnt) {
      AccountMoneyBox accountMoneyBox = account.getMoneyBox();
      accountMoneyBox.addCnt(2013000001, cnt);
      account.setMoneyBox(accountMoneyBox);
      account.save();
   }

   public void costTylor(Account account, int cnt) {
      AccountMoneyBox accountMoneyBox = account.getMoneyBox();
      accountMoneyBox.subCnt(2013000001, cnt);
      account.setMoneyBox(accountMoneyBox);
      account.save();
   }

   public void updateMoney(Account account, PT_MONEY_ITEM pt_money_item) {
      account.getMoneyBox().putCurrency(pt_money_item);
      account.save();
   }

   public void addCera(Account account, int cnt) {
      AccountMoneyBox accountMoneyBox = account.getMoneyBox();
      accountMoneyBox.addCnt(2, cnt);
      account.setMoneyBox(accountMoneyBox);
      account.save();
   }

   public void costCera(Account account, int cnt) {
      AccountMoneyBox accountMoneyBox = account.getMoneyBox();
      accountMoneyBox.subCnt(2, cnt);
      account.setMoneyBox(accountMoneyBox);
      account.save();
   }

   @CachePut(
      value = {"account"},
      key = "#account.id"
   )
   public Account saveToCache(Account account) {
      return account;
   }

   @Cacheable(
      value = {"account"},
      key = "#root.args[0]"
   )
   public Account getAccount(String id) {
      Account account = (Account)this.dao.fetch(Account.class, Cnd.where("id", "=", id));
      if (account != null) {
         account.doAfterInit();
      }

      return account;
   }

   @Cacheable(
      value = {"account"},
      key = "#userID"
   )
   public Account getAccountByUserID(String userID) {
      Account account = (Account)this.dao.fetch(Account.class, Cnd.where("userID", "=", userID));
      if (account != null) {
         account.doAfterInit();
      }

      return account;
   }

   @CachePut(
      value = {"account"},
      key = "#account.id"
   )
   public Account addAccount(Account account) {
      Db4CommonService.getInstance().add2Queue(account);
      return account;
   }

   @TargetDataSource(
      name = "adb"
   )
   public AccountLogin queryAccount(String openId) {
      AccountLogin account = (AccountLogin)this.dao.fetch(AccountLogin.class, Cnd.where("id", "=", openId));
      return account;
   }

   @TargetDataSource(
      name = "adb"
   )
   public int countPayGateway(String username) {
      return this.dao.count(PayGateWay.class, Cnd.where("account", "=", username));
   }

   @TargetDataSource(
      name = "adb"
   )
   public void updateInvite(Invite invite) {
      this.dao.update(invite, "state|updatetime");
   }

   @TargetDataSource(
      name = "adb"
   )
   public List<Invite> queryInviteList(String inviteAccount) {
      List<Invite> list = this.dao.query(Invite.class, Cnd.where("inviteAccount", "=", inviteAccount).and("state", "=", 0));
      return list;
   }

   @TargetDataSource(
      name = "adb"
   )
   public void update(Sql sql) {
      this.dao.execute(sql);
   }

   @TargetDataSource(
      name = "adb"
   )
   public Invite fetchInvite(String username) {
      return (Invite)this.dao.fetch(Invite.class, Cnd.where("account", "=", username).and("rmb", "=", 0).and("state", "=", 1));
   }

   @TargetDataSource(
      name = "adb"
   )
   public int countInvite(String username) {
      return this.dao.count(Invite.class, Cnd.where("inviteAccount", "=", username).and("rmb", "=", 0).and("state", "=", 1));
   }

   @TargetDataSource(
      name = "adb"
   )
   public int countInvite(String username, String start, String end) {
      return this.dao.count(Invite.class, Cnd.where("inviteAccount", "=", username).and("rmb", "=", 0).and("state", "=", 1).and("createtime", ">", start).and("createtime", "<", end));
   }

   @TargetDataSource(
      name = "adb"
   )
   public void insertInvite(Invite invite) {
      this.dao.insert(invite);
   }

   @TargetDataSource(
      name = "adb"
   )
   public List<LoginServer> queryServer() {
      return this.dao.query(LoginServer.class, Cnd.NEW());
   }

   @TargetDataSource(
      name = "adb"
   )
   public AccountLogin queryByUsername(String userID) {
      return (AccountLogin)this.dao.fetch(AccountLogin.class, Cnd.where("userID", "=", userID));
   }

   @TargetDataSource(
      name = "adb"
   )
   public void updateAccount(AccountLogin accountLogin, String content) {
      if (content == null) {
         this.dao.update(accountLogin, "passwd|isStop");
      } else {
         this.dao.update(accountLogin, content);
      }

   }

   @TargetDataSource(
      name = "adb"
   )
   public List<PayGateWay> getPayGateWay(String account) {
      return this.dao.query(PayGateWay.class, Cnd.where("account", "=", account).and("state", "=", 99));
   }

   @TargetDataSource(
      name = "adb"
   )
   public void update(PayGateWay payGateWay) {
      this.dao.update(payGateWay, "state");
   }

   public boolean isBanedDevice(String deviceInfo) {
      return banned.contains(deviceInfo);
   }

   public void addBanedDevice(String deviceInfo) {
      banned.add(deviceInfo);
   }

   public boolean isBanedIP(String ipAddr) {
      return banned.contains(ipAddr);
   }

   public void addBanedIP(String ipAddr) {
      banned.add(ipAddr);
   }

   public Map<String, Object> fetchAccountFromLoginServer(String sid) {
      Map<String, Object> params = new HashMap();
      params.put("id", sid);
      Request request = Request.create(((ServerService)SpringUtils.getBean(ServerService.class)).getLoginServerUrl() + "/login/fetch", METHOD.POST).setParams(params);
      Response response = Sender.create(request).setTimeout(3000).send();
      return !Strings.isEmpty(response.getContent()) ? JsonUtils.string2Map(response.getContent()) : null;
   }

   public boolean isBanned(String id, String ip, String mac) {
      try {
         Map<String, Object> params = new HashMap();
         params.put("id", id);
         params.put("ip", ip);
         params.put("mac", mac);
         Request request = Request.create(((ServerService)SpringUtils.getBean(ServerService.class)).getLoginServerUrl() + "/login/banned", METHOD.POST).setParams(params);
         Response response = Sender.create(request).setTimeout(3000).send();
         if (!Strings.isEmpty(response.getContent())) {
            this.logger.error("从登录服验证黑名单信息=={}", response.getContent());
            return (Boolean)JsonUtils.string2Object(response.getContent(), Boolean.class);
         } else {
            return false;
         }
      } catch (Exception var7) {
         return false;
      }
   }

   public Account getAccountByUsername(String name) {
      Account account = (Account)this.dao.fetch(Account.class, Cnd.where("userID", "=", name));
      if (account != null) {
         account.doAfterInit();
      }

      return account;
   }
}
