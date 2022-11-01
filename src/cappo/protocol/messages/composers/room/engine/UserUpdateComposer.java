/*  1:   */ package cappo.protocol.messages.composers.room.engine;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.game.games.snowwar.Direction8;
/*  5:   */ import cappo.game.roomengine.entity.live.Avatar;
/*  6:   */ import cappo.game.roomengine.entity.live.LiveEntity;
/*  7:   */ import cappo.game.roomengine.entity.live.PetEntity;
/*  8:   */ import cappo.protocol.messages.Composer;
/*  9:   */ import java.util.Collection;
/* 10:   */ 
/* 11:   */ public class UserUpdateComposer
/* 12:   */ {
/* 13:   */   public static int HEADER;
/* 14:   */   
/* 15:   */   public static final MessageWriter compose(Collection<Avatar> UserList, Collection<PetEntity> BotList)
/* 16:   */   {
/* 17:21 */     int i = 0;
/* 18:   */     
/* 19:23 */     MessageWriter ClientMessage = new MessageWriter(100 + (UserList.size() + BotList.size()) * 100);
/* 20:24 */     Composer.initPacket(HEADER, ClientMessage);
/* 21:25 */     Composer.add(ClientMessage.setSaved(Integer.valueOf(0)), ClientMessage);
/* 22:27 */     for (Avatar User : UserList)
/* 23:   */     {
/* 24:28 */       Composer.add(Short.valueOf(User.virtualId), ClientMessage);
/* 25:29 */       Composer.add(Integer.valueOf(User.x), ClientMessage);
/* 26:30 */       Composer.add(Integer.valueOf(User.y), ClientMessage);
/* 27:31 */       Composer.add(Float.toString(User.z).replace(',', '.'), ClientMessage);
/* 28:32 */       Composer.add(Integer.valueOf(User.RotHead.getRot()), ClientMessage);
/* 29:33 */       Composer.add(Integer.valueOf(User.RotBody.getRot()), ClientMessage);
/* 30:34 */       Composer.add(User.Status, ClientMessage);
/* 31:35 */       i++;
/* 32:   */     }
/* 33:38 */     for (PetEntity User : BotList)
/* 34:   */     {
/* 35:39 */       Composer.add(Short.valueOf(User.virtualId), ClientMessage);
/* 36:40 */       Composer.add(Integer.valueOf(User.x), ClientMessage);
/* 37:41 */       Composer.add(Integer.valueOf(User.y), ClientMessage);
/* 38:42 */       Composer.add(Float.toString(User.z).replace(',', '.'), ClientMessage);
/* 39:43 */       Composer.add(Integer.valueOf(User.RotHead.getRot()), ClientMessage);
/* 40:44 */       Composer.add(Integer.valueOf(User.RotBody.getRot()), ClientMessage);
/* 41:45 */       Composer.add(User.Status, ClientMessage);
/* 42:46 */       i++;
/* 43:   */     }
/* 44:49 */     ClientMessage.writeSaved(Integer.valueOf(i));
/* 45:50 */     Composer.endPacket(ClientMessage);
/* 46:51 */     return ClientMessage;
/* 47:   */   }
/* 48:   */   
/* 49:   */   public static final MessageWriter compose(Collection<LiveEntity> UserList)
/* 50:   */   {
/* 51:55 */     MessageWriter ClientMessage = new MessageWriter(100 + UserList.size() * 100);
/* 52:56 */     Composer.initPacket(HEADER, ClientMessage);
/* 53:57 */     Composer.add(Integer.valueOf(UserList.size()), ClientMessage);
/* 54:58 */     for (LiveEntity User : UserList)
/* 55:   */     {
/* 56:59 */       Composer.add(Short.valueOf(User.virtualId), ClientMessage);
/* 57:60 */       Composer.add(Integer.valueOf(User.x), ClientMessage);
/* 58:61 */       Composer.add(Integer.valueOf(User.y), ClientMessage);
/* 59:62 */       Composer.add(Float.toString(User.z).replace(',', '.'), ClientMessage);
/* 60:63 */       Composer.add(Integer.valueOf(User.RotHead.getRot()), ClientMessage);
/* 61:64 */       Composer.add(Integer.valueOf(User.RotBody.getRot()), ClientMessage);
/* 62:65 */       Composer.add(User.Status, ClientMessage);
/* 63:   */     }
/* 64:67 */     Composer.endPacket(ClientMessage);
/* 65:68 */     return ClientMessage;
/* 66:   */   }
/* 67:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.room.engine.UserUpdateComposer
 * JD-Core Version:    0.7.0.1
 */