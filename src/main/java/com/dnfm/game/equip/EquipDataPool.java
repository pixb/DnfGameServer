package com.dnfm.game.equip;

import cn.hutool.core.util.RandomUtil;
import com.dnfm.common.thread.IdGenerator;
import com.dnfm.game.config.Equip;
import com.dnfm.game.equip.model.UpgradeInfo;
import com.dnfm.game.item.ItemDataPool;
import com.dnfm.game.role.model.Role;
import com.dnfm.mina.protobuf.PT_AVATAR_ITEM;
import com.dnfm.mina.protobuf.PT_EQUIP;
import com.dnfm.mina.protobuf.PT_EQUIPPED;
import com.dnfm.mina.protobuf.PT_RANDOMOPTION_ITEM;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class EquipDataPool {
   public static final String equTypeWeight = "weapon|1 coat|0.75 pants|0.75 shoulder|0.75 waist|0.75 shoes|0.75 amulet|0.75 wrist|0.75 ring|0.75";
   public static final String accValueTableStr = "0|0|0|0|0|0|0 1|1|3|4|7|13|13 2|3|7|9|16|31|31 3|5|12|16|28|55|55 4|8|18|26|44|87|87 5|12|27|39|66|130|130 6|18|39|56|95|188|188 7|26|54|79|134|265|265 8|36|75|110|186|368|368 9|50|103|151|255|506|506 10|68|141|207|349|696|696 11|107|220|323|544|1086|1086 12|168|343|506|852|1700|1700 13|302|616|911|1531|3056|3056 14|585|1191|1765|2962|5915|5915 15|1259|2555|3793|6359|12699|12699 16|4068|8250|12255|20538|41022|41022 17|8227|16674|24778|41517|82928|82928 18|13552|27458|40813|68374|136579|136579 19|22003|44568|66256|110988|221705|221705 20|35936|72776|108205|181244|362054|362054 21|1727627|3518526|5201343|8746145|17441018|17441018 22|1814008|3694452|5461410|9183452|18313069|18313069 23|1904708|3879175|5734481|9642625|19228722|19228722 24|1999943|4073134|6021205|10124756|20190158|20190158 25|2099940|4276791|6322265|10630994|21199666|21199666 26|2204937|4490631|6638378|11162544|22259649|22259649 27|2315184|4715163|6970297|11720671|23372631|23372631 28|2430943|4950921|7318812|12306705|24541263|24541263 29|2552490|5198467|7684753|12922040|25768326|25768326 30|2680115|5458390|8068991|13568142|27056742|27056742";
   public static final String baseValueStr = "upgrade|230 amplify|55000 reforge|2500 transmission|230";
   public static final String upgradeSuccRateStr = "0|100|0|0|0|0|0|0|0 1|100|0|0|0|0|0|0|0 2|100|0|0|0|0|0|0|0 3|100|0|0|0|0|0|0|0 4|80|0|0|0|0|0|0|0 5|70|0|0|0|0|0|0|0 6|60|0|0|0|0|0|0|0 7|50|0|0|0|0|0|0|0 8|40|0|0|0|0|0|0|0 9|30|0|0|0|0|0|0|0 10|15|0|0|0|0|35|0|35 11|10|0|0|0|0|20|0|20 12|7|0|0|0|0|14|0|14 13|5|0|0|0|0|10|0|10 14|3|0|0|0|0|6|0|6 15|1|0|0|0|0|2|0|2 16|1|0|0|0|0|2|0|2 17|1|0|0|0|0|2|0|2 18|1|0|0|0|0|2|0|2 19|1|0|0|0|0|2|0|2 20|1|0|0|0|0|2|0|2";
   public static final String materialInfoStr = "upgrade|2013100894|5000 amplify|2013100893|35000 reforge|2013100892|20000 inherit|2013100894|5000 amplify_assign|2013100708|50000 amplify_change|2013100708|50000 transmission|2013100894|5000 advanced_upgrade|2013106035|500000 advanced_transmission|2013106035|500000 advanced_inherit|2013106035|500000";
   public static final String upgradePointMaxStr = "0|1|2|3|4|7 1|2|3|5|7|14 2|2|3|5|7|14 3|3|5|7|11|21 4|5|10|15|25|50 5|8|15|23|38|75 6|13|25|37|62|123 7|18|35|52|87|173 8|26|52|77|128|256 9|36|72|107|178|356 10|86|172|258|429|858 11|139|278|417|695|1389 12|313|626|939|1565|3130 13|670|1340|2009|3348|6696 14|1612|3223|4835|8058|16115 15|6807|13613|20419|34031|68061 16|10078|20155|30232|50386|100771 17|12903|25805|38707|64511|129022 18|20477|40953|61429|102382|204764 19|33759|67518|101277|168795|337589";
   public static final int AVATAR = 1;
   public static final int WEAPON = 2;
   public static final int TITLE = 3;
   public static final int COAT = 101;
   public static final int SHOULDER = 102;
   public static final int PANTS = 103;
   public static final int SHOES = 104;
   public static final int WAIST = 105;
   public static final int AMULET = 106;
   public static final int WRIST = 107;
   public static final int RING = 108;
   public static final int EQUIP = 100;
   public static final int SUPPORT = 10;
   public static final int MAGICSTONE = 11;
   public static final int SKILLWEAPON = 12;
   public static final int CREATURE = 13;
   public static Map<Integer, Integer> index2Score;
   public static Map<Integer, Equip> index2Equip;
   public static Map<Integer, String> enumMap = new HashMap();
   public static Map<String, Double> equWeightMap = new HashMap();
   public static Map<Integer, List<Double>> accValueTable = new HashMap();
   public static Map<Integer, List<String>> baseValueTable = new HashMap();
   public static Map<Integer, List<Double>> upgradeSuccRateTable = new HashMap();
   public static Map<String, Double> materialInfoMap = new HashMap();
   public static Map<Integer, List<Double>> upgradePointMaxMap = new HashMap();

   public static int getEquType(int equType) {
      switch (equType) {
         case 0:
         case 1:
         case 2:
         case 3:
         case 4:
         case 5:
         case 6:
         case 7:
         case 8:
         case 9:
         case 10:
            return 1;
         case 11:
            return 2;
         case 12:
            return 3;
         case 13:
         case 14:
         case 15:
         case 16:
         case 17:
         case 18:
         case 19:
         case 20:
            return 100;
         case 21:
            return 10;
         case 22:
            return 11;
         case 23:
            return 12;
         case 24:
            return 13;
         default:
            return -1;
      }
   }

   public static List<PT_RANDOMOPTION_ITEM> selectRoption(List<PT_RANDOMOPTION_ITEM> roption, int index, boolean locked) {
      List<PT_RANDOMOPTION_ITEM> roptionnew = new ArrayList();
      int count = 1;
      if (roption.size() != 3) {
         ++index;
      }

      for(PT_RANDOMOPTION_ITEM ptRandomOptionItem : roption) {
         if (ptRandomOptionItem.index < 4000) {
            if (index == count) {
               if (locked) {
                  ptRandomOptionItem.locked = locked;
               } else {
                  ptRandomOptionItem.locked = false;
               }

               ++count;
            } else {
               ++count;
            }
         }

         roptionnew.add(ptRandomOptionItem);
      }

      return roptionnew;
   }

   public static int changeEpic(long guid, Role role) {
      int index = 0;
      PT_EQUIPPED ptEquipped = role.getEquippedBox().getEquipped(guid);
      List<PT_RANDOMOPTION_ITEM> roptionnew = new ArrayList<>();
      new ArrayList<>();
      PT_EQUIP equip = new PT_EQUIP();
      int equipType = 0;
      int minlevel = 0;
      List<PT_RANDOMOPTION_ITEM> roption;
      if (ptEquipped == null) {
         equip = role.getEquipBox().getEquip(guid);
         roption = equip.getRoption();
         equipType = equip.equiptype;
         minlevel = equip.minlevel;
      } else {
         roption = ptEquipped.getRoption();
         equipType = ptEquipped.equiptype;
         minlevel = ptEquipped.minlevel;
      }

      for(PT_RANDOMOPTION_ITEM ptRandomOptionItem : roption) {
         if (ptRandomOptionItem.index > 4000) {
            if (equipType != 13 && equipType != 14 && equipType != 15 && equipType != 16 && equipType != 17) {
               if (equipType == 11) {
                  ptRandomOptionItem.index = getEpicWeaponRopIndex(RandomUtil.randomDouble((double)0.0F, (double)100.0F), minlevel);
               } else {
                  ptRandomOptionItem.index = getRingEpicRopIndex(RandomUtil.randomDouble((double)0.0F, (double)100.0F));
               }
            } else {
               ptRandomOptionItem.index = getEpicRopIndex(RandomUtil.randomDouble((double)0.0F, (double)100.0F), minlevel);
            }

            index = ptRandomOptionItem.index;
         }

         roptionnew.add(ptRandomOptionItem);
      }

      if (ptEquipped == null) {
         equip.setRoption(roptionnew);
         role.getEquipBox().addEquip(equip);
      } else {
         ptEquipped.setRoption(roptionnew);
         role.getEquippedBox().updateEquip(ptEquipped);
      }

      return index;
   }

   public static List<PT_RANDOMOPTION_ITEM> getrop(int rarity, int equipType, int minlevel) {
      List<PT_RANDOMOPTION_ITEM> roption = new ArrayList();
      PT_RANDOMOPTION_ITEM ptRandomOptionItem = new PT_RANDOMOPTION_ITEM();
      PT_RANDOMOPTION_ITEM ptRandomOptionItem1 = new PT_RANDOMOPTION_ITEM();
      PT_RANDOMOPTION_ITEM ptRandomOptionItem2 = new PT_RANDOMOPTION_ITEM();
      if (equipType != 11 && equipType != 13 && equipType != 14 && equipType != 15 && equipType != 16 && equipType != 17) {
         ptRandomOptionItem1.index = getRingRopIndex(RandomUtil.randomDouble((double)0.0F, (double)100.0F));
         ptRandomOptionItem2.index = getRingRopIndex(RandomUtil.randomDouble((double)0.0F, (double)100.0F));
         ptRandomOptionItem.index = getRingEpicRopIndex(RandomUtil.randomDouble((double)0.0F, (double)100.0F));
         ptRandomOptionItem.unique = true;
      } else {
         ptRandomOptionItem1.index = getWeaponRopIndex(RandomUtil.randomDouble((double)0.0F, (double)100.0F));
         ptRandomOptionItem2.index = getWeaponRopIndex(RandomUtil.randomDouble((double)0.0F, (double)100.0F));
         ptRandomOptionItem.index = getEpicRopIndex(RandomUtil.randomDouble((double)0.0F, (double)100.0F), minlevel);
         ptRandomOptionItem.unique = true;
      }

      if (rarity == 2) {
         roption.add(ptRandomOptionItem2);
      } else if (rarity == 3) {
         roption.add(ptRandomOptionItem1);
         roption.add(ptRandomOptionItem2);
      } else if (rarity >= 4) {
         roption.add(ptRandomOptionItem);
         roption.add(ptRandomOptionItem1);
         roption.add(ptRandomOptionItem2);
      }

      return roption;
   }

   public static List<PT_RANDOMOPTION_ITEM> getrnop(int rarity, int equipType, List<PT_RANDOMOPTION_ITEM> roption, int minlevel) {
      List<PT_RANDOMOPTION_ITEM> rnoption = new ArrayList();

      for(PT_RANDOMOPTION_ITEM ptRandomOptionItem : roption) {
         if (ptRandomOptionItem.index > 4000) {
            PT_RANDOMOPTION_ITEM ptRandomOptionItem2 = new PT_RANDOMOPTION_ITEM();
            if (equipType != 11 && equipType != 13 && equipType != 14 && equipType != 15 && equipType != 16 && equipType != 17) {
               ptRandomOptionItem2.index = getRingEpicRopIndex(RandomUtil.randomDouble((double)0.0F, (double)100.0F));
            } else {
               ptRandomOptionItem2.index = getEpicRopIndex(RandomUtil.randomDouble((double)0.0F, (double)100.0F), minlevel);
            }

            ptRandomOptionItem2.unique = true;
            rnoption.add(ptRandomOptionItem2);
         } else if (ptRandomOptionItem.locked != null && ptRandomOptionItem.locked) {
            rnoption.add(ptRandomOptionItem);
         } else {
            PT_RANDOMOPTION_ITEM ptRandomOptionItem1 = new PT_RANDOMOPTION_ITEM();
            if (equipType != 11 && equipType != 13 && equipType != 14 && equipType != 15 && equipType != 16 && equipType != 17) {
               ptRandomOptionItem1.index = getRingRopIndex(RandomUtil.randomDouble((double)0.0F, (double)100.0F));
            } else {
               ptRandomOptionItem1.index = getWeaponRopIndex(RandomUtil.randomDouble((double)0.0F, (double)100.0F));
            }

            rnoption.add(ptRandomOptionItem1);
         }
      }

      return rnoption;
   }

   public static int getWeaponRopIndex(double random) {
      int index = 0;
      if (random < (double)4.5F) {
         index = 9;
      } else if ((double)4.5F <= random && random < (double)9.0F) {
         index = 10;
      } else if ((double)9.0F <= random && random < (double)15.5F) {
         index = 0;
      } else if ((double)15.5F <= random && random < (double)22.0F) {
         index = 1;
      } else if ((double)22.0F <= random && random < (double)29.0F) {
         index = 2;
      } else if ((double)29.0F <= random && random < (double)36.0F) {
         index = 3;
      } else if ((double)36.0F <= random && random < (double)43.0F) {
         index = 4;
      } else if ((double)43.0F <= random && random < (double)50.0F) {
         index = 5;
      } else if ((double)50.0F <= random && random < (double)57.0F) {
         index = 6;
      } else if ((double)57.0F <= random && random < (double)64.0F) {
         index = 11;
      } else if ((double)64.0F <= random && random < (double)71.0F) {
         index = 12;
      } else if ((double)71.0F <= random && random < (double)78.0F) {
         index = 13;
      } else if ((double)78.0F <= random && random < (double)85.0F) {
         index = 14;
      } else if ((double)85.0F <= random && random < (double)92.5F) {
         index = 15;
      } else if ((double)92.5F <= random && random < (double)100.0F) {
         index = 16;
      }

      return index;
   }

   public static int getRingRopIndex(double random) {
      int index = 0;
      if (random < (double)2.5F) {
         index = 17;
      } else if ((double)2.5F <= random && random < (double)5.0F) {
         index = 19;
      } else if ((double)5.0F <= random && random < (double)7.5F) {
         index = 21;
      } else if ((double)7.5F <= random && random < (double)10.0F) {
         index = 23;
      } else if ((double)10.0F <= random && random < (double)16.0F) {
         index = 0;
      } else if ((double)16.0F <= random && random < (double)22.0F) {
         index = 1;
      } else if ((double)22.0F <= random && random < (double)27.0F) {
         index = 2;
      } else if ((double)27.0F <= random && random < (double)32.0F) {
         index = 3;
      } else if ((double)32.0F <= random && random < (double)37.0F) {
         index = 4;
      } else if ((double)37.0F <= random && random < (double)42.0F) {
         index = 5;
      } else if ((double)42.0F <= random && random < (double)47.0F) {
         index = 6;
      } else if ((double)47.0F <= random && random < (double)52.0F) {
         index = 11;
      } else if ((double)52.0F <= random && random < (double)57.0F) {
         index = 12;
      } else if ((double)57.0F <= random && random < (double)62.0F) {
         index = 13;
      } else if ((double)62.0F <= random && random < (double)67.0F) {
         index = 14;
      } else if ((double)67.0F <= random && random < (double)72.5F) {
         index = 18;
      } else if ((double)72.5F <= random && random < (double)78.0F) {
         index = 20;
      } else if ((double)78.0F <= random && random < (double)83.5F) {
         index = 22;
      } else if ((double)83.5F <= random && random < (double)89.0F) {
         index = 24;
      } else if ((double)89.0F <= random && random < (double)95.0F) {
         index = 15;
      } else if ((double)95.0F <= random && random < (double)100.0F) {
         index = 16;
      }

      return index;
   }

   public static int getRingEpicRopIndex(double random) {
      int index = 0;
      if (random < (double)4.0F) {
         index = 4018;
      } else if ((double)4.0F <= random && random < (double)8.0F) {
         index = 4020;
      } else if ((double)8.0F <= random && random < (double)12.0F) {
         index = 4022;
      } else if ((double)12.0F <= random && random < (double)16.0F) {
         index = 4024;
      } else if ((double)16.0F <= random && random < (double)37.0F) {
         index = 4001;
      } else if ((double)37.0F <= random && random < (double)58.0F) {
         index = 4002;
      } else if ((double)58.0F <= random && random < (double)79.0F) {
         index = 4003;
      } else if ((double)79.0F <= random && random < (double)100.0F) {
         index = 4004;
      }

      return index;
   }

   public static int getEpicRopIndex(double random, int minlevel) {
      int index = 0;
      if (minlevel < 55) {
         if (random < (double)5.0F) {
            index = 4001;
         } else if ((double)5.0F <= random && random < (double)10.0F) {
            index = 4002;
         } else if ((double)10.0F <= random && random < (double)22.5F) {
            index = 4003;
         } else if ((double)22.5F <= random && random < (double)55.0F) {
            index = 4004;
         } else if ((double)55.0F <= random && random < (double)77.5F) {
            index = 4010;
         } else {
            index = 4011;
         }
      } else if (random < (double)5.0F) {
         index = 4001;
      } else if ((double)5.0F <= random && random < (double)10.0F) {
         index = 4002;
      } else if ((double)10.0F <= random && random < (double)17.5F) {
         index = 4018;
      } else if ((double)17.5F <= random && random < (double)25.0F) {
         index = 4020;
      } else if ((double)25.0F <= random && random < (double)32.5F) {
         index = 4022;
      } else if ((double)32.5F <= random && random < (double)40.0F) {
         index = 4024;
      } else if ((double)40.0F <= random && random < (double)60.0F) {
         index = 4001;
      } else if ((double)60.0F <= random && random < (double)80.0F) {
         index = 4002;
      } else if ((double)80.0F <= random && random < (double)90.0F) {
         index = 4008;
      } else {
         index = 4009;
      }

      return index;
   }

   public static int getEpicWeaponRopIndex(double random, int minlevel) {
      int index = 0;
      if (minlevel < 55) {
         if (random < (double)4.0F) {
            index = 4000;
         } else if ((double)4.0F <= random && random < (double)8.0F) {
            index = 4018;
         } else if ((double)8.0F <= random && random < (double)12.0F) {
            index = 4020;
         } else if ((double)12.0F <= random && random < (double)16.0F) {
            index = 4022;
         } else if ((double)16.0F <= random && random < (double)20.0F) {
            index = 4024;
         } else if ((double)20.0F <= random && random < (double)40.0F) {
            index = 4001;
         } else if ((double)40.0F <= random && random < (double)60.0F) {
            index = 4002;
         } else if ((double)60.0F <= random && random < (double)80.0F) {
            index = 4008;
         } else {
            index = 4009;
         }
      } else if (random < (double)5.0F) {
         index = 4001;
      } else if ((double)5.0F <= random && random < (double)10.0F) {
         index = 4002;
      } else if ((double)10.0F <= random && random < (double)17.5F) {
         index = 4018;
      } else if ((double)17.5F <= random && random < (double)25.0F) {
         index = 4020;
      } else if ((double)25.0F <= random && random < (double)32.5F) {
         index = 4022;
      } else if ((double)32.5F <= random && random < (double)40.0F) {
         index = 4024;
      } else if ((double)40.0F <= random && random < (double)60.0F) {
         index = 4001;
      } else if ((double)60.0F <= random && random < (double)80.0F) {
         index = 4002;
      } else if ((double)80.0F <= random && random < (double)90.0F) {
         index = 4008;
      } else {
         index = 4009;
      }

      return index;
   }

   public static void initEquipData(List<Equip> list) {
      index2Score = new HashMap();
      index2Equip = new HashMap();

      for(Equip equip : list) {
         index2Score.put(equip.getIndex(), equip.getScore());
         index2Equip.put(equip.getIndex(), equip);
         ItemDataPool.index2TbMap.put(equip.getIndex(), 0);
      }

   }

   public static PT_EQUIP changeEquip(PT_EQUIPPED equipped) {
      PT_EQUIP roleEquip = new PT_EQUIP();
      roleEquip.score = equipped.score;
      roleEquip.equiptype = equipped.equiptype;
      roleEquip.minlevel = equipped.minlevel;
      roleEquip.index = equipped.index;
      roleEquip.guid = equipped.guid;
      roleEquip.quality = equipped.quality;
      roleEquip.endurance = equipped.endurance;
      roleEquip.slot = equipped.slot;
      if (equipped.upgrade != null) {
         roleEquip.upgrade = equipped.upgrade;
      }

      if (equipped.upgradepoint != null) {
         roleEquip.upgradepoint = equipped.upgradepoint;
      }

      if (equipped.rappearance != null) {
         roleEquip.rappearance = equipped.rappearance;
      }

      if (equipped.roption != null) {
         roleEquip.roption = equipped.roption;
      }

      if (equipped.rnoption != null) {
         roleEquip.roption = equipped.roption;
      }

      if (equipped.card != null) {
         roleEquip.card = equipped.card;
      }

      if (equipped.emblem != null) {
         roleEquip.emblem = equipped.emblem;
      }

      return roleEquip;
   }

   public static PT_EQUIPPED changeEquipped(PT_EQUIP equip) {
      PT_EQUIPPED equipped = new PT_EQUIPPED();
      equipped.score = equip.score;
      equipped.equiptype = equip.equiptype;
      equipped.minlevel = equip.minlevel;
      equipped.guid = equip.guid;
      equipped.index = equip.index;
      equipped.quality = equip.quality;
      equipped.endurance = equip.endurance;
      equipped.slot = equip.slot;
      if (equip.upgrade != null) {
         equipped.upgrade = equip.upgrade;
      }

      if (equip.upgradepoint != null) {
         equipped.upgradepoint = equip.upgradepoint;
      }

      if (equip.rappearance != null) {
         equipped.rappearance = equip.rappearance;
      }

      if (equip.roption != null) {
         equipped.roption = equip.roption;
      }

      if (equip.rnoption != null) {
         equipped.roption = equip.roption;
      }

      if (equip.card != null) {
         equipped.card = equip.card;
      }

      if (equip.emblem != null) {
         equipped.emblem = equip.emblem;
      }

      return equipped;
   }

   public static PT_EQUIP createEquip(int index) {
      Equip equip = (Equip)index2Equip.get(index);
      PT_EQUIP roleEquip = new PT_EQUIP();
      roleEquip.index = index;
      roleEquip.guid = IdGenerator.getNextId();
      Random rnd = new Random();
      int quality = rnd.nextInt(20) + 60;
      roleEquip.score = (Integer)index2Score.get(index) * quality / 100;
      roleEquip.equiptype = equip.getEquiptype();
      roleEquip.minlevel = equip.getMinlevel();
      roleEquip.quality = quality;
      roleEquip.endurance = 30;
      roleEquip.upgrade = 0;
      roleEquip.upgradepoint = 0;
      if (equip.getMinlevel() >= 30 && equip.getRarity() >= 3) {
         roleEquip.rappearance = true;
      }

      if (equip.getRarity() >= 3) {
         roleEquip.emblem = new ArrayList();
      }

      roleEquip.card = new ArrayList();
      return roleEquip;
   }

   public static PT_EQUIP createTitle(int index) {
      Equip var10000 = (Equip)index2Equip.get(index);
      PT_EQUIP roleEquip = new PT_EQUIP();
      roleEquip.index = index;
      roleEquip.guid = IdGenerator.getNextId();
      Random rnd = new Random();
      int quality = rnd.nextInt(20) + 60;
      roleEquip.quality = quality;
      roleEquip.score = (Integer)index2Score.get(index) * quality / 100;
      return roleEquip;
   }

   public static PT_AVATAR_ITEM createAvatar(int index) {
      PT_AVATAR_ITEM pt_avatar_item = new PT_AVATAR_ITEM();
      pt_avatar_item.index = index;
      pt_avatar_item.guid = IdGenerator.getNextId();
      pt_avatar_item.score = (Integer)index2Score.get(index);
      return pt_avatar_item;
   }

   public static PT_EQUIPPED createEquipped(int index, int slot) {
      Equip equip = (Equip)index2Equip.get(index);
      PT_EQUIPPED roleEquip = new PT_EQUIPPED();
      roleEquip.index = index;
      roleEquip.guid = IdGenerator.getNextId();
      roleEquip.quality = 100;
      roleEquip.endurance = 30;
      roleEquip.score = equip.getScore();
      roleEquip.equiptype = equip.getEquiptype();
      roleEquip.minlevel = equip.getMinlevel();
      roleEquip.slot = slot;
      roleEquip.upgrade = 0;
      roleEquip.upgradepoint = 0;
      if (equip.getMinlevel() >= 30 && equip.getRarity() >= 3) {
         roleEquip.rappearance = true;
      }

      if (equip.getRarity() >= 3) {
         roleEquip.emblem = new ArrayList();
      }

      roleEquip.card = new ArrayList();
      return roleEquip;
   }

   public static void initUpgradeConfig() {
      String[] equTypeWeightArr = "weapon|1 coat|0.75 pants|0.75 shoulder|0.75 waist|0.75 shoes|0.75 amulet|0.75 wrist|0.75 ring|0.75".split(" ");

      for(String equTypeWeight : equTypeWeightArr) {
         String[] equTypeWeightArr1 = equTypeWeight.split("\\|");
         equWeightMap.put(equTypeWeightArr1[0], Double.parseDouble(equTypeWeightArr1[1]));
      }

      String[] accValueStrArr = "0|0|0|0|0|0|0 1|1|3|4|7|13|13 2|3|7|9|16|31|31 3|5|12|16|28|55|55 4|8|18|26|44|87|87 5|12|27|39|66|130|130 6|18|39|56|95|188|188 7|26|54|79|134|265|265 8|36|75|110|186|368|368 9|50|103|151|255|506|506 10|68|141|207|349|696|696 11|107|220|323|544|1086|1086 12|168|343|506|852|1700|1700 13|302|616|911|1531|3056|3056 14|585|1191|1765|2962|5915|5915 15|1259|2555|3793|6359|12699|12699 16|4068|8250|12255|20538|41022|41022 17|8227|16674|24778|41517|82928|82928 18|13552|27458|40813|68374|136579|136579 19|22003|44568|66256|110988|221705|221705 20|35936|72776|108205|181244|362054|362054 21|1727627|3518526|5201343|8746145|17441018|17441018 22|1814008|3694452|5461410|9183452|18313069|18313069 23|1904708|3879175|5734481|9642625|19228722|19228722 24|1999943|4073134|6021205|10124756|20190158|20190158 25|2099940|4276791|6322265|10630994|21199666|21199666 26|2204937|4490631|6638378|11162544|22259649|22259649 27|2315184|4715163|6970297|11720671|23372631|23372631 28|2430943|4950921|7318812|12306705|24541263|24541263 29|2552490|5198467|7684753|12922040|25768326|25768326 30|2680115|5458390|8068991|13568142|27056742|27056742".split(" ");
      int index = 0;

      for(String accValue : accValueStrArr) {
         List<Double> list = new ArrayList();
         String[] strArr = accValue.split("\\|");

         for(String str : strArr) {
            list.add(Double.parseDouble(str));
         }

         accValueTable.put(index, list);
         ++index;
      }

      String[] baseValueStrArr = "upgrade|230 amplify|55000 reforge|2500 transmission|230".split(" ");
      index = 0;

      for(String baseValue : baseValueStrArr) {
         List<String> list = new ArrayList();
         String[] strArr = baseValue.split("\\|");

         for(String str : strArr) {
            list.add(str);
         }

         baseValueTable.put(index, list);
         ++index;
      }

      String[] upgradeSuccRateStrArr = "0|100|0|0|0|0|0|0|0 1|100|0|0|0|0|0|0|0 2|100|0|0|0|0|0|0|0 3|100|0|0|0|0|0|0|0 4|80|0|0|0|0|0|0|0 5|70|0|0|0|0|0|0|0 6|60|0|0|0|0|0|0|0 7|50|0|0|0|0|0|0|0 8|40|0|0|0|0|0|0|0 9|30|0|0|0|0|0|0|0 10|15|0|0|0|0|35|0|35 11|10|0|0|0|0|20|0|20 12|7|0|0|0|0|14|0|14 13|5|0|0|0|0|10|0|10 14|3|0|0|0|0|6|0|6 15|1|0|0|0|0|2|0|2 16|1|0|0|0|0|2|0|2 17|1|0|0|0|0|2|0|2 18|1|0|0|0|0|2|0|2 19|1|0|0|0|0|2|0|2 20|1|0|0|0|0|2|0|2".split(" ");
      index = 0;

      for(String upgradeSuccRate : upgradeSuccRateStrArr) {
         List<Double> list = new ArrayList();
         String[] strArr = upgradeSuccRate.split("\\|");

         for(String str : strArr) {
            list.add(Double.parseDouble(str));
         }

         upgradeSuccRateTable.put(index, list);
         ++index;
      }

      String[] materialInfoStrArr = "upgrade|2013100894|5000 amplify|2013100893|35000 reforge|2013100892|20000 inherit|2013100894|5000 amplify_assign|2013100708|50000 amplify_change|2013100708|50000 transmission|2013100894|5000 advanced_upgrade|2013106035|500000 advanced_transmission|2013106035|500000 advanced_inherit|2013106035|500000".split(" ");

      for(String materialInfo : materialInfoStrArr) {
         String[] strArr = materialInfo.split("\\|");
         materialInfoMap.put(strArr[0], Double.parseDouble(strArr[2]));
      }

      index = 0;
      String[] upgradePointMaxStrArr = "0|1|2|3|4|7 1|2|3|5|7|14 2|2|3|5|7|14 3|3|5|7|11|21 4|5|10|15|25|50 5|8|15|23|38|75 6|13|25|37|62|123 7|18|35|52|87|173 8|26|52|77|128|256 9|36|72|107|178|356 10|86|172|258|429|858 11|139|278|417|695|1389 12|313|626|939|1565|3130 13|670|1340|2009|3348|6696 14|1612|3223|4835|8058|16115 15|6807|13613|20419|34031|68061 16|10078|20155|30232|50386|100771 17|12903|25805|38707|64511|129022 18|20477|40953|61429|102382|204764 19|33759|67518|101277|168795|337589".split(" ");

      for(String upgradePointMax : upgradePointMaxStrArr) {
         List<Double> list = new ArrayList();
         String[] strArr = upgradePointMax.split("\\|");

         for(String str : strArr) {
            list.add(Double.parseDouble(str));
         }

         upgradePointMaxMap.put(index, list);
         ++index;
      }

   }

   private static int getRandom(List<Integer> Jade1) {
      Random random = new Random();
      int index = random.nextInt(Jade1.size());
      if (index == 0) {
         index = random.nextInt(Jade1.size());
      }

      return (Integer)Jade1.get(index);
   }

   public static double getEquipmentTypeWeight(int equType) {
      String equStr = (String)enumMap.get(equType);
      return (Double)equWeightMap.get(equStr);
   }

   public static double getReinforceUpgradeValue(int equType, int rarity, int reinforceLevel, int equLevel) {
      double value = (double)0.0F;
      double a1 = (double)0.0F;
      double a2 = (double)1.5F;
      double a3 = (double)0.0F;
      double a4 = (double)0.0F;
      List<Double> splits = null;
      if (reinforceLevel < 1) {
         return value;
      } else {
         a1 = getEquipmentTypeWeight(equType);
         splits = (List)accValueTable.get(reinforceLevel);
         a3 = (Double)splits.get(rarity + 1);
         List<String> splits2 = (List)baseValueTable.get(0);
         a4 = Double.parseDouble((String)splits2.get(1));
         value = a1 * a2 * a3 * (double)equLevel * a4;
         return value;
      }
   }

   public static double getReinforceSuccessRate(int reinforceLevel) {
      List<Double> list = (List)upgradeSuccRateTable.get(reinforceLevel);
      double rate = (Double)list.get(1);
      return rate;
   }

   public static double getReinforceCost(int reinforceLevel, int equType, int equLevel, int rarity) {
      double reinforceUpgradeValue = getReinforceUpgradeValue(equType, rarity, reinforceLevel, equLevel);
      double reinforceUpgradeValueNext = getReinforceUpgradeValue(equType, rarity, reinforceLevel + 1, equLevel);
      double reinforceSuccessRate = getReinforceSuccessRate(reinforceLevel);
      double cost = (double)0.0F;
      cost = reinforceSuccessRate * (reinforceUpgradeValueNext - reinforceUpgradeValue) * 0.01;
      return cost;
   }

   public static double getUpgradeMax(int reinforceLevel, int rarity) {
      List<Double> list = (List)upgradePointMaxMap.get(reinforceLevel);
      double max = (Double)list.get(rarity + 1);
      return max;
   }

   public static double getPointMaxLevelWeight(int equLevel) {
      Map<Integer, Double> map = new LinkedHashMap();
      map.put(1, (double)1.0F);
      map.put(5, 6.365251);
      map.put(10, 14.125375);
      map.put(15, 22.516711);
      map.put(20, 31.346171);
      map.put(25, 40.516415);
      map.put(30, 49.96771);
      map.put(35, 59.659313);
      map.put(40, 69.561508);
      map.put(45, 79.651579);
      map.put(50, 89.911554);
      map.put(55, 100.326831);
      double point = (Double)map.get(1);

      for(Map.Entry<Integer, Double> entry : map.entrySet()) {
         if ((Integer)entry.getKey() >= equLevel) {
            point = (Double)entry.getValue();
            break;
         }
      }

      return point;
   }

   public static int getUpgradePointMax(int reinforceLevel, int rarity, int equType, int equLevel) {
      double upgradeMax = getUpgradeMax(reinforceLevel, rarity);
      double pointMaxLevelWeight = getPointMaxLevelWeight(equLevel);
      double equTypeWeight = getEquipmentTypeWeight(equType);
      double value = pointMaxLevelWeight * upgradeMax * equTypeWeight;
      return (int)value;
   }

   public static double getAccumulateMaxPoint(int reinforceLevel, int rarity, int equType, int equLevel) {
      double accuMaxPoint = (double)0.0F;
      int index = 0;

      do {
         int point = getUpgradePointMax(index, rarity, equType, equLevel);
         accuMaxPoint += (double)point;
         ++index;
      } while(index <= reinforceLevel);

      return accuMaxPoint;
   }

   public static UpgradeInfo getUpgradeInfo(int reinforceLevel, int equType, int equLevel, int rarity) {
      UpgradeInfo upgradeInfo = new UpgradeInfo();
      double cost = (double)((int)getReinforceCost(reinforceLevel, equType, equLevel, rarity));
      upgradeInfo.needGold = (int)(cost * (double)0.5F);
      double materialInfo = (Double)materialInfoMap.get("upgrade");
      double materialRatio = (double)0.5F;
      upgradeInfo.needMaterial = (int)(cost * materialRatio / materialInfo);
      materialInfo = (Double)materialInfoMap.get("advanced_upgrade");
      materialRatio = (double)0.5F;
      upgradeInfo.needAdvMaterial = (int)(cost * materialRatio / materialInfo);
      upgradeInfo.pointMax = getUpgradePointMax(reinforceLevel, rarity, equType, equLevel);
      upgradeInfo.accuPointMax = (int)getAccumulateMaxPoint(reinforceLevel - 1, rarity, equType, equLevel);
      return upgradeInfo;
   }

   public static Equip getEquip(int index) {
      return (Equip)index2Equip.get(index);
   }

   static {
      enumMap.put(0, "hat avatar");
      enumMap.put(1, "hair avatar");
      enumMap.put(2, "face avatar");
      enumMap.put(3, "coat avatar");
      enumMap.put(4, "pants avatar");
      enumMap.put(5, "shoes avatar");
      enumMap.put(6, "breast avatar");
      enumMap.put(7, "waist avatar");
      enumMap.put(8, "skin avatar");
      enumMap.put(9, "aurora avatar");
      enumMap.put(10, "weapon avatar");
      enumMap.put(11, "weapon");
      enumMap.put(12, "title name");
      enumMap.put(13, "coat");
      enumMap.put(14, "shoulder");
      enumMap.put(15, "pants");
      enumMap.put(16, "shoes");
      enumMap.put(17, "waist");
      enumMap.put(18, "amulet");
      enumMap.put(19, "wrist");
      enumMap.put(20, "ring");
      enumMap.put(21, "support");
      enumMap.put(22, "magic stone");
      enumMap.put(23, "skill weapon");
      enumMap.put(24, "creature");
   }
}
