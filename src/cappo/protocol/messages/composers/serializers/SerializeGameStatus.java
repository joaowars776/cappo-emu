/*  1:   */ package cappo.protocol.messages.composers.serializers;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.game.games.snowwar.SnowWarRoom;
/*  5:   */ import cappo.game.games.snowwar.gameevents.AddBallToMachine;
/*  6:   */ import cappo.game.games.snowwar.gameevents.BallThrowToHuman;
/*  7:   */ import cappo.game.games.snowwar.gameevents.BallThrowToPosition;
/*  8:   */ import cappo.game.games.snowwar.gameevents.CreateSnowBall;
/*  9:   */ import cappo.game.games.snowwar.gameevents.Event;
/* 10:   */ import cappo.game.games.snowwar.gameevents.MakeSnowBall;
/* 11:   */ import cappo.game.games.snowwar.gameevents.PickBallFromGameItem;
/* 12:   */ import cappo.game.games.snowwar.gameevents.PlayerLeft;
/* 13:   */ import cappo.game.games.snowwar.gameevents.UserMove;
/* 14:   */ import cappo.protocol.messages.Composer;
/* 15:   */ 
/* 16:   */ public class SerializeGameStatus
/* 17:   */ {
/* 18:   */   public static void parse(MessageWriter ClientMessage, SnowWarRoom arena, boolean isFull)
/* 19:   */   {
/* 20:24 */     int i = 0;
/* 21:   */     
/* 22:26 */     Composer.add(Integer.valueOf(arena.Turn), ClientMessage);
/* 23:27 */     Composer.add(Integer.valueOf(seed(arena.Turn) + arena.checksum), ClientMessage);
/* 24:   */     
/* 25:29 */     Composer.add(Integer.valueOf(1), ClientMessage);
/* 26:   */     
/* 27:31 */     Composer.add(ClientMessage.setSaved(Integer.valueOf(0)), ClientMessage);
/* 28:32 */     for (Event evt : arena.gameEvents)
/* 29:   */     {
/* 30:33 */       Composer.add(Integer.valueOf(evt.EventType), ClientMessage);
/* 31:34 */       if (evt.EventType == 1) {
/* 32:35 */         SerializeGame2EventPlayerLeft.parse(ClientMessage, (PlayerLeft)evt);
/* 33:36 */       } else if (evt.EventType == 2) {
/* 34:37 */         SerializeGame2EventMove.parse(ClientMessage, (UserMove)evt);
/* 35:38 */       } else if (evt.EventType == 7) {
/* 36:39 */         SerializeGame2EventPickSnowBall.parse(ClientMessage, (MakeSnowBall)evt);
/* 37:40 */       } else if (evt.EventType == 8) {
/* 38:41 */         SerializeGame2EventCreateSnowBall.parse(ClientMessage, (CreateSnowBall)evt);
/* 39:42 */       } else if (evt.EventType == 4) {
/* 40:43 */         SerializeGame2EventBallThrowToPosition.parse(ClientMessage, (BallThrowToPosition)evt);
/* 41:44 */       } else if (evt.EventType == 3) {
/* 42:45 */         SerializeGame2EventBallThrowToHuman.parse(ClientMessage, (BallThrowToHuman)evt);
/* 43:46 */       } else if (evt.EventType == 12) {
/* 44:47 */         SerializeGame2EventPickBallFromGameItem.parse(ClientMessage, (PickBallFromGameItem)evt);
/* 45:48 */       } else if (evt.EventType == 11) {
/* 46:49 */         SerializeGame2EventAddBallToMachine.parse(ClientMessage, (AddBallToMachine)evt);
/* 47:   */       } else {
/* 48:51 */         throw new UnsupportedOperationException("Not yet implemented");
/* 49:   */       }
/* 50:54 */       if (!isFull) {
/* 51:55 */         evt.apply();
/* 52:   */       }
/* 53:58 */       i++;
/* 54:   */     }
/* 55:60 */     ClientMessage.writeSaved(Integer.valueOf(i));
/* 56:   */   }
/* 57:   */   
/* 58:   */   public static int seed(int Turn)
/* 59:   */   {
/* 60:66 */     if (Turn == 0) {
/* 61:67 */       Turn = -1;
/* 62:   */     }
/* 63:69 */     int k = Turn << 13;
/* 64:70 */     Turn ^= k;
/* 65:71 */     k = Turn >> 17;
/* 66:72 */     Turn ^= k;
/* 67:73 */     k = Turn << 5;
/* 68:74 */     Turn ^= k;
/* 69:75 */     return Turn;
/* 70:   */   }
/* 71:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.serializers.SerializeGameStatus
 * JD-Core Version:    0.7.0.1
 */