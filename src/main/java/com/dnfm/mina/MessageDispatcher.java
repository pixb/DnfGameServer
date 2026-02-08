package com.dnfm.mina;

import com.dnfm.game.role.model.Role;
import com.dnfm.mina.annotation.MessageMeta;
import com.dnfm.mina.annotation.RequestMapping;
import com.dnfm.mina.cache.SessionUtils;
import com.dnfm.mina.message.CmdExecutor;
import com.dnfm.mina.message.IMessageDispatcher;
import com.dnfm.mina.protobuf.Message;
import com.dnfm.mina.session.SessionManager;
import com.dnfm.mina.session.SessionProperties;
import com.dnfm.mina.task.MessageTask;
import com.dnfm.mina.task.TaskHandlerContext;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessageDispatcher implements IMessageDispatcher {
   private static final Map<String, CmdExecutor> MODULE_CMD_HANDLERS = new HashMap();
   private static final MessageDispatcher instance = new MessageDispatcher();
   private final Logger logger = LoggerFactory.getLogger(this.getClass());

   public static MessageDispatcher getInstance() {
      return instance;
   }

   public void registerMessageHandler(Object controller) {
      try {
         Method[] methods = controller.getClass().getDeclaredMethods();

         for(Method method : methods) {
            RequestMapping mapperAnnotation = (RequestMapping)method.getAnnotation(RequestMapping.class);
            if (mapperAnnotation != null) {
               String meta = this.getMessageMeta(method);
               if (meta == null) {
                  this.logger.error("methodName = " + method.getName());
                  throw new RuntimeException(String.format("controller[%s] method[%s] lack of RequestMapping annotation", controller.getClass().getSimpleName(), method.getName()));
               }

               CmdExecutor cmdExecutor = (CmdExecutor)MODULE_CMD_HANDLERS.get(meta);
               if (cmdExecutor != null && !meta.equals("-1")) {
                  throw new RuntimeException(String.format("CmdExecutor[%s] duplicated", meta));
               }

               cmdExecutor = CmdExecutor.valueOf(method, method.getParameterTypes(), controller);
               MODULE_CMD_HANDLERS.put(meta, cmdExecutor);
            }
         }
      } catch (Exception e) {
         this.logger.error("", e);
      }

   }

   private String getMessageMeta(Method method) {
      for(Class<?> paramClazz : method.getParameterTypes()) {
         if (Message.class.isAssignableFrom(paramClazz)) {
            MessageMeta protocol = (MessageMeta)paramClazz.getAnnotation(MessageMeta.class);
            if (protocol != null) {
               return String.valueOf(protocol.module());
            }
         }
      }

      return null;
   }

   public void dispatch(IoSession session, Message message) {
      int module = message.getModule();
      CmdExecutor cmdExecutor = (CmdExecutor)MODULE_CMD_HANDLERS.get(String.valueOf(module));
      if (cmdExecutor == null) {
         this.logger.error("message executor missed, module={}", module);
      } else {
         Object[] params = this.convertToMethodParams(session, cmdExecutor.getParams(), message);
         Object controller = cmdExecutor.getHandler();
         int distributeKey = (Integer)session.getAttribute(SessionProperties.DISTRIBUTE_KEY);
         TaskHandlerContext.INSTANCE.acceptTask(MessageTask.valueOf(distributeKey, controller, cmdExecutor.getMethod(), params, message));
      }
   }

   private Object[] convertToMethodParams(IoSession session, Class<?>[] methodParams, Message message) {
      Object[] result = new Object[methodParams == null ? 0 : methodParams.length];

      for(int i = 0; i < result.length; ++i) {
         Class<?> param = methodParams[i];
         if (IoSession.class.isAssignableFrom(param)) {
            result[i] = session;
         } else if (Long.class.isAssignableFrom(param)) {
            result[i] = SessionManager.INSTANCE.getPlayerIdBy(session);
         } else if (Long.TYPE.isAssignableFrom(param)) {
            result[i] = SessionManager.INSTANCE.getPlayerIdBy(session);
         } else if (Role.class.isAssignableFrom(param)) {
            Role role = SessionUtils.getRoleBySession(session);
            result[i] = role;
         } else if (Message.class.isAssignableFrom(param)) {
            result[i] = message;
         }
      }

      return result;
   }
}
