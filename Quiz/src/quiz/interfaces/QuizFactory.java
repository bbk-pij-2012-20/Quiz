package quiz.interfaces;

import java.rmi.RemoteException;

public interface QuizFactory {

	/**
	 * Generates three quizzes, with 6,8 and 10 questions. 
	 * Calls generateAndStoreId() and sends a representation of each quiz to 
	 * QuizController's UI view for setUpClient to access. 
	 * 
	 * @throws RemoteException (in case anything goes wrong with network connectivity)
	 */
	void make3Quizzes() throws RemoteException;
	
}
