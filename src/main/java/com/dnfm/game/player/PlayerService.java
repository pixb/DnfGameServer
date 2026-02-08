package com.dnfm.game.player;

import com.dnfm.common.spring.SpringUtils;
import com.dnfm.game.player.model.AccountProfile;
import com.dnfm.game.player.model.PlayerProfile;
import com.dnfm.game.role.model.Role;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {
   public static final String CACHE_ROLE_KEY_PREFIX = "role:";
   Logger logger = LoggerFactory.getLogger(this.getClass());
   @Autowired
   private Dao dao;
   @Autowired
   private JdbcTemplate jdbcTemplate;
   private final ConcurrentMap<Long, PlayerProfile> playerProfiles = new ConcurrentHashMap();
   private final ConcurrentMap<String, AccountProfile> accountProfiles = new ConcurrentHashMap();
   private final ConcurrentHashMap<String, Long> name2roleUid = new ConcurrentHashMap();
   private final ConcurrentHashMap<Integer, Long> roleId2roleUid = new ConcurrentHashMap();
   private final AtomicInteger randNameInc = new AtomicInteger();

   private long getUidByName(String name) {
      String sqlstr = "select uid from t_role where name=@name";
      Sql sql = Sqls.create(sqlstr);
      sql.setParam("name", name);
      sql.setCallback(new SqlCallback() {
         public Object invoke(Connection connection, ResultSet resultSet, Sql sql) throws SQLException {
            return resultSet.next() ? resultSet.getLong("uid") : 0;
         }
      });
      ((Dao)SpringUtils.getBean(Dao.class)).execute(sql);
      long uid = sql.getLong();
      return uid;
   }

   public void delProfile(String accountId, String name) {
      AccountProfile account = (AccountProfile)this.accountProfiles.get(accountId);
      if (account != null) {
         List<PlayerProfile> playerProfiles = account.getPlayers();
         account.setPlayers(new ArrayList());

         for(PlayerProfile playerProfile : playerProfiles) {
            if (!playerProfile.getName().equals(name)) {
               account.getPlayers().add(playerProfile);
            }
         }

      }
   }

   public void loadAllPlayerProfiles() {
      this.playerProfiles.clear();
      this.accountProfiles.clear();
      this.name2roleUid.clear();
      String sql = "select openid, roleId, uid, `name`, distName, exp, `level`, job, fatigue from t_role";
      List<PlayerProfile> profileList = this.jdbcTemplate.query(sql, new PlayerRowMapper());
      this.logger.error("角色数量=={}", profileList.size());
      profileList.forEach((p) -> {
         if (this.name2roleUid.containsKey(p.getName())) {
         }

         this.addPlayerProfile(p);
      });
   }

   public ConcurrentMap<Long, PlayerProfile> getAllPlayerProfiles() {
      return this.playerProfiles;
   }

   public void addPlayerProfile(PlayerProfile baseInfo) {
      this.playerProfiles.put(baseInfo.getUid(), baseInfo);
      String accountId = baseInfo.getOpenid();
      AccountProfile accountProfile = null;
      if (!this.accountProfiles.containsKey(baseInfo.getOpenid())) {
         accountProfile = new AccountProfile();
         accountProfile.setPlayers(new ArrayList());
         this.accountProfiles.putIfAbsent(accountId, accountProfile);
      } else {
         accountProfile = (AccountProfile)this.accountProfiles.get(baseInfo.getOpenid());
      }

      accountProfile.getPlayers().add(baseInfo);
      this.name2roleUid.put(baseInfo.getName(), baseInfo.getUid());
      this.roleId2roleUid.put(baseInfo.getRoleId(), baseInfo.getUid());
   }

   public List<PlayerProfile> getPlayersBy(String openid) {
      AccountProfile account = (AccountProfile)this.accountProfiles.get(openid);
      return (List<PlayerProfile>)(account == null ? new ArrayList() : account.getPlayers());
   }

   @Cacheable(
      value = {"player"},
      key = "#uid"
   )
   public Role getPlayerBy(long uid) {
      if (uid <= 0L) {
         return null;
      } else {
         Role role = (Role)this.dao.fetch(Role.class, Cnd.where("uid", "=", uid));
         if (role != null) {
            role.doAfterInit();
         }

         return role;
      }
   }

   @CachePut(
      value = {"player"},
      key = "#role.uid"
   )
   public Role saveToCache(Role role) {
      return role;
   }

   public long getUidBy(String name) {
      return (Long)this.name2roleUid.getOrDefault(name, 0L);
   }

   public List<Long> getUidsByIndistinct(String name) {
      List<Long> result = new ArrayList();

      for(Map.Entry<String, Long> entry : this.name2roleUid.entrySet()) {
         if (((String)entry.getKey()).indexOf(name) >= 0) {
            result.add(entry.getValue());
         }
      }

      return result;
   }

   public long getUidBy(int roleId) {
      return (Long)this.roleId2roleUid.getOrDefault(roleId, 0L);
   }

   public void updateRoleLevel(Role role) {
      PlayerProfile playerProfile = (PlayerProfile)this.playerProfiles.get(role.getUid());
      if (playerProfile != null) {
         playerProfile.setLevel(role.getLevel());
      }
   }

   public void updateRoleEnterTime(Role role) {
      PlayerProfile playerProfile = (PlayerProfile)this.playerProfiles.get(role.getUid());
      if (playerProfile != null) {
         ;
      }
   }

   public void updateRoleIcon(Role role) {
   }

   public void updateRoleName(String oldName, Role role) {
   }

   public PlayerProfile queryPlayerProfile(long playerId) {
      return (PlayerProfile)this.playerProfiles.get(playerId);
   }

   public boolean occupyName(String roleName, long uid) {
      return this.name2roleUid.putIfAbsent(roleName, uid) == null;
   }

   public ArrayList<Long> allUids() {
      return new ArrayList(this.name2roleUid.values());
   }

   public void updatePlayerProfile(Role role) {
   }

   private class PlayerRowMapper implements RowMapper<PlayerProfile> {
      private PlayerRowMapper() {
      }

      public PlayerProfile mapRow(ResultSet re, int arg1) throws SQLException {
         return new PlayerProfile(re.getString("openid"), re.getInt("roleId"), re.getLong("uid"), re.getString("name"), re.getString("distName"), re.getInt("exp"), re.getInt("level"), re.getInt("job"), re.getInt("fatigue"));
      }
   }
}
