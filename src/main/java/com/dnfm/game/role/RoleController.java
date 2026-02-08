package com.dnfm.game.role;

import com.dnfm.common.spring.SpringUtils;
import com.dnfm.common.utils.Util;
import com.dnfm.game.ServerService;
import com.dnfm.game.config.Server;
import com.dnfm.game.player.PlayerService;
import com.dnfm.game.role.model.Account;
import com.dnfm.game.role.model.Role;
import com.dnfm.game.role.model.TonicBox;
import com.dnfm.game.role.service.AccountService;
import com.dnfm.game.role.service.RoleService;
import com.dnfm.game.utils.TimeUtil;
import com.dnfm.mina.annotation.RequestMapping;
import com.dnfm.mina.cache.SessionUtils;
import com.dnfm.mina.message.MessagePusher;
import com.dnfm.mina.protobuf.PT_BATTLE_PASS_INFO;
import com.dnfm.mina.protobuf.PT_MONEY_ITEM;
import com.dnfm.mina.protobuf.PT_RETURN_USER_INFO;
import com.dnfm.mina.protobuf.PT_SYSTEM_BUFF_APPENDAGE;
import com.dnfm.mina.protobuf.PT_TONIC_INFO;
import com.dnfm.mina.protobuf.PT_TONIC_MATERIAL_USAGE;
import com.dnfm.mina.protobuf.REQ_ARCADE_PVP_REWARD_INFO;
import com.dnfm.mina.protobuf.REQ_CREATE_CHARACTER;
import com.dnfm.mina.protobuf.REQ_EXIT_CHARACTER;
import com.dnfm.mina.protobuf.REQ_REMOVE_CHARACTER;
import com.dnfm.mina.protobuf.REQ_SEASON_PASS_INFO;
import com.dnfm.mina.protobuf.REQ_TONIC_INFO;
import com.dnfm.mina.protobuf.REQ_TONIC_INIT;
import com.dnfm.mina.protobuf.REQ_TONIC_UPGRADE;
import com.dnfm.mina.protobuf.RES_ARCADE_PVP_REWARD_INFO;
import com.dnfm.mina.protobuf.RES_CREATE_CHARACTER;
import com.dnfm.mina.protobuf.RES_EXIT_CHARACTER;
import com.dnfm.mina.protobuf.RES_REMOVE_CHARACTER;
import com.dnfm.mina.protobuf.RES_SEASON_PASS_INFO;
import com.dnfm.mina.protobuf.RES_SYSTEM_BUFF_APPENDAGE;
import com.dnfm.mina.protobuf.RES_TONIC_INFO;
import com.dnfm.mina.protobuf.RES_TONIC_INIT;
import com.dnfm.mina.protobuf.RES_TONIC_UPGRADE;
import com.dnfm.mina.protobuf.UPDATE_ANTIEVIL_SCORE;
import com.dnfm.mina.session.SessionManager;
import com.dnfm.mina.session.SessionProperties;
import java.util.ArrayList;
import java.util.List;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class RoleController {
   @Autowired
   PlayerService playerService;
   @Autowired
   AccountService accountService;
   Logger logger = LoggerFactory.getLogger(RoleController.class);

   @RequestMapping
   public void REQ_EXIT_CHARACTER(IoSession session, REQ_EXIT_CHARACTER req_exit_character) {
      Role role = SessionUtils.getRoleBySession(session);
      if (role != null) {
         role.setLastlogout(TimeUtil.currS());
         role.save();
      }

      RES_EXIT_CHARACTER res_exit_character = new RES_EXIT_CHARACTER();
      res_exit_character.world = req_exit_character.world;
      res_exit_character.channel = req_exit_character.channel;
      res_exit_character.exittype = req_exit_character.exittype;
      res_exit_character.transId = req_exit_character.transId;
      MessagePusher.pushMessage((IoSession)session, res_exit_character);
   }

   @RequestMapping
   public void REQ_REMOVE_CHARACTER(IoSession session, REQ_REMOVE_CHARACTER req_remove_character) {
      long charguid = req_remove_character.charguid;
      Role role = this.playerService.getPlayerBy(charguid);
      role.setDelete(true);
      role.save();
      RES_REMOVE_CHARACTER res_remove_character = new RES_REMOVE_CHARACTER();
      res_remove_character.charguid = charguid;
      res_remove_character.transId = req_remove_character.transId;
      MessagePusher.pushMessage((IoSession)session, res_remove_character);
   }

   @RequestMapping
   public void ReqCreateCharacter(IoSession session, REQ_CREATE_CHARACTER req_create_character) {
      String name = req_create_character.name;
      RES_CREATE_CHARACTER res_create_character = new RES_CREATE_CHARACTER();
      ServerService serverService = (ServerService)SpringUtils.getBean(ServerService.class);
      String openid = (String)SessionManager.INSTANCE.getSessionAttr(session, SessionProperties.ACCOUNT_OPENID, String.class);
      if (Util.isEmpty(openid)) {
         this.logger.error("ReqCreateCharacter==ERR==openid为空");
      } else {
         Account account = this.accountService.getAccount(openid);
         String accountKey = account.getAccountkey();
         RoleService roleService = (RoleService)SpringUtils.getBean(RoleService.class);
         Server server = serverService.getServer();
         PlayerService playerService = (PlayerService)SpringUtils.getBean(PlayerService.class);
         String distName = server.getName();
         this.logger.error("ReqCreateCharacter==distName=={}", distName);
         if (req_create_character.job != null) {
            res_create_character.job = req_create_character.job;
         }

         boolean roleExist = roleService.checkRoleName(name);
         if (roleExist) {
            RES_CREATE_CHARACTER res_create_character1 = new RES_CREATE_CHARACTER();
            res_create_character1.error = 1;
            res_create_character1.transId = req_create_character.transId;
            MessagePusher.pushMessage((IoSession)session, res_create_character1);
         } else {
            Role newRole = roleService.createRole(session, openid, accountKey, req_create_character);
            if (newRole == null) {
               this.logger.error("ReqCreateChar==newRole==null");
            } else {
               res_create_character.charguid = newRole.getUid();
               res_create_character.name = name;
               res_create_character.transId = req_create_character.transId;
               MessagePusher.pushMessage((IoSession)session, res_create_character);
            }
         }
      }
   }

   @RequestMapping
   public void REQ_SEASON_PASS_INFO(IoSession session, REQ_SEASON_PASS_INFO reqSeasonPassInfo) {
      RES_SEASON_PASS_INFO resSeasonPassInfo = new RES_SEASON_PASS_INFO();
      resSeasonPassInfo.returnUserInfo = new PT_RETURN_USER_INFO();
      resSeasonPassInfo.battlePassInfo = new PT_BATTLE_PASS_INFO();
      resSeasonPassInfo.pvpBattlePassInfo = new PT_BATTLE_PASS_INFO();
      resSeasonPassInfo.transId = reqSeasonPassInfo.transId;
      MessagePusher.pushMessage((IoSession)session, resSeasonPassInfo);
   }

   @RequestMapping
   public void ReqTonicInfo(IoSession session, REQ_TONIC_INFO reqTonicInfo) {
      Role role = SessionUtils.getRoleBySession(session);
      TonicBox tonicBox = role.getTonicBox();
      RES_TONIC_INFO res_tonic_info = new RES_TONIC_INFO();
      res_tonic_info.crystaltonic = tonicBox.getCrystaltonic();
      res_tonic_info.crystaltonicpoint = tonicBox.getCrystaltonicpoint();
      res_tonic_info.transId = reqTonicInfo.transId;
      MessagePusher.pushMessage((IoSession)session, res_tonic_info);
   }

   @RequestMapping
   public void REQ_TONIC_INIT(IoSession session, REQ_TONIC_INIT reqTonicInfo) {
      Role role = SessionUtils.getRoleBySession(session);
      TonicBox tonicBox = role.getTonicBox();
      RES_TONIC_INIT resTonicInit = new RES_TONIC_INIT();
      resTonicInit.index = reqTonicInfo.index;
      resTonicInit.crystaltonic = tonicBox.getCrystaltonic();
      resTonicInit.crystaltonicpoint = tonicBox.getCrystaltonicpoint();
      resTonicInit.transId = reqTonicInfo.transId;
      MessagePusher.pushMessage((IoSession)session, resTonicInit);
   }

   @RequestMapping
   public void REQ_TONIC_UPGRADE(IoSession session, REQ_TONIC_UPGRADE reqTonicUpgrade) {
      int index = reqTonicUpgrade.index;
      RES_TONIC_UPGRADE resTonicUpgrade = new RES_TONIC_UPGRADE();
      Role role = SessionUtils.getRoleBySession(session);
      int level = 0;
      List<PT_MONEY_ITEM> currency = new ArrayList();
      PT_TONIC_INFO ptTonicInfo = role.getTonicBox().getTonic(index);
      level = ptTonicInfo.level;
      String needMoney = (String)RoleDataPool.tonicUpgrade.get(level);
      int itemIndex = Integer.valueOf(needMoney.split("-")[0]);
      int count = Integer.valueOf(needMoney.split("-")[1]);
      int needgold = Integer.valueOf(needMoney.split("-")[2]);
      if (itemIndex == 2013102101) {
         int highcount = 0;
         PT_TONIC_MATERIAL_USAGE ptTonicMaterialUsage = role.getTonicBox().getTonicPoint(2013102101);
         ptTonicMaterialUsage.index = 2013102101;
         ptTonicMaterialUsage.total = ptTonicMaterialUsage.total + count;
         highcount = ptTonicMaterialUsage.total;
         PT_TONIC_INFO ptTonicInfo1 = role.getTonicBox().getTonic(11);
         if (highcount >= 5) {
            if (ptTonicInfo1 == null) {
               ptTonicInfo1 = new PT_TONIC_INFO();
               ptTonicInfo1.index = 11;
               ptTonicInfo1.level = 1;
            } else {
               ptTonicInfo1.level = 1;
            }
         } else if (highcount >= 25) {
            ptTonicInfo1.level = 2;
         } else if (highcount >= 80) {
            ptTonicInfo1.level = 3;
         } else if (highcount >= 250) {
            ptTonicInfo1.level = 4;
         } else if (highcount >= 500) {
            ptTonicInfo1.level = 5;
         }

         role.getTonicBox().updateCrystalTonicPoint(ptTonicMaterialUsage);
         role.getTonicBox().updateCrystaralTonic(ptTonicInfo1);
         role.getMoneyBox().subCnt(2013102101, count);
         currency.add(role.getMoneyBox().getMoneyItem(2013102101));
      } else {
         PT_TONIC_MATERIAL_USAGE ptTonicMaterialUsage = role.getTonicBox().getTonicPoint(2013102100);
         ptTonicMaterialUsage.index = 2013102100;
         ptTonicMaterialUsage.total = ptTonicMaterialUsage.total + count;
         role.getTonicBox().updateCrystalTonicPoint(ptTonicMaterialUsage);
         role.getMoneyBox().subCnt(2013102100, count);
         currency.add(role.getMoneyBox().getMoneyItem(2013102100));
      }

      Integer var19 = ptTonicInfo.level;
      Integer var20 = ptTonicInfo.level = ptTonicInfo.level + 1;
      role.getTonicBox().updateCrystaralTonic(ptTonicInfo);
      role.getMoneyBox().submoney(needgold);
      this.sendSystemBuffAppendage(session, ptTonicInfo.level, ptTonicInfo.index + 3100, 1, role.getUid(), role);
      currency.add(role.getMoneyBox().getMoneyItem(0));
      resTonicUpgrade.index = index;
      resTonicUpgrade.currency = currency;
      resTonicUpgrade.crystaltonic = role.getTonicBox().getCrystaltonic();
      resTonicUpgrade.crystaltonicpoint = role.getTonicBox().getCrystaltonicpoint();
      resTonicUpgrade.transId = reqTonicUpgrade.transId;
      role.save();
      MessagePusher.pushMessage((IoSession)session, resTonicUpgrade);
   }

   @RequestMapping
   public void reqArcadePvpRewardInfo(IoSession session, REQ_ARCADE_PVP_REWARD_INFO reqArcadePvpRewardInfo) {
      RES_ARCADE_PVP_REWARD_INFO resArcadePvpRewardInfo = new RES_ARCADE_PVP_REWARD_INFO();
      resArcadePvpRewardInfo.error = 4;
      resArcadePvpRewardInfo.transId = reqArcadePvpRewardInfo.transId;
      MessagePusher.pushMessage((IoSession)session, resArcadePvpRewardInfo);
   }

   public void sendSystemBuffAppendage(IoSession session, int level, int index, int type, long guid, Role role) {
      RES_SYSTEM_BUFF_APPENDAGE resSystemBuffAppendageList0 = new RES_SYSTEM_BUFF_APPENDAGE();
      resSystemBuffAppendageList0.time = TimeUtil.currS();
      if (type != 0) {
         resSystemBuffAppendageList0.type = type;
      }

      if (level != 0) {
         List<Integer> buffList = new ArrayList();
         buffList.add(level);
         resSystemBuffAppendageList0.values = buffList;
         PT_SYSTEM_BUFF_APPENDAGE ptSystemBuffAppendage = new PT_SYSTEM_BUFF_APPENDAGE();
         ptSystemBuffAppendage.index = index;
         ptSystemBuffAppendage.values = buffList;
         role.getSysBuffBox().addAppendages(ptSystemBuffAppendage);
      } else {
         role.getSysBuffBox().removeAppendages(index);
      }

      resSystemBuffAppendageList0.index = index;
      resSystemBuffAppendageList0.targetguid = guid;
      resSystemBuffAppendageList0.transId = SessionUtils.getNotiTransId(session);
      MessagePusher.pushMessage((IoSession)session, resSystemBuffAppendageList0);
   }

   public void UPDATE_ANTIEVIL_SCORE(IoSession session, int oldscore, int newscore) {
      UPDATE_ANTIEVIL_SCORE updateAntievilScore = new UPDATE_ANTIEVIL_SCORE();
      updateAntievilScore.prevscore = oldscore;
      updateAntievilScore.afterscore = newscore;
      MessagePusher.pushMessage((IoSession)session, updateAntievilScore);
   }

   private void checkHeart(Role role, long prevHeartTime, IoSession session) {
   }
}
