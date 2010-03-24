package server;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.net.*;

public class Server  {

	/**
	 * 
	 */

	public static void main(String[] args){
		try{
			System.out.println("Server Started");
			System.out.println("Host Name:" + InetAddress.getLocalHost().getHostName());
			System.out.println("Ip Address:" + InetAddress.getLocalHost().getHostAddress());
			Registry registry = LocateRegistry.getRegistry();
			registry.bind("map", new MapImpl());
		}catch(RemoteException e){
			e.printStackTrace();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AlreadyBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
}
