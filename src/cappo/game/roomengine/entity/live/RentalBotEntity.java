/*  1:   */ package cappo.game.roomengine.entity.live;
/*  2:   */ 
/*  3:   */ import cappo.engine.threadpools.RoomTask;
/*  4:   */ import cappo.game.bots.RentalBot;
/*  5:   */ import cappo.game.collections.Utils;
/*  6:   */ import cappo.game.roomengine.gamemap.GameMapBase;
/*  7:   */ 
/*  8:   */ public class RentalBotEntity
/*  9:   */   extends LiveEntity
/* 10:   */ {
/* 11:   */   public RentalBot botData;
/* 12: 9 */   private long ticks = 0L;
/* 13:10 */   private long nextTick = 0L;
/* 14:   */   
/* 15:   */   public RentalBotEntity(RoomTask room, short virtualId)
/* 16:   */   {
/* 17:13 */     super(room, virtualId);
/* 18:   */   }
/* 19:   */   
/* 20:   */   public void OnSelfEnterRoom(RoomTask room)
/* 21:   */   {
/* 22:17 */     moveTo(Utils.GetRandomNumber(0, room.model.widthX), Utils.GetRandomNumber(0, room.model.heightY));
/* 23:   */   }
/* 24:   */   
/* 25:   */   public void OnTimerTick(RoomTask room)
/* 26:   */   {
/* 27:21 */     if (++this.ticks < this.nextTick) {
/* 28:22 */       return;
/* 29:   */     }
/* 30:24 */     this.nextTick = (this.ticks + 2L);
/* 31:26 */     if ((this.botData.walkRandomEnabled) && 
/* 32:27 */       (Utils.GetRandomNumber(0, 10) < 2)) {
/* 33:28 */       moveTo(Utils.GetRandomNumber(1, room.model.widthX), Utils.GetRandomNumber(1, room.model.heightY));
/* 34:   */     }
/* 35:32 */     if ((this.botData.chatAuto) && 
/* 36:33 */       (this.ticks >= this.botData.nextChat))
/* 37:   */     {
/* 38:34 */       String txt = this.botData.getSpeech();
/* 39:35 */       if ((txt != null) && (!txt.isEmpty())) {
/* 40:36 */         say(txt, 2, -1, false);
/* 41:   */       }
/* 42:38 */       this.botData.nextChat = (this.ticks + this.botData.chatDelay * 2);
/* 43:   */     }
/* 44:   */   }
/* 45:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.roomengine.entity.live.RentalBotEntity
 * JD-Core Version:    0.7.0.1
 */