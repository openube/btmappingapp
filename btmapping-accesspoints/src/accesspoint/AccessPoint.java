package accesspoint;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import remote.Map;

public class AccessPoint {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if(args.length <= 0){
			System.out.println("IP Address Required");
			return;
		}
		
		if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }

		String host = args[0].trim();
		try{
			Map map = (Map) Naming.lookup("rmi://"+host+"/map");
			String location = map.getLocation("My location");
			System.out.println(location);
		}catch(NotBoundException e){
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
