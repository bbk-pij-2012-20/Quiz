package quiz;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Random;
import java.io.Serializable;

public class QuizServer extends UnicastRemoteObject implements QuizService {

	private int score = 0;
	private int totalNoOfQuestions; 
	private int noOfAnswersPerQuestion;
	private QueAndAns[] listOfQAndALists;

	/**
	 * getter for score (primarily for JUnit)
	 * 
	 * @return int   the score
	 * @throws RemoteException
	 */
	public int getScore() throws RemoteException {
		
		return score;
		
	}
	/**
	 * empty constructor must be explicitly written for RMI
	 * @throws RemoteException
	 */
	public QuizServer() throws RemoteException {}

	public QuizServer(int totalNoOfQuestions, int noOfAnswersPerQuestion) throws RemoteException {

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

	@Override
	public void playQuiz() throws RemoteException {
	
		System.out.println("------------------- NEW GAME -------------------\n"); 		
		System.out.println("There are " + totalNoOfQuestions + " questions to answer. "); 
		System.out.println("Select one of the " + noOfAnswersPerQuestion + " answers\n");  
		System.out.println("------------------------------------------------"); 		
		
		if (startGame()) {
			
			makeListOfQAndALists();
		
		} else {
			
			System.exit(0);
		
		}
		
		final int ORIGINAL_CORRECT_ANSWER_INDEX = 2;
		int correctAnswer = 0;
		int answerCounter = 0;
		int userInput = 0;
		int newCorrectAnswerIndex = 0;
		
		for (int questionNo = 0; questionNo < totalNoOfQuestions; questionNo++) {

			System.out.println("Question#" + questionNo + 1 +":");
			System.out.println(listOfQAndALists[questionNo].toString());
			correctAnswer = listOfQAndALists[questionNo].getQue_AnsList()[ORIGINAL_CORRECT_ANSWER_INDEX];
			System.out.println("Pick one: ");
		 	
		 	newCorrectAnswerIndex = shuffleAnswers(questionNo);

			for (int answerNo = 0; answerNo < noOfAnswersPerQuestion; answerNo++) {
			
				System.out.println(answerNo + 1 + ".  " + listOfQAndALists[questionNo].getQue_AnsList()[answerNo]);
				
			}
			
			keepScore(System.console().readLine(), correctAnswer, newCorrectAnswerIndex);
		
		}
			
	}
	
	/**
	 * Prompts playerClient to start game. Accepts any input beginning with y or n. 
	 * Input y resumes with playQuiz(), otherwise terminates program.
	 * 
	 * @return    true if user enters any word beginning with y.
	 * 
	 * (temporarily made public for JUnit test)
	 */
	public boolean startGame() {
		
		boolean start = false;
		
		System.out.println("Do you want to set up a new quiz game? (y/n)");
		char input = System.console().readLine().trim().toLowerCase().charAt(0);
		
		if (input == 'y') {
		
			start = true;
		
		} else if (input == 'n') {
		
			System.out.println("goodbye");
			start = false;	
		
		} else { 
		
			System.out.println("that was neither y or n. Try again");
			startGame();		
		
		}
		
		return start;

	}
	
	/**
	 * Fills the listOfQueAndAnsLists with que_AndLists with as many as the setUpClient has specified. 
	 * Assures no repeated questions by calling isRepeatedQuestion(QueAndAns, int). 
	 * 
	 * (temporarily made public for JUnit test)
	 */
	public void makeListOfQAndALists() {
	
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
	public boolean isRepeatedQuestion(QueAndAnsImpl queAndAnsObj, int listOfQAndAListsIndex) {
	
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
	public int shuffleAnswers(int questionNo) {
		
		final int ORIGINAL_CORRECT_ANSWER_INDEX = 2;
		Random randomObj = new Random();
		int randomNewCorrectAnswerIndex = ORIGINAL_CORRECT_ANSWER_INDEX + randomObj.nextInt(4);

		int temp = listOfQAndALists[questionNo].getQue_AnsList()[randomNewCorrectAnswerIndex];
		listOfQAndALists[questionNo].getQue_AnsList()[randomNewCorrectAnswerIndex] = listOfQAndALists[questionNo].getQue_AnsList()[ORIGINAL_CORRECT_ANSWER_INDEX];
		listOfQAndALists[questionNo].getQue_AnsList()[ORIGINAL_CORRECT_ANSWER_INDEX] = temp;
	
		return randomNewCorrectAnswerIndex;
		
	}
	
	/**
	 * Increments the score for every correct answer given by the playerClient. 
	 * (Accepts both the answer number and the actual answer value).
	 * 
	 * @param userInputStr            the input from the playerClient
	 * @param correctAnswer           the correct answer value
	 * @param newCorrectAnswerIndex   the position of the correct answer after calling shuffleAnswers()
	 * 
	 * (temporarily made public for JUnit test)  
	 */
	public void keepScore(String userInputStr, int correctAnswer, int newCorrectAnswerIndex) {
	
		int userInput = 0;
		
		try {

			userInput = Integer.parseInt(userInputStr);
				
		} catch (NumberFormatException e) {
			
			System.out.println("A numeric input was required");
		
		}
		
		if (userInput == correctAnswer || userInput == newCorrectAnswerIndex) {
			
				score++; 
		
		}
		
	}
}
