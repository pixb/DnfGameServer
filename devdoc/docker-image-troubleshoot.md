# Docker 镜像拉取问题解决方案

## 问题描述

在使用 Docker Compose 启动 MySQL 时，遇到以下错误：

```
Error response from daemon: pull access denied for registry.cn-hangzhou.aliyuncs.com/library/mysql, repository does not exist or may require 'docker login': denied: requested access to the resource is denied
```

## 原因分析

阿里云镜像源的地址可能不正确，或者需要登录认证。

## 解决方案

### 方案 1：使用官方镜像（推荐）

直接使用 Docker Hub 官方镜像，虽然速度可能较慢，但最稳定。

**优点**：
- 无需额外配置
- 稳定可靠
- 自动更新

**缺点**：
- 国内拉取速度较慢
- 可能需要多次重试

**使用方法**：

docker-compose.yaml 中使用：
```yaml
image: mysql:5.7
```

### 方案 2：配置 Docker 镜像加速器

使用 Docker 镜像加速器，如：

1. **阿里云容器镜像服务**
   - 注册：https://cr.console.aliyun.com/
   - 获取加速器地址
   - 配置 Docker Daemon

2. **腾讯云镜像加速**
   - 注册：https://cloud.tencent.com/product/tke
   - 获取加速器地址

3. **网易镜像加速**
   - 地址：https://c.163yun.com/hub

**配置方法**：

编辑 `/etc/docker/daemon.json`：

```json
{
  "registry-mirrors": [
    "https://<your-accelerator>.mirror.aliyuncs.com"
  ]
}
```

重启 Docker：
```bash
sudo systemctl restart docker
```

### 方案 3：手动拉取镜像

如果网络不稳定，可以手动拉取镜像：

```bash
# 拉取镜像
docker pull mysql:5.7

# 查看本地镜像
docker images | grep mysql
```

### 方案 4：使用其他镜像源

使用其他可用的镜像源：

**网易镜像**：
```yaml
image: hub.c.163.com/library/mysql:5.7
```

**中科大镜像**：
```yaml
image: docker.mirrors.ustc.edu.cn/library/mysql:5.7
```

## 当前配置

项目已恢复为官方镜像：

```yaml
image: mysql:5.7
```

**注意**：如果网络不稳定，建议使用镜像加速器或手动拉取镜像。

## 网络问题解决方案

### 方案 A：配置 Docker 镜像加速器（最推荐）

**阿里云镜像加速器**：

1. 访问 https://cr.console.aliyun.com/cn-hangzhou/instances/mirrors
2. 注册/登录阿里云账号
3. 复制专属加速器地址
4. 配置 Docker Daemon

**配置步骤**：

```bash
# 创建配置目录
sudo mkdir -p /etc/docker

# 配置镜像加速器（替换 <your-accelerator> 为你的实际地址）
sudo tee /etc/docker/daemon.json > /dev/null <<EOF
{
  "registry-mirrors": [
    "https://<your-accelerator>.mirror.aliyuncs.com"
  ]
}
EOF

# 重启 Docker
sudo systemctl restart docker

# 验证配置
docker info | grep -A 10 "Registry Mirrors"
```

**其他镜像加速器**：

- **腾讯云**：https://mirror.ccs.tencentyun.com
- **网易云**：https://hub-mirror.c.163yun.com
- **中科大**：https://docker.mirrors.ustc.edu.cn

### 方案 B：手动拉取镜像（网络不稳定时）

如果网络不稳定，可以手动多次尝试拉取：

```bash
# 在 mysql-db 目录下
cd /Users/pix/dev/code/java/DnfGameServer/mysql-db

# 手动拉取镜像（可能需要多次尝试）
docker pull mysql:5.7

# 如果失败，重试
docker pull mysql:5.7

# 查看是否成功
docker images | grep mysql
```

**预期输出**：
```
mysql   5.7   <image-id>   <time ago>   448MB
```

### 方案 C：使用本地镜像文件

如果完全无法拉取镜像，可以从其他地方获取镜像文件：

```bash
# 从其他机器导出镜像
docker save mysql:5.7 | gzip > mysql-5.7.tar.gz

# 在本机导入镜像
docker load < mysql-5.7.tar.gz

# 验证镜像
docker images | grep mysql
```

### 方案 D：使用简化配置（无镜像拉取）

如果暂时无法解决网络问题，可以使用本地 MySQL：

1. 安装 MySQL：`brew install mysql`
2. 启动 MySQL：`brew services start mysql`
3. 创建数据库：参考 [init.sql](../mysql-db/init.sql)
4. 启动游戏服务器：`./start.sh`

## 使用建议

### 首次启动

1. 确保网络连接正常
2. 运行 `./start-mysql.sh`
3. 如果拉取失败，等待重试或使用加速器

### 网络不稳定时

1. 使用镜像加速器（方案 2）
2. 或手动拉取镜像（方案 3）
3. 或使用国内镜像源（方案 4）

## 验证镜像

拉取成功后，验证镜像：

```bash
docker images | grep mysql
```

应该看到：
```
mysql   5.7   <image-id>   <time ago>   448MB
```

## 故障排查

### 问题 1：拉取超时

**解决方案**：
- 使用镜像加速器
- 或手动拉取镜像
- 检查网络连接

### 问题 2：权限错误

**解决方案**：
- 检查 Docker 是否有权限
- 使用 sudo 运行
- 检查镜像源是否需要登录

### 问题 3：镜像不存在

**解决方案**：
- 检查镜像名称是否正确
- 使用官方镜像
- 查看镜像仓库文档

## 相关资源

- Docker Hub：https://hub.docker.com/_/mysql
- Docker 文档：https://docs.docker.com/
- 阿里云镜像服务：https://cr.console.aliyun.com/
