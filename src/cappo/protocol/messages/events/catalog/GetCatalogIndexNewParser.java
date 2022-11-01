/*  1:   */ package cappo.protocol.messages.events.catalog;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageReader;
/*  4:   */ import cappo.engine.network.MessageWriter;
/*  5:   */ import cappo.engine.network.QueueWriter;
/*  6:   */ import cappo.engine.player.Connection;
/*  7:   */ import cappo.game.catalog.Catalog;
/*  8:   */ import cappo.game.player.PlayerData;
/*  9:   */ import cappo.protocol.messages.IncomingMessageEvent;
/* 10:   */ import cappo.protocol.messages.composers.catalog.CatalogIndexComposer;
/* 11:   */ import java.util.Map;
/* 12:   */ 
/* 13:   */ public class GetCatalogIndexNewParser
/* 14:   */   extends IncomingMessageEvent
/* 15:   */ {
/* 16:   */   public void messageReceived(Connection cn)
/* 17:   */   {
/* 18:22 */     if (Catalog.isBlocked) {
/* 19:23 */       return;
/* 20:   */     }
/* 21:27 */     String catalogType = cn.currentPacket.readString();
/* 22:   */     
/* 23:29 */     MessageWriter response = (MessageWriter)Catalog.indexMap.get(Integer.valueOf(cn.playerData.staffLevel));
/* 24:30 */     if (response == null)
/* 25:   */     {
/* 26:31 */       response = CatalogIndexComposer.compose(cn.playerData.staffLevel, catalogType);
/* 27:32 */       Catalog.indexMap.put(Integer.valueOf(cn.playerData.staffLevel), response);
/* 28:   */     }
/* 29:35 */     QueueWriter.write(cn.socket, response);
/* 30:   */   }
/* 31:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.catalog.GetCatalogIndexNewParser
 * JD-Core Version:    0.7.0.1
 */