# dnf-go-client 项目结构说明

## 📁 项目目录结构

```
dnf-go-client/
├── .gitignore                 # Git忽略文件配置
├── go.mod                    # Go模块定义
├── go.sum                    # Go依赖锁定文件
├── examples/                 # 示例代码（每个文件都是独立的可执行程序）
│   ├── README.md            # 示例说明文档
│   ├── login_client.go      # 登录客户端示例
│   ├── ping_client.go       # Ping客户端示例
│   ├── char_list_client.go  # 角色列表客户端示例
│   ├── batch04_codec.go    # 批次04编解码测试
│   ├── batch05_codec.go    # 批次05编解码测试
│   ├── batch06_codec.go    # 批次06编解码测试
│   ├── batch07_codec.go    # 批次07编解码测试
│   └── batch08_codec.go    # 批次08编解码测试
├── test/                     # 测试代码
│   ├── auth_login_test.go  # 认证登录测试
│   ├── session_test.go     # 会话测试
│   ├── channel_test.go     # 频道测试
│   ├── character_test.go   # 角色测试
│   ├── character_list_codec_test.go  # 角色列表编解码测试
│   ├── network_test.go     # 网络测试（需要Java服务端运行）
│   ├── batch05_test.go    # 批次05测试
│   ├── batch06_test.go    # 批次06测试
│   ├── batch07_test.go    # 批次07测试
│   └── batch08_test.go    # 批次08测试
└── gen/                      # 生成的代码（由buf生成）
    └── dnf/
        └── v1/
            ├── auth_login.pb.go      # 认证登录协议
            ├── auth.pb.go          # 认证协议
            ├── session.pb.go       # 会话协议
            ├── channel.pb.go       # 频道协议
            ├── character.pb.go     # 角色协议
            ├── common.pb.go        # 通用协议
            ├── platform.pb.go      # 平台协议
            ├── battle.pb.go        # 战斗协议
            ├── idip.pb.go         # IDIP协议
            ├── server_data.pb.go   # 服务器数据协议
            └── town.pb.go         # 城镇协议
```

**注意**：
- `examples/` 目录下的每个文件都是独立的可执行程序，包含各自的 `main` 函数
- 运行示例程序时需要单独指定文件，不能使用 `go build ./...` 编译整个 examples 目录

## 🔧 配置文件

### go.mod

```go
module github.com/pixb/DnfGameServer/dnf-go-client

go 1.25.5

require google.golang.org/protobuf v1.36.11
```

**说明**:
- 模块名称：`github.com/pixb/DnfGameServer/dnf-go-client`
- Go版本：1.25.5
- 依赖：google.golang.org/protobuf v1.36.11

### .gitignore

忽略以下文件和目录：
- 二进制文件（*.exe, *.dll, *.so, *.dylib）
- 测试文件（*.test, *.out）
- 依赖目录（vendor/）
- 生成的代码（gen/）
- IDE配置文件（.idea/, .vscode/）
- 操作系统文件（.DS_Store, Thumbs.db）

## 📦 代码生成

### buf.gen.yaml

```yaml
version: v2
managed:
  enabled: true
  disable:
    - file_option: go_package
      module: buf.build/googleapis/googleapis
  override:
    - file_option: java_package
      value: "com.dnfm.mina.protobuf.generated"
    - file_option: go_package_prefix
      value: github.com/pixb/DnfGameServer/dnf-go-client/gen

plugins:
  # Java Protobuf代码生成
  - remote: buf.build/protocolbuffers/java
    out: gen/java

  # Go代码生成（生成到dnf-go-client工程）
  - remote: buf.build/protocolbuffers/go
    out: ../dnf-go-client/gen
    opt: paths=source_relative
```

**说明**:
- Go代码生成到：`../dnf-go-client/gen`
- Java代码生成到：`gen/java`（在proto目录下）
- 使用source_relative模式，保持包结构

### 生成命令

```bash
cd /home/pix/dev/code/java/DnfGameServer/proto
buf generate
```

## 🧪 Import路径

所有生成的代码使用统一的import路径：

```go
import dnfv1 "github.com/pixb/DnfGameServer/dnf-go-client/gen/dnf/v1"
```

## 🧪 测试运行

### 运行单个测试文件

```bash
cd /home/pix/dev/code/java/DnfGameServer/dnf-go-client
go test -v ./test/batch08_test.go
```

### 运行示例程序

```bash
cd /home/pix/dev/code/java/DnfGameServer/dnf-go-client
go run examples/batch08_codec.go
```

## 📊 项目统计

| 项目 | 数量 |
| :--- | :--- |
| 示例文件 | 9 |
| 测试文件 | 10 |
| 生成的Proto文件 | 11 |
| 迁移批次 | 8 |

## 🔄 整理历史

### 整理前的问题

1. **go.mod与import路径不一致**
   - go.mod定义：`dnf-go-client`
   - 代码中使用：`dnf-go-client/gen/dnf/v1`

2. **多余的目录**
   - `gen/java/` - Java代码不应该在Go项目中
   - `gen/go/proto/` - 旧的生成代码

3. **目录结构混乱**
   - 新生成的代码在 `gen/go/dnf/v1/`
   - 旧代码在 `gen/go/proto/`

### 整理后的改进

1. **统一go.mod模块名**
   - 修改为：`github.com/pixb/DnfGameServer/dnf-go-client`

2. **统一import路径**
   - 所有文件使用：`github.com/pixb/DnfGameServer/dnf-go-client/gen/dnf/v1`

3. **清理多余目录**
   - 删除 `gen/java/`
   - 删除 `gen/go/proto/`

4. **统一生成目录**
   - 所有生成代码在 `gen/dnf/v1/`

5. **添加.gitignore**
   - 忽略生成的代码和临时文件

## 📝 注意事项

1. **生成代码不要手动修改**
   - 所有生成的代码都在 `gen/` 目录下
   - 修改proto文件后重新生成

2. **import路径统一**
   - 始终使用 `github.com/pixb/DnfGameServer/dnf-go-client/gen/dnf/v1`
   - 不要使用相对路径或绝对路径

3. **测试和示例**
   - 测试文件在 `test/` 目录
   - 示例文件在 `examples/` 目录

4. **依赖管理**
   - 使用 `go mod tidy` 整理依赖
   - 不要手动编辑 `go.sum`

## 🎯 下一步

1. 继续迁移更多批次的消息
2. 添加更多示例代码
3. 完善测试覆盖
4. 优化项目结构

---

**文档版本**: 1.0.0
**创建日期**: 2026-02-09
**创建人员**: AI Assistant
**状态**: ✅ 已完成
