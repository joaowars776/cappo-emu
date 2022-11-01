/*  1:   */ package cappo.engine;
/*  2:   */ 
/*  3:   */ import java.rmi.RemoteException;
/*  4:   */ import java.rmi.server.UnicastRemoteObject;
/*  5:   */ 
/*  6:   */ public class ShutdownInstance
/*  7:   */   extends UnicastRemoteObject
/*  8:   */   implements RemoteCommand
/*  9:   */ {
/* 10:   */   public ShutdownInstance()
/* 11:   */     throws RemoteException
/* 12:   */   {}
/* 13:   */   
/* 14:   */   public String exec()
/* 15:   */     throws RemoteException
/* 16:   */   {
/* 17:20 */     ServerProps.STATUS = false;
/* 18:21 */     return "WORKS!: ";
/* 19:   */   }
/* 20:   */ }


/* Location:           C:\Users\Manel\Downloads\cappo.zip
 * Qualified Name:     cappo.engine.ShutdownInstance
 * JD-Core Version:    0.7.0.1
 */