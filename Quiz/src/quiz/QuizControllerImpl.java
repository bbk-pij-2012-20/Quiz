package quiz;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.io.Serializable;

public class QuizControllerImpl extends UnicastRemoteObject implements QuizController {

	private boolean gameIsOver = false;
	private String view = "";
	private String userInput = "";
	private int quizChoice = 0;
	private QuizFactory quizFactory = new QuizFactoryImpl();
	private final int ORIGINAL_CORRECT_ANSWER_INDEX = 2;
	private int score = 0;
	private static final long serialVersionUID = 4592620853082060733L;
	private Map<Integer,QueAndAns[]> quizAndIds = new HashMap<>();
	
	/**
	 * Empty constructor must be explicitly written for RMI to work.
	 * @throws RemoteException  (in case anything goes wrong with network connectivity)
	 */
	public QuizControllerImpl() throws RemoteException {}
	
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
	public void playQuiz() throws RemoteException {
	
/*		System.out.println("------------------- NEW GAME -------------------\n"); 		
		System.out.println("There are " + totalNoOfQuestions + " questions to answer. "); 
		System.out.println("Select one of the " + noOfAnswersPerQuestion + " answers\n");  
		System.out.println("------------------------------------------------"); 		
*/

		updateView("------------------- NEW GAME -------------------\n\nThere are " 
		+ quizFactory.getNoOfQuestionsPerQuiz() + " questions to answer. \nSelect one of the " 
		+ quizFactory.getNoOfAnswersPerQuestion() + " answers\n\n ------------------------------------------------\n");
		
		if (startGame()) {
			
			quizAndIds = quizFactory.make3Quizzes();
		
		} else {
			
			System.exit(0);
		
		}
		
		int noOfQuestionsAnswered = 0;
		int newCorrectAnswerIndex = 0;	
		int questionNo = 0;
	
		do {
			
			updateView("Question#");
			updateView(questionNo + 1 +":\n");
			updateView(quizFactory.getQuiz()[questionNo].toString()+"\n");
			updateView("Pick one: \n");
			
			newCorrectAnswerIndex = shuffleAnswers(questionNo);
			
			for (int answerNo = 0; answerNo < quizFactory.getNoOfAnswersPerQuestion(); answerNo++) {
				
//				System.out.println(answerNo + 1 + ".  " + quiz[questionNo].getQue_AnsList()[answerNo+2]);
				updateView(answerNo + 1 + ".  " + quizFactory.getQuiz()[questionNo].getQue_AnsList()[answerNo+2]+"\n");
				
			}

			String inputConsoleReadLine = getUserInput();
			noOfQuestionsAnswered += keepScore(inputConsoleReadLine, newCorrectAnswerIndex);

			questionNo++;
			
		} while (!gameIsOver && questionNo < quizFactory.getNoOfQuestionsPerQuiz());
		
//		for (int questionNo = 0; questionNo < quizFactory.getNoOfQuestionsPerQuiz(); questionNo++) {
			
/*			System.out.print("Question#");
			System.out.println(questionNo + 1 +":");
			System.out.println(quiz[questionNo].toString());
			System.out.println("Pick one: ");
*/
//			updateView("Question#");
//			updateView(questionNo + 1 +":\n");
//			updateView(quizFactory.getQuiz()[questionNo].toString()+"\n");
//			updateView("Pick one: \n");

//			newCorrectAnswerIndex = shuffleAnswers(questionNo);

//			for (int answerNo = 0; answerNo < quizFactory.getNoOfAnswersPerQuestion(); answerNo++) {
			
//				System.out.println(answerNo + 1 + ".  " + quiz[questionNo].getQue_AnsList()[answerNo+2]);
//				updateView(answerNo + 1 + ".  " + quizFactory.getquiz()[questionNo].getQue_AnsList()[answerNo+2]+"\n");
				
//			}
			
//			String inputConsoleReadLine = System.console().readLine();
//			String inputConsoleReadLine = "1";
			String inputConsoleReadLine = getUserInput();

			noOfQuestionsAnswered += keepScore(inputConsoleReadLine, newCorrectAnswerIndex);
			
//		}
		
		if (noOfQuestionsAnswered == quizFactory.getNoOfQuestionsPerQuiz()) {
			
//			System.out.printf("You answered %d out of %d correctly \n", score, totalNoOfQuestions);
			updateView("You answered " + score + " out of "+ quizFactory.getNoOfQuestionsPerQuiz() + "correctly \n");

			startGame();
			
		}
					
	}
	
	public synchronized void endGamePrematurely(Integer id) {
		
		//TODO
		
	}
	
	/**
	 * Prompts playerClient to start game. Accepts any input beginning with y or n. 
	 * Input y resumes with playQuiz(), otherwise terminates program.
	 * 
	 * @return    true if user enters any word beginning with y.
	 * @throws RemoteException (in case anything goes wrong with network connectivity)
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

	/**
	 * Generates a new position for the correct answer (originally at position 2 in the que_AnsList), 
	 * by random and immediately prior to being output to playerClient UI. It is only called from 
	 * playQuiz(). 
	 *  
	 * @param questionNo   an int position of the current que_AnsList held in the listOfQAndALists 
	 * @return             an int new position of the correct answer in the current que_AndAnsList.
	 * @throws RemoteException (in case anything goes wrong with network connectivity)
	 * (temporarily made public for JUnit test)  
	 */
	private int shuffleAnswers(int questionNo) throws RemoteException{
		
		Random randomObj = new Random();
		int randomNewCorrectAnswerIndex = ORIGINAL_CORRECT_ANSWER_INDEX + randomObj.nextInt(4);

		int temp = quizFactory.getQuiz()[questionNo].getQue_AnsList()[randomNewCorrectAnswerIndex];
		quizFactory.getQuiz()[questionNo].getQue_AnsList()[randomNewCorrectAnswerIndex] = quizFactory.getQuiz()[questionNo].getQue_AnsList()[ORIGINAL_CORRECT_ANSWER_INDEX];
		quizFactory.getQuiz()[questionNo].getQue_AnsList()[ORIGINAL_CORRECT_ANSWER_INDEX] = temp;
	
		return randomNewCorrectAnswerIndex;
		
	}
	
	/**
	 * Increments the score for every correct answer given by the playerClient. 
	 * (Only accepts the answer number from user, not the actual answer value).
	 * 
	 * @param userInputStr            the input from the playerClient
	 * @param newCorrectAnswerIndex   the position of the correct answer after calling shuffleAnswers()
	 * @throws RemoteException (in case anything goes wrong with network connectivity)
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
	 * @throws RemoteException (in case anything goes wrong with network connectivity)
	 */
	@Override
	public int getScore(int idOf) throws RemoteException {
		
		return score;
		
	}


	public synchronized int endGame(int idInput) {
		
		return ; 
		
	}

	@Override
	public void setUserInput(String userInput) throws RemoteException {
	
		this.userInput = userInput;
		
	}

	@Override
	public boolean getGameIsOverStatus() throws RemoteException{
		
		return gameIsOver;
		
	}
	
}