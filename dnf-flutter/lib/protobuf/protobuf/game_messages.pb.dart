// This is a generated file - do not edit.
//
// Generated from protobuf/game_messages.proto.

// @dart = 3.3

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names
// ignore_for_file: curly_braces_in_flow_control_structures
// ignore_for_file: deprecated_member_use_from_same_package, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_relative_imports

import 'dart:core' as $core;

import 'package:fixnum/fixnum.dart' as $fixnum;
import 'package:protobuf/protobuf.dart' as $pb;

export 'package:protobuf/protobuf.dart' show GeneratedMessageGenericExtensions;

class BaseMessage extends $pb.GeneratedMessage {
  factory BaseMessage({
    $core.String? msgId,
    $fixnum.Int64? timestamp,
    $core.int? msgType,
    $core.List<$core.int>? data,
  }) {
    final result = create();
    if (msgId != null) result.msgId = msgId;
    if (timestamp != null) result.timestamp = timestamp;
    if (msgType != null) result.msgType = msgType;
    if (data != null) result.data = data;
    return result;
  }

  BaseMessage._();

  factory BaseMessage.fromBuffer($core.List<$core.int> data,
          [$pb.ExtensionRegistry registry = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromBuffer(data, registry);
  factory BaseMessage.fromJson($core.String json,
          [$pb.ExtensionRegistry registry = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromJson(json, registry);

  static final $pb.BuilderInfo _i = $pb.BuilderInfo(
      _omitMessageNames ? '' : 'BaseMessage',
      package: const $pb.PackageName(_omitMessageNames ? '' : 'dnf'),
      createEmptyInstance: create)
    ..aOS(1, _omitFieldNames ? '' : 'msgId', protoName: 'msgId')
    ..aInt64(2, _omitFieldNames ? '' : 'timestamp')
    ..aI(3, _omitFieldNames ? '' : 'msgType', protoName: 'msgType')
    ..a<$core.List<$core.int>>(
        4, _omitFieldNames ? '' : 'data', $pb.PbFieldType.OY)
    ..hasRequiredFields = false;

  @$core.Deprecated('See https://github.com/google/protobuf.dart/issues/998.')
  BaseMessage clone() => deepCopy();
  @$core.Deprecated('See https://github.com/google/protobuf.dart/issues/998.')
  BaseMessage copyWith(void Function(BaseMessage) updates) =>
      super.copyWith((message) => updates(message as BaseMessage))
          as BaseMessage;

  @$core.override
  $pb.BuilderInfo get info_ => _i;

  @$core.pragma('dart2js:noInline')
  static BaseMessage create() => BaseMessage._();
  @$core.override
  BaseMessage createEmptyInstance() => create();
  @$core.pragma('dart2js:noInline')
  static BaseMessage getDefault() => _defaultInstance ??=
      $pb.GeneratedMessage.$_defaultFor<BaseMessage>(create);
  static BaseMessage? _defaultInstance;

  @$pb.TagNumber(1)
  $core.String get msgId => $_getSZ(0);
  @$pb.TagNumber(1)
  set msgId($core.String value) => $_setString(0, value);
  @$pb.TagNumber(1)
  $core.bool hasMsgId() => $_has(0);
  @$pb.TagNumber(1)
  void clearMsgId() => $_clearField(1);

  @$pb.TagNumber(2)
  $fixnum.Int64 get timestamp => $_getI64(1);
  @$pb.TagNumber(2)
  set timestamp($fixnum.Int64 value) => $_setInt64(1, value);
  @$pb.TagNumber(2)
  $core.bool hasTimestamp() => $_has(1);
  @$pb.TagNumber(2)
  void clearTimestamp() => $_clearField(2);

  @$pb.TagNumber(3)
  $core.int get msgType => $_getIZ(2);
  @$pb.TagNumber(3)
  set msgType($core.int value) => $_setSignedInt32(2, value);
  @$pb.TagNumber(3)
  $core.bool hasMsgType() => $_has(2);
  @$pb.TagNumber(3)
  void clearMsgType() => $_clearField(3);

  @$pb.TagNumber(4)
  $core.List<$core.int> get data => $_getN(3);
  @$pb.TagNumber(4)
  set data($core.List<$core.int> value) => $_setBytes(3, value);
  @$pb.TagNumber(4)
  $core.bool hasData() => $_has(3);
  @$pb.TagNumber(4)
  void clearData() => $_clearField(4);
}

class LoginRequest extends $pb.GeneratedMessage {
  factory LoginRequest({
    $core.String? username,
    $core.String? password,
    $core.String? deviceId,
  }) {
    final result = create();
    if (username != null) result.username = username;
    if (password != null) result.password = password;
    if (deviceId != null) result.deviceId = deviceId;
    return result;
  }

  LoginRequest._();

  factory LoginRequest.fromBuffer($core.List<$core.int> data,
          [$pb.ExtensionRegistry registry = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromBuffer(data, registry);
  factory LoginRequest.fromJson($core.String json,
          [$pb.ExtensionRegistry registry = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromJson(json, registry);

  static final $pb.BuilderInfo _i = $pb.BuilderInfo(
      _omitMessageNames ? '' : 'LoginRequest',
      package: const $pb.PackageName(_omitMessageNames ? '' : 'dnf'),
      createEmptyInstance: create)
    ..aOS(1, _omitFieldNames ? '' : 'username')
    ..aOS(2, _omitFieldNames ? '' : 'password')
    ..aOS(3, _omitFieldNames ? '' : 'deviceId', protoName: 'deviceId')
    ..hasRequiredFields = false;

  @$core.Deprecated('See https://github.com/google/protobuf.dart/issues/998.')
  LoginRequest clone() => deepCopy();
  @$core.Deprecated('See https://github.com/google/protobuf.dart/issues/998.')
  LoginRequest copyWith(void Function(LoginRequest) updates) =>
      super.copyWith((message) => updates(message as LoginRequest))
          as LoginRequest;

  @$core.override
  $pb.BuilderInfo get info_ => _i;

  @$core.pragma('dart2js:noInline')
  static LoginRequest create() => LoginRequest._();
  @$core.override
  LoginRequest createEmptyInstance() => create();
  @$core.pragma('dart2js:noInline')
  static LoginRequest getDefault() => _defaultInstance ??=
      $pb.GeneratedMessage.$_defaultFor<LoginRequest>(create);
  static LoginRequest? _defaultInstance;

  @$pb.TagNumber(1)
  $core.String get username => $_getSZ(0);
  @$pb.TagNumber(1)
  set username($core.String value) => $_setString(0, value);
  @$pb.TagNumber(1)
  $core.bool hasUsername() => $_has(0);
  @$pb.TagNumber(1)
  void clearUsername() => $_clearField(1);

  @$pb.TagNumber(2)
  $core.String get password => $_getSZ(1);
  @$pb.TagNumber(2)
  set password($core.String value) => $_setString(1, value);
  @$pb.TagNumber(2)
  $core.bool hasPassword() => $_has(1);
  @$pb.TagNumber(2)
  void clearPassword() => $_clearField(2);

  @$pb.TagNumber(3)
  $core.String get deviceId => $_getSZ(2);
  @$pb.TagNumber(3)
  set deviceId($core.String value) => $_setString(2, value);
  @$pb.TagNumber(3)
  $core.bool hasDeviceId() => $_has(2);
  @$pb.TagNumber(3)
  void clearDeviceId() => $_clearField(3);
}

class LoginResponse extends $pb.GeneratedMessage {
  factory LoginResponse({
    $core.bool? success,
    $core.String? message,
    $core.String? token,
    $fixnum.Int64? userId,
    $core.Iterable<RoleInfo>? roles,
  }) {
    final result = create();
    if (success != null) result.success = success;
    if (message != null) result.message = message;
    if (token != null) result.token = token;
    if (userId != null) result.userId = userId;
    if (roles != null) result.roles.addAll(roles);
    return result;
  }

  LoginResponse._();

  factory LoginResponse.fromBuffer($core.List<$core.int> data,
          [$pb.ExtensionRegistry registry = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromBuffer(data, registry);
  factory LoginResponse.fromJson($core.String json,
          [$pb.ExtensionRegistry registry = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromJson(json, registry);

  static final $pb.BuilderInfo _i = $pb.BuilderInfo(
      _omitMessageNames ? '' : 'LoginResponse',
      package: const $pb.PackageName(_omitMessageNames ? '' : 'dnf'),
      createEmptyInstance: create)
    ..aOB(1, _omitFieldNames ? '' : 'success')
    ..aOS(2, _omitFieldNames ? '' : 'message')
    ..aOS(3, _omitFieldNames ? '' : 'token')
    ..aInt64(4, _omitFieldNames ? '' : 'userId', protoName: 'userId')
    ..pPM<RoleInfo>(5, _omitFieldNames ? '' : 'roles',
        subBuilder: RoleInfo.create)
    ..hasRequiredFields = false;

  @$core.Deprecated('See https://github.com/google/protobuf.dart/issues/998.')
  LoginResponse clone() => deepCopy();
  @$core.Deprecated('See https://github.com/google/protobuf.dart/issues/998.')
  LoginResponse copyWith(void Function(LoginResponse) updates) =>
      super.copyWith((message) => updates(message as LoginResponse))
          as LoginResponse;

  @$core.override
  $pb.BuilderInfo get info_ => _i;

  @$core.pragma('dart2js:noInline')
  static LoginResponse create() => LoginResponse._();
  @$core.override
  LoginResponse createEmptyInstance() => create();
  @$core.pragma('dart2js:noInline')
  static LoginResponse getDefault() => _defaultInstance ??=
      $pb.GeneratedMessage.$_defaultFor<LoginResponse>(create);
  static LoginResponse? _defaultInstance;

  @$pb.TagNumber(1)
  $core.bool get success => $_getBF(0);
  @$pb.TagNumber(1)
  set success($core.bool value) => $_setBool(0, value);
  @$pb.TagNumber(1)
  $core.bool hasSuccess() => $_has(0);
  @$pb.TagNumber(1)
  void clearSuccess() => $_clearField(1);

  @$pb.TagNumber(2)
  $core.String get message => $_getSZ(1);
  @$pb.TagNumber(2)
  set message($core.String value) => $_setString(1, value);
  @$pb.TagNumber(2)
  $core.bool hasMessage() => $_has(1);
  @$pb.TagNumber(2)
  void clearMessage() => $_clearField(2);

  @$pb.TagNumber(3)
  $core.String get token => $_getSZ(2);
  @$pb.TagNumber(3)
  set token($core.String value) => $_setString(2, value);
  @$pb.TagNumber(3)
  $core.bool hasToken() => $_has(2);
  @$pb.TagNumber(3)
  void clearToken() => $_clearField(3);

  @$pb.TagNumber(4)
  $fixnum.Int64 get userId => $_getI64(3);
  @$pb.TagNumber(4)
  set userId($fixnum.Int64 value) => $_setInt64(3, value);
  @$pb.TagNumber(4)
  $core.bool hasUserId() => $_has(3);
  @$pb.TagNumber(4)
  void clearUserId() => $_clearField(4);

  @$pb.TagNumber(5)
  $pb.PbList<RoleInfo> get roles => $_getList(4);
}

class RoleInfo extends $pb.GeneratedMessage {
  factory RoleInfo({
    $fixnum.Int64? uid,
    $core.String? name,
    $core.int? level,
    $core.int? job,
    $fixnum.Int64? exp,
    $fixnum.Int64? gold,
  }) {
    final result = create();
    if (uid != null) result.uid = uid;
    if (name != null) result.name = name;
    if (level != null) result.level = level;
    if (job != null) result.job = job;
    if (exp != null) result.exp = exp;
    if (gold != null) result.gold = gold;
    return result;
  }

  RoleInfo._();

  factory RoleInfo.fromBuffer($core.List<$core.int> data,
          [$pb.ExtensionRegistry registry = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromBuffer(data, registry);
  factory RoleInfo.fromJson($core.String json,
          [$pb.ExtensionRegistry registry = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromJson(json, registry);

  static final $pb.BuilderInfo _i = $pb.BuilderInfo(
      _omitMessageNames ? '' : 'RoleInfo',
      package: const $pb.PackageName(_omitMessageNames ? '' : 'dnf'),
      createEmptyInstance: create)
    ..aInt64(1, _omitFieldNames ? '' : 'uid')
    ..aOS(2, _omitFieldNames ? '' : 'name')
    ..aI(3, _omitFieldNames ? '' : 'level')
    ..aI(4, _omitFieldNames ? '' : 'job')
    ..aInt64(5, _omitFieldNames ? '' : 'exp')
    ..aInt64(6, _omitFieldNames ? '' : 'gold')
    ..hasRequiredFields = false;

  @$core.Deprecated('See https://github.com/google/protobuf.dart/issues/998.')
  RoleInfo clone() => deepCopy();
  @$core.Deprecated('See https://github.com/google/protobuf.dart/issues/998.')
  RoleInfo copyWith(void Function(RoleInfo) updates) =>
      super.copyWith((message) => updates(message as RoleInfo)) as RoleInfo;

  @$core.override
  $pb.BuilderInfo get info_ => _i;

  @$core.pragma('dart2js:noInline')
  static RoleInfo create() => RoleInfo._();
  @$core.override
  RoleInfo createEmptyInstance() => create();
  @$core.pragma('dart2js:noInline')
  static RoleInfo getDefault() =>
      _defaultInstance ??= $pb.GeneratedMessage.$_defaultFor<RoleInfo>(create);
  static RoleInfo? _defaultInstance;

  @$pb.TagNumber(1)
  $fixnum.Int64 get uid => $_getI64(0);
  @$pb.TagNumber(1)
  set uid($fixnum.Int64 value) => $_setInt64(0, value);
  @$pb.TagNumber(1)
  $core.bool hasUid() => $_has(0);
  @$pb.TagNumber(1)
  void clearUid() => $_clearField(1);

  @$pb.TagNumber(2)
  $core.String get name => $_getSZ(1);
  @$pb.TagNumber(2)
  set name($core.String value) => $_setString(1, value);
  @$pb.TagNumber(2)
  $core.bool hasName() => $_has(1);
  @$pb.TagNumber(2)
  void clearName() => $_clearField(2);

  @$pb.TagNumber(3)
  $core.int get level => $_getIZ(2);
  @$pb.TagNumber(3)
  set level($core.int value) => $_setSignedInt32(2, value);
  @$pb.TagNumber(3)
  $core.bool hasLevel() => $_has(2);
  @$pb.TagNumber(3)
  void clearLevel() => $_clearField(3);

  @$pb.TagNumber(4)
  $core.int get job => $_getIZ(3);
  @$pb.TagNumber(4)
  set job($core.int value) => $_setSignedInt32(3, value);
  @$pb.TagNumber(4)
  $core.bool hasJob() => $_has(3);
  @$pb.TagNumber(4)
  void clearJob() => $_clearField(4);

  @$pb.TagNumber(5)
  $fixnum.Int64 get exp => $_getI64(4);
  @$pb.TagNumber(5)
  set exp($fixnum.Int64 value) => $_setInt64(4, value);
  @$pb.TagNumber(5)
  $core.bool hasExp() => $_has(4);
  @$pb.TagNumber(5)
  void clearExp() => $_clearField(5);

  @$pb.TagNumber(6)
  $fixnum.Int64 get gold => $_getI64(5);
  @$pb.TagNumber(6)
  set gold($fixnum.Int64 value) => $_setInt64(5, value);
  @$pb.TagNumber(6)
  $core.bool hasGold() => $_has(5);
  @$pb.TagNumber(6)
  void clearGold() => $_clearField(6);
}

class CreateRoleRequest extends $pb.GeneratedMessage {
  factory CreateRoleRequest({
    $fixnum.Int64? userId,
    $core.String? roleName,
    $core.int? jobClass,
  }) {
    final result = create();
    if (userId != null) result.userId = userId;
    if (roleName != null) result.roleName = roleName;
    if (jobClass != null) result.jobClass = jobClass;
    return result;
  }

  CreateRoleRequest._();

  factory CreateRoleRequest.fromBuffer($core.List<$core.int> data,
          [$pb.ExtensionRegistry registry = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromBuffer(data, registry);
  factory CreateRoleRequest.fromJson($core.String json,
          [$pb.ExtensionRegistry registry = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromJson(json, registry);

  static final $pb.BuilderInfo _i = $pb.BuilderInfo(
      _omitMessageNames ? '' : 'CreateRoleRequest',
      package: const $pb.PackageName(_omitMessageNames ? '' : 'dnf'),
      createEmptyInstance: create)
    ..aInt64(1, _omitFieldNames ? '' : 'userId', protoName: 'userId')
    ..aOS(2, _omitFieldNames ? '' : 'roleName', protoName: 'roleName')
    ..aI(3, _omitFieldNames ? '' : 'jobClass', protoName: 'jobClass')
    ..hasRequiredFields = false;

  @$core.Deprecated('See https://github.com/google/protobuf.dart/issues/998.')
  CreateRoleRequest clone() => deepCopy();
  @$core.Deprecated('See https://github.com/google/protobuf.dart/issues/998.')
  CreateRoleRequest copyWith(void Function(CreateRoleRequest) updates) =>
      super.copyWith((message) => updates(message as CreateRoleRequest))
          as CreateRoleRequest;

  @$core.override
  $pb.BuilderInfo get info_ => _i;

  @$core.pragma('dart2js:noInline')
  static CreateRoleRequest create() => CreateRoleRequest._();
  @$core.override
  CreateRoleRequest createEmptyInstance() => create();
  @$core.pragma('dart2js:noInline')
  static CreateRoleRequest getDefault() => _defaultInstance ??=
      $pb.GeneratedMessage.$_defaultFor<CreateRoleRequest>(create);
  static CreateRoleRequest? _defaultInstance;

  @$pb.TagNumber(1)
  $fixnum.Int64 get userId => $_getI64(0);
  @$pb.TagNumber(1)
  set userId($fixnum.Int64 value) => $_setInt64(0, value);
  @$pb.TagNumber(1)
  $core.bool hasUserId() => $_has(0);
  @$pb.TagNumber(1)
  void clearUserId() => $_clearField(1);

  @$pb.TagNumber(2)
  $core.String get roleName => $_getSZ(1);
  @$pb.TagNumber(2)
  set roleName($core.String value) => $_setString(1, value);
  @$pb.TagNumber(2)
  $core.bool hasRoleName() => $_has(1);
  @$pb.TagNumber(2)
  void clearRoleName() => $_clearField(2);

  @$pb.TagNumber(3)
  $core.int get jobClass => $_getIZ(2);
  @$pb.TagNumber(3)
  set jobClass($core.int value) => $_setSignedInt32(2, value);
  @$pb.TagNumber(3)
  $core.bool hasJobClass() => $_has(2);
  @$pb.TagNumber(3)
  void clearJobClass() => $_clearField(3);
}

class CreateRoleResponse extends $pb.GeneratedMessage {
  factory CreateRoleResponse({
    $core.bool? success,
    $core.String? message,
    $fixnum.Int64? roleId,
  }) {
    final result = create();
    if (success != null) result.success = success;
    if (message != null) result.message = message;
    if (roleId != null) result.roleId = roleId;
    return result;
  }

  CreateRoleResponse._();

  factory CreateRoleResponse.fromBuffer($core.List<$core.int> data,
          [$pb.ExtensionRegistry registry = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromBuffer(data, registry);
  factory CreateRoleResponse.fromJson($core.String json,
          [$pb.ExtensionRegistry registry = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromJson(json, registry);

  static final $pb.BuilderInfo _i = $pb.BuilderInfo(
      _omitMessageNames ? '' : 'CreateRoleResponse',
      package: const $pb.PackageName(_omitMessageNames ? '' : 'dnf'),
      createEmptyInstance: create)
    ..aOB(1, _omitFieldNames ? '' : 'success')
    ..aOS(2, _omitFieldNames ? '' : 'message')
    ..aInt64(3, _omitFieldNames ? '' : 'roleId', protoName: 'roleId')
    ..hasRequiredFields = false;

  @$core.Deprecated('See https://github.com/google/protobuf.dart/issues/998.')
  CreateRoleResponse clone() => deepCopy();
  @$core.Deprecated('See https://github.com/google/protobuf.dart/issues/998.')
  CreateRoleResponse copyWith(void Function(CreateRoleResponse) updates) =>
      super.copyWith((message) => updates(message as CreateRoleResponse))
          as CreateRoleResponse;

  @$core.override
  $pb.BuilderInfo get info_ => _i;

  @$core.pragma('dart2js:noInline')
  static CreateRoleResponse create() => CreateRoleResponse._();
  @$core.override
  CreateRoleResponse createEmptyInstance() => create();
  @$core.pragma('dart2js:noInline')
  static CreateRoleResponse getDefault() => _defaultInstance ??=
      $pb.GeneratedMessage.$_defaultFor<CreateRoleResponse>(create);
  static CreateRoleResponse? _defaultInstance;

  @$pb.TagNumber(1)
  $core.bool get success => $_getBF(0);
  @$pb.TagNumber(1)
  set success($core.bool value) => $_setBool(0, value);
  @$pb.TagNumber(1)
  $core.bool hasSuccess() => $_has(0);
  @$pb.TagNumber(1)
  void clearSuccess() => $_clearField(1);

  @$pb.TagNumber(2)
  $core.String get message => $_getSZ(1);
  @$pb.TagNumber(2)
  set message($core.String value) => $_setString(1, value);
  @$pb.TagNumber(2)
  $core.bool hasMessage() => $_has(1);
  @$pb.TagNumber(2)
  void clearMessage() => $_clearField(2);

  @$pb.TagNumber(3)
  $fixnum.Int64 get roleId => $_getI64(2);
  @$pb.TagNumber(3)
  set roleId($fixnum.Int64 value) => $_setInt64(2, value);
  @$pb.TagNumber(3)
  $core.bool hasRoleId() => $_has(2);
  @$pb.TagNumber(3)
  void clearRoleId() => $_clearField(3);
}

class DeleteRoleRequest extends $pb.GeneratedMessage {
  factory DeleteRoleRequest({
    $fixnum.Int64? userId,
    $fixnum.Int64? roleId,
  }) {
    final result = create();
    if (userId != null) result.userId = userId;
    if (roleId != null) result.roleId = roleId;
    return result;
  }

  DeleteRoleRequest._();

  factory DeleteRoleRequest.fromBuffer($core.List<$core.int> data,
          [$pb.ExtensionRegistry registry = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromBuffer(data, registry);
  factory DeleteRoleRequest.fromJson($core.String json,
          [$pb.ExtensionRegistry registry = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromJson(json, registry);

  static final $pb.BuilderInfo _i = $pb.BuilderInfo(
      _omitMessageNames ? '' : 'DeleteRoleRequest',
      package: const $pb.PackageName(_omitMessageNames ? '' : 'dnf'),
      createEmptyInstance: create)
    ..aInt64(1, _omitFieldNames ? '' : 'userId', protoName: 'userId')
    ..aInt64(2, _omitFieldNames ? '' : 'roleId', protoName: 'roleId')
    ..hasRequiredFields = false;

  @$core.Deprecated('See https://github.com/google/protobuf.dart/issues/998.')
  DeleteRoleRequest clone() => deepCopy();
  @$core.Deprecated('See https://github.com/google/protobuf.dart/issues/998.')
  DeleteRoleRequest copyWith(void Function(DeleteRoleRequest) updates) =>
      super.copyWith((message) => updates(message as DeleteRoleRequest))
          as DeleteRoleRequest;

  @$core.override
  $pb.BuilderInfo get info_ => _i;

  @$core.pragma('dart2js:noInline')
  static DeleteRoleRequest create() => DeleteRoleRequest._();
  @$core.override
  DeleteRoleRequest createEmptyInstance() => create();
  @$core.pragma('dart2js:noInline')
  static DeleteRoleRequest getDefault() => _defaultInstance ??=
      $pb.GeneratedMessage.$_defaultFor<DeleteRoleRequest>(create);
  static DeleteRoleRequest? _defaultInstance;

  @$pb.TagNumber(1)
  $fixnum.Int64 get userId => $_getI64(0);
  @$pb.TagNumber(1)
  set userId($fixnum.Int64 value) => $_setInt64(0, value);
  @$pb.TagNumber(1)
  $core.bool hasUserId() => $_has(0);
  @$pb.TagNumber(1)
  void clearUserId() => $_clearField(1);

  @$pb.TagNumber(2)
  $fixnum.Int64 get roleId => $_getI64(1);
  @$pb.TagNumber(2)
  set roleId($fixnum.Int64 value) => $_setInt64(1, value);
  @$pb.TagNumber(2)
  $core.bool hasRoleId() => $_has(1);
  @$pb.TagNumber(2)
  void clearRoleId() => $_clearField(2);
}

class DeleteRoleResponse extends $pb.GeneratedMessage {
  factory DeleteRoleResponse({
    $core.bool? success,
    $core.String? message,
  }) {
    final result = create();
    if (success != null) result.success = success;
    if (message != null) result.message = message;
    return result;
  }

  DeleteRoleResponse._();

  factory DeleteRoleResponse.fromBuffer($core.List<$core.int> data,
          [$pb.ExtensionRegistry registry = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromBuffer(data, registry);
  factory DeleteRoleResponse.fromJson($core.String json,
          [$pb.ExtensionRegistry registry = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromJson(json, registry);

  static final $pb.BuilderInfo _i = $pb.BuilderInfo(
      _omitMessageNames ? '' : 'DeleteRoleResponse',
      package: const $pb.PackageName(_omitMessageNames ? '' : 'dnf'),
      createEmptyInstance: create)
    ..aOB(1, _omitFieldNames ? '' : 'success')
    ..aOS(2, _omitFieldNames ? '' : 'message')
    ..hasRequiredFields = false;

  @$core.Deprecated('See https://github.com/google/protobuf.dart/issues/998.')
  DeleteRoleResponse clone() => deepCopy();
  @$core.Deprecated('See https://github.com/google/protobuf.dart/issues/998.')
  DeleteRoleResponse copyWith(void Function(DeleteRoleResponse) updates) =>
      super.copyWith((message) => updates(message as DeleteRoleResponse))
          as DeleteRoleResponse;

  @$core.override
  $pb.BuilderInfo get info_ => _i;

  @$core.pragma('dart2js:noInline')
  static DeleteRoleResponse create() => DeleteRoleResponse._();
  @$core.override
  DeleteRoleResponse createEmptyInstance() => create();
  @$core.pragma('dart2js:noInline')
  static DeleteRoleResponse getDefault() => _defaultInstance ??=
      $pb.GeneratedMessage.$_defaultFor<DeleteRoleResponse>(create);
  static DeleteRoleResponse? _defaultInstance;

  @$pb.TagNumber(1)
  $core.bool get success => $_getBF(0);
  @$pb.TagNumber(1)
  set success($core.bool value) => $_setBool(0, value);
  @$pb.TagNumber(1)
  $core.bool hasSuccess() => $_has(0);
  @$pb.TagNumber(1)
  void clearSuccess() => $_clearField(1);

  @$pb.TagNumber(2)
  $core.String get message => $_getSZ(1);
  @$pb.TagNumber(2)
  set message($core.String value) => $_setString(1, value);
  @$pb.TagNumber(2)
  $core.bool hasMessage() => $_has(1);
  @$pb.TagNumber(2)
  void clearMessage() => $_clearField(2);
}

class MoveRequest extends $pb.GeneratedMessage {
  factory MoveRequest({
    $fixnum.Int64? uid,
    $core.double? x,
    $core.double? y,
    $core.int? direction,
  }) {
    final result = create();
    if (uid != null) result.uid = uid;
    if (x != null) result.x = x;
    if (y != null) result.y = y;
    if (direction != null) result.direction = direction;
    return result;
  }

  MoveRequest._();

  factory MoveRequest.fromBuffer($core.List<$core.int> data,
          [$pb.ExtensionRegistry registry = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromBuffer(data, registry);
  factory MoveRequest.fromJson($core.String json,
          [$pb.ExtensionRegistry registry = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromJson(json, registry);

  static final $pb.BuilderInfo _i = $pb.BuilderInfo(
      _omitMessageNames ? '' : 'MoveRequest',
      package: const $pb.PackageName(_omitMessageNames ? '' : 'dnf'),
      createEmptyInstance: create)
    ..aInt64(1, _omitFieldNames ? '' : 'uid')
    ..aD(2, _omitFieldNames ? '' : 'x')
    ..aD(3, _omitFieldNames ? '' : 'y')
    ..aI(4, _omitFieldNames ? '' : 'direction')
    ..hasRequiredFields = false;

  @$core.Deprecated('See https://github.com/google/protobuf.dart/issues/998.')
  MoveRequest clone() => deepCopy();
  @$core.Deprecated('See https://github.com/google/protobuf.dart/issues/998.')
  MoveRequest copyWith(void Function(MoveRequest) updates) =>
      super.copyWith((message) => updates(message as MoveRequest))
          as MoveRequest;

  @$core.override
  $pb.BuilderInfo get info_ => _i;

  @$core.pragma('dart2js:noInline')
  static MoveRequest create() => MoveRequest._();
  @$core.override
  MoveRequest createEmptyInstance() => create();
  @$core.pragma('dart2js:noInline')
  static MoveRequest getDefault() => _defaultInstance ??=
      $pb.GeneratedMessage.$_defaultFor<MoveRequest>(create);
  static MoveRequest? _defaultInstance;

  @$pb.TagNumber(1)
  $fixnum.Int64 get uid => $_getI64(0);
  @$pb.TagNumber(1)
  set uid($fixnum.Int64 value) => $_setInt64(0, value);
  @$pb.TagNumber(1)
  $core.bool hasUid() => $_has(0);
  @$pb.TagNumber(1)
  void clearUid() => $_clearField(1);

  @$pb.TagNumber(2)
  $core.double get x => $_getN(1);
  @$pb.TagNumber(2)
  set x($core.double value) => $_setDouble(1, value);
  @$pb.TagNumber(2)
  $core.bool hasX() => $_has(1);
  @$pb.TagNumber(2)
  void clearX() => $_clearField(2);

  @$pb.TagNumber(3)
  $core.double get y => $_getN(2);
  @$pb.TagNumber(3)
  set y($core.double value) => $_setDouble(2, value);
  @$pb.TagNumber(3)
  $core.bool hasY() => $_has(2);
  @$pb.TagNumber(3)
  void clearY() => $_clearField(3);

  @$pb.TagNumber(4)
  $core.int get direction => $_getIZ(3);
  @$pb.TagNumber(4)
  set direction($core.int value) => $_setSignedInt32(3, value);
  @$pb.TagNumber(4)
  $core.bool hasDirection() => $_has(3);
  @$pb.TagNumber(4)
  void clearDirection() => $_clearField(4);
}

class MoveResponse extends $pb.GeneratedMessage {
  factory MoveResponse({
    $core.bool? success,
    $core.String? message,
    $fixnum.Int64? uid,
    $core.double? x,
    $core.double? y,
    $core.int? direction,
  }) {
    final result = create();
    if (success != null) result.success = success;
    if (message != null) result.message = message;
    if (uid != null) result.uid = uid;
    if (x != null) result.x = x;
    if (y != null) result.y = y;
    if (direction != null) result.direction = direction;
    return result;
  }

  MoveResponse._();

  factory MoveResponse.fromBuffer($core.List<$core.int> data,
          [$pb.ExtensionRegistry registry = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromBuffer(data, registry);
  factory MoveResponse.fromJson($core.String json,
          [$pb.ExtensionRegistry registry = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromJson(json, registry);

  static final $pb.BuilderInfo _i = $pb.BuilderInfo(
      _omitMessageNames ? '' : 'MoveResponse',
      package: const $pb.PackageName(_omitMessageNames ? '' : 'dnf'),
      createEmptyInstance: create)
    ..aOB(1, _omitFieldNames ? '' : 'success')
    ..aOS(2, _omitFieldNames ? '' : 'message')
    ..aInt64(3, _omitFieldNames ? '' : 'uid')
    ..aD(4, _omitFieldNames ? '' : 'x')
    ..aD(5, _omitFieldNames ? '' : 'y')
    ..aI(6, _omitFieldNames ? '' : 'direction')
    ..hasRequiredFields = false;

  @$core.Deprecated('See https://github.com/google/protobuf.dart/issues/998.')
  MoveResponse clone() => deepCopy();
  @$core.Deprecated('See https://github.com/google/protobuf.dart/issues/998.')
  MoveResponse copyWith(void Function(MoveResponse) updates) =>
      super.copyWith((message) => updates(message as MoveResponse))
          as MoveResponse;

  @$core.override
  $pb.BuilderInfo get info_ => _i;

  @$core.pragma('dart2js:noInline')
  static MoveResponse create() => MoveResponse._();
  @$core.override
  MoveResponse createEmptyInstance() => create();
  @$core.pragma('dart2js:noInline')
  static MoveResponse getDefault() => _defaultInstance ??=
      $pb.GeneratedMessage.$_defaultFor<MoveResponse>(create);
  static MoveResponse? _defaultInstance;

  @$pb.TagNumber(1)
  $core.bool get success => $_getBF(0);
  @$pb.TagNumber(1)
  set success($core.bool value) => $_setBool(0, value);
  @$pb.TagNumber(1)
  $core.bool hasSuccess() => $_has(0);
  @$pb.TagNumber(1)
  void clearSuccess() => $_clearField(1);

  @$pb.TagNumber(2)
  $core.String get message => $_getSZ(1);
  @$pb.TagNumber(2)
  set message($core.String value) => $_setString(1, value);
  @$pb.TagNumber(2)
  $core.bool hasMessage() => $_has(1);
  @$pb.TagNumber(2)
  void clearMessage() => $_clearField(2);

  @$pb.TagNumber(3)
  $fixnum.Int64 get uid => $_getI64(2);
  @$pb.TagNumber(3)
  set uid($fixnum.Int64 value) => $_setInt64(2, value);
  @$pb.TagNumber(3)
  $core.bool hasUid() => $_has(2);
  @$pb.TagNumber(3)
  void clearUid() => $_clearField(3);

  @$pb.TagNumber(4)
  $core.double get x => $_getN(3);
  @$pb.TagNumber(4)
  set x($core.double value) => $_setDouble(3, value);
  @$pb.TagNumber(4)
  $core.bool hasX() => $_has(3);
  @$pb.TagNumber(4)
  void clearX() => $_clearField(4);

  @$pb.TagNumber(5)
  $core.double get y => $_getN(4);
  @$pb.TagNumber(5)
  set y($core.double value) => $_setDouble(4, value);
  @$pb.TagNumber(5)
  $core.bool hasY() => $_has(4);
  @$pb.TagNumber(5)
  void clearY() => $_clearField(5);

  @$pb.TagNumber(6)
  $core.int get direction => $_getIZ(5);
  @$pb.TagNumber(6)
  set direction($core.int value) => $_setSignedInt32(5, value);
  @$pb.TagNumber(6)
  $core.bool hasDirection() => $_has(5);
  @$pb.TagNumber(6)
  void clearDirection() => $_clearField(6);
}

class AttackRequest extends $pb.GeneratedMessage {
  factory AttackRequest({
    $fixnum.Int64? uid,
    $core.int? skillId,
    $core.int? direction,
  }) {
    final result = create();
    if (uid != null) result.uid = uid;
    if (skillId != null) result.skillId = skillId;
    if (direction != null) result.direction = direction;
    return result;
  }

  AttackRequest._();

  factory AttackRequest.fromBuffer($core.List<$core.int> data,
          [$pb.ExtensionRegistry registry = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromBuffer(data, registry);
  factory AttackRequest.fromJson($core.String json,
          [$pb.ExtensionRegistry registry = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromJson(json, registry);

  static final $pb.BuilderInfo _i = $pb.BuilderInfo(
      _omitMessageNames ? '' : 'AttackRequest',
      package: const $pb.PackageName(_omitMessageNames ? '' : 'dnf'),
      createEmptyInstance: create)
    ..aInt64(1, _omitFieldNames ? '' : 'uid')
    ..aI(2, _omitFieldNames ? '' : 'skillId', protoName: 'skillId')
    ..aI(3, _omitFieldNames ? '' : 'direction')
    ..hasRequiredFields = false;

  @$core.Deprecated('See https://github.com/google/protobuf.dart/issues/998.')
  AttackRequest clone() => deepCopy();
  @$core.Deprecated('See https://github.com/google/protobuf.dart/issues/998.')
  AttackRequest copyWith(void Function(AttackRequest) updates) =>
      super.copyWith((message) => updates(message as AttackRequest))
          as AttackRequest;

  @$core.override
  $pb.BuilderInfo get info_ => _i;

  @$core.pragma('dart2js:noInline')
  static AttackRequest create() => AttackRequest._();
  @$core.override
  AttackRequest createEmptyInstance() => create();
  @$core.pragma('dart2js:noInline')
  static AttackRequest getDefault() => _defaultInstance ??=
      $pb.GeneratedMessage.$_defaultFor<AttackRequest>(create);
  static AttackRequest? _defaultInstance;

  @$pb.TagNumber(1)
  $fixnum.Int64 get uid => $_getI64(0);
  @$pb.TagNumber(1)
  set uid($fixnum.Int64 value) => $_setInt64(0, value);
  @$pb.TagNumber(1)
  $core.bool hasUid() => $_has(0);
  @$pb.TagNumber(1)
  void clearUid() => $_clearField(1);

  @$pb.TagNumber(2)
  $core.int get skillId => $_getIZ(1);
  @$pb.TagNumber(2)
  set skillId($core.int value) => $_setSignedInt32(1, value);
  @$pb.TagNumber(2)
  $core.bool hasSkillId() => $_has(1);
  @$pb.TagNumber(2)
  void clearSkillId() => $_clearField(2);

  @$pb.TagNumber(3)
  $core.int get direction => $_getIZ(2);
  @$pb.TagNumber(3)
  set direction($core.int value) => $_setSignedInt32(2, value);
  @$pb.TagNumber(3)
  $core.bool hasDirection() => $_has(2);
  @$pb.TagNumber(3)
  void clearDirection() => $_clearField(3);
}

class AttackResponse extends $pb.GeneratedMessage {
  factory AttackResponse({
    $core.bool? success,
    $core.String? message,
    $fixnum.Int64? attackerUid,
    $fixnum.Int64? targetUid,
    $core.int? damage,
    $core.bool? isCritical,
  }) {
    final result = create();
    if (success != null) result.success = success;
    if (message != null) result.message = message;
    if (attackerUid != null) result.attackerUid = attackerUid;
    if (targetUid != null) result.targetUid = targetUid;
    if (damage != null) result.damage = damage;
    if (isCritical != null) result.isCritical = isCritical;
    return result;
  }

  AttackResponse._();

  factory AttackResponse.fromBuffer($core.List<$core.int> data,
          [$pb.ExtensionRegistry registry = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromBuffer(data, registry);
  factory AttackResponse.fromJson($core.String json,
          [$pb.ExtensionRegistry registry = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromJson(json, registry);

  static final $pb.BuilderInfo _i = $pb.BuilderInfo(
      _omitMessageNames ? '' : 'AttackResponse',
      package: const $pb.PackageName(_omitMessageNames ? '' : 'dnf'),
      createEmptyInstance: create)
    ..aOB(1, _omitFieldNames ? '' : 'success')
    ..aOS(2, _omitFieldNames ? '' : 'message')
    ..aInt64(3, _omitFieldNames ? '' : 'attackerUid', protoName: 'attackerUid')
    ..aInt64(4, _omitFieldNames ? '' : 'targetUid', protoName: 'targetUid')
    ..aI(5, _omitFieldNames ? '' : 'damage')
    ..aOB(6, _omitFieldNames ? '' : 'isCritical', protoName: 'isCritical')
    ..hasRequiredFields = false;

  @$core.Deprecated('See https://github.com/google/protobuf.dart/issues/998.')
  AttackResponse clone() => deepCopy();
  @$core.Deprecated('See https://github.com/google/protobuf.dart/issues/998.')
  AttackResponse copyWith(void Function(AttackResponse) updates) =>
      super.copyWith((message) => updates(message as AttackResponse))
          as AttackResponse;

  @$core.override
  $pb.BuilderInfo get info_ => _i;

  @$core.pragma('dart2js:noInline')
  static AttackResponse create() => AttackResponse._();
  @$core.override
  AttackResponse createEmptyInstance() => create();
  @$core.pragma('dart2js:noInline')
  static AttackResponse getDefault() => _defaultInstance ??=
      $pb.GeneratedMessage.$_defaultFor<AttackResponse>(create);
  static AttackResponse? _defaultInstance;

  @$pb.TagNumber(1)
  $core.bool get success => $_getBF(0);
  @$pb.TagNumber(1)
  set success($core.bool value) => $_setBool(0, value);
  @$pb.TagNumber(1)
  $core.bool hasSuccess() => $_has(0);
  @$pb.TagNumber(1)
  void clearSuccess() => $_clearField(1);

  @$pb.TagNumber(2)
  $core.String get message => $_getSZ(1);
  @$pb.TagNumber(2)
  set message($core.String value) => $_setString(1, value);
  @$pb.TagNumber(2)
  $core.bool hasMessage() => $_has(1);
  @$pb.TagNumber(2)
  void clearMessage() => $_clearField(2);

  @$pb.TagNumber(3)
  $fixnum.Int64 get attackerUid => $_getI64(2);
  @$pb.TagNumber(3)
  set attackerUid($fixnum.Int64 value) => $_setInt64(2, value);
  @$pb.TagNumber(3)
  $core.bool hasAttackerUid() => $_has(2);
  @$pb.TagNumber(3)
  void clearAttackerUid() => $_clearField(3);

  @$pb.TagNumber(4)
  $fixnum.Int64 get targetUid => $_getI64(3);
  @$pb.TagNumber(4)
  set targetUid($fixnum.Int64 value) => $_setInt64(3, value);
  @$pb.TagNumber(4)
  $core.bool hasTargetUid() => $_has(3);
  @$pb.TagNumber(4)
  void clearTargetUid() => $_clearField(4);

  @$pb.TagNumber(5)
  $core.int get damage => $_getIZ(4);
  @$pb.TagNumber(5)
  set damage($core.int value) => $_setSignedInt32(4, value);
  @$pb.TagNumber(5)
  $core.bool hasDamage() => $_has(4);
  @$pb.TagNumber(5)
  void clearDamage() => $_clearField(5);

  @$pb.TagNumber(6)
  $core.bool get isCritical => $_getBF(5);
  @$pb.TagNumber(6)
  set isCritical($core.bool value) => $_setBool(5, value);
  @$pb.TagNumber(6)
  $core.bool hasIsCritical() => $_has(5);
  @$pb.TagNumber(6)
  void clearIsCritical() => $_clearField(6);
}

class PlayerState extends $pb.GeneratedMessage {
  factory PlayerState({
    $fixnum.Int64? uid,
    $core.double? x,
    $core.double? y,
    $core.int? direction,
    $core.int? action,
    $core.int? hp,
    $core.int? maxHp,
    $core.int? mp,
    $core.int? maxMp,
  }) {
    final result = create();
    if (uid != null) result.uid = uid;
    if (x != null) result.x = x;
    if (y != null) result.y = y;
    if (direction != null) result.direction = direction;
    if (action != null) result.action = action;
    if (hp != null) result.hp = hp;
    if (maxHp != null) result.maxHp = maxHp;
    if (mp != null) result.mp = mp;
    if (maxMp != null) result.maxMp = maxMp;
    return result;
  }

  PlayerState._();

  factory PlayerState.fromBuffer($core.List<$core.int> data,
          [$pb.ExtensionRegistry registry = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromBuffer(data, registry);
  factory PlayerState.fromJson($core.String json,
          [$pb.ExtensionRegistry registry = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromJson(json, registry);

  static final $pb.BuilderInfo _i = $pb.BuilderInfo(
      _omitMessageNames ? '' : 'PlayerState',
      package: const $pb.PackageName(_omitMessageNames ? '' : 'dnf'),
      createEmptyInstance: create)
    ..aInt64(1, _omitFieldNames ? '' : 'uid')
    ..aD(2, _omitFieldNames ? '' : 'x')
    ..aD(3, _omitFieldNames ? '' : 'y')
    ..aI(4, _omitFieldNames ? '' : 'direction')
    ..aI(5, _omitFieldNames ? '' : 'action')
    ..aI(6, _omitFieldNames ? '' : 'hp')
    ..aI(7, _omitFieldNames ? '' : 'maxHp', protoName: 'maxHp')
    ..aI(8, _omitFieldNames ? '' : 'mp')
    ..aI(9, _omitFieldNames ? '' : 'maxMp', protoName: 'maxMp')
    ..hasRequiredFields = false;

  @$core.Deprecated('See https://github.com/google/protobuf.dart/issues/998.')
  PlayerState clone() => deepCopy();
  @$core.Deprecated('See https://github.com/google/protobuf.dart/issues/998.')
  PlayerState copyWith(void Function(PlayerState) updates) =>
      super.copyWith((message) => updates(message as PlayerState))
          as PlayerState;

  @$core.override
  $pb.BuilderInfo get info_ => _i;

  @$core.pragma('dart2js:noInline')
  static PlayerState create() => PlayerState._();
  @$core.override
  PlayerState createEmptyInstance() => create();
  @$core.pragma('dart2js:noInline')
  static PlayerState getDefault() => _defaultInstance ??=
      $pb.GeneratedMessage.$_defaultFor<PlayerState>(create);
  static PlayerState? _defaultInstance;

  @$pb.TagNumber(1)
  $fixnum.Int64 get uid => $_getI64(0);
  @$pb.TagNumber(1)
  set uid($fixnum.Int64 value) => $_setInt64(0, value);
  @$pb.TagNumber(1)
  $core.bool hasUid() => $_has(0);
  @$pb.TagNumber(1)
  void clearUid() => $_clearField(1);

  @$pb.TagNumber(2)
  $core.double get x => $_getN(1);
  @$pb.TagNumber(2)
  set x($core.double value) => $_setDouble(1, value);
  @$pb.TagNumber(2)
  $core.bool hasX() => $_has(1);
  @$pb.TagNumber(2)
  void clearX() => $_clearField(2);

  @$pb.TagNumber(3)
  $core.double get y => $_getN(2);
  @$pb.TagNumber(3)
  set y($core.double value) => $_setDouble(2, value);
  @$pb.TagNumber(3)
  $core.bool hasY() => $_has(2);
  @$pb.TagNumber(3)
  void clearY() => $_clearField(3);

  @$pb.TagNumber(4)
  $core.int get direction => $_getIZ(3);
  @$pb.TagNumber(4)
  set direction($core.int value) => $_setSignedInt32(3, value);
  @$pb.TagNumber(4)
  $core.bool hasDirection() => $_has(3);
  @$pb.TagNumber(4)
  void clearDirection() => $_clearField(4);

  @$pb.TagNumber(5)
  $core.int get action => $_getIZ(4);
  @$pb.TagNumber(5)
  set action($core.int value) => $_setSignedInt32(4, value);
  @$pb.TagNumber(5)
  $core.bool hasAction() => $_has(4);
  @$pb.TagNumber(5)
  void clearAction() => $_clearField(5);

  @$pb.TagNumber(6)
  $core.int get hp => $_getIZ(5);
  @$pb.TagNumber(6)
  set hp($core.int value) => $_setSignedInt32(5, value);
  @$pb.TagNumber(6)
  $core.bool hasHp() => $_has(5);
  @$pb.TagNumber(6)
  void clearHp() => $_clearField(6);

  @$pb.TagNumber(7)
  $core.int get maxHp => $_getIZ(6);
  @$pb.TagNumber(7)
  set maxHp($core.int value) => $_setSignedInt32(6, value);
  @$pb.TagNumber(7)
  $core.bool hasMaxHp() => $_has(6);
  @$pb.TagNumber(7)
  void clearMaxHp() => $_clearField(7);

  @$pb.TagNumber(8)
  $core.int get mp => $_getIZ(7);
  @$pb.TagNumber(8)
  set mp($core.int value) => $_setSignedInt32(7, value);
  @$pb.TagNumber(8)
  $core.bool hasMp() => $_has(7);
  @$pb.TagNumber(8)
  void clearMp() => $_clearField(8);

  @$pb.TagNumber(9)
  $core.int get maxMp => $_getIZ(8);
  @$pb.TagNumber(9)
  set maxMp($core.int value) => $_setSignedInt32(8, value);
  @$pb.TagNumber(9)
  $core.bool hasMaxMp() => $_has(8);
  @$pb.TagNumber(9)
  void clearMaxMp() => $_clearField(9);
}

class GameStateSync extends $pb.GeneratedMessage {
  factory GameStateSync({
    $core.Iterable<PlayerState>? players,
    $fixnum.Int64? timestamp,
  }) {
    final result = create();
    if (players != null) result.players.addAll(players);
    if (timestamp != null) result.timestamp = timestamp;
    return result;
  }

  GameStateSync._();

  factory GameStateSync.fromBuffer($core.List<$core.int> data,
          [$pb.ExtensionRegistry registry = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromBuffer(data, registry);
  factory GameStateSync.fromJson($core.String json,
          [$pb.ExtensionRegistry registry = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromJson(json, registry);

  static final $pb.BuilderInfo _i = $pb.BuilderInfo(
      _omitMessageNames ? '' : 'GameStateSync',
      package: const $pb.PackageName(_omitMessageNames ? '' : 'dnf'),
      createEmptyInstance: create)
    ..pPM<PlayerState>(1, _omitFieldNames ? '' : 'players',
        subBuilder: PlayerState.create)
    ..aInt64(2, _omitFieldNames ? '' : 'timestamp')
    ..hasRequiredFields = false;

  @$core.Deprecated('See https://github.com/google/protobuf.dart/issues/998.')
  GameStateSync clone() => deepCopy();
  @$core.Deprecated('See https://github.com/google/protobuf.dart/issues/998.')
  GameStateSync copyWith(void Function(GameStateSync) updates) =>
      super.copyWith((message) => updates(message as GameStateSync))
          as GameStateSync;

  @$core.override
  $pb.BuilderInfo get info_ => _i;

  @$core.pragma('dart2js:noInline')
  static GameStateSync create() => GameStateSync._();
  @$core.override
  GameStateSync createEmptyInstance() => create();
  @$core.pragma('dart2js:noInline')
  static GameStateSync getDefault() => _defaultInstance ??=
      $pb.GeneratedMessage.$_defaultFor<GameStateSync>(create);
  static GameStateSync? _defaultInstance;

  @$pb.TagNumber(1)
  $pb.PbList<PlayerState> get players => $_getList(0);

  @$pb.TagNumber(2)
  $fixnum.Int64 get timestamp => $_getI64(1);
  @$pb.TagNumber(2)
  set timestamp($fixnum.Int64 value) => $_setInt64(1, value);
  @$pb.TagNumber(2)
  $core.bool hasTimestamp() => $_has(1);
  @$pb.TagNumber(2)
  void clearTimestamp() => $_clearField(2);
}

class HeartbeatRequest extends $pb.GeneratedMessage {
  factory HeartbeatRequest({
    $fixnum.Int64? timestamp,
  }) {
    final result = create();
    if (timestamp != null) result.timestamp = timestamp;
    return result;
  }

  HeartbeatRequest._();

  factory HeartbeatRequest.fromBuffer($core.List<$core.int> data,
          [$pb.ExtensionRegistry registry = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromBuffer(data, registry);
  factory HeartbeatRequest.fromJson($core.String json,
          [$pb.ExtensionRegistry registry = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromJson(json, registry);

  static final $pb.BuilderInfo _i = $pb.BuilderInfo(
      _omitMessageNames ? '' : 'HeartbeatRequest',
      package: const $pb.PackageName(_omitMessageNames ? '' : 'dnf'),
      createEmptyInstance: create)
    ..aInt64(1, _omitFieldNames ? '' : 'timestamp')
    ..hasRequiredFields = false;

  @$core.Deprecated('See https://github.com/google/protobuf.dart/issues/998.')
  HeartbeatRequest clone() => deepCopy();
  @$core.Deprecated('See https://github.com/google/protobuf.dart/issues/998.')
  HeartbeatRequest copyWith(void Function(HeartbeatRequest) updates) =>
      super.copyWith((message) => updates(message as HeartbeatRequest))
          as HeartbeatRequest;

  @$core.override
  $pb.BuilderInfo get info_ => _i;

  @$core.pragma('dart2js:noInline')
  static HeartbeatRequest create() => HeartbeatRequest._();
  @$core.override
  HeartbeatRequest createEmptyInstance() => create();
  @$core.pragma('dart2js:noInline')
  static HeartbeatRequest getDefault() => _defaultInstance ??=
      $pb.GeneratedMessage.$_defaultFor<HeartbeatRequest>(create);
  static HeartbeatRequest? _defaultInstance;

  @$pb.TagNumber(1)
  $fixnum.Int64 get timestamp => $_getI64(0);
  @$pb.TagNumber(1)
  set timestamp($fixnum.Int64 value) => $_setInt64(0, value);
  @$pb.TagNumber(1)
  $core.bool hasTimestamp() => $_has(0);
  @$pb.TagNumber(1)
  void clearTimestamp() => $_clearField(1);
}

class HeartbeatResponse extends $pb.GeneratedMessage {
  factory HeartbeatResponse({
    $fixnum.Int64? timestamp,
    $fixnum.Int64? serverTime,
  }) {
    final result = create();
    if (timestamp != null) result.timestamp = timestamp;
    if (serverTime != null) result.serverTime = serverTime;
    return result;
  }

  HeartbeatResponse._();

  factory HeartbeatResponse.fromBuffer($core.List<$core.int> data,
          [$pb.ExtensionRegistry registry = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromBuffer(data, registry);
  factory HeartbeatResponse.fromJson($core.String json,
          [$pb.ExtensionRegistry registry = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromJson(json, registry);

  static final $pb.BuilderInfo _i = $pb.BuilderInfo(
      _omitMessageNames ? '' : 'HeartbeatResponse',
      package: const $pb.PackageName(_omitMessageNames ? '' : 'dnf'),
      createEmptyInstance: create)
    ..aInt64(1, _omitFieldNames ? '' : 'timestamp')
    ..aInt64(2, _omitFieldNames ? '' : 'serverTime', protoName: 'serverTime')
    ..hasRequiredFields = false;

  @$core.Deprecated('See https://github.com/google/protobuf.dart/issues/998.')
  HeartbeatResponse clone() => deepCopy();
  @$core.Deprecated('See https://github.com/google/protobuf.dart/issues/998.')
  HeartbeatResponse copyWith(void Function(HeartbeatResponse) updates) =>
      super.copyWith((message) => updates(message as HeartbeatResponse))
          as HeartbeatResponse;

  @$core.override
  $pb.BuilderInfo get info_ => _i;

  @$core.pragma('dart2js:noInline')
  static HeartbeatResponse create() => HeartbeatResponse._();
  @$core.override
  HeartbeatResponse createEmptyInstance() => create();
  @$core.pragma('dart2js:noInline')
  static HeartbeatResponse getDefault() => _defaultInstance ??=
      $pb.GeneratedMessage.$_defaultFor<HeartbeatResponse>(create);
  static HeartbeatResponse? _defaultInstance;

  @$pb.TagNumber(1)
  $fixnum.Int64 get timestamp => $_getI64(0);
  @$pb.TagNumber(1)
  set timestamp($fixnum.Int64 value) => $_setInt64(0, value);
  @$pb.TagNumber(1)
  $core.bool hasTimestamp() => $_has(0);
  @$pb.TagNumber(1)
  void clearTimestamp() => $_clearField(1);

  @$pb.TagNumber(2)
  $fixnum.Int64 get serverTime => $_getI64(1);
  @$pb.TagNumber(2)
  set serverTime($fixnum.Int64 value) => $_setInt64(1, value);
  @$pb.TagNumber(2)
  $core.bool hasServerTime() => $_has(1);
  @$pb.TagNumber(2)
  void clearServerTime() => $_clearField(2);
}

class ErrorMessage extends $pb.GeneratedMessage {
  factory ErrorMessage({
    $core.int? errorCode,
    $core.String? errorMessage,
  }) {
    final result = create();
    if (errorCode != null) result.errorCode = errorCode;
    if (errorMessage != null) result.errorMessage = errorMessage;
    return result;
  }

  ErrorMessage._();

  factory ErrorMessage.fromBuffer($core.List<$core.int> data,
          [$pb.ExtensionRegistry registry = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromBuffer(data, registry);
  factory ErrorMessage.fromJson($core.String json,
          [$pb.ExtensionRegistry registry = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromJson(json, registry);

  static final $pb.BuilderInfo _i = $pb.BuilderInfo(
      _omitMessageNames ? '' : 'ErrorMessage',
      package: const $pb.PackageName(_omitMessageNames ? '' : 'dnf'),
      createEmptyInstance: create)
    ..aI(1, _omitFieldNames ? '' : 'errorCode', protoName: 'errorCode')
    ..aOS(2, _omitFieldNames ? '' : 'errorMessage', protoName: 'errorMessage')
    ..hasRequiredFields = false;

  @$core.Deprecated('See https://github.com/google/protobuf.dart/issues/998.')
  ErrorMessage clone() => deepCopy();
  @$core.Deprecated('See https://github.com/google/protobuf.dart/issues/998.')
  ErrorMessage copyWith(void Function(ErrorMessage) updates) =>
      super.copyWith((message) => updates(message as ErrorMessage))
          as ErrorMessage;

  @$core.override
  $pb.BuilderInfo get info_ => _i;

  @$core.pragma('dart2js:noInline')
  static ErrorMessage create() => ErrorMessage._();
  @$core.override
  ErrorMessage createEmptyInstance() => create();
  @$core.pragma('dart2js:noInline')
  static ErrorMessage getDefault() => _defaultInstance ??=
      $pb.GeneratedMessage.$_defaultFor<ErrorMessage>(create);
  static ErrorMessage? _defaultInstance;

  @$pb.TagNumber(1)
  $core.int get errorCode => $_getIZ(0);
  @$pb.TagNumber(1)
  set errorCode($core.int value) => $_setSignedInt32(0, value);
  @$pb.TagNumber(1)
  $core.bool hasErrorCode() => $_has(0);
  @$pb.TagNumber(1)
  void clearErrorCode() => $_clearField(1);

  @$pb.TagNumber(2)
  $core.String get errorMessage => $_getSZ(1);
  @$pb.TagNumber(2)
  set errorMessage($core.String value) => $_setString(1, value);
  @$pb.TagNumber(2)
  $core.bool hasErrorMessage() => $_has(1);
  @$pb.TagNumber(2)
  void clearErrorMessage() => $_clearField(2);
}

const $core.bool _omitFieldNames =
    $core.bool.fromEnvironment('protobuf.omit_field_names');
const $core.bool _omitMessageNames =
    $core.bool.fromEnvironment('protobuf.omit_message_names');
