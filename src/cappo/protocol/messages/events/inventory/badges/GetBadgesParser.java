/*  1:   */ package cappo.protocol.messages.events.inventory.badges;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.QueueWriter;
/*  4:   */ import cappo.engine.player.Connection;
/*  5:   */ import cappo.protocol.messages.IncomingMessageEvent;
/*  6:   */ import cappo.protocol.messages.composers.inventory.badges.BadgesComposer;
/*  7:   */ import java.util.Map;
/*  8:   */ 
/*  9:   */ public class GetBadgesParser
/* 10:   */   extends IncomingMessageEvent
/* 11:   */ {
/* 12:   */   public void messageReceived(Connection Main)
/* 13:   */   {
/* 14:17 */     QueueWriter.write(Main.socket, BadgesComposer.compose(Main.badges.values(), Main.badgesSelected.values()));
/* 15:   */   }
/* 16:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.inventory.badges.GetBadgesParser
 * JD-Core Version:    0.7.0.1
 */