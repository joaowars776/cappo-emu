/*  1:   */ package cappo.protocol.messages.events.navigator;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.QueueWriter;
/*  4:   */ import cappo.engine.player.Connection;
/*  5:   */ import cappo.engine.threadpools.RoomTask;
/*  6:   */ import cappo.game.roomengine.RoomData;
/*  7:   */ import cappo.game.roomengine.entity.live.Avatar;
/*  8:   */ import cappo.protocol.messages.IncomingMessageEvent;
/*  9:   */ import cappo.protocol.messages.composers.navigator.CanCreateEventComposer;
/* 10:   */ 
/* 11:   */ public class CanCreateEventParser
/* 12:   */   extends IncomingMessageEvent
/* 13:   */ {
/* 14:   */   public void messageReceived(Connection Main)
/* 15:   */   {
/* 16:19 */     Avatar avatar = Main.avatar;
/* 17:20 */     if ((avatar == null) || (Main.avatar.controllerLevel < 4)) {
/* 18:21 */       return;
/* 19:   */     }
/* 20:24 */     if (avatar.room.roomData.state != 0)
/* 21:   */     {
/* 22:25 */       QueueWriter.write(Main.socket, CanCreateEventComposer.compose(Boolean.valueOf(false), 3));
/* 23:26 */       return;
/* 24:   */     }
/* 25:29 */     QueueWriter.write(Main.socket, CanCreateEventComposer.compose(Boolean.valueOf(true), 0));
/* 26:   */   }
/* 27:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.navigator.CanCreateEventParser
 * JD-Core Version:    0.7.0.1
 */