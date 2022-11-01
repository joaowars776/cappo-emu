/*  1:   */ package cappo.protocol.messages.events.room.action;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.QueueWriter;
/*  4:   */ import cappo.engine.player.Connection;
/*  5:   */ import cappo.engine.threadpools.RoomTask;
/*  6:   */ import cappo.game.player.PlayerData;
/*  7:   */ import cappo.game.roomengine.entity.live.Avatar;
/*  8:   */ import cappo.game.roomengine.settings.PlayerRight;
/*  9:   */ import cappo.protocol.messages.IncomingMessageEvent;
/* 10:   */ import cappo.protocol.messages.composers.room.permissions.YouAreNotControllerComposer;
/* 11:   */ import cappo.protocol.messages.composers.roomsettings.FlatControllerRemovedComposer;
/* 12:   */ import java.util.Map;
/* 13:   */ 
/* 14:   */ public class RemoveAllRightsParser
/* 15:   */   extends IncomingMessageEvent
/* 16:   */ {
/* 17:   */   public void messageReceived(Connection Main)
/* 18:   */   {
/* 19:21 */     Avatar avatar = Main.avatar;
/* 20:22 */     if ((avatar == null) || (Main.ownRooms.containsKey(Integer.valueOf(avatar.room.roomId)))) {
/* 21:23 */       return;
/* 22:   */     }
/* 23:26 */     for (PlayerRight right : avatar.room.usersWithRights.values())
/* 24:   */     {
/* 25:27 */       QueueWriter.write(Main.socket, FlatControllerRemovedComposer.compose(avatar.room.roomId, right.player.userId));
/* 26:   */       
/* 27:29 */       Connection clientConnection = right.player.connection;
/* 28:30 */       if (clientConnection != null)
/* 29:   */       {
/* 30:34 */         Avatar clientAvatar = clientConnection.avatar;
/* 31:35 */         if (clientAvatar != null) {
/* 32:39 */           if (clientAvatar.room == avatar.room)
/* 33:   */           {
/* 34:40 */             clientAvatar.controllerLevel = 0;
/* 35:41 */             QueueWriter.writeAndFlush(clientConnection.socket, YouAreNotControllerComposer.compose());
/* 36:   */           }
/* 37:   */         }
/* 38:   */       }
/* 39:   */     }
/* 40:45 */     avatar.room.usersWithRights.clear();
/* 41:   */   }
/* 42:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.room.action.RemoveAllRightsParser
 * JD-Core Version:    0.7.0.1
 */