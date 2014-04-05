package quiz;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.io.Serializable;

public interface QuizService extends Remote {

	/**
	 * Gives the setUpClient options on design of the quiz.
	 * @param numberOfQuestions
	 * @param numberOfAnswersPerQuestions
	 * @throws RemoteException
	 */
	void setUpQuiz(int numberOfQuestions, int numberOfAnswersPerQuestions) throws RemoteException;
	
	/**
	 * Gives the playerClient to play the quiz as most recently set up by setUpClient.
	 * @throws RemoteException
	 */
	void playQuiz() throws RemoteException;

}
