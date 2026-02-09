package mysql

import (
	"context"
	"fmt"
	"strings"

	"github.com/pixb/DnfGameServer/dnf-go-server/store"
)

// GetSkill 获取技能
func (d *DB) GetSkill(ctx context.Context, find *store.FindSkill) (*store.Skill, error) {
	var conditions []string
	var args []interface{}

	if find.ID != nil {
		conditions = append(conditions, "id = ?")
		args = append(args, *find.ID)
	}
	if find.SkillID != nil {
		conditions = append(conditions, "skill_id = ?")
		args = append(args, *find.SkillID)
	}
	if find.JobRequired != nil {
		conditions = append(conditions, "job_required = ?")
		args = append(args, *find.JobRequired)
	}
	if find.Type != nil {
		conditions = append(conditions, "type = ?")
		args = append(args, *find.Type)
	}

	where := ""
	if len(conditions) > 0 {
		where = "WHERE " + strings.Join(conditions, " AND ")
	}

	query := fmt.Sprintf(`
		SELECT id, skill_id, name, description, level, max_level, sp, tp,
		       type, job_required, level_required, pre_skill_id, pre_skill_level
		FROM skills %s LIMIT 1
	`, where)

	var skill store.Skill
	err := d.db.QueryRowContext(ctx, query, args...).Scan(
		&skill.ID, &skill.SkillID, &skill.Name, &skill.Description,
		&skill.Level, &skill.MaxLevel, &skill.SP, &skill.TP,
		&skill.Type, &skill.JobRequired, &skill.LevelRequired,
		&skill.PreSkillID, &skill.PreSkillLevel,
	)
	if err != nil {
		return nil, fmt.Errorf("failed to get skill: %w", err)
	}

	return &skill, nil
}

// ListSkills 获取技能列表
func (d *DB) ListSkills(ctx context.Context, find *store.FindSkill) ([]*store.Skill, error) {
	var conditions []string
	var args []interface{}

	if find.SkillID != nil {
		conditions = append(conditions, "skill_id = ?")
		args = append(args, *find.SkillID)
	}
	if find.JobRequired != nil {
		conditions = append(conditions, "job_required = ?")
		args = append(args, *find.JobRequired)
	}
	if find.Type != nil {
		conditions = append(conditions, "type = ?")
		args = append(args, *find.Type)
	}

	where := ""
	if len(conditions) > 0 {
		where = "WHERE " + strings.Join(conditions, " AND ")
	}

	query := fmt.Sprintf(`
		SELECT id, skill_id, name, description, level, max_level, sp, tp,
		       type, job_required, level_required, pre_skill_id, pre_skill_level
		FROM skills %s
	`, where)

	rows, err := d.db.QueryContext(ctx, query, args...)
	if err != nil {
		return nil, fmt.Errorf("failed to list skills: %w", err)
	}
	defer rows.Close()

	var skills []*store.Skill
	for rows.Next() {
		var skill store.Skill
		err := rows.Scan(
			&skill.ID, &skill.SkillID, &skill.Name, &skill.Description,
			&skill.Level, &skill.MaxLevel, &skill.SP, &skill.TP,
			&skill.Type, &skill.JobRequired, &skill.LevelRequired,
			&skill.PreSkillID, &skill.PreSkillLevel,
		)
		if err != nil {
			return nil, fmt.Errorf("failed to scan skill: %w", err)
		}
		skills = append(skills, &skill)
	}

	return skills, nil
}

// GetRoleSkill 获取角色技能
func (d *DB) GetRoleSkill(ctx context.Context, find *store.FindRoleSkill) (*store.RoleSkill, error) {
	var conditions []string
	var args []interface{}

	if find.ID != nil {
		conditions = append(conditions, "id = ?")
		args = append(args, *find.ID)
	}
	if find.RoleID != nil {
		conditions = append(conditions, "role_id = ?")
		args = append(args, *find.RoleID)
	}
	if find.SkillID != nil {
		conditions = append(conditions, "skill_id = ?")
		args = append(args, *find.SkillID)
	}

	where := ""
	if len(conditions) > 0 {
		where = "WHERE " + strings.Join(conditions, " AND ")
	}

	query := fmt.Sprintf(`
		SELECT id, role_id, skill_id, level, is_learned
		FROM role_skills %s LIMIT 1
	`, where)

	var roleSkill store.RoleSkill
	var isLearned int
	err := d.db.QueryRowContext(ctx, query, args...).Scan(
		&roleSkill.ID, &roleSkill.RoleID, &roleSkill.SkillID,
		&roleSkill.Level, &isLearned,
	)
	if err != nil {
		return nil, fmt.Errorf("failed to get role skill: %w", err)
	}
	roleSkill.IsLearned = isLearned == 1

	return &roleSkill, nil
}

// ListRoleSkills 获取角色技能列表
func (d *DB) ListRoleSkills(ctx context.Context, roleID uint64) ([]*store.RoleSkill, error) {
	query := `
		SELECT id, role_id, skill_id, level, is_learned
		FROM role_skills WHERE role_id = ?
	`

	rows, err := d.db.QueryContext(ctx, query, roleID)
	if err != nil {
		return nil, fmt.Errorf("failed to list role skills: %w", err)
	}
	defer rows.Close()

	var skills []*store.RoleSkill
	for rows.Next() {
		var roleSkill store.RoleSkill
		var isLearned int
		err := rows.Scan(
			&roleSkill.ID, &roleSkill.RoleID, &roleSkill.SkillID,
			&roleSkill.Level, &isLearned,
		)
		if err != nil {
			return nil, fmt.Errorf("failed to scan role skill: %w", err)
		}
		roleSkill.IsLearned = isLearned == 1
		skills = append(skills, &roleSkill)
	}

	return skills, nil
}

// CreateRoleSkill 创建角色技能
func (d *DB) CreateRoleSkill(ctx context.Context, create *store.CreateRoleSkill) (*store.RoleSkill, error) {
	query := `
		INSERT INTO role_skills (role_id, skill_id, level, is_learned)
		VALUES (?, ?, ?, ?)
	`

	isLearned := 0
	if create.IsLearned {
		isLearned = 1
	}

	_, err := d.db.ExecContext(ctx, query,
		create.RoleID, create.SkillID, create.Level, isLearned,
	)
	if err != nil {
		return nil, fmt.Errorf("failed to create role skill: %w", err)
	}

	return &store.RoleSkill{
		RoleID:    create.RoleID,
		SkillID:   create.SkillID,
		Level:     create.Level,
		IsLearned: create.IsLearned,
	}, nil
}

// UpdateRoleSkill 更新角色技能
func (d *DB) UpdateRoleSkill(ctx context.Context, update *store.UpdateRoleSkill) error {
	var sets []string
	var args []interface{}

	if update.Level != nil {
		sets = append(sets, "level = ?")
		args = append(args, *update.Level)
	}
	if update.IsLearned != nil {
		isLearned := 0
		if *update.IsLearned {
			isLearned = 1
		}
		sets = append(sets, "is_learned = ?")
		args = append(args, isLearned)
	}

	if len(sets) == 0 {
		return nil
	}

	args = append(args, update.ID)
	query := fmt.Sprintf("UPDATE role_skills SET %s WHERE id = ?", strings.Join(sets, ", "))

	_, err := d.db.ExecContext(ctx, query, args...)
	if err != nil {
		return fmt.Errorf("failed to update role skill: %w", err)
	}

	return nil
}
