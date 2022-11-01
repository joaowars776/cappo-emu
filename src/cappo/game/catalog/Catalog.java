/*   1:    */ package cappo.game.catalog;
/*   2:    */ 
/*   3:    */ import cappo.engine.database.DBResult;
/*   4:    */ import cappo.engine.database.Database;
/*   5:    */ import cappo.engine.logging.Log;
/*   6:    */ import cappo.engine.network.MessageWriter;
/*   7:    */ import cappo.game.collections.BaseItem;
/*   8:    */ import java.sql.ResultSet;
/*   9:    */ import java.util.ArrayList;
/*  10:    */ import java.util.HashMap;
/*  11:    */ import java.util.List;
/*  12:    */ import java.util.Map;
/*  13:    */ import java.util.concurrent.ConcurrentHashMap;
/*  14:    */ 
/*  15:    */ public class Catalog
/*  16:    */ {
/*  17:    */   public static class CatalogSubItem
/*  18:    */   {
/*  19:    */     public Integer amount;
/*  20:    */     public Integer Expire;
/*  21:    */     public String extraData;
/*  22:    */     public BaseItem baseItem;
/*  23:    */     public int extraParam;
/*  24:    */   }
/*  25:    */   
/*  26:    */   public static class CatalogProduct
/*  27:    */   {
/*  28:    */     public boolean AllowGift;
/*  29:    */     public int clubLevel;
/*  30:    */     public int creditCost;
/*  31:    */     public int activityPointsType;
/*  32:    */     public int productId;
/*  33: 30 */     public List<Catalog.CatalogSubItem> items = new ArrayList();
/*  34:    */     public String itemName;
/*  35:    */     public int activityPointCost;
/*  36:    */     public boolean allowBundleDiscounts;
/*  37:    */     public int pageId;
/*  38:    */     public Integer uniqueLimitedItemsLaunched;
/*  39:    */     public Integer uniqueLimitedItemsLeft;
/*  40:    */   }
/*  41:    */   
/*  42:    */   public static class CatalogPage
/*  43:    */   {
/*  44:    */     public String caption;
/*  45:    */     public int iconColor;
/*  46:    */     public int IconImage;
/*  47:    */     public int pageId;
/*  48: 48 */     public List<Catalog.CatalogProduct> items = new ArrayList();
/*  49:    */     public String Layout;
/*  50:    */     public String[][] PageData;
/*  51:    */     public int parentId;
/*  52:    */     public int minRank;
/*  53:    */     public boolean acceptSeasonCurrencyAsCredits;
/*  54:    */     public String pageName;
/*  55:    */     public boolean clubOnly;
/*  56:    */     public boolean isEnabled;
/*  57:    */     public boolean isVisible;
/*  58:    */     public boolean isCacheDisabled;
/*  59:    */   }
/*  60:    */   
/*  61: 88 */   public static Map<Integer, List<CatalogPage>> catalogMap = new HashMap();
/*  62: 89 */   public static Map<Integer, CatalogPage> pages = new HashMap();
/*  63: 90 */   public static Map<Integer, CatalogProduct> Items = new HashMap();
/*  64: 91 */   public static Map<Integer, CatalogProduct> limitedItems = new HashMap();
/*  65: 93 */   public static Map<Integer, MessageWriter> indexMap = new ConcurrentHashMap(1000);
/*  66:    */   public static boolean isBlocked;
/*  67: 96 */   public static Map<Integer, MessageWriter> pageMap = new ConcurrentHashMap(1000);
/*  68:    */   
/*  69:    */   public static void block()
/*  70:    */   {
/*  71: 99 */     isBlocked = true;
/*  72:100 */     indexMap.clear();
/*  73:101 */     pageMap.clear();
/*  74:102 */     catalogMap.clear();
/*  75:103 */     pages.clear();
/*  76:105 */     for (CatalogProduct item : Items.values()) {
/*  77:106 */       if (item.uniqueLimitedItemsLaunched.intValue() > 0)
/*  78:    */       {
/*  79:107 */         int sells = item.uniqueLimitedItemsLaunched.intValue() - item.uniqueLimitedItemsLeft.intValue();
/*  80:    */         try
/*  81:    */         {
/*  82:110 */           Database.exec("UPDATE catalog_items_copy SET limited_sells=" + sells + " WHERE id=" + item.productId + ";", new Object[0]);
/*  83:    */         }
/*  84:    */         catch (Exception ex)
/*  85:    */         {
/*  86:112 */           Log.printException("Catalog", ex);
/*  87:    */         }
/*  88:    */       }
/*  89:    */     }
/*  90:117 */     Items.clear();
/*  91:    */   }
/*  92:    */   
/*  93:    */   public static void unblock()
/*  94:    */   {
/*  95:121 */     isBlocked = false;
/*  96:    */   }
/*  97:    */   
/*  98:    */   private static void GeneratePage(CatalogPage Page)
/*  99:    */   {
/* 100:125 */     pages.put(Integer.valueOf(Page.pageId), Page);
/* 101:    */     
/* 102:127 */     List<CatalogPage> a = (List)catalogMap.get(Integer.valueOf(Page.parentId));
/* 103:128 */     if (a == null)
/* 104:    */     {
/* 105:129 */       a = new ArrayList();
/* 106:130 */       catalogMap.put(Integer.valueOf(Page.parentId), a);
/* 107:    */     }
/* 108:132 */     a.add(Page);
/* 109:    */   }
/* 110:    */   
/* 111:    */   public static void Init(DBResult result)
/* 112:    */     throws Exception
/* 113:    */   {
/* 114:137 */     CatalogPage page = new CatalogPage();
/* 115:    */     
/* 116:139 */     page.pageId = -1;
/* 117:140 */     page.parentId = 0;
/* 118:141 */     page.caption = "";
/* 119:142 */     page.minRank = 0;
/* 120:143 */     page.iconColor = 0;
/* 121:144 */     page.IconImage = 0;
/* 122:145 */     page.pageName = "root";
/* 123:146 */     page.isVisible = true;
/* 124:147 */     GeneratePage(page);
/* 125:    */     
/* 126:149 */     Database.query(result, "SELECT * FROM catalog_pages ORDER BY order_num;", new Object[0]);
/* 127:150 */     while (result.data.next())
/* 128:    */     {
/* 129:151 */       page = new CatalogPage();
/* 130:152 */       page.isEnabled = result.data.getString("enabled").equals("1");
/* 131:153 */       page.isVisible = result.data.getString("visible").equals("1");
/* 132:154 */       page.pageId = result.data.getInt("id");
/* 133:155 */       page.parentId = result.data.getInt("parent_id");
/* 134:156 */       page.caption = result.data.getString("caption");
/* 135:157 */       page.minRank = result.data.getInt("min_rank");
/* 136:158 */       page.clubOnly = result.data.getString("club_only").equals("1");
/* 137:159 */       page.iconColor = result.data.getInt("icon_color");
/* 138:160 */       page.IconImage = result.data.getInt("icon_image");
/* 139:161 */       page.Layout = result.data.getString("page_layout");
/* 140:162 */       String[] images = null;String[] texts = null;
/* 141:163 */       if (page.Layout.equals("frontpage"))
/* 142:    */       {
/* 143:164 */         images = new String[] { result.data.getString("page_headline"), result.data.getString("page_teaser"), result.data.getString("page_special") };
/* 144:165 */         texts = new String[] { "", "", "", "�Conseguir Cr�ditos f�cilmente?", result.data.getString("page_text2"), result.data.getString("page_text_details"), "", "#FEFEFE", "#FEFEFE", "Lograr Cr�ditos>>", "credits" };
/* 145:166 */         page.Layout = "frontpage3";
/* 146:    */       }
/* 147:167 */       else if (page.Layout.equals("camera1"))
/* 148:    */       {
/* 149:168 */         texts = new String[] { "ctlg_header_text", "ctlg_text_1" };
/* 150:    */       }
/* 151:169 */       else if (page.Layout.equals("club1"))
/* 152:    */       {
/* 153:170 */         texts = new String[] { "ctlg_text_1", "ctlg_text_2", "ctlg_text_3", "ctlg_text_4", "ctlg_text_5" };
/* 154:    */       }
/* 155:171 */       else if (page.Layout.equals("club2"))
/* 156:    */       {
/* 157:172 */         texts = new String[] { "ctlg_text_1", "ctlg_text_2", "ctlg_text_3", "ctlg_text_4" };
/* 158:    */       }
/* 159:173 */       else if (page.Layout.equals("presents"))
/* 160:    */       {
/* 161:174 */         texts = new String[] { "ctlg_header_text", "ctlg_text1" };
/* 162:    */       }
/* 163:175 */       else if (page.Layout.equals("collectibles"))
/* 164:    */       {
/* 165:176 */         texts = new String[] { "ctlg_header_text", "ctlg_collectibles_link" };
/* 166:    */       }
/* 167:177 */       else if (page.Layout.equals("purse"))
/* 168:    */       {
/* 169:178 */         texts = new String[] { "ctlg_header_text", "ctlg_special_txt" };
/* 170:    */       }
/* 171:179 */       else if (page.Layout.startsWith("pets"))
/* 172:    */       {
/* 173:180 */         texts = new String[] { result.data.getString("page_text1"), "Give a name:", "Pick a color:", "Pick a race:" };
/* 174:    */       }
/* 175:181 */       else if (page.Layout.startsWith("pets2"))
/* 176:    */       {
/* 177:182 */         texts = new String[] { result.data.getString("page_text1"), "Give a name:", "Pick a color:", "Pick a race:" };
/* 178:    */       }
/* 179:183 */       else if (page.Layout.startsWith("pets3"))
/* 180:    */       {
/* 181:184 */         texts = new String[] { result.data.getString("page_text1"), "Give a name:", "Pick a color:", "Pick a race:" };
/* 182:    */       }
/* 183:185 */       else if (page.Layout.equals("info_credits"))
/* 184:    */       {
/* 185:186 */         texts = new String[] { "ctlg_text_1", "ctlg_text_2", "ctlg_text_3", "ctlg_text_4", "ctlg_text_5", "ctlg_text_6", "ctlg_text_7", "ctlg_text_8" };
/* 186:    */       }
/* 187:187 */       else if (page.Layout.equals("info_pixels"))
/* 188:    */       {
/* 189:188 */         texts = new String[] { "ctlg_text_1", "ctlg_text_2", "ctlg_text_3", "ctlg_text_4", "ctlg_text_5", "ctlg_text_6", "ctlg_text_7", "ctlg_text_8" };
/* 190:    */       }
/* 191:189 */       else if (page.Layout.equals("bots"))
/* 192:    */       {
/* 193:190 */         images = new String[] { result.data.getString("page_headline") };
/* 194:    */         
/* 195:192 */         texts = new String[] { "", "", "", "" };
/* 196:    */       }
/* 197:193 */       else if (page.Layout.equals("badge_display"))
/* 198:    */       {
/* 199:194 */         texts = new String[] { result.data.getString("page_text1"), result.data.getString("page_text_details"), result.data.getString("page_teaser") };
/* 200:195 */         images = new String[] { result.data.getString("page_headline"), result.data.getString("page_teaser"), result.data.getString("page_special") };
/* 201:    */       }
/* 202:198 */       if (images == null) {
/* 203:200 */         images = new String[] { result.data.getString("page_headline"), result.data.getString("page_teaser"), result.data.getString("page_special"), "", "" };
/* 204:    */       }
/* 205:203 */       if (texts == null) {
/* 206:205 */         texts = new String[] { result.data.getString("page_text1"), result.data.getString("page_text_details"), result.data.getString("page_teaser"), "", "" };
/* 207:    */       }
/* 208:208 */       page.PageData = new String[][] { images, texts };
/* 209:    */       
/* 210:    */ 
/* 211:211 */       page.pageName = (page.Layout + page.pageId);
/* 212:212 */       GeneratePage(page);
/* 213:    */     }
/* 214:215 */     Database.query(result, "SELECT * FROM catalog_items_copy WHERE ltd_id = 0;", new Object[0]);
/* 215:216 */     while (result.data.next())
/* 216:    */     {
/* 217:217 */       page = (CatalogPage)pages.get(Integer.valueOf(result.data.getInt("page_id")));
/* 218:218 */       if (page != null)
/* 219:    */       {
/* 220:219 */         CatalogProduct product = new CatalogProduct();
/* 221:220 */         product.productId = result.data.getInt("id");
/* 222:221 */         product.pageId = page.pageId;
/* 223:222 */         product.itemName = result.data.getString("catalog_name");
/* 224:223 */         product.creditCost = result.data.getInt("cost_credits");
/* 225:224 */         product.uniqueLimitedItemsLaunched = Integer.valueOf(0);
/* 226:225 */         product.uniqueLimitedItemsLeft = Integer.valueOf(0);
/* 227:226 */         product.allowBundleDiscounts = true;
/* 228:227 */         int pixels = result.data.getInt("cost_pixels");
/* 229:228 */         int cristals = result.data.getInt("cost_crystal");
/* 230:229 */         if (cristals > pixels)
/* 231:    */         {
/* 232:230 */           product.activityPointCost = cristals;
/* 233:231 */           product.activityPointsType = 105;
/* 234:    */         }
/* 235:    */         else
/* 236:    */         {
/* 237:233 */           product.activityPointCost = pixels;
/* 238:234 */           product.activityPointsType = 0;
/* 239:    */         }
/* 240:236 */         String items = result.data.getString("item_ids");
/* 241:237 */         for (String itemid : items.split(";"))
/* 242:    */         {
/* 243:238 */           CatalogSubItem subitem = new CatalogSubItem();
/* 244:239 */           subitem.baseItem = ((BaseItem)BaseItem.baseItems.get(Integer.valueOf(Integer.parseInt(itemid))));
/* 245:240 */           if (subitem.baseItem == null)
/* 246:    */           {
/* 247:241 */             Log.printLog("BASE NULL:" + itemid);
/* 248:    */           }
/* 249:    */           else
/* 250:    */           {
/* 251:244 */             subitem.amount = Integer.valueOf(result.data.getInt("amount"));
/* 252:245 */             if ((product.allowBundleDiscounts) && (subitem.amount.intValue() > 1)) {
/* 253:246 */               product.allowBundleDiscounts = false;
/* 254:    */             }
/* 255:249 */             subitem.extraParam = result.data.getInt("extra_param");
/* 256:250 */             subitem.extraData = result.data.getString("extra_data");
/* 257:    */             
/* 258:252 */             subitem.Expire = Integer.valueOf(-1);
/* 259:253 */             product.items.add(subitem);
/* 260:    */           }
/* 261:    */         }
/* 262:256 */         if ((product.allowBundleDiscounts) && (product.items.size() > 1)) {
/* 263:257 */           product.allowBundleDiscounts = false;
/* 264:    */         }
/* 265:260 */         product.AllowGift = product.allowBundleDiscounts;
/* 266:261 */         product.clubLevel = (page.clubOnly ? 2 : 0);
/* 267:262 */         page.items.add(product);
/* 268:263 */         Items.put(Integer.valueOf(product.productId), product);
/* 269:    */       }
/* 270:    */     }
/* 271:267 */     Database.query(result, "SELECT * FROM catalog_items_copy LEFT JOIN ltd_items ON (catalog_items_copy.ltd_id=ltd_items.id) WHERE ltd_id != 0;", new Object[0]);
/* 272:268 */     while (result.data.next())
/* 273:    */     {
/* 274:269 */       page = (CatalogPage)pages.get(Integer.valueOf(result.data.getInt("page_id")));
/* 275:270 */       if (page != null)
/* 276:    */       {
/* 277:271 */         CatalogProduct product = new CatalogProduct();
/* 278:272 */         product.productId = result.data.getInt("id");
/* 279:273 */         product.pageId = page.pageId;
/* 280:274 */         product.itemName = result.data.getString("catalog_name");
/* 281:275 */         product.creditCost = result.data.getInt("cost_credits");
/* 282:276 */         product.uniqueLimitedItemsLaunched = Integer.valueOf(result.data.getInt("limited_stack"));
/* 283:277 */         product.uniqueLimitedItemsLeft = Integer.valueOf(product.uniqueLimitedItemsLaunched.intValue() - result.data.getInt("limited_sells"));
/* 284:278 */         product.allowBundleDiscounts = false;
/* 285:279 */         int pixels = result.data.getInt("cost_pixels");
/* 286:280 */         int cristals = result.data.getInt("cost_crystal");
/* 287:281 */         if (cristals > pixels)
/* 288:    */         {
/* 289:282 */           product.activityPointCost = cristals;
/* 290:283 */           product.activityPointsType = 105;
/* 291:    */         }
/* 292:    */         else
/* 293:    */         {
/* 294:285 */           product.activityPointCost = pixels;
/* 295:286 */           product.activityPointsType = 0;
/* 296:    */         }
/* 297:288 */         String items = result.data.getString("item_ids");
/* 298:289 */         for (String itemid : items.split(";"))
/* 299:    */         {
/* 300:290 */           CatalogSubItem subitem = new CatalogSubItem();
/* 301:291 */           subitem.baseItem = ((BaseItem)BaseItem.baseItems.get(Integer.valueOf(Integer.parseInt(itemid))));
/* 302:292 */           if (subitem.baseItem == null)
/* 303:    */           {
/* 304:293 */             Log.printLog("BASE NULL:" + itemid);
/* 305:    */           }
/* 306:    */           else
/* 307:    */           {
/* 308:296 */             subitem.amount = Integer.valueOf(result.data.getInt("amount"));
/* 309:297 */             subitem.extraParam = result.data.getInt("extra_param");
/* 310:298 */             subitem.extraData = result.data.getString("extra_data");
/* 311:299 */             subitem.Expire = Integer.valueOf(-1);
/* 312:300 */             product.items.add(subitem);
/* 313:    */           }
/* 314:    */         }
/* 315:302 */         product.clubLevel = (page.clubOnly ? 2 : 0);
/* 316:303 */         page.items.add(product);
/* 317:304 */         Items.put(Integer.valueOf(product.productId), product);
/* 318:305 */         CatalogProduct prev = (CatalogProduct)limitedItems.put(Integer.valueOf(product.productId), product);
/* 319:306 */         if (prev != null) {
/* 320:307 */           product.uniqueLimitedItemsLeft = prev.uniqueLimitedItemsLeft;
/* 321:    */         }
/* 322:309 */         LimitedItems.add(product.productId, new LimitedItems(product, result.data.getLong("ltd_start"), result.data.getLong("ltd_end")));
/* 323:    */       }
/* 324:    */     }
/* 325:    */   }
/* 326:    */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.catalog.Catalog
 * JD-Core Version:    0.7.0.1
 */