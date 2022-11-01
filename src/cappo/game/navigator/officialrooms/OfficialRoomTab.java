/*  1:   */ package cappo.game.navigator.officialrooms;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.protocol.messages.Composer;
/*  5:   */ import java.sql.ResultSet;
/*  6:   */ 
/*  7:   */ public class OfficialRoomTab
/*  8:   */   extends Official
/*  9:   */ {
/* 10:   */   public boolean isOpen;
/* 11:   */   
/* 12:   */   public OfficialRoomTab(ResultSet data)
/* 13:   */     throws Exception
/* 14:   */   {
/* 15:12 */     super(data);
/* 16:   */     
/* 17:14 */     this.isOpen = data.getString("extra").equals("1");
/* 18:   */   }
/* 19:   */   
/* 20:   */   public void compose(MessageWriter clientMessage)
/* 21:   */   {
/* 22:19 */     Composer.add(Integer.valueOf(0), clientMessage);
/* 23:20 */     Composer.add(Integer.valueOf(this.type), clientMessage);
/* 24:21 */     Composer.add(Boolean.valueOf(this.isOpen), clientMessage);
/* 25:   */   }
/* 26:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.navigator.officialrooms.OfficialRoomTab
 * JD-Core Version:    0.7.0.1
 */