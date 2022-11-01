/*   1:    */ package cappo.protocol.messages;
/*   2:    */ 
/*   3:    */ import cappo.protocol.messages.events.HorseMountUpdateParser;
/*   4:    */ import cappo.protocol.messages.events.PutHorseSaddleParser;
/*   5:    */ import cappo.protocol.messages.events.RemoveHorseSaddleParser;
/*   6:    */ import cappo.protocol.messages.events.RidingPermissionParser;
/*   7:    */ import cappo.protocol.messages.events.advertisement.GetInterstitialParser;
/*   8:    */ import cappo.protocol.messages.events.avatar.ChangeUserNameParser;
/*   9:    */ import cappo.protocol.messages.events.avatar.CheckUserNameParser;
/*  10:    */ import cappo.protocol.messages.events.avatar.GetWardrobeParser;
/*  11:    */ import cappo.protocol.messages.events.avatar.SaveWardrobeOutfitParser;
/*  12:    */ import cappo.protocol.messages.events.catalog.GetBundleDynamicDiscountsParser;
/*  13:    */ import cappo.protocol.messages.events.catalog.GetCatalogIndexParser;
/*  14:    */ import cappo.protocol.messages.events.catalog.GetCatalogPageParser;
/*  15:    */ import cappo.protocol.messages.events.catalog.GetClubOffersParser;
/*  16:    */ import cappo.protocol.messages.events.catalog.GetGiftWrappingConfigurationParser;
/*  17:    */ import cappo.protocol.messages.events.catalog.GetHabboClubExtendOfferParser;
/*  18:    */ import cappo.protocol.messages.events.catalog.GetIsOfferGiftableParser;
/*  19:    */ import cappo.protocol.messages.events.catalog.GetSellablePetBreedsParser;
/*  20:    */ import cappo.protocol.messages.events.catalog.GetSnowWarTokensParser;
/*  21:    */ import cappo.protocol.messages.events.catalog.GetUniqueLimitedItemParser;
/*  22:    */ import cappo.protocol.messages.events.catalog.PurchaseFromCatalogAsGiftParser;
/*  23:    */ import cappo.protocol.messages.events.catalog.PurchaseFromCatalogParser;
/*  24:    */ import cappo.protocol.messages.events.friendlist.AcceptFriendParser;
/*  25:    */ import cappo.protocol.messages.events.friendlist.DeclineFriendParser;
/*  26:    */ import cappo.protocol.messages.events.friendlist.FollowFriendParser;
/*  27:    */ import cappo.protocol.messages.events.friendlist.FriendListUpdateParser;
/*  28:    */ import cappo.protocol.messages.events.friendlist.GetBuddyRequestsParser;
/*  29:    */ import cappo.protocol.messages.events.friendlist.HabboSearchParser;
/*  30:    */ import cappo.protocol.messages.events.friendlist.MessengerInitParser;
/*  31:    */ import cappo.protocol.messages.events.friendlist.RemoveFriendParser;
/*  32:    */ import cappo.protocol.messages.events.friendlist.RequestBuddyParser;
/*  33:    */ import cappo.protocol.messages.events.friendlist.SendMsgParser;
/*  34:    */ import cappo.protocol.messages.events.friendlist.SendRoomInviteParser;
/*  35:    */ import cappo.protocol.messages.events.friendlist.SetRelationshipStatusParser;
/*  36:    */ import cappo.protocol.messages.events.games.gamecenter.GetGameAchievementsParser;
/*  37:    */ import cappo.protocol.messages.events.games.gamecenter.GetGameListParser;
/*  38:    */ import cappo.protocol.messages.events.games.gamecenter.GetStatusGameParser;
/*  39:    */ import cappo.protocol.messages.events.games.gamecenter.JoinPlayerQueueParser;
/*  40:    */ import cappo.protocol.messages.events.games.snowwar.CheckGameDirectoryStatusParser;
/*  41:    */ import cappo.protocol.messages.events.games.snowwar.ExitGameParser;
/*  42:    */ import cappo.protocol.messages.events.games.snowwar.GameChatParser;
/*  43:    */ import cappo.protocol.messages.events.games.snowwar.GetAccountGameStatusParser;
/*  44:    */ import cappo.protocol.messages.events.games.snowwar.LeaveGameParser;
/*  45:    */ import cappo.protocol.messages.events.games.snowwar.LoadStageReadyParser;
/*  46:    */ import cappo.protocol.messages.events.games.snowwar.MakeSnowballParser;
/*  47:    */ import cappo.protocol.messages.events.games.snowwar.PlayAgainParser;
/*  48:    */ import cappo.protocol.messages.events.games.snowwar.QuickJoinGameParser;
/*  49:    */ import cappo.protocol.messages.events.games.snowwar.RequestFullStatusUpdateParser;
/*  50:    */ import cappo.protocol.messages.events.games.snowwar.SetUserMoveTargetParser;
/*  51:    */ import cappo.protocol.messages.events.games.snowwar.ThrowSnowballAtHumanParser;
/*  52:    */ import cappo.protocol.messages.events.games.snowwar.ThrowSnowballAtPositionParser;
/*  53:    */ import cappo.protocol.messages.events.guides.SetDutyGuideToolParser;
/*  54:    */ import cappo.protocol.messages.events.handshake.DisconnectParser;
/*  55:    */ import cappo.protocol.messages.events.handshake.GenerateSecretKeyParser;
/*  56:    */ import cappo.protocol.messages.events.handshake.InfoRetrieveParser;
/*  57:    */ import cappo.protocol.messages.events.handshake.InitCryptoParser;
/*  58:    */ import cappo.protocol.messages.events.handshake.PongParser;
/*  59:    */ import cappo.protocol.messages.events.handshake.SSOTicketParser;
/*  60:    */ import cappo.protocol.messages.events.handshake.UniqueIDParser;
/*  61:    */ import cappo.protocol.messages.events.handshake.VersionCheckParser;
/*  62:    */ import cappo.protocol.messages.events.help.CallForHelp2Parser;
/*  63:    */ import cappo.protocol.messages.events.help.CallForHelpInRoomParser;
/*  64:    */ import cappo.protocol.messages.events.help.CallForHelpOpenParser;
/*  65:    */ import cappo.protocol.messages.events.help.CallForHelpParser;
/*  66:    */ import cappo.protocol.messages.events.help.CallForHelpRoomPanicParser;
/*  67:    */ import cappo.protocol.messages.events.help.CallForHelpRoomParser;
/*  68:    */ import cappo.protocol.messages.events.inventory.achievements.GetAchievementsParser;
/*  69:    */ import cappo.protocol.messages.events.inventory.avatareffect.AvatarEffectActivatedParser;
/*  70:    */ import cappo.protocol.messages.events.inventory.avatareffect.AvatarEffectSelectedParser;
/*  71:    */ import cappo.protocol.messages.events.inventory.badges.GetBadgePointLimitsParser;
/*  72:    */ import cappo.protocol.messages.events.inventory.badges.GetBadgesParser;
/*  73:    */ import cappo.protocol.messages.events.inventory.badges.SetActivatedBadgesParser;
/*  74:    */ import cappo.protocol.messages.events.inventory.bots.RequestBotInventoryParser;
/*  75:    */ import cappo.protocol.messages.events.inventory.furni.RequestFurniInventoryParser;
/*  76:    */ import cappo.protocol.messages.events.inventory.furni.RequestRoomPropertySetParser;
/*  77:    */ import cappo.protocol.messages.events.inventory.pets.RequestPetInventoryParser;
/*  78:    */ import cappo.protocol.messages.events.inventory.purse.GetCreditsInfoParser;
/*  79:    */ import cappo.protocol.messages.events.inventory.trading.AcceptTradingParser;
/*  80:    */ import cappo.protocol.messages.events.inventory.trading.AddItemToTradeParser;
/*  81:    */ import cappo.protocol.messages.events.inventory.trading.CloseTradingParser;
/*  82:    */ import cappo.protocol.messages.events.inventory.trading.ConfirmAcceptTradingParser;
/*  83:    */ import cappo.protocol.messages.events.inventory.trading.ConfirmDeclineTradingParser;
/*  84:    */ import cappo.protocol.messages.events.inventory.trading.OpenTradingParser;
/*  85:    */ import cappo.protocol.messages.events.inventory.trading.RemoveItemFromTradeParser;
/*  86:    */ import cappo.protocol.messages.events.inventory.trading.UnacceptTradingParser;
/*  87:    */ import cappo.protocol.messages.events.landing.GetLandingNewsParser;
/*  88:    */ import cappo.protocol.messages.events.landing.GetLandingView6Parser;
/*  89:    */ import cappo.protocol.messages.events.landing.GetNextLimitedAvailableParser;
/*  90:    */ import cappo.protocol.messages.events.landing.RefreshLandingViewParser;
/*  91:    */ import cappo.protocol.messages.events.marketplace.GetMarketplaceCanMakeOfferParser;
/*  92:    */ import cappo.protocol.messages.events.marketplace.GetMarketplaceConfigurationParser;
/*  93:    */ import cappo.protocol.messages.events.moderator.CloseIssuesParser;
/*  94:    */ import cappo.protocol.messages.events.moderator.GetModeratorRoomInfoParser;
/*  95:    */ import cappo.protocol.messages.events.moderator.GetModeratorUserInfoParser;
/*  96:    */ import cappo.protocol.messages.events.moderator.ModBanParser;
/*  97:    */ import cappo.protocol.messages.events.moderator.ModKickParser;
/*  98:    */ import cappo.protocol.messages.events.moderator.ModMessageParser;
/*  99:    */ import cappo.protocol.messages.events.moderator.ModMuteParser;
/* 100:    */ import cappo.protocol.messages.events.moderator.ModerateRoomParser;
/* 101:    */ import cappo.protocol.messages.events.moderator.ModeratorActionParser;
/* 102:    */ import cappo.protocol.messages.events.moderator.ModeratorRoomActionParser;
/* 103:    */ import cappo.protocol.messages.events.moderator.PickIssuesParser;
/* 104:    */ import cappo.protocol.messages.events.moderator.ReleaseIssuesParser;
/* 105:    */ import cappo.protocol.messages.events.navigator.AddFavouriteRoomParser;
/* 106:    */ import cappo.protocol.messages.events.navigator.CanCreateRoomParser;
/* 107:    */ import cappo.protocol.messages.events.navigator.CreateFlatParser;
/* 108:    */ import cappo.protocol.messages.events.navigator.DeleteFavouriteRoomParser;
/* 109:    */ import cappo.protocol.messages.events.navigator.EditEventParser;
/* 110:    */ import cappo.protocol.messages.events.navigator.GetGuestRoomParser;
/* 111:    */ import cappo.protocol.messages.events.navigator.GetOfficialRoomsParser;
/* 112:    */ import cappo.protocol.messages.events.navigator.GetPopularRoomTagsParser;
/* 113:    */ import cappo.protocol.messages.events.navigator.GetUserFlatCatsParser;
/* 114:    */ import cappo.protocol.messages.events.navigator.LatestEventsSearchParser;
/* 115:    */ import cappo.protocol.messages.events.navigator.MyFavouriteRoomsSearchParser;
/* 116:    */ import cappo.protocol.messages.events.navigator.MyFriendsRoomsSearchParser;
/* 117:    */ import cappo.protocol.messages.events.navigator.MyRoomHistorySearchParser;
/* 118:    */ import cappo.protocol.messages.events.navigator.MyRoomsSearchParser;
/* 119:    */ import cappo.protocol.messages.events.navigator.PopularRoomsSearchParser;
/* 120:    */ import cappo.protocol.messages.events.navigator.RateFlatParser;
/* 121:    */ import cappo.protocol.messages.events.navigator.RoomTagSearchParser;
/* 122:    */ import cappo.protocol.messages.events.navigator.RoomTextSearchParser;
/* 123:    */ import cappo.protocol.messages.events.navigator.RoomsWhereMyFriendsAreSearchParser;
/* 124:    */ import cappo.protocol.messages.events.navigator.RoomsWithHighestScoreSearchParser;
/* 125:    */ import cappo.protocol.messages.events.navigator.ToggleStaffPickParser;
/* 126:    */ import cappo.protocol.messages.events.navigator.UpdateNavigatorSettingsParser;
/* 127:    */ import cappo.protocol.messages.events.notifications.ResetUnseenItemsParser;
/* 128:    */ import cappo.protocol.messages.events.poll.PollAnswerParser;
/* 129:    */ import cappo.protocol.messages.events.poll.PollRejectParser;
/* 130:    */ import cappo.protocol.messages.events.poll.PollStartParser;
/* 131:    */ import cappo.protocol.messages.events.quest.FriendRequestQuestCompleteParser;
/* 132:    */ import cappo.protocol.messages.events.recycler.GetRecyclerPrizesParser;
/* 133:    */ import cappo.protocol.messages.events.register.UpdateFigureDataParser;
/* 134:    */ import cappo.protocol.messages.events.room.action.AssignRightsParser;
/* 135:    */ import cappo.protocol.messages.events.room.action.BanUserParser;
/* 136:    */ import cappo.protocol.messages.events.room.action.DropCarryObjectParser;
/* 137:    */ import cappo.protocol.messages.events.room.action.KickUserParser;
/* 138:    */ import cappo.protocol.messages.events.room.action.LetUserInParser;
/* 139:    */ import cappo.protocol.messages.events.room.action.MuteUserParser;
/* 140:    */ import cappo.protocol.messages.events.room.action.RemoveAllRightsParser;
/* 141:    */ import cappo.protocol.messages.events.room.action.RemoveBanParser;
/* 142:    */ import cappo.protocol.messages.events.room.action.RemoveRightsParser;
/* 143:    */ import cappo.protocol.messages.events.room.action.ShareCarryObjectParser;
/* 144:    */ import cappo.protocol.messages.events.room.avatar.ChangeMottoParser;
/* 145:    */ import cappo.protocol.messages.events.room.avatar.ChangePostureParser;
/* 146:    */ import cappo.protocol.messages.events.room.avatar.DanceParser;
/* 147:    */ import cappo.protocol.messages.events.room.avatar.LookToParser;
/* 148:    */ import cappo.protocol.messages.events.room.avatar.SetAvatarExpressionParser;
/* 149:    */ import cappo.protocol.messages.events.room.avatar.SignParser;
/* 150:    */ import cappo.protocol.messages.events.room.bots.RequestBotSkillParser;
/* 151:    */ import cappo.protocol.messages.events.room.bots.SetBotSkillParser;
/* 152:    */ import cappo.protocol.messages.events.room.chat.CancelTypingParser;
/* 153:    */ import cappo.protocol.messages.events.room.chat.ChatParser;
/* 154:    */ import cappo.protocol.messages.events.room.chat.ShoutParser;
/* 155:    */ import cappo.protocol.messages.events.room.chat.StartTypingParser;
/* 156:    */ import cappo.protocol.messages.events.room.chat.WhisperParser;
/* 157:    */ import cappo.protocol.messages.events.room.engine.GetPetCommandsParser;
/* 158:    */ import cappo.protocol.messages.events.room.engine.GetRoomCampaignAdsParser;
/* 159:    */ import cappo.protocol.messages.events.room.engine.GetRoomCompetitionParser;
/* 160:    */ import cappo.protocol.messages.events.room.engine.GetRoomEntryDataParser;
/* 161:    */ import cappo.protocol.messages.events.room.engine.MoveAvatarParser;
/* 162:    */ import cappo.protocol.messages.events.room.engine.MoveObjectParser;
/* 163:    */ import cappo.protocol.messages.events.room.engine.MoveWallItemParser;
/* 164:    */ import cappo.protocol.messages.events.room.engine.ObjectSaveStuffDataParser;
/* 165:    */ import cappo.protocol.messages.events.room.engine.PickupObjectParser;
/* 166:    */ import cappo.protocol.messages.events.room.engine.PlaceObjectParser;
/* 167:    */ import cappo.protocol.messages.events.room.engine.PlacePetParser;
/* 168:    */ import cappo.protocol.messages.events.room.engine.PlaceRentalBotParser;
/* 169:    */ import cappo.protocol.messages.events.room.engine.RemoveBotFromFlatParser;
/* 170:    */ import cappo.protocol.messages.events.room.engine.RemovePetFromFlatParser;
/* 171:    */ import cappo.protocol.messages.events.room.engine.SetClothingChangeDataParser;
/* 172:    */ import cappo.protocol.messages.events.room.engine.UpdateRoomMapParser;
/* 173:    */ import cappo.protocol.messages.events.room.engine.UseFurnitureParser;
/* 174:    */ import cappo.protocol.messages.events.room.engine.UseWallItemParser;
/* 175:    */ import cappo.protocol.messages.events.room.furniture.AddSpamWallPostIt2Parser;
/* 176:    */ import cappo.protocol.messages.events.room.furniture.CreditFurniRedeemParser;
/* 177:    */ import cappo.protocol.messages.events.room.furniture.DiceOffParser;
/* 178:    */ import cappo.protocol.messages.events.room.furniture.OpenPostItParser;
/* 179:    */ import cappo.protocol.messages.events.room.furniture.PlacePostItParser;
/* 180:    */ import cappo.protocol.messages.events.room.furniture.PresentOpenParser;
/* 181:    */ import cappo.protocol.messages.events.room.furniture.RoomDimmerChangeStateParser;
/* 182:    */ import cappo.protocol.messages.events.room.furniture.RoomDimmerGetPresetsParser;
/* 183:    */ import cappo.protocol.messages.events.room.furniture.RoomDimmerSavePresetParser;
/* 184:    */ import cappo.protocol.messages.events.room.furniture.SetOutfitNameParser;
/* 185:    */ import cappo.protocol.messages.events.room.furniture.ThrowDiceParser;
/* 186:    */ import cappo.protocol.messages.events.room.furniture.UpdateOutfitParser;
/* 187:    */ import cappo.protocol.messages.events.room.pets.GetPetInfoParser;
/* 188:    */ import cappo.protocol.messages.events.room.pets.RespectPetParser;
/* 189:    */ import cappo.protocol.messages.events.room.session.ChangeQueueParser;
/* 190:    */ import cappo.protocol.messages.events.room.session.GoToFlatParser;
/* 191:    */ import cappo.protocol.messages.events.room.session.OpenFlatConnectionParser;
/* 192:    */ import cappo.protocol.messages.events.room.session.QuitParser;
/* 193:    */ import cappo.protocol.messages.events.roomsettings.DeleteRoomParser;
/* 194:    */ import cappo.protocol.messages.events.roomsettings.GetBannedUsersParser;
/* 195:    */ import cappo.protocol.messages.events.roomsettings.GetFlatControllersParser;
/* 196:    */ import cappo.protocol.messages.events.roomsettings.GetRoomSettingsParser;
/* 197:    */ import cappo.protocol.messages.events.roomsettings.SaveRoomSettingsMessageEvent;
/* 198:    */ import cappo.protocol.messages.events.roomsettings.SetRoomMuteStateParser;
/* 199:    */ import cappo.protocol.messages.events.sound.AddJukeboxDiskParser;
/* 200:    */ import cappo.protocol.messages.events.sound.GetJukeboxPlayListParser;
/* 201:    */ import cappo.protocol.messages.events.sound.GetNowPlayingParser;
/* 202:    */ import cappo.protocol.messages.events.sound.GetSongInfoParser;
/* 203:    */ import cappo.protocol.messages.events.sound.GetUserSongDisksParser;
/* 204:    */ import cappo.protocol.messages.events.sound.RemoveJukeboxDiskParser;
/* 205:    */ import cappo.protocol.messages.events.sound.SetSoundSettingsParser;
/* 206:    */ import cappo.protocol.messages.events.talents.GetTalentTrackParser;
/* 207:    */ import cappo.protocol.messages.events.tracking.EventLogParser;
/* 208:    */ import cappo.protocol.messages.events.tracking.LatencyPingReportParser;
/* 209:    */ import cappo.protocol.messages.events.tracking.LatencyPingRequestParser;
/* 210:    */ import cappo.protocol.messages.events.tracking.PerformanceLogParser;
/* 211:    */ import cappo.protocol.messages.events.userdefinedroomevents.OpenParser;
/* 212:    */ import cappo.protocol.messages.events.userdefinedroomevents.UpdateActionParser;
/* 213:    */ import cappo.protocol.messages.events.userdefinedroomevents.UpdateConditionParser;
/* 214:    */ import cappo.protocol.messages.events.userdefinedroomevents.UpdateTriggerParser;
/* 215:    */ import cappo.protocol.messages.events.users.ApproveNameParser;
/* 216:    */ import cappo.protocol.messages.events.users.GetExtendedProfileParser;
/* 217:    */ import cappo.protocol.messages.events.users.GetHabboGroupBadgesParser;
/* 218:    */ import cappo.protocol.messages.events.users.GetIgnoredUsersParser;
/* 219:    */ import cappo.protocol.messages.events.users.GetRelationshipStatusParser;
/* 220:    */ import cappo.protocol.messages.events.users.GetSelectedBadgesParser;
/* 221:    */ import cappo.protocol.messages.events.users.GetUserNotificationsParser;
/* 222:    */ import cappo.protocol.messages.events.users.GetUserSettingsParser;
/* 223:    */ import cappo.protocol.messages.events.users.GetUserTagsParser;
/* 224:    */ import cappo.protocol.messages.events.users.RespectUserParser;
/* 225:    */ import cappo.protocol.messages.events.users.ScrGetUserInfoParser;
/* 226:    */ import cappo.protocol.messages.events.users.SetUserChatSettingMessageEvent;
/* 227:    */ 
/* 228:    */ public class OpCodesOld
/* 229:    */ {
/* 230:    */   public static byte Init;
/* 231:    */   
/* 232:    */   public static void registerComposers()
/* 233:    */     throws Exception
/* 234:    */   {
/* 235: 19 */     OpCodesManager.setComposerId("landing.UpdateLandingComposer", 134);
/* 236: 20 */     OpCodesManager.setComposerId("landing.LandingNewsComposer", 2031);
/* 237: 21 */     OpCodesManager.setComposerId("landing.BadgeButtonStatusComposer", 1629);
/* 238: 22 */     OpCodesManager.setComposerId("landing.LandingView6Composer", 196);
/* 239: 23 */     OpCodesManager.setComposerId("landing.RewardResultComposer", 606);
/* 240: 24 */     OpCodesManager.setComposerId("landing.PersonalMessagesComposer", 3412);
/* 241:    */     
/* 242: 26 */     OpCodesManager.setComposerId("advertisement.InterstitialComposer", 2549);
/* 243:    */     
/* 244:    */ 
/* 245: 29 */     OpCodesManager.setComposerId("availability.Pending2029Composer", 789);
/* 246: 30 */     OpCodesManager.setComposerId("availability.Pending548Composer", 1753);
/* 247: 31 */     OpCodesManager.setComposerId("availability.AvailabilityStatusComposer", 552);
/* 248: 32 */     OpCodesManager.setComposerId("availability.Pending2850Composer", 1753);
/* 249:    */     
/* 250: 34 */     OpCodesManager.setComposerId("avatar.WardrobeComposer", 3812);
/* 251: 35 */     OpCodesManager.setComposerId("avatar.ResultChangeUserNameComposer", 454);
/* 252: 36 */     OpCodesManager.setComposerId("avatar.ResultCheckUserNameComposer", 965);
/* 253:    */     
/* 254: 38 */     OpCodesManager.setComposerId("catalog.BundleDynamicDiscountsComposer", 3397);
/* 255: 39 */     OpCodesManager.setComposerId("catalog.CatalogIndexComposer", 2485);
/* 256: 40 */     OpCodesManager.setComposerId("catalog.CatalogPageComposer", 3312);
/* 257: 41 */     OpCodesManager.setComposerId("catalog.ErrorPurchaseFromCatalogComposer", 381);
/* 258: 42 */     OpCodesManager.setComposerId("catalog.GiftWrappingConfigurationComposer", 695);
/* 259: 43 */     OpCodesManager.setComposerId("catalog.HabboClubExtendOfferComposer", 2938);
/* 260: 44 */     OpCodesManager.setComposerId("catalog.HabboClubOffersComposer", 3407);
/* 261: 45 */     OpCodesManager.setComposerId("catalog.SellablePetBreedsComposer", 3708);
/* 262: 46 */     OpCodesManager.setComposerId("catalog.SnowWarTokensComposer", 545);
/* 263: 47 */     OpCodesManager.setComposerId("catalog.UniqueLimitedItemComposer", 2002);
/* 264: 48 */     OpCodesManager.setComposerId("catalog.UniqueLimitedItemSoldOutComposer", 3996);
/* 265: 49 */     OpCodesManager.setComposerId("catalog.ErrorBuyComposer", 2626);
/* 266:    */     
/* 267: 51 */     OpCodesManager.setComposerId("error.ErrorComposer", 1092);
/* 268:    */     
/* 269: 53 */     OpCodesManager.setComposerId("facebook.Pending1298Composer", 1986);
/* 270: 54 */     OpCodesManager.setComposerId("facebook.Pending2310Composer", 3559);
/* 271: 55 */     OpCodesManager.setComposerId("facebook.Pending3136Composer", 1071);
/* 272:    */     
/* 273: 57 */     OpCodesManager.setComposerId("friendlist.BuddyMessageComposer", 2041);
/* 274: 58 */     OpCodesManager.setComposerId("friendlist.BuddyRequestsComposer", 2217);
/* 275: 59 */     OpCodesManager.setComposerId("friendlist.FollowFriendFailedComposer", 3864);
/* 276: 60 */     OpCodesManager.setComposerId("friendlist.InstantMessageErrorComposer", 224);
/* 277: 61 */     OpCodesManager.setComposerId("friendlist.MessengerErrorComposer", 1796);
/* 278: 62 */     OpCodesManager.setComposerId("friendlist.HabboSearchResultsComposer", 2270);
/* 279: 63 */     OpCodesManager.setComposerId("friendlist.MessengerInitComposer", 87);
/* 280: 64 */     OpCodesManager.setComposerId("friendlist.NewBuddyRequestComposer", 1568);
/* 281: 65 */     OpCodesManager.setComposerId("friendlist.FriendsUpdatesComposer", 3359);
/* 282: 66 */     OpCodesManager.setComposerId("friendlist.RoomInviteComposer", 1833);
/* 283: 67 */     OpCodesManager.setComposerId("friendlist.RoomInviteErrorComposer", 1410);
/* 284:    */     
/* 285: 69 */     OpCodesManager.setComposerId("guides.UpdateGuideToolComposer", 3731);
/* 286:    */     
/* 287: 71 */     OpCodesManager.setComposerId("games.gamecenter.JoinedPlayerQueueComposer", 3098);
/* 288: 72 */     OpCodesManager.setComposerId("games.gamecenter.GameListComposer", 1753);
/* 289: 73 */     OpCodesManager.setComposerId("games.gamecenter.StatusGameComposer", 1509);
/* 290: 74 */     OpCodesManager.setComposerId("games.gamecenter.LoadGameComposer", 1814);
/* 291: 75 */     OpCodesManager.setComposerId("games.gamecenter.GameAchievementsComposer", 277);
/* 292:    */     
/* 293: 77 */     OpCodesManager.setComposerId("games.snowwar.ArenaEnteredComposer", 2318);
/* 294: 78 */     OpCodesManager.setComposerId("games.snowwar.EnterArenaComposer", 3246);
/* 295: 79 */     OpCodesManager.setComposerId("games.snowwar.EnterArenaFailedComposer", 3476);
/* 296: 80 */     OpCodesManager.setComposerId("games.snowwar.FriendsLeaderboardComposer", 3647);
/* 297: 81 */     OpCodesManager.setComposerId("games.snowwar.FullGameStatusComposer", 539);
/* 298: 82 */     OpCodesManager.setComposerId("games.snowwar.GameCancelledComposer", 237);
/* 299: 83 */     OpCodesManager.setComposerId("games.snowwar.GameChatFromPlayerComposer", 3750);
/* 300: 84 */     OpCodesManager.setComposerId("games.snowwar.GameCreatedComposer", 728);
/* 301: 85 */     OpCodesManager.setComposerId("games.snowwar.GameDirectoryStatusComposer", 475);
/* 302: 86 */     OpCodesManager.setComposerId("games.snowwar.GameEndingComposer", 1168);
/* 303: 87 */     OpCodesManager.setComposerId("games.snowwar.GameLongDataComposer", 1337);
/* 304: 88 */     OpCodesManager.setComposerId("games.snowwar.GameRejoinComposer", 3155);
/* 305: 89 */     OpCodesManager.setComposerId("games.snowwar.GameStatusComposer", 2033);
/* 306: 90 */     OpCodesManager.setComposerId("games.snowwar.InArenaQueueComposer", 310);
/* 307: 91 */     OpCodesManager.setComposerId("games.snowwar.JoiningGameFailedComposer", 539);
/* 308: 92 */     OpCodesManager.setComposerId("games.snowwar.PlayerExitedGameArenaComposer", 3982);
/* 309: 93 */     OpCodesManager.setComposerId("games.snowwar.PlayerRematchesComposer", 3961);
/* 310: 94 */     OpCodesManager.setComposerId("games.snowwar.StageEndingComposer", 457);
/* 311: 95 */     OpCodesManager.setComposerId("games.snowwar.StageLoadComposer", 3647);
/* 312: 96 */     OpCodesManager.setComposerId("games.snowwar.StageRunningComposer", 3829);
/* 313: 97 */     OpCodesManager.setComposerId("games.snowwar.StageStartingComposer", 718);
/* 314: 98 */     OpCodesManager.setComposerId("games.snowwar.StageStillLoadingComposer", 2292);
/* 315: 99 */     OpCodesManager.setComposerId("games.snowwar.StartCounterComposer", 3066);
/* 316:100 */     OpCodesManager.setComposerId("games.snowwar.StartingGameFailedComposer", 1849);
/* 317:101 */     OpCodesManager.setComposerId("games.snowwar.StopCounterComposer", 3066);
/* 318:102 */     OpCodesManager.setComposerId("games.snowwar.TotalLeaderboardComposer", 3905);
/* 319:103 */     OpCodesManager.setComposerId("games.snowwar.UserBlockedComposer", 2865);
/* 320:104 */     OpCodesManager.setComposerId("games.snowwar.UserJoinedGameComposer", 1916);
/* 321:105 */     OpCodesManager.setComposerId("games.snowwar.UserLeftGameComposer", 425);
/* 322:106 */     OpCodesManager.setComposerId("games.snowwar.AccountGameStatusComposer", 959);
/* 323:107 */     OpCodesManager.setComposerId("games.snowwar.GameStartedComposer", 2665);
/* 324:    */     
/* 325:109 */     OpCodesManager.setComposerId("games.snowwar.WeeklyLeaderboardComposer", 3155);
/* 326:    */     
/* 327:111 */     OpCodesManager.setComposerId("handshake.GenericErrorComposer", 1821);
/* 328:112 */     OpCodesManager.setComposerId("handshake.UserLevelsComposer", 3469);
/* 329:113 */     OpCodesManager.setComposerId("handshake.AuthOKComposer", 1838);
/* 330:114 */     OpCodesManager.setComposerId("handshake.BannerTokenComposer", 2668);
/* 331:115 */     OpCodesManager.setComposerId("handshake.PerkAllowancesComposer", 535);
/* 332:116 */     OpCodesManager.setComposerId("handshake.ConnectionPingComposer", 3919);
/* 333:117 */     OpCodesManager.setComposerId("handshake.ServerPublicKeyComposer", 2759);
/* 334:118 */     OpCodesManager.setComposerId("handshake.UserDisconnectComposer", 4000);
/* 335:119 */     OpCodesManager.setComposerId("handshake.UserInfoComposer", 3674);
/* 336:    */     
/* 337:121 */     OpCodesManager.setComposerId("help.CallForHelpMutedComposer", 3073);
/* 338:122 */     OpCodesManager.setComposerId("help.CallForHelpOpenComposer", 3827);
/* 339:123 */     OpCodesManager.setComposerId("help.CallForHelpPendingCallsComposer", 1058);
/* 340:124 */     OpCodesManager.setComposerId("help.CallForHelpReplyComposer", 1098);
/* 341:125 */     OpCodesManager.setComposerId("help.CallForHelpResultComposer", 625);
/* 342:126 */     OpCodesManager.setComposerId("help.IssueCloseNotificationComposer", 948);
/* 343:    */     
/* 344:128 */     OpCodesManager.setComposerId("inventory.achievements.AchievementsComposer", 3115);
/* 345:129 */     OpCodesManager.setComposerId("inventory.achievements.AchievementsScoreComposer", 3590);
/* 346:    */     
/* 347:131 */     OpCodesManager.setComposerId("inventory.avatareffect.EffectAddedComposer", 3515);
/* 348:132 */     OpCodesManager.setComposerId("inventory.avatareffect.EffectEnabledComposer", 1545);
/* 349:133 */     OpCodesManager.setComposerId("inventory.avatareffect.EffectStopedComposer", 1325);
/* 350:134 */     OpCodesManager.setComposerId("inventory.avatareffect.EffectsComposer", 574);
/* 351:    */     
/* 352:136 */     OpCodesManager.setComposerId("inventory.badges.BadgesComposer", 2997);
/* 353:    */     
/* 354:138 */     OpCodesManager.setComposerId("inventory.furni.FurniListComposer", 1388);
/* 355:139 */     OpCodesManager.setComposerId("inventory.furni.FurniListAddOrUpdateComposer", 735);
/* 356:140 */     OpCodesManager.setComposerId("inventory.furni.FurniListRemoveComposer", 2299);
/* 357:141 */     OpCodesManager.setComposerId("inventory.furni.FurniListUpdateComposer", 1178);
/* 358:142 */     OpCodesManager.setComposerId("inventory.furni.PostItPlacedComposer", 1358);
/* 359:    */     
/* 360:144 */     OpCodesManager.setComposerId("inventory.pets.AddPetToInventoryComposer", 2268);
/* 361:145 */     OpCodesManager.setComposerId("inventory.pets.PetsInventoryComposer", 2065);
/* 362:146 */     OpCodesManager.setComposerId("inventory.pets.RemovePetInventoryComposer", 3220);
/* 363:    */     
/* 364:148 */     OpCodesManager.setComposerId("inventory.bots.BotsInventoryComposer", 456);
/* 365:149 */     OpCodesManager.setComposerId("inventory.bots.AddBotToInventoryComposer", 897);
/* 366:150 */     OpCodesManager.setComposerId("inventory.bots.RemoveBotInventoryComposer", 1444);
/* 367:    */     
/* 368:152 */     OpCodesManager.setComposerId("inventory.purse.CreditBalanceComposer", 545);
/* 369:    */     
/* 370:154 */     OpCodesManager.setComposerId("inventory.trading.TradingAcceptComposer", 1139);
/* 371:155 */     OpCodesManager.setComposerId("inventory.trading.TradingAlreadyOpenComposer", 847);
/* 372:156 */     OpCodesManager.setComposerId("inventory.trading.TradingCloseComposer", 1291);
/* 373:157 */     OpCodesManager.setComposerId("inventory.trading.TradingCompletedComposer", 1052);
/* 374:158 */     OpCodesManager.setComposerId("inventory.trading.TradingConfirmationComposer", 496);
/* 375:159 */     OpCodesManager.setComposerId("inventory.trading.TradingItemListComposer", 1025);
/* 376:160 */     OpCodesManager.setComposerId("inventory.trading.TradingOpenComposer", 2081);
/* 377:    */     
/* 378:162 */     OpCodesManager.setComposerId("landing.NextLimitedAvailableComposer", 3928);
/* 379:    */     
/* 380:    */ 
/* 381:165 */     OpCodesManager.setComposerId("marketplace.MarketplaceConfigComposer", 3554);
/* 382:    */     
/* 383:167 */     OpCodesManager.setComposerId("moderation.IssueInfoComposer", 2703);
/* 384:168 */     OpCodesManager.setComposerId("moderation.IssuePickFailedComposer", 2261);
/* 385:169 */     OpCodesManager.setComposerId("moderation.ModMessageComposer", 1992);
/* 386:170 */     OpCodesManager.setComposerId("moderation.ModeratorInitComposer", 1078);
/* 387:171 */     OpCodesManager.setComposerId("moderation.ModeratorRoomInfoComposer", 1204);
/* 388:172 */     OpCodesManager.setComposerId("moderation.ModeratorUserInfoComposer", 1896);
/* 389:    */     
/* 390:174 */     OpCodesManager.setComposerId("navigator.OfficialRoomsComposer", 458);
/* 391:175 */     OpCodesManager.setComposerId("navigator.FlatCreatedComposer", 951);
/* 392:176 */     OpCodesManager.setComposerId("navigator.DoorbellUserComposer", 1296);
/* 393:177 */     OpCodesManager.setComposerId("room.session.FlatAccessibleComposer", 2808);
/* 394:178 */     OpCodesManager.setComposerId("navigator.DoorBellNoAnswerComposer", 1777);
/* 395:179 */     OpCodesManager.setComposerId("navigator.GuestRoomResultComposer", 398);
/* 396:    */     
/* 397:181 */     OpCodesManager.setComposerId("navigator.CanCreateRoomComposer", 2912);
/* 398:182 */     OpCodesManager.setComposerId("navigator.EventComposer", 1261);
/* 399:183 */     OpCodesManager.setComposerId("navigator.FavouritesComposer", 5);
/* 400:184 */     OpCodesManager.setComposerId("navigator.FavouriteChangedComposer", 3216);
/* 401:185 */     OpCodesManager.setComposerId("navigator.FlatCategoriesComposer", 1007);
/* 402:186 */     OpCodesManager.setComposerId("navigator.NavigatorSettingsComposer", 104);
/* 403:187 */     OpCodesManager.setComposerId("navigator.GuestRoomSearchResultComposer", 766);
/* 404:188 */     OpCodesManager.setComposerId("navigator.PopularRoomTagsResultComposer", 2993);
/* 405:189 */     OpCodesManager.setComposerId("navigator.RoomRatingComposer", 2515);
/* 406:190 */     OpCodesManager.setComposerId("navigator.RoomUpdatedComposer", 1764);
/* 407:191 */     OpCodesManager.setComposerId("navigator.FlatAccessDeniedComposer", 905);
/* 408:192 */     OpCodesManager.setComposerId("navigator.RoomForwardComposer", 866);
/* 409:    */     
/* 410:194 */     OpCodesManager.setComposerId("notifications.PetReceivedMessageComposer", 2928);
/* 411:195 */     OpCodesManager.setComposerId("notifications.PetRespectFailedComposer", 793);
/* 412:196 */     OpCodesManager.setComposerId("notifications.BuyNotificationComposer", 1062);
/* 413:197 */     OpCodesManager.setComposerId("notifications.ActivityPointsComposer", 2206);
/* 414:198 */     OpCodesManager.setComposerId("notifications.BroadcastImageComposer", 405);
/* 415:199 */     OpCodesManager.setComposerId("notifications.HabboActivityPointNotificationComposer", 36);
/* 416:200 */     OpCodesManager.setComposerId("notifications.ClubGiftNotificationComposer", 84);
/* 417:201 */     OpCodesManager.setComposerId("notifications.HabboBroadcastComposer", 2037);
/* 418:202 */     OpCodesManager.setComposerId("notifications.HabboBroadcastCustomComposer", 918);
/* 419:203 */     OpCodesManager.setComposerId("notifications.InfoFeedEnableComposer", 476);
/* 420:204 */     OpCodesManager.setComposerId("notifications.MOTDComposer", 3331);
/* 421:205 */     OpCodesManager.setComposerId("notifications.PetLevelNotificationComposer", 1539);
/* 422:206 */     OpCodesManager.setComposerId("notifications.UnseenItemsComposer", 2541);
/* 423:    */     
/* 424:208 */     OpCodesManager.setComposerId("poll.PollContentsMessageComposer", 2019);
/* 425:209 */     OpCodesManager.setComposerId("poll.PollOfferMessageComposer", 669);
/* 426:210 */     OpCodesManager.setComposerId("poll.PollErrorMessageComposer", 859);
/* 427:    */     
/* 428:212 */     OpCodesManager.setComposerId("recycler.RecyclerOkComposer", 629);
/* 429:213 */     OpCodesManager.setComposerId("recycler.RecyclerPrizesComposer", 1564);
/* 430:214 */     OpCodesManager.setComposerId("recycler.RecyclerStatusComposer", 3397);
/* 431:    */     
/* 432:216 */     OpCodesManager.setComposerId("room.action.UserDanceComposer", 2063);
/* 433:217 */     OpCodesManager.setComposerId("room.action.AvatarExpressionComposer", 185);
/* 434:218 */     OpCodesManager.setComposerId("room.action.CarryObjectComposer", 2203);
/* 435:219 */     OpCodesManager.setComposerId("room.action.UserAsleepComposer", 2744);
/* 436:220 */     OpCodesManager.setComposerId("room.action.UserEffectComposer", 3190);
/* 437:    */     
/* 438:222 */     OpCodesManager.setComposerId("room.chat.ChatSettingsComposer", 2165);
/* 439:223 */     OpCodesManager.setComposerId("room.chat.ChatComposer", 755);
/* 440:224 */     OpCodesManager.setComposerId("room.chat.ShoutComposer", 1957);
/* 441:225 */     OpCodesManager.setComposerId("room.chat.WhisperComposer", 3153);
/* 442:226 */     OpCodesManager.setComposerId("room.chat.FloodControlComposer", 3030);
/* 443:227 */     OpCodesManager.setComposerId("room.chat.UserTypingComposer", 2799);
/* 444:    */     
/* 445:229 */     OpCodesManager.setComposerId("room.bots.BotSkillComposer", 2887);
/* 446:230 */     OpCodesManager.setComposerId("room.bots.BotErrorComposer", 1749);
/* 447:    */     
/* 448:232 */     OpCodesManager.setComposerId("room.engine.FloorHeightMapComposer", 1094);
/* 449:233 */     OpCodesManager.setComposerId("room.engine.HeightMapComposer", 3513);
/* 450:234 */     OpCodesManager.setComposerId("room.engine.HeightMapUpdateComposer", 2287);
/* 451:235 */     OpCodesManager.setComposerId("room.engine.ObjectsComposer", 2711);
/* 452:236 */     OpCodesManager.setComposerId("room.engine.ObjectAddComposer", 563);
/* 453:237 */     OpCodesManager.setComposerId("room.engine.ObjectRemoveComposer", 265);
/* 454:238 */     OpCodesManager.setComposerId("room.engine.ObjectUpdateComposer", 2061);
/* 455:239 */     OpCodesManager.setComposerId("room.engine.ObjectDataUpdateComposer", 3798);
/* 456:240 */     OpCodesManager.setComposerId("room.engine.ObjectsDataUpdateComposer", 2896);
/* 457:241 */     OpCodesManager.setComposerId("room.engine.PublicRoomObjectsMessageParser", 3978);
/* 458:242 */     OpCodesManager.setComposerId("room.engine.ItemsComposer", 3097);
/* 459:243 */     OpCodesManager.setComposerId("room.engine.ItemAddComposer", 1632);
/* 460:244 */     OpCodesManager.setComposerId("room.engine.ItemRemoveComposer", 2902);
/* 461:245 */     OpCodesManager.setComposerId("room.engine.ItemUpdateComposer", 401);
/* 462:246 */     OpCodesManager.setComposerId("room.engine.UsersComposer", 492);
/* 463:247 */     OpCodesManager.setComposerId("room.engine.UserUpdateComposer", 2790);
/* 464:248 */     OpCodesManager.setComposerId("room.engine.UserChangeComposer", 3888);
/* 465:249 */     OpCodesManager.setComposerId("room.engine.UserRemoveComposer", 1096);
/* 466:250 */     OpCodesManager.setComposerId("room.engine.RoomVisualizationSettingsComposer", 324);
/* 467:251 */     OpCodesManager.setComposerId("room.engine.RoomEntryInfoComposer", 3179);
/* 468:252 */     OpCodesManager.setComposerId("room.engine.RoomPropertyComposer", 2139);
/* 469:253 */     OpCodesManager.setComposerId("room.engine.RoomCampaignAdsComposer", 555);
/* 470:254 */     OpCodesManager.setComposerId("room.engine.SlideObjectBundleComposer", 3862);
/* 471:255 */     OpCodesManager.setComposerId("room.engine.PlaceObjectErrorComposer", 30);
/* 472:    */     
/* 473:257 */     OpCodesManager.setComposerId("room.furniture.RequestSpamWallPostItComposer", 3751);
/* 474:258 */     OpCodesManager.setComposerId("room.furniture.RoomDimmerPresetsComposer", 2770);
/* 475:    */     
/* 476:260 */     OpCodesManager.setComposerId("room.permissions.YouAreControllerComposer", 144);
/* 477:261 */     OpCodesManager.setComposerId("room.permissions.YouAreNotControllerComposer", 3382);
/* 478:262 */     OpCodesManager.setComposerId("room.permissions.YouAreOwnerComposer", 1694);
/* 479:    */     
/* 480:264 */     OpCodesManager.setComposerId("room.pets.PetCommandsComposer", 2076);
/* 481:265 */     OpCodesManager.setComposerId("room.pets.PetInfoComposer", 2834);
/* 482:266 */     OpCodesManager.setComposerId("room.pets.PetPlacingErrorComposer", 3343);
/* 483:    */     
/* 484:268 */     OpCodesManager.setComposerId("room.publicroom.ParkBusCannotEnterComposer", 2131);
/* 485:    */     
/* 486:270 */     OpCodesManager.setComposerId("room.session.YouArePlayingGameComposer", 2681);
/* 487:271 */     OpCodesManager.setComposerId("room.session.OpenConnectionComposer", 1803);
/* 488:272 */     OpCodesManager.setComposerId("room.session.RoomReadyComposer", 3026);
/* 489:273 */     OpCodesManager.setComposerId("room.session.CloseConnectionComposer", 3502);
/* 490:274 */     OpCodesManager.setComposerId("room.session.RoomQueueStatusComposer", 1728);
/* 491:275 */     OpCodesManager.setComposerId("room.session.YouAreSpectatorComposer", 3332);
/* 492:    */     
/* 493:277 */     OpCodesManager.setComposerId("roomsettings.RoomMuteStateComposer", 3166);
/* 494:278 */     OpCodesManager.setComposerId("roomsettings.BannedUsersComposer", 2432);
/* 495:279 */     OpCodesManager.setComposerId("roomsettings.RoomBanRemoved", 2394);
/* 496:280 */     OpCodesManager.setComposerId("roomsettings.FlatControllerAddedComposer", 1791);
/* 497:281 */     OpCodesManager.setComposerId("roomsettings.FlatControllerRemovedComposer", 2100);
/* 498:282 */     OpCodesManager.setComposerId("roomsettings.FlatControllersComposer", 11);
/* 499:283 */     OpCodesManager.setComposerId("roomsettings.RoomSettingsDataComposer", 2619);
/* 500:284 */     OpCodesManager.setComposerId("roomsettings.RoomSettingsSavedComposer", 682);
/* 501:285 */     OpCodesManager.setComposerId("roomsettings.RoomSettingsErrorComposer", 493);
/* 502:    */     
/* 503:287 */     OpCodesManager.setComposerId("users.UserSettingsComposer", 836);
/* 504:288 */     OpCodesManager.setComposerId("sound.JukeboxSongDisksComposer", 3896);
/* 505:289 */     OpCodesManager.setComposerId("sound.TraxSongInfoComposer", 3989);
/* 506:290 */     OpCodesManager.setComposerId("sound.UserSongDisksInventoryComposer", 3916);
/* 507:291 */     OpCodesManager.setComposerId("sound.NowPlayingComposer", 3520);
/* 508:292 */     OpCodesManager.setComposerId("sound.JukeboxPlayListFullComposer", 1495);
/* 509:    */     
/* 510:294 */     OpCodesManager.setComposerId("talents.TalentTrackComposer", 3251);
/* 511:    */     
/* 512:296 */     OpCodesManager.setComposerId("tracking.PingResponseComposer", 1199);
/* 513:    */     
/* 514:298 */     OpCodesManager.setComposerId("userdefinedroomevents.OpenWiredComposer", 2490);
/* 515:299 */     OpCodesManager.setComposerId("userdefinedroomevents.WiredUpdateFailedComposer", 362);
/* 516:300 */     OpCodesManager.setComposerId("userdefinedroomevents.WiredUpdatedComposer", 44);
/* 517:301 */     OpCodesManager.setComposerId("userdefinedroomevents.WiredRewardNotificationComposer", 606);
/* 518:    */     
/* 519:303 */     OpCodesManager.setComposerId("users.ApproveNameComposer", 785);
/* 520:304 */     OpCodesManager.setComposerId("users.UserTagsComposer", 1383);
/* 521:305 */     OpCodesManager.setComposerId("users.HabboGroupBadgesComposer", 560);
/* 522:306 */     OpCodesManager.setComposerId("users.IgnoredUsersComposer", 2982);
/* 523:307 */     OpCodesManager.setComposerId("users.NotifyUserNameChangeComposer", 55);
/* 524:308 */     OpCodesManager.setComposerId("users.PetRespectedComposer", 1327);
/* 525:309 */     OpCodesManager.setComposerId("users.RelationshipStatusComposer", 1759);
/* 526:310 */     OpCodesManager.setComposerId("users.UserBadgesComposer", 219);
/* 527:311 */     OpCodesManager.setComposerId("users.UserProfileInfoComposer", 744);
/* 528:312 */     OpCodesManager.setComposerId("users.ScrUserInfoComposer", 719);
/* 529:313 */     OpCodesManager.setComposerId("users.UserRespectedComposer", 921);
/* 530:    */   }
/* 531:    */   
/* 532:    */   static
/* 533:    */   {
/* 534:317 */     IncomingMessageEvent.callBacks[2242] = new GetLandingNewsParser();
/* 535:318 */     IncomingMessageEvent.callBacks[2479] = new RefreshLandingViewParser();
/* 536:    */     
/* 537:320 */     IncomingMessageEvent.callBacks[669] = new GetLandingView6Parser();
/* 538:    */     
/* 539:    */ 
/* 540:    */ 
/* 541:    */ 
/* 542:325 */     IncomingMessageEvent.callBacks[693] = new PutHorseSaddleParser();
/* 543:326 */     IncomingMessageEvent.callBacks[2544] = new HorseMountUpdateParser();
/* 544:327 */     IncomingMessageEvent.callBacks[2814] = new RemoveHorseSaddleParser();
/* 545:328 */     IncomingMessageEvent.callBacks[2898] = new RidingPermissionParser();
/* 546:    */     
/* 547:330 */     IncomingMessageEvent.callBacks[1007] = new GetInterstitialParser();
/* 548:    */     
/* 549:    */ 
/* 550:333 */     IncomingMessageEvent.callBacks[''] = new ChangeUserNameParser();
/* 551:334 */     IncomingMessageEvent.callBacks[2824] = new CheckUserNameParser();
/* 552:335 */     IncomingMessageEvent.callBacks[2472] = new GetWardrobeParser();
/* 553:336 */     IncomingMessageEvent.callBacks[2129] = new SaveWardrobeOutfitParser();
/* 554:    */     
/* 555:338 */     IncomingMessageEvent.callBacks[1150] = new GetGiftWrappingConfigurationParser();
/* 556:339 */     IncomingMessageEvent.callBacks[779] = new GetHabboClubExtendOfferParser();
/* 557:340 */     IncomingMessageEvent.callBacks[1919] = new GetSellablePetBreedsParser();
/* 558:    */     
/* 559:342 */     IncomingMessageEvent.callBacks[3370] = new GetIsOfferGiftableParser();
/* 560:343 */     IncomingMessageEvent.callBacks[1342] = new GetClubOffersParser();
/* 561:344 */     IncomingMessageEvent.callBacks[25] = new GetCatalogIndexParser();
/* 562:    */     
/* 563:346 */     IncomingMessageEvent.callBacks[698] = new PurchaseFromCatalogParser();
/* 564:347 */     IncomingMessageEvent.callBacks[3324] = new PurchaseFromCatalogAsGiftParser();
/* 565:348 */     IncomingMessageEvent.callBacks[600] = new GetCatalogPageParser();
/* 566:349 */     IncomingMessageEvent.callBacks[3470] = new GetSnowWarTokensParser();
/* 567:350 */     IncomingMessageEvent.callBacks[1418] = new GetBundleDynamicDiscountsParser();
/* 568:351 */     IncomingMessageEvent.callBacks[2503] = new GetUniqueLimitedItemParser();
/* 569:    */     
/* 570:353 */     IncomingMessageEvent.callBacks[3783] = new SetRelationshipStatusParser();
/* 571:354 */     IncomingMessageEvent.callBacks[3534] = new SendMsgParser();
/* 572:355 */     IncomingMessageEvent.callBacks[2228] = new SendRoomInviteParser();
/* 573:356 */     IncomingMessageEvent.callBacks[3688] = new AcceptFriendParser();
/* 574:357 */     IncomingMessageEvent.callBacks[3645] = new DeclineFriendParser();
/* 575:358 */     IncomingMessageEvent.callBacks[103] = new RequestBuddyParser();
/* 576:359 */     IncomingMessageEvent.callBacks[2456] = new RemoveFriendParser();
/* 577:360 */     IncomingMessageEvent.callBacks[3712] = new HabboSearchParser();
/* 578:361 */     IncomingMessageEvent.callBacks[3967] = new MessengerInitParser();
/* 579:362 */     IncomingMessageEvent.callBacks[615] = new FriendListUpdateParser();
/* 580:363 */     IncomingMessageEvent.callBacks[3116] = new GetBuddyRequestsParser();
/* 581:364 */     IncomingMessageEvent.callBacks[334] = new FollowFriendParser();
/* 582:    */     
/* 583:    */ 
/* 584:367 */     IncomingMessageEvent.callBacks[''] = new SetDutyGuideToolParser();
/* 585:    */     
/* 586:369 */     IncomingMessageEvent.callBacks[2967] = new JoinPlayerQueueParser();
/* 587:370 */     IncomingMessageEvent.callBacks[2607] = new GetStatusGameParser();
/* 588:371 */     IncomingMessageEvent.callBacks[2874] = new GetGameListParser();
/* 589:372 */     IncomingMessageEvent.callBacks[297] = new GetGameAchievementsParser();
/* 590:    */     
/* 591:374 */     IncomingMessageEvent.callBacks[2715] = new CheckGameDirectoryStatusParser();
/* 592:375 */     IncomingMessageEvent.callBacks[1633] = new GetAccountGameStatusParser();
/* 593:376 */     IncomingMessageEvent.callBacks[3607] = new ExitGameParser();
/* 594:377 */     IncomingMessageEvent.callBacks[1300] = new QuickJoinGameParser();
/* 595:378 */     IncomingMessageEvent.callBacks[3487] = new LeaveGameParser();
/* 596:379 */     IncomingMessageEvent.callBacks['ü'] = new GameChatParser();
/* 597:380 */     IncomingMessageEvent.callBacks[455] = new LoadStageReadyParser();
/* 598:381 */     IncomingMessageEvent.callBacks[2291] = new SetUserMoveTargetParser();
/* 599:382 */     IncomingMessageEvent.callBacks[1308] = new RequestFullStatusUpdateParser();
/* 600:383 */     IncomingMessageEvent.callBacks[3088] = new MakeSnowballParser();
/* 601:384 */     IncomingMessageEvent.callBacks[3108] = new PlayAgainParser();
/* 602:385 */     IncomingMessageEvent.callBacks[3715] = new ThrowSnowballAtHumanParser();
/* 603:386 */     IncomingMessageEvent.callBacks[2590] = new ThrowSnowballAtPositionParser();
/* 604:    */     
/* 605:    */ 
/* 606:    */ 
/* 607:    */ 
/* 608:    */ 
/* 609:    */ 
/* 610:    */ 
/* 611:    */ 
/* 612:    */ 
/* 613:396 */     IncomingMessageEvent.callBacks[2294] = new InfoRetrieveParser();
/* 614:397 */     IncomingMessageEvent.callBacks[667] = new PongParser();
/* 615:398 */     IncomingMessageEvent.callBacks[1712] = new InitCryptoParser();
/* 616:399 */     IncomingMessageEvent.callBacks[1777] = new SSOTicketParser();
/* 617:400 */     IncomingMessageEvent.callBacks[3410] = new DisconnectParser();
/* 618:401 */     IncomingMessageEvent.callBacks['à'] = new UniqueIDParser();
/* 619:402 */     IncomingMessageEvent.callBacks[2401] = new VersionCheckParser();
/* 620:403 */     IncomingMessageEvent.callBacks[2722] = new GenerateSecretKeyParser();
/* 621:    */     
/* 622:405 */     IncomingMessageEvent.callBacks[2838] = new CallForHelpOpenParser();
/* 623:406 */     IncomingMessageEvent.callBacks[3797] = new CallForHelp2Parser();
/* 624:407 */     IncomingMessageEvent.callBacks[778] = new CallForHelpParser();
/* 625:408 */     IncomingMessageEvent.callBacks[2050] = new CallForHelpInRoomParser();
/* 626:409 */     IncomingMessageEvent.callBacks[268] = new CallForHelpRoomPanicParser();
/* 627:410 */     IncomingMessageEvent.callBacks[1946] = new CallForHelpRoomParser();
/* 628:    */     
/* 629:412 */     IncomingMessageEvent.callBacks[3448] = new GetAchievementsParser();
/* 630:    */     
/* 631:414 */     IncomingMessageEvent.callBacks[1560] = new AvatarEffectSelectedParser();
/* 632:415 */     IncomingMessageEvent.callBacks[1770] = new AvatarEffectActivatedParser();
/* 633:    */     
/* 634:417 */     IncomingMessageEvent.callBacks[3887] = new GetBadgesParser();
/* 635:418 */     IncomingMessageEvent.callBacks['Ý'] = new SetActivatedBadgesParser();
/* 636:419 */     IncomingMessageEvent.callBacks[2612] = new GetBadgePointLimitsParser();
/* 637:    */     
/* 638:421 */     IncomingMessageEvent.callBacks[2122] = new RequestRoomPropertySetParser();
/* 639:422 */     IncomingMessageEvent.callBacks[3588] = new RequestFurniInventoryParser();
/* 640:    */     
/* 641:424 */     IncomingMessageEvent.callBacks[2316] = new RequestPetInventoryParser();
/* 642:    */     
/* 643:426 */     IncomingMessageEvent.callBacks[1715] = new RequestBotInventoryParser();
/* 644:    */     
/* 645:428 */     IncomingMessageEvent.callBacks[2425] = new GetCreditsInfoParser();
/* 646:    */     
/* 647:430 */     IncomingMessageEvent.callBacks[64] = new OpenTradingParser();
/* 648:431 */     IncomingMessageEvent.callBacks[986] = new AddItemToTradeParser();
/* 649:432 */     IncomingMessageEvent.callBacks[3548] = new RemoveItemFromTradeParser();
/* 650:433 */     IncomingMessageEvent.callBacks[36] = new ConfirmAcceptTradingParser();
/* 651:434 */     IncomingMessageEvent.callBacks[1989] = new ConfirmDeclineTradingParser();
/* 652:435 */     IncomingMessageEvent.callBacks[3864] = new AcceptTradingParser();
/* 653:436 */     IncomingMessageEvent.callBacks[3574] = new UnacceptTradingParser();
/* 654:437 */     IncomingMessageEvent.callBacks[2634] = new CloseTradingParser();
/* 655:    */     
/* 656:439 */     IncomingMessageEvent.callBacks[2973] = new GetNextLimitedAvailableParser();
/* 657:    */     
/* 658:441 */     IncomingMessageEvent.callBacks[2380] = new GetMarketplaceConfigurationParser();
/* 659:442 */     IncomingMessageEvent.callBacks[3262] = new GetMarketplaceCanMakeOfferParser();
/* 660:    */     
/* 661:444 */     IncomingMessageEvent.callBacks[2454] = new GetModeratorUserInfoParser();
/* 662:445 */     IncomingMessageEvent.callBacks[2067] = new GetModeratorRoomInfoParser();
/* 663:446 */     IncomingMessageEvent.callBacks[1623] = new ModeratorActionParser();
/* 664:447 */     IncomingMessageEvent.callBacks[1884] = new ModMessageParser();
/* 665:448 */     IncomingMessageEvent.callBacks[1958] = new ModKickParser();
/* 666:449 */     IncomingMessageEvent.callBacks[2412] = new ModMuteParser();
/* 667:450 */     IncomingMessageEvent.callBacks[2978] = new ModBanParser();
/* 668:451 */     IncomingMessageEvent.callBacks[527] = new ModerateRoomParser();
/* 669:452 */     IncomingMessageEvent.callBacks[1944] = new ModeratorRoomActionParser();
/* 670:453 */     IncomingMessageEvent.callBacks['¾'] = new PickIssuesParser();
/* 671:454 */     IncomingMessageEvent.callBacks[2540] = new ReleaseIssuesParser();
/* 672:455 */     IncomingMessageEvent.callBacks[3160] = new CloseIssuesParser();
/* 673:    */     
/* 674:457 */     IncomingMessageEvent.callBacks[3288] = new AddFavouriteRoomParser();
/* 675:458 */     IncomingMessageEvent.callBacks[2349] = new DeleteFavouriteRoomParser();
/* 676:459 */     IncomingMessageEvent.callBacks[2420] = new CreateFlatParser();
/* 677:460 */     IncomingMessageEvent.callBacks[3209] = new RateFlatParser();
/* 678:    */     
/* 679:    */ 
/* 680:    */ 
/* 681:464 */     IncomingMessageEvent.callBacks[2851] = new EditEventParser();
/* 682:465 */     IncomingMessageEvent.callBacks[2056] = new GetOfficialRoomsParser();
/* 683:466 */     IncomingMessageEvent.callBacks[1367] = new GetPopularRoomTagsParser();
/* 684:467 */     IncomingMessageEvent.callBacks[1762] = new UpdateNavigatorSettingsParser();
/* 685:468 */     IncomingMessageEvent.callBacks[3552] = new GetGuestRoomParser();
/* 686:469 */     IncomingMessageEvent.callBacks[1030] = new CanCreateRoomParser();
/* 687:470 */     IncomingMessageEvent.callBacks[1192] = new PopularRoomsSearchParser();
/* 688:471 */     IncomingMessageEvent.callBacks[604] = new RoomsWithHighestScoreSearchParser();
/* 689:472 */     IncomingMessageEvent.callBacks[2312] = new MyFriendsRoomsSearchParser();
/* 690:473 */     IncomingMessageEvent.callBacks[3094] = new RoomsWhereMyFriendsAreSearchParser();
/* 691:474 */     IncomingMessageEvent.callBacks[959] = new MyRoomsSearchParser();
/* 692:475 */     IncomingMessageEvent.callBacks[3437] = new MyFavouriteRoomsSearchParser();
/* 693:476 */     IncomingMessageEvent.callBacks[3122] = new MyRoomHistorySearchParser();
/* 694:477 */     IncomingMessageEvent.callBacks['ó'] = new RoomTextSearchParser();
/* 695:478 */     IncomingMessageEvent.callBacks[3094] = new RoomTagSearchParser();
/* 696:479 */     IncomingMessageEvent.callBacks[2091] = new LatestEventsSearchParser();
/* 697:480 */     IncomingMessageEvent.callBacks[329] = new GetUserFlatCatsParser();
/* 698:481 */     IncomingMessageEvent.callBacks[3889] = new ToggleStaffPickParser();
/* 699:    */     
/* 700:483 */     IncomingMessageEvent.callBacks[2439] = new ResetUnseenItemsParser();
/* 701:    */     
/* 702:485 */     IncomingMessageEvent.callBacks[723] = new PollStartParser();
/* 703:486 */     IncomingMessageEvent.callBacks[1036] = new PollRejectParser();
/* 704:487 */     IncomingMessageEvent.callBacks[1323] = new PollAnswerParser();
/* 705:    */     
/* 706:    */ 
/* 707:490 */     IncomingMessageEvent.callBacks[1405] = new FriendRequestQuestCompleteParser();
/* 708:    */     
/* 709:492 */     IncomingMessageEvent.callBacks[738] = new GetRecyclerPrizesParser();
/* 710:    */     
/* 711:    */ 
/* 712:    */ 
/* 713:496 */     IncomingMessageEvent.callBacks[1276] = new UpdateFigureDataParser();
/* 714:    */     
/* 715:498 */     IncomingMessageEvent.callBacks[876] = new KickUserParser();
/* 716:499 */     IncomingMessageEvent.callBacks[543] = new BanUserParser();
/* 717:500 */     IncomingMessageEvent.callBacks['¬'] = new AssignRightsParser();
/* 718:501 */     IncomingMessageEvent.callBacks[1999] = new RemoveRightsParser();
/* 719:502 */     IncomingMessageEvent.callBacks[3414] = new RemoveAllRightsParser();
/* 720:503 */     IncomingMessageEvent.callBacks[2154] = new RemoveBanParser();
/* 721:504 */     IncomingMessageEvent.callBacks[3197] = new MuteUserParser();
/* 722:505 */     IncomingMessageEvent.callBacks[1004] = new LetUserInParser();
/* 723:506 */     IncomingMessageEvent.callBacks[2631] = new DropCarryObjectParser();
/* 724:507 */     IncomingMessageEvent.callBacks[790] = new ShareCarryObjectParser();
/* 725:    */     
/* 726:509 */     IncomingMessageEvent.callBacks[1395] = new LookToParser();
/* 727:510 */     IncomingMessageEvent.callBacks[1933] = new DanceParser();
/* 728:511 */     IncomingMessageEvent.callBacks[2478] = new SignParser();
/* 729:512 */     IncomingMessageEvent.callBacks[1760] = new ChangePostureParser();
/* 730:513 */     IncomingMessageEvent.callBacks[1871] = new ChangeMottoParser();
/* 731:514 */     IncomingMessageEvent.callBacks[3843] = new SetAvatarExpressionParser();
/* 732:    */     
/* 733:516 */     IncomingMessageEvent.callBacks[3479] = new StartTypingParser();
/* 734:517 */     IncomingMessageEvent.callBacks[3215] = new CancelTypingParser();
/* 735:518 */     IncomingMessageEvent.callBacks[616] = new ChatParser();
/* 736:519 */     IncomingMessageEvent.callBacks[569] = new ShoutParser();
/* 737:520 */     IncomingMessageEvent.callBacks[1472] = new WhisperParser();
/* 738:    */     
/* 739:522 */     IncomingMessageEvent.callBacks[1959] = new UpdateRoomMapParser();
/* 740:523 */     IncomingMessageEvent.callBacks[3382] = new PickupObjectParser();
/* 741:524 */     IncomingMessageEvent.callBacks[804] = new MoveObjectParser();
/* 742:525 */     IncomingMessageEvent.callBacks[1948] = new MoveAvatarParser();
/* 743:526 */     IncomingMessageEvent.callBacks[2642] = new PlaceObjectParser();
/* 744:527 */     IncomingMessageEvent.callBacks[1508] = new MoveWallItemParser();
/* 745:528 */     IncomingMessageEvent.callBacks[587] = new GetRoomEntryDataParser();
/* 746:529 */     IncomingMessageEvent.callBacks[792] = new UseFurnitureParser();
/* 747:530 */     IncomingMessageEvent.callBacks[2032] = new UseWallItemParser();
/* 748:531 */     IncomingMessageEvent.callBacks[387] = new SetClothingChangeDataParser();
/* 749:532 */     IncomingMessageEvent.callBacks[3046] = new PlacePetParser();
/* 750:533 */     IncomingMessageEvent.callBacks[1229] = new RemovePetFromFlatParser();
/* 751:534 */     IncomingMessageEvent.callBacks[3535] = new GetPetCommandsParser();
/* 752:535 */     IncomingMessageEvent.callBacks[996] = new GetRoomCampaignAdsParser();
/* 753:536 */     IncomingMessageEvent.callBacks[2073] = new PlaceRentalBotParser();
/* 754:537 */     IncomingMessageEvent.callBacks[3693] = new RemoveBotFromFlatParser();
/* 755:538 */     IncomingMessageEvent.callBacks[3948] = new GetRoomCompetitionParser();
/* 756:539 */     IncomingMessageEvent.callBacks[1003] = new ObjectSaveStuffDataParser();
/* 757:    */     
/* 758:541 */     IncomingMessageEvent.callBacks[3919] = new RoomDimmerGetPresetsParser();
/* 759:542 */     IncomingMessageEvent.callBacks[3336] = new RoomDimmerSavePresetParser();
/* 760:543 */     IncomingMessageEvent.callBacks['Ü'] = new RoomDimmerChangeStateParser();
/* 761:    */     
/* 762:545 */     IncomingMessageEvent.callBacks[3625] = new PlacePostItParser();
/* 763:546 */     IncomingMessageEvent.callBacks[813] = new OpenPostItParser();
/* 764:    */     
/* 765:548 */     IncomingMessageEvent.callBacks[1610] = new AddSpamWallPostIt2Parser();
/* 766:549 */     IncomingMessageEvent.callBacks[2647] = new CreditFurniRedeemParser();
/* 767:550 */     IncomingMessageEvent.callBacks[2489] = new ThrowDiceParser();
/* 768:551 */     IncomingMessageEvent.callBacks[2867] = new DiceOffParser();
/* 769:    */     
/* 770:553 */     IncomingMessageEvent.callBacks[2477] = new SetOutfitNameParser();
/* 771:554 */     IncomingMessageEvent.callBacks[1767] = new UpdateOutfitParser();
/* 772:555 */     IncomingMessageEvent.callBacks[''] = new PresentOpenParser();
/* 773:    */     
/* 774:    */ 
/* 775:558 */     IncomingMessageEvent.callBacks[3239] = new GetPetInfoParser();
/* 776:559 */     IncomingMessageEvent.callBacks[1860] = new RespectPetParser();
/* 777:    */     
/* 778:561 */     IncomingMessageEvent.callBacks[1098] = new SetBotSkillParser();
/* 779:562 */     IncomingMessageEvent.callBacks[3518] = new RequestBotSkillParser();
/* 780:    */     
/* 781:564 */     IncomingMessageEvent.callBacks[1190] = new OpenFlatConnectionParser();
/* 782:    */     
/* 783:566 */     IncomingMessageEvent.callBacks[817] = new GoToFlatParser();
/* 784:567 */     IncomingMessageEvent.callBacks[2065] = new ChangeQueueParser();
/* 785:568 */     IncomingMessageEvent.callBacks[549] = new QuitParser();
/* 786:    */     
/* 787:570 */     IncomingMessageEvent.callBacks[1220] = new SetRoomMuteStateParser();
/* 788:571 */     IncomingMessageEvent.callBacks[3174] = new DeleteRoomParser();
/* 789:572 */     IncomingMessageEvent.callBacks[2523] = new GetRoomSettingsParser();
/* 790:573 */     IncomingMessageEvent.callBacks[3232] = new SaveRoomSettingsMessageEvent();
/* 791:574 */     IncomingMessageEvent.callBacks[3969] = new GetFlatControllersParser();
/* 792:575 */     IncomingMessageEvent.callBacks[115] = new GetBannedUsersParser();
/* 793:    */     
/* 794:577 */     IncomingMessageEvent.callBacks[''] = new GetNowPlayingParser();
/* 795:578 */     IncomingMessageEvent.callBacks[3755] = new AddJukeboxDiskParser();
/* 796:579 */     IncomingMessageEvent.callBacks[2492] = new RemoveJukeboxDiskParser();
/* 797:580 */     IncomingMessageEvent.callBacks[2355] = new GetUserSongDisksParser();
/* 798:581 */     IncomingMessageEvent.callBacks[3577] = new GetJukeboxPlayListParser();
/* 799:582 */     IncomingMessageEvent.callBacks[752] = new GetSongInfoParser();
/* 800:583 */     IncomingMessageEvent.callBacks[1691] = new SetSoundSettingsParser();
/* 801:    */     
/* 802:585 */     IncomingMessageEvent.callBacks[2386] = new GetTalentTrackParser();
/* 803:    */     
/* 804:587 */     IncomingMessageEvent.callBacks[3084] = new LatencyPingRequestParser();
/* 805:588 */     IncomingMessageEvent.callBacks[''] = new LatencyPingReportParser();
/* 806:589 */     IncomingMessageEvent.callBacks[2621] = new PerformanceLogParser();
/* 807:590 */     IncomingMessageEvent.callBacks[2040] = new EventLogParser();
/* 808:    */     
/* 809:592 */     IncomingMessageEvent.callBacks[3917] = new UpdateTriggerParser();
/* 810:593 */     IncomingMessageEvent.callBacks[1329] = new UpdateActionParser();
/* 811:594 */     IncomingMessageEvent.callBacks[750] = new UpdateConditionParser();
/* 812:595 */     IncomingMessageEvent.callBacks[1932] = new OpenParser();
/* 813:    */     
/* 814:597 */     IncomingMessageEvent.callBacks[2143] = new ScrGetUserInfoParser();
/* 815:598 */     IncomingMessageEvent.callBacks[851] = new GetExtendedProfileParser();
/* 816:599 */     IncomingMessageEvent.callBacks[1819] = new ApproveNameParser();
/* 817:600 */     IncomingMessageEvent.callBacks[3626] = new GetUserTagsParser();
/* 818:601 */     IncomingMessageEvent.callBacks[3342] = new GetIgnoredUsersParser();
/* 819:602 */     IncomingMessageEvent.callBacks[2525] = new GetRelationshipStatusParser();
/* 820:603 */     IncomingMessageEvent.callBacks[2683] = new GetSelectedBadgesParser();
/* 821:604 */     IncomingMessageEvent.callBacks[3244] = new RespectUserParser();
/* 822:605 */     IncomingMessageEvent.callBacks[2994] = new GetUserNotificationsParser();
/* 823:606 */     IncomingMessageEvent.callBacks[3953] = new GetHabboGroupBadgesParser();
/* 824:607 */     IncomingMessageEvent.callBacks[518] = new GetUserSettingsParser();
/* 825:608 */     IncomingMessageEvent.callBacks[2435] = new SetUserChatSettingMessageEvent();
/* 826:    */   }
/* 827:    */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.OpCodesOld
 * JD-Core Version:    0.7.0.1
 */