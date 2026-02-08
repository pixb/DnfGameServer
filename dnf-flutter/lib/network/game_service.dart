import 'dart:async';
import 'package:fixnum/fixnum.dart';
import 'package:logger/logger.dart';
import 'tcp_network_service.dart';
import '../protobuf/protobuf/game_messages.pb.dart';

class GameService {
  static final GameService _instance = GameService._internal();
  factory GameService() => _instance;
  GameService._internal() {
    _networkService.onStatusChange(_onStatusChange);
  }

  final TcpNetworkService _networkService = TcpNetworkService();
  final Logger _logger = Logger();

  final StreamController<RoleInfo> _roleListController =
      StreamController.broadcast();
  final StreamController<PlayerState> _playerStateController =
      StreamController.broadcast();
  final StreamController<GameStateSync> _gameStateController =
      StreamController.broadcast();

  Stream<RoleInfo> get roleListStream => _roleListController.stream;
  Stream<PlayerState> get playerStateStream => _playerStateController.stream;
  Stream<GameStateSync> get gameStateStream => _gameStateController.stream;

  String? _token;
  Int64? _currentUserId;
  RoleInfo? _currentRole;

  String? get token => _token;
  Int64? get currentUserId => _currentUserId;
  RoleInfo? get currentRole => _currentRole;

  void _onStatusChange(ConnectionStatus status) {
    _logger.i('Network status changed: $status');
  }

  Future<void> connect({String? serverUrl}) async {
    await _networkService.connect(serverUrl: serverUrl, token: _token);
    _setupMessageHandlers();
  }

  void disconnect() {
    _networkService.disconnect();
  }

  void _setupMessageHandlers() {
    _networkService.onMessage(1002, (message) {
      try {
        final response = LoginResponse.fromBuffer(message.data);
        if (response.success) {
          _token = response.token;
          _currentUserId = response.userId;
          _logger.i('Login successful: ${response.userId}');
        } else {
          _logger.e('Login failed: ${response.message}');
        }
      } catch (e) {
        _logger.e('Parse login response failed: $e');
      }
    });

    _networkService.onMessage(2002, (message) {
      try {
        final response = MoveResponse.fromBuffer(message.data);
        _logger.d('Move response: ${response.success}');
      } catch (e) {
        _logger.e('Parse move response failed: $e');
      }
    });

    _networkService.onMessage(2004, (message) {
      try {
        final response = AttackResponse.fromBuffer(message.data);
        _logger.d(
          'Attack response: ${response.success}, damage: ${response.damage}',
        );
      } catch (e) {
        _logger.e('Parse attack response failed: $e');
      }
    });

    _networkService.onMessage(3001, (message) {
      try {
        final playerState = PlayerState.fromBuffer(message.data);
        _playerStateController.add(playerState);
        _logger.d('Player state update: ${playerState.uid}');
      } catch (e) {
        _logger.e('Parse player state failed: $e');
      }
    });

    _networkService.onMessage(3002, (message) {
      try {
        final gameState = GameStateSync.fromBuffer(message.data);
        _gameStateController.add(gameState);
        _logger.d('Game state update: ${gameState.players.length} players');
      } catch (e) {
        _logger.e('Parse game state failed: $e');
      }
    });

    _networkService.onMessage(1003, (message) {
      try {
        final response = HeartbeatResponse.fromBuffer(message.data);
        _logger.d('Heartbeat response: ${response.serverTime}');
      } catch (e) {
        _logger.e('Parse heartbeat response failed: $e');
      }
    });
  }

  Future<LoginResponse> login(
    String username,
    String password, {
    String? deviceId,
  }) async {
    final request = LoginRequest()
      ..username = username
      ..password = password
      ..deviceId =
          deviceId ?? 'flutter_${DateTime.now().millisecondsSinceEpoch}';

    final message = BaseMessage()
      ..msgId = _generateMsgId()
      ..timestamp = Int64(DateTime.now().millisecondsSinceEpoch)
      ..msgType = 1001
      ..data = request.writeToBuffer();

    try {
      final response = await _networkService.sendRequest<BaseMessage>(message);
      final loginResponse = LoginResponse.fromBuffer(response.data);

      if (loginResponse.success) {
        _token = loginResponse.token;
        _currentUserId = loginResponse.userId;
        _logger.i('Login successful: ${loginResponse.userId}');
      }

      return loginResponse;
    } catch (e) {
      _logger.e('Login failed: $e');
      rethrow;
    }
  }

  Future<MoveResponse> move(double x, double y, int direction) async {
    if (_currentUserId == null) {
      throw Exception('Not logged in');
    }

    final request = MoveRequest()
      ..uid = _currentUserId!
      ..x = x
      ..y = y
      ..direction = direction;

    final message = BaseMessage()
      ..msgId = _generateMsgId()
      ..timestamp = Int64(DateTime.now().millisecondsSinceEpoch)
      ..msgType = 2001
      ..data = request.writeToBuffer();

    try {
      final response = await _networkService.sendRequest<BaseMessage>(message);
      return MoveResponse.fromBuffer(response.data);
    } catch (e) {
      _logger.e('Move failed: $e');
      rethrow;
    }
  }

  Future<AttackResponse> attack(int skillId, int direction) async {
    if (_currentUserId == null) {
      throw Exception('Not logged in');
    }

    final request = AttackRequest()
      ..uid = _currentUserId!
      ..skillId = skillId
      ..direction = direction;

    final message = BaseMessage()
      ..msgId = _generateMsgId()
      ..timestamp = Int64(DateTime.now().millisecondsSinceEpoch)
      ..msgType = 2003
      ..data = request.writeToBuffer();

    try {
      final response = await _networkService.sendRequest<BaseMessage>(message);
      return AttackResponse.fromBuffer(response.data);
    } catch (e) {
      _logger.e('Attack failed: $e');
      rethrow;
    }
  }

  void selectRole(RoleInfo role) {
    _currentRole = role;
    _logger.i('Selected role: ${role.name}');
  }

  String _generateMsgId() {
    return '${DateTime.now().millisecondsSinceEpoch}_${DateTime.now().microsecond}';
  }

  void dispose() {
    _roleListController.close();
    _playerStateController.close();
    _gameStateController.close();
    _networkService.dispose();
  }
}
