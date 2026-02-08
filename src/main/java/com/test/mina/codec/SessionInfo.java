package com.test.mina.codec;

import org.apache.mina.core.session.IoSession;

public class SessionInfo {
   public int sessionId;
   public int roomId;
   public int roomServerId;
   public byte commType;
   public int tmpSessionId;
   public IoSession ioSession;
}
