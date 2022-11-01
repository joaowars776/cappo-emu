/*  1:   */ package cappo.game.navigator.officialrooms;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import java.sql.ResultSet;
/*  5:   */ 
/*  6:   */ public abstract class Official
/*  7:   */ {
/*  8:   */   public int id;
/*  9:   */   public String caption;
/* 10:   */   public String desc;
/* 11:   */   public boolean showDetails;
/* 12:   */   public String image;
/* 13:   */   public int parentId;
/* 14:   */   public int type;
/* 15:   */   
/* 16:   */   public Official(ResultSet data)
/* 17:   */     throws Exception
/* 18:   */   {
/* 19:23 */     this.id = data.getInt("id");
/* 20:24 */     this.caption = data.getString("caption");
/* 21:25 */     this.desc = data.getString("desc");
/* 22:26 */     this.showDetails = (data.getInt("show_details") == 1);
/* 23:27 */     this.image = data.getString("image");
/* 24:28 */     this.parentId = data.getInt("parent_id");
/* 25:29 */     this.type = data.getInt("type");
/* 26:   */   }
/* 27:   */   
/* 28:   */   public abstract void compose(MessageWriter paramMessageWriter)
/* 29:   */     throws Exception;
/* 30:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.navigator.officialrooms.Official
 * JD-Core Version:    0.7.0.1
 */