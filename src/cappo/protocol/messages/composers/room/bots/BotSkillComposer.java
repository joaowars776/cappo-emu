/*  1:   */ package cappo.protocol.messages.composers.room.bots;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.protocol.messages.Composer;
/*  5:   */ 
/*  6:   */ public class BotSkillComposer
/*  7:   */ {
/*  8:   */   public static int HEADER;
/*  9:   */   
/* 10:   */   public static final MessageWriter compose(int botId, int skillId, String data)
/* 11:   */   {
/* 12:16 */     MessageWriter clientMessage = new MessageWriter();
/* 13:17 */     Composer.initPacket(HEADER, clientMessage);
/* 14:18 */     Composer.add(Integer.valueOf(botId), clientMessage);
/* 15:19 */     Composer.add(Integer.valueOf(skillId), clientMessage);
/* 16:20 */     Composer.add(data, clientMessage);
/* 17:21 */     Composer.endPacket(clientMessage);
/* 18:22 */     return clientMessage;
/* 19:   */   }
/* 20:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.room.bots.BotSkillComposer
 * JD-Core Version:    0.7.0.1
 */