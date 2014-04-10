package quiz;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Random;
import java.io.Serializable;

public class QuizControllerImpl extends UnicastRemoteObject implements QuizController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4592620853082060733L;


	public static void main(String[] args) throws RemoteException {
		
		QuizControllerImpl quizController = new QuizControllerImpl(10,4);
		quizController.playQuiz();
		
	}

	/**
	 * empty constructor must be explicitly written for RMI
	 * @throws RemoteException
	 */
	public QuizControllerImpl() throws RemoteException {}

	public QuizControllerImpl(int totalNoOfQuestions, int noOfAnswersPerQuestion) throws RemoteException {

		this.totalNoOfQuestions = totalNoOfQuestions;
		this.noOfAnswersPerQuestion = noOfAnswersPerQuestion;
	
	}

	@Override
	public void playQuiz() throws RemoteException {
	
		System.out.println("------------------- SHAHIN'S CRAZY NEW QUIZ CHALLENGE -------------------\n"); 		
		System.out.println("                    There are " + totalNoOfQuestions + " questions to answer. "); 
		System.out.println("                    Only one of the " + noOfAnswersPerQuestion + " answers is the correct one. \n");  
		System.out.println("-------------------------------------------------------------------------"); 		
		
		if (startGame()) {
			
			makeListOfQAndALists();
		
		} else {
			
			System.exit(0);
		
		}
		
		int noOfQuestionsAnswered = 0;
		int newCorrectAnswerIndex = 0;
		
		for (int questionNo = 0; questionNo < totalNoOfQuestions; questionNo++) {
			
			System.out.print("\n\nQuestion#");
			System.out.print(questionNo + 1 +": ");
			System.out.println(listOfQAndALists[questionNo].toString());
			System.out.println("\nPick one of the following: ");
		 	newCorrectAnswerIndex = shuffleAnswers(questionNo);

			for (int answerNo = 0; answerNo < noOfAnswersPerQuestion; answerNo++) {
			
				System.out.println(answerNo + 1 + ".  " + listOfQAndALists[questionNo].getQue_AnsList()[answerNo+2]);
				
			}
			
//			String inputConsoleReadLine = System.console().readLine();
			String inputConsoleReadLine = "1";

			noOfQuestionsAnswered += keepScore(inputConsoleReadLine, newCorrectAnswerIndex);
			
		}
		
		if (noOfQuestionsAnswered == totalNoOfQuestions) {
			
			System.out.printf("\nYou answered %d out of %d correctly \n", score, totalNoOfQuestions);
			startGame();
			
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
	

}
