package com.test.common.start;

import com.test.mina.MessageDispatcher;
import com.test.mina.ServerSocketIoHandler;
import com.test.mina.codec.SerializerHelper;
import com.test.mina.udp.UdpMessageFactory;
import com.test.mina.udp.UdpTaskHandlerContext;
import java.net.InetSocketAddress;
import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.DatagramSessionConfig;
import org.apache.mina.transport.socket.nio.NioDatagramAcceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class UdpServer implements CommandLineRunner {
   private final Logger logger = LoggerFactory.getLogger(UdpServer.class);
   private static NioDatagramAcceptor acceptor;

   public void run(String... var1) throws Exception {
      initUdpServer();
   }

   public static void main(String[] args) throws Exception {
      initUdpServer();
   }

   public static void initUdpServer() throws Exception {
      UdpMessageFactory.INSTANCE.initMessagePool("com.test.mina.udp.model");
      UdpTaskHandlerContext.INSTANCE.initialize();
      int coreSize = Runtime.getRuntime().availableProcessors();
      acceptor = new NioDatagramAcceptor();
      DefaultIoFilterChainBuilder filterChain = acceptor.getFilterChain();
      filterChain.addLast("codec", new ProtocolCodecFilter(SerializerHelper.getInstance().getCodecFactory()));
      acceptor.setHandler(new ServerSocketIoHandler(MessageDispatcher.getInstance()));
      DatagramSessionConfig dcfg = acceptor.getSessionConfig();
      dcfg.setReuseAddress(true);
      acceptor.bind(new InetSocketAddress(31002));
   }

   public NioDatagramAcceptor getAcceptor() {
      return acceptor;
   }
}
