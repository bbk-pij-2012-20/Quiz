package quiz;

import java.rmi.RemoteException;
import java.util.Map;

public interface QuizFactory {

	/**
	 * Generates three quizzes, with 6,8 and 10 questions. 
	 * Calls generateAndStoreId() to generate a unique Id#
	 * with each quiz in a HashMap which is returned to caller of
	 * make3Quizzes() and stored in QuizController objects.
	 * 
	 * @return HashMap         map of quizIds Integers and quizzes (an array with 
	 *                         6,8, or 10 questions, with 4 answers each).
	 * @throws RemoteException (in case anything goes wrong with network connectivity)
	 */
	Map<Integer, QueAndAns[]> make3Quizzes() throws RemoteException;

	/**
	 * Getter for quiz
	 * 
	 * @return quiz            a list of queAndAns lists 
	 * @throws RemoteException (in case anything goes wrong with network connectivity)
	 */
	QueAndAns[] getQuiz() throws RemoteException;

	/**
	 * Setter for quiz
	 * 
	 * @param quiz             a list of queAndAns lists 
	 * @throws RemoteException (in case anything goes wrong with network connectivity)
	 */
	void setQuiz(QueAndAns[] quiz) throws RemoteException;
	
	/**
	 * Getter for constant value noOfAnswersPerQuestion.
	 * 
	 * @return noOfAnswersPerQuestion   answers per question int (always 4)
	 * @throws RemoteException          (in case anything goes wrong with network connectivity)
	 */
	int getNoOfAnswersPerQuestion() throws RemoteException;

	/**
	 * Getter for noOfQuestionsPerQuiz 
	 * 
	 * @return noOfQuestionsPerQuiz     questions per quiz int (6,8 or 10)
	 * @throws RemoteException          (in case anything goes wrong with network connectivity)
	 */
	int getNoOfQuestionsPerQuiz() throws RemoteException;

}
