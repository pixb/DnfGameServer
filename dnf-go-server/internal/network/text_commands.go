package network

// textCommandMeta 文本命令 → (module, cmd) 映射。
// 用于 TCP 测试辅助:客户端以 "COMMAND:JSON" 文本格式发送请求,
// 服务端将其映射为二进制协议的 (module, cmd) 并走正常 handler 分发。
var textCommandMeta = map[string]MessageMeta{
	// ==================== 认证模块 (10000) ====================
	"LOGIN":              {Module: 10000, Cmd: 0},
	"CREATE_CHARACTER":   {Module: 10000, Cmd: 2},
	"GET_CHARACTER_LIST": {Module: 10000, Cmd: 4},
	"SELECT_CHARACTER":   {Module: 10000, Cmd: 6},
	"ENTER_GAME":         {Module: 10000, Cmd: 8},
	"LOAD_PLAYER_DATA":   {Module: 10000, Cmd: 10},

	// ==================== 角色模块 (10001) ====================
	"GET_ROLE_INFO":    {Module: 10001, Cmd: 0},
	"UPDATE_ATTRIBUTES": {Module: 10001, Cmd: 2},
	"LEARN_SKILL":      {Module: 10001, Cmd: 4},
	"UPGRADE_SKILL":    {Module: 10001, Cmd: 6},
	"RECOVER_FATIGUE":  {Module: 10001, Cmd: 8},

	// ==================== 背包模块 (10002) ====================
	"GET_BAG_INFO":  {Module: 10002, Cmd: 0},
	"USE_ITEM":      {Module: 10002, Cmd: 2},
	"ITEM_TRANSFER": {Module: 10002, Cmd: 4},
	"SELL_ITEM":     {Module: 10002, Cmd: 6},
	"EQUIP_ITEM":    {Module: 10002, Cmd: 8},
	"DROP_ITEM":     {Module: 10002, Cmd: 10},
	"ITEM_COMPOSE":  {Module: 10002, Cmd: 12},
	"ITEM_REINFORCE": {Module: 10002, Cmd: 14},
	"ITEM_SORT":     {Module: 10002, Cmd: 16},
	"ITEM_DECOMPOSE": {Module: 10002, Cmd: 18},
	"ITEM_RENAME":   {Module: 10002, Cmd: 20},
	"BAG_EXPAND":    {Module: 10002, Cmd: 22},

	// ==================== 副本模块 (10003) ====================
	"ENTER_DUNGEON": {Module: 10003, Cmd: 0},
	"EXIT_DUNGEON":  {Module: 10003, Cmd: 2},
	"REVIVE":        {Module: 10003, Cmd: 4},
	"CHANGE_ROOM":   {Module: 10003, Cmd: 6},

	// ==================== 聊天模块 (10004) ====================
	"SEND_CHAT":        {Module: 10004, Cmd: 0},
	"GET_CHAT_HISTORY": {Module: 10004, Cmd: 2},
	"GET_FRIEND_LIST":  {Module: 10004, Cmd: 200},
	"ADD_FRIEND":       {Module: 10004, Cmd: 202},
	"REMOVE_FRIEND":    {Module: 10004, Cmd: 206},

	// ==================== 商店/拍卖模块 (10005) ====================
	"GET_SHOP_ITEM_LIST":    {Module: 10005, Cmd: 0},
	"GET_SHOP_ITEM_DETAIL":  {Module: 10005, Cmd: 0},
	"BUY_SHOP_ITEM":         {Module: 10005, Cmd: 2},
	"SELL_TO_SHOP":          {Module: 10005, Cmd: 4},
	"AUCTION_SEARCH":        {Module: 10005, Cmd: 100},
	"AUCTION_LIST":          {Module: 10005, Cmd: 100},
	"AUCTION_CATEGORY":      {Module: 10005, Cmd: 100},
	"AUCTION_DETAIL":        {Module: 10005, Cmd: 100},
	"AUCTION_RECORD":        {Module: 10005, Cmd: 100},
	"AUCTION_STATISTIC":     {Module: 10005, Cmd: 100},
	"REGISTER_AUCTION_ITEM": {Module: 10005, Cmd: 102},
	"BID_AUCTION":           {Module: 10005, Cmd: 104},
	"BUYOUT_AUCTION":        {Module: 10005, Cmd: 106},
	"CANCEL_AUCTION":        {Module: 10005, Cmd: 102},
	"QUERY_SHOP_ORDER":      {Module: 10005, Cmd: 108},
	"CANCEL_SHOP_ORDER":     {Module: 10005, Cmd: 110},
	"AUCTION_END":           {Module: 10005, Cmd: 112},
	"AUCTION_FEE":           {Module: 10005, Cmd: 114},

	// ==================== 任务模块 (10006) ====================
	"GET_QUEST_LIST":   {Module: 10006, Cmd: 0},
	"ACCEPT_QUEST":     {Module: 10006, Cmd: 2},
	"COMPLETE_QUEST":   {Module: 10006, Cmd: 4},
	"GET_QUEST_REWARD": {Module: 10006, Cmd: 6},
	"ABANDON_QUEST":    {Module: 10006, Cmd: 8},

	// ==================== 公会模块 (10007) ====================
	"GET_GUILD_INFO":       {Module: 10007, Cmd: 0},
	"CREATE_GUILD":         {Module: 10007, Cmd: 2},
	"JOIN_GUILD":           {Module: 10007, Cmd: 4},
	"LEAVE_GUILD":          {Module: 10007, Cmd: 6},
	"GUILD_DONATE":         {Module: 10007, Cmd: 8},
	"GET_GUILD_SKILL":      {Module: 10007, Cmd: 10},
	"UPGRADE_GUILD_SKILL":  {Module: 10007, Cmd: 12},

	// ==================== 成就模块 (10700/10701/10704/10706) ====================
	"ACHIEVEMENT_INFO":          {Module: 10700, Cmd: 0},
	"GET_ACHIEVEMENT_DETAIL":    {Module: 10700, Cmd: 0},
	"GET_ACHIEVEMENT_LIST":      {Module: 10704, Cmd: 0},
	"GET_ACHIEVEMENT_STATISTICS": {Module: 10704, Cmd: 0},
	"BATCH_GET_ACHIEVEMENTS":    {Module: 10704, Cmd: 0},
	"RECEIVE_ACHIEVEMENT_REWARD": {Module: 10701, Cmd: 0},
	"ACHIEVEMENT_UNLOCK_NOTIFICATION": {Module: 10700, Cmd: 0},
	"ACHIEVEMENT_SYSTEM_BOUNDARY":     {Module: 10700, Cmd: 0},
	"ACHIEVEMENT_SYSTEM_CONCURRENT":   {Module: 10700, Cmd: 0},
	"ACHIEVEMENT_SYSTEM_PERFORMANCE":  {Module: 10700, Cmd: 0},
	"UPDATE_ACHIEVEMENT_PROGRESS":     {Module: 10704, Cmd: 0},
	"VALIDATE_ACHIEVEMENT_CONDITION":  {Module: 10704, Cmd: 0},

	// ==================== PK 模块 (10008) ====================
	"MULTI_PLAY_REQUEST_MATCH":           {Module: 10008, Cmd: 0},
	"MULTI_PLAY_REQUEST_MATCH_CANCEL":    {Module: 10008, Cmd: 2},
	"MULTI_PLAY_REQUEST_MATCH_CONCURRENT": {Module: 10008, Cmd: 0},
	"HISTORIC_SITE_NOTI":                 {Module: 10008, Cmd: 4},
	"LOAD_GUILD_DONATION_INFO":           {Module: 10008, Cmd: 6},
	"DREAM_MAZE_BASIC_INFO":              {Module: 10008, Cmd: 8},
	"RAID_ENTRANCE_COUNT":                {Module: 10008, Cmd: 10},
	"LOADING_PROGRESS":                   {Module: 10008, Cmd: 12},
	"RETURN_TO_TOWN_AT_MULTI_PLAY":       {Module: 10008, Cmd: 14},
	"CUSTOM_GAME_ROOM_SETTING":           {Module: 10008, Cmd: 16},
	"PK_RECORD":                          {Module: 10008, Cmd: 20},
	"PK_RANKING":                         {Module: 10008, Cmd: 22},
	"PK_STATS":                           {Module: 10008, Cmd: 24},
	"PK_MATCH_HISTORY":                   {Module: 10008, Cmd: 26},
	"PK_SEASON_INFO":                     {Module: 10008, Cmd: 28},
	"PK_REWARD":                          {Module: 10008, Cmd: 30},
	"PK_DAILY_RESET":                     {Module: 10008, Cmd: 32},
	"PK_MATCH_TYPE":                      {Module: 10008, Cmd: 34},
	"PK_BATTLE_RESULT":                   {Module: 10008, Cmd: 36},

	// ==================== 组队模块 (10009) ====================
	"SEARCH_PARTY":            {Module: 10009, Cmd: 0},
	"CREATE_PARTY":            {Module: 10009, Cmd: 2},
	"RECOMMEND_GROUP":         {Module: 10009, Cmd: 2},
	"JOIN_PARTY":              {Module: 10009, Cmd: 4},
	"LEAVE_PARTY":             {Module: 10009, Cmd: 4},
	"MODIFY_PARTY_SETTING":    {Module: 10009, Cmd: 4},
	"KICK_OUT_MEMBER":         {Module: 10009, Cmd: 4},
	"START_MULTI_PLAY":        {Module: 10009, Cmd: 6},
	"LOAD_PARTY_STATUS":       {Module: 10009, Cmd: 12},
	"HALF_OPEN_PARTY":         {Module: 10009, Cmd: 22},
	"CONNECT_BATTLE_SERVER":   {Module: 10009, Cmd: 18},
	"CONCURRENT_OPERATION":    {Module: 10009, Cmd: 4},

	// ==================== 事件模块 (10500) ====================
	"QUERY_EVENT_LIST":            {Module: 10500, Cmd: 0},
	"QUERY_EVENT_DETAIL":          {Module: 10500, Cmd: 2},
	"QUERY_EVENT_STATUS":          {Module: 10500, Cmd: 4},
	"QUERY_EVENT_PROGRESS":        {Module: 10500, Cmd: 6},
	"TRIGGER_EVENT":               {Module: 10500, Cmd: 8},
	"HANDLE_EVENT":                {Module: 10500, Cmd: 10},
	"VALIDATE_EVENT_COMPLETION":   {Module: 10500, Cmd: 12},
	"CREATE_EVENT":                {Module: 10500, Cmd: 14},
	"DELETE_EVENT":                {Module: 10500, Cmd: 16},
	"RECEIVE_EVENT_REWARD":        {Module: 10500, Cmd: 18},
	"DISTRIBUTE_EVENT_REWARD":     {Module: 10500, Cmd: 20},
	"RESET_EVENT":                 {Module: 10500, Cmd: 22},

	// ==================== 排名模块 (10501) ====================
	"QUERY_MY_RANK":      {Module: 10501, Cmd: 0},
	"QUERY_PERSONAL_RANK": {Module: 10501, Cmd: 2},
	"QUERY_FRIEND_RANK":  {Module: 10501, Cmd: 4},
	"QUERY_MY_TEAM_RANK": {Module: 10501, Cmd: 6},

	// ==================== 日志模块 (10502) ====================
	"QUERY_LOG":    {Module: 10502, Cmd: 0},
	"RECORD_LOG":   {Module: 10502, Cmd: 2},
	"STATISTIC_LOG": {Module: 10502, Cmd: 4},
	"DELETE_LOG":   {Module: 10502, Cmd: 6},
	"EXPORT_LOG":   {Module: 10502, Cmd: 8},
	"CLEAN_LOG":    {Module: 10502, Cmd: 10},
	"MONITOR_LOG":  {Module: 10502, Cmd: 12},
	"ANALYZE_LOG":  {Module: 10502, Cmd: 14},
}
