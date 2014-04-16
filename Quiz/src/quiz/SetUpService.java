package quiz;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.Remote;

public interface SetUpService extends Remote, Serializable {

	/**
	 * Takes input from setUpClient as a string and passes it to make3Quizzes() in QuizFactory
	 * OR stopQuiz(int) in QuizController according to what input the setUpClient enters. 
	 * If a 'y' character is entered, it is interpreted as to make new quizzes. 
	 * If any integer is entered, it is interpreted as an id# of a quiz that should be stopped. 
	 * (leading to the score being output to the setUpClient).
	 * 
	 * @param input String       setUpClient input 
	 * @throws RemoteException   (in case anything goes wrong with network connectivity)
	 */
	void processInput(String input) throws RemoteException;
	
	/**
	 * 
	 * @return
	 */
	String getSetUpView() throws RemoteException;
	
}
