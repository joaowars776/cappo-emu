/*   1:    */ package cappo.game.roomengine.entity.item.floor.wired;
/*   2:    */ 
/*   3:    */ import cappo.engine.database.DBResult;
/*   4:    */ import cappo.engine.database.Database;
/*   5:    */ import cappo.engine.threadpools.RoomTask;
/*   6:    */ import cappo.game.games.snowwar.Direction8;
/*   7:    */ import cappo.game.roomengine.entity.item.floor.FloorItem;
/*   8:    */ import cappo.game.roomengine.entity.item.floor.GenericFloorItem;
/*   9:    */ import cappo.game.roomengine.wired.WiredManager;
/*  10:    */ import java.sql.ResultSet;
/*  11:    */ import java.util.Iterator;
/*  12:    */ import java.util.Map;
/*  13:    */ import java.util.Set;
/*  14:    */ import java.util.concurrent.ConcurrentHashMap;
/*  15:    */ 
/*  16:    */ public abstract class WiredItemBase
/*  17:    */   extends GenericFloorItem
/*  18:    */ {
/*  19:    */   public Map<Integer, FloorItem> deletedItems;
/*  20: 21 */   public Map<Integer, FloorItem> items = new ConcurrentHashMap();
/*  21:    */   public WiredManager wiredManager;
/*  22:    */   public int selectionType;
/*  23:    */   
/*  24:    */   public void setWiredOption(int index, int option) {}
/*  25:    */   
/*  26:    */   public int[] getWiredOptions()
/*  27:    */   {
/*  28: 28 */     return new int[0];
/*  29:    */   }
/*  30:    */   
/*  31:    */   public void setWiredData(String data) {}
/*  32:    */   
/*  33:    */   public String getWiredData()
/*  34:    */   {
/*  35: 32 */     return "";
/*  36:    */   }
/*  37:    */   
/*  38:    */   public void onChildMove(FloorItem item, int xy, Direction8 dir) {}
/*  39:    */   
/*  40:    */   public void onChildPicked(FloorItem item, int xy, Direction8 dir)
/*  41:    */   {
/*  42: 40 */     this.items.remove(Integer.valueOf(item.itemId));
/*  43:    */   }
/*  44:    */   
/*  45:    */   public abstract int getCode();
/*  46:    */   
/*  47:    */   public abstract boolean needUser();
/*  48:    */   
/*  49:    */   public void setManager(WiredManager manager)
/*  50:    */   {
/*  51: 97 */     this.wiredManager = manager;
/*  52:    */   }
/*  53:    */   
/*  54:    */   public void removeManager()
/*  55:    */   {
/*  56:101 */     this.wiredManager = null;
/*  57:    */   }
/*  58:    */   
/*  59:    */   public void refreshItems()
/*  60:    */   {
/*  61:105 */     this.deletedItems = this.items;
/*  62:106 */     this.items = new ConcurrentHashMap();
/*  63:    */   }
/*  64:    */   
/*  65:    */   public void cleanDeletedItems()
/*  66:    */   {
/*  67:110 */     this.deletedItems = null;
/*  68:    */   }
/*  69:    */   
/*  70:    */   public boolean addItem(FloorItem item)
/*  71:    */   {
/*  72:114 */     if (item == null) {
/*  73:115 */       return false;
/*  74:    */     }
/*  75:118 */     if (this.items.containsKey(Integer.valueOf(item.itemId))) {
/*  76:119 */       return false;
/*  77:    */     }
/*  78:121 */     this.items.put(Integer.valueOf(item.itemId), item);
/*  79:    */     
/*  80:123 */     return (this.deletedItems == null) || (this.deletedItems.remove(Integer.valueOf(item.itemId)) == null);
/*  81:    */   }
/*  82:    */   
/*  83:    */   public boolean removeAllItems()
/*  84:    */   {
/*  85:127 */     for (FloorItem item : this.items.values()) {
/*  86:128 */       item.removeAttachedWired(this.itemId);
/*  87:    */     }
/*  88:130 */     this.items.clear();
/*  89:131 */     return true;
/*  90:    */   }
/*  91:    */   
/*  92:    */   public void saveData()
/*  93:    */     throws Exception
/*  94:    */   {
/*  95:135 */     Database.exec("DELETE FROM trigger_in_place WHERE original_trigger = " + this.itemId + ";", new Object[0]);
/*  96:137 */     for (Iterator localIterator = this.items.keySet().iterator(); localIterator.hasNext();)
/*  97:    */     {
/*  98:137 */       int id = ((Integer)localIterator.next()).intValue();
/*  99:138 */       Database.exec("INSERT IGNORE INTO trigger_in_place (original_trigger,triggers_item) VALUES (" + this.itemId + "," + id + ");", new Object[0]);
/* 100:    */     }
/* 101:    */   }
/* 102:    */   
/* 103:    */   public void loadData(DBResult result)
/* 104:    */     throws Exception
/* 105:    */   {
/* 106:143 */     Database.query(result, "SELECT triggers_item FROM trigger_in_place WHERE original_trigger = " + this.itemId + ";", new Object[0]);
/* 107:144 */     while (result.data.next()) {
/* 108:145 */       addItem(getRoom().getFloorItem(result.data.getInt("triggers_item")));
/* 109:    */     }
/* 110:    */   }
/* 111:    */   
/* 112:    */   public void deleteData()
/* 113:    */     throws Exception
/* 114:    */   {
/* 115:150 */     Database.exec("DELETE FROM trigger_item WHERE trigger_id = " + this.itemId + ";", new Object[0]);
/* 116:151 */     Database.exec("DELETE FROM trigger_in_place WHERE original_trigger = " + this.itemId + " OR triggers_item = " + this.itemId + ";", new Object[0]);
/* 117:    */   }
/* 118:    */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.roomengine.entity.item.floor.wired.WiredItemBase
 * JD-Core Version:    0.7.0.1
 */