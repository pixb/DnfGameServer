# 制作系统 - API文档

## 1. HTTP 接口

| API路径 | 方法 | 模块/文件 | 类型 | 功能描述 | 请求体 (JSON) | 成功响应 (200 OK) |
| :--- | :--- | :--- | :--- | :--- | :--- | :--- |
| `/api/v1/craft/list` | `GET` | `handler/craft_handler.go` | `Router` | 制作配方查询 | `{"skill_level": 1, "item_type": 1}` | `{"recipes": [...], "error": 0}` |
| `/api/v1/craft/create` | `POST` | `handler/craft_handler.go` | `Router` | 物品制作 | `{"recipe_id": 123456, "craft_count": 1}` | `{"item_index": 1001, "item_count": 1, "success": true, "error": 0}` |
| `/api/v1/craft/skill` | `GET` | `handler/craft_handler.go` | `Router` | 制作技能查询 | `{}` | `{"skill_level": 5, "experience": 1200, "next_level_exp": 2000, "error": 0}` |
| `/api/v1/craft/preview` | `GET` | `handler/craft_handler.go` | `Router` | 制作预览 | `{"recipe_id": 123456}` | `{"recipe": {...}, "success_rate": 0.9, "error": 0}` |

## 2. gRPC 接口

| 方法 | 服务 | 模块/文件 | 功能描述 | 请求 | 响应 |
| :--- | :--- | :--- | :--- | :--- | :--- |
| `CraftList` | `CraftService` | `proto/dnf/v1/craft.proto` | 制作配方查询 | `CraftListRequest` | `CraftListResponse` |
| `CraftCreate` | `CraftService` | `proto/dnf/v1/craft.proto` | 物品制作 | `CraftCreateRequest` | `CraftCreateResponse` |
| `CraftSkill` | `CraftService` | `proto/dnf/v1/craft.proto` | 制作技能查询 | `CraftSkillRequest` | `CraftSkillResponse` |
| `CraftPreview` | `CraftService` | `proto/dnf/v1/craft.proto` | 制作预览 | `CraftPreviewRequest` | `CraftPreviewResponse` |