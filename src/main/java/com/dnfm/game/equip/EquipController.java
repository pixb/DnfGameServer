package com.dnfm.game.equip;

import cn.hutool.core.util.RandomUtil;
import com.dnfm.common.utils.Util;
import com.dnfm.game.bag.model.EmblemBox;
import com.dnfm.game.bag.model.EquippedBox;
import com.dnfm.game.config.Equip;
import com.dnfm.game.equip.model.UpgradeInfo;
import com.dnfm.game.equip.service.EquipService;
import com.dnfm.game.item.ItemDataPool;
import com.dnfm.game.make.MakeController;
import com.dnfm.game.role.model.Role;
import com.dnfm.mina.annotation.RequestMapping;
import com.dnfm.mina.cache.SessionUtils;
import com.dnfm.mina.message.MessagePusher;
import com.dnfm.mina.protobuf.PT_ARTIFACT;
import com.dnfm.mina.protobuf.PT_AVATAR_ITEM;
import com.dnfm.mina.protobuf.PT_CARD_ATTACH;
import com.dnfm.mina.protobuf.PT_CONTENTS_REWARD_INFO;
import com.dnfm.mina.protobuf.PT_CREATURE;
import com.dnfm.mina.protobuf.PT_CURRENCY_REWARD_INFO;
import com.dnfm.mina.protobuf.PT_EMBLEM;
import com.dnfm.mina.protobuf.PT_EMBLEM_RESULT;
import com.dnfm.mina.protobuf.PT_EQUIP;
import com.dnfm.mina.protobuf.PT_EQUIPPED;
import com.dnfm.mina.protobuf.PT_EQUIP_PUT_ON_OFF;
import com.dnfm.mina.protobuf.PT_ITEMS;
import com.dnfm.mina.protobuf.PT_ITEM_REWARD_INFO;
import com.dnfm.mina.protobuf.PT_MONEY_ITEM;
import com.dnfm.mina.protobuf.PT_RANDOMOPTION_ITEM;
import com.dnfm.mina.protobuf.PT_REMOVEITEMS;
import com.dnfm.mina.protobuf.PT_SKIN_ITEM;
import com.dnfm.mina.protobuf.PT_STACKABLE;
import com.dnfm.mina.protobuf.REQ_AVATAR_VISIBLE_FLAGS_SET;
import com.dnfm.mina.protobuf.REQ_CARD_ATTACH;
import com.dnfm.mina.protobuf.REQ_EMBLEM_EQUIP;
import com.dnfm.mina.protobuf.REQ_EQUIPMENT_RARIFY;
import com.dnfm.mina.protobuf.REQ_EQUIPPED_LIST;
import com.dnfm.mina.protobuf.REQ_EQUIP_PUT_ON_OFF;
import com.dnfm.mina.protobuf.REQ_ITEM_EMBLEM_EXTRACT;
import com.dnfm.mina.protobuf.REQ_ITEM_REINFORCE;
import com.dnfm.mina.protobuf.REQ_RANDOMOPTION_CHANGE;
import com.dnfm.mina.protobuf.REQ_RANDOMOPTION_SELECT;
import com.dnfm.mina.protobuf.REQ_RANDOMOPTION_SLOT_LOCK;
import com.dnfm.mina.protobuf.REQ_RANDOMOPTION_UNLOCK;
import com.dnfm.mina.protobuf.REQ_TRANSMISSION_ITEM;
import com.dnfm.mina.protobuf.RES_AVATAR_VISIBLE_FLAGS_SET;
import com.dnfm.mina.protobuf.RES_CARD_ATTACH;
import com.dnfm.mina.protobuf.RES_EMBLEM_EQUIP;
import com.dnfm.mina.protobuf.RES_EMBLEM_EXTRACT;
import com.dnfm.mina.protobuf.RES_EQUIPMENT_RARIFY;
import com.dnfm.mina.protobuf.RES_EQUIPPED_LIST;
import com.dnfm.mina.protobuf.RES_EQUIP_PUT_ON_OFF;
import com.dnfm.mina.protobuf.RES_ITEM_REINFORCE;
import com.dnfm.mina.protobuf.RES_RANDOMOPTION_CHANGE;
import com.dnfm.mina.protobuf.RES_RANDOMOPTION_SELECT;
import com.dnfm.mina.protobuf.RES_RANDOMOPTION_SLOT_LOCK;
import com.dnfm.mina.protobuf.RES_RANDOMOPTION_UNLOCK;
import com.dnfm.mina.protobuf.RES_TRANSMISSION_ITEM;
import com.dnfm.mina.protobuf.UPDATE_ANTIEVIL_SCORE;
import com.dnfm.mina.protobuf.UPDATE_ANTIEVIL_SCORE1;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class EquipController {
   @Autowired
   EquipService equipService;
   Logger logger = LoggerFactory.getLogger(MakeController.class);

   @RequestMapping
   public void REQ_EQUIP_PUT_ON_OFF(IoSession session, REQ_EQUIP_PUT_ON_OFF req_equip_put_on_off) {
      List<PT_EQUIP_PUT_ON_OFF> reqequip = req_equip_put_on_off.equip;
      Role role = SessionUtils.getRoleBySession(session);
      new PT_EQUIP();
      new PT_EQUIPPED();
      PT_AVATAR_ITEM ptAvatarItem = new PT_AVATAR_ITEM();
      RES_EQUIP_PUT_ON_OFF res_equip_put_on_off = new RES_EQUIP_PUT_ON_OFF();
      int equipscore = role.getEquipscore();
      UPDATE_ANTIEVIL_SCORE update_antievil_score = new UPDATE_ANTIEVIL_SCORE();

      for(PT_EQUIP_PUT_ON_OFF equip : reqequip) {
         if (equip.slot == null) {
            equip.slot = 0;
         }

         if (equip.guid != null) {
            if (equip.slot != 0 && equip.slot != 1 && equip.slot != 2 && equip.slot != 3 && equip.slot != 4 && equip.slot != 5 && equip.slot != 6 && equip.slot != 7 && equip.slot != 8 && equip.slot != 9 && equip.slot != 10) {
               if (equip.slot != 2000 && equip.slot != 2001 && equip.slot != 2002 && equip.slot != 2003 && equip.slot != 2004 && equip.slot != 2005 && equip.slot != 2006 && equip.slot != 2007 && equip.slot != 2008 && equip.slot != 2009 && equip.slot != 2010) {
                  if (equip.slot == 12) {
                     PT_EQUIPPED var20 = role.getEquippedBox().getEquippedBySlot(equip.slot);
                     PT_EQUIP ptequip1 = role.getTitleBox().getTitle(equip.guid);
                     ptequip1.setSlot(equip.slot);
                     role.getTitleBox().removeTitle(ptequip1.guid);
                     PT_EQUIPPED ptEquipped1 = EquipDataPool.changeEquipped(ptequip1);
                     role.getEquippedBox().addEquip(ptEquipped1);
                     equipscore += ptEquipped1.score;
                     if (var20 != null) {
                        PT_EQUIP var17 = role.getTitleBox().changeTitle(var20);
                        role.getTitleBox().addTitle(var17);
                        equipscore -= var17.score;
                        role.getEquippedBox().removeEquip(var20);
                        update_antievil_score.prevscore = role.getEquipscore() - ptEquipped1.score + var17.score;
                     } else {
                        update_antievil_score.prevscore = role.getEquipscore() - ptAvatarItem.score;
                     }

                     res_equip_put_on_off.equipscore = role.getEquipscore();
                     update_antievil_score.afterscore = role.getEquipscore();
                  } else if (equip.slot == 11 || equip.slot == 13 || equip.slot == 14 || equip.slot == 15 || equip.slot == 16 || equip.slot == 17 || equip.slot == 18 || equip.slot == 19 || equip.slot == 20) {
                     PT_EQUIPPED var19 = role.getEquippedBox().getEquippedBySlot(equip.slot);
                     PT_EQUIP ptequip1 = role.getEquipBox().getEquip(equip.guid);
                     ptequip1.setSlot(equip.slot);
                     PT_EQUIPPED ptEquipped1 = EquipDataPool.changeEquipped(ptequip1);
                     role.getEquippedBox().addEquip(ptEquipped1);
                     role.getEquipBox().removeEquip(equip.guid);
                     if (var19 != null) {
                        PT_EQUIP var16 = EquipDataPool.changeEquip(var19);
                        role.getEquipBox().addEquip(var16);
                        role.getEquippedBox().removeEquip(var19);
                     }
                  }
               } else {
                  ptAvatarItem = role.getAvatarBox().getAvatar(equip.guid);
                  equipscore += ptAvatarItem.score;
                  PT_AVATAR_ITEM pt_avatar_item = role.getEquippedBox().getAvatarSkinBySlot(equip.slot);
                  ptAvatarItem.setSlot(equip.slot);
                  role.getEquippedBox().addAvatarskin(ptAvatarItem);
                  role.getAvatarBox().remove(ptAvatarItem.guid);
                  if (pt_avatar_item != null) {
                     role.getAvatarBox().addAvatar(pt_avatar_item);
                     equipscore -= pt_avatar_item.score;
                     role.getEquippedBox().removeAvatarskin(pt_avatar_item.guid);
                     update_antievil_score.prevscore = role.getEquipscore() - ptAvatarItem.score + pt_avatar_item.score;
                  } else {
                     update_antievil_score.prevscore = role.getEquipscore() - ptAvatarItem.score;
                  }

                  res_equip_put_on_off.equipscore = role.getEquipscore();
                  update_antievil_score.afterscore = role.getEquipscore();
               }
            } else {
               ptAvatarItem = role.getAvatarBox().getAvatar(equip.guid);
               equipscore += ptAvatarItem.score;
               PT_AVATAR_ITEM pt_avatar_item = role.getEquippedBox().getAvatarBySlot(equip.slot);
               ptAvatarItem.setSlot(equip.slot);
               role.getAvatarBox().remove(ptAvatarItem.guid);
               role.getEquippedBox().addAvatar(ptAvatarItem);
               if (pt_avatar_item != null) {
                  role.getAvatarBox().addAvatar(pt_avatar_item);
                  equipscore -= pt_avatar_item.score;
                  role.getEquippedBox().removeAvatar(pt_avatar_item);
                  update_antievil_score.prevscore = role.getEquipscore() - ptAvatarItem.score + pt_avatar_item.score;
               } else {
                  update_antievil_score.prevscore = role.getEquipscore() - ptAvatarItem.score;
               }

               res_equip_put_on_off.equipscore = role.getEquipscore();
               update_antievil_score.afterscore = role.getEquipscore();
            }
         } else if (equip.slot == 12) {
            PT_EQUIPPED var18 = role.getEquippedBox().getEquippedBySlot(equip.slot);
            PT_EQUIP var15 = role.getTitleBox().changeTitle(var18);
            role.getTitleBox().addTitle(var15);
            role.getEquippedBox().removeEquip(var18);
            equipscore -= var15.score;
            res_equip_put_on_off.equipscore = role.getEquipscore();
            update_antievil_score.prevscore = role.getEquipscore() + var15.score;
            update_antievil_score.afterscore = role.getEquipscore();
         } else if (equip.slot != 11 && equip.slot != 13 && equip.slot != 14 && equip.slot != 15 && equip.slot != 16 && equip.slot != 17 && equip.slot != 18 && equip.slot != 19 && equip.slot != 20) {
            if (equip.slot != 0 && equip.slot != 1 && equip.slot != 2 && equip.slot != 3 && equip.slot != 4 && equip.slot != 5 && equip.slot != 6 && equip.slot != 7 && equip.slot != 8 && equip.slot != 9 && equip.slot != 10) {
               if (equip.slot == 2000 || equip.slot == 2001 || equip.slot == 2002 || equip.slot == 2003 || equip.slot == 2004 || equip.slot == 2005 || equip.slot == 2006 || equip.slot == 2007 || equip.slot == 2008 || equip.slot == 2009 || equip.slot == 2010) {
                  ptAvatarItem = role.getEquippedBox().getAvatarSkinBySlot(equip.slot);
                  ptAvatarItem.setSlot(666);
                  role.getAvatarBox().addAvatar(ptAvatarItem);
                  role.getEquippedBox().removeAvatarskin(ptAvatarItem.guid);
                  equipscore -= ptAvatarItem.score;
                  res_equip_put_on_off.equipscore = role.getEquipscore();
                  update_antievil_score.prevscore = role.getEquipscore() + ptAvatarItem.score;
                  update_antievil_score.afterscore = role.getEquipscore();
               }
            } else {
               ptAvatarItem = role.getEquippedBox().getAvatarBySlot(equip.slot);
               ptAvatarItem.setSlot(666);
               role.getAvatarBox().addAvatar(ptAvatarItem);
               role.getEquippedBox().removeAvatar(ptAvatarItem.guid);
               equipscore -= ptAvatarItem.score;
               update_antievil_score.prevscore = role.getEquipscore() + ptAvatarItem.score;
               res_equip_put_on_off.equipscore = role.getEquipscore();
               update_antievil_score.afterscore = role.getEquipscore();
            }
         } else {
            PT_EQUIPPED ptEquipped = role.getEquippedBox().getEquippedBySlot(equip.slot);
            PT_EQUIP ptequip = EquipDataPool.changeEquip(ptEquipped);
            role.getEquipBox().addEquip(ptequip);
            role.getEquippedBox().removeEquip(ptEquipped);
         }
      }

      role.setEquipscore(equipscore);
      res_equip_put_on_off.equip = reqequip;
      res_equip_put_on_off.transId = req_equip_put_on_off.transId;
      role.save();
      MessagePusher.pushMessage((IoSession)session, res_equip_put_on_off);
   }

   @RequestMapping
   public void UPDATE_ANTIEVIL_SCORE1(IoSession session, UPDATE_ANTIEVIL_SCORE1 updateAntievilScore) {
      UPDATE_ANTIEVIL_SCORE update_antievil_score = new UPDATE_ANTIEVIL_SCORE();
      Role role = SessionUtils.getRoleBySession(session);
      update_antievil_score.prevscore = role.getEquipscore();
      if (updateAntievilScore.afterscore != null) {
         role.setEquipscore(updateAntievilScore.afterscore);
      }

      update_antievil_score.afterscore = updateAntievilScore.afterscore;
      update_antievil_score.transId = updateAntievilScore.transId;
      MessagePusher.pushMessage((IoSession)session, update_antievil_score);
   }

   @RequestMapping
   public void ReqEquippedList(IoSession session, REQ_EQUIPPED_LIST reqEquippedList) {
      Role role = SessionUtils.getRoleBySession(session);
      EquippedBox equippedBox = role.getEquippedBox();
      List<PT_EQUIPPED> equiplist = equippedBox.getEquiplist();
      List<PT_CREATURE> creaturelist = equippedBox.getCreaturelist();
      List<PT_AVATAR_ITEM> avatarlist = equippedBox.getAvatarlist();
      List<PT_ARTIFACT> cartifactlist = equippedBox.getCartifactlist();
      List<PT_EQUIPPED> equipskinlist = equippedBox.getEquipskinlist();
      List<PT_AVATAR_ITEM> avatarskinlist = equippedBox.getAvatarskinlist();
      List<PT_SKIN_ITEM> skinlist = equippedBox.getSkinlist();
      List<PT_AVATAR_ITEM> sdavatarlist = equippedBox.getSdavatarlist();
      RES_EQUIPPED_LIST res_equipped_list = new RES_EQUIPPED_LIST();
      if (equiplist != null) {
         res_equipped_list.equiplist = equiplist;
      }

      if (creaturelist != null) {
         res_equipped_list.creaturelist = creaturelist;
      }

      if (avatarlist != null) {
         res_equipped_list.avatarlist = avatarlist;
      }

      if (cartifactlist != null) {
         res_equipped_list.cartifactlist = cartifactlist;
      }

      if (equipskinlist != null) {
         res_equipped_list.equipskinlist = equipskinlist;
      }

      if (avatarskinlist != null) {
         res_equipped_list.avatarskinlist = avatarskinlist;
      }

      if (skinlist != null) {
         res_equipped_list.skinlist = skinlist;
      }

      if (sdavatarlist != null) {
         res_equipped_list.sdavatarlist = sdavatarlist;
      }

      res_equipped_list.transId = reqEquippedList.transId;
      MessagePusher.pushMessage((IoSession)session, res_equipped_list);
   }

   @RequestMapping
   public void REQ_ITEM_REINFORCE(IoSession session, REQ_ITEM_REINFORCE reqItemReinforce) {
      Role role = SessionUtils.getRoleBySession(session);
      PT_EQUIPPED equipped = role.getEquippedBox().getEquipped(reqItemReinforce.guid);
      int upgrade = 0;
      PT_EQUIP ptEquip = new PT_EQUIP();
      int updatepoint = 0;
      Equip equipinfo;
      if (equipped != null) {
         upgrade = equipped.upgrade;
         equipinfo = EquipDataPool.getEquip(equipped.index);
         updatepoint = equipped.upgradepoint;
      } else {
         ptEquip = role.getEquipBox().getEquip(reqItemReinforce.guid);
         upgrade = ptEquip.upgrade;
         equipinfo = EquipDataPool.getEquip(ptEquip.index);
         updatepoint = ptEquip.upgradepoint;
      }

      int equiptype = equipinfo.getEquiptype();
      int rarity = equipinfo.getRarity();
      int level = equipinfo.getMinlevel();
      Random random = new Random();
      UpgradeInfo upgradeInfo = EquipDataPool.getUpgradeInfo(upgrade, equiptype, level, rarity);
      int needGold = upgradeInfo.needGold;
      int needMaterial = upgradeInfo.needMaterial;
      int needAdvMaterial = upgradeInfo.needAdvMaterial;
      int pointMax = upgradeInfo.pointMax;
      double sucrandom = (double)random.nextInt(10000) * 0.01;
      Boolean isSuccess = false;
      double rate = (double)0.0F;
      double luckyrate = (double)1.0F;
      int talisman = 0;
      RES_ITEM_REINFORCE res_item_reinforce = new RES_ITEM_REINFORCE();
      PT_REMOVEITEMS removeitems = new PT_REMOVEITEMS();
      List<PT_STACKABLE> materialitems = new ArrayList();
      if (reqItemReinforce.talisman != null) {
         talisman = reqItemReinforce.talisman;
         if (equipinfo.getMinlevel() == 55) {
            if (upgrade != 10 && upgrade != 11 && upgrade != 12 && upgrade != 13 && upgrade != 14 && upgrade != 15 && upgrade != 16) {
               if (upgrade == 17) {
                  talisman = 3;
               } else if (upgrade == 18) {
                  talisman = 4;
               } else if (upgrade == 19) {
                  talisman = 5;
               }
            } else {
               talisman = 2;
            }
         } else if (upgrade != 10 && upgrade != 11 && upgrade != 12 && upgrade != 13 && upgrade != 14 && upgrade != 15 && upgrade != 16) {
            if (upgrade == 17) {
               talisman = 2;
            } else if (upgrade == 18) {
               talisman = 3;
            } else if (upgrade == 19) {
               talisman = 4;
            }
         } else {
            talisman = 1;
         }

         PT_STACKABLE ptStackable = role.getMaterialBox().getMaterial(2013104426);
         PT_STACKABLE ptStackable1 = role.getMaterialBox().getMaterial(2013000026);
         luckyrate = (double)3.0F;
         if (ptStackable != null) {
            if (ptStackable.count >= talisman) {
               ptStackable.count = ptStackable.count - talisman;
               materialitems.add(ptStackable);
               materialitems.add(ItemDataPool.setMaterial(2013104426, 0, true));
            } else {
               talisman -= ptStackable.count;
               ptStackable.count = 0;
               materialitems.add(ptStackable);
               ptStackable1.count = ptStackable1.count - talisman;
               materialitems.add(ptStackable1);
               role.getMaterialBox().removeMaterial(ptStackable.index);
               materialitems.add(ItemDataPool.setMaterial(2013000026, 0, true));
            }
         }
      }

      if (0 <= upgrade && upgrade < 4) {
         isSuccess = true;
      } else if (4 <= upgrade && upgrade < 6) {
         rate = 0.7;
         if (sucrandom > (double)30.0F) {
            isSuccess = true;
         } else {
            isSuccess = false;
            sucrandom = ((double)70.0F + sucrandom) * rate;
         }
      } else if (6 <= upgrade && upgrade < 8) {
         rate = 0.6;
         if (sucrandom > (double)65.0F) {
            isSuccess = true;
         } else {
            isSuccess = false;
            sucrandom = ((double)60.0F + sucrandom) * rate;
         }
      } else if (8 <= upgrade && upgrade < 9) {
         rate = 0.4;
         if (sucrandom > (double)70.0F) {
            isSuccess = true;
         } else {
            isSuccess = false;
            sucrandom = ((double)40.0F + sucrandom) * rate;
         }
      } else if (9 <= upgrade && upgrade < 10) {
         rate = 0.2;
         if (sucrandom > (double)83.0F) {
            isSuccess = true;
         } else {
            isSuccess = false;
            sucrandom = ((double)40.0F + sucrandom) * rate;
         }
      } else if (10 <= upgrade && upgrade < 11) {
         rate = 0.15;
         if (sucrandom > (double)88.0F) {
            isSuccess = true;
         } else {
            isSuccess = false;
            sucrandom = ((double)50.0F + sucrandom / (double)2.0F) * rate;
         }
      } else if (11 <= upgrade && upgrade < 12) {
         rate = 0.1;
         if (sucrandom > (double)92.0F) {
            isSuccess = true;
         } else {
            isSuccess = false;
            sucrandom = ((double)50.0F + sucrandom / (double)2.0F) * rate;
         }
      } else if (12 <= upgrade && upgrade < 13) {
         rate = 0.07;
         if (sucrandom > (double)94.5F) {
            isSuccess = true;
         } else {
            isSuccess = false;
            sucrandom = ((double)50.0F + sucrandom / (double)2.0F) * rate;
         }
      } else if (upgrade == 13) {
         rate = 0.05;
         if (sucrandom > (double)96.0F) {
            isSuccess = true;
         } else {
            isSuccess = false;
            sucrandom = ((double)50.0F + sucrandom / (double)2.0F) * rate;
         }
      } else if (upgrade == 14) {
         rate = 0.03;
         if (sucrandom > (double)98.0F) {
            isSuccess = true;
         } else {
            isSuccess = false;
            sucrandom = ((double)50.0F + sucrandom / (double)2.0F) * rate;
         }
      } else if (upgrade == 15) {
         rate = 0.02;
         if (sucrandom > (double)98.5F) {
            isSuccess = true;
         } else {
            isSuccess = false;
            sucrandom = ((double)50.0F + sucrandom / (double)2.0F) * rate;
         }
      } else if (upgrade == 16) {
         rate = 0.01;
         if (sucrandom > (double)99.0F) {
            isSuccess = true;
         } else {
            isSuccess = false;
            sucrandom = ((double)50.0F + sucrandom / (double)2.0F) * rate;
         }
      } else if (upgrade == 17) {
         rate = 0.01;
         if (sucrandom > 99.2) {
            isSuccess = true;
         } else {
            isSuccess = false;
            sucrandom = ((double)50.0F + sucrandom / (double)2.0F) * rate;
         }
      } else if (upgrade == 18) {
         rate = 0.01;
         if (sucrandom > (double)99.5F) {
            isSuccess = true;
         } else {
            isSuccess = false;
            sucrandom = ((double)50.0F + sucrandom / (double)2.0F) * rate;
         }
      } else if (upgrade == 19) {
         rate = 0.01;
         if (sucrandom > 99.8) {
            isSuccess = true;
         } else {
            isSuccess = false;
            sucrandom = ((double)50.0F + sucrandom / (double)2.0F) * rate;
         }
      }

      if (isSuccess) {
         updatepoint = 0;
         ++upgrade;
         res_item_reinforce.success = true;
      } else {
         updatepoint += (int)((double)(pointMax / 2) * (sucrandom / (double)100.0F) * luckyrate * 0.9);
         if (updatepoint >= pointMax) {
            res_item_reinforce.success = true;
            updatepoint -= pointMax;
            ++upgrade;
         } else {
            res_item_reinforce.success = false;
         }
      }

      if (equipped != null) {
         equipped.upgrade = upgrade;
         equipped.upgradepoint = updatepoint;
         role.getEquippedBox().updateEquip(equipped);
      } else {
         ptEquip.upgrade = upgrade;
         ptEquip.upgradepoint = updatepoint;
         role.getEquipBox().addEquip(ptEquip);
      }

      if (upgrade > 14) {
         materialitems.add(ItemDataPool.setMaterial(2013106036, 0, true));
         materialitems.add(ItemDataPool.setMaterial(2013106035, 0, false));
         PT_STACKABLE cola = (PT_STACKABLE)role.getMaterialBox().getMaterialsMap().get(2013106035);
         PT_STACKABLE cola1 = (PT_STACKABLE)role.getMaterialBox().getMaterialsMap().get(2013106036);
         if (cola != null) {
            if (cola.count > needAdvMaterial) {
               needMaterial = 0;
               cola.count = cola.count - needAdvMaterial;
               role.getMaterialBox().addMaterial(cola);
            } else {
               needMaterial = needAdvMaterial - cola.count;
               role.getMaterialBox().removeMaterial(2013106035);
               cola.count = 0;
            }

            cola.acquisitiontime = System.currentTimeMillis() / 1000L;
            materialitems.add(cola);
         } else {
            materialitems.add(ItemDataPool.setMaterial(2013106035, 0, true));
         }

         if (cola1 != null) {
            if (needMaterial > 0) {
               if (cola1.count > needMaterial) {
                  cola1.count = cola1.count - needMaterial;
                  role.getMaterialBox().addMaterial(cola1);
                  needMaterial = 0;
               } else {
                  int var10000 = needMaterial - cola1.count;
                  cola1.count = 0;
                  role.getMaterialBox().removeMaterial(2013106036);
               }

               cola1.acquisitiontime = System.currentTimeMillis() / 1000L;
            }

            materialitems.add(cola1);
         } else {
            materialitems.add(ItemDataPool.setMaterial(2013100894, 0, false));
         }
      } else {
         materialitems.add(ItemDataPool.setMaterial(2013106066, 0, true));
         materialitems.add(ItemDataPool.setMaterial(2013106066, 0, false));
         materialitems.add(ItemDataPool.setMaterial(2013104171, 0, false));
         materialitems.add(ItemDataPool.setMaterial(2013100894, 0, true));
         materialitems.add(ItemDataPool.setMaterial(2013104187, 0, false));
         PT_STACKABLE luyantan = (PT_STACKABLE)role.getMaterialBox().getMaterialsMap().get(2013104187);
         PT_STACKABLE luyantan1 = (PT_STACKABLE)role.getMaterialBox().getMaterialsMap().get(2013104171);
         PT_STACKABLE luyantan2 = (PT_STACKABLE)role.getMaterialBox().getMaterialsMap().get(2013100894);
         if (luyantan != null) {
            if (luyantan.count > needMaterial) {
               luyantan.count = luyantan.count - needMaterial;
               needMaterial = 0;
               role.getMaterialBox().addMaterial(luyantan);
            } else {
               needMaterial -= luyantan.count;
               luyantan.count = 0;
               role.getMaterialBox().removeMaterial(2013104187);
            }

            luyantan.acquisitiontime = System.currentTimeMillis() / 1000L;
            materialitems.add(luyantan);
         } else {
            materialitems.add(ItemDataPool.setMaterial(2013104187, 0, true));
         }

         if (luyantan1 != null) {
            if (needMaterial > 0) {
               if (luyantan1.count > needMaterial) {
                  luyantan1.count = luyantan1.count - needMaterial;
                  role.getMaterialBox().addMaterial(luyantan1);
                  needMaterial = 0;
               } else {
                  needMaterial -= luyantan1.count;
                  luyantan1.count = 0;
                  role.getMaterialBox().removeMaterial(2013104171);
               }

               luyantan1.acquisitiontime = System.currentTimeMillis() / 1000L;
            }

            materialitems.add(luyantan1);
         } else {
            materialitems.add(ItemDataPool.setMaterial(2013104171, 0, true));
         }

         if (luyantan2 != null) {
            if (needMaterial > 0) {
               if (luyantan2.count > needMaterial) {
                  luyantan2.count = luyantan2.count - needMaterial;
                  role.getMaterialBox().addMaterial(luyantan2);
                  needMaterial = 0;
               } else {
                  int var58 = needMaterial - luyantan2.count;
                  luyantan2.count = 0;
                  role.getMaterialBox().removeMaterial(2013100894);
               }

               luyantan2.acquisitiontime = System.currentTimeMillis() / 1000L;
               materialitems.add(luyantan2);
            } else {
               materialitems.add(luyantan2);
            }
         } else {
            materialitems.add(ItemDataPool.setMaterial(2013100894, 0, false));
         }
      }

      role.getMoneyBox().subCnt(0, needGold);
      removeitems.materialitems = materialitems;
      List<PT_MONEY_ITEM> currency = new ArrayList();
      currency.add(role.getMoneyBox().getMoneyItem(0));
      res_item_reinforce.guid = reqItemReinforce.guid;
      res_item_reinforce.upgrade = upgrade;
      res_item_reinforce.upgradepoint = updatepoint;
      res_item_reinforce.removeitems = removeitems;
      res_item_reinforce.currency = currency;
      res_item_reinforce.transId = reqItemReinforce.transId;
      role.save();
      MessagePusher.pushMessage((IoSession)session, res_item_reinforce);
   }

   @RequestMapping
   public void REQ_RANDOMOPTION_UNLOCK(IoSession session, REQ_RANDOMOPTION_UNLOCK reqItemReinforce) {
      long guid = reqItemReinforce.guid;
      RES_RANDOMOPTION_UNLOCK resRandomoptionUnlock = new RES_RANDOMOPTION_UNLOCK();
      if (reqItemReinforce.type != null) {
         resRandomoptionUnlock.type = reqItemReinforce.type;
      }

      resRandomoptionUnlock.guid = guid;
      Role role = SessionUtils.getRoleBySession(session);
      PT_EQUIPPED ptEquipped = role.getEquippedBox().getEquipped(guid);
      Equip equip;
      if (ptEquipped == null) {
         PT_EQUIP ptEquip = role.getEquipBox().getEquip(guid);
         equip = (Equip)EquipDataPool.index2Equip.get(ptEquip.index);
      } else {
         equip = (Equip)EquipDataPool.index2Equip.get(ptEquipped.index);
      }

      int rarity = equip.getRarity();
      if (ptEquipped == null) {
         PT_EQUIP ptEquip = role.getEquipBox().getEquip(guid);
         ptEquip.roption = EquipDataPool.getrop(rarity, equip.getEquiptype(), equip.getMinlevel());
         role.getEquipBox().addEquip(ptEquip);
         resRandomoptionUnlock.equip = ptEquip;
      } else {
         ptEquipped.roption = EquipDataPool.getrop(rarity, equip.getEquiptype(), equip.getMinlevel());
         role.getEquippedBox().updateEquip(ptEquipped);
         PT_EQUIP equipnew = EquipDataPool.changeEquip(ptEquipped);
         resRandomoptionUnlock.equip = equipnew;
      }

      resRandomoptionUnlock.transId = reqItemReinforce.transId;
      role.save();
      MessagePusher.pushMessage((IoSession)session, resRandomoptionUnlock);
   }

   @RequestMapping
   public void REQ_RANDOMOPTION_CHANGE(IoSession session, REQ_RANDOMOPTION_CHANGE reqRandomoptionChange) {
      RES_RANDOMOPTION_CHANGE resRandomoptionChange = new RES_RANDOMOPTION_CHANGE();
      long guid = reqRandomoptionChange.guid;
      Role role = SessionUtils.getRoleBySession(session);
      PT_EQUIPPED ptEquipped = role.getEquippedBox().getEquipped(guid);
      double costrate = (double)1.0F;
      double rarityrate = (double)1.0F;
      Equip equip;
      if (ptEquipped == null) {
         PT_EQUIP ptEquip = role.getEquipBox().getEquip(guid);
         equip = (Equip)EquipDataPool.index2Equip.get(ptEquip.index);
      } else {
         equip = (Equip)EquipDataPool.index2Equip.get(ptEquipped.index);
      }

      int rarity = equip.getRarity();
      int level = equip.getMinlevel();
      if (rarity <= 2) {
         costrate = 0.2;
      } else if (rarity == 3) {
         costrate = (double)1.5F;
      } else if (rarity == 4) {
         costrate = (double)8.0F;
         rarityrate = 0.2;
      } else if (rarity == 5) {
         costrate = (double)8.0F;
      }

      int costgold = (int)((double)(500 * level) * costrate * rarityrate);
      role.getMoneyBox().subCnt(0, costgold);
      PT_EQUIP equipnew = new PT_EQUIP();
      if (ptEquipped == null) {
         PT_EQUIP ptEquip = role.getEquipBox().getEquip(guid);
         equipnew.index = ptEquip.index;
         equipnew.guid = ptEquip.guid;
         equipnew.quality = ptEquip.quality;
         equipnew.endurance = ptEquip.endurance;
         equipnew.rappearance = ptEquip.rappearance;
         equipnew.roption = ptEquip.roption;
         equipnew.rnoption = EquipDataPool.getrnop(rarity, equip.getEquiptype(), ptEquip.roption, equip.getMinlevel());
         ptEquip.rnoption = equipnew.rnoption;
         role.getEquipBox().addEquip(ptEquip);
      } else {
         equipnew.index = ptEquipped.index;
         equipnew.guid = ptEquipped.guid;
         equipnew.quality = ptEquipped.quality;
         equipnew.endurance = ptEquipped.endurance;
         equipnew.rappearance = ptEquipped.rappearance;
         equipnew.roption = ptEquipped.roption;
         equipnew.rnoption = EquipDataPool.getrnop(rarity, equip.getEquiptype(), ptEquipped.roption, equip.getMinlevel());
         ptEquipped.rnoption = equipnew.rnoption;
         role.getEquippedBox().updateEquip(ptEquipped);
      }

      List<PT_MONEY_ITEM> currency = new ArrayList();
      currency.add(role.getMoneyBox().getMoneyItem(0));
      PT_REMOVEITEMS removeitems = new PT_REMOVEITEMS();
      List<PT_STACKABLE> materialitems = new ArrayList();
      PT_STACKABLE materialitem = role.getMaterialBox().getMaterial(2013105727);
      if (materialitem != null) {
         role.getMaterialBox().updateMaterialSub(2013105727, 1);
         materialitems.add(role.getMaterialBox().getMaterial(2013105727));
      } else {
         PT_STACKABLE materialitem1 = role.getMaterialBox().getMaterial(2013106585);
         if (materialitem1 != null) {
            role.getMaterialBox().updateMaterialSub(2013106585, 1);
            materialitems.add(role.getMaterialBox().getMaterial(2013106585));
         } else {
            PT_STACKABLE materialitem2 = role.getMaterialBox().getMaterial(2013105726);
            role.getMaterialBox().updateMaterialSub(2013105726, 1);
            materialitems.add(role.getMaterialBox().getMaterial(2013105726));
         }
      }

      removeitems.materialitems = materialitems;
      resRandomoptionChange.guid = guid;
      resRandomoptionChange.type = reqRandomoptionChange.type;
      resRandomoptionChange.equip = equipnew;
      resRandomoptionChange.currency = currency;
      resRandomoptionChange.removeitems = removeitems;
      resRandomoptionChange.transId = reqRandomoptionChange.transId;
      role.save();
      MessagePusher.pushMessage((IoSession)session, resRandomoptionChange);
   }

   @RequestMapping
   public void REQ_RANDOMOPTION_SLOT_LOCK(IoSession session, REQ_RANDOMOPTION_SLOT_LOCK reqRandomoptionSlotLock) {
      RES_RANDOMOPTION_SLOT_LOCK resRandomoptionSlotLock = new RES_RANDOMOPTION_SLOT_LOCK();
      long guid = reqRandomoptionSlotLock.guid;
      int index = 0;
      if (reqRandomoptionSlotLock.index != null) {
         index = reqRandomoptionSlotLock.index;
      }

      Role role = SessionUtils.getRoleBySession(session);
      PT_EQUIPPED ptEquipped = role.getEquippedBox().getEquipped(guid);
      PT_EQUIP ptEquip = new PT_EQUIP();
      if (reqRandomoptionSlotLock.locked != null) {
         if (ptEquipped == null) {
            ptEquip.roption = EquipDataPool.selectRoption(ptEquip.roption, index, reqRandomoptionSlotLock.locked);
            role.getEquipBox().addEquip(ptEquip);
         } else {
            ptEquipped.roption = EquipDataPool.selectRoption(ptEquipped.roption, index, reqRandomoptionSlotLock.locked);
            role.getEquippedBox().updateEquip(ptEquipped);
            ptEquip = EquipDataPool.changeEquip(ptEquipped);
         }
      } else if (ptEquipped == null) {
         ptEquip = role.getEquipBox().getEquip(guid);
         ptEquip.roption = EquipDataPool.selectRoption(ptEquip.roption, index, false);
         role.getEquipBox().addEquip(ptEquip);
      } else {
         ptEquipped.roption = EquipDataPool.selectRoption(ptEquipped.roption, index, false);
         role.getEquippedBox().updateEquip(ptEquipped);
         ptEquip = EquipDataPool.changeEquip(ptEquipped);
      }

      resRandomoptionSlotLock.guid = guid;
      resRandomoptionSlotLock.type = reqRandomoptionSlotLock.type;
      resRandomoptionSlotLock.equip = ptEquip;
      resRandomoptionSlotLock.transId = reqRandomoptionSlotLock.transId;
      role.save();
      MessagePusher.pushMessage((IoSession)session, resRandomoptionSlotLock);
   }

   @RequestMapping
   public void REQ_RANDOMOPTION_SELECT(IoSession session, REQ_RANDOMOPTION_SELECT reqRandomoptionSelect) {
      RES_RANDOMOPTION_SELECT resRandomoptionSelect = new RES_RANDOMOPTION_SELECT();
      long guid = reqRandomoptionSelect.guid;
      Role role = SessionUtils.getRoleBySession(session);
      PT_EQUIPPED ptEquipped = role.getEquippedBox().getEquipped(guid);
      new PT_EQUIP();
      PT_EQUIP ptEquip;
      if (reqRandomoptionSelect.selecttype != null) {
         if (ptEquipped == null) {
            ptEquip = role.getEquipBox().getEquip(guid);
            ptEquip.roption = ptEquip.rnoption;
            ptEquip.rnoption = new ArrayList();
            role.getEquipBox().addEquip(ptEquip);
         } else {
            ptEquipped.setRoption(ptEquipped.rnoption);
            ptEquipped.rnoption = new ArrayList();
            role.getEquippedBox().updateEquip(ptEquipped);
            ptEquip = EquipDataPool.changeEquip(ptEquipped);
         }
      } else if (ptEquipped == null) {
         ptEquip = role.getEquipBox().getEquip(guid);
         ptEquip.rnoption = new ArrayList();
         role.getEquipBox().addEquip(ptEquip);
      } else {
         ptEquipped.rnoption = new ArrayList();
         role.getEquippedBox().updateEquip(ptEquipped);
         ptEquip = EquipDataPool.changeEquip(ptEquipped);
      }

      resRandomoptionSelect.guid = guid;
      resRandomoptionSelect.type = reqRandomoptionSelect.type;
      resRandomoptionSelect.equip = ptEquip;
      resRandomoptionSelect.transId = reqRandomoptionSelect.transId;
      role.save();
      MessagePusher.pushMessage((IoSession)session, resRandomoptionSelect);
   }

   @RequestMapping
   public void REQ_EQUIPMENT_RARIFY(IoSession session, REQ_EQUIPMENT_RARIFY reqEquipmentRarify) {
      RES_EQUIPMENT_RARIFY resEquipmentRarify = new RES_EQUIPMENT_RARIFY();
      long guid = reqEquipmentRarify.guid;
      Role role = SessionUtils.getRoleBySession(session);
      PT_EQUIPPED ptEquipped = role.getEquippedBox().getEquipped(guid);
      int quality = 0;
      if (ptEquipped == null) {
         PT_EQUIP ptEquip = role.getEquipBox().getEquip(guid);
         quality = RandomUtil.randomInt(1, 101);
         ptEquip.quality = quality;
         role.getEquipBox().addEquip(ptEquip);
      } else {
         quality = RandomUtil.randomInt(1, 101);
         ptEquipped.quality = quality;
         role.getEquippedBox().updateEquip(ptEquipped);
      }

      resEquipmentRarify.removeitems = new PT_REMOVEITEMS();
      resEquipmentRarify.removeitems.consumeitems = new ArrayList();
      PT_STACKABLE ptStackable = new PT_STACKABLE();
      if (role.getConsumableBox().getConsumable(2013106098) != null && role.getConsumableBox().getConsumable(2013106098).count > 0) {
         ptStackable.index = 2013106098;
         ptStackable.count = role.getConsumableBox().getConsumable(2013106098).count - 1;
      } else {
         ptStackable.index = 2013101270;
         ptStackable.count = role.getConsumableBox().getConsumable(2013101270).count - 1;
      }

      role.getConsumableBox().addConsumable(ptStackable);
      role.getMoneyBox().submoney(10000);
      resEquipmentRarify.guid = guid;
      resEquipmentRarify.currency = new ArrayList();
      resEquipmentRarify.currency.add(role.getMoneyBox().getMoneyItem(0));
      resEquipmentRarify.removeitems.consumeitems.add(ptStackable);
      resEquipmentRarify.quality = quality;
      resEquipmentRarify.transId = reqEquipmentRarify.transId;
      role.save();
      MessagePusher.pushMessage((IoSession)session, resEquipmentRarify);
   }

   @RequestMapping
   public void REQ_CARD_ATTACH(IoSession session, REQ_CARD_ATTACH reqCardAttach) {
      RES_CARD_ATTACH resCardAttach = new RES_CARD_ATTACH();
      Role role = SessionUtils.getRoleBySession(session);
      long guid = reqCardAttach.guid;
      int index = reqCardAttach.card.index;
      PT_STACKABLE card = role.getCardBox().getCard(index);
      if (card.count == null) {
         card.count = 0;
      } else {
         card.count = card.count - 1;
      }

      role.getCardBox().updateCard(card);
      new PT_STACKABLE();
      PT_CARD_ATTACH ptCardAttach = new PT_CARD_ATTACH();
      PT_EQUIPPED ptEquipped = role.getEquippedBox().getEquipped(guid);
      if (ptEquipped == null) {
         PT_EQUIP ptEquip = role.getEquipBox().getEquip(guid);
         PT_STACKABLE ptStackable = new PT_STACKABLE();
         ptStackable.index = index;
         ptEquip.card = new ArrayList();
         ptEquip.card.add(ptStackable);
         role.getEquipBox().addEquip(ptEquip);
      } else {
         PT_STACKABLE var13 = new PT_STACKABLE();
         var13.index = index;
         ptEquipped.card = new ArrayList();
         ptEquipped.card.add(var13);
         role.getEquippedBox().updateEquip(ptEquipped);
      }

      ptCardAttach.index = index;
      resCardAttach.guid = guid;
      resCardAttach.card = ptCardAttach;
      role.getMoneyBox().submoney(10000);
      resCardAttach.currency = new ArrayList();
      resCardAttach.currency.add(role.getMoneyBox().getMoneyItem(0));
      resCardAttach.transId = reqCardAttach.transId;
      role.save();
      MessagePusher.pushMessage((IoSession)session, resCardAttach);
   }

   @RequestMapping
   public void REQ_AVATAR_VISIBLE_FLAGS_SET(IoSession session, REQ_AVATAR_VISIBLE_FLAGS_SET reqAvatarVisibleFlagsSet) {
      RES_AVATAR_VISIBLE_FLAGS_SET resAvatarVisibleFlagsSet = new RES_AVATAR_VISIBLE_FLAGS_SET();
      Role role = SessionUtils.getRoleBySession(session);
      resAvatarVisibleFlagsSet.guid = role.getUid();
      resAvatarVisibleFlagsSet.flags = reqAvatarVisibleFlagsSet.flags;
      resAvatarVisibleFlagsSet.transId = reqAvatarVisibleFlagsSet.transId;
      MessagePusher.pushMessage((IoSession)session, resAvatarVisibleFlagsSet);
   }

   @RequestMapping
   public void REQ_TRANSMISSION_ITEM(IoSession session, REQ_TRANSMISSION_ITEM reqTransmissionItem) {
      RES_TRANSMISSION_ITEM resTransmissionItem = new RES_TRANSMISSION_ITEM();
      long oldguid = reqTransmissionItem.materialguid;
      long newguid = reqTransmissionItem.itemguid;
      Role role = SessionUtils.getRoleBySession(session);
      PT_EQUIP ptEquipOld = role.getEquipBox().getEquip(oldguid);
      Equip equipInfoOld = EquipDataPool.getEquip(ptEquipOld.index);
      UpgradeInfo upgradeInfoold = EquipDataPool.getUpgradeInfo(ptEquipOld.upgrade, ptEquipOld.equiptype, equipInfoOld.getMinlevel(), equipInfoOld.getRarity());
      int accuPointMaxOld = upgradeInfoold.accuPointMax;
      int pointAllOld = accuPointMaxOld + ptEquipOld.upgradepoint;
      int accuPointMaxNew = 0;
      int pointCount = 0;
      new PT_EQUIP();
      PT_EQUIPPED ptEquipped = role.getEquippedBox().getEquipped(newguid);
      if (ptEquipped == null) {
         PT_EQUIP ptEquipnew = role.getEquipBox().getEquip(newguid);
         Equip equipInfoNew = EquipDataPool.getEquip(ptEquipnew.index);
         UpgradeInfo upgradeInfoNew = EquipDataPool.getUpgradeInfo(ptEquipnew.upgrade, equipInfoNew.getEquiptype(), equipInfoNew.getMinlevel(), equipInfoNew.getRarity());
         int pointAllNew = upgradeInfoNew.accuPointMax + ptEquipnew.upgradepoint;

         for(int i = 0; i <= 19; ++i) {
            upgradeInfoNew = EquipDataPool.getUpgradeInfo(i, equipInfoNew.getEquiptype(), equipInfoNew.getMinlevel(), equipInfoNew.getRarity());
            accuPointMaxNew = upgradeInfoNew.accuPointMax;
            if (pointAllOld < accuPointMaxNew) {
               ptEquipnew.upgrade = i - 1;
               ptEquipnew.upgradepoint = pointCount;
               break;
            }

            if (pointAllOld == accuPointMaxNew) {
               ptEquipnew.upgrade = i;
               ptEquipnew.upgradepoint = 0;
               break;
            }

            pointCount = pointAllOld - accuPointMaxNew;
         }

         pointCount = pointAllNew - accuPointMaxOld;

         for(int j = 0; j <= 19; ++j) {
            UpgradeInfo upgradeInfoOld = EquipDataPool.getUpgradeInfo(j, equipInfoOld.getEquiptype(), equipInfoOld.getMinlevel(), equipInfoOld.getRarity());
            accuPointMaxOld = upgradeInfoOld.accuPointMax;
            if (pointAllNew < accuPointMaxOld) {
               if (j == 0) {
                  ptEquipOld.upgrade = 0;
                  ptEquipOld.upgradepoint = pointCount;
                  role.getEquipBox().addEquip(ptEquipOld);
               } else {
                  ptEquipOld.upgrade = j - 1;
                  ptEquipOld.upgradepoint = pointCount;
               }
               break;
            }

            if (pointAllNew == accuPointMaxOld) {
               ptEquipOld.upgrade = j;
               ptEquipOld.upgradepoint = 0;
               break;
            }

            pointCount = pointAllNew - accuPointMaxOld;
         }

         List<PT_RANDOMOPTION_ITEM> roption = ptEquipnew.roption;
         ptEquipnew.roption = ptEquipOld.roption;
         ptEquipOld.roption = roption;
         List<PT_EMBLEM> emblem = ptEquipnew.emblem;
         List<PT_EMBLEM> emblemold = ptEquipOld.emblem;
         if (emblem != null) {
            ptEquipOld.emblem = emblem;
         }

         if (emblemold != null) {
            ptEquipnew.emblem = emblemold;
         }

         List<PT_STACKABLE> card = ptEquipnew.card;
         List<PT_STACKABLE> cardold = ptEquipOld.card;
         if (card != null) {
            ptEquipOld.card = card;
         }

         if (cardold != null) {
            ptEquipnew.card = cardold;
         }

         role.getEquipBox().addEquip(ptEquipnew);
         role.getEquipBox().addEquip(ptEquipOld);
         resTransmissionItem.targetitem = ptEquipnew;
         resTransmissionItem.metarialitem = ptEquipOld;
      } else {
         Equip equipInfoNew = EquipDataPool.getEquip(ptEquipped.index);
         UpgradeInfo upgradeInfoNew = EquipDataPool.getUpgradeInfo(ptEquipped.upgrade, equipInfoNew.getEquiptype(), equipInfoNew.getMinlevel(), equipInfoNew.getRarity());
         int pointAllNew = upgradeInfoNew.accuPointMax + ptEquipped.upgradepoint;

         for(int i = 0; i <= 19; ++i) {
            upgradeInfoNew = EquipDataPool.getUpgradeInfo(i, equipInfoNew.getEquiptype(), equipInfoNew.getMinlevel(), equipInfoNew.getRarity());
            accuPointMaxNew = upgradeInfoNew.accuPointMax;
            if (pointAllOld < accuPointMaxNew) {
               ptEquipped.upgrade = i - 1;
               ptEquipped.upgradepoint = pointCount;
               break;
            }

            if (pointAllOld == accuPointMaxNew) {
               ptEquipped.upgrade = i;
               ptEquipped.upgradepoint = 0;
               break;
            }

            pointCount = pointAllOld - accuPointMaxNew;
         }

         for(int j = 0; j <= 19; ++j) {
            UpgradeInfo upgradeInfoOld = EquipDataPool.getUpgradeInfo(j, equipInfoOld.getEquiptype(), equipInfoOld.getMinlevel(), equipInfoOld.getRarity());
            accuPointMaxOld = upgradeInfoOld.accuPointMax;
            if (pointAllNew < accuPointMaxOld) {
               if (j == 0) {
                  ptEquipOld.upgrade = 0;
                  ptEquipOld.upgradepoint = pointCount;
               } else {
                  ptEquipOld.upgrade = j - 1;
                  ptEquipOld.upgradepoint = pointCount;
               }
               break;
            }

            if (pointAllNew == accuPointMaxOld) {
               ptEquipOld.upgrade = j;
               ptEquipOld.upgradepoint = 0;
               break;
            }
         }

         List<PT_RANDOMOPTION_ITEM> roption = ptEquipped.roption;
         ptEquipped.roption = ptEquipOld.roption;
         ptEquipOld.roption = roption;
         List<PT_STACKABLE> card = ptEquipped.card;
         List<PT_STACKABLE> cardold = ptEquipOld.card;
         if (card != null) {
            ptEquipOld.card = card;
         }

         if (cardold != null) {
            ptEquipped.card = cardold;
         }

         role.getEquippedBox().updateEquip(ptEquipped);
         role.getEquipBox().addEquip(ptEquipOld);
         resTransmissionItem.targetitem = EquipDataPool.changeEquip(ptEquipped);
         resTransmissionItem.metarialitem = ptEquipOld;
      }

      role.getMoneyBox().submoney(100000);
      resTransmissionItem.rewardinfo = new PT_CONTENTS_REWARD_INFO();
      resTransmissionItem.rewardinfo.currency = new PT_CURRENCY_REWARD_INFO();
      resTransmissionItem.rewardinfo.currency.currency = new ArrayList();
      resTransmissionItem.rewardinfo.currency.currency.add(role.getMoneyBox().getMoneyItem(0));
      resTransmissionItem.transId = reqTransmissionItem.transId;
      role.save();
      MessagePusher.pushMessage((IoSession)session, resTransmissionItem);
   }

   @RequestMapping
   public void REQ_EMBLEM_EQUIP(IoSession session, REQ_EMBLEM_EQUIP req_emblem_equip) {
      Role role = SessionUtils.getRoleBySession(session);
      EquippedBox equippedBox = role.getEquippedBox();
      EmblemBox emblemBox = role.getEmblemBox();
      RES_EMBLEM_EQUIP res_emblem_equip = new RES_EMBLEM_EQUIP();
      res_emblem_equip.output = new PT_EMBLEM_RESULT();
      int index = req_emblem_equip.emblem.index;
      long guid = req_emblem_equip.guid;
      int slot = 0;
      if (req_emblem_equip.emblem.slot != null) {
         slot = req_emblem_equip.emblem.slot;
      }

      PT_STACKABLE emblem = emblemBox.getEmblem(index);
      emblem.count = emblem.count - 1;
      emblemBox.putEmblem(emblem);
      PT_EQUIPPED pt_equipped = equippedBox.getEquipped(guid);
      if (pt_equipped == null) {
         PT_EQUIP pt_equip = role.getEquipBox().getEquip(guid);
         if (Util.isEmpty(pt_equip.emblem)) {
            pt_equip.emblem = new ArrayList();
            PT_EMBLEM tmp = new PT_EMBLEM();
            tmp.index = index;
            tmp.count = 1;
            tmp.slot = slot;
            pt_equip.emblem.add(tmp);
         } else {
            int outputIndex = -1;
            int removeIndex = -1;

            for(int i = 0; i < pt_equip.emblem.size(); ++i) {
               PT_EMBLEM e = (PT_EMBLEM)pt_equip.emblem.get(i);
               if (e.slot == slot) {
                  removeIndex = i;
                  outputIndex = e.index;
                  break;
               }
            }

            if (outputIndex != -1) {
               pt_equip.emblem.remove(removeIndex);
               PT_EMBLEM tmp = new PT_EMBLEM();
               tmp.index = index;
               tmp.count = 1;
               tmp.slot = slot;
               pt_equip.emblem.add(tmp);
               res_emblem_equip.output.index = outputIndex;
            } else {
               PT_EMBLEM tmp = new PT_EMBLEM();
               tmp.index = index;
               tmp.count = 1;
               tmp.slot = slot;
               pt_equip.emblem.add(tmp);
            }
         }

         role.getEquipBox().addEquip(pt_equip);
      } else {
         if (Util.isEmpty(pt_equipped.emblem)) {
            pt_equipped.emblem = new ArrayList();
            PT_EMBLEM tmp = new PT_EMBLEM();
            tmp.index = index;
            tmp.count = 1;
            tmp.slot = slot;
            pt_equipped.emblem.add(tmp);
         } else {
            int outputIndex = -1;
            int removeIndex = -1;

            for(int i = 0; i < pt_equipped.emblem.size(); ++i) {
               PT_EMBLEM e = (PT_EMBLEM)pt_equipped.emblem.get(i);
               if (e.slot == slot) {
                  removeIndex = i;
                  outputIndex = e.index;
                  break;
               }
            }

            if (outputIndex != -1) {
               pt_equipped.emblem.remove(removeIndex);
               PT_EMBLEM tmp = new PT_EMBLEM();
               tmp.index = index;
               tmp.count = 1;
               tmp.slot = slot;
               pt_equipped.emblem.add(tmp);
               res_emblem_equip.output.index = outputIndex;
            } else {
               PT_EMBLEM tmp = new PT_EMBLEM();
               tmp.index = index;
               tmp.count = 1;
               tmp.slot = slot;
               pt_equipped.emblem.add(tmp);
            }
         }

         equippedBox.updateEquip(pt_equipped);
         role.setEquippedBox(equippedBox);
      }

      res_emblem_equip.emblem = new PT_STACKABLE();
      res_emblem_equip.emblem.index = index;
      res_emblem_equip.emblem.count = 1;
      res_emblem_equip.guid = guid;
      if (req_emblem_equip.emblem.slot != null) {
         res_emblem_equip.slot = req_emblem_equip.emblem.slot;
      }

      role.save();
      res_emblem_equip.transId = req_emblem_equip.transId;
      MessagePusher.pushMessage((IoSession)session, res_emblem_equip);
   }

   @RequestMapping
   public void REQ_ITEM_EMBLEM_EXTRACT(IoSession session, REQ_ITEM_EMBLEM_EXTRACT req_item_emblem_extract) {
      Role role = SessionUtils.getRoleBySession(session);
      EquippedBox equippedBox = role.getEquippedBox();
      long guid = req_item_emblem_extract.guid;
      int slot = 0;
      RES_EMBLEM_EXTRACT res_emblem_extract = new RES_EMBLEM_EXTRACT();
      if (req_item_emblem_extract.slot != null) {
         slot = req_item_emblem_extract.slot;
         res_emblem_extract.slot = slot;
      }

      PT_EQUIPPED pt_equipped = equippedBox.getEquipped(guid);
      PT_EMBLEM emblem;
      if (pt_equipped == null) {
         PT_EQUIP ptEquip = role.getEquipBox().getEquip(guid);
         int removeIndex = equippedBox.removeEmblem2(ptEquip, slot);
         if (removeIndex == -1) {
            this.logger.error("ERROR==REQ_ITEM_EMBLEM_EXTRACT remove emblem failed");
            return;
         }

         emblem = (PT_EMBLEM)ptEquip.emblem.get(removeIndex);
         ptEquip.emblem.remove(removeIndex);
         role.getEmblemBox().updateEmblemAdd(removeIndex, 1);
         role.getEquipBox().addEquip(ptEquip);
      } else {
         int removeIndex = equippedBox.removeEmblem(pt_equipped, slot);
         if (removeIndex == -1) {
            this.logger.error("ERROR==REQ_ITEM_EMBLEM_EXTRACT remove emblem failed");
            return;
         }

         emblem = (PT_EMBLEM)pt_equipped.emblem.get(removeIndex);
         pt_equipped.emblem.remove(removeIndex);
         role.getEmblemBox().updateEmblemAdd(removeIndex, 1);
         equippedBox.updateEquip(pt_equipped);
      }

      res_emblem_extract.guid = guid;
      res_emblem_extract.rewards = new PT_CONTENTS_REWARD_INFO();
      res_emblem_extract.rewards.items = new PT_ITEM_REWARD_INFO();
      res_emblem_extract.rewards.items.inventory = new PT_ITEMS();
      res_emblem_extract.rewards.items.inventory.emblemitems = new ArrayList();
      PT_STACKABLE ptStackable = new PT_STACKABLE();
      ptStackable.index = emblem.index;
      ptStackable.count = 1;
      res_emblem_extract.rewards.items.inventory.emblemitems.add(ptStackable);
      role.setEquippedBox(equippedBox);
      role.save();
      res_emblem_extract.transId = req_item_emblem_extract.transId;
      MessagePusher.pushMessage((IoSession)session, res_emblem_extract);
   }
}
