import 'package:flutter/foundation.dart';
import '../network/game_service.dart';
import '../protobuf/protobuf/game_messages.pb.dart';

class AuthProvider with ChangeNotifier {
  final GameService _gameService = GameService();
  
  bool _isLoggedIn = false;
  String? _token;
  int64? _userId;
  String? _username;
  List<RoleInfo> _roles = [];
  RoleInfo? _selectedRole;
  String? _errorMessage;

  bool get isLoggedIn => _isLoggedIn;
  String? get token => _token;
  int64? get userId => _userId;
  String? get username => _username;
  List<RoleInfo> get roles => _roles;
  RoleInfo? get selectedRole => _selectedRole;
  String? get errorMessage => _errorMessage;
  bool get hasError => _errorMessage != null;

  Future<bool> login(String username, String password) async {
    try {
      _errorMessage = null;
      notifyListeners();

      final response = await _gameService.login(username, password);

      if (response.success) {
        _isLoggedIn = true;
        _token = response.token;
        _userId = response.userId;
        _username = username;
        _roles = response.roles;
        notifyListeners();
        return true;
      } else {
        _errorMessage = response.message.isNotEmpty 
            ? response.message 
            : '登录失败';
        notifyListeners();
        return false;
      }
    } catch (e) {
      _errorMessage = '网络错误: $e';
      notifyListeners();
      return false;
    }
  }

  Future<bool> logout() async {
    try {
      await _gameService.disconnect();
      _isLoggedIn = false;
      _token = null;
      _userId = null;
      _username = null;
      _roles = [];
      _selectedRole = null;
      _errorMessage = null;
      notifyListeners();
      return true;
    } catch (e) {
      _errorMessage = '登出失败: $e';
      notifyListeners();
      return false;
    }
  }

  void selectRole(RoleInfo role) {
    _selectedRole = role;
    notifyListeners();
  }

  void clearError() {
    _errorMessage = null;
    notifyListeners();
  }

  Future<bool> createRole(String roleName, int jobClass) async {
    try {
      _errorMessage = null;
      notifyListeners();

      final request = CreateRoleRequest()
        ..userId = _userId ?? Int64(0)
        ..roleName = roleName
        ..jobClass = jobClass;

      final response = await _gameService.createRole(request);

      if (response.success) {
        final newRole = RoleInfo()
          ..roleId = response.roleId
          ..roleName = roleName
          ..jobClass = jobClass
          ..level = 1;

        _roles.add(newRole);
        notifyListeners();
        return true;
      } else {
        _errorMessage = response.message.isNotEmpty 
            ? response.message 
            : '创建角色失败';
        notifyListeners();
        return false;
      }
    } catch (e) {
      _errorMessage = '网络错误: $e';
      notifyListeners();
      return false;
    }
  }

  Future<bool> deleteRole(int64 roleId) async {
    try {
      _errorMessage = null;
      notifyListeners();

      final request = DeleteRoleRequest()
        ..userId = _userId ?? Int64(0)
        ..roleId = roleId;

      final response = await _gameService.deleteRole(request);

      if (response.success) {
        _roles.removeWhere((role) => role.roleId == roleId);
        if (_selectedRole?.roleId == roleId) {
          _selectedRole = null;
        }
        notifyListeners();
        return true;
      } else {
        _errorMessage = response.message.isNotEmpty 
            ? response.message 
            : '删除角色失败';
        notifyListeners();
        return false;
      }
    } catch (e) {
      _errorMessage = '网络错误: $e';
      notifyListeners();
      return false;
    }
  }
}
