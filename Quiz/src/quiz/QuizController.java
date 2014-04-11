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
	 * @return true if game is finished.
	 * @throws RemoteException 
	 */
	boolean getGameIsOverStatus() throws RemoteException;

	/**
	 * getter for listOfQAndALists (primarily for JUnit)
	 * 
	 * @return an array of type QueAndAns 
	 * @throws RemoteException
	 */
	QueAndAns[] getListOfQAndALists() throws RemoteException;
	
	/**
	 * setter for listOfQAndALists (primarily for JUnit)
	 * 
	 * @param listOfQAndALists   an array of type QueAndAns
	 * @throws RemoteException
	 */
	void setListOfQAndALists(QueAndAns[] listOfQAndALists) throws RemoteException;

	/**
	 * 
	 * @return
	 * @throws RemoteException
	 */
	int getScore() throws RemoteException;

	/**
	 * 
	 * @param view
	 * @throws RemoteException
	 */
	void updateView(String view) throws RemoteException;

	/**
	 * 
	 * @return
	 * @throws RemoteException
	 */
	String getView() throws RemoteException;

	String getUserInput() throws RemoteException;

	void setUserInput(String userInput) throws RemoteException;

	void processPlayerInput();

}
