package cappo.game.games.snowwar.gameevents;

public abstract class Event
{
  public static final int PLAYERLEFT = 1;
  public static final int MOVE = 2;
  public static final int BALLTHROWHUMAN = 3;
  public static final int BALLTHROWPOSITION = 4;
  public static final int MAKENOWBALL = 7;
  public static final int CREATESNOWBALL = 8;
  public static final int ADDBALLTOMACHINE = 11;
  public static final int PICKBALLFROMGAMEITEM = 12;
  public int EventType;
  
  public abstract void apply();
}


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.game.games.snowwar.gameevents.Event
 * JD-Core Version:    0.7.0.1
 */