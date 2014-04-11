package quiz;

import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.io.Serializable;

public class SetUpClient implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2530077513828507665L;

	public static void main(String[] args) {
		
		try {
			
			Remote remoteObj2 = Naming.lookup("//127.0.0.1:1099/setup");
			SetUpService setUpService = (SetUpService) remoteObj2;
			System.out.println("Create a new quiz (y/n) ? " + setUpService.generateGameId());
			
			do {
					System.out.println("Enter id number of game to terminate it");
					int setUpInput = Integer.parseInt(System.console().readLine().trim());

					if (setUpService.endGame(setUpInput) == 0) {
						
						System.out.println("No game by that Id, try again...");
						
					} else {

						System.out.println(setUpService.updateView(setUpInput));
					
					}
					
			} while (!setUpService.isGameOver());		

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
