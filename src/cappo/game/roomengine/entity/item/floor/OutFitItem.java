/*  1:   */ package cappo.game.roomengine.entity.item.floor;
/*  2:   */ 
/*  3:   */ import cappo.game.player.AvatarLook;
/*  4:   */ import cappo.game.player.PlayerData;
/*  5:   */ import cappo.game.roomengine.entity.item.extradata.MapStuffData;
/*  6:   */ import java.util.Map;
/*  7:   */ 
/*  8:   */ public class OutFitItem
/*  9:   */   extends FloorItem
/* 10:   */ {
/* 11:   */   public AvatarLook look;
/* 12:   */   
/* 13:   */   public void setName(String name)
/* 14:   */   {
/* 15:17 */     MapStuffData data = (MapStuffData)this.extraData;
/* 16:18 */     data.setExtraData("OUTFIT_NAME=" + name);
/* 17:   */   }
/* 18:   */   
/* 19:   */   public void setLook(PlayerData playerData)
/* 20:   */   {
/* 21:22 */     String outLook = playerData.avatarLook.filterLook(new int[] { 10, 7 });
/* 22:23 */     this.look = new AvatarLook(outLook);
/* 23:24 */     MapStuffData data = (MapStuffData)this.extraData;
/* 24:25 */     data.setExtraData("FIGURE=" + outLook + "\t" + "GENDER=" + (playerData.sex == 1 ? "M" : "F"));
/* 25:   */   }
/* 26:   */   
/* 27:   */   public void generateLook(PlayerData playerData)
/* 28:   */   {
/* 29:29 */     MapStuffData data = (MapStuffData)this.extraData;
/* 30:30 */     playerData.sex = (((String)data.extraData.get("GENDER")).equals("M") ? 1 : 0);
/* 31:31 */     playerData.avatarLook.setPart(10, this.look);
/* 32:32 */     playerData.avatarLook.setPart(7, this.look);
/* 33:   */   }
/* 34:   */   
/* 35:   */   public void getAvatarLook()
/* 36:   */   {
/* 37:36 */     MapStuffData data = (MapStuffData)this.extraData;
/* 38:37 */     this.look = new AvatarLook((String)data.extraData.get("FIGURE"));
/* 39:   */   }
/* 40:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.roomengine.entity.item.floor.OutFitItem
 * JD-Core Version:    0.7.0.1
 */