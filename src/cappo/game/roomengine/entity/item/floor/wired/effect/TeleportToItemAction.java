/*  1:   */ package cappo.game.roomengine.entity.item.floor.wired.effect;
/*  2:   */ 
/*  3:   */ import cappo.engine.logging.Log;
/*  4:   */ import cappo.engine.player.Connection;
/*  5:   */ import cappo.engine.threadpools.RoomTask;
/*  6:   */ import cappo.game.roomengine.entity.item.floor.FloorItem;
/*  7:   */ import cappo.game.roomengine.entity.live.Avatar;
/*  8:   */ import cappo.game.roomengine.gamemap.GameMapBase;
/*  9:   */ import cappo.game.roomengine.roomevents.wired.TeleportToEvent;
/* 10:   */ import java.util.Map;
/* 11:   */ import java.util.Set;
/* 12:   */ 
/* 13:   */ public class TeleportToItemAction
/* 14:   */   extends WiredEffectBase
/* 15:   */ {
/* 16:   */   public int getCode()
/* 17:   */   {
/* 18:14 */     return 0;
/* 19:   */   }
/* 20:   */   
/* 21:   */   public boolean needUser()
/* 22:   */   {
/* 23:19 */     return true;
/* 24:   */   }
/* 25:   */   
/* 26:   */   public void invoke(Connection invoker)
/* 27:   */   {
/* 28:24 */     if (this.delayEffect > 0) {
/* 29:25 */       getRoom().addItemEvent(new TeleportToEvent(this, invoker), this.delayEffect);
/* 30:   */     } else {
/* 31:27 */       doEffect(this, invoker);
/* 32:   */     }
/* 33:   */   }
/* 34:   */   
/* 35:   */   public static void doEffect(TeleportToItemAction wired, Connection invoker)
/* 36:   */   {
/* 37:32 */     if (!wired.items.isEmpty())
/* 38:   */     {
/* 39:33 */       Object[] keys = wired.items.keySet().toArray();
/* 40:34 */       Integer randKey = (Integer)keys[cappo.game.collections.Utils.GetRandomNumber(0, keys.length - 1)];
/* 41:35 */       FloorItem item = (FloorItem)wired.items.get(randKey);
/* 42:36 */       if (item == null) {
/* 43:37 */         return;
/* 44:   */       }
/* 45:40 */       RoomTask room = item.getRoom();
/* 46:41 */       if (room == null) {
/* 47:42 */         return;
/* 48:   */       }
/* 49:45 */       Avatar avatar = invoker.avatar;
/* 50:   */       
/* 51:47 */       avatar.clearMovement();
/* 52:49 */       if (item.getRoom().model != null)
/* 53:   */       {
/* 54:50 */         avatar.x = item.getX();
/* 55:51 */         avatar.y = item.getY();
/* 56:52 */         int xy = item.getX() + item.getY() * item.getRoom().model.widthX;
/* 57:   */         
/* 58:54 */         room.entityWalk(xy, avatar, true);
/* 59:55 */         room.entityWalk(avatar.xy, avatar, false);
/* 60:56 */         avatar.xy = xy;
/* 61:   */       }
/* 62:   */       else
/* 63:   */       {
/* 64:58 */         Log.printLog("Critic: TeleportTo NULL OBJECT");
/* 65:59 */         return;
/* 66:   */       }
/* 67:61 */       avatar.z = item.getZ();
/* 68:62 */       wired.getRoom().updateUserStatus(avatar, true);
/* 69:63 */       wired.getRoom().userUpdateNeeded(avatar);
/* 70:   */     }
/* 71:   */   }
/* 72:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.roomengine.entity.item.floor.wired.effect.TeleportToItemAction
 * JD-Core Version:    0.7.0.1
 */