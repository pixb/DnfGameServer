# 批次06消息文件映射报告

**批次**: batch_06  
**模块**: 认证密钥刷新/平台资料更新  
**状态**: completed  
**生成时间**: 2026-02-09 23:12:57

---

## 消息映射清单

| ModuleID | CMD | 旧消息 | 类型 | 新消息 | Proto文件 | 实现状态 | 生成文件 |
|:--------:|:---:|:-------|:----:|:-------|:----------|:--------:|:---------|
| 10009 | 0 | `REQ_AUTHKEYREFRESH` | REQ | `AuthkeyRefreshRequest` | `auth.proto` | ✅ complete | AuthkeyRefreshRequest.java, auth.pb.go |
| 10009 | 1 | `RES_AUTHKEYREFRESH` | RES | `AuthkeyRefreshResponse` | `auth.proto` | ✅ complete | AuthkeyRefreshResponse.java, auth.pb.go |
| 10012 | 0 | `REQ_PLATFORMPROFILEUPDATE` | REQ | `PlatformProfileUpdateRequest` | `platform.proto` | ✅ complete | PlatformProfileUpdateRequest.java, platform.pb.go |
| 10012 | 1 | `RES_PLATFORMPROFILEUPDATE` | RES | `PlatformProfileUpdateResponse` | `platform.proto` | ✅ complete | PlatformProfileUpdateResponse.java, platform.pb.go |


---

## 文件路径汇总

### 原Java文件位置
- **路径**: `src/main/java/com/dnfm/mina/protobuf/`
- **文件数**: 4 个

### Proto文件位置
- **路径**: `proto/dnf/v1/`
- **文件列表**:
  - `auth.proto`
  - `platform.proto`

### 生成的Java文件位置
- **路径**: `proto/gen/java/com/dnfm/mina/protobuf/generated/`
- **文件数**: 4 个

### 生成的Go文件位置
- **路径**: `dnf-go-client/gen/dnf/v1/`
- **文件数**: 2 个

---

## 实现状态统计

| 状态 | 消息数 | 百分比 |
|:-----|:------:|:------:|
| ✅ 完整实现 | 4 | 100.0% |
| ⚠️ 简化实现 | 0 | 0.0% |
| ❌ 缺失实现 | 0 | 0.0% |
| **总计** | **4** | **100%** |

---

*报告由消息映射追踪系统自动生成*
