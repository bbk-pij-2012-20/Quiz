package quiz;

import java.rmi.RemoteException;

public interface QuizModel {

	
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
