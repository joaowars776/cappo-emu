/*   1:    */ package cappo.game.collections;
/*   2:    */ 
/*   3:    */ import cappo.engine.database.DBResult;
/*   4:    */ import cappo.engine.database.Database;
/*   5:    */ import cappo.engine.logging.Log;
/*   6:    */ import java.sql.ResultSet;
/*   7:    */ import java.util.ArrayList;
/*   8:    */ import java.util.List;
/*   9:    */ 
/*  10:    */ public class MoodlightData
/*  11:    */ {
/*  12:    */   public int CurrentPreset;
/*  13:    */   public boolean Enabled;
/*  14:    */   public int ItemId;
/*  15:    */   public List<MoodlightPreset> Presets;
/*  16:    */   
/*  17:    */   public class MoodlightPreset
/*  18:    */   {
/*  19:    */     public boolean BackgroundOnly;
/*  20:    */     public String ColorCode;
/*  21:    */     public int ColorIntensity;
/*  22:    */     
/*  23:    */     public MoodlightPreset(String ColorCode, int ColorIntensity, boolean BackgroundOnly)
/*  24:    */     {
/*  25: 23 */       this.ColorCode = ColorCode;
/*  26: 24 */       this.ColorIntensity = ColorIntensity;
/*  27: 25 */       this.BackgroundOnly = BackgroundOnly;
/*  28:    */     }
/*  29:    */   }
/*  30:    */   
/*  31:    */   public MoodlightData()
/*  32:    */   {
/*  33: 36 */     this.Presets = new ArrayList();
/*  34:    */   }
/*  35:    */   
/*  36:    */   public MoodlightData(int id)
/*  37:    */   {
/*  38: 40 */     this();
/*  39:    */     
/*  40: 42 */     this.ItemId = id;
/*  41:    */     
/*  42:    */ 
/*  43: 45 */     DBResult result = new DBResult();
/*  44:    */     try
/*  45:    */     {
/*  46: 47 */       Database.query(result, "SELECT * FROM items_moodlight WHERE item_id = " + id + " LIMIT 1;", new Object[0]);
/*  47: 48 */       if (result.data.next())
/*  48:    */       {
/*  49: 49 */         this.Enabled = (result.data.getInt("enabled") == 1);
/*  50: 50 */         this.CurrentPreset = result.data.getInt("current_preset");
/*  51: 51 */         AddPresent(result.data.getString("preset_one"));
/*  52: 52 */         AddPresent(result.data.getString("preset_two"));
/*  53: 53 */         AddPresent(result.data.getString("preset_three"));
/*  54:    */       }
/*  55:    */       else
/*  56:    */       {
/*  57: 55 */         this.Enabled = false;
/*  58: 56 */         this.CurrentPreset = 1;
/*  59: 57 */         AddPresent("#000000,255,0");
/*  60: 58 */         AddPresent("#000000,255,0");
/*  61: 59 */         AddPresent("#000000,255,0");
/*  62:    */       }
/*  63:    */     }
/*  64:    */     catch (Exception ex)
/*  65:    */     {
/*  66: 63 */       Log.printException("MoodlightData", ex);
/*  67:    */     }
/*  68: 65 */     result.close();
/*  69:    */   }
/*  70:    */   
/*  71:    */   public void mysqlSave()
/*  72:    */     throws Exception
/*  73:    */   {
/*  74: 69 */     MoodlightPreset present = GetPreset(1);
/*  75: 70 */     String one = present.ColorCode + "," + present.ColorIntensity + (present.BackgroundOnly ? "1" : "0");
/*  76: 71 */     present = GetPreset(2);
/*  77: 72 */     String two = present.ColorCode + "," + present.ColorIntensity + (present.BackgroundOnly ? "1" : "0");
/*  78: 73 */     present = GetPreset(3);
/*  79: 74 */     String three = present.ColorCode + "," + present.ColorIntensity + (present.BackgroundOnly ? "1" : "0");
/*  80:    */     
/*  81: 76 */     Database.exec("INSERT INTO items_moodlight (item_id,enabled,current_preset,preset_one,preset_two,preset_three)VALUES(" + this.ItemId + "," + (this.Enabled ? 1 : 0) + "," + this.CurrentPreset + ",?,?,?) on DUPLICATE KEY UPDATE `preset_one`=?,`preset_two`=?,`preset_three`=?;", new Object[] { one, two, three, one, two, three });
/*  82:    */   }
/*  83:    */   
/*  84:    */   public void AddPresent(String Present)
/*  85:    */   {
/*  86: 80 */     this.Presets.add(GeneratePreset(Present));
/*  87:    */   }
/*  88:    */   
/*  89:    */   public String GenerateExtraData()
/*  90:    */   {
/*  91: 84 */     MoodlightPreset Preset = GetPreset(this.CurrentPreset);
/*  92: 85 */     return (this.Enabled ? "2" : "1") + "," + this.CurrentPreset + "," + (Preset.BackgroundOnly ? "2" : "1") + "," + Preset.ColorCode + "," + Preset.ColorIntensity;
/*  93:    */   }
/*  94:    */   
/*  95:    */   private MoodlightPreset GeneratePreset(String Data)
/*  96:    */   {
/*  97: 89 */     String[] Bits = Data.split(",");
/*  98: 90 */     if (!IsValidColor(Bits[0])) {
/*  99: 91 */       Bits[0] = "#000000";
/* 100:    */     }
/* 101: 93 */     return new MoodlightPreset(Bits[0], Integer.parseInt(Bits[1]), Bits[2].equals("1"));
/* 102:    */   }
/* 103:    */   
/* 104:    */   private MoodlightPreset GetPreset(int i)
/* 105:    */   {
/* 106: 97 */     return (MoodlightPreset)this.Presets.get(--i);
/* 107:    */   }
/* 108:    */   
/* 109:    */   private boolean IsValidColor(String ColorCode)
/* 110:    */   {
/* 111:101 */     if (ColorCode.equals("#000000")) {
/* 112:102 */       return true;
/* 113:    */     }
/* 114:105 */     if (ColorCode.equals("#0053F7")) {
/* 115:106 */       return true;
/* 116:    */     }
/* 117:109 */     if (ColorCode.equals("#EA4532")) {
/* 118:110 */       return true;
/* 119:    */     }
/* 120:113 */     if (ColorCode.equals("#82F349")) {
/* 121:114 */       return true;
/* 122:    */     }
/* 123:117 */     if (ColorCode.equals("#74F5F5")) {
/* 124:118 */       return true;
/* 125:    */     }
/* 126:121 */     if (ColorCode.equals("#E759DE")) {
/* 127:122 */       return true;
/* 128:    */     }
/* 129:125 */     if (ColorCode.equals("#F2F851")) {
/* 130:126 */       return true;
/* 131:    */     }
/* 132:129 */     return false;
/* 133:    */   }
/* 134:    */   
/* 135:    */   private boolean IsValidIntensity(int Intensity)
/* 136:    */   {
/* 137:133 */     if ((Intensity < 0) || (Intensity > 255)) {
/* 138:134 */       return false;
/* 139:    */     }
/* 140:137 */     return true;
/* 141:    */   }
/* 142:    */   
/* 143:    */   public void UpdatePreset(String Color, int Intensity, boolean BgOnly)
/* 144:    */   {
/* 145:141 */     if ((!IsValidColor(Color)) || (!IsValidIntensity(Intensity))) {
/* 146:142 */       return;
/* 147:    */     }
/* 148:145 */     MoodlightPreset Preset = GetPreset(this.CurrentPreset);
/* 149:146 */     Preset.ColorCode = Color;
/* 150:147 */     Preset.ColorIntensity = Intensity;
/* 151:148 */     Preset.BackgroundOnly = BgOnly;
/* 152:    */   }
/* 153:    */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.collections.MoodlightData
 * JD-Core Version:    0.7.0.1
 */