/*   1:    */ package cappo.game.player;
/*   2:    */ 
/*   3:    */ import cappo.game.collections.Utils;
/*   4:    */ 
/*   5:    */ public class AvatarLook
/*   6:    */ {
/*   7:    */   public static final int SETS_HD = 0;
/*   8:    */   public static final int SETS_HR = 1;
/*   9:    */   public static final int SETS_HA = 2;
/*  10:    */   public static final int SETS_HE = 3;
/*  11:    */   public static final int SETS_EA = 4;
/*  12:    */   public static final int SETS_FA = 5;
/*  13:    */   public static final int SETS_CC = 6;
/*  14:    */   public static final int SETS_CH = 7;
/*  15:    */   public static final int SETS_CA = 8;
/*  16:    */   public static final int SETS_CP = 9;
/*  17:    */   public static final int SETS_LG = 10;
/*  18:    */   public static final int SETS_SH = 11;
/*  19:    */   public static final int SETS_WA = 12;
/*  20:    */   public static final int SETS_PARTS = 13;
/*  21: 21 */   public static final String[] sets = new String[13];
/*  22: 22 */   public long[] parts = new long[13];
/*  23:    */   private static final int PACK_NUMBITS = 12;
/*  24:    */   private static final int PACK_BITS = 4095;
/*  25:    */   
/*  26:    */   static
/*  27:    */   {
/*  28: 30 */     sets[0] = "hd";
/*  29: 31 */     sets[1] = "hr";
/*  30: 32 */     sets[2] = "ha";
/*  31: 33 */     sets[3] = "he";
/*  32: 34 */     sets[4] = "ea";
/*  33: 35 */     sets[5] = "fa";
/*  34: 36 */     sets[6] = "cc";
/*  35: 37 */     sets[7] = "ch";
/*  36: 38 */     sets[8] = "ca";
/*  37: 39 */     sets[9] = "cp";
/*  38: 40 */     sets[10] = "lg";
/*  39: 41 */     sets[11] = "sh";
/*  40: 42 */     sets[12] = "wa";
/*  41:    */   }
/*  42:    */   
/*  43:    */   public String toString()
/*  44:    */   {
/*  45: 46 */     return filterLook(new int[] {
/*  46: 47 */       0, 
/*  47: 48 */       1, 
/*  48: 49 */       2, 
/*  49: 50 */       3, 
/*  50: 51 */       4, 
/*  51: 52 */       5, 
/*  52: 53 */       6, 
/*  53: 54 */       7, 
/*  54: 55 */       8, 
/*  55: 56 */       9, 
/*  56: 57 */       10, 
/*  57: 58 */       11, 
/*  58: 59 */       12 });
/*  59:    */   }
/*  60:    */   
/*  61:    */   public AvatarLook(String lookStr)
/*  62:    */   {
/*  63: 64 */     String[] tmp = lookStr.split("\\.");
/*  64: 65 */     for (String part : tmp)
/*  65:    */     {
/*  66: 66 */       String[] Set = part.split("-");
/*  67: 67 */       if ((Set.length >= 2) && (Set.length <= 5) && (!part.endsWith("-")))
/*  68:    */       {
/*  69: 71 */         int type = 0;
/*  70:    */         
/*  71: 73 */         int a = 0;
/*  72: 74 */         for (String s : sets)
/*  73:    */         {
/*  74: 75 */           if (s.equals(Set[0]))
/*  75:    */           {
/*  76: 76 */             type = a;
/*  77: 77 */             a = -1;
/*  78: 78 */             break;
/*  79:    */           }
/*  80: 80 */           a++;
/*  81:    */         }
/*  82: 83 */         if (a < 0)
/*  83:    */         {
/*  84: 88 */           int set = 0;
/*  85:    */           
/*  86: 90 */           a = 0;
/*  87:    */           do
/*  88:    */           {
/*  89:    */             try
/*  90:    */             {
/*  91: 95 */               int val = Integer.parseInt(Set[a]) & 0xFFF;
/*  92: 96 */               if (a == 1) {
/*  93: 98 */                 set |= val;
/*  94: 99 */               } else if (a == 2) {
/*  95:101 */                 set |= val << 12;
/*  96:102 */               } else if (a == 3) {
/*  97:104 */                 set |= val << 24;
/*  98:105 */               } else if (a == 4) {
/*  99:107 */                 set |= val << 36;
/* 100:    */               }
/* 101:    */             }
/* 102:    */             catch (NumberFormatException ex)
/* 103:    */             {
/* 104:111 */               set = 0;
/* 105:112 */               break;
/* 106:    */             }
/* 107: 91 */             a++;
/* 108: 91 */           } while (a < Set.length);
/* 109:116 */           if (set != 0) {
/* 110:122 */             this.parts[type] = set;
/* 111:    */           }
/* 112:    */         }
/* 113:    */       }
/* 114:    */     }
/* 115:    */   }
/* 116:    */   
/* 117:    */   public AvatarLook()
/* 118:    */   {
/* 119:134 */     this.parts[1] = 172147L;
/* 120:135 */     this.parts[0] = 4286L;
/* 121:136 */     this.parts[7] = 254167L;
/* 122:137 */     this.parts[10] = 373021L;
/* 123:138 */     this.parts[11] = 254242L;
/* 124:    */   }
/* 125:    */   
/* 126:    */   private int getIntBits(long num)
/* 127:    */   {
/* 128:142 */     return (int)(num & 0xFFF);
/* 129:    */   }
/* 130:    */   
/* 131:    */   private int getIntShiftBits(int type, int shift)
/* 132:    */   {
/* 133:146 */     if (shift > 0) {
/* 134:147 */       return getIntBits(this.parts[type] >>> shift);
/* 135:    */     }
/* 136:149 */     return getIntBits(this.parts[type]);
/* 137:    */   }
/* 138:    */   
/* 139:    */   public void setPart(int type, AvatarLook from)
/* 140:    */   {
/* 141:154 */     this.parts[type] = from.parts[type];
/* 142:    */   }
/* 143:    */   
/* 144:    */   public String filterLook(int... types)
/* 145:    */   {
/* 146:158 */     StringBuilder look = new StringBuilder(32);
/* 147:159 */     for (int type : types)
/* 148:    */     {
/* 149:160 */       int partSet = getIntShiftBits(type, 0);
/* 150:161 */       if (partSet >= 1)
/* 151:    */       {
/* 152:165 */         if (look.length() > 0) {
/* 153:166 */           look.append(".");
/* 154:    */         }
/* 155:169 */         look.append(sets[type]);
/* 156:170 */         look.append("-");
/* 157:171 */         look.append(Integer.toString(partSet));
/* 158:    */         
/* 159:173 */         int colors = getIntShiftBits(type, 12);
/* 160:174 */         if (colors > 0)
/* 161:    */         {
/* 162:175 */           look.append("-");
/* 163:176 */           look.append(Integer.toString(colors));
/* 164:    */           
/* 165:178 */           colors = getIntShiftBits(type, 24);
/* 166:179 */           if (colors > 0)
/* 167:    */           {
/* 168:180 */             look.append("-");
/* 169:181 */             look.append(Integer.toString(colors));
/* 170:    */             
/* 171:183 */             colors = getIntShiftBits(type, 36);
/* 172:184 */             if (colors > 0)
/* 173:    */             {
/* 174:185 */               look.append("-");
/* 175:186 */               look.append(Integer.toString(colors));
/* 176:    */             }
/* 177:    */           }
/* 178:    */         }
/* 179:    */       }
/* 180:    */     }
/* 181:191 */     return look.toString();
/* 182:    */   }
/* 183:    */   
/* 184:    */   private static boolean isBadType(String string)
/* 185:    */   {
/* 186:195 */     for (String set : sets) {
/* 187:196 */       if (set.equals(string)) {
/* 188:197 */         return false;
/* 189:    */       }
/* 190:    */     }
/* 191:200 */     return true;
/* 192:    */   }
/* 193:    */   
/* 194:    */   public static boolean validateLook(String SelectedLook)
/* 195:    */   {
/* 196:204 */     for (String part : SelectedLook.split("\\."))
/* 197:    */     {
/* 198:205 */       String[] set = part.split("-");
/* 199:206 */       if ((set.length < 2) || (set.length > 5) || (part.endsWith("-"))) {
/* 200:207 */         return false;
/* 201:    */       }
/* 202:210 */       if (isBadType(set[0])) {
/* 203:211 */         return false;
/* 204:    */       }
/* 205:214 */       int a = 1;
/* 206:215 */       while (a < set.length) {
/* 207:216 */         if (Utils.isBadInteger(set[(a++)])) {
/* 208:217 */           return false;
/* 209:    */         }
/* 210:    */       }
/* 211:    */     }
/* 212:221 */     return true;
/* 213:    */   }
/* 214:    */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.player.AvatarLook
 * JD-Core Version:    0.7.0.1
 */