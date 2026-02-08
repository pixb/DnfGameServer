package com.dnfm.game.shop;

import com.dnfm.game.bag.model.CeraShopBuyInfo;
import com.dnfm.game.bag.model.MaterialBox;
import com.dnfm.game.bag.model.RoleShopInfoBox;
import com.dnfm.game.role.model.Account;
import com.dnfm.game.role.model.Role;
import com.dnfm.game.shop.model.ShopCache;
import com.dnfm.mina.annotation.RequestMapping;
import com.dnfm.mina.cache.SessionUtils;
import com.dnfm.mina.message.MessagePusher;
import com.dnfm.mina.protobuf.PT_GACHA_MILEAGE;
import com.dnfm.mina.protobuf.PT_GACHA_RESULT;
import com.dnfm.mina.protobuf.PT_REMOVEITEMS;
import com.dnfm.mina.protobuf.PT_STACKABLE;
import com.dnfm.mina.protobuf.REQ_CERA_GACHA_BUY;
import com.dnfm.mina.protobuf.REQ_CERA_GACHA_MILEAGE;
import com.dnfm.mina.protobuf.RES_CERA_GACHA_BUY;
import com.dnfm.mina.protobuf.RES_CERA_GACHA_MILEAGE;
import java.util.ArrayList;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

@Controller
public class ShopController {
   private static final Logger log = LoggerFactory.getLogger(ShopController.class);

   @RequestMapping
   public void REQ_CERA_GACHA_MILEAGE(IoSession session, REQ_CERA_GACHA_MILEAGE req_cera_gacha_mileage) {
      RES_CERA_GACHA_MILEAGE res_cera_gacha_mileage = new RES_CERA_GACHA_MILEAGE();
      res_cera_gacha_mileage.mileage = new ArrayList();

      for(int i = 3; i < 10; ++i) {
         PT_GACHA_MILEAGE pt_gacha_mileage = new PT_GACHA_MILEAGE();
         pt_gacha_mileage.index = i;
         res_cera_gacha_mileage.mileage.add(pt_gacha_mileage);
      }

      res_cera_gacha_mileage.transId = req_cera_gacha_mileage.transId;
      MessagePusher.pushMessage((IoSession)session, res_cera_gacha_mileage);
   }

   @RequestMapping
   public void REQ_CERA_GACHA_BUY(IoSession session, REQ_CERA_GACHA_BUY req_cera_gacha_buy) {
      Role role = SessionUtils.getRoleBySession(session);
      Account account = SessionUtils.getAccountBySession(session);
      CeraShopBuyInfo ceraShopBuyInfo = role.getCeraShopBuyInfo();
      MaterialBox materialBox = role.getMaterialBox();
      int keyCnt = materialBox.getCnt(2013101405);
      if (keyCnt == -1) {
         log.error("ERROR==keyCnt==-1");
      } else {
         int index = req_cera_gacha_buy.index;
         int consumeCnt = 3;
         if (index == 50002) {
            consumeCnt = 30;
         }

         RoleShopInfoBox roleShopInfoBox = role.getRoleShopInfoBox();
         if (index == 50001) {
            roleShopInfoBox.addGroup(1, 1);
         } else {
            roleShopInfoBox.addGroup(2, 10);
         }

         RES_CERA_GACHA_BUY res_cera_gachabuy = new RES_CERA_GACHA_BUY();
         res_cera_gachabuy.grade = 3;
         res_cera_gachabuy.index = index;
         res_cera_gachabuy.removeitems = new PT_REMOVEITEMS();
         res_cera_gachabuy.removeitems.materialitems = new ArrayList();
         PT_STACKABLE p1 = new PT_STACKABLE();
         p1.index = 2013104145;
         PT_STACKABLE p2 = new PT_STACKABLE();
         p2.index = p1.index;
         p2.bind = true;
         PT_STACKABLE p3 = new PT_STACKABLE();
         p3.index = 2013104143;
         PT_STACKABLE p4 = new PT_STACKABLE();
         p4.index = p3.index;
         p4.bind = true;
         PT_STACKABLE p5 = new PT_STACKABLE();
         p5.index = 2013104146;
         PT_STACKABLE p6 = new PT_STACKABLE();
         p6.index = p5.index;
         p6.bind = true;
         PT_STACKABLE p7 = new PT_STACKABLE();
         p7.index = 2013104425;
         PT_STACKABLE p8 = new PT_STACKABLE();
         p8.index = p7.index;
         p8.bind = true;
         PT_STACKABLE p9 = new PT_STACKABLE();
         p9.index = 2013101405;
         p9.count = keyCnt - consumeCnt;
         PT_STACKABLE p10 = new PT_STACKABLE();
         p10.index = p9.index;
         p10.bind = true;
         res_cera_gachabuy.removeitems.materialitems.add(p1);
         res_cera_gachabuy.removeitems.materialitems.add(p2);
         res_cera_gachabuy.removeitems.materialitems.add(p3);
         res_cera_gachabuy.removeitems.materialitems.add(p4);
         res_cera_gachabuy.removeitems.materialitems.add(p5);
         res_cera_gachabuy.removeitems.materialitems.add(p6);
         res_cera_gachabuy.removeitems.materialitems.add(p7);
         res_cera_gachabuy.removeitems.materialitems.add(p8);
         res_cera_gachabuy.removeitems.materialitems.add(p9);
         res_cera_gachabuy.removeitems.materialitems.add(p10);
         materialBox.updateMaterialSub(2013101405, consumeCnt);
         res_cera_gachabuy.grade = 5;
         res_cera_gachabuy.mileage = new ArrayList();
         res_cera_gachabuy.result = new ArrayList();
         if (index == 50001) {
            PT_GACHA_RESULT pt_gacha_result1 = ShopCache.getRandom(role, account, 13, 2);
            PT_GACHA_RESULT pt_gacha_result2 = ShopCache.getRandom(role, account, 13, 3);
            if (pt_gacha_result1.grade < res_cera_gachabuy.grade) {
               res_cera_gachabuy.grade = pt_gacha_result1.grade;
            }

            if (pt_gacha_result2.grade < res_cera_gachabuy.grade) {
               res_cera_gachabuy.grade = pt_gacha_result2.grade;
            }

            res_cera_gachabuy.result.add(pt_gacha_result1);
            res_cera_gachabuy.result.add(pt_gacha_result2);
         } else if (index == 50002) {
            for(int i = 0; i < 10; ++i) {
               PT_GACHA_RESULT pt_gacha_result1 = ShopCache.getRandom(role, account, 13, 10);
               PT_GACHA_RESULT pt_gacha_result2 = ShopCache.getRandom(role, account, 13, 1);
               if (pt_gacha_result1.grade < res_cera_gachabuy.grade) {
                  res_cera_gachabuy.grade = pt_gacha_result1.grade;
               }

               if (pt_gacha_result2.grade < res_cera_gachabuy.grade) {
                  res_cera_gachabuy.grade = pt_gacha_result2.grade;
               }

               res_cera_gachabuy.result.add(pt_gacha_result1);
               res_cera_gachabuy.result.add(pt_gacha_result2);
            }
         } else {
            log.error("UNREACH==gacha-index=" + index);
         }

         res_cera_gachabuy.resultinven = res_cera_gachabuy.result;
         role.setCeraShopBuyInfo(ceraShopBuyInfo);
         role.setRoleShopInfoBox(roleShopInfoBox);
         role.save();
         account.save();
         res_cera_gachabuy.transId = req_cera_gacha_buy.transId;
         MessagePusher.pushMessage((IoSession)session, res_cera_gachabuy);
      }
   }
}
