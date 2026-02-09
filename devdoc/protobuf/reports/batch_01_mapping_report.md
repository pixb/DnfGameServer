# 批次01消息文件映射报告

**批次**: batch_01  
**模块**: 登录认证模块  
**状态**: completed  
**生成时间**: 2026-02-09 19:58:09

---

## 消息映射清单

| ModuleID | CMD | 旧消息 | 类型 | 新消息 | Proto文件 | 实现状态 | 生成文件 |
|:--------:|:---:|:-------|:----:|:-------|:----------|:--------:|:---------|
| 10000 | 0 | `REQ_LOGIN` | REQ | `LoginRequest` | `auth_login.proto` | ✅ complete | LoginRequest.java, auth_login.pb.go |
| 10000 | 0 | `PT_CHANNELINFO` | UNKNOWN | `ChannelInfo` | `auth_login.proto` | ❌ missing | ChannelInfo.java, auth_login.pb.go |
| 10000 | 0 | `PT_INTRUDEMEMBERINFO` | UNKNOWN | `IntrudeMemberInfo` | `auth_login.proto` | ❌ missing | IntrudeMemberInfo.java, auth_login.pb.go |
| 10000 | 0 | `PT_INTRUDEINFO` | UNKNOWN | `IntrudeInfo` | `auth_login.proto` | ❌ missing | IntrudeInfo.java, auth_login.pb.go |
| 10000 | 1 | `RES_LOGIN` | RES | `LoginResponse` | `auth_login.proto` | ✅ complete | LoginResponse.java, auth_login.pb.go |


---

## 文件路径汇总

### 原Java文件位置
- **路径**: `src/main/java/com/dnfm/mina/protobuf/`
- **文件数**: 5 个

### Proto文件位置
- **路径**: `proto/dnf/v1/`
- **文件列表**:
  - `auth_login.proto`

### 生成的Java文件位置
- **路径**: `proto/gen/java/com/dnfm/mina/protobuf/generated/`
- **文件数**: 5 个

### 生成的Go文件位置
- **路径**: `dnf-go-client/gen/dnf/v1/`
- **文件数**: 1 个

---

## 实现状态统计

| 状态 | 消息数 | 百分比 |
|:-----|:------:|:------:|
| ✅ 完整实现 | 2 | 40.0% |
| ⚠️ 简化实现 | 0 | 0.0% |
| ❌ 缺失实现 | 3 | 60.0% |
| **总计** | **5** | **100%** |

---

*报告由消息映射追踪系统自动生成*
