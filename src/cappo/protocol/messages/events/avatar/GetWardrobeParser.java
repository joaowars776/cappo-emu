/*  1:   */ package cappo.protocol.messages.events.avatar;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.QueueWriter;
/*  4:   */ import cappo.engine.player.Connection;
/*  5:   */ import cappo.protocol.messages.IncomingMessageEvent;
/*  6:   */ import cappo.protocol.messages.composers.avatar.WardrobeComposer;
/*  7:   */ import java.util.Map;
/*  8:   */ 
/*  9:   */ public class GetWardrobeParser
/* 10:   */   extends IncomingMessageEvent
/* 11:   */ {
/* 12:   */   public void messageReceived(Connection Main)
/* 13:   */   {
/* 14:17 */     QueueWriter.write(Main.socket, WardrobeComposer.compose(2, Main.Wardrobes.values()));
/* 15:   */   }
/* 16:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.avatar.GetWardrobeParser
 * JD-Core Version:    0.7.0.1
 */