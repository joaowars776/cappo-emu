/*  1:   */ package cappo.protocol.messages.composers.games.gamecenter;
/*  2:   */ 
/*  3:   */ import cappo.engine.Server;
/*  4:   */ import cappo.engine.network.MessageWriter;
/*  5:   */ import cappo.protocol.messages.Composer;
/*  6:   */ 
/*  7:   */ public class LoadGameComposer
/*  8:   */ {
/*  9:   */   public static int HEADER;
/* 10:   */   
/* 11:   */   public static final MessageWriter compose(int GameId, String token)
/* 12:   */   {
/* 13:17 */     MessageWriter ClientMessage = new MessageWriter();
/* 14:18 */     Composer.initPacket(HEADER, ClientMessage);
/* 15:19 */     Composer.add(Integer.valueOf(GameId), ClientMessage);
/* 16:20 */     Composer.add(Long.toString(System.currentTimeMillis()), ClientMessage);
/* 17:21 */     Composer.add("http://dcr.lavvos.pl/FastFood.swf", ClientMessage);
/* 18:22 */     Composer.add("best", ClientMessage);
/* 19:23 */     Composer.add("showAll", ClientMessage);
/* 20:24 */     Composer.add(Integer.valueOf(60), ClientMessage);
/* 21:25 */     Composer.add(Integer.valueOf(10), ClientMessage);
/* 22:26 */     Composer.add(Integer.valueOf(0), ClientMessage);
/* 23:27 */     Composer.add(Integer.valueOf(5), ClientMessage);
/* 24:   */     
/* 25:   */ 
/* 26:30 */     Composer.add("accessToken", ClientMessage);
/* 27:31 */     Composer.add(token, ClientMessage);
/* 28:   */     
/* 29:   */ 
/* 30:34 */     Composer.add("gameServerHost", ClientMessage);
/* 31:35 */     Composer.add(Server.fastfoodIP, ClientMessage);
/* 32:   */     
/* 33:   */ 
/* 34:38 */     Composer.add("gameServerPort", ClientMessage);
/* 35:39 */     Composer.add(Server.fastfoodPORT, ClientMessage);
/* 36:   */     
/* 37:   */ 
/* 38:42 */     Composer.add("socketPolicyPort", ClientMessage);
/* 39:43 */     Composer.add("30843", ClientMessage);
/* 40:   */     
/* 41:   */ 
/* 42:46 */     Composer.add("assetUrl", ClientMessage);
/* 43:47 */     Composer.add("http://dcr.lavvos.pl/BasicAssets.swf", ClientMessage);
/* 44:   */     
/* 45:   */ 
/* 46:   */ 
/* 47:51 */     Composer.endPacket(ClientMessage);
/* 48:52 */     return ClientMessage;
/* 49:   */   }
/* 50:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.games.gamecenter.LoadGameComposer
 * JD-Core Version:    0.7.0.1
 */