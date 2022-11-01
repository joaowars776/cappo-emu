/*  1:   */ package cappo.protocol.messages.composers.users;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.game.collections.Badge;
/*  5:   */ import cappo.protocol.messages.Composer;
/*  6:   */ import java.util.Collection;
/*  7:   */ 
/*  8:   */ public class UserBadgesComposer
/*  9:   */ {
/* 10:   */   public static int HEADER;
/* 11:   */   
/* 12:   */   public static final MessageWriter compose(int ClientId, Collection<Badge> BadgesSelected)
/* 13:   */   {
/* 14:19 */     MessageWriter ClientMessage = new MessageWriter();
/* 15:20 */     Composer.initPacket(HEADER, ClientMessage);
/* 16:21 */     Composer.add(Integer.valueOf(ClientId), ClientMessage);
/* 17:22 */     Composer.add(Integer.valueOf(BadgesSelected.size()), ClientMessage);
/* 18:23 */     for (Badge Badge : BadgesSelected)
/* 19:   */     {
/* 20:24 */       Composer.add(Integer.valueOf(0), ClientMessage);
/* 21:25 */       Composer.add(Badge.badgeCode, ClientMessage);
/* 22:   */     }
/* 23:27 */     Composer.endPacket(ClientMessage);
/* 24:28 */     return ClientMessage;
/* 25:   */   }
/* 26:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.users.UserBadgesComposer
 * JD-Core Version:    0.7.0.1
 */