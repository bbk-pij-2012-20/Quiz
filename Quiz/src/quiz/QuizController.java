package quiz;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.io.Serializable;

public interface QuizController extends Remote {

	/**
	 * Processes playerClient user input, first a selection of which quiz to play.
	 * Second the answer submitted for each question.
	 * 
	 * @throws RemoteException (in case anything goes wrong with network connectivity)
	 */
	void processPlayerInput() throws RemoteException;

	/**
	 * Gives the playerClient option to begin a new quiz, with parameters set up by setUpClient. It calls 
	 * the private method startGame() to prompt the user to start. It calls the private method 
	 * makeListOfQAndALists(), which calls private method isRepeatedQuestion(QueAndAns,int) to prevent 
	 * repeating the same question. It calls two more private methods: keepScore(String,int,int) and 
	 * shuffleAnswers(int).
	 * 
	 * @throws RemoteException    (in case anything goes wrong with network connectivity)
	 */
	void playQuiz() throws RemoteException;

	/**
	 * 
	 * @return true if game is finished.
	 * @throws RemoteException (in case anything goes wrong with network connectivity)
	 */
	boolean getGameIsOverStatus() throws RemoteException;

	/**
	 * getter for listOfQAndALists (primarily for JUnit)
	 * 
	 * @return an array of type QueAndAns 
	 * @throws RemoteException (in case anything goes wrong with network connectivity)
	 */
	QueAndAns[] getListOfQAndALists() throws RemoteException;
	
	/**
	 * setter for listOfQAndALists (primarily for JUnit)
	 * 
	 * @param listOfQAndALists   an array of type QueAndAns
	 * @throws RemoteException   (in case anything goes wrong with network connectivity)
	 */
	void setListOfQAndALists(QueAndAns[] listOfQAndALists) throws RemoteException;

	/**
	 * 
	 * @param view
	 * @throws RemoteException  (in case anything goes wrong with network connectivity)
	 */
	void updateView(String view) throws RemoteException;

	/**
	 * 
	 * @return
	 * @throws RemoteException  (in case anything goes wrong with network connectivity)
	 */
	String getView() throws RemoteException;

	/**
	 * 
	 * @return
	 * @throws RemoteException (in case anything goes wrong with network connectivity)
	 */
	String getUserInput() throws RemoteException;

	/**
	 * 
	 * @param userInput
	 * @throws RemoteException (in case anything goes wrong with network connectivity)
	 */
	void setUserInput(String userInput) throws RemoteException;


	/**
	 * Queries the status of the gameIsOver boolean in QuizControllerImpl. 
	 * 
	 * @param quizId
	 * @return true if the game is over
	 * @throws RemoteException (in case anything goes wrong with network connectivity)
	 */
	boolean isGameOver(int quizId) throws RemoteException;

	
	int getScore(int idOf) throws RemoteException;

	/**
	 * Takes the id number of a quiz from the setup client and terminates 
	 * the game, returning the score to the user.
	 * 
	 * @param quizId   id# int of quiz to be terminated
	 * @return score   score int of quiz acquired whenever a game ends
	 * @throws RemoteException (in case anything goes wrong with network connectivity)
	 */
	int endGame(int quizId) throws RemoteException;

}
