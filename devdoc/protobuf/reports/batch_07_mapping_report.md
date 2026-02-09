# 批次07消息文件映射报告

**批次**: batch_07  
**模块**: 战斗服务器/IDIP禁止/服务器数据  
**状态**: completed  
**生成时间**: 2026-02-09 23:12:57

---

## 消息映射清单

| ModuleID | CMD | 旧消息 | 类型 | 新消息 | Proto文件 | 实现状态 | 生成文件 |
|:--------:|:---:|:-------|:----:|:-------|:----------|:--------:|:---------|
| 10014 | 0 | `REQ_CONNECTBATTLESERVER` | REQ | `ConnectBattleServerRequest` | `battle.proto` | ✅ complete | ConnectBattleServerRequest.java, battle.pb.go |
| 10014 | 1 | `RES_CONNECTBATTLESERVER` | RES | `ConnectBattleServerResponse` | `battle.proto` | ✅ complete | ConnectBattleServerResponse.java, battle.pb.go |
| 10017 | 0 | `PT_PROHIBIT` | PT | `Prohibit` | `idip.proto` | ❌ missing | Prohibit.java, idip.pb.go |
| 10017 | 0 | `REQ_IDIPPROHIBITLIST` | REQ | `IdipProhibitListRequest` | `idip.proto` | ✅ complete | IdipProhibitListRequest.java, idip.pb.go |
| 10017 | 1 | `RES_IDIPPROHIBITLIST` | RES | `IdipProhibitListResponse` | `idip.proto` | ✅ complete | IdipProhibitListResponse.java, idip.pb.go |
| 10031 | 0 | `REQ_LOADSERVERSIMPLEDATA` | REQ | `LoadServerSimpleDataRequest` | `server_data.proto` | ✅ complete | LoadServerSimpleDataRequest.java, server_data.pb.go |
| 10031 | 0 | `REQ_SAVESERVERSIMPLEDATA` | REQ | `SaveServerSimpleDataRequest` | `server_data.proto` | ✅ complete | SaveServerSimpleDataRequest.java, server_data.pb.go |
| 10031 | 0 | `PT_LOADSERVERSIMPLEDATA` | UNKNOWN | `LoadServerSimpleData` | `server_data.proto` | ✅ complete | LoadServerSimpleData.java, server_data.pb.go |
| 10031 | 1 | `RES_LOADSERVERSIMPLEDATA` | RES | `LoadServerSimpleDataResponse` | `server_data.proto` | ✅ complete | LoadServerSimpleDataResponse.java, server_data.pb.go |
| 10031 | 1 | `RES_SAVESERVERSIMPLEDATA` | RES | `SaveServerSimpleDataResponse` | `server_data.proto` | ✅ complete | SaveServerSimpleDataResponse.java, server_data.pb.go |


---

## 文件路径汇总

### 原Java文件位置
- **路径**: `src/main/java/com/dnfm/mina/protobuf/`
- **文件数**: 10 个

### Proto文件位置
- **路径**: `proto/dnf/v1/`
- **文件列表**:
  - `battle.proto`
  - `idip.proto`
  - `server_data.proto`

### 生成的Java文件位置
- **路径**: `proto/gen/java/com/dnfm/mina/protobuf/generated/`
- **文件数**: 10 个

### 生成的Go文件位置
- **路径**: `dnf-go-client/gen/dnf/v1/`
- **文件数**: 3 个

---

## 实现状态统计

| 状态 | 消息数 | 百分比 |
|:-----|:------:|:------:|
| ✅ 完整实现 | 9 | 90.0% |
| ⚠️ 简化实现 | 0 | 0.0% |
| ❌ 缺失实现 | 1 | 10.0% |
| **总计** | **10** | **100%** |

---

*报告由消息映射追踪系统自动生成*
