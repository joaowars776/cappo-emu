/*   1:    */ package cappo.game.roomengine.gamemap;
/*   2:    */ 
/*   3:    */ import cappo.engine.logging.Log;
/*   4:    */ import cappo.game.games.snowwar.Direction8;
/*   5:    */ import cappo.game.roomengine.Square;
/*   6:    */ import java.util.Map;
/*   7:    */ import java.util.Set;
/*   8:    */ import java.util.concurrent.ConcurrentHashMap;
/*   9:    */ 
/*  10:    */ public class GameMapBase
/*  11:    */ {
/*  12:    */   public String modelName;
/*  13:    */   public int doorX;
/*  14:    */   public int doorY;
/*  15:    */   public float doorZ;
/*  16:    */   public Direction8 DoorOrientation;
/*  17:    */   public boolean clubOnly;
/*  18:    */   public int widthX;
/*  19:    */   public int heightY;
/*  20:    */   private Map<Short, Square> floorMap;
/*  21:    */   
/*  22:    */   public GameMapBase(String name, int doorx, int doory, float doorz, Direction8 doorRot, boolean club)
/*  23:    */   {
/*  24: 29 */     this.modelName = name;
/*  25: 30 */     this.doorX = doorx;
/*  26: 31 */     this.doorY = doory;
/*  27: 32 */     this.doorZ = doorz;
/*  28: 33 */     this.DoorOrientation = doorRot;
/*  29: 34 */     this.clubOnly = club;
/*  30:    */   }
/*  31:    */   
/*  32:    */   public void initFloorMap(int size)
/*  33:    */   {
/*  34: 38 */     this.floorMap = new ConcurrentHashMap((int)(size * 0.6D), 0.9F);
/*  35:    */   }
/*  36:    */   
/*  37:    */   public void generateModel(String[] Lines)
/*  38:    */     throws Exception
/*  39:    */   {
/*  40: 42 */     this.widthX = Lines[0].length();
/*  41: 43 */     this.heightY = Lines.length;
/*  42:    */     
/*  43: 45 */     int size = this.widthX * this.heightY;
/*  44: 46 */     if (size > 5000) {
/*  45: 47 */       throw new Exception("TOO BIG roomModel: " + size);
/*  46:    */     }
/*  47: 50 */     initFloorMap(size);
/*  48:    */     
/*  49: 52 */     int index = 0;
/*  50: 53 */     for (int y = 0; y < this.heightY; y++) {
/*  51: 54 */       for (int x = 0; x < this.widthX; x++)
/*  52:    */       {
/*  53:    */         try
/*  54:    */         {
/*  55: 56 */           if ((this.doorX == x) && (this.doorY == y))
/*  56:    */           {
/*  57: 57 */             this.floorMap.put(Short.valueOf((short)index), new Square(x, y, index, this.doorZ));
/*  58:    */           }
/*  59:    */           else
/*  60:    */           {
/*  61: 59 */             char tile = Lines[y].charAt(x);
/*  62: 60 */             if ((tile != 'x') && (tile != 'X')) {
/*  63: 61 */               this.floorMap.put(Short.valueOf((short)index), new Square(x, y, index, Integer.parseInt(Character.toString(tile), 36)));
/*  64:    */             }
/*  65:    */           }
/*  66:    */         }
/*  67:    */         catch (Exception ex)
/*  68:    */         {
/*  69: 65 */           Log.printException("", ex);
/*  70:    */         }
/*  71: 68 */         index++;
/*  72:    */       }
/*  73:    */     }
/*  74: 72 */     buildSquares();
/*  75:    */   }
/*  76:    */   
/*  77:    */   public void buildSquares()
/*  78:    */   {
/*  79: 76 */     int index = 0;
/*  80: 77 */     for (int y = 0; y < this.heightY; y++) {
/*  81: 78 */       for (int x = 0; x < this.widthX; x++)
/*  82:    */       {
/*  83:    */         try
/*  84:    */         {
/*  85: 80 */           buildAdjacencies(index);
/*  86:    */         }
/*  87:    */         catch (Exception ex)
/*  88:    */         {
/*  89: 82 */           Log.printException("", ex);
/*  90:    */         }
/*  91: 85 */         index++;
/*  92:    */       }
/*  93:    */     }
/*  94:    */   }
/*  95:    */   
/*  96:    */   private void buildAdjacencies(int xy)
/*  97:    */   {
/*  98: 91 */     Square element = (Square)this.floorMap.get(Short.valueOf((short)xy));
/*  99: 92 */     if (element == null) {
/* 100: 93 */       return;
/* 101:    */     }
/* 102: 97 */     int Y = xy / this.widthX;
/* 103: 98 */     int X = xy - Y * this.widthX;
/* 104: 99 */     int maxX = this.widthX - 1;
/* 105:100 */     int maxY = this.heightY - 1;
/* 106:    */     
/* 107:102 */     float minAdjacentHeight = element.height - 5.0F;
/* 108:103 */     float maxAdjacentHeight = element.height + 5.0F;
/* 109:105 */     if (X < maxX)
/* 110:    */     {
/* 111:106 */       addAdjacent(element, minAdjacentHeight, maxAdjacentHeight, xy + 1, false);
/* 112:107 */       if (X > 0) {
/* 113:108 */         addAdjacent(element, minAdjacentHeight, maxAdjacentHeight, xy + this.widthX - 1, true);
/* 114:    */       }
/* 115:110 */       if (Y < maxY) {
/* 116:111 */         addAdjacent(element, minAdjacentHeight, maxAdjacentHeight, xy + this.widthX + 1, true);
/* 117:    */       }
/* 118:    */     }
/* 119:115 */     if (Y < maxY) {
/* 120:116 */       addAdjacent(element, minAdjacentHeight, maxAdjacentHeight, xy + this.widthX, false);
/* 121:    */     }
/* 122:    */   }
/* 123:    */   
/* 124:    */   private void addAdjacent(Square element, float minAdjacentHeight, float maxAdjacentHeight, int xy, boolean diagonal)
/* 125:    */   {
/* 126:122 */     Square sq = (Square)this.floorMap.get(Short.valueOf((short)xy));
/* 127:123 */     if (sq == null) {
/* 128:124 */       return;
/* 129:    */     }
/* 130:127 */     if (sq.height > maxAdjacentHeight) {
/* 131:128 */       return;
/* 132:    */     }
/* 133:131 */     if (sq.height < minAdjacentHeight) {
/* 134:132 */       return;
/* 135:    */     }
/* 136:135 */     sq.adjacencies.add(element);
/* 137:136 */     element.adjacencies.add(sq);
/* 138:137 */     if (!diagonal)
/* 139:    */     {
/* 140:138 */       sq.adjacenciesNoDiagonal.add(element);
/* 141:139 */       element.adjacenciesNoDiagonal.add(sq);
/* 142:    */     }
/* 143:    */   }
/* 144:    */   
/* 145:    */   public Square getSquare(int xy)
/* 146:    */   {
/* 147:144 */     return (Square)this.floorMap.get(Short.valueOf((short)xy));
/* 148:    */   }
/* 149:    */   
/* 150:    */   public Square setSquare(int xy, Square sq)
/* 151:    */   {
/* 152:148 */     return (Square)this.floorMap.put(Short.valueOf((short)xy), sq);
/* 153:    */   }
/* 154:    */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.roomengine.gamemap.GameMapBase
 * JD-Core Version:    0.7.0.1
 */