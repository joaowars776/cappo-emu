/*  1:   */ package cappo.protocol.messages.composers.inventory.badges;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.game.collections.Badge;
/*  5:   */ import cappo.protocol.messages.Composer;
/*  6:   */ import java.util.Collection;
/*  7:   */ 
/*  8:   */ public class BadgesComposer
/*  9:   */ {
/* 10:   */   public static int HEADER;
/* 11:   */   
/* 12:   */   public static final MessageWriter compose(Collection<Badge> Badges, Collection<Badge> BadgesSelected)
/* 13:   */   {
/* 14:19 */     MessageWriter ClientMessage = new MessageWriter(500 + Badges.size() * 15);
/* 15:20 */     Composer.initPacket(HEADER, ClientMessage);
/* 16:21 */     Composer.add(Integer.valueOf(Badges.size()), ClientMessage);
/* 17:22 */     for (Badge Badge : Badges)
/* 18:   */     {
/* 19:23 */       Composer.add(Integer.valueOf(Badge.badgeId), ClientMessage);
/* 20:24 */       Composer.add(Badge.badgeCode, ClientMessage);
/* 21:   */     }
/* 22:26 */     Composer.add(Integer.valueOf(BadgesSelected.size()), ClientMessage);
/* 23:27 */     for (Badge Badge : BadgesSelected)
/* 24:   */     {
/* 25:28 */       Composer.add(Integer.valueOf(0), ClientMessage);
/* 26:29 */       Composer.add(Badge.badgeCode, ClientMessage);
/* 27:   */     }
/* 28:31 */     Composer.endPacket(ClientMessage);
/* 29:32 */     return ClientMessage;
/* 30:   */   }
/* 31:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.inventory.badges.BadgesComposer
 * JD-Core Version:    0.7.0.1
 */