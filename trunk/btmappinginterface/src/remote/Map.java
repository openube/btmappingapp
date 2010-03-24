package remote;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Map extends Remote {
	
	/** This is a sample method. Delete it and add one of your own. */
	public String getLocation(String argument) throws RemoteException;
	

}
