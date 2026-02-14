package sqlite

import (
	"context"
	"database/sql"
	"fmt"

	dnfv1 "github.com/pixb/DnfGameServer/dnf-go-server/proto/gen/dnf/v1"
	"github.com/pixb/DnfGameServer/dnf-go-server/store"
)

// ==================== 组队相关实现 ====================

func (d *DB) SearchPartyList(ctx context.Context, dungeonIndex, minLevel, maxLevel uint32) ([]*store.PartyInfo, error) {
	query := `
		SELECT p.party_id, p.leader_id, p.name, p.max_members,
		       p.dungeon_index, p.room_id, p.min_level, p.max_level,
		       p.area, p.subtype, p.stage_index, p.public_type
		FROM t_party p
		WHERE p.status = 0
	`

	args := []interface{}{}
	argIndex := 1

	if dungeonIndex > 0 {
		query += fmt.Sprintf(" AND p.dungeon_index = $%d", argIndex)
		args = append(args, dungeonIndex)
		argIndex++
	}

	if minLevel > 0 {
		query += fmt.Sprintf(" AND p.min_level >= $%d", argIndex)
		args = append(args, minLevel)
		argIndex++
	}

	if maxLevel > 0 {
		query += fmt.Sprintf(" AND p.max_level <= $%d", argIndex)
		args = append(args, maxLevel)
		argIndex++
	}

	query += " ORDER BY p.create_time DESC LIMIT 100"

	rows, err := d.db.QueryContext(ctx, query, args...)
	if err != nil {
		return nil, fmt.Errorf("failed to search party list: %w", err)
	}
	defer rows.Close()

	var parties []*store.PartyInfo
	for rows.Next() {
		party := &store.PartyInfo{}
		err := rows.Scan(
			&party.PartyGuid, &party.LeaderGuid, &party.Name, &party.MaxMembers,
			&party.DungeonIndex, &party.RoomID, &party.MinLevel, &party.MaxLevel,
			&party.Area, &party.SubType, &party.StageIndex, &party.PublicType,
		)
		if err != nil {
			return nil, fmt.Errorf("failed to scan party: %w", err)
		}

		members, err := d.getPartyMembers(ctx, party.PartyGuid)
		if err != nil {
			return nil, fmt.Errorf("failed to get party members: %w", err)
		}
		party.Members = members

		parties = append(parties, party)
	}

	return parties, nil
}

func (d *DB) RecommendGroup(ctx context.Context, dungeonIndex uint32) ([]*store.PartyInfo, error) {
	query := `
		SELECT p.party_id, p.leader_id, p.name, p.max_members,
		       p.dungeon_index, p.room_id, p.min_level, p.max_level,
		       p.area, p.subtype, p.stage_index, p.public_type
		FROM t_party p
		WHERE p.status = 0 AND p.dungeon_index = ?
		ORDER BY p.create_time DESC
		LIMIT 10
	`

	rows, err := d.db.QueryContext(ctx, query, dungeonIndex)
	if err != nil {
		return nil, fmt.Errorf("failed to recommend group: %w", err)
	}
	defer rows.Close()

	var parties []*store.PartyInfo
	for rows.Next() {
		party := &store.PartyInfo{}
		err := rows.Scan(
			&party.PartyGuid, &party.LeaderGuid, &party.Name, &party.MaxMembers,
			&party.DungeonIndex, &party.RoomID, &party.MinLevel, &party.MaxLevel,
			&party.Area, &party.SubType, &party.StageIndex, &party.PublicType,
		)
		if err != nil {
			return nil, fmt.Errorf("failed to scan party: %w", err)
		}

		members, err := d.getPartyMembers(ctx, party.PartyGuid)
		if err != nil {
			return nil, fmt.Errorf("failed to get party members: %w", err)
		}
		party.Members = members

		parties = append(parties, party)
	}

	return parties, nil
}

func (d *DB) ControlGroup(ctx context.Context, roleID uint64, action uint32, targetGuid uint64, partyGuid uint64) error {
	switch action {
	case 0:
		return d.createParty(ctx, roleID)
	case 1:
		return d.inviteToParty(ctx, roleID, targetGuid, partyGuid)
	case 2:
		return d.leaveParty(ctx, roleID)
	case 3:
		return d.kickFromParty(ctx, roleID, targetGuid)
	case 4:
		return d.changePartyLeader(ctx, roleID, targetGuid)
	default:
		return fmt.Errorf("unknown action: %d", action)
	}
}

func (d *DB) StartMultiPlay(ctx context.Context, roleID uint64, partyGuid uint64) (*store.StartMultiPlayResult, error) {
	party, err := d.getPartyByRoleID(ctx, roleID)
	if err != nil {
		return nil, fmt.Errorf("failed to get party: %w", err)
	}

	if party == nil {
		return nil, fmt.Errorf("party not found")
	}

	members, err := d.getPartyMembers(ctx, party.PartyGuid)
	if err != nil {
		return nil, fmt.Errorf("failed to get party members: %w", err)
	}

	users := make([]uint64, len(members))
	for i, member := range members {
		users[i] = member.Charguid
	}

	result := &store.StartMultiPlayResult{
		MatchingGuid: party.PartyGuid,
		DungeonGuid:  party.PartyGuid,
		IP:           "127.0.0.1",
		Port:         8082,
		Users:        users,
	}

	return result, nil
}

func (d *DB) SyncDungeon(ctx context.Context, roleID uint64, stageID, progress uint32) error {
	return nil
}

func (d *DB) MultiPlayDungeonEnterComplete(ctx context.Context, roleID uint64, stageID uint32) error {
	return nil
}

func (d *DB) GetPartyLoadingStatus(ctx context.Context, roleID uint64) ([]uint64, []uint64, error) {
	party, err := d.getPartyByRoleID(ctx, roleID)
	if err != nil {
		return nil, nil, fmt.Errorf("failed to get party: %w", err)
	}

	if party == nil {
		return nil, nil, fmt.Errorf("party not found")
	}

	members, err := d.getPartyMembers(ctx, party.PartyGuid)
	if err != nil {
		return nil, nil, fmt.Errorf("failed to get party members: %w", err)
	}

	loadingUsers := make([]uint64, 0)
	readyUsers := make([]uint64, 0)

	for _, member := range members {
		if member.Ping == 0 {
			loadingUsers = append(loadingUsers, member.Charguid)
		} else {
			readyUsers = append(readyUsers, member.Charguid)
		}
	}

	return loadingUsers, readyUsers, nil
}

func (d *DB) ReadyToLockstep(ctx context.Context, roleID uint64, ready bool) error {
	return nil
}

func (d *DB) VoteKickOut(ctx context.Context, roleID, targetGuid uint64) error {
	return d.kickFromParty(ctx, roleID, targetGuid)
}

func (d *DB) ConnectBattleServer(ctx context.Context, matchingGuid, charguid uint64, authKey string) (string, uint32, error) {
	return "127.0.0.1", 8082, nil
}

func (d *DB) CheckProhibitedWord(ctx context.Context, word string) (bool, error) {
	return false, nil
}

func (d *DB) HalfOpenPartyAccept(ctx context.Context, roleID, partyGuid uint64) error {
	return d.inviteToParty(ctx, roleID, roleID, partyGuid)
}

func (d *DB) HalfOpenPartyRefuse(ctx context.Context, roleID, partyGuid uint64) error {
	return nil
}

func (d *DB) ControlGroupCustom(ctx context.Context, roleID uint64, customData []byte) error {
	return nil
}

func (d *DB) ControlGroupQueryarea(ctx context.Context, roleID uint64) error {
	return nil
}

func (d *DB) HalfOpenPartyJoin(ctx context.Context, roleID, partyGuid uint64) error {
	return d.inviteToParty(ctx, roleID, roleID, partyGuid)
}

func (d *DB) PartyDungeonCondition(ctx context.Context, roleID uint64, dungeonIndex uint32) error {
	return nil
}

func (d *DB) MultiPlayStartDungeon(ctx context.Context, roleID uint64, stageID uint32) error {
	return nil
}

func (d *DB) RequestToReEnterAcceptDungeon(ctx context.Context, roleID, charguid uint64) error {
	return nil
}

func (d *DB) RequestToReEnterDungeon(ctx context.Context, roleID, charguid uint64) error {
	return nil
}

func (d *DB) SuggestMoveParty(ctx context.Context, roleID uint64, area uint32) error {
	return nil
}

func (d *DB) TargetUserPartyInfo(ctx context.Context, roleID, targetGuid uint64) (*store.PartyInfo, error) {
	party, err := d.getPartyByRoleID(ctx, targetGuid)
	if err != nil {
		return nil, fmt.Errorf("failed to get party: %w", err)
	}

	if party == nil {
		return nil, fmt.Errorf("party not found")
	}

	members, err := d.getPartyMembers(ctx, party.PartyGuid)
	if err != nil {
		return nil, fmt.Errorf("failed to get party members: %w", err)
	}
	party.Members = members

	return party, nil
}

func (d *DB) WaitinigToUsersLoading(ctx context.Context, roleID uint64) error {
	return nil
}

func (d *DB) getPartyByRoleID(ctx context.Context, roleID uint64) (*store.PartyInfo, error) {
	query := `
		SELECT p.party_id, p.leader_id, p.name, p.max_members,
		       p.dungeon_index, p.room_id, p.min_level, p.max_level,
		       p.area, p.subtype, p.stage_index, p.public_type
		FROM t_party p
		INNER JOIN t_party_member pm ON p.party_id = pm.party_id
		WHERE pm.role_id = ? AND p.status = 0
		LIMIT 1
	`

	party := &store.PartyInfo{}
	err := d.db.QueryRowContext(ctx, query, roleID).Scan(
		&party.PartyGuid, &party.LeaderGuid, &party.Name, &party.MaxMembers,
		&party.DungeonIndex, &party.RoomID, &party.MinLevel, &party.MaxLevel,
		&party.Area, &party.SubType, &party.StageIndex, &party.PublicType,
	)

	if err == sql.ErrNoRows {
		return nil, nil
	}
	if err != nil {
		return nil, fmt.Errorf("failed to get party by role id: %w", err)
	}

	return party, nil
}

func (d *DB) getPartyMembers(ctx context.Context, partyID uint64) ([]*dnfv1.GroupMember, error) {
	query := `
		SELECT pm.role_id, r.name, 0, r.level, 0, '',
		       pm.role_id = p.leader_id, r.fatigue, r.channel, 0, pm.team_type
		FROM t_party_member pm
		INNER JOIN role r ON pm.role_id = r.role_id
		INNER JOIN t_party p ON pm.party_id = p.party_id
		WHERE pm.party_id = ?
	`

	rows, err := d.db.QueryContext(ctx, query, partyID)
	if err != nil {
		return nil, fmt.Errorf("failed to get party members: %w", err)
	}
	defer rows.Close()

	var members []*dnfv1.GroupMember
	for rows.Next() {
		member := &dnfv1.GroupMember{}

		err := rows.Scan(
			&member.Charguid, &member.Name, &member.Equipscore, &member.Level,
			&member.Gguid, &member.Gname, &member.Partyleader, &member.Fatigue,
			&member.World, &member.Creditscore, &member.Teamtype,
		)
		if err != nil {
			return nil, fmt.Errorf("failed to scan party member: %w", err)
		}

		members = append(members, member)
	}

	return members, nil
}

func (d *DB) createParty(ctx context.Context, roleID uint64) error {
	role, err := d.getRoleByID(ctx, roleID)
	if err != nil {
		return fmt.Errorf("failed to get role: %w", err)
	}

	if role == nil {
		return fmt.Errorf("role not found")
	}

	tx, err := d.db.BeginTx(ctx, nil)
	if err != nil {
		return fmt.Errorf("failed to begin transaction: %w", err)
	}
	defer tx.Rollback()

	query := `
		INSERT INTO t_party (leader_id, name, max_members, status, create_time, update_time)
		VALUES (?, ?, 4, 0, datetime('now'), datetime('now'))
	`

	result, err := tx.ExecContext(ctx, query, roleID, role.Name)
	if err != nil {
		return fmt.Errorf("failed to create party: %w", err)
	}

	partyID, err := result.LastInsertId()
	if err != nil {
		return fmt.Errorf("failed to get party id: %w", err)
	}

	memberQuery := `
		INSERT INTO t_party_member (party_id, role_id, player_id, team_type, status, join_time)
		VALUES (?, ?, ?, 0, 0, datetime('now'))
	`

	_, err = tx.ExecContext(ctx, memberQuery, partyID, roleID, role.PlayerID)
	if err != nil {
		return fmt.Errorf("failed to add party member: %w", err)
	}

	return tx.Commit()
}

func (d *DB) inviteToParty(ctx context.Context, roleID, targetGuid, partyGuid uint64) error {
	party, err := d.getPartyByRoleID(ctx, roleID)
	if err != nil {
		return fmt.Errorf("failed to get party: %w", err)
	}

	if party == nil {
		return fmt.Errorf("party not found")
	}

	memberCount, err := d.getPartyMemberCount(ctx, party.PartyGuid)
	if err != nil {
		return fmt.Errorf("failed to get party member count: %w", err)
	}

	if memberCount >= int(party.MaxMembers) {
		return fmt.Errorf("party is full")
	}

	targetRole, err := d.getRoleByID(ctx, targetGuid)
	if err != nil {
		return fmt.Errorf("failed to get target role: %w", err)
	}

	if targetRole == nil {
		return fmt.Errorf("target role not found")
	}

	query := `
		INSERT INTO t_party_member (party_id, role_id, player_id, team_type, status, join_time)
		VALUES (?, ?, ?, 0, 0, datetime('now'))
	`

	_, err = d.db.ExecContext(ctx, query, party.PartyGuid, targetGuid, targetRole.PlayerID)
	if err != nil {
		return fmt.Errorf("failed to invite to party: %w", err)
	}

	return nil
}

func (d *DB) leaveParty(ctx context.Context, roleID uint64) error {
	party, err := d.getPartyByRoleID(ctx, roleID)
	if err != nil {
		return fmt.Errorf("failed to get party: %w", err)
	}

	if party == nil {
		return fmt.Errorf("party not found")
	}

	if party.LeaderGuid == roleID {
		return d.disbandParty(ctx, party.PartyGuid)
	}

	query := `
		DELETE FROM t_party_member
		WHERE party_id = ? AND role_id = ?
	`

	_, err = d.db.ExecContext(ctx, query, party.PartyGuid, roleID)
	if err != nil {
		return fmt.Errorf("failed to leave party: %w", err)
	}

	return nil
}

func (d *DB) kickFromParty(ctx context.Context, roleID, targetGuid uint64) error {
	party, err := d.getPartyByRoleID(ctx, roleID)
	if err != nil {
		return fmt.Errorf("failed to get party: %w", err)
	}

	if party == nil {
		return fmt.Errorf("party not found")
	}

	if party.LeaderGuid != roleID {
		return fmt.Errorf("only leader can kick members")
	}

	query := `
		DELETE FROM t_party_member
		WHERE party_id = ? AND role_id = ?
	`

	_, err = d.db.ExecContext(ctx, query, party.PartyGuid, targetGuid)
	if err != nil {
		return fmt.Errorf("failed to kick from party: %w", err)
	}

	return nil
}

func (d *DB) changePartyLeader(ctx context.Context, roleID, targetGuid uint64) error {
	party, err := d.getPartyByRoleID(ctx, roleID)
	if err != nil {
		return fmt.Errorf("failed to get party: %w", err)
	}

	if party == nil {
		return fmt.Errorf("party not found")
	}

	if party.LeaderGuid != roleID {
		return fmt.Errorf("only leader can change leader")
	}

	query := `
		UPDATE t_party
		SET leader_id = ?
		WHERE party_id = ?
	`

	_, err = d.db.ExecContext(ctx, query, targetGuid, party.PartyGuid)
	if err != nil {
		return fmt.Errorf("failed to change party leader: %w", err)
	}

	return nil
}

func (d *DB) disbandParty(ctx context.Context, partyID uint64) error {
	tx, err := d.db.BeginTx(ctx, nil)
	if err != nil {
		return fmt.Errorf("failed to begin transaction: %w", err)
	}
	defer tx.Rollback()

	_, err = tx.ExecContext(ctx, "DELETE FROM t_party_member WHERE party_id = ?", partyID)
	if err != nil {
		return fmt.Errorf("failed to delete party members: %w", err)
	}

	_, err = tx.ExecContext(ctx, "DELETE FROM t_party WHERE party_id = ?", partyID)
	if err != nil {
		return fmt.Errorf("failed to delete party: %w", err)
	}

	return tx.Commit()
}

func (d *DB) getPartyMemberCount(ctx context.Context, partyID uint64) (int, error) {
	query := `SELECT COUNT(*) FROM t_party_member WHERE party_id = ?`

	var count int
	err := d.db.QueryRowContext(ctx, query, partyID).Scan(&count)
	if err != nil {
		return 0, fmt.Errorf("failed to get party member count: %w", err)
	}

	return count, nil
}

func (d *DB) getRoleByID(ctx context.Context, roleID uint64) (*Role, error) {
	query := `
		SELECT id, role_id, player_id, name, job, level, fatigue, channel
		FROM role
		WHERE role_id = ?
	`

	role := &Role{}
	err := d.db.QueryRowContext(ctx, query, roleID).Scan(
		&role.ID, &role.RoleID, &role.PlayerID, &role.Name, &role.Job, &role.Level,
		&role.Fatigue, &role.World,
	)

	if err == sql.ErrNoRows {
		return nil, nil
	}
	if err != nil {
		return nil, fmt.Errorf("failed to get role: %w", err)
	}

	return role, nil
}

type Role struct {
	ID       uint64
	RoleID   uint64
	PlayerID uint32
	Name     string
	Job      uint32
	Level    uint32
	Fatigue  uint32
	World    uint32
}
