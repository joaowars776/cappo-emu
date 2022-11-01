/*  1:   */ package cappo.protocol.messages.composers.inventory.bots;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.game.bots.RentalBot;
/*  5:   */ import cappo.protocol.messages.Composer;
/*  6:   */ import cappo.protocol.messages.composers.serializers.SerializeBot;
/*  7:   */ 
/*  8:   */ public class AddBotToInventoryComposer
/*  9:   */ {
/* 10:   */   public static int HEADER;
/* 11:   */   
/* 12:   */   public static final MessageWriter compose(RentalBot bot)
/* 13:   */   {
/* 14:18 */     MessageWriter ClientMessage = new MessageWriter();
/* 15:19 */     Composer.initPacket(HEADER, ClientMessage);
/* 16:20 */     SerializeBot.parse(ClientMessage, bot);
/* 17:21 */     Composer.add(Boolean.valueOf(false), ClientMessage);
/* 18:22 */     Composer.endPacket(ClientMessage);
/* 19:23 */     return ClientMessage;
/* 20:   */   }
/* 21:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.inventory.bots.AddBotToInventoryComposer
 * JD-Core Version:    0.7.0.1
 */