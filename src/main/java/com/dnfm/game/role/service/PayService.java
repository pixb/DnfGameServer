package com.dnfm.game.role.service;

import com.alibaba.fastjson.JSONObject;
import com.dnfm.game.ServerService;
import com.dnfm.game.role.model.Role;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.nutz.dao.Dao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

@Service
@PropertySource({"classpath:game.properties"})
public class PayService {
   Logger logger = LoggerFactory.getLogger(PayService.class);
   @Autowired
   ServerService serverService;
   @Autowired
   Dao dao;
   @Autowired
   AccountService accountService;
   private final int rate = 1;
   @Value("${pay.notify.url}")
   private String notifyUrl;
   private final List<Integer> appoints = new ArrayList(Arrays.asList(31, 32, 33, 34, 35, 41, 42, 43, 44, 45, 51, 52, 53, 54, 55));

   private String crateShortUrl(String longUrl) {
      BufferedReader reader = null;

      try {
         longUrl = URLEncoder.encode(longUrl, "GBK");
         URL url = new URL("http://api.t.sina.com.cn/short_url/shorten.json?source=2849184197&url_long=" + longUrl);
         InputStream iStream = url.openStream();
         reader = new BufferedReader(new InputStreamReader(iStream));
         String jsonStr = reader.readLine();
         JSONObject jsonObj = JSONObject.parseArray(jsonStr).getJSONObject(0);
         String var7 = jsonObj.getString("url_short");
         return var7;
      } catch (Exception var11) {
         return longUrl;
      } finally {
         IOUtils.closeQuietly(reader);
      }
   }

   public void loadCommonSet(Map<String, String> commonSetMap) {
   }

   public void handleAccumulativeReward(Role role, int money, int addGold) {
      if (role != null) {
         this.chargeActivity(role, money, addGold);
      }
   }

   private void chargeActivity(Role role, int money, int addGold) {
   }

   public void addReward(int rmb, Role role) {
   }
}
