/*  1:   */ package cappo.protocol.messages.composers.roomsettings;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.game.player.PlayerData;
/*  5:   */ import cappo.game.roomengine.settings.PlayerRight;
/*  6:   */ import cappo.protocol.messages.Composer;
/*  7:   */ import cappo.protocol.messages.composers.serializers.SerializeFlatController;
/*  8:   */ import java.util.Collection;
/*  9:   */ 
/* 10:   */ public class FlatControllersComposer
/* 11:   */ {
/* 12:   */   public static int HEADER;
/* 13:   */   
/* 14:   */   public static MessageWriter compose(int roomId, Collection<PlayerRight> controllers)
/* 15:   */   {
/* 16:19 */     MessageWriter ClientMessage = new MessageWriter();
/* 17:20 */     Composer.initPacket(HEADER, ClientMessage);
/* 18:21 */     Composer.add(Integer.valueOf(roomId), ClientMessage);
/* 19:22 */     Composer.add(Integer.valueOf(controllers.size()), ClientMessage);
/* 20:23 */     for (PlayerRight right : controllers) {
/* 21:24 */       SerializeFlatController.parse(ClientMessage, right.player.userId, right.player.userName);
/* 22:   */     }
/* 23:26 */     Composer.endPacket(ClientMessage);
/* 24:27 */     return ClientMessage;
/* 25:   */   }
/* 26:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.roomsettings.FlatControllersComposer
 * JD-Core Version:    0.7.0.1
 */