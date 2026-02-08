package com.dnfm.game.make;

import cn.hutool.core.util.RandomUtil;
import com.dnfm.common.thread.IdGenerator;
import com.dnfm.common.utils.Util;
import com.dnfm.game.bag.model.AccountMoneyBox;
import com.dnfm.game.bag.model.AvatarBox;
import com.dnfm.game.bag.model.CardBox;
import com.dnfm.game.bag.model.ConsumableBox;
import com.dnfm.game.bag.model.EmblemBox;
import com.dnfm.game.bag.model.MaterialBox;
import com.dnfm.game.bag.model.MoneyBox;
import com.dnfm.game.bag.serveice.BagService;
import com.dnfm.game.config.ConsumeItem;
import com.dnfm.game.config.Equip;
import com.dnfm.game.equip.EquipDataPool;
import com.dnfm.game.equip.model.EquipBox;
import com.dnfm.game.item.ItemDataPool;
import com.dnfm.game.make.model.AvatarCompoundRes;
import com.dnfm.game.make.model.MakeModel;
import com.dnfm.game.make.model.RemoveItem;
import com.dnfm.game.make.model.StackableModel;
import com.dnfm.game.role.model.Account;
import com.dnfm.game.role.model.Role;
import com.dnfm.game.role.service.RoleService;
import com.dnfm.game.utils.TimeUtil;
import com.dnfm.mina.annotation.RequestMapping;
import com.dnfm.mina.cache.DataCache;
import com.dnfm.mina.cache.SessionUtils;
import com.dnfm.mina.message.MessagePusher;
import com.dnfm.mina.protobuf.PT_AVATAR_GUID;
import com.dnfm.mina.protobuf.PT_AVATAR_ITEM;
import com.dnfm.mina.protobuf.PT_CARD_COMPOSE;
import com.dnfm.mina.protobuf.PT_CONSUME_ITEMS;
import com.dnfm.mina.protobuf.PT_CONTENTS_REWARD_INFO;
import com.dnfm.mina.protobuf.PT_CURRENCY_REWARD_INFO;
import com.dnfm.mina.protobuf.PT_EQUIP;
import com.dnfm.mina.protobuf.PT_INDEX_COUNT;
import com.dnfm.mina.protobuf.PT_ITEMS;
import com.dnfm.mina.protobuf.PT_ITEM_DISJOINT_GUID;
import com.dnfm.mina.protobuf.PT_ITEM_PRODUCTION_SLOT;
import com.dnfm.mina.protobuf.PT_ITEM_REWARD_INFO;
import com.dnfm.mina.protobuf.PT_MATERIAL_ITEM;
import com.dnfm.mina.protobuf.PT_MONEY_ITEM;
import com.dnfm.mina.protobuf.PT_REMOVEITEMS;
import com.dnfm.mina.protobuf.PT_STACKABLE;
import com.dnfm.mina.protobuf.REQ_CARD_COMPOSE;
import com.dnfm.mina.protobuf.REQ_ITEM_AVATAR_COMPOSE;
import com.dnfm.mina.protobuf.REQ_ITEM_COMBINE;
import com.dnfm.mina.protobuf.REQ_ITEM_DISJOINT;
import com.dnfm.mina.protobuf.REQ_ITEM_EMBLEM_UPGRADE_INVEN;
import com.dnfm.mina.protobuf.REQ_ITEM_EMBLEM_UPGRADE_QUICK;
import com.dnfm.mina.protobuf.REQ_ITEM_PRODUCTION_INFO;
import com.dnfm.mina.protobuf.REQ_ITEM_PRODUCTION_REGISTER;
import com.dnfm.mina.protobuf.REQ_WARDROBE_SET_SLOT;
import com.dnfm.mina.protobuf.RES_CARD_COMPOSE;
import com.dnfm.mina.protobuf.RES_EMBLEM_UPGRADE;
import com.dnfm.mina.protobuf.RES_ITEM_AVATAR_COMPOSE;
import com.dnfm.mina.protobuf.RES_ITEM_COMBINE;
import com.dnfm.mina.protobuf.RES_ITEM_DISJOINT;
import com.dnfm.mina.protobuf.RES_ITEM_EMBLEM_UPGRADE_QUICK;
import com.dnfm.mina.protobuf.RES_ITEM_PRODUCTION_INFO;
import com.dnfm.mina.protobuf.RES_ITEM_PRODUCTION_REGISTER;
import com.dnfm.mina.protobuf.RES_WARDROBE_SET_SLOT;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MakeController {
   @Autowired
   RoleService roleService;
   @Autowired
   BagService bagService;
   Logger logger = LoggerFactory.getLogger(MakeController.class);

   public static boolean isEmblemSuccess(int level) {
      int randoms = RandomUtil.randomInt(0, 100);
      if (level < 5) {
         return true;
      } else if (level == 5) {
         return randoms <= 70;
      } else if (level == 6) {
         return randoms <= 60;
      } else if (level == 7) {
         return randoms <= 50;
      } else if (level == 8) {
         return randoms <= 40;
      } else if (level == 9) {
         return randoms <= 30;
      } else {
         return false;
      }
   }

   public static int getemblemcost(int level) {
      int moneycost = 0;
      if (level == 1) {
         moneycost = 1000;
      } else if (level == 2) {
         moneycost = 1500;
      } else if (level == 3) {
         moneycost = 2000;
      } else if (level == 4) {
         moneycost = 3000;
      } else if (level == 5) {
         moneycost = 4000;
      } else if (level == 6) {
         moneycost = 5000;
      } else if (level == 7) {
         moneycost = 10000;
      } else if (level == 8) {
         moneycost = 15000;
      } else if (level == 9) {
         moneycost = 30000;
      }

      return moneycost;
   }

   public static int getLuckycost(int level) {
      if (level == 6) {
         return 1;
      } else if (level == 7) {
         return 3;
      } else if (level == 8) {
         return 8;
      } else if (level == 9) {
         return 19;
      } else {
         return level == 10 ? 46 : 0;
      }
   }

   public static void main(String[] args) {
   }

   @RequestMapping
   public void REQ_ITEM_EMBLEM_UPGRADE_INVEN(IoSession session, REQ_ITEM_EMBLEM_UPGRADE_INVEN req_item_emblem_upgrade_inven) {
      Role role = SessionUtils.getRoleBySession(session);
      EmblemBox emblemBox = role.getEmblemBox();
      MaterialBox materialBox = role.getMaterialBox();
      RES_EMBLEM_UPGRADE res_emblem_upgrade = new RES_EMBLEM_UPGRADE();
      PT_REMOVEITEMS removeitems = new PT_REMOVEITEMS();
      removeitems.consumeitems = new ArrayList();
      removeitems.materialitems = new ArrayList();
      removeitems.emblemitems = new ArrayList();
      PT_CONTENTS_REWARD_INFO rewards = new PT_CONTENTS_REWARD_INFO();
      rewards.items = new PT_ITEM_REWARD_INFO();
      rewards.currency = new PT_CURRENCY_REWARD_INFO();
      rewards.items.inventory = new PT_ITEMS();
      rewards.items.inventory.emblemitems = new ArrayList();
      int index = req_item_emblem_upgrade_inven.index;
      int trycount = req_item_emblem_upgrade_inven.trycount;
      int moneycost = 0;
      int talisman = 0;
      int needtalisman = 0;
      res_emblem_upgrade.trycount = trycount;
      int level = ((ConsumeItem)ItemDataPool.consumeItemMap.get(index)).getLevel();
      moneycost = getemblemcost(level);
      PT_STACKABLE emblem = emblemBox.getEmblem(index);
      PT_STACKABLE emblemnew = emblemBox.getEmblem(index + 1);
      PT_STACKABLE emblemreward = new PT_STACKABLE();
      PT_STACKABLE emblemOldReward = new PT_STACKABLE();
      emblemOldReward.index = index;
      emblemOldReward.count = 0;
      emblemreward.index = index + 1;
      emblemreward.count = 0;
      PT_STACKABLE ptStackable = (PT_STACKABLE)materialBox.getMaterialsMap().get(2013104426);
      PT_STACKABLE ptStackable1 = (PT_STACKABLE)materialBox.getMaterialsMap().get(2013000026);
      if (req_item_emblem_upgrade_inven.talisman != null) {
         res_emblem_upgrade.success = trycount;
         talisman = req_item_emblem_upgrade_inven.trycount;
         if (ptStackable != null && ptStackable.getCount() > 0) {
            if (ptStackable.getCount() >= talisman) {
               ptStackable.setCount(ptStackable.getCount() - talisman);
            } else {
               needtalisman = talisman - ptStackable.getCount();
               ptStackable.setCount(0);
            }

            materialBox.addMaterial(ptStackable);
            removeitems.materialitems.add(ptStackable);
            if (needtalisman > 0) {
               ptStackable1.setCount(ptStackable1.getCount() - needtalisman);
               materialBox.addMaterial(ptStackable1);
               removeitems.materialitems.add(ptStackable1);
            }
         }

         emblem.count = emblem.count - trycount * 2;
         removeitems.emblemitems.add(emblem);
         emblemBox.putEmblem(emblem);
         if (emblemnew != null) {
            emblemnew.count = emblemnew.count + trycount;
            emblemreward.count = trycount;
         } else {
            emblemnew = new PT_STACKABLE();
            emblemnew.setIndex(index + 1);
            emblemnew.setCount(trycount);
            emblemreward.setCount(trycount);
         }

         emblemBox.putEmblem(emblemnew);
         rewards.items.inventory.emblemitems.add(emblemreward);
      } else {
         if (ptStackable != null) {
            removeitems.materialitems.add(ptStackable);
         } else {
            removeitems.materialitems.add(ItemDataPool.setMaterial(2013104426, 0, false));
         }

         for(int i = 1; i <= trycount; ++i) {
            if (isEmblemSuccess(level)) {
               if (res_emblem_upgrade.success == null) {
                  res_emblem_upgrade.success = 1;
               } else {
                  Integer var23 = res_emblem_upgrade.success;
                  Integer var24 = res_emblem_upgrade.success = res_emblem_upgrade.success + 1;
               }

               emblem.count = emblem.count - 2;
               if (emblemnew == null) {
                  emblemnew = new PT_STACKABLE();
                  emblemnew.setIndex(index + 1);
                  emblemnew.setCount(1);
               } else {
                  emblemnew.count = emblemnew.count + 1;
               }

               emblemreward.count = emblemreward.count + 1;
            } else {
               emblem.count = emblem.count - 1;
               emblemOldReward.count = emblemOldReward.count + 1;
            }
         }

         if (emblemreward.count > 0) {
            emblemBox.putEmblem(emblemnew);
            rewards.items.inventory.emblemitems.add(emblemreward);
            removeitems.materialitems.add(ItemDataPool.setMaterial(emblemreward.index, 0, true));
         }

         if (emblemOldReward.count > 0) {
            rewards.items.inventory.emblemitems.add(emblemOldReward);
         }

         removeitems.emblemitems.add(emblem);
         emblemBox.putEmblem(emblem);
      }

      removeitems.emblemitems.add(ItemDataPool.setMaterial(index, 0, true));
      if (needtalisman == 0) {
         if (ptStackable1 != null) {
            removeitems.materialitems.add(ptStackable1);
         } else {
            removeitems.materialitems.add(ItemDataPool.setMaterial(2013000026, 0, false));
         }
      }

      res_emblem_upgrade.rewards = rewards;
      role.getMoneyBox().submoney(moneycost * trycount);
      rewards.currency.currency = new ArrayList();
      rewards.currency.currency.add(role.getMoneyBox().getMoneyItem(0));
      role.setEmblemBox(emblemBox);
      role.setMaterialBox(materialBox);
      res_emblem_upgrade.removeitems = removeitems;
      res_emblem_upgrade.transId = req_item_emblem_upgrade_inven.transId;
      role.save();
      MessagePusher.pushMessage((IoSession)session, res_emblem_upgrade);
   }

   @RequestMapping
   public void REQ_ITEM_EMBLEM_UPGRADE_QUICK(IoSession session, REQ_ITEM_EMBLEM_UPGRADE_QUICK req_item_emblem_upgrade_quick) {
      Role role = SessionUtils.getRoleBySession(session);
      MoneyBox moneyBox = role.getMoneyBox();
      MaterialBox materialBox = role.getMaterialBox();
      EmblemBox emblemBox = role.getEmblemBox();
      PT_STACKABLE luckyStackable = materialBox.getMaterial(2013104426);
      PT_STACKABLE ptStackable1 = materialBox.getMaterial(2013000026);
      int level = ((ConsumeItem)ItemDataPool.consumeItemMap.get(req_item_emblem_upgrade_quick.target)).getLevel();
      int luckyCount = 0;
      List<PT_STACKABLE> materialItems = new ArrayList();
      List<PT_STACKABLE> emblemItems = new ArrayList();
      RES_ITEM_EMBLEM_UPGRADE_QUICK res_item_emblem_upgrade_quick = new RES_ITEM_EMBLEM_UPGRADE_QUICK();
      res_item_emblem_upgrade_quick.target = req_item_emblem_upgrade_quick.target;
      res_item_emblem_upgrade_quick.source = req_item_emblem_upgrade_quick.source;
      res_item_emblem_upgrade_quick.removeitems = new PT_REMOVEITEMS();
      if (level > 5) {
         int luckycost = getLuckycost(level);
         int needlucky = 0;
         if (luckyStackable != null) {
            if (luckyStackable.count >= luckycost) {
               luckyStackable.count = luckyStackable.count - luckycost;
               materialBox.addMaterial(luckyStackable);
               this.addStackable(materialItems, 2013104426, false, luckyStackable.count, 0L);
            } else {
               needlucky = luckycost - luckyStackable.count;
               luckyStackable.count = 0;
               materialBox.removeMaterial(luckyStackable.index);
               this.addStackable(materialItems, 2013104426, false, 0, 0L);
            }

            if (needlucky > 0) {
               ptStackable1.count = ptStackable1.count - needlucky;
               materialBox.addMaterial(ptStackable1);
               this.addStackable(materialItems, 2013000026, false, ptStackable1.count, 0L);
            }
         }

         this.addStackable(materialItems, 2013000026, false, 0, 0L);
      }

      res_item_emblem_upgrade_quick.removeitems.materialitems = materialItems;

      for(PT_INDEX_COUNT pic : req_item_emblem_upgrade_quick.source) {
         int index = pic.index;
         int needCnt = pic.count;
         PT_STACKABLE var18 = emblemBox.getEmblem(index);
         var18.count = var18.count - needCnt;
         this.addStackable(emblemItems, index, false, emblemBox.getEmblem(index).count, 0L);
         emblemBox.updateEmblemSub(index, needCnt);
      }

      res_item_emblem_upgrade_quick.removeitems.emblemitems = emblemItems;
      res_item_emblem_upgrade_quick.rewards = new PT_CONTENTS_REWARD_INFO();
      res_item_emblem_upgrade_quick.rewards.items = new PT_ITEM_REWARD_INFO();
      res_item_emblem_upgrade_quick.rewards.items.inventory = new PT_ITEMS();
      res_item_emblem_upgrade_quick.rewards.items.inventory.emblemitems = new ArrayList();
      PT_STACKABLE ptStackable = new PT_STACKABLE();
      ptStackable.index = req_item_emblem_upgrade_quick.target;
      ptStackable.count = 1;
      ptStackable.acquisitiontime = TimeUtil.currS();
      res_item_emblem_upgrade_quick.rewards.items.inventory.emblemitems.add(ptStackable);
      emblemBox.updateEmblem(ptStackable);
      moneyBox.submoney(1000);
      res_item_emblem_upgrade_quick.rewards.currency = new PT_CURRENCY_REWARD_INFO();
      res_item_emblem_upgrade_quick.rewards.currency.currency = new ArrayList();
      PT_MONEY_ITEM pt_currency = new PT_MONEY_ITEM();
      pt_currency.count = moneyBox.getMoneyItem(0).count;
      res_item_emblem_upgrade_quick.rewards.currency.currency.add(pt_currency);
      role.setMoneyBox(moneyBox);
      role.setMaterialBox(materialBox);
      role.setEmblemBox(emblemBox);
      role.save();
      res_item_emblem_upgrade_quick.transId = req_item_emblem_upgrade_quick.transId;
      MessagePusher.pushMessage((IoSession)session, res_item_emblem_upgrade_quick);
   }

   @RequestMapping
   public void REQ_WARDROBE_SET_SLOT(IoSession session, REQ_WARDROBE_SET_SLOT req_wardrobe_set_slot) {
      RES_WARDROBE_SET_SLOT res_wardrobe_set_slot = new RES_WARDROBE_SET_SLOT();
      res_wardrobe_set_slot.transId = req_wardrobe_set_slot.transId;
      MessagePusher.pushMessage((IoSession)session, res_wardrobe_set_slot);
   }

   private AvatarCompoundRes getAvatarComposeRes(int job, String type) {
      List<AvatarCompoundRes> resList = null;
      if (job == 0) {
         int i = Util.randInt(0, 100);
         if (i < 15) {
            resList = (List)DataCache.avatarCompoundResSwordman2.get(type);
         } else {
            resList = (List)DataCache.avatarCompoundResSwordman.get(type);
         }
      } else if (job == 1) {
         int i = Util.randInt(0, 100);
         if (i < 15) {
            resList = (List)DataCache.avatarCompoundResFighter2.get(type);
         } else {
            resList = (List)DataCache.avatarCompoundResFighter.get(type);
         }
      } else if (job == 2) {
         int i = Util.randInt(0, 100);
         if (i < 15) {
            resList = (List)DataCache.avatarCompoundResGunner2.get(type);
         } else {
            resList = (List)DataCache.avatarCompoundResGunner.get(type);
         }
      } else if (job == 3) {
         int i = Util.randInt(0, 100);
         if (i < 15) {
            resList = (List)DataCache.avatarCompoundResMage2.get(type);
         } else {
            resList = (List)DataCache.avatarCompoundResMage.get(type);
         }
      } else {
         if (job != 14) {
            this.logger.error("UNREACH==getAvatarComposeRes==job:" + job);
            return null;
         }

         int i = Util.randInt(0, 100);
         if (i < 15) {
            resList = (List)DataCache.avatarCompoundResPriest2.get(type);
         } else {
            resList = (List)DataCache.avatarCompoundResPriest.get(type);
         }
      }

      int index = Util.randInt(0, resList.size());
      return (AvatarCompoundRes)resList.get(index);
   }

   @RequestMapping
   public void REQ_ITEM_AVATAR_COMPOSE(IoSession session, REQ_ITEM_AVATAR_COMPOSE req_item_avatar_compose) {
      Role role = SessionUtils.getRoleBySession(session);
      AvatarBox avatarBox = role.getAvatarBox();
      MoneyBox moneyBox = role.getMoneyBox();
      ConsumableBox consumableBox = role.getConsumableBox();
      int cnt = consumableBox.getConsumable(2013101485).count;
      if (cnt == 0) {
         this.logger.error("UNREACH==REQ_ITEM_AVATAR_COMPOSE==cnt:" + cnt);
      } else {
         int job = role.getJob();
         String type = null;
         List<PT_AVATAR_GUID> avatar_guids = req_item_avatar_compose.guids;

         for(PT_AVATAR_GUID pt_avatar_guid : avatar_guids) {
            PT_AVATAR_ITEM pt_avatar_item = avatarBox.getAvatar(pt_avatar_guid.guid);
            int index = pt_avatar_item.index;
            if (type == null) {
               int equType = ((Equip)EquipDataPool.index2Equip.get(index)).getEquiptype();
               type = "[" + (String)EquipDataPool.enumMap.get(equType) + "]";
            }

            avatarBox.remove(pt_avatar_guid.guid);
         }

         AvatarCompoundRes avatarCompoundRes = this.getAvatarComposeRes(job, type);
         RES_ITEM_AVATAR_COMPOSE res_item_avatar_compose = new RES_ITEM_AVATAR_COMPOSE();
         res_item_avatar_compose.guids = avatar_guids;
         res_item_avatar_compose.removeitems = new PT_REMOVEITEMS();
         res_item_avatar_compose.removeitems.consumeitems = new ArrayList();
         PT_STACKABLE ptStackable1 = new PT_STACKABLE();
         ptStackable1.index = 2013106320;
         ptStackable1.bind = true;
         res_item_avatar_compose.removeitems.consumeitems.add(ptStackable1);
         PT_STACKABLE ptStackable1b = new PT_STACKABLE();
         ptStackable1b.index = ptStackable1.index;
         res_item_avatar_compose.removeitems.consumeitems.add(ptStackable1b);
         PT_STACKABLE ptStackable2 = new PT_STACKABLE();
         ptStackable2.index = 2013104397;
         ptStackable2.bind = true;
         res_item_avatar_compose.removeitems.consumeitems.add(ptStackable2);
         PT_STACKABLE ptStackable2b = new PT_STACKABLE();
         ptStackable2b.index = ptStackable2.index;
         res_item_avatar_compose.removeitems.consumeitems.add(ptStackable2b);
         PT_STACKABLE ptStackable3 = new PT_STACKABLE();
         ptStackable3.index = 2013105966;
         ptStackable3.bind = true;
         res_item_avatar_compose.removeitems.consumeitems.add(ptStackable3);
         PT_STACKABLE ptStackable3b = new PT_STACKABLE();
         ptStackable3b.index = ptStackable3.index;
         res_item_avatar_compose.removeitems.consumeitems.add(ptStackable3b);
         PT_STACKABLE ptStackable4 = new PT_STACKABLE();
         ptStackable4.index = 2013106326;
         ptStackable4.bind = true;
         res_item_avatar_compose.removeitems.consumeitems.add(ptStackable4);
         PT_STACKABLE ptStackable4b = new PT_STACKABLE();
         ptStackable4b.index = ptStackable4.index;
         res_item_avatar_compose.removeitems.consumeitems.add(ptStackable4b);
         PT_STACKABLE ptStackable5 = new PT_STACKABLE();
         ptStackable5.index = 2013101485;
         ptStackable5.bind = true;
         res_item_avatar_compose.removeitems.consumeitems.add(ptStackable5);
         PT_STACKABLE ptStackable5b = new PT_STACKABLE();
         ptStackable5b.index = ptStackable5.index;
         if (cnt - 1 != 0) {
            ptStackable5b.count = cnt - 1;
            ptStackable5b.acquisitiontime = consumableBox.getConsumable(2013101485).acquisitiontime;
         }

         consumableBox.getConsumable(2013101485).count = cnt - 1;
         res_item_avatar_compose.removeitems.consumeitems.add(ptStackable5b);
         res_item_avatar_compose.rewards = new PT_CONTENTS_REWARD_INFO();
         res_item_avatar_compose.rewards.items = new PT_ITEM_REWARD_INFO();
         res_item_avatar_compose.rewards.items.inventory = new PT_ITEMS();
         res_item_avatar_compose.rewards.items.inventory.avataritems = new ArrayList();
         PT_AVATAR_ITEM pt_avatar_item = new PT_AVATAR_ITEM();
         pt_avatar_item.index = avatarCompoundRes.index;
         pt_avatar_item.guid = IdGenerator.getNextId();
         res_item_avatar_compose.rewards.items.inventory.avataritems.add(pt_avatar_item);
         avatarBox.addAvatar(pt_avatar_item);
         res_item_avatar_compose.rewards.currency = new PT_CURRENCY_REWARD_INFO();
         res_item_avatar_compose.rewards.currency.currency = new ArrayList();
         PT_MONEY_ITEM var24 = moneyBox.getMoneyItem(0);
         var24.count = var24.count - 4000;
         res_item_avatar_compose.rewards.currency.currency.add(moneyBox.getMoneyItem(0));
         role.setMoneyBox(moneyBox);
         role.setAvatarBox(avatarBox);
         role.setConsumableBox(consumableBox);
         role.save();
         res_item_avatar_compose.transId = req_item_avatar_compose.transId;
         MessagePusher.pushMessage((IoSession)session, res_item_avatar_compose);
      }
   }

   @RequestMapping
   public void REQ_ITEM_PRODUCTION_INFO(IoSession session, REQ_ITEM_PRODUCTION_INFO req_item_production_info) {
      if (req_item_production_info.slottype == 1) {
         RES_ITEM_PRODUCTION_INFO res_item_production_info = new RES_ITEM_PRODUCTION_INFO();
         res_item_production_info.infos = new ArrayList();
         PT_ITEM_PRODUCTION_SLOT pt_item_production_slot = new PT_ITEM_PRODUCTION_SLOT();
         pt_item_production_slot.slotindex = 1;
         pt_item_production_slot.usablecount = 100;
         res_item_production_info.infos.add(pt_item_production_slot);
         PT_ITEM_PRODUCTION_SLOT pt_item_production_slot2 = new PT_ITEM_PRODUCTION_SLOT();
         pt_item_production_slot2.slotindex = 2;
         pt_item_production_slot2.usablecount = 100;
         res_item_production_info.infos.add(pt_item_production_slot2);
         PT_ITEM_PRODUCTION_SLOT pt_item_production_slot3 = new PT_ITEM_PRODUCTION_SLOT();
         pt_item_production_slot3.slotindex = 3;
         pt_item_production_slot3.usablecount = 100;
         res_item_production_info.infos.add(pt_item_production_slot3);
         PT_ITEM_PRODUCTION_SLOT pt_item_production_slot4 = new PT_ITEM_PRODUCTION_SLOT();
         pt_item_production_slot4.slotindex = -1;
         pt_item_production_slot4.usablecount = 100;
         res_item_production_info.infos.add(pt_item_production_slot4);
         res_item_production_info.transId = req_item_production_info.transId;
         MessagePusher.pushMessage((IoSession)session, res_item_production_info);
      } else {
         this.logger.error("UNREACH==slottype=" + req_item_production_info.slottype);
      }

   }

   @RequestMapping
   public void REQ_ITEM_PRODUCTION_REGISTER(IoSession session, REQ_ITEM_PRODUCTION_REGISTER req_item_production_register) {
      Account account = SessionUtils.getAccountBySession(session);
      Role role = SessionUtils.getRoleBySession(session);
      ConsumableBox consumableBox = role.getConsumableBox();
      MoneyBox moneyBox = role.getMoneyBox();
      RES_ITEM_PRODUCTION_REGISTER res_item_production_register = new RES_ITEM_PRODUCTION_REGISTER();
      Integer slotindex = req_item_production_register.slotindex;
      Integer count = req_item_production_register.count;
      Integer recipeindex = req_item_production_register.recipeindex;
      StackableModel stackableModel = (StackableModel)DataCache.RECIPE_INDEX.get(recipeindex);
      if (stackableModel == null) {
         this.logger.error("UNREACH==REQ_ITEM_PRODUCTION_REGISTER.recipeindex=" + recipeindex);
      } else {
         int index = stackableModel.index;
         int price = stackableModel.price;
         ConsumeItem consumeItem = (ConsumeItem)ItemDataPool.consumeItemMap.get(index);
         if (consumeItem == null) {
            this.logger.error("UNREACH==consume-index=" + index);
         } else {
            res_item_production_register.slotindex = slotindex;
            res_item_production_register.recipeindex = recipeindex;
            res_item_production_register.state = 2;
            res_item_production_register.materialitems = new ArrayList();
            MakeModel makeModel = (MakeModel)DataCache.MAKE_OTHER_MAP.get(index);

            for(Map.Entry<Integer, Integer> entry : makeModel.needs.entrySet()) {
               int needIndex = (Integer)entry.getKey();
               int needCnt = (Integer)entry.getValue();
               PT_CONSUME_ITEMS pt_consume_items = new PT_CONSUME_ITEMS();
               pt_consume_items.itemindex = needIndex;
               pt_consume_items.count = needCnt * count;
               res_item_production_register.materialitems.add(pt_consume_items);
            }

            List<Integer> indexList = new ArrayList();
            indexList.add(index);
            res_item_production_register.rewards = this.bagService.getRewardsMakeStackable(account, role, indexList);
            res_item_production_register.rewards.currency = new PT_CURRENCY_REWARD_INFO();
            res_item_production_register.rewards.currency.currency = new ArrayList();
            PT_MONEY_ITEM ptMoneyItem = new PT_MONEY_ITEM();
            moneyBox.submoney(makeModel.price);
            ptMoneyItem.count = moneyBox.getMoneyItem(0).count;
            res_item_production_register.rewards.currency.currency.add(ptMoneyItem);
            role.setMoneyBox(moneyBox);
            account.save();
            role.save();
            res_item_production_register.transId = req_item_production_register.transId;
            MessagePusher.pushMessage((IoSession)session, res_item_production_register);
         }
      }
   }

   private void addStackable(List<PT_STACKABLE> list, int index, boolean bind, int count, long acquisitiontime) {
      PT_STACKABLE ptStackable = new PT_STACKABLE();
      ptStackable.index = index;
      if (bind) {
         ptStackable.bind = bind;
      }

      if (count != 0) {
         ptStackable.count = count;
      }

      list.add(ptStackable);
   }

   @RequestMapping
   public void REQ_ITEM_COMBINE(IoSession session, REQ_ITEM_COMBINE req_item_combine) {
      Account account = SessionUtils.getAccountBySession(session);
      Role role = SessionUtils.getRoleBySession(session);
      EquipBox equipBox = role.getEquipBox();
      AvatarBox avatarBox = role.getAvatarBox();
      MoneyBox moneyBox = role.getMoneyBox();
      MaterialBox materialBox = role.getMaterialBox();
      int index = req_item_combine.index;
      List<PT_MATERIAL_ITEM> consumeEqus = req_item_combine.materialitems;
      int count = req_item_combine.count;
      RES_ITEM_COMBINE res_item_combine = new RES_ITEM_COMBINE();
      Equip equip = (Equip)EquipDataPool.index2Equip.get(index);
      if (equip == null) {
         this.logger.error("UNREACH==equNULL==REQ_ITEM_COMBINE==index=" + index);
      } else {
         int equType = EquipDataPool.getEquType(equip.getEquiptype());
         if (equType == 100) {
            PT_EQUIP ptEquip = EquipDataPool.createEquip(index);
            res_item_combine.equip = ptEquip;
            equipBox.addEquip(res_item_combine.equip);
            role.setEquipBox(equipBox);
         } else {
            if (equType != 1) {
               this.logger.error("UNREACH==REQ_ITEM_COMBINE==index=" + index);
               return;
            }

            res_item_combine.avatar = EquipDataPool.createAvatar(index);
            avatarBox.addAvatar(res_item_combine.avatar);
            role.setAvatarBox(avatarBox);
         }

         res_item_combine.removeitems = new PT_REMOVEITEMS();
         res_item_combine.removeitems.materialitems = new ArrayList();
         List<RemoveItem> needStackableList = new ArrayList();
         MakeModel makeModel = (MakeModel)DataCache.MAKE_EQU_MAP.get(index);
         int price = makeModel.price;

         for(Map.Entry<Integer, Integer> entry : makeModel.needs.entrySet()) {
            int needIndex = (Integer)entry.getKey();
            int needCnt = (Integer)entry.getValue();
            Equip needEqu = (Equip)EquipDataPool.index2Equip.get(needIndex);
            if (needEqu == null) {
               RemoveItem removeItem = new RemoveItem();
               removeItem.index = needIndex;
               removeItem.cnt = needCnt;
               needStackableList.add(removeItem);
            }
         }

         res_item_combine.removeitems = this.bagService.getRemoveitems(account, role, needStackableList);
         if (!Util.isEmpty(consumeEqus)) {
            this.bagService.subEquList(role, consumeEqus);
         }

         moneyBox.submoney(price);
         role.setMoneyBox(moneyBox);
         role.save();
         account.save();
         res_item_combine.transId = req_item_combine.transId;
         MessagePusher.pushMessage((IoSession)session, res_item_combine);
      }
   }

   @RequestMapping
   public void REQ_ITEM_DISJOINT(IoSession session, REQ_ITEM_DISJOINT req_item_disjoint) {
      Role role = SessionUtils.getRoleBySession(session);
      Account account = SessionUtils.getAccountBySession(session);
      MoneyBox moneyBox = role.getMoneyBox();
      AccountMoneyBox accountMoneyBox = account.getMoneyBox();
      EquipBox equipBox = role.getEquipBox();
      MaterialBox materialBox = role.getMaterialBox();
      List<PT_ITEM_DISJOINT_GUID> list = req_item_disjoint.guids;
      List<Equip> targetEqus = new ArrayList();

      for(PT_ITEM_DISJOINT_GUID pt_item_disjoint_guid : list) {
         long guid = pt_item_disjoint_guid.guid;
         PT_EQUIP pt_equip = equipBox.getEquip(guid);
         int index = pt_equip.index;
         Equip equip = (Equip)EquipDataPool.index2Equip.get(index);
         if (equip != null) {
            targetEqus.add(equip);
         }
      }

      RES_ITEM_DISJOINT res_item_disjoint = new RES_ITEM_DISJOINT();
      res_item_disjoint.guids = list;
      List<PT_STACKABLE> materialList = new ArrayList();
      int wsxjkIndex = 2013100883;
      int wsxjkCnt = 0;
      int commonIndex = 2013100889;
      int commonCnt = 0;
      int uncommonIndex = 2013100890;
      int uncommonCnt = 0;
      int rareIndex = 2013100887;
      int rareCnt = 0;
      int uniqueIndex = 2013100888;
      int uniqueCnt = 0;
      int epicIndex = 2013100707;
      int epicCnt = 0;
      int tpAvatarIndex = 2013103571;
      int tpAvatarCnt = 0;
      int tpWeaponIndex = 1990000349;
      int tpWeaponCnt = 0;
      long acquisitiontime = TimeUtil.currS();

      for(Equip equip : targetEqus) {
         int rarity = equip.getRarity();
         int minLevel = equip.getMinlevel();
         int equType = equip.getEquiptype();
         if (rarity == 0) {
            if (minLevel >= 50) {
               commonCnt += 5;
            } else {
               ++commonCnt;
            }

            wsxjkCnt += 10;
         } else if (rarity == 1) {
            if (minLevel >= 50) {
               uncommonCnt += 5;
            } else {
               ++uncommonCnt;
            }

            wsxjkCnt += 20;
         } else if (rarity == 2) {
            if (minLevel >= 50) {
               rareCnt += 5;
            } else {
               ++rareCnt;
            }

            wsxjkCnt += 30;
         } else if (rarity == 3) {
            if (minLevel >= 50) {
               uniqueCnt += 5;
            } else {
               ++uniqueCnt;
            }

            wsxjkCnt += 40;
         } else if (rarity == 4 && minLevel < 55) {
            if (minLevel >= 50) {
               epicCnt += 5;
            } else {
               ++epicCnt;
            }

            wsxjkCnt += 50;
         } else if (rarity == 4 && minLevel >= 55) {
            if (EquipDataPool.getEquType(equType) == 1) {
               ++tpAvatarCnt;
            } else {
               ++tpWeaponCnt;
            }

            wsxjkCnt += 50;
         } else {
            this.logger.error("UNREACH==分解装备==rarity=" + rarity);
         }
      }

      if (wsxjkCnt > 0) {
         PT_STACKABLE ptStackableWs = new PT_STACKABLE();
         ptStackableWs.index = wsxjkIndex;
         ptStackableWs.count = wsxjkCnt;
         ptStackableWs.acquisitiontime = acquisitiontime;
         materialList.add(ptStackableWs);
         materialBox.addCnt(wsxjkIndex, wsxjkCnt, acquisitiontime);
      }

      if (commonCnt > 0) {
         PT_STACKABLE ptStackableCommon = new PT_STACKABLE();
         ptStackableCommon.index = commonIndex;
         ptStackableCommon.count = commonCnt;
         ptStackableCommon.acquisitiontime = acquisitiontime;
         materialList.add(ptStackableCommon);
         materialBox.addCnt(commonIndex, commonCnt, acquisitiontime);
      }

      if (uncommonCnt > 0) {
         PT_STACKABLE ptStackableUncommon = new PT_STACKABLE();
         ptStackableUncommon.index = uncommonIndex;
         ptStackableUncommon.count = uncommonCnt;
         ptStackableUncommon.acquisitiontime = acquisitiontime;
         materialList.add(ptStackableUncommon);
         materialBox.addCnt(uncommonIndex, uncommonCnt, acquisitiontime);
      }

      if (rareCnt > 0) {
         PT_STACKABLE ptStackableRare = new PT_STACKABLE();
         ptStackableRare.index = rareIndex;
         ptStackableRare.count = rareCnt;
         ptStackableRare.acquisitiontime = acquisitiontime;
         materialList.add(ptStackableRare);
         materialBox.addCnt(rareIndex, rareCnt, acquisitiontime);
      }

      if (uniqueCnt > 0) {
         PT_STACKABLE ptStackableUnique = new PT_STACKABLE();
         ptStackableUnique.index = uniqueIndex;
         ptStackableUnique.count = uniqueCnt;
         ptStackableUnique.acquisitiontime = acquisitiontime;
         materialList.add(ptStackableUnique);
         materialBox.addCnt(uniqueIndex, uniqueCnt, acquisitiontime);
      }

      if (epicCnt > 0) {
         PT_STACKABLE ptStackableEpic = new PT_STACKABLE();
         ptStackableEpic.index = epicIndex;
         ptStackableEpic.count = epicCnt;
         ptStackableEpic.acquisitiontime = acquisitiontime;
         materialList.add(ptStackableEpic);
         materialBox.addCnt(epicIndex, epicCnt, acquisitiontime);
      }

      if (tpAvatarCnt > 0) {
         PT_STACKABLE ptStackableTpAvatar = new PT_STACKABLE();
         ptStackableTpAvatar.index = tpAvatarIndex;
         ptStackableTpAvatar.count = tpAvatarCnt;
         ptStackableTpAvatar.acquisitiontime = acquisitiontime;
         materialList.add(ptStackableTpAvatar);
         materialBox.addCnt(tpAvatarIndex, tpAvatarCnt, acquisitiontime);
      }

      if (tpWeaponCnt > 0) {
         PT_STACKABLE ptStackableTpWeapon = new PT_STACKABLE();
         ptStackableTpWeapon.index = tpWeaponIndex;
         ptStackableTpWeapon.count = tpWeaponCnt;
         ptStackableTpWeapon.acquisitiontime = acquisitiontime;
         materialList.add(ptStackableTpWeapon);
         materialBox.addCnt(tpWeaponIndex, tpWeaponCnt, acquisitiontime);
      }

      for(PT_ITEM_DISJOINT_GUID pt_item_disjoint_guid : list) {
         role.getEquipBox().removeEquip(pt_item_disjoint_guid.guid);
      }

      res_item_disjoint.rewards = new PT_CONTENTS_REWARD_INFO();
      res_item_disjoint.rewards.items = new PT_ITEM_REWARD_INFO();
      res_item_disjoint.rewards.items.inventory = new PT_ITEMS();
      res_item_disjoint.rewards.items.inventory.materialitems = materialList;
      res_item_disjoint.rewards.currency = new PT_CURRENCY_REWARD_INFO();
      res_item_disjoint.rewards.currency.currency = new ArrayList();
      PT_MONEY_ITEM ptMoneyItem = new PT_MONEY_ITEM();
      ptMoneyItem.count = moneyBox.getMoneyCnt();
      res_item_disjoint.rewards.currency.currency.add(ptMoneyItem);
      res_item_disjoint.rewards.paymentcurrency = new PT_CURRENCY_REWARD_INFO();
      res_item_disjoint.rewards.paymentcurrency.currency = new ArrayList();
      res_item_disjoint.rewards.paymentcurrency.currency.add(new PT_MONEY_ITEM());
      role.setMaterialBox(materialBox);
      role.save();
      res_item_disjoint.transId = req_item_disjoint.transId;
      MessagePusher.pushMessage((IoSession)session, res_item_disjoint);
   }

   @RequestMapping
   public void REQ_CARD_COMPOSE(IoSession session, REQ_CARD_COMPOSE req_card_compose) {
      Role role = SessionUtils.getRoleBySession(session);
      MoneyBox moneyBox = role.getMoneyBox();
      CardBox cardBox = role.getCardBox();
      List<PT_CARD_COMPOSE> usercardList = req_card_compose.usercardlist;
      int rarity = -1;
      int cnt = 0;

      for(PT_CARD_COMPOSE usercard : usercardList) {
         if (rarity == -1) {
            ConsumeItem consumeItem = (ConsumeItem)ItemDataPool.consumeItemMap.get(usercard.index);
            rarity = consumeItem.getRarity();
         }

         cnt += usercard.count;
         cardBox.removeCard(usercard.index, usercard.count);
      }

      int q = 0;
      if (rarity == 0) {
         if (cnt == 2) {
            q = 20;
         } else if (cnt == 3) {
            q = 40;
         } else if (cnt == 4) {
            q = 80;
         } else {
            this.logger.error("UNREACH==usercardlist.cnt=" + cnt);
         }
      } else if (rarity == 1) {
         if (cnt == 2) {
            q = 4;
         } else if (cnt == 3) {
            q = 8;
         } else if (cnt == 4) {
            q = 16;
         } else {
            this.logger.error("UNREACH==usercardlist.cnt=" + cnt);
         }
      } else if (rarity == 2) {
         if (cnt == 2) {
            q = 2;
         } else if (cnt == 3) {
            q = 3;
         } else if (cnt == 4) {
            q = 4;
         } else {
            this.logger.error("UNREACH==usercardlist.cnt=" + cnt);
         }
      } else if (rarity == 3) {
         if (cnt == 2) {
            q = 0;
         } else if (cnt == 3) {
            q = 0;
         } else if (cnt == 4) {
            q = 0;
         } else {
            this.logger.error("UNREACH==usercardlist.cnt=" + cnt);
         }
      } else {
         this.logger.error("UNREACH==卡片合成==rarity=" + rarity);
      }

      int r = Util.randInt(0, 100);
      int resCardIndex = -1;
      if (r < q) {
         List<Integer> indexList = (List)DataCache.CARD_RARITY_MAP.get(rarity + 1);
         int randomIndex = Util.randIndex(indexList.size());
         resCardIndex = (Integer)indexList.get(randomIndex);
      } else {
         List<Integer> indexList = (List)DataCache.CARD_RARITY_MAP.get(rarity);
         int randomIndex = Util.randIndex(indexList.size());
         resCardIndex = (Integer)indexList.get(randomIndex);
      }

      cardBox.addCard(resCardIndex, 1, TimeUtil.currS());
      RES_CARD_COMPOSE res_card_compose = new RES_CARD_COMPOSE();
      res_card_compose.usercardlist = req_card_compose.usercardlist;
      res_card_compose.card = new ArrayList();
      PT_CARD_COMPOSE ptCardCompose = new PT_CARD_COMPOSE();
      ptCardCompose.index = resCardIndex;
      ptCardCompose.count = 1;
      res_card_compose.card.add(ptCardCompose);
      res_card_compose.currency = new ArrayList();
      int costMoney = 0;
      int commonWeight = 1;
      int uncommonWeight = 2;
      int rareWeight = 4;
      int uniqueWeight = 10;
      int basevalue = 10000;
      if (rarity == 0) {
         costMoney = (int)((float)((double)((float)((double)((float)(basevalue * cnt * commonWeight)) * 0.1)) + 0.9));
      } else if (rarity == 1) {
         costMoney = (int)((float)((double)((float)((double)((float)(basevalue * cnt * uncommonWeight)) * 0.1)) + 0.9));
      } else if (rarity == 2) {
         costMoney = (int)((float)((double)((float)((double)((float)(basevalue * cnt * rareWeight)) * 0.1)) + 0.9));
      } else if (rarity == 3) {
         costMoney = (int)((float)((double)((float)((double)((float)(basevalue * cnt * uniqueWeight)) * 0.1)) + 0.9));
      } else {
         this.logger.error("UNREACH==卡片合成金币消耗==rarity=" + rarity);
      }

      moneyBox.submoney(costMoney);
      PT_MONEY_ITEM ptMoneyItem = new PT_MONEY_ITEM();
      ptMoneyItem.count = moneyBox.getMoneyCnt();
      res_card_compose.currency.add(ptMoneyItem);
      role.setMoneyBox(moneyBox);
      role.setCardBox(cardBox);
      role.save();
      res_card_compose.transId = req_card_compose.transId;
      MessagePusher.pushMessage((IoSession)session, res_card_compose);
   }
}
