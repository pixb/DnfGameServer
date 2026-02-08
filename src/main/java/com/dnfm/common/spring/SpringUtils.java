package com.dnfm.common.spring;

import com.dnfm.cross.core.callback.CallBackService;
import com.dnfm.game.chat.service.ChatService;
import com.dnfm.game.equip.service.EquipService;
import com.dnfm.game.fight.service.BroadcastService;
import com.dnfm.game.friend.service.FriendService;
import com.dnfm.game.identity.IdentityService;
import com.dnfm.game.map.service.MapService;
import com.dnfm.game.player.PlayerService;
import com.dnfm.game.role.service.AccountService;
import com.dnfm.game.role.service.RoleService;
import com.dnfm.game.skill.service.SkillService;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringUtils implements ApplicationContextAware {
   private static ApplicationContext applicationContext;
   private static SpringUtils self;
   @Resource
   private RoleService roleService;
   @Resource
   private EquipService equipService;
   @Resource
   private PlayerService playerService;
   @Resource
   private SkillService skillService;
   @Resource
   private MapService mapService;
   @Resource
   private ChatService chatService;
   @Resource
   private IdentityService identityService;
   @Resource
   private BroadcastService broadcastService;
   @Resource
   private AccountService accountService;
   @Resource
   private FriendService friendService;
   @Resource
   private CallBackService callBackService;

   public static ApplicationContext getApplicationContext() {
      return applicationContext;
   }

   public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
      SpringUtils.applicationContext = applicationContext;
   }

   public static Object getBean(String name) {
      return getApplicationContext().getBean(name);
   }

   public static <T> T getBean(Class<T> clazz) {
      return (T)getApplicationContext().getBean(clazz);
   }

   public static <T> T getBean(String name, Class<T> clazz) {
      return (T)getApplicationContext().getBean(name, clazz);
   }

   public static <T> Map<String, T> getBeansOfType(Class<T> var1) {
      return getApplicationContext().getBeansOfType(var1);
   }

   public static final RoleService getRoleService() {
      return self.roleService;
   }

   public static final EquipService getEquipService() {
      return self.equipService;
   }

   public static final PlayerService getPlayerService() {
      return self.playerService;
   }

   public static final SkillService getSkillService() {
      return self.skillService;
   }

   public static final MapService getMapService() {
      return self.mapService;
   }

   public static final ChatService getChatService() {
      return self.chatService;
   }

   public static final IdentityService getIdentityService() {
      return self.identityService;
   }

   public static final BroadcastService getBroadcastService() {
      return self.broadcastService;
   }

   public static final AccountService getAccountService() {
      return self.accountService;
   }

   public static final FriendService getFriendService() {
      return self.friendService;
   }

   public static final CallBackService getCallBackService() {
      return self.callBackService;
   }

   @PostConstruct
   private void init() {
      self = this;
   }
}
