package quiz;

import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
//import java.io.Serializable;

public class SetUpClient {
	
	public static void main(String[] args) {
		
		try {
	
			Remote remoteObj = Naming.lookup("//127.0.0.1:1099/Now");
			SetUpService setUpService = (SetUpService) remoteObj;
			System.out.println("hello I'm a setterUpperer");
		
		} catch (RemoteException e) {
		
			e.printStackTrace();
		
		} catch (NotBoundException e) {
		
			e.printStackTrace();
		
		} catch (Exception e) {
		
			e.printStackTrace();
		
		}
	
	}
	
}
//java -Djava.security.policy=QuizSecurity.txt SetUpClient 
