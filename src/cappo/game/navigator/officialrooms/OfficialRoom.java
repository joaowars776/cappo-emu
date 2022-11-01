/*  1:   */ package cappo.game.navigator.officialrooms;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.engine.threadpools.RoomTask;
/*  5:   */ import cappo.game.roomengine.RoomData;
/*  6:   */ import cappo.game.roomengine.RoomManager;
/*  7:   */ import cappo.protocol.messages.Composer;
/*  8:   */ import cappo.protocol.messages.composers.serializers.SerializeRoom;
/*  9:   */ import java.sql.ResultSet;
/* 10:   */ 
/* 11:   */ public class OfficialRoom
/* 12:   */   extends Official
/* 13:   */ {
/* 14:   */   public int roomId;
/* 15:   */   
/* 16:   */   public OfficialRoom(ResultSet data)
/* 17:   */     throws Exception
/* 18:   */   {
/* 19:15 */     super(data);
/* 20:   */     
/* 21:17 */     this.roomId = Integer.parseInt(data.getString("extra"));
/* 22:   */   }
/* 23:   */   
/* 24:   */   public void compose(MessageWriter clientMessage)
/* 25:   */     throws Exception
/* 26:   */   {
/* 27:22 */     RoomData room = RoomManager.getRoom(this.roomId);
/* 28:23 */     if (room == null)
/* 29:   */     {
/* 30:24 */       room = RoomManager.loadRoom(this.roomId);
/* 31:25 */       if (room == null) {
/* 32:26 */         throw new Exception("Not found room:" + this.roomId);
/* 33:   */       }
/* 34:   */     }
/* 35:29 */     Composer.add(Integer.valueOf(room.room != null ? room.room.userCount : 0), clientMessage);
/* 36:30 */     Composer.add(Integer.valueOf(this.type), clientMessage);
/* 37:31 */     SerializeRoom.parse(clientMessage, room);
/* 38:   */   }
/* 39:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.navigator.officialrooms.OfficialRoom
 * JD-Core Version:    0.7.0.1
 */