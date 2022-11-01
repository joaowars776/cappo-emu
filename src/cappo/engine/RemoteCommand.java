package cappo.engine;

import java.rmi.Remote;
import java.rmi.RemoteException;

public abstract interface RemoteCommand
  extends Remote
{
  public abstract String exec()
    throws RemoteException;
}


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.engine.RemoteCommand
 * JD-Core Version:    0.7.0.1
 */