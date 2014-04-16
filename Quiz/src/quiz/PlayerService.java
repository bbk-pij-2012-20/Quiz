package quiz;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.Remote;

public interface PlayerService extends Remote, Serializable {
	
	/**
	 * Called by the playerClient as the first activity in a quiz game for a player
	 * to start playing a quiz.
	 *  
	 * @return a number as a String from the player, 1 of 3 game selections 
	 * @throws RemoteException 
	 */
	String getGameList() throws RemoteException;
	
	/**
	 * Takes an input from the player and passes it to QuizController 
	 * to load the chosen quiz to play. It then is also used to process 
	 * answers submitted by playerClient.
	 * 
	 * @param userInput  an int input 
	 */
	void processInput(int userInput) throws RemoteException;

	/**
	 * Checks with QuizController on status of the current quiz game. 
	 * While active, it expects an input to processInput(int)
	 * 
	 * @return true if current game is not active.
	 */
	boolean isCurrentQuizActive() throws RemoteException;

	/**
	 * 
	 * @return a String  which is returned to be output to UI via view
	 */
	String getPlayerView();
	
}
