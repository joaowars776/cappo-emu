/*  1:   */ package cappo.game.collections;
/*  2:   */ 
/*  3:   */ import java.util.ArrayList;
/*  4:   */ import java.util.List;
/*  5:   */ 
/*  6:   */ public class UnseenItems
/*  7:   */ {
/*  8:   */   public static final int FURNIS = 1;
/*  9:   */   public static final int RENTALS = 2;
/* 10:   */   public static final int PET = 3;
/* 11:   */   public static final int BADGE = 4;
/* 12:   */   public static final int BOT = 5;
/* 13:   */   public static final int GAMES = 6;
/* 14:   */   public int Size;
/* 15:21 */   public List<List<Integer>> unseenItems = new ArrayList();
/* 16:   */   
/* 17:   */   public UnseenItems()
/* 18:   */   {
/* 19:24 */     this.unseenItems.add(0, null);
/* 20:25 */     this.unseenItems.add(1, new ArrayList());
/* 21:26 */     this.unseenItems.add(2, new ArrayList());
/* 22:27 */     this.unseenItems.add(3, new ArrayList());
/* 23:28 */     this.unseenItems.add(4, new ArrayList());
/* 24:29 */     this.unseenItems.add(5, new ArrayList());
/* 25:   */   }
/* 26:   */   
/* 27:   */   public void AddItem(int Type, int Id)
/* 28:   */   {
/* 29:33 */     List<Integer> items = (List)this.unseenItems.get(Type);
/* 30:34 */     if (items.isEmpty()) {
/* 31:35 */       this.Size += 1;
/* 32:   */     }
/* 33:37 */     items.add(Integer.valueOf(Id));
/* 34:   */   }
/* 35:   */   
/* 36:   */   public void ResetItems(int Type)
/* 37:   */   {
/* 38:41 */     List<Integer> items = (List)this.unseenItems.get(Type);
/* 39:42 */     if (!items.isEmpty())
/* 40:   */     {
/* 41:43 */       this.Size -= 1;
/* 42:44 */       items.clear();
/* 43:   */     }
/* 44:   */   }
/* 45:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.collections.UnseenItems
 * JD-Core Version:    0.7.0.1
 */