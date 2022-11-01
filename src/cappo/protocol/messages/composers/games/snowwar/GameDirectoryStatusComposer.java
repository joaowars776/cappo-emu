/*  1:   */ package cappo.protocol.messages.composers.games.snowwar;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.game.player.PlayerData;
/*  5:   */ import cappo.protocol.messages.Composer;
/*  6:   */ 
/*  7:   */ public class GameDirectoryStatusComposer
/*  8:   */ {
/*  9:   */   public static final int ENABLED = 0;
/* 10:   */   public static final int UNKNOW1 = 1;
/* 11:   */   public static final int UNKNOW2 = 2;
/* 12:   */   public static final int UNKNOW3 = 3;
/* 13:   */   public static int HEADER;
/* 14:   */   
/* 15:   */   public static final MessageWriter compose(PlayerData player, int state)
/* 16:   */   {
/* 17:22 */     MessageWriter ClientMessage = new MessageWriter();
/* 18:23 */     Composer.initPacket(HEADER, ClientMessage);
/* 19:24 */     Composer.add(Integer.valueOf(state), ClientMessage);
/* 20:25 */     Composer.add(Integer.valueOf(0), ClientMessage);
/* 21:26 */     Composer.add(Integer.valueOf(0), ClientMessage);
/* 22:27 */     Composer.add(Integer.valueOf(-1), ClientMessage);
/* 23:28 */     Composer.endPacket(ClientMessage);
/* 24:29 */     return ClientMessage;
/* 25:   */   }
/* 26:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.games.snowwar.GameDirectoryStatusComposer
 * JD-Core Version:    0.7.0.1
 */