package quiz.server;

import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.io.Serializable;

import quiz.controller.QuizControllerImpl;
import quiz.model.QuizFactoryImpl;

public class SetUpServer extends UnicastRemoteObject implements SetUpService, Serializable {

	private static final long serialVersionUID = -6033300511237555304L;
	private QuizControllerImpl quizController = new QuizControllerImpl();
	private QuizFactoryImpl quizFactory = new QuizFactoryImpl();
	private String setUpView = "";

	protected SetUpServer() throws RemoteException {}

	@Override
	public synchronized void processInput(String input) throws RemoteException {
		
		int idInput = 0;
		
		if (input.charAt(0) == 'y') {
			
			quizFactory.make3Quizzes();
		
		} else {
			
			try {
		
				idInput = Integer.parseInt(input);
				
			} catch (NumberFormatException e) {
				
				System.out.println("..was that meant to be a number? (Try again)");
				e.printStackTrace();
				
			} finally {
				
				quizController.stopQuiz(idInput);
				
			}		
		
		}
		
		setUpView = quizController.getView();
			
	}
	
	public String getSetUpView() throws RemoteException {
		
		return setUpView;
		
	}

}
