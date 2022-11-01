/*   1:    */ package cappo.game.pets;
/*   2:    */ 
/*   3:    */ import cappo.game.collections.Utils;
/*   4:    */ import java.util.ArrayList;
/*   5:    */ import java.util.List;
/*   6:    */ import java.util.Map;
/*   7:    */ import java.util.concurrent.ConcurrentHashMap;
/*   8:    */ 
/*   9:    */ public class PetBase
/*  10:    */ {
/*  11:    */   public static final short SPEECH_NOT_FOUND = 0;
/*  12:    */   public static final short SPEECH_NO_ENERGY = 1;
/*  13:    */   public static final short SPEECH_REFUSE = 2;
/*  14:    */   public static final short SPEECH_RANDOM = 3;
/*  15:    */   public short raceId;
/*  16: 24 */   public Map<Short, List<String>> speeches = new ConcurrentHashMap();
/*  17: 25 */   public Map<Short, PetBase> races = new ConcurrentHashMap();
/*  18: 27 */   public static final int[] ExperienceLevels = { 100, 200, 300, 400, 600, 900, 1300, 1800, 2400, 3200, 4300, 5700, 7600, 10100, 13300, 17500, 23000, 31000, 39600, 51900, 51900 };
/*  19: 28 */   public static final int[] MaxEnergyLevels = { 120, 140, 160, 180, 200, 220, 240, 260, 280, 300, 320, 340, 360, 380, 400, 420, 440, 460, 480, 500, 500 };
/*  20: 29 */   public static final int[] MaxHappinessLevels = { 120, 140, 160, 180, 200, 220, 240, 260, 280, 300, 320, 340, 360, 380, 400, 420, 440, 460, 480, 500, 500 };
/*  21:    */   public static final int MaxLevel = 20;
/*  22:    */   public static final int MaxHappiness = 100;
/*  23:    */   
/*  24:    */   public PetBase() {}
/*  25:    */   
/*  26:    */   public PetBase(short race)
/*  27:    */   {
/*  28:130 */     this.raceId = race;
/*  29:    */     
/*  30:132 */     this.speeches.put(Short.valueOf((short)0), new ArrayList());
/*  31:133 */     this.speeches.put(Short.valueOf((short)1), new ArrayList());
/*  32:134 */     this.speeches.put(Short.valueOf((short)2), new ArrayList());
/*  33:135 */     this.speeches.put(Short.valueOf((short)3), new ArrayList());
/*  34:    */     
/*  35:137 */     List<String> notfound = (List)this.speeches.get(Short.valueOf((short)0));
/*  36:138 */     List<String> noenergy = (List)this.speeches.get(Short.valueOf((short)1));
/*  37:139 */     List<String> refuse = (List)this.speeches.get(Short.valueOf((short)2));
/*  38:    */     
/*  39:141 */     notfound.add("*Confundido*");
/*  40:142 */     notfound.add("¿Qué quieres?");
/*  41:143 */     notfound.add("No te entiendo");
/*  42:144 */     notfound.add("¿Qué es eso?");
/*  43:    */     
/*  44:146 */     noenergy.add("ZzZzzzzz");
/*  45:147 */     noenergy.add("*Estoy cansado*");
/*  46:148 */     noenergy.add("Cansado *Está cansado*");
/*  47:149 */     noenergy.add("ZzZzZZzzzZZz");
/*  48:150 */     noenergy.add("zzZzzZzzz");
/*  49:151 */     noenergy.add("... Con sueño ..");
/*  50:152 */     noenergy.add("ZzZzzZ");
/*  51:    */     
/*  52:154 */     refuse.add("*Me niego*");
/*  53:155 */     refuse.add(" ... ");
/*  54:156 */     refuse.add("¿Quién te crees que eres?");
/*  55:157 */     refuse.add("¿Qué haces?");
/*  56:158 */     refuse.add("Grrrrr");
/*  57:159 */     refuse.add("*Tengo ganas de jugar*");
/*  58:160 */     refuse.add("¿Por qué?");
/*  59:    */   }
/*  60:    */   
/*  61:    */   public boolean checkLevel(Pet pet)
/*  62:    */   {
/*  63:164 */     int newlevel = pet.level;
/*  64:166 */     while ((newlevel < 20) && 
/*  65:167 */       (pet.Experience > ExperienceLevels[newlevel])) {
/*  66:168 */       newlevel++;
/*  67:    */     }
/*  68:173 */     if (newlevel > pet.level)
/*  69:    */     {
/*  70:174 */       pet.level = newlevel;
/*  71:175 */       return true;
/*  72:    */     }
/*  73:178 */     return false;
/*  74:    */   }
/*  75:    */   
/*  76:    */   public void addRace(PetBase petBase)
/*  77:    */   {
/*  78:183 */     this.races.put(Short.valueOf(petBase.raceId), petBase);
/*  79:    */   }
/*  80:    */   
/*  81:    */   public void addSpeech(short key, String value)
/*  82:    */   {
/*  83:187 */     List<String> speech = (List)this.speeches.get(Short.valueOf(key));
/*  84:188 */     speech.add(value);
/*  85:    */   }
/*  86:    */   
/*  87:    */   public String getSpeech(short key)
/*  88:    */   {
/*  89:192 */     List<String> speech = (List)this.speeches.get(Short.valueOf(key));
/*  90:193 */     if (speech.isEmpty()) {
/*  91:194 */       return "";
/*  92:    */     }
/*  93:196 */     return (String)speech.get(Utils.GetRandomNumber(0, speech.size() - 1));
/*  94:    */   }
/*  95:    */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.pets.PetBase
 * JD-Core Version:    0.7.0.1
 */