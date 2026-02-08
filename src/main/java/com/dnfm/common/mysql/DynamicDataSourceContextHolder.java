package com.dnfm.common.mysql;

import java.util.ArrayList;
import java.util.List;

public class DynamicDataSourceContextHolder {
   private static final ThreadLocal<String> contextHolder = new ThreadLocal();
   public static List<String> dataSourceIds = new ArrayList();

   public static String getDataSourceType() {
      return (String)contextHolder.get();
   }

   public static void setDataSourceType(String dataSourceType) {
      contextHolder.set(dataSourceType);
   }

   public static void clearDataSourceType() {
      contextHolder.remove();
   }

   public static boolean containsDataSource(String dataSourceId) {
      return dataSourceIds.contains(dataSourceId);
   }
}
