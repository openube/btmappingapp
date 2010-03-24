package server;

import java.rmi.RemoteException; 
import java.rmi.server.UnicastRemoteObject;

import remote.Map;

public class MapImpl extends UnicastRemoteObject implements Map {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6287314101803652675L;

	public MapImpl() throws RemoteException{
		
	}
	
	public String getLocation(String argument) throws RemoteException {
		// TODO Auto-generated method stub
		System.out.println("Implementation accessed");
		return argument + " has been received and here is the location";
	}

}
