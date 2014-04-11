package quiz;

import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.io.Serializable;

public class SetUpClient implements Serializable {
	
	private static final long serialVersionUID = -2530077513828507665L;

	public static void main(String[] args) {
		
		try {
			
			Remote remoteObj2 = Naming.lookup("//127.0.0.1:1099/setup");
			SetUpService setUpService = (SetUpService) remoteObj2;
					
			System.out.println("Create new quizzes (y) or end a quiz by entering its id#");					
			String input = System.console().readLine().trim()
			setUpService.processInput(input);												

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
