package cappo.game.roomengine.itemInteractor;

import cappo.engine.player.Connection;
import cappo.engine.threadpools.RoomTask;
import cappo.game.roomengine.entity.item.floor.FloorItem;
import cappo.game.roomengine.entity.item.floor.GenericFloorItem;
import cappo.game.roomengine.entity.item.wall.GenericWallItem;

public class InteractorHabboWheel
  extends Interactor
{
  public void OnPlace(RoomTask room, Connection User, GenericFloorItem Item) {}
  
  public void OnTriggerFloor(RoomTask room, Connection User, FloorItem Item, int Request, boolean UserHasRights) {}
  
  public void OnTriggerWall(RoomTask room, Connection User, GenericWallItem Item, int Request, boolean UserHasRights) {}
  
  public void OnPickUp(RoomTask room, Connection User, GenericFloorItem Item) {}
}


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.roomengine.itemInteractor.InteractorHabboWheel
 * JD-Core Version:    0.7.0.1
 */