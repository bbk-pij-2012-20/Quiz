package quiz;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.io.Serializable;

public class QuizControllerImpl extends UnicastRemoteObject implements QuizController {

	private boolean gameIsOver = true;
	private String view = "";
	private String userInput = "";
	private int quizChoice = 0;
	
	private int score = 0;
	private int totalNoOfQuestions; 
	private final int NO_OF_ANSWERS_PER_QUESTION = 4;
	private QueAndAns[] listOfQAndALists;
	private final int ORIGINAL_CORRECT_ANSWER_INDEX = 2;
	private Map<Integer,QueAndAns[]> quizAndIds;

	private static final long serialVersionUID = 4592620853082060733L;

	/**
	 * empty constructor must be explicitly written for RMI
	 * @throws RemoteException
	 */
	public QuizControllerImpl() throws RemoteException {}

	public QuizControllerImpl(int totalNoOfQuestions) throws RemoteException {

		this.totalNoOfQuestions = totalNoOfQuestions;
		
	}
	
	@Override
	public void setUserInput(String userInput) throws RemoteException {
	
		this.userInput = userInput;
		
	}
	
	@Override
	public void processPlayerInput() {
		
		boolean quizChoiceMade = false;
		int userInput = 0;

		try {
			
			userInput = Integer.parseInt(this.userInput);
			
			while (!quizChoiceMade) {
				
				quizChoice = userInput;
				quizChoiceMade = true;
			
			}
			
		} catch (NumberFormatException e) {
			
			System.out.println("the user input was not a number");
			e.printStackTrace();
			
		}
		
	}

	@Override
	public void updateView(String view) throws RemoteException {
	
		this.view += view;
		
	}
	
	@Override
	public String getView() throws RemoteException {
	
		return view;
	
	}
	
	@Override
	public String getUserInput() throws RemoteException {
	
		return userInput;
	
	}

	@Override
	public QueAndAns[] getListOfQAndALists() throws RemoteException{
		
		return listOfQAndALists;
		
	}
	
	@Override
	public void setListOfQAndALists(QueAndAns[] listOfQAndALists) throws RemoteException{
		
		this.listOfQAndALists = listOfQAndALists;
		
	}
	
	/**
	 * This method is for setUpClient to call
	 * 
	 * @throws RemoteException
	 */
	protected void makeQuizOfPlayerChoice() throws RemoteException {
		
		switch (quizChoice) {
			
			case 1: makeListOfQAndALists(2); break;
			case 2: makeListOfQAndALists(5); break;
			case 3: makeListOfQAndALists(10); break;
			default: throw new IllegalArgumentException("the choice is not 1,2,or 3");
			
		}
		
	}
	
	/**
	 * Fills the listOfQueAndAnsLists with que_AndLists with as many as the setUpClient has specified. 
	 * Assures no repeated questions by calling isRepeatedQuestion(QueAndAns, int). 
	 * 
	 * (temporarily made public for JUnit test)
	 */
	private void makeListOfQAndALists(int noOfQuestions) throws RemoteException {
		
		this.totalNoOfQuestions = noOfQuestions;
		listOfQAndALists = new QueAndAns[totalNoOfQuestions];
		QueAndAnsImpl queAndAnsObj;

		for (int i = 0; i < listOfQAndALists.length; i++) {
		
			queAndAnsObj = new QueAndAnsImpl(NO_OF_ANSWERS_PER_QUESTION);
			listOfQAndALists[i] = queAndAnsObj;			
				
			if (isRepeatedQuestion(queAndAnsObj, i)) {
			
				i--;
				
			}
				
		}
		
		generateAndStoreId(listOfQAndALists);
		
	}

	private void generateAndStoreId(QueAndAns[] listOfQAndALists) {
		
		int id = 0;
		quizAndIds = new HashMap<>();
		Random randomObj = new Random();
		
		do {
			
			id = randomObj.nextInt(10000)+1;
		
		} while (quizAndIds.containsKey(id));

		quizAndIds.put(id,listOfQAndALists);

	}
	
	@Override
	public void playQuiz() throws RemoteException {
	
		/*		System.out.println("------------------- NEW GAME -------------------\n"); 		
		System.out.println("There are " + totalNoOfQuestions + " questions to answer. "); 
		System.out.println("Select one of the " + noOfAnswersPerQuestion + " answers\n");  
		System.out.println("------------------------------------------------"); 		
*/

		updateView("------------------- NEW GAME -------------------\n\nThere are " 
		+ totalNoOfQuestions + " questions to answer. \nSelect one of the " 
		+ NO_OF_ANSWERS_PER_QUESTION + " answers\n\n ------------------------------------------------\n");
		
		if (startGame()) {
			
			makeListOfQAndALists();
		
		} else {
			
			System.exit(0);
		
		}
		
		int noOfQuestionsAnswered = 0;
		int newCorrectAnswerIndex = 0;
		
		for (int questionNo = 0; questionNo < getTotalNoOfQuestions(); questionNo++) {
			
			/*			System.out.print("Question#");
			System.out.println(questionNo + 1 +":");
			System.out.println(listOfQAndALists[questionNo].toString());
			System.out.println("Pick one: ");
*/
			updateView("Question#");
			updateView(questionNo + 1 +":\n");
			updateView(listOfQAndALists[questionNo].toString()+"\n");
			updateView("Pick one: \n");

			newCorrectAnswerIndex = shuffleAnswers(questionNo);

			for (int answerNo = 0; answerNo < getNoOfAnswersPerQuestion(); answerNo++) {
			
//				System.out.println(answerNo + 1 + ".  " + listOfQAndALists[questionNo].getQue_AnsList()[answerNo+2]);
				updateView(answerNo + 1 + ".  " + listOfQAndALists[questionNo].getQue_AnsList()[answerNo+2]+"\n");
				
			}
			
//			String inputConsoleReadLine = System.console().readLine();
//			String inputConsoleReadLine = "1";
			String inputConsoleReadLine = getUserInput();

			noOfQuestionsAnswered += keepScore(inputConsoleReadLine, newCorrectAnswerIndex);
			
		}
		
		if (noOfQuestionsAnswered == getTotalNoOfQuestions()) {
			
//			System.out.printf("You answered %d out of %d correctly \n", score, totalNoOfQuestions);
			updateView("You answered " + score + " out of "+ totalNoOfQuestions + "correctly \n");

			startGame();
			
		}
					
	}
	
	public void endGamePrematurely(int Id){
		
		
	}
	
	/**
	 * Prompts playerClient to start game. Accepts any input beginning with y or n. 
	 * Input y resumes with playQuiz(), otherwise terminates program.
	 * 
	 * @return    true if user enters any word beginning with y.
	 * 
	 * (temporarily made public for JUnit test)
	 */
	private boolean startGame() throws RemoteException {
		
		boolean start = false;
		
		System.out.println("\nSo, do you wanna play (again) or not? (y/n)");
//		String inputConsoleReadLine = System.console().readLine();
		String inputConsoleReadLine = "yes";
		char input = inputConsoleReadLine.trim().toLowerCase().charAt(0);
		
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
	
	@Override
	public boolean getGameIsOverStatus() throws RemoteException{
		
		return gameIsOver;
		
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
	private int shuffleAnswers(int questionNo) throws RemoteException{
		
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
	private int keepScore(String userInputStr, int newCorrectAnswerIndex) throws RemoteException {
	
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
	@Override
	public int getScore() throws RemoteException {
		
		return score;
		
	}
	
	@Override
	public int getTotalNoOfQuestions() throws RemoteException {
	
		return totalNoOfQuestions; 

	}

	@Override
	public int getNoOfAnswersPerQuestion() throws RemoteException {
		
		return NO_OF_ANSWERS_PER_QUESTION;

	}

	@Override
	public char[] updateView(char input) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setUpQuiz(int numberOfQuestions, int numberOfAnswersPerQuestion)
			throws RemoteException {
		// TODO Auto-generated method stub
		
	}
	
}