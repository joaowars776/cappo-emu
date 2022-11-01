package cappo.game.roomengine.roomevents;

import cappo.engine.threadpools.RoomTask;

public abstract class Event
{
  public int Ticks;
  public Integer eventId;
  
  public abstract void run(RoomTask paramRoomTask);
}


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.roomengine.roomevents.Event
 * JD-Core Version:    0.7.0.1
 */