/*  1:   */ package cappo.protocol.messages.events.avatar;
/*  2:   */ 
/*  3:   */ import cappo.engine.database.DBResult;
/*  4:   */ import cappo.engine.database.Database;
/*  5:   */ import cappo.engine.logging.Log;
/*  6:   */ import cappo.engine.network.MessageReader;
/*  7:   */ import cappo.engine.network.QueueWriter;
/*  8:   */ import cappo.engine.player.Connection;
/*  9:   */ import cappo.game.collections.Utils;
/* 10:   */ import cappo.game.player.PlayerData;
/* 11:   */ import cappo.game.player.data.AvatarData;
/* 12:   */ import cappo.protocol.messages.IncomingMessageEvent;
/* 13:   */ import cappo.protocol.messages.composers.avatar.ResultCheckUserNameComposer;
/* 14:   */ import java.sql.ResultSet;
/* 15:   */ import java.util.ArrayList;
/* 16:   */ 
/* 17:   */ public class CheckUserNameParser
/* 18:   */   extends IncomingMessageEvent
/* 19:   */ {
/* 20:   */   public void messageReceived(Connection Main)
/* 21:   */   {
/* 22:24 */     if (!Main.haveFlag(4)) {
/* 23:25 */       return;
/* 24:   */     }
/* 25:29 */     long now = Utils.getTimestamp();
/* 26:30 */     if (Main.avatarData.lastCheckNameTry >= now)
/* 27:   */     {
/* 28:31 */       QueueWriter.write(Main.socket, ResultCheckUserNameComposer.compose(6, "", new ArrayList()));
/* 29:32 */       return;
/* 30:   */     }
/* 31:34 */     Main.avatarData.lastCheckNameTry = (now + 1L);
/* 32:   */     
/* 33:36 */     String name = Main.currentPacket.readString();
/* 34:38 */     if (name.length() < 5)
/* 35:   */     {
/* 36:39 */       QueueWriter.write(Main.socket, ResultCheckUserNameComposer.compose(2, name, new ArrayList()));
/* 37:40 */       return;
/* 38:   */     }
/* 39:42 */     if (name.length() > 15)
/* 40:   */     {
/* 41:43 */       QueueWriter.write(Main.socket, ResultCheckUserNameComposer.compose(3, name, new ArrayList()));
/* 42:44 */       return;
/* 43:   */     }
/* 44:47 */     if ((name == Main.playerData.userName) || (name.toLowerCase().startsWith("mod-")))
/* 45:   */     {
/* 46:48 */       QueueWriter.write(Main.socket, ResultCheckUserNameComposer.compose(4, name, new ArrayList()));
/* 47:49 */       return;
/* 48:   */     }
/* 49:52 */     DBResult result = new DBResult();
/* 50:   */     try
/* 51:   */     {
/* 52:54 */       Database.query(result, "SELECT null FROM users WHERE username = ? LIMIT 1;", new Object[] { name });
/* 53:56 */       if (result.data.next())
/* 54:   */       {
/* 55:57 */         QueueWriter.write(Main.socket, ResultCheckUserNameComposer.compose(5, name, new ArrayList()));
/* 56:58 */         result.close();
/* 57:59 */         return;
/* 58:   */       }
/* 59:   */     }
/* 60:   */     catch (Exception ex)
/* 61:   */     {
/* 62:63 */       Log.printException("CheckUserNameParser-1", ex);
/* 63:   */       
/* 64:65 */       result.close();
/* 65:   */       
/* 66:67 */       QueueWriter.write(Main.socket, ResultCheckUserNameComposer.compose(0, name, new ArrayList()));
/* 67:   */     }
/* 68:   */   }
/* 69:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.avatar.CheckUserNameParser
 * JD-Core Version:    0.7.0.1
 */