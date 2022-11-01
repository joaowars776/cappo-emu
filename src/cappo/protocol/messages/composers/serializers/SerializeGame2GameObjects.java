/*  1:   */ package cappo.protocol.messages.composers.serializers;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.game.games.snowwar.SnowWarRoom;
/*  5:   */ import cappo.game.games.snowwar.gameobjects.GameItemObject;
/*  6:   */ import cappo.game.games.snowwar.gameobjects.HumanGameObject;
/*  7:   */ import cappo.protocol.messages.Composer;
/*  8:   */ import java.util.Map;
/*  9:   */ 
/* 10:   */ public class SerializeGame2GameObjects
/* 11:   */ {
/* 12:   */   public static void parse(MessageWriter ClientMessage, SnowWarRoom arena)
/* 13:   */   {
/* 14:16 */     Composer.add(Integer.valueOf(arena.gameObjects.size()), ClientMessage);
/* 15:17 */     for (GameItemObject Object : arena.gameObjects.values())
/* 16:   */     {
/* 17:18 */       for (int i = 0; i < Object.variablesCount; i++) {
/* 18:19 */         Composer.add(Integer.valueOf(Object.getVariable(i)), ClientMessage);
/* 19:   */       }
/* 20:22 */       if (Object.getVariable(0) == 5)
/* 21:   */       {
/* 22:23 */         HumanGameObject Player = (HumanGameObject)Object;
/* 23:24 */         Composer.add(Player.userName, ClientMessage);
/* 24:25 */         Composer.add(Player.motto, ClientMessage);
/* 25:26 */         Composer.add(Player.look, ClientMessage);
/* 26:27 */         Composer.add(Player.sex == 1 ? "M" : "F", ClientMessage);
/* 27:   */       }
/* 28:   */     }
/* 29:   */   }
/* 30:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.serializers.SerializeGame2GameObjects
 * JD-Core Version:    0.7.0.1
 */