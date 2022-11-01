/*    1:     */ package cappo.game.games.snowwar;
/*    2:     */ 
/*    3:     */ import cappo.game.collections.BaseItem;
/*    4:     */ import cappo.game.games.snowwar.gameobjects.GameItemObject;
/*    5:     */ import cappo.game.games.snowwar.gameobjects.MachineGameObject;
/*    6:     */ import cappo.game.games.snowwar.gameobjects.TreeGameObject;
/*    7:     */ import cappo.game.roomengine.entity.item.extradata.ExtraDataBase;
/*    8:     */ import cappo.game.roomengine.entity.item.extradata.MapStuffData;
/*    9:     */ import java.util.List;
/*   10:     */ import java.util.Map;
/*   11:     */ 
/*   12:     */ public class SnowWarArena8
/*   13:     */   extends SnowWarArenaBase
/*   14:     */ {
/*   15:     */   public SnowWarArena8()
/*   16:     */   {
/*   17:  21 */     this.ArenaType = 8;
/*   18:  22 */     this.ArenaHeight = 50;
/*   19:  23 */     this.ArenaWidth = 50;
/*   20:  24 */     this.HeightMap = "xxxxxxxxxxxxxxxxxxx00000xxxxxxxxxxxxxxxxxxxxxxxxxx\rxxxxxxxxxxxxxxxxxx0000000xxxxxxxxxxxxxxxxxxxxxxxxx\rxxxxxxxxxxxxxxxxx000000000xxxxxxxxxxxxxxxxxxxxxxxx\rxxxxxxxxxxxxxxxx00000000000xxxxxxxxxxxxxxxxxxxxxxx\rxxxxxxxxxxxxxxx0000000000000xxxxxxxxxxxxxxxxxxxxxx\rxxxxxxxxxxxxxx000000000000000xxxxxxxxxxxxxxxxxxxxx\rxxxxxxxxxxxxx00000000000000000xxxxxxxxxxxxxxxxxxxx\rxxxxxxxxxxxx0000000000000000000xxxxxxxxxxxxxxxxxxx\rxxxxxxxxxxx000000000000000000000xxxxxxxxxxxxxxxxxx\rxxxxxxxxxx00000000000000000000000xxxxxxxxxxxxxxxxx\rxxxxxxxxx0000000000000000000000000xxxxxxxxxxxxxxxx\rxxxxxxxx000000000000000000000000000xxxxxxxxxxxxxxx\rxxxxxxx00000000000000000000000000000xxxxxxxxxxxxxx\rxxxxxx0000000000000000000000000000000xxxxxxxxxxxxx\rxxxxx000000000000000000000000000000000xxxxxxxxxxxx\rxxxx00000000000000000000000000000000000xxxxxxxxxxx\rxxx0000000000000000000000000000000000000xxxxxxxxxx\rxx000000000000000000000000000000000000000xxxxxxxxx\rx00000000000000000000000000000000000000000xxxxxxxx\r00000000000000000000xxxxx0xxxxxxx0000000000xxxxxxx\r00000000000000000000xxxxx0xxxxxxx00000000000xxxxxx\r00000000000000000000xxxxx0xxxxxxx000000000000xxxxx\r00000000000000000000xxx0000000xxx0000000000000xxxx\rx0000000000000000000xxx0000000xxx00000000000000xxx\rxx000000000000000000xxx0000000000000000000000000xx\rxxx00000000000000000xxx0000000xxx0000000000000000x\rxxxx00000000000000000000000000xxx00000000000000000\rxxxxx000000000000000xxx0000000xxx00000000000000000\rxxxxxx00000000000000xxxxxxx0xxxxx00000000000000000\rxxxxxxx0000000000000xxxxxxx0xxxxx00000000000000000\rxxxxxxxx000000000000xxxxxxx0xxxxx00000000000000000\rxxxxxxxxx00000000000000000000000000000000000000000\rxxxxxxxxxx000000000000000000000000000000000000000x\rxxxxxxxxxxx0000000000000000000000000000000000000xx\rxxxxxxxxxxxx00000000000000000000000000000000000xxx\rxxxxxxxxxxxxx000000000000000000000000000000000xxxx\rxxxxxxxxxxxxxx0000000000000000000000000000000xxxxx\rxxxxxxxxxxxxxxx00000000000000000000000000000xxxxxx\rxxxxxxxxxxxxxxxx000000000000000000000000000xxxxxxx\rxxxxxxxxxxxxxxxxx0000000000000000000000000xxxxxxxx\rxxxxxxxxxxxxxxxxxx00000000000000000000000xxxxxxxxx\rxxxxxxxxxxxxxxxxxxx000000000000000000000xxxxxxxxxx\rxxxxxxxxxxxxxxxxxxxx0000000000000000000xxxxxxxxxxx\rxxxxxxxxxxxxxxxxxxxxx00000000000000000xxxxxxxxxxxx\rxxxxxxxxxxxxxxxxxxxxxx000000000000000xxxxxxxxxxxxx\rxxxxxxxxxxxxxxxxxxxxxxx0000000000000xxxxxxxxxxxxxx\rxxxxxxxxxxxxxxxxxxxxxxxx00000000000xxxxxxxxxxxxxxx\rxxxxxxxxxxxxxxxxxxxxxxxxx000000000xxxxxxxxxxxxxxxx\rxxxxxxxxxxxxxxxxxxxxxxxxxx0000000xxxxxxxxxxxxxxxxx\rxxxxxxxxxxxxxxxxxxxxxxxxxxx00000xxxxxxxxxxxxxxxxxx\r";
/*   21:     */     
/*   22:     */ 
/*   23:     */ 
/*   24:     */ 
/*   25:     */ 
/*   26:     */ 
/*   27:     */ 
/*   28:     */ 
/*   29:     */ 
/*   30:     */ 
/*   31:     */ 
/*   32:     */ 
/*   33:     */ 
/*   34:     */ 
/*   35:     */ 
/*   36:     */ 
/*   37:     */ 
/*   38:     */ 
/*   39:     */ 
/*   40:     */ 
/*   41:     */ 
/*   42:     */ 
/*   43:     */ 
/*   44:     */ 
/*   45:     */ 
/*   46:     */ 
/*   47:     */ 
/*   48:     */ 
/*   49:     */ 
/*   50:     */ 
/*   51:     */ 
/*   52:     */ 
/*   53:     */ 
/*   54:     */ 
/*   55:     */ 
/*   56:     */ 
/*   57:     */ 
/*   58:     */ 
/*   59:     */ 
/*   60:     */ 
/*   61:     */ 
/*   62:     */ 
/*   63:     */ 
/*   64:     */ 
/*   65:     */ 
/*   66:     */ 
/*   67:     */ 
/*   68:     */ 
/*   69:     */ 
/*   70:     */ 
/*   71:     */ 
/*   72:  76 */     GamefuseObject Item = new GamefuseObject();
/*   73:  77 */     Item.baseItem = BaseItem.snst_block1;
/*   74:  78 */     Item.baseItem.allowWalk = false;
/*   75:  79 */     Item.baseItem.Height = 1.0F;
/*   76:  80 */     Item.itemId = 0;
/*   77:  81 */     Item.X = 41;
/*   78:  82 */     Item.Y = 37;
/*   79:  83 */     Item.Rot = 0;
/*   80:  84 */     Item.Z = 0;
/*   81:  85 */     Item.extraData.setExtraData("");
/*   82:  86 */     this.fuseObjects.add(Item);
/*   83:     */     
/*   84:  88 */     Item = new GamefuseObject();
/*   85:  89 */     Item.baseItem = BaseItem.snst_block1;
/*   86:  90 */     Item.baseItem.allowWalk = false;
/*   87:  91 */     Item.baseItem.Height = 1.0F;
/*   88:  92 */     Item.itemId = 1;
/*   89:  93 */     Item.X = 2;
/*   90:  94 */     Item.Y = 20;
/*   91:  95 */     Item.Rot = 0;
/*   92:  96 */     Item.Z = 1440;
/*   93:  97 */     Item.extraData.setExtraData("");
/*   94:  98 */     this.fuseObjects.add(Item);
/*   95:     */     
/*   96: 100 */     Item = new GamefuseObject();
/*   97: 101 */     Item.baseItem = BaseItem.snst_block1;
/*   98: 102 */     Item.baseItem.allowWalk = false;
/*   99: 103 */     Item.baseItem.Height = 1.0F;
/*  100: 104 */     Item.itemId = 2;
/*  101: 105 */     Item.X = 10;
/*  102: 106 */     Item.Y = 18;
/*  103: 107 */     Item.Rot = 6;
/*  104: 108 */     Item.Z = 1440;
/*  105: 109 */     Item.extraData.setExtraData("");
/*  106: 110 */     this.fuseObjects.add(Item);
/*  107:     */     
/*  108: 112 */     Item = new GamefuseObject();
/*  109: 113 */     Item.baseItem = BaseItem.snst_tree1;
/*  110: 114 */     Item.baseItem.allowWalk = false;
/*  111: 115 */     Item.baseItem.Height = 1.0F;
/*  112: 116 */     Item.itemId = 3;
/*  113: 117 */     Item.X = 19;
/*  114: 118 */     Item.Y = 41;
/*  115: 119 */     Item.Rot = 0;
/*  116: 120 */     Item.Z = 0;
/*  117: 121 */     Item.extraData.setExtraData("0");
/*  118: 122 */     this.fuseObjects.add(Item);
/*  119:     */     
/*  120: 124 */     Item = new GamefuseObject();
/*  121: 125 */     Item.baseItem = BaseItem.snst_block1;
/*  122: 126 */     Item.baseItem.allowWalk = false;
/*  123: 127 */     Item.baseItem.Height = 1.0F;
/*  124: 128 */     Item.itemId = 4;
/*  125: 129 */     Item.X = 41;
/*  126: 130 */     Item.Y = 20;
/*  127: 131 */     Item.Rot = 0;
/*  128: 132 */     Item.Z = 0;
/*  129: 133 */     Item.extraData.setExtraData("");
/*  130: 134 */     this.fuseObjects.add(Item);
/*  131:     */     
/*  132: 136 */     Item = new GamefuseObject();
/*  133: 137 */     Item.baseItem = BaseItem.snst_tree1;
/*  134: 138 */     Item.baseItem.allowWalk = false;
/*  135: 139 */     Item.baseItem.Height = 1.0F;
/*  136: 140 */     Item.itemId = 5;
/*  137: 141 */     Item.X = 9;
/*  138: 142 */     Item.Y = 31;
/*  139: 143 */     Item.Rot = 0;
/*  140: 144 */     Item.Z = 0;
/*  141: 145 */     Item.extraData.setExtraData("0");
/*  142: 146 */     this.fuseObjects.add(Item);
/*  143:     */     
/*  144: 148 */     Item = new GamefuseObject();
/*  145: 149 */     Item.baseItem = BaseItem.snst_block1;
/*  146: 150 */     Item.baseItem.allowWalk = false;
/*  147: 151 */     Item.baseItem.Height = 1.0F;
/*  148: 152 */     Item.itemId = 6;
/*  149: 153 */     Item.X = 23;
/*  150: 154 */     Item.Y = 44;
/*  151: 155 */     Item.Rot = 0;
/*  152: 156 */     Item.Z = 1440;
/*  153: 157 */     Item.extraData.setExtraData("");
/*  154: 158 */     this.fuseObjects.add(Item);
/*  155:     */     
/*  156: 160 */     Item = new GamefuseObject();
/*  157: 161 */     Item.baseItem = BaseItem.snst_block1;
/*  158: 162 */     Item.baseItem.allowWalk = false;
/*  159: 163 */     Item.baseItem.Height = 1.0F;
/*  160: 164 */     Item.itemId = 7;
/*  161: 165 */     Item.X = 4;
/*  162: 166 */     Item.Y = 18;
/*  163: 167 */     Item.Rot = 0;
/*  164: 168 */     Item.Z = 0;
/*  165: 169 */     Item.extraData.setExtraData("");
/*  166: 170 */     this.fuseObjects.add(Item);
/*  167:     */     
/*  168: 172 */     Item = new GamefuseObject();
/*  169: 173 */     Item.baseItem = BaseItem.snst_block1;
/*  170: 174 */     Item.baseItem.allowWalk = false;
/*  171: 175 */     Item.baseItem.Height = 1.0F;
/*  172: 176 */     Item.itemId = 8;
/*  173: 177 */     Item.X = 2;
/*  174: 178 */     Item.Y = 20;
/*  175: 179 */     Item.Rot = 0;
/*  176: 180 */     Item.Z = 0;
/*  177: 181 */     Item.extraData.setExtraData("");
/*  178: 182 */     this.fuseObjects.add(Item);
/*  179:     */     
/*  180: 184 */     Item = new GamefuseObject();
/*  181: 185 */     Item.baseItem = BaseItem.snst_tree1;
/*  182: 186 */     Item.baseItem.allowWalk = false;
/*  183: 187 */     Item.baseItem.Height = 1.0F;
/*  184: 188 */     Item.itemId = 9;
/*  185: 189 */     Item.X = 25;
/*  186: 190 */     Item.Y = 38;
/*  187: 191 */     Item.Rot = 0;
/*  188: 192 */     Item.Z = 0;
/*  189: 193 */     Item.extraData.setExtraData("0");
/*  190: 194 */     this.fuseObjects.add(Item);
/*  191:     */     
/*  192: 196 */     Item = new GamefuseObject();
/*  193: 197 */     Item.baseItem = BaseItem.snst_block1;
/*  194: 198 */     Item.baseItem.allowWalk = false;
/*  195: 199 */     Item.baseItem.Height = 1.0F;
/*  196: 200 */     Item.itemId = 10;
/*  197: 201 */     Item.X = 2;
/*  198: 202 */     Item.Y = 19;
/*  199: 203 */     Item.Rot = 0;
/*  200: 204 */     Item.Z = 0;
/*  201: 205 */     Item.extraData.setExtraData("");
/*  202: 206 */     this.fuseObjects.add(Item);
/*  203:     */     
/*  204: 208 */     Item = new GamefuseObject();
/*  205: 209 */     Item.baseItem = BaseItem.snst_block1;
/*  206: 210 */     Item.baseItem.allowWalk = false;
/*  207: 211 */     Item.baseItem.Height = 1.0F;
/*  208: 212 */     Item.itemId = 11;
/*  209: 213 */     Item.X = 9;
/*  210: 214 */     Item.Y = 18;
/*  211: 215 */     Item.Rot = 0;
/*  212: 216 */     Item.Z = 0;
/*  213: 217 */     Item.extraData.setExtraData("");
/*  214: 218 */     this.fuseObjects.add(Item);
/*  215:     */     
/*  216: 220 */     Item = new GamefuseObject();
/*  217: 221 */     Item.baseItem = BaseItem.snst_block1;
/*  218: 222 */     Item.baseItem.allowWalk = false;
/*  219: 223 */     Item.baseItem.Height = 1.0F;
/*  220: 224 */     Item.itemId = 12;
/*  221: 225 */     Item.X = 7;
/*  222: 226 */     Item.Y = 18;
/*  223: 227 */     Item.Rot = 0;
/*  224: 228 */     Item.Z = 0;
/*  225: 229 */     Item.extraData.setExtraData("");
/*  226: 230 */     this.fuseObjects.add(Item);
/*  227:     */     
/*  228: 232 */     Item = new GamefuseObject();
/*  229: 233 */     Item.baseItem = BaseItem.snst_fence;
/*  230: 234 */     Item.baseItem.allowWalk = false;
/*  231: 235 */     Item.baseItem.Height = 0.0F;
/*  232: 236 */     Item.itemId = 13;
/*  233: 237 */     Item.X = 17;
/*  234: 238 */     Item.Y = 14;
/*  235: 239 */     Item.Rot = 2;
/*  236: 240 */     Item.Z = 0;
/*  237: 241 */     Item.extraData.setExtraData("2");
/*  238: 242 */     this.fuseObjects.add(Item);
/*  239:     */     
/*  240: 244 */     Item = new GamefuseObject();
/*  241: 245 */     Item.baseItem = BaseItem.snst_block1;
/*  242: 246 */     Item.baseItem.allowWalk = false;
/*  243: 247 */     Item.baseItem.Height = 1.0F;
/*  244: 248 */     Item.itemId = 14;
/*  245: 249 */     Item.X = 2;
/*  246: 250 */     Item.Y = 18;
/*  247: 251 */     Item.Rot = 0;
/*  248: 252 */     Item.Z = 0;
/*  249: 253 */     Item.extraData.setExtraData("");
/*  250: 254 */     this.fuseObjects.add(Item);
/*  251:     */     
/*  252: 256 */     Item = new GamefuseObject();
/*  253: 257 */     Item.baseItem = BaseItem.snst_tree1;
/*  254: 258 */     Item.baseItem.allowWalk = false;
/*  255: 259 */     Item.baseItem.Height = 1.0F;
/*  256: 260 */     Item.itemId = 15;
/*  257: 261 */     Item.X = 49;
/*  258: 262 */     Item.Y = 28;
/*  259: 263 */     Item.Rot = 0;
/*  260: 264 */     Item.Z = 0;
/*  261: 265 */     Item.extraData.setExtraData("0");
/*  262: 266 */     this.fuseObjects.add(Item);
/*  263:     */     
/*  264: 268 */     Item = new GamefuseObject();
/*  265: 269 */     Item.baseItem = BaseItem.snst_block1;
/*  266: 270 */     Item.baseItem.allowWalk = false;
/*  267: 271 */     Item.baseItem.Height = 1.0F;
/*  268: 272 */     Item.itemId = 16;
/*  269: 273 */     Item.X = 2;
/*  270: 274 */     Item.Y = 22;
/*  271: 275 */     Item.Rot = 6;
/*  272: 276 */     Item.Z = 0;
/*  273: 277 */     Item.extraData.setExtraData("");
/*  274: 278 */     this.fuseObjects.add(Item);
/*  275:     */     
/*  276: 280 */     Item = new GamefuseObject();
/*  277: 281 */     Item.baseItem = BaseItem.snst_block1;
/*  278: 282 */     Item.baseItem.allowWalk = false;
/*  279: 283 */     Item.baseItem.Height = 1.0F;
/*  280: 284 */     Item.itemId = 17;
/*  281: 285 */     Item.X = 5;
/*  282: 286 */     Item.Y = 18;
/*  283: 287 */     Item.Rot = 0;
/*  284: 288 */     Item.Z = 0;
/*  285: 289 */     Item.extraData.setExtraData("");
/*  286: 290 */     this.fuseObjects.add(Item);
/*  287:     */     
/*  288: 292 */     Item = new GamefuseObject();
/*  289: 293 */     Item.baseItem = BaseItem.snst_block1;
/*  290: 294 */     Item.baseItem.allowWalk = false;
/*  291: 295 */     Item.baseItem.Height = 1.0F;
/*  292: 296 */     Item.itemId = 18;
/*  293: 297 */     Item.X = 4;
/*  294: 298 */     Item.Y = 18;
/*  295: 299 */     Item.Rot = 0;
/*  296: 300 */     Item.Z = 1440;
/*  297: 301 */     Item.extraData.setExtraData("");
/*  298: 302 */     this.fuseObjects.add(Item);
/*  299:     */     
/*  300: 304 */     Item = new GamefuseObject();
/*  301: 305 */     Item.baseItem = BaseItem.snst_block1;
/*  302: 306 */     Item.baseItem.allowWalk = false;
/*  303: 307 */     Item.baseItem.Height = 1.0F;
/*  304: 308 */     Item.itemId = 19;
/*  305: 309 */     Item.X = 39;
/*  306: 310 */     Item.Y = 22;
/*  307: 311 */     Item.Rot = 2;
/*  308: 312 */     Item.Z = 1440;
/*  309: 313 */     Item.extraData.setExtraData("");
/*  310: 314 */     this.fuseObjects.add(Item);
/*  311:     */     
/*  312: 316 */     Item = new GamefuseObject();
/*  313: 317 */     Item.baseItem = BaseItem.snst_block1;
/*  314: 318 */     Item.baseItem.allowWalk = false;
/*  315: 319 */     Item.baseItem.Height = 1.0F;
/*  316: 320 */     Item.itemId = 20;
/*  317: 321 */     Item.X = 39;
/*  318: 322 */     Item.Y = 23;
/*  319: 323 */     Item.Rot = 0;
/*  320: 324 */     Item.Z = 0;
/*  321: 325 */     Item.extraData.setExtraData("");
/*  322: 326 */     this.fuseObjects.add(Item);
/*  323:     */     
/*  324: 328 */     Item = new GamefuseObject();
/*  325: 329 */     Item.baseItem = BaseItem.snst_fence;
/*  326: 330 */     Item.baseItem.allowWalk = false;
/*  327: 331 */     Item.baseItem.Height = 0.0F;
/*  328: 332 */     Item.itemId = 21;
/*  329: 333 */     Item.X = 24;
/*  330: 334 */     Item.Y = 44;
/*  331: 335 */     Item.Rot = 0;
/*  332: 336 */     Item.Z = 0;
/*  333: 337 */     Item.extraData.setExtraData("0");
/*  334: 338 */     this.fuseObjects.add(Item);
/*  335:     */     
/*  336: 340 */     Item = new GamefuseObject();
/*  337: 341 */     Item.baseItem = BaseItem.snst_tree1;
/*  338: 342 */     Item.baseItem.allowWalk = false;
/*  339: 343 */     Item.baseItem.Height = 1.0F;
/*  340: 344 */     Item.itemId = 22;
/*  341: 345 */     Item.X = 36;
/*  342: 346 */     Item.Y = 15;
/*  343: 347 */     Item.Rot = 0;
/*  344: 348 */     Item.Z = 0;
/*  345: 349 */     Item.extraData.setExtraData("0");
/*  346: 350 */     this.fuseObjects.add(Item);
/*  347:     */     
/*  348: 352 */     Item = new GamefuseObject();
/*  349: 353 */     Item.baseItem = BaseItem.snst_block1;
/*  350: 354 */     Item.baseItem.allowWalk = false;
/*  351: 355 */     Item.baseItem.Height = 1.0F;
/*  352: 356 */     Item.itemId = 23;
/*  353: 357 */     Item.X = 23;
/*  354: 358 */     Item.Y = 44;
/*  355: 359 */     Item.Rot = 0;
/*  356: 360 */     Item.Z = 0;
/*  357: 361 */     Item.extraData.setExtraData("");
/*  358: 362 */     this.fuseObjects.add(Item);
/*  359:     */     
/*  360: 364 */     Item = new GamefuseObject();
/*  361: 365 */     Item.baseItem = BaseItem.snst_tree1;
/*  362: 366 */     Item.baseItem.allowWalk = false;
/*  363: 367 */     Item.baseItem.Height = 1.0F;
/*  364: 368 */     Item.itemId = 24;
/*  365: 369 */     Item.X = 47;
/*  366: 370 */     Item.Y = 32;
/*  367: 371 */     Item.Rot = 0;
/*  368: 372 */     Item.Z = 0;
/*  369: 373 */     Item.extraData.setExtraData("0");
/*  370: 374 */     this.fuseObjects.add(Item);
/*  371:     */     
/*  372: 376 */     Item = new GamefuseObject();
/*  373: 377 */     Item.baseItem = BaseItem.snst_block1;
/*  374: 378 */     Item.baseItem.allowWalk = false;
/*  375: 379 */     Item.baseItem.Height = 1.0F;
/*  376: 380 */     Item.itemId = 25;
/*  377: 381 */     Item.X = 39;
/*  378: 382 */     Item.Y = 37;
/*  379: 383 */     Item.Rot = 0;
/*  380: 384 */     Item.Z = 0;
/*  381: 385 */     Item.extraData.setExtraData("");
/*  382: 386 */     this.fuseObjects.add(Item);
/*  383:     */     
/*  384: 388 */     Item = new GamefuseObject();
/*  385: 389 */     Item.baseItem = BaseItem.snst_block1;
/*  386: 390 */     Item.baseItem.allowWalk = false;
/*  387: 391 */     Item.baseItem.Height = 1.0F;
/*  388: 392 */     Item.itemId = 26;
/*  389: 393 */     Item.X = 8;
/*  390: 394 */     Item.Y = 18;
/*  391: 395 */     Item.Rot = 0;
/*  392: 396 */     Item.Z = 0;
/*  393: 397 */     Item.extraData.setExtraData("");
/*  394: 398 */     this.fuseObjects.add(Item);
/*  395:     */     
/*  396: 400 */     Item = new GamefuseObject();
/*  397: 401 */     Item.baseItem = BaseItem.snst_block1;
/*  398: 402 */     Item.baseItem.allowWalk = false;
/*  399: 403 */     Item.baseItem.Height = 1.0F;
/*  400: 404 */     Item.itemId = 27;
/*  401: 405 */     Item.X = 2;
/*  402: 406 */     Item.Y = 19;
/*  403: 407 */     Item.Rot = 0;
/*  404: 408 */     Item.Z = 1440;
/*  405: 409 */     Item.extraData.setExtraData("");
/*  406: 410 */     this.fuseObjects.add(Item);
/*  407:     */     
/*  408: 412 */     Item = new GamefuseObject();
/*  409: 413 */     Item.baseItem = BaseItem.snst_tree1;
/*  410: 414 */     Item.baseItem.allowWalk = false;
/*  411: 415 */     Item.baseItem.Height = 1.0F;
/*  412: 416 */     Item.itemId = 28;
/*  413: 417 */     Item.X = 6;
/*  414: 418 */     Item.Y = 20;
/*  415: 419 */     Item.Rot = 0;
/*  416: 420 */     Item.Z = 0;
/*  417: 421 */     Item.extraData.setExtraData("0");
/*  418: 422 */     this.fuseObjects.add(Item);
/*  419:     */     
/*  420: 424 */     Item = new GamefuseObject();
/*  421: 425 */     Item.baseItem = BaseItem.snst_block1;
/*  422: 426 */     Item.baseItem.allowWalk = false;
/*  423: 427 */     Item.baseItem.Height = 1.0F;
/*  424: 428 */     Item.itemId = 29;
/*  425: 429 */     Item.X = 2;
/*  426: 430 */     Item.Y = 18;
/*  427: 431 */     Item.Rot = 0;
/*  428: 432 */     Item.Z = 1440;
/*  429: 433 */     Item.extraData.setExtraData("");
/*  430: 434 */     this.fuseObjects.add(Item);
/*  431:     */     
/*  432: 436 */     Item = new GamefuseObject();
/*  433: 437 */     Item.baseItem = BaseItem.snst_fence;
/*  434: 438 */     Item.baseItem.allowWalk = false;
/*  435: 439 */     Item.baseItem.Height = 0.0F;
/*  436: 440 */     Item.itemId = 30;
/*  437: 441 */     Item.X = 15;
/*  438: 442 */     Item.Y = 14;
/*  439: 443 */     Item.Rot = 2;
/*  440: 444 */     Item.Z = 0;
/*  441: 445 */     Item.extraData.setExtraData("0");
/*  442: 446 */     this.fuseObjects.add(Item);
/*  443:     */     
/*  444: 448 */     Item = new GamefuseObject();
/*  445: 449 */     Item.baseItem = BaseItem.snst_tree1;
/*  446: 450 */     Item.baseItem.allowWalk = false;
/*  447: 451 */     Item.baseItem.Height = 1.0F;
/*  448: 452 */     Item.itemId = 31;
/*  449: 453 */     Item.X = 26;
/*  450: 454 */     Item.Y = 6;
/*  451: 455 */     Item.Rot = 0;
/*  452: 456 */     Item.Z = 0;
/*  453: 457 */     Item.extraData.setExtraData("0");
/*  454: 458 */     this.fuseObjects.add(Item);
/*  455:     */     
/*  456: 460 */     Item = new GamefuseObject();
/*  457: 461 */     Item.baseItem = BaseItem.snst_block1;
/*  458: 462 */     Item.baseItem.allowWalk = false;
/*  459: 463 */     Item.baseItem.Height = 1.0F;
/*  460: 464 */     Item.itemId = 32;
/*  461: 465 */     Item.X = 39;
/*  462: 466 */     Item.Y = 23;
/*  463: 467 */     Item.Rot = 4;
/*  464: 468 */     Item.Z = 1440;
/*  465: 469 */     Item.extraData.setExtraData("");
/*  466: 470 */     this.fuseObjects.add(Item);
/*  467:     */     
/*  468: 472 */     Item = new GamefuseObject();
/*  469: 473 */     Item.baseItem = BaseItem.snst_block1;
/*  470: 474 */     Item.baseItem.allowWalk = false;
/*  471: 475 */     Item.baseItem.Height = 1.0F;
/*  472: 476 */     Item.itemId = 33;
/*  473: 477 */     Item.X = 23;
/*  474: 478 */     Item.Y = 38;
/*  475: 479 */     Item.Rot = 2;
/*  476: 480 */     Item.Z = 1440;
/*  477: 481 */     Item.extraData.setExtraData("");
/*  478: 482 */     this.fuseObjects.add(Item);
/*  479:     */     
/*  480: 484 */     Item = new GamefuseObject();
/*  481: 485 */     Item.baseItem = BaseItem.snst_tree1;
/*  482: 486 */     Item.baseItem.allowWalk = false;
/*  483: 487 */     Item.baseItem.Height = 1.0F;
/*  484: 488 */     Item.itemId = 34;
/*  485: 489 */     Item.X = 10;
/*  486: 490 */     Item.Y = 26;
/*  487: 491 */     Item.Rot = 0;
/*  488: 492 */     Item.Z = 0;
/*  489: 493 */     Item.extraData.setExtraData("0");
/*  490: 494 */     this.fuseObjects.add(Item);
/*  491:     */     
/*  492: 496 */     Item = new GamefuseObject();
/*  493: 497 */     Item.baseItem = BaseItem.snst_block1;
/*  494: 498 */     Item.baseItem.allowWalk = false;
/*  495: 499 */     Item.baseItem.Height = 1.0F;
/*  496: 500 */     Item.itemId = 35;
/*  497: 501 */     Item.X = 23;
/*  498: 502 */     Item.Y = 45;
/*  499: 503 */     Item.Rot = 4;
/*  500: 504 */     Item.Z = 0;
/*  501: 505 */     Item.extraData.setExtraData("");
/*  502: 506 */     this.fuseObjects.add(Item);
/*  503:     */     
/*  504: 508 */     Item = new GamefuseObject();
/*  505: 509 */     Item.baseItem = BaseItem.snst_block1;
/*  506: 510 */     Item.baseItem.allowWalk = false;
/*  507: 511 */     Item.baseItem.Height = 1.0F;
/*  508: 512 */     Item.itemId = 36;
/*  509: 513 */     Item.X = 39;
/*  510: 514 */     Item.Y = 21;
/*  511: 515 */     Item.Rot = 0;
/*  512: 516 */     Item.Z = 1440;
/*  513: 517 */     Item.extraData.setExtraData("");
/*  514: 518 */     this.fuseObjects.add(Item);
/*  515:     */     
/*  516: 520 */     Item = new GamefuseObject();
/*  517: 521 */     Item.baseItem = BaseItem.snst_block1;
/*  518: 522 */     Item.baseItem.allowWalk = false;
/*  519: 523 */     Item.baseItem.Height = 1.0F;
/*  520: 524 */     Item.itemId = 37;
/*  521: 525 */     Item.X = 5;
/*  522: 526 */     Item.Y = 18;
/*  523: 527 */     Item.Rot = 0;
/*  524: 528 */     Item.Z = 1440;
/*  525: 529 */     Item.extraData.setExtraData("");
/*  526: 530 */     this.fuseObjects.add(Item);
/*  527:     */     
/*  528: 532 */     Item = new GamefuseObject();
/*  529: 533 */     Item.baseItem = BaseItem.snst_block1;
/*  530: 534 */     Item.baseItem.allowWalk = false;
/*  531: 535 */     Item.baseItem.Height = 1.0F;
/*  532: 536 */     Item.itemId = 38;
/*  533: 537 */     Item.X = 39;
/*  534: 538 */     Item.Y = 24;
/*  535: 539 */     Item.Rot = 6;
/*  536: 540 */     Item.Z = 1440;
/*  537: 541 */     Item.extraData.setExtraData("");
/*  538: 542 */     this.fuseObjects.add(Item);
/*  539:     */     
/*  540: 544 */     Item = new GamefuseObject();
/*  541: 545 */     Item.baseItem = BaseItem.snst_tree1;
/*  542: 546 */     Item.baseItem.allowWalk = false;
/*  543: 547 */     Item.baseItem.Height = 1.0F;
/*  544: 548 */     Item.itemId = 39;
/*  545: 549 */     Item.X = 13;
/*  546: 550 */     Item.Y = 15;
/*  547: 551 */     Item.Rot = 0;
/*  548: 552 */     Item.Z = 0;
/*  549: 553 */     Item.extraData.setExtraData("0");
/*  550: 554 */     this.fuseObjects.add(Item);
/*  551:     */     
/*  552: 556 */     Item = new GamefuseObject();
/*  553: 557 */     Item.baseItem = BaseItem.snst_block1;
/*  554: 558 */     Item.baseItem.allowWalk = false;
/*  555: 559 */     Item.baseItem.Height = 1.0F;
/*  556: 560 */     Item.itemId = 40;
/*  557: 561 */     Item.X = 3;
/*  558: 562 */     Item.Y = 18;
/*  559: 563 */     Item.Rot = 0;
/*  560: 564 */     Item.Z = 1440;
/*  561: 565 */     Item.extraData.setExtraData("");
/*  562: 566 */     this.fuseObjects.add(Item);
/*  563:     */     
/*  564: 568 */     Item = new GamefuseObject();
/*  565: 569 */     Item.baseItem = BaseItem.snst_fence;
/*  566: 570 */     Item.baseItem.allowWalk = false;
/*  567: 571 */     Item.baseItem.Height = 0.0F;
/*  568: 572 */     Item.itemId = 41;
/*  569: 573 */     Item.X = 13;
/*  570: 574 */     Item.Y = 14;
/*  571: 575 */     Item.Rot = 2;
/*  572: 576 */     Item.Z = 0;
/*  573: 577 */     Item.extraData.setExtraData("1");
/*  574: 578 */     this.fuseObjects.add(Item);
/*  575:     */     
/*  576: 580 */     Item = new GamefuseObject();
/*  577: 581 */     Item.baseItem = BaseItem.snst_tree1;
/*  578: 582 */     Item.baseItem.allowWalk = false;
/*  579: 583 */     Item.baseItem.Height = 1.0F;
/*  580: 584 */     Item.itemId = 42;
/*  581: 585 */     Item.X = 30;
/*  582: 586 */     Item.Y = 7;
/*  583: 587 */     Item.Rot = 0;
/*  584: 588 */     Item.Z = 0;
/*  585: 589 */     Item.extraData.setExtraData("0");
/*  586: 590 */     this.fuseObjects.add(Item);
/*  587:     */     
/*  588: 592 */     Item = new GamefuseObject();
/*  589: 593 */     Item.baseItem = BaseItem.snst_block1;
/*  590: 594 */     Item.baseItem.allowWalk = false;
/*  591: 595 */     Item.baseItem.Height = 1.0F;
/*  592: 596 */     Item.itemId = 43;
/*  593: 597 */     Item.X = 40;
/*  594: 598 */     Item.Y = 20;
/*  595: 599 */     Item.Rot = 0;
/*  596: 600 */     Item.Z = 0;
/*  597: 601 */     Item.extraData.setExtraData("");
/*  598: 602 */     this.fuseObjects.add(Item);
/*  599:     */     
/*  600: 604 */     Item = new GamefuseObject();
/*  601: 605 */     Item.baseItem = BaseItem.snst_fence;
/*  602: 606 */     Item.baseItem.allowWalk = false;
/*  603: 607 */     Item.baseItem.Height = 0.0F;
/*  604: 608 */     Item.itemId = 44;
/*  605: 609 */     Item.X = 29;
/*  606: 610 */     Item.Y = 7;
/*  607: 611 */     Item.Rot = 0;
/*  608: 612 */     Item.Z = 0;
/*  609: 613 */     Item.extraData.setExtraData("1");
/*  610: 614 */     this.fuseObjects.add(Item);
/*  611:     */     
/*  612: 616 */     Item = new GamefuseObject();
/*  613: 617 */     Item.baseItem = BaseItem.snst_fence;
/*  614: 618 */     Item.baseItem.allowWalk = false;
/*  615: 619 */     Item.baseItem.Height = 0.0F;
/*  616: 620 */     Item.itemId = 45;
/*  617: 621 */     Item.X = 21;
/*  618: 622 */     Item.Y = 14;
/*  619: 623 */     Item.Rot = 2;
/*  620: 624 */     Item.Z = 0;
/*  621: 625 */     Item.extraData.setExtraData("2");
/*  622: 626 */     this.fuseObjects.add(Item);
/*  623:     */     
/*  624: 628 */     Item = new GamefuseObject();
/*  625: 629 */     Item.baseItem = BaseItem.snst_fence;
/*  626: 630 */     Item.baseItem.allowWalk = false;
/*  627: 631 */     Item.baseItem.Height = 0.0F;
/*  628: 632 */     Item.itemId = 46;
/*  629: 633 */     Item.X = 24;
/*  630: 634 */     Item.Y = 40;
/*  631: 635 */     Item.Rot = 0;
/*  632: 636 */     Item.Z = 0;
/*  633: 637 */     Item.extraData.setExtraData("0");
/*  634: 638 */     this.fuseObjects.add(Item);
/*  635:     */     
/*  636: 640 */     Item = new GamefuseObject();
/*  637: 641 */     Item.baseItem = BaseItem.snst_block1;
/*  638: 642 */     Item.baseItem.allowWalk = false;
/*  639: 643 */     Item.baseItem.Height = 1.0F;
/*  640: 644 */     Item.itemId = 47;
/*  641: 645 */     Item.X = 23;
/*  642: 646 */     Item.Y = 40;
/*  643: 647 */     Item.Rot = 0;
/*  644: 648 */     Item.Z = 1440;
/*  645: 649 */     Item.extraData.setExtraData("");
/*  646: 650 */     this.fuseObjects.add(Item);
/*  647:     */     
/*  648: 652 */     Item = new GamefuseObject();
/*  649: 653 */     Item.baseItem = BaseItem.snst_tree1;
/*  650: 654 */     Item.baseItem.allowWalk = false;
/*  651: 655 */     Item.baseItem.Height = 1.0F;
/*  652: 656 */     Item.itemId = 48;
/*  653: 657 */     Item.X = 15;
/*  654: 658 */     Item.Y = 10;
/*  655: 659 */     Item.Rot = 0;
/*  656: 660 */     Item.Z = 0;
/*  657: 661 */     Item.extraData.setExtraData("0");
/*  658: 662 */     this.fuseObjects.add(Item);
/*  659:     */     
/*  660: 664 */     Item = new GamefuseObject();
/*  661: 665 */     Item.baseItem = BaseItem.snst_block1;
/*  662: 666 */     Item.baseItem.allowWalk = false;
/*  663: 667 */     Item.baseItem.Height = 1.0F;
/*  664: 668 */     Item.itemId = 49;
/*  665: 669 */     Item.X = 37;
/*  666: 670 */     Item.Y = 37;
/*  667: 671 */     Item.Rot = 0;
/*  668: 672 */     Item.Z = 0;
/*  669: 673 */     Item.extraData.setExtraData("");
/*  670: 674 */     this.fuseObjects.add(Item);
/*  671:     */     
/*  672: 676 */     Item = new GamefuseObject();
/*  673: 677 */     Item.baseItem = BaseItem.snst_tree1;
/*  674: 678 */     Item.baseItem.allowWalk = false;
/*  675: 679 */     Item.baseItem.Height = 1.0F;
/*  676: 680 */     Item.itemId = 50;
/*  677: 681 */     Item.X = 20;
/*  678: 682 */     Item.Y = 4;
/*  679: 683 */     Item.Rot = 0;
/*  680: 684 */     Item.Z = 0;
/*  681: 685 */     Item.extraData.setExtraData("0");
/*  682: 686 */     this.fuseObjects.add(Item);
/*  683:     */     
/*  684: 688 */     Item = new GamefuseObject();
/*  685: 689 */     Item.baseItem = BaseItem.snst_block1;
/*  686: 690 */     Item.baseItem.allowWalk = false;
/*  687: 691 */     Item.baseItem.Height = 1.0F;
/*  688: 692 */     Item.itemId = 51;
/*  689: 693 */     Item.X = 3;
/*  690: 694 */     Item.Y = 18;
/*  691: 695 */     Item.Rot = 0;
/*  692: 696 */     Item.Z = 0;
/*  693: 697 */     Item.extraData.setExtraData("");
/*  694: 698 */     this.fuseObjects.add(Item);
/*  695:     */     
/*  696: 700 */     Item = new GamefuseObject();
/*  697: 701 */     Item.baseItem = BaseItem.snst_block1;
/*  698: 702 */     Item.baseItem.allowWalk = false;
/*  699: 703 */     Item.baseItem.Height = 1.0F;
/*  700: 704 */     Item.itemId = 52;
/*  701: 705 */     Item.X = 23;
/*  702: 706 */     Item.Y = 40;
/*  703: 707 */     Item.Rot = 0;
/*  704: 708 */     Item.Z = 0;
/*  705: 709 */     Item.extraData.setExtraData("");
/*  706: 710 */     this.fuseObjects.add(Item);
/*  707:     */     
/*  708: 712 */     Item = new GamefuseObject();
/*  709: 713 */     Item.baseItem = BaseItem.snst_block1;
/*  710: 714 */     Item.baseItem.allowWalk = false;
/*  711: 715 */     Item.baseItem.Height = 1.0F;
/*  712: 716 */     Item.itemId = 53;
/*  713: 717 */     Item.X = 2;
/*  714: 718 */     Item.Y = 22;
/*  715: 719 */     Item.Rot = 2;
/*  716: 720 */     Item.Z = 1440;
/*  717: 721 */     Item.extraData.setExtraData("");
/*  718: 722 */     this.fuseObjects.add(Item);
/*  719:     */     
/*  720: 724 */     Item = new GamefuseObject();
/*  721: 725 */     Item.baseItem = BaseItem.snst_block1;
/*  722: 726 */     Item.baseItem.allowWalk = false;
/*  723: 727 */     Item.baseItem.Height = 1.0F;
/*  724: 728 */     Item.itemId = 54;
/*  725: 729 */     Item.X = 43;
/*  726: 730 */     Item.Y = 20;
/*  727: 731 */     Item.Rot = 6;
/*  728: 732 */     Item.Z = 1440;
/*  729: 733 */     Item.extraData.setExtraData("");
/*  730: 734 */     this.fuseObjects.add(Item);
/*  731:     */     
/*  732: 736 */     Item = new GamefuseObject();
/*  733: 737 */     Item.baseItem = BaseItem.snst_block1;
/*  734: 738 */     Item.baseItem.allowWalk = false;
/*  735: 739 */     Item.baseItem.Height = 1.0F;
/*  736: 740 */     Item.itemId = 55;
/*  737: 741 */     Item.X = 23;
/*  738: 742 */     Item.Y = 39;
/*  739: 743 */     Item.Rot = 0;
/*  740: 744 */     Item.Z = 0;
/*  741: 745 */     Item.extraData.setExtraData("");
/*  742: 746 */     this.fuseObjects.add(Item);
/*  743:     */     
/*  744: 748 */     Item = new GamefuseObject();
/*  745: 749 */     Item.baseItem = BaseItem.snst_tree1;
/*  746: 750 */     Item.baseItem.allowWalk = false;
/*  747: 751 */     Item.baseItem.Height = 1.0F;
/*  748: 752 */     Item.itemId = 56;
/*  749: 753 */     Item.X = 45;
/*  750: 754 */     Item.Y = 25;
/*  751: 755 */     Item.Rot = 0;
/*  752: 756 */     Item.Z = 0;
/*  753: 757 */     Item.extraData.setExtraData("0");
/*  754: 758 */     this.fuseObjects.add(Item);
/*  755:     */     
/*  756: 760 */     Item = new GamefuseObject();
/*  757: 761 */     Item.baseItem = BaseItem.snst_block1;
/*  758: 762 */     Item.baseItem.allowWalk = false;
/*  759: 763 */     Item.baseItem.Height = 1.0F;
/*  760: 764 */     Item.itemId = 57;
/*  761: 765 */     Item.X = 23;
/*  762: 766 */     Item.Y = 42;
/*  763: 767 */     Item.Rot = 0;
/*  764: 768 */     Item.Z = 0;
/*  765: 769 */     Item.extraData.setExtraData("");
/*  766: 770 */     this.fuseObjects.add(Item);
/*  767:     */     
/*  768: 772 */     Item = new GamefuseObject();
/*  769: 773 */     Item.baseItem = BaseItem.snst_block1;
/*  770: 774 */     Item.baseItem.allowWalk = false;
/*  771: 775 */     Item.baseItem.Height = 1.0F;
/*  772: 776 */     Item.itemId = 58;
/*  773: 777 */     Item.X = 42;
/*  774: 778 */     Item.Y = 20;
/*  775: 779 */     Item.Rot = 0;
/*  776: 780 */     Item.Z = 0;
/*  777: 781 */     Item.extraData.setExtraData("");
/*  778: 782 */     this.fuseObjects.add(Item);
/*  779:     */     
/*  780: 784 */     Item = new GamefuseObject();
/*  781: 785 */     Item.baseItem = BaseItem.snst_block1;
/*  782: 786 */     Item.baseItem.allowWalk = false;
/*  783: 787 */     Item.baseItem.Height = 1.0F;
/*  784: 788 */     Item.itemId = 59;
/*  785: 789 */     Item.X = 9;
/*  786: 790 */     Item.Y = 18;
/*  787: 791 */     Item.Rot = 0;
/*  788: 792 */     Item.Z = 1440;
/*  789: 793 */     Item.extraData.setExtraData("");
/*  790: 794 */     this.fuseObjects.add(Item);
/*  791:     */     
/*  792: 796 */     Item = new GamefuseObject();
/*  793: 797 */     Item.baseItem = BaseItem.snst_block1;
/*  794: 798 */     Item.baseItem.allowWalk = false;
/*  795: 799 */     Item.baseItem.Height = 1.0F;
/*  796: 800 */     Item.itemId = 60;
/*  797: 801 */     Item.X = 10;
/*  798: 802 */     Item.Y = 18;
/*  799: 803 */     Item.Rot = 0;
/*  800: 804 */     Item.Z = 0;
/*  801: 805 */     Item.extraData.setExtraData("");
/*  802: 806 */     this.fuseObjects.add(Item);
/*  803:     */     
/*  804: 808 */     Item = new GamefuseObject();
/*  805: 809 */     Item.baseItem = BaseItem.snst_fence;
/*  806: 810 */     Item.baseItem.allowWalk = false;
/*  807: 811 */     Item.baseItem.Height = 0.0F;
/*  808: 812 */     Item.itemId = 61;
/*  809: 813 */     Item.X = 24;
/*  810: 814 */     Item.Y = 38;
/*  811: 815 */     Item.Rot = 0;
/*  812: 816 */     Item.Z = 0;
/*  813: 817 */     Item.extraData.setExtraData("1");
/*  814: 818 */     this.fuseObjects.add(Item);
/*  815:     */     
/*  816: 820 */     Item = new GamefuseObject();
/*  817: 821 */     Item.baseItem = BaseItem.snst_block1;
/*  818: 822 */     Item.baseItem.allowWalk = false;
/*  819: 823 */     Item.baseItem.Height = 1.0F;
/*  820: 824 */     Item.itemId = 62;
/*  821: 825 */     Item.X = 11;
/*  822: 826 */     Item.Y = 18;
/*  823: 827 */     Item.Rot = 0;
/*  824: 828 */     Item.Z = 0;
/*  825: 829 */     Item.extraData.setExtraData("");
/*  826: 830 */     this.fuseObjects.add(Item);
/*  827:     */     
/*  828: 832 */     Item = new GamefuseObject();
/*  829: 833 */     Item.baseItem = BaseItem.snst_block1;
/*  830: 834 */     Item.baseItem.allowWalk = false;
/*  831: 835 */     Item.baseItem.Height = 1.0F;
/*  832: 836 */     Item.itemId = 63;
/*  833: 837 */     Item.X = 39;
/*  834: 838 */     Item.Y = 37;
/*  835: 839 */     Item.Rot = 0;
/*  836: 840 */     Item.Z = 1440;
/*  837: 841 */     Item.extraData.setExtraData("");
/*  838: 842 */     this.fuseObjects.add(Item);
/*  839:     */     
/*  840: 844 */     Item = new GamefuseObject();
/*  841: 845 */     Item.baseItem = BaseItem.snst_block1;
/*  842: 846 */     Item.baseItem.allowWalk = false;
/*  843: 847 */     Item.baseItem.Height = 1.0F;
/*  844: 848 */     Item.itemId = 64;
/*  845: 849 */     Item.X = 8;
/*  846: 850 */     Item.Y = 18;
/*  847: 851 */     Item.Rot = 0;
/*  848: 852 */     Item.Z = 1440;
/*  849: 853 */     Item.extraData.setExtraData("");
/*  850: 854 */     this.fuseObjects.add(Item);
/*  851:     */     
/*  852: 856 */     Item = new GamefuseObject();
/*  853: 857 */     Item.baseItem = BaseItem.snst_block1;
/*  854: 858 */     Item.baseItem.allowWalk = false;
/*  855: 859 */     Item.baseItem.Height = 1.0F;
/*  856: 860 */     Item.itemId = 65;
/*  857: 861 */     Item.X = 39;
/*  858: 862 */     Item.Y = 20;
/*  859: 863 */     Item.Rot = 0;
/*  860: 864 */     Item.Z = 0;
/*  861: 865 */     Item.extraData.setExtraData("");
/*  862: 866 */     this.fuseObjects.add(Item);
/*  863:     */     
/*  864: 868 */     Item = new GamefuseObject();
/*  865: 869 */     Item.baseItem = BaseItem.snst_block1;
/*  866: 870 */     Item.baseItem.allowWalk = false;
/*  867: 871 */     Item.baseItem.Height = 1.0F;
/*  868: 872 */     Item.itemId = 66;
/*  869: 873 */     Item.X = 38;
/*  870: 874 */     Item.Y = 37;
/*  871: 875 */     Item.Rot = 0;
/*  872: 876 */     Item.Z = 0;
/*  873: 877 */     Item.extraData.setExtraData("");
/*  874: 878 */     this.fuseObjects.add(Item);
/*  875:     */     
/*  876: 880 */     Item = new GamefuseObject();
/*  877: 881 */     Item.baseItem = BaseItem.snst_block1;
/*  878: 882 */     Item.baseItem.allowWalk = false;
/*  879: 883 */     Item.baseItem.Height = 1.0F;
/*  880: 884 */     Item.itemId = 67;
/*  881: 885 */     Item.X = 40;
/*  882: 886 */     Item.Y = 37;
/*  883: 887 */     Item.Rot = 0;
/*  884: 888 */     Item.Z = 1440;
/*  885: 889 */     Item.extraData.setExtraData("");
/*  886: 890 */     this.fuseObjects.add(Item);
/*  887:     */     
/*  888: 892 */     Item = new GamefuseObject();
/*  889: 893 */     Item.baseItem = BaseItem.snst_block1;
/*  890: 894 */     Item.baseItem.allowWalk = false;
/*  891: 895 */     Item.baseItem.Height = 1.0F;
/*  892: 896 */     Item.itemId = 68;
/*  893: 897 */     Item.X = 2;
/*  894: 898 */     Item.Y = 21;
/*  895: 899 */     Item.Rot = 0;
/*  896: 900 */     Item.Z = 0;
/*  897: 901 */     Item.extraData.setExtraData("");
/*  898: 902 */     this.fuseObjects.add(Item);
/*  899:     */     
/*  900: 904 */     Item = new GamefuseObject();
/*  901: 905 */     Item.baseItem = BaseItem.snst_fence;
/*  902: 906 */     Item.baseItem.allowWalk = false;
/*  903: 907 */     Item.baseItem.Height = 0.0F;
/*  904: 908 */     Item.itemId = 69;
/*  905: 909 */     Item.X = 24;
/*  906: 910 */     Item.Y = 42;
/*  907: 911 */     Item.Rot = 0;
/*  908: 912 */     Item.Z = 0;
/*  909: 913 */     Item.extraData.setExtraData("2");
/*  910: 914 */     this.fuseObjects.add(Item);
/*  911:     */     
/*  912: 916 */     Item = new GamefuseObject();
/*  913: 917 */     Item.baseItem = BaseItem.snst_block1;
/*  914: 918 */     Item.baseItem.allowWalk = false;
/*  915: 919 */     Item.baseItem.Height = 1.0F;
/*  916: 920 */     Item.itemId = 70;
/*  917: 921 */     Item.X = 39;
/*  918: 922 */     Item.Y = 22;
/*  919: 923 */     Item.Rot = 0;
/*  920: 924 */     Item.Z = 0;
/*  921: 925 */     Item.extraData.setExtraData("");
/*  922: 926 */     this.fuseObjects.add(Item);
/*  923:     */     
/*  924: 928 */     Item = new GamefuseObject();
/*  925: 929 */     Item.baseItem = BaseItem.snst_block1;
/*  926: 930 */     Item.baseItem.allowWalk = false;
/*  927: 931 */     Item.baseItem.Height = 1.0F;
/*  928: 932 */     Item.itemId = 71;
/*  929: 933 */     Item.X = 38;
/*  930: 934 */     Item.Y = 37;
/*  931: 935 */     Item.Rot = 0;
/*  932: 936 */     Item.Z = 1440;
/*  933: 937 */     Item.extraData.setExtraData("");
/*  934: 938 */     this.fuseObjects.add(Item);
/*  935:     */     
/*  936: 940 */     Item = new GamefuseObject();
/*  937: 941 */     Item.baseItem = BaseItem.snst_block1;
/*  938: 942 */     Item.baseItem.allowWalk = false;
/*  939: 943 */     Item.baseItem.Height = 1.0F;
/*  940: 944 */     Item.itemId = 72;
/*  941: 945 */     Item.X = 40;
/*  942: 946 */     Item.Y = 20;
/*  943: 947 */     Item.Rot = 0;
/*  944: 948 */     Item.Z = 1440;
/*  945: 949 */     Item.extraData.setExtraData("");
/*  946: 950 */     this.fuseObjects.add(Item);
/*  947:     */     
/*  948: 952 */     Item = new GamefuseObject();
/*  949: 953 */     Item.baseItem = BaseItem.snst_tree1;
/*  950: 954 */     Item.baseItem.allowWalk = false;
/*  951: 955 */     Item.baseItem.Height = 1.0F;
/*  952: 956 */     Item.itemId = 73;
/*  953: 957 */     Item.X = 28;
/*  954: 958 */     Item.Y = 47;
/*  955: 959 */     Item.Rot = 0;
/*  956: 960 */     Item.Z = 0;
/*  957: 961 */     Item.extraData.setExtraData("0");
/*  958: 962 */     this.fuseObjects.add(Item);
/*  959:     */     
/*  960: 964 */     Item = new GamefuseObject();
/*  961: 965 */     Item.baseItem = BaseItem.snst_block1;
/*  962: 966 */     Item.baseItem.allowWalk = false;
/*  963: 967 */     Item.baseItem.Height = 1.0F;
/*  964: 968 */     Item.itemId = 74;
/*  965: 969 */     Item.X = 6;
/*  966: 970 */     Item.Y = 18;
/*  967: 971 */     Item.Rot = 0;
/*  968: 972 */     Item.Z = 0;
/*  969: 973 */     Item.extraData.setExtraData("");
/*  970: 974 */     this.fuseObjects.add(Item);
/*  971:     */     
/*  972: 976 */     Item = new GamefuseObject();
/*  973: 977 */     Item.baseItem = BaseItem.s_snowball_machine;
/*  974: 978 */     Item.baseItem.allowWalk = false;
/*  975: 979 */     Item.baseItem.Height = 0.0F;
/*  976: 980 */     Item.itemId = 75;
/*  977: 981 */     Item.X = 26;
/*  978: 982 */     Item.Y = 24;
/*  979: 983 */     Item.Rot = 0;
/*  980: 984 */     Item.Z = 0;
/*  981: 985 */     Item.extraData.setExtraData("");
/*  982: 986 */     this.fuseObjects.add(Item);
/*  983:     */     
/*  984: 988 */     Item = new GamefuseObject();
/*  985: 989 */     Item.baseItem = BaseItem.snst_tree1;
/*  986: 990 */     Item.baseItem.allowWalk = false;
/*  987: 991 */     Item.baseItem.Height = 1.0F;
/*  988: 992 */     Item.itemId = 76;
/*  989: 993 */     Item.X = 5;
/*  990: 994 */     Item.Y = 24;
/*  991: 995 */     Item.Rot = 0;
/*  992: 996 */     Item.Z = 0;
/*  993: 997 */     Item.extraData.setExtraData("0");
/*  994: 998 */     this.fuseObjects.add(Item);
/*  995:     */     
/*  996:1000 */     Item = new GamefuseObject();
/*  997:1001 */     Item.baseItem = BaseItem.snst_block1;
/*  998:1002 */     Item.baseItem.allowWalk = false;
/*  999:1003 */     Item.baseItem.Height = 1.0F;
/* 1000:1004 */     Item.itemId = 77;
/* 1001:1005 */     Item.X = 41;
/* 1002:1006 */     Item.Y = 20;
/* 1003:1007 */     Item.Rot = 4;
/* 1004:1008 */     Item.Z = 1440;
/* 1005:1009 */     Item.extraData.setExtraData("");
/* 1006:1010 */     this.fuseObjects.add(Item);
/* 1007:     */     
/* 1008:1012 */     Item = new GamefuseObject();
/* 1009:1013 */     Item.baseItem = BaseItem.snst_fence;
/* 1010:1014 */     Item.baseItem.allowWalk = false;
/* 1011:1015 */     Item.baseItem.Height = 0.0F;
/* 1012:1016 */     Item.itemId = 78;
/* 1013:1017 */     Item.X = 19;
/* 1014:1018 */     Item.Y = 14;
/* 1015:1019 */     Item.Rot = 2;
/* 1016:1020 */     Item.Z = 0;
/* 1017:1021 */     Item.extraData.setExtraData("0");
/* 1018:1022 */     this.fuseObjects.add(Item);
/* 1019:     */     
/* 1020:1024 */     Item = new GamefuseObject();
/* 1021:1025 */     Item.baseItem = BaseItem.ads_background;
/* 1022:1026 */     Item.baseItem.allowWalk = true;
/* 1023:1027 */     Item.baseItem.Height = 0.0F;
/* 1024:1028 */     Item.baseItem.itemExtraType = 1;
/* 1025:1029 */     Item.itemId = 79;
/* 1026:1030 */     Item.X = 0;
/* 1027:1031 */     Item.Y = 19;
/* 1028:1032 */     Item.Rot = 1;
/* 1029:1033 */     Item.Z = 0;
/* 1030:1034 */     Item.extraData = new MapStuffData("state=0\toffsetX=-1166\toffsetZ=10000\toffsetY=1542\timageUrl=http://dcr.lavvos.pl/lavvos/c_images/DEV_tests/snst_bg_1_a_big.png");
/* 1031:1035 */     this.fuseObjects.add(Item);
/* 1032:     */     
/* 1033:1037 */     Item = new GamefuseObject();
/* 1034:1038 */     Item.baseItem = BaseItem.snst_tree1;
/* 1035:1039 */     Item.baseItem.allowWalk = false;
/* 1036:1040 */     Item.baseItem.Height = 1.0F;
/* 1037:1041 */     Item.itemId = 80;
/* 1038:1042 */     Item.X = 20;
/* 1039:1043 */     Item.Y = 8;
/* 1040:1044 */     Item.Rot = 0;
/* 1041:1045 */     Item.Z = 0;
/* 1042:1046 */     Item.extraData.setExtraData("0");
/* 1043:1047 */     this.fuseObjects.add(Item);
/* 1044:     */     
/* 1045:1049 */     Item = new GamefuseObject();
/* 1046:1050 */     Item.baseItem = BaseItem.snst_block1;
/* 1047:1051 */     Item.baseItem.allowWalk = false;
/* 1048:1052 */     Item.baseItem.Height = 1.0F;
/* 1049:1053 */     Item.itemId = 81;
/* 1050:1054 */     Item.X = 2;
/* 1051:1055 */     Item.Y = 21;
/* 1052:1056 */     Item.Rot = 0;
/* 1053:1057 */     Item.Z = 1440;
/* 1054:1058 */     Item.extraData.setExtraData("");
/* 1055:1059 */     this.fuseObjects.add(Item);
/* 1056:     */     
/* 1057:1061 */     Item = new GamefuseObject();
/* 1058:1062 */     Item.baseItem = BaseItem.snst_block1;
/* 1059:1063 */     Item.baseItem.allowWalk = false;
/* 1060:1064 */     Item.baseItem.Height = 1.0F;
/* 1061:1065 */     Item.itemId = 82;
/* 1062:1066 */     Item.X = 23;
/* 1063:1067 */     Item.Y = 38;
/* 1064:1068 */     Item.Rot = 0;
/* 1065:1069 */     Item.Z = 0;
/* 1066:1070 */     Item.extraData.setExtraData("");
/* 1067:1071 */     this.fuseObjects.add(Item);
/* 1068:     */     
/* 1069:1073 */     Item = new GamefuseObject();
/* 1070:1074 */     Item.baseItem = BaseItem.snst_block1;
/* 1071:1075 */     Item.baseItem.allowWalk = false;
/* 1072:1076 */     Item.baseItem.Height = 1.0F;
/* 1073:1077 */     Item.itemId = 83;
/* 1074:1078 */     Item.X = 23;
/* 1075:1079 */     Item.Y = 42;
/* 1076:1080 */     Item.Rot = 0;
/* 1077:1081 */     Item.Z = 1440;
/* 1078:1082 */     Item.extraData.setExtraData("");
/* 1079:1083 */     this.fuseObjects.add(Item);
/* 1080:     */     
/* 1081:1085 */     Item = new GamefuseObject();
/* 1082:1086 */     Item.baseItem = BaseItem.snst_block1;
/* 1083:1087 */     Item.baseItem.allowWalk = false;
/* 1084:1088 */     Item.baseItem.Height = 1.0F;
/* 1085:1089 */     Item.itemId = 84;
/* 1086:1090 */     Item.X = 42;
/* 1087:1091 */     Item.Y = 20;
/* 1088:1092 */     Item.Rot = 0;
/* 1089:1093 */     Item.Z = 1440;
/* 1090:1094 */     Item.extraData.setExtraData("");
/* 1091:1095 */     this.fuseObjects.add(Item);
/* 1092:     */     
/* 1093:1097 */     Item = new GamefuseObject();
/* 1094:1098 */     Item.baseItem = BaseItem.snst_block1;
/* 1095:1099 */     Item.baseItem.allowWalk = false;
/* 1096:1100 */     Item.baseItem.Height = 1.0F;
/* 1097:1101 */     Item.itemId = 85;
/* 1098:1102 */     Item.X = 39;
/* 1099:1103 */     Item.Y = 21;
/* 1100:1104 */     Item.Rot = 0;
/* 1101:1105 */     Item.Z = 0;
/* 1102:1106 */     Item.extraData.setExtraData("");
/* 1103:1107 */     this.fuseObjects.add(Item);
/* 1104:     */     
/* 1105:1109 */     Item = new GamefuseObject();
/* 1106:1110 */     Item.baseItem = BaseItem.snst_block1;
/* 1107:1111 */     Item.baseItem.allowWalk = false;
/* 1108:1112 */     Item.baseItem.Height = 1.0F;
/* 1109:1113 */     Item.itemId = 86;
/* 1110:1114 */     Item.X = 12;
/* 1111:1115 */     Item.Y = 18;
/* 1112:1116 */     Item.Rot = 4;
/* 1113:1117 */     Item.Z = 0;
/* 1114:1118 */     Item.extraData.setExtraData("");
/* 1115:1119 */     this.fuseObjects.add(Item);
/* 1116:     */     
/* 1117:1121 */     Item = new GamefuseObject();
/* 1118:1122 */     Item.baseItem = BaseItem.snst_block1;
/* 1119:1123 */     Item.baseItem.allowWalk = false;
/* 1120:1124 */     Item.baseItem.Height = 1.0F;
/* 1121:1125 */     Item.itemId = 87;
/* 1122:1126 */     Item.X = 11;
/* 1123:1127 */     Item.Y = 18;
/* 1124:1128 */     Item.Rot = 0;
/* 1125:1129 */     Item.Z = 1440;
/* 1126:1130 */     Item.extraData.setExtraData("");
/* 1127:1131 */     this.fuseObjects.add(Item);
/* 1128:     */     
/* 1129:1133 */     Item = new GamefuseObject();
/* 1130:1134 */     Item.baseItem = BaseItem.snst_block1;
/* 1131:1135 */     Item.baseItem.allowWalk = false;
/* 1132:1136 */     Item.baseItem.Height = 1.0F;
/* 1133:1137 */     Item.itemId = 88;
/* 1134:1138 */     Item.X = 40;
/* 1135:1139 */     Item.Y = 37;
/* 1136:1140 */     Item.Rot = 0;
/* 1137:1141 */     Item.Z = 0;
/* 1138:1142 */     Item.extraData.setExtraData("");
/* 1139:1143 */     this.fuseObjects.add(Item);
/* 1140:     */     
/* 1141:1145 */     Item = new GamefuseObject();
/* 1142:1146 */     Item.baseItem = BaseItem.snst_block1;
/* 1143:1147 */     Item.baseItem.allowWalk = false;
/* 1144:1148 */     Item.baseItem.Height = 1.0F;
/* 1145:1149 */     Item.itemId = 89;
/* 1146:1150 */     Item.X = 23;
/* 1147:1151 */     Item.Y = 41;
/* 1148:1152 */     Item.Rot = 0;
/* 1149:1153 */     Item.Z = 0;
/* 1150:1154 */     Item.extraData.setExtraData("");
/* 1151:1155 */     this.fuseObjects.add(Item);
/* 1152:     */     
/* 1153:1157 */     Item = new GamefuseObject();
/* 1154:1158 */     Item.baseItem = BaseItem.snst_block1;
/* 1155:1159 */     Item.baseItem.allowWalk = false;
/* 1156:1160 */     Item.baseItem.Height = 1.0F;
/* 1157:1161 */     Item.itemId = 90;
/* 1158:1162 */     Item.X = 43;
/* 1159:1163 */     Item.Y = 20;
/* 1160:1164 */     Item.Rot = 2;
/* 1161:1165 */     Item.Z = 0;
/* 1162:1166 */     Item.extraData.setExtraData("");
/* 1163:1167 */     this.fuseObjects.add(Item);
/* 1164:     */     
/* 1165:1169 */     Item = new GamefuseObject();
/* 1166:1170 */     Item.baseItem = BaseItem.snst_block1;
/* 1167:1171 */     Item.baseItem.allowWalk = false;
/* 1168:1172 */     Item.baseItem.Height = 1.0F;
/* 1169:1173 */     Item.itemId = 91;
/* 1170:1174 */     Item.X = 39;
/* 1171:1175 */     Item.Y = 20;
/* 1172:1176 */     Item.Rot = 0;
/* 1173:1177 */     Item.Z = 1440;
/* 1174:1178 */     Item.extraData.setExtraData("");
/* 1175:1179 */     this.fuseObjects.add(Item);
/* 1176:     */     
/* 1177:1181 */     Item = new GamefuseObject();
/* 1178:1182 */     Item.baseItem = BaseItem.snst_tree1;
/* 1179:1183 */     Item.baseItem.allowWalk = false;
/* 1180:1184 */     Item.baseItem.Height = 1.0F;
/* 1181:1185 */     Item.itemId = 92;
/* 1182:1186 */     Item.X = 15;
/* 1183:1187 */     Item.Y = 34;
/* 1184:1188 */     Item.Rot = 0;
/* 1185:1189 */     Item.Z = 0;
/* 1186:1190 */     Item.extraData.setExtraData("0");
/* 1187:1191 */     this.fuseObjects.add(Item);
/* 1188:     */     
/* 1189:1193 */     Item = new GamefuseObject();
/* 1190:1194 */     Item.baseItem = BaseItem.snst_block1;
/* 1191:1195 */     Item.baseItem.allowWalk = false;
/* 1192:1196 */     Item.baseItem.Height = 1.0F;
/* 1193:1197 */     Item.itemId = 93;
/* 1194:1198 */     Item.X = 6;
/* 1195:1199 */     Item.Y = 18;
/* 1196:1200 */     Item.Rot = 0;
/* 1197:1201 */     Item.Z = 1440;
/* 1198:1202 */     Item.extraData.setExtraData("");
/* 1199:1203 */     this.fuseObjects.add(Item);
/* 1200:     */     
/* 1201:1205 */     Item = new GamefuseObject();
/* 1202:1206 */     Item.baseItem = BaseItem.snst_block1;
/* 1203:1207 */     Item.baseItem.allowWalk = false;
/* 1204:1208 */     Item.baseItem.Height = 1.0F;
/* 1205:1209 */     Item.itemId = 94;
/* 1206:1210 */     Item.X = 7;
/* 1207:1211 */     Item.Y = 18;
/* 1208:1212 */     Item.Rot = 0;
/* 1209:1213 */     Item.Z = 1440;
/* 1210:1214 */     Item.extraData.setExtraData("");
/* 1211:1215 */     this.fuseObjects.add(Item);
/* 1212:     */     
/* 1213:1217 */     Item = new GamefuseObject();
/* 1214:1218 */     Item.baseItem = BaseItem.snst_fence;
/* 1215:1219 */     Item.baseItem.allowWalk = false;
/* 1216:1220 */     Item.baseItem.Height = 0.0F;
/* 1217:1221 */     Item.itemId = 95;
/* 1218:1222 */     Item.X = 29;
/* 1219:1223 */     Item.Y = 9;
/* 1220:1224 */     Item.Rot = 0;
/* 1221:1225 */     Item.Z = 0;
/* 1222:1226 */     Item.extraData.setExtraData("2");
/* 1223:1227 */     this.fuseObjects.add(Item);
/* 1224:     */     
/* 1225:1229 */     Item = new GamefuseObject();
/* 1226:1230 */     Item.baseItem = BaseItem.snst_block1;
/* 1227:1231 */     Item.baseItem.allowWalk = false;
/* 1228:1232 */     Item.baseItem.Height = 1.0F;
/* 1229:1233 */     Item.itemId = 96;
/* 1230:1234 */     Item.X = 39;
/* 1231:1235 */     Item.Y = 24;
/* 1232:1236 */     Item.Rot = 2;
/* 1233:1237 */     Item.Z = 0;
/* 1234:1238 */     Item.extraData.setExtraData("");
/* 1235:1239 */     this.fuseObjects.add(Item);
/* 1236:     */     
/* 1237:1241 */     Item = new GamefuseObject();
/* 1238:1242 */     Item.baseItem = BaseItem.snst_block1;
/* 1239:1243 */     Item.baseItem.allowWalk = false;
/* 1240:1244 */     Item.baseItem.Height = 1.0F;
/* 1241:1245 */     Item.itemId = 97;
/* 1242:1246 */     Item.X = 23;
/* 1243:1247 */     Item.Y = 43;
/* 1244:1248 */     Item.Rot = 0;
/* 1245:1249 */     Item.Z = 0;
/* 1246:1250 */     Item.extraData.setExtraData("");
/* 1247:1251 */     this.fuseObjects.add(Item);
/* 1248:     */     
/* 1249:     */ 
/* 1250:1254 */     Item = new GamefuseObject();
/* 1251:1255 */     Item.baseItem = BaseItem.ads_igorraygun;
/* 1252:1256 */     Item.baseItem.allowWalk = false;
/* 1253:1257 */     Item.baseItem.Height = 0.1F;
/* 1254:1258 */     Item.itemId = 98;
/* 1255:1259 */     Item.X = 28;
/* 1256:1260 */     Item.Y = 12;
/* 1257:1261 */     Item.Rot = 4;
/* 1258:1262 */     Item.Z = 0;
/* 1259:1263 */     Item.extraData.setExtraData("");
/* 1260:1264 */     this.fuseObjects.add(Item);
/* 1261:     */     
/* 1262:1266 */     Item = new GamefuseObject();
/* 1263:1267 */     Item.baseItem = BaseItem.ads_igorraygun;
/* 1264:1268 */     Item.baseItem.allowWalk = false;
/* 1265:1269 */     Item.baseItem.Height = 0.1F;
/* 1266:1270 */     Item.itemId = 99;
/* 1267:1271 */     Item.X = 41;
/* 1268:1272 */     Item.Y = 33;
/* 1269:1273 */     Item.Rot = 6;
/* 1270:1274 */     Item.Z = 0;
/* 1271:1275 */     Item.extraData.setExtraData("");
/* 1272:1276 */     this.fuseObjects.add(Item);
/* 1273:     */     
/* 1274:1278 */     Item = new GamefuseObject();
/* 1275:1279 */     Item.baseItem = BaseItem.ads_igorraygun;
/* 1276:1280 */     Item.baseItem.allowWalk = false;
/* 1277:1281 */     Item.baseItem.Height = 0.1F;
/* 1278:1282 */     Item.itemId = 100;
/* 1279:1283 */     Item.X = 31;
/* 1280:1284 */     Item.Y = 41;
/* 1281:1285 */     Item.Rot = 0;
/* 1282:1286 */     Item.Z = 0;
/* 1283:1287 */     Item.extraData.setExtraData("");
/* 1284:1288 */     this.fuseObjects.add(Item);
/* 1285:     */     
/* 1286:1290 */     Item = new GamefuseObject();
/* 1287:1291 */     Item.baseItem = BaseItem.ads_igorraygun;
/* 1288:1292 */     Item.baseItem.allowWalk = false;
/* 1289:1293 */     Item.baseItem.Height = 0.1F;
/* 1290:1294 */     Item.itemId = 101;
/* 1291:1295 */     Item.X = 17;
/* 1292:1296 */     Item.Y = 37;
/* 1293:1297 */     Item.Rot = 2;
/* 1294:1298 */     Item.Z = 0;
/* 1295:1299 */     Item.extraData.setExtraData("");
/* 1296:1300 */     this.fuseObjects.add(Item);
/* 1297:     */     
/* 1298:1302 */     Item = new GamefuseObject();
/* 1299:1303 */     Item.baseItem = BaseItem.ads_igorraygun;
/* 1300:1304 */     Item.baseItem.allowWalk = false;
/* 1301:1305 */     Item.baseItem.Height = 0.1F;
/* 1302:1306 */     Item.itemId = 102;
/* 1303:1307 */     Item.X = 11;
/* 1304:1308 */     Item.Y = 21;
/* 1305:1309 */     Item.Rot = 2;
/* 1306:1310 */     Item.Z = 0;
/* 1307:1311 */     Item.extraData.setExtraData("");
/* 1308:1312 */     this.fuseObjects.add(Item);
/* 1309:     */     
/* 1310:     */ 
/* 1311:1315 */     this.spawnsBLUE.add(new SpawnPoint(22, 9));
/* 1312:1316 */     this.spawnsBLUE.add(new SpawnPoint(25, 12));
/* 1313:1317 */     this.spawnsBLUE.add(new SpawnPoint(26, 8));
/* 1314:1318 */     this.spawnsBLUE.add(new SpawnPoint(31, 14));
/* 1315:1319 */     this.spawnsBLUE.add(new SpawnPoint(23, 13));
/* 1316:     */     
/* 1317:1321 */     this.spawnsRED.add(new SpawnPoint(30, 43));
/* 1318:1322 */     this.spawnsRED.add(new SpawnPoint(33, 42));
/* 1319:1323 */     this.spawnsRED.add(new SpawnPoint(38, 41));
/* 1320:1324 */     this.spawnsRED.add(new SpawnPoint(26, 42));
/* 1321:1325 */     this.spawnsRED.add(new SpawnPoint(33, 46));
/* 1322:     */   }
/* 1323:     */   
/* 1324:     */   public void gameObjects(Map<Integer, GameItemObject> gameObjects, SnowWarRoom room)
/* 1325:     */   {
/* 1326:1330 */     gameObjects.put(Integer.valueOf(0), new TreeGameObject(19, 41, 0, 1, 3, 3, 0, room.map, room));
/* 1327:1331 */     gameObjects.put(Integer.valueOf(1), new TreeGameObject(9, 31, 0, 1, 5, 3, 0, room.map, room));
/* 1328:1332 */     gameObjects.put(Integer.valueOf(2), new TreeGameObject(25, 38, 0, 1, 9, 3, 0, room.map, room));
/* 1329:1333 */     gameObjects.put(Integer.valueOf(3), new TreeGameObject(49, 28, 0, 1, 15, 3, 0, room.map, room));
/* 1330:1334 */     gameObjects.put(Integer.valueOf(4), new TreeGameObject(36, 15, 0, 1, 22, 3, 0, room.map, room));
/* 1331:1335 */     gameObjects.put(Integer.valueOf(5), new TreeGameObject(47, 32, 0, 1, 24, 3, 0, room.map, room));
/* 1332:1336 */     gameObjects.put(Integer.valueOf(6), new TreeGameObject(6, 20, 0, 1, 28, 3, 0, room.map, room));
/* 1333:1337 */     gameObjects.put(Integer.valueOf(7), new TreeGameObject(26, 6, 0, 1, 31, 3, 0, room.map, room));
/* 1334:1338 */     gameObjects.put(Integer.valueOf(8), new TreeGameObject(10, 26, 0, 1, 34, 3, 0, room.map, room));
/* 1335:1339 */     gameObjects.put(Integer.valueOf(9), new TreeGameObject(13, 15, 0, 1, 39, 3, 0, room.map, room));
/* 1336:1340 */     gameObjects.put(Integer.valueOf(10), new TreeGameObject(30, 7, 0, 1, 42, 3, 0, room.map, room));
/* 1337:1341 */     gameObjects.put(Integer.valueOf(11), new TreeGameObject(15, 10, 0, 1, 48, 3, 0, room.map, room));
/* 1338:1342 */     gameObjects.put(Integer.valueOf(12), new TreeGameObject(20, 4, 0, 1, 50, 3, 0, room.map, room));
/* 1339:1343 */     gameObjects.put(Integer.valueOf(13), new TreeGameObject(45, 25, 0, 1, 56, 3, 0, room.map, room));
/* 1340:1344 */     gameObjects.put(Integer.valueOf(14), new TreeGameObject(28, 47, 0, 1, 73, 3, 0, room.map, room));
/* 1341:1345 */     gameObjects.put(Integer.valueOf(15), new MachineGameObject(26, 24, 0, 5, 0, 75, room.map, room));
/* 1342:1346 */     gameObjects.put(Integer.valueOf(16), new TreeGameObject(5, 24, 0, 1, 76, 3, 0, room.map, room));
/* 1343:1347 */     gameObjects.put(Integer.valueOf(17), new TreeGameObject(20, 8, 0, 1, 80, 3, 0, room.map, room));
/* 1344:1348 */     gameObjects.put(Integer.valueOf(18), new TreeGameObject(15, 34, 0, 1, 92, 3, 0, room.map, room));
/* 1345:     */   }
/* 1346:     */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.games.snowwar.SnowWarArena8
 * JD-Core Version:    0.7.0.1
 */