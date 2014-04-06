package quiz;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.io.Serializable;

public class QuizServer extends UnicastRemoteObject implements QuizService {

	private int score = 0;
	private int totalNoOfQuestions; 
	private int noOfAnswersPerQuestion;
	private QueAndAns[] listOfQAndALists;

	public QuizServer() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setUpQuiz(int numberOfQuestions, int numberOfAnswersPeryumchastions) throws RemoteException {
		// TODO Auto-generated method stub

	}

	@Override
	public void playQuiz() throws RemoteException {
		// TODO Auto-generated method stub

	}
	
	/**
	 * Prompts playerClient to start game. Accepts any input beginning with y or n. 
	 * Input y resumes with playQuiz(), otherwise terminates program.
	 * 
	 * @return    true if user enters any word beginning with y.
	 */
	private boolean startGame() {
		//TODO
		return true;
	}
	
	/**
	 * Fills the listOfQueAndAnsLists with que_AndLists with as many as the setUpClient has specified. 
	 * Assures no repeated questions by calling isRepeatedQuestion(QueAndAns, int). 
	 */
	private void makeListOfQAndALists() {
		//TODO
	}
	
	
	/**
	 * @param queAndAnsObj            a queAndAnsObj, created and passed from makeListOfQAndALists()
	 * @param listOfQAndAListsIndex   an int that is the position of the current queAndAnsObj created and passed from makeListOfQAndALists()  
	 * @return                        true if the question was already created and stored at another position in the listOfQAndALists
	 */
	private boolean isRepeatedQuestion(QueAndAns queAndAnsObj, int listOfQAndAListsIndex) {
		
		//TODO
		return true;
	}
	
	/**
	 * Randomly finds a new position for the correct answer (originally at position 2 in the que_AnsList), 
	 * just prior to be output to playerClient. It is called from playQuiz() only. 
	 *  
	 * @param questionNo   an int position of the current que_AnsList held in the listOfQAndALists 
	 * @return             an int new position of the correct answer in the current qua_AndAnsList.
	 */
	private int shuffleAnswers(int questionNo) {
		//TODO
		
		return 0; 
	}
	
	/**
	 * Increments the score for every correct answer given by the playerClient. 
	 * (Accepts both the answer number and the actual answer value).
	 * 
	 * @param userInputStr            the input from the playerClient
	 * @param correctAnswer           the correct answer value
	 * @param newCorrectAnswerIndex   the position of the correct answer after calling shuffleAnswers()
	 */
	private void keepScore(String userInputStr, int correctAnswer, int newCorrectAnswerIndex) {
	
	}
}
