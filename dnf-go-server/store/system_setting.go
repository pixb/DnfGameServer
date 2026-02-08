package store

// InstanceBasicSetting 实例基本设置
type InstanceBasicSetting struct {
	SecretKey string
	Version   string
	Name      string
}

// InstanceSetting 实例设置
type InstanceSetting struct {
	Key         string
	Value       string
	Description string
}
