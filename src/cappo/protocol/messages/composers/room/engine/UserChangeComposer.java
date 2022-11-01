/*  1:   */ package cappo.protocol.messages.composers.room.engine;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.protocol.messages.Composer;
/*  5:   */ 
/*  6:   */ public class UserChangeComposer
/*  7:   */ {
/*  8:   */   public static int HEADER;
/*  9:   */   
/* 10:   */   public static final MessageWriter compose(int VirtualId, String Look, int Sex, String Motto, int AchievementsScore)
/* 11:   */   {
/* 12:16 */     MessageWriter ClientMessage = new MessageWriter();
/* 13:17 */     Composer.initPacket(HEADER, ClientMessage);
/* 14:18 */     Composer.add(Integer.valueOf(VirtualId), ClientMessage);
/* 15:19 */     Composer.add(Look, ClientMessage);
/* 16:20 */     Composer.add(Sex == 1 ? "M" : "F", ClientMessage);
/* 17:21 */     Composer.add(Motto, ClientMessage);
/* 18:22 */     Composer.add(Integer.valueOf(AchievementsScore), ClientMessage);
/* 19:23 */     Composer.endPacket(ClientMessage);
/* 20:24 */     return ClientMessage;
/* 21:   */   }
/* 22:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.room.engine.UserChangeComposer
 * JD-Core Version:    0.7.0.1
 */