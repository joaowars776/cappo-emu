/*  1:   */ package cappo.protocol.messages.composers.inventory.bots;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.game.bots.RentalBot;
/*  5:   */ import cappo.protocol.messages.Composer;
/*  6:   */ import cappo.protocol.messages.composers.serializers.SerializeBot;
/*  7:   */ import java.util.Collection;
/*  8:   */ 
/*  9:   */ public class BotsInventoryComposer
/* 10:   */ {
/* 11:   */   public static int HEADER;
/* 12:   */   
/* 13:   */   public static final MessageWriter compose(Collection<RentalBot> inventoryBots)
/* 14:   */   {
/* 15:20 */     MessageWriter ClientMessage = new MessageWriter(500 + inventoryBots.size() * 500);
/* 16:21 */     Composer.initPacket(HEADER, ClientMessage);
/* 17:22 */     Composer.add(Integer.valueOf(inventoryBots.size()), ClientMessage);
/* 18:23 */     for (RentalBot bot : inventoryBots) {
/* 19:24 */       SerializeBot.parse(ClientMessage, bot);
/* 20:   */     }
/* 21:26 */     Composer.endPacket(ClientMessage);
/* 22:27 */     return ClientMessage;
/* 23:   */   }
/* 24:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.inventory.bots.BotsInventoryComposer
 * JD-Core Version:    0.7.0.1
 */