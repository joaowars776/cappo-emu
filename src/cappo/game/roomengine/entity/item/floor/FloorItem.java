/*   1:    */ package cappo.game.roomengine.entity.item.floor;
/*   2:    */ 
/*   3:    */ import cappo.engine.database.Database;
/*   4:    */ import cappo.engine.logging.Log;
/*   5:    */ import cappo.engine.player.Connection;
/*   6:    */ import cappo.engine.threadpools.RoomTask;
/*   7:    */ import cappo.game.collections.BaseItem;
/*   8:    */ import cappo.game.collections.BaseItem.FurniLogic;
/*   9:    */ import cappo.game.collections.BaseItem.ItemType;
/*  10:    */ import cappo.game.collections.BflyData;
/*  11:    */ import cappo.game.games.snowwar.Direction8;
/*  12:    */ import cappo.game.player.PlayerData;
/*  13:    */ import cappo.game.roomengine.SquareFlagManager;
/*  14:    */ import cappo.game.roomengine.entity.item.Item;
/*  15:    */ import cappo.game.roomengine.entity.item.RoomItemData;
/*  16:    */ import cappo.game.roomengine.entity.item.extradata.CrackableExtraData;
/*  17:    */ import cappo.game.roomengine.entity.item.extradata.ExtraData1;
/*  18:    */ import cappo.game.roomengine.entity.item.extradata.ExtraDataBase;
/*  19:    */ import cappo.game.roomengine.entity.item.extradata.IntArrayStuffData;
/*  20:    */ import cappo.game.roomengine.entity.item.extradata.MapStuffData;
/*  21:    */ import cappo.game.roomengine.entity.item.extradata.StringArrayStuffData;
/*  22:    */ import cappo.game.roomengine.entity.item.extradata.StringStuffData;
/*  23:    */ import cappo.game.roomengine.entity.item.extradata.StuffDataReader;
/*  24:    */ import cappo.game.roomengine.entity.item.floor.wired.AtachedWireds;
/*  25:    */ import cappo.game.roomengine.entity.item.floor.wired.WiredItemBase;
/*  26:    */ import cappo.game.roomengine.entity.item.floor.wired.condition.FurniHasUser;
/*  27:    */ import cappo.game.roomengine.entity.item.floor.wired.effect.GiveReward;
/*  28:    */ import cappo.game.roomengine.entity.item.floor.wired.effect.MoveRotateItemAction;
/*  29:    */ import cappo.game.roomengine.entity.item.floor.wired.effect.ShowMessageAction;
/*  30:    */ import cappo.game.roomengine.entity.item.floor.wired.effect.TeleportToItemAction;
/*  31:    */ import cappo.game.roomengine.entity.item.floor.wired.effect.ToggleItemStateAction;
/*  32:    */ import cappo.game.roomengine.entity.item.floor.wired.trigger.GameEndsTrigger;
/*  33:    */ import cappo.game.roomengine.entity.item.floor.wired.trigger.GameStartsTrigger;
/*  34:    */ import cappo.game.roomengine.entity.item.floor.wired.trigger.RepeatTrigger;
/*  35:    */ import cappo.game.roomengine.entity.item.floor.wired.trigger.TimerResetTrigger;
/*  36:    */ import cappo.game.roomengine.entity.item.floor.wired.trigger.UserEntersRoomTrigger;
/*  37:    */ import cappo.game.roomengine.entity.item.floor.wired.trigger.UserSaysPhraseTrigger;
/*  38:    */ import cappo.game.roomengine.entity.item.floor.wired.trigger.UserStepsOffItemTrigger;
/*  39:    */ import cappo.game.roomengine.entity.item.floor.wired.trigger.UserStepsOnItemTrigger;
/*  40:    */ import cappo.game.roomengine.entity.item.floor.wired.trigger.UserUsesItemTrigger;
/*  41:    */ import cappo.game.roomengine.itemInteractor.Interactor;
/*  42:    */ import cappo.game.roomengine.itemInteractor.Interactor.InteractorType;
/*  43:    */ import java.util.List;
/*  44:    */ 
/*  45:    */ public abstract class FloorItem
/*  46:    */   extends Item
/*  47:    */ {
/*  48:    */   private int extraParam;
/*  49:    */   private RoomFloorItemData data;
/*  50:    */   private AtachedWireds attachedWireds;
/*  51:    */   
/*  52:    */   public static FloorItem createItem(PlayerData owner, int ref, int Id, StuffDataReader data, int extraparam, BaseItem baseItem)
/*  53:    */   {
/*  54: 57 */     FloorItem userItem = null;
/*  55: 59 */     if (baseItem.logic == BaseItem.FurniLogic.CRACKABLE)
/*  56:    */     {
/*  57: 60 */       userItem = new CrackeableItem();
/*  58: 61 */       if (data.type != 7)
/*  59:    */       {
/*  60: 62 */         Log.printLog("BAD StuffDataReader = " + data.type + " - Logic = " + baseItem.logic);
/*  61: 63 */         return null;
/*  62:    */       }
/*  63:    */     }
/*  64: 65 */     else if (baseItem.interactorType == Interactor.InteractorType.walkeablechange)
/*  65:    */     {
/*  66: 66 */       userItem = new WalkeableChangeItem();
/*  67:    */     }
/*  68: 67 */     else if (baseItem.interactorType == Interactor.InteractorType.roller)
/*  69:    */     {
/*  70: 68 */       userItem = new RollerItem();
/*  71:    */     }
/*  72: 69 */     else if (baseItem.interactorType == Interactor.InteractorType.gift)
/*  73:    */     {
/*  74: 70 */       userItem = new PresentItem();
/*  75:    */     }
/*  76: 71 */     else if (baseItem.logic == BaseItem.FurniLogic.MANNEQUIN)
/*  77:    */     {
/*  78: 72 */       userItem = new OutFitItem();
/*  79: 73 */       if (data.type != 1)
/*  80:    */       {
/*  81: 74 */         Log.printLog("BAD StuffDataReader = " + data.type + " - Logic = " + baseItem.logic);
/*  82: 75 */         return null;
/*  83:    */       }
/*  84:    */     }
/*  85: 77 */     else if (baseItem.itemCategory == 8)
/*  86:    */     {
/*  87: 78 */       userItem = new SongItem();
/*  88:    */     }
/*  89: 79 */     else if (baseItem.interactor == Interactor.iterWired)
/*  90:    */     {
/*  91: 80 */       if (baseItem.itemType == BaseItem.ItemType.WIRED_CONDITION)
/*  92:    */       {
/*  93: 81 */         if (baseItem.interactorType == Interactor.InteractorType.conditionfurnishaveusers) {
/*  94: 82 */           userItem = new FurniHasUser();
/*  95:    */         }
/*  96:    */       }
/*  97: 84 */       else if (baseItem.itemType == BaseItem.ItemType.WIRED_EFFECT)
/*  98:    */       {
/*  99: 85 */         if (baseItem.interactorType == Interactor.InteractorType.actionshowmessage) {
/* 100: 86 */           userItem = new ShowMessageAction();
/* 101: 87 */         } else if (baseItem.interactorType == Interactor.InteractorType.actionteleportto) {
/* 102: 88 */           userItem = new TeleportToItemAction();
/* 103: 89 */         } else if (baseItem.interactorType == Interactor.InteractorType.actionmoverotate) {
/* 104: 90 */           userItem = new MoveRotateItemAction();
/* 105: 91 */         } else if (baseItem.interactorType == Interactor.InteractorType.actiontogglestate) {
/* 106: 92 */           userItem = new ToggleItemStateAction();
/* 107: 93 */         } else if (baseItem.interactorType == Interactor.InteractorType.actiongivereward) {
/* 108: 94 */           userItem = new GiveReward();
/* 109:    */         }
/* 110:    */       }
/* 111: 97 */       else if (baseItem.itemType == BaseItem.ItemType.WIRED_TRIGGER) {
/* 112: 98 */         if (baseItem.interactorType == Interactor.InteractorType.triggerroomenter) {
/* 113: 99 */           userItem = new UserEntersRoomTrigger();
/* 114:100 */         } else if (baseItem.interactorType == Interactor.InteractorType.triggergameend) {
/* 115:101 */           userItem = new GameEndsTrigger();
/* 116:102 */         } else if (baseItem.interactorType == Interactor.InteractorType.triggergamestart) {
/* 117:103 */           userItem = new GameStartsTrigger();
/* 118:104 */         } else if (baseItem.interactorType == Interactor.InteractorType.triggerstatechanged) {
/* 119:105 */           userItem = new UserUsesItemTrigger();
/* 120:106 */         } else if (baseItem.interactorType == Interactor.InteractorType.triggeronusersay) {
/* 121:107 */           userItem = new UserSaysPhraseTrigger();
/* 122:108 */         } else if (baseItem.interactorType == Interactor.InteractorType.triggerwalkofffurni) {
/* 123:109 */           userItem = new UserStepsOffItemTrigger();
/* 124:110 */         } else if (baseItem.interactorType == Interactor.InteractorType.triggerwalkonfurni) {
/* 125:111 */           userItem = new UserStepsOnItemTrigger();
/* 126:112 */         } else if (baseItem.interactorType == Interactor.InteractorType.triggerrepeater) {
/* 127:113 */           userItem = new RepeatTrigger();
/* 128:114 */         } else if (baseItem.interactorType == Interactor.InteractorType.triggertimer) {
/* 129:115 */           userItem = new TimerResetTrigger();
/* 130:    */         }
/* 131:    */       }
/* 132:119 */       if (userItem == null) {
/* 133:120 */         Log.printLog("UnRegistered Wired Type: " + baseItem.interactorType);
/* 134:    */       }
/* 135:    */     }
/* 136:124 */     if (userItem == null) {
/* 137:125 */       userItem = new GenericFloorItem();
/* 138:    */     }
/* 139:128 */     userItem.refId = ref;
/* 140:129 */     userItem.itemId = Id;
/* 141:130 */     userItem.baseItem = baseItem;
/* 142:131 */     userItem.owner = owner;
/* 143:    */     
/* 144:133 */     userItem.setExtraParam(extraparam);
/* 145:135 */     if (data.type == 0)
/* 146:    */     {
/* 147:136 */       StringStuffData stuffdata = new StringStuffData(data);
/* 148:137 */       userItem.extraData = stuffdata;
/* 149:    */       try
/* 150:    */       {
/* 151:139 */         GenericFloorItem floor = (GenericFloorItem)userItem;
/* 152:140 */         floor.setIntData(Integer.parseInt(stuffdata.extraData));
/* 153:    */       }
/* 154:    */       catch (Exception localException) {}
/* 155:    */     }
/* 156:145 */     else if (data.type == 1)
/* 157:    */     {
/* 158:146 */       userItem.extraData = new MapStuffData(data);
/* 159:148 */       if (baseItem.logic == BaseItem.FurniLogic.MANNEQUIN)
/* 160:    */       {
/* 161:149 */         OutFitItem outfit = (OutFitItem)userItem;
/* 162:150 */         outfit.getAvatarLook();
/* 163:    */       }
/* 164:    */     }
/* 165:152 */     else if (data.type == 2)
/* 166:    */     {
/* 167:153 */       userItem.extraData = new StringArrayStuffData(data);
/* 168:    */     }
/* 169:154 */     else if (data.type == 3)
/* 170:    */     {
/* 171:155 */       userItem.extraData = new ExtraData1(data);
/* 172:    */     }
/* 173:158 */     else if (data.type == 5)
/* 174:    */     {
/* 175:159 */       userItem.extraData = new IntArrayStuffData(data);
/* 176:    */     }
/* 177:162 */     else if (data.type == 7)
/* 178:    */     {
/* 179:163 */       userItem.extraData = new CrackableExtraData(data);
/* 180:    */     }
/* 181:    */     else
/* 182:    */     {
/* 183:165 */       Log.printLog("BAD EXTRATYPE = " + data.type + " - BASEID = " + baseItem.Id);
/* 184:166 */       return null;
/* 185:    */     }
/* 186:169 */     return userItem;
/* 187:    */   }
/* 188:    */   
/* 189:    */   public void addAttachedWired(WiredItemBase wired)
/* 190:    */   {
/* 191:173 */     if (this.attachedWireds == null) {
/* 192:174 */       this.attachedWireds = new AtachedWireds(wired);
/* 193:    */     } else {
/* 194:176 */       this.attachedWireds.addWired(wired);
/* 195:    */     }
/* 196:    */   }
/* 197:    */   
/* 198:    */   public void removeAttachedWired(int itemId)
/* 199:    */   {
/* 200:181 */     if (this.attachedWireds != null) {
/* 201:182 */       this.attachedWireds.removeWired(itemId);
/* 202:    */     }
/* 203:    */   }
/* 204:    */   
/* 205:    */   public void itemMoved(int prevXy, Direction8 prevDir)
/* 206:    */   {
/* 207:187 */     if (this.attachedWireds != null) {
/* 208:188 */       this.attachedWireds.itemMoved(this, prevXy, prevDir);
/* 209:    */     }
/* 210:    */   }
/* 211:    */   
/* 212:    */   public void itemPick(int prevXy, Direction8 prevDir)
/* 213:    */   {
/* 214:193 */     if (this.attachedWireds != null)
/* 215:    */     {
/* 216:194 */       this.attachedWireds.itemPicked(this, prevXy, prevDir);
/* 217:195 */       this.attachedWireds = null;
/* 218:    */     }
/* 219:198 */     cleanRoomData();
/* 220:    */   }
/* 221:    */   
/* 222:    */   public void setRoomData(RoomItemData dat)
/* 223:    */   {
/* 224:203 */     this.data = ((RoomFloorItemData)dat);
/* 225:    */   }
/* 226:    */   
/* 227:    */   public void cleanRoomData()
/* 228:    */   {
/* 229:208 */     this.data = null;
/* 230:    */   }
/* 231:    */   
/* 232:    */   public int getRoomId()
/* 233:    */   {
/* 234:213 */     if (this.data == null) {
/* 235:214 */       return 0;
/* 236:    */     }
/* 237:216 */     return this.data.getRoomId();
/* 238:    */   }
/* 239:    */   
/* 240:    */   public void insertItem()
/* 241:    */     throws Exception
/* 242:    */   {
/* 243:221 */     int roomId = getRoomId();
/* 244:    */     
/* 245:223 */     byte[] data = this.extraData.data();
/* 246:224 */     if (data == null) {
/* 247:225 */       Database.exec("INSERT IGNORE INTO furnis(id,userid,baseid,roomid,data)VALUES(" + this.itemId + "," + this.owner.userId + "," + this.baseItem.Id + "," + roomId + ",NULL);", new Object[0]);
/* 248:    */     } else {
/* 249:227 */       Database.exec("INSERT IGNORE INTO furnis(id,userid,baseid,roomid,data)VALUES(" + this.itemId + "," + this.owner.userId + "," + this.baseItem.Id + "," + roomId + ",?);", new Object[] { data });
/* 250:    */     }
/* 251:230 */     if (roomId > 0) {
/* 252:231 */       Database.exec("INSERT IGNORE INTO furnis_roomdata(id,a,b,r)VALUES(" + this.itemId + "," + BflyData.Combine(getX(), getY()) + "," + getZ() + "," + getDir().getRot() + ");", new Object[0]);
/* 253:    */     }
/* 254:234 */     if (this.extraParam > 0) {
/* 255:235 */       Database.exec("INSERT IGNORE INTO furnis_floorextra(id,param)VALUES(" + this.itemId + "," + this.extraParam + ");", new Object[0]);
/* 256:    */     }
/* 257:    */   }
/* 258:    */   
/* 259:    */   public void roomDataSave(boolean moved)
/* 260:    */     throws Exception
/* 261:    */   {
/* 262:241 */     if (moved)
/* 263:    */     {
/* 264:242 */       float a = BflyData.Combine(getX(), getY());
/* 265:243 */       float b = getZ();
/* 266:244 */       int r = getDir().getRot();
/* 267:245 */       Database.exec("INSERT INTO furnis_roomdata(id,a,b,r)VALUES(" + this.itemId + "," + a + "," + b + "," + r + ") on DUPLICATE KEY UPDATE `a`='" + a + "',`b`='" + b + "',`r`='" + r + "';", new Object[0]);
/* 268:247 */       if ((this instanceof SongItem)) {
/* 269:249 */         Database.exec("INSERT IGNORE INTO room_discs (roomid,songid,itemid)VALUES(" + getRoomId() + "," + getExtraParam() + "," + this.itemId + ");", new Object[0]);
/* 270:    */       }
/* 271:    */     }
/* 272:    */     else
/* 273:    */     {
/* 274:253 */       this.data.save();
/* 275:    */     }
/* 276:    */   }
/* 277:    */   
/* 278:    */   public byte[] SquareInFront()
/* 279:    */   {
/* 280:258 */     return this.data.SquareInFront();
/* 281:    */   }
/* 282:    */   
/* 283:    */   public byte[] SquareBehind()
/* 284:    */   {
/* 285:262 */     return this.data.SquareBehind();
/* 286:    */   }
/* 287:    */   
/* 288:    */   public void finishPlace(List<RoomFloorItemData.AffectedTile> Points)
/* 289:    */   {
/* 290:267 */     this.data.finishPlace(Points);
/* 291:    */   }
/* 292:    */   
/* 293:    */   public void finishPlace(Connection user, List<RoomFloorItemData.AffectedTile> Points, boolean add)
/* 294:    */   {
/* 295:271 */     this.data.finishPlace(user, Points, add);
/* 296:    */   }
/* 297:    */   
/* 298:    */   public void setPosition()
/* 299:    */   {
/* 300:275 */     this.data.setPosition();
/* 301:    */   }
/* 302:    */   
/* 303:    */   public void setPosition(int argX, int argY, int argXY)
/* 304:    */   {
/* 305:279 */     this.data.setPosition(argX, argY, argXY);
/* 306:    */   }
/* 307:    */   
/* 308:    */   public void setPosition(int arg1, int arg2)
/* 309:    */   {
/* 310:283 */     this.data.setPosition(arg1, arg2);
/* 311:    */   }
/* 312:    */   
/* 313:    */   public void setPosition(int val)
/* 314:    */   {
/* 315:287 */     this.data.setPosition(val);
/* 316:    */   }
/* 317:    */   
/* 318:    */   public void setPosition(float val)
/* 319:    */   {
/* 320:291 */     this.data.setPosition(val);
/* 321:    */   }
/* 322:    */   
/* 323:    */   public void setDir(Direction8 val)
/* 324:    */   {
/* 325:295 */     this.data.setDir(val);
/* 326:    */   }
/* 327:    */   
/* 328:    */   public Direction8 getDir()
/* 329:    */   {
/* 330:299 */     return this.data.getDir();
/* 331:    */   }
/* 332:    */   
/* 333:    */   public List<RoomFloorItemData.AffectedTile> getAffectedTiles()
/* 334:    */   {
/* 335:303 */     return this.data.getAffectedTiles();
/* 336:    */   }
/* 337:    */   
/* 338:    */   public List<RoomFloorItemData.AffectedTile> getAffectedTiles(boolean asd)
/* 339:    */   {
/* 340:307 */     return this.data.getAffectedTiles(asd);
/* 341:    */   }
/* 342:    */   
/* 343:    */   public List<RoomFloorItemData.AffectedTile> getAffectedTiles(int xy, Direction8 dir)
/* 344:    */   {
/* 345:311 */     return this.data.getAffectedTiles(xy, dir);
/* 346:    */   }
/* 347:    */   
/* 348:    */   public List<RoomFloorItemData.AffectedTile> getOutSideTiles()
/* 349:    */   {
/* 350:315 */     return this.data.getOutSideTiles();
/* 351:    */   }
/* 352:    */   
/* 353:    */   public RoomTask getRoom()
/* 354:    */   {
/* 355:319 */     if (this.data == null) {
/* 356:320 */       return null;
/* 357:    */     }
/* 358:322 */     return this.data.currentRoom;
/* 359:    */   }
/* 360:    */   
/* 361:    */   public final void eventSetFlag(int xy, int flag, boolean Add)
/* 362:    */   {
/* 363:326 */     this.data.currentRoom.squareFlag.eventSetFlag(xy, flag, Add);
/* 364:    */   }
/* 365:    */   
/* 366:    */   public void setExtraParam(int extraparam)
/* 367:    */   {
/* 368:330 */     this.extraParam = extraparam;
/* 369:    */   }
/* 370:    */   
/* 371:    */   public int getExtraParam()
/* 372:    */   {
/* 373:334 */     return this.extraParam;
/* 374:    */   }
/* 375:    */   
/* 376:    */   public int getX()
/* 377:    */   {
/* 378:339 */     return this.data.getX();
/* 379:    */   }
/* 380:    */   
/* 381:    */   public int getY()
/* 382:    */   {
/* 383:343 */     return this.data.getY();
/* 384:    */   }
/* 385:    */   
/* 386:    */   public float getZ()
/* 387:    */   {
/* 388:347 */     return this.data.getZ();
/* 389:    */   }
/* 390:    */   
/* 391:    */   public int getXy()
/* 392:    */   {
/* 393:351 */     return this.data.getXy();
/* 394:    */   }
/* 395:    */   
/* 396:    */   public boolean itemsOnTop()
/* 397:    */   {
/* 398:355 */     return this.data.itemsOnTop();
/* 399:    */   }
/* 400:    */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.roomengine.entity.item.floor.FloorItem
 * JD-Core Version:    0.7.0.1
 */