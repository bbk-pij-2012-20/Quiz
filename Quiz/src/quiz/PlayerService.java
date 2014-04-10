package quiz;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.Remote;

public interface PlayerService extends Remote, Serializable {

	/**
	 * checks with QuizController on status of the current quiz game. 
	 * While the game is not over, it expects an input to updateView(String)
	 * 
	 * 
	 * @return
	 */
	boolean isGameOver();

	/**
	 * Takes an input from the player and passes it to QuizController 
	 * which processes it and returns a String to be printed out to
	 * the player's UI (client-side). 
	 * 
	 * @param readLine   the player's input to the UI which is only ever a single character
	 * @return a String  which is returned to be output to UI
	 */
	char[] updateView(char input);

	/**
	 * Called by the playerClient as the first activity in a quiz game for a player
	 * to start playing a quiz.
	 *  
	 * @return a number as a String from the player, 1 of 3 game selections 
	 */
	String getGameList();


	
}
