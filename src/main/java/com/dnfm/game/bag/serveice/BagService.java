package com.dnfm.game.bag.serveice;

import com.dnfm.common.thread.IdGenerator;
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
import com.dnfm.game.bag.model.RePurStoItemBox;
import com.dnfm.game.bag.model.TitleBox;
import com.dnfm.game.config.ConsumeItem;
import com.dnfm.game.config.Equip;
import com.dnfm.game.config.Skin;
import com.dnfm.game.equip.EquipDataPool;
import com.dnfm.game.equip.model.EquipBox;
import com.dnfm.game.equip.model.RoleEquip;
import com.dnfm.game.item.ItemDataPool;
import com.dnfm.game.make.model.RemoveItem;
import com.dnfm.game.role.model.Account;
import com.dnfm.game.role.model.Role;
import com.dnfm.game.role.service.RoleService;
import com.dnfm.mina.protobuf.DEF_ITEM_CONSUMABLE;
import com.dnfm.mina.protobuf.PT_AVATAR_ITEM;
import com.dnfm.mina.protobuf.PT_CONTENTS_REWARD_INFO;
import com.dnfm.mina.protobuf.PT_CURRENCY_REWARD_INFO;
import com.dnfm.mina.protobuf.PT_EQUIP;
import com.dnfm.mina.protobuf.PT_ITEMS;
import com.dnfm.mina.protobuf.PT_ITEM_REWARD_INFO;
import com.dnfm.mina.protobuf.PT_ITEM_SELL;
import com.dnfm.mina.protobuf.PT_ITEM_SELL_GUID;
import com.dnfm.mina.protobuf.PT_ITEM_SELL_INDEX;
import com.dnfm.mina.protobuf.PT_LOOTS;
import com.dnfm.mina.protobuf.PT_MATERIAL_ITEM;
import com.dnfm.mina.protobuf.PT_MONEY_ITEM;
import com.dnfm.mina.protobuf.PT_REMOVEITEMS;
import com.dnfm.mina.protobuf.PT_REPURCHASE_EQUIP;
import com.dnfm.mina.protobuf.PT_REPURCHASE_STACKABLE;
import com.dnfm.mina.protobuf.PT_SKIN_ITEM;
import com.dnfm.mina.protobuf.PT_STACKABLE;
import com.dnfm.mina.protobuf.RES_LOOTING;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BagService {
   private static final Logger log = LoggerFactory.getLogger(BagService.class);
   private final Logger logger = LoggerFactory.getLogger(BagService.class);
   @Autowired
   RoleService roleService;

   public static void addItem(Role role, Account account, int index, int cnt, long guid) {
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
      int type = getIndexType(index);
      switch (type) {
         case 1:
            avatarBox.addAvatar(index);
            break;
         case 2:
         case 100:
            equipBox.addEquip(EquipDataPool.createEquip(index));
            break;
         case 3:
            titleBox.addTitle(index);
            break;
         case 1000:
            materialBox.updateMaterialAdd(index, cnt);
            break;
         case 1001:
            consumableBox.updateConsumeAdd(index, cnt);
            break;
         case 1003:
            accountMoneyBox.updateAccountCurrencyAdd(index, cnt);
            break;
         case 1004:
            moneyBox.addCnt(index, cnt);
            break;
         case 1005:
            emblemBox.updateEmblemAdd(index, cnt);
            break;
         case 1006:
            epicPieceBox.updateEpicPieceAdd(index, cnt);
            break;
         case 1007:
            bookmarkBox.updateBookmarkAdd(index, cnt);
            break;
         case 1009:
            cardBox.updateCardAdd(index, cnt);
            break;
         case 10000:
            damageBox.addDamageFont(index);
            break;
         case 10001:
            chatFrameBox.addChatFrame(index);
            break;
         case 10002:
            charFrameBox.addCharFrame(index);
            break;
         default:
            log.error("UNREACH==addItem==type==" + type);
      }

   }

   public static void subItem(Role role, Account account, int index, int cnt, long guid) {
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
      int type = getIndexType(index);
      switch (type) {
         case 1:
            avatarBox.remove(guid);
            break;
         case 2:
         case 100:
            equipBox.removeEquip(guid);
            break;
         case 3:
            titleBox.remove(guid);
            break;
         case 1000:
            materialBox.updateMaterialSub(index, cnt);
            break;
         case 1001:
            consumableBox.updateConsumeSub(index, cnt);
            break;
         case 1003:
            accountMoneyBox.updateAccountCurrencySub(index, cnt);
            break;
         case 1004:
            moneyBox.subCnt(index, cnt);
            break;
         case 1005:
            emblemBox.updateEmblemSub(index, cnt);
            break;
         case 1006:
            epicPieceBox.updateEpicPieceSub(index, cnt);
            break;
         case 1007:
            bookmarkBox.updateBookmarkSub(index, cnt);
            break;
         case 1009:
            cardBox.updateCardSub(index, cnt);
            break;
         case 10000:
            damageBox.remove(guid);
            break;
         case 10001:
            chatFrameBox.remove(guid);
            break;
         case 10002:
            charFrameBox.remove(guid);
            break;
         default:
            log.error("UNREACH==subItem==type==" + type);
      }

   }

   public static int getIndexTypeEqu(int index) {
      Equip equip = EquipDataPool.getEquip(index);
      int equType = equip.getEquiptype();
      return EquipDataPool.getEquType(equType);
   }

   public static int getTable(int index) {
      Integer res = (Integer)ItemDataPool.index2TbMap.get(index);
      return res == null ? -1 : res;
   }

   public static int getIndexType(int index) {
      if (index == 0) {
         return 1004;
      } else if (index == 2) {
         return 1003;
      } else {
         int table = getTable(index);
         if (table == 0) {
            Equip equip = EquipDataPool.getEquip(index);
            int equType = equip.getEquiptype();
            return EquipDataPool.getEquType(equType);
         } else if (table == 1) {
            ConsumeItem consumeItem = (ConsumeItem)ItemDataPool.consumeItemMap.get(index);
            int stackabletype = consumeItem.getStackabletype();
            return ItemDataPool.getConsumeType(stackabletype);
         } else if (table == 2) {
            Skin skin = (Skin)ItemDataPool.skinItemMap.get(index);
            int subtype = skin.getSubtype();
            return ItemDataPool.getSkinType(subtype);
         } else {
            log.error("UNREACH==getIndexType==index==" + table);
            return -1;
         }
      }
   }

   public static int getIndexTypeConsume(int index) {
      ConsumeItem consumeItem = (ConsumeItem)ItemDataPool.consumeItemMap.get(index);
      int stackabletype = consumeItem.getStackabletype();
      return ItemDataPool.getConsumeType(stackabletype);
   }

   public boolean repurchaseItems(Role role, List<Long> guids) {
      MaterialBox materialBox = role.getMaterialBox();
      ConsumableBox consumableBox = role.getConsumableBox();
      EmblemBox emblemBox = role.getEmblemBox();
      CardBox cardBox = role.getCardBox();
      EquipBox equipBox = role.getEquipBox();
      TitleBox titleBox = role.getTitleBox();
      RePurStoItemBox rePurStoItemBox = role.getRePurStoItem();
      int totalPrice = 0;

      for(Long guid : guids) {
         PT_REPURCHASE_EQUIP equ = rePurStoItemBox.getEquItem(guid);
         if (equ == null) {
            PT_REPURCHASE_EQUIP title = rePurStoItemBox.getTitleItem(guid);
            if (title == null) {
               PT_REPURCHASE_STACKABLE material = rePurStoItemBox.getMaterialItem(guid);
               if (material == null) {
                  PT_REPURCHASE_STACKABLE consume = rePurStoItemBox.getConsumeItem(guid);
                  if (consume == null) {
                     PT_REPURCHASE_STACKABLE emblem = rePurStoItemBox.getEmblemItem(guid);
                     if (emblem == null) {
                        PT_REPURCHASE_STACKABLE card = rePurStoItemBox.getCardItem(guid);
                        if (card != null) {
                           rePurStoItemBox.removeCard(guid);
                           cardBox.updateCardAdd(card.item.index, card.item.count);
                           ConsumeItem consumeItem = (ConsumeItem)ItemDataPool.consumeItemMap.get(card.item.index);
                           totalPrice += this.getStackableRepurPrice(consumeItem) * card.item.count;
                        }
                     } else {
                        rePurStoItemBox.removeEmblem(guid);
                        emblemBox.updateEmblemAdd(emblem.item.index, emblem.item.count);
                        ConsumeItem consumeItem = (ConsumeItem)ItemDataPool.consumeItemMap.get(emblem.item.index);
                        totalPrice += this.getStackableRepurPrice(consumeItem) * emblem.item.count;
                     }
                  } else {
                     rePurStoItemBox.removeConsume(guid);
                     consumableBox.updateConsumeAdd(consume.item.index, consume.item.count);
                     ConsumeItem consumeItem = (ConsumeItem)ItemDataPool.consumeItemMap.get(consume.item.index);
                     totalPrice += this.getStackableRepurPrice(consumeItem) * consume.item.count;
                  }
               } else {
                  rePurStoItemBox.removeMaterial(guid);
                  materialBox.updateMaterialAdd(material.item.index, material.item.count);
                  ConsumeItem consumeItem = (ConsumeItem)ItemDataPool.consumeItemMap.get(material.item.index);
                  totalPrice += this.getStackableRepurPrice(consumeItem) * material.item.count;
               }
            } else {
               rePurStoItemBox.removeTitle(guid);
               titleBox.addTitle(title.item);
               Equip equip = EquipDataPool.getEquip(title.item.index);
               totalPrice += this.getEquRepurPrice(equip);
            }
         } else {
            rePurStoItemBox.removeEqu(guid);
            equipBox.addEquip(equ.item);
            Equip equip = EquipDataPool.getEquip(equ.item.index);
            totalPrice += this.getEquRepurPrice(equip);
         }
      }

      if (role.getMoneyBox().getMoneyItem(0).count >= totalPrice) {
         role.getMoneyBox().submoney(totalPrice);
         return true;
      } else {
         return false;
      }
   }

   private int getEquRepurPrice(Equip equip) {
      return this.getEquSellPrice(equip) + 1000;
   }

   private int getEquSellPrice(Equip equip) {
      int rarityWeight = 1;
      int rarity = equip.getRarity();
      if (rarity == 0) {
         rarityWeight = 1;
      } else if (rarity == 1) {
         rarityWeight = 2;
      } else if (rarity == 2) {
         rarityWeight = 5;
      } else if (rarity == 3) {
         rarityWeight = 10;
      } else if (rarity == 6) {
         rarityWeight = 12;
      } else {
         if (rarity != 4) {
            log.error("UNREACH==getEquSellPrice==rarity:" + rarity + "==index==" + equip.getIndex());
            return -1;
         }

         rarityWeight = 15;
      }

      int basePrice = 1000;
      int level = equip.getMinlevel();
      double levelWeight = 0.2;
      double sellRate = (double)0.5F;
      return (int)((double)(rarityWeight * basePrice) * levelWeight * (double)level * sellRate);
   }

   private int getStackableRepurPrice(ConsumeItem consumeItem) {
      return consumeItem.getSellprice() != -1 ? consumeItem.getSellprice() + 1000 : this.getStackableSellPrice(consumeItem) + 1000;
   }

   private int getStackableSellPrice(ConsumeItem consumeItem) {
      int basePrice = 1000;
      double levelWeight = 0.2;
      double sellRate = (double)0.5F;
      int rarityWeight = 1;
      int rarity = consumeItem.getRarity();
      if (rarity == 0) {
         rarityWeight = 1;
      } else if (rarity == 1) {
         rarityWeight = 2;
      } else if (rarity == 2) {
         rarityWeight = 5;
      } else if (rarity == 3) {
         rarityWeight = 10;
      } else if (rarity == 6) {
         rarityWeight = 12;
      } else {
         if (rarity != 4) {
            log.error("UNREACH==getStackableSellPrice==rarity:" + rarity + "==index==" + consumeItem.getIndex());
            return -1;
         }

         rarityWeight = 15;
      }

      return (int)((double)(rarityWeight * basePrice) * levelWeight * sellRate);
   }

   public int calcStackablesPrice(Role role, List<PT_ITEM_SELL> stackables) {
      MaterialBox materialBox = role.getMaterialBox();
      ConsumableBox consumableBox = role.getConsumableBox();
      EmblemBox emblemBox = role.getEmblemBox();
      BookmarkBox bookmarkBox = role.getBookmarkBox();
      CardBox cardBox = role.getCardBox();
      RePurStoItemBox rePurStoItemBox = role.getRePurStoItem();
      int totalPrice = 0;

      for(PT_ITEM_SELL stackable : stackables) {
         int index = ((PT_ITEM_SELL_INDEX)stackable.indexes.get(0)).index;
         int cnt = ((PT_ITEM_SELL_INDEX)stackable.indexes.get(0)).count;
         ConsumeItem consumeItem = (ConsumeItem)ItemDataPool.consumeItemMap.get(index);
         int type = getIndexTypeConsume(index);
         int count = 0;
         switch (type) {
            case 1000:
               count = materialBox.getCnt(index);
               rePurStoItemBox.addMaterialRepur(index, cnt);
               if (cnt > count) {
                  cnt = count;
               }

               materialBox.updateMaterialSub(index, cnt);
               break;
            case 1001:
               count = consumableBox.getConsumable(index).count;
               rePurStoItemBox.addConsumeRepur(index, cnt);
               if (cnt > count) {
                  cnt = count;
               }

               consumableBox.updateConsumeSub(index, cnt);
               break;
            case 1002:
            case 1003:
            case 1004:
            case 1006:
            case 1007:
            case 1008:
            default:
               log.error("UNREACH==calcStackablesPrice==type==" + type + "index==" + index);
               return -1;
            case 1005:
               count = emblemBox.getEmblem(index).count;
               rePurStoItemBox.addEmblemRepur(index, cnt);
               if (cnt > count) {
                  cnt = count;
               }

               emblemBox.updateEmblemSub(index, cnt);
               break;
            case 1009:
               count = cardBox.getCard(index).count;
               rePurStoItemBox.addCardRepur(index, cnt);
               if (cnt > count) {
                  cnt = count;
               }

               cardBox.updateCardSub(index, cnt);
         }

         if (consumeItem == null) {
            log.error("UNREACH==calcStackablesPrice==index:" + index);
            return -1;
         }

         if (consumeItem.getSellprice() != -1) {
            totalPrice = consumeItem.getSellprice() * cnt;
         } else {
            int price = this.getStackableSellPrice(consumeItem);
            totalPrice += price * cnt;
         }
      }

      return totalPrice;
   }

   public int calcGuidsPrice(int type, Role role, List<PT_ITEM_SELL> guids) {
      EquipBox equipBox = role.getEquipBox();
      TitleBox titleBox = role.getTitleBox();
      RePurStoItemBox rePurStoItemBox = role.getRePurStoItem();
      int totalPrice = 0;

      for(PT_ITEM_SELL guidItem : guids) {
         long guid = ((PT_ITEM_SELL_GUID)guidItem.guids.get(0)).guid;
         if (type == 11) {
            PT_EQUIP title = titleBox.getTitle(guid);
            Equip equip = EquipDataPool.getEquip(title.index);
            if (equip == null) {
               log.error("UNREACH==calcGuidsPrice==title==null==type==" + type);
               return -1;
            }

            int price = this.getEquSellPrice(equip);
            totalPrice += price;
            titleBox.removeTitle(guid);
            rePurStoItemBox.addTitleRepur(title);
         } else {
            PT_EQUIP pt_equip = equipBox.getEquip(guid);
            Equip equip = EquipDataPool.getEquip(pt_equip.index);
            if (equip == null) {
               log.error("UNREACH==calcGuidsPrice==equip==null==type==" + type);
               return -1;
            }

            int price = this.getEquSellPrice(equip);
            totalPrice += price;
            equipBox.removeEquip(guid);
            rePurStoItemBox.addEquRepur(pt_equip);
         }
      }

      return totalPrice;
   }

   public void subEquList(Role role, List<PT_MATERIAL_ITEM> comsumeEquList) {
      AvatarBox avatarBox = role.getAvatarBox();
      EquipBox equipBox = role.getEquipBox();
      TitleBox titleBox = role.getTitleBox();

      for(PT_MATERIAL_ITEM item : comsumeEquList) {
         long guid = item.guid;
         int inventype = item.inventype;
         if (inventype == 1) {
            equipBox.removeEquip(guid);
         } else {
            this.logger.error("UNREACH==subEquList error inventype==" + inventype);
         }
      }

   }

   public PT_REMOVEITEMS getRemoveitems(Account account, Role role, List<RemoveItem> removeList) {
      PT_REMOVEITEMS removeitems = new PT_REMOVEITEMS();
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

      for(RemoveItem removeItem : removeList) {
         int index = removeItem.index;
         int cnt = removeItem.cnt;
         int type = getIndexTypeConsume(index);
         switch (type) {
            case 1000:
               if (removeitems.materialitems == null) {
                  removeitems.materialitems = new ArrayList();
               }

               PT_STACKABLE material = materialBox.getMaterial(index);
               material.count = material.count - cnt;
               materialBox.addMaterial(material);
               removeitems.materialitems.add(material);
               break;
            case 1001:
               if (removeitems.consumeitems == null) {
                  removeitems.consumeitems = new ArrayList();
               }

               DEF_ITEM_CONSUMABLE consume = consumableBox.getConsumable(index);
               consume.count = consume.count - cnt;
               consumableBox.addConsumable(consume);
               PT_STACKABLE consume2 = new PT_STACKABLE();
               consume2.index = consume.index;
               consume2.count = consume.count;
               removeitems.consumeitems.add(consume2);
               break;
            case 1002:
            case 1003:
            case 1004:
            case 1008:
            default:
               log.error("UNREACH==getRemoveItems==type==" + type + "==index==" + index);
               break;
            case 1005:
               if (removeitems.emblemitems == null) {
                  removeitems.emblemitems = new ArrayList();
               }

               PT_STACKABLE emblem = emblemBox.getEmblem(index);
               emblem.count = emblem.count - cnt;
               emblemBox.addEmblem(emblem);
               removeitems.emblemitems.add(emblem);
               break;
            case 1006:
               if (removeitems.epicpieceitems == null) {
                  removeitems.epicpieceitems = new ArrayList();
               }

               PT_STACKABLE epicPiece = epicPieceBox.getEpicPiece(index);
               epicPiece.count = epicPiece.count - cnt;
               epicPieceBox.addEpicPiece(epicPiece);
               removeitems.epicpieceitems.add(epicPiece);
               break;
            case 1007:
               if (removeitems.bookmarkitems == null) {
                  removeitems.bookmarkitems = new ArrayList();
               }

               PT_STACKABLE bookmark = bookmarkBox.getBookmark(index);
               bookmark.count = bookmark.count - cnt;
               bookmarkBox.putBookmark(bookmark);
               removeitems.bookmarkitems.add(bookmark);
               break;
            case 1009:
               if (removeitems.carditems == null) {
                  removeitems.carditems = new ArrayList();
               }

               PT_STACKABLE card = cardBox.getCard(index);
               card.count = card.count - cnt;
               cardBox.addCard(card);
               removeitems.carditems.add(card);
         }
      }

      return removeitems;
   }

   public PT_CONTENTS_REWARD_INFO getRewardsMakeStackable(Account account, Role role, List<Integer> indexList) {
      PT_CONTENTS_REWARD_INFO rewards = new PT_CONTENTS_REWARD_INFO();
      rewards.items = new PT_ITEM_REWARD_INFO();
      PT_ITEMS rewardItems = new PT_ITEMS();
      int cnt = 1;

      for(Integer index : indexList) {
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
         int type = getIndexType(index);
         switch (type) {
            case 1:
               if (rewardItems.avataritems == null) {
                  rewardItems.avataritems = new ArrayList();
               }

               PT_AVATAR_ITEM pt_avatar_item = new PT_AVATAR_ITEM();
               pt_avatar_item.index = index;
               pt_avatar_item.guid = IdGenerator.getNextId();
               rewardItems.avataritems.add(pt_avatar_item);
               avatarBox.addAvatar(index);
               break;
            case 2:
            case 100:
               if (rewardItems.equipitems == null) {
                  rewardItems.equipitems = new ArrayList();
               }

               PT_EQUIP pt_equip = EquipDataPool.createEquip(index);
               rewardItems.equipitems.add(pt_equip);
               equipBox.addEquip(pt_equip);
               break;
            case 3:
               if (rewardItems.titleitems == null) {
                  rewardItems.titleitems = new ArrayList();
               }

               PT_EQUIP title = EquipDataPool.createTitle(index);
               rewardItems.titleitems.add(title);
               titleBox.addTitle(title);
               break;
            case 1000:
               if (rewardItems.materialitems == null) {
                  rewardItems.materialitems = new ArrayList();
               }

               PT_STACKABLE ptStackableMaterial = new PT_STACKABLE();
               ptStackableMaterial.index = index;
               ptStackableMaterial.count = cnt;
               rewardItems.materialitems.add(ptStackableMaterial);
               materialBox.updateMaterialAdd(index, cnt);
               break;
            case 1001:
               if (rewardItems.consumeitems == null) {
                  rewardItems.consumeitems = new ArrayList();
               }

               PT_STACKABLE ptStackableConsume = new PT_STACKABLE();
               ptStackableConsume.index = index;
               ptStackableConsume.count = cnt;
               rewardItems.consumeitems.add(ptStackableConsume);
               consumableBox.updateConsumeAdd(index, cnt);
               break;
            case 1003:
               log.error("UNREACH==getRewards==type==ACCOUNT_CURRENCY==index==" + index);
               break;
            case 1004:
               if (rewards.currency == null) {
                  rewards.currency = new PT_CURRENCY_REWARD_INFO();
               }

               if (rewards.currency.currency == null) {
                  rewards.currency.currency = new ArrayList();
               }

               if (rewards.paymentcurrency == null) {
                  rewards.paymentcurrency = new PT_CURRENCY_REWARD_INFO();
               }

               if (rewards.paymentcurrency.currency == null) {
                  rewards.paymentcurrency.currency = new ArrayList();
               }

               PT_MONEY_ITEM ptMoneyItem = new PT_MONEY_ITEM();
               ptMoneyItem.index = index;
               ptMoneyItem.count = cnt;
               rewards.paymentcurrency.currency.add(ptMoneyItem);
               moneyBox.addCnt(index, cnt);
               PT_MONEY_ITEM pt_money_item = moneyBox.getMoneyItem(index);
               rewards.currency.currency.add(pt_money_item);
               break;
            case 1005:
               if (rewardItems.emblemitems == null) {
                  rewardItems.emblemitems = new ArrayList();
               }

               PT_STACKABLE ptStackableEmblem = new PT_STACKABLE();
               ptStackableEmblem.index = index;
               ptStackableEmblem.count = cnt;
               rewardItems.emblemitems.add(ptStackableEmblem);
               emblemBox.updateEmblemAdd(index, cnt);
               break;
            case 1006:
               if (rewardItems.epicpieceitems == null) {
                  rewardItems.epicpieceitems = new ArrayList();
               }

               PT_STACKABLE ptStackableEpicPiece = new PT_STACKABLE();
               ptStackableEpicPiece.index = index;
               ptStackableEpicPiece.count = cnt;
               rewardItems.epicpieceitems.add(ptStackableEpicPiece);
               epicPieceBox.updateEpicPieceAdd(index, cnt);
               break;
            case 1007:
               if (rewardItems.bookmarkitems == null) {
                  rewardItems.bookmarkitems = new ArrayList();
               }

               PT_STACKABLE ptStackableBookmark = new PT_STACKABLE();
               ptStackableBookmark.index = index;
               ptStackableBookmark.count = cnt;
               rewardItems.bookmarkitems.add(ptStackableBookmark);
               bookmarkBox.updateBookmarkAdd(index, cnt);
               break;
            case 1009:
               if (rewardItems.carditems == null) {
                  rewardItems.carditems = new ArrayList();
               }

               PT_STACKABLE ptStackableCard = new PT_STACKABLE();
               ptStackableCard.index = index;
               ptStackableCard.count = cnt;
               rewardItems.carditems.add(ptStackableCard);
               cardBox.updateCardAdd(index, cnt);
               break;
            case 10000:
               if (rewardItems.damagefontitems == null) {
                  rewardItems.damagefontitems = new ArrayList();
               }

               PT_SKIN_ITEM damageFont = new PT_SKIN_ITEM();
               damageFont.index = index;
               damageFont.guid = IdGenerator.getNextId();
               rewardItems.damagefontitems.add(damageFont);
               damageBox.addDamageFont(damageFont);
               break;
            case 10001:
               if (rewardItems.chatframeitems == null) {
                  rewardItems.chatframeitems = new ArrayList();
               }

               PT_SKIN_ITEM chatFrame = new PT_SKIN_ITEM();
               chatFrame.index = index;
               chatFrame.guid = IdGenerator.getNextId();
               rewardItems.chatframeitems.add(chatFrame);
               chatFrameBox.addChatFrame(chatFrame);
               break;
            case 10002:
               if (rewardItems.characterframeitems == null) {
                  rewardItems.characterframeitems = new ArrayList();
               }

               PT_SKIN_ITEM characterFrame = new PT_SKIN_ITEM();
               characterFrame.index = index;
               characterFrame.guid = IdGenerator.getNextId();
               rewardItems.characterframeitems.add(characterFrame);
               charFrameBox.addCharFrame(characterFrame);
               break;
            default:
               log.error("UNREACH==getRewards==type==" + type + "==index==" + index);
         }
      }

      rewards.items.inventory = rewardItems;
      return rewards;
   }

   public RES_LOOTING getResLooting(List<PT_LOOTS> lootList, List<PT_STACKABLE> consumeList, Role role, Account account) {
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
      RES_LOOTING res_looting = new RES_LOOTING();
      int totalCnt = 0;
      int totalMoney = 0;

      for(PT_LOOTS loot : lootList) {
         int index = loot.itemindex;
         long cnt = loot.qty;
         int loottype = loot.loottype;
         totalCnt = (int)((long)totalCnt + cnt);
         if (loottype == 2) {
            totalMoney += loottype;
         } else if (loottype == 3) {
            int type = getIndexType(index);
            switch (type) {
               case 1:
                  if (res_looting.items == null) {
                     res_looting.items = new PT_ITEMS();
                  }

                  if (res_looting.items.avataritems == null) {
                     res_looting.items.avataritems = new ArrayList();
                  }

                  PT_AVATAR_ITEM pt_avatar_item = new PT_AVATAR_ITEM();
                  pt_avatar_item.guid = IdGenerator.getNextId();
                  pt_avatar_item.index = index;
                  pt_avatar_item.count = (int)cnt;
                  res_looting.items.avataritems.add(pt_avatar_item);
                  avatarBox.addAvatar(pt_avatar_item);
                  break;
               case 2:
               case 100:
                  if (res_looting.items == null) {
                     res_looting.items = new PT_ITEMS();
                  }

                  if (res_looting.items.equipitems == null) {
                     res_looting.items.equipitems = new ArrayList();
                  }

                  PT_EQUIP pt_equip_item = new PT_EQUIP();
                  pt_equip_item.guid = IdGenerator.getNextId();
                  pt_equip_item.index = index;
                  pt_equip_item.count = (int)cnt;
                  res_looting.items.equipitems.add(pt_equip_item);
                  equipBox.addEquip(pt_equip_item);
                  break;
               case 3:
                  if (res_looting.items == null) {
                     res_looting.items = new PT_ITEMS();
                  }

                  if (res_looting.items.titleitems == null) {
                     res_looting.items.titleitems = new ArrayList();
                  }

                  PT_EQUIP pt_title_item = new PT_EQUIP();
                  pt_title_item.guid = IdGenerator.getNextId();
                  pt_title_item.index = index;
                  pt_title_item.count = (int)cnt;
                  res_looting.items.titleitems.add(pt_title_item);
                  titleBox.addTitle(pt_title_item);
                  break;
               case 1000:
                  if (res_looting.items == null) {
                     res_looting.items = new PT_ITEMS();
                  }

                  if (res_looting.items.materialitems == null) {
                     res_looting.items.materialitems = new ArrayList();
                  }

                  PT_STACKABLE pt_material_item = new PT_STACKABLE();
                  pt_material_item.index = index;
                  pt_material_item.count = (int)cnt;
                  res_looting.items.materialitems.add(pt_material_item);
                  materialBox.updateMaterial(pt_material_item);
                  break;
               case 1001:
                  if (res_looting.items == null) {
                     res_looting.items = new PT_ITEMS();
                  }

                  if (res_looting.items.consumeitems == null) {
                     res_looting.items.consumeitems = new ArrayList();
                  }

                  PT_STACKABLE pt_consume_item = new PT_STACKABLE();
                  pt_consume_item.index = index;
                  pt_consume_item.count = (int)cnt;
                  res_looting.items.consumeitems.add(pt_consume_item);
                  consumableBox.updateConsume(pt_consume_item);
                  break;
               case 1003:
                  if (res_looting.accountcurrency == null) {
                     res_looting.accountcurrency = new ArrayList();
                  }

                  PT_MONEY_ITEM ptMoneyItem = new PT_MONEY_ITEM();
                  ptMoneyItem.index = index;
                  ptMoneyItem.count = (int)cnt;
                  res_looting.accountcurrency.add(ptMoneyItem);
                  accountMoneyBox.updateAccountCurrency(ptMoneyItem);
                  break;
               case 1004:
                  if (res_looting.currency == null) {
                     res_looting.currency = new ArrayList();
                  }

                  PT_MONEY_ITEM ptMoneyItem1 = new PT_MONEY_ITEM();
                  ptMoneyItem1.index = index;
                  ptMoneyItem1.count = (int)cnt;
                  res_looting.currency.add(ptMoneyItem1);
                  moneyBox.updateCurrency(ptMoneyItem1);
                  break;
               case 1005:
                  if (res_looting.items == null) {
                     res_looting.items = new PT_ITEMS();
                  }

                  if (res_looting.items.emblemitems == null) {
                     res_looting.items.emblemitems = new ArrayList();
                  }

                  PT_STACKABLE ptEmblem = new PT_STACKABLE();
                  ptEmblem.index = index;
                  ptEmblem.count = (int)cnt;
                  res_looting.items.emblemitems.add(ptEmblem);
                  emblemBox.updateEmblem(ptEmblem);
                  break;
               case 1006:
                  if (res_looting.items == null) {
                     res_looting.items = new PT_ITEMS();
                  }

                  if (res_looting.items.epicpieceitems == null) {
                     res_looting.items.epicpieceitems = new ArrayList();
                  }

                  PT_STACKABLE ptEpicPiece = new PT_STACKABLE();
                  ptEpicPiece.index = index;
                  ptEpicPiece.count = (int)cnt;
                  res_looting.items.epicpieceitems.add(ptEpicPiece);
                  epicPieceBox.updateEpicPiece(ptEpicPiece);
                  break;
               case 1007:
                  if (res_looting.items == null) {
                     res_looting.items = new PT_ITEMS();
                  }

                  if (res_looting.items.bookmarkitems == null) {
                     res_looting.items.bookmarkitems = new ArrayList();
                  }

                  PT_STACKABLE ptBookmark = new PT_STACKABLE();
                  ptBookmark.index = index;
                  ptBookmark.count = (int)cnt;
                  res_looting.items.bookmarkitems.add(ptBookmark);
                  bookmarkBox.updateBookmark(ptBookmark);
                  break;
               case 1009:
                  if (res_looting.items == null) {
                     res_looting.items = new PT_ITEMS();
                  }

                  if (res_looting.items.carditems == null) {
                     res_looting.items.carditems = new ArrayList();
                  }

                  PT_STACKABLE ptCard = new PT_STACKABLE();
                  ptCard.index = index;
                  ptCard.count = (int)cnt;
                  res_looting.items.carditems.add(ptCard);
                  cardBox.updateCard(ptCard);
                  break;
               case 10000:
                  if (res_looting.items == null) {
                     res_looting.items = new PT_ITEMS();
                  }

                  if (res_looting.items.damagefontitems == null) {
                     res_looting.items.damagefontitems = new ArrayList();
                  }

                  PT_SKIN_ITEM ptDamageFont = new PT_SKIN_ITEM();
                  ptDamageFont.index = index;
                  ptDamageFont.guid = IdGenerator.getNextId();
                  res_looting.items.damagefontitems.add(ptDamageFont);
                  damageBox.addDamageFont(ptDamageFont);
                  break;
               case 10001:
                  if (res_looting.items == null) {
                     res_looting.items = new PT_ITEMS();
                  }

                  if (res_looting.items.chatframeitems == null) {
                     res_looting.items.chatframeitems = new ArrayList();
                  }

                  PT_SKIN_ITEM ptChatFrame = new PT_SKIN_ITEM();
                  ptChatFrame.index = index;
                  ptChatFrame.guid = IdGenerator.getNextId();
                  res_looting.items.chatframeitems.add(ptChatFrame);
                  chatFrameBox.addChatFrame(ptChatFrame);
                  break;
               case 10002:
                  if (res_looting.items == null) {
                     res_looting.items = new PT_ITEMS();
                  }

                  if (res_looting.items.characterframeitems == null) {
                     res_looting.items.characterframeitems = new ArrayList();
                  }

                  PT_SKIN_ITEM ptCharacterFrame = new PT_SKIN_ITEM();
                  ptCharacterFrame.index = index;
                  ptCharacterFrame.guid = IdGenerator.getNextId();
                  res_looting.items.characterframeitems.add(ptCharacterFrame);
                  charFrameBox.addCharFrame(ptCharacterFrame);
                  break;
               default:
                  this.logger.error("UNREACH==getResLooting==type==" + type);
            }
         } else {
            this.logger.error("UNREACH==getPtItems==loottype=" + loottype + "==index=" + index);
         }

         if (totalCnt != 0) {
            res_looting.earnitemcount = totalCnt;
         }

         if (totalMoney != 0) {
            if (res_looting.currency == null) {
               res_looting.currency = new ArrayList();
            }

            PT_MONEY_ITEM ptMoneyItem = new PT_MONEY_ITEM();
            ptMoneyItem.count = totalMoney;
            res_looting.currency.add(ptMoneyItem);
         }
      }

      if (!Util.isEmpty(consumeList)) {
         res_looting.removeitems = new PT_REMOVEITEMS();

         for(int i = 0; i < consumeList.size(); ++i) {
            PT_STACKABLE ptStackable = (PT_STACKABLE)consumeList.get(i);
            int cnt = ptStackable.count;
            int index = ptStackable.index;
            int type = getIndexTypeConsume(index);
            switch (type) {
               case 1000:
                  if (res_looting.removeitems.materialitems == null) {
                     res_looting.removeitems.materialitems = new ArrayList();
                  }

                  PT_STACKABLE ptStackableMaterial = materialBox.getMaterial(index);
                  ptStackableMaterial.count = ptStackableMaterial.count - cnt;
                  res_looting.removeitems.materialitems.add(ptStackableMaterial);
                  break;
               case 1001:
                  if (res_looting.removeitems.consumeitems == null) {
                     res_looting.removeitems.consumeitems = new ArrayList();
                  }

                  DEF_ITEM_CONSUMABLE ptStackableConsume = consumableBox.getConsumable(index);
                  ptStackableConsume.count = ptStackableConsume.count - cnt;
                  PT_STACKABLE ptStackableConsume2 = new PT_STACKABLE();
                  ptStackableConsume2.count = ptStackableConsume.count;
                  ptStackableConsume2.index = ptStackableConsume.index;
                  res_looting.removeitems.consumeitems.add(ptStackableConsume2);
                  break;
               case 1002:
               case 1003:
               case 1004:
               case 1008:
               default:
                  log.error("UNREACH==getResLooting-consumeList==type==" + type);
                  break;
               case 1005:
                  if (res_looting.removeitems.emblemitems == null) {
                     res_looting.removeitems.emblemitems = new ArrayList();
                  }

                  PT_STACKABLE ptStackableEmblem = emblemBox.getEmblem(index);
                  ptStackableEmblem.count = ptStackableEmblem.count - cnt;
                  res_looting.removeitems.emblemitems.add(ptStackableEmblem);
                  break;
               case 1006:
                  if (res_looting.removeitems.epicpieceitems == null) {
                     res_looting.removeitems.epicpieceitems = new ArrayList();
                  }

                  PT_STACKABLE ptStackableEpicPiece = epicPieceBox.getEpicPiece(index);
                  ptStackableEpicPiece.count = ptStackableEpicPiece.count - cnt;
                  res_looting.removeitems.epicpieceitems.add(ptStackableEpicPiece);
                  break;
               case 1007:
                  if (res_looting.removeitems.bookmarkitems == null) {
                     res_looting.removeitems.bookmarkitems = new ArrayList();
                  }

                  PT_STACKABLE ptStackableBookmark = bookmarkBox.getBookmark(index);
                  ptStackableBookmark.count = ptStackableBookmark.count - cnt;
                  res_looting.removeitems.bookmarkitems.add(ptStackableBookmark);
                  break;
               case 1009:
                  if (res_looting.removeitems.carditems == null) {
                     res_looting.removeitems.carditems = new ArrayList();
                  }

                  PT_STACKABLE ptStackableCard = cardBox.getCard(index);
                  ptStackableCard.count = ptStackableCard.count - cnt;
                  res_looting.removeitems.carditems.add(ptStackableCard);
            }
         }
      }

      return res_looting;
   }

   public void addEquip(Role role, int index, int cnt) {
   }

   public void addConsume(Role role, int index, int cnt) {
   }

   public void addSkin(Role role, int index, int cnt) {
   }

   public void useItem(Role role, int index, int cnt) {
      int table = getTable(index);
      if (table != -1) {
         if (table == 0) {
            this.useEquip(role, index, cnt);
         } else if (table == 1) {
            this.useConsume(role, index, cnt);
         } else if (table == 2) {
            this.useSkin(role, index, cnt);
         }

      }
   }

   public void useEquip(Role role, int index, int cnt) {
      Equip equip = EquipDataPool.getEquip(index);
      int type = EquipDataPool.getEquType(equip.getEquiptype());
      switch (type) {
         case 1:
            this.useAvatar(role, index, cnt);
            break;
         case 2:
            this.useWeapon(role, index, cnt);
            break;
         case 3:
            this.useTitle(role, index, cnt);
            break;
         case 13:
            this.useCreature(role, index, cnt);
            break;
         default:
            this.logger.error("ERROR====useEquip error, type is {}", type);
      }

   }

   public void useTitle(Role role, int index, int cnt) {
   }

   public void useAvatar(Role role, int index, int cnt) {
   }

   public void useWeapon(Role role, int index, int cnt) {
   }

   public void useFlag(Role role, int index, int cnt) {
   }

   public void useArtifact(Role role, int index, int cnt) {
   }

   public void useCreature(Role role, int index, int cnt) {
   }

   public void useSD(Role role, int index, int cnt) {
   }

   public void useConsume(Role role, int index, int cnt) {
      ConsumeItem var10000 = (ConsumeItem)ItemDataPool.consumeItemMap.get(index);
   }

   public void useSkin(Role role, int index, int cnt) {
      Skin var10000 = (Skin)ItemDataPool.skinItemMap.get(index);
   }

   public void add(Role role, PT_EQUIP roleEquip) {
      EquipBox equipBox = role.getEquipBox();
      equipBox.addEquip(roleEquip);
   }

   public void delRoleEquip(RoleEquip roleEquip, Role role) {
   }

   private int getRandom(List<Integer> Jade1) {
      Random random = new Random();
      int index = random.nextInt(Jade1.size());
      if (index == 0) {
         index = random.nextInt(Jade1.size());
      }

      return (Integer)Jade1.get(index);
   }

   public PT_ITEM_REWARD_INFO getshoprewards(int index, int count, int monetindex) {
      int itemtype = 0;
      int newtype = 0;
      int equiptype = -1;
      Map<Integer, ConsumeItem> consumeItemMap = ItemDataPool.consumeItemMap;
      ConsumeItem consumeItem = (ConsumeItem)consumeItemMap.get(index);
      PT_ITEM_REWARD_INFO items = new PT_ITEM_REWARD_INFO();
      PT_ITEMS inventory = new PT_ITEMS();
      if (consumeItemMap.get(index) != null) {
         newtype = ((ConsumeItem)consumeItemMap.get(index)).getStackabletype();
         if (newtype == 0 | newtype == 17 | newtype == 22) {
            PT_STACKABLE ptStackable = new PT_STACKABLE();
            ptStackable.index = index;
            ptStackable.count = count;
         }
      } else {
         Map<Integer, Equip> equipItemMap = EquipDataPool.index2Equip;
         if (equipItemMap.get(index) != null) {
            itemtype = 2;
            equiptype = ((Equip)equipItemMap.get(index)).getEquiptype();
         }
      }

      items.inventory = inventory;
      return items;
   }

   public int delRoloEquip(String equipName, int num, boolean useLimit, Role role) {
      return 0;
   }

   public void useItem(byte pos, short useNum, IoSession session) {
   }

   public void addMall(String name, boolean isGold, Role role, int num) {
   }

   public void addMall(String name, boolean isGold, Role role) {
   }
}
