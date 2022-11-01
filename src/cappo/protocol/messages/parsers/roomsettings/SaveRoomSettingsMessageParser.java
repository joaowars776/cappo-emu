/*   1:    */ package cappo.protocol.messages.parsers.roomsettings;
/*   2:    */ 
/*   3:    */ import cappo.engine.network.MessageReader;
/*   4:    */ import cappo.engine.player.Connection;
/*   5:    */ import cappo.engine.threadpools.RoomTask;
/*   6:    */ import cappo.game.roomengine.RoomData;
/*   7:    */ import cappo.game.roomengine.RoomManager;
/*   8:    */ import cappo.game.roomengine.entity.live.Avatar;
/*   9:    */ import cappo.game.roomengine.settings.ChatSettings;
/*  10:    */ import cappo.game.roomengine.settings.ControllerLevels;
/*  11:    */ 
/*  12:    */ public class SaveRoomSettingsMessageParser
/*  13:    */ {
/*  14:    */   public boolean isValid;
/*  15:    */   private RoomData roomData;
/*  16:    */   private String roomName;
/*  17:    */   private String roomDesc;
/*  18:    */   private int roomState;
/*  19:    */   private String roomPassword;
/*  20:    */   private int roomMaxUsers;
/*  21:    */   private int roomCategory;
/*  22:    */   private String[] roomTags;
/*  23:    */   private int roomTrading;
/*  24:    */   private boolean roomAllowPets;
/*  25:    */   private boolean roomAllowPetsEat;
/*  26:    */   private boolean roomAllowWalkthrough;
/*  27:    */   private boolean roomHideWall;
/*  28:    */   private int roomWallAnchor;
/*  29:    */   private int roomFloorAnchor;
/*  30:    */   private int roomMute;
/*  31:    */   private int roomKick;
/*  32:    */   private int roomBan;
/*  33:    */   private int chatMode;
/*  34:    */   private int chatBubbleWidth;
/*  35:    */   private int chatScrollSpeed;
/*  36:    */   public boolean roomVisualizationChanged;
/*  37:    */   public boolean roomChatChanged;
/*  38:    */   
/*  39:    */   public SaveRoomSettingsMessageParser(MessageReader reader, Connection cn)
/*  40:    */   {
/*  41: 41 */     int roomId = reader.readInt();
/*  42: 42 */     if (cn.avatar == null)
/*  43:    */     {
/*  44: 43 */       this.roomData = RoomManager.getRoom(roomId);
/*  45: 44 */       if (this.roomData == null) {
/*  46: 45 */         return;
/*  47:    */       }
/*  48: 48 */       int controllerLevel = ControllerLevels.getLevel(cn.playerData, this.roomData, this.roomData.room);
/*  49: 49 */       if (controllerLevel >= 4) {}
/*  50:    */     }
/*  51:    */     else
/*  52:    */     {
/*  53: 53 */       this.roomData = cn.avatar.room.roomData;
/*  54: 55 */       if (cn.avatar.controllerLevel < 4) {
/*  55: 56 */         return;
/*  56:    */       }
/*  57:    */     }
/*  58: 60 */     this.roomName = reader.readString();
/*  59: 61 */     this.roomDesc = reader.readString();
/*  60: 62 */     this.roomState = reader.readInt();
/*  61: 63 */     this.roomPassword = reader.readString();
/*  62: 64 */     this.roomMaxUsers = reader.readInt();
/*  63: 65 */     this.roomCategory = reader.readInt();
/*  64:    */     
/*  65: 67 */     int tagSize = reader.readInt();
/*  66: 68 */     this.roomTags = new String[tagSize];
/*  67: 69 */     for (int i = 0; i < tagSize; i++) {
/*  68: 70 */       this.roomTags[i] = reader.readString().toLowerCase();
/*  69:    */     }
/*  70: 73 */     this.roomTrading = reader.readInt();
/*  71:    */     
/*  72: 75 */     this.roomAllowPets = reader.readBoolean();
/*  73: 76 */     this.roomAllowPetsEat = reader.readBoolean();
/*  74: 77 */     this.roomAllowWalkthrough = reader.readBoolean();
/*  75:    */     
/*  76: 79 */     this.roomHideWall = reader.readBoolean();
/*  77: 80 */     this.roomWallAnchor = reader.readInt();
/*  78: 81 */     this.roomFloorAnchor = reader.readInt();
/*  79:    */     
/*  80: 83 */     this.roomMute = reader.readInt();
/*  81: 84 */     this.roomKick = reader.readInt();
/*  82: 85 */     this.roomBan = reader.readInt();
/*  83:    */     
/*  84: 87 */     this.chatMode = reader.readInt();
/*  85: 88 */     this.chatBubbleWidth = reader.readInt();
/*  86: 89 */     this.chatScrollSpeed = reader.readInt();
/*  87:    */     
/*  88: 91 */     this.isValid = true;
/*  89:    */   }
/*  90:    */   
/*  91:    */   public RoomData getRoomData()
/*  92:    */   {
/*  93: 95 */     return this.roomData;
/*  94:    */   }
/*  95:    */   
/*  96:    */   public void setRoomName(int minLen)
/*  97:    */   {
/*  98: 99 */     if (this.roomName.length() > minLen) {
/*  99:100 */       this.roomData.name = this.roomName;
/* 100:    */     }
/* 101:    */   }
/* 102:    */   
/* 103:    */   public void setRoomDesc()
/* 104:    */   {
/* 105:105 */     this.roomData.description = this.roomDesc;
/* 106:    */   }
/* 107:    */   
/* 108:    */   public void setRoomState(int moreThan, int lessThan)
/* 109:    */   {
/* 110:109 */     if ((this.roomState > moreThan) && (this.roomState < lessThan)) {
/* 111:110 */       this.roomData.state = this.roomState;
/* 112:    */     }
/* 113:    */   }
/* 114:    */   
/* 115:    */   public void setRoomPassword()
/* 116:    */   {
/* 117:115 */     this.roomData.password = this.roomPassword;
/* 118:    */   }
/* 119:    */   
/* 120:    */   public void setRoomMaxUsers(int min, int max, int mod)
/* 121:    */   {
/* 122:119 */     if ((this.roomMaxUsers > max) || (this.roomMaxUsers < min)) {
/* 123:120 */       return;
/* 124:    */     }
/* 125:123 */     if (this.roomMaxUsers % mod != 0) {
/* 126:124 */       return;
/* 127:    */     }
/* 128:127 */     this.roomData.updateMaxUsers(this.roomMaxUsers);
/* 129:    */   }
/* 130:    */   
/* 131:    */   public void setRoomCategory()
/* 132:    */   {
/* 133:131 */     this.roomData.category = this.roomCategory;
/* 134:    */   }
/* 135:    */   
/* 136:    */   public void setRoomTags(int maxCount, int maxLen)
/* 137:    */   {
/* 138:135 */     int tagCount = this.roomTags.length;
/* 139:136 */     if (tagCount > maxCount) {
/* 140:137 */       return;
/* 141:    */     }
/* 142:140 */     for (String tag : this.roomTags)
/* 143:    */     {
/* 144:141 */       int len = tag.length();
/* 145:142 */       if (len > maxLen) {
/* 146:143 */         return;
/* 147:    */       }
/* 148:    */     }
/* 149:147 */     for (String tag : this.roomTags) {
/* 150:148 */       RoomManager.AddTag(tag);
/* 151:    */     }
/* 152:151 */     for (String tag : this.roomData.tags) {
/* 153:152 */       RoomManager.RemoveTag(tag);
/* 154:    */     }
/* 155:155 */     this.roomData.tags = this.roomTags;
/* 156:    */   }
/* 157:    */   
/* 158:    */   public void setRoomTrading(int minVal, int maxVal)
/* 159:    */   {
/* 160:159 */     if ((this.roomTrading < minVal) || (this.roomTrading > maxVal)) {
/* 161:160 */       return;
/* 162:    */     }
/* 163:162 */     this.roomData.tradingSettings.permissions = this.roomTrading;
/* 164:    */   }
/* 165:    */   
/* 166:    */   public void setRoomOthersSettings()
/* 167:    */   {
/* 168:166 */     this.roomData.setFlag(2, this.roomAllowPets);
/* 169:167 */     this.roomData.setFlag(4, this.roomAllowPetsEat);
/* 170:168 */     this.roomData.setFlag(8, this.roomAllowWalkthrough);
/* 171:    */   }
/* 172:    */   
/* 173:    */   public void setRoomVisualizationSettings()
/* 174:    */   {
/* 175:172 */     if (this.roomData.haveFlag(16) != this.roomHideWall)
/* 176:    */     {
/* 177:173 */       this.roomData.setFlag(16, this.roomHideWall);
/* 178:174 */       this.roomVisualizationChanged = true;
/* 179:    */     }
/* 180:177 */     if (this.roomData.wallAnchor != this.roomWallAnchor)
/* 181:    */     {
/* 182:178 */       this.roomData.wallAnchor = this.roomWallAnchor;
/* 183:179 */       this.roomVisualizationChanged = true;
/* 184:    */     }
/* 185:182 */     if (this.roomData.floorAnchor != this.roomFloorAnchor)
/* 186:    */     {
/* 187:183 */       this.roomData.floorAnchor = this.roomFloorAnchor;
/* 188:184 */       this.roomVisualizationChanged = true;
/* 189:    */     }
/* 190:    */   }
/* 191:    */   
/* 192:    */   public void setRoomModPermissionsSettings()
/* 193:    */   {
/* 194:189 */     if ((this.roomMute < 0) || 
/* 195:190 */       (this.roomMute > 1)) {
/* 196:191 */       return;
/* 197:    */     }
/* 198:194 */     if ((this.roomKick < 0) || 
/* 199:195 */       (this.roomKick > 2)) {
/* 200:196 */       return;
/* 201:    */     }
/* 202:199 */     if ((this.roomBan < 0) || 
/* 203:200 */       (this.roomBan > 1)) {
/* 204:201 */       return;
/* 205:    */     }
/* 206:204 */     this.roomData.modPermissions.permissionsMute = this.roomMute;
/* 207:205 */     this.roomData.modPermissions.permissionsKick = this.roomKick;
/* 208:206 */     this.roomData.modPermissions.permissionsBan = this.roomBan;
/* 209:    */   }
/* 210:    */   
/* 211:    */   public void setRoomChatSettings()
/* 212:    */   {
/* 213:210 */     if ((this.chatMode < 0) || 
/* 214:211 */       (this.chatMode > 1)) {
/* 215:212 */       return;
/* 216:    */     }
/* 217:215 */     if ((this.chatBubbleWidth < 0) || 
/* 218:216 */       (this.chatBubbleWidth > 2)) {
/* 219:217 */       return;
/* 220:    */     }
/* 221:220 */     if ((this.chatScrollSpeed < 0) || 
/* 222:221 */       (this.chatScrollSpeed > 2)) {
/* 223:222 */       return;
/* 224:    */     }
/* 225:225 */     if (this.roomData.chatSettings.chatMode != this.chatMode)
/* 226:    */     {
/* 227:226 */       this.roomData.chatSettings.chatMode = this.chatMode;
/* 228:227 */       this.roomChatChanged = true;
/* 229:    */     }
/* 230:230 */     if (this.roomData.chatSettings.chatBubbleWidth != this.chatBubbleWidth)
/* 231:    */     {
/* 232:231 */       this.roomData.chatSettings.chatBubbleWidth = this.chatBubbleWidth;
/* 233:232 */       this.roomChatChanged = true;
/* 234:    */     }
/* 235:235 */     if (this.roomData.chatSettings.chatScrollSpeed != this.chatScrollSpeed)
/* 236:    */     {
/* 237:236 */       this.roomData.chatSettings.chatScrollSpeed = this.chatScrollSpeed;
/* 238:237 */       this.roomChatChanged = true;
/* 239:    */     }
/* 240:    */   }
/* 241:    */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.parsers.roomsettings.SaveRoomSettingsMessageParser
 * JD-Core Version:    0.7.0.1
 */