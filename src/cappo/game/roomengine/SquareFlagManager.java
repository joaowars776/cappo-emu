/*  1:   */ package cappo.game.roomengine;
/*  2:   */ 
/*  3:   */ import java.util.Map;
/*  4:   */ import java.util.concurrent.ConcurrentHashMap;
/*  5:   */ 
/*  6:   */ public class SquareFlagManager
/*  7:   */ {
/*  8:   */   private final Map<Short, Integer> events;
/*  9:   */   private final Map<Short, Integer> flags;
/* 10:   */   public static final int squareLAY = 1;
/* 11:   */   public static final int squareSIT = 2;
/* 12:   */   public static final int squareWALKABLE = 4;
/* 13:   */   public static final int squareWALKABLE_LASTSTEP = 8;
/* 14:   */   public static final int squareEVENT = 16;
/* 15:   */   public static final int squareEvent_GameGate = 1;
/* 16:   */   public static final int squareEvent_Roller = 2;
/* 17:   */   public static final int squareEvent_Skates = 4;
/* 18:   */   public static final int squareEvent_BanzaiTile = 8;
/* 19:   */   public static final int squareEvent_BanzaiPuck = 16;
/* 20:   */   public static final int squareEvent_FootballBall = 32;
/* 21:   */   public static final int squareEvent_FootballGoal = 64;
/* 22:   */   public static final int squareEvent_Water = 128;
/* 23:   */   public static final int squareEvent_WiredWalkIn = 256;
/* 24:   */   public static final int squareEvent_WiredWalkOut = 512;
/* 25:   */   
/* 26:   */   public SquareFlagManager()
/* 27:   */   {
/* 28:28 */     this.events = new ConcurrentHashMap();
/* 29:29 */     this.flags = new ConcurrentHashMap();
/* 30:   */   }
/* 31:   */   
/* 32:   */   public boolean eventHave(int a, int flag)
/* 33:   */   {
/* 34:33 */     short xy = (short)a;
/* 35:   */     
/* 36:35 */     Integer bits = (Integer)this.events.get(Short.valueOf(xy));
/* 37:36 */     if (bits == null) {
/* 38:37 */       return false;
/* 39:   */     }
/* 40:40 */     return (bits.intValue() & flag) > 0;
/* 41:   */   }
/* 42:   */   
/* 43:   */   public final void eventSetFlag(int a, int flag, boolean Add)
/* 44:   */   {
/* 45:44 */     short xy = (short)a;
/* 46:   */     
/* 47:46 */     Integer bits = (Integer)this.events.get(Short.valueOf(xy));
/* 48:47 */     if (bits == null)
/* 49:   */     {
/* 50:48 */       if (!Add) {
/* 51:49 */         return;
/* 52:   */       }
/* 53:51 */       this.events.put(Short.valueOf(xy), Integer.valueOf(flag));
/* 54:   */     }
/* 55:53 */     else if (Add)
/* 56:   */     {
/* 57:54 */       this.events.put(Short.valueOf(xy), Integer.valueOf(bits.intValue() | flag));
/* 58:   */     }
/* 59:   */     else
/* 60:   */     {
/* 61:56 */       this.events.put(Short.valueOf(xy), Integer.valueOf(bits.intValue() & (flag ^ 0xFFFFFFFF)));
/* 62:   */     }
/* 63:   */   }
/* 64:   */   
/* 65:   */   public boolean have(int a, int flag)
/* 66:   */   {
/* 67:62 */     short xy = (short)a;
/* 68:   */     
/* 69:64 */     Integer bits = (Integer)this.flags.get(Short.valueOf(xy));
/* 70:65 */     if (bits == null) {
/* 71:66 */       return false;
/* 72:   */     }
/* 73:69 */     return (bits.intValue() & flag) > 0;
/* 74:   */   }
/* 75:   */   
/* 76:   */   public final void SetFlag(int a, int flag, boolean Add)
/* 77:   */   {
/* 78:73 */     short xy = (short)a;
/* 79:   */     
/* 80:75 */     Integer bits = (Integer)this.flags.get(Short.valueOf(xy));
/* 81:76 */     if (bits == null)
/* 82:   */     {
/* 83:77 */       if (!Add) {
/* 84:78 */         return;
/* 85:   */       }
/* 86:80 */       this.flags.put(Short.valueOf(xy), Integer.valueOf(flag));
/* 87:   */     }
/* 88:82 */     else if (Add)
/* 89:   */     {
/* 90:83 */       this.flags.put(Short.valueOf(xy), Integer.valueOf(bits.intValue() | flag));
/* 91:   */     }
/* 92:   */     else
/* 93:   */     {
/* 94:85 */       this.flags.put(Short.valueOf(xy), Integer.valueOf(bits.intValue() & (flag ^ 0xFFFFFFFF)));
/* 95:   */     }
/* 96:   */   }
/* 97:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.roomengine.SquareFlagManager
 * JD-Core Version:    0.7.0.1
 */