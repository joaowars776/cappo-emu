/*  1:   */ package cappo.protocol.messages.events.avatar;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageReader;
/*  4:   */ import cappo.engine.player.Connection;
/*  5:   */ import cappo.game.collections.Wardrobe;
/*  6:   */ import cappo.game.player.AvatarLook;
/*  7:   */ import cappo.protocol.messages.IncomingMessageEvent;
/*  8:   */ import java.util.Map;
/*  9:   */ 
/* 10:   */ public class SaveWardrobeOutfitParser
/* 11:   */   extends IncomingMessageEvent
/* 12:   */ {
/* 13:   */   public void messageReceived(Connection Main)
/* 14:   */   {
/* 15:17 */     int slotId = Main.currentPacket.readInt();
/* 16:18 */     String SelectedLook = Main.currentPacket.readString();
/* 17:20 */     if (!AvatarLook.validateLook(SelectedLook)) {
/* 18:21 */       SelectedLook = "hr-115-42.hd-190-1.ch-215-62.lg-285-91.sh-290-62";
/* 19:   */     }
/* 20:24 */     Wardrobe wrb = new Wardrobe(slotId, SelectedLook, Main.currentPacket.readString().equals("M") ? 1 : 0);
/* 21:25 */     wrb.needInsert = true;
/* 22:26 */     Main.Wardrobes.put(Short.valueOf(wrb.slotId), wrb);
/* 23:   */   }
/* 24:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.avatar.SaveWardrobeOutfitParser
 * JD-Core Version:    0.7.0.1
 */