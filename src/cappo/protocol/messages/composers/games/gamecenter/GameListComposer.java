/*  1:   */ package cappo.protocol.messages.composers.games.gamecenter;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.game.games.GameBase;
/*  5:   */ import cappo.game.games.GamesManager;
/*  6:   */ import cappo.protocol.messages.Composer;
/*  7:   */ import java.util.List;
/*  8:   */ 
/*  9:   */ public class GameListComposer
/* 10:   */ {
/* 11:   */   public static int HEADER;
/* 12:   */   
/* 13:   */   public static final MessageWriter compose()
/* 14:   */   {
/* 15:18 */     MessageWriter ClientMessage = new MessageWriter();
/* 16:19 */     Composer.initPacket(HEADER, ClientMessage);
/* 17:20 */     Composer.add(Integer.valueOf(GamesManager.games.size()), ClientMessage);
/* 18:21 */     for (GameBase game : GamesManager.games)
/* 19:   */     {
/* 20:22 */       Composer.add(Integer.valueOf(game.gameId), ClientMessage);
/* 21:23 */       Composer.add(game.gameName, ClientMessage);
/* 22:24 */       Composer.add(game.bgColor, ClientMessage);
/* 23:25 */       Composer.add(game.textColor, ClientMessage);
/* 24:26 */       Composer.add(game.imagesPath, ClientMessage);
/* 25:27 */       Composer.add("", ClientMessage);
/* 26:   */     }
/* 27:29 */     Composer.endPacket(ClientMessage);
/* 28:30 */     return ClientMessage;
/* 29:   */   }
/* 30:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.games.gamecenter.GameListComposer
 * JD-Core Version:    0.7.0.1
 */