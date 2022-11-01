/*  1:   */ package cappo.protocol.messages.composers.serializers;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.game.bots.RentalBot;
/*  5:   */ import cappo.game.player.AvatarLook;
/*  6:   */ import cappo.protocol.messages.Composer;
/*  7:   */ 
/*  8:   */ public class SerializeBot
/*  9:   */ {
/* 10:   */   public static void parse(MessageWriter ClientMessage, RentalBot bot)
/* 11:   */   {
/* 12:15 */     Composer.add(Integer.valueOf(bot.id), ClientMessage);
/* 13:16 */     Composer.add(bot.name, ClientMessage);
/* 14:17 */     Composer.add(bot.motto, ClientMessage);
/* 15:18 */     Composer.add(bot.gender, ClientMessage);
/* 16:19 */     Composer.add(bot.botLook.toString(), ClientMessage);
/* 17:   */   }
/* 18:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.serializers.SerializeBot
 * JD-Core Version:    0.7.0.1
 */