/*   1:    */ package cappo.protocol.messages;
/*   2:    */ 
/*   3:    */ import cappo.engine.Server;
/*   4:    */ import cappo.engine.logging.Log;
/*   5:    */ import java.io.InputStream;
/*   6:    */ import java.lang.reflect.Field;
/*   7:    */ import java.net.URL;
/*   8:    */ import java.net.URLConnection;
/*   9:    */ import java.net.URLDecoder;
/*  10:    */ import java.util.Enumeration;
/*  11:    */ import java.util.HashMap;
/*  12:    */ import java.util.Map;
/*  13:    */ import java.util.Map.Entry;
/*  14:    */ import java.util.Properties;
/*  15:    */ import java.util.jar.JarEntry;
/*  16:    */ import java.util.jar.JarFile;
/*  17:    */ 
/*  18:    */ public class OpCodesManager
/*  19:    */ {
/*  20:    */   private static Map<String, Class<?>> composersMap;
/*  21:    */   private static Map<String, IncomingMessageEvent> parsersMap;
/*  22:    */   private static String composerChecksum;
/*  23:    */   private static String parserChecksum;
/*  24:    */   
/*  25:    */   public static void init()
/*  26:    */     throws Exception
/*  27:    */   {
/*  28: 25 */     getObjects();
/*  29: 26 */     OpCodes.registerComposers();
/*  30: 27 */     OpCodes.registerParsers();
/*  31: 28 */     checkComposerOverrides();
/*  32: 29 */     checkParserOverrides();
/*  33:    */   }
/*  34:    */   
/*  35:    */   public static void checkComposerOverrides()
/*  36:    */     throws Exception
/*  37:    */   {
/*  38: 33 */     ClassLoader classLoader = Server.class.getClassLoader();
/*  39: 34 */     URL resURL = classLoader.getResource("Composers.properties");
/*  40: 35 */     if (resURL == null) {
/*  41: 36 */       return;
/*  42:    */     }
/*  43: 39 */     URLConnection resConn = resURL.openConnection();
/*  44: 40 */     resConn.setUseCaches(false);
/*  45: 41 */     InputStream in = resConn.getInputStream();
/*  46:    */     
/*  47: 43 */     Properties props = new Properties();
/*  48: 44 */     props.load(in);
/*  49: 45 */     in.close();
/*  50:    */     
/*  51: 47 */     String check = props.getProperty("checksum", "1");
/*  52: 48 */     if ((composerChecksum != null) && (composerChecksum.equals(check))) {
/*  53: 50 */       return;
/*  54:    */     }
/*  55: 52 */     composerChecksum = check;
/*  56: 54 */     for (Map.Entry<Object, Object> e : props.entrySet())
/*  57:    */     {
/*  58: 55 */       String key = (String)e.getKey();
/*  59: 56 */       if (!key.equals("checksum"))
/*  60:    */       {
/*  61: 60 */         int header = Integer.parseInt((String)e.getValue());
/*  62: 61 */         setComposerId(key, header);
/*  63:    */       }
/*  64:    */     }
/*  65:    */   }
/*  66:    */   
/*  67:    */   public static void checkParserOverrides()
/*  68:    */     throws Exception
/*  69:    */   {
/*  70: 66 */     ClassLoader classLoader = Server.class.getClassLoader();
/*  71: 67 */     URL resURL = classLoader.getResource("Parsers.properties");
/*  72: 68 */     if (resURL == null) {
/*  73: 69 */       return;
/*  74:    */     }
/*  75: 72 */     URLConnection resConn = resURL.openConnection();
/*  76: 73 */     resConn.setUseCaches(false);
/*  77: 74 */     InputStream in = resConn.getInputStream();
/*  78:    */     
/*  79: 76 */     Properties props = new Properties();
/*  80: 77 */     props.load(in);
/*  81: 78 */     in.close();
/*  82:    */     
/*  83: 80 */     String check = props.getProperty("checksum", "1");
/*  84: 81 */     if ((parserChecksum != null) && (parserChecksum.equals(check))) {
/*  85: 83 */       return;
/*  86:    */     }
/*  87: 85 */     parserChecksum = check;
/*  88: 87 */     for (Map.Entry<Object, Object> e : props.entrySet())
/*  89:    */     {
/*  90: 88 */       String key = (String)e.getKey();
/*  91: 89 */       if (!key.equals("checksum"))
/*  92:    */       {
/*  93: 93 */         int header = Integer.parseInt((String)e.getValue());
/*  94: 94 */         setParserId(header, key);
/*  95:    */       }
/*  96:    */     }
/*  97:    */   }
/*  98:    */   
/*  99:    */   public static void setComposerId(String key, int header)
/* 100:    */     throws Exception
/* 101:    */   {
/* 102: 99 */     Class<?> cls = (Class)composersMap.get(key);
/* 103:100 */     if (cls == null)
/* 104:    */     {
/* 105:101 */       Log.printLog("NULL setComposerId:" + key);
/* 106:102 */       return;
/* 107:    */     }
/* 108:104 */     Log.printLog("setComposerId:" + key + ":" + header);
/* 109:105 */     cls.getField("HEADER").setInt(null, header);
/* 110:    */   }
/* 111:    */   
/* 112:    */   public static void setParserId(int header, String key)
/* 113:    */     throws Exception
/* 114:    */   {
/* 115:109 */     IncomingMessageEvent event = (IncomingMessageEvent)parsersMap.get(key);
/* 116:110 */     if (event == null)
/* 117:    */     {
/* 118:111 */       Log.printLog("NULL setParserId:" + key);
/* 119:112 */       return;
/* 120:    */     }
/* 121:114 */     Log.printLog("setParserId:" + key + ":" + header);
/* 122:115 */     IncomingMessageEvent.callBacks[event.HEADER] = null;
/* 123:116 */     IncomingMessageEvent.callBacks[header] = event;
/* 124:117 */     event.HEADER = header;
/* 125:    */   }
/* 126:    */   
/* 127:    */   private static void getObjects()
/* 128:    */     throws Exception
/* 129:    */   {
/* 130:121 */     composersMap = new HashMap();
/* 131:122 */     parsersMap = new HashMap();
/* 132:    */     
/* 133:124 */     ClassLoader classLoader = Server.class.getClassLoader();
/* 134:125 */     URL packageURL = classLoader.getResource("cappo/protocol/messages/");
/* 135:    */     
/* 136:127 */     String prefix1 = "cappo/protocol/messages/composers/";
/* 137:128 */     String prefix2 = "cappo/protocol/messages/events/";
/* 138:129 */     int len1 = prefix1.length();int len2 = prefix2.length();
/* 139:131 */     if (packageURL.getProtocol().equals("jar"))
/* 140:    */     {
/* 141:132 */       String jarFileName = URLDecoder.decode(packageURL.getFile(), "UTF-8");
/* 142:133 */       jarFileName = jarFileName.substring(5, jarFileName.indexOf("!"));
/* 143:    */       
/* 144:135 */       JarFile jf = new JarFile(jarFileName);
/* 145:136 */       Enumeration<JarEntry> jarEntries = jf.entries();
/* 146:137 */       while (jarEntries.hasMoreElements())
/* 147:    */       {
/* 148:138 */         String entryName = ((JarEntry)jarEntries.nextElement()).getName();
/* 149:139 */         if (entryName.endsWith(".class"))
/* 150:    */         {
/* 151:140 */           entryName = entryName.substring(0, entryName.length() - 6);
/* 152:142 */           if (entryName.startsWith(prefix1))
/* 153:    */           {
/* 154:143 */             entryName = entryName.replaceAll("/", "\\.");
/* 155:144 */             String key = entryName.substring(len1);
/* 156:145 */             Class<?> value = Class.forName(entryName);
/* 157:146 */             composersMap.put(key, value);
/* 158:    */           }
/* 159:147 */           else if (entryName.startsWith(prefix2))
/* 160:    */           {
/* 161:148 */             entryName = entryName.replaceAll("/", "\\.");
/* 162:149 */             String key = entryName.substring(len2);
/* 163:150 */             Class<?> cls = Class.forName(entryName);
/* 164:151 */             Object instance = cls.newInstance();
/* 165:152 */             if ((instance instanceof IncomingMessageEvent))
/* 166:    */             {
/* 167:153 */               IncomingMessageEvent value = (IncomingMessageEvent)instance;
/* 168:154 */               parsersMap.put(key, value);
/* 169:    */             }
/* 170:    */           }
/* 171:    */         }
/* 172:    */       }
/* 173:159 */       jf.close();
/* 174:    */     }
/* 175:    */   }
/* 176:    */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.OpCodesManager
 * JD-Core Version:    0.7.0.1
 */