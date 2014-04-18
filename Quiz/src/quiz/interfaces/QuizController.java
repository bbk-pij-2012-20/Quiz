package quiz.interfaces;

import java.rmi.RemoteException;
import java.io.Serializable;

public interface QuizController extends Serializable {

	/**
	 * Has a double use: first processes the player quiz selection
	 * to activate the chosen quiz; all subsequent inputs are 
	 * treated as answers to questions. They are processed by passing
	 * the inputs to private method answerQuestions(int) as the player's 
	 * chosen answer to each question one at a time. 
	 * 
	 * @throws RemoteException    (in case anything goes wrong with network connectivity)
	 */
	void playQuiz(int userInput) throws RemoteException;
	
	/**
	 * 
	 * @param view
	 * @throws RemoteException  (in case anything goes wrong with network connectivity)
	 */
	void updateView(String view) throws RemoteException;

	/**
	 * 
	 * @return
	 * @throws RemoteException  (in case anything goes wrong with network connectivity)
	 */
	String getView() throws RemoteException;

	/**
	 * getter for score (primarily for JUnit)
	 * 
	 * @return String   a  string representation of the score out of how many attempted 
	 * @throws RemoteException (in case anything goes wrong with network connectivity)
	 */
	String getScoreOutOf(int quizId) throws RemoteException;

	/**
	 * Stops quiz with corresponding id#, and returns the score to the user.
	 * 
	 * @param quizId   id# int of quiz to be terminated
	 * @return score   score int of quiz acquired whenever a game ends
	 * @throws RemoteException (in case anything goes wrong with network connectivity)
	 */
	void stopQuiz(int quizId) throws RemoteException;

	/**
	 * Determines whether or not a quiz with a given id# exists in the list of
	 * quizzes held in QuizController.
	 * 
	 * @param quizId
	 * @return
	 */
	boolean containsQuizWithId(int quizId);

	/**
	 * 
	 * @param newQuiz
	 */
	void addNewQuiz(Quiz newQuiz);

}
