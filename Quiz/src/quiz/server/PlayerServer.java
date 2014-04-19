package quiz.server;

import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.io.Serializable;

import quiz.client.SetUpClient;
import quiz.interfaces.QuizController;

/** 
 * PlayerServer generates a view of the UI for a player on server side. 
 * It is called directly by the PlayerClient in order for the client to 
 * see the UI view on the client side.
 */
public class PlayerServer extends UnicastRemoteObject implements PlayerService, Serializable {

	private static final long serialVersionUID = 7708290464329235363L;
	private QuizController quizController;
	private String playerView = "";
	private SetUpServer setUpServer = new SetUpServer();
	
	protected PlayerServer() throws RemoteException {
		
		try {
					
			if (setUpServer.getQuizController() == null) {
				
				System.out.println("no quizzes made yet");
				throw new NullPointerException();
				
			} else {
				
				this.quizController = setUpServer.getQuizController();

			}
			
			if (setUpServer.getSetUpView() == null) {
				
				System.out.println("no quizzes or setup views made yet");
				throw new NullPointerException();
				
			} else {
				
				playerView = setUpServer.getSetUpView();
				
			}
			
		} catch (NullPointerException e) {
			
			e.printStackTrace();
			
		} catch (Exception e) {
			
			System.out.println("an exception other than NullPointerException thrown");
			e.printStackTrace();
			
		}
	
	}

	@Override
	public String getGameList() throws RemoteException {
		
		return "Choose one from the following three quiz options:\n"
				+ "1. 6 questions \n"
				+ "2. 8 questions \n"
				+ "3. 10 questions";
		
	}
	
	@Override
	public void processInput(int userInput) throws RemoteException {
					
		playerView = setUpServer.getQuizController().playQuiz(userInput).toString();
	
	}
	
	public String getPlayerView() throws RemoteException {
		
		return playerView;
		
	}
	
	@Override
	public boolean isCurrentQuizActive() throws RemoteException{
		
		return quizController.isCurrentQuizActive();
		
	}

}
