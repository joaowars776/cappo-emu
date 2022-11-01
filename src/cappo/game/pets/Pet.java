/*   1:    */ package cappo.game.pets;
/*   2:    */ 
/*   3:    */ import cappo.engine.logging.Log;
/*   4:    */ import cappo.game.roomengine.entity.item.floor.FloorItem;
/*   5:    */ import cappo.game.roomengine.entity.live.PetEntity;
/*   6:    */ import java.util.Map;
/*   7:    */ 
/*   8:    */ public class Pet
/*   9:    */ {
/*  10:    */   public static final short PET_DOG = 0;
/*  11:    */   public static final short PET_CAT = 1;
/*  12:    */   public static final short PET_CROCO = 2;
/*  13:    */   public static final short PET_TERRIER = 3;
/*  14:    */   public static final short PET_BEAR = 4;
/*  15:    */   public static final short PET_PIG = 5;
/*  16:    */   public static final short PET_LION = 6;
/*  17:    */   public static final short PET_RHINO = 7;
/*  18:    */   public static final short PET_SPIDER = 8;
/*  19:    */   public static final short PET_TURTLE = 9;
/*  20:    */   public static final short PET_CHICKEN = 10;
/*  21:    */   public static final short PET_FROG = 11;
/*  22:    */   public static final short PET_DRAGON = 12;
/*  23:    */   public static final short PET_MONSTER = 13;
/*  24:    */   public static final short PET_MONKEY = 14;
/*  25:    */   public static final short PET_HORSE = 15;
/*  26:    */   public static final short PET_MONSTERPLANT = 16;
/*  27:    */   public static final short PET_BUNNYEASTER = 17;
/*  28:    */   public static final short PET_BUNNYEVIL = 18;
/*  29:    */   public static final short PET_BUNNYDEPRESSED = 19;
/*  30:    */   public static final short PET_BUNNYLOVE = 20;
/*  31:    */   public static final short PET_PIGEONGOOD = 21;
/*  32:    */   public static final short PET_PIGEONEVIL = 22;
/*  33:    */   public static final short PET_DEMONMONKEY = 23;
/*  34:    */   public static final short PET_BEARBABY = 24;
/*  35:    */   public static final short PET_TERRIERBABY = 25;
/*  36:    */   public static final short PET_GNOME = 26;
/*  37:    */   public static final short PET_LIMIT = 27;
/*  38: 45 */   public static final PetBase[] PETS = {
/*  39:    */   
/*  40: 47 */     new Dog(), 
/*  41:    */     
/*  42: 49 */     new Cat(), 
/*  43:    */     
/*  44: 51 */     new Croco(), 
/*  45:    */     
/*  46: 53 */     new Terrier(), 
/*  47:    */     
/*  48: 55 */     new Bear(), 
/*  49:    */     
/*  50: 57 */     new Pig(), 
/*  51:    */     
/*  52: 59 */     new Lion(), 
/*  53:    */     
/*  54: 61 */     new Rhino(), 
/*  55:    */     
/*  56: 63 */     new Spider(), 
/*  57:    */     
/*  58: 65 */     new Turtle(), 
/*  59:    */     
/*  60: 67 */     new Chicken(), 
/*  61:    */     
/*  62: 69 */     new Frog(), 
/*  63:    */     
/*  64: 71 */     new Dragon(), 
/*  65:    */     
/*  66: 73 */     new Monster(), 
/*  67:    */     
/*  68: 75 */     new Monkey(), 
/*  69:    */     
/*  70: 77 */     new Horse(), 
/*  71:    */     
/*  72: 79 */     new Monsterplant(), 
/*  73:    */     
/*  74: 81 */     new BunnyEaster(), 
/*  75:    */     
/*  76: 83 */     new BunnyEvil(), 
/*  77:    */     
/*  78: 85 */     new BunnyDepressed(), 
/*  79:    */     
/*  80: 87 */     new BunnyLove(), 
/*  81:    */     
/*  82: 89 */     new PigeOnGood(), 
/*  83:    */     
/*  84: 91 */     new PigeOnEvil(), 
/*  85:    */     
/*  86: 93 */     new PigeOnEvil(), 
/*  87:    */     
/*  88: 95 */     new PigeOnEvil(), 
/*  89:    */     
/*  90: 97 */     new PigeOnEvil(), 
/*  91:    */     
/*  92: 99 */     new PigeOnEvil() };
/*  93:    */   public String Color;
/*  94:    */   public int Energy;
/*  95:    */   public int happiness;
/*  96:    */   public int Experience;
/*  97:    */   public int id;
/*  98:    */   public int level;
/*  99:    */   public String name;
/* 100:    */   public int Nutrition;
/* 101:    */   public int ownerId;
/* 102:    */   public String ownerName;
/* 103:    */   public int Respects;
/* 104:    */   public long TimeCreated;
/* 105:    */   public PetBase base;
/* 106:    */   public short petType;
/* 107:    */   public boolean haveSaddle;
/* 108:    */   public FloorItem saddleFurni;
/* 109:    */   public boolean ridingAll;
/* 110:    */   public PetEntity petEntity;
/* 111:    */   public boolean needInsert;
/* 112:    */   
/* 113:    */   public Pet(int iId, String sName, short type, short race, String color)
/* 114:    */   {
/* 115:125 */     this.id = iId;
/* 116:126 */     this.name = sName;
/* 117:127 */     this.Color = color;
/* 118:128 */     this.petType = type;
/* 119:129 */     this.base = ((PetBase)PETS[type].races.get(Short.valueOf(race)));
/* 120:130 */     if (this.base == null) {
/* 121:131 */       Log.printLog("Unknown pet race, type=" + type + " race=" + race);
/* 122:    */     }
/* 123:    */   }
/* 124:    */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.pets.Pet
 * JD-Core Version:    0.7.0.1
 */