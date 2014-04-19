package quiz.server;

import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.io.Serializable;

import quiz.controller.QuizControllerImpl;
import quiz.interfaces.QuizController;
import quiz.interfaces.QuizFactory;
import quiz.model.QuizFactoryImpl;
import quiz.view.QuizView;
import quiz.view.QuizViewImpl;

public class SetUpServer extends UnicastRemoteObject implements SetUpService, Serializable {

//	private static final long serialVersionUID = -6033300511237555304L;
	private QuizControllerImpl quizController;
	private QuizFactory quizFactory;
	private QuizView setUpView;

	protected SetUpServer() throws RemoteException {
		
		quizController = new QuizControllerImpl();
		quizFactory = new QuizFactoryImpl();
		setUpView = new QuizViewImpl();

	}

	@Override
	public synchronized void processInput(String input) throws RemoteException {
		
		int idInput = 0;
		
		if (input.charAt(0) == 'y') {
			System.out.println("LINE 35 processInput().....");
			setUpView.updateSetUpView(quizFactory.make3Quizzes().toString());
		
		} else {
			
			try {
		
				idInput = Integer.parseInt(input);
				
			} catch (NumberFormatException e) {
				
				System.out.println("..was that meant to be a number? (Try again)");
				e.printStackTrace();
			
			}
			
			quizController.stopQuiz(idInput);
		
		}

		System.out.println("LINE 55 processInput().....");
//		setUpView = "goodbye";	

	}
	
	@Override
	public QuizControllerImpl getQuizController() throws RemoteException {
		
		return quizController;
		
	}
	
	@Override
	public String getSetUpView() throws RemoteException{
		
		return setUpView.toString();
		
	}

}
