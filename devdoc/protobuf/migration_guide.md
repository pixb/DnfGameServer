# JProtobuf 到标准 Protobuf 迁移流程规范

## 1. 迁移前准备

### 1.1 检查迁移状态
- **使用工具**：`devdoc/protobuf/reports/scripts/core/migration_tracker.py`
- **操作步骤**：
  1. 执行 `python devdoc/protobuf/reports/scripts/core/migration_tracker.py check_status --batch 51`
  2. 查看输出结果，确认批次状态
  3. 如有异常，执行 `python devdoc/protobuf/reports/scripts/batch_management/fix_batch_status.py --batch 51` 修复状态

### 1.2 分析文件结构
- **使用工具**：`devdoc/protobuf/reports/scripts/analyze/analyze_jprotobuf_files.py`
- **操作步骤**：
  1. 执行 `python devdoc/protobuf/reports/scripts/analyze/analyze_jprotobuf_files.py --batch 51`
  2. 查看生成的分析报告，了解文件结构和依赖关系
  3. 检查 `devdoc/protobuf/reports/cache/analysis_cache.json` 获取缓存的分析结果

### 1.3 创建迁移计划
- **使用工具**：`devdoc/protobuf/reports/scripts/main.py`
- **操作步骤**：
  1. 执行 `python devdoc/protobuf/reports/scripts/main.py create_plan --batch 51`
  2. 查看生成的迁移计划，确认文件清单和迁移步骤
  3. 将计划保存到 `devdoc/protobuf/batch_51/01_迁移计划.md`

## 2. Proto 文件更新

### 2.1 添加消息定义
- **操作步骤**：
  1. 在对应的 proto 文件中添加消息类型定义
  2. 确保字段类型和编号正确
  3. 添加必要的注释和文档

### 2.2 修复语法错误
- **操作步骤**：
  1. 移除多余代码行和重复定义
  2. 修复语法错误和格式问题
  3. 确保 proto 文件符合语法规范

### 2.3 添加导入语句
- **操作步骤**：
  1. 分析跨文件类型依赖
  2. 添加正确的 import 语句
  3. 解决类型引用问题

### 2.4 验证 Proto 文件
- **使用工具**：`buf` 命令行工具
- **操作步骤**：
  1. 执行 `cd proto && buf lint`
  2. 修复所有 lint 错误
  3. 确保 proto 文件通过验证

## 3. 代码生成与验证

### 3.1 生成代码
- **使用工具**：`buf generate`
- **操作步骤**：
  1. 执行 `cd proto && buf generate`
  2. 查看生成的 Java 和 Go 代码
  3. 确认代码生成成功

### 3.2 项目构建
- **使用工具**：Maven
- **操作步骤**：
  1. 执行 `./mvnw clean package -DskipTests`
  2. 查看构建结果，确保编译通过
  3. 修复所有编译错误

### 3.3 运行测试
- **使用工具**：`go test`
- **操作步骤**：
  1. 执行 `cd dnf-go-client && go test ./test/...`
  2. 查看测试结果，确保测试通过
  3. 修复所有测试错误

## 4. 测试代码编写（强制要求）

### 4.1 创建测试文件
- **操作步骤**：
  1. 为每个批次创建对应的测试文件（如 `batch51_test.go`）
  2. 确保测试文件位于 `dnf-go-client/test/` 目录下
  3. 使用正确的包名和导入语句

### 4.2 编写测试用例
- **使用工具**：`testify/assert` 框架
- **操作步骤**：
  1. 为每个消息类型编写测试用例
  2. 验证消息类型的字段设置、获取功能
  3. 确保测试覆盖所有重要字段

### 4.3 执行测试
- **使用工具**：`go test`
- **操作步骤**：
  1. 执行 `cd dnf-go-client && go test ./test/batch51_test.go -v`
  2. 查看测试结果，确保所有测试通过
  3. 修复所有测试错误

## 5. 状态更新与报告

### 5.1 更新迁移状态
- **使用工具**：`devdoc/protobuf/reports/scripts/update_batch_status.py`
- **操作步骤**：
  1. 执行 `python devdoc/protobuf/reports/scripts/update_batch_status.py --batch 51 --status completed`
  2. 验证数据库状态已更新
  3. 执行 `python devdoc/protobuf/reports/scripts/core/migration_tracker.py check_status --batch 51` 确认状态

### 5.2 生成迁移报告
- **使用工具**：`devdoc/protobuf/reports/scripts/reports/generate_report.py`
- **操作步骤**：
  1. 执行 `python devdoc/protobuf/reports/scripts/reports/generate_report.py --batch 51`
  2. 查看生成的报告，确认迁移结果
  3. 将报告保存到 `devdoc/protobuf/batch_51/02_迁移结果.md`

### 5.3 生成综合报告
- **使用工具**：`devdoc/protobuf/reports/scripts/reports/enhanced_report_generator.py`
- **操作步骤**：
  1. 执行 `python devdoc/protobuf/reports/scripts/reports/enhanced_report_generator.py --batch 51`
  2. 查看生成的综合报告
  3. 将报告保存到 `devdoc/protobuf/reports/outputs/batch_51_migration_report.md`

### 5.4 提取消息映射
- **使用工具**：`devdoc/protobuf/reports/scripts/extract/extract_message_mappings.py`
- **操作步骤**：
  1. 执行 `python devdoc/protobuf/reports/scripts/extract/extract_message_mappings.py --batch 51`
  2. 查看生成的映射关系
  3. 将映射保存到 `devdoc/protobuf/reports/outputs/batch_51_mappings.json`

## 6. 验证与质量保证

### 6.1 验证构建成功
- **使用工具**：Maven
- **操作步骤**：
  1. 执行 `./mvnw clean package -DskipTests`
  2. 确保构建成功，无编译错误

### 6.2 验证测试通过
- **使用工具**：`go test`
- **操作步骤**：
  1. 执行 `cd dnf-go-client && go test ./test/...`
  2. 确保所有测试通过，无测试错误

### 6.3 验证代码生成
- **使用工具**：`buf generate`
- **操作步骤**：
  1. 执行 `cd proto && buf generate`
  2. 确保代码生成成功，无错误

### 6.4 验证迁移状态
- **使用工具**：`devdoc/protobuf/reports/scripts/core/migration_tracker.py`
- **操作步骤**：
  1. 执行 `python devdoc/protobuf/reports/scripts/core/migration_tracker.py check_status --batch 51`
  2. 确保状态为 `completed`
  3. 执行 `python devdoc/protobuf/reports/scripts/core/migration_tracker.py get_summary` 查看总体状态

## 7. 问题排查与解决

### 7.1 迁移状态异常
- **使用工具**：`devdoc/protobuf/reports/scripts/batch_management/fix_batch_status.py`
- **操作步骤**：
  1. 执行 `python devdoc/protobuf/reports/scripts/batch_management/fix_batch_status.py --batch 51`
  2. 查看修复结果，确认状态已更新

### 7.2 代码生成失败
- **使用工具**：`buf lint` 和 `buf generate`
- **操作步骤**：
  1. 执行 `cd proto && buf lint` 查找错误
  2. 修复所有 lint 错误
  3. 重新执行 `buf generate`

### 7.3 测试失败
- **使用工具**：`go test -v`
- **操作步骤**：
  1. 执行 `cd dnf-go-client && go test ./test/batch51_test.go -v`
  2. 查看详细错误信息
  3. 修复测试代码和相关问题

### 7.4 依赖问题
- **使用工具**：`devdoc/protobuf/reports/scripts/analyze/analyze_jprotobuf_files.py`
- **操作步骤**：
  1. 执行 `python devdoc/protobuf/reports/scripts/analyze/analyze_jprotobuf_files.py --batch 51 --dependencies`
  2. 查看依赖分析结果
  3. 解决依赖问题和循环引用

## 8. 工具使用总结

### 8.1 核心工具
- **migration_tracker.py**：管理迁移状态和进度
- **analyze_jprotobuf_files.py**：分析 JProtobuf 文件结构
- **main.py**：主脚本，协调迁移流程
- **update_batch_status.py**：更新批次状态
- **generate_report.py**：生成迁移报告

### 8.2 辅助工具
- **fix_batch_status.py**：修复批次状态异常
- **extract_message_mappings.py**：提取消息映射关系
- **enhanced_report_generator.py**：生成增强版报告
- **buf**：Proto 文件验证和代码生成

### 8.3 工具使用流程
1. **迁移前**：使用分析工具了解文件结构和状态
2. **迁移中**：使用生成工具创建计划和更新文件
3. **迁移后**：使用验证工具确保质量和生成报告
4. **问题排查**：使用修复工具解决遇到的问题

## 9. 质量保证

### 9.1 代码审查
- 检查生成的代码是否符合项目规范
- 确保代码风格一致，无明显错误

### 9.2 性能测试
- 验证生成的代码性能是否满足要求
- 确保序列化/反序列化速度正常

### 9.3 兼容性测试
- 确保迁移后的代码与原有系统兼容
- 验证消息类型的序列化格式一致

### 9.4 文档更新
- 更新相关文档，记录迁移过程和结果
- 确保文档与实际代码一致

## 10. 总结

通过以上完整的迁移流程，结合 `devdoc/protobuf/reports` 工具的使用，可以确保 JProtobuf 到标准 Protobuf 的迁移过程更加高效、自动化和可靠。工具的使用不仅可以提高迁移效率，还可以减少人为错误，确保迁移质量。

在迁移过程中，应严格按照流程执行每个步骤，确保每个环节都经过验证和测试。特别是测试代码的编写，是确保迁移质量的关键环节，必须认真执行。

通过工具的辅助，可以更加清晰地了解迁移状态和进度，及时发现和解决问题，确保迁移工作顺利完成。