/*  1:   */ package cappo.protocol.messages.events.inventory.badges;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageReader;
/*  4:   */ import cappo.engine.player.Connection;
/*  5:   */ import cappo.engine.threadpools.RoomTask;
/*  6:   */ import cappo.game.collections.Badge;
/*  7:   */ import cappo.game.player.PlayerData;
/*  8:   */ import cappo.game.roomengine.entity.live.Avatar;
/*  9:   */ import cappo.protocol.messages.IncomingMessageEvent;
/* 10:   */ import cappo.protocol.messages.composers.users.UserBadgesComposer;
/* 11:   */ import java.util.Map;
/* 12:   */ 
/* 13:   */ public class SetActivatedBadgesParser
/* 14:   */   extends IncomingMessageEvent
/* 15:   */ {
/* 16:   */   public static final int SLOTS = 5;
/* 17:   */   
/* 18:   */   public void messageReceived(Connection Main)
/* 19:   */   {
/* 20:20 */     for (Badge badge : Main.badgesSelected.values())
/* 21:   */     {
/* 22:21 */       badge.badgeSlot = 0;
/* 23:22 */       badge.needInsert = true;
/* 24:   */     }
/* 25:24 */     Main.badgesSelected.clear();
/* 26:26 */     for (int i = 0; i < 5; i++)
/* 27:   */     {
/* 28:27 */       int slot = Main.currentPacket.readInt();
/* 29:28 */       Badge badge = (Badge)Main.badges.get(Main.currentPacket.readString());
/* 30:29 */       if (badge != null)
/* 31:   */       {
/* 32:30 */         badge.badgeSlot = slot;
/* 33:31 */         badge.needInsert = true;
/* 34:32 */         Main.badgesSelected.put(Integer.valueOf(badge.badgeSlot), badge);
/* 35:   */       }
/* 36:   */     }
/* 37:36 */     Avatar avatar = Main.avatar;
/* 38:37 */     if (avatar == null) {
/* 39:38 */       return;
/* 40:   */     }
/* 41:41 */     avatar.room.sendMessage(UserBadgesComposer.compose(Main.playerData.userId, Main.badgesSelected.values()));
/* 42:   */   }
/* 43:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.inventory.badges.SetActivatedBadgesParser
 * JD-Core Version:    0.7.0.1
 */