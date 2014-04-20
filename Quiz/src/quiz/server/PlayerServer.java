package quiz.server;

import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.awt.List;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

import quiz.client.SetUpClient;
import quiz.interfaces.QuizController;
import quiz.model.QuizImpl;

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
					
		playerView = setUpServer.getQuizController().playQuiz(userInput).toString();
		setUpServer.getQuizController().readInQuizList();
	
	}
	
	public String getPlayerView() throws RemoteException {
		
		return playerView;
		
	}
	
	@Override
	public boolean isCurrentQuizActive() throws RemoteException{
		
		return quizController.isCurrentQuizActive();
		
	}

}
