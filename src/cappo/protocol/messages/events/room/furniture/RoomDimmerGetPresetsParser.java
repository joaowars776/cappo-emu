/*  1:   */ package cappo.protocol.messages.events.room.furniture;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.QueueWriter;
/*  4:   */ import cappo.engine.player.Connection;
/*  5:   */ import cappo.engine.threadpools.RoomTask;
/*  6:   */ import cappo.game.roomengine.entity.live.Avatar;
/*  7:   */ import cappo.protocol.messages.IncomingMessageEvent;
/*  8:   */ import cappo.protocol.messages.composers.room.furniture.RoomDimmerPresetsComposer;
/*  9:   */ 
/* 10:   */ public class RoomDimmerGetPresetsParser
/* 11:   */   extends IncomingMessageEvent
/* 12:   */ {
/* 13:   */   public void messageReceived(Connection Main)
/* 14:   */   {
/* 15:19 */     Avatar avatar = Main.avatar;
/* 16:20 */     if ((avatar == null) || (avatar.controllerLevel < 4) || 
/* 17:21 */       (avatar.room.MoodlightData == null)) {
/* 18:22 */       return;
/* 19:   */     }
/* 20:25 */     QueueWriter.write(Main.socket, RoomDimmerPresetsComposer.compose(avatar.room.MoodlightData));
/* 21:   */   }
/* 22:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.room.furniture.RoomDimmerGetPresetsParser
 * JD-Core Version:    0.7.0.1
 */