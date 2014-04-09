package quiz;

import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
//import java.io.Serializable;

public class PlayerClient {

	public static void main(String[] args) {
		
		try {
	
			Remote remoteObj = Naming.lookup("//127.0.0.1:1099/Quizzy");
			PlayerService playerService = (PlayerService) remoteObj;
			System.out.println("hello I'm a player");
		
		} catch (RemoteException e) {
		
			e.printStackTrace();
		
		} catch (NotBoundException e) {
		
			e.printStackTrace();
		
		} catch (Exception e) {
		
			e.printStackTrace();
		
		}
	
	}
	
}
//java -Djava.security.policy=QuizSecurity.txt PlayerClient 
