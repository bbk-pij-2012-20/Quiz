package quiz;

import java.rmi.RemoteException;
import java.rmi.Remote;

public interface SetUpService extends Remote {


	/**
	 * Queries the status of the gameIsOver boolean in QuizControllerImpl. 
	 * 
	 * @return true if the game is over
	 */
	boolean isGameOver() throws RemoteException;

	/**
	 * generates a new game Id number 
	 * 
	 * @return
	 * @throws RemoteException
	 */
	String generateGameId() throws RemoteException;

	/**
	 * 
	 * @param setUpInput
	 * @return
	 */
	char[] updateView(int setUpInput) throws RemoteException;

	/**
	 * 
	 * @param setUpInput
	 * @return
	 */
	int endGame(int setUpInput) throws RemoteException;

}
