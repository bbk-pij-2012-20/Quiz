package quiz;

import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.io.Serializable;

public class SetUpServer extends UnicastRemoteObject implements SetUpService {

	private static final long serialVersionUID = -6033300511237555304L;
	private QuizControllerImpl quizController = new QuizControllerImpl();
	private QuizFactoryImpl quizFactory = new QuizFactoryImpl();

	protected SetUpServer() throws RemoteException {}

	@Override
	public boolean isGameOver() throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void processInput(String input) throws RemoteException {
		
		int idInput = 0;
		
		if (input.charAt(0) == 'y') {
			
			make3Quizzes();
		
		} else {
			
			try {
		
				idInput = Integer.parseInt(input);
				
			} catch (NumberFormatException e) {
				
				System.out.println("please enter a number");
				e.printStackTrace();
				
			} finally {
				
				endGame(idInput);
				
			}		
		
		}
	
	}
	
	@Override
	public void make3Quizzes() throws RemoteException {
	
		quizFactory.make3Quizzes();
		
	}

	@Override
	public int endGame(int idOfQuizToStop) throws RemoteException {
		// TODO Auto-generated method stub
		return quizController.getScore();
		
	}

}
