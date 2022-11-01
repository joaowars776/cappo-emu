package cappo.engine.threadpools;

import java.util.concurrent.ScheduledFuture;

public abstract class GameTask
  extends Thread
{
  public ScheduledFuture<?> future;
}


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.engine.threadpools.GameTask
 * JD-Core Version:    0.7.0.1
 */