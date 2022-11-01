/*  1:   */ package cappo.protocol.messages.events.navigator;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageReader;
/*  4:   */ import cappo.engine.network.QueueWriter;
/*  5:   */ import cappo.engine.player.Connection;
/*  6:   */ import cappo.protocol.messages.IncomingMessageEvent;
/*  7:   */ import cappo.protocol.messages.composers.navigator.FavouriteChangedComposer;
/*  8:   */ import java.util.Map;
/*  9:   */ 
/* 10:   */ public class DeleteFavouriteRoomParser
/* 11:   */   extends IncomingMessageEvent
/* 12:   */ {
/* 13:   */   public void messageReceived(Connection Main)
/* 14:   */   {
/* 15:17 */     int roomId = Main.currentPacket.readInt();
/* 16:18 */     Main.favoriteRooms.remove(Integer.valueOf(roomId));
/* 17:19 */     QueueWriter.write(Main.socket, FavouriteChangedComposer.compose(roomId, Boolean.valueOf(false)));
/* 18:   */   }
/* 19:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.navigator.DeleteFavouriteRoomParser
 * JD-Core Version:    0.7.0.1
 */