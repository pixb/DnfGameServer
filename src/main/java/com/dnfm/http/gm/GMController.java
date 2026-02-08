package com.dnfm.http.gm;

import com.alibaba.fastjson.JSON;
import com.dnfm.common.spring.SpringUtils;
import com.dnfm.game.mail.model.MailItem;
import com.dnfm.game.player.PlayerService;
import com.dnfm.game.player.model.PlayerProfile;
import com.dnfm.game.role.model.Account;
import com.dnfm.game.role.model.AccountLogin;
import com.dnfm.game.role.model.Role;
import com.dnfm.game.role.service.AccountService;
import com.dnfm.game.role.service.RoleService;
import com.dnfm.game.utils.TimeUtil;
import com.dnfm.mina.cache.DataCache;
import com.dnfm.mina.cache.SessionUtils;
import com.dnfm.mina.message.MessagePusher;
import com.dnfm.mina.protobuf.RES_EXIT_CHARACTER;
import com.dnfm.mina.session.SessionManager;
import com.dnfm.mina.task.ThreadLocalUtil;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/gm"})
public class GMController {
   private static final Logger log = LoggerFactory.getLogger(GMController.class);
   private static final String FAIL = "FAIL";
   private static final String SUCCESS = "SUCCESS";
   @Autowired(
      required = false
   )
   HttpServletResponse response;
   @Autowired
   AccountService accountService;
   @Autowired
   PlayerService playerService;
   @Autowired
   RoleService roleService;

   @RequestMapping(
      method = {RequestMethod.POST},
      path = {"sendItems"}
   )
   @ResponseBody
   public String sendItems(String userName, String title, String massage, String items) {
      try {
         if (StringUtils.isEmpty(userName)) {
            log.error("发邮件时userID为空");
            return "FAIL";
         } else if (!"".equals(items) && items.contains("@")) {
            Account account = this.accountService.getAccountByUsername(userName);
            if (account == null) {
               log.error("发邮件时找不到对应的账户, userID:{}", userName);
               return "FAIL";
            } else {
               List<MailItem> mailItemList = new ArrayList();
               if (items.contains(",")) {
                  String[] split = items.split(",");

                  for(String s : split) {
                     MailItem mailItem = new MailItem();
                     String[] param = s.split("@");
                     mailItem.index = Integer.parseInt(param[0]);
                     mailItem.cnt = Integer.parseInt(param[1]);
                     mailItemList.add(mailItem);
                  }
               } else {
                  MailItem mailItem = new MailItem();
                  String[] param = items.split("@");
                  mailItem.index = Integer.parseInt(param[0]);
                  mailItem.cnt = Integer.parseInt(param[1]);
                  mailItemList.add(mailItem);
               }

               Long charguid = (Long)DataCache.ONLINE_ACC_MAP.get(account.getId());
               log.error("openid: [{}] -> charguid: [{}]", account.getId(), charguid);
               if (charguid != null) {
                  IoSession session = SessionUtils.getSessionBy(charguid);
                  Account accountOnline = SessionUtils.getAccountBySession(session);
                  log.error("account online: [{}]", accountOnline.getId());
                  if (accountOnline == null) {
                     account.getMailBox().addMail(title, massage, mailItemList);
                     account.save();
                  } else {
                     accountOnline.getMailBox().addMail(title, massage, mailItemList);
                     accountOnline.save();
                  }
               } else {
                  log.error("account no online openId: [{}]", account.getId());
                  account.getMailBox().addMail(title, massage, mailItemList);
                  account.save();
               }

               log.error("发送邮件成功");
               return "SUCCESS";
            }
         } else {
            log.error("发邮件时道具为空");
            return "FAIL";
         }
      } catch (Exception e) {
         log.error("GM 发送道具异常", e);
         return "FAIL";
      }
   }

   @RequestMapping(
      method = {RequestMethod.POST},
      path = {"banAccount"}
   )
   @ResponseBody
   public String banAccount(String accountName, Integer type) {
      try {
         if (StringUtils.isEmpty(accountName)) {
            log.error("封号时userID为空");
            return "FAIL";
         } else {
            Account account = this.accountService.getAccountByUsername(accountName);
            if (account == null) {
               log.error("封号时找不到对应的账户, userID:{}", accountName);
               return "FAIL";
            } else {
               AccountLogin accountLogin = this.accountService.queryAccount(account.getId());
               log.error("accountLogin:{}", JSON.toJSONString(accountLogin));
               if (type == 0) {
                  account.setIsStop(true);
                  accountLogin.setIsStop(true);
               } else if (type == 1) {
                  account.setIsStop(false);
                  accountLogin.setIsStop(false);
               }

               this.accountService.updateAccount(accountLogin, (String)null);

               for(PlayerProfile playerProfile : this.playerService.getPlayersBy(account.getId())) {
                  Role role = this.playerService.getPlayerBy(playerProfile.getUid());
                  if (role != null && SpringUtils.getRoleService().isOnline(role)) {
                     IoSession session = SessionUtils.getSessionBy(role.getUid());
                     role.setLastlogout(TimeUtil.currS());
                     role.save();
                     RES_EXIT_CHARACTER res_exit_character = new RES_EXIT_CHARACTER();
                     res_exit_character.world = -1;
                     res_exit_character.channel = 1;
                     res_exit_character.exittype = 1;
                     MessagePusher.pushMessage((IoSession)session, res_exit_character);
                     log.error("玩家[{}]被踢下线", role.getName());
                     SessionManager.INSTANCE.closeSession(session);
                  }
               }

               account.save();
               return "SUCCESS";
            }
         }
      } catch (Exception e) {
         log.error("GM 封号异常", e);
         return "FAIL";
      }
   }

   @RequestMapping(
      method = {RequestMethod.POST},
      path = {"muteAccount"}
   )
   @ResponseBody
   public String muteAccount(String accountName, Integer type) {
      try {
         if (StringUtils.isEmpty(accountName)) {
            log.error("accountName为空");
            return "FAIL";
         } else {
            Account account = this.accountService.getAccountByUsername(accountName);
            if (account == null) {
               log.error("禁言时找不到对应的账户, accountName:{}", accountName);
               return "FAIL";
            } else {
               for(PlayerProfile playerProfile : this.playerService.getPlayersBy(account.getId())) {
                  Role role = this.playerService.getPlayerBy(playerProfile.getUid());
                  if (role != null) {
                     if (SpringUtils.getRoleService().isOnline(role)) {
                        ThreadLocalUtil.addLocalTask((Role)role, () -> {
                           if (type == 0) {
                              role.setWordTime(0L);
                              log.error("玩家[{}]解除禁言", role.getName());
                           } else if (type == 1) {
                              role.setWordTime(TimeUtil.currS());
                              log.error("玩家[{}]开启禁言", role.getName());
                           }

                           role.save();
                        });
                     } else if (type == 0) {
                        role.setWordTime(0L);
                        log.error("玩家[{}]解除禁言", role.getName());
                     } else if (type == 1) {
                        role.setWordTime(TimeUtil.currS());
                        log.error("玩家[{}]开启禁言", role.getName());
                     }
                  }
               }

               return "SUCCESS";
            }
         }
      } catch (Exception e) {
         log.error("GM 禁言异常", e);
         return "FAIL";
      }
   }
}
