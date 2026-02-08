package com.dnfm.game.scene;

import com.dnfm.common.thread.NamedThreadFactory;
import com.dnfm.game.config.GameMap;
import com.dnfm.game.role.model.Role;
import com.dnfm.game.scene.aoi.AOI;
import com.dnfm.game.scene.aoi.Scene;
import com.dnfm.logs.LoggerFunction;
import com.dnfm.mina.protobuf.Message;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import org.slf4j.Logger;

public enum SceneManager {
   INSTANCE;

   private static final Logger logger = LoggerFunction.SCENE.getLogger();
   private static final int CORE_SIZE = 8;
   private static final List<TaskWorker> workerPool = new ArrayList();
   private static final Map<Integer, AOI> scenes = new HashMap();
   private final AtomicInteger atomicInteger = new AtomicInteger();

   public void initiate(List<GameMap> maps) {
      maps.forEach((gameMap) -> {
         AOI scene = new Scene();
         scene.setId(this.atomicInteger.getAndIncrement());
         scenes.put(gameMap.getId(), scene);
      });
   }

   private void addScene(int mapId) {
      AOI scene = new Scene();
      scene.setId(this.atomicInteger.getAndIncrement());
      scenes.put(mapId, scene);
   }

   private AOI getScene(int mapId) {
      AOI scene = (AOI)scenes.get(mapId);
      if (scene == null) {
         this.addScene(mapId);
         return (AOI)scenes.get(mapId);
      } else {
         return scene;
      }
   }

   public void enterMap(int mapId, Role role) {
      AOI scene = this.getScene(mapId);
      this.acceptTask(scene.getId(), () -> scene.enter(role));
   }

   public void leaveMap(int mapId, Role role) {
      AOI scene = this.getScene(mapId);
      this.acceptTask(scene.getId(), () -> scene.leave(role));
   }

   public void movePosition(int mapId, Role role) {
      AOI scene = this.getScene(mapId);
      this.acceptTask(scene.getId(), () -> scene.move(role));
   }

   public void sendMessages(Role role, Message... messages) {
      AOI scene = this.getScene(role.getPos().getArea());
      this.acceptTask(scene.getId(), () -> scene.sendMessages(role, messages));
   }

   public void acceptTask(int sceneId, Runnable task) {
      if (task != null) {
         int size = workerPool.size();
         int index = sceneId % size;
         ((TaskWorker)workerPool.get(index)).addTask(task);
      }
   }

   public List<Role> getNearbyRoles(int mapId, Role role) {
      AOI scene = this.getScene(mapId);
      return scene.getNearbyRoles(role);
   }

   static {
      for(int i = 1; i <= 8; ++i) {
         TaskWorker worker = new TaskWorker(i);
         workerPool.add(worker);
         (new NamedThreadFactory("scene_" + i)).newThread(worker).start();
      }

   }

   private static class TaskWorker implements Runnable {
      private final int id;
      private final BlockingQueue<Runnable> taskQueue = new LinkedBlockingQueue();

      TaskWorker(int index) {
         this.id = index;
      }

      void addTask(Runnable task) {
         this.taskQueue.add(task);
      }

      public void run() {
         while(true) {
            try {
               Runnable task = (Runnable)this.taskQueue.take();
               task.run();
            } catch (Exception e) {
               SceneManager.logger.error("Scene task error" + this.id, e);
            }
         }
      }
   }
}
