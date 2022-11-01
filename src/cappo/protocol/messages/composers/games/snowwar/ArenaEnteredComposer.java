/*  1:   */ package cappo.protocol.messages.composers.games.snowwar;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.game.games.snowwar.gameobjects.HumanGameObject;
/*  5:   */ import cappo.protocol.messages.Composer;
/*  6:   */ import cappo.protocol.messages.composers.serializers.SerializeGame2PlayerData;
/*  7:   */ 
/*  8:   */ public class ArenaEnteredComposer
/*  9:   */ {
/* 10:   */   public static int HEADER;
/* 11:   */   
/* 12:   */   public static final MessageWriter compose(HumanGameObject Player)
/* 13:   */   {
/* 14:18 */     MessageWriter ClientMessage = new MessageWriter();
/* 15:19 */     Composer.initPacket(HEADER, ClientMessage);
/* 16:20 */     SerializeGame2PlayerData.parse(ClientMessage, Player);
/* 17:21 */     Composer.endPacket(ClientMessage);
/* 18:22 */     return ClientMessage;
/* 19:   */   }
/* 20:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.games.snowwar.ArenaEnteredComposer
 * JD-Core Version:    0.7.0.1
 */