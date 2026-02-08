package com.dnfm.cross.core;

import com.dnfm.cross.core.codec.CrossSerializerHelper;
import com.dnfm.cross.core.server.BaseCMessageDispatcher;
import java.net.InetSocketAddress;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.buffer.SimpleBufferAllocator;
import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.DefaultSocketSessionConfig;
import org.apache.mina.transport.socket.SocketAcceptor;
import org.apache.mina.transport.socket.SocketSessionConfig;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CrossServer {
   private final Logger logger = LoggerFactory.getLogger(CrossServer.class);
   private SocketAcceptor acceptor;

   public void start(int port) throws Exception {
      IoBuffer.setUseDirectBuffer(false);
      IoBuffer.setAllocator(new SimpleBufferAllocator());
      this.acceptor = new NioSocketAcceptor();
      this.acceptor.setReuseAddress(true);
      this.acceptor.getSessionConfig().setAll(this.getSessionConfig());
      this.logger.info("cross server start at port:{},正在监听服务器点对点的连接...", port);
      DefaultIoFilterChainBuilder filterChain = this.acceptor.getFilterChain();
      filterChain.addLast("codec", new ProtocolCodecFilter(CrossSerializerHelper.getInstance().getCodecFactory()));
      this.acceptor.setHandler(new Game2GameIoHandler(BaseCMessageDispatcher.getInstance()));
      this.acceptor.setDefaultLocalAddress(new InetSocketAddress(port));
      this.acceptor.bind();
   }

   private SocketSessionConfig getSessionConfig() {
      SocketSessionConfig config = new DefaultSocketSessionConfig();
      config.setKeepAlive(true);
      config.setReuseAddress(true);
      config.setSoLinger(0);
      return config;
   }

   public void shutdown() {
      if (this.acceptor != null) {
         this.acceptor.unbind();
         this.acceptor.dispose();
      }

      this.logger.error("---------> cross server stop successfully");
   }
}
