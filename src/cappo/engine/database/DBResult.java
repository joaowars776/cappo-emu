/*  1:   */ package cappo.engine.database;
/*  2:   */ 
/*  3:   */ import java.sql.Connection;
/*  4:   */ import java.sql.PreparedStatement;
/*  5:   */ import java.sql.ResultSet;
/*  6:   */ 
/*  7:   */ public class DBResult
/*  8:   */ {
/*  9:   */   public Connection cn;
/* 10:   */   public ResultSet data;
/* 11:   */   public PreparedStatement pst;
/* 12:   */   
/* 13:   */   public DBResult()
/* 14:   */   {
/* 15:19 */     this.cn = Database.getNew();
/* 16:   */   }
/* 17:   */   
/* 18:   */   public void close()
/* 19:   */   {
/* 20:23 */     if (this.data != null) {
/* 21:   */       try
/* 22:   */       {
/* 23:25 */         this.data.close();
/* 24:   */       }
/* 25:   */       catch (Exception localException) {}
/* 26:   */     }
/* 27:29 */     if (this.pst != null) {
/* 28:   */       try
/* 29:   */       {
/* 30:31 */         this.pst.close();
/* 31:   */       }
/* 32:   */       catch (Exception localException1) {}
/* 33:   */     }
/* 34:35 */     if (this.cn != null) {
/* 35:   */       try
/* 36:   */       {
/* 37:37 */         this.cn.close();
/* 38:   */       }
/* 39:   */       catch (Exception localException2) {}
/* 40:   */     }
/* 41:   */   }
/* 42:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.engine.database.DBResult
 * JD-Core Version:    0.7.0.1
 */