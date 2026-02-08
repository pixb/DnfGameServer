# DNF Flutter客户端

## 项目简介

DNF Flutter客户端是基于Flutter框架开发的横版格斗游戏客户端，与现有的Java后端（DnfGameServer）配合使用，实现多人在线对战功能。

## 技术栈

- **Flutter**: 3.16.0+
- **Dart**: 3.0+
- **Flame**: 1.10.0+ (游戏引擎)
- **Riverpod**: 2.4.0+ (状态管理)
- **WebSocket**: 实时通信
- **Protobuf**: 高效数据序列化

## 项目结构

```
dnf-flutter/
├── docs/                      # 开发文档
│   ├── 00_项目规划.md
│   ├── 01_开发指南.md
│   ├── 02_问题记录.md
│   ├── 03_测试文档.md
│   └── 04_API文档.md
├── tests/                     # 测试目录
│   ├── unit/
│   ├── widget/
│   ├── integration/
│   └── e2e/
├── lib/                       # 源代码
│   ├── main.dart
│   ├── game/
│   ├── network/
│   ├── ui/
│   ├── models/
│   ├── providers/
│   ├── utils/
│   └── services/
├── assets/                    # 资源文件
│   ├── images/
│   ├── audio/
│   ├── fonts/
│   └── data/
└── pubspec.yaml
```

## 快速开始

### 环境要求

- Flutter SDK 3.16.0+
- Dart 3.0+
- Android Studio / Xcode (用于移动端开发)

### 安装步骤

1. 克隆项目
```bash
cd /Users/pix/dev/code/java/DnfGameServer/dnf-flutter
```

2. 安装依赖
```bash
flutter pub get
```

3. 运行项目
```bash
flutter run
```

## 开发文档

详细的开发文档请查看 [docs](./docs) 目录：

- [00_项目规划.md](./docs/00_项目规划.md) - 项目规划和开发阶段
- [01_开发指南.md](./docs/01_开发指南.md) - 开发环境搭建和开发流程
- [02_问题记录.md](./docs/02_问题记录.md) - 开发过程中遇到的问题和解决方案
- [03_测试文档.md](./docs/03_测试文档.md) - 测试框架和测试用例
- [04_API文档.md](./docs/04_API文档.md) - API接口文档

## 核心功能

### 已完成

- [x] 项目规划
- [x] 开发文档
- [x] 目录结构

### 开发中

- [ ] 项目初始化
- [ ] 网络通信模块
- [ ] 基础UI
- [ ] 游戏引擎集成
- [ ] 角色系统
- [ ] 战斗系统
- [ ] 网络同步
- [ ] 测试和优化

## 开发规范

### 代码规范

- 遵循Dart官方代码规范
- 使用effective_dart插件
- 代码注释使用中文
- 每个类和函数添加文档注释

### Git规范

- 分支命名: feature/功能名, bugfix/问题名
- 提交信息: type(scope): subject
  - type: feat, fix, docs, style, refactor, test, chore
  - scope: 模块名
  - subject: 简短描述

## 测试

```bash
# 运行所有测试
flutter test

# 运行特定测试
flutter test tests/unit/models/player_test.dart

# 运行带覆盖率的测试
flutter test --coverage
```

## 性能目标

- 帧率: 60fps
- 网络延迟: < 100ms
- 内存占用: < 200MB
- 启动时间: < 3s

## 联系方式

- 项目负责人: [待定]
- 技术支持: [待定]
- 问题反馈: [待定]

## 许可证

[待定]

---

*最后更新时间: 2026-02-06*
