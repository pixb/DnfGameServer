package store

import (
	"context"
	"sync"
	"time"
)

// Config 缓存配置
type Config struct {
	DefaultTTL      time.Duration
	CleanupInterval time.Duration
	MaxItems        int
}

// Cache 内存缓存
type Cache struct {
	items           map[string]*cacheItem
	mu              sync.RWMutex
	defaultTTL      time.Duration
	cleanupInterval time.Duration
	maxItems        int
	stopCleanup     chan struct{}
}

type cacheItem struct {
	value      interface{}
	expiration int64
}

// NewCache 创建缓存实例
func NewCache(config Config) *Cache {
	if config.DefaultTTL == 0 {
		config.DefaultTTL = 10 * time.Minute
	}
	if config.CleanupInterval == 0 {
		config.CleanupInterval = 5 * time.Minute
	}
	if config.MaxItems == 0 {
		config.MaxItems = 1000
	}

	c := &Cache{
		items:           make(map[string]*cacheItem),
		defaultTTL:      config.DefaultTTL,
		cleanupInterval: config.CleanupInterval,
		maxItems:        config.MaxItems,
		stopCleanup:     make(chan struct{}),
	}

	go c.cleanup()
	return c
}

// Set 设置缓存
func (c *Cache) Set(ctx context.Context, key string, value interface{}) {
	c.mu.Lock()
	defer c.mu.Unlock()

	// LRU: 如果超过最大项数，删除最旧的
	if len(c.items) >= c.maxItems {
		c.evictOldest(100)
	}

	c.items[key] = &cacheItem{
		value:      value,
		expiration: time.Now().Add(c.defaultTTL).UnixNano(),
	}
}

// Get 获取缓存
func (c *Cache) Get(ctx context.Context, key string) (interface{}, bool) {
	c.mu.RLock()
	defer c.mu.RUnlock()

	item, ok := c.items[key]
	if !ok {
		return nil, false
	}

	if time.Now().UnixNano() > item.expiration {
		return nil, false
	}

	return item.value, true
}

// Delete 删除缓存
func (c *Cache) Delete(ctx context.Context, key string) {
	c.mu.Lock()
	defer c.mu.Unlock()
	delete(c.items, key)
}

// Clear 清空缓存
func (c *Cache) Clear() {
	c.mu.Lock()
	defer c.mu.Unlock()
	c.items = make(map[string]*cacheItem)
}

// Close 关闭缓存
func (c *Cache) Close() {
	close(c.stopCleanup)
}

// cleanup 定期清理过期项
func (c *Cache) cleanup() {
	ticker := time.NewTicker(c.cleanupInterval)
	defer ticker.Stop()

	for {
		select {
		case <-ticker.C:
			c.deleteExpired()
		case <-c.stopCleanup:
			return
		}
	}
}

// deleteExpired 删除过期项
func (c *Cache) deleteExpired() {
	c.mu.Lock()
	defer c.mu.Unlock()

	now := time.Now().UnixNano()
	for k, v := range c.items {
		if now > v.expiration {
			delete(c.items, k)
		}
	}
}

// evictOldest 淘汰最旧的项
func (c *Cache) evictOldest(count int) {
	// 简单策略: 随机删除一些项
	// 生产环境可以使用LRU链表
	i := 0
	for k := range c.items {
		if i >= count {
			break
		}
		delete(c.items, k)
		i++
	}
}

// Len 返回缓存项数
func (c *Cache) Len() int {
	c.mu.RLock()
	defer c.mu.RUnlock()
	return len(c.items)
}
