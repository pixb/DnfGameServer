# DNF Flutter客户端API文档

## API概述

**通信协议**: WebSocket
**数据格式**: Protobuf
**服务端地址**: ws://localhost:20001/game
**心跳间隔**: 30秒

## Protobuf消息定义

### 1. 基础消息

```protobuf
syntax = "proto3";

package game;

// 基础消息
message GameMessage {
  string type = 1;           // 消息类型
  int64 timestamp = 2;        // 时间戳
  int64 player_id = 3;        // 玩家ID
  bytes data = 4;            // 消息数据
  string error = 5;           // 错误信息
}

// 消息类型枚举
enum MessageType {
  UNKNOWN = 0;
  // 认证相关
  LOGIN = 1;
  LOGOUT = 2;
  REGISTER = 3;
  
  // 角色相关
  CREATE_ROLE = 10;
  SELECT_ROLE = 11;
  DELETE_ROLE = 12;
  GET_ROLE_LIST = 13;
  
  // 游戏相关
  ENTER_GAME = 20;
  LEAVE_GAME = 21;
  READY = 22;
  
  // 战斗相关
  MOVE = 30;
  ATTACK = 31;
  SKILL = 32;
  HIT = 33;
  DEAD = 34;
  
  // 状态相关
  STATE_SYNC = 40;
  PLAYER_JOIN = 41;
  PLAYER_LEAVE = 42;
  
  // 心跳相关
  HEARTBEAT = 99;
}
```

### 2. 认证消息

```protobuf
// 登录请求
message LoginRequest {
  string username = 1;
  string password = 2;
  string device_id = 3;
}

// 登录响应
message LoginResponse {
  bool success = 1;
  string token = 2;
  repeated Role roles = 3;
  string error = 4;
}

// 注册请求
message RegisterRequest {
  string username = 1;
  string password = 2;
  string email = 3;
}

// 注册响应
message RegisterResponse {
  bool success = 1;
  string user_id = 2;
  string error = 3;
}

// 角色信息
message Role {
  int64 uid = 1;
  string name = 2;
  int32 job = 3;
  int32 level = 4;
  int32 hp = 5;
  int32 max_hp = 6;
  int32 exp = 7;
  int64 money = 8;
}
```

### 3. 游戏消息

```protobuf
// 移动请求
message MoveRequest {
  double x = 1;
  double y = 2;
  double vx = 3;      // 速度X
  double vy = 4;      // 速度Y
  int32 frame = 5;    // 帧号
}

// 移动响应
message MoveResponse {
  int64 player_id = 1;
  double x = 2;
  double y = 3;
  int32 frame = 4;
}

// 攻击请求
message AttackRequest {
  int32 skill_id = 1;
  int64 target_id = 2;
  double x = 3;
  double y = 4;
  int32 frame = 5;
}

// 攻击响应
message AttackResponse {
  int64 attacker_id = 1;
  int64 target_id = 2;
  int32 damage = 3;
  int32 skill_id = 4;
  bool is_critical = 5;
}

// 技能请求
message SkillRequest {
  int32 skill_id = 1;
  repeated int64 target_ids = 2;
  double x = 3;
  double y = 4;
  int32 frame = 5;
}

// 技能响应
message SkillResponse {
  int64 caster_id = 1;
  int32 skill_id = 2;
  repeated int64 target_ids = 3;
  repeated int32 damages = 4;
}

// 受击消息
message HitMessage {
  int64 player_id = 1;
  int64 attacker_id = 2;
  int32 damage = 3;
  int32 current_hp = 4;
  int32 max_hp = 5;
}

// 死亡消息
message DeadMessage {
  int64 player_id = 1;
  int64 killer_id = 2;
  int32 death_time = 3;
}
```

### 4. 状态同步消息

```protobuf
// 玩家状态
message PlayerState {
  int64 player_id = 1;
  string name = 2;
  double x = 3;
  double y = 4;
  int32 hp = 5;
  int32 max_hp = 6;
  int32 level = 7;
  int32 job = 8;
  int32 state = 9;      // 状态：0=idle, 1=walk, 2=attack
  int32 direction = 10;  // 方向：0=left, 1=right
}

// 状态同步请求
message StateSyncRequest {
  int32 frame = 1;
  repeated PlayerState players = 2;
}

// 玩家加入
message PlayerJoinMessage {
  PlayerState player = 1;
}

// 玩家离开
message PlayerLeaveMessage {
  int64 player_id = 1;
  string name = 2;
}
```

### 5. 心跳消息

```protobuf
// 心跳请求
message HeartbeatRequest {
  int64 timestamp = 1;
}

// 心跳响应
message HeartbeatResponse {
  int64 timestamp = 1;
  int32 server_time = 2;
}
```

## API调用示例

### 1. 连接服务器

```dart
class GameClient {
  late WebSocketChannel channel;
  String? playerId;
  bool isConnected = false;
  
  Future<void> connect(String url) async {
    try {
      channel = WebSocketChannel.connect(Uri.parse(url));
      isConnected = true;
      
      channel.stream.listen(
        (message) => handleMessage(message),
        onError: (error) => handleError(error),
        onDone: () => handleDisconnect(),
      );
      
      // 发送心跳
      startHeartbeat();
    } catch (e) {
      isConnected = false;
      rethrow;
    }
  }
  
  void handleMessage(dynamic message) {
    if (message is String) {
      final json = jsonDecode(message);
      final type = json['type'];
      
      switch (type) {
        case 'login':
          handleLoginResponse(json);
          break;
        case 'move':
          handleMoveResponse(json);
          break;
        case 'attack':
          handleAttackResponse(json);
          break;
      }
    }
  }
  
  void handleError(dynamic error) {
    logger.severe('WebSocket错误: $error');
    isConnected = false;
    
    // 尝试重连
    Future.delayed(Duration(seconds: 5), () {
      connect(serverUrl);
    });
  }
  
  void handleDisconnect() {
    logger.warning('WebSocket断开连接');
    isConnected = false;
    
    // 尝试重连
    Future.delayed(Duration(seconds: 5), () {
      connect(serverUrl);
    });
  }
}
```

### 2. 登录

```dart
class GameClient {
  Future<LoginResponse> login(String username, String password) async {
    final request = LoginRequest()
      ..username = username
      ..password = password
      ..deviceId = await getDeviceId();
    
    final message = GameMessage()
      ..type = MessageType.LOGIN.name
      ..timestamp = DateTime.now().millisecondsSinceEpoch
      ..data = request.writeToBuffer();
    
    channel.sink.add(message.writeToBuffer());
    
    // 等待响应
    final response = await waitForResponse<LoginResponse>(
      MessageType.LOGIN.name,
    );
    
    if (response.success) {
      playerId = response.token;
      return response;
    } else {
      throw Exception(response.error);
    }
  }
  
  Future<T> waitForResponse<T>(String type) async {
    final completer = Completer<T>();
    
    final subscription = channel.stream.listen((message) {
      if (message is String) {
        final json = jsonDecode(message);
        if (json['type'] == type) {
          final response = T.fromJson(json);
          completer.complete(response);
        }
      }
    });
    
    return completer.future.timeout(Duration(seconds: 10));
  }
}
```

### 3. 发送移动

```dart
class GameClient {
  int currentFrame = 0;
  final List<InputData> inputHistory = [];
  
  void sendMove(double x, double y, double vx, double vy) {
    currentFrame++;
    
    final request = MoveRequest()
      ..x = x
      ..y = y
      ..vx = vx
      ..vy = vy
      ..frame = currentFrame;
    
    final message = GameMessage()
      ..type = MessageType.MOVE.name
      ..timestamp = DateTime.now().millisecondsSinceEpoch
      ..playerId = playerId
      ..data = request.writeToBuffer();
    
    channel.sink.add(message.writeToBuffer());
    
    // 保存输入历史
    inputHistory.add(InputData(
      frame: currentFrame,
      x: x,
      y: y,
      vx: vx,
      vy: vy,
    ));
    
    if (inputHistory.length > 100) {
      inputHistory.removeAt(0);
    }
  }
}
```

### 4. 发送攻击

```dart
class GameClient {
  void sendAttack(int skillId, int64? targetId, double x, double y) {
    currentFrame++;
    
    final request = AttackRequest()
      ..skillId = skillId
      ..targetId = targetId ?? 0
      ..x = x
      ..y = y
      ..frame = currentFrame;
    
    final message = GameMessage()
      ..type = MessageType.ATTACK.name
      ..timestamp = DateTime.now().millisecondsSinceEpoch
      ..playerId = playerId
      ..data = request.writeToBuffer();
    
    channel.sink.add(message.writeToBuffer());
  }
}
```

### 5. 心跳机制

```dart
class GameClient {
  Timer? heartbeatTimer;
  static const heartbeatInterval = Duration(seconds: 30);
  
  void startHeartbeat() {
    heartbeatTimer?.cancel();
    heartbeatTimer = Timer.periodic(heartbeatInterval, (timer) {
      sendHeartbeat();
    });
  }
  
  void sendHeartbeat() {
    final request = HeartbeatRequest()
      ..timestamp = DateTime.now().millisecondsSinceEpoch;
    
    final message = GameMessage()
      ..type = MessageType.HEARTBEAT.name
      ..timestamp = DateTime.now().millisecondsSinceEpoch
      ..playerId = playerId
      ..data = request.writeToBuffer();
    
    channel.sink.add(message.writeToBuffer());
  }
  
  void stopHeartbeat() {
    heartbeatTimer?.cancel();
    heartbeatTimer = null;
  }
}
```

## 错误处理

### 1. 错误码定义

```dart
enum ErrorCode {
  SUCCESS(0, '成功'),
  INVALID_USERNAME(1001, '用户名无效'),
  INVALID_PASSWORD(1002, '密码错误'),
  USER_NOT_FOUND(1003, '用户不存在'),
  USER_ALREADY_EXISTS(1004, '用户已存在'),
  INVALID_TOKEN(1005, 'Token无效'),
  TOKEN_EXPIRED(1006, 'Token已过期'),
  SERVER_ERROR(2000, '服务器错误'),
  NETWORK_ERROR(3000, '网络错误'),
  TIMEOUT(3001, '请求超时'),
  UNKNOWN_ERROR(9999, '未知错误');
  
  final int code;
  final String message;
  
  const ErrorCode(this.code, this.message);
  
  static ErrorCode fromCode(int code) {
    return ErrorCode.values.firstWhere(
      (e) => e.code == code,
      orElse: () => UNKNOWN_ERROR,
    );
  }
}
```

### 2. 错误处理

```dart
class GameClient {
  void handleError(dynamic error) {
    logger.severe('发生错误: $error');
    
    if (error is WebSocketChannelException) {
      final errorCode = ErrorCode.NETWORK_ERROR;
      showError(errorCode.message);
      reconnect();
    } else if (error is TimeoutException) {
      final errorCode = ErrorCode.TIMEOUT;
      showError(errorCode.message);
    } else {
      final errorCode = ErrorCode.UNKNOWN_ERROR;
      showError(errorCode.message);
    }
  }
  
  void showError(String message) {
    // 显示错误提示
    ScaffoldMessenger.of(context).showSnackBar(
      SnackBar(content: Text(message)),
    );
  }
}
```

## 性能优化

### 1. 消息压缩

```dart
class GameClient {
  bool useCompression = true;
  
  Uint8List compressMessage(Uint8List data) {
    if (!useCompression) return data;
    
    // 使用gzip压缩
    return gzip.encode(data);
  }
  
  Uint8List decompressMessage(Uint8List data) {
    if (!useCompression) return data;
    
    // 使用gzip解压
    return gzip.decode(data);
  }
}
```

### 2. 消息批处理

```dart
class GameClient {
  final List<GameMessage> messageQueue = [];
  Timer? batchTimer;
  static const batchInterval = Duration(milliseconds: 50);
  
  void sendMessage(GameMessage message) {
    messageQueue.add(message);
    
    if (messageQueue.length >= 10) {
      sendBatch();
    } else {
      batchTimer?.cancel();
      batchTimer = Timer(batchInterval, () {
        sendBatch();
      });
    }
  }
  
  void sendBatch() {
    if (messageQueue.isEmpty) return;
    
    for (final message in messageQueue) {
      channel.sink.add(message.writeToBuffer());
    }
    
    messageQueue.clear();
  }
}
```

## 安全考虑

### 1. Token管理

```dart
class TokenManager {
  static const String _tokenKey = 'auth_token';
  
  static Future<void> saveToken(String token) async {
    final prefs = await SharedPreferences.getInstance();
    await prefs.setString(_tokenKey, token);
  }
  
  static Future<String?> getToken() async {
    final prefs = await SharedPreferences.getInstance();
    return prefs.getString(_tokenKey);
  }
  
  static Future<void> clearToken() async {
    final prefs = await SharedPreferences.getInstance();
    await prefs.remove(_tokenKey);
  }
  
  static bool isTokenValid(String? token) {
    return token != null && token.isNotEmpty;
  }
}
```

### 2. 数据加密

```dart
class SecurityManager {
  static String encrypt(String data, String key) {
    final bytes = utf8.encode(data);
    final keyBytes = utf8.encode(key);
    
    final encrypted = encrypt.AES(
      Key(keyBytes),
      mode: AESMode.ecb,
    );
    
    return encrypted.encrypt(bytes).base64;
  }
  
  static String decrypt(String encrypted, String key) {
    final bytes = base64.decode(encrypted);
    final keyBytes = utf8.encode(key);
    
    final decrypter = encrypt.AES(
      Key(keyBytes),
      mode: AESMode.ecb,
    );
    
    final decrypted = decrypter.decrypt(bytes);
    return utf8.decode(decrypted);
  }
}
```

## 测试工具

### 1. Mock服务器

```dart
class MockGameServer {
  final List<Function> handlers = [];
  
  void onMessage(String type, Function handler) {
    handlers.add((type, handler));
  }
  
  void handleMessage(String message) {
    final json = jsonDecode(message);
    final type = json['type'];
    
    for (final (handlerType, handler) in handlers) {
      if (handlerType == type) {
        handler(json);
        return;
      }
    }
  }
}
```

### 2. 测试客户端

```dart
class TestGameClient extends GameClient {
  final List<GameMessage> sentMessages = [];
  final List<GameMessage> receivedMessages = [];
  
  @override
  void send(GameMessage message) {
    sentMessages.add(message);
    super.send(message);
  }
  
  @override
  void handleMessage(dynamic message) {
    receivedMessages.add(message);
    super.handleMessage(message);
  }
  
  void clear() {
    sentMessages.clear();
    receivedMessages.clear();
  }
}
```

## API版本管理

### 版本号

```dart
class ApiVersion {
  static const String currentVersion = '1.0.0';
  static const String minCompatibleVersion = '1.0.0';
  static const String protocolVersion = '1.0';
  
  static bool isCompatible(String version) {
    final current = Version.parse(currentVersion);
    final min = Version.parse(minCompatibleVersion);
    final target = Version.parse(version);
    
    return target >= min && target <= current;
  }
}
```

### 版本协商

```dart
class GameClient {
  Future<void> negotiateVersion() async {
    final message = GameMessage()
      ..type = 'version_check'
      ..data = jsonEncode({
        'client_version': ApiVersion.currentVersion,
        'protocol_version': ApiVersion.protocolVersion,
      });
    
    channel.sink.add(message.writeToBuffer());
    
    final response = await waitForResponse('version_check');
    final serverVersion = response['server_version'];
    
    if (!ApiVersion.isCompatible(serverVersion)) {
      throw Exception('版本不兼容，请更新客户端');
    }
  }
}
```

---

*最后更新时间: 2026-02-06*
*文档维护者: AI Assistant*