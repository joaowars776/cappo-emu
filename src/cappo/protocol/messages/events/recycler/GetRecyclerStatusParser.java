/*  1:   */ package cappo.protocol.messages.events.recycler;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.QueueWriter;
/*  4:   */ import cappo.engine.player.Connection;
/*  5:   */ import cappo.game.collections.Utils;
/*  6:   */ import cappo.game.player.data.AvatarData;
/*  7:   */ import cappo.protocol.messages.IncomingMessageEvent;
/*  8:   */ import cappo.protocol.messages.composers.recycler.RecyclerStatusComposer;
/*  9:   */ 
/* 10:   */ public class GetRecyclerStatusParser
/* 11:   */   extends IncomingMessageEvent
/* 12:   */ {
/* 13:   */   public void messageReceived(Connection Main)
/* 14:   */   {
/* 15:18 */     int Status = 1;
/* 16:19 */     int TimeToWait = 0;
/* 17:20 */     if (Utils.getTimestamp() < Main.avatarData.EcotronNextTime)
/* 18:   */     {
/* 19:21 */       TimeToWait = (int)(Main.avatarData.EcotronNextTime - Utils.getTimestamp());
/* 20:22 */       Status = 3;
/* 21:   */     }
/* 22:25 */     QueueWriter.write(Main.socket, RecyclerStatusComposer.compose(Status, TimeToWait));
/* 23:   */   }
/* 24:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.recycler.GetRecyclerStatusParser
 * JD-Core Version:    0.7.0.1
 */