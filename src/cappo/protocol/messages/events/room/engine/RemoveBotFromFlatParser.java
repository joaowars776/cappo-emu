/*  1:   */ package cappo.protocol.messages.events.room.engine;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageReader;
/*  4:   */ import cappo.engine.network.QueueWriter;
/*  5:   */ import cappo.engine.player.Connection;
/*  6:   */ import cappo.engine.threadpools.RoomTask;
/*  7:   */ import cappo.game.bots.RentalBot;
/*  8:   */ import cappo.game.player.PlayerData;
/*  9:   */ import cappo.game.player.inventory.PlayerInventory;
/* 10:   */ import cappo.game.roomengine.entity.live.Avatar;
/* 11:   */ import cappo.game.roomengine.entity.live.RentalBotEntity;
/* 12:   */ import cappo.protocol.messages.IncomingMessageEvent;
/* 13:   */ import cappo.protocol.messages.composers.inventory.bots.AddBotToInventoryComposer;
/* 14:   */ 
/* 15:   */ public class RemoveBotFromFlatParser
/* 16:   */   extends IncomingMessageEvent
/* 17:   */ {
/* 18:   */   public void messageReceived(Connection Main)
/* 19:   */   {
/* 20:20 */     Avatar avatar = Main.avatar;
/* 21:21 */     if (avatar == null) {
/* 22:22 */       return;
/* 23:   */     }
/* 24:25 */     RoomTask room = avatar.room;
/* 25:   */     
/* 26:27 */     RentalBotEntity botEntity = room.getRoomBotById(Main.currentPacket.readInt());
/* 27:28 */     if ((botEntity != null) && (botEntity.botData.ownerId == Main.playerData.userId))
/* 28:   */     {
/* 29:29 */       Main.inventory.addBot(botEntity.botData.id, botEntity.botData);
/* 30:30 */       QueueWriter.write(Main.socket, AddBotToInventoryComposer.compose(botEntity.botData));
/* 31:31 */       room.removeBot(botEntity);
/* 32:   */     }
/* 33:   */   }
/* 34:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.room.engine.RemoveBotFromFlatParser
 * JD-Core Version:    0.7.0.1
 */