package quiz;

import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.io.Serializable;

/** 
 * PlayerServer generates a view of the UI for a player on server side. 
 * It is called directly by the PlayerClient in order for the client to 
 * see the UI view on the client side.
 */
public class PlayerServer extends UnicastRemoteObject implements PlayerService, Serializable {

	private QuizControllerImpl quizController = new QuizControllerImpl();
	private static final long serialVersionUID = 7708290464329235363L;

	protected PlayerServer() throws RemoteException {}

	@Override
	public String getGameList() throws RemoteException {
		
		return "Choose one from the following three quiz options:\n"
				+ "1. 2 questions\n3. 5 questions\n 3. 10 questions";
		
	}

	@Override
	public String updateView(char input) throws RemoteException {
		
		quizController.setUserInput(""+input);
		return quizController.getView();
	
	}

	@Override
	public boolean isGameOver() throws RemoteException {
		
		return quizController.getGameIsOverStatus(); 
		
	}

}
