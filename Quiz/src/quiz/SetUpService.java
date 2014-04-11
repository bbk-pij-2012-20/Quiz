package quiz;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.Remote;

public interface SetUpService extends Remote, Serializable {


	/**
	 * Queries the status of the gameIsOver boolean in QuizControllerImpl. 
	 * 
	 * @return true if the game is over
	 */
	boolean isGameOver() throws RemoteException;

	/**
	 * creates 3 quizzes (2 questions, 4 questions and 6 questions) by indirectly calling 
	 * makeListofQAndALists() in quizFactory which itself calls generateAndStoreId(). 
	 * Each quiz and its id number are stored together in a hashmap in quizFactory.
	 * 
	 * @return
	 * @throws RemoteException
	 */
	void make3Quizzes() throws RemoteException;

	/**
	 * Takes the id number of a quiz from the setup client and terminates the 
	 * game, returning the score to the user.
	 * 
	 * @param idOfQuizToStop
	 * @return
	 * @throws RemoteException
	 */
	int endGame(int idOfQuizToStop) throws RemoteException;

	/**
	 * Takes input from setUpClient and passes it to make3Quizzes or endGame according to
	 * what the input is (a 'y' for yes make quizzes, or a number for id number of quiz that 
	 * should be terminated).
	 * 
	 * @param input from the user input by the setup client.
	 * @throws RemoteException 
	 */
	void processInput(String input) throws RemoteException;
	
}
