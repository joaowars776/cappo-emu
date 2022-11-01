package cappo.game.roomengine.entity.item.floor.wired.condition;

import cappo.engine.player.Connection;
import cappo.game.roomengine.entity.item.floor.wired.WiredItemBase;

public abstract class WiredConditionBase
  extends WiredItemBase
{
  public abstract boolean checkCondition(Connection paramConnection);
}


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.roomengine.entity.item.floor.wired.condition.WiredConditionBase
 * JD-Core Version:    0.7.0.1
 */