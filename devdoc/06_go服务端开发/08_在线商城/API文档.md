# 4. API 文档

## 4.1 HTTP 接口

### 4.1.1 商城列表接口
- **URL**: `/api/mall/list`
- **方法**: `GET`
- **参数**:
  - `page`: 页码，默认1
  - `page_size`: 每页数量，默认10
  - `item_type`: 商品类型，默认0（全部）
  - `sort_type`: 排序类型，默认0（默认排序）
- **返回**:
  ```json
  {
    "items": [
      {
        "id": 1,
        "name": "测试商品",
        "description": "测试商品描述",
        "item_type": 1,
        "item_index": 1001,
        "item_count": 1,
        "bind": false,
        "price": 100,
        "currency_type": 1,
        "original_price": 150,
        "sale_quota": 100,
        "sale_count": 0,
        "for_sale": true,
        "recommend": true,
        "page": 1,
        "barcode": "",
        "start_time": 1771899939000,
        "end_time": 1774578339000,
        "buy_limit": 5,
        "buy_count": 0,
        "unit": "个"
      }
    ],
    "total": 1,
    "error": 0,
    "message": ""
  }
  ```

### 4.1.2 商城商品详情接口
- **URL**: `/api/mall/detail`
- **方法**: `GET`
- **参数**:
  - `item_id`: 商品ID
- **返回**:
  ```json
  {
    "item": {
      "id": 1,
      "name": "测试商品",
      "description": "测试商品描述",
      "item_type": 1,
      "item_index": 1001,
      "item_count": 1,
      "bind": false,
      "price": 100,
      "currency_type": 1,
      "original_price": 150,
      "sale_quota": 100,
      "sale_count": 0,
      "for_sale": true,
      "recommend": true,
      "page": 1,
      "barcode": "",
      "start_time": 1771899939000,
      "end_time": 1774578339000,
      "buy_limit": 5,
      "buy_count": 0,
      "unit": "个"
    },
    "error": 0,
    "message": ""
  }
  ```

### 4.1.3 商城购买接口
- **URL**: `/api/mall/buy`
- **方法**: `POST`
- **参数**:
  ```json
  {
    "item_id": 1,
    "buy_count": 1,
    "currency_type": 1
  }
  ```
- **返回**:
  ```json
  {
    "item": {
      "id": 1,
      "name": "测试商品",
      "description": "测试商品描述",
      "item_type": 1,
      "item_index": 1001,
      "item_count": 1,
      "bind": false,
      "price": 100,
      "currency_type": 1,
      "original_price": 150,
      "sale_quota": 100,
      "sale_count": 0,
      "for_sale": true,
      "recommend": true,
      "page": 1,
      "barcode": "",
      "start_time": 1771899939000,
      "end_time": 1774578339000,
      "buy_limit": 5,
      "buy_count": 0,
      "unit": "个"
    },
    "rewards": [
      {
        "index": 1001,
        "count": 1,
        "bind": false
      }
    ],
    "remaining_count": 4,
    "error": 0,
    "message": ""
  }
  ```

### 4.1.4 商城推荐接口
- **URL**: `/api/mall/recommend`
- **方法**: `GET`
- **参数**:
  - `recommend_type`: 推荐类型，默认0（热门）
  - `limit`: 数量限制，默认10
- **返回**:
  ```json
  {
    "items": [
      {
        "id": 1,
        "name": "测试商品",
        "description": "测试商品描述",
        "item_type": 1,
        "item_index": 1001,
        "item_count": 1,
        "bind": false,
        "price": 100,
        "currency_type": 1,
        "original_price": 150,
        "sale_quota": 100,
        "sale_count": 0,
        "for_sale": true,
        "recommend": true,
        "page": 1,
        "barcode": "",
        "start_time": 1771899939000,
        "end_time": 1774578339000,
        "buy_limit": 5,
        "buy_count": 0,
        "unit": "个"
      }
    ],
    "error": 0,
    "message": ""
  }
  ```

### 4.1.5 商城分类接口
- **URL**: `/api/mall/category`
- **方法**: `GET`
- **参数**: 无
- **返回**:
  ```json
  {
    "categories": [
      {
        "id": 1,
        "name": "装备",
        "parent_id": 0,
        "sort": 1
      },
      {
        "id": 2,
        "name": "武器",
        "parent_id": 1,
        "sort": 1
      }
    ],
    "error": 0,
    "message": ""
  }
  ```

### 4.1.6 商城搜索接口
- **URL**: `/api/mall/search`
- **方法**: `GET`
- **参数**:
  - `item_name`: 商品名称
  - `item_type`: 商品类型
  - `min_price`: 最低价格
  - `max_price`: 最高价格
  - `page`: 页码，默认1
  - `page_size`: 每页数量，默认10
- **返回**:
  ```json
  {
    "items": [
      {
        "id": 1,
        "name": "测试商品",
        "description": "测试商品描述",
        "item_type": 1,
        "item_index": 1001,
        "item_count": 1,
        "bind": false,
        "price": 100,
        "currency_type": 1,
        "original_price": 150,
        "sale_quota": 100,
        "sale_count": 0,
        "for_sale": true,
        "recommend": true,
        "page": 1,
        "barcode": "",
        "start_time": 1771899939000,
        "end_time": 1774578339000,
        "buy_limit": 5,
        "buy_count": 0,
        "unit": "个"
      }
    ],
    "total": 1,
    "error": 0,
    "message": ""
  }
  ```

### 4.1.7 购买记录接口
- **URL**: `/api/mall/buy_record`
- **方法**: `GET`
- **参数**:
  - `page`: 页码，默认1
  - `page_size`: 每页数量，默认10
- **返回**:
  ```json
  {
    "records": [
      {
        "id": 1,
        "item_id": 1,
        "item_name": "测试商品",
        "item_count": 1,
        "price": 100,
        "currency_type": 1,
        "buy_time": 1771899939000
      }
    ],
    "total": 1,
    "error": 0,
    "message": ""
  }
  ```

## 4.2 gRPC 接口

### 4.2.1 商城列表接口
- **服务**: `dnf.v1.OnlineMallService`
- **方法**: `GetMallList`
- **请求**:
  ```protobuf
  message OnlineMallListRequest {
    uint32 page = 1;
    uint32 page_size = 2;
    uint32 item_type = 3;
    uint32 sort_type = 4;
  }
  ```
- **响应**:
  ```protobuf
  message OnlineMallListResponse {
    repeated OnlineMallItem items = 1;
    uint32 total = 2;
    uint32 error = 3;
    string message = 4;
  }
  ```

### 4.2.2 商城商品详情接口
- **服务**: `dnf.v1.OnlineMallService`
- **方法**: `GetMallItemDetail`
- **请求**:
  ```protobuf
  message OnlineMallDetailRequest {
    uint32 item_id = 1;
  }
  ```
- **响应**:
  ```protobuf
  message OnlineMallDetailResponse {
    OnlineMallItem item = 1;
    uint32 error = 2;
    string message = 3;
  }
  ```

### 4.2.3 商城购买接口
- **服务**: `dnf.v1.OnlineMallService`
- **方法**: `BuyMallItem`
- **请求**:
  ```protobuf
  message OnlineMallBuyRequest {
    uint32 item_id = 1;
    uint32 buy_count = 2;
    uint32 currency_type = 3;
  }
  ```
- **响应**:
  ```protobuf
  message OnlineMallBuyResponse {
    OnlineMallItem item = 1;
    repeated StackableItem rewards = 2;
    uint32 remaining_count = 3;
    uint32 error = 4;
    string message = 5;
  }
  ```

### 4.2.4 商城推荐接口
- **服务**: `dnf.v1.OnlineMallService`
- **方法**: `GetRecommendItems`
- **请求**:
  ```protobuf
  message OnlineMallRecommendRequest {
    uint32 recommend_type = 1;
    uint32 limit = 2;
  }
  ```
- **响应**:
  ```protobuf
  message OnlineMallRecommendResponse {
    repeated OnlineMallItem items = 1;
    uint32 error = 2;
    string message = 3;
  }
  ```

### 4.2.5 商城分类接口
- **服务**: `dnf.v1.OnlineMallService`
- **方法**: `GetMallCategories`
- **请求**:
  ```protobuf
  message OnlineMallCategoryRequest {
  }
  ```
- **响应**:
  ```protobuf
  message OnlineMallCategoryResponse {
    repeated CategoryInfo categories = 1;
    uint32 error = 2;
    string message = 3;
  }
  ```

### 4.2.6 商城搜索接口
- **服务**: `dnf.v1.OnlineMallService`
- **方法**: `SearchMallItems`
- **请求**:
  ```protobuf
  message OnlineMallSearchRequest {
    string item_name = 1;
    uint32 item_type = 2;
    uint64 min_price = 3;
    uint64 max_price = 4;
    uint32 page = 5;
    uint32 page_size = 6;
  }
  ```
- **响应**:
  ```protobuf
  message OnlineMallSearchResponse {
    repeated OnlineMallItem items = 1;
    uint32 total = 2;
    uint32 error = 3;
    string message = 4;
  }
  ```

### 4.2.7 购买记录接口
- **服务**: `dnf.v1.OnlineMallService`
- **方法**: `GetBuyRecords`
- **请求**:
  ```protobuf
  message OnlineMallBuyRecordRequest {
    uint32 page = 1;
    uint32 page_size = 2;
  }
  ```
- **响应**:
  ```protobuf
  message OnlineMallBuyRecordResponse {
    repeated BuyRecordInfo records = 1;
    uint32 total = 2;
    uint32 error = 3;
    string message = 4;
  }
  ```