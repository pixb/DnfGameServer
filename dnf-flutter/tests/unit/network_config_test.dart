import 'package:flutter_test/flutter_test.dart';
import 'package:fixnum/fixnum.dart';
import 'package:dnf_flutter/network/network_config.dart';

void main() {
  group('NetworkConfig Tests', () {
    test('defaultServerUrl should be ws://127.0.0.1:20002', () {
      expect(NetworkConfig.defaultServerUrl, 'ws://127.0.0.1:20002');
    });

    test('defaultPort should be 20002', () {
      expect(NetworkConfig.defaultPort, 20002);
    });

    test('connectTimeout should be 10 seconds', () {
      expect(NetworkConfig.connectTimeout, const Duration(seconds: 10));
    });

    test('receiveTimeout should be 30 seconds', () {
      expect(NetworkConfig.receiveTimeout, const Duration(seconds: 30));
    });

    test('sendTimeout should be 10 seconds', () {
      expect(NetworkConfig.sendTimeout, const Duration(seconds: 10));
    });

    test('heartbeatInterval should be 30 seconds', () {
      expect(NetworkConfig.heartbeatInterval, const Duration(seconds: 30));
    });

    test('maxReconnectAttempts should be 5', () {
      expect(NetworkConfig.maxReconnectAttempts, 5);
    });

    test('reconnectDelay should be 3 seconds', () {
      expect(NetworkConfig.reconnectDelay, const Duration(seconds: 3));
    });

    test('getServerUrl should return correct WebSocket URL', () {
      expect(NetworkConfig.getServerUrl(), 'ws://127.0.0.1:20002');
      expect(NetworkConfig.getServerUrl(host: '192.168.1.1'), 'ws://192.168.1.1:20002');
      expect(NetworkConfig.getServerUrl(port: 8080), 'ws://127.0.0.1:8080');
      expect(NetworkConfig.getServerUrl(host: 'example.com', port: 9999), 'ws://example.com:9999');
    });

    test('getHttpUrl should return correct HTTP URL', () {
      expect(NetworkConfig.getHttpUrl(), 'http://127.0.0.1:20001');
      expect(NetworkConfig.getHttpUrl(host: '192.168.1.1'), 'http://192.168.1.1:20001');
      expect(NetworkConfig.getHttpUrl(port: 8080), 'http://127.0.0.1:8080');
      expect(NetworkConfig.getHttpUrl(host: 'example.com', port: 9999), 'http://example.com:9999');
    });
  });
}
