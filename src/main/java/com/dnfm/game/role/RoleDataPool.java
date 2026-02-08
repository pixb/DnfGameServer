package com.dnfm.game.role;

import com.dnfm.game.config.PetExp;
import com.dnfm.game.config.RoleExp;
import java.util.HashMap;
import java.util.Map;

public class RoleDataPool {
   public static final int in = 0;
   public static Map<Integer, RoleExp> level2RoleExp = new HashMap();
   public static Map<Integer, PetExp> level2PetExp = new HashMap();
   public static Map<Integer, String> tonicUpgrade = new HashMap();

   static {
      tonicUpgrade.put(0, "2013102101-1-16000");
      tonicUpgrade.put(1, "2013102100-8-18000");
      tonicUpgrade.put(2, "2013102100-9-20250");
      tonicUpgrade.put(3, "2013102100-10-22782");
      tonicUpgrade.put(4, "2013102100-11-25629");
      tonicUpgrade.put(5, "2013102101-3-28833");
      tonicUpgrade.put(6, "2013102100-13-32437");
      tonicUpgrade.put(7, "2013102100-15-36492");
      tonicUpgrade.put(8, "2013102100-17-41053");
      tonicUpgrade.put(9, "2013102100-19-46185");
      tonicUpgrade.put(10, "2013102101-4-51958");
      tonicUpgrade.put(11, "2013102100-24-58452");
      tonicUpgrade.put(12, "2013102100-27-65759");
      tonicUpgrade.put(13, "2013102100-30-73979");
      tonicUpgrade.put(14, "2013102100-34-83226");
      tonicUpgrade.put(15, "2013102101-6-93629");
      tonicUpgrade.put(16, "2013102100-43-105333");
      tonicUpgrade.put(17, "2013102100-48-118499");
      tonicUpgrade.put(18, "2013102100-54-133311");
      tonicUpgrade.put(19, "2013102100-60-149975");
      tonicUpgrade.put(20, "2013102101-8-168722");
      tonicUpgrade.put(21, "2013102100-76-189812");
      tonicUpgrade.put(22, "2013102100-86-213539");
      tonicUpgrade.put(23, "2013102100-97-240231");
      tonicUpgrade.put(24, "2013102100-109-270260");
      tonicUpgrade.put(25, "2013102101-9-304042");
      tonicUpgrade.put(26, "2013102100-137-342047");
      tonicUpgrade.put(27, "2013102100-154-384803");
      tonicUpgrade.put(28, "2013102100-174-432904");
      tonicUpgrade.put(29, "2013102100-195-487016");
      tonicUpgrade.put(30, "2013102101-11-547893");
      tonicUpgrade.put(31, "2013102100-247-616380");
      tonicUpgrade.put(32, "2013102100-278-693427");
      tonicUpgrade.put(33, "2013102100-313-780106");
      tonicUpgrade.put(34, "2013102100-352-877619");
      tonicUpgrade.put(35, "2013102101-13-987321");
      tonicUpgrade.put(36, "2013102100-445-1110736");
      tonicUpgrade.put(37, "2013102100-500-1249578");
      tonicUpgrade.put(38, "2013102100-563-1405776");
      tonicUpgrade.put(39, "2013102100-633-1581497");
      tonicUpgrade.put(40, "2013102101-14-1779185");
      tonicUpgrade.put(41, "2013102100-801-2001583");
      tonicUpgrade.put(42, "2013102100-901-2251780");
      tonicUpgrade.put(43, "2013102100-1014-2533253");
      tonicUpgrade.put(44, "2013102100-1140-2849909");
      tonicUpgrade.put(45, "2013102101-16-3206148");
      tonicUpgrade.put(46, "2013102100-1443-3606916");
      tonicUpgrade.put(47, "2013102100-1624-4057781");
      tonicUpgrade.put(48, "2013102100-1827-4565003");
      tonicUpgrade.put(49, "2013102100-2055-5135629");
   }
}
