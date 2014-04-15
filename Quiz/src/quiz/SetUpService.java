package quiz;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.Remote;

public interface SetUpService extends Remote, Serializable {

	/**
	 * Takes input from setUpClient and passes it to make3Quizzes or 
	 * endGame according to what the input is (a 'y' for yes make quizzes, or a number for id number of quiz that 
	 * should be terminated).
	 * 
	 * @param input from the user input by the setup client.
	 * @throws RemoteException (in case anything goes wrong with network connectivity)
	 */
	void processInput(String input) throws RemoteException;
	
}
