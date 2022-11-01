/*  1:   */ package cappo.protocol.messages.events.inventory.furni;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.engine.network.QueueWriter;
/*  5:   */ import cappo.engine.player.Connection;
/*  6:   */ import cappo.game.player.inventory.PlayerInventory;
/*  7:   */ import cappo.protocol.messages.IncomingMessageEvent;
/*  8:   */ import cappo.protocol.messages.composers.inventory.furni.FurniListComposer;
/*  9:   */ 
/* 10:   */ public class RequestFurniInventoryParser
/* 11:   */   extends IncomingMessageEvent
/* 12:   */ {
/* 13:   */   public void messageReceived(Connection Main)
/* 14:   */   {
/* 15:18 */     for (MessageWriter packet : FurniListComposer.compose(Main.inventory.getObjects(), Main.inventory.getItems())) {
/* 16:19 */       if (packet != null) {
/* 17:24 */         QueueWriter.write(Main.socket, packet);
/* 18:   */       }
/* 19:   */     }
/* 20:   */   }
/* 21:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.inventory.furni.RequestFurniInventoryParser
 * JD-Core Version:    0.7.0.1
 */