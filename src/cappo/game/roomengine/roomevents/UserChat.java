/*  1:   */ package cappo.game.roomengine.roomevents;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.engine.threadpools.RoomTask;
/*  5:   */ 
/*  6:   */ public class UserChat
/*  7:   */   extends Event
/*  8:   */ {
/*  9:   */   private MessageWriter message;
/* 10:   */   private boolean isTalking;
/* 11:   */   
/* 12:   */   public void run(RoomTask room)
/* 13:   */   {
/* 14:18 */     if (this.isTalking)
/* 15:   */     {
/* 16:19 */       this.isTalking = false;
/* 17:20 */       room.sendMessage(this.message);
/* 18:   */     }
/* 19:   */   }
/* 20:   */   
/* 21:   */   public void talk(RoomTask room, MessageWriter msg)
/* 22:   */   {
/* 23:25 */     if (!this.isTalking)
/* 24:   */     {
/* 25:26 */       this.isTalking = true;
/* 26:   */       
/* 27:28 */       this.message = msg;
/* 28:29 */       room.addUserEvent(this, 0);
/* 29:   */     }
/* 30:   */   }
/* 31:   */   
/* 32:   */   public void stop(RoomTask room)
/* 33:   */   {
/* 34:34 */     this.isTalking = false;
/* 35:   */   }
/* 36:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.roomengine.roomevents.UserChat
 * JD-Core Version:    0.7.0.1
 */