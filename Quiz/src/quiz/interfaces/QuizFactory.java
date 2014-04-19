package quiz.interfaces;

import java.rmi.RemoteException;

import quiz.view.QuizView;

public interface QuizFactory {

	/**
	 * Generates three quizzes, with 6,8 and 10 questions. 
	 * Calls generateAndStoreId() and returns a representation of each quiz to 
	 * the caller as a view. 
	 * 
	 * @param quizView
	 * @throws RemoteException (in case anything goes wrong with network connectivity)
	 */
	QuizView make3Quizzes() throws RemoteException;
	
}
