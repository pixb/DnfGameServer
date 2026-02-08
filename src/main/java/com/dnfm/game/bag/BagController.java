package com.dnfm.game.bag;

import com.alibaba.fastjson.JSON;
import com.dnfm.common.thread.IdGenerator;
import com.dnfm.common.utils.Util;
import com.dnfm.game.bag.model.AccShopInfoBox;
import com.dnfm.game.bag.model.AdStorageBox;
import com.dnfm.game.bag.model.CardBox;
import com.dnfm.game.bag.model.CharStorageBox;
import com.dnfm.game.bag.model.ConsumableBox;
import com.dnfm.game.bag.model.EmblemBox;
import com.dnfm.game.bag.model.MoneyBox;
import com.dnfm.game.bag.model.RePurStoItemBox;
import com.dnfm.game.bag.serveice.BagService;
import com.dnfm.game.config.ConsumeItem;
import com.dnfm.game.config.Equip;
import com.dnfm.game.config.Skin;
import com.dnfm.game.config.itemShop;
import com.dnfm.game.equip.EquipDataPool;
import com.dnfm.game.item.ItemDataPool;
import com.dnfm.game.item.model.giftContent;
import com.dnfm.game.mail.model.MailItem;
import com.dnfm.game.role.model.Account;
import com.dnfm.game.role.model.Role;
import com.dnfm.game.role.service.AccountService;
import com.dnfm.game.role.service.RoleService;
import com.dnfm.game.utils.DateUtils;
import com.dnfm.game.utils.TimeUtil;
import com.dnfm.mina.annotation.RequestMapping;
import com.dnfm.mina.cache.SessionUtils;
import com.dnfm.mina.message.MessagePusher;
import com.dnfm.mina.protobuf.DEF_ITEM_CONSUMABLE;
import com.dnfm.mina.protobuf.NOTIFY_ADVENTUREBOOK_UPDATE_CONDITION;
import com.dnfm.mina.protobuf.PT_ADVENTUREBOOK_OPEN_CONDITION;
import com.dnfm.mina.protobuf.PT_ARTIFACT;
import com.dnfm.mina.protobuf.PT_AVATAR_ITEM;
import com.dnfm.mina.protobuf.PT_CERA_SHOP_BUY;
import com.dnfm.mina.protobuf.PT_CERA_SHOP_INFO;
import com.dnfm.mina.protobuf.PT_CONTENTS_REWARD_INFO;
import com.dnfm.mina.protobuf.PT_CREATURE;
import com.dnfm.mina.protobuf.PT_CURRENCY_REWARD_INFO;
import com.dnfm.mina.protobuf.PT_EQUIP;
import com.dnfm.mina.protobuf.PT_EQUIPPED;
import com.dnfm.mina.protobuf.PT_ITEMS;
import com.dnfm.mina.protobuf.PT_ITEM_REWARD_INFO;
import com.dnfm.mina.protobuf.PT_ITEM_SELL;
import com.dnfm.mina.protobuf.PT_ITEM_USE_RESULT;
import com.dnfm.mina.protobuf.PT_MONEY_ITEM;
import com.dnfm.mina.protobuf.PT_REMOVEITEMS;
import com.dnfm.mina.protobuf.PT_REPURCHASE_EQUIP;
import com.dnfm.mina.protobuf.PT_REPURCHASE_STACKABLE;
import com.dnfm.mina.protobuf.PT_REPURCHASE_STORAGE_LIST;
import com.dnfm.mina.protobuf.PT_SHOP_BUY_COUNT;
import com.dnfm.mina.protobuf.PT_STACKABLE;
import com.dnfm.mina.protobuf.REQ_ADVENTUREBOOK_UPDATE_CONDITION;
import com.dnfm.mina.protobuf.REQ_ADVENTURE_STORAGE_LIST;
import com.dnfm.mina.protobuf.REQ_CERA_SHOP_BUY;
import com.dnfm.mina.protobuf.REQ_CERA_SHOP_BUY_INFO;
import com.dnfm.mina.protobuf.REQ_CHAR_STORAGE_LIST;
import com.dnfm.mina.protobuf.REQ_INVEN_EXTEND;
import com.dnfm.mina.protobuf.REQ_ITEM_USE;
import com.dnfm.mina.protobuf.REQ_NPC_SHOP_ITEM_SELL;
import com.dnfm.mina.protobuf.REQ_REPURCHASE_ITEM;
import com.dnfm.mina.protobuf.REQ_REPURCHASE_ITEM_STORAGE_LIST;
import com.dnfm.mina.protobuf.REQ_SEND_INVEN;
import com.dnfm.mina.protobuf.REQ_SEND_STORAGE;
import com.dnfm.mina.protobuf.REQ_STORAGE_EXTEND;
import com.dnfm.mina.protobuf.RES_ADVENTUREBOOK_UPDATE_CONDITION;
import com.dnfm.mina.protobuf.RES_ADVENTURE_STORAGE_LIST;
import com.dnfm.mina.protobuf.RES_CERA_SHOP_BUY;
import com.dnfm.mina.protobuf.RES_CERA_SHOP_BUY_INFO;
import com.dnfm.mina.protobuf.RES_CHAR_STORAGE_LIST;
import com.dnfm.mina.protobuf.RES_INVEN_EXTEND;
import com.dnfm.mina.protobuf.RES_ITEM_USE;
import com.dnfm.mina.protobuf.RES_NPC_SHOP_ITEM_SELL;
import com.dnfm.mina.protobuf.RES_REPURCHASE_ITEM;
import com.dnfm.mina.protobuf.RES_REPURCHASE_ITEM_STORAGE_LIST;
import com.dnfm.mina.protobuf.RES_SEND_INVEN;
import com.dnfm.mina.protobuf.RES_SEND_STORAGE;
import com.dnfm.mina.protobuf.RES_STORAGE_EXTEND;
import com.dnfm.mina.protobuf.SendItem_GuidInfo;
import com.dnfm.mina.protobuf.SendItem_Info;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class BagController {
   Logger logger = LoggerFactory.getLogger(BagController.class);
   @Autowired
   BagService bagService;
   @Autowired
   AccountService accountService;
   @Autowired
   RoleService roleService;

   @RequestMapping
   public void REQ_NPC_SHOP_ITEM_SELL(IoSession session, REQ_NPC_SHOP_ITEM_SELL req_npc_shop_item_sell) {
      Role role = SessionUtils.getRoleBySession(session);
      MoneyBox moneyBox = role.getMoneyBox();
      RePurStoItemBox rePurStoItemBox = role.getRePurStoItem();
      int type = ((PT_ITEM_SELL)req_npc_shop_item_sell.item.get(0)).type;
      RES_NPC_SHOP_ITEM_SELL res_npc_shop_item_sell = new RES_NPC_SHOP_ITEM_SELL();
      res_npc_shop_item_sell.item = req_npc_shop_item_sell.item;
      int totalPrice = -1;
      if (((PT_ITEM_SELL)req_npc_shop_item_sell.item.get(0)).guids != null && ((PT_ITEM_SELL)req_npc_shop_item_sell.item.get(0)).guids.size() > 0) {
         totalPrice = this.bagService.calcGuidsPrice(type, role, req_npc_shop_item_sell.item);
         if (totalPrice == -1) {
            this.logger.error("UNREACH==REQ_NPC_SHOP_ITEM_SELL: calcGuidsPrice error==req==" + JSON.toJSONString(req_npc_shop_item_sell));
            return;
         }

         res_npc_shop_item_sell.info = new PT_CONTENTS_REWARD_INFO();
         res_npc_shop_item_sell.info.paymentcurrency = new PT_CURRENCY_REWARD_INFO();
         res_npc_shop_item_sell.info.paymentcurrency.currency = new ArrayList();
         moneyBox.addmoney(totalPrice);
         PT_MONEY_ITEM ptMoneyItem = moneyBox.getMoneyItem(0);
         PT_MONEY_ITEM ptMoneyItemPay = new PT_MONEY_ITEM();
         ptMoneyItemPay.count = totalPrice;
         res_npc_shop_item_sell.info.paymentcurrency.currency.add(ptMoneyItemPay);
         res_npc_shop_item_sell.info.currency = new PT_CURRENCY_REWARD_INFO();
         res_npc_shop_item_sell.info.currency.currency = new ArrayList();
         res_npc_shop_item_sell.info.currency.currency.add(ptMoneyItem);
      } else {
         totalPrice = this.bagService.calcStackablesPrice(role, req_npc_shop_item_sell.item);
         if (totalPrice == -1) {
            this.logger.error("UNREACH==REQ_NPC_SHOP_ITEM_SELL: calcGuidsPrice error==req==" + JSON.toJSONString(req_npc_shop_item_sell));
            return;
         }

         res_npc_shop_item_sell.info = new PT_CONTENTS_REWARD_INFO();
         res_npc_shop_item_sell.info.paymentcurrency = new PT_CURRENCY_REWARD_INFO();
         res_npc_shop_item_sell.info.paymentcurrency.currency = new ArrayList();
         moneyBox.addmoney(totalPrice);
         PT_MONEY_ITEM ptMoneyItem = moneyBox.getMoneyItem(0);
         PT_MONEY_ITEM ptMoneyItemPay = new PT_MONEY_ITEM();
         ptMoneyItemPay.count = totalPrice;
         res_npc_shop_item_sell.info.paymentcurrency.currency.add(ptMoneyItemPay);
         res_npc_shop_item_sell.info.currency = new PT_CURRENCY_REWARD_INFO();
         res_npc_shop_item_sell.info.currency.currency = new ArrayList();
         res_npc_shop_item_sell.info.currency.currency.add(ptMoneyItem);
      }

      role.save();
      res_npc_shop_item_sell.transId = req_npc_shop_item_sell.transId;
      MessagePusher.pushMessage((IoSession)session, res_npc_shop_item_sell);
   }

   @RequestMapping
   public void REQ_REPURCHASE_ITEM(IoSession session, REQ_REPURCHASE_ITEM req_repurchase_item) {
      RES_REPURCHASE_ITEM res_repurchase_item = new RES_REPURCHASE_ITEM();
      Role role = SessionUtils.getRoleBySession(session);
      boolean success = this.bagService.repurchaseItems(role, req_repurchase_item.storageguids);
      if (success) {
         res_repurchase_item.storageguids = req_repurchase_item.storageguids;
         role.save();
      } else {
         res_repurchase_item.error = 1;
      }

      res_repurchase_item.transId = req_repurchase_item.transId;
      MessagePusher.pushMessage((IoSession)session, res_repurchase_item);
   }

   @RequestMapping
   public void ReqRepurchaseItemStorageList(IoSession session, REQ_REPURCHASE_ITEM_STORAGE_LIST reqRepurchaseItemStorageList) {
      Role role = SessionUtils.getRoleBySession(session);
      RES_REPURCHASE_ITEM_STORAGE_LIST resRepurchaseItemStorageList0 = new RES_REPURCHASE_ITEM_STORAGE_LIST();
      RePurStoItemBox rePurStoItemBox = role.getRePurStoItem();
      resRepurchaseItemStorageList0.storageitems = new PT_REPURCHASE_STORAGE_LIST();
      List<PT_REPURCHASE_EQUIP> equipitems = rePurStoItemBox.getEquipitems();
      List<PT_REPURCHASE_EQUIP> titleitems = rePurStoItemBox.getTitleitems();
      List<PT_REPURCHASE_STACKABLE> consumeitems = rePurStoItemBox.getConsumeitems();
      List<PT_REPURCHASE_STACKABLE> materialitems = rePurStoItemBox.getMaterialitems();
      List<PT_REPURCHASE_STACKABLE> carditems = rePurStoItemBox.getCarditems();
      List<PT_REPURCHASE_STACKABLE> emblemitems = rePurStoItemBox.getEmblemitems();
      if (!Util.isEmpty(equipitems)) {
         resRepurchaseItemStorageList0.storageitems.equipitems = equipitems;
      }

      if (!Util.isEmpty(titleitems)) {
         resRepurchaseItemStorageList0.storageitems.titleitems = titleitems;
      }

      if (!Util.isEmpty(consumeitems)) {
         resRepurchaseItemStorageList0.storageitems.consumeitems = consumeitems;
      }

      if (!Util.isEmpty(materialitems)) {
         resRepurchaseItemStorageList0.storageitems.materialitems = materialitems;
      }

      if (!Util.isEmpty(carditems)) {
         resRepurchaseItemStorageList0.storageitems.carditems = carditems;
      }

      if (!Util.isEmpty(emblemitems)) {
         resRepurchaseItemStorageList0.storageitems.emblemitems = emblemitems;
      }

      resRepurchaseItemStorageList0.transId = reqRepurchaseItemStorageList.transId;
      MessagePusher.pushMessage((IoSession)session, resRepurchaseItemStorageList0);
   }

   @RequestMapping
   public void REQ_ADVENTUREBOOK_UPDATE_CONDITION(IoSession session, REQ_ADVENTUREBOOK_UPDATE_CONDITION req_adventurebook_update_condition) {
      String authKey = req_adventurebook_update_condition.authkey;
      List<Integer> list = req_adventurebook_update_condition.conditionindexes;
      RES_ADVENTUREBOOK_UPDATE_CONDITION res_adventurebook_update_condition = new RES_ADVENTUREBOOK_UPDATE_CONDITION();
      res_adventurebook_update_condition.transId = req_adventurebook_update_condition.transId;
      MessagePusher.pushMessage((IoSession)session, res_adventurebook_update_condition);
      NOTIFY_ADVENTUREBOOK_UPDATE_CONDITION notify_adventurebook_update_condition = new NOTIFY_ADVENTUREBOOK_UPDATE_CONDITION();
      notify_adventurebook_update_condition.conditioninfos = new ArrayList();

      for(Integer i : list) {
         PT_ADVENTUREBOOK_OPEN_CONDITION pt_adventurebook_open_condition = new PT_ADVENTUREBOOK_OPEN_CONDITION();
         pt_adventurebook_open_condition.cindex = i;
         pt_adventurebook_open_condition.cstate = 1;
         notify_adventurebook_update_condition.conditioninfos.add(pt_adventurebook_open_condition);
      }

      MessagePusher.pushMessage((IoSession)session, notify_adventurebook_update_condition);
   }

   @RequestMapping
   public void ReqSendStorage(IoSession session, REQ_SEND_STORAGE reqSendStorage) {
      RES_SEND_STORAGE resSendStorage = new RES_SEND_STORAGE();
      List<SendItem_GuidInfo> guids = reqSendStorage.guids;
      List<SendItem_Info> indexes = reqSendStorage.indexes;
      Role role = SessionUtils.getRoleBySession(session);
      if (reqSendStorage.type == 1) {
         if (reqSendStorage.storage == null) {
            for(SendItem_GuidInfo guidInfo : guids) {
               long guid = guidInfo.guid;
               PT_EQUIP ptEquip = role.getEquipBox().getEquip(guid);
               if (ptEquip == null) {
                  ptEquip = role.getTitleBox().getTitle(guid);
                  if (ptEquip == null) {
                     PT_AVATAR_ITEM ptAvatarItem = role.getAvatarBox().getAvatar(guid);
                     if (ptAvatarItem != null) {
                        role.getAvatarBox().remove(guid);
                        role.getCharStorageBox().getAvataritems().add(ptAvatarItem);
                     }
                  } else {
                     role.getTitleBox().removeTitle(guid);
                     role.getCharStorageBox().getTitleitems().add(ptEquip);
                  }
               } else {
                  role.getEquipBox().removeEquip(guid);
                  role.getCharStorageBox().getEquipitems().add(ptEquip);
               }
            }
         } else {
            Account account = SessionUtils.getAccountBySession(session);

            for(SendItem_GuidInfo guidInfo : guids) {
               long guid = guidInfo.guid;
               PT_EQUIP ptEquip = role.getEquipBox().getEquip(guid);
               if (ptEquip == null) {
                  ptEquip = role.getTitleBox().getTitle(guid);
                  if (ptEquip == null) {
                     PT_AVATAR_ITEM ptAvatarItem = role.getAvatarBox().getAvatar(guid);
                     if (ptAvatarItem != null) {
                        role.getAvatarBox().remove(guid);
                        account.getAdStorageBox().getAvataritems().add(ptAvatarItem);
                     }
                  } else {
                     role.getTitleBox().removeTitle(guid);
                     account.getAdStorageBox().getTitleitems().add(ptEquip);
                  }
               } else {
                  role.getEquipBox().removeEquip(guid);
                  account.getAdStorageBox().getEquipitems().add(ptEquip);
               }
            }
         }
      } else if (reqSendStorage.type == 2) {
         if (reqSendStorage.storage == null) {
            for(SendItem_Info indexs : indexes) {
               if (role.getCharStorageBox().getMaterialitems().size() > 0) {
                  PT_STACKABLE ptStackable = role.getCharStorageBox().getMaterial(indexs.index);
                  if (ptStackable == null) {
                     ptStackable = new PT_STACKABLE();
                     ptStackable.index = indexs.index;
                     ptStackable.count = indexs.count;
                     role.getCharStorageBox().getMaterialitems().add(ptStackable);
                  } else {
                     ptStackable.count = ptStackable.count + indexs.count;
                  }
               } else {
                  PT_STACKABLE ptStackable = new PT_STACKABLE();
                  ptStackable.index = indexs.index;
                  ptStackable.count = indexs.count;
                  role.getCharStorageBox().getMaterialitems().add(ptStackable);
               }

               role.getMaterialBox().updateMaterialSub(indexs.index, indexs.count);
            }
         } else {
            Account account = SessionUtils.getAccountBySession(session);

            for(SendItem_Info indexs : indexes) {
               if (account.getAdStorageBox().getMaterialitems().size() > 0) {
                  PT_STACKABLE ptStackable = account.getAdStorageBox().getMaterial(indexs.index);
                  if (ptStackable == null) {
                     ptStackable = new PT_STACKABLE();
                     ptStackable.index = indexs.index;
                     ptStackable.count = indexs.count;
                     account.getAdStorageBox().getMaterialitems().add(ptStackable);
                  } else {
                     ptStackable.count = ptStackable.count + indexs.count;
                  }
               } else {
                  PT_STACKABLE ptStackable = new PT_STACKABLE();
                  ptStackable.index = indexs.index;
                  ptStackable.count = indexs.count;
                  account.getAdStorageBox().getMaterialitems().add(ptStackable);
               }

               role.getMaterialBox().updateMaterialSub(indexs.index, indexs.count);
            }

            account.save();
         }
      } else if (reqSendStorage.type == 4) {
         if (reqSendStorage.storage == null) {
            for(SendItem_Info indexs : indexes) {
               if (role.getCharStorageBox().getEmblemitems().size() > 0) {
                  PT_STACKABLE ptStackable = role.getCharStorageBox().getEmblem(indexs.index);
                  if (ptStackable == null) {
                     ptStackable = new PT_STACKABLE();
                     ptStackable.index = indexs.index;
                     ptStackable.count = indexs.count;
                     role.getCharStorageBox().getEmblemitems().add(ptStackable);
                  } else {
                     ptStackable.count = ptStackable.count + indexs.count;
                  }
               } else {
                  PT_STACKABLE ptStackable = new PT_STACKABLE();
                  ptStackable.index = indexs.index;
                  ptStackable.count = indexs.count;
                  role.getCharStorageBox().getEmblemitems().add(ptStackable);
               }

               role.getEmblemBox().updateEmblemSub(indexs.index, indexs.count);
            }
         } else {
            Account account = SessionUtils.getAccountBySession(session);

            for(SendItem_Info indexs : indexes) {
               if (account.getAdStorageBox().getEmblemitems().size() > 0) {
                  PT_STACKABLE ptStackable = account.getAdStorageBox().getEmblem(indexs.index);
                  if (ptStackable == null) {
                     ptStackable = new PT_STACKABLE();
                     ptStackable.index = indexs.index;
                     ptStackable.count = indexs.count;
                     account.getAdStorageBox().getEmblemitems().add(ptStackable);
                  } else {
                     ptStackable.count = ptStackable.count + indexs.count;
                  }
               } else {
                  PT_STACKABLE ptStackable = new PT_STACKABLE();
                  ptStackable.index = indexs.index;
                  ptStackable.count = indexs.count;
                  account.getAdStorageBox().getEmblemitems().add(ptStackable);
               }

               role.getEmblemBox().updateEmblemSub(indexs.index, indexs.count);
            }

            account.save();
         }
      } else if (reqSendStorage.type == 5) {
         if (reqSendStorage.storage == null) {
            for(SendItem_Info indexs : indexes) {
               if (role.getCharStorageBox().getCarditems().size() > 0) {
                  PT_STACKABLE ptStackable = role.getCharStorageBox().getCard(indexs.index);
                  if (ptStackable == null) {
                     ptStackable = new PT_STACKABLE();
                     ptStackable.index = indexs.index;
                     ptStackable.count = indexs.count;
                     role.getCharStorageBox().getCarditems().add(ptStackable);
                  } else {
                     ptStackable.count = ptStackable.count + indexs.count;
                  }
               } else {
                  PT_STACKABLE ptStackable = new PT_STACKABLE();
                  ptStackable.index = indexs.index;
                  ptStackable.count = indexs.count;
                  role.getCharStorageBox().getCarditems().add(ptStackable);
               }

               role.getCardBox().updateCardSub(indexs.index, indexs.count);
            }
         } else {
            Account account = SessionUtils.getAccountBySession(session);

            for(SendItem_Info indexs : indexes) {
               if (account.getAdStorageBox().getCarditems().size() > 0) {
                  PT_STACKABLE ptStackable = account.getAdStorageBox().getCard(indexs.index);
                  if (ptStackable == null) {
                     ptStackable = new PT_STACKABLE();
                     ptStackable.index = indexs.index;
                     ptStackable.count = indexs.count;
                     account.getAdStorageBox().getCarditems().add(ptStackable);
                  } else {
                     ptStackable.count = ptStackable.count + indexs.count;
                  }
               } else {
                  PT_STACKABLE ptStackable = new PT_STACKABLE();
                  ptStackable.index = indexs.index;
                  ptStackable.count = indexs.count;
                  account.getAdStorageBox().getCarditems().add(ptStackable);
               }

               role.getCardBox().updateCardSub(indexs.index, indexs.count);
            }

            account.save();
         }
      } else if (reqSendStorage.type == 10) {
         if (reqSendStorage.storage == null) {
            for(SendItem_Info indexs : indexes) {
               if (role.getCharStorageBox().getConsumeitems().size() > 0) {
                  PT_STACKABLE ptStackable = role.getCharStorageBox().getConsume(indexs.index);
                  if (ptStackable == null) {
                     ptStackable = new PT_STACKABLE();
                     ptStackable.index = indexs.index;
                     ptStackable.count = indexs.count;
                     role.getCharStorageBox().getConsumeitems().add(ptStackable);
                  } else {
                     ptStackable.count = ptStackable.count + indexs.count;
                  }
               } else {
                  PT_STACKABLE ptStackable = new PT_STACKABLE();
                  ptStackable.index = indexs.index;
                  ptStackable.count = indexs.count;
                  role.getCharStorageBox().getConsumeitems().add(ptStackable);
               }

               role.getConsumableBox().updateConsumeSub(indexs.index, indexs.count);
            }
         } else {
            Account account = SessionUtils.getAccountBySession(session);

            for(SendItem_Info indexs : indexes) {
               if (account.getAdStorageBox().getConsumeitems().size() > 0) {
                  PT_STACKABLE ptStackable = account.getAdStorageBox().getConsume(indexs.index);
                  if (ptStackable == null) {
                     ptStackable = new PT_STACKABLE();
                     ptStackable.index = indexs.index;
                     ptStackable.count = indexs.count;
                     account.getAdStorageBox().getConsumeitems().add(ptStackable);
                  } else {
                     ptStackable.count = ptStackable.count + indexs.count;
                  }
               } else {
                  PT_STACKABLE ptStackable = new PT_STACKABLE();
                  ptStackable.index = indexs.index;
                  ptStackable.count = indexs.count;
                  account.getAdStorageBox().getConsumeitems().add(ptStackable);
               }

               role.getConsumableBox().updateConsumeSub(indexs.index, indexs.count);
            }

            account.save();
         }
      }

      role.save();
      resSendStorage.transId = reqSendStorage.transId;
      MessagePusher.pushMessage((IoSession)session, resSendStorage);
   }

   @RequestMapping
   public void REQ_SEND_INVEN(IoSession session, REQ_SEND_INVEN reqSendInven) {
      RES_SEND_INVEN resSendInven = new RES_SEND_INVEN();
      Role role = SessionUtils.getRoleBySession(session);
      if (reqSendInven.storage == null) {
         if (reqSendInven.equipitems.size() > 0) {
            for(SendItem_GuidInfo equip : reqSendInven.equipitems) {
               PT_EQUIP ptEquip = role.getCharStorageBox().getEquipItem(equip.guid);
               role.getEquipBox().addEquip(ptEquip);
               role.getCharStorageBox().removeEquipItem(equip.guid);
            }
         }

         if (reqSendInven.consumeitems.size() > 0) {
            for(SendItem_Info equip : reqSendInven.consumeitems) {
               role.getConsumableBox().updateConsumeAdd(equip.index, equip.count);
               role.getCharStorageBox().subConsumeitems(equip.index, equip.count, false);
            }
         }

         if (reqSendInven.titleitems.size() > 0) {
            for(SendItem_GuidInfo equip : reqSendInven.titleitems) {
               PT_EQUIP ptEquip = role.getCharStorageBox().getTitileItem(equip.guid);
               role.getTitleBox().addTitle(ptEquip);
               role.getCharStorageBox().removeTitleItem(equip.guid);
            }
         }

         if (reqSendInven.avataritems.size() > 0) {
            for(SendItem_GuidInfo equip : reqSendInven.avataritems) {
               PT_AVATAR_ITEM ptAvatarItem = role.getCharStorageBox().getAvatar(equip.guid);
               role.getAvatarBox().addAvatar(ptAvatarItem);
               role.getCharStorageBox().removeAvatar(equip.guid);
            }
         }

         if (reqSendInven.materialitems.size() > 0) {
            for(SendItem_Info equip : reqSendInven.materialitems) {
               role.getMaterialBox().updateMaterialAdd(equip.index, equip.count);
               role.getCharStorageBox().subMaterial(equip.index, equip.count, false);
            }
         }

         if (reqSendInven.emblemitems.size() > 0) {
            for(SendItem_Info equip : reqSendInven.emblemitems) {
               role.getEmblemBox().updateEmblemAdd(equip.index, equip.count);
               role.getCharStorageBox().subEmblemitems(equip.index, equip.count);
            }
         }

         if (reqSendInven.carditems.size() > 0) {
            for(SendItem_Info equip : reqSendInven.carditems) {
               role.getCardBox().updateCardAdd(equip.index, equip.count);
               role.getCharStorageBox().subCarditems(equip.index, equip.count);
            }
         }

         if (reqSendInven.sdavataritems.size() > 0) {
            for(SendItem_GuidInfo equip : reqSendInven.sdavataritems) {
               PT_AVATAR_ITEM ptAvatarItem = role.getCharStorageBox().getAvatar(equip.guid);
               role.getAvatarBox().addAvatar(ptAvatarItem);
               role.getCharStorageBox().removeAvatar(equip.guid);
            }
         }
      } else {
         Account account = SessionUtils.getAccountBySession(session);
         if (reqSendInven.equipitems.size() > 0) {
            for(SendItem_GuidInfo equip : reqSendInven.equipitems) {
               PT_EQUIP ptEquip = role.getCharStorageBox().getEquipItem(equip.guid);
               role.getEquipBox().addEquip(ptEquip);
               account.getAdStorageBox().removeEquipItem(equip.guid);
            }
         }

         if (reqSendInven.consumeitems.size() > 0) {
            for(SendItem_Info equip : reqSendInven.consumeitems) {
               role.getConsumableBox().updateConsumeAdd(equip.index, equip.count);
               account.getAdStorageBox().subConsumeitems(equip.index, equip.count, false);
            }
         }

         if (reqSendInven.titleitems.size() > 0) {
            for(SendItem_GuidInfo equip : reqSendInven.titleitems) {
               PT_EQUIP ptEquip = role.getCharStorageBox().getTitileItem(equip.guid);
               role.getTitleBox().addTitle(ptEquip);
               account.getAdStorageBox().removeTitleItem(equip.guid);
            }
         }

         if (reqSendInven.avataritems.size() > 0) {
            for(SendItem_GuidInfo equip : reqSendInven.avataritems) {
               PT_AVATAR_ITEM ptAvatarItem = role.getCharStorageBox().getAvatar(equip.guid);
               role.getAvatarBox().addAvatar(ptAvatarItem);
               account.getAdStorageBox().removeAvatar(equip.guid);
            }
         }

         if (reqSendInven.materialitems.size() > 0) {
            for(SendItem_Info equip : reqSendInven.materialitems) {
               role.getMaterialBox().updateMaterialAdd(equip.index, equip.count);
               account.getAdStorageBox().subMaterial(equip.index, equip.count, false);
            }
         }

         if (reqSendInven.emblemitems.size() > 0) {
            for(SendItem_Info equip : reqSendInven.emblemitems) {
               role.getEmblemBox().updateEmblemAdd(equip.index, equip.count);
               account.getAdStorageBox().subEmblemitems(equip.index, equip.count);
            }
         }

         if (reqSendInven.carditems.size() > 0) {
            for(SendItem_Info equip : reqSendInven.carditems) {
               role.getCardBox().updateCardAdd(equip.index, equip.count);
               account.getAdStorageBox().subCarditems(equip.index, equip.count);
            }
         }

         if (reqSendInven.sdavataritems.size() > 0) {
            for(SendItem_GuidInfo equip : reqSendInven.sdavataritems) {
               PT_AVATAR_ITEM ptAvatarItem = role.getCharStorageBox().getAvatar(equip.guid);
               role.getAvatarBox().addAvatar(ptAvatarItem);
               account.getAdStorageBox().removeAvatar(equip.guid);
            }
         }
      }

      resSendInven.transId = reqSendInven.transId;
      MessagePusher.pushMessage((IoSession)session, resSendInven);
   }

   @RequestMapping
   public void ReqCharStorageList(IoSession session, REQ_CHAR_STORAGE_LIST reqCharStorageList) {
      Role role = SessionUtils.getRoleBySession(session);
      RES_CHAR_STORAGE_LIST resCharStorageList = new RES_CHAR_STORAGE_LIST();
      CharStorageBox charStorageBox = role.getCharStorageBox();
      resCharStorageList.equipitems = new ArrayList();
      resCharStorageList.consumeitems = new ArrayList();
      resCharStorageList.titleitems = new ArrayList();
      resCharStorageList.flagitems = new ArrayList();
      resCharStorageList.materialitems = new ArrayList();
      resCharStorageList.emblemitems = new ArrayList();
      resCharStorageList.carditems = new ArrayList();
      resCharStorageList.avataritems = new ArrayList();
      resCharStorageList.cartifactitems = new ArrayList();
      resCharStorageList.epicpieceitems = new ArrayList();
      resCharStorageList.creatureitems = new ArrayList();
      int sindex = 0;
      if (charStorageBox.getEquipitems().size() > 0) {
         for(PT_EQUIP equip : charStorageBox.getEquipitems()) {
            equip.sindex = sindex;
            resCharStorageList.equipitems.add(equip);
            ++sindex;
         }
      }

      if (charStorageBox.getConsumeitems().size() > 0) {
         for(PT_STACKABLE equip : charStorageBox.getConsumeitems()) {
            equip.sindex = sindex;
            resCharStorageList.consumeitems.add(equip);
            ++sindex;
         }
      }

      if (charStorageBox.getTitleitems().size() > 0) {
         for(PT_EQUIP equip : charStorageBox.getTitleitems()) {
            equip.sindex = sindex;
            resCharStorageList.titleitems.add(equip);
            ++sindex;
         }
      }

      if (charStorageBox.getFlagitems().size() > 0) {
         for(PT_EQUIP equip : charStorageBox.getFlagitems()) {
            equip.sindex = sindex;
            resCharStorageList.flagitems.add(equip);
            ++sindex;
         }
      }

      if (charStorageBox.getMaterialitems().size() > 0) {
         for(PT_STACKABLE equip : charStorageBox.getMaterialitems()) {
            equip.sindex = sindex;
            resCharStorageList.materialitems.add(equip);
            ++sindex;
         }
      }

      if (charStorageBox.getEmblemitems().size() > 0) {
         for(PT_STACKABLE equip : charStorageBox.getEmblemitems()) {
            equip.sindex = sindex;
            resCharStorageList.emblemitems.add(equip);
            ++sindex;
         }
      }

      if (charStorageBox.getCarditems().size() > 0) {
         for(PT_STACKABLE equip : charStorageBox.getCarditems()) {
            equip.sindex = sindex;
            resCharStorageList.carditems.add(equip);
            ++sindex;
         }
      }

      if (charStorageBox.getAvataritems().size() > 0) {
         for(PT_AVATAR_ITEM equip : charStorageBox.getAvataritems()) {
            equip.sindex = sindex;
            resCharStorageList.avataritems.add(equip);
            ++sindex;
         }
      }

      if (charStorageBox.getCartifactitems().size() > 0) {
         for(PT_ARTIFACT equip : charStorageBox.getCartifactitems()) {
            equip.sindex = sindex;
            resCharStorageList.cartifactitems.add(equip);
            ++sindex;
         }
      }

      if (charStorageBox.getEpicpieceitems().size() > 0) {
         for(PT_STACKABLE equip : charStorageBox.getEpicpieceitems()) {
            equip.sindex = sindex;
            resCharStorageList.epicpieceitems.add(equip);
            ++sindex;
         }
      }

      if (charStorageBox.getCreatureitems().size() > 0) {
         for(PT_CREATURE equip : charStorageBox.getCreatureitems()) {
            equip.sindex = sindex;
            resCharStorageList.creatureitems.add(equip);
            ++sindex;
         }
      }

      resCharStorageList.line = role.getCharStorageBox().getLine();
      resCharStorageList.transId = reqCharStorageList.transId;
      MessagePusher.pushMessage((IoSession)session, resCharStorageList);
   }

   @RequestMapping
   public void ReqAdventureStorageList(IoSession session, REQ_ADVENTURE_STORAGE_LIST reqAdventureStorageList) {
      Account account = SessionUtils.getAccountBySession(session);
      RES_ADVENTURE_STORAGE_LIST resAdventureStorageList = new RES_ADVENTURE_STORAGE_LIST();
      AdStorageBox storageBox = account.getAdStorageBox();
      resAdventureStorageList.equipitems = new ArrayList();
      resAdventureStorageList.consumeitems = new ArrayList();
      resAdventureStorageList.titleitems = new ArrayList();
      resAdventureStorageList.flagitems = new ArrayList();
      resAdventureStorageList.materialitems = new ArrayList();
      resAdventureStorageList.emblemitems = new ArrayList();
      resAdventureStorageList.carditems = new ArrayList();
      resAdventureStorageList.avataritems = new ArrayList();
      resAdventureStorageList.cartifactitems = new ArrayList();
      resAdventureStorageList.epicpieceitems = new ArrayList();
      resAdventureStorageList.creatureitems = new ArrayList();
      int sindex = 0;
      if (storageBox.getEquipitems().size() > 0) {
         for(PT_EQUIP equip : storageBox.getEquipitems()) {
            equip.sindex = sindex;
            resAdventureStorageList.equipitems.add(equip);
            ++sindex;
         }
      }

      if (storageBox.getConsumeitems().size() > 0) {
         for(PT_STACKABLE equip : storageBox.getConsumeitems()) {
            equip.sindex = sindex;
            resAdventureStorageList.consumeitems.add(equip);
            ++sindex;
         }
      }

      if (storageBox.getTitleitems().size() > 0) {
         for(PT_EQUIP equip : storageBox.getTitleitems()) {
            equip.sindex = sindex;
            resAdventureStorageList.titleitems.add(equip);
            ++sindex;
         }
      }

      if (storageBox.getFlagitems().size() > 0) {
         for(PT_EQUIP equip : storageBox.getFlagitems()) {
            equip.sindex = sindex;
            resAdventureStorageList.flagitems.add(equip);
            ++sindex;
         }
      }

      if (storageBox.getMaterialitems().size() > 0) {
         for(PT_STACKABLE equip : storageBox.getMaterialitems()) {
            equip.sindex = sindex;
            resAdventureStorageList.materialitems.add(equip);
            ++sindex;
         }
      }

      if (storageBox.getEmblemitems().size() > 0) {
         for(PT_STACKABLE equip : storageBox.getEmblemitems()) {
            equip.sindex = sindex;
            resAdventureStorageList.emblemitems.add(equip);
            ++sindex;
         }
      }

      if (storageBox.getCarditems().size() > 0) {
         for(PT_STACKABLE equip : storageBox.getCarditems()) {
            equip.sindex = sindex;
            resAdventureStorageList.carditems.add(equip);
            ++sindex;
         }
      }

      if (storageBox.getAvataritems().size() > 0) {
         for(PT_AVATAR_ITEM equip : storageBox.getAvataritems()) {
            equip.sindex = sindex;
            resAdventureStorageList.avataritems.add(equip);
            ++sindex;
         }
      }

      if (storageBox.getCartifactitems().size() > 0) {
         for(PT_ARTIFACT equip : storageBox.getCartifactitems()) {
            equip.sindex = sindex;
            resAdventureStorageList.cartifactitems.add(equip);
            ++sindex;
         }
      }

      if (storageBox.getEpicpieceitems().size() > 0) {
         for(PT_STACKABLE equip : storageBox.getEpicpieceitems()) {
            equip.sindex = sindex;
            resAdventureStorageList.epicpieceitems.add(equip);
            ++sindex;
         }
      }

      if (storageBox.getCreatureitems().size() > 0) {
         for(PT_CREATURE equip : storageBox.getCreatureitems()) {
            equip.sindex = sindex;
            resAdventureStorageList.creatureitems.add(equip);
            ++sindex;
         }
      }

      resAdventureStorageList.line = account.getAdStorageBox().getLine();
      resAdventureStorageList.transId = reqAdventureStorageList.transId;
      MessagePusher.pushMessage((IoSession)session, resAdventureStorageList);
   }

   @RequestMapping
   public void REQ_STORAGE_EXTEND(IoSession session, REQ_STORAGE_EXTEND reqStorageExtend) {
      Account account = SessionUtils.getAccountBySession(session);
      Role role = SessionUtils.getRoleBySession(session);
      RES_STORAGE_EXTEND resStorageExtend = new RES_STORAGE_EXTEND();
      PT_MONEY_ITEM tylorItem = account.getMoneyBox().getTylor();
      int tylorCnt = tylorItem.count;
      int level = 0;
      if (reqStorageExtend.storage == null) {
         level = role.getCharStorageBox().getLine();
         int cost = this.getcost(0, level);
         if (tylorCnt >= cost) {
            tylorItem.count = tylorCnt - cost;
            ++level;
            role.getCharStorageBox().setLine(level);
            account.getMoneyBox().updateTylor(tylorItem);
         } else {
            this.logger.error("ERROR====账户余额不足");
         }

         role.save();
      } else if (reqStorageExtend.storage == 1) {
         level = account.getAdStorageBox().getLine();
         int cost = this.getcost(1, level);
         if (tylorCnt >= cost) {
            tylorItem.count = tylorCnt - cost;
            ++level;
            account.getMoneyBox().updateTylor(tylorItem);
            account.getAdStorageBox().setLine(level);
         } else {
            this.logger.error("账户余额不足");
         }
      }

      resStorageExtend.line = level;
      resStorageExtend.transId = reqStorageExtend.transId;
      account.save();
      MessagePusher.pushMessage((IoSession)session, resStorageExtend);
   }

   @RequestMapping
   public void REQ_ITEM_USE(IoSession session, REQ_ITEM_USE req_item_use) {
      Role role = SessionUtils.getRoleBySession(session);
      Account account = SessionUtils.getAccountBySession(session);
      int index = req_item_use.index;
      int type = 0;
      int count = req_item_use.count;
      boolean issuccess = true;
      if (req_item_use.type != null) {
         type = req_item_use.type;
      }

      RES_ITEM_USE res_item_use = new RES_ITEM_USE();
      res_item_use.index = index;
      res_item_use.result = new PT_ITEM_USE_RESULT();
      res_item_use.removeitems = new PT_REMOVEITEMS();
      if (index == 2013106365) {
         res_item_use.info = new PT_CONTENTS_REWARD_INFO();
         res_item_use.info.items = new PT_ITEM_REWARD_INFO();
         res_item_use.info.items.inventory = new PT_ITEMS();
         res_item_use.info.items.inventory.consumeitems = new ArrayList();
         PT_STACKABLE ptStackable = new PT_STACKABLE();
         ptStackable.index = 1990000051;
         ptStackable.count = 1;
         ptStackable.acquisitiontime = TimeUtil.currS();
         res_item_use.info.items.inventory.consumeitems.add(ptStackable);
         this.roleService.addConsumable(role, ptStackable);
      } else if (index == 1990000051) {
         int growtype = Integer.parseInt(req_item_use.input);
         res_item_use.result.growth = growtype;
         res_item_use.result.totalexp = 4414035;
         res_item_use.result.level = 28;
         res_item_use.result.changecount = 194530;
         res_item_use.result.adventureunionlevel = 1;
         res_item_use.result.adventureunionexp = 1324L;
         this.roleService.lvTo28(session, role.getJob(), growtype);
      } else if (index == 2013106366) {
         res_item_use.info = new PT_CONTENTS_REWARD_INFO();
         res_item_use.info.items = new PT_ITEM_REWARD_INFO();
         res_item_use.info.items.inventory = new PT_ITEMS();
         res_item_use.info.items.inventory.consumeitems = new ArrayList();
         PT_STACKABLE ptStackable = new PT_STACKABLE();
         ptStackable.index = 1990000052;
         ptStackable.count = 1;
         ptStackable.acquisitiontime = TimeUtil.currS();
         res_item_use.info.items.inventory.consumeitems.add(ptStackable);
         this.roleService.addConsumable(role, ptStackable);
      } else if (index == 1990000052) {
         int growtype = Integer.parseInt(req_item_use.input);
         res_item_use.result.growth = growtype;
         res_item_use.result.totalexp = 107836272;
         res_item_use.result.level = 55;
         if (role.getJob() == 0 && role.getGrowtype() == 1) {
            res_item_use.result.changecount = 327306;
         } else if (role.getJob() == 0 && role.getGrowtype() == 2) {
            res_item_use.result.changecount = 285257;
         } else if (role.getJob() == 0 && role.getGrowtype() == 3) {
            res_item_use.result.changecount = 487767;
         } else if (role.getJob() == 0 && role.getGrowtype() == 4) {
            res_item_use.result.changecount = 591467;
         } else if (role.getJob() == 1 && role.getGrowtype() == 1) {
            res_item_use.result.changecount = 208401;
         } else if (role.getJob() == 1 && role.getGrowtype() == 2) {
            res_item_use.result.changecount = 194531;
         } else if (role.getJob() == 2 && role.getGrowtype() == 1) {
            res_item_use.result.changecount = 183911;
         } else if (role.getJob() == 2 && role.getGrowtype() == 2) {
            res_item_use.result.changecount = 400494;
         } else if (role.getJob() == 2 && role.getGrowtype() == 3) {
            res_item_use.result.changecount = 156789;
         } else if (role.getJob() == 2 && role.getGrowtype() == 4) {
            res_item_use.result.changecount = 155619;
         } else if (role.getJob() == 3 && role.getGrowtype() == 1) {
            res_item_use.result.changecount = 154057;
         } else if (role.getJob() == 3 && role.getGrowtype() == 4) {
            res_item_use.result.changecount = 121658;
         } else if (role.getJob() == 14 && role.getGrowtype() == 1) {
            res_item_use.result.changecount = 307303;
         } else {
            res_item_use.result.changecount = 194531;
         }

         res_item_use.result.secondgrowtype = 1;
         role.setSecgrowtype(1);
         res_item_use.result.adventureunionlevel = 8;
         res_item_use.result.adventureunionexp = 32344L;
         res_item_use.info = new PT_CONTENTS_REWARD_INFO();
         res_item_use.info.items = new PT_ITEM_REWARD_INFO();
         res_item_use.info.items.inventory = new PT_ITEMS();
         res_item_use.info.items.inventory.consumeitems = new ArrayList();
         res_item_use.info.items.inventory.materialitems = new ArrayList();
         ConsumableBox consumableBox = role.getConsumableBox();
         if (consumableBox == null) {
            consumableBox = new ConsumableBox();
         }

         PT_STACKABLE ptStackable8 = new PT_STACKABLE();
         ptStackable8.index = 2013106360;
         ptStackable8.count = 1;
         consumableBox.addConsumable(ptStackable8);
         res_item_use.info.items.inventory.consumeitems.add(ptStackable8);
         PT_STACKABLE ptStackable19 = new PT_STACKABLE();
         ptStackable19.index = 2013103646;
         ptStackable19.count = 1;
         ptStackable19.bind = true;
         consumableBox.addConsumable(ptStackable19);
         res_item_use.info.items.inventory.consumeitems.add(ptStackable19);
         CardBox cardBox = role.getCardBox();
         if (cardBox == null) {
            cardBox = new CardBox();
            role.setCardBox(cardBox);
         }

         EmblemBox emblemBox = role.getEmblemBox();
         if (emblemBox == null) {
            emblemBox = new EmblemBox();
            role.setEmblemBox(emblemBox);
         }

         role.setConsumableBox(consumableBox);
         this.roleService.lvTo55(session, role.getJob(), growtype);
      } else if (index != 2013106329) {
         if (index == 2013104235) {
            int option = EquipDataPool.changeEpic(req_item_use.guid, role);
            res_item_use.guid = req_item_use.guid;
            res_item_use.result.option = option;
            role.getMoneyBox().submoney(100000);
            res_item_use.info = new PT_CONTENTS_REWARD_INFO();
            res_item_use.info.currency = new PT_CURRENCY_REWARD_INFO();
            res_item_use.info.currency.currency = new ArrayList();
            PT_MONEY_ITEM ptMoneyItem = new PT_MONEY_ITEM();
            ptMoneyItem.count = role.getMoneyBox().getMoneyItem(0).count;
            res_item_use.info.currency.currency.add(ptMoneyItem);
         } else if (index != 2013106503 && index != 2013103646) {
            if (index != 2013105097 && index != 2013103769 && index != 2013103666 && index != 2013103633 && index != 2013103631) {
               if (index == 2013106360) {
                  PT_EQUIP ptequip = EquipDataPool.createEquip(req_item_use.selectitemindex);
                  role.getEquipBox().addEquip(ptequip);
                  res_item_use.info = new PT_CONTENTS_REWARD_INFO();
                  res_item_use.info.items = new PT_ITEM_REWARD_INFO();
                  res_item_use.info.items.inventory = new PT_ITEMS();
                  res_item_use.info.items.inventory.equipitems = new ArrayList();
                  res_item_use.info.items.inventory.equipitems.add(ptequip);
                  res_item_use.result = new PT_ITEM_USE_RESULT();
               } else if (index != 2013101108 && index != 2013103608 && index != 2013106056) {
                  if (index != 2013106404 && index != 2013106405 && index != 2013106406 && index != 2013106407 && index != 2013106408 && index != 2013106409 && index != 2013106611 && index != 2013106597 && index != 2013103644 && index != 2013106158) {
                     if (index != 2013106380 && index != 2013106381 && index != 2013106382 && index != 2013106383 && index != 2013106384 && index != 2013106385 && index != 2013106386 && index != 2013106387 && index != 2013106388 && index != 2013106389 && index != 2013106390 && index != 2013106391 && index != 2013106392 && index != 2013106393 && index != 2013106394 && index != 2013106395 && index != 2013106396 && index != 2013106397 && index != 2013106398 && index != 2013106399 && index != 2013106400 && index != 2013106401 && index != 2013106402 && index != 2013106403) {
                        if (index != 2013106374 && index != 2013106375 && index != 2013106376 && index != 2013106377 && index != 2013106378 && index != 2013106379) {
                           if (index == 2013106026 || index == 2013106031 || index == 2013106027 || index == 2013101269 || index == 2013100888 || index == 2013104324 || index == 2013103867 || index == 2013100710 || index == 2013103578 || index == 2013103941) {
                              List<giftContent> giftContents = ItemDataPool.getGiftList(index);
                              res_item_use.info = new PT_CONTENTS_REWARD_INFO();
                              res_item_use.info.items = new PT_ITEM_REWARD_INFO();
                              res_item_use.info.items.inventory = new PT_ITEMS();
                              res_item_use.info.items.inventory.materialitems = new ArrayList();

                              for(giftContent giftContent : giftContents) {
                                 if (giftContent.id == 2013000001) {
                                    account.getMoneyBox().addTylor(giftContent.count);
                                    res_item_use.info = new PT_CONTENTS_REWARD_INFO();
                                    res_item_use.info.paymentcurrency = new PT_CURRENCY_REWARD_INFO();
                                    res_item_use.info.paymentcurrency.currency = new ArrayList();
                                    PT_MONEY_ITEM ptMoneyItem = new PT_MONEY_ITEM();
                                    ptMoneyItem.index = 2013000001;
                                    ptMoneyItem.count = giftContent.count * req_item_use.count;
                                    res_item_use.info.paymentcurrency.currency.add(ptMoneyItem);
                                 } else {
                                    PT_STACKABLE ptStackable = role.getMaterialBox().getMaterial(giftContent.id);
                                    PT_STACKABLE ptStackable1 = new PT_STACKABLE();
                                    ptStackable1.index = giftContent.id;
                                    ptStackable1.count = giftContent.count * req_item_use.count;
                                    if (ptStackable == null) {
                                       role.getMaterialBox().addMaterial(ptStackable1);
                                    } else {
                                       ptStackable.count = ptStackable.count + giftContent.count * req_item_use.count;
                                       role.getMaterialBox().addMaterial(ptStackable);
                                    }

                                    res_item_use.info.items.inventory.materialitems.add(ptStackable1);
                                 }
                              }
                           } else if (index == 2013106539 || index == 2013103863 || index == 2013106671) {
                              List<giftContent> giftContents = ItemDataPool.getGiftList(index);
                              res_item_use.info = new PT_CONTENTS_REWARD_INFO();
                              res_item_use.info.items = new PT_ITEM_REWARD_INFO();
                              res_item_use.info.items.inventory = new PT_ITEMS();
                              res_item_use.info.items.inventory.consumeitems = new ArrayList();
                              res_item_use.info.paymentcurrency = new PT_CURRENCY_REWARD_INFO();
                              res_item_use.info.paymentcurrency.currency = new ArrayList();

                              for(giftContent giftContent : giftContents) {
                                 if (giftContent.id == 2013000001) {
                                    account.getMoneyBox().addTylor(giftContent.count * req_item_use.count);
                                    PT_MONEY_ITEM ptMoneyItem = new PT_MONEY_ITEM();
                                    ptMoneyItem.index = 2013000001;
                                    ptMoneyItem.count = giftContent.count * req_item_use.count;
                                    res_item_use.info.paymentcurrency.currency.add(ptMoneyItem);
                                 } else if (giftContent.id == 2013100902) {
                                    role.getMoneyBox().addCnt(2013100902, giftContent.count * req_item_use.count);
                                    PT_MONEY_ITEM ptMoneyItem = new PT_MONEY_ITEM();
                                    ptMoneyItem.index = 2013100902;
                                    ptMoneyItem.count = giftContent.count * req_item_use.count;
                                    res_item_use.info.paymentcurrency.currency.add(ptMoneyItem);
                                 } else {
                                    DEF_ITEM_CONSUMABLE ptStackable = role.getConsumableBox().getConsumable(giftContent.id);
                                    PT_STACKABLE ptStackable1 = new PT_STACKABLE();
                                    ptStackable1.index = giftContent.id;
                                    ptStackable1.count = giftContent.count * req_item_use.count;
                                    if (ptStackable == null) {
                                       role.getConsumableBox().addConsumable(ptStackable1);
                                    } else {
                                       ptStackable.count = ptStackable.count + giftContent.count * req_item_use.count;
                                       role.getConsumableBox().addConsumable(ptStackable);
                                    }

                                    res_item_use.info.items.inventory.consumeitems.add(ptStackable1);
                                 }
                              }
                           } else if (index == 2013101303 || index == 2013103516) {
                              List<giftContent> giftContents = ItemDataPool.getGiftList(index);
                              res_item_use.info = new PT_CONTENTS_REWARD_INFO();
                              res_item_use.info.items = new PT_ITEM_REWARD_INFO();
                              res_item_use.info.items.inventory = new PT_ITEMS();
                              res_item_use.info.items.inventory.carditems = new ArrayList();

                              for(giftContent giftContent : giftContents) {
                                 PT_STACKABLE ptStackable = role.getCardBox().getCard(giftContent.id);
                                 PT_STACKABLE ptStackable1 = new PT_STACKABLE();
                                 ptStackable1.index = giftContent.id;
                                 ptStackable1.count = giftContent.count * req_item_use.count;
                                 if (ptStackable == null) {
                                    role.getCardBox().addCard(ptStackable1);
                                 } else {
                                    ptStackable.count = ptStackable.count + giftContent.count * req_item_use.count;
                                    role.getCardBox().addCard(ptStackable);
                                 }

                                 res_item_use.info.items.inventory.carditems.add(ptStackable1);
                              }
                           } else if (index == 2013106410) {
                              List<giftContent> giftContents = ItemDataPool.getGiftList(index);
                              res_item_use.info = new PT_CONTENTS_REWARD_INFO();
                              res_item_use.info.items = new PT_ITEM_REWARD_INFO();
                              res_item_use.info.items.inventory = new PT_ITEMS();
                              res_item_use.info.items.inventory.consumeitems = new ArrayList();
                              res_item_use.info.paymentcurrency = new PT_CURRENCY_REWARD_INFO();
                              res_item_use.info.paymentcurrency.currency = new ArrayList();

                              for(giftContent giftContent : giftContents) {
                                 if (giftContent.id == 2013104144) {
                                    account.getMoneyBox().addCnt(2013104144, giftContent.count);
                                    PT_MONEY_ITEM ptMoneyItem = new PT_MONEY_ITEM();
                                    ptMoneyItem.index = 2013104144;
                                    ptMoneyItem.count = giftContent.count * req_item_use.count;
                                    res_item_use.info.paymentcurrency.currency.add(ptMoneyItem);
                                 } else if (giftContent.id == 0) {
                                    role.getMoneyBox().addmoney(giftContent.count * req_item_use.count);
                                    PT_MONEY_ITEM ptMoneyItem = new PT_MONEY_ITEM();
                                    ptMoneyItem.count = giftContent.count * req_item_use.count;
                                    res_item_use.info.paymentcurrency.currency.add(ptMoneyItem);
                                 } else if (giftContent.id == 2013100902) {
                                    account.getMoneyBox().addCnt(2013100902, giftContent.count * req_item_use.count);
                                    PT_MONEY_ITEM ptMoneyItem = new PT_MONEY_ITEM();
                                    ptMoneyItem.index = 2013100902;
                                    ptMoneyItem.count = giftContent.count * req_item_use.count;
                                    res_item_use.info.paymentcurrency.currency.add(ptMoneyItem);
                                 } else {
                                    DEF_ITEM_CONSUMABLE ptStackable = role.getConsumableBox().getConsumable(giftContent.id);
                                    PT_STACKABLE ptStackable1 = new PT_STACKABLE();
                                    ptStackable1.index = giftContent.id;
                                    ptStackable1.count = giftContent.count * req_item_use.count;
                                    if (ptStackable == null) {
                                       role.getConsumableBox().addConsumable(ptStackable1);
                                    } else {
                                       ptStackable.count = ptStackable.count + giftContent.count * req_item_use.count;
                                       role.getConsumableBox().addConsumable(ptStackable);
                                    }

                                    res_item_use.info.items.inventory.consumeitems.add(ptStackable1);
                                 }
                              }
                           } else if (index == 2013106411) {
                              res_item_use.info = new PT_CONTENTS_REWARD_INFO();
                              res_item_use.info.items = new PT_ITEM_REWARD_INFO();
                              res_item_use.info.items.inventory = new PT_ITEMS();
                              res_item_use.info.items.inventory.titleitems = new ArrayList();
                              PT_EQUIP ptEquip = role.getTitleBox().createTitle(req_item_use.selectitemindex);
                              res_item_use.info.items.inventory.titleitems.add(ptEquip);
                           } else if (index != 2013106413 && index != 2013104644 && index != 2013104504) {
                              if (index == 2013104404) {
                                 count = 3300;
                                 account.getMoneyBox().addCera(count);
                                 res_item_use.info = new PT_CONTENTS_REWARD_INFO();
                                 res_item_use.info.paymentcurrency = new PT_CURRENCY_REWARD_INFO();
                                 res_item_use.info.paymentcurrency.currency = new ArrayList();
                                 PT_MONEY_ITEM ptMoneyItem = new PT_MONEY_ITEM();
                                 ptMoneyItem.index = 2;
                                 ptMoneyItem.count = count;
                                 res_item_use.info.paymentcurrency.currency.add(ptMoneyItem);
                              } else if (index == 2013103647) {
                                 String name = req_item_use.input;
                                 if (name.equals("张哥哥")) {
                                    res_item_use.error = 11;
                                    res_item_use.transId = req_item_use.transId;
                                    MessagePusher.pushMessage((IoSession)session, res_item_use);
                                    return;
                                 }

                                 role.setName(name);
                              } else if (index == 2013106021) {
                                 if (role.getFatigue() + 10 > 100) {
                                    role.setFatigue(100);
                                 } else {
                                    role.setFatigue(role.getFatigue() + 10);
                                 }

                                 res_item_use.result = new PT_ITEM_USE_RESULT();
                                 res_item_use.result.fatigue = role.getFatigue();
                              } else if (index == 2013104914 || index == 1990000505 || index == 2013104497 || index == 2013104496) {
                                 List<giftContent> giftContents = ItemDataPool.getGiftList(index);
                                 res_item_use.info = new PT_CONTENTS_REWARD_INFO();
                                 res_item_use.info.items = new PT_ITEM_REWARD_INFO();
                                 res_item_use.info.items.inventory = new PT_ITEMS();
                                 res_item_use.info.items.inventory.emblemitems = new ArrayList();
                                 int random = (int)(Math.random() * (double)giftContents.size());

                                 for(int i = 0; i < giftContents.size(); ++i) {
                                    giftContent giftContent = (giftContent)giftContents.get(i);
                                    if (i == random) {
                                       PT_STACKABLE ptStackable = role.getCardBox().getCard(giftContent.id);
                                       PT_STACKABLE ptStackable1 = new PT_STACKABLE();
                                       ptStackable1.index = giftContent.id;
                                       ptStackable1.count = giftContent.count * req_item_use.count;
                                       if (ptStackable == null) {
                                          role.getEmblemBox().addEmblem(ptStackable1);
                                       } else {
                                          ptStackable.count = ptStackable.count + giftContent.count * req_item_use.count;
                                          role.getEmblemBox().addEmblem(ptStackable);
                                       }

                                       res_item_use.info.items.inventory.emblemitems.add(ptStackable1);
                                    }
                                 }
                              } else if (index != 2013106159 && index != 2013106199 && index != 2013106200 && index != 2013106201 && index != 2013106202 && index != 2013106205 && index != 2013106206 && index != 2013104180 && index != 2013106495 && index != 2013106502 && index != 2013106496 && index != 2013106496) {
                                 if (index == 2013106509) {
                                    PT_EQUIP ptequip = EquipDataPool.createEquip(req_item_use.selectitemindex);
                                    role.getEquipBox().addEquip(ptequip);
                                    res_item_use.info = new PT_CONTENTS_REWARD_INFO();
                                    res_item_use.info.items = new PT_ITEM_REWARD_INFO();
                                    res_item_use.info.items.inventory = new PT_ITEMS();
                                    res_item_use.info.items.inventory.equipitems = new ArrayList();
                                    res_item_use.info.items.inventory.equipitems.add(ptequip);
                                    res_item_use.result = new PT_ITEM_USE_RESULT();
                                 } else {
                                    issuccess = false;
                                    this.logger.error("ERROR====物品没处理=={}", index);
                                 }
                              } else {
                                 long guid = req_item_use.guid;
                                 PT_EQUIPPED equipped = role.getEquippedBox().getEquipped(guid);
                                 List<PT_STACKABLE> ptStackables = new ArrayList();
                                 PT_STACKABLE ptStackable = new PT_STACKABLE();
                                 ptStackable.index = index;
                                 ptStackable.count = 1;
                                 ptStackables.add(ptStackable);
                                 PT_EQUIP title = role.getTitleBox().getTitle(guid);
                                 if (title == null) {
                                    if (equipped == null) {
                                       PT_EQUIP equip = role.getEquipBox().getEquip(guid);
                                       equip.card = ptStackables;
                                    } else {
                                       equipped.card = ptStackables;
                                    }
                                 } else {
                                    title.card = ptStackables;
                                 }

                                 res_item_use.guid = req_item_use.guid;
                                 res_item_use.result.enchantindex = index;
                                 if (index == 2013106201) {
                                    res_item_use.result.enchant = 101004;
                                 } else if (index == 2013106159) {
                                    res_item_use.result.enchant = 115004;
                                 } else if (index == 2013106199) {
                                    res_item_use.result.enchant = 107003;
                                 } else if (index == 2013106200) {
                                    res_item_use.result.enchant = 108003;
                                 } else if (index == 2013106202) {
                                    res_item_use.result.enchant = 102004;
                                 } else if (index == 2013106205) {
                                    res_item_use.result.enchant = 105002;
                                 } else if (index == 2013106206) {
                                    res_item_use.result.enchant = 106002;
                                 } else if (index == 2013104180) {
                                    res_item_use.result.enchant = 132001;
                                 } else if (index == 2013106495) {
                                    res_item_use.result.enchant = 102005;
                                 } else if (index == 2013106502) {
                                    res_item_use.result.enchant = 101005;
                                 } else if (index == 2013106496) {
                                    res_item_use.result.enchant = 115005;
                                 }

                                 res_item_use.info = new PT_CONTENTS_REWARD_INFO();
                              }
                           } else {
                              res_item_use.info = new PT_CONTENTS_REWARD_INFO();
                              res_item_use.info.items = new PT_ITEM_REWARD_INFO();
                              res_item_use.info.items.inventory = new PT_ITEMS();
                              res_item_use.info.items.inventory.avataritems = new ArrayList();
                              PT_AVATAR_ITEM ptEquip = role.getAvatarBox().createAvatar(req_item_use.selectitemindex);
                              res_item_use.info.items.inventory.avataritems.add(ptEquip);
                           }
                        } else {
                           List<giftContent> giftContents = ItemDataPool.getGiftList(index);
                           res_item_use.info = new PT_CONTENTS_REWARD_INFO();
                           res_item_use.info.items = new PT_ITEM_REWARD_INFO();
                           res_item_use.info.items.inventory = new PT_ITEMS();
                           res_item_use.info.items.inventory.consumeitems = new ArrayList();

                           for(giftContent giftContent : giftContents) {
                              DEF_ITEM_CONSUMABLE ptStackable = role.getConsumableBox().getConsumable(giftContent.id);
                              PT_STACKABLE ptStackable1 = new PT_STACKABLE();
                              ptStackable1.index = giftContent.id;
                              ptStackable1.count = giftContent.count * req_item_use.count;
                              if (ptStackable == null) {
                                 role.getConsumableBox().addConsumable(ptStackable1);
                              } else {
                                 ptStackable.count = ptStackable.count + giftContent.count * req_item_use.count;
                                 role.getConsumableBox().addConsumable(ptStackable);
                              }

                              res_item_use.info.items.inventory.consumeitems.add(ptStackable1);
                           }

                           res_item_use.result = new PT_ITEM_USE_RESULT();
                        }
                     } else {
                        List<giftContent> giftContents = ItemDataPool.getGiftList(index);
                        res_item_use.info = new PT_CONTENTS_REWARD_INFO();
                        res_item_use.info.items = new PT_ITEM_REWARD_INFO();
                        res_item_use.info.items.inventory = new PT_ITEMS();
                        res_item_use.info.items.inventory.avataritems = new ArrayList();

                        for(giftContent giftContent : giftContents) {
                           PT_AVATAR_ITEM ptAvatarItem = new PT_AVATAR_ITEM();
                           ptAvatarItem.index = giftContent.id;
                           ptAvatarItem.guid = IdGenerator.getNextId();
                           role.getAvatarBox().addAvatar(ptAvatarItem);
                           res_item_use.info.items.inventory.avataritems.add(ptAvatarItem);
                        }

                        res_item_use.result = new PT_ITEM_USE_RESULT();
                     }
                  } else {
                     res_item_use.info = new PT_CONTENTS_REWARD_INFO();
                     res_item_use.info.items = new PT_ITEM_REWARD_INFO();
                     res_item_use.info.items.inventory = new PT_ITEMS();
                     res_item_use.info.items.inventory.consumeitems = new ArrayList();
                     if (req_item_use.selectitemindex != null) {
                        DEF_ITEM_CONSUMABLE ptStackable = role.getConsumableBox().getConsumable(req_item_use.selectitemindex);
                        PT_STACKABLE ptStackable1 = new PT_STACKABLE();
                        ptStackable1.index = req_item_use.selectitemindex;
                        ptStackable1.count = req_item_use.count;
                        if (ptStackable == null) {
                           role.getConsumableBox().addConsumable(ptStackable1);
                        } else {
                           ptStackable.count = ptStackable.count + req_item_use.count;
                           role.getConsumableBox().addConsumable(ptStackable);
                        }

                        res_item_use.info.items.inventory.consumeitems.add(ptStackable1);
                     } else {
                        for(giftContent giftContent : ItemDataPool.getGiftList(index)) {
                           DEF_ITEM_CONSUMABLE ptStackable = role.getConsumableBox().getConsumable(giftContent.id);
                           PT_STACKABLE ptStackable1 = new PT_STACKABLE();
                           ptStackable1.index = giftContent.id;
                           ptStackable1.count = giftContent.count * req_item_use.count;
                           if (ptStackable == null) {
                              role.getConsumableBox().addConsumable(ptStackable1);
                           } else {
                              ptStackable.count = ptStackable.count + giftContent.count * req_item_use.count;
                              role.getConsumableBox().addConsumable(ptStackable);
                           }

                           res_item_use.info.items.inventory.consumeitems.add(ptStackable1);
                        }
                     }

                     res_item_use.result = new PT_ITEM_USE_RESULT();
                  }
               } else {
                  count = 1000 * req_item_use.count;
                  if (index == 2013106056) {
                     count = '鱀' * req_item_use.count;
                  }

                  account.getMoneyBox().addTylor(count);
                  res_item_use.info = new PT_CONTENTS_REWARD_INFO();
                  res_item_use.info.paymentcurrency = new PT_CURRENCY_REWARD_INFO();
                  res_item_use.info.paymentcurrency.currency = new ArrayList();
                  PT_MONEY_ITEM ptMoneyItem = new PT_MONEY_ITEM();
                  ptMoneyItem.index = 2013000001;
                  ptMoneyItem.count = count;
                  res_item_use.info.paymentcurrency.currency.add(ptMoneyItem);
                  res_item_use.result = new PT_ITEM_USE_RESULT();
               }
            } else {
               PT_EQUIPPED ptEquipped = role.getEquippedBox().getEquipped(req_item_use.guid);
               int random = (int)(Math.random() * (double)100.0F);
               int rate = this.getReinforceRate(index);
               res_item_use.result = new PT_ITEM_USE_RESULT();
               if (ptEquipped == null) {
                  PT_EQUIP ptEquip = role.getEquipBox().getEquip(req_item_use.guid);
                  if (random < rate) {
                     ptEquip.setUpgrade(12);
                     ptEquip.setUpgradepoint(0);
                     role.getEquipBox().addEquip(ptEquip);
                     res_item_use.result.result = true;
                     res_item_use.result.upgrade = 12;
                  } else {
                     res_item_use.result.result = false;
                     res_item_use.result.upgrade = ptEquip.upgrade;
                  }
               } else if (random < rate) {
                  ptEquipped.setUpgrade(12);
                  ptEquipped.setUpgradepoint(0);
                  role.getEquippedBox().updateEquip(ptEquipped);
                  res_item_use.result.result = true;
                  res_item_use.result.upgrade = 12;
               } else {
                  res_item_use.result.result = false;
                  res_item_use.result.upgrade = ptEquipped.upgrade;
               }

               res_item_use.guid = req_item_use.guid;
            }
         } else {
            PT_EQUIPPED ptEquipped = role.getEquippedBox().getEquipped(req_item_use.guid);
            if (ptEquipped == null) {
               PT_EQUIP ptEquip = role.getEquipBox().getEquip(req_item_use.guid);
               ptEquip.setUpgrade(12);
               ptEquip.setUpgradepoint(0);
               role.getEquipBox().addEquip(ptEquip);
            } else {
               ptEquipped.setUpgrade(12);
               ptEquipped.setUpgradepoint(0);
               role.getEquippedBox().updateEquip(ptEquipped);
            }

            res_item_use.result = new PT_ITEM_USE_RESULT();
            res_item_use.result.result = true;
            res_item_use.result.upgrade = 12;
            res_item_use.guid = req_item_use.guid;
         }
      }

      if (issuccess) {
         int table = BagService.getTable(index);
         if (table != 0 && table == 1) {
            switch (type) {
               case 0:
               case 17:
               case 22:
                  DEF_ITEM_CONSUMABLE consumable = role.getConsumableBox().getConsumable(index);
                  consumable.count = consumable.count - req_item_use.count;
                  if (consumable.count == 0) {
                     role.getConsumableBox().removeConsumable(index);
                  } else {
                     role.getConsumableBox().addConsumable(consumable);
                  }

                  if (consumable.bind != null && consumable.bind) {
                     res_item_use.removeitems.consumeitems = new ArrayList();
                     PT_STACKABLE ptStackable = new PT_STACKABLE();
                     ptStackable.index = index;
                     ptStackable.bind = true;
                     ptStackable.count = consumable.count;
                     res_item_use.removeitems.consumeitems.add(ptStackable);
                     PT_STACKABLE ptStackable1 = new PT_STACKABLE();
                     ptStackable1.index = index;
                     res_item_use.removeitems.consumeitems.add(ptStackable1);
                  } else {
                     res_item_use.removeitems.consumeitems = new ArrayList();
                     PT_STACKABLE ptStackable = new PT_STACKABLE();
                     ptStackable.index = index;
                     ptStackable.bind = true;
                     res_item_use.removeitems.consumeitems.add(ptStackable);
                     PT_STACKABLE ptStackable1 = new PT_STACKABLE();
                     ptStackable1.index = index;
                     ptStackable1.count = consumable.count;
                     res_item_use.removeitems.consumeitems.add(ptStackable1);
                  }
            }
         }
      }

      res_item_use.sender = role.getUid();
      res_item_use.transId = req_item_use.transId;
      role.save();
      MessagePusher.pushMessage((IoSession)session, res_item_use);
   }

   @RequestMapping
   public void ReqCeraShopBuy(IoSession session, REQ_CERA_SHOP_BUY reqCeraShopBuy) {
      String openkey = reqCeraShopBuy.openkey;
      List<PT_CERA_SHOP_BUY> buy = reqCeraShopBuy.buy;
      Map<Integer, itemShop> itemShopMap = ItemDataPool.itemShopMap;
      Role role = SessionUtils.getRoleBySession(session);
      Account account = SessionUtils.getAccountBySession(session);
      RES_CERA_SHOP_BUY resCeraShopBuy = new RES_CERA_SHOP_BUY();
      PT_CONTENTS_REWARD_INFO rewards = new PT_CONTENTS_REWARD_INFO();
      PT_ITEM_REWARD_INFO items = new PT_ITEM_REWARD_INFO();
      PT_ITEMS inventory = new PT_ITEMS();
      PT_CURRENCY_REWARD_INFO currency = new PT_CURRENCY_REWARD_INFO();
      PT_CURRENCY_REWARD_INFO paymentcurrency = new PT_CURRENCY_REWARD_INFO();
      List<PT_MONEY_ITEM> paycurrencylist = new ArrayList();
      List<PT_MONEY_ITEM> currencylist = new ArrayList();
      inventory.equipitems = new ArrayList();
      inventory.titleitems = new ArrayList();
      inventory.flagitems = new ArrayList();
      inventory.materialitems = new ArrayList();
      inventory.consumeitems = new ArrayList();
      inventory.emblemitems = new ArrayList();
      inventory.carditems = new ArrayList();
      inventory.epicpieceitems = new ArrayList();
      inventory.cartifactitems = new ArrayList();
      inventory.creatureitems = new ArrayList();
      inventory.avataritems = new ArrayList();
      inventory.damagefontitems = new ArrayList();
      inventory.chatframeitems = new ArrayList();
      inventory.characterframeitems = new ArrayList();
      inventory.crackitems = new ArrayList();
      inventory.crackequipitems = new ArrayList();
      inventory.sdavataritems = new ArrayList();
      inventory.timebox = new ArrayList();
      inventory.bookmarkitems = new ArrayList();
      inventory.scrollitems = new ArrayList();
      resCeraShopBuy.account = new PT_CERA_SHOP_INFO();
      resCeraShopBuy.character = new PT_CERA_SHOP_INFO();
      PT_MONEY_ITEM ptMoneyItem = new PT_MONEY_ITEM();
      int moneytype = 0;
      boolean isitem = false;

      for(PT_CERA_SHOP_BUY ptCeraShopBuy : buy) {
         int goodid = ptCeraShopBuy.goodsid;
         int count = ptCeraShopBuy.count;
         itemShop itemShop = (itemShop)itemShopMap.get(goodid);
         moneytype = itemShop.getMoneytype();
         int moneycount = itemShop.getMoneycount();
         String limittype = itemShop.getLimittype();
         if (goodid == 70000) {
            new PT_MONEY_ITEM();
            ptMoneyItem = (PT_MONEY_ITEM)account.getMoneyBox().getAccountcurrency().get(2);
            ptMoneyItem.count = ptMoneyItem.count - count;
            currencylist.add(ptMoneyItem);
            account.getMoneyBox().getAccountcurrency().put(2, ptMoneyItem);
            account.getMoneyBox().addTylor(count * 10);
            PT_MONEY_ITEM ptMoneyItem2 = new PT_MONEY_ITEM();
            ptMoneyItem2.index = 2013000001;
            ptMoneyItem2.count = account.getMoneyBox().getMoneyItem(2013000001).count;
            currencylist.add(ptMoneyItem2);
            PT_MONEY_ITEM ptMoneyItem3 = new PT_MONEY_ITEM();
            ptMoneyItem3.index = 2013000001;
            ptMoneyItem3.count = count * 10;
            paycurrencylist.add(ptMoneyItem3);
         } else if (goodid == 70001) {
            new PT_MONEY_ITEM();
            ptMoneyItem = (PT_MONEY_ITEM)account.getMoneyBox().getAccountcurrency().get(2013000001);
            ptMoneyItem.count = ptMoneyItem.count - count;
            currencylist.add(ptMoneyItem);
            account.getMoneyBox().getAccountcurrency().put(2013000001, ptMoneyItem);
            role.getMoneyBox().addmoney(count * 100);
            PT_MONEY_ITEM ptMoneyItem2 = new PT_MONEY_ITEM();
            ptMoneyItem2.count = role.getMoneyBox().getMoneyItem(0).count;
            currencylist.add(ptMoneyItem2);
            PT_MONEY_ITEM ptMoneyItem3 = new PT_MONEY_ITEM();
            ptMoneyItem3.count = count * 100;
            paycurrencylist.add(ptMoneyItem3);
         } else if (goodid == 70002) {
            new PT_MONEY_ITEM();
            ptMoneyItem = (PT_MONEY_ITEM)account.getMoneyBox().getAccountcurrency().get(2);
            ptMoneyItem.count = ptMoneyItem.count - count;
            currencylist.add(ptMoneyItem);
            account.getMoneyBox().getAccountcurrency().put(2, ptMoneyItem);
            role.getMoneyBox().addmoney(count * 1000);
            PT_MONEY_ITEM ptMoneyItem2 = new PT_MONEY_ITEM();
            ptMoneyItem2.count = role.getMoneyBox().getMoneyItem(0).count;
            currencylist.add(ptMoneyItem2);
            PT_MONEY_ITEM ptMoneyItem3 = new PT_MONEY_ITEM();
            ptMoneyItem3.count = count * 1000;
            paycurrencylist.add(ptMoneyItem3);
         } else if (goodid == 30015) {
            PT_MONEY_ITEM ptMoneyItem2 = role.getMoneyBox().getMoneyItem(2013100902);
            PT_MONEY_ITEM ptMoneyItem3 = new PT_MONEY_ITEM();
            ptMoneyItem3.index = 2013100902;
            ptMoneyItem3.count = count;
            paycurrencylist.add(ptMoneyItem3);
            if (ptMoneyItem2 == null) {
               ptMoneyItem2 = new PT_MONEY_ITEM();
               ptMoneyItem2.index = 2013100902;
               ptMoneyItem2.count = 0;
            } else {
               currencylist.add(ptMoneyItem2);
            }

            role.getMoneyBox().addCnt(2013100902, count);
         } else if (goodid == 30017) {
            ptMoneyItem = (PT_MONEY_ITEM)account.getMoneyBox().getAccountcurrency().get(2013000001);
            ptMoneyItem.count = ptMoneyItem.count - 1500 * count;
            currencylist.add(ptMoneyItem);
            account.getMoneyBox().getAccountcurrency().put(2013000001, ptMoneyItem);
            currencylist.add(role.getMoneyBox().getMoneyItem(2013100902));
            PT_MONEY_ITEM ptMoneyItem3 = new PT_MONEY_ITEM();
            ptMoneyItem3.index = 2013100902;
            ptMoneyItem3.count = count;
            paycurrencylist.add(ptMoneyItem3);
            role.getMoneyBox().addCnt(2013100902, count);
         } else if (goodid == 30053) {
            isitem = true;
            PT_STACKABLE ptStackable = new PT_STACKABLE();
            ptStackable.index = 2013101405;
            ptStackable.count = count * 3;
            inventory.materialitems.add(ptStackable);
            PT_STACKABLE ptStackable1 = role.getMaterialBox().getMaterial(2013101405);
            if (ptStackable1 != null) {
               ptStackable1.count = ptStackable1.count + count * 3;
               role.getMaterialBox().addMaterial(ptStackable1);
            } else {
               role.getMaterialBox().addMaterial(ptStackable);
            }
         } else {
            isitem = true;
            if (this.isAccountMoney(moneytype)) {
               Map<Integer, PT_MONEY_ITEM> accountMoney = account.getMoneyBox().getAccountcurrency();
               ptMoneyItem = (PT_MONEY_ITEM)accountMoney.get(moneytype);
               int money = ptMoneyItem.count;
               if (money >= count * moneycount) {
                  money -= count * moneycount;
                  ptMoneyItem.count = money;
                  accountMoney.put(moneytype, ptMoneyItem);
                  account.getMoneyBox().setAccountcurrency(accountMoney);
               } else {
                  this.logger.error("ERROR====账户余额不足");
               }
            } else {
               Map<Integer, PT_MONEY_ITEM> roleMoney = role.getMoneyBox().getCurrency();
               ptMoneyItem = (PT_MONEY_ITEM)roleMoney.get(moneytype);
               int money = ptMoneyItem.count;
               if (money >= count * moneycount) {
                  money -= count * moneycount;
                  ptMoneyItem.count = money;
                  roleMoney.put(moneytype, ptMoneyItem);
                  role.getMoneyBox().setCurrency(roleMoney);
               } else {
                  this.logger.error("ERROR====角色余额不足");
               }
            }

            int index = itemShop.getIndex();
            Map<Integer, ConsumeItem> consumeItemMap = ItemDataPool.consumeItemMap;
            ConsumeItem consumeItem = (ConsumeItem)consumeItemMap.get(index);
            if (consumeItemMap.get(index) != null) {
               int newtype = ((ConsumeItem)consumeItemMap.get(index)).getStackabletype();
               this.logger.error("goodsid == {}, index == {}, name == {}, newtype == {}", new Object[]{goodid, index, consumeItem.getItemname(), newtype});
               if (newtype == 0 | newtype == 17 | newtype == 22) {
                  PT_STACKABLE ptStackable = new PT_STACKABLE();
                  ptStackable.index = index;
                  ptStackable.count = count;
                  inventory.consumeitems.add(ptStackable);
                  DEF_ITEM_CONSUMABLE ptStackable1 = role.getConsumableBox().getConsumable(index);
                  if (newtype == 17) {
                     account.getActivityBox().setTylorBagExpireTime(DateUtils.getAfterDayEndDate(30).getTime());
                     String title = "泰拉充电包";
                     String msg = "泰拉充电包 每日邮件";
                     List<MailItem> mailItemList = new ArrayList();
                     MailItem mailItem = new MailItem();
                     mailItem.index = 2013000001;
                     mailItem.cnt = 2000;
                     mailItemList.add(mailItem);
                     account.getMailBox().addMail(title, msg, mailItemList);
                     this.logger.error("泰拉充电包 邮件发送 {}", mailItem);
                  }

                  if (ptStackable1 != null) {
                     ptStackable1.count = ptStackable1.count + count;
                     role.getConsumableBox().addConsumable(ptStackable1);
                  } else {
                     role.getConsumableBox().addConsumable(ptStackable);
                  }
               } else if (newtype == 1 | newtype == 2 | newtype == 19 | newtype == 24) {
                  PT_STACKABLE ptStackable = new PT_STACKABLE();
                  ptStackable.index = index;
                  ptStackable.count = count;
                  inventory.materialitems.add(ptStackable);
                  PT_STACKABLE ptStackable1 = role.getMaterialBox().getMaterial(index);
                  if (ptStackable1 != null) {
                     ptStackable1.count = ptStackable1.count + count;
                     role.getMaterialBox().addMaterial(ptStackable1);
                  } else {
                     role.getMaterialBox().addMaterial(ptStackable);
                  }
               } else if (newtype == 14) {
                  PT_STACKABLE ptStackable = new PT_STACKABLE();
                  ptStackable.index = index;
                  ptStackable.count = count;
                  inventory.emblemitems.add(ptStackable);
                  role.getEmblemBox().updateEmblem(ptStackable);
               } else if (newtype == 2) {
                  PT_STACKABLE ptStackable = new PT_STACKABLE();
                  ptStackable.index = index;
                  ptStackable.count = count;
                  inventory.carditems.add(ptStackable);
                  role.getCardBox().updateCard(ptStackable);
               } else if (newtype == 18) {
                  PT_STACKABLE ptStackable = new PT_STACKABLE();
                  ptStackable.index = index;
                  ptStackable.count = count;
                  inventory.epicpieceitems.add(ptStackable);
                  account.getEpicPieceBox().updateEpicPiece(ptStackable);
               } else if (newtype == 21) {
                  if (goodid != 70001 && goodid != 70002 && goodid != 70003 && goodid != 30015 && goodid != 30017) {
                     account.getMoneyBox().addCnt(index, count);
                     PT_MONEY_ITEM ptMoneyItem3 = new PT_MONEY_ITEM();
                     ptMoneyItem3.count = count;
                     paycurrencylist.add(ptMoneyItem3);
                  }
               } else if (newtype == 20 && goodid != 70001 && goodid != 70002 && goodid != 70003 && goodid != 30015 && goodid != 30017) {
                  role.getMoneyBox().addCnt(index, count);
                  PT_MONEY_ITEM ptMoneyItem3 = new PT_MONEY_ITEM();
                  ptMoneyItem3.count = count;
                  paycurrencylist.add(ptMoneyItem3);
               }
            } else if (EquipDataPool.index2Equip.get(index) == null) {
               Map<Integer, Skin> equipItemMap = ItemDataPool.skinItemMap;
               if (equipItemMap.get(index) != null) {
               }
            } else {
               int newtype = ((Equip)EquipDataPool.index2Equip.get(index)).getEquiptype();

               for(int i = 0; i < count; ++i) {
                  if (newtype != 0 && newtype != 1 && newtype != 2 && newtype != 3 && newtype != 4 && newtype != 5 && newtype != 6 && newtype != 7 && newtype != 8 && newtype != 9 && newtype != 10) {
                     if (newtype == 12) {
                        PT_EQUIP pt_equip = new PT_EQUIP();
                        pt_equip.index = index;
                        pt_equip.guid = IdGenerator.getNextId();
                        pt_equip.quality = 100;
                        inventory.titleitems.add(pt_equip);
                        role.getTitleBox().addTitle(pt_equip);
                     } else if (newtype == 11 || newtype == 13 || newtype == 14 || newtype == 15 || newtype == 16 || newtype == 17 || newtype == 18 || newtype == 19 || newtype == 20) {
                        PT_EQUIP pt_equip = EquipDataPool.createEquip(index);
                        inventory.equipitems.add(pt_equip);
                        role.getEquipBox().addEquip(pt_equip);
                     }
                  } else {
                     PT_AVATAR_ITEM ptAvatarItem = new PT_AVATAR_ITEM();
                     ptAvatarItem.index = index;
                     ptAvatarItem.guid = IdGenerator.getNextId();
                     inventory.avataritems.add(ptAvatarItem);
                     role.getAvatarBox().addAvatar(ptAvatarItem);
                  }
               }
            }
         }

         PT_SHOP_BUY_COUNT pt_shop_buy_count = new PT_SHOP_BUY_COUNT();
         pt_shop_buy_count.goodsid = goodid;
         pt_shop_buy_count.count = count;
         pt_shop_buy_count.lastbuytime = TimeUtil.currS();
         if ("character".equals(limittype)) {
            role.getRoleShopInfoBox().addBuy(pt_shop_buy_count);
         } else {
            account.getAccShopInfoBox().addBuy(pt_shop_buy_count);
         }
      }

      paymentcurrency.currency = paycurrencylist;
      items.inventory = inventory;
      currency.currency = currencylist;
      if (isitem) {
         rewards.items = items;
         if (this.isAccountMoney(moneytype)) {
         }

         currencylist.add(ptMoneyItem);
      } else {
         rewards.paymentcurrency = paymentcurrency;
      }

      rewards.currency = currency;
      resCeraShopBuy.buy = reqCeraShopBuy.buy;
      resCeraShopBuy.rewards = rewards;
      resCeraShopBuy.transId = reqCeraShopBuy.transId;
      role.save();
      account.save();
      MessagePusher.pushMessage((IoSession)session, resCeraShopBuy);
   }

   public boolean isAccountMoney(int index) {
      switch (index) {
         case 0:
            return false;
         case 2:
            return true;
         case 1990000084:
            return false;
         case 2013000001:
            return true;
         case 2013000002:
            return false;
         case 2013100897:
            return false;
         case 2013100902:
            return false;
         case 2013101309:
            return false;
         case 2013101454:
            return false;
         case 2013101484:
            return true;
         case 2013102100:
            return false;
         case 2013102101:
            return false;
         case 2013102924:
            return true;
         case 2013103791:
            return true;
         case 2013104144:
            return true;
         case 2013104267:
            return true;
         case 2013104686:
            return false;
         case 2013105779:
            return true;
         case 2013106097:
            return true;
         case 2013106215:
            return true;
         default:
            return false;
      }
   }

   @RequestMapping
   public void REQ_INVEN_EXTEND(IoSession session, REQ_INVEN_EXTEND reqInvenExtend) {
      Role role = SessionUtils.getRoleBySession(session);
      Account account = SessionUtils.getAccountBySession(session);
      RES_INVEN_EXTEND resInvenExtend = new RES_INVEN_EXTEND();
      int tylorcost = 0;
      int count = role.getEquipBox().getMaxcount() + 5;
      if (reqInvenExtend.type == null) {
         role.getEquipBox().setMaxcount(count);
         role.getMaterialBox().setMaxcount(count);
         role.getCardBox().setMaxcount(count);
         role.getConsumableBox().setMaxcount(count);
         role.getEmblemBox().setMaxcount(count);
         tylorcost = this.getExtendCost(count);
         account.getMoneyBox().subCnt(2013000001, tylorcost);
      }

      List<PT_MONEY_ITEM> paycurrencylist = new ArrayList();
      PT_MONEY_ITEM ptMoneyItem = new PT_MONEY_ITEM();
      ptMoneyItem.index = 2013000001;
      ptMoneyItem.count = account.getMoneyBox().getMoneyItem(2013000001).count;
      paycurrencylist.add(ptMoneyItem);
      resInvenExtend.count = count;
      resInvenExtend.accountcurrency = paycurrencylist;
      resInvenExtend.transId = reqInvenExtend.transId;
      MessagePusher.pushMessage((IoSession)session, resInvenExtend);
   }

   public int getExtendCost(int count) {
      int cost = 800;
      if (count == 50) {
         cost = 1600;
      } else if (count == 55) {
         cost = 3200;
      } else if (count == 60) {
         cost = 6400;
      } else if (count == 65) {
         cost = 12800;
      } else if (count == 70) {
         cost = 25600;
      } else if (count != 75 && count != 80) {
         if (count == 85 || count == 90) {
            cost = 80000;
         }
      } else {
         cost = 50000;
      }

      return cost;
   }

   @RequestMapping
   public void ReqCeraShopBuyInfo(IoSession session, REQ_CERA_SHOP_BUY_INFO reqCeraShopBuyInfo) {
      RES_CERA_SHOP_BUY_INFO res_cera_shop_buy_info = new RES_CERA_SHOP_BUY_INFO();
      Account account = SessionUtils.getAccountBySession(session);
      Role role = SessionUtils.getRoleBySession(session);
      PT_CERA_SHOP_INFO accptCeraShopInfo = new PT_CERA_SHOP_INFO();
      PT_CERA_SHOP_INFO roleptCeraShopInfo = new PT_CERA_SHOP_INFO();
      if (account.getAccShopInfoBox() == null) {
         account.setAccShopInfoBox(new AccShopInfoBox());
      }

      if (account.getAccShopInfoBox().getBuy() != null) {
         accptCeraShopInfo.buy = account.getAccShopInfoBox().getBuy();
      }

      if (role.getRoleShopInfoBox().getBuy() != null) {
         roleptCeraShopInfo.buy = role.getRoleShopInfoBox().getBuy();
      }

      res_cera_shop_buy_info.account = accptCeraShopInfo;
      res_cera_shop_buy_info.character = roleptCeraShopInfo;
      res_cera_shop_buy_info.transId = reqCeraShopBuyInfo.transId;
      MessagePusher.pushMessage((IoSession)session, res_cera_shop_buy_info);
   }

   public int getcost(int type, int level) {
      int cost = 200;
      if (level < 10) {
         cost = 200 * level;
      } else if (10 <= level && level < 15) {
         cost = 50000;
      } else if (15 <= level && level < 20) {
         cost = 80000;
      } else if (20 <= level && level < 30) {
         cost = 100000;
      } else if (30 <= level && level < 40) {
         cost = 150000;
      }

      if (type == 1) {
         cost *= 10;
      }

      return cost;
   }

   public int getReinforceRate(int index) {
      switch (index) {
         case 2013103631:
            return 30;
         case 2013103633:
            return 90;
         case 2013103666:
            return 50;
         case 2013103769:
            return 10;
         case 2013105097:
            return 70;
         default:
            return 50;
      }
   }
}
