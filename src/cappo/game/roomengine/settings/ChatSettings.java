/*  1:   */ package cappo.game.roomengine.settings;
/*  2:   */ 
/*  3:   */ public class ChatSettings
/*  4:   */ {
/*  5:   */   public static final int MODE_1 = 0;
/*  6:   */   public static final int MODE_2 = 1;
/*  7:   */   public static final int WIDTH_LARGE = 0;
/*  8:   */   public static final int WIDTH_MEDIUM = 1;
/*  9:   */   public static final int WIDTH_SMALL = 2;
/* 10:   */   public static final int SPEED_FAST = 0;
/* 11:   */   public static final int SPEED_NORMAL = 1;
/* 12:   */   public static final int SPEED_SLOW = 2;
/* 13:   */   public int chatMode;
/* 14:   */   public int chatBubbleWidth;
/* 15:   */   public int chatScrollSpeed;
/* 16:   */   public int chatHearingDistance;
/* 17:   */   public int chatFloodSensitivity;
/* 18:   */   
/* 19:   */   public ChatSettings(int data)
/* 20:   */   {
/* 21:28 */     this.chatMode = getIntShiftBits(data, 0, 7);
/* 22:29 */     this.chatBubbleWidth = getIntShiftBits(data, 3, 7);
/* 23:30 */     this.chatScrollSpeed = getIntShiftBits(data, 6, 7);
/* 24:31 */     this.chatHearingDistance = getIntShiftBits(data, 9, 31);
/* 25:32 */     this.chatFloodSensitivity = getIntShiftBits(data, 14, 7);
/* 26:   */   }
/* 27:   */   
/* 28:   */   public int getIntValue()
/* 29:   */   {
/* 30:36 */     return this.chatMode | 
/* 31:37 */       this.chatBubbleWidth << 3 | 
/* 32:38 */       this.chatScrollSpeed << 6 | 
/* 33:39 */       this.chatHearingDistance << 9 | 
/* 34:40 */       this.chatFloodSensitivity << 14;
/* 35:   */   }
/* 36:   */   
/* 37:   */   private int getIntBits(long num, int bits)
/* 38:   */   {
/* 39:44 */     return (int)(num & bits);
/* 40:   */   }
/* 41:   */   
/* 42:   */   private int getIntShiftBits(int data, int shift, int bits)
/* 43:   */   {
/* 44:48 */     if (shift > 0) {
/* 45:49 */       return getIntBits(data >>> shift, bits);
/* 46:   */     }
/* 47:51 */     return getIntBits(data, bits);
/* 48:   */   }
/* 49:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.roomengine.settings.ChatSettings
 * JD-Core Version:    0.7.0.1
 */