/*  1:   */ package cappo.protocol.messages.events.navigator;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.QueueWriter;
/*  4:   */ import cappo.engine.player.Connection;
/*  5:   */ import cappo.protocol.messages.IncomingMessageEvent;
/*  6:   */ import cappo.protocol.messages.composers.navigator.PopularRoomTagsResultComposer;
/*  7:   */ import java.util.HashMap;
/*  8:   */ import java.util.Map;
/*  9:   */ 
/* 10:   */ public class GetPopularRoomTagsParser
/* 11:   */   extends IncomingMessageEvent
/* 12:   */ {
/* 13:18 */   static final Map<String, Integer> tmp = new HashMap(0);
/* 14:   */   
/* 15:   */   public void messageReceived(Connection Main)
/* 16:   */   {
/* 17:24 */     QueueWriter.write(Main.socket, PopularRoomTagsResultComposer.compose(tmp));
/* 18:   */   }
/* 19:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.navigator.GetPopularRoomTagsParser
 * JD-Core Version:    0.7.0.1
 */