/*  1:   */ package cappo.protocol.messages.events.inventory.bots;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.QueueWriter;
/*  4:   */ import cappo.engine.player.Connection;
/*  5:   */ import cappo.game.player.inventory.PlayerInventory;
/*  6:   */ import cappo.protocol.messages.IncomingMessageEvent;
/*  7:   */ import cappo.protocol.messages.composers.inventory.bots.BotsInventoryComposer;
/*  8:   */ 
/*  9:   */ public class RequestBotInventoryParser
/* 10:   */   extends IncomingMessageEvent
/* 11:   */ {
/* 12:   */   public void messageReceived(Connection Main)
/* 13:   */   {
/* 14:17 */     QueueWriter.write(Main.socket, BotsInventoryComposer.compose(Main.inventory.getBots()));
/* 15:   */   }
/* 16:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.inventory.bots.RequestBotInventoryParser
 * JD-Core Version:    0.7.0.1
 */