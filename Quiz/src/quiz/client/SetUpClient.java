package quiz.client;

import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;
import java.io.Serializable;

import quiz.server.SetUpService;

public class SetUpClient { //implements Serializable {
		
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);

		try {
			
			Remote remoteObj2 = Naming.lookup("//127.0.0.1:1099/setup");
			SetUpService setUpService = (SetUpService) remoteObj2;
					
			System.out.println("\nCreate new quizzes (y) or stop a quiz by entering its id#");					
			String input = scan.next().trim().toLowerCase();
			setUpService.processInput(input);
			System.out.println("---------------\n3 quizzes made:\n--------------- " + setUpService.getSetUpView());
			
			
		} catch (RemoteException e) {
		
			e.printStackTrace();
		
		} catch (NotBoundException e) {
		
			e.printStackTrace();
		
		} catch (Exception e) {
		
			e.printStackTrace();
		
		}
	
	}

}	

