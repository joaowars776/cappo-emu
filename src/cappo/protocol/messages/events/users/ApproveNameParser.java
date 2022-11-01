/*  1:   */ package cappo.protocol.messages.events.users;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageReader;
/*  4:   */ import cappo.engine.network.QueueWriter;
/*  5:   */ import cappo.engine.player.Connection;
/*  6:   */ import cappo.protocol.messages.IncomingMessageEvent;
/*  7:   */ import cappo.protocol.messages.composers.users.ApproveNameComposer;
/*  8:   */ 
/*  9:   */ public class ApproveNameParser
/* 10:   */   extends IncomingMessageEvent
/* 11:   */ {
/* 12:   */   public void messageReceived(Connection Main)
/* 13:   */   {
/* 14:17 */     String PetName = Main.currentPacket.readString();
/* 15:18 */     int len = PetName.length();
/* 16:19 */     if (len < 3) {
/* 17:20 */       QueueWriter.write(Main.socket, ApproveNameComposer.compose(2, "3"));
/* 18:21 */     } else if (len > 15) {
/* 19:22 */       QueueWriter.write(Main.socket, ApproveNameComposer.compose(1, "15"));
/* 20:23 */     } else if (!ValidPetNameChars(PetName, len)) {
/* 21:24 */       QueueWriter.write(Main.socket, ApproveNameComposer.compose(3, ""));
/* 22:   */     } else {
/* 23:26 */       QueueWriter.write(Main.socket, ApproveNameComposer.compose(0, ""));
/* 24:   */     }
/* 25:   */   }
/* 26:   */   
/* 27:   */   private boolean ValidPetNameChars(String inputStr, int HardCodedLen)
/* 28:   */   {
/* 29:31 */     for (int i = 0; i < HardCodedLen; i++)
/* 30:   */     {
/* 31:32 */       if ((i == 0) && 
/* 32:33 */         (inputStr.charAt(0) == ' ')) {
/* 33:34 */         return false;
/* 34:   */       }
/* 35:37 */       if (((inputStr.charAt(i) < 'a') || (inputStr.charAt(i) > 'z')) && ((inputStr.charAt(i) < '0') || (inputStr.charAt(i) > '9'))) {
/* 36:38 */         return false;
/* 37:   */       }
/* 38:   */     }
/* 39:42 */     return true;
/* 40:   */   }
/* 41:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.users.ApproveNameParser
 * JD-Core Version:    0.7.0.1
 */