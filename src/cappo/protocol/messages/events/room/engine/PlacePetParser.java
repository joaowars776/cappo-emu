/*  1:   */ package cappo.protocol.messages.events.room.engine;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageReader;
/*  4:   */ import cappo.engine.network.QueueWriter;
/*  5:   */ import cappo.engine.player.Connection;
/*  6:   */ import cappo.engine.threadpools.RoomTask;
/*  7:   */ import cappo.game.pets.Pet;
/*  8:   */ import cappo.game.player.inventory.PlayerInventory;
/*  9:   */ import cappo.game.roomengine.RoomData;
/* 10:   */ import cappo.game.roomengine.entity.live.Avatar;
/* 11:   */ import cappo.game.roomengine.gamemap.GameMapBase;
/* 12:   */ import cappo.protocol.messages.IncomingMessageEvent;
/* 13:   */ import cappo.protocol.messages.composers.inventory.pets.RemovePetInventoryComposer;
/* 14:   */ import cappo.protocol.messages.composers.room.pets.PetPlacingErrorComposer;
/* 15:   */ import java.util.Map;
/* 16:   */ 
/* 17:   */ public class PlacePetParser
/* 18:   */   extends IncomingMessageEvent
/* 19:   */ {
/* 20:   */   public void messageReceived(Connection Main)
/* 21:   */   {
/* 22:23 */     Avatar avatar = Main.avatar;
/* 23:24 */     if (avatar == null) {
/* 24:25 */       return;
/* 25:   */     }
/* 26:28 */     RoomTask room = avatar.room;
/* 27:30 */     if ((!room.roomData.haveFlag(2)) || (
/* 28:31 */       (avatar.controllerLevel != 1) && 
/* 29:32 */       (avatar.controllerLevel < 4)))
/* 30:   */     {
/* 31:33 */       QueueWriter.write(Main.socket, PetPlacingErrorComposer.compose(1));
/* 32:34 */       return;
/* 33:   */     }
/* 34:37 */     if (room.PetCounter >= 5)
/* 35:   */     {
/* 36:38 */       QueueWriter.write(Main.socket, PetPlacingErrorComposer.compose(2));
/* 37:39 */       return;
/* 38:   */     }
/* 39:42 */     int petId = Main.currentPacket.readInt();
/* 40:   */     
/* 41:   */ 
/* 42:45 */     int xy = Main.currentPacket.readInt() + Main.currentPacket.readInt() * room.model.widthX;
/* 43:47 */     if (!room.canPlacePet(xy))
/* 44:   */     {
/* 45:48 */       QueueWriter.write(Main.socket, PetPlacingErrorComposer.compose(4));
/* 46:49 */       return;
/* 47:   */     }
/* 48:52 */     Pet pet = Main.inventory.removePet(petId);
/* 49:53 */     if (pet == null) {
/* 50:54 */       return;
/* 51:   */     }
/* 52:57 */     Float z = (Float)room.squareAbsoluteHeight.get(Integer.valueOf(xy));
/* 53:58 */     if (z == null) {
/* 54:59 */       z = Float.valueOf(0.0F);
/* 55:   */     }
/* 56:61 */     room.deployPet(pet, xy, z.floatValue());
/* 57:   */     
/* 58:63 */     QueueWriter.write(Main.socket, RemovePetInventoryComposer.compose(pet.id));
/* 59:   */   }
/* 60:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.room.engine.PlacePetParser
 * JD-Core Version:    0.7.0.1
 */