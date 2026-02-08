package com.dnfm.game.shop.model;

import com.dnfm.common.utils.Util;
import com.dnfm.game.bag.model.AccountMoneyBox;
import com.dnfm.game.bag.model.AvatarBox;
import com.dnfm.game.bag.model.BookmarkBox;
import com.dnfm.game.bag.model.CardBox;
import com.dnfm.game.bag.model.CharFrameBox;
import com.dnfm.game.bag.model.ChatFrameBox;
import com.dnfm.game.bag.model.ConsumableBox;
import com.dnfm.game.bag.model.DamageBox;
import com.dnfm.game.bag.model.EmblemBox;
import com.dnfm.game.bag.model.EpicPieceBox;
import com.dnfm.game.bag.model.MaterialBox;
import com.dnfm.game.bag.model.MoneyBox;
import com.dnfm.game.bag.model.TitleBox;
import com.dnfm.game.bag.serveice.BagService;
import com.dnfm.game.equip.EquipDataPool;
import com.dnfm.game.equip.model.EquipBox;
import com.dnfm.game.role.model.Account;
import com.dnfm.game.role.model.Role;
import com.dnfm.mina.protobuf.PT_AVATAR_ITEM;
import com.dnfm.mina.protobuf.PT_EQUIP;
import com.dnfm.mina.protobuf.PT_GACHA_RESULT;
import com.dnfm.mina.protobuf.PT_STACKABLE;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShopCache {
   private static final Logger log = LoggerFactory.getLogger(ShopCache.class);
   public static final Map<Integer, List<GachaRecipeModel>> GACHA_RECIPE_MAP = new ConcurrentHashMap();
   public static int TOTAL_RATE = 0;

   public static int getTargetI(int rateNum, List<GachaRecipeModel> list) {
      for(int i = 0; i < list.size(); ++i) {
         GachaRecipeModel gachaRecipeModel = (GachaRecipeModel)list.get(i);
         int rNum = gachaRecipeModel.rateNum;
         if (rNum > rateNum) {
            return i;
         }
      }

      return -1;
   }

   public static PT_GACHA_RESULT getRandom(Role role, Account account, int recipe, int gachaindex) {
      PT_GACHA_RESULT pt_gacha_result = new PT_GACHA_RESULT();
      pt_gacha_result.gachaindex = gachaindex;
      List<GachaRecipeModel> gachaRecipeModels = (List)GACHA_RECIPE_MAP.get(1);
      int randIntVal = Util.randIndex(TOTAL_RATE);
      int targetI = getTargetI(randIntVal, gachaRecipeModels);
      int index = ((GachaRecipeModel)gachaRecipeModels.get(targetI)).index;
      int type = BagService.getIndexType(index);
      int cnt = ((GachaRecipeModel)gachaRecipeModels.get(targetI)).count;
      pt_gacha_result.recipe = ((GachaRecipeModel)gachaRecipeModels.get(targetI)).recipe;
      pt_gacha_result.grade = ((GachaRecipeModel)gachaRecipeModels.get(targetI)).grade;
      AvatarBox avatarBox = role.getAvatarBox();
      EquipBox equipBox = role.getEquipBox();
      TitleBox titleBox = role.getTitleBox();
      MaterialBox materialBox = role.getMaterialBox();
      ConsumableBox consumableBox = role.getConsumableBox();
      AccountMoneyBox accountMoneyBox = account.getMoneyBox();
      MoneyBox moneyBox = role.getMoneyBox();
      EmblemBox emblemBox = role.getEmblemBox();
      EpicPieceBox epicPieceBox = account.getEpicPieceBox();
      BookmarkBox bookmarkBox = role.getBookmarkBox();
      CardBox cardBox = role.getCardBox();
      ChatFrameBox chatFrameBox = role.getChatFrameBox();
      CharFrameBox charFrameBox = role.getCharFrameBox();
      DamageBox damageBox = role.getDamageBox();
      switch (type) {
         case 1:
            pt_gacha_result.avatar = new ArrayList();
            PT_AVATAR_ITEM pt_avatar_item = EquipDataPool.createAvatar(index);
            pt_gacha_result.avatar.add(pt_avatar_item);
            avatarBox.addAvatar(pt_avatar_item);
            break;
         case 2:
         case 100:
            pt_gacha_result.equip = new ArrayList();
            PT_EQUIP pt_equip = EquipDataPool.createEquip(index);
            pt_gacha_result.equip.add(pt_equip);
            equipBox.addEquip(pt_equip);
            break;
         case 3:
            log.error("UNREACH==getRandom==TITLE==" + index);
            break;
         case 1000:
            pt_gacha_result.material = new ArrayList();
            PT_STACKABLE ptStackable = new PT_STACKABLE();
            ptStackable.index = index;
            ptStackable.count = cnt;
            pt_gacha_result.material.add(ptStackable);
            materialBox.updateMaterial(ptStackable);
            break;
         case 1001:
            pt_gacha_result.consume = new ArrayList();
            PT_STACKABLE ptStackableConsume = new PT_STACKABLE();
            ptStackableConsume.index = index;
            ptStackableConsume.count = cnt;
            pt_gacha_result.consume.add(ptStackableConsume);
            consumableBox.updateConsume(ptStackableConsume);
            break;
         case 1003:
            log.error("UNREACH==getRandom==ACCOUNT_CURRENCY==" + index);
            break;
         case 1004:
            log.error("UNREACH==getRandom==CURRENCY==" + index);
            break;
         case 1005:
            pt_gacha_result.emblem = new ArrayList();
            PT_STACKABLE ptStackableEmblem = new PT_STACKABLE();
            ptStackableEmblem.index = index;
            ptStackableEmblem.count = cnt;
            pt_gacha_result.emblem.add(ptStackableEmblem);
            emblemBox.updateEmblem(ptStackableEmblem);
            break;
         case 1006:
            log.error("UNREACH==getRandom==EPIC_PIECE==" + index);
            break;
         case 1007:
            pt_gacha_result.bookmark = new ArrayList();
            PT_STACKABLE ptStackableBookmark = new PT_STACKABLE();
            ptStackableBookmark.index = index;
            ptStackableBookmark.count = cnt;
            pt_gacha_result.bookmark.add(ptStackableBookmark);
            bookmarkBox.updateBookmark(ptStackableBookmark);
            break;
         case 1009:
            pt_gacha_result.card = new ArrayList();
            PT_STACKABLE ptStackableCard = new PT_STACKABLE();
            ptStackableCard.index = index;
            ptStackableCard.count = cnt;
            pt_gacha_result.card.add(ptStackableCard);
            cardBox.updateCard(ptStackableCard);
            break;
         case 10000:
            log.error("UNREACH==getRandom==DAMAGE_FONT==" + index);
            break;
         case 10001:
            log.error("UNREACH==getRandom==CHAT_FRAME==" + index);
            break;
         case 10002:
            log.error("UNREACH==getRandom==CHARACTER_FRAME==" + index);
            break;
         default:
            log.error("UNREACH==getRandom==type==" + type);
      }

      return pt_gacha_result;
   }
}
