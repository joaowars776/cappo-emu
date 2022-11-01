/*  1:   */ package cappo.protocol.messages.composers.users;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.game.player.PlayerData;
/*  5:   */ import cappo.game.player.messenger.PlayerMessenger;
/*  6:   */ import cappo.protocol.messages.Composer;
/*  7:   */ 
/*  8:   */ public class RelationshipStatusComposer
/*  9:   */ {
/* 10:   */   public static int HEADER;
/* 11:   */   
/* 12:   */   public static final MessageWriter compose(PlayerData player)
/* 13:   */   {
/* 14:17 */     MessageWriter message = new MessageWriter();
/* 15:18 */     Composer.initPacket(HEADER, message);
/* 16:19 */     Composer.add(Integer.valueOf(player.userId), message);
/* 17:20 */     player.messenger.serializeRelationshipStatus(message);
/* 18:21 */     Composer.endPacket(message);
/* 19:22 */     return message;
/* 20:   */   }
/* 21:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.users.RelationshipStatusComposer
 * JD-Core Version:    0.7.0.1
 */