package quiz.client;

import java.io.Serializable;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

import quiz.server.PlayerService;

public class PlayerClient implements Serializable{
	
	private static final long serialVersionUID = 4942293985111888448L;
	
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		try {
	
			Remote remoteObj = Naming.lookup("//127.0.0.1:1099/play");
			PlayerService playerService = (PlayerService) remoteObj;
			System.out.println("\nChoose which game to play (1/2/3): "+playerService.getGameList());
			
			do {
					
					playerService.processInput(scan.nextInt());
					System.out.println(playerService.getPlayerView());
						
			} while (playerService.isCurrentQuizActive());		
			
		} catch (RemoteException e) {
		
			e.printStackTrace();
		
		} catch (NotBoundException e) {
		
			e.printStackTrace();
		
		} catch (Exception e) {
		
			e.printStackTrace();
		
		}
		
	}
	
}
//java -Djava.security.policy=QSecurity.txt PlayerClient 
