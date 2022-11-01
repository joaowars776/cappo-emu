/*  1:   */ package cappo.game.bots;
/*  2:   */ 
/*  3:   */ import cappo.engine.logging.Log;
/*  4:   */ import cappo.game.collections.Utils;
/*  5:   */ import cappo.game.player.AvatarLook;
/*  6:   */ import cappo.game.roomengine.entity.live.RentalBotEntity;
/*  7:   */ import java.util.ArrayList;
/*  8:   */ import java.util.List;
/*  9:   */ 
/* 10:   */ public class RentalBot
/* 11:   */ {
/* 12:   */   public static final int PROPERTY_UNDEFINED1 = 0;
/* 13:   */   public static final int PROPERTY_DRESS = 1;
/* 14:   */   public static final int PROPERTY_CHAT = 2;
/* 15:   */   public static final int PROPERTY_WALK = 3;
/* 16:   */   public static final int PROPERTY_DANCE = 4;
/* 17:   */   public static final int PROPERTY_NAME = 5;
/* 18:   */   public static final int PROPERTY_UNDEFINED2 = 6;
/* 19:   */   public static final short BOT_GENERIC = 0;
/* 20:   */   public static final short BOT_BARTENDER = 1;
/* 21:   */   public static final short BOT_LIMIT = 2;
/* 22:32 */   public static final BotBase[] BOTS = {
/* 23:   */   
/* 24:34 */     new BotGeneric(), 
/* 25:   */     
/* 26:36 */     new BotBarTender() };
/* 27:   */   public int id;
/* 28:   */   public String name;
/* 29:   */   public String motto;
/* 30:   */   public String gender;
/* 31:   */   public AvatarLook botLook;
/* 32:   */   public int ownerId;
/* 33:   */   public String ownerName;
/* 34:   */   public BotBase base;
/* 35:   */   public short botType;
/* 36:   */   public RentalBotEntity botEntity;
/* 37:   */   public boolean needInsert;
/* 38:54 */   public List<String> speeches = new ArrayList();
/* 39:   */   public boolean danceEnabled;
/* 40:56 */   public boolean walkRandomEnabled = true;
/* 41:57 */   public boolean chatAuto = true;
/* 42:58 */   public int chatDelay = 7;
/* 43:59 */   public long nextChat = 0L;
/* 44:   */   
/* 45:   */   public RentalBot(int iId, String sName, short type)
/* 46:   */   {
/* 47:62 */     this.id = iId;
/* 48:63 */     this.name = sName;
/* 49:64 */     this.botType = type;
/* 50:65 */     this.base = BOTS[type];
/* 51:66 */     if (this.base == null) {
/* 52:67 */       Log.printLog("Unknown bot type: " + type);
/* 53:   */     }
/* 54:   */   }
/* 55:   */   
/* 56:   */   public void setDefaults()
/* 57:   */   {
/* 58:72 */     this.needInsert = true;
/* 59:   */     
/* 60:74 */     this.motto = "I am a bot!";
/* 61:75 */     this.botLook = this.base.defaultLook;
/* 62:76 */     this.gender = this.base.defaultGender;
/* 63:   */   }
/* 64:   */   
/* 65:   */   public String getSpeech()
/* 66:   */   {
/* 67:81 */     if (this.speeches.isEmpty()) {
/* 68:82 */       return null;
/* 69:   */     }
/* 70:84 */     return (String)this.speeches.get(Utils.GetRandomNumber(0, this.speeches.size() - 1));
/* 71:   */   }
/* 72:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.bots.RentalBot
 * JD-Core Version:    0.7.0.1
 */