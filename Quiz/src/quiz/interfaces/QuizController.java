package quiz.interfaces;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

import quiz.view.QuizView;

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
	QuizView playQuiz(int userInput) throws RemoteException;

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
	void addToQuizList(Quiz newQuiz);

	/**
	 * 
	 * @return
	 */
	boolean isCurrentQuizActive();

	/**
	 * 
	 * @return
	 */
	List<Quiz> getQuizList();

}
