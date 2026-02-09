package test

import (
	"testing"

	dnfv1 "github.com/pixb/DnfGameServer/dnf-go-client/gen/dnf/v1"
	"google.golang.org/protobuf/proto"
)

// 测试邮件列表请求消息
func TestBatch20MailListRequest(t *testing.T) {
	req := &dnfv1.MailListRequest{
		Page: 1,
		Type: 1,
	}

	data, err := proto.Marshal(req)
	if err != nil {
		t.Fatalf("Failed to marshal MailListRequest: %v", err)
	}

	unmarshaled := &dnfv1.MailListRequest{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal MailListRequest: %v", err)
	}

	if unmarshaled.Page != req.Page {
		t.Errorf("Page mismatch: got %d, want %d", unmarshaled.Page, req.Page)
	}

	if unmarshaled.Type != req.Type {
		t.Errorf("Type mismatch: got %d, want %d", unmarshaled.Type, req.Type)
	}
}

// 测试邮件列表响应消息
func TestBatch20MailListResponse(t *testing.T) {
	resp := &dnfv1.MailListResponse{
		Error:      0,
		TotalCount: 10,
		Page:       1,
	}

	data, err := proto.Marshal(resp)
	if err != nil {
		t.Fatalf("Failed to marshal MailListResponse: %v", err)
	}

	unmarshaled := &dnfv1.MailListResponse{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal MailListResponse: %v", err)
	}

	if unmarshaled.Error != resp.Error {
		t.Errorf("Error mismatch: got %d, want %d", unmarshaled.Error, resp.Error)
	}

	if unmarshaled.TotalCount != resp.TotalCount {
		t.Errorf("TotalCount mismatch: got %d, want %d", unmarshaled.TotalCount, resp.TotalCount)
	}

	if unmarshaled.Page != resp.Page {
		t.Errorf("Page mismatch: got %d, want %d", unmarshaled.Page, resp.Page)
	}
}

// 测试邮件获取请求消息
func TestBatch20MailGetRequest(t *testing.T) {
	req := &dnfv1.MailGetRequest{
		Type: 1,
		Guid: 1234567890,
	}

	data, err := proto.Marshal(req)
	if err != nil {
		t.Fatalf("Failed to marshal MailGetRequest: %v", err)
	}

	unmarshaled := &dnfv1.MailGetRequest{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal MailGetRequest: %v", err)
	}

	if unmarshaled.Type != req.Type {
		t.Errorf("Type mismatch: got %d, want %d", unmarshaled.Type, req.Type)
	}

	if unmarshaled.Guid != req.Guid {
		t.Errorf("Guid mismatch: got %d, want %d", unmarshaled.Guid, req.Guid)
	}
}

// 测试邮件获取响应消息
func TestBatch20MailGetResponse(t *testing.T) {
	resp := &dnfv1.MailGetResponse{
		Error: 0,
	}

	data, err := proto.Marshal(resp)
	if err != nil {
		t.Fatalf("Failed to marshal MailGetResponse: %v", err)
	}

	unmarshaled := &dnfv1.MailGetResponse{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal MailGetResponse: %v", err)
	}

	if unmarshaled.Error != resp.Error {
		t.Errorf("Error mismatch: got %d, want %d", unmarshaled.Error, resp.Error)
	}
}

// 测试邮件读取请求消息
func TestBatch20MailReadRequest(t *testing.T) {
	req := &dnfv1.MailReadRequest{
		Guid: 1234567890,
		Type: 1,
	}

	data, err := proto.Marshal(req)
	if err != nil {
		t.Fatalf("Failed to marshal MailReadRequest: %v", err)
	}

	unmarshaled := &dnfv1.MailReadRequest{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal MailReadRequest: %v", err)
	}

	if unmarshaled.Guid != req.Guid {
		t.Errorf("Guid mismatch: got %d, want %d", unmarshaled.Guid, req.Guid)
	}

	if unmarshaled.Type != req.Type {
		t.Errorf("Type mismatch: got %d, want %d", unmarshaled.Type, req.Type)
	}
}

// 测试邮件读取响应消息
func TestBatch20MailReadResponse(t *testing.T) {
	resp := &dnfv1.MailReadResponse{
		Error:   0,
		Guid:    1234567890,
		Success: true,
	}

	data, err := proto.Marshal(resp)
	if err != nil {
		t.Fatalf("Failed to marshal MailReadResponse: %v", err)
	}

	unmarshaled := &dnfv1.MailReadResponse{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal MailReadResponse: %v", err)
	}

	if unmarshaled.Error != resp.Error {
		t.Errorf("Error mismatch: got %d, want %d", unmarshaled.Error, resp.Error)
	}

	if unmarshaled.Guid != resp.Guid {
		t.Errorf("Guid mismatch: got %d, want %d", unmarshaled.Guid, resp.Guid)
	}

	if unmarshaled.Success != resp.Success {
		t.Errorf("Success mismatch: got %v, want %v", unmarshaled.Success, resp.Success)
	}
}

// 测试邮件删除请求消息
func TestBatch20MailDeleteRequest(t *testing.T) {
	req := &dnfv1.MailDeleteRequest{
		Guid: 1234567890,
		Type: 1,
	}

	data, err := proto.Marshal(req)
	if err != nil {
		t.Fatalf("Failed to marshal MailDeleteRequest: %v", err)
	}

	unmarshaled := &dnfv1.MailDeleteRequest{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal MailDeleteRequest: %v", err)
	}

	if unmarshaled.Guid != req.Guid {
		t.Errorf("Guid mismatch: got %d, want %d", unmarshaled.Guid, req.Guid)
	}

	if unmarshaled.Type != req.Type {
		t.Errorf("Type mismatch: got %d, want %d", unmarshaled.Type, req.Type)
	}
}

// 测试邮件删除响应消息
func TestBatch20MailDeleteResponse(t *testing.T) {
	resp := &dnfv1.MailDeleteResponse{
		Error:   0,
		Guid:    1234567890,
		Success: true,
	}

	data, err := proto.Marshal(resp)
	if err != nil {
		t.Fatalf("Failed to marshal MailDeleteResponse: %v", err)
	}

	unmarshaled := &dnfv1.MailDeleteResponse{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal MailDeleteResponse: %v", err)
	}

	if unmarshaled.Error != resp.Error {
		t.Errorf("Error mismatch: got %d, want %d", unmarshaled.Error, resp.Error)
	}

	if unmarshaled.Guid != resp.Guid {
		t.Errorf("Guid mismatch: got %d, want %d", unmarshaled.Guid, resp.Guid)
	}

	if unmarshaled.Success != resp.Success {
		t.Errorf("Success mismatch: got %v, want %v", unmarshaled.Success, resp.Success)
	}
}

// 测试邮件物品全部获取请求消息
func TestBatch20MailItemAllGetRequest(t *testing.T) {
	req := &dnfv1.MailItemAllGetRequest{
		Type: 1,
	}

	data, err := proto.Marshal(req)
	if err != nil {
		t.Fatalf("Failed to marshal MailItemAllGetRequest: %v", err)
	}

	unmarshaled := &dnfv1.MailItemAllGetRequest{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal MailItemAllGetRequest: %v", err)
	}

	if unmarshaled.Type != req.Type {
		t.Errorf("Type mismatch: got %d, want %d", unmarshaled.Type, req.Type)
	}
}

// 测试邮件物品全部获取响应消息
func TestBatch20MailItemAllGetResponse(t *testing.T) {
	resp := &dnfv1.MailItemAllGetResponse{
		Error:      0,
		TotalCount: 5,
	}

	data, err := proto.Marshal(resp)
	if err != nil {
		t.Fatalf("Failed to marshal MailItemAllGetResponse: %v", err)
	}

	unmarshaled := &dnfv1.MailItemAllGetResponse{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal MailItemAllGetResponse: %v", err)
	}

	if unmarshaled.Error != resp.Error {
		t.Errorf("Error mismatch: got %d, want %d", unmarshaled.Error, resp.Error)
	}

	if unmarshaled.TotalCount != resp.TotalCount {
		t.Errorf("TotalCount mismatch: got %d, want %d", unmarshaled.TotalCount, resp.TotalCount)
	}
}

// 测试邮件全部删除请求消息
func TestBatch20MailAllDeleteRequest(t *testing.T) {
	req := &dnfv1.MailAllDeleteRequest{
		Type: 1,
	}

	data, err := proto.Marshal(req)
	if err != nil {
		t.Fatalf("Failed to marshal MailAllDeleteRequest: %v", err)
	}

	unmarshaled := &dnfv1.MailAllDeleteRequest{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal MailAllDeleteRequest: %v", err)
	}

	if unmarshaled.Type != req.Type {
		t.Errorf("Type mismatch: got %d, want %d", unmarshaled.Type, req.Type)
	}
}

// 测试邮件全部删除响应消息
func TestBatch20MailAllDeleteResponse(t *testing.T) {
	resp := &dnfv1.MailAllDeleteResponse{
		Error:        0,
		Type:         1,
		DeletedCount: 10,
		Success:      true,
	}

	data, err := proto.Marshal(resp)
	if err != nil {
		t.Fatalf("Failed to marshal MailAllDeleteResponse: %v", err)
	}

	unmarshaled := &dnfv1.MailAllDeleteResponse{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal MailAllDeleteResponse: %v", err)
	}

	if unmarshaled.Error != resp.Error {
		t.Errorf("Error mismatch: got %d, want %d", unmarshaled.Error, resp.Error)
	}

	if unmarshaled.Type != resp.Type {
		t.Errorf("Type mismatch: got %d, want %d", unmarshaled.Type, resp.Type)
	}

	if unmarshaled.DeletedCount != resp.DeletedCount {
		t.Errorf("DeletedCount mismatch: got %d, want %d", unmarshaled.DeletedCount, resp.DeletedCount)
	}

	if unmarshaled.Success != resp.Success {
		t.Errorf("Success mismatch: got %v, want %v", unmarshaled.Success, resp.Success)
	}
}

// 测试邮件信息
func TestBatch20MailInfo(t *testing.T) {
	mailInfo := &dnfv1.MailInfo{
		Guid:          1234567890,
		Title:         "Test Mail",
		Content:       "This is a test mail content",
		Type:          1,
		Status:        1,
		SendTime:      1234567890,
		ExpireTime:    12345678900,
		HasAttachment: true,
		IsRead:        false,
	}

	data, err := proto.Marshal(mailInfo)
	if err != nil {
		t.Fatalf("Failed to marshal MailInfo: %v", err)
	}

	unmarshaled := &dnfv1.MailInfo{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal MailInfo: %v", err)
	}

	if unmarshaled.Guid != mailInfo.Guid {
		t.Errorf("Guid mismatch: got %d, want %d", unmarshaled.Guid, mailInfo.Guid)
	}

	if unmarshaled.Title != mailInfo.Title {
		t.Errorf("Title mismatch: got %s, want %s", unmarshaled.Title, mailInfo.Title)
	}

	if unmarshaled.Content != mailInfo.Content {
		t.Errorf("Content mismatch: got %s, want %s", unmarshaled.Content, mailInfo.Content)
	}

	if unmarshaled.Type != mailInfo.Type {
		t.Errorf("Type mismatch: got %d, want %d", unmarshaled.Type, mailInfo.Type)
	}

	if unmarshaled.Status != mailInfo.Status {
		t.Errorf("Status mismatch: got %d, want %d", unmarshaled.Status, mailInfo.Status)
	}

	if unmarshaled.SendTime != mailInfo.SendTime {
		t.Errorf("SendTime mismatch: got %d, want %d", unmarshaled.SendTime, mailInfo.SendTime)
	}

	if unmarshaled.ExpireTime != mailInfo.ExpireTime {
		t.Errorf("ExpireTime mismatch: got %d, want %d", unmarshaled.ExpireTime, mailInfo.ExpireTime)
	}

	if unmarshaled.HasAttachment != mailInfo.HasAttachment {
		t.Errorf("HasAttachment mismatch: got %v, want %v", unmarshaled.HasAttachment, mailInfo.HasAttachment)
	}

	if unmarshaled.IsRead != mailInfo.IsRead {
		t.Errorf("IsRead mismatch: got %v, want %v", unmarshaled.IsRead, mailInfo.IsRead)
	}
}

// 测试选中的物品
func TestBatch20SelectedItem(t *testing.T) {
	selectedItem := &dnfv1.SelectedItem{
		Guid:  1234567890,
		Count: 5,
	}

	data, err := proto.Marshal(selectedItem)
	if err != nil {
		t.Fatalf("Failed to marshal SelectedItem: %v", err)
	}

	unmarshaled := &dnfv1.SelectedItem{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal SelectedItem: %v", err)
	}

	if unmarshaled.Guid != selectedItem.Guid {
		t.Errorf("Guid mismatch: got %d, want %d", unmarshaled.Guid, selectedItem.Guid)
	}

	if unmarshaled.Count != selectedItem.Count {
		t.Errorf("Count mismatch: got %d, want %d", unmarshaled.Count, selectedItem.Count)
	}
}

// 测试邮寄包裹
func TestBatch20PostPackage(t *testing.T) {
	postPackage := &dnfv1.PostPackage{
		Guid:  1234567890,
		Count: 3,
	}

	data, err := proto.Marshal(postPackage)
	if err != nil {
		t.Fatalf("Failed to marshal PostPackage: %v", err)
	}

	unmarshaled := &dnfv1.PostPackage{}
	if err := proto.Unmarshal(data, unmarshaled); err != nil {
		t.Fatalf("Failed to unmarshal PostPackage: %v", err)
	}

	if unmarshaled.Guid != postPackage.Guid {
		t.Errorf("Guid mismatch: got %d, want %d", unmarshaled.Guid, postPackage.Guid)
	}

	if unmarshaled.Count != postPackage.Count {
		t.Errorf("Count mismatch: got %d, want %d", unmarshaled.Count, postPackage.Count)
	}
}
