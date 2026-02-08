import 'package:flutter_test/flutter_test.dart';
import 'package:fixnum/fixnum.dart';
import 'package:dnf_flutter/network/game_service.dart';
import 'package:dnf_flutter/protobuf/protobuf/game_messages.pb.dart';

void main() {
  group('GameService Tests', () {
    late GameService gameService;

    setUp(() {
      gameService = GameService();
    });

    test('should be singleton', () {
      final service1 = GameService();
      final service2 = GameService();
      expect(identical(service1, service2), true);
    });

    test('token should be null initially', () {
      expect(gameService.token, null);
    });

    test('currentUserId should be null initially', () {
      expect(gameService.currentUserId, null);
    });

    test('currentRole should be null initially', () {
      expect(gameService.currentRole, null);
    });

    test('should have roleListStream', () {
      expect(gameService.roleListStream, isNotNull);
    });

    test('should have playerStateStream', () {
      expect(gameService.playerStateStream, isNotNull);
    });

    test('should have gameStateStream', () {
      expect(gameService.gameStateStream, isNotNull);
    });

    test('should select role', () {
      final role = RoleInfo()
        ..uid = Int64(12345)
        ..name = 'TestRole'
        ..level = 10
        ..job = 1
        ..exp = Int64(1000)
        ..gold = Int64(500);

      gameService.selectRole(role);

      expect(gameService.currentRole?.name, 'TestRole');
      expect(gameService.currentRole?.level, 10);
    });

    test('should handle disconnect when not connected', () {
      gameService.disconnect();
    });

    test('should handle dispose', () {
      gameService.dispose();
    });
  });
}
