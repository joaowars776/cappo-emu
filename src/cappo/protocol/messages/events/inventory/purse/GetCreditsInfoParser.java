/*  1:   */ package cappo.protocol.messages.events.inventory.purse;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.QueueWriter;
/*  4:   */ import cappo.engine.player.Connection;
/*  5:   */ import cappo.protocol.messages.IncomingMessageEvent;
/*  6:   */ import cappo.protocol.messages.composers.inventory.purse.CreditBalanceComposer;
/*  7:   */ 
/*  8:   */ public class GetCreditsInfoParser
/*  9:   */   extends IncomingMessageEvent
/* 10:   */ {
/* 11:   */   public void messageReceived(Connection Main)
/* 12:   */   {
/* 13:17 */     QueueWriter.write(Main.socket, CreditBalanceComposer.compose(Main.credits));
/* 14:   */   }
/* 15:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.inventory.purse.GetCreditsInfoParser
 * JD-Core Version:    0.7.0.1
 */