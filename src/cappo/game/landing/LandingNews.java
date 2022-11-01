/*  1:   */ package cappo.game.landing;
/*  2:   */ 
/*  3:   */ import cappo.engine.database.DBResult;
/*  4:   */ import cappo.engine.database.Database;
/*  5:   */ import java.sql.ResultSet;
/*  6:   */ import java.util.ArrayList;
/*  7:   */ import java.util.List;
/*  8:   */ 
/*  9:   */ public class LandingNews
/* 10:   */ {
/* 11:   */   public int id;
/* 12:   */   public String newTitle;
/* 13:   */   public String newText;
/* 14:   */   public String newImage;
/* 15:   */   public boolean isClientAction;
/* 16:   */   public String link;
/* 17:   */   public String action;
/* 18:   */   public String extra;
/* 19:   */   public String button;
/* 20:20 */   public static List<LandingNews> news = new ArrayList();
/* 21:   */   
/* 22:   */   public LandingNews(int ID, String title, String text, String btnTxt, String image, boolean clientAction, String lnk, String act, String extraData)
/* 23:   */   {
/* 24:23 */     this.id = ID;
/* 25:24 */     this.newTitle = title;
/* 26:25 */     this.newText = text;
/* 27:26 */     this.button = btnTxt;
/* 28:27 */     this.newImage = image;
/* 29:28 */     this.isClientAction = clientAction;
/* 30:29 */     this.link = lnk;
/* 31:30 */     this.action = act;
/* 32:31 */     this.extra = extraData;
/* 33:   */   }
/* 34:   */   
/* 35:   */   public String getLink()
/* 36:   */   {
/* 37:35 */     if (!this.isClientAction) {
/* 38:36 */       return this.link;
/* 39:   */     }
/* 40:38 */     return this.link + "/" + this.action + "/" + this.extra;
/* 41:   */   }
/* 42:   */   
/* 43:   */   public static void Init(DBResult result)
/* 44:   */     throws Exception
/* 45:   */   {
/* 46:42 */     news.clear();
/* 47:43 */     Database.query(result, "SELECT * FROM landing_news WHERE enabled='1';", new Object[0]);
/* 48:44 */     while (result.data.next()) {
/* 49:45 */       news.add(new LandingNews(result.data.getInt("id"), result.data.getString("title"), result.data.getString("text"), result.data.getString("button"), result.data.getString("image"), result.data.getInt("is_link") == 0, result.data.getString("link"), result.data.getString("action"), result.data.getString("extra")));
/* 50:   */     }
/* 51:   */   }
/* 52:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.landing.LandingNews
 * JD-Core Version:    0.7.0.1
 */