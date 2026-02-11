package com.dnfm.common.start;

import com.dnfm.common.spring.ScheduledService;
import com.dnfm.common.thread.NamedThreadFactory;
import com.dnfm.game.ServerService;
import com.dnfm.mina.MessageDispatcher;
import com.dnfm.mina.ServerSocketIoHandler;
import com.dnfm.mina.codec.SerializerHelper;
import com.dnfm.mina.message.MessageFactory;
import com.dnfm.mina.task.TaskHandlerContext;
import java.net.InetSocketAddress;
import java.nio.channels.spi.SelectorProvider;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.buffer.SimpleBufferAllocator;
import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.service.SimpleIoProcessorPool;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.DefaultSocketSessionConfig;
import org.apache.mina.transport.socket.SocketAcceptor;
import org.apache.mina.transport.socket.SocketSessionConfig;
import org.apache.mina.transport.socket.nio.NioProcessor;
import org.apache.mina.transport.socket.nio.NioSession;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class GameServer implements CommandLineRunner {
   private static SocketAcceptor acceptor;
   private final Logger logger = LoggerFactory.getLogger(GameServer.class);
   @Autowired
   ServerService serverService;
   @Autowired
   ScheduledService scheduledService;

   public void run(String... var1) throws Exception {
      this.initGameServer();
   }

   private void initGameServer() throws Exception {
      this.logger.error("GameServer.initGameServer...");
      MessageFactory.INSTANCE.initMessagePool("com.dnfm.mina.protobuf");
      TaskHandlerContext.INSTANCE.initialize();
      IoBuffer.setUseDirectBuffer(false);
      IoBuffer.setAllocator(new SimpleBufferAllocator());
      Executor executor = Executors.newCachedThreadPool(new NamedThreadFactory("SOCKET-IO-PROCESSOR"));
      int coreSize = Runtime.getRuntime().availableProcessors();
      SimpleIoProcessorPool<NioSession> processor = new SimpleIoProcessorPool(NioProcessor.class, executor, coreSize, (SelectorProvider)null);
      acceptor = new NioSocketAcceptor(processor);
      acceptor.setReuseAddress(true);
      SocketSessionConfig config = new DefaultSocketSessionConfig();
      config.setKeepAlive(false);
      config.setTcpNoDelay(true);
      config.setReuseAddress(true);
      config.setReadBufferSize(2048);
      config.setIdleTime(IdleStatus.BOTH_IDLE, 30000);
      config.setSoLinger(0);
      acceptor.getSessionConfig().setAll(config);
      DefaultIoFilterChainBuilder filterChain = acceptor.getFilterChain();
      filterChain.addLast("codec", new ProtocolCodecFilter(SerializerHelper.getInstance().getCodecFactory()));
      acceptor.setHandler(new ServerSocketIoHandler(MessageDispatcher.getInstance()));
      acceptor.setBacklog(3000);
      acceptor.setDefaultLocalAddress(new InetSocketAddress(this.serverService.getInetIp(), this.serverService.getPort()));
      acceptor.bind();
      this.logger.error("game_server start at ip {}, port:{},", this.serverService.getInetIp(), this.serverService.getPort());
      this.logger.error("start game_server success");
   }

   public SocketAcceptor getAcceptor() {
      return acceptor;
   }
}
