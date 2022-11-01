/*  1:   */ package cappo.protocol.messages.composers.room.engine;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.game.roomengine.Square;
/*  5:   */ import cappo.game.roomengine.gamemap.GameMapBase;
/*  6:   */ import cappo.protocol.messages.Composer;
/*  7:   */ 
/*  8:   */ public class FloorHeightMapComposer
/*  9:   */ {
/* 10:   */   public static int HEADER;
/* 11:   */   
/* 12:   */   public static final MessageWriter compose(GameMapBase model)
/* 13:   */   {
/* 14:21 */     boolean scale = true;
/* 15:   */     
/* 16:23 */     int len = model.widthX * model.heightY;
/* 17:24 */     int packetlen = len + (model.heightY - 1);
/* 18:25 */     MessageWriter writer = new MessageWriter(9 + packetlen);
/* 19:26 */     Composer.initPacket(HEADER, writer);
/* 20:27 */     Composer.writeBoolean(scale, writer);
/* 21:28 */     Composer.writeInt16(packetlen, writer);
/* 22:29 */     for (int xy = 0; xy < len; xy++)
/* 23:   */     {
/* 24:30 */       if ((xy > 0) && (xy % model.widthX == 0)) {
/* 25:31 */         Composer.writeChar('\r', writer);
/* 26:   */       }
/* 27:34 */       Square square = model.getSquare(xy);
/* 28:   */       char c;
/* 29:   */       char c;
/* 30:36 */       if (square == null)
/* 31:   */       {
/* 32:37 */         c = 'x';
/* 33:   */       }
/* 34:   */       else
/* 35:   */       {
/* 36:39 */         int z = (int)square.height;
/* 37:40 */         c = Integer.toString(z, 36).charAt(0);
/* 38:   */       }
/* 39:42 */       Composer.writeChar(c, writer);
/* 40:   */     }
/* 41:44 */     Composer.endPacket(writer);
/* 42:   */     
/* 43:46 */     return writer;
/* 44:   */   }
/* 45:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.room.engine.FloorHeightMapComposer
 * JD-Core Version:    0.7.0.1
 */