package quiz;

import java.rmi.RemoteException;
import java.util.Random;

public class QuizModelImpl implements QuizModel {

	private int score = 0;
	private int totalNoOfQuestions; 
	private int noOfAnswersPerQuestion;
	private QueAndAns[] listOfQAndALists;
	private final int ORIGINAL_CORRECT_ANSWER_INDEX = 2;

	/**
	 * empty constructor
	 */
	public QuizModelImpl() {}

	public QuizModelImpl(int totalNoOfQuestions, int noOfAnswersPerQuestion) {
		
		this.totalNoOfQuestions = totalNoOfQuestions;
		this.noOfAnswersPerQuestion = noOfAnswersPerQuestion;

	}

	@Override
	public QueAndAns[] getListOfQAndALists() throws RemoteException{
		
		return listOfQAndALists;
		
	}
	
	@Override
	public void setListOfQAndALists(QueAndAns[] listOfQAndALists) throws RemoteException{
		
		this.listOfQAndALists = listOfQAndALists;
		
	}
	@Override
	public void setUpQuiz(int numberOfQuestions, int numberOfAnswersPerQuestion) throws RemoteException {
		// TODO Auto-generated method stub
	}
	
	/**
	 * Fills the listOfQueAndAnsLists with que_AndLists with as many as the setUpClient has specified. 
	 * Assures no repeated questions by calling isRepeatedQuestion(QueAndAns, int). 
	 * 
	 * (temporarily made public for JUnit test)
	 */
	protected void makeListOfQAndALists() throws RemoteException {
	
		listOfQAndALists = new QueAndAns[totalNoOfQuestions];
		QueAndAnsImpl queAndAnsObj;

		for (int i = 0; i < listOfQAndALists.length; i++) {
		
			queAndAnsObj = new QueAndAnsImpl(noOfAnswersPerQuestion);
			listOfQAndALists[i] = queAndAnsObj;			
				
			if (isRepeatedQuestion(queAndAnsObj, i)) {
			
				i--;
				
			}
				
		}
		
	}
	
	
	/**
	 * @param queAndAnsObj            a queAndAnsObj, created and passed from makeListOfQAndALists()
	 * @param listOfQAndAListsIndex   an int that is the position of the current queAndAnsObj created and passed from makeListOfQAndALists()  
	 * @return                        true if the question was already created and stored at another position in the listOfQAndALists
	 * 
	 * (temporarily made public for JUnit test)  
	 */
	private boolean isRepeatedQuestion(QueAndAnsImpl queAndAnsObj, int listOfQAndAListsIndex) throws RemoteException {
	
		boolean same = false;
		int i = 0;
		
		while (!same && i < listOfQAndAListsIndex) {
			
			if (queAndAnsObj.getQue_AnsList()[0] == listOfQAndALists[i].getQue_AnsList()[0]) {

				if (queAndAnsObj.getQue_AnsList()[1] == listOfQAndALists[i].getQue_AnsList()[1]) {
				
					same = true;
					
				} else {
				
					i++;
					continue;
				
				}
									
			} else {
				
				i++;
				continue;
				
			}
			
		}
		
		return same;

	}
	
	/**
	 * Generates a new position for the correct answer (originally at position 2 in the que_AnsList), 
	 * by random and immediately prior to being output to playerClient UI. It is only called from 
	 * playQuiz(). 
	 *  
	 * @param questionNo   an int position of the current que_AnsList held in the listOfQAndALists 
	 * @return             an int new position of the correct answer in the current que_AndAnsList.
	 * 
	 * (temporarily made public for JUnit test)  
	 */
	protected int shuffleAnswers(int questionNo) throws RemoteException{
		
		Random randomObj = new Random();
		int randomNewCorrectAnswerIndex = ORIGINAL_CORRECT_ANSWER_INDEX + randomObj.nextInt(4);

		int temp = listOfQAndALists[questionNo].getQue_AnsList()[randomNewCorrectAnswerIndex];
		listOfQAndALists[questionNo].getQue_AnsList()[randomNewCorrectAnswerIndex] = listOfQAndALists[questionNo].getQue_AnsList()[ORIGINAL_CORRECT_ANSWER_INDEX];
		listOfQAndALists[questionNo].getQue_AnsList()[ORIGINAL_CORRECT_ANSWER_INDEX] = temp;
	
		return randomNewCorrectAnswerIndex;
		
	}
	
	/**
	 * Increments the score for every correct answer given by the playerClient. 
	 * (Only accepts the answer number from user, not the actual answer value).
	 * 
	 * @param userInputStr            the input from the playerClient
	 * @param newCorrectAnswerIndex   the position of the correct answer after calling shuffleAnswers()
	 * 
	 * (temporarily made public for JUnit test)  
	 */ 
	protected int keepScore(String userInputStr, int newCorrectAnswerIndex) throws RemoteException {
	
		int userInput = 0;
	
		try {

			userInput = Integer.parseInt(userInputStr);
				
		} catch (NumberFormatException e) {
			
			System.out.println("A numeric input was required");
		
		}
		
		
		if (userInput == newCorrectAnswerIndex - 1) {
			
				score++; 
		
		}
		
		int numberOfQuestionsAnswered = 1;
		
		return numberOfQuestionsAnswered;
		
	}
	
	/**
	 * getter for score (primarily for JUnit)
	 * 
	 * @return int   the score
	 * @throws RemoteException
	 */
	public int getScore() throws RemoteException {
		
		return score;
		
	}

	public int getTotalNoOfQuestions() {
	
		return totalNoOfQuestions; 

	}

	public int getNoOfAnswersPerQuestion() {
		
		return noOfAnswersPerQuestion;

	}
	
}
