# 批次22迁移文档：STREAM_DATA 和 USER_INFO

## 迁移概述

**批次**：22
**日期**：2026-02-09
**模块**：STREAM_DATA、USER_INFO
**状态**：完成
**负责人**：系统自动

## 迁移文件

### 源文件（JProtobuf）
- `src/main/java/com/dnfm/mina/protobuf/STREAM_DATA.java`
- `src/main/java/com/dnfm/mina/protobuf/USER_INFO.java`

### 目标文件（标准Protobuf）
- `proto/dnf/v1/stream_data.proto`
- `proto/dnf/v1/user_info.proto`

## 消息定义

### STREAM_DATA 模块

| 消息类型 | 字段 | 类型 | 说明 |
|---------|------|------|------|
| FrameData | frame_size | int32 | 帧大小 |
| FrameData | split_count | int32 | 分割数量 |
| FrameData | split_seq | int32 | 分割序号 |
| FrameData | frame_data | bytes | 帧数据 |
| StreamDataRequest | start_frame | int32 | 起始帧 |
| StreamDataResponse | error | int32 | 错误码 |
| StreamDataResponse | req_frame | int32 | 请求帧 |
| StreamDataResponse | end_frame | int32 | 结束帧 |
| StreamDataResponse | frames | repeated FrameData | 帧列表 |

### USER_INFO 模块

| 消息类型 | 字段 | 类型 | 说明 |
|---------|------|------|------|
| UserInfo | charguid | uint64 | 角色GUID |
| UserInfo | job | int32 | 职业 |
| UserInfo | growtype | int32 | 成长类型 |
| UserInfo | secgrowtype | int32 | 次要成长类型 |
| UserInfo | team_type | int32 | 队伍类型 |
| UserInfo | world | int32 | 世界 |
| UserInfo | level | int32 | 等级 |
| UserInfo | name | string | 名称 |
| UserInfo | profile_url | string | 头像URL |
| UserInfo | profile_name | string | 头像名称 |
| UserInfo | character_frame | int32 | 角色边框 |
| UserInfo | rank | int32 | 排名 |

## 编解码器支持

### 模块ID分配
- STREAM_DATA: 22000
- USER_INFO: 22001

### 解码器适配
- `StandardProtobufDecoder.adaptStreamDataRequest()`
- `StandardProtobufDecoder.adaptUserInfoRequest()`

### 编码器适配
- `StandardProtobufEncoder.adaptStreamDataResponse()`
- `StandardProtobufEncoder.adaptUserInfoResponse()`

## 测试验证

### Go单元测试
- `dnf-go-client/test/batch22_test.go`
- 测试用例：
  - `TestBatch22StreamDataRequest`
  - `TestBatch22StreamDataResponse`
  - `TestBatch22UserInfo`
- 测试结果：全部通过

### Java编译验证
- Maven编译：成功
- 编译时间：2026-02-09 22:21:29
- 编译状态：BUILD SUCCESS

## 代码生成

### Java代码
- 生成目录：`proto/gen/java/com/dnfm/mina/protobuf/generated/`
- 生成文件：
  - `StreamDataRequest.java`
  - `StreamDataResponse.java`
  - `FrameData.java`
  - `UserInfo.java`

### Go代码
- 生成目录：`dnf-go-client/gen/dnf/v1/`
- 生成文件：
  - `stream_data.pb.go`
  - `user_info.pb.go`

## 迁移成果

1. **成功迁移**：将 STREAM_DATA 和 USER_INFO 模块从 JProtobuf 迁移到标准 Protobuf
2. **编解码器支持**：扩展了标准 Protobuf 编解码器，支持新迁移的消息
3. **跨语言支持**：生成了 Java 和 Go 语言的代码，支持跨语言通信
4. **测试验证**：编写了 Go 单元测试，验证消息编解码功能正常
5. **编译通过**：Java 代码编译成功，无语法错误

## 注意事项

- 保留了原始 JProtobuf 消息结构，确保向后兼容性
- 使用了标准 Protobuf 3 语法，遵循最佳实践
- 为每个消息类型分配了唯一的字段编号
- 确保了生成的代码与现有系统的集成

## 后续步骤

- 运行完整的功能测试，验证消息处理的端到端流程
- 监控生产环境中的消息处理性能
- 继续下一批次的迁移工作
