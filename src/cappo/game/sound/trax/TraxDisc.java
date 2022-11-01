/*  1:   */ package cappo.game.sound.trax;
/*  2:   */ 
/*  3:   */ public class TraxDisc
/*  4:   */ {
/*  5:   */   public int Id;
/*  6:   */   public int Length;
/*  7:   */   public String Author;
/*  8:   */   public String Name;
/*  9:   */   public String SongData;
/* 10:   */   
/* 11:   */   public TraxDisc(int id, String name, String songdata, int length, String author)
/* 12:   */   {
/* 13:17 */     this.Id = id;
/* 14:18 */     this.Name = name;
/* 15:19 */     this.SongData = songdata;
/* 16:20 */     this.Length = (length * 1000);
/* 17:21 */     this.Author = author;
/* 18:   */   }
/* 19:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.sound.trax.TraxDisc
 * JD-Core Version:    0.7.0.1
 */