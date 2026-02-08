import 'dart:async';
import 'package:flutter_test/flutter_test.dart';
import 'package:dnf_flutter/network/game_service.dart';
import 'package:dnf_flutter/network/tcp_network_service.dart';
import 'package:dnf_flutter/protobuf/protobuf/game_messages.pb.dart';

void main() {
  group('Integration Tests', () {
    late GameService gameService;

    setUp(() {
      gameService = GameService();
    });

    tearDown(() {
      gameService.dispose();
    });

    test('should connect to server', () async {
      final completer = Completer<bool>();

      gameService
          .connect(serverUrl: '127.0.0.1:10001')
          .then((_) {
            completer.complete(true);
          })
          .catchError((error) {
            print('Connection error: $error');
            completer.complete(false);
          });

      final result = await completer.future.timeout(
        const Duration(seconds: 5),
        onTimeout: () => false,
      );

      expect(result, true, reason: 'Failed to connect to server');
    });

    test('should handle connection status changes', () async {
      final statusList = <ConnectionStatus>[];

      gameService.connect(serverUrl: '127.0.0.1:10001');

      await Future.delayed(const Duration(seconds: 2));

      expect(gameService.token, isNull);
      expect(gameService.currentUserId, isNull);
    });

    test('should disconnect from server', () async {
      await gameService.connect(serverUrl: '127.0.0.1:10001');
      await Future.delayed(const Duration(seconds: 1));

      gameService.disconnect();

      expect(gameService.token, isNull);
    });
  });
}
