package com.test.common.start;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

public class UDPServerTest {
   public static void main(String[] args) throws IOException {
      IoAcceptor acceptor = new NioSocketAcceptor();
      acceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"))));
      acceptor.bind(new InetSocketAddress(9123));
      System.out.println("udp server success");
   }
}
