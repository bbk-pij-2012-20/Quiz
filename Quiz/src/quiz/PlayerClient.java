package quiz;

import java.io.Serializable;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;
//import java.io.Serializable;

public class PlayerClient implements Serializable{
	
	private static final long serialVersionUID = 4942293985111888448L;
	
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		try {
	
			Remote remoteObj = Naming.lookup("//127.0.0.1:1099/play");
			PlayerService playerService = (PlayerService) remoteObj;
			System.out.println("Choose which game to play (1/2/3): "+playerService.getGameList());
			
			do {
					char playerInput = scan.next().trim().toLowerCase().charAt(0);
					System.out.println(playerService.updateView(playerInput));
						
			} while (!playerService.isGameOver());		
			
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
