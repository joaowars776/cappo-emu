/*  1:   */ package cappo.protocol.messages.events.room.action;
/*  2:   */ 
/*  3:   */ import cappo.engine.database.Database;
/*  4:   */ import cappo.engine.logging.Log;
/*  5:   */ import cappo.engine.network.MessageReader;
/*  6:   */ import cappo.engine.network.QueueWriter;
/*  7:   */ import cappo.engine.player.Connection;
/*  8:   */ import cappo.engine.threadpools.RoomTask;
/*  9:   */ import cappo.game.player.PlayerData;
/* 10:   */ import cappo.game.roomengine.RoomData;
/* 11:   */ import cappo.game.roomengine.entity.live.Avatar;
/* 12:   */ import cappo.game.roomengine.settings.PlayerRight;
/* 13:   */ import cappo.protocol.messages.IncomingMessageEvent;
/* 14:   */ import cappo.protocol.messages.composers.room.permissions.YouAreNotControllerComposer;
/* 15:   */ import cappo.protocol.messages.composers.roomsettings.FlatControllerRemovedComposer;
/* 16:   */ import java.util.Map;
/* 17:   */ 
/* 18:   */ public class RemoveRightsParser
/* 19:   */   extends IncomingMessageEvent
/* 20:   */ {
/* 21:   */   public void messageReceived(Connection Main)
/* 22:   */   {
/* 23:24 */     Avatar avatar = Main.avatar;
/* 24:25 */     if (avatar == null) {
/* 25:26 */       return;
/* 26:   */     }
/* 27:29 */     RoomTask room = avatar.room;
/* 28:   */     
/* 29:31 */     int ammount = Main.currentPacket.readInt();
/* 30:32 */     if ((ammount < 1) || (ammount > 50) || (Main.playerData.userId != room.roomData.roomOwner.userId)) {
/* 31:33 */       return;
/* 32:   */     }
/* 33:36 */     for (int i = 0; i < ammount; i++)
/* 34:   */     {
/* 35:37 */       PlayerRight right = (PlayerRight)room.usersWithRights.remove(Integer.valueOf(Main.currentPacket.readInt()));
/* 36:38 */       if (right != null)
/* 37:   */       {
/* 38:42 */         QueueWriter.write(Main.socket, FlatControllerRemovedComposer.compose(room.roomId, right.player.userId));
/* 39:   */         
/* 40:44 */         Connection clientConnection = right.player.connection;
/* 41:45 */         if (clientConnection != null)
/* 42:   */         {
/* 43:46 */           Avatar clientAvatar = clientConnection.avatar;
/* 44:48 */           if ((clientAvatar != null) && 
/* 45:49 */             (clientAvatar.room == room))
/* 46:   */           {
/* 47:50 */             clientAvatar.controllerLevel = 0;
/* 48:51 */             QueueWriter.writeAndFlush(clientConnection.socket, YouAreNotControllerComposer.compose());
/* 49:   */           }
/* 50:   */         }
/* 51:   */         try
/* 52:   */         {
/* 53:57 */           Database.exec("DELETE FROM room_rights WHERE `user_id`='" + right.player.userId + "' AND `room_id`='" + room.roomId + "';", new Object[0]);
/* 54:   */         }
/* 55:   */         catch (Exception ex)
/* 56:   */         {
/* 57:60 */           Log.printException("rights", ex);
/* 58:   */         }
/* 59:   */       }
/* 60:   */     }
/* 61:   */   }
/* 62:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.room.action.RemoveRightsParser
 * JD-Core Version:    0.7.0.1
 */