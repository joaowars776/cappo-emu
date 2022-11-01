/*  1:   */ package cappo.protocol.messages.composers.serializers;
/*  2:   */ 
/*  3:   */ import cappo.engine.network.MessageWriter;
/*  4:   */ import cappo.game.collections.BaseItem;
/*  5:   */ import cappo.game.roomengine.entity.item.Item;
/*  6:   */ import cappo.game.roomengine.entity.item.extradata.ExtraDataBase;
/*  7:   */ import cappo.protocol.messages.Composer;
/*  8:   */ 
/*  9:   */ public class SerializeItemData
/* 10:   */ {
/* 11:   */   public static void parse(MessageWriter writer, BaseItem baseItem, Item item)
/* 12:   */   {
/* 13:16 */     Composer.add(Integer.valueOf(item.extraData.getType()), writer);
/* 14:17 */     item.extraData.serializeComposer(writer);
/* 15:   */   }
/* 16:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.protocol.messages.composers.serializers.SerializeItemData
 * JD-Core Version:    0.7.0.1
 */