/*  1:   */ package cappo.protocol.messages.composers.games.snowwar;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.game.games.snowwar.gameobjects.HumanGameObject;
/*  5:   */ import cappo.protocol.messages.Composer;
/*  6:   */ import java.util.Collection;
/*  7:   */ 
/*  8:   */ public class StageStillLoadingComposer
/*  9:   */ {
/* 10:   */   public static int HEADER;
/* 11:   */   
/* 12:   */   public static final MessageWriter compose(Collection<HumanGameObject> playersLoaded)
/* 13:   */   {
/* 14:19 */     MessageWriter ClientMessage = new MessageWriter();
/* 15:20 */     Composer.initPacket(HEADER, ClientMessage);
/* 16:21 */     Composer.add(Integer.valueOf(0), ClientMessage);
/* 17:22 */     Composer.add(Integer.valueOf(playersLoaded.size()), ClientMessage);
/* 18:23 */     for (HumanGameObject player : playersLoaded) {
/* 19:24 */       Composer.add(Integer.valueOf(player.userId), ClientMessage);
/* 20:   */     }
/* 21:26 */     Composer.endPacket(ClientMessage);
/* 22:27 */     return ClientMessage;
/* 23:   */   }
/* 24:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.games.snowwar.StageStillLoadingComposer
 * JD-Core Version:    0.7.0.1
 */