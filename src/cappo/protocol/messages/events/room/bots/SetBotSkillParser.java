/*   1:    */ package cappo.protocol.messages.events.room.bots;
/*   2:    */ 
/*   3:    */ import cappo.engine.network.MessageReader;
/*   4:    */ import cappo.engine.network.QueueWriter;
/*   5:    */ import cappo.engine.player.Connection;
/*   6:    */ import cappo.engine.threadpools.RoomTask;
/*   7:    */ import cappo.game.bots.RentalBot;
/*   8:    */ import cappo.game.player.AvatarLook;
/*   9:    */ import cappo.game.player.PlayerData;
/*  10:    */ import cappo.game.roomengine.chat.wf.WordFilter;
/*  11:    */ import cappo.game.roomengine.chat.wf.WordFilterAction;
/*  12:    */ import cappo.game.roomengine.entity.live.Avatar;
/*  13:    */ import cappo.game.roomengine.entity.live.RentalBotEntity;
/*  14:    */ import cappo.protocol.messages.IncomingMessageEvent;
/*  15:    */ import cappo.protocol.messages.composers.room.action.UserDanceComposer;
/*  16:    */ import cappo.protocol.messages.composers.room.bots.BotErrorComposer;
/*  17:    */ import cappo.protocol.messages.composers.room.engine.UserChangeComposer;
/*  18:    */ import cappo.protocol.messages.composers.users.NotifyUserNameChangeComposer;
/*  19:    */ import java.util.List;
/*  20:    */ 
/*  21:    */ public class SetBotSkillParser
/*  22:    */   extends IncomingMessageEvent
/*  23:    */ {
/*  24:    */   public void messageReceived(Connection Main)
/*  25:    */   {
/*  26: 26 */     Avatar avatar = Main.avatar;
/*  27: 27 */     if (avatar == null) {
/*  28: 28 */       return;
/*  29:    */     }
/*  30: 31 */     RentalBotEntity botEntity = avatar.room.getRoomBotById(Main.currentPacket.readInt());
/*  31: 32 */     if (botEntity == null) {
/*  32: 33 */       return;
/*  33:    */     }
/*  34: 36 */     RentalBot bot = botEntity.botData;
/*  35: 37 */     if (bot.ownerId != Main.playerData.userId) {
/*  36: 38 */       return;
/*  37:    */     }
/*  38: 41 */     int propId = Main.currentPacket.readInt();
/*  39: 42 */     String value = Main.currentPacket.readString();
/*  40:    */     
/*  41: 44 */     WordFilterAction action = WordFilter.getAction(value);
/*  42: 45 */     if ((action != null) && (action.run(Main))) {
/*  43: 46 */       return;
/*  44:    */     }
/*  45: 49 */     if (4 == propId)
/*  46:    */     {
/*  47: 50 */       bot.danceEnabled = (!bot.danceEnabled);
/*  48: 51 */       int danceId = bot.danceEnabled ? 1 : 0;
/*  49: 52 */       bot.botEntity.room.sendMessage(UserDanceComposer.compose(bot.botEntity.virtualId, danceId));
/*  50:    */     }
/*  51: 53 */     else if (3 == propId)
/*  52:    */     {
/*  53: 54 */       bot.walkRandomEnabled = (!bot.walkRandomEnabled);
/*  54:    */     }
/*  55: 55 */     else if (1 == propId)
/*  56:    */     {
/*  57: 56 */       setBotLook(bot, Main.playerData);
/*  58:    */     }
/*  59: 57 */     else if (5 == propId)
/*  60:    */     {
/*  61: 58 */       if (value.length() < 5)
/*  62:    */       {
/*  63: 59 */         QueueWriter.write(Main.socket, BotErrorComposer.compose(4));
/*  64: 60 */         return;
/*  65:    */       }
/*  66: 63 */       if (value.length() > 15)
/*  67:    */       {
/*  68: 64 */         QueueWriter.write(Main.socket, BotErrorComposer.compose(4));
/*  69: 65 */         return;
/*  70:    */       }
/*  71: 68 */       if (value.toLowerCase().startsWith("mod-"))
/*  72:    */       {
/*  73: 69 */         QueueWriter.write(Main.socket, BotErrorComposer.compose(4));
/*  74: 70 */         return;
/*  75:    */       }
/*  76: 73 */       setBotName(bot, value);
/*  77:    */     }
/*  78: 74 */     else if (2 == propId)
/*  79:    */     {
/*  80: 75 */       parseChatConfiguration(bot, value);
/*  81:    */     }
/*  82:    */   }
/*  83:    */   
/*  84:    */   private void setBotLook(RentalBot bot, PlayerData playerData)
/*  85:    */   {
/*  86: 80 */     bot.botLook = playerData.avatarLook;
/*  87: 81 */     bot.gender = (playerData.sex == 1 ? "M" : "F");
/*  88: 82 */     bot.botEntity.room.sendMessage(UserChangeComposer.compose(bot.botEntity.virtualId, bot.botLook.toString(), bot.gender.equals("M") ? 1 : 0, bot.motto, 0));
/*  89:    */   }
/*  90:    */   
/*  91:    */   private void setBotName(RentalBot bot, String name)
/*  92:    */   {
/*  93: 86 */     bot.name = name;
/*  94: 87 */     bot.botEntity.room.sendMessage(NotifyUserNameChangeComposer.compose(-1, bot.botEntity.virtualId, bot.name));
/*  95:    */   }
/*  96:    */   
/*  97:    */   private void parseChatConfiguration(RentalBot bot, String data)
/*  98:    */   {
/*  99:    */     String[] tmp;
/* 100:    */     String[] tmp;
/* 101: 93 */     if (data.indexOf(";#;") == -1) {
/* 102: 94 */       tmp = data.split(";");
/* 103:    */     } else {
/* 104: 96 */       tmp = data.split(";#;");
/* 105:    */     }
/* 106: 99 */     bot.speeches.clear();
/* 107:100 */     if ((tmp.length == 3) || (tmp.length == 4))
/* 108:    */     {
/* 109:101 */       for (String line : tmp[0].split("\r")) {
/* 110:102 */         if ((!line.isEmpty()) && (line.length() <= 100)) {
/* 111:105 */           bot.speeches.add(line);
/* 112:    */         }
/* 113:    */       }
/* 114:107 */       bot.chatAuto = ((tmp[1].toLowerCase().equals("true")) || (tmp[1].equals("1")));
/* 115:108 */       bot.chatDelay = Integer.parseInt(tmp[2]);
/* 116:109 */       if (bot.chatDelay < 7) {
/* 117:110 */         bot.chatDelay = 7;
/* 118:    */       }
/* 119:    */     }
/* 120:    */   }
/* 121:    */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.events.room.bots.SetBotSkillParser
 * JD-Core Version:    0.7.0.1
 */