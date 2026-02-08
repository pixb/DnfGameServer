package com.test.common.start;

import com.test.mina.Global;
import com.test.mina.MessageDispatcher;
import com.test.mina.ServerSocketIoHandler;
import com.test.mina.codec.SerializerHelper;
import com.test.mina.udp.UdpMessageFactory;
import com.test.mina.udp.UdpTaskHandlerContext;
import java.net.InetSocketAddress;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.buffer.SimpleBufferAllocator;
import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.DatagramSessionConfig;
import org.apache.mina.transport.socket.nio.NioDatagramAcceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class GameServer implements CommandLineRunner {
   private static final Logger logger = LoggerFactory.getLogger(GameServer.class);
   private static NioDatagramAcceptor acceptor;
   @Autowired
   ServerService serverService;

   public void run(String... var1) throws Exception {
      initGameServer();
   }

   public static void start() {
      try {
         initGameServer();
      } catch (Exception e) {
         e.printStackTrace();
      }

   }

   private static void initGameServer() throws Exception {
      logger.error("GameServer.initGameServer...");
      UdpMessageFactory.INSTANCE.initMessagePool("com.test.mina.udp.model");
      UdpTaskHandlerContext.INSTANCE.initialize();
      IoBuffer.setUseDirectBuffer(false);
      IoBuffer.setAllocator(new SimpleBufferAllocator());
      acceptor = new NioDatagramAcceptor();
      DefaultIoFilterChainBuilder filterChain = acceptor.getFilterChain();
      filterChain.addLast("codec", new ProtocolCodecFilter(SerializerHelper.getInstance().getCodecFactory()));
      acceptor.setHandler(new ServerSocketIoHandler(MessageDispatcher.getInstance()));
      DatagramSessionConfig dcfg = acceptor.getSessionConfig();
      dcfg.setReuseAddress(true);
      acceptor.bind(new InetSocketAddress(31001));
      logger.error("udp_server start port:{},", 31001);
      Global.initLoop();
   }

   public NioDatagramAcceptor getAcceptor() {
      return acceptor;
   }
}
