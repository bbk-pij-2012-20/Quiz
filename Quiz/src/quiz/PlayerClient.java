package quiz;

import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
//import java.io.Serializable;

public class PlayerClient {

	public static void main(String[] args) {
		
		try {
	
			Remote remoteObj = Naming.lookup("//127.0.0.1:1099/playerServer");
			PlayerService playerService = (PlayerService) remoteObj;
			System.out.println("Choose which game to play (1/2/3): "+playerService.getGameList());
			
			do {
					char playerInput = System.console().readLine().trim().toLowerCase().charAt(0);
					System.out.println(playerService.updateView(playerInput));
						
			} while (!playerService.isGameOver());		
			
		} catch (RemoteException e) {
		
			e.printStackTrace();
		
		} catch (NotBoundException e) {
		
			e.printStackTrace();
		
		} catch (Exception e) {
		
			e.printStackTrace();
		
		}
		
		try {
			
			Remote remoteObj2 = Naming.lookup("//127.0.0.1:1099/quizMaker");
			SetUpService setUpService = (SetUpService) remoteObj2;
			System.out.println("hello I'm a maker");

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
