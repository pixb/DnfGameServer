package com.dnfm.game.identity;

import com.dnfm.common.db.Db4CommonService;
import com.dnfm.common.spring.SpringUtils;
import com.dnfm.mina.ServerConfig;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IdentityService {
   @Autowired
   private Dao dao;
   private final Map<Integer, IdentityEnt> map = new HashMap();
   private final AtomicInteger defaultFactory = new AtomicInteger(1);

   public void init() {
      for(IdentityType kind : IdentityType.values()) {
         int type1 = kind.getType();
         if (kind.isSave()) {
            IdentityEnt ent = (IdentityEnt)this.dao.fetch(IdentityEnt.class, Cnd.where("type", "=", type1));
            if (ent == null) {
               ent = IdentityEnt.valueOf(type1, this.createInitSeed());
               this.dao.insertOrUpdate(ent);
            }

            this.map.put(ent.getType(), ent);
         }
      }

   }

   private int createInitSeed() {
      ServerConfig config = (ServerConfig)SpringUtils.getBean(ServerConfig.class);
      return config.getServerId() << 25;
   }

   public int getNextId(IdentityType idType) {
      if (!idType.isSave()) {
         return this.defaultFactory.getAndIncrement();
      } else {
         int type = idType.getType();
         IdentityEnt ent = (IdentityEnt)this.map.get(type);
         int nextId = ent.getNextId();
         Db4CommonService.getInstance().add2Queue(ent);
         return nextId;
      }
   }
}
