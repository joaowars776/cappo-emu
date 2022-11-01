/*  1:   */ package cappo.protocol.messages.events.users;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.QueueWriter;
/*  4:   */ import cappo.engine.player.Connection;
/*  5:   */ import cappo.protocol.messages.IncomingMessageEvent;
/*  6:   */ import cappo.protocol.messages.composers.notifications.MOTDComposer;
/*  7:   */ 
/*  8:   */ public class GetMOTDParser
/*  9:   */   extends IncomingMessageEvent
/* 10:   */ {
/* 11:   */   public void messageReceived(Connection Main)
/* 12:   */   {
/* 13:18 */     if (!cappo.game.utils.lang.LangTexts.texts[5].isEmpty()) {
/* 14:19 */       QueueWriter.write(Main.socket, MOTDComposer.compose(new String[] { cappo.game.utils.lang.LangTexts.texts[5] }));
/* 15:   */     }
/* 16:   */   }
/* 17:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.users.GetMOTDParser
 * JD-Core Version:    0.7.0.1
 */