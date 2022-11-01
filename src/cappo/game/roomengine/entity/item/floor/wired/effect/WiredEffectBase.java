package cappo.game.roomengine.entity.item.floor.wired.effect;

import cappo.engine.player.Connection;
import cappo.game.roomengine.entity.item.floor.wired.WiredItemBase;

public abstract class WiredEffectBase
  extends WiredItemBase
{
  public int delayEffect;
  
  public abstract void invoke(Connection paramConnection);
}


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.roomengine.entity.item.floor.wired.effect.WiredEffectBase
 * JD-Core Version:    0.7.0.1
 */