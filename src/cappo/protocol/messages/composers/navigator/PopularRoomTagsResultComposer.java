/*  1:   */ package cappo.protocol.messages.composers.navigator;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.protocol.messages.Composer;
/*  5:   */ import java.util.Map;
/*  6:   */ 
/*  7:   */ public class PopularRoomTagsResultComposer
/*  8:   */ {
/*  9:   */   public static int HEADER;
/* 10:   */   
/* 11:   */   public static final MessageWriter compose(Map<String, Integer> PopularTags)
/* 12:   */   {
/* 13:18 */     MessageWriter ClientMessage = new MessageWriter(50 + PopularTags.size() * 30);
/* 14:19 */     Composer.initPacket(HEADER, ClientMessage);
/* 15:20 */     Composer.add(Integer.valueOf(PopularTags.size()), ClientMessage);
/* 16:21 */     for (String key : PopularTags.keySet())
/* 17:   */     {
/* 18:22 */       Composer.add(key, ClientMessage);
/* 19:23 */       Composer.add(PopularTags.get(key), ClientMessage);
/* 20:   */     }
/* 21:25 */     Composer.endPacket(ClientMessage);
/* 22:26 */     return ClientMessage;
/* 23:   */   }
/* 24:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.navigator.PopularRoomTagsResultComposer
 * JD-Core Version:    0.7.0.1
 */