/*  1:   */ package cappo.protocol.messages.composers.room.engine;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.engine.threadpools.RoomTask;
/*  5:   */ import cappo.game.roomengine.Square;
/*  6:   */ import cappo.game.roomengine.gamemap.GameMapBase;
/*  7:   */ import cappo.protocol.messages.Composer;
/*  8:   */ import java.util.Map;
/*  9:   */ 
/* 10:   */ public class HeightMapComposer
/* 11:   */ {
/* 12:   */   public static int HEADER;
/* 13:   */   
/* 14:   */   public static final MessageWriter compose(RoomTask room)
/* 15:   */   {
/* 16:21 */     GameMapBase model = room.model;
/* 17:   */     
/* 18:23 */     int len = model.widthX * model.heightY;
/* 19:24 */     MessageWriter writer = new MessageWriter(14 + len * 2);
/* 20:25 */     Composer.initPacket(HEADER, writer);
/* 21:26 */     Composer.writeInt32(model.widthX, writer);
/* 22:27 */     Composer.writeInt32(len, writer);
/* 23:28 */     for (int xy = 0; xy < len; xy++)
/* 24:   */     {
/* 25:29 */       Square square = model.getSquare(xy);
/* 26:30 */       if (square == null)
/* 27:   */       {
/* 28:31 */         Composer.writeInt16(16384, writer);
/* 29:   */       }
/* 30:   */       else
/* 31:   */       {
/* 32:33 */         Float newZ = (Float)room.squareAbsoluteHeight.get(Integer.valueOf(xy));
/* 33:34 */         if (newZ == null) {
/* 34:36 */           Composer.writeInt16(16384, writer);
/* 35:   */         } else {
/* 36:38 */           Composer.writeInt16(newZ.intValue() * 256, writer);
/* 37:   */         }
/* 38:   */       }
/* 39:   */     }
/* 40:42 */     Composer.endPacket(writer);
/* 41:43 */     return writer;
/* 42:   */   }
/* 43:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.room.engine.HeightMapComposer
 * JD-Core Version:    0.7.0.1
 */