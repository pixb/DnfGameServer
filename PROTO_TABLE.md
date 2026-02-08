# Protobuf 协议汇总表

**总计**: 740 个协议文件 | **分页**: 第 1/15 页

| 编号 | 文件名 | 消息名 | 字段数 | 字段示例 |
|-----|-------|--------|-------|---------|
| ??? | `enum_battle_option.java.proto` | `enum_battle_option` | 0 | N/A |
| ??? | `enum_chivalry_mission.java.proto` | `enum_chivalry_mission` | 0 | N/A |
| ??? | `enum_client_build_type.java.proto` | `enum_client_build_type` | 0 | N/A |
| ??? | `enum_dungeon_gauge_type.java.proto` | `enum_dungeon_gauge_type` | 0 | N/A |
| ??? | `enum_idip_prohibit_type.java.proto` | `enum_idip_prohibit_type` | 0 | N/A |
| ??? | `enum_message_assistant_switch_status.java.proto` | `enum_message_assistant_switch_status` | 0 | N/A |
| ??? | `enum_party_invite_type.java.proto` | `enum_party_invite_type` | 0 | N/A |
| ??? | `enum_team.java.proto` | `enum_team` | 0 | N/A |
| 102161 | `notify_adventurebook_update_condition.java.proto` | `NOTIFY_ADVENTUREBOOK_UPDATE_CONDITION` | 1 | error |
| 101551 | `notify_change_status.java.proto` | `NOTIFY_CHANGE_STATUS` | 1 | status |
| 102091 | `notify_complete_quest.java.proto` | `NOTIFY_COMPLETE_QUEST` | 1 | error |
| 300031 | `notify_contents_notify_remove.java.proto` | `NOTIFY_CONTENTS_NOTIFY_REMOVE` | 5 | error, index, content, count, type |
| 135661 | `notify_control_group.java.proto` | `NOTIFY_CONTROL_GROUP` | 5 | error, world, channel, partyguid, partyleaderguid |
| 110161 | `notify_dungeon_result.java.proto` | `NOTIFY_DUNGEON_RESULT` | 5 | error, dungeonindex, matchtype, exp, level |
| 100361 | `notify_load_server_simple_data.java.proto` | `NOTIFY_LOAD_SERVER_SIMPLE_DATA` | 4 | error, type, enumvalue, value |
| 100241 | `notify_reconnect_info.java.proto` | `NOTIFY_RECONNECT_INFO` | 5 | error, rpguid, Boolean, partyguid, town |
| 130471 | `notify_request_to_re_enter_accept_dungeon.java.proto` | `NOTIFY_REQUEST_TO_RE_ENTER_ACCEPT_DUNGEON` | 3 | error, subtype, guid |
| 130461 | `notify_request_to_re_enter_dungeon.java.proto` | `NOTIFY_REQUEST_TO_RE_ENTER_DUNGEON` | 5 | error, type, difficulty, subtype, reason |
| 157251 | `notify_suggest_move_party.java.proto` | `NOTIFY_SUGGEST_MOVE_PARTY` | 3 | error, dungeonindex, suggesttype |
| ??? | `pt_account_ticket.java.proto` | `PT_ACCOUNT_TICKET` | 2 | dungeontype, count |
| ??? | `pt_achievement_reward.java.proto` | `PT_ACHIEVEMENT_REWARD` | 3 | level, type, value |
| ??? | `pt_action_count_info.java.proto` | `PT_ACTION_COUNT_INFO` | 5 | combocount, aerialcount, overkillcount1, overkillcount2, countercount |
| ??? | `pt_adventure_clear_reward.java.proto` | `PT_ADVENTURE_CLEAR_REWARD` | 3 | exp, index, count |
| ??? | `pt_adventure_union_collection.java.proto` | `PT_ADVENTURE_UNION_COLLECTION` | 4 | job, growtype, secgrowtype, equipscore |
| ??? | `pt_adventure_union_collection_slot.java.proto` | `PT_ADVENTURE_UNION_COLLECTION_SLOT` | 2 | slot, rewardindex |
| ??? | `pt_adventure_union_expedition.java.proto` | `PT_ADVENTURE_UNION_EXPEDITION` | 4 | area, time, consumefatigue, regdate |
| ??? | `pt_adventure_union_expedition_rewards.java.proto` | `PT_ADVENTURE_UNION_EXPEDITION_REWARDS` | 2 | index, count |
| ??? | `pt_adventure_union_level_reward.java.proto` | `PT_ADVENTURE_UNION_LEVEL_REWARD` | 1 | level |
| ??? | `pt_adventure_union_shareboard_background.java.proto` | `PT_ADVENTURE_UNION_SHAREBOARD_BACKGROUND` | 1 | index |
| ??? | `pt_adventure_union_shareboard_frame.java.proto` | `PT_ADVENTURE_UNION_SHAREBOARD_FRAME` | 1 | index |
| ??? | `pt_adventure_union_shareboard_slot.java.proto` | `PT_ADVENTURE_UNION_SHAREBOARD_SLOT` | 4 | slot, charguid, Boolean, antievilscore |
| ??? | `pt_adventure_union_shareboard_slot_detail_info.java.proto` | `PT_ADVENTURE_UNION_SHAREBOARD_SLOT_DETAIL_INFO` | 2 | slot, Boolean |
| ??? | `pt_adventurebook_info.java.proto` | `PT_ADVENTUREBOOK_INFO` | 5 | bindex, bstate, charguid, charname, growtype |
| ??? | `pt_adventurebook_open_condition.java.proto` | `PT_ADVENTUREBOOK_OPEN_CONDITION` | 2 | cindex, cstate |
| ??? | `pt_alchemy_recipe_limit.java.proto` | `PT_ALCHEMY_RECIPE_LIMIT` | 2 | recipeindex, usecount |
| ??? | `pt_all_clear_reward.java.proto` | `PT_ALL_CLEAR_REWARD` | 3 | index, count, floor |
| ??? | `pt_all_skill_slot.java.proto` | `PT_ALL_SKILL_SLOT` | 0 | N/A |
| ??? | `pt_appendage.java.proto` | `PT_APPENDAGE` | 2 | appendageindex, count |
| ??? | `pt_appendage_exp.java.proto` | `PT_APPENDAGE_EXP` | 2 | index, exp |
| ??? | `pt_arcade_pvp_info_currency.java.proto` | `PT_ARCADE_PVP_INFO_CURRENCY` | 2 | index, count |
| ??? | `pt_artifact.java.proto` | `PT_ARTIFACT` | 5 | index, count, guid, slot, scount |
| ??? | `pt_artifact_base_option.java.proto` | `PT_ARTIFACT_BASE_OPTION` | 3 | option, rarity, Boolean |
| ??? | `pt_auction_equip.java.proto` | `PT_AUCTION_EQUIP` | 5 | guid, upgrade, quality, endurance, reforge |
| ??? | `pt_auction_item_index.java.proto` | `PT_AUCTION_ITEM_INDEX` | 2 | index, qty |
| ??? | `pt_auction_item_price_info.java.proto` | `PT_AUCTION_ITEM_PRICE_INFO` | 2 | price, count |
| ??? | `pt_auction_stackable.java.proto` | `PT_AUCTION_STACKABLE` | 5 | guid, upgrade, quality, endurance, reforge |
| ??? | `pt_avatar_guid.java.proto` | `PT_AVATAR_GUID` | 1 | guid |
| ??? | `pt_avatar_index_slot.java.proto` | `PT_AVATAR_INDEX_SLOT` | 2 | index, slot |
| ??? | `pt_avatar_item.java.proto` | `PT_AVATAR_ITEM` | 5 | index, count, guid, expiretime, option |
| ??? | `pt_avatar_mannequin_info.java.proto` | `PT_AVATAR_MANNEQUIN_INFO` | 2 | slot, name |

[← 上一页](#protobuf-协议汇总表-p2) | [下一页 →](#protobuf-协议汇总表-p2)

---

# Protobuf 协议汇总表（第 2/{total_pages} 页）

**总计**: 740 个协议文件 | **分页**: 第 2/15 页

| 编号 | 文件名 | 消息名 | 字段数 | 字段示例 |
|-----|-------|--------|-------|---------|
| ??? | `pt_avatar_mannequin_part_info.java.proto` | `PT_AVATAR_MANNEQUIN_PART_INFO` | 2 | guid, index |
| ??? | `pt_battle_option.java.proto` | `PT_BATTLE_OPTION` | 1 | Boolean |
| ??? | `pt_battle_pass_info.java.proto` | `PT_BATTLE_PASS_INFO` | 1 | seasonpassFlag |
| ??? | `pt_battleleague_contribute.java.proto` | `PT_BATTLELEAGUE_CONTRIBUTE` | 2 | charguid, contribution |
| ??? | `pt_battleleague_pve_record.java.proto` | `PT_BATTLELEAGUE_PVE_RECORD` | 2 | charguid, contribution |
| ??? | `pt_battleleague_pvp_record.java.proto` | `PT_BATTLELEAGUE_PVP_RECORD` | 4 | charguid, kill, death, mortar |
| ??? | `pt_battleleague_reward.java.proto` | `PT_BATTLELEAGUE_REWARD` | 3 | charguid, rewardcount, rewardlimited |
| ??? | `pt_battleleague_reward_info.java.proto` | `PT_BATTLELEAGUE_REWARD_INFO` | 2 | index, count |
| ??? | `pt_blacklist.java.proto` | `PT_BLACKLIST` | 5 | charguid, name, time, level, job |
| ??? | `pt_buff_list.java.proto` | `PT_BUFF_LIST` | 1 | time |
| ??? | `pt_card_attach.java.proto` | `PT_CARD_ATTACH` | 1 | index |
| ??? | `pt_card_compose.java.proto` | `PT_CARD_COMPOSE` | 3 | index, count, Boolean |
| ??? | `pt_cera_shop_buy.java.proto` | `PT_CERA_SHOP_BUY` | 3 | goodsid, count, type |
| ??? | `pt_cera_shop_info.java.proto` | `PT_CERA_SHOP_INFO` | 0 | N/A |
| ??? | `pt_cera_shop_mileage.java.proto` | `PT_CERA_SHOP_Mileage` | 2 | index, value |
| ??? | `pt_cera_shop_money.java.proto` | `PT_CERA_SHOP_MONEY` | 3 | index, count, value |
| ??? | `pt_champion_info.java.proto` | `PT_CHAMPION_INFO` | 1 | Boolean |
| ??? | `pt_champion_stage_info.java.proto` | `PT_CHAMPION_STAGE_INFO` | 5 | guid, posx, posy, Boolean, objectguid |
| ??? | `pt_channel.java.proto` | `PT_CHANNEL` | 5 | world, channel, iP, port, saturation |
| ??? | `pt_channel_info.java.proto` | `PT_CHANNEL_INFO` | 5 | world, channel, ip, port, priority |
| ??? | `pt_character.java.proto` | `PT_CHARACTER` | 5 | charguid, lastlogout, growtype, secgrowtype, job |
| ??? | `pt_character_equip_only_index.java.proto` | `PT_CHARACTER_EQUIP_ONLY_INDEX` | 0 | N/A |
| ??? | `pt_character_guid.java.proto` | `PT_CHARACTER_GUID` | 4 | charguid, type, posx, posy |
| ??? | `pt_character_info.java.proto` | `PT_CHARACTER_INFO` | 5 | charguid, job, growtype, secondgrowtype, level |
| ??? | `pt_charguid_entrance_item.java.proto` | `PT_CHARGUID_ENTRANCE_ITEM` | 1 | charguid |
| ??? | `pt_charguid_fatigue.java.proto` | `PT_CHARGUID_FATIGUE` | 2 | charguid, fatigue |
| ??? | `pt_charguid_ticket.java.proto` | `PT_CHARGUID_TICKET` | 1 | charguid |
| ??? | `pt_chat.java.proto` | `PT_CHAT` | 5 | error, type, subtype, charguid, targetguid |
| ??? | `pt_chat_sync.java.proto` | `PT_CHAT_SYNC` | 2 | index, type |
| ??? | `pt_chat_sync_info.java.proto` | `PT_CHAT_SYNC_INFO` | 3 | lastindex, totalcount, type |
| ??? | `pt_chivalry.java.proto` | `PT_CHIVALRY` | 5 | dungeontype, index, grade, exp, Boolean |
| ??? | `pt_chivalry_currency_daily_gain.java.proto` | `PT_CHIVALRY_CURRENCY_DAILY_GAIN` | 3 | type, index, count |
| ??? | `pt_chivalry_mission.java.proto` | `PT_CHIVALRY_MISSION` | 3 | ENUM_CHIVALRY_MISSION, value, count |
| ??? | `pt_clear_dungeon_info.java.proto` | `PT_CLEAR_DUNGEON_INFO` | 5 | floor, grade, score, step, shortestcleartime |
| ??? | `pt_clear_dungeon_info_list.java.proto` | `PT_CLEAR_DUNGEON_INFO_LIST` | 1 | index |
| ??? | `pt_clear_dungeon_starreward.java.proto` | `PT_CLEAR_DUNGEON_STARREWARD` | 2 | count, type |
| ??? | `pt_clientinfo.java.proto` | `PT_CLIENTINFO` | 5 | platID, deviceSoft, deviceHard, teleOper, network |
| ??? | `pt_closeness_result_info.java.proto` | `PT_CLOSENESS_RESULT_INFO` | 3 | fguid, increasescore, closenessscore |
| ??? | `pt_collection_info.java.proto` | `PT_COLLECTION_INFO` | 2 | index, Boolean |
| ??? | `pt_collection_item.java.proto` | `PT_COLLECTION_ITEM` | 4 | index, Boolean, Boolean, rewardindex |
| ??? | `pt_complete_quest_info.java.proto` | `PT_COMPLETE_QUEST_INFO` | 4 | index, type, state, count |
| ??? | `pt_consume_items.java.proto` | `PT_CONSUME_ITEMS` | 2 | itemindex, count |
| ??? | `pt_consume_list.java.proto` | `PT_CONSUME_LIST` | 0 | N/A |
| ??? | `pt_contents_reward_info.java.proto` | `PT_CONTENTS_REWARD_INFO` | 0 | N/A |
| ??? | `pt_control_group_invite_item.java.proto` | `PT_CONTROL_GROUP_INVITE_ITEM` | 1 | targetguid |
| ??? | `pt_crack.java.proto` | `PT_CRACK` | 4 | index, count, expiretime, slot |
| ??? | `pt_creature.java.proto` | `PT_CREATURE` | 5 | index, count, guid, slot, exp |
| ??? | `pt_creature_communion.java.proto` | `PT_CREATURE_COMMUNION` | 4 | level, selectedslot, extentionslot, Map |
| ??? | `pt_creature_errand.java.proto` | `PT_CREATURE_ERRAND` | 2 | level, lastresettime |
| ??? | `pt_creature_learn_skill_info.java.proto` | `PT_CREATURE_LEARN_SKILL_INFO` | 3 | category, skillindex, skilllevel |

[← 上一页](#protobuf-协议汇总表-p1) | [下一页 →](#protobuf-协议汇总表-p3)

---

# Protobuf 协议汇总表（第 3/{total_pages} 页）

**总计**: 740 个协议文件 | **分页**: 第 3/15 页

| 编号 | 文件名 | 消息名 | 字段数 | 字段示例 |
|-----|-------|--------|-------|---------|
| ??? | `pt_creature_learn_skill_infos.java.proto` | `PT_CREATURE_LEARN_SKILL_INFOS` | 0 | N/A |
| ??? | `pt_creature_skill.java.proto` | `PT_CREATURE_SKILL` | 2 | skillindex, pairindex |
| ??? | `pt_cube_items.java.proto` | `PT_CUBE_ITEMS` | 2 | itemindex, count |
| ??? | `pt_currency_daily_gain.java.proto` | `PT_CURRENCY_DAILY_GAIN` | 2 | index, count |
| ??? | `pt_currency_reward_info.java.proto` | `PT_CURRENCY_REWARD_INFO` | 0 | N/A |
| ??? | `pt_custom_data.java.proto` | `PT_CUSTOM_DATA` | 4 | type, senderguid, iValue, Float |
| ??? | `pt_custom_pvp_data.java.proto` | `PT_CUSTOM_PVP_DATA` | 5 | type, data, isuse, ishide, charguid |
| ??? | `pt_custom_pvp_room_setting.java.proto` | `PT_CUSTOM_PVP_ROOM_SETTING` | 0 | N/A |
| ??? | `pt_dining_food_buff_info.java.proto` | `PT_DINING_FOOD_BUFF_INFO` | 2 | index, endtime |
| ??? | `pt_dream_maze_dungeon.java.proto` | `PT_DREAM_MAZE_DUNGEON` | 4 | index, cleartime, grade, bosshp |
| ??? | `pt_drop_object_gold.java.proto` | `PT_DROP_OBJECT_GOLD` | 2 | charguid, gold |
| ??? | `pt_drop_object_item.java.proto` | `PT_DROP_OBJECT_ITEM` | 5 | charguid, itemindex, upgrade, quality, count |
| ??? | `pt_dungeon_party_count.java.proto` | `PT_DUNGEON_PARTY_COUNT` | 3 | dungeonindex, count, grade |
| ??? | `pt_dungeon_result_quest_info.java.proto` | `PT_DUNGEON_RESULT_QUEST_INFO` | 2 | index, type |
| ??? | `pt_emblem.java.proto` | `PT_EMBLEM` | 5 | index, count, Boolean, scount, tcount |
| ??? | `pt_emblem_request.java.proto` | `PT_EMBLEM_REQUEST` | 2 | index, slot |
| ??? | `pt_emblem_result.java.proto` | `PT_EMBLEM_RESULT` | 1 | index |
| ??? | `pt_entrance_item.java.proto` | `PT_ENTRANCE_ITEM` | 2 | index, count |
| ??? | `pt_equip.java.proto` | `PT_EQUIP` | 5 | index, count, guid, upgrade, quality |
| ??? | `pt_equip_index_slot.java.proto` | `PT_EQUIP_INDEX_SLOT` | 5 | index, slot, reforge, reforgeexp, upgrade |
| ??? | `pt_equip_list.java.proto` | `PT_EQUIP_LIST` | 0 | N/A |
| ??? | `pt_equip_put_on_off.java.proto` | `PT_EQUIP_PUT_ON_OFF` | 2 | guid, slot |
| ??? | `pt_equipped.java.proto` | `PT_EQUIPPED` | 5 | index, guid, upgrade, reforge, reforgeexp |
| ??? | `pt_errand_clear.java.proto` | `PT_ERRAND_CLEAR` | 2 | rarity, count |
| ??? | `pt_errand_info.java.proto` | `PT_ERRAND_INFO` | 5 | errandid, state, starttime, endtime, Boolean |
| ??? | `pt_errand_today.java.proto` | `PT_ERRAND_TODAY` | 2 | rewardindex, rewardcount |
| ??? | `pt_escort_reward.java.proto` | `PT_ESCORT_REWARD` | 5 | addexp, totalexp, wagonhp, index, count |
| ??? | `pt_event_data.java.proto` | `PT_EVENT_DATA` | 5 | index, status, count, value, dateStart |
| ??? | `pt_event_data_for_character.java.proto` | `PT_EVENT_DATA_FOR_CHARACTER` | 1 | guid |
| ??? | `pt_event_on_time.java.proto` | `PT_EVENT_ON_TIME` | 5 | index, active, startHour, startMinute, endHour |
| ??? | `pt_event_reward_info.java.proto` | `PT_EVENT_REWARD_INFO` | 1 | index |
| ??? | `pt_event_scheduler.java.proto` | `PT_EVENT_SCHEDULER` | 4 | index, startdate, enddate, Boolean |
| ??? | `pt_event_select_info.java.proto` | `PT_EVENT_SELECT_INFO` | 3 | selectedCharguid, eventDateEnd, eventIndex |
| ??? | `pt_exp_detailinfo.java.proto` | `PT_EXP_DETAILINFO` | 3 | baseexp, rankexp, premiumexp |
| ??? | `pt_exp_info.java.proto` | `PT_EXP_INFO` | 2 | exp, charguid |
| ??? | `pt_facility_info.java.proto` | `PT_FACILITY_INFO` | 2 | type, level |
| ??? | `pt_floor_info.java.proto` | `PT_FLOOR_INFO` | 5 | index, count, floor, bestcleartime, cleartime |
| ??? | `pt_friend_daily_closeness_info.java.proto` | `PT_FRIEND_DAILY_CLOSENESS_INFO` | 2 | index, count |
| ??? | `pt_friend_info.java.proto` | `PT_FRIEND_INFO` | 5 | world, fguid, name, level, job |
| ??? | `pt_friend_rank.java.proto` | `PT_FRIEND_RANK` | 5 | guid, score, job, growtype, name |
| ??? | `pt_gacha_mileage.java.proto` | `PT_GACHA_MILEAGE` | 2 | index, score |
| ??? | `pt_gacha_result.java.proto` | `PT_GACHA_RESULT` | 2 | recipe, gachaindex |
| ??? | `pt_gauge.java.proto` | `PT_GAUGE` | 1 | gauge |
| ??? | `pt_gold_goblin_hp.java.proto` | `PT_GOLD_GOBLIN_HP` | 1 | gghp |
| ??? | `pt_group_member.java.proto` | `PT_GROUP_MEMBER` | 5 | charguid, equipscore, job, level, name |
| ??? | `pt_group_recommend_member.java.proto` | `PT_GROUP_RECOMMEND_MEMBER` | 5 | groupguid, world, Boolean, Boolean, status |
| ??? | `pt_guardian_auction.java.proto` | `PT_GUARDIAN_AUCTION` | 2 | index, count |
| ??? | `pt_guardian_deal.java.proto` | `PT_GUARDIAN_DEAL` | 2 | charguid, guardiandeal |
| ??? | `pt_guardian_order.java.proto` | `PT_GUARDIAN_ORDER` | 5 | guardiandeal, growtype, secondgrowtype, job, name |
| ??? | `pt_guild_content_buff_info.java.proto` | `PT_GUILD_CONTENT_BUFF_INFO` | 2 | index, level |
| ??? | `pt_guild_donation_accumulate_reward.java.proto` | `PT_GUILD_DONATION_ACCUMULATE_REWARD` | 2 | clearcount, Boolean |
| ??? | `pt_guild_donation_recipe_2.java.proto` | `PT_GUILD_DONATION_RECIPE_2` | 3 | index, count, status |
| ??? | `pt_guild_symbol.java.proto` | `PT_GUILD_SYMBOL` | 2 | index, colorindex |
| ??? | `pt_gvoice_member_info.java.proto` | `PT_GVOICE_MEMBER_INFO` | 3 | charguid, key, grade |
| ??? | `pt_hidden_chatting.java.proto` | `PT_HIDDEN_CHATTING` | 2 | type, charguid |
| ??? | `pt_historicsite_relic_info.java.proto` | `PT_HISTORICSITE_RELIC_INFO` | 5 | relicindex, reliccount, buyername, buyerjob, buyergrowtype |
| ??? | `pt_hyperlink_data.java.proto` | `PT_HYPERLINK_DATA` | 2 | index, Boolean |
| ??? | `pt_hyperlink_systemmessage_sub.java.proto` | `PT_HYPERLINK_SYSTEMMESSAGE_SUB` | 1 | msg |
| ??? | `pt_index_count.java.proto` | `PT_INDEX_COUNT` | 2 | index, count |
| ??? | `pt_init_skill.java.proto` | `PT_INIT_SKILL` | 3 | Boolean, currentVersion, latestVersion |
| ??? | `pt_integration_world.java.proto` | `PT_INTEGRATION_WORLD` | 1 | world |
| ??? | `pt_interesting_goods.java.proto` | `PT_INTERESTING_GOODS` | 3 | shopid, count, Boolean |
| ??? | `pt_intrude_info.java.proto` | `PT_INTRUDE_INFO` | 4 | Boolean, name, guid, rpguid |
| ??? | `pt_intrude_member_info.java.proto` | `PT_INTRUDE_MEMBER_INFO` | 5 | name, level, job, growtype, secgrowtype |
| ??? | `pt_item_disjoint_guid.java.proto` | `PT_ITEM_DISJOINT_GUID` | 1 | guid |
| ??? | `pt_item_production_slot.java.proto` | `PT_ITEM_PRODUCTION_SLOT` | 5 | slotindex, recipeindex, starttime, endtime, state |
| ??? | `pt_item_reward_info.java.proto` | `PT_ITEM_REWARD_INFO` | 0 | N/A |
| ??? | `pt_item_sell.java.proto` | `PT_ITEM_SELL` | 1 | type |
| ??? | `pt_item_sell_guid.java.proto` | `PT_ITEM_SELL_GUID` | 1 | guid |
| ??? | `pt_item_sell_index.java.proto` | `PT_ITEM_SELL_INDEX` | 3 | index, count, Boolean |
| ??? | `pt_item_timebox.java.proto` | `PT_ITEM_TIMEBOX` | 4 | index, group, guid, opentime |
| ??? | `pt_item_usable_info.java.proto` | `PT_ITEM_USABLE_INFO` | 2 | index, count |
| ??? | `pt_item_use_inven_slot.java.proto` | `PT_ITEM_USE_INVEN_SLOT` | 2 | type, count |
| ??? | `pt_item_use_result.java.proto` | `PT_ITEM_USE_RESULT` | 5 | fatigue, growth, secondgrowtype, totalexp, exp |
| ??? | `pt_items.java.proto` | `PT_ITEMS` | 2 | index, count |
| ??? | `pt_items_tower.java.proto` | `PT_ITEMS_TOWER` | 3 | index, count, floor |
| ??? | `pt_job_limit_info.java.proto` | `PT_JOB_LIMIT_INFO` | 3 | job, growtype, level |
| ??? | `pt_linktext.java.proto` | `PT_LinkText` | 2 | text, url |
| ??? | `pt_load_server_simple_data.java.proto` | `PT_LOAD_SERVER_SIMPLE_DATA` | 2 | type, enumvalue |
| ??? | `pt_localreward.java.proto` | `PT_LOCALREWARD` | 3 | error, rewardkey, Boolean |
| ??? | `pt_loots.java.proto` | `PT_LOOTS` | 5 | itemindex, type, guid, qty, loottype |
| ??? | `pt_main_event_data.java.proto` | `PT_MAIN_EVENT_DATA` | 5 | index, status, count, value, dateStart |
| ??? | `pt_map_guids.java.proto` | `PT_MAP_GUIDS` | 1 | guid |
| ??? | `pt_map_info.java.proto` | `PT_MAP_INFO` | 5 | guid, index, Boolean, cleartime, starttick |
| ??? | `pt_map_object_drop.java.proto` | `PT_MAP_OBJECT_DROP` | 2 | index, count |
| ??? | `pt_material_item.java.proto` | `PT_MATERIAL_ITEM` | 2 | inventype, guid |
| ??? | `pt_material_list.java.proto` | `PT_MATERIAL_LIST` | 0 | N/A |
| ??? | `pt_member_area_info.java.proto` | `PT_MEMBER_AREA_INFO` | 3 | charguid, area, town |
| ??? | `pt_money_item.java.proto` | `PT_MONEY_ITEM` | 3 | index, count, season |
| ??? | `pt_monster.java.proto` | `PT_MONSTER` | 1 | index |
| ??? | `pt_monster_exp.java.proto` | `PT_MONSTER_EXP` | 3 | playercount, charguid, exp |
| ??? | `pt_monsterkill_last_shot.java.proto` | `PT_MONSTERKILL_LAST_SHOT` | 2 | charguid, monsterguid |
| ??? | `pt_new_contents.java.proto` | `PT_NEW_CONTENTS` | 3 | index, content, count |
| ??? | `pt_note_message.java.proto` | `PT_NOTE_MESSAGE` | 5 | charguid, name, job, growtype, level |
| ??? | `pt_object_info.java.proto` | `PT_OBJECT_INFO` | 5 | guid, ownerguid, level, teamtype, Boolean |
| ??? | `pt_party_member_ranking.java.proto` | `PT_PARTY_MEMBER_RANKING` | 5 | guid, job, growtype, secondgrowtype, level |
| ??? | `pt_party_ranking.java.proto` | `PT_PARTY_RANKING` | 3 | rank, score, maxrank |
| ??? | `pt_passive_object.java.proto` | `PT_PASSIVE_OBJECT` | 1 | index |
| ??? | `pt_play_with_me_friend_info.java.proto` | `PT_PLAY_WITH_ME_FRIEND_INFO` | 5 | fguid, name, level, job, growtype |
| ??? | `pt_post_all_list.java.proto` | `PT_POST_ALL_LIST` | 5 | index, count, guid, title, msg |
| ??? | `pt_post_package.java.proto` | `PT_POST_PACKAGE` | 3 | value, index, slotindex |
| ??? | `pt_postal_reward_info.java.proto` | `PT_POSTAL_REWARD_INFO` | 0 | N/A |
| ??? | `pt_privatestore_goods.java.proto` | `PT_PRIVATESTORE_GOODS` | 2 | index, remainbuyingcount |
| ??? | `pt_prohibit.java.proto` | `PT_PROHIBIT` | 3 | target, endtime, reason |
| ??? | `pt_protocol_transaction.java.proto` | `PT_PROTOCOL_TRANSACTION` | 2 | protocol, transactionid |
| ??? | `pt_pve_round_info.java.proto` | `PT_PVE_ROUND_INFO` | 5 | totalDamage, Float, harmDamage, Float, supportPoint |
| ??? | `pt_pvp_battle_info.java.proto` | `PT_PVP_BATTLE_INFO` | 5 | maxcombodamage, Float, counterdamage, matchtime, Float |
| ??? | `pt_pvp_char_dice.java.proto` | `PT_PVP_CHAR_DICE` | 2 | charguid, dice |
| ??? | `pt_pvp_dice_info.java.proto` | `PT_PVP_DICE_INFO` | 1 | round |
| ??? | `pt_pvp_glory_result.java.proto` | `PT_PVP_GLORY_RESULT` | 5 | Boolean, Boolean, Boolean, glorypoint, gainglorypoint |
| ??? | `pt_pvp_record_info.java.proto` | `PT_PVP_RECORD_INFO` | 5 | consecutivewin, draw, lose, maxconsecutivewin, maxpr |
| ??? | `pt_quest.java.proto` | `PT_QUEST` | 2 | qindex, type |
| ??? | `pt_quest_info.java.proto` | `PT_QUEST_INFO` | 4 | qindex, state, count, Boolean |
| ??? | `pt_raid_entrance_count.java.proto` | `PT_RAID_ENTRANCE_COUNT` | 5 | raidindex, Boolean, dailycharacter, character, account |
| ??? | `pt_raid_round_info.java.proto` | `PT_RAID_ROUND_INFO` | 1 | charguid |
| ??? | `pt_randomoption_item.java.proto` | `PT_RANDOMOPTION_ITEM` | 4 | index, rarity, Boolean, Boolean |
| ??? | `pt_rank_friend_info.java.proto` | `PT_RANK_FRIEND_INFO` | 1 | guid |
| ??? | `pt_rank_platform_friend_info.java.proto` | `PT_RANK_PLATFORM_FRIEND_INFO` | 1 | openid |
| ??? | `pt_ranking.java.proto` | `PT_RANKING` | 5 | guid, growtype, secondgrowtype, job, level |
| ??? | `pt_ranking_account_info.java.proto` | `PT_RANKING_ACCOUNT_INFO` | 5 | guid, level, job, growtype, secgrowtype |
| ??? | `pt_ranking_minigame_info.java.proto` | `PT_RANKING_MINIGAME_INFO` | 5 | guid, level, job, growtype, secgrowtype |
| ??? | `pt_reap_reward.java.proto` | `PT_REAP_REWARD` | 2 | itemindex, count |
| ??? | `pt_reason_user_info.java.proto` | `PT_REASON_USER_INFO` | 2 | charguid, reason |
| ??? | `pt_recommend_friend_info.java.proto` | `PT_RECOMMEND_FRIEND_INFO` | 5 | fguid, name, level, job, growtype |
| ??? | `pt_recommend_guild.java.proto` | `PT_RECOMMEND_GUILD` | 5 | gguid, gboard, minlevel, area, Boolean |
| ??? | `pt_recruit_party_info.java.proto` | `PT_RECRUIT_PARTY_INFO` | 5 | area, channel, grade, ip, partyname |
| ??? | `pt_removeitems.java.proto` | `PT_REMOVEITEMS` | 0 | N/A |
| ??? | `pt_repurchase_artifact.java.proto` | `PT_REPURCHASE_ARTIFACT` | 1 | guid |
| ??? | `pt_repurchase_avatar_item.java.proto` | `PT_REPURCHASE_AVATAR_ITEM` | 1 | guid |
| ??? | `pt_repurchase_creture.java.proto` | `PT_REPURCHASE_CRETURE` | 1 | guid |
| ??? | `pt_repurchase_equip.java.proto` | `PT_REPURCHASE_EQUIP` | 1 | guid |
| ??? | `pt_repurchase_stackable.java.proto` | `PT_REPURCHASE_STACKABLE` | 1 | guid |
| ??? | `pt_repurchase_storage_list.java.proto` | `PT_REPURCHASE_STORAGE_LIST` | 0 | N/A |
| ??? | `pt_res_party_list.java.proto` | `PT_RES_PARTY_LIST` | 5 | ip, port, area, subtype, stageindex |
| ??? | `pt_return_user_info.java.proto` | `PT_RETURN_USER_INFO` | 5 | selectedCharguid, selectableDateStart, selectableDateEnd, missionDateStart, missionDateEnd |
| ??? | `pt_reward_quest_item.java.proto` | `PT_REWARD_QUEST_ITEM` | 1 | itemindex |
| ??? | `pt_sd_death_match_record.java.proto` | `PT_SD_DEATH_MATCH_RECORD` | 4 | charguid, rank, score, Boolean |
| ??? | `pt_sd_death_match_reward.java.proto` | `PT_SD_DEATH_MATCH_REWARD` | 2 | charguid, rewardcount |
| ??? | `pt_sd_death_match_reward_info.java.proto` | `PT_SD_DEATH_MATCH_REWARD_INFO` | 2 | index, count |
| ??? | `pt_selected_item.java.proto` | `PT_SELECTED_ITEM` | 4 | index, count, guid, slotindex |
| ??? | `pt_share_reward_info.java.proto` | `PT_SHARE_REWARD_INFO` | 2 | index, state |
| ??? | `pt_shop_buy_count.java.proto` | `PT_SHOP_BUY_COUNT` | 5 | goodsid, count, lastbuytime, supplycount, supplydurationtime |
| ??? | `pt_shop_group_reset.java.proto` | `PT_SHOP_GROUP_RESET` | 2 | groupid, count |
| ??? | `pt_shop_tab_reset.java.proto` | `PT_SHOP_TAB_RESET` | 3 | shopid, tab, count |
| ??? | `pt_skill.java.proto` | `PT_SKILL` | 2 | index, level |
| ??? | `pt_skill_info.java.proto` | `PT_SKILL_INFO` | 3 | type, sp, tp |
| ??? | `pt_skill_slot.java.proto` | `PT_SKILL_SLOT` | 2 | index, slot |
| ??? | `pt_skill_slot_matching.java.proto` | `PT_SKILL_SLOT_MATCHING` | 2 | source, dest |
| ??? | `pt_skill_use_count.java.proto` | `PT_SKILL_USE_COUNT` | 2 | index, count |
| ??? | `pt_skills.java.proto` | `PT_SKILLS` | 4 | sp, tp, addsp, addtp |
| ??? | `pt_skin_chat_info.java.proto` | `PT_SKIN_CHAT_INFO` | 2 | chatframe, characterframe |
| ??? | `pt_skin_item.java.proto` | `PT_SKIN_ITEM` | 5 | index, guid, expiretime, acquisitionTime, favorite |
| ??? | `pt_stackable.java.proto` | `PT_STACKABLE` | 5 | index, count, Boolean, scount, tcount |
| ??? | `pt_stage_party_count.java.proto` | `PT_STAGE_PARTY_COUNT` | 4 | area, subtype, stageindex, count |
| ??? | `pt_subdue_info.java.proto` | `PT_SUBDUE_INFO` | 5 | index, starttime, endtime, state, count |
| ??? | `pt_system_buff_appendage.java.proto` | `PT_SYSTEM_BUFF_APPENDAGE` | 2 | index, endtime |
| ??? | `pt_ticket.java.proto` | `PT_TICKET` | 5 | dungeontype, dailyticket, weeklyticket, premiumticket, sweepticket |
| ??? | `pt_ticket_reward_info.java.proto` | `PT_TICKET_REWARD_INFO` | 0 | N/A |
| ??? | `pt_tonic_info.java.proto` | `PT_TONIC_INFO` | 2 | index, level |
| ??? | `pt_tonic_material_usage.java.proto` | `PT_TONIC_MATERIAL_USAGE` | 2 | index, total |
| ??? | `pt_tower_of_illusion_clear_rate.java.proto` | `PT_TOWER_OF_ILLUSION_CLEAR_RATE` | 2 | floor, Float |
| ??? | `pt_tower_reward_items.java.proto` | `PT_TOWER_REWARD_ITEMS` | 2 | index, count |
| ??? | `pt_town_interval.java.proto` | `PT_TOWN_INTERVAL` | 2 | townindex, refreshinterval |
| ??? | `pt_tsslightsig.java.proto` | `PT_TSSLIGHTSIG` | 3 | gamesafedata, gamesafedatacrc, gamesafedatafilename |
| ??? | `pt_tsslightsig_list.java.proto` | `PT_TSSLIGHTSIG_LIST` | 0 | N/A |
| ??? | `pt_tutorial.java.proto` | `PT_TUTORIAL` | 4 | index, state, type, Boolean |
| ??? | `pt_tutorial_data.java.proto` | `PT_TUTORIAL_DATA` | 4 | index, state, type, Boolean |
| ??? | `pt_tutorial_reward.java.proto` | `PT_TUTORIAL_REWARD` | 2 | index, count |
| ??? | `pt_user_info.java.proto` | `PT_USER_INFO` | 5 | charguid, playerid, objectgroupid, cobjectid, performancerating |
| ??? | `pt_user_loading_status.java.proto` | `PT_USER_LOADING_STATUS` | 5 | charguid, Boolean, Boolean, Boolean, Boolean |
| ??? | `pt_user_minimum_info.java.proto` | `PT_USER_MINIMUM_INFO` | 5 | charguid, growtype, secgrowtype, job, level |
| ??? | `pt_verification.java.proto` | `PT_VERIFICATION` | 1 | clientstarttime |
| ??? | `pt_wardrobe_info.java.proto` | `PT_WARDROBE_INFO` | 3 | extentionslot, appendageslot, slot |
| ??? | `pt_wedding_invitation.java.proto` | `PT_WEDDING_INVITATION` | 5 | marriageguid, inviterguid, invitername, spouseguid, spousename |
| ??? | `pt_worldrade_reward.java.proto` | `PT_WORLDRADE_REWARD` | 3 | index, count, totalscore |
| 102030 | `req_accept_quest.java.proto` | `REQ_ACCEPT_QUEST` | 4 | authkey, charguid, qindex, type |
| 107000 | `req_achievement_info.java.proto` | `REQ_ACHIEVEMENT_INFO` | 1 | type |
| 107040 | `req_achievement_list.java.proto` | `REQ_ACHIEVEMENT_LIST` | 1 | type |
| 107010 | `req_achievement_reward.java.proto` | `REQ_ACHIEVEMENT_REWARD` | 2 | type, guid |
| 172120 | `req_adventure_reap_info.java.proto` | `REQ_ADVENTURE_REAP_INFO` | 0 | N/A |
| 172130 | `req_adventure_reap_reward.java.proto` | `REQ_ADVENTURE_REAP_REWARD` | 1 | error |
| 141010 | `req_adventure_storage_list.java.proto` | `REQ_ADVENTURE_STORAGE_LIST` | 0 | N/A |
| 172210 | `req_adventure_union_collection_reward.java.proto` | `REQ_ADVENTURE_UNION_COLLECTION_REWARD` | 1 | rewardindex |
| 172000 | `req_adventure_union_info.java.proto` | `REQ_ADVENTURE_UNION_INFO` | 0 | N/A |
| 172160 | `req_adventure_union_info_other.java.proto` | `REQ_ADVENTURE_UNION_INFO_OTHER` | 1 | charguid |
| 172220 | `req_adventure_union_level_reward.java.proto` | `REQ_ADVENTURE_UNION_LEVEL_REWARD` | 1 | level |
| 172010 | `req_adventure_union_name_change.java.proto` | `REQ_ADVENTURE_UNION_NAME_CHANGE` | 1 | name |
| 172100 | `req_adventure_union_open_shareboard_slot.java.proto` | `REQ_ADVENTURE_UNION_OPEN_SHAREBOARD_SLOT` | 1 | slot |
| 172150 | `req_adventure_union_search_start.java.proto` | `REQ_ADVENTURE_UNION_SEARCH_START` | 0 | N/A |
| 172110 | `req_adventure_union_set_shareboard.java.proto` | `REQ_ADVENTURE_UNION_SET_SHAREBOARD` | 3 | shareboardbackground, shareboardframe, Boolean |
| 172060 | `req_adventure_union_subdue_info.java.proto` | `REQ_ADVENTURE_UNION_SUBDUE_INFO` | 0 | N/A |
| 102100 | `req_adventurebook_info.java.proto` | `REQ_ADVENTUREBOOK_INFO` | 2 | authkey, accountkey |
| 102150 | `req_adventurebook_update_condition.java.proto` | `REQ_ADVENTUREBOOK_UPDATE_CONDITION` | 1 | authkey |
| 110430 | `req_arcade_pvp_reward_info.java.proto` | `REQ_ARCADE_PVP_REWARD_INFO` | 0 | N/A |
| 140300 | `req_artifact_list.java.proto` | `REQ_ARTIFACT_LIST` | 0 | N/A |
| 100630 | `req_auction_buy_at_once.java.proto` | `REQ_AUCTION_BUY_AT_ONCE` | 4 | category, index, price, qty |
| 100600 | `req_auction_category_item_list.java.proto` | `REQ_AUCTION_CATEGORY_ITEM_LIST` | 1 | category |
| 100740 | `req_auction_history.java.proto` | `REQ_AUCTION_HISTORY` | 1 | type |
| 100640 | `req_auction_item_price_list.java.proto` | `REQ_AUCTION_ITEM_PRICE_LIST` | 2 | category, index |
| 100680 | `req_auction_my_registered_item_list.java.proto` | `REQ_AUCTION_MY_REGISTERED_ITEM_LIST` | 0 | N/A |
| 100670 | `req_auction_register_cancel.java.proto` | `REQ_AUCTION_REGISTER_CANCEL` | 3 | category, auid, index |
| 100700 | `req_auction_register_complete.java.proto` | `REQ_AUCTION_REGISTER_COMPLETE` | 2 | auid, itemindex |
| 100660 | `req_auction_register_item.java.proto` | `REQ_AUCTION_REGISTER_ITEM` | 5 | type, tab, guid, price, index |
| 100090 | `req_authkey_refresh.java.proto` | `REQ_AUTHKEY_REFRESH` | 1 | authkey |
| 140150 | `req_avatar_item_list.java.proto` | `REQ_AVATAR_ITEM_LIST` | 0 | N/A |
| 100340 | `req_avatar_visible_flags_set.java.proto` | `REQ_AVATAR_VISIBLE_FLAGS_SET` | 1 | flags |
| ??? | `req_battle_pass_ranking.java.proto` | `REQ_BATTLE_PASS_RANKING` | 0 | N/A |
| 102520 | `req_battle_tutorial_save.java.proto` | `REQ_BATTLE_TUTORIAL_SAVE` | 1 | state |
| 100920 | `req_biling_kr_balance.java.proto` | `REQ_BILING_KR_BALANCE` | 0 | N/A |
| 109000 | `req_black_diamon_info.java.proto` | `REQ_BLACK_DIAMON_INFO` | 0 | N/A |
| 140120 | `req_bookmark_list.java.proto` | `REQ_BOOKMARK_LIST` | 0 | N/A |
| 140500 | `req_card_attach.java.proto` | `REQ_CARD_ATTACH` | 2 | type, guid |
| 140650 | `req_card_compose.java.proto` | `REQ_CARD_COMPOSE` | 0 | N/A |
| 140190 | `req_card_list.java.proto` | `REQ_CARD_LIST` | 0 | N/A |
| 111020 | `req_cera_gacha_buy.java.proto` | `REQ_CERA_GACHA_BUY` | 4 | openkey, paytoken, index, type |
| 111030 | `req_cera_gacha_mileage.java.proto` | `REQ_CERA_GACHA_MILEAGE` | 0 | N/A |
| 111070 | `req_cera_interesting_goods_list.java.proto` | `REQ_CERA_INTERESTING_GOODS_LIST` | 0 | N/A |
| 111000 | `req_cera_shop_buy.java.proto` | `REQ_CERA_SHOP_BUY` | 3 | shopid, openkey, paytoken |
| 111010 | `req_cera_shop_buy_info.java.proto` | `REQ_CERA_SHOP_BUY_INFO` | 0 | N/A |
| 100080 | `req_channel_list.java.proto` | `REQ_CHANNEL_LIST` | 2 | type, worldid |
| 141000 | `req_char_storage_list.java.proto` | `REQ_CHAR_STORAGE_LIST` | 0 | N/A |
| 100020 | `req_charac_list.java.proto` | `REQ_CHARAC_LIST` | 0 | N/A |
| 140940 | `req_character_frame_tab_list.java.proto` | `REQ_CHARACTER_FRAME_TAB_LIST` | 0 | N/A |
| 101030 | `req_character_info.java.proto` | `REQ_CHARACTER_INFO` | 2 | authkey, option |
| 140930 | `req_chat_frame_tab_list.java.proto` | `REQ_CHAT_FRAME_TAB_LIST` | 0 | N/A |
| 101170 | `req_check_prohibited_word.java.proto` | `REQ_CHECK_PROHIBITED_WORD` | 1 | msg |
| 157150 | `req_chivalry_info.java.proto` | `REQ_CHIVALRY_INFO` | 0 | N/A |
| 110150 | `req_clear_dungeon_info_list.java.proto` | `REQ_CLEAR_DUNGEON_INFO_LIST` | 3 | index, difficulty, authkey |
| 160000 | `req_collection_load.java.proto` | `REQ_COLLECTION_LOAD` | 0 | N/A |
| 102010 | `req_complete_quest.java.proto` | `REQ_COMPLETE_QUEST` | 3 | qindex, state, type |
| 100140 | `req_connect_battle_server.java.proto` | `REQ_CONNECT_BATTLE_SERVER` | 5 | authkey, openid, world, channel, charguid |
| 140720 | `req_consumable_list.java.proto` | `REQ_CONSUMABLE_LIST` | 0 | N/A |
| 102600 | `req_contents_preview_load.java.proto` | `REQ_CONTENTS_PREVIEW_LOAD` | 0 | N/A |
| 102610 | `req_contents_preview_reward.java.proto` | `REQ_CONTENTS_PREVIEW_REWARD` | 1 | index |
| 130250 | `req_control_group.java.proto` | `REQ_CONTROL_GROUP` | 5 | type, partyleaderguid, targetguid, partyguid, charguid |
| 130530 | `req_control_group_custom.java.proto` | `REQ_CONTROL_GROUP_CUSTOM` | 0 | N/A |
| 130540 | `req_control_group_queryarea.java.proto` | `REQ_CONTROL_GROUP_QUERYAREA` | 1 | partyguid |
| 140960 | `req_crack_list.java.proto` | `REQ_CRACK_LIST` | 0 | N/A |
| 140970 | `req_crackequip_list.java.proto` | `REQ_CRACKEQUIP_LIST` | 0 | N/A |
| 100030 | `req_create_character.java.proto` | `REQ_CREATE_CHARACTER` | 2 | job, name |
| 270000 | `req_create_guild.java.proto` | `REQ_CREATE_GUILD` | 5 | gname, gboard, Boolean, area, minlevel |
| 140850 | `req_creature_communion_info.java.proto` | `REQ_CREATURE_COMMUNION_INFO` | 0 | N/A |
| 173000 | `req_creature_errand_list.java.proto` | `REQ_CREATURE_ERRAND_LIST` | 0 | N/A |
| 140330 | `req_creature_feeding.java.proto` | `REQ_CREATURE_FEEDING` | 1 | creature |
| 140290 | `req_creature_list.java.proto` | `REQ_CREATURE_LIST` | 0 | N/A |
| 130710 | `req_custom_game_room_setting.java.proto` | `REQ_CUSTOM_GAME_ROOM_SETTING` | 0 | N/A |
| 100510 | `req_daily_reset.java.proto` | `REQ_DAILY_RESET` | 0 | N/A |
| 140920 | `req_damage_font_tab_list.java.proto` | `REQ_DAMAGE_FONT_TAB_LIST` | 0 | N/A |
| 103070 | `req_delete_friend.java.proto` | `REQ_DELETE_FRIEND` | 1 | fguid |
| 330000 | `req_dream_maze_basicinfo.java.proto` | `REQ_DREAM_MAZE_BASICINFO` | 0 | N/A |
| 110420 | `req_dungeon_monster_die.java.proto` | `REQ_DUNGEON_MONSTER_DIE` | 3 | dungeonguid, stageguid, playercount |
| 110120 | `req_dungeon_result.java.proto` | `REQ_DUNGEON_RESULT` | 5 | byte, authkey, dungeonguid, stage, rankscore |
| 101500 | `req_dungeon_tickets.java.proto` | `REQ_DUNGEON_TICKETS` | 0 | N/A |
| 140180 | `req_emblem_equip.java.proto` | `REQ_EMBLEM_EQUIP` | 2 | type, guid |
| 140160 | `req_emblem_list.java.proto` | `REQ_EMBLEM_LIST` | 0 | N/A |
| 100110 | `req_enter_channel.java.proto` | `REQ_ENTER_CHANNEL` | 5 | openid, charguid, authkey, version, accesstoken |
| 110250 | `req_enter_stage.java.proto` | `REQ_ENTER_STAGE` | 5 | dungeonguid, stageguid, matchingguid, time, cinematime |
| 101000 | `req_enter_to_town.java.proto` | `REQ_ENTER_TO_TOWN` | 5 | authkey, town, area, posx, posy |
| 140610 | `req_epic_piece_list.java.proto` | `REQ_EPIC_PIECE_LIST` | 0 | N/A |
| 140010 | `req_equip_list.java.proto` | `REQ_EQUIP_LIST` | 0 | N/A |
| 140030 | `req_equip_put_on_off.java.proto` | `REQ_EQUIP_PUT_ON_OFF` | 1 | boolean |
| 140640 | `req_equipment_rarify.java.proto` | `REQ_EQUIPMENT_RARIFY` | 2 | guid, type |
| 140000 | `req_equipped_list.java.proto` | `REQ_EQUIPPED_LIST` | 0 | N/A |
| 140200 | `req_essence_list.java.proto` | `REQ_ESSENCE_LIST` | 0 | N/A |
| 108020 | `req_event_get_reward.java.proto` | `REQ_EVENT_GET_REWARD` | 2 | index, Boolean |
| 108000 | `req_event_list.java.proto` | `REQ_EVENT_LIST` | 1 | page |
| 100070 | `req_exit_character.java.proto` | `REQ_EXIT_CHARACTER` | 4 | world, channel, reservationtype, exittype |
| 290150 | `req_fame_and_charm_point.java.proto` | `REQ_FAME_AND_CHARM_POINT` | 0 | N/A |
| 140730 | `req_flag_list.java.proto` | `REQ_FLAG_LIST` | 0 | N/A |
| 103150 | `req_friend_find.java.proto` | `REQ_FRIEND_FIND` | 1 | fname |
| 103190 | `req_friend_recommend.java.proto` | `REQ_FRIEND_RECOMMEND` | 0 | N/A |
| 103180 | `req_friend_recommend_with_me.java.proto` | `REQ_FRIEND_RECOMMEND_WITH_ME` | 0 | N/A |
| 101620 | `req_game_flow_tlog.java.proto` | `REQ_GAME_FLOW_TLOG` | 5 | dungeonguid, stageguid, sectlogtype, sectlogvalue, gamesafedata |
| 110470 | `req_get_local_reward.java.proto` | `REQ_GET_LOCAL_REWARD` | 0 | N/A |
| 111040 | `req_get_privatestore_goodslist.java.proto` | `REQ_GET_PRIVATESTORE_GOODSLIST` | 1 | shopid |
| 325090 | `req_guild_contents_buff_load.java.proto` | `REQ_GUILD_CONTENTS_BUFF_LOAD` | 0 | N/A |
| 157120 | `req_half_open_party_accept.java.proto` | `REQ_HALF_OPEN_PARTY_ACCEPT` | 1 | charguid |
| 157110 | `req_half_open_party_join.java.proto` | `REQ_HALF_OPEN_PARTY_JOIN` | 2 | leaderguid, partyguid |
| 157130 | `req_half_open_party_refuse.java.proto` | `REQ_HALF_OPEN_PARTY_REFUSE` | 1 | leaderguid |
| 101310 | `req_hidden_chatting_load.java.proto` | `REQ_HIDDEN_CHATTING_LOAD` | 0 | N/A |
| 271590 | `req_historicsite_noti.java.proto` | `REQ_HISTORICSITE_NOTI` | 0 | N/A |
| 101240 | `req_idip_check_reward.java.proto` | `REQ_IDIP_CHECK_REWARD` | 0 | N/A |
| 100180 | `req_idip_notices.java.proto` | `REQ_IDIP_NOTICES` | 0 | N/A |
| 100170 | `req_idip_prohibit_list.java.proto` | `REQ_IDIP_PROHIBIT_LIST` | 0 | N/A |
| 115060 | `req_inquire_party_ranking.java.proto` | `REQ_INQUIRE_PARTY_RANKING` | 4 | season, type, rankMin, rankMax |
| 115000 | `req_inquire_personal_ranking.java.proto` | `REQ_INQUIRE_PERSONAL_RANKING` | 5 | season, type, rankMin, rankMax, job |
| 101080 | `req_interaction_menu.java.proto` | `REQ_INTERACTION_MENU` | 2 | charguid, openmenutype |
| 140660 | `req_inven_extend.java.proto` | `REQ_INVEN_EXTEND` | 1 | type |
| 140210 | `req_item_avatar_compose.java.proto` | `REQ_ITEM_AVATAR_COMPOSE` | 0 | N/A |
| 100620 | `req_item_avr_price.java.proto` | `REQ_ITEM_AVR_PRICE` | 4 | index, upgrade, amplify, reforge |
| 140420 | `req_item_combine.java.proto` | `REQ_ITEM_COMBINE` | 3 | index, recipe, count |
| 140070 | `req_item_disjoint.java.proto` | `REQ_ITEM_DISJOINT` | 1 | type |
| 140270 | `req_item_emblem_extract.java.proto` | `REQ_ITEM_EMBLEM_EXTRACT` | 3 | guid, type, slot |
| 140230 | `req_item_emblem_upgrade_inven.java.proto` | `REQ_ITEM_EMBLEM_UPGRADE_INVEN` | 3 | index, trycount, talisman |
| 140790 | `req_item_emblem_upgrade_quick.java.proto` | `REQ_ITEM_EMBLEM_UPGRADE_QUICK` | 1 | target |
| 140740 | `req_item_production_info.java.proto` | `REQ_ITEM_PRODUCTION_INFO` | 1 | slottype |
| 140750 | `req_item_production_register.java.proto` | `REQ_ITEM_PRODUCTION_REGISTER` | 3 | slotindex, count, recipeindex |
| 140060 | `req_item_reinforce.java.proto` | `REQ_ITEM_REINFORCE` | 4 | type, guid, talisman, customtype |
| 140800 | `req_item_usable_list.java.proto` | `REQ_ITEM_USABLE_LIST` | 0 | N/A |
| 140170 | `req_item_use.java.proto` | `REQ_ITEM_USE` | 5 | index, count, Boolean, guid, type |
| 101090 | `req_leave_from_town.java.proto` | `REQ_LEAVE_FROM_TOWN` | 0 | N/A |
| 101150 | `req_load_blacklist.java.proto` | `REQ_LOAD_BLACKLIST` | 0 | N/A |
| 103000 | `req_load_friends_info.java.proto` | `REQ_LOAD_FRIENDS_INFO` | 0 | N/A |
| ??? | `req_load_guild_donation_info_2.java.proto` | `REQ_LOAD_GUILD_DONATION_INFO_2` | 0 | N/A |
| 100310 | `req_load_server_simple_data.java.proto` | `REQ_LOAD_SERVER_SIMPLE_DATA` | 0 | N/A |
| 106500 | `req_load_share_reward.java.proto` | `REQ_LOAD_SHARE_REWARD` | 0 | N/A |
| 104090 | `req_load_wedding_invitation_list.java.proto` | `REQ_LOAD_WEDDING_INVITATION_LIST` | 0 | N/A |
| 260030 | `req_loading_progress.java.proto` | `REQ_LOADING_PROGRESS` | 3 | matchingguid, charguid, value |
| 110460 | `req_local_reward_history.java.proto` | `REQ_LOCAL_REWARD_HISTORY` | 0 | N/A |
| 100000 | `req_login.java.proto` | `REQ_LOGIN` | 5 | openid, type, token, platID, clientIP |
| 110140 | `req_looting.java.proto` | `REQ_LOOTING` | 5 | authkey, stage, matchingguid, time, dungeonguid |
| 150060 | `req_mail_all_delete.java.proto` | `REQ_MAIL_ALL_DELETE` | 1 | type |
| 150040 | `req_mail_delete.java.proto` | `REQ_MAIL_DELETE` | 2 | guid, type |
| 150020 | `req_mail_get.java.proto` | `REQ_MAIL_GET` | 2 | type, guid |
| 150010 | `req_mail_list.java.proto` | `REQ_MAIL_LIST` | 2 | page, type |
| 150030 | `req_mail_read.java.proto` | `REQ_MAIL_READ` | 2 | guid, type |
| 140020 | `req_material_list.java.proto` | `REQ_MATERIAL_LIST` | 0 | N/A |
| 281140 | `req_minigame_breaking_load.java.proto` | `REQ_MINIGAME_BREAKING_LOAD` | 0 | N/A |
| 281100 | `req_minigame_chemical_load.java.proto` | `REQ_MINIGAME_CHEMICAL_LOAD` | 0 | N/A |
| 281020 | `req_minigame_lottery_list.java.proto` | `REQ_MINIGAME_LOTTERY_LIST` | 0 | N/A |
| 281320 | `req_minigame_mini_pong_load.java.proto` | `REQ_MINIGAME_MINI_PONG_LOAD` | 0 | N/A |
| 281400 | `req_minigame_restaurant_load.java.proto` | `REQ_MINIGAME_RESTAURANT_LOAD` | 0 | N/A |
| 142000 | `req_money_item_list.java.proto` | `REQ_MONEY_ITEM_LIST` | 0 | N/A |
| 157070 | `req_multi_play_dungeon_enter_complete.java.proto` | `REQ_MULTI_PLAY_DUNGEON_ENTER_COMPLETE` | 1 | dungeonguid |
| 130000 | `req_multi_play_request_match.java.proto` | `REQ_MULTI_PLAY_REQUEST_MATCH` | 5 | authkey, type, index, partyguid, partyleaderguid |
| 130010 | `req_multi_play_request_match_cancel.java.proto` | `REQ_MULTI_PLAY_REQUEST_MATCH_CANCEL` | 2 | authkey, type |
| 157000 | `req_multi_play_start_dungeon.java.proto` | `REQ_MULTI_PLAY_START_DUNGEON` | 4 | dungeonguid, Boolean, gamesafedata, gamesafedatacrc |
| 130020 | `req_multi_play_sync_dungeon.java.proto` | `REQ_MULTI_PLAY_SYNC_DUNGEON` | 5 | authkey, type, matchingguid, Boolean, gamesafedata |
| 115080 | `req_my_party_ranking.java.proto` | `REQ_MY_PARTY_RANKING` | 1 | type |
| 115030 | `req_my_ranking.java.proto` | `REQ_MY_RANKING` | 1 | type |
| 300010 | `req_new_contents_list.java.proto` | `REQ_NEW_CONTENTS_LIST` | 2 | content, type |
| 101530 | `req_not_transaction_character_state_info.java.proto` | `REQ_NOT_TRANSACTION_CHARACTER_STATE_INFO` | 2 | status, Boolean |
| 101120 | `req_note_messenger_add_user.java.proto` | `REQ_NOTE_MESSENGER_ADD_USER` | 1 | targetguid |
| 101100 | `req_note_messenger_load.java.proto` | `REQ_NOTE_MESSENGER_LOAD` | 0 | N/A |
| 102140 | `req_npc_count.java.proto` | `REQ_NPC_COUNT` | 1 | npcindex |
| 140050 | `req_npc_shop_item_sell.java.proto` | `REQ_NPC_SHOP_ITEM_SELL` | 0 | N/A |
| 135650 | `req_party_dungeon_condition.java.proto` | `REQ_PARTY_DUNGEON_CONDITION` | 5 | matchtype, index, grade, partyguid, type |
| 130080 | `req_party_loading_status.java.proto` | `REQ_PARTY_LOADING_STATUS` | 1 | matchingguid |
| 100060 | `req_ping.java.proto` | `REQ_PING` | 0 | N/A |
| 130120 | `req_pvp_record_info.java.proto` | `REQ_PVP_RECORD_INFO` | 3 | authkey, matchtype, Boolean |
| 108040 | `req_query_access_time_by_event.java.proto` | `REQ_QUERY_ACCESS_TIME_BY_EVENT` | 1 | index |
| 102000 | `req_quest_info.java.proto` | `REQ_QUEST_INFO` | 2 | authkey, charguid |
| 112330 | `req_raid_entrance_count.java.proto` | `REQ_RAID_ENTRANCE_COUNT` | 0 | N/A |
| 140440 | `req_randomoption_change.java.proto` | `REQ_RANDOMOPTION_CHANGE` | 2 | guid, type |
| 140450 | `req_randomoption_select.java.proto` | `REQ_RANDOMOPTION_SELECT` | 3 | guid, type, selecttype |
| 140700 | `req_randomoption_slot_lock.java.proto` | `REQ_RANDOMOPTION_SLOT_LOCK` | 4 | guid, type, index, Boolean |
| 140430 | `req_randomoption_unlock.java.proto` | `REQ_RANDOMOPTION_UNLOCK` | 2 | guid, type |
| 115040 | `req_rank_friend.java.proto` | `REQ_RANK_FRIEND` | 3 | type, job, growtype |
| 130060 | `req_ready_to_lockstep.java.proto` | `REQ_READY_TO_LOCKSTEP` | 1 | matchingguid |
| 103010 | `req_received_invite_friend_list.java.proto` | `REQ_RECEIVED_INVITE_FRIEND_LIST` | 0 | N/A |
| 130240 | `req_recommand_group.java.proto` | `REQ_RECOMMAND_GROUP` | 4 | dungeonindex, dungeongrade, friendseq, name |
| 270030 | `req_recommend_guild_list.java.proto` | `REQ_RECOMMEND_GUILD_LIST` | 5 | page, Boolean, area, minlevel, maxlevel |
| 135570 | `req_refresh_target_character_town_info.java.proto` | `REQ_REFRESH_TARGET_CHARACTER_TOWN_INFO` | 1 | charguid |
| 100040 | `req_remove_character.java.proto` | `REQ_REMOVE_CHARACTER` | 1 | charguid |
| 103050 | `req_reply_friend_invite.java.proto` | `REQ_REPLY_FRIEND_INVITE` | 2 | type, fguid |
| 141150 | `req_repurchase_item.java.proto` | `REQ_REPURCHASE_ITEM` | 0 | N/A |
| 141140 | `req_repurchase_item_storage_list.java.proto` | `REQ_REPURCHASE_ITEM_STORAGE_LIST` | 0 | N/A |
| 103030 | `req_request_friend_invite.java.proto` | `REQ_REQUEST_FRIEND_INVITE` | 3 | fguid, msg, route |
| 130450 | `req_request_to_re_enter_accept_dungeon.java.proto` | `REQ_REQUEST_TO_RE_ENTER_ACCEPT_DUNGEON` | 2 | subtype, guid |
| 130440 | `req_request_to_re_enter_dungeon.java.proto` | `REQ_REQUEST_TO_RE_ENTER_DUNGEON` | 5 | difficulty, subtype, reason, dungeonindex, guid |
| 130280 | `req_return_to_town_at_multi_play.java.proto` | `REQ_RETURN_TO_TOWN_AT_MULTI_PLAY` | 2 | type, reason |
| 102020 | `req_reward_quest.java.proto` | `REQ_REWARD_QUEST` | 4 | authkey, charguid, qindex, type |
| 100320 | `req_save_server_simple_data.java.proto` | `REQ_SAVE_SERVER_SIMPLE_DATA` | 3 | type, enumvalue, value |
| 140130 | `req_scroll_list.java.proto` | `REQ_SCROLL_LIST` | 0 | N/A |
| 141070 | `req_sd_avatar_list.java.proto` | `REQ_SD_AVATAR_LIST` | 0 | N/A |
| 135470 | `req_search_party_list.java.proto` | `REQ_SEARCH_PARTY_LIST` | 5 | type, area, subtype, stageindex, grade |
| 107800 | `req_season_pass_info.java.proto` | `REQ_SEASON_PASS_INFO` | 0 | N/A |
| 110130 | `req_select_reward_card.java.proto` | `REQ_SELECT_REWARD_CARD` | 3 | cardtype, authkey, dungeonguid |
| 141030 | `req_send_inven.java.proto` | `REQ_SEND_INVEN` | 1 | storage |
| 141020 | `req_send_storage.java.proto` | `REQ_SEND_STORAGE` | 2 | type, storage |
| 103020 | `req_sending_invite_friend_list.java.proto` | `REQ_SENDING_INVITE_FRIEND_LIST` | 0 | N/A |
| 100220 | `req_server_noti_ack.java.proto` | `REQ_SERVER_NOTI_ACK` | 1 | charguid |
| 120020 | `req_skill_list.java.proto` | `REQ_SKILL_LIST` | 1 | type |
| 120010 | `req_skill_set.java.proto` | `REQ_SKILL_SET` | 1 | type |
| 120030 | `req_skill_slot.java.proto` | `REQ_SKILL_SLOT` | 1 | type |
| 110230 | `req_stage_clear.java.proto` | `REQ_STAGE_CLEAR` | 5 | byte, authkey, dungeonguid, stage, rankscore |
| 100010 | `req_standby.java.proto` | `REQ_STANDBY` | 0 | N/A |
| 110110 | `req_start_dungeon.java.proto` | `REQ_START_DUNGEON` | 5 | index, authkey, Boolean, grade, dungeonguid |
| 110220 | `req_start_dungeon_complete.java.proto` | `REQ_START_DUNGEON_COMPLETE` | 2 | matchingguid, time |
| 100050 | `req_start_game.java.proto` | `REQ_START_GAME` | 5 | charguid, dungeonguid, authkey, accesstoken, paytoken |
| 130270 | `req_start_multi_play.java.proto` | `REQ_START_MULTI_PLAY` | 5 | input, type, index, grade, partyguid |
| 141040 | `req_storage_extend.java.proto` | `REQ_STORAGE_EXTEND` | 1 | storage |
| 141050 | `req_storage_step.java.proto` | `REQ_STORAGE_STEP` | 0 | N/A |
| 157240 | `req_suggest_move_party.java.proto` | `REQ_SUGGEST_MOVE_PARTY` | 2 | partyguid, suggesttype |
| 110300 | `req_sync_dungeon_start_time.java.proto` | `REQ_SYNC_DUNGEON_START_TIME` | 0 | N/A |
| 153050 | `req_system_buff_appendage_list.java.proto` | `REQ_SYSTEM_BUFF_APPENDAGE_LIST` | 0 | N/A |
| 101070 | `req_target_user_detail_info.java.proto` | `REQ_TARGET_USER_DETAIL_INFO` | 1 | targetguid |
| 135480 | `req_target_user_party_info.java.proto` | `REQ_TARGET_USER_PARTY_INFO` | 2 | charguid, partyguid |
| 140490 | `req_title_list.java.proto` | `REQ_TITLE_LIST` | 0 | N/A |
| 101600 | `req_tlog_controller_data.java.proto` | `REQ_TLOG_CONTROLLER_DATA` | 5 | movecontroltype, runplaycondtype, slideskilltype, combocount, pvpcombocount |
| 110390 | `req_tonic_info.java.proto` | `REQ_TONIC_INFO` | 0 | N/A |
| 110400 | `req_tonic_init.java.proto` | `REQ_TONIC_INIT` | 1 | index |
| 110410 | `req_tonic_upgrade.java.proto` | `REQ_TONIC_UPGRADE` | 2 | index, Boolean |
| 110800 | `req_tower_info.java.proto` | `REQ_TOWER_INFO` | 1 | type |
| 101050 | `req_town_chat.java.proto` | `REQ_TOWN_CHAT` | 5 | type, chat, voice, voicetime, name |
| 101250 | `req_town_chat_list.java.proto` | `REQ_TOWN_CHAT_LIST` | 0 | N/A |
| 101060 | `req_town_user_guid_list.java.proto` | `REQ_TOWN_USER_GUID_LIST` | 3 | count, posx, posy |
| 140460 | `req_transmission_item.java.proto` | `REQ_TRANSMISSION_ITEM` | 4 | type, materialguid, itemguid, Boolean |
| 102500 | `req_tutorial_list.java.proto` | `REQ_TUTORIAL_LIST` | 0 | N/A |
| 102510 | `req_tutorial_save.java.proto` | `REQ_TUTORIAL_SAVE` | 0 | N/A |
| 110180 | `req_use_coin.java.proto` | `REQ_USE_COIN` | 3 | guid, usetype, frame |
| 130090 | `req_vote_kick_out_user.java.proto` | `REQ_VOTE_KICK_OUT_USER` | 3 | matchingguid, targetguid, Boolean |
| 130070 | `req_waiting_to_users_loading.java.proto` | `REQ_WAITING_TO_USERS_LOADING` | 1 | matchingguid |
| 140810 | `req_wardrobe_info.java.proto` | `REQ_WARDROBE_INFO` | 0 | N/A |
| 143020 | `req_wardrobe_set_slot.java.proto` | `REQ_WARDROBE_SET_SLOT` | 1 | slot |
| 102031 | `res_accept_quest.java.proto` | `RES_ACCEPT_QUEST` | 4 | error, qindex, type, state |
| 107001 | `res_achievement_info.java.proto` | `RES_ACHIEVEMENT_INFO` | 3 | error, type, score |
| 107041 | `res_achievement_list.java.proto` | `RES_ACHIEVEMENT_LIST` | 4 | error, type, page, total |
| 107011 | `res_achievement_reward.java.proto` | `RES_ACHIEVEMENT_REWARD` | 3 | error, adventureunionlevel, adventureunionexp |
| 172121 | `res_adventure_reap_info.java.proto` | `RES_ADVENTURE_REAP_INFO` | 2 | error, starttime |
| 172131 | `res_adventure_reap_reward.java.proto` | `RES_ADVENTURE_REAP_REWARD` | 4 | error, starttime, adventureunionlevel, adventureunionexp |
| 141011 | `res_adventure_storage_list.java.proto` | `RES_ADVENTURE_STORAGE_LIST` | 5 | error, line, page, storagegold, storagetera |
| 172211 | `res_adventure_union_collection_reward.java.proto` | `RES_ADVENTURE_UNION_COLLECTION_REWARD` | 2 | error, rewardindex |
| 172001 | `res_adventure_union_info.java.proto` | `RES_ADVENTURE_UNION_INFO` | 5 | error, exp, level, day, typicalcharacterguid |
| 172161 | `res_adventure_union_info_other.java.proto` | `RES_ADVENTURE_UNION_INFO_OTHER` | 5 | error, level, adventureunionexp, day, name |
| 172221 | `res_adventure_union_level_reward.java.proto` | `RES_ADVENTURE_UNION_LEVEL_REWARD` | 1 | error |
| 172011 | `res_adventure_union_name_change.java.proto` | `RES_ADVENTURE_UNION_NAME_CHANGE` | 2 | error, name |
| 172101 | `res_adventure_union_open_shareboard_slot.java.proto` | `RES_ADVENTURE_UNION_OPEN_SHAREBOARD_SLOT` | 2 | error, slot |
| 172151 | `res_adventure_union_search_start.java.proto` | `RES_ADVENTURE_UNION_SEARCH_START` | 1 | error |
| 172111 | `res_adventure_union_set_shareboard.java.proto` | `RES_ADVENTURE_UNION_SET_SHAREBOARD` | 4 | error, shareboardbackground, shareboardframe, Boolean |
| 172061 | `res_adventure_union_subdue_info.java.proto` | `RES_ADVENTURE_UNION_SUBDUE_INFO` | 1 | error |
| 102101 | `res_adventurebook_info.java.proto` | `RES_ADVENTUREBOOK_INFO` | 1 | error |
| 102151 | `res_adventurebook_update_condition.java.proto` | `RES_ADVENTUREBOOK_UPDATE_CONDITION` | 1 | error |
| 110431 | `res_arcade_pvp_reward_info.java.proto` | `RES_ARCADE_PVP_REWARD_INFO` | 5 | error, pvpstargrade, winpoint, prepvpstargrade, prewinpoint |
| 140301 | `res_artifact_list.java.proto` | `RES_ARTIFACT_LIST` | 3 | error, page, maxcount |
| 100631 | `res_auction_buy_at_once.java.proto` | `RES_AUCTION_BUY_AT_ONCE` | 5 | error, category, index, price, paymentamount |
| 100601 | `res_auction_category_list.java.proto` | `RES_AUCTION_CATEGORY_LIST` | 3 | error, category, time |
| 100741 | `res_auction_history.java.proto` | `RES_AUCTION_HISTORY` | 2 | error, type |
| 100641 | `res_auction_item_price_list.java.proto` | `RES_AUCTION_ITEM_PRICE_LIST` | 4 | error, category, index, time |
| 100681 | `res_auction_my_registered_item_list.java.proto` | `RES_AUCTION_MY_REGISTERED_ITEM_LIST` | 2 | error, time |
| 100671 | `res_auction_register_cancel.java.proto` | `RES_AUCTION_REGISTER_CANCEL` | 4 | error, category, auid, time |
| 100701 | `res_auction_register_complete.java.proto` | `RES_AUCTION_REGISTER_COMPLETE` | 4 | error, category, auid, time |
| 100661 | `res_auction_register_item.java.proto` | `RES_AUCTION_REGISTER_ITEM` | 5 | error, time, type, tab, guid |
| 100091 | `res_authkey_refresh.java.proto` | `RES_AUTHKEY_REFRESH` | 3 | error, authkey, version |
| 140151 | `res_avatar_item_list.java.proto` | `RES_AVATAR_ITEM_LIST` | 3 | error, page, maxcount |
| 100341 | `res_avatar_visible_flags_set.java.proto` | `RES_AVATAR_VISIBLE_FLAGS_SET` | 3 | error, guid, flags |
| ??? | `res_battle_pass_ranking.java.proto` | `RES_BATTLE_PASS_RANKING` | 1 | error |
| 102521 | `res_battle_tutorial_save.java.proto` | `RES_BATTLE_TUTORIAL_SAVE` | 2 | error, state |
| 100921 | `res_biling_kr_balance.java.proto` | `RES_BILING_KR_BALANCE` | 3 | error, cera, accumulatecera |
| 109001 | `res_black_diamon_info.java.proto` | `RES_BLACK_DIAMON_INFO` | 5 | error, reward, buff, payment, state |
| 140121 | `res_bookmark_list.java.proto` | `RES_BOOKMARK_LIST` | 3 | error, page, maxcount |
| 101211 | `res_broadcast_gvoice_member_list.java.proto` | `RES_BROADCAST_GVOICE_MEMBER_LIST` | 2 | error, type |
| 140501 | `res_card_attach.java.proto` | `RES_CARD_ATTACH` | 3 | error, type, guid |
| 140651 | `res_card_compose.java.proto` | `RES_CARD_COMPOSE` | 1 | error |
| 140191 | `res_card_list.java.proto` | `RES_CARD_LIST` | 3 | error, page, maxcount |
| 111021 | `res_cera_gacha_buy.java.proto` | `RES_CERA_GACHA_BUY` | 3 | error, grade, index |
| 111031 | `res_cera_gacha_mileage.java.proto` | `RES_CERA_GACHA_MILEAGE` | 1 | error |
| 111071 | `res_cera_interesting_goods_list.java.proto` | `RES_CERA_INTERESTING_GOODS_LIST` | 1 | error |
| 111001 | `res_cera_shop_buy.java.proto` | `RES_CERA_SHOP_BUY` | 4 | error, billno, shopid, battlepassexp |
| 111011 | `res_cera_shop_buy_info.java.proto` | `RES_CERA_SHOP_BUY_INFO` | 1 | error |
| 100101 | `res_change_job.java.proto` | `RES_CHANGE_JOB` | 4 | error, growtype, secgrowtype, changecount |
| 100081 | `res_channel_list.java.proto` | `RES_CHANNEL_LIST` | 3 | error, count, type |
| 141001 | `res_char_storage_list.java.proto` | `RES_CHAR_STORAGE_LIST` | 5 | error, line, page, storagegold, storagetera |
| 100021 | `res_charac_list.java.proto` | `RES_CHARAC_LIST` | 5 | error, version, count, adventureunionlevel, adventureunionexp |
| 140941 | `res_character_frame_tab_list.java.proto` | `RES_CHARACTER_FRAME_TAB_LIST` | 1 | error |
| 101031 | `res_character_info.java.proto` | `RES_CHARACTER_INFO` | 1 | error |
| 140931 | `res_chat_frame_tab_list.java.proto` | `RES_CHAT_FRAME_TAB_LIST` | 1 | error |
| 101171 | `res_check_prohibited_word.java.proto` | `RES_CHECK_PROHIBITED_WORD` | 2 | error, msg |
| 157151 | `res_chivalry_info.java.proto` | `RES_CHIVALRY_INFO` | 4 | error, totallike, like, rank |
| 110151 | `res_clear_dungeon_info_list.java.proto` | `RES_CLEAR_DUNGEON_INFO_LIST` | 1 | error |
| 160001 | `res_collection_load.java.proto` | `RES_COLLECTION_LOAD` | 3 | error, level, exp |
| 102011 | `res_complete_quest.java.proto` | `RES_COMPLETE_QUEST` | 2 | error, qindex |
| 100141 | `res_connect_battle_server.java.proto` | `RES_CONNECT_BATTLE_SERVER` | 5 | error, authkey, bchannel, servertime, Boolean |
| 140721 | `res_consumable_list.java.proto` | `RES_CONSUMABLE_LIST` | 3 | error, page, maxcount |
| 102601 | `res_contents_preview_load.java.proto` | `RES_CONTENTS_PREVIEW_LOAD` | 1 | error |
| 102611 | `res_contents_preview_reward.java.proto` | `RES_CONTENTS_PREVIEW_REWARD` | 2 | error, index |
| 130251 | `res_control_group.java.proto` | `RES_CONTROL_GROUP` | 5 | error, world, channel, partyguid, partyleaderguid |
| 130531 | `res_control_group_custom.java.proto` | `RES_CONTROL_GROUP_CUSTOM` | 1 | error |
| 130541 | `res_control_group_queryarea.java.proto` | `RES_CONTROL_GROUP_QUERYAREA` | 1 | error |
| 140961 | `res_crack_list.java.proto` | `RES_CRACK_LIST` | 3 | error, page, maxcount |
| 140971 | `res_crackequip_list.java.proto` | `RES_CRACKEQUIP_LIST` | 3 | error, page, maxcount |
| 100031 | `res_create_character.java.proto` | `RES_CREATE_CHARACTER` | 4 | error, charguid, job, name |
| 270001 | `res_create_guild.java.proto` | `RES_CREATE_GUILD` | 4 | error, gguid, gname, lastkicktime |
| 140851 | `res_creature_communion_info.java.proto` | `RES_CREATURE_COMMUNION_INFO` | 1 | error |
| 173001 | `res_creature_errand_list.java.proto` | `RES_CREATURE_ERRAND_LIST` | 1 | error |
| 140331 | `res_creature_feeding.java.proto` | `RES_CREATURE_FEEDING` | 3 | error, creature, exp |
| 140291 | `res_creature_list.java.proto` | `RES_CREATURE_LIST` | 3 | error, page, maxcount |
| 130711 | `res_custom_game_room_setting.java.proto` | `RES_CUSTOM_GAME_ROOM_SETTING` | 1 | error |
| 100511 | `res_daily_reset.java.proto` | `RES_DAILY_RESET` | 1 | error |
| 140921 | `res_damage_font_tab_list.java.proto` | `RES_DAMAGE_FONT_TAB_LIST` | 1 | error |
| 103071 | `res_delete_friend.java.proto` | `RES_DELETE_FRIEND` | 2 | error, fguid |
| 330001 | `res_dream_maze_basicinfo.java.proto` | `RES_DREAM_MAZE_BASICINFO` | 5 | error, state, opendungeonindex, rewardcount, clearrewardcount |
| 110421 | `res_dungeon_monster_die.java.proto` | `RES_DUNGEON_MONSTER_DIE` | 5 | error, totalexp, level, earnexp, sender |
| 110121 | `res_dungeon_result.java.proto` | `RES_DUNGEON_RESULT` | 1 | error |
| 101501 | `res_dungeon_tickets.java.proto` | `RES_DUNGEON_TICKETS` | 5 | error, fatiguetime, helpercount, jarofgreed, lotteryfreecount |
| 140181 | `res_emblem_equip.java.proto` | `RES_EMBLEM_EQUIP` | 4 | error, type, slot, guid |
| 140271 | `res_emblem_extract.java.proto` | `RES_EMBLEM_EXTRACT` | 4 | error, guid, type, slot |
| 140161 | `res_emblem_list.java.proto` | `RES_EMBLEM_LIST` | 3 | error, page, maxcount |
| 140231 | `res_emblem_upgrade.java.proto` | `RES_EMBLEM_UPGRADE` | 4 | error, index, trycount, success |
| 100111 | `res_enter_channel.java.proto` | `RES_ENTER_CHANNEL` | 5 | error, standby, Boolean, servertime, authkey |
| 110251 | `res_enter_stage.java.proto` | `RES_ENTER_STAGE` | 5 | error, fatigue, consumefatigue, recoveryfatiguetime, servertime |
| 101001 | `res_enter_to_town.java.proto` | `RES_ENTER_TO_TOWN` | 5 | error, town, area, posx, posy |
| 140611 | `res_epic_piece_list.java.proto` | `RES_EPIC_PIECE_LIST` | 3 | error, page, maxcount |
| 140011 | `res_equip_list.java.proto` | `RES_EQUIP_LIST` | 3 | error, page, maxcount |
| 140031 | `res_equip_put_on_off.java.proto` | `RES_EQUIP_PUT_ON_OFF` | 2 | error, equipscore |
| 140641 | `res_equipment_rarify.java.proto` | `RES_EQUIPMENT_RARIFY` | 4 | error, guid, type, quality |
| 140001 | `res_equipped_list.java.proto` | `RES_EQUIPPED_LIST` | 1 | error |
| 140201 | `res_essence_list.java.proto` | `RES_ESSENCE_LIST` | 3 | error, page, maxcount |
| 108021 | `res_event_get_reward.java.proto` | `RES_EVENT_GET_REWARD` | 3 | error, index, fatigue |
| 108001 | `res_event_list.java.proto` | `RES_EVENT_LIST` | 3 | error, page, maxpage |
| 100071 | `res_exit_character.java.proto` | `RES_EXIT_CHARACTER` | 5 | error, world, channel, Boolean, exittype |
| 290151 | `res_fame_and_charm_point.java.proto` | `RES_FAME_AND_CHARM_POINT` | 4 | error, fame, charm, remainCharm |
| 140731 | `res_flag_list.java.proto` | `RES_FLAG_LIST` | 3 | error, page, maxcount |
| 103151 | `res_friend_find.java.proto` | `RES_FRIEND_FIND` | 5 | error, fguid, name, level, job |
| 103191 | `res_friend_recommend.java.proto` | `RES_FRIEND_RECOMMEND` | 1 | error |
| 103181 | `res_friend_recommend_with_me.java.proto` | `RES_FRIEND_RECOMMEND_WITH_ME` | 1 | error |
| 110471 | `res_get_local_reward.java.proto` | `RES_GET_LOCAL_REWARD` | 1 | error |
| 111041 | `res_get_privatestore_goodslist.java.proto` | `RES_GET_PRIVATESTORE_GOODSLIST` | 5 | shopid, remaincostresetcount, lastfreeresettime, error, Boolean |
| 325091 | `res_guild_contents_buff_load.java.proto` | `RES_GUILD_CONTENTS_BUFF_LOAD` | 1 | error |
| 157121 | `res_half_open_party_accept.java.proto` | `RES_HALF_OPEN_PARTY_ACCEPT` | 5 | error, leaderguid, partyguid, ip, port |
| 157111 | `res_half_open_party_join.java.proto` | `RES_HALF_OPEN_PARTY_JOIN` | 5 | error, charguid, job, growtype, secondgrowtype |
| 101311 | `res_hidden_chatting_load.java.proto` | `RES_HIDDEN_CHATTING_LOAD` | 1 | error |
| 271591 | `res_historicsite_noti.java.proto` | `RES_HISTORICSITE_NOTI` | 2 | error, value |
| 101241 | `res_idip_check_reward.java.proto` | `RES_IDIP_CHECK_REWARD` | 1 | error |
| 100181 | `res_idip_notices.java.proto` | `RES_IDIP_NOTICES` | 1 | error |
| 100171 | `res_idip_prohibit_list.java.proto` | `RES_IDIP_PROHIBIT_LIST` | 1 | error |
| 115061 | `res_inquire_party_ranking.java.proto` | `RES_INQUIRE_PARTY_RANKING` | 4 | error, index, count, type |
| 115001 | `res_inquire_personal_ranking.java.proto` | `RES_INQUIRE_PERSONAL_RANKING` | 5 | error, count, rank, maxrank, job |
| 101081 | `res_interaction_menu.java.proto` | `RES_INTERACTION_MENU` | 5 | error, charguid, online, status, lastlogout |
| 140661 | `res_inven_extend.java.proto` | `RES_INVEN_EXTEND` | 3 | error, type, count |
| 140211 | `res_item_avatar_compose.java.proto` | `RES_ITEM_AVATAR_COMPOSE` | 2 | error, avatarcompounddailycount |
| 100621 | `res_item_avr_price.java.proto` | `RES_ITEM_AVR_PRICE` | 5 | error, index, price, upgrade, amplify |
| 140421 | `res_item_combine.java.proto` | `RES_ITEM_COMBINE` | 1 | error |
| 140071 | `res_item_disjoint.java.proto` | `RES_ITEM_DISJOINT` | 2 | error, type |
| 140791 | `res_item_emblem_upgrade_quick.java.proto` | `RES_ITEM_EMBLEM_UPGRADE_QUICK` | 2 | error, target |
| 140741 | `res_item_production_info.java.proto` | `RES_ITEM_PRODUCTION_INFO` | 1 | error |
| 140751 | `res_item_production_register.java.proto` | `RES_ITEM_PRODUCTION_REGISTER` | 5 | error, slottype, slotindex, recipeindex, starttime |
| 140061 | `res_item_reinforce.java.proto` | `RES_ITEM_REINFORCE` | 5 | error, guid, upgrade, Boolean, customtype |
| 140801 | `res_item_usable_list.java.proto` | `RES_ITEM_USABLE_LIST` | 1 | error |
| 140171 | `res_item_use.java.proto` | `RES_ITEM_USE` | 5 | error, index, count, Boolean, guid |
| 101091 | `res_leave_from_town.java.proto` | `RES_LEAVE_FROM_TOWN` | 1 | error |
| 101151 | `res_load_blacklist.java.proto` | `RES_LOAD_BLACKLIST` | 1 | error |
| 103001 | `res_load_friends_info.java.proto` | `RES_LOAD_FRIENDS_INFO` | 1 | error |
| ??? | `res_load_guild_donation_info_2.java.proto` | `RES_LOAD_GUILD_DONATION_INFO_2` | 3 | error, requestcount, responsecount |
| 100311 | `res_load_server_simple_data.java.proto` | `RES_LOAD_SERVER_SIMPLE_DATA` | 4 | error, type, enumvalue, value |
| 106501 | `res_load_share_reward.java.proto` | `RES_LOAD_SHARE_REWARD` | 1 | error |
| 104091 | `res_load_wedding_invitation_list.java.proto` | `RES_LOAD_WEDDING_INVITATION_LIST` | 1 | error |
| 260031 | `res_loading_progress.java.proto` | `RES_LOADING_PROGRESS` | 4 | error, matchingguid, charguid, value |
| 110461 | `res_local_reward_history.java.proto` | `RES_LOCAL_REWARD_HISTORY` | 3 | error, page, totalpage |
| 100001 | `res_login.java.proto` | `RES_LOGIN` | 5 | error, authkey, accountkey, Boolean, servertime |
| 110141 | `res_looting.java.proto` | `RES_LOOTING` | 3 | error, earnexp, earnitemcount |
| 150061 | `res_mail_all_delete.java.proto` | `RES_MAIL_ALL_DELETE` | 3 | error, type, removecount |
| 150041 | `res_mail_delete.java.proto` | `RES_MAIL_DELETE` | 3 | error, guid, type |
| 150021 | `res_mail_get.java.proto` | `RES_MAIL_GET` | 4 | error, guid, Boolean, type |
| 150011 | `res_mail_list.java.proto` | `RES_MAIL_LIST` | 3 | error, count, type |
| 150031 | `res_mail_read.java.proto` | `RES_MAIL_READ` | 3 | error, guid, type |
| 140021 | `res_material_list.java.proto` | `RES_MATERIAL_LIST` | 3 | error, page, maxcount |
| 281141 | `res_minigame_breaking_load.java.proto` | `RES_MINIGAME_BREAKING_LOAD` | 5 | error, ticketcount, playcount, rewardstate, myrank |
| 281101 | `res_minigame_chemical_load.java.proto` | `RES_MINIGAME_CHEMICAL_LOAD` | 5 | error, ticketcount, rewardpoint, rewardstate, myrank |
| 281021 | `res_minigame_lottery_list.java.proto` | `RES_MINIGAME_LOTTERY_LIST` | 5 | error, lotteryfreecount, lotterychargecount, time, trycount |
| 281321 | `res_minigame_mini_pong_load.java.proto` | `RES_MINIGAME_MINI_PONG_LOAD` | 5 | error, ticketcount, rewardpoint, rewardstate, myrank |
| 281401 | `res_minigame_restaurant_load.java.proto` | `RES_MINIGAME_RESTAURANT_LOAD` | 5 | error, state, ticketcount, playcount, playreward |
| 142001 | `res_money_item_list.java.proto` | `RES_MONEY_ITEM_LIST` | 1 | error |
| 157071 | `res_multi_play_dungeon_enter_complete.java.proto` | `RES_MULTI_PLAY_DUNGEON_ENTER_COMPLETE` | 5 | error, fatigue, consumefatigue, gold, tera |
| 130001 | `res_multi_play_request_match.java.proto` | `RES_MULTI_PLAY_REQUEST_MATCH` | 5 | error, dungeonindex, enemycharguid, aimatchingindex, Boolean |
| 130011 | `res_multi_play_request_match_cancel.java.proto` | `RES_MULTI_PLAY_REQUEST_MATCH_CANCEL` | 1 | error |
| 157001 | `res_multi_play_start_dungeon.java.proto` | `RES_MULTI_PLAY_START_DUNGEON` | 5 | error, dungeonguid, partyguid, partyleaderguid, fatigue |
| 130021 | `res_multi_play_sync_dungeon.java.proto` | `RES_MULTI_PLAY_SYNC_DUNGEON` | 5 | error, hp, matchingguid, dungeonguid, index |
| 115081 | `res_my_party_ranking.java.proto` | `RES_MY_PARTY_RANKING` | 2 | error, count |
| 115031 | `res_my_ranking.java.proto` | `RES_MY_RANKING` | 4 | error, rank, score, count |
| 300011 | `res_new_contents_list.java.proto` | `RES_NEW_CONTENTS_LIST` | 2 | error, type |
| 101531 | `res_not_transaction_character_state_info.java.proto` | `RES_NOT_TRANSACTION_CHARACTER_STATE_INFO` | 2 | error, status |
| 101121 | `res_note_messenger_add_user.java.proto` | `RES_NOTE_MESSENGER_ADD_USER` | 5 | error, charguid, name, job, growtype |
| 101101 | `res_note_messenger_load.java.proto` | `RES_NOTE_MESSENGER_LOAD` | 2 | error, usercount |
| 102141 | `res_npc_count.java.proto` | `RES_NPC_COUNT` | 1 | error |
| 140051 | `res_npc_shop_item_sell.java.proto` | `RES_NPC_SHOP_ITEM_SELL` | 1 | error |
| 135651 | `res_party_dungeon_condition.java.proto` | `RES_PARTY_DUNGEON_CONDITION` | 4 | error, type, index, grade |
| 130081 | `res_party_loading_status.java.proto` | `RES_PARTY_LOADING_STATUS` | 1 | error |
| 159501 | `res_party_member_battle_server_connect.java.proto` | `RES_PARTY_MEMBER_BATTLE_SERVER_CONNECT` | 3 | error, charguid, matchingguid |
| 100061 | `res_ping.java.proto` | `RES_PING` | 2 | error, responsetime |
| 130121 | `res_pvp_record_info.java.proto` | `RES_PVP_RECORD_INFO` | 2 | error, matchtype |
| 108041 | `res_query_access_time_by_event.java.proto` | `RES_QUERY_ACCESS_TIME_BY_EVENT` | 2 | accesstime, error |
| 102001 | `res_quest_info.java.proto` | `RES_QUEST_INFO` | 1 | error |
| 112331 | `res_raid_entrance_count.java.proto` | `RES_RAID_ENTRANCE_COUNT` | 1 | error |
| 140441 | `res_randomoption_change.java.proto` | `RES_RANDOMOPTION_CHANGE` | 3 | error, guid, type |
| 140451 | `res_randomoption_select.java.proto` | `RES_RANDOMOPTION_SELECT` | 3 | error, guid, type |
| 140701 | `res_randomoption_slot_lock.java.proto` | `RES_RANDOMOPTION_SLOT_LOCK` | 3 | error, guid, type |
| 140431 | `res_randomoption_unlock.java.proto` | `RES_RANDOMOPTION_UNLOCK` | 3 | error, guid, type |
| 115041 | `res_ranking_friend.java.proto` | `RES_RANKING_FRIEND` | 2 | error, type |
| 130061 | `res_ready_to_lockstep.java.proto` | `RES_READY_TO_LOCKSTEP` | 1 | error |
| 103011 | `res_received_invite_friend_list.java.proto` | `RES_RECEIVED_INVITE_FRIEND_LIST` | 2 | error, pageno |
| 130241 | `res_recommand_group.java.proto` | `RES_RECOMMAND_GROUP` | 3 | error, count, lastfriendseq |
| 270031 | `res_recommend_guild_list.java.proto` | `RES_RECOMMEND_GUILD_LIST` | 4 | error, lastkicktime, Boolean, freejoinMinlevel |
| 135571 | `res_refresh_target_character_town_info.java.proto` | `RES_REFRESH_TARGET_CHARACTER_TOWN_INFO` | 4 | error, charguid, partyguid, partyleaderguid |
| 100041 | `res_remove_character.java.proto` | `RES_REMOVE_CHARACTER` | 3 | error, charguid, pendingtime |
| 103051 | `res_reply_friend_invite.java.proto` | `RES_REPLY_FRIEND_INVITE` | 4 | error, world, fguid, type |
| 141151 | `res_repurchase_item.java.proto` | `RES_REPURCHASE_ITEM` | 1 | error |
| 141141 | `res_repurchase_item_storage_list.java.proto` | `RES_REPURCHASE_ITEM_STORAGE_LIST` | 1 | error |
| 103031 | `res_request_friend_invite.java.proto` | `RES_REQUEST_FRIEND_INVITE` | 5 | error, world, fguid, name, level |
| 130451 | `res_request_to_re_enter_accept_dungeon.java.proto` | `RES_REQUEST_TO_RE_ENTER_ACCEPT_DUNGEON` | 3 | error, subtype, guid |
| 130441 | `res_request_to_re_enter_dungeon.java.proto` | `RES_REQUEST_TO_RE_ENTER_DUNGEON` | 5 | error, type, difficulty, subtype, reason |
| 130281 | `res_return_to_town_at_multi_play.java.proto` | `RES_RETURN_TO_TOWN_AT_MULTI_PLAY` | 1 | error |
| 102021 | `res_reward_quest.java.proto` | `RES_REWARD_QUEST` | 5 | error, exp, level, adventureunionlevel, adventureunionexp |
| 100321 | `res_save_server_simple_data.java.proto` | `RES_SAVE_SERVER_SIMPLE_DATA` | 1 | error |
| 140131 | `res_scroll_list.java.proto` | `RES_SCROLL_LIST` | 3 | error, page, maxcount |
| 141071 | `res_sd_avatar_list.java.proto` | `RES_SD_AVATAR_LIST` | 3 | error, page, maxcount |
| 135471 | `res_search_party_list.java.proto` | `RES_SEARCH_PARTY_LIST` | 5 | error, type, area, subtype, detail |
| 107801 | `res_season_pass_info.java.proto` | `RES_SEASON_PASS_INFO` | 1 | error |
| 110131 | `res_select_reward_card.java.proto` | `RES_SELECT_REWARD_CARD` | 4 | error, type, earngold, premiumindex |
| 141031 | `res_send_inven.java.proto` | `RES_SEND_INVEN` | 1 | error |
| 141021 | `res_send_storage.java.proto` | `RES_SEND_STORAGE` | 1 | error |
| 103021 | `res_sending_invite_friend_list.java.proto` | `RES_SENDING_INVITE_FRIEND_LIST` | 2 | error, pageno |
| 100221 | `res_server_noti_ack.java.proto` | `RES_SERVER_NOTI_ACK` | 1 | error |
| 100231 | `res_server_response_packet.java.proto` | `RES_SERVER_RESPONSE_PACKET` | 1 | error |
| 120021 | `res_skill_list.java.proto` | `RES_SKILL_LIST` | 5 | error, type, sp, addsp, tp |
| 120011 | `res_skill_set.java.proto` | `RES_SKILL_SET` | 3 | error, type, index |
| 120031 | `res_skill_slot.java.proto` | `RES_SKILL_SLOT` | 3 | error, index, type |
| 110231 | `res_stage_clear.java.proto` | `RES_STAGE_CLEAR` | 5 | error, fatigue, gage, wagonhp, exp |
| 110241 | `res_stage_info.java.proto` | `RES_STAGE_INFO` | 2 | error, mapsize |
| 100011 | `res_standby.java.proto` | `RES_STANDBY` | 4 | error, standby, vip, reconnect |
| 110111 | `res_start_dungeon.java.proto` | `RES_START_DUNGEON` | 5 | error, createtime, index, dungeonguid, score |
| 110221 | `res_start_dungeon_complete.java.proto` | `RES_START_DUNGEON_COMPLETE` | 4 | error, fatigue, consumefatigue, Boolean |
| 100051 | `res_start_game.java.proto` | `RES_START_GAME` | 5 | error, growchangecount, world, currentworld, town |
| 130271 | `res_start_multi_play.java.proto` | `RES_START_MULTI_PLAY` | 5 | error, matchingguid, dungeonguid, battleworld, bchannel |
| 141041 | `res_storage_extend.java.proto` | `RES_STORAGE_EXTEND` | 2 | error, line |
| 141051 | `res_storage_step.java.proto` | `RES_STORAGE_STEP` | 2 | error, line |
| 157241 | `res_suggest_move_party.java.proto` | `RES_SUGGEST_MOVE_PARTY` | 1 | error |
| 110301 | `res_sync_dungeon_start_time.java.proto` | `RES_SYNC_DUNGEON_START_TIME` | 2 | error, starttime |
| 153061 | `res_system_buff_appendage.java.proto` | `RES_SYSTEM_BUFF_APPENDAGE` | 5 | error, time, endtime, type, index |
| 153051 | `res_system_buff_appendage_list.java.proto` | `RES_SYSTEM_BUFF_APPENDAGE_LIST` | 2 | error, time |
| 101071 | `res_target_user_detail_info.java.proto` | `RES_TARGET_USER_DETAIL_INFO` | 5 | error, guid, world, gguid, gmastername |
| 135481 | `res_target_user_party_info.java.proto` | `RES_TARGET_USER_PARTY_INFO` | 5 | error, ip, port, publictype, area |
| 101181 | `res_tencent_creditscore_info.java.proto` | `RES_TENCENT_CREDITSCORE_INFO` | 5 | error, level, score, tagblack, tagugc |
| 140491 | `res_title_list.java.proto` | `RES_TITLE_LIST` | 3 | error, page, maxcount |
| 101601 | `res_tlog_controller_data.java.proto` | `RES_TLOG_CONTROLLER_DATA` | 0 | N/A |
| 110391 | `res_tonic_info.java.proto` | `RES_TONIC_INFO` | 1 | error |
| 110401 | `res_tonic_init.java.proto` | `RES_TONIC_INIT` | 2 | error, index |
| 110411 | `res_tonic_upgrade.java.proto` | `RES_TONIC_UPGRADE` | 2 | error, index |
| 110801 | `res_tower_info.java.proto` | `RES_TOWER_INFO` | 5 | error, clearfloor, type, sweepcount, agp |
| 101051 | `res_town_chat.java.proto` | `RES_TOWN_CHAT` | 5 | error, type, subtype, charguid, targetguid |
| 101251 | `res_town_chat_list.java.proto` | `RES_TOWN_CHAT_LIST` | 2 | error, interval |
| 101061 | `res_town_user_guid_list.java.proto` | `RES_TOWN_USER_GUID_LIST` | 1 | error |
| 140461 | `res_transmission_item.java.proto` | `RES_TRANSMISSION_ITEM` | 2 | error, type |
| 102501 | `res_tutorial_list.java.proto` | `RES_TUTORIAL_LIST` | 1 | error |
| 102511 | `res_tutorial_save.java.proto` | `RES_TUTORIAL_SAVE` | 1 | error |
| 110181 | `res_use_coin.java.proto` | `RES_USE_COIN` | 5 | error, usetype, coin, tera, guid |
| 130091 | `res_vote_kick_out_user.java.proto` | `RES_VOTE_KICK_OUT_USER` | 1 | error |
| 130071 | `res_waiting_to_users_loading.java.proto` | `RES_WAITING_TO_USERS_LOADING` | 1 | error |
| 140811 | `res_wardrobe_info.java.proto` | `RES_WARDROBE_INFO` | 1 | error |
| 143021 | `res_wardrobe_set_slot.java.proto` | `RES_WARDROBE_SET_SLOT` | 2 | error, slot |

[← 上一页](#protobuf-协议汇总表-p2) | [末页 →](#结尾)

---

# 协议汇总表结尾