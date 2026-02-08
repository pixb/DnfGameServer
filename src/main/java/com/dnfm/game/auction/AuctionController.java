package com.dnfm.game.auction;

import com.dnfm.common.thread.IdGenerator;
import com.dnfm.common.utils.Util;
import com.dnfm.game.auction.model.AuctionBox;
import com.dnfm.game.auction.model.AuctionCache;
import com.dnfm.game.auction.model.AuctionModel;
import com.dnfm.game.bag.model.AccountMoneyBox;
import com.dnfm.game.bag.serveice.BagService;
import com.dnfm.game.mail.MailBox;
import com.dnfm.game.mail.model.MailItem;
import com.dnfm.game.role.model.Account;
import com.dnfm.game.role.model.Role;
import com.dnfm.game.role.service.AccountService;
import com.dnfm.game.role.service.RoleService;
import com.dnfm.game.utils.TimeUtil;
import com.dnfm.mina.annotation.RequestMapping;
import com.dnfm.mina.cache.DataCache;
import com.dnfm.mina.cache.SessionUtils;
import com.dnfm.mina.message.MessagePusher;
import com.dnfm.mina.protobuf.PT_AUCTION_EQUIP;
import com.dnfm.mina.protobuf.PT_AUCTION_ITEM_INDEX;
import com.dnfm.mina.protobuf.PT_AUCTION_ITEM_PRICE_INFO;
import com.dnfm.mina.protobuf.PT_AUCTION_STACKABLE;
import com.dnfm.mina.protobuf.REQ_AUCTION_BUY_AT_ONCE;
import com.dnfm.mina.protobuf.REQ_AUCTION_CATEGORY_ITEM_LIST;
import com.dnfm.mina.protobuf.REQ_AUCTION_HISTORY;
import com.dnfm.mina.protobuf.REQ_AUCTION_ITEM_PRICE_LIST;
import com.dnfm.mina.protobuf.REQ_AUCTION_MY_REGISTERED_ITEM_LIST;
import com.dnfm.mina.protobuf.REQ_AUCTION_REGISTER_CANCEL;
import com.dnfm.mina.protobuf.REQ_AUCTION_REGISTER_COMPLETE;
import com.dnfm.mina.protobuf.REQ_AUCTION_REGISTER_ITEM;
import com.dnfm.mina.protobuf.REQ_ITEM_AVR_PRICE;
import com.dnfm.mina.protobuf.RES_AUCTION_BUY_AT_ONCE;
import com.dnfm.mina.protobuf.RES_AUCTION_CATEGORY_LIST;
import com.dnfm.mina.protobuf.RES_AUCTION_HISTORY;
import com.dnfm.mina.protobuf.RES_AUCTION_ITEM_PRICE_LIST;
import com.dnfm.mina.protobuf.RES_AUCTION_MY_REGISTERED_ITEM_LIST;
import com.dnfm.mina.protobuf.RES_AUCTION_REGISTER_CANCEL;
import com.dnfm.mina.protobuf.RES_AUCTION_REGISTER_COMPLETE;
import com.dnfm.mina.protobuf.RES_AUCTION_REGISTER_ITEM;
import com.dnfm.mina.protobuf.RES_ITEM_AVR_PRICE;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class AuctionController {
   private static final Logger log = LoggerFactory.getLogger(AuctionController.class);
   @Autowired
   RoleService roleService;
   @Autowired
   AccountService accountService;

   public static void main(String[] args) {
      String s = "000101";
      int i = Integer.parseInt(s);
      System.out.println(i);
   }

   @RequestMapping
   public void REQ_AUCTION_MY_REGISTERED_ITEM_LIST(IoSession session, REQ_AUCTION_MY_REGISTERED_ITEM_LIST req_auction_my_registered_item_list) {
      RES_AUCTION_MY_REGISTERED_ITEM_LIST res_auction_my_registered_item_list = new RES_AUCTION_MY_REGISTERED_ITEM_LIST();
      Role role = SessionUtils.getRoleBySession(session);
      AuctionBox auctionBox = role.getAuctionBox();
      Map<Long, PT_AUCTION_EQUIP> equipMap = auctionBox.getEquipMapReg();
      Map<Long, PT_AUCTION_STACKABLE> stackableMap = auctionBox.getStackableMapReg();
      long now = TimeUtil.currS();
      if (!Util.isEmpty(equipMap)) {
         List<Long> removeList = new ArrayList();

         for(Map.Entry<Long, PT_AUCTION_EQUIP> entry : equipMap.entrySet()) {
            PT_AUCTION_EQUIP equip = (PT_AUCTION_EQUIP)entry.getValue();
            if (equip.enddate != null && now >= equip.enddate) {
               removeList.add(entry.getKey());
            }
         }

         for(Long auid : removeList) {
            PT_AUCTION_EQUIP equip = auctionBox.removeEquipReg(auid);
            AuctionCache.removeAuction(equip.auid, equip.index);
            if (equip.tera != null && equip.tera > 0) {
               String title2 = "拍卖行-售出得到的泰拉";
               String msg2 = "[拍卖行]售出得到的泰拉";
               List<MailItem> mailItemList2 = new ArrayList();
               MailItem mailItem2 = new MailItem();
               mailItem2.index = 2013000001;
               mailItem2.cnt = (int)((double)equip.tera * 0.9);
               mailItemList2.add(mailItem2);
               role.getMailBox().addMail(title2, msg2, mailItemList2);
            } else {
               String title2 = "拍卖行-返还到期未售出的物品";
               String msg2 = "[拍卖行]返还到期未售出的物品";
               List<MailItem> mailItemList2 = new ArrayList();
               MailItem mailItem2 = new MailItem();
               mailItem2.index = equip.index;
               mailItem2.cnt = equip.count;
               mailItemList2.add(mailItem2);
               role.getMailBox().addMail(title2, msg2, mailItemList2);
            }
         }

         res_auction_my_registered_item_list.equip = auctionBox.getEquipRegList();
      }

      if (!Util.isEmpty(stackableMap)) {
         List<Long> removeList = new ArrayList();

         for(Map.Entry<Long, PT_AUCTION_STACKABLE> entry : stackableMap.entrySet()) {
            PT_AUCTION_STACKABLE stackable = (PT_AUCTION_STACKABLE)entry.getValue();
            if (stackable.enddate != null && now >= stackable.enddate) {
               removeList.add(entry.getKey());
            }
         }

         for(Long auid : removeList) {
            PT_AUCTION_STACKABLE stackable = auctionBox.removeStackableReg(auid);
            AuctionCache.removeAuction(stackable.auid, stackable.index);
            if (stackable.count == 0) {
               String title = "拍卖行-出售得到的泰拉";
               String msg = "[拍卖行]出售得到的泰拉";
               List<MailItem> mailItemList = new ArrayList();
               MailItem mailItem = new MailItem();
               mailItem.index = 2013000001;
               mailItem.cnt = (int)((double)stackable.tera * 0.9);
               mailItemList.add(mailItem);
               role.getMailBox().addMail(title, msg, mailItemList);
            } else if (stackable.tera != null && stackable.tera > 0) {
               String title = "拍卖行-出售得到的泰拉";
               String msg = "[拍卖行]出售得到的泰拉";
               List<MailItem> mailItemList = new ArrayList();
               MailItem mailItem = new MailItem();
               mailItem.index = 2013000001;
               mailItem.cnt = (int)((double)stackable.tera * 0.9);
               mailItemList.add(mailItem);
               role.getMailBox().addMail(title, msg, mailItemList);
               String title2 = "拍卖行-返还未售出的物品";
               String msg2 = "[拍卖行]返还未售出的物品";
               List<MailItem> mailItemList2 = new ArrayList();
               MailItem mailItem2 = new MailItem();
               mailItem2.index = stackable.index;
               mailItem2.cnt = stackable.count;
               mailItemList2.add(mailItem2);
               role.getMailBox().addMail(title2, msg2, mailItemList2);
            } else {
               String title2 = "拍卖行-返还未售出的物品";
               String msg2 = "[拍卖行]返还未售出的物品";
               List<MailItem> mailItemList2 = new ArrayList();
               MailItem mailItem2 = new MailItem();
               mailItem2.index = stackable.index;
               mailItem2.cnt = stackable.count;
               mailItemList2.add(mailItem2);
               role.getMailBox().addMail(title2, msg2, mailItemList2);
            }
         }

         res_auction_my_registered_item_list.stackable = auctionBox.getStackableRegList();
      }

      res_auction_my_registered_item_list.transId = req_auction_my_registered_item_list.transId;
      MessagePusher.pushMessage((IoSession)session, res_auction_my_registered_item_list);
   }

   @RequestMapping
   public void REQ_AUCTION_CATEGORY_ITEM_LIST(IoSession session, REQ_AUCTION_CATEGORY_ITEM_LIST req_auction_category_item_list) {
      RES_AUCTION_CATEGORY_LIST res_auction_category_list = new RES_AUCTION_CATEGORY_LIST();
      int type = (Integer)DataCache.auctionCategoryType.get(req_auction_category_item_list.category);
      res_auction_category_list.category = req_auction_category_item_list.category;
      res_auction_category_list.item = new ArrayList();
      res_auction_category_list.transId = req_auction_category_item_list.transId;
      MessagePusher.pushMessage((IoSession)session, res_auction_category_list);
   }

   @RequestMapping
   public void REQ_AUCTION_ITEM_PRICE_LIST(IoSession session, REQ_AUCTION_ITEM_PRICE_LIST req_auction_item_price_list) {
      RES_AUCTION_ITEM_PRICE_LIST res_auction_item_price_list = new RES_AUCTION_ITEM_PRICE_LIST();
      res_auction_item_price_list.category = req_auction_item_price_list.category;
      res_auction_item_price_list.index = req_auction_item_price_list.index;
      int type = (Integer)DataCache.auctionCategoryType.get(req_auction_item_price_list.category);
      res_auction_item_price_list.list = AuctionCache.GET_AUCTION_ITEM_PRICE_LIST(req_auction_item_price_list.index, type);
      res_auction_item_price_list.transId = req_auction_item_price_list.transId;
      MessagePusher.pushMessage((IoSession)session, res_auction_item_price_list);
   }

   @RequestMapping
   public void REQ_AUCTION_BUY_AT_ONCE(IoSession session, REQ_AUCTION_BUY_AT_ONCE req_auction_buy_at_once) {
      Role role = SessionUtils.getRoleBySession(session);
      AuctionBox auctionBox = role.getAuctionBox();
      Account account = SessionUtils.getAccountBySession(session);
      RES_AUCTION_BUY_AT_ONCE res_auction_buy_at_once = new RES_AUCTION_BUY_AT_ONCE();
      res_auction_buy_at_once.error = 3;
      res_auction_buy_at_once.transId = req_auction_buy_at_once.transId;
      MessagePusher.pushMessage((IoSession)session, res_auction_buy_at_once);
      if (role == null) {
         int totalTera = req_auction_buy_at_once.price * req_auction_buy_at_once.qty;
         AccountMoneyBox moneyBox = account.getMoneyBox();
         int curTera = moneyBox.getTylorCnt();
         if (curTera < totalTera) {
            log.error("UNREACH==购买拍卖行物品失败，泰拉不足");
         } else {
            int type = (Integer)DataCache.auctionCategoryType.get(req_auction_buy_at_once.category);
            if (type == 1) {
               int res = AuctionCache.buyStackable(req_auction_buy_at_once.index, req_auction_buy_at_once.price, req_auction_buy_at_once.qty);
               if (res == -1) {
                  log.error("ERROR==buy stackable error");
                  RES_AUCTION_BUY_AT_ONCE res_auction_buy_at_once1 = new RES_AUCTION_BUY_AT_ONCE();
                  res_auction_buy_at_once1.error = 11;
                  res_auction_buy_at_once1.transId = req_auction_buy_at_once.transId;
                  MessagePusher.pushMessage((IoSession)session, res_auction_buy_at_once1);
                  return;
               }
            } else {
               int res = AuctionCache.buyEquip(req_auction_buy_at_once.index, req_auction_buy_at_once.price, req_auction_buy_at_once.qty);
               if (res == -1) {
                  log.error("ERROR==buy equip error");
                  RES_AUCTION_BUY_AT_ONCE res_auction_buy_at_once1 = new RES_AUCTION_BUY_AT_ONCE();
                  res_auction_buy_at_once1.error = 11;
                  res_auction_buy_at_once1.transId = req_auction_buy_at_once.transId;
                  MessagePusher.pushMessage((IoSession)session, res_auction_buy_at_once1);
                  return;
               }
            }

            res_auction_buy_at_once.categoryitemlist = new ArrayList();

            for(AuctionModel auctionModel : (List<AuctionModel>)DataCache.auctionCategoryMap.get(req_auction_buy_at_once.category)) {
               PT_AUCTION_ITEM_INDEX pt_auction_item_index = new PT_AUCTION_ITEM_INDEX();
               pt_auction_item_index.index = auctionModel.index;
               if (type == 1) {
                  pt_auction_item_index.qty = AuctionCache.getStackableCnt(auctionModel.index);
               } else {
                  pt_auction_item_index.qty = AuctionCache.getEquipCnt(auctionModel.index);
               }

               res_auction_buy_at_once.categoryitemlist.add(pt_auction_item_index);
            }

            res_auction_buy_at_once.itempricelist = new ArrayList();
            PT_AUCTION_ITEM_PRICE_INFO pt_auction_item_price_info = new PT_AUCTION_ITEM_PRICE_INFO();
            pt_auction_item_price_info.price = req_auction_buy_at_once.price;
            pt_auction_item_price_info.count = AuctionCache.getPriceListAllCnt(req_auction_buy_at_once.index, req_auction_buy_at_once.price, type);
            res_auction_buy_at_once.itempricelist.add(pt_auction_item_price_info);
            long enddate = TimeUtil.currS() + 86400L;
            if (type == 1) {
               auctionBox.addStackableBuy(req_auction_buy_at_once.price, enddate, req_auction_buy_at_once.qty, req_auction_buy_at_once.price, req_auction_buy_at_once.index, req_auction_buy_at_once.qty);
            } else {
               auctionBox.addEquipBuy(req_auction_buy_at_once.price, enddate, req_auction_buy_at_once.qty, req_auction_buy_at_once.price, req_auction_buy_at_once.index, req_auction_buy_at_once.qty);
            }

            res_auction_buy_at_once.transId = req_auction_buy_at_once.transId;
            MessagePusher.pushMessage((IoSession)session, res_auction_buy_at_once);
            String title = "拍卖行购买物品成功";
            String msg = "恭喜您在拍卖行购买物品成功-点击领取";
            MailItem mailItem = new MailItem();
            mailItem.cnt = req_auction_buy_at_once.qty;
            mailItem.index = req_auction_buy_at_once.index;
            List<MailItem> mailItemList = new ArrayList();
            mailItemList.add(mailItem);
            role.getMailBox().addMail(title, msg, mailItemList);
            moneyBox.subCnt(2013000001, totalTera);
            account.save();
         }
      }
   }

   @RequestMapping
   public void REQ_ITEM_AVR_PRICE(IoSession session, REQ_ITEM_AVR_PRICE req_item_avr_price) {
      RES_ITEM_AVR_PRICE res_item_avr_price = new RES_ITEM_AVR_PRICE();
      List<Integer> prices = AuctionCache.getPriceList(req_item_avr_price.index);
      Integer avrPrice = (Integer)AuctionCache.avrPriceMap.get(req_item_avr_price.index);
      if (!Util.isEmpty(prices) && avrPrice != null) {
         res_item_avr_price.index = req_item_avr_price.index;
         res_item_avr_price.price = avrPrice;
         res_item_avr_price.prices = prices;
         res_item_avr_price.transId = req_item_avr_price.transId;
         MessagePusher.pushMessage((IoSession)session, res_item_avr_price);
      } else {
         log.error("UNREACH==price null==" + req_item_avr_price.index);
      }
   }

   @RequestMapping
   public void REQ_AUCTION_REGISTER_ITEM(IoSession session, REQ_AUCTION_REGISTER_ITEM req_auction_register_item) {
      Role role = SessionUtils.getRoleBySession(session);
      Account account = SessionUtils.getAccountBySession(session);
      AuctionBox auctionBox = role.getAuctionBox();
      RES_AUCTION_REGISTER_ITEM res_auction_register_item = new RES_AUCTION_REGISTER_ITEM();
      res_auction_register_item.tab = req_auction_register_item.tab;
      res_auction_register_item.index = req_auction_register_item.index;
      res_auction_register_item.qty = req_auction_register_item.qty;
      if (req_auction_register_item.guid == null) {
         res_auction_register_item.stackable = new ArrayList();
         PT_AUCTION_STACKABLE pt_auction_stackable = new PT_AUCTION_STACKABLE();
         pt_auction_stackable.charguid = role.getUid();
         pt_auction_stackable.type = 1;
         pt_auction_stackable.auid = IdGenerator.getNextId();
         pt_auction_stackable.price = req_auction_register_item.price;
         pt_auction_stackable.enddate = TimeUtil.currS() + 86400L;
         pt_auction_stackable.registcount = req_auction_register_item.qty;
         pt_auction_stackable.buyprice = req_auction_register_item.price;
         pt_auction_stackable.index = req_auction_register_item.index;
         pt_auction_stackable.count = req_auction_register_item.qty;
         pt_auction_stackable.charguid = role.getUid();
         res_auction_register_item.stackable.add(pt_auction_stackable);
         AuctionCache.addAuctionStackable(pt_auction_stackable);
         auctionBox.addStackableReg(pt_auction_stackable);
         BagService.subItem(role, account, pt_auction_stackable.index, pt_auction_stackable.count, 0L);
      } else {
         res_auction_register_item.equip = new ArrayList();
         PT_AUCTION_EQUIP pt_auction_equip = new PT_AUCTION_EQUIP();
         pt_auction_equip.charguid = role.getUid();
         pt_auction_equip.guid = req_auction_register_item.guid;
         pt_auction_equip.type = 1;
         pt_auction_equip.auid = IdGenerator.getNextId();
         pt_auction_equip.price = req_auction_register_item.price;
         pt_auction_equip.enddate = TimeUtil.currS() + 86400L;
         pt_auction_equip.registcount = req_auction_register_item.qty;
         pt_auction_equip.buyprice = req_auction_register_item.price;
         pt_auction_equip.index = req_auction_register_item.index;
         pt_auction_equip.count = req_auction_register_item.qty;
         pt_auction_equip.charguid = role.getUid();
         res_auction_register_item.equip.add(pt_auction_equip);
         AuctionCache.addAuctionEquip(pt_auction_equip);
         auctionBox.addEquipReg(pt_auction_equip);
         BagService.subItem(role, account, pt_auction_equip.index, pt_auction_equip.count, pt_auction_equip.guid);
      }

      role.setAuctionBox(auctionBox);
      role.save();
      res_auction_register_item.transId = req_auction_register_item.transId;
      MessagePusher.pushMessage((IoSession)session, res_auction_register_item);
   }

   @RequestMapping
   public void REQ_AUCTION_REGISTER_CANCEL(IoSession session, REQ_AUCTION_REGISTER_CANCEL req_auction_register_cancel) {
      Role role = SessionUtils.getRoleBySession(session);
      AuctionBox auctionBox = role.getAuctionBox();
      MailBox mailBox = role.getMailBox();
      RES_AUCTION_REGISTER_CANCEL res_auction_register_cancel = new RES_AUCTION_REGISTER_CANCEL();
      res_auction_register_cancel.category = req_auction_register_cancel.category;
      res_auction_register_cancel.auid = req_auction_register_cancel.auid;
      AuctionCache.removeAuction(req_auction_register_cancel.auid, req_auction_register_cancel.index);
      int removeRes = auctionBox.removeReg(req_auction_register_cancel.auid);
      if (removeRes == 1) {
         PT_AUCTION_STACKABLE stackable = auctionBox.getStackableReg(req_auction_register_cancel.auid);
         if (stackable.count == 0) {
            String title = "拍卖行-出售得到的泰拉";
            String msg = "[拍卖行]出售得到的泰拉";
            List<MailItem> mailItemList = new ArrayList();
            MailItem mailItem = new MailItem();
            mailItem.index = 2013000001;
            mailItem.cnt = (int)((double)stackable.tera * 0.9);
            mailItemList.add(mailItem);
            role.getMailBox().addMail(title, msg, mailItemList);
         } else if (stackable.tera != null && stackable.tera > 0) {
            String title = "拍卖行-出售得到的泰拉";
            String msg = "[拍卖行]出售得到的泰拉";
            List<MailItem> mailItemList = new ArrayList();
            MailItem mailItem = new MailItem();
            mailItem.index = 2013000001;
            mailItem.cnt = (int)((double)stackable.tera * 0.9);
            mailItemList.add(mailItem);
            role.getMailBox().addMail(title, msg, mailItemList);
            String title2 = "拍卖行-返还未售出的物品";
            String msg2 = "[拍卖行]返还未售出的物品";
            List<MailItem> mailItemList2 = new ArrayList();
            MailItem mailItem2 = new MailItem();
            mailItem2.index = stackable.index;
            mailItem2.cnt = stackable.count;
            mailItemList2.add(mailItem2);
            role.getMailBox().addMail(title2, msg2, mailItemList2);
         } else {
            String title2 = "拍卖行-返还未售出的物品";
            String msg2 = "[拍卖行]返还未售出的物品";
            List<MailItem> mailItemList2 = new ArrayList();
            MailItem mailItem2 = new MailItem();
            mailItem2.index = stackable.index;
            mailItem2.cnt = stackable.count;
            mailItemList2.add(mailItem2);
            role.getMailBox().addMail(title2, msg2, mailItemList2);
         }

         auctionBox.removeStackableReg(req_auction_register_cancel.auid);
      } else {
         PT_AUCTION_EQUIP equip = auctionBox.getEquipReg(req_auction_register_cancel.auid);
         if (equip.count == 0) {
            String title = "拍卖行-出售得到的泰拉";
            String msg = "[拍卖行]出售得到的泰拉";
            List<MailItem> mailItemList = new ArrayList();
            MailItem mailItem = new MailItem();
            mailItem.index = 2013000001;
            mailItem.cnt = (int)((double)equip.tera * 0.9);
            mailItemList.add(mailItem);
            role.getMailBox().addMail(title, msg, mailItemList);
         } else {
            String title2 = "拍卖行-返还未售出的物品";
            String msg2 = "[拍卖行]返还未售出的物品";
            List<MailItem> mailItemList2 = new ArrayList();
            MailItem mailItem2 = new MailItem();
            mailItem2.index = equip.index;
            mailItem2.cnt = equip.count;
            mailItemList2.add(mailItem2);
            role.getMailBox().addMail(title2, msg2, mailItemList2);
         }

         auctionBox.removeEquipReg(req_auction_register_cancel.auid);
      }

      role.setAuctionBox(auctionBox);
      role.save();
      res_auction_register_cancel.transId = req_auction_register_cancel.transId;
      MessagePusher.pushMessage((IoSession)session, res_auction_register_cancel);
   }

   @RequestMapping
   public void REQ_AUCTION_REGISTER_COMPLETE(IoSession session, REQ_AUCTION_REGISTER_COMPLETE req_auction_register_complete) {
      Role role = SessionUtils.getRoleBySession(session);
      AuctionBox auctionBox = role.getAuctionBox();
      RES_AUCTION_REGISTER_COMPLETE res_auction_register_complete = new RES_AUCTION_REGISTER_COMPLETE();
      res_auction_register_complete.category = (Integer)AuctionCache.index2Category.get(req_auction_register_complete.itemindex);
      res_auction_register_complete.auid = req_auction_register_complete.auid;
      AuctionCache.removeAuction(req_auction_register_complete.auid, req_auction_register_complete.itemindex);
      int res = auctionBox.completeReg(req_auction_register_complete.auid);
      if (res == 1) {
         PT_AUCTION_STACKABLE stackable = auctionBox.getStackableReg(req_auction_register_complete.auid);
         if (stackable.count == 0) {
            String title = "拍卖行-出售得到的泰拉";
            String msg = "[拍卖行]出售得到的泰拉";
            List<MailItem> mailItemList = new ArrayList();
            MailItem mailItem = new MailItem();
            mailItem.index = 2013000001;
            mailItem.cnt = (int)((double)stackable.tera * 0.9);
            mailItemList.add(mailItem);
            role.getMailBox().addMail(title, msg, mailItemList);
         } else if (stackable.tera != null && stackable.tera > 0) {
            String title = "拍卖行-出售得到的泰拉";
            String msg = "[拍卖行]出售得到的泰拉";
            List<MailItem> mailItemList = new ArrayList();
            MailItem mailItem = new MailItem();
            mailItem.index = 2013000001;
            mailItem.cnt = (int)((double)stackable.tera * 0.9);
            mailItemList.add(mailItem);
            role.getMailBox().addMail(title, msg, mailItemList);
            String title2 = "拍卖行-返还未售出的物品";
            String msg2 = "[拍卖行]返还未售出的物品";
            List<MailItem> mailItemList2 = new ArrayList();
            MailItem mailItem2 = new MailItem();
            mailItem2.index = stackable.index;
            mailItem2.cnt = stackable.count;
            mailItemList2.add(mailItem2);
            role.getMailBox().addMail(title2, msg2, mailItemList2);
         } else {
            String title2 = "拍卖行-返还未售出的物品";
            String msg2 = "[拍卖行]返还未售出的物品";
            List<MailItem> mailItemList2 = new ArrayList();
            MailItem mailItem2 = new MailItem();
            mailItem2.index = stackable.index;
            mailItem2.cnt = stackable.count;
            mailItemList2.add(mailItem2);
            role.getMailBox().addMail(title2, msg2, mailItemList2);
         }

         auctionBox.removeStackableReg(req_auction_register_complete.auid);
      } else {
         PT_AUCTION_EQUIP equip = auctionBox.getEquipReg(req_auction_register_complete.auid);
         if (equip.count == 0) {
            String title = "拍卖行-出售得到的泰拉";
            String msg = "[拍卖行]出售得到的泰拉";
            List<MailItem> mailItemList = new ArrayList();
            MailItem mailItem = new MailItem();
            mailItem.index = 2013000001;
            mailItem.cnt = (int)((double)equip.tera * 0.9);
            mailItemList.add(mailItem);
            role.getMailBox().addMail(title, msg, mailItemList);
         } else {
            String title2 = "拍卖行-返还未售出的物品";
            String msg2 = "[拍卖行]返还未售出的物品";
            List<MailItem> mailItemList2 = new ArrayList();
            MailItem mailItem2 = new MailItem();
            mailItem2.index = equip.index;
            mailItem2.cnt = equip.count;
            mailItemList2.add(mailItem2);
            role.getMailBox().addMail(title2, msg2, mailItemList2);
         }

         auctionBox.removeEquipReg(req_auction_register_complete.auid);
      }

      role.setAuctionBox(auctionBox);
      role.save();
      res_auction_register_complete.transId = req_auction_register_complete.transId;
      MessagePusher.pushMessage((IoSession)session, res_auction_register_complete);
   }

   @RequestMapping
   public void REQ_AUCTION_HISTORY(IoSession session, REQ_AUCTION_HISTORY req_auction_history) {
      RES_AUCTION_HISTORY res_auction_history = new RES_AUCTION_HISTORY();
      Role role = SessionUtils.getRoleBySession(session);
      AuctionBox auctionBox = role.getAuctionBox();
      List<PT_AUCTION_STACKABLE> stackableBuyList = auctionBox.getStackableBuyList();
      List<PT_AUCTION_EQUIP> equipBuyList = auctionBox.getEquipBuyList();
      List<PT_AUCTION_STACKABLE> stackableSellList = auctionBox.getStackableSellList();
      List<PT_AUCTION_EQUIP> equipSellList = auctionBox.getEquipSellList();
      if (req_auction_history.type == null) {
         if (stackableSellList != null) {
            res_auction_history.stackable = stackableSellList;
         }

         if (equipSellList != null) {
            res_auction_history.equip = equipSellList;
         }
      } else if (req_auction_history.type == 1) {
         res_auction_history.type = 1;
         if (stackableBuyList != null) {
            res_auction_history.stackable = stackableBuyList;
         }

         if (equipBuyList != null) {
            res_auction_history.equip = equipBuyList;
         }
      } else {
         log.error("UNREACH==拍卖历史记录type==" + req_auction_history.type);
      }

      res_auction_history.transId = req_auction_history.transId;
      MessagePusher.pushMessage((IoSession)session, res_auction_history);
   }
}
