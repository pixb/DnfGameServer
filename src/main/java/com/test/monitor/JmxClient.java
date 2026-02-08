package com.test.monitor;

import com.test.game.utils.FileUtils;
import java.util.HashMap;
import java.util.Map;
import javax.management.JMX;
import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

public class JmxClient {
   public static void main(String[] args) throws Exception {
      String user = "admin";
      String pwd = "hellokittyxyz";
      String[] account = new String[]{user, pwd};
      Map<String, String[]> props = new HashMap();
      props.put("jmx.remote.credentials", account);
      JMXServiceURL address = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://x.x.x.x:10083/jmxrmi");
      JMXConnector connector = JMXConnectorFactory.connect(address, props);
      MBeanServerConnection mBeanConnection = connector.getMBeanServerConnection();
      connector.connect();
      ObjectName objectName = new ObjectName("GameMXBean:name=gameMonitor");
      GameMonitorMBean mBean = (GameMonitorMBean)JMX.newMBeanProxy(mBeanConnection, objectName, GameMonitorMBean.class);
      String script = FileUtils.readText("hotswap/CommonScript.java");
      System.err.println("script:\n" + script);
      System.err.println(mBean.execGroovyScript(script));
   }
}
