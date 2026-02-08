// This is a generated file - do not edit.
//
// Generated from protobuf/game_messages.proto.

// @dart = 3.3

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names
// ignore_for_file: curly_braces_in_flow_control_structures
// ignore_for_file: deprecated_member_use_from_same_package, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_relative_imports
// ignore_for_file: unused_import

import 'dart:convert' as $convert;
import 'dart:core' as $core;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use baseMessageDescriptor instead')
const BaseMessage$json = {
  '1': 'BaseMessage',
  '2': [
    {'1': 'msgId', '3': 1, '4': 1, '5': 9, '10': 'msgId'},
    {'1': 'timestamp', '3': 2, '4': 1, '5': 3, '10': 'timestamp'},
    {'1': 'msgType', '3': 3, '4': 1, '5': 5, '10': 'msgType'},
    {'1': 'data', '3': 4, '4': 1, '5': 12, '10': 'data'},
  ],
};

/// Descriptor for `BaseMessage`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List baseMessageDescriptor = $convert.base64Decode(
    'CgtCYXNlTWVzc2FnZRIUCgVtc2dJZBgBIAEoCVIFbXNnSWQSHAoJdGltZXN0YW1wGAIgASgDUg'
    'l0aW1lc3RhbXASGAoHbXNnVHlwZRgDIAEoBVIHbXNnVHlwZRISCgRkYXRhGAQgASgMUgRkYXRh');

@$core.Deprecated('Use loginRequestDescriptor instead')
const LoginRequest$json = {
  '1': 'LoginRequest',
  '2': [
    {'1': 'username', '3': 1, '4': 1, '5': 9, '10': 'username'},
    {'1': 'password', '3': 2, '4': 1, '5': 9, '10': 'password'},
    {'1': 'deviceId', '3': 3, '4': 1, '5': 9, '10': 'deviceId'},
  ],
};

/// Descriptor for `LoginRequest`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List loginRequestDescriptor = $convert.base64Decode(
    'CgxMb2dpblJlcXVlc3QSGgoIdXNlcm5hbWUYASABKAlSCHVzZXJuYW1lEhoKCHBhc3N3b3JkGA'
    'IgASgJUghwYXNzd29yZBIaCghkZXZpY2VJZBgDIAEoCVIIZGV2aWNlSWQ=');

@$core.Deprecated('Use loginResponseDescriptor instead')
const LoginResponse$json = {
  '1': 'LoginResponse',
  '2': [
    {'1': 'success', '3': 1, '4': 1, '5': 8, '10': 'success'},
    {'1': 'message', '3': 2, '4': 1, '5': 9, '10': 'message'},
    {'1': 'token', '3': 3, '4': 1, '5': 9, '10': 'token'},
    {'1': 'userId', '3': 4, '4': 1, '5': 3, '10': 'userId'},
    {
      '1': 'roles',
      '3': 5,
      '4': 3,
      '5': 11,
      '6': '.dnf.RoleInfo',
      '10': 'roles'
    },
  ],
};

/// Descriptor for `LoginResponse`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List loginResponseDescriptor = $convert.base64Decode(
    'Cg1Mb2dpblJlc3BvbnNlEhgKB3N1Y2Nlc3MYASABKAhSB3N1Y2Nlc3MSGAoHbWVzc2FnZRgCIA'
    'EoCVIHbWVzc2FnZRIUCgV0b2tlbhgDIAEoCVIFdG9rZW4SFgoGdXNlcklkGAQgASgDUgZ1c2Vy'
    'SWQSIwoFcm9sZXMYBSADKAsyDS5kbmYuUm9sZUluZm9SBXJvbGVz');

@$core.Deprecated('Use roleInfoDescriptor instead')
const RoleInfo$json = {
  '1': 'RoleInfo',
  '2': [
    {'1': 'uid', '3': 1, '4': 1, '5': 3, '10': 'uid'},
    {'1': 'name', '3': 2, '4': 1, '5': 9, '10': 'name'},
    {'1': 'level', '3': 3, '4': 1, '5': 5, '10': 'level'},
    {'1': 'job', '3': 4, '4': 1, '5': 5, '10': 'job'},
    {'1': 'exp', '3': 5, '4': 1, '5': 3, '10': 'exp'},
    {'1': 'gold', '3': 6, '4': 1, '5': 3, '10': 'gold'},
  ],
};

/// Descriptor for `RoleInfo`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List roleInfoDescriptor = $convert.base64Decode(
    'CghSb2xlSW5mbxIQCgN1aWQYASABKANSA3VpZBISCgRuYW1lGAIgASgJUgRuYW1lEhQKBWxldm'
    'VsGAMgASgFUgVsZXZlbBIQCgNqb2IYBCABKAVSA2pvYhIQCgNleHAYBSABKANSA2V4cBISCgRn'
    'b2xkGAYgASgDUgRnb2xk');

@$core.Deprecated('Use createRoleRequestDescriptor instead')
const CreateRoleRequest$json = {
  '1': 'CreateRoleRequest',
  '2': [
    {'1': 'userId', '3': 1, '4': 1, '5': 3, '10': 'userId'},
    {'1': 'roleName', '3': 2, '4': 1, '5': 9, '10': 'roleName'},
    {'1': 'jobClass', '3': 3, '4': 1, '5': 5, '10': 'jobClass'},
  ],
};

/// Descriptor for `CreateRoleRequest`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List createRoleRequestDescriptor = $convert.base64Decode(
    'ChFDcmVhdGVSb2xlUmVxdWVzdBIWCgZ1c2VySWQYASABKANSBnVzZXJJZBIaCghyb2xlTmFtZR'
    'gCIAEoCVIIcm9sZU5hbWUSGgoIam9iQ2xhc3MYAyABKAVSCGpvYkNsYXNz');

@$core.Deprecated('Use createRoleResponseDescriptor instead')
const CreateRoleResponse$json = {
  '1': 'CreateRoleResponse',
  '2': [
    {'1': 'success', '3': 1, '4': 1, '5': 8, '10': 'success'},
    {'1': 'message', '3': 2, '4': 1, '5': 9, '10': 'message'},
    {'1': 'roleId', '3': 3, '4': 1, '5': 3, '10': 'roleId'},
  ],
};

/// Descriptor for `CreateRoleResponse`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List createRoleResponseDescriptor = $convert.base64Decode(
    'ChJDcmVhdGVSb2xlUmVzcG9uc2USGAoHc3VjY2VzcxgBIAEoCFIHc3VjY2VzcxIYCgdtZXNzYW'
    'dlGAIgASgJUgdtZXNzYWdlEhYKBnJvbGVJZBgDIAEoA1IGcm9sZUlk');

@$core.Deprecated('Use deleteRoleRequestDescriptor instead')
const DeleteRoleRequest$json = {
  '1': 'DeleteRoleRequest',
  '2': [
    {'1': 'userId', '3': 1, '4': 1, '5': 3, '10': 'userId'},
    {'1': 'roleId', '3': 2, '4': 1, '5': 3, '10': 'roleId'},
  ],
};

/// Descriptor for `DeleteRoleRequest`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List deleteRoleRequestDescriptor = $convert.base64Decode(
    'ChFEZWxldGVSb2xlUmVxdWVzdBIWCgZ1c2VySWQYASABKANSBnVzZXJJZBIWCgZyb2xlSWQYAi'
    'ABKANSBnJvbGVJZA==');

@$core.Deprecated('Use deleteRoleResponseDescriptor instead')
const DeleteRoleResponse$json = {
  '1': 'DeleteRoleResponse',
  '2': [
    {'1': 'success', '3': 1, '4': 1, '5': 8, '10': 'success'},
    {'1': 'message', '3': 2, '4': 1, '5': 9, '10': 'message'},
  ],
};

/// Descriptor for `DeleteRoleResponse`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List deleteRoleResponseDescriptor = $convert.base64Decode(
    'ChJEZWxldGVSb2xlUmVzcG9uc2USGAoHc3VjY2VzcxgBIAEoCFIHc3VjY2VzcxIYCgdtZXNzYW'
    'dlGAIgASgJUgdtZXNzYWdl');

@$core.Deprecated('Use moveRequestDescriptor instead')
const MoveRequest$json = {
  '1': 'MoveRequest',
  '2': [
    {'1': 'uid', '3': 1, '4': 1, '5': 3, '10': 'uid'},
    {'1': 'x', '3': 2, '4': 1, '5': 1, '10': 'x'},
    {'1': 'y', '3': 3, '4': 1, '5': 1, '10': 'y'},
    {'1': 'direction', '3': 4, '4': 1, '5': 5, '10': 'direction'},
  ],
};

/// Descriptor for `MoveRequest`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List moveRequestDescriptor = $convert.base64Decode(
    'CgtNb3ZlUmVxdWVzdBIQCgN1aWQYASABKANSA3VpZBIMCgF4GAIgASgBUgF4EgwKAXkYAyABKA'
    'FSAXkSHAoJZGlyZWN0aW9uGAQgASgFUglkaXJlY3Rpb24=');

@$core.Deprecated('Use moveResponseDescriptor instead')
const MoveResponse$json = {
  '1': 'MoveResponse',
  '2': [
    {'1': 'success', '3': 1, '4': 1, '5': 8, '10': 'success'},
    {'1': 'message', '3': 2, '4': 1, '5': 9, '10': 'message'},
    {'1': 'uid', '3': 3, '4': 1, '5': 3, '10': 'uid'},
    {'1': 'x', '3': 4, '4': 1, '5': 1, '10': 'x'},
    {'1': 'y', '3': 5, '4': 1, '5': 1, '10': 'y'},
    {'1': 'direction', '3': 6, '4': 1, '5': 5, '10': 'direction'},
  ],
};

/// Descriptor for `MoveResponse`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List moveResponseDescriptor = $convert.base64Decode(
    'CgxNb3ZlUmVzcG9uc2USGAoHc3VjY2VzcxgBIAEoCFIHc3VjY2VzcxIYCgdtZXNzYWdlGAIgAS'
    'gJUgdtZXNzYWdlEhAKA3VpZBgDIAEoA1IDdWlkEgwKAXgYBCABKAFSAXgSDAoBeRgFIAEoAVIB'
    'eRIcCglkaXJlY3Rpb24YBiABKAVSCWRpcmVjdGlvbg==');

@$core.Deprecated('Use attackRequestDescriptor instead')
const AttackRequest$json = {
  '1': 'AttackRequest',
  '2': [
    {'1': 'uid', '3': 1, '4': 1, '5': 3, '10': 'uid'},
    {'1': 'skillId', '3': 2, '4': 1, '5': 5, '10': 'skillId'},
    {'1': 'direction', '3': 3, '4': 1, '5': 5, '10': 'direction'},
  ],
};

/// Descriptor for `AttackRequest`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List attackRequestDescriptor = $convert.base64Decode(
    'Cg1BdHRhY2tSZXF1ZXN0EhAKA3VpZBgBIAEoA1IDdWlkEhgKB3NraWxsSWQYAiABKAVSB3NraW'
    'xsSWQSHAoJZGlyZWN0aW9uGAMgASgFUglkaXJlY3Rpb24=');

@$core.Deprecated('Use attackResponseDescriptor instead')
const AttackResponse$json = {
  '1': 'AttackResponse',
  '2': [
    {'1': 'success', '3': 1, '4': 1, '5': 8, '10': 'success'},
    {'1': 'message', '3': 2, '4': 1, '5': 9, '10': 'message'},
    {'1': 'attackerUid', '3': 3, '4': 1, '5': 3, '10': 'attackerUid'},
    {'1': 'targetUid', '3': 4, '4': 1, '5': 3, '10': 'targetUid'},
    {'1': 'damage', '3': 5, '4': 1, '5': 5, '10': 'damage'},
    {'1': 'isCritical', '3': 6, '4': 1, '5': 8, '10': 'isCritical'},
  ],
};

/// Descriptor for `AttackResponse`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List attackResponseDescriptor = $convert.base64Decode(
    'Cg5BdHRhY2tSZXNwb25zZRIYCgdzdWNjZXNzGAEgASgIUgdzdWNjZXNzEhgKB21lc3NhZ2UYAi'
    'ABKAlSB21lc3NhZ2USIAoLYXR0YWNrZXJVaWQYAyABKANSC2F0dGFja2VyVWlkEhwKCXRhcmdl'
    'dFVpZBgEIAEoA1IJdGFyZ2V0VWlkEhYKBmRhbWFnZRgFIAEoBVIGZGFtYWdlEh4KCmlzQ3JpdG'
    'ljYWwYBiABKAhSCmlzQ3JpdGljYWw=');

@$core.Deprecated('Use playerStateDescriptor instead')
const PlayerState$json = {
  '1': 'PlayerState',
  '2': [
    {'1': 'uid', '3': 1, '4': 1, '5': 3, '10': 'uid'},
    {'1': 'x', '3': 2, '4': 1, '5': 1, '10': 'x'},
    {'1': 'y', '3': 3, '4': 1, '5': 1, '10': 'y'},
    {'1': 'direction', '3': 4, '4': 1, '5': 5, '10': 'direction'},
    {'1': 'action', '3': 5, '4': 1, '5': 5, '10': 'action'},
    {'1': 'hp', '3': 6, '4': 1, '5': 5, '10': 'hp'},
    {'1': 'maxHp', '3': 7, '4': 1, '5': 5, '10': 'maxHp'},
    {'1': 'mp', '3': 8, '4': 1, '5': 5, '10': 'mp'},
    {'1': 'maxMp', '3': 9, '4': 1, '5': 5, '10': 'maxMp'},
  ],
};

/// Descriptor for `PlayerState`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List playerStateDescriptor = $convert.base64Decode(
    'CgtQbGF5ZXJTdGF0ZRIQCgN1aWQYASABKANSA3VpZBIMCgF4GAIgASgBUgF4EgwKAXkYAyABKA'
    'FSAXkSHAoJZGlyZWN0aW9uGAQgASgFUglkaXJlY3Rpb24SFgoGYWN0aW9uGAUgASgFUgZhY3Rp'
    'b24SDgoCaHAYBiABKAVSAmhwEhQKBW1heEhwGAcgASgFUgVtYXhIcBIOCgJtcBgIIAEoBVICbX'
    'ASFAoFbWF4TXAYCSABKAVSBW1heE1w');

@$core.Deprecated('Use gameStateSyncDescriptor instead')
const GameStateSync$json = {
  '1': 'GameStateSync',
  '2': [
    {
      '1': 'players',
      '3': 1,
      '4': 3,
      '5': 11,
      '6': '.dnf.PlayerState',
      '10': 'players'
    },
    {'1': 'timestamp', '3': 2, '4': 1, '5': 3, '10': 'timestamp'},
  ],
};

/// Descriptor for `GameStateSync`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List gameStateSyncDescriptor = $convert.base64Decode(
    'Cg1HYW1lU3RhdGVTeW5jEioKB3BsYXllcnMYASADKAsyEC5kbmYuUGxheWVyU3RhdGVSB3BsYX'
    'llcnMSHAoJdGltZXN0YW1wGAIgASgDUgl0aW1lc3RhbXA=');

@$core.Deprecated('Use heartbeatRequestDescriptor instead')
const HeartbeatRequest$json = {
  '1': 'HeartbeatRequest',
  '2': [
    {'1': 'timestamp', '3': 1, '4': 1, '5': 3, '10': 'timestamp'},
  ],
};

/// Descriptor for `HeartbeatRequest`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List heartbeatRequestDescriptor = $convert.base64Decode(
    'ChBIZWFydGJlYXRSZXF1ZXN0EhwKCXRpbWVzdGFtcBgBIAEoA1IJdGltZXN0YW1w');

@$core.Deprecated('Use heartbeatResponseDescriptor instead')
const HeartbeatResponse$json = {
  '1': 'HeartbeatResponse',
  '2': [
    {'1': 'timestamp', '3': 1, '4': 1, '5': 3, '10': 'timestamp'},
    {'1': 'serverTime', '3': 2, '4': 1, '5': 3, '10': 'serverTime'},
  ],
};

/// Descriptor for `HeartbeatResponse`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List heartbeatResponseDescriptor = $convert.base64Decode(
    'ChFIZWFydGJlYXRSZXNwb25zZRIcCgl0aW1lc3RhbXAYASABKANSCXRpbWVzdGFtcBIeCgpzZX'
    'J2ZXJUaW1lGAIgASgDUgpzZXJ2ZXJUaW1l');

@$core.Deprecated('Use errorMessageDescriptor instead')
const ErrorMessage$json = {
  '1': 'ErrorMessage',
  '2': [
    {'1': 'errorCode', '3': 1, '4': 1, '5': 5, '10': 'errorCode'},
    {'1': 'errorMessage', '3': 2, '4': 1, '5': 9, '10': 'errorMessage'},
  ],
};

/// Descriptor for `ErrorMessage`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List errorMessageDescriptor = $convert.base64Decode(
    'CgxFcnJvck1lc3NhZ2USHAoJZXJyb3JDb2RlGAEgASgFUgllcnJvckNvZGUSIgoMZXJyb3JNZX'
    'NzYWdlGAIgASgJUgxlcnJvck1lc3NhZ2U=');
