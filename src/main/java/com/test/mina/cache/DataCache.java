package com.test.mina.cache;

import com.test.mina.codec.SessionInfo;
import java.util.HashMap;
import java.util.Map;

public interface DataCache {
   Map<Long, SessionInfo> SESSION_MAP = new HashMap();
}
