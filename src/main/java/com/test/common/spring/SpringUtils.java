package com.test.common.spring;

import java.util.Map;
import javax.annotation.PostConstruct;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringUtils implements ApplicationContextAware {
   private static ApplicationContext applicationContext;
   private static SpringUtils self;

   @PostConstruct
   private void init() {
      self = this;
   }

   public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
      SpringUtils.applicationContext = applicationContext;
   }

   public static ApplicationContext getApplicationContext() {
      return applicationContext;
   }

   public static Object getBean(String name) {
      return getApplicationContext().getBean(name);
   }

   public static <T> T getBean(Class<T> clazz) {
      return (T)getApplicationContext().getBean(clazz);
   }

   public static <T> T getBean(String name, Class<T> clazz) {
      return (T)getApplicationContext().getBean(name, clazz);
   }

   public static <T> Map<String, T> getBeansOfType(Class<T> var1) {
      return getApplicationContext().getBeansOfType(var1);
   }
}
