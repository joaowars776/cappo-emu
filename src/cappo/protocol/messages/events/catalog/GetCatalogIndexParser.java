/*  1:   */ package cappo.protocol.messages.events.catalog;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.engine.network.QueueWriter;
/*  5:   */ import cappo.engine.player.Connection;
/*  6:   */ import cappo.game.catalog.Catalog;
/*  7:   */ import cappo.game.player.PlayerData;
/*  8:   */ import cappo.protocol.messages.IncomingMessageEvent;
/*  9:   */ import cappo.protocol.messages.composers.catalog.CatalogIndexComposer;
/* 10:   */ import java.util.Map;
/* 11:   */ 
/* 12:   */ public class GetCatalogIndexParser
/* 13:   */   extends IncomingMessageEvent
/* 14:   */ {
/* 15:   */   public void messageReceived(Connection cn)
/* 16:   */   {
/* 17:19 */     if (Catalog.isBlocked) {
/* 18:20 */       return;
/* 19:   */     }
/* 20:23 */     MessageWriter response = (MessageWriter)Catalog.indexMap.get(Integer.valueOf(cn.playerData.staffLevel));
/* 21:24 */     if (response == null)
/* 22:   */     {
/* 23:25 */       response = CatalogIndexComposer.compose(cn.playerData.staffLevel, "NORMAL");
/* 24:26 */       Catalog.indexMap.put(Integer.valueOf(cn.playerData.staffLevel), response);
/* 25:   */     }
/* 26:29 */     QueueWriter.write(cn.socket, response);
/* 27:   */   }
/* 28:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.catalog.GetCatalogIndexParser
 * JD-Core Version:    0.7.0.1
 */