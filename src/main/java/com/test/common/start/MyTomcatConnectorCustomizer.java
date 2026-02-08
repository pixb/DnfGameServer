package com.test.common.start;

import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.Http11NioProtocol;
import org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer;

class MyTomcatConnectorCustomizer implements TomcatConnectorCustomizer {
   public void customize(Connector connector) {
      Http11NioProtocol protocol = (Http11NioProtocol)connector.getProtocolHandler();
      protocol.setMaxConnections(1000);
      protocol.setMaxThreads(300);
      protocol.setConnectionTimeout(3000);
   }
}
