import 'dart:async';
import 'dart:io';
import 'package:fixnum/fixnum.dart';
import 'package:logger/logger.dart';
import 'network_config.dart';
import '../protobuf/protobuf/game_messages.pb.dart';

enum ConnectionStatus {
  disconnected,
  connecting,
  connected,
  reconnecting,
  error,
}

typedef MessageCallback = void Function(BaseMessage message);

class TcpNetworkService {
  static final TcpNetworkService _instance = TcpNetworkService._internal();
  factory TcpNetworkService() => _instance;
  TcpNetworkService._internal();

  final Logger _logger = Logger();
  Socket? _socket;
  ConnectionStatus _status = ConnectionStatus.disconnected;
  String _serverUrl = NetworkConfig.defaultServerUrl;
  final Map<String, List<MessageCallback>> _messageCallbacks = {};
  final Map<String, Completer<BaseMessage>> _pendingRequests = {};
  Timer? _heartbeatTimer;
  int _reconnectAttempts = 0;
  String? _token;

  ConnectionStatus get status => _status;
  bool get isConnected => _status == ConnectionStatus.connected;
  String? get token => _token;

  Future<void> connect({String? serverUrl, String? token}) async {
    if (_status == ConnectionStatus.connecting ||
        _status == ConnectionStatus.connected) {
      _logger.w('Already connected or connecting');
      return;
    }

    _serverUrl = serverUrl ?? NetworkConfig.defaultServerUrl;
    _token = token;
    _status = ConnectionStatus.connecting;
    _notifyStatusChange();

    try {
      String host;
      int port;

      if (_serverUrl.contains('://')) {
        final uri = Uri.parse(_serverUrl);
        host = uri.host;
        port = uri.port;
      } else {
        final parts = _serverUrl.split(':');
        if (parts.length == 2) {
          host = parts[0];
          port = int.tryParse(parts[1]) ?? 0;
        } else {
          throw Exception('Invalid server URL format: $_serverUrl');
        }
      }

      if (port == 0) {
        throw Exception('Invalid port: $port');
      }

      _logger.i('Connecting to $host:$port');
      _socket = await Socket.connect(
        host,
        port,
        timeout: NetworkConfig.connectTimeout,
      );

      _status = ConnectionStatus.connected;
      _reconnectAttempts = 0;
      _notifyStatusChange();
      _logger.i('Connected to server');

      _startHeartbeat();
      _listenToMessages();
    } catch (e) {
      _status = ConnectionStatus.error;
      _notifyStatusChange();
      _logger.e('Connection failed: $e');

      if (_reconnectAttempts < NetworkConfig.maxReconnectAttempts) {
        _status = ConnectionStatus.reconnecting;
        _notifyStatusChange();
        _reconnectAttempts++;
        _logger.i('Reconnecting... Attempt $_reconnectAttempts');
        await Future.delayed(NetworkConfig.reconnectDelay);
        await connect(serverUrl: _serverUrl, token: _token);
      }
    }
  }

  void disconnect() {
    _heartbeatTimer?.cancel();
    _socket?.destroy();
    _socket = null;
    _status = ConnectionStatus.disconnected;
    _notifyStatusChange();
    _logger.i('Disconnected from server');
  }

  Future<T> sendRequest<T extends BaseMessage>(BaseMessage message) async {
    if (!isConnected) {
      throw Exception('Not connected to server');
    }

    final msgId = message.msgId;
    final completer = Completer<BaseMessage>();
    _pendingRequests[msgId] = completer;

    try {
      _socket?.add(message.writeToBuffer());
      _logger.d('Sent message: ${message.msgType}');

      final response = await completer.future.timeout(
        NetworkConfig.receiveTimeout,
      );
      return response as T;
    } catch (e) {
      _pendingRequests.remove(msgId);
      _logger.e('Send request failed: $e');
      rethrow;
    }
  }

  void sendMessage(BaseMessage message) {
    if (!isConnected) {
      _logger.w('Not connected to server');
      return;
    }

    try {
      _socket?.add(message.writeToBuffer());
      _logger.d('Sent message: ${message.msgType}');
    } catch (e) {
      _logger.e('Send message failed: $e');
    }
  }

  void onMessage(int msgType, MessageCallback callback) {
    final key = msgType.toString();
    _messageCallbacks.putIfAbsent(key, () => []).add(callback);
  }

  void offMessage(int msgType, MessageCallback callback) {
    final key = msgType.toString();
    _messageCallbacks[key]?.remove(callback);
    if (_messageCallbacks[key]?.isEmpty ?? false) {
      _messageCallbacks.remove(key);
    }
  }

  void _listenToMessages() {
    _socket?.listen(
      (data) {
        try {
          final message = BaseMessage.fromBuffer(data);
          _logger.d('Received message: ${message.msgType}');

          final msgId = message.msgId;
          if (_pendingRequests.containsKey(msgId)) {
            final completer = _pendingRequests.remove(msgId);
            completer?.complete(message);
          } else {
            final key = message.msgType.toString();
            final callbacks = _messageCallbacks[key];
            if (callbacks != null) {
              for (final callback in callbacks) {
                callback(message);
              }
            }
          }
        } catch (e) {
          _logger.e('Parse message failed: $e');
        }
      },
      onError: (error) {
        _logger.e('Socket error: $error');
        _status = ConnectionStatus.error;
        _notifyStatusChange();
      },
      onDone: () {
        _logger.i('Socket connection closed');
        _status = ConnectionStatus.disconnected;
        _notifyStatusChange();

        if (_reconnectAttempts < NetworkConfig.maxReconnectAttempts) {
          _status = ConnectionStatus.reconnecting;
          _notifyStatusChange();
          _reconnectAttempts++;
          _logger.i('Reconnecting... Attempt $_reconnectAttempts');
          Future.delayed(NetworkConfig.reconnectDelay, () {
            connect(serverUrl: _serverUrl, token: _token);
          });
        }
      },
    );
  }

  void _startHeartbeat() {
    _heartbeatTimer?.cancel();
    _heartbeatTimer = Timer.periodic(NetworkConfig.heartbeatInterval, (timer) {
      if (isConnected) {
        _sendHeartbeat();
      }
    });
  }

  void _sendHeartbeat() {
    final heartbeat = BaseMessage()
      ..msgId = _generateMsgId()
      ..timestamp = Int64(DateTime.now().millisecondsSinceEpoch)
      ..msgType = 1001;

    sendMessage(heartbeat);
  }

  String _generateMsgId() {
    return '${DateTime.now().millisecondsSinceEpoch}_${DateTime.now().microsecond}';
  }

  final List<void Function(ConnectionStatus)> _statusCallbacks = [];

  void onStatusChange(void Function(ConnectionStatus) callback) {
    _statusCallbacks.add(callback);
  }

  void offStatusChange(void Function(ConnectionStatus) callback) {
    _statusCallbacks.remove(callback);
  }

  void _notifyStatusChange() {
    for (final callback in _statusCallbacks) {
      callback(_status);
    }
  }

  void dispose() {
    disconnect();
    _messageCallbacks.clear();
    _pendingRequests.clear();
    _statusCallbacks.clear();
  }
}
