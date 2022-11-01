/*  1:   */ package cappo.protocol.messages.events.room.engine;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageReader;
/*  4:   */ import cappo.engine.network.QueueWriter;
/*  5:   */ import cappo.engine.player.Connection;
/*  6:   */ import cappo.engine.threadpools.RoomTask;
/*  7:   */ import cappo.game.bots.RentalBot;
/*  8:   */ import cappo.game.player.inventory.PlayerInventory;
/*  9:   */ import cappo.game.roomengine.entity.live.Avatar;
/* 10:   */ import cappo.game.roomengine.gamemap.GameMapBase;
/* 11:   */ import cappo.protocol.messages.IncomingMessageEvent;
/* 12:   */ import cappo.protocol.messages.composers.inventory.bots.RemoveBotInventoryComposer;
/* 13:   */ import cappo.protocol.messages.composers.room.bots.BotErrorComposer;
/* 14:   */ import java.util.Map;
/* 15:   */ 
/* 16:   */ public class PlaceRentalBotParser
/* 17:   */   extends IncomingMessageEvent
/* 18:   */ {
/* 19:   */   public void messageReceived(Connection Main)
/* 20:   */   {
/* 21:22 */     Avatar avatar = Main.avatar;
/* 22:23 */     if (avatar == null) {
/* 23:24 */       return;
/* 24:   */     }
/* 25:27 */     if (avatar.controllerLevel < 4) {
/* 26:28 */       return;
/* 27:   */     }
/* 28:31 */     RoomTask room = avatar.room;
/* 29:33 */     if (room.PetCounter >= 5)
/* 30:   */     {
/* 31:34 */       QueueWriter.write(Main.socket, BotErrorComposer.compose(2));
/* 32:35 */       return;
/* 33:   */     }
/* 34:38 */     int botId = Main.currentPacket.readInt();
/* 35:   */     
/* 36:   */ 
/* 37:41 */     int xy = Main.currentPacket.readInt() + Main.currentPacket.readInt() * room.model.widthX;
/* 38:43 */     if (!room.canPlacePet(xy))
/* 39:   */     {
/* 40:44 */       QueueWriter.write(Main.socket, BotErrorComposer.compose(3));
/* 41:45 */       return;
/* 42:   */     }
/* 43:48 */     RentalBot bot = Main.inventory.removeBot(botId);
/* 44:49 */     if (bot == null) {
/* 45:50 */       return;
/* 46:   */     }
/* 47:53 */     Float z = (Float)room.squareAbsoluteHeight.get(Integer.valueOf(xy));
/* 48:54 */     if (z == null) {
/* 49:55 */       z = Float.valueOf(0.0F);
/* 50:   */     }
/* 51:57 */     room.deployBot(bot, xy, z.floatValue(), bot.botLook, bot.gender);
/* 52:   */     
/* 53:59 */     QueueWriter.write(Main.socket, RemoveBotInventoryComposer.compose(bot.id));
/* 54:   */   }
/* 55:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.room.engine.PlaceRentalBotParser
 * JD-Core Version:    0.7.0.1
 */