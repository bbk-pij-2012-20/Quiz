package quiz.server;

import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.registry.LocateRegistry;
import java.net.MalformedURLException;
import java.rmi.RemoteException;

public class QuizLauncher {

	public static void main(String[] args) {
		
		QuizLauncher quizLauncher = new QuizLauncher();
		quizLauncher.launch();
		
	}
	
	public void launch(){
		
		System.setProperty("java.security.policy", "src/QSecurity.policy");
		
		if (System.getSecurityManager() == null) {
			
			System.setSecurityManager(new RMISecurityManager());
			
		}
		
		try {
			
			LocateRegistry.createRegistry(1099);
			PlayerServer playerServer = new PlayerServer();
			SetUpServer setUpServer = new SetUpServer();
			String registryHost = "//localhost/";
			String serviceName = "play";
			String serviceName2 = "setup";
			Naming.rebind(registryHost+serviceName,playerServer);
			Naming.rebind(registryHost+serviceName2,setUpServer);

		} catch (MalformedURLException e) {
		
			e.printStackTrace();
						
		} catch (RemoteException e) {
		
			e.printStackTrace();
		
		}
	}
}
