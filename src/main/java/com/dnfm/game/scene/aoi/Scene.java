package com.dnfm.game.scene.aoi;

import com.dnfm.common.spring.SpringUtils;
import com.dnfm.game.fight.service.BroadcastService;
import com.dnfm.game.role.model.Role;
import com.dnfm.game.role.service.RoleService;
import com.dnfm.logs.LoggerFunction;
import com.dnfm.mina.cache.DataCache;
import com.dnfm.mina.message.MessagePusher;
import com.dnfm.mina.protobuf.Message;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.slf4j.Logger;

public class Scene extends AOI {
   private static final Logger logger;
   private static final int DISTANCE = 900;
   private final BroadcastService service = SpringUtils.getBroadcastService();
   private final RoleService roleService = SpringUtils.getRoleService();
   private final Node _head = new Node(Long.MIN_VALUE, 0, 0);
   private final Node _tail = new Node(Long.MAX_VALUE, 0, 0);
   private final Map<Long, Node> allNodes = new ConcurrentHashMap();

   public Scene() {
      this._head.xNext = this._tail;
      this._head.yNext = this._tail;
      this._tail.xPrev = this._head;
      this._tail.yPrev = this._head;
   }

   public static void main(String[] args) {
      Role r = null;
      List<Role> list = new ArrayList();
      System.out.println(list.size());
      list.add(r);
      System.out.println(list.size());
   }

   private void addActor(Node node) {
      this.allNodes.put(node.id, node);
      int i = 0;

      for(Node cur = this._head.xNext; cur != null; ++i) {
         if (i >= 50) {
            logger.error("x 没有人，别动了快歇歇吧 node: [{}]", node);
            break;
         }

         if (cur.x > node.x || cur == this._tail) {
            node.xNext = cur;
            node.xPrev = cur.xPrev;
            cur.xPrev.xNext = node;
            cur.xPrev = node;
            break;
         }

         cur = cur.xNext;
      }

      i = 0;

      for(Node var5 = this._head.yNext; var5 != null; ++i) {
         if (i >= 50) {
            logger.error("y 没有人，别动了快歇歇吧 node: [{}]", node);
            break;
         }

         if (var5.y > node.y || var5 == this._tail) {
            node.yNext = var5;
            node.yPrev = var5.yPrev;
            var5.yPrev.yNext = node;
            var5.yPrev = node;
            break;
         }

         var5 = var5.yNext;
      }

   }

   private Node getNodeByRole(Role role) {
      Node node = (Node)this.allNodes.get(role.getUid());
      if (node == null) {
         node = new Node(role.getUid(), role.getPos().getX(), role.getPos().getY());
         this.addActor(node);
      }

      return node;
   }

   private void leave(Node node) {
      if (node.xPrev != null) {
         node.xPrev.xNext = node.xNext;
      }

      if (node.xNext != null) {
         node.xNext.xPrev = node.xPrev;
      }

      if (node.yPrev != null) {
         node.yPrev.yNext = node.yNext;
      }

      if (node.yNext != null) {
         node.yNext.yPrev = node.yPrev;
      }

      node.xPrev = null;
      node.xNext = null;
      node.yPrev = null;
      node.yNext = null;
   }

   private Set<Long> getVisualObjects(Node node) {
      Set<Long> visuals = new HashSet();
      if (node == null) {
         logger.error("node is null:[{}]", (new RuntimeException()).getMessage());
         return visuals;
      } else {
         int i = 0;

         for(Node cur = node.xNext; cur != this._tail; ++i) {
            if (i >= 50) {
               logger.error("x 没有人，别动了快歇歇吧");
               break;
            }

            if (cur == null || cur.x - node.x > 900) {
               break;
            }

            int inteval = node.y - cur.y;
            if (inteval >= -900 && inteval <= 900) {
               visuals.add(cur.id);
            }

            cur = cur.xNext;
         }

         i = 0;

         for(Node var7 = node.xPrev; var7 != this._head; ++i) {
            if (i >= 50) {
               logger.error("y 没有人，别动了快歇歇吧");
               break;
            }

            if (var7 == null || node.x - var7.x > 900) {
               break;
            }

            int inteval = node.y - var7.y;
            if (inteval >= -900 && inteval <= 900) {
               visuals.add(var7.id);
            }

            var7 = var7.xPrev;
         }

         return visuals;
      }
   }

   public void enter(Role role) {
      Node node = (Node)this.allNodes.get(role.getUid());
      if (node == null) {
         this.getNodeByRole(role);
      }
   }

   public void leave(Role role) {
      long uid = role.getUid();
      Node node = this.getNodeByRole(role);
      this.leave(node);
      this.allNodes.remove(uid);
   }

   public void move(Role role) {
      Node node = this.getNodeByRole(role);
      this.getVisualObjects(node);
      this.leave(node);
      node.x = role.getPos().getX();
      node.y = role.getPos().getY();
      this.addActor(node);
   }

   public List<Role> getNearbyRoles(Role role) {
      Node node = this.getNodeByRole(role);
      Set<Long> uids = this.getVisualObjects(node);
      List<Role> resList = new ArrayList();

      for(Long uid : uids) {
         Role r = (Role)DataCache.ONLINE_ROLES.get(uid);
         if (r == null) {
            logger.error("角色不存在:{}", uid);
         } else {
            resList.add(r);
         }
      }

      return resList;
   }

   public void sendMessages(Role role, Message... messages) {
      Node node = this.getNodeByRole(role);
      Set<Long> ids = this.getVisualObjects(node);
      this.sendMessages(role, ids, messages);
   }

   private void sendMessages(Role role, Set<Long> ids, Message... messages) {
      MessagePusher.pushMessages(role, messages);
      if (ids.size() > 0) {
         for(long targetId : ids) {
            Role target = this.roleService.getOnlinePlayer(targetId);
            if (target != null && this.service.inSameScene(role, target)) {
               MessagePusher.pushMessages(target, messages);
            }
         }

      }
   }

   private void sendMove(Role selfRole, Set<Long> moves) {
      Message movePacket = this.service.getMovePacket(selfRole);
      MessagePusher.pushMessage(selfRole, movePacket);
      if (moves.size() > 0) {
         for(long targetId : moves) {
            Role target = this.roleService.getOnlinePlayer(targetId);
            if (target != null && this.service.inSameScene(selfRole, target)) {
            }
         }

      }
   }

   private void sendAppear(Role selfRole, Set<Long> enters) {
   }

   private void sendDisappear(Role selfRole, Set<Long> leaves) {
   }

   static {
      logger = LoggerFunction.SCENE.getLogger();
   }
}
