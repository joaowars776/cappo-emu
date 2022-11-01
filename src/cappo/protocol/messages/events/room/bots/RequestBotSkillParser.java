/*  1:   */ package cappo.protocol.messages.events.room.bots;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageReader;
/*  4:   */ import cappo.engine.network.QueueWriter;
/*  5:   */ import cappo.engine.player.Connection;
/*  6:   */ import cappo.engine.threadpools.RoomTask;
/*  7:   */ import cappo.game.bots.RentalBot;
/*  8:   */ import cappo.game.player.PlayerData;
/*  9:   */ import cappo.game.roomengine.entity.live.Avatar;
/* 10:   */ import cappo.game.roomengine.entity.live.RentalBotEntity;
/* 11:   */ import cappo.protocol.messages.IncomingMessageEvent;
/* 12:   */ import cappo.protocol.messages.composers.room.bots.BotSkillComposer;
/* 13:   */ 
/* 14:   */ public class RequestBotSkillParser
/* 15:   */   extends IncomingMessageEvent
/* 16:   */ {
/* 17:   */   public void messageReceived(Connection Main)
/* 18:   */   {
/* 19:20 */     Avatar avatar = Main.avatar;
/* 20:21 */     if (avatar == null) {
/* 21:22 */       return;
/* 22:   */     }
/* 23:25 */     RentalBotEntity botEntity = avatar.room.getRoomBotById(Main.currentPacket.readInt());
/* 24:26 */     if (botEntity == null) {
/* 25:27 */       return;
/* 26:   */     }
/* 27:30 */     RentalBot bot = botEntity.botData;
/* 28:31 */     if (bot.ownerId != Main.playerData.userId) {
/* 29:32 */       return;
/* 30:   */     }
/* 31:35 */     int propId = Main.currentPacket.readInt();
/* 32:36 */     if (2 == propId)
/* 33:   */     {
/* 34:37 */       String data = "";
/* 35:38 */       for (String chat : bot.speeches) {
/* 36:39 */         data = data + chat + "\r";
/* 37:   */       }
/* 38:41 */       data = data + ";#;" + (bot.chatAuto ? 1 : 0) + ";#;" + bot.chatDelay;
/* 39:   */       
/* 40:43 */       QueueWriter.write(Main.socket, BotSkillComposer.compose(bot.id, propId, data));
/* 41:   */     }
/* 42:44 */     else if (5 == propId)
/* 43:   */     {
/* 44:45 */       QueueWriter.write(Main.socket, BotSkillComposer.compose(bot.id, propId, bot.name));
/* 45:   */     }
/* 46:   */   }
/* 47:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.room.bots.RequestBotSkillParser
 * JD-Core Version:    0.7.0.1
 */