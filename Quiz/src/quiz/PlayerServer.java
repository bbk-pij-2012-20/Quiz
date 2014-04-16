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

	private static final long serialVersionUID = 7708290464329235363L;
	private QuizControllerImpl quizController = new QuizControllerImpl();
	private String playerView = "";
	
	protected PlayerServer() throws RemoteException {}

	@Override
	public String getGameList() throws RemoteException {
		
		return "Choose one from the following three quiz options:\n"
				+ "1. 6 questions \n"
				+ "2. 8 questions \n"
				+ "3. 10 questions";
		
	}
	
	@Override
	public void processInput(int userInput) throws RemoteException {
					
		quizController.playQuiz(userInput);
		playerView = quizController.getView();
	
	}
	
	public String getPlayerView() {
		
		return playerView;
		
	}
	
	@Override
	public boolean isCurrentQuizActive() throws RemoteException{
		
		return quizController.isCurrentQuizActive();
		
	}

}
