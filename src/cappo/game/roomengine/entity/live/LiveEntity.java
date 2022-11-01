/*   1:    */ package cappo.game.roomengine.entity.live;
/*   2:    */ 
/*   3:    */ import cappo.engine.threadpools.RoomTask;
/*   4:    */ import cappo.game.games.snowwar.Direction8;
/*   5:    */ import cappo.game.roomengine.gamemap.GameMapBase;
/*   6:    */ import cappo.game.roomengine.roomevents.User_WALK;
/*   7:    */ import cappo.protocol.messages.composers.room.chat.ChatComposer;
/*   8:    */ import cappo.protocol.messages.composers.room.chat.ShoutComposer;
/*   9:    */ import java.util.ArrayList;
/*  10:    */ 
/*  11:    */ public class LiveEntity
/*  12:    */ {
/*  13:    */   public static final int ENTITY_USER = 1;
/*  14:    */   public static final int ENTITY_PET = 2;
/*  15:    */   public static final int ENTITY_BOT = 3;
/*  16:    */   public static final int ENTITY_RENTABLE_BOT = 4;
/*  17:    */   public int entityType;
/*  18:    */   public boolean allowOverride;
/*  19:    */   public short virtualId;
/*  20:    */   public RoomTask room;
/*  21:    */   public User_WALK evtWalk;
/*  22:    */   public int x;
/*  23:    */   public int y;
/*  24:    */   public int xy;
/*  25:    */   public float z;
/*  26: 26 */   public Direction8 RotBody = Direction8.N;
/*  27: 27 */   public Direction8 RotHead = Direction8.N;
/*  28: 29 */   public String Status = "";
/*  29:    */   public LiveEntity ridingEntity;
/*  30:    */   
/*  31:    */   public LiveEntity(RoomTask curentRoom, short VirtualId)
/*  32:    */   {
/*  33: 34 */     this.room = curentRoom;
/*  34: 35 */     this.virtualId = VirtualId;
/*  35: 36 */     this.evtWalk = new User_WALK(this);
/*  36:    */     
/*  37: 38 */     this.room.userUpdateNeeded(this);
/*  38:    */   }
/*  39:    */   
/*  40:    */   public int hashCode()
/*  41:    */   {
/*  42: 43 */     return this.virtualId;
/*  43:    */   }
/*  44:    */   
/*  45:    */   public boolean HaveStatus(String Key)
/*  46:    */   {
/*  47: 47 */     return this.Status.contains(Key);
/*  48:    */   }
/*  49:    */   
/*  50:    */   public void SetPos(int X, int Y, float Z)
/*  51:    */   {
/*  52: 51 */     this.x = X;
/*  53: 52 */     this.y = Y;
/*  54: 53 */     this.xy = (X + Y * this.room.model.widthX);
/*  55: 54 */     this.z = Z;
/*  56:    */   }
/*  57:    */   
/*  58:    */   public void SetRot(Direction8 Rotation)
/*  59:    */   {
/*  60: 58 */     SetRot(Rotation, false);
/*  61:    */   }
/*  62:    */   
/*  63:    */   public void SetRot(Direction8 Rotation, boolean HeadOnly)
/*  64:    */   {
/*  65: 62 */     if (this.Status.contains("lay")) {
/*  66: 63 */       return;
/*  67:    */     }
/*  68: 66 */     int diff = this.RotBody.getRot() - Rotation.getRot();
/*  69: 67 */     this.RotHead = this.RotBody;
/*  70: 69 */     if ((this.Status.contains("sit")) || (HeadOnly))
/*  71:    */     {
/*  72: 70 */       if (diff > 0) {
/*  73: 71 */         this.RotHead = this.RotBody.rotateDirection45Degrees(false);
/*  74: 72 */       } else if (diff < 0) {
/*  75: 73 */         this.RotHead = this.RotBody.rotateDirection45Degrees(true);
/*  76:    */       }
/*  77:    */     }
/*  78: 75 */     else if ((diff < -1) || (diff > 1))
/*  79:    */     {
/*  80: 76 */       this.RotBody = Rotation;
/*  81: 77 */       this.RotHead = Rotation;
/*  82:    */     }
/*  83:    */     else
/*  84:    */     {
/*  85: 79 */       this.RotHead = Rotation;
/*  86:    */     }
/*  87: 82 */     this.room.userUpdateNeeded(this);
/*  88:    */   }
/*  89:    */   
/*  90:    */   public void setStatus(String Key, String Value)
/*  91:    */   {
/*  92: 86 */     String status = "/";
/*  93: 87 */     if (!Key.isEmpty()) {
/*  94: 88 */       status = status + Key + " " + Value + "/";
/*  95:    */     }
/*  96: 91 */     status = status + "/";
/*  97:    */     
/*  98: 93 */     this.Status = status;
/*  99: 94 */     this.room.userUpdateNeeded(this);
/* 100:    */   }
/* 101:    */   
/* 102:    */   public void say(String message, int styleId, int sayId, boolean isShout)
/* 103:    */   {
/* 104: 98 */     if (isShout) {
/* 105: 99 */       this.room.sendMessage(ShoutComposer.compose(this.virtualId, message, 0, styleId, new ArrayList(), sayId));
/* 106:    */     } else {
/* 107:101 */       this.room.sendMessage(ChatComposer.compose(this.virtualId, message, 0, styleId, new ArrayList(), sayId));
/* 108:    */     }
/* 109:    */   }
/* 110:    */   
/* 111:    */   public void clearMovement()
/* 112:    */   {
/* 113:106 */     if (this.evtWalk.isWalking) {
/* 114:107 */       this.evtWalk.isWalking = false;
/* 115:    */     }
/* 116:    */   }
/* 117:    */   
/* 118:    */   public void moveTo(int X, int Y)
/* 119:    */   {
/* 120:112 */     this.evtWalk.walk(this.room, X, Y);
/* 121:    */   }
/* 122:    */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.roomengine.entity.live.LiveEntity
 * JD-Core Version:    0.7.0.1
 */