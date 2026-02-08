class NetworkConfig {
  static const String defaultServerUrl = 'ws://127.0.0.1:10001';
  static const int defaultPort = 10001;
  static const Duration connectTimeout = Duration(seconds: 10);
  static const Duration receiveTimeout = Duration(seconds: 30);
  static const Duration sendTimeout = Duration(seconds: 10);
  static const Duration heartbeatInterval = Duration(seconds: 30);
  static const int maxReconnectAttempts = 5;
  static const Duration reconnectDelay = Duration(seconds: 3);

  static String getServerUrl({String host = '127.0.0.1', int port = 10001}) {
    return 'ws://$host:$port';
  }

  static String getHttpUrl({String host = '127.0.0.1', int port = 20001}) {
    return 'http://$host:$port';
  }
}
