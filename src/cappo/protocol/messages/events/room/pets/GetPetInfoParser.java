/*  1:   */ package cappo.protocol.messages.events.room.pets;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageReader;
/*  4:   */ import cappo.engine.network.QueueWriter;
/*  5:   */ import cappo.engine.player.Connection;
/*  6:   */ import cappo.engine.threadpools.RoomTask;
/*  7:   */ import cappo.game.roomengine.entity.live.Avatar;
/*  8:   */ import cappo.game.roomengine.entity.live.PetEntity;
/*  9:   */ import cappo.protocol.messages.IncomingMessageEvent;
/* 10:   */ import cappo.protocol.messages.composers.room.pets.PetInfoComposer;
/* 11:   */ 
/* 12:   */ public class GetPetInfoParser
/* 13:   */   extends IncomingMessageEvent
/* 14:   */ {
/* 15:   */   public void messageReceived(Connection Main)
/* 16:   */   {
/* 17:19 */     Avatar avatar = Main.avatar;
/* 18:20 */     if (avatar == null) {
/* 19:21 */       return;
/* 20:   */     }
/* 21:24 */     PetEntity User = avatar.room.getRoomPetById(Main.currentPacket.readInt());
/* 22:25 */     if (User != null) {
/* 23:26 */       QueueWriter.write(Main.socket, PetInfoComposer.compose(User.petData));
/* 24:   */     }
/* 25:   */   }
/* 26:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.room.pets.GetPetInfoParser
 * JD-Core Version:    0.7.0.1
 */