package quiz;

import java.rmi.RemoteException;
import java.io.Serializable;

public interface QuizController extends Serializable {

	/**
	 * Begins chosen quiz. It calls the private method 
	 * makeListOfQAndALists(), which calls private method isRepeatedQuestion(QueAndAns,int) to prevent 
	 * repeating the same question. It calls two more private methods: keepScore(String,int,int) and 
	 * shuffleAnswers(int).
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

	/**
	 * 
	 * @param answerToSubmit
	 * @throws RemoteException
	 */
	void answerQuestions(int answerToSubmit) throws RemoteException;

}
