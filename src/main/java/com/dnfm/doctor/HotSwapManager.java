package com.dnfm.doctor;

import com.dnfm.game.utils.FileUtils;
import com.dnfm.logs.LoggerUtils;
import com.sun.tools.attach.VirtualMachine;
import groovy.lang.GroovyClassLoader;
import java.io.File;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class HotSwapManager {
   public static volatile Exception exception;
   public static volatile String log;
   private static HotSwapManager self;
   public Map<String, Object> params = new HashMap();
   private final Logger logger = LoggerFactory.getLogger(HotSwapManager.class);

   public static HotSwapManager getInstance() {
      return self;
   }

   @PostConstruct
   private void init() {
      self = new HotSwapManager();
   }

   public String loadJavaFile(String classFullName) {
      String simpleName = classFullName.substring(classFullName.lastIndexOf(".") + 1);

      try {
         String filePath = "hotswap/" + simpleName + ".java";
         String clazzFile = FileUtils.readText(filePath);
         Class<?> clazz = (new GroovyClassLoader()).parseClass(clazzFile, classFullName);
         clazz.newInstance();
         return "load class succ";
      } catch (Exception e) {
         return "load class failed ," + e.getMessage();
      }
   }

   public String reloadClass(String path) {
      try {
         String pid = ManagementFactory.getRuntimeMXBean().getName().split("@")[0];
         VirtualMachine vm = VirtualMachine.attach(pid);
         path = "hotswap" + File.separator + path;
         List<File> files = FileUtils.listFiles(path);
         List<String> succFiles = new ArrayList();
         log = "empty";
         exception = null;
         LoggerUtils.error("热更目录[{}]，总共有{}个文件", path, files.size());

         for(File file : files) {
            String classPath = path + File.separator + file.getName();
            LoggerUtils.error("reload path ==" + classPath);
            vm.loadAgent("agent/hotswap-agent.jar", classPath);
            succFiles.add(file.getName());
            this.logger.error("热更日志{}", log);
            this.logger.error("热更异常{}", exception);
         }

         return log;
      } catch (Throwable e) {
         this.logger.error("", e);
         return "热更失败";
      }
   }
}
