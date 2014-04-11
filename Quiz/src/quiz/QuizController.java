package quiz;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.io.Serializable;

public interface QuizController extends Remote {


	/**
	 * Gives the playerClient option to begin a new quiz, with parameters set up by setUpClient. It calls 
	 * the private method startGame() to prompt the user to start. It calls the private method 
	 * makeListOfQAndALists(), which calls private method isRepeatedQuestion(QueAndAns,int) to prevent 
	 * repeating the same question. It calls two more private methods: keepScore(String,int,int) and 
	 * shuffleAnswers(int).
	 * 
	 * @throws RemoteException    throws RemoteException if anything goes wrong with network connectivity.
	 */
	void playQuiz() throws RemoteException;

	/**
	 * 
	 * @param input
	 * @return a String of depicting the next view
	 */
	char[] updateView(char input);

	/**
	 * 
	 * @return true if game is finished.
	 */
	boolean getGameIsOverStatus();


}
