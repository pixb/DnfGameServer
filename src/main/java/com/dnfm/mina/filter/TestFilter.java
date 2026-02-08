package com.dnfm.mina.filter;

import org.apache.mina.core.filterchain.IoFilter;
import org.apache.mina.core.filterchain.IoFilterAdapter;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestFilter extends IoFilterAdapter {
   private static final Logger logger = LoggerFactory.getLogger(TestFilter.class);

   public void messageReceived(IoFilter.NextFilter nextFilter, IoSession session, Object message) throws Exception {
      nextFilter.messageReceived(session, message);
   }
}
