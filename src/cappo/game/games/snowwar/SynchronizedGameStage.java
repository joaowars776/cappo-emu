/*  1:   */ package cappo.game.games.snowwar;
/*  2:   */ 
/*  3:   */ import cappo.game.games.snowwar.gameobjects.GameItemObject;
/*  4:   */ import java.util.ArrayList;
/*  5:   */ import java.util.LinkedHashMap;
/*  6:   */ import java.util.List;
/*  7:   */ import java.util.Map;
/*  8:   */ 
/*  9:   */ public class SynchronizedGameStage
/* 10:   */ {
/* 11:   */   public Map<Integer, GameItemObject> gameObjects;
/* 12:   */   private final List<GameItemObject> _2xj;
/* 13:   */   public int objectIdCounter;
/* 14:   */   
/* 15:   */   public SynchronizedGameStage()
/* 16:   */   {
/* 17:24 */     this.gameObjects = new LinkedHashMap();
/* 18:25 */     this._2xj = new ArrayList();
/* 19:   */   }
/* 20:   */   
/* 21:   */   public void addGameObject(GameItemObject obj)
/* 22:   */   {
/* 23:29 */     if (obj.objectId == 0) {
/* 24:30 */       obj.objectId = (this.objectIdCounter++);
/* 25:   */     }
/* 26:32 */     this.gameObjects.put(Integer.valueOf(obj.objectId), obj);
/* 27:33 */     obj._active = true;
/* 28:   */   }
/* 29:   */   
/* 30:   */   public void removeGameObject(int _arg1)
/* 31:   */   {
/* 32:37 */     GameItemObject local1 = (GameItemObject)this.gameObjects.remove(Integer.valueOf(_arg1));
/* 33:38 */     if (local1 != null) {
/* 34:39 */       local1.onRemove();
/* 35:   */     }
/* 36:   */   }
/* 37:   */   
/* 38:   */   public void queueDeleteObject(GameItemObject _arg1)
/* 39:   */   {
/* 40:44 */     if (_arg1 == null) {
/* 41:46 */       return;
/* 42:   */     }
/* 43:48 */     this._2xj.add(_arg1);
/* 44:49 */     _arg1._active = false;
/* 45:50 */     _arg1.GenerateCHECKSUM((SnowWarRoom)this, -1);
/* 46:   */   }
/* 47:   */   
/* 48:   */   public GameItemObject _3Pl(int _arg1)
/* 49:   */   {
/* 50:54 */     return (GameItemObject)this.gameObjects.get(Integer.valueOf(_arg1));
/* 51:   */   }
/* 52:   */   
/* 53:   */   public void subturn()
/* 54:   */   {
/* 55:58 */     for (GameItemObject local0 : this.gameObjects.values()) {
/* 56:59 */       local0.subturn(this);
/* 57:   */     }
/* 58:61 */     if (!this._2xj.isEmpty())
/* 59:   */     {
/* 60:62 */       for (GameItemObject local1 : this._2xj) {
/* 61:63 */         removeGameObject(local1.objectId);
/* 62:   */       }
/* 63:65 */       this._2xj.clear();
/* 64:   */     }
/* 65:   */   }
/* 66:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.games.snowwar.SynchronizedGameStage
 * JD-Core Version:    0.7.0.1
 */