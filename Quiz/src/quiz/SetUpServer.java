package quiz;

import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.util.Map;
import java.io.Serializable;

public class SetUpServer extends UnicastRemoteObject implements SetUpService {

	private static final long serialVersionUID = -6033300511237555304L;
	private QuizControllerImpl quizController = new QuizControllerImpl();
	private QuizFactoryImpl quizFactory = new QuizFactoryImpl();

	protected SetUpServer() throws RemoteException {}

	@Override
	public void processInput(String input) throws RemoteException {
		
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
				System.out.println(quizController.getView());
				
			}		
		
		}
	
	}

}
