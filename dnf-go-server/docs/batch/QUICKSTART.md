# DNF Go Server 迁移项目 - 快速开始

**最后更新**: 2026-02-09  
**当前批次**: batch-02 (登录与账户系统)  
**项目状态**: 批次1完成 ✅, 批次2准备中 📝

---

## 📋 项目结构速览

```
dnf-go-server/
├── docs/
│   ├── batch/                    # 分批迁移文档
│   │   ├── README.md            # 总览和路线图
│   │   └── 02-login/            # 批次2: 登录系统
│   │       ├── design.md        # 详细设计
│   │       ├── tests.md         # 测试计划
│   │       └── tasks.md         # 任务清单
│   └── architecture/            # 架构设计文档
├── store/                       # 数据层
├── server/                      # 服务层
└── [其他代码目录...]
```

---

## 🎯 当前状态

### 批次1: 基础设施 ✅ (100%)
- [x] Store层架构
- [x] Server层架构  
- [x] Main层架构
- [x] 完整文档

### 批次2: 登录与账户系统 📝 (0%)
**计划时间**: 2026-02-10 至 2026-02-16

**当前阶段**: 设计阶段
- ⏳ T1.1 完善设计文档
- ⏳ T2.1 Session模型定义
- ⏳ T2.2 MySQL实现
- ⏳ ... (共13个任务)

**文档已创建**:
- ✅ design.md - 详细设计
- ✅ tests.md - 测试计划
- ✅ tasks.md - 任务清单

---

## 🚀 如何开始工作

### 场景1: 开始新的批次

```bash
# 1. 创建批次目录结构
mkdir -p docs/batch/03-role

# 2. 创建文档
touch docs/batch/03-role/design.md
touch docs/batch/03-role/tests.md
touch docs/batch/03-role/tasks.md

# 3. 更新批次总览
# 编辑 docs/batch/README.md 添加批次信息
```

### 场景2: 继续批次2的工作

**当前应做**:
1. 阅读 `docs/batch/02-login/design.md` - 了解设计
2. 阅读 `docs/batch/02-login/tasks.md` - 查看任务清单
3. 查看当前任务状态 (T1.1)
4. 开始编码

**编码顺序**:
1. `store/session.go` - 模型定义
2. `store/db/mysql/session.go` - MySQL实现
3. `store/store.go` - Store方法
4. `store/session_test.go` - 单元测试
5. ... (按tasks.md顺序)

### 场景3: 完成一个任务

```bash
# 1. 完成任务编码
# 2. 运行测试
go test ./store/... -v

# 3. 更新任务状态
# 编辑 docs/batch/02-login/tasks.md
# 将对应任务状态改为 ✅ COMPLETED

# 4. 记录变更
git add .
git commit -m "[batch-02] feat: 实现Session模型定义

- 添加Session结构体
- 定义Session常量
- 添加单元测试

Refs: docs/batch/02-login/tasks.md T2.1"
```

---

## 📖 常用文档索引

| 文档 | 用途 | 路径 |
|------|------|------|
| 批次总览 | 查看整体进度 | `docs/batch/README.md` |
| 批次2设计 | 查看详细设计 | `docs/batch/02-login/design.md` |
| 批次2测试 | 查看测试计划 | `docs/batch/02-login/tests.md` |
| 批次2任务 | 查看任务清单 | `docs/batch/02-login/tasks.md` |
| 架构总览 | 了解整体架构 | `docs/architecture/01-overview.md` |
| Store设计 | Store层设计 | `docs/architecture/02-store-layer-design.md` |
| Server设计 | Server层设计 | `docs/architecture/03-server-layer-design.md` |

---

## 🔧 常用命令

```bash
# 运行测试
go test ./store/... -v                    # Store层测试
go test ./store/... -run TestSession -v   # 特定测试
go test ./... -coverprofile=coverage.out  # 覆盖率

# 构建
make build         # 构建
make dev           # 开发模式运行
make test          # 运行所有测试

# 数据库迁移
make migrate       # 执行迁移

# Docker
docker-compose up -d      # 启动服务
docker-compose logs -f    # 查看日志
```

---

## 📝 文档规范

### 提交信息格式
```
[batch-XX] type: 简短描述

- 详细变更1
- 详细变更2

Refs: docs/batch/XX-module/tasks.md T.X.X
```

### 任务状态标记
- ⏳ PENDING - 待开始
- 🔄 IN_PROGRESS - 进行中
- ✅ COMPLETED - 已完成
- 🐛 BLOCKED - 阻塞中

### 批次状态标记
- ⏳ PENDING - 待开始
- 📝 PLANNING - 计划阶段
- 🔄 IN_PROGRESS - 进行中
- ✅ COMPLETED - 已完成

---

## 🎓 如何阅读设计文档

### design.md 阅读顺序
1. **需求概述** - 了解功能范围
2. **数据模型** - 查看数据结构
3. **接口定义** - 了解API设计
4. **流程图** - 理解业务流程
5. **测试重点** - 明确测试要求

### tests.md 阅读顺序
1. **测试策略** - 了解整体策略
2. **单元测试** - 查看具体测试用例
3. **集成测试** - 了解流程测试
4. **覆盖率清单** - 明确验收标准

### tasks.md 阅读顺序
1. **任务总览** - 了解整体工作量
2. **详细任务** - 查看具体任务
3. **每日计划** - 了解时间安排
4. **进度追踪** - 查看当前进度

---

## ⚠️ 注意事项

1. **文档先行**: 每个批次开始前必须先完成设计文档
2. **测试先行**: 每个功能先写测试再实现
3. **及时记录**: 每次变更都要更新任务状态
4. **最小实现**: 每个任务只实现必要功能，避免过度设计
5. **状态同步**: 定期更新批次README.md中的状态

---

## 📞 快速查询

### 当前批次信息
- **批次ID**: batch-02
- **模块**: 登录与账户系统
- **状态**: 📝 PLANNING
- **进度**: 0%
- **负责人**: 待分配

### 当前任务
- **任务ID**: T1.1
- **任务名**: 完善设计文档
- **状态**: ⏳ PENDING
- **预计耗时**: 4h
- **截止日期**: 2026-02-10 12:00

### 下一步
1. 阅读 `docs/batch/02-login/design.md`
2. 根据设计完善细节
3. 更新任务状态为 ✅
4. 开始 T2.1 Session模型定义

---

**提示**: 每次开始工作前，请先查看本文档的"当前状态"和"下一步"部分。
