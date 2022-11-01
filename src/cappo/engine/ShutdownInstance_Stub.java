package cappo.engine;

import java.lang.reflect.Method;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.UnexpectedException;
import java.rmi.server.RemoteObject;
import java.rmi.server.RemoteRef;
import java.rmi.server.RemoteStub;

public final class ShutdownInstance_Stub
  extends RemoteStub
  implements RemoteCommand, Remote
{
  private static final long serialVersionUID = 2L;
  private static Method $method_exec_0;
  
  static
  {
    try
    {
      $method_exec_0 = RemoteCommand.class.getMethod("exec", new Class[0]);
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      throw new NoSuchMethodError("stub class initialization failed");
    }
  }
  
  public ShutdownInstance_Stub(RemoteRef paramRemoteRef)
  {
    super(paramRemoteRef);
  }
  
  public String exec()
    throws RemoteException
  {
    try
    {
      Object localObject = this.ref.invoke(this, $method_exec_0, null, -4315905130807960990L);
      return (String)localObject;
    }
    catch (RuntimeException localRuntimeException)
    {
      throw localRuntimeException;
    }
    catch (RemoteException localRemoteException)
    {
      throw localRemoteException;
    }
    catch (Exception localException)
    {
      throw new UnexpectedException("undeclared checked exception", localException);
    }
  }
}


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.engine.ShutdownInstance_Stub
 * JD-Core Version:    0.7.0.1
 */