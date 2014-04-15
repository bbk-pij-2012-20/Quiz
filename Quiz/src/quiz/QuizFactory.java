package quiz;

import java.rmi.RemoteException;
import java.util.Map;

public interface QuizFactory {

	/**
	 * Generates three quizzes, one with 6 questions, one with 8 questions
	 * and one with 10 questions. Calls generateAndStoreId() 
	 * 
	 * @return HashMap              map of quizIds Integers and quizzes 
	 *                              (an array with 6,8, or 10 questions, 
	 *                              with 4 answers each).
	 * @throws RemoteException
	 */
	Map<Integer, QueAndAns[]> make3Quizzes() throws RemoteException;

	/**
	 * Getter for quiz
	 * 
	 * @return quiz                 a list of queAndAns lists 
	 * @throws RemoteException
	 */
	QueAndAns[] getQuiz() throws RemoteException;

	/**
	 * Setter for quiz
	 * 
	 * @param quiz                 a list of queAndAns lists 
	 * @throws RemoteException
	 */
	void setQuiz(QueAndAns[] quiz) throws RemoteException;
	
	/**
	 * Getter for constant value noOfAnswersPerQuestion.
	 * 
	 * @return noOfAnswersPerQuestion   answers per question int (always 4)
	 * @throws RemoteException
	 */
	int getNoOfAnswersPerQuestion() throws RemoteException;

	/**
	 * Getter for noOfQuestionsPerQuiz 
	 * 
	 * @return noOfQuestionsPerQuiz     questions per quiz int (6,8 or 10)
	 * @throws RemoteException
	 */
	int getNoOfQuestionsPerQuiz() throws RemoteException;

}
