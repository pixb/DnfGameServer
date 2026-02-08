package com.dnfm;

import com.dnfm.common.mysql.DynamicDataSourceRegister;
import com.dnfm.common.spring.SpringUtils;
import com.dnfm.core.SchedulerManager;
import com.dnfm.game.player.PlayerService;
import com.dnfm.logs.LoggerUtils;
import com.dnfm.mina.session.SessionManager;
import com.dnfm.mina.task.TaskHandlerContext;
import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner.Mode;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EntityScan(
   basePackages = {"com.dnfm"}
)
@EnableScheduling
@EnableCaching
@Import({DynamicDataSourceRegister.class})
@ServletComponentScan
public class GameServerStartup extends SpringBootServletInitializer {
   private static final Logger logger = LoggerFactory.getLogger(GameServerStartup.class);

   public static void main(String[] args) {
      StopWatch stopWatch = new StopWatch();
      stopWatch.start();

      try {
         SpringApplicationBuilder applicationBuilder = new SpringApplicationBuilder(new Object[]{GameServerStartup.class});
         applicationBuilder.bannerMode(Mode.OFF);
         applicationBuilder.run(args);
         logger.error("applicationBuilder.run执行完毕");
         SpringUtils.getIdentityService().init();
         ((PlayerService)SpringUtils.getBean(PlayerService.class)).loadAllPlayerProfiles();
      } catch (Exception e) {
         LoggerUtils.error("", e);
         System.exit(-1);
      }

      stopWatch.stop();
      logger.warn("启动成功，耗時{}毫秒", stopWatch.getTime());
      Runtime.getRuntime().addShutdownHook(new Thread() {
         public void run() {
            GameServerStartup.onServerShutDown();
         }
      });
   }

   private static void onServerShutDown() {
      logger.error("服务准备关闭");

      try {
         SessionManager.INSTANCE.killAllPlayers();
      } catch (Exception e) {
         LoggerUtils.error("", e);
      }

      TaskHandlerContext.INSTANCE.shutDown();
      SchedulerManager.getInstance().shutDown();
      logger.error("服务准备关闭结束");
   }

   protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
      return application.sources(new Class[]{GameServerStartup.class});
   }
}
