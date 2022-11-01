/*   1:    */ package cappo.protocol.messages.composers.inventory.furni;
/*   2:    */ 
/*   3:    */ import cappo.engine.network.MessageWriter;
/*   4:    */ import cappo.game.collections.BaseItem;
/*   5:    */ import cappo.game.roomengine.entity.item.floor.FloorItem;
/*   6:    */ import cappo.game.roomengine.entity.item.wall.WallItem;
/*   7:    */ import cappo.protocol.messages.Composer;
/*   8:    */ import cappo.protocol.messages.composers.serializers.SerializeItemData;
/*   9:    */ import java.util.Collection;
/*  10:    */ 
/*  11:    */ public class FurniListComposer
/*  12:    */ {
/*  13:    */   public static int HEADER;
/*  14:    */   
/*  15:    */   public static void writeObject(FloorItem obj, MessageWriter message)
/*  16:    */   {
/*  17: 21 */     Composer.add(Integer.valueOf(obj.itemId), message);
/*  18: 22 */     Composer.add(obj.baseItem.Type.toUpperCase(), message);
/*  19: 23 */     Composer.add(Integer.valueOf(obj.refId), message);
/*  20: 24 */     Composer.add(Integer.valueOf(obj.baseItem.SpriteId), message);
/*  21: 25 */     Composer.add(Integer.valueOf(obj.baseItem.itemCategory), message);
/*  22: 26 */     SerializeItemData.parse(message, obj.baseItem, obj);
/*  23: 27 */     Composer.add(Boolean.valueOf(obj.baseItem.AllowRecycle), message);
/*  24: 28 */     Composer.add(Boolean.valueOf(obj.baseItem.AllowTrade), message);
/*  25: 29 */     Composer.add(Boolean.valueOf(obj.baseItem.AllowInventoryStack), message);
/*  26: 30 */     Composer.add(Boolean.valueOf(obj.baseItem.AllowMarketplaceSell), message);
/*  27: 31 */     Composer.add(Integer.valueOf(-1), message);
/*  28: 32 */     Composer.add(Boolean.valueOf(false), message);
/*  29: 33 */     Composer.add(Integer.valueOf(-1), message);
/*  30: 34 */     Composer.add("", message);
/*  31: 35 */     Composer.add(Integer.valueOf(0), message);
/*  32:    */   }
/*  33:    */   
/*  34:    */   public static void writeItem(WallItem obj, MessageWriter message)
/*  35:    */   {
/*  36: 39 */     Composer.add(Integer.valueOf(obj.itemId), message);
/*  37: 40 */     Composer.add(obj.baseItem.Type.toUpperCase(), message);
/*  38: 41 */     Composer.add(Integer.valueOf(obj.refId), message);
/*  39: 42 */     Composer.add(Integer.valueOf(obj.baseItem.SpriteId), message);
/*  40: 43 */     Composer.add(Integer.valueOf(obj.baseItem.itemCategory), message);
/*  41: 44 */     SerializeItemData.parse(message, obj.baseItem, obj);
/*  42: 45 */     Composer.add(Boolean.valueOf(obj.baseItem.AllowRecycle), message);
/*  43: 46 */     Composer.add(Boolean.valueOf(obj.baseItem.AllowTrade), message);
/*  44: 47 */     Composer.add(Boolean.valueOf(obj.baseItem.AllowInventoryStack), message);
/*  45: 48 */     Composer.add(Boolean.valueOf(obj.baseItem.AllowMarketplaceSell), message);
/*  46: 49 */     Composer.add(Integer.valueOf(-1), message);
/*  47: 50 */     Composer.add(Boolean.valueOf(false), message);
/*  48: 51 */     Composer.add(Integer.valueOf(-1), message);
/*  49:    */   }
/*  50:    */   
/*  51:    */   public static final MessageWriter[] compose(Collection<FloorItem> objects, Collection<WallItem> items)
/*  52:    */   {
/*  53: 57 */     int objectsSize = objects.size();
/*  54: 58 */     int itemsSize = items.size();
/*  55: 59 */     int ammountOfFurnis = objectsSize + itemsSize;
/*  56:    */     
/*  57: 61 */     int splitCount = 1;
/*  58: 62 */     if (ammountOfFurnis > 1500)
/*  59:    */     {
/*  60: 63 */       splitCount = ammountOfFurnis / 1500;
/*  61: 64 */       splitCount++;
/*  62:    */     }
/*  63: 67 */     MessageWriter[] packets = new MessageWriter[splitCount];
/*  64:    */     
/*  65: 69 */     int tmp = 0;int i = -1;
/*  66: 71 */     for (FloorItem obj : objects)
/*  67:    */     {
/*  68: 72 */       if ((tmp >= 1500) || (i == -1))
/*  69:    */       {
/*  70: 73 */         int size = ammountOfFurnis > 1500 ? 1500 : ammountOfFurnis;
/*  71: 74 */         ammountOfFurnis -= size;
/*  72: 76 */         if (i != -1) {
/*  73: 77 */           Composer.endPacket(packets[i]);
/*  74:    */         }
/*  75: 80 */         packets[(++i)] = new MessageWriter(100 + size * 300);
/*  76: 81 */         tmp = 0;
/*  77:    */         
/*  78: 83 */         Composer.initPacket(HEADER, packets[i]);
/*  79: 84 */         Composer.add(Integer.valueOf(splitCount), packets[i]);
/*  80: 85 */         Composer.add(Integer.valueOf(i), packets[i]);
/*  81: 86 */         Composer.add(Integer.valueOf(size), packets[i]);
/*  82:    */       }
/*  83: 88 */       writeObject(obj, packets[i]);
/*  84: 89 */       tmp++;
/*  85:    */     }
/*  86: 92 */     for (WallItem obj : items)
/*  87:    */     {
/*  88: 93 */       if ((tmp >= 1500) || (i == -1))
/*  89:    */       {
/*  90: 94 */         int size = ammountOfFurnis > 1500 ? 1500 : ammountOfFurnis;
/*  91: 95 */         ammountOfFurnis -= size;
/*  92: 97 */         if (i != -1) {
/*  93: 98 */           Composer.endPacket(packets[i]);
/*  94:    */         }
/*  95:101 */         packets[(++i)] = new MessageWriter(100 + size * 300);
/*  96:102 */         tmp = 0;
/*  97:    */         
/*  98:104 */         Composer.initPacket(HEADER, packets[i]);
/*  99:105 */         Composer.add(Integer.valueOf(splitCount), packets[i]);
/* 100:106 */         Composer.add(Integer.valueOf(i), packets[i]);
/* 101:107 */         Composer.add(Integer.valueOf(size), packets[i]);
/* 102:    */       }
/* 103:109 */       writeItem(obj, packets[i]);
/* 104:110 */       tmp++;
/* 105:    */     }
/* 106:113 */     if (i == -1)
/* 107:    */     {
/* 108:114 */       i = 0;
/* 109:115 */       packets[i] = new MessageWriter(100);
/* 110:116 */       Composer.initPacket(HEADER, packets[i]);
/* 111:117 */       Composer.add(Integer.valueOf(1), packets[i]);
/* 112:118 */       Composer.add(Integer.valueOf(0), packets[i]);
/* 113:119 */       Composer.add(Integer.valueOf(0), packets[i]);
/* 114:    */     }
/* 115:122 */     Composer.endPacket(packets[i]);
/* 116:    */     
/* 117:124 */     return packets;
/* 118:    */   }
/* 119:    */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.inventory.furni.FurniListComposer
 * JD-Core Version:    0.7.0.1
 */