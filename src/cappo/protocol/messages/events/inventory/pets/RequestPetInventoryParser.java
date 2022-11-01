/*  1:   */ package cappo.protocol.messages.events.inventory.pets;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.QueueWriter;
/*  4:   */ import cappo.engine.player.Connection;
/*  5:   */ import cappo.game.player.inventory.PlayerInventory;
/*  6:   */ import cappo.game.roomengine.entity.live.Avatar;
/*  7:   */ import cappo.protocol.messages.IncomingMessageEvent;
/*  8:   */ import cappo.protocol.messages.composers.inventory.pets.PetsInventoryComposer;
/*  9:   */ 
/* 10:   */ public class RequestPetInventoryParser
/* 11:   */   extends IncomingMessageEvent
/* 12:   */ {
/* 13:   */   public void messageReceived(Connection Main)
/* 14:   */   {
/* 15:17 */     QueueWriter.write(Main.socket, PetsInventoryComposer.compose(Main.avatar.virtualId, Main.inventory.getPets()));
/* 16:   */   }
/* 17:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.inventory.pets.RequestPetInventoryParser
 * JD-Core Version:    0.7.0.1
 */