#!/bin/bash

# DNF Go Server API 测试脚本

BASE_URL="http://localhost:8080"

echo "=========================================="
echo "DNF Go Server API 测试"
echo "=========================================="

# 1. 健康检查
echo ""
echo "1. 测试健康检查..."
curl -s "$BASE_URL/healthz"
echo ""

# 2. 登录获取Token
echo ""
echo "2. 登录获取Token..."
LOGIN_RESPONSE=$(curl -s -X POST "$BASE_URL/api/v1/auth/login" \
  -H "Content-Type: application/json" \
  -d '{"openid": "test_user_002"}')
echo "$LOGIN_RESPONSE" | head -c 300
echo ""

TOKEN=$(echo "$LOGIN_RESPONSE" | grep -o '"authKey":"[^"]*"' | cut -d'"' -f4)
echo "Token: ${TOKEN:0:50}..."

# 3. 创建角色
echo ""
echo "3. 创建角色..."
CREATE_RESPONSE=$(curl -s -X POST "$BASE_URL/api/v1/character/create" \
  -H "Authorization: Bearer $TOKEN" \
  -H "Content-Type: application/json" \
  -d '{"slot": 1, "name": "TestHero2", "job": 2}')
echo "$CREATE_RESPONSE"

# 4. 获取角色列表
echo ""
echo "4. 获取角色列表..."
CHAR_LIST=$(curl -s "$BASE_URL/api/v1/character/list" \
  -H "Authorization: Bearer $TOKEN")
echo "$CHAR_LIST"

# 5. 选择角色获取游戏Token
echo ""
echo "5. 选择角色..."
SELECT_RESPONSE=$(curl -s -X POST "$BASE_URL/api/v1/character/select" \
  -H "Authorization: Bearer $TOKEN" \
  -H "Content-Type: application/json" \
  -d '{"uid": 1}')
echo "$SELECT_RESPONSE" | head -c 200
echo ""

# 6. 获取背包
echo ""
echo "6. 获取背包..."
BAG_RESPONSE=$(curl -s "$BASE_URL/api/v1/bag" \
  -H "Authorization: Bearer $TOKEN")
echo "$BAG_RESPONSE"

# 7. 获取商店列表
echo ""
echo "7. 获取商店列表..."
SHOP_RESPONSE=$(curl -s "$BASE_URL/api/v1/shop/list" \
  -H "Authorization: Bearer $TOKEN" \
  -d '{"shop_type": 1}')
echo "$SHOP_RESPONSE"

# 8. 购买物品测试
echo ""
echo "8. 购买物品测试..."
BUY_RESPONSE=$(curl -s -X POST "$BASE_URL/api/v1/shop/buy" \
  -H "Authorization: Bearer $TOKEN" \
  -H "Content-Type: application/json" \
  -d '{"shop_type": 1, "slot": 1, "count": 5}')
echo "$BUY_RESPONSE"

# 9. 再次获取背包
echo ""
echo "9. 购买后获取背包..."
BAG_RESPONSE2=$(curl -s "$BASE_URL/api/v1/bag" \
  -H "Authorization: Bearer $TOKEN")
echo "$BAG_RESPONSE2"

# 10. 获取好友列表
echo ""
echo "10. 获取好友列表..."
FRIEND_LIST=$(curl -s "$BASE_URL/api/v1/friend/list" \
  -H "Authorization: Bearer $TOKEN")
echo "$FRIEND_LIST"

# 11. 添加好友测试
echo ""
echo "11. 添加好友测试..."
ADD_FRIEND=$(curl -s -X POST "$BASE_URL/api/v1/friend/add" \
  -H "Authorization: Bearer $TOKEN" \
  -H "Content-Type: application/json" \
  -d '{"target_name": "TestHero"}')
echo "$ADD_FRIEND"

# 12. 获取公会信息（无公会）
echo ""
echo "12. 获取公会信息..."
GUILD_INFO=$(curl -s "$BASE_URL/api/v1/guild/info" \
  -H "Authorization: Bearer $TOKEN")
echo "$GUILD_INFO"

echo ""
echo "=========================================="
echo "测试完成"
echo "=========================================="
