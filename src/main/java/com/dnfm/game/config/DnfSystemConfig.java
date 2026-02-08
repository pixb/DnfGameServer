package com.dnfm.game.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DnfSystemConfig {
   private static Map<Integer, UpgradeScoreConfig> upgradeScoreConfig;
   private static Map<String, CharacterScoreConfig> characterScoreConfig;

   public static void initUpgradeScoreConfig(List<UpgradeScoreConfig> list) {
      upgradeScoreConfig = new HashMap();

      for(UpgradeScoreConfig config : list) {
         upgradeScoreConfig.put(config.getId(), config);
      }

   }

   public static UpgradeScoreConfig getUpgradeScoreConfig(int index) {
      return (UpgradeScoreConfig)upgradeScoreConfig.get(index);
   }

   public static void initCharacterScoreConfig(List<CharacterScoreConfig> list) {
      characterScoreConfig = new HashMap();

      for(CharacterScoreConfig config : list) {
         characterScoreConfig.put(config.getJobid() + config.getGroupname(), config);
      }

   }

   public static CharacterScoreConfig getCharacterScoreConfig(Integer jobID, String groupName) {
      return (CharacterScoreConfig)characterScoreConfig.get(jobID + groupName);
   }

   public static double getMagicSpell(int magicSpell) {
      switch (magicSpell) {
         case 1:
            return (double)1.0F;
         case 2:
            return 1.6;
         case 3:
            return 2.2;
         case 4:
            return 2.8;
         case 5:
            return (double)3.0F;
         case 6:
            return (double)3.5F;
         default:
            return (double)0.0F;
      }
   }
}
