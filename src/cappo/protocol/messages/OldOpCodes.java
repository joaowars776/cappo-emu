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
/*  22:    */ import cappo.protocol.messages.events.catalog.PurchaseFromCatalogParser;
/*  23:    */ import cappo.protocol.messages.events.friendlist.AcceptFriendParser;
/*  24:    */ import cappo.protocol.messages.events.friendlist.DeclineFriendParser;
/*  25:    */ import cappo.protocol.messages.events.friendlist.FollowFriendParser;
/*  26:    */ import cappo.protocol.messages.events.friendlist.FriendListUpdateParser;
/*  27:    */ import cappo.protocol.messages.events.friendlist.GetBuddyRequestsParser;
/*  28:    */ import cappo.protocol.messages.events.friendlist.HabboSearchParser;
/*  29:    */ import cappo.protocol.messages.events.friendlist.MessengerInitParser;
/*  30:    */ import cappo.protocol.messages.events.friendlist.RemoveFriendParser;
/*  31:    */ import cappo.protocol.messages.events.friendlist.RequestBuddyParser;
/*  32:    */ import cappo.protocol.messages.events.friendlist.SendMsgParser;
/*  33:    */ import cappo.protocol.messages.events.friendlist.SendRoomInviteParser;
/*  34:    */ import cappo.protocol.messages.events.friendlist.SetRelationshipStatusParser;
/*  35:    */ import cappo.protocol.messages.events.games.gamecenter.GetGameAchievementsParser;
/*  36:    */ import cappo.protocol.messages.events.games.gamecenter.GetGameListParser;
/*  37:    */ import cappo.protocol.messages.events.games.gamecenter.GetStatusGameParser;
/*  38:    */ import cappo.protocol.messages.events.games.gamecenter.JoinPlayerQueueParser;
/*  39:    */ import cappo.protocol.messages.events.games.snowwar.CheckGameDirectoryStatusParser;
/*  40:    */ import cappo.protocol.messages.events.games.snowwar.ExitGameParser;
/*  41:    */ import cappo.protocol.messages.events.games.snowwar.GameChatParser;
/*  42:    */ import cappo.protocol.messages.events.games.snowwar.GetAccountGameStatusParser;
/*  43:    */ import cappo.protocol.messages.events.games.snowwar.LeaveGameParser;
/*  44:    */ import cappo.protocol.messages.events.games.snowwar.LoadStageReadyParser;
/*  45:    */ import cappo.protocol.messages.events.games.snowwar.MakeSnowballParser;
/*  46:    */ import cappo.protocol.messages.events.games.snowwar.PlayAgainParser;
/*  47:    */ import cappo.protocol.messages.events.games.snowwar.QuickJoinGameParser;
/*  48:    */ import cappo.protocol.messages.events.games.snowwar.RequestFullStatusUpdateParser;
/*  49:    */ import cappo.protocol.messages.events.games.snowwar.SetUserMoveTargetParser;
/*  50:    */ import cappo.protocol.messages.events.games.snowwar.ThrowSnowballAtHumanParser;
/*  51:    */ import cappo.protocol.messages.events.games.snowwar.ThrowSnowballAtPositionParser;
/*  52:    */ import cappo.protocol.messages.events.guides.SetDutyGuideToolParser;
/*  53:    */ import cappo.protocol.messages.events.handshake.DisconnectParser;
/*  54:    */ import cappo.protocol.messages.events.handshake.GenerateSecretKeyParser;
/*  55:    */ import cappo.protocol.messages.events.handshake.InfoRetrieveParser;
/*  56:    */ import cappo.protocol.messages.events.handshake.InitCryptoParser;
/*  57:    */ import cappo.protocol.messages.events.handshake.PongParser;
/*  58:    */ import cappo.protocol.messages.events.handshake.SSOTicketParser;
/*  59:    */ import cappo.protocol.messages.events.handshake.UniqueIDParser;
/*  60:    */ import cappo.protocol.messages.events.handshake.VersionCheckParser;
/*  61:    */ import cappo.protocol.messages.events.help.CallForHelp2Parser;
/*  62:    */ import cappo.protocol.messages.events.help.CallForHelpInRoomParser;
/*  63:    */ import cappo.protocol.messages.events.help.CallForHelpOpenParser;
/*  64:    */ import cappo.protocol.messages.events.help.CallForHelpParser;
/*  65:    */ import cappo.protocol.messages.events.help.CallForHelpRoomPanicParser;
/*  66:    */ import cappo.protocol.messages.events.help.CallForHelpRoomParser;
/*  67:    */ import cappo.protocol.messages.events.inventory.achievements.GetAchievementsParser;
/*  68:    */ import cappo.protocol.messages.events.inventory.avatareffect.AvatarEffectActivatedParser;
/*  69:    */ import cappo.protocol.messages.events.inventory.avatareffect.AvatarEffectSelectedParser;
/*  70:    */ import cappo.protocol.messages.events.inventory.badges.GetBadgePointLimitsParser;
/*  71:    */ import cappo.protocol.messages.events.inventory.badges.GetBadgesParser;
/*  72:    */ import cappo.protocol.messages.events.inventory.badges.SetActivatedBadgesParser;
/*  73:    */ import cappo.protocol.messages.events.inventory.bots.RequestBotInventoryParser;
/*  74:    */ import cappo.protocol.messages.events.inventory.furni.RequestFurniInventoryParser;
/*  75:    */ import cappo.protocol.messages.events.inventory.furni.RequestRoomPropertySetParser;
/*  76:    */ import cappo.protocol.messages.events.inventory.pets.RequestPetInventoryParser;
/*  77:    */ import cappo.protocol.messages.events.inventory.purse.GetCreditsInfoParser;
/*  78:    */ import cappo.protocol.messages.events.inventory.trading.AcceptTradingParser;
/*  79:    */ import cappo.protocol.messages.events.inventory.trading.AddItemToTradeParser;
/*  80:    */ import cappo.protocol.messages.events.inventory.trading.CloseTradingParser;
/*  81:    */ import cappo.protocol.messages.events.inventory.trading.ConfirmAcceptTradingParser;
/*  82:    */ import cappo.protocol.messages.events.inventory.trading.ConfirmDeclineTradingParser;
/*  83:    */ import cappo.protocol.messages.events.inventory.trading.OpenTradingParser;
/*  84:    */ import cappo.protocol.messages.events.inventory.trading.RemoveItemFromTradeParser;
/*  85:    */ import cappo.protocol.messages.events.inventory.trading.UnacceptTradingParser;
/*  86:    */ import cappo.protocol.messages.events.landing.GetLandingNewsParser;
/*  87:    */ import cappo.protocol.messages.events.landing.GetLandingView6Parser;
/*  88:    */ import cappo.protocol.messages.events.landing.GetNextLimitedAvailableParser;
/*  89:    */ import cappo.protocol.messages.events.landing.RefreshLandingViewParser;
/*  90:    */ import cappo.protocol.messages.events.marketplace.GetMarketplaceCanMakeOfferParser;
/*  91:    */ import cappo.protocol.messages.events.marketplace.GetMarketplaceConfigurationParser;
/*  92:    */ import cappo.protocol.messages.events.moderator.CloseIssuesParser;
/*  93:    */ import cappo.protocol.messages.events.moderator.GetModeratorRoomInfoParser;
/*  94:    */ import cappo.protocol.messages.events.moderator.GetModeratorUserInfoParser;
/*  95:    */ import cappo.protocol.messages.events.moderator.ModBanParser;
/*  96:    */ import cappo.protocol.messages.events.moderator.ModKickParser;
/*  97:    */ import cappo.protocol.messages.events.moderator.ModMessageParser;
/*  98:    */ import cappo.protocol.messages.events.moderator.ModMuteParser;
/*  99:    */ import cappo.protocol.messages.events.moderator.ModerateRoomParser;
/* 100:    */ import cappo.protocol.messages.events.moderator.ModeratorActionParser;
/* 101:    */ import cappo.protocol.messages.events.moderator.ModeratorRoomActionParser;
/* 102:    */ import cappo.protocol.messages.events.moderator.PickIssuesParser;
/* 103:    */ import cappo.protocol.messages.events.moderator.ReleaseIssuesParser;
/* 104:    */ import cappo.protocol.messages.events.navigator.AddFavouriteRoomParser;
/* 105:    */ import cappo.protocol.messages.events.navigator.CanCreateRoomParser;
/* 106:    */ import cappo.protocol.messages.events.navigator.CreateFlatParser;
/* 107:    */ import cappo.protocol.messages.events.navigator.DeleteFavouriteRoomParser;
/* 108:    */ import cappo.protocol.messages.events.navigator.EditEventParser;
/* 109:    */ import cappo.protocol.messages.events.navigator.GetGuestRoomParser;
/* 110:    */ import cappo.protocol.messages.events.navigator.GetOfficialRoomsParser;
/* 111:    */ import cappo.protocol.messages.events.navigator.GetPopularRoomTagsParser;
/* 112:    */ import cappo.protocol.messages.events.navigator.GetUserFlatCatsParser;
/* 113:    */ import cappo.protocol.messages.events.navigator.LatestEventsSearchParser;
/* 114:    */ import cappo.protocol.messages.events.navigator.MyFavouriteRoomsSearchParser;
/* 115:    */ import cappo.protocol.messages.events.navigator.MyFriendsRoomsSearchParser;
/* 116:    */ import cappo.protocol.messages.events.navigator.MyRoomHistorySearchParser;
/* 117:    */ import cappo.protocol.messages.events.navigator.MyRoomsSearchParser;
/* 118:    */ import cappo.protocol.messages.events.navigator.PopularRoomsSearchParser;
/* 119:    */ import cappo.protocol.messages.events.navigator.RateFlatParser;
/* 120:    */ import cappo.protocol.messages.events.navigator.RoomTagSearchParser;
/* 121:    */ import cappo.protocol.messages.events.navigator.RoomTextSearchParser;
/* 122:    */ import cappo.protocol.messages.events.navigator.RoomsWhereMyFriendsAreSearchParser;
/* 123:    */ import cappo.protocol.messages.events.navigator.RoomsWithHighestScoreSearchParser;
/* 124:    */ import cappo.protocol.messages.events.navigator.ToggleStaffPickParser;
/* 125:    */ import cappo.protocol.messages.events.navigator.UpdateNavigatorSettingsParser;
/* 126:    */ import cappo.protocol.messages.events.notifications.ResetUnseenItemsParser;
/* 127:    */ import cappo.protocol.messages.events.poll.PollAnswerParser;
/* 128:    */ import cappo.protocol.messages.events.poll.PollRejectParser;
/* 129:    */ import cappo.protocol.messages.events.poll.PollStartParser;
/* 130:    */ import cappo.protocol.messages.events.quest.FriendRequestQuestCompleteParser;
/* 131:    */ import cappo.protocol.messages.events.recycler.GetRecyclerPrizesParser;
/* 132:    */ import cappo.protocol.messages.events.register.UpdateFigureDataParser;
/* 133:    */ import cappo.protocol.messages.events.room.action.AssignRightsParser;
/* 134:    */ import cappo.protocol.messages.events.room.action.BanUserParser;
/* 135:    */ import cappo.protocol.messages.events.room.action.DropCarryObjectParser;
/* 136:    */ import cappo.protocol.messages.events.room.action.KickUserParser;
/* 137:    */ import cappo.protocol.messages.events.room.action.LetUserInParser;
/* 138:    */ import cappo.protocol.messages.events.room.action.RemoveAllRightsParser;
/* 139:    */ import cappo.protocol.messages.events.room.action.RemoveRightsParser;
/* 140:    */ import cappo.protocol.messages.events.room.action.ShareCarryObjectParser;
/* 141:    */ import cappo.protocol.messages.events.room.avatar.ChangeMottoParser;
/* 142:    */ import cappo.protocol.messages.events.room.avatar.ChangePostureParser;
/* 143:    */ import cappo.protocol.messages.events.room.avatar.DanceParser;
/* 144:    */ import cappo.protocol.messages.events.room.avatar.LookToParser;
/* 145:    */ import cappo.protocol.messages.events.room.avatar.SetAvatarExpressionParser;
/* 146:    */ import cappo.protocol.messages.events.room.avatar.SignParser;
/* 147:    */ import cappo.protocol.messages.events.room.bots.RequestBotSkillParser;
/* 148:    */ import cappo.protocol.messages.events.room.bots.SetBotSkillParser;
/* 149:    */ import cappo.protocol.messages.events.room.chat.CancelTypingParser;
/* 150:    */ import cappo.protocol.messages.events.room.chat.ChatParser;
/* 151:    */ import cappo.protocol.messages.events.room.chat.ShoutParser;
/* 152:    */ import cappo.protocol.messages.events.room.chat.StartTypingParser;
/* 153:    */ import cappo.protocol.messages.events.room.chat.WhisperParser;
/* 154:    */ import cappo.protocol.messages.events.room.engine.GetPetCommandsParser;
/* 155:    */ import cappo.protocol.messages.events.room.engine.GetRoomCampaignAdsParser;
/* 156:    */ import cappo.protocol.messages.events.room.engine.GetRoomCompetitionParser;
/* 157:    */ import cappo.protocol.messages.events.room.engine.GetRoomEntryDataParser;
/* 158:    */ import cappo.protocol.messages.events.room.engine.MoveAvatarParser;
/* 159:    */ import cappo.protocol.messages.events.room.engine.MoveObjectParser;
/* 160:    */ import cappo.protocol.messages.events.room.engine.MoveWallItemParser;
/* 161:    */ import cappo.protocol.messages.events.room.engine.ObjectSaveStuffDataParser;
/* 162:    */ import cappo.protocol.messages.events.room.engine.PickupObjectParser;
/* 163:    */ import cappo.protocol.messages.events.room.engine.PlaceObjectParser;
/* 164:    */ import cappo.protocol.messages.events.room.engine.PlacePetParser;
/* 165:    */ import cappo.protocol.messages.events.room.engine.PlaceRentalBotParser;
/* 166:    */ import cappo.protocol.messages.events.room.engine.RemoveBotFromFlatParser;
/* 167:    */ import cappo.protocol.messages.events.room.engine.RemovePetFromFlatParser;
/* 168:    */ import cappo.protocol.messages.events.room.engine.SetClothingChangeDataParser;
/* 169:    */ import cappo.protocol.messages.events.room.engine.UseFurnitureParser;
/* 170:    */ import cappo.protocol.messages.events.room.engine.UseWallItemParser;
/* 171:    */ import cappo.protocol.messages.events.room.furniture.AddSpamWallPostIt2Parser;
/* 172:    */ import cappo.protocol.messages.events.room.furniture.CreditFurniRedeemParser;
/* 173:    */ import cappo.protocol.messages.events.room.furniture.DiceOffParser;
/* 174:    */ import cappo.protocol.messages.events.room.furniture.OpenPostItParser;
/* 175:    */ import cappo.protocol.messages.events.room.furniture.PlacePostItParser;
/* 176:    */ import cappo.protocol.messages.events.room.furniture.RoomDimmerChangeStateParser;
/* 177:    */ import cappo.protocol.messages.events.room.furniture.RoomDimmerGetPresetsParser;
/* 178:    */ import cappo.protocol.messages.events.room.furniture.RoomDimmerSavePresetParser;
/* 179:    */ import cappo.protocol.messages.events.room.furniture.SetOutfitNameParser;
/* 180:    */ import cappo.protocol.messages.events.room.furniture.SpinWheelOfFortuneParser;
/* 181:    */ import cappo.protocol.messages.events.room.furniture.ThrowDiceParser;
/* 182:    */ import cappo.protocol.messages.events.room.furniture.UpdateOutfitParser;
/* 183:    */ import cappo.protocol.messages.events.room.pets.GetPetInfoParser;
/* 184:    */ import cappo.protocol.messages.events.room.pets.RespectPetParser;
/* 185:    */ import cappo.protocol.messages.events.room.session.ChangeQueueParser;
/* 186:    */ import cappo.protocol.messages.events.room.session.GoToFlatParser;
/* 187:    */ import cappo.protocol.messages.events.room.session.OpenFlatConnectionParser;
/* 188:    */ import cappo.protocol.messages.events.room.session.QuitParser;
/* 189:    */ import cappo.protocol.messages.events.roomsettings.DeleteRoomParser;
/* 190:    */ import cappo.protocol.messages.events.roomsettings.GetBannedUsersParser;
/* 191:    */ import cappo.protocol.messages.events.roomsettings.GetFlatControllersParser;
/* 192:    */ import cappo.protocol.messages.events.roomsettings.GetRoomSettingsParser;
/* 193:    */ import cappo.protocol.messages.events.roomsettings.SaveRoomSettingsMessageEvent;
/* 194:    */ import cappo.protocol.messages.events.roomsettings.SetRoomMuteStateParser;
/* 195:    */ import cappo.protocol.messages.events.sound.AddJukeboxDiskParser;
/* 196:    */ import cappo.protocol.messages.events.sound.GetJukeboxPlayListParser;
/* 197:    */ import cappo.protocol.messages.events.sound.GetNowPlayingParser;
/* 198:    */ import cappo.protocol.messages.events.sound.GetSongInfoParser;
/* 199:    */ import cappo.protocol.messages.events.sound.GetUserSongDisksParser;
/* 200:    */ import cappo.protocol.messages.events.sound.RemoveJukeboxDiskParser;
/* 201:    */ import cappo.protocol.messages.events.sound.SetSoundSettingsParser;
/* 202:    */ import cappo.protocol.messages.events.talents.GetTalentTrackParser;
/* 203:    */ import cappo.protocol.messages.events.tracking.EventLogParser;
/* 204:    */ import cappo.protocol.messages.events.tracking.LatencyPingReportParser;
/* 205:    */ import cappo.protocol.messages.events.tracking.LatencyPingRequestParser;
/* 206:    */ import cappo.protocol.messages.events.tracking.PerformanceLogParser;
/* 207:    */ import cappo.protocol.messages.events.userdefinedroomevents.OpenParser;
/* 208:    */ import cappo.protocol.messages.events.userdefinedroomevents.UpdateActionParser;
/* 209:    */ import cappo.protocol.messages.events.userdefinedroomevents.UpdateConditionParser;
/* 210:    */ import cappo.protocol.messages.events.userdefinedroomevents.UpdateTriggerParser;
/* 211:    */ import cappo.protocol.messages.events.users.ApproveNameParser;
/* 212:    */ import cappo.protocol.messages.events.users.GetExtendedProfileParser;
/* 213:    */ import cappo.protocol.messages.events.users.GetHabboGroupBadgesParser;
/* 214:    */ import cappo.protocol.messages.events.users.GetIgnoredUsersParser;
/* 215:    */ import cappo.protocol.messages.events.users.GetRelationshipStatusParser;
/* 216:    */ import cappo.protocol.messages.events.users.GetSelectedBadgesParser;
/* 217:    */ import cappo.protocol.messages.events.users.GetUserNotificationsParser;
/* 218:    */ import cappo.protocol.messages.events.users.GetUserSettingsParser;
/* 219:    */ import cappo.protocol.messages.events.users.GetUserTagsParser;
/* 220:    */ import cappo.protocol.messages.events.users.RespectUserParser;
/* 221:    */ import cappo.protocol.messages.events.users.ScrGetUserInfoParser;
/* 222:    */ import cappo.protocol.messages.events.users.SetUserChatSettingMessageEvent;
/* 223:    */ 
/* 224:    */ public class OldOpCodes
/* 225:    */ {
/* 226:    */   public static byte Init;
/* 227:    */   
/* 228:    */   static
/* 229:    */   {
/* 230: 19 */     cappo.protocol.messages.composers.landing.UpdateLandingComposer.HEADER = 1286;
/* 231: 20 */     cappo.protocol.messages.composers.landing.LandingNewsComposer.HEADER = 2915;
/* 232: 21 */     cappo.protocol.messages.composers.landing.BadgeButtonStatusComposer.HEADER = 211;
/* 233: 22 */     cappo.protocol.messages.composers.landing.LandingView6Composer.HEADER = 2210;
/* 234: 23 */     cappo.protocol.messages.composers.landing.RewardResultComposer.HEADER = 634;
/* 235:    */     
/* 236:    */ 
/* 237: 26 */     cappo.protocol.messages.composers.advertisement.InterstitialComposer.HEADER = 2347;
/* 238:    */     
/* 239:    */ 
/* 240: 29 */     cappo.protocol.messages.composers.availability.Pending2029Composer.HEADER = 1151;
/* 241: 30 */     cappo.protocol.messages.composers.availability.Pending548Composer.HEADER = 2931;
/* 242: 31 */     cappo.protocol.messages.composers.availability.AvailabilityStatusComposer.HEADER = 134;
/* 243: 32 */     cappo.protocol.messages.composers.availability.Pending2850Composer.HEADER = 2931;
/* 244:    */     
/* 245: 34 */     cappo.protocol.messages.composers.avatar.WardrobeComposer.HEADER = 1538;
/* 246: 35 */     cappo.protocol.messages.composers.avatar.ResultChangeUserNameComposer.HEADER = 452;
/* 247: 36 */     cappo.protocol.messages.composers.avatar.ResultCheckUserNameComposer.HEADER = 1387;
/* 248:    */     
/* 249: 38 */     cappo.protocol.messages.composers.notifications.BuyNotificationComposer.HEADER = 797;
/* 250: 39 */     cappo.protocol.messages.composers.catalog.BundleDynamicDiscountsComposer.HEADER = 2342;
/* 251: 40 */     cappo.protocol.messages.composers.catalog.CatalogIndexComposer.HEADER = 755;
/* 252: 41 */     cappo.protocol.messages.composers.catalog.CatalogPageComposer.HEADER = 1484;
/* 253: 42 */     cappo.protocol.messages.composers.catalog.ErrorPurchaseFromCatalogComposer.HEADER = 3149;
/* 254: 43 */     cappo.protocol.messages.composers.catalog.GiftWrappingConfigurationComposer.HEADER = 3305;
/* 255: 44 */     cappo.protocol.messages.composers.catalog.HabboClubExtendOfferComposer.HEADER = 2694;
/* 256: 45 */     cappo.protocol.messages.composers.catalog.HabboClubOffersComposer.HEADER = 161;
/* 257: 46 */     cappo.protocol.messages.composers.catalog.SellablePetBreedsComposer.HEADER = 189;
/* 258: 47 */     cappo.protocol.messages.composers.catalog.SnowWarTokensComposer.HEADER = 2706;
/* 259: 48 */     cappo.protocol.messages.composers.catalog.UniqueLimitedItemComposer.HEADER = 2002;
/* 260: 49 */     cappo.protocol.messages.composers.catalog.UniqueLimitedItemSoldOutComposer.HEADER = 2651;
/* 261: 50 */     cappo.protocol.messages.composers.catalog.ErrorBuyComposer.HEADER = 1374;
/* 262:    */     
/* 263: 52 */     cappo.protocol.messages.composers.error.ErrorComposer.HEADER = 2814;
/* 264:    */     
/* 265: 54 */     cappo.protocol.messages.composers.facebook.Pending1298Composer.HEADER = 2569;
/* 266: 55 */     cappo.protocol.messages.composers.facebook.Pending2310Composer.HEADER = 803;
/* 267: 56 */     cappo.protocol.messages.composers.facebook.Pending3136Composer.HEADER = 3370;
/* 268:    */     
/* 269: 58 */     cappo.protocol.messages.composers.friendlist.BuddyMessageComposer.HEADER = 968;
/* 270: 59 */     cappo.protocol.messages.composers.friendlist.BuddyRequestsComposer.HEADER = 3815;
/* 271: 60 */     cappo.protocol.messages.composers.friendlist.FollowFriendFailedComposer.HEADER = 2639;
/* 272: 61 */     cappo.protocol.messages.composers.friendlist.InstantMessageErrorComposer.HEADER = 901;
/* 273: 62 */     cappo.protocol.messages.composers.friendlist.MessengerErrorComposer.HEADER = 1156;
/* 274: 63 */     cappo.protocol.messages.composers.friendlist.HabboSearchResultsComposer.HEADER = 3457;
/* 275: 64 */     cappo.protocol.messages.composers.friendlist.MessengerInitComposer.HEADER = 1514;
/* 276: 65 */     cappo.protocol.messages.composers.friendlist.NewBuddyRequestComposer.HEADER = 1203;
/* 277: 66 */     cappo.protocol.messages.composers.friendlist.FriendsUpdatesComposer.HEADER = 363;
/* 278: 67 */     cappo.protocol.messages.composers.friendlist.RoomInviteComposer.HEADER = 3015;
/* 279: 68 */     cappo.protocol.messages.composers.friendlist.RoomInviteErrorComposer.HEADER = 3507;
/* 280:    */     
/* 281: 70 */     cappo.protocol.messages.composers.guides.UpdateGuideToolComposer.HEADER = 1235;
/* 282:    */     
/* 283: 72 */     cappo.protocol.messages.composers.games.gamecenter.JoinedPlayerQueueComposer.HEADER = 607;
/* 284: 73 */     cappo.protocol.messages.composers.games.gamecenter.GameListComposer.HEADER = 2661;
/* 285: 74 */     cappo.protocol.messages.composers.games.gamecenter.StatusGameComposer.HEADER = 1045;
/* 286: 75 */     cappo.protocol.messages.composers.games.gamecenter.LoadGameComposer.HEADER = 117;
/* 287: 76 */     cappo.protocol.messages.composers.games.gamecenter.GameAchievementsComposer.HEADER = 1729;
/* 288:    */     
/* 289: 78 */     cappo.protocol.messages.composers.games.snowwar.ArenaEnteredComposer.HEADER = 1451;
/* 290: 79 */     cappo.protocol.messages.composers.games.snowwar.EnterArenaComposer.HEADER = 2310;
/* 291: 80 */     cappo.protocol.messages.composers.games.snowwar.EnterArenaFailedComposer.HEADER = 3131;
/* 292: 81 */     cappo.protocol.messages.composers.games.snowwar.FriendsLeaderboardComposer.HEADER = 3579;
/* 293: 82 */     cappo.protocol.messages.composers.games.snowwar.FullGameStatusComposer.HEADER = 329;
/* 294: 83 */     cappo.protocol.messages.composers.games.snowwar.GameCancelledComposer.HEADER = 2152;
/* 295: 84 */     cappo.protocol.messages.composers.games.snowwar.GameChatFromPlayerComposer.HEADER = 3617;
/* 296: 85 */     cappo.protocol.messages.composers.games.snowwar.GameCreatedComposer.HEADER = 1606;
/* 297: 86 */     cappo.protocol.messages.composers.games.snowwar.GameDirectoryStatusComposer.HEADER = 418;
/* 298: 87 */     cappo.protocol.messages.composers.games.snowwar.GameEndingComposer.HEADER = 24;
/* 299: 88 */     cappo.protocol.messages.composers.games.snowwar.GameLongDataComposer.HEADER = 2077;
/* 300: 89 */     cappo.protocol.messages.composers.games.snowwar.GameRejoinComposer.HEADER = 997;
/* 301: 90 */     cappo.protocol.messages.composers.games.snowwar.GameStatusComposer.HEADER = 577;
/* 302: 91 */     cappo.protocol.messages.composers.games.snowwar.InArenaQueueComposer.HEADER = 194;
/* 303: 92 */     cappo.protocol.messages.composers.games.snowwar.JoiningGameFailedComposer.HEADER = 1078;
/* 304: 93 */     cappo.protocol.messages.composers.games.snowwar.PlayerExitedGameArenaComposer.HEADER = 2267;
/* 305: 94 */     cappo.protocol.messages.composers.games.snowwar.PlayerRematchesComposer.HEADER = 3574;
/* 306: 95 */     cappo.protocol.messages.composers.games.snowwar.StageEndingComposer.HEADER = 1068;
/* 307: 96 */     cappo.protocol.messages.composers.games.snowwar.StageLoadComposer.HEADER = 3855;
/* 308: 97 */     cappo.protocol.messages.composers.games.snowwar.StageRunningComposer.HEADER = 3770;
/* 309: 98 */     cappo.protocol.messages.composers.games.snowwar.StageStartingComposer.HEADER = 793;
/* 310: 99 */     cappo.protocol.messages.composers.games.snowwar.StageStillLoadingComposer.HEADER = 1553;
/* 311:100 */     cappo.protocol.messages.composers.games.snowwar.StartCounterComposer.HEADER = 603;
/* 312:101 */     cappo.protocol.messages.composers.games.snowwar.StartingGameFailedComposer.HEADER = 1926;
/* 313:102 */     cappo.protocol.messages.composers.games.snowwar.StopCounterComposer.HEADER = 887;
/* 314:103 */     cappo.protocol.messages.composers.games.snowwar.TotalLeaderboardComposer.HEADER = 2072;
/* 315:104 */     cappo.protocol.messages.composers.games.snowwar.UserBlockedComposer.HEADER = 1131;
/* 316:105 */     cappo.protocol.messages.composers.games.snowwar.UserJoinedGameComposer.HEADER = 3954;
/* 317:106 */     cappo.protocol.messages.composers.games.snowwar.UserLeftGameComposer.HEADER = 433;
/* 318:107 */     cappo.protocol.messages.composers.games.snowwar.AccountGameStatusComposer.HEADER = 3349;
/* 319:108 */     cappo.protocol.messages.composers.games.snowwar.GameStartedComposer.HEADER = 2759;
/* 320:    */     
/* 321:110 */     cappo.protocol.messages.composers.games.snowwar.WeeklyLeaderboardComposer.HEADER = 2637;
/* 322:    */     
/* 323:112 */     cappo.protocol.messages.composers.handshake.GenericErrorComposer.HEADER = 2117;
/* 324:113 */     cappo.protocol.messages.composers.handshake.UserLevelsComposer.HEADER = 815;
/* 325:114 */     cappo.protocol.messages.composers.handshake.AuthOKComposer.HEADER = 3695;
/* 326:115 */     cappo.protocol.messages.composers.handshake.BannerTokenComposer.HEADER = 2226;
/* 327:116 */     cappo.protocol.messages.composers.handshake.PerkAllowancesComposer.HEADER = 2313;
/* 328:117 */     cappo.protocol.messages.composers.handshake.ConnectionPingComposer.HEADER = 3576;
/* 329:118 */     cappo.protocol.messages.composers.handshake.ServerPublicKeyComposer.HEADER = 2949;
/* 330:119 */     cappo.protocol.messages.composers.handshake.UserDisconnectComposer.HEADER = 4000;
/* 331:120 */     cappo.protocol.messages.composers.handshake.UserInfoComposer.HEADER = 3540;
/* 332:    */     
/* 333:122 */     cappo.protocol.messages.composers.help.CallForHelpMutedComposer.HEADER = 1211;
/* 334:123 */     cappo.protocol.messages.composers.help.CallForHelpOpenComposer.HEADER = 1939;
/* 335:124 */     cappo.protocol.messages.composers.help.CallForHelpPendingCallsComposer.HEADER = 3586;
/* 336:125 */     cappo.protocol.messages.composers.help.CallForHelpReplyComposer.HEADER = 2721;
/* 337:126 */     cappo.protocol.messages.composers.help.CallForHelpResultComposer.HEADER = 2559;
/* 338:127 */     cappo.protocol.messages.composers.help.IssueCloseNotificationComposer.HEADER = 488;
/* 339:    */     
/* 340:129 */     cappo.protocol.messages.composers.inventory.achievements.AchievementsComposer.HEADER = 3913;
/* 341:130 */     cappo.protocol.messages.composers.inventory.achievements.AchievementsScoreComposer.HEADER = 2282;
/* 342:    */     
/* 343:132 */     cappo.protocol.messages.composers.inventory.avatareffect.EffectAddedComposer.HEADER = 3137;
/* 344:133 */     cappo.protocol.messages.composers.inventory.avatareffect.EffectEnabledComposer.HEADER = 2758;
/* 345:134 */     cappo.protocol.messages.composers.inventory.avatareffect.EffectStopedComposer.HEADER = 3605;
/* 346:135 */     cappo.protocol.messages.composers.inventory.avatareffect.EffectsComposer.HEADER = 444;
/* 347:    */     
/* 348:137 */     cappo.protocol.messages.composers.inventory.badges.BadgesComposer.HEADER = 2773;
/* 349:    */     
/* 350:139 */     cappo.protocol.messages.composers.inventory.furni.FurniListComposer.HEADER = 2303;
/* 351:140 */     cappo.protocol.messages.composers.inventory.furni.FurniListAddOrUpdateComposer.HEADER = 201;
/* 352:141 */     cappo.protocol.messages.composers.inventory.furni.FurniListRemoveComposer.HEADER = 1944;
/* 353:142 */     cappo.protocol.messages.composers.inventory.furni.FurniListUpdateComposer.HEADER = 3798;
/* 354:143 */     cappo.protocol.messages.composers.inventory.furni.PostItPlacedComposer.HEADER = 3749;
/* 355:    */     
/* 356:145 */     cappo.protocol.messages.composers.inventory.pets.AddPetToInventoryComposer.HEADER = 2074;
/* 357:146 */     cappo.protocol.messages.composers.inventory.pets.PetsInventoryComposer.HEADER = 2813;
/* 358:147 */     cappo.protocol.messages.composers.inventory.pets.RemovePetInventoryComposer.HEADER = 3842;
/* 359:    */     
/* 360:149 */     cappo.protocol.messages.composers.inventory.bots.BotsInventoryComposer.HEADER = 3241;
/* 361:150 */     cappo.protocol.messages.composers.inventory.bots.AddBotToInventoryComposer.HEADER = 507;
/* 362:151 */     cappo.protocol.messages.composers.inventory.bots.RemoveBotInventoryComposer.HEADER = 29;
/* 363:    */     
/* 364:153 */     cappo.protocol.messages.composers.inventory.purse.CreditBalanceComposer.HEADER = 3045;
/* 365:    */     
/* 366:155 */     cappo.protocol.messages.composers.inventory.trading.TradingAcceptComposer.HEADER = 2860;
/* 367:156 */     cappo.protocol.messages.composers.inventory.trading.TradingAlreadyOpenComposer.HEADER = 2232;
/* 368:157 */     cappo.protocol.messages.composers.inventory.trading.TradingCloseComposer.HEADER = 3373;
/* 369:158 */     cappo.protocol.messages.composers.inventory.trading.TradingCompletedComposer.HEADER = 315;
/* 370:159 */     cappo.protocol.messages.composers.inventory.trading.TradingConfirmationComposer.HEADER = 491;
/* 371:160 */     cappo.protocol.messages.composers.inventory.trading.TradingItemListComposer.HEADER = 924;
/* 372:161 */     cappo.protocol.messages.composers.inventory.trading.TradingOpenComposer.HEADER = 2290;
/* 373:    */     
/* 374:163 */     cappo.protocol.messages.composers.landing.NextLimitedAvailableComposer.HEADER = 3279;
/* 375:    */     
/* 376:165 */     cappo.protocol.messages.composers.marketplace.MarketplaceCanMakeOfferComposer.HEADER = 605;
/* 377:166 */     cappo.protocol.messages.composers.marketplace.MarketplaceConfigComposer.HEADER = 3996;
/* 378:    */     
/* 379:168 */     cappo.protocol.messages.composers.moderation.IssueInfoComposer.HEADER = 2137;
/* 380:169 */     cappo.protocol.messages.composers.moderation.IssuePickFailedComposer.HEADER = 487;
/* 381:170 */     cappo.protocol.messages.composers.moderation.ModMessageComposer.HEADER = 812;
/* 382:171 */     cappo.protocol.messages.composers.moderation.ModeratorInitComposer.HEADER = 621;
/* 383:172 */     cappo.protocol.messages.composers.moderation.ModeratorRoomInfoComposer.HEADER = 1815;
/* 384:173 */     cappo.protocol.messages.composers.moderation.ModeratorUserInfoComposer.HEADER = 1193;
/* 385:    */     
/* 386:175 */     cappo.protocol.messages.composers.navigator.OfficialRoomsComposer.HEADER = 2392;
/* 387:176 */     cappo.protocol.messages.composers.navigator.FlatCreatedComposer.HEADER = 3484;
/* 388:177 */     cappo.protocol.messages.composers.navigator.DoorbellUserComposer.HEADER = 1610;
/* 389:178 */     cappo.protocol.messages.composers.room.session.FlatAccessibleComposer.HEADER = 3176;
/* 390:179 */     cappo.protocol.messages.composers.navigator.DoorBellNoAnswerComposer.HEADER = 2216;
/* 391:180 */     cappo.protocol.messages.composers.navigator.GuestRoomResultComposer.HEADER = 1664;
/* 392:181 */     cappo.protocol.messages.composers.navigator.CanCreateEventComposer.HEADER = 2073;
/* 393:182 */     cappo.protocol.messages.composers.navigator.CanCreateRoomComposer.HEADER = 684;
/* 394:183 */     cappo.protocol.messages.composers.navigator.EventComposer.HEADER = 2389;
/* 395:184 */     cappo.protocol.messages.composers.navigator.FavouritesComposer.HEADER = 479;
/* 396:185 */     cappo.protocol.messages.composers.navigator.FavouriteChangedComposer.HEADER = 1764;
/* 397:186 */     cappo.protocol.messages.composers.navigator.FlatCategoriesComposer.HEADER = 1177;
/* 398:187 */     cappo.protocol.messages.composers.navigator.NavigatorSettingsComposer.HEADER = 2499;
/* 399:188 */     cappo.protocol.messages.composers.navigator.GuestRoomSearchResultComposer.HEADER = 3439;
/* 400:189 */     cappo.protocol.messages.composers.navigator.PopularRoomTagsResultComposer.HEADER = 468;
/* 401:190 */     cappo.protocol.messages.composers.navigator.RoomRatingComposer.HEADER = 1263;
/* 402:191 */     cappo.protocol.messages.composers.navigator.RoomUpdatedComposer.HEADER = 2263;
/* 403:192 */     cappo.protocol.messages.composers.navigator.FlatAccessDeniedComposer.HEADER = 2150;
/* 404:193 */     cappo.protocol.messages.composers.navigator.RoomForwardComposer.HEADER = 3502;
/* 405:    */     
/* 406:195 */     cappo.protocol.messages.composers.notifications.ActivityPointsComposer.HEADER = 3901;
/* 407:196 */     cappo.protocol.messages.composers.notifications.BroadcastImageComposer.HEADER = 1849;
/* 408:197 */     cappo.protocol.messages.composers.notifications.HabboActivityPointNotificationComposer.HEADER = 2549;
/* 409:198 */     cappo.protocol.messages.composers.notifications.ClubGiftNotificationComposer.HEADER = 2822;
/* 410:199 */     cappo.protocol.messages.composers.notifications.HabboBroadcastComposer.HEADER = 1248;
/* 411:200 */     cappo.protocol.messages.composers.notifications.HabboBroadcastCustomComposer.HEADER = 2515;
/* 412:201 */     cappo.protocol.messages.composers.notifications.InfoFeedEnableComposer.HEADER = 2452;
/* 413:202 */     cappo.protocol.messages.composers.notifications.MOTDComposer.HEADER = 760;
/* 414:203 */     cappo.protocol.messages.composers.notifications.PetLevelNotificationComposer.HEADER = 3121;
/* 415:204 */     cappo.protocol.messages.composers.notifications.UnseenItemsComposer.HEADER = 1287;
/* 416:    */     
/* 417:206 */     cappo.protocol.messages.composers.poll.PollContentsMessageComposer.HEADER = 1984;
/* 418:207 */     cappo.protocol.messages.composers.poll.PollOfferMessageComposer.HEADER = 2520;
/* 419:208 */     cappo.protocol.messages.composers.poll.PollErrorMessageComposer.HEADER = 2304;
/* 420:    */     
/* 421:210 */     cappo.protocol.messages.composers.recycler.RecyclerOkComposer.HEADER = 3856;
/* 422:211 */     cappo.protocol.messages.composers.recycler.RecyclerPrizesComposer.HEADER = 1028;
/* 423:212 */     cappo.protocol.messages.composers.recycler.RecyclerStatusComposer.HEADER = 48;
/* 424:    */     
/* 425:214 */     cappo.protocol.messages.composers.room.action.UserDanceComposer.HEADER = 3010;
/* 426:215 */     cappo.protocol.messages.composers.room.action.AvatarExpressionComposer.HEADER = 112;
/* 427:216 */     cappo.protocol.messages.composers.room.action.CarryObjectComposer.HEADER = 1457;
/* 428:217 */     cappo.protocol.messages.composers.room.action.UserAsleepComposer.HEADER = 2067;
/* 429:218 */     cappo.protocol.messages.composers.room.action.UserEffectComposer.HEADER = 2157;
/* 430:    */     
/* 431:220 */     cappo.protocol.messages.composers.room.chat.ChatComposer.HEADER = 3381;
/* 432:221 */     cappo.protocol.messages.composers.room.chat.FloodControlComposer.HEADER = 85;
/* 433:222 */     cappo.protocol.messages.composers.room.chat.ShoutComposer.HEADER = 980;
/* 434:223 */     cappo.protocol.messages.composers.room.chat.UserTypingComposer.HEADER = 2016;
/* 435:224 */     cappo.protocol.messages.composers.room.chat.WhisperComposer.HEADER = 668;
/* 436:    */     
/* 437:226 */     cappo.protocol.messages.composers.room.bots.BotSkillComposer.HEADER = 1139;
/* 438:227 */     cappo.protocol.messages.composers.room.bots.BotErrorComposer.HEADER = 1838;
/* 439:    */     
/* 440:229 */     cappo.protocol.messages.composers.room.engine.FloorHeightMapComposer.HEADER = 3067;
/* 441:230 */     cappo.protocol.messages.composers.room.engine.HeightMapComposer.HEADER = 1956;
/* 442:231 */     cappo.protocol.messages.composers.room.engine.HeightMapUpdateComposer.HEADER = 3281;
/* 443:232 */     cappo.protocol.messages.composers.room.engine.ObjectsComposer.HEADER = 2289;
/* 444:233 */     cappo.protocol.messages.composers.room.engine.ObjectAddComposer.HEADER = 3983;
/* 445:234 */     cappo.protocol.messages.composers.room.engine.ObjectRemoveComposer.HEADER = 2997;
/* 446:235 */     cappo.protocol.messages.composers.room.engine.ObjectUpdateComposer.HEADER = 2946;
/* 447:236 */     cappo.protocol.messages.composers.room.engine.ObjectDataUpdateComposer.HEADER = 1475;
/* 448:237 */     cappo.protocol.messages.composers.room.engine.ObjectsDataUpdateComposer.HEADER = 2782;
/* 449:238 */     cappo.protocol.messages.composers.room.engine.PublicRoomObjectsMessageParser.HEADER = 3596;
/* 450:239 */     cappo.protocol.messages.composers.room.engine.ItemsComposer.HEADER = 387;
/* 451:240 */     cappo.protocol.messages.composers.room.engine.ItemAddComposer.HEADER = 128;
/* 452:241 */     cappo.protocol.messages.composers.room.engine.ItemRemoveComposer.HEADER = 3811;
/* 453:242 */     cappo.protocol.messages.composers.room.engine.ItemUpdateComposer.HEADER = 2121;
/* 454:243 */     cappo.protocol.messages.composers.room.engine.UsersComposer.HEADER = 467;
/* 455:244 */     cappo.protocol.messages.composers.room.engine.UserUpdateComposer.HEADER = 1720;
/* 456:245 */     cappo.protocol.messages.composers.room.engine.UserChangeComposer.HEADER = 921;
/* 457:246 */     cappo.protocol.messages.composers.room.engine.UserRemoveComposer.HEADER = 3163;
/* 458:247 */     cappo.protocol.messages.composers.room.engine.RoomVisualizationSettingsComposer.HEADER = 1699;
/* 459:248 */     cappo.protocol.messages.composers.room.engine.RoomEntryInfoComposer.HEADER = 3884;
/* 460:249 */     cappo.protocol.messages.composers.room.engine.RoomPropertyComposer.HEADER = 3620;
/* 461:250 */     cappo.protocol.messages.composers.room.engine.RoomCampaignAdsComposer.HEADER = 312;
/* 462:251 */     cappo.protocol.messages.composers.room.engine.SlideObjectBundleComposer.HEADER = 2845;
/* 463:252 */     cappo.protocol.messages.composers.room.engine.PlaceObjectErrorComposer.HEADER = 2787;
/* 464:    */     
/* 465:254 */     cappo.protocol.messages.composers.room.furniture.RequestSpamWallPostItComposer.HEADER = 1804;
/* 466:255 */     cappo.protocol.messages.composers.room.furniture.RoomDimmerPresetsComposer.HEADER = 3940;
/* 467:    */     
/* 468:257 */     cappo.protocol.messages.composers.room.permissions.YouAreControllerComposer.HEADER = 739;
/* 469:258 */     cappo.protocol.messages.composers.room.permissions.YouAreNotControllerComposer.HEADER = 190;
/* 470:259 */     cappo.protocol.messages.composers.room.permissions.YouAreOwnerComposer.HEADER = 41;
/* 471:    */     
/* 472:261 */     cappo.protocol.messages.composers.room.pets.PetCommandsComposer.HEADER = 1918;
/* 473:262 */     cappo.protocol.messages.composers.room.pets.PetInfoComposer.HEADER = 2730;
/* 474:263 */     cappo.protocol.messages.composers.room.pets.PetPlacingErrorComposer.HEADER = 1706;
/* 475:264 */     cappo.protocol.messages.composers.notifications.PetRespectFailedComposer.HEADER = 2891;
/* 476:    */     
/* 477:266 */     cappo.protocol.messages.composers.room.publicroom.ParkBusCannotEnterComposer.HEADER = 225;
/* 478:    */     
/* 479:268 */     cappo.protocol.messages.composers.room.session.YouArePlayingGameComposer.HEADER = 2791;
/* 480:269 */     cappo.protocol.messages.composers.room.session.OpenConnectionComposer.HEADER = 2487;
/* 481:270 */     cappo.protocol.messages.composers.room.session.RoomReadyComposer.HEADER = 2445;
/* 482:271 */     cappo.protocol.messages.composers.room.session.CloseConnectionComposer.HEADER = 1959;
/* 483:272 */     cappo.protocol.messages.composers.room.session.RoomQueueStatusComposer.HEADER = 2426;
/* 484:273 */     cappo.protocol.messages.composers.room.session.YouAreSpectatorComposer.HEADER = 2416;
/* 485:    */     
/* 486:275 */     cappo.protocol.messages.composers.roomsettings.RoomMuteStateComposer.HEADER = 3126;
/* 487:276 */     cappo.protocol.messages.composers.roomsettings.BannedUsersComposer.HEADER = 2138;
/* 488:277 */     cappo.protocol.messages.composers.roomsettings.RoomBanRemoved.HEADER = 242;
/* 489:278 */     cappo.protocol.messages.composers.roomsettings.FlatControllerAddedComposer.HEADER = 3379;
/* 490:279 */     cappo.protocol.messages.composers.roomsettings.FlatControllerRemovedComposer.HEADER = 2748;
/* 491:280 */     cappo.protocol.messages.composers.roomsettings.FlatControllersComposer.HEADER = 79;
/* 492:281 */     cappo.protocol.messages.composers.roomsettings.RoomSettingsDataComposer.HEADER = 2398;
/* 493:282 */     cappo.protocol.messages.composers.roomsettings.RoomSettingsSavedComposer.HEADER = 10;
/* 494:283 */     cappo.protocol.messages.composers.roomsettings.RoomSettingsErrorComposer.HEADER = 1801;
/* 495:    */     
/* 496:285 */     cappo.protocol.messages.composers.users.UserSettingsComposer.HEADER = 1471;
/* 497:286 */     cappo.protocol.messages.composers.sound.JukeboxSongDisksComposer.HEADER = 2930;
/* 498:287 */     cappo.protocol.messages.composers.sound.TraxSongInfoComposer.HEADER = 834;
/* 499:288 */     cappo.protocol.messages.composers.sound.UserSongDisksInventoryComposer.HEADER = 3594;
/* 500:289 */     cappo.protocol.messages.composers.sound.NowPlayingComposer.HEADER = 3191;
/* 501:290 */     cappo.protocol.messages.composers.sound.JukeboxPlayListFullComposer.HEADER = 1458;
/* 502:    */     
/* 503:292 */     cappo.protocol.messages.composers.talents.TalentTrackComposer.HEADER = 3330;
/* 504:    */     
/* 505:294 */     cappo.protocol.messages.composers.tracking.PingResponseComposer.HEADER = 3773;
/* 506:    */     
/* 507:296 */     cappo.protocol.messages.composers.userdefinedroomevents.OpenWiredComposer.HEADER = 688;
/* 508:297 */     cappo.protocol.messages.composers.userdefinedroomevents.WiredUpdateFailedComposer.HEADER = 1181;
/* 509:298 */     cappo.protocol.messages.composers.userdefinedroomevents.WiredUpdatedComposer.HEADER = 447;
/* 510:299 */     cappo.protocol.messages.composers.userdefinedroomevents.WiredRewardNotificationComposer.HEADER = 634;
/* 511:    */     
/* 512:301 */     cappo.protocol.messages.composers.users.ApproveNameComposer.HEADER = 3606;
/* 513:302 */     cappo.protocol.messages.composers.users.UserTagsComposer.HEADER = 708;
/* 514:303 */     cappo.protocol.messages.composers.users.HabboGroupBadgesComposer.HEADER = 3360;
/* 515:304 */     cappo.protocol.messages.composers.users.IgnoredUsersComposer.HEADER = 2017;
/* 516:305 */     cappo.protocol.messages.composers.users.NotifyUserNameChangeComposer.HEADER = 3985;
/* 517:306 */     cappo.protocol.messages.composers.users.PetRespectedComposer.HEADER = 908;
/* 518:307 */     cappo.protocol.messages.composers.users.RelationshipStatusComposer.HEADER = 3530;
/* 519:308 */     cappo.protocol.messages.composers.users.UserBadgesComposer.HEADER = 1567;
/* 520:309 */     cappo.protocol.messages.composers.users.UserProfileInfoComposer.HEADER = 1011;
/* 521:310 */     cappo.protocol.messages.composers.users.ScrUserInfoComposer.HEADER = 3319;
/* 522:311 */     cappo.protocol.messages.composers.users.UserRespectedComposer.HEADER = 3442;
/* 523:    */     
/* 524:    */ 
/* 525:    */ 
/* 526:    */ 
/* 527:316 */     IncomingMessageEvent.callBacks[3831] = new GetLandingNewsParser();
/* 528:317 */     IncomingMessageEvent.callBacks[2062] = new RefreshLandingViewParser();
/* 529:    */     
/* 530:319 */     IncomingMessageEvent.callBacks[424] = new GetLandingView6Parser();
/* 531:    */     
/* 532:    */ 
/* 533:    */ 
/* 534:    */ 
/* 535:324 */     IncomingMessageEvent.callBacks[2286] = new PutHorseSaddleParser();
/* 536:325 */     IncomingMessageEvent.callBacks[1673] = new HorseMountUpdateParser();
/* 537:326 */     IncomingMessageEvent.callBacks[1897] = new RemoveHorseSaddleParser();
/* 538:327 */     IncomingMessageEvent.callBacks[2523] = new RidingPermissionParser();
/* 539:    */     
/* 540:329 */     IncomingMessageEvent.callBacks[3663] = new GetInterstitialParser();
/* 541:    */     
/* 542:    */ 
/* 543:332 */     IncomingMessageEvent.callBacks[1280] = new ChangeUserNameParser();
/* 544:333 */     IncomingMessageEvent.callBacks[502] = new CheckUserNameParser();
/* 545:334 */     IncomingMessageEvent.callBacks[15] = new GetWardrobeParser();
/* 546:335 */     IncomingMessageEvent.callBacks[1267] = new SaveWardrobeOutfitParser();
/* 547:    */     
/* 548:337 */     IncomingMessageEvent.callBacks[''] = new GetGiftWrappingConfigurationParser();
/* 549:338 */     IncomingMessageEvent.callBacks[1416] = new GetHabboClubExtendOfferParser();
/* 550:339 */     IncomingMessageEvent.callBacks[2984] = new GetSellablePetBreedsParser();
/* 551:    */     
/* 552:341 */     IncomingMessageEvent.callBacks[1512] = new GetIsOfferGiftableParser();
/* 553:342 */     IncomingMessageEvent.callBacks['º'] = new GetClubOffersParser();
/* 554:343 */     IncomingMessageEvent.callBacks[299] = new GetCatalogIndexParser();
/* 555:    */     
/* 556:345 */     IncomingMessageEvent.callBacks[289] = new PurchaseFromCatalogParser();
/* 557:346 */     IncomingMessageEvent.callBacks[1750] = new GetCatalogPageParser();
/* 558:347 */     IncomingMessageEvent.callBacks[3626] = new GetSnowWarTokensParser();
/* 559:348 */     IncomingMessageEvent.callBacks[800] = new GetBundleDynamicDiscountsParser();
/* 560:349 */     IncomingMessageEvent.callBacks[12] = new GetUniqueLimitedItemParser();
/* 561:    */     
/* 562:351 */     IncomingMessageEvent.callBacks[3323] = new SetRelationshipStatusParser();
/* 563:352 */     IncomingMessageEvent.callBacks[3155] = new SendMsgParser();
/* 564:353 */     IncomingMessageEvent.callBacks[520] = new SendRoomInviteParser();
/* 565:354 */     IncomingMessageEvent.callBacks[3731] = new AcceptFriendParser();
/* 566:355 */     IncomingMessageEvent.callBacks[3045] = new DeclineFriendParser();
/* 567:356 */     IncomingMessageEvent.callBacks[3237] = new RequestBuddyParser();
/* 568:357 */     IncomingMessageEvent.callBacks[2989] = new RemoveFriendParser();
/* 569:358 */     IncomingMessageEvent.callBacks[3199] = new HabboSearchParser();
/* 570:359 */     IncomingMessageEvent.callBacks[1749] = new MessengerInitParser();
/* 571:360 */     IncomingMessageEvent.callBacks[3110] = new FriendListUpdateParser();
/* 572:361 */     IncomingMessageEvent.callBacks[1609] = new GetBuddyRequestsParser();
/* 573:362 */     IncomingMessageEvent.callBacks[1066] = new FollowFriendParser();
/* 574:    */     
/* 575:    */ 
/* 576:365 */     IncomingMessageEvent.callBacks[3724] = new SetDutyGuideToolParser();
/* 577:    */     
/* 578:367 */     IncomingMessageEvent.callBacks[569] = new JoinPlayerQueueParser();
/* 579:368 */     IncomingMessageEvent.callBacks[778] = new GetStatusGameParser();
/* 580:369 */     IncomingMessageEvent.callBacks[''] = new GetGameListParser();
/* 581:370 */     IncomingMessageEvent.callBacks[3146] = new GetGameAchievementsParser();
/* 582:    */     
/* 583:372 */     IncomingMessageEvent.callBacks[3001] = new CheckGameDirectoryStatusParser();
/* 584:373 */     IncomingMessageEvent.callBacks[3484] = new GetAccountGameStatusParser();
/* 585:374 */     IncomingMessageEvent.callBacks[1534] = new ExitGameParser();
/* 586:375 */     IncomingMessageEvent.callBacks[1925] = new QuickJoinGameParser();
/* 587:376 */     IncomingMessageEvent.callBacks[1153] = new LeaveGameParser();
/* 588:377 */     IncomingMessageEvent.callBacks[2682] = new GameChatParser();
/* 589:378 */     IncomingMessageEvent.callBacks[2285] = new LoadStageReadyParser();
/* 590:379 */     IncomingMessageEvent.callBacks[422] = new SetUserMoveTargetParser();
/* 591:380 */     IncomingMessageEvent.callBacks[3728] = new RequestFullStatusUpdateParser();
/* 592:381 */     IncomingMessageEvent.callBacks[1296] = new MakeSnowballParser();
/* 593:382 */     IncomingMessageEvent.callBacks[49] = new PlayAgainParser();
/* 594:383 */     IncomingMessageEvent.callBacks[3076] = new ThrowSnowballAtHumanParser();
/* 595:384 */     IncomingMessageEvent.callBacks[66] = new ThrowSnowballAtPositionParser();
/* 596:    */     
/* 597:    */ 
/* 598:    */ 
/* 599:    */ 
/* 600:    */ 
/* 601:    */ 
/* 602:    */ 
/* 603:392 */     IncomingMessageEvent.callBacks[1067] = new InfoRetrieveParser();
/* 604:393 */     IncomingMessageEvent.callBacks[1227] = new PongParser();
/* 605:394 */     IncomingMessageEvent.callBacks[3838] = new InitCryptoParser();
/* 606:395 */     IncomingMessageEvent.callBacks[3395] = new SSOTicketParser();
/* 607:396 */     IncomingMessageEvent.callBacks[71] = new DisconnectParser();
/* 608:397 */     IncomingMessageEvent.callBacks[2294] = new UniqueIDParser();
/* 609:398 */     IncomingMessageEvent.callBacks[1069] = new VersionCheckParser();
/* 610:399 */     IncomingMessageEvent.callBacks[1337] = new GenerateSecretKeyParser();
/* 611:    */     
/* 612:401 */     IncomingMessageEvent.callBacks[2092] = new CallForHelpOpenParser();
/* 613:402 */     IncomingMessageEvent.callBacks[297] = new CallForHelp2Parser();
/* 614:403 */     IncomingMessageEvent.callBacks[894] = new CallForHelpParser();
/* 615:404 */     IncomingMessageEvent.callBacks[671] = new CallForHelpInRoomParser();
/* 616:405 */     IncomingMessageEvent.callBacks[121] = new CallForHelpRoomPanicParser();
/* 617:406 */     IncomingMessageEvent.callBacks[832] = new CallForHelpRoomParser();
/* 618:    */     
/* 619:408 */     IncomingMessageEvent.callBacks[734] = new GetAchievementsParser();
/* 620:    */     
/* 621:410 */     IncomingMessageEvent.callBacks[1022] = new AvatarEffectSelectedParser();
/* 622:411 */     IncomingMessageEvent.callBacks[2604] = new AvatarEffectActivatedParser();
/* 623:    */     
/* 624:413 */     IncomingMessageEvent.callBacks[1039] = new GetBadgesParser();
/* 625:414 */     IncomingMessageEvent.callBacks[1410] = new SetActivatedBadgesParser();
/* 626:415 */     IncomingMessageEvent.callBacks[3108] = new GetBadgePointLimitsParser();
/* 627:    */     
/* 628:417 */     IncomingMessageEvent.callBacks[1648] = new RequestRoomPropertySetParser();
/* 629:418 */     IncomingMessageEvent.callBacks[3415] = new RequestFurniInventoryParser();
/* 630:    */     
/* 631:420 */     IncomingMessageEvent.callBacks[2579] = new RequestPetInventoryParser();
/* 632:    */     
/* 633:422 */     IncomingMessageEvent.callBacks[2450] = new RequestBotInventoryParser();
/* 634:    */     
/* 635:424 */     IncomingMessageEvent.callBacks[1802] = new GetCreditsInfoParser();
/* 636:    */     
/* 637:426 */     IncomingMessageEvent.callBacks[3540] = new OpenTradingParser();
/* 638:427 */     IncomingMessageEvent.callBacks[1005] = new AddItemToTradeParser();
/* 639:428 */     IncomingMessageEvent.callBacks[3020] = new RemoveItemFromTradeParser();
/* 640:429 */     IncomingMessageEvent.callBacks[378] = new ConfirmAcceptTradingParser();
/* 641:430 */     IncomingMessageEvent.callBacks[3374] = new ConfirmDeclineTradingParser();
/* 642:431 */     IncomingMessageEvent.callBacks[3026] = new AcceptTradingParser();
/* 643:432 */     IncomingMessageEvent.callBacks[3282] = new UnacceptTradingParser();
/* 644:433 */     IncomingMessageEvent.callBacks[1178] = new CloseTradingParser();
/* 645:    */     
/* 646:435 */     IncomingMessageEvent.callBacks[3785] = new GetNextLimitedAvailableParser();
/* 647:    */     
/* 648:437 */     IncomingMessageEvent.callBacks[3575] = new GetMarketplaceConfigurationParser();
/* 649:438 */     IncomingMessageEvent.callBacks[980] = new GetMarketplaceCanMakeOfferParser();
/* 650:    */     
/* 651:440 */     IncomingMessageEvent.callBacks[1941] = new GetModeratorUserInfoParser();
/* 652:441 */     IncomingMessageEvent.callBacks[3075] = new GetModeratorRoomInfoParser();
/* 653:442 */     IncomingMessageEvent.callBacks[1623] = new ModeratorActionParser();
/* 654:443 */     IncomingMessageEvent.callBacks[1759] = new ModMessageParser();
/* 655:444 */     IncomingMessageEvent.callBacks[2102] = new ModKickParser();
/* 656:445 */     IncomingMessageEvent.callBacks[1470] = new ModMuteParser();
/* 657:446 */     IncomingMessageEvent.callBacks[1282] = new ModBanParser();
/* 658:447 */     IncomingMessageEvent.callBacks[1658] = new ModerateRoomParser();
/* 659:448 */     IncomingMessageEvent.callBacks[945] = new ModeratorRoomActionParser();
/* 660:449 */     IncomingMessageEvent.callBacks[1478] = new PickIssuesParser();
/* 661:450 */     IncomingMessageEvent.callBacks[''] = new ReleaseIssuesParser();
/* 662:451 */     IncomingMessageEvent.callBacks[2663] = new CloseIssuesParser();
/* 663:    */     
/* 664:453 */     IncomingMessageEvent.callBacks[915] = new AddFavouriteRoomParser();
/* 665:454 */     IncomingMessageEvent.callBacks[3019] = new DeleteFavouriteRoomParser();
/* 666:455 */     IncomingMessageEvent.callBacks[''] = new CreateFlatParser();
/* 667:456 */     IncomingMessageEvent.callBacks[3757] = new RateFlatParser();
/* 668:    */     
/* 669:    */ 
/* 670:    */ 
/* 671:460 */     IncomingMessageEvent.callBacks[1303] = new EditEventParser();
/* 672:461 */     IncomingMessageEvent.callBacks[964] = new GetOfficialRoomsParser();
/* 673:462 */     IncomingMessageEvent.callBacks[2872] = new GetPopularRoomTagsParser();
/* 674:463 */     IncomingMessageEvent.callBacks[1519] = new UpdateNavigatorSettingsParser();
/* 675:464 */     IncomingMessageEvent.callBacks[1480] = new GetGuestRoomParser();
/* 676:465 */     IncomingMessageEvent.callBacks[367] = new CanCreateRoomParser();
/* 677:466 */     IncomingMessageEvent.callBacks[3171] = new PopularRoomsSearchParser();
/* 678:467 */     IncomingMessageEvent.callBacks[604] = new RoomsWithHighestScoreSearchParser();
/* 679:468 */     IncomingMessageEvent.callBacks[2312] = new MyFriendsRoomsSearchParser();
/* 680:469 */     IncomingMessageEvent.callBacks[1586] = new RoomsWhereMyFriendsAreSearchParser();
/* 681:470 */     IncomingMessageEvent.callBacks[3562] = new MyRoomsSearchParser();
/* 682:471 */     IncomingMessageEvent.callBacks[3451] = new MyFavouriteRoomsSearchParser();
/* 683:472 */     IncomingMessageEvent.callBacks[2254] = new MyRoomHistorySearchParser();
/* 684:473 */     IncomingMessageEvent.callBacks[1898] = new RoomTextSearchParser();
/* 685:474 */     IncomingMessageEvent.callBacks[1586] = new RoomTagSearchParser();
/* 686:475 */     IncomingMessageEvent.callBacks[627] = new LatestEventsSearchParser();
/* 687:476 */     IncomingMessageEvent.callBacks[1492] = new GetUserFlatCatsParser();
/* 688:477 */     IncomingMessageEvent.callBacks[902] = new ToggleStaffPickParser();
/* 689:    */     
/* 690:479 */     IncomingMessageEvent.callBacks[1912] = new ResetUnseenItemsParser();
/* 691:    */     
/* 692:481 */     IncomingMessageEvent.callBacks[2780] = new PollStartParser();
/* 693:482 */     IncomingMessageEvent.callBacks[3030] = new PollRejectParser();
/* 694:483 */     IncomingMessageEvent.callBacks[1762] = new PollAnswerParser();
/* 695:    */     
/* 696:    */ 
/* 697:486 */     IncomingMessageEvent.callBacks[1639] = new FriendRequestQuestCompleteParser();
/* 698:    */     
/* 699:488 */     IncomingMessageEvent.callBacks[1529] = new GetRecyclerPrizesParser();
/* 700:    */     
/* 701:    */ 
/* 702:    */ 
/* 703:492 */     IncomingMessageEvent.callBacks[3699] = new UpdateFigureDataParser();
/* 704:    */     
/* 705:494 */     IncomingMessageEvent.callBacks[3175] = new KickUserParser();
/* 706:495 */     IncomingMessageEvent.callBacks[2289] = new BanUserParser();
/* 707:496 */     IncomingMessageEvent.callBacks[1171] = new AssignRightsParser();
/* 708:497 */     IncomingMessageEvent.callBacks[2015] = new RemoveRightsParser();
/* 709:498 */     IncomingMessageEvent.callBacks[1573] = new RemoveAllRightsParser();
/* 710:499 */     IncomingMessageEvent.callBacks[78] = new LetUserInParser();
/* 711:500 */     IncomingMessageEvent.callBacks['Ð'] = new DropCarryObjectParser();
/* 712:501 */     IncomingMessageEvent.callBacks[3766] = new ShareCarryObjectParser();
/* 713:    */     
/* 714:503 */     IncomingMessageEvent.callBacks[''] = new LookToParser();
/* 715:504 */     IncomingMessageEvent.callBacks[2272] = new DanceParser();
/* 716:505 */     IncomingMessageEvent.callBacks[2940] = new SignParser();
/* 717:506 */     IncomingMessageEvent.callBacks[2387] = new ChangePostureParser();
/* 718:507 */     IncomingMessageEvent.callBacks[3835] = new ChangeMottoParser();
/* 719:508 */     IncomingMessageEvent.callBacks[1316] = new SetAvatarExpressionParser();
/* 720:    */     
/* 721:510 */     IncomingMessageEvent.callBacks[3768] = new StartTypingParser();
/* 722:511 */     IncomingMessageEvent.callBacks[2753] = new CancelTypingParser();
/* 723:512 */     IncomingMessageEvent.callBacks[2642] = new ChatParser();
/* 724:513 */     IncomingMessageEvent.callBacks[1388] = new ShoutParser();
/* 725:514 */     IncomingMessageEvent.callBacks[819] = new WhisperParser();
/* 726:    */     
/* 727:516 */     IncomingMessageEvent.callBacks[1737] = new PickupObjectParser();
/* 728:517 */     IncomingMessageEvent.callBacks[2820] = new MoveObjectParser();
/* 729:518 */     IncomingMessageEvent.callBacks[683] = new MoveAvatarParser();
/* 730:519 */     IncomingMessageEvent.callBacks[3691] = new PlaceObjectParser();
/* 731:520 */     IncomingMessageEvent.callBacks[2061] = new MoveWallItemParser();
/* 732:521 */     IncomingMessageEvent.callBacks['ë'] = new GetRoomEntryDataParser();
/* 733:522 */     IncomingMessageEvent.callBacks[1232] = new UseFurnitureParser();
/* 734:523 */     IncomingMessageEvent.callBacks[3353] = new UseWallItemParser();
/* 735:524 */     IncomingMessageEvent.callBacks[2051] = new SetClothingChangeDataParser();
/* 736:525 */     IncomingMessageEvent.callBacks[2045] = new PlacePetParser();
/* 737:526 */     IncomingMessageEvent.callBacks[3438] = new RemovePetFromFlatParser();
/* 738:527 */     IncomingMessageEvent.callBacks[1254] = new GetPetCommandsParser();
/* 739:528 */     IncomingMessageEvent.callBacks[3168] = new GetRoomCampaignAdsParser();
/* 740:529 */     IncomingMessageEvent.callBacks[1677] = new PlaceRentalBotParser();
/* 741:530 */     IncomingMessageEvent.callBacks[2729] = new RemoveBotFromFlatParser();
/* 742:531 */     IncomingMessageEvent.callBacks[478] = new GetRoomCompetitionParser();
/* 743:532 */     IncomingMessageEvent.callBacks[2475] = new ObjectSaveStuffDataParser();
/* 744:    */     
/* 745:    */ 
/* 746:535 */     IncomingMessageEvent.callBacks[3576] = new RoomDimmerGetPresetsParser();
/* 747:536 */     IncomingMessageEvent.callBacks[2215] = new RoomDimmerSavePresetParser();
/* 748:537 */     IncomingMessageEvent.callBacks[596] = new RoomDimmerChangeStateParser();
/* 749:    */     
/* 750:539 */     IncomingMessageEvent.callBacks[3908] = new PlacePostItParser();
/* 751:540 */     IncomingMessageEvent.callBacks[757] = new OpenPostItParser();
/* 752:    */     
/* 753:542 */     IncomingMessageEvent.callBacks[2676] = new AddSpamWallPostIt2Parser();
/* 754:543 */     IncomingMessageEvent.callBacks[5] = new CreditFurniRedeemParser();
/* 755:544 */     IncomingMessageEvent.callBacks[2418] = new ThrowDiceParser();
/* 756:545 */     IncomingMessageEvent.callBacks[995] = new DiceOffParser();
/* 757:546 */     IncomingMessageEvent.callBacks[757] = new SpinWheelOfFortuneParser();
/* 758:547 */     IncomingMessageEvent.callBacks[3934] = new SetOutfitNameParser();
/* 759:548 */     IncomingMessageEvent.callBacks[3358] = new UpdateOutfitParser();
/* 760:    */     
/* 761:550 */     IncomingMessageEvent.callBacks[375] = new GetPetInfoParser();
/* 762:551 */     IncomingMessageEvent.callBacks[1784] = new RespectPetParser();
/* 763:    */     
/* 764:553 */     IncomingMessageEvent.callBacks[2521] = new SetBotSkillParser();
/* 765:554 */     IncomingMessageEvent.callBacks[950] = new RequestBotSkillParser();
/* 766:    */     
/* 767:556 */     IncomingMessageEvent.callBacks[2749] = new OpenFlatConnectionParser();
/* 768:    */     
/* 769:558 */     IncomingMessageEvent.callBacks[1317] = new GoToFlatParser();
/* 770:559 */     IncomingMessageEvent.callBacks[2320] = new ChangeQueueParser();
/* 771:560 */     IncomingMessageEvent.callBacks[3281] = new QuitParser();
/* 772:    */     
/* 773:562 */     IncomingMessageEvent.callBacks[3519] = new SetRoomMuteStateParser();
/* 774:563 */     IncomingMessageEvent.callBacks[1894] = new DeleteRoomParser();
/* 775:564 */     IncomingMessageEvent.callBacks[2825] = new GetRoomSettingsParser();
/* 776:565 */     IncomingMessageEvent.callBacks[19] = new SaveRoomSettingsMessageEvent();
/* 777:566 */     IncomingMessageEvent.callBacks[651] = new GetFlatControllersParser();
/* 778:567 */     IncomingMessageEvent.callBacks[3572] = new GetBannedUsersParser();
/* 779:    */     
/* 780:569 */     IncomingMessageEvent.callBacks[3697] = new GetNowPlayingParser();
/* 781:570 */     IncomingMessageEvent.callBacks[2268] = new AddJukeboxDiskParser();
/* 782:571 */     IncomingMessageEvent.callBacks[943] = new RemoveJukeboxDiskParser();
/* 783:572 */     IncomingMessageEvent.callBacks[267] = new GetUserSongDisksParser();
/* 784:573 */     IncomingMessageEvent.callBacks[1524] = new GetJukeboxPlayListParser();
/* 785:574 */     IncomingMessageEvent.callBacks[1257] = new GetSongInfoParser();
/* 786:575 */     IncomingMessageEvent.callBacks[1223] = new GetUserSettingsParser();
/* 787:576 */     IncomingMessageEvent.callBacks[2273] = new SetSoundSettingsParser();
/* 788:    */     
/* 789:578 */     IncomingMessageEvent.callBacks[1676] = new GetTalentTrackParser();
/* 790:    */     
/* 791:580 */     IncomingMessageEvent.callBacks[1617] = new LatencyPingRequestParser();
/* 792:581 */     IncomingMessageEvent.callBacks[418] = new LatencyPingReportParser();
/* 793:582 */     IncomingMessageEvent.callBacks[3998] = new PerformanceLogParser();
/* 794:583 */     IncomingMessageEvent.callBacks[3695] = new EventLogParser();
/* 795:    */     
/* 796:585 */     IncomingMessageEvent.callBacks[411] = new UpdateTriggerParser();
/* 797:586 */     IncomingMessageEvent.callBacks[1448] = new UpdateActionParser();
/* 798:587 */     IncomingMessageEvent.callBacks[1604] = new UpdateConditionParser();
/* 799:588 */     IncomingMessageEvent.callBacks[2448] = new OpenParser();
/* 800:    */     
/* 801:590 */     IncomingMessageEvent.callBacks[1991] = new ScrGetUserInfoParser();
/* 802:591 */     IncomingMessageEvent.callBacks[2889] = new GetExtendedProfileParser();
/* 803:592 */     IncomingMessageEvent.callBacks[1310] = new ApproveNameParser();
/* 804:593 */     IncomingMessageEvent.callBacks[2869] = new GetUserTagsParser();
/* 805:594 */     IncomingMessageEvent.callBacks[901] = new GetIgnoredUsersParser();
/* 806:595 */     IncomingMessageEvent.callBacks[3577] = new GetRelationshipStatusParser();
/* 807:596 */     IncomingMessageEvent.callBacks[2178] = new GetSelectedBadgesParser();
/* 808:597 */     IncomingMessageEvent.callBacks[3800] = new RespectUserParser();
/* 809:598 */     IncomingMessageEvent.callBacks[3823] = new GetUserNotificationsParser();
/* 810:599 */     IncomingMessageEvent.callBacks[2823] = new GetHabboGroupBadgesParser();
/* 811:    */     
/* 812:    */ 
/* 813:    */ 
/* 814:    */ 
/* 815:    */ 
/* 816:605 */     cappo.protocol.messages.composers.room.chat.ChatSettingsComposer.HEADER = 2165;
/* 817:606 */     cappo.protocol.messages.composers.notifications.PetReceivedMessageComposer.HEADER = 2928;
/* 818:607 */     IncomingMessageEvent.callBacks[2435] = new SetUserChatSettingMessageEvent();
/* 819:    */   }
/* 820:    */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.OldOpCodes
 * JD-Core Version:    0.7.0.1
 */