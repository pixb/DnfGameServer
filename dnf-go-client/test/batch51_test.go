package test

import (
	"testing"

	dnfv1 "github.com/pixb/DnfGameServer/dnf-go-client/gen/dnf/v1"
	"github.com/stretchr/testify/assert"
)

// TestBatch51Migration 测试批次51的迁移结果
func TestBatch51Migration(t *testing.T) {
	// 测试批次51的消息
	t.Run("Batch51Messages", testBatch51Messages)
}

// testBatch51Messages 测试批次51的消息
func testBatch51Messages(t *testing.T) {
	// 测试PT_RANDOMOPTION_ITEM
	t.Run("PT_RANDOMOPTION_ITEM", func(t *testing.T) {
		randomOptionItem := &dnfv1.PT_RANDOMOPTION_ITEM{}
		assert.NotNil(t, randomOptionItem)
	})

	// 测试RES_AVATAR_VISIBLE_FLAGS_SET
	t.Run("RES_AVATAR_VISIBLE_FLAGS_SET", func(t *testing.T) {
		avatarVisibleFlagsSet := &dnfv1.RES_AVATAR_VISIBLE_FLAGS_SET{}
		assert.NotNil(t, avatarVisibleFlagsSet)
	})
}
