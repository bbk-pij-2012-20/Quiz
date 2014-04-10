package quiz;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.io.Serializable;

public interface QuizController extends Remote {

	/**
	 * Gives the setUpClient options on design of the quiz in terms of 
	 * number of questions and number of answers per question.
	 * 
	 * @param numberOfQuestions             number of questions per quiz
	 * @param numberOfAnswersPerQuestions   number of answers per question for multiple choice (includes 1 correct answer)
	 * @throws RemoteException              in case anything goes wrong with network connectivity.
	 */
	void setUpQuiz(int numberOfQuestions, int numberOfAnswersPerQuestion) throws RemoteException;
	
	/**
	 * Gives the playerClient option to begin a new quiz, with parameters set up by setUpClient. It calls 
	 * the private method startGame() to prompt the user to start. It calls the private method 
	 * makeListOfQAndALists(), which calls private method isRepeatedQuestion(QueAndAns,int) to prevent 
	 * repeating the same question. It calls two more private methods: keepScore(String,int,int) and 
	 * shuffleAnswers(int).
	 * 
	 * @throws RemoteException    throws RemoteException if anything goes wrong with network connectivity.
	 */
	void playQuiz() throws RemoteException;

	/**
	 * getter for listOfQAndALists (primarily for JUnit)
	 * 
	 * @return an array of type QueAndAns 
	 * @throws RemoteException
	 */
	QueAndAns[] getListOfQAndALists() throws RemoteException;
	

	/**
	 * setter for listOfQAndALists (primarily for JUnit)
	 * 
	 * @param listOfQAndALists   an array of type QueAndAns
	 * @throws RemoteException
	 */
	void setListOfQAndALists(QueAndAns[] listOfQAndALists) throws RemoteException;

}
