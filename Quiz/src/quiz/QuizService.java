package quiz;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.io.Serializable;

public interface QuizService extends Remote {

	/**
	 * Gives the setUpClient options on design of the quiz.
	 * 
	 * @param numberOfQuestions             number of questions per quiz
	 * @param numberOfAnswersPerQuestions   number of answers per question for multiple choice (includes 1 correct answer)
	 * @throws RemoteException              in case anything goes wrong with network connectivity.
	 */
	void setUpQuiz(int numberOfQuestions, int numberOfAnswersPerQuestions) throws RemoteException;
	
	/**
	 * Gives the playerClient option to begin a new quiz, with parameters set up by setUpClient.
	 * 
	 * @throws RemoteException              in case anything goes wrong with network connectivity.
	 */
	void playQuiz() throws RemoteException;

}
