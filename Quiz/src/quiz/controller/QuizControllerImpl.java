package quiz.controller;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.io.Serializable;

import quiz.interfaces.Quiz;
import quiz.interfaces.QuizController;
import quiz.server.SetUpServer;
import quiz.view.QuizView;
import quiz.view.QuizViewImpl;

/**
 * QuizControllerImpl presents questions from a quiz and scores the answers 
 * given by playerClient. 
 * A quiz and id# pair are stored here in a HashMap.
 * 
 * @author Shahin
 */
public class QuizControllerImpl implements QuizController, Serializable {

	private static final long serialVersionUID = 4592620853082060733L;
	private final int ORIGINAL_CORRECT_ANSWER_INDEX = 2;
	private final int ANSWER_INDEX_START = 2;
	private Quiz currentQuiz = null;
	private List<Quiz> quizList;
	private QuizView quizView;

	/**
	 * Empty constructor must be explicitly written for RMI to work.
	 * @throws RemoteException  (in case anything goes wrong with network connectivity)
	 */
	public QuizControllerImpl() throws RemoteException {
		
		quizList = new ArrayList<>();
		quizView = new QuizViewImpl();
	
	}
	
	@Override
	public QuizView playQuiz(int userInput) throws RemoteException {
	
		int answerToSubmit = 0;
		int quizToActivate = 0;
		
		if (!currentQuiz.getQuizIsActive()) {
		
			quizToActivate = userInput;
			Quiz currentQuiz  = getChosenQuiz(quizToActivate);
			currentQuiz.setQuizIsActive(true);
	
		} else {
			
			answerToSubmit = userInput;
			
		}
		
		startQuizHeader();
		answerQuestions(answerToSubmit);
		
		return quizView;
		
	}

	/**
	 * Opening view header to introduce the quiz.
	 * 
	 * @throws RemoteException
	 */
	private void startQuizHeader() throws RemoteException {
	
		quizView.updatePlayerView("------------------- NEW GAME -------------------\n\nThere are " 
		+ currentQuiz.getNoOfQuestionsPerQuiz() + " questions to answer. \nSelect one of the " 
		+ currentQuiz.getQueAndAns()[0].getNoOfAnswersPerQuestion() + " answers\n\n ------------------------------------------------\n");

	}
	
	
	/**
	 * Presents question and multiple choice answers to the player. 
	 * It takes input from the player via playQuiz(int).
	 * It calls private method shuffleAnswers(int) to re-order 
	 * the correct answer position randomly. It calls private method
	 * keepScore(int,int) to keep score. 
	 * 
	 * @param answerToSubmit    int input from playerClient via playQuiz(int)
	 * @throws RemoteException
	 */
	private void answerQuestions(int answerToSubmit) throws RemoteException {

		int noOfQuestionsAnswered = 0;
		int newCorrectAnswerIndex = 0;	
		int questionNo = 0;
	
		do {
			
			quizView.updatePlayerView("Question#");
			quizView.updatePlayerView(questionNo + 1 +":\n");
			quizView.updatePlayerView(currentQuiz.getQueAndAns()[questionNo].toString()+"\n");
			quizView.updatePlayerView("Pick one of the following answers: \n");
			
			newCorrectAnswerIndex = shuffleAnswers(questionNo);
			
			for (int answerNo = 0; answerNo < currentQuiz.getQueAndAns()[0].getNoOfAnswersPerQuestion(); answerNo++) {
	
				quizView.updatePlayerView(answerNo + 1 + ".  " + currentQuiz.getQueAndAns()[questionNo].getQue_AnsList()[answerNo + ANSWER_INDEX_START] + "\n");
				
			}

			keepScore(answerToSubmit, newCorrectAnswerIndex);
			questionNo++;
			
		} while (currentQuiz.getQuizIsActive() && questionNo < currentQuiz.getNoOfQuestionsPerQuiz());

		if (noOfQuestionsAnswered == currentQuiz.getNoOfQuestionsPerQuiz()) {
			
			quizView.updatePlayerView("You answered " + currentQuiz.getScore() + " out of "+ currentQuiz.getNoOfQuestionsPerQuiz() + "correctly \n");

		}
		
/* original format with two for loops, no do-while loop
 
		for (int questionNum = 0; questionNum < currentQuiz.getNoOfQuestionsPerQuiz(); questionNum++) {
		
			updateView("Question#");
			updateView(questionNo + 1 +":\n");
			updateView(currentQuiz.getQueAndAns()[questionNum].toString()+"\n");
			updateView("Pick one: \n");

			newCorrectAnswerIndex = shuffleAnswers(questionNo);

			for (int answerNo = 0; answerNo < currentQuiz.getQueAndAns()[0].getNoOfAnswersPerQuestion(); answerNo++) {
			
				System.out.println(answerNo + 1 + ".  " + currentQuiz.getQueAndAns()[questionNo].getQue_AnsList()[answerNo + ANSWER_INDEX_START]);
				updateView(answerNo + 1 + ".  " + currentQuiz.getQueAndAns()[questionNo].getQue_AnsList()[answerNo + ANSWER_INDEX_START]+"\n");
				
			}
			
			noOfQuestionsAnswered += keepScore(answerToSubmit, newCorrectAnswerIndex);
			
		}
		
		if (noOfQuestionsAnswered == currentQuiz.getNoOfQuestionsPerQuiz()) {
			
			updateView("You answered " + currentQuiz.getScore() + " out of "+ currentQuiz.getNoOfQuestionsPerQuiz() + "correctly \n");

			startGame();
			
		}
*/
					
	}

	/**
	 * Gets the quiz corresponding to the player's choice of question numbers 
	 * from the list of quizzes held in QuizController (created by setUpClient).
	 * Either 1,2,or 3 for a 6,8,or 10 question quiz. 
	 * 
	 * @param quizToActivate              the quizToActivate selection int 
	 * @return Quiz                       the Quiz that was chosen
	 * @throws IllegalArgumentException   if int choice passed is not 1,2 or 3
	 */
	private Quiz getChosenQuiz(int quizToActivate) throws IllegalArgumentException {
	
		int noOfQuestions = 0 ;
		Quiz quizOfChoice = null;
		
		switch (quizToActivate) {
		
			case 1 : noOfQuestions = 6; break;
			case 2 : noOfQuestions = 8; break;
			case 3 : noOfQuestions = 10; break;
			default: throw new IllegalArgumentException("not 1 2 or 3");
		
		}
		
		for (Quiz quiz : quizList) {
			
			if (quiz.getNoOfQuestionsPerQuiz() == noOfQuestions) {
				
				quizOfChoice = quiz; 
				
			}
		
		}
		
		return quizOfChoice;
		
	}
	
	@Override
	public boolean isCurrentQuizActive() {
		
		return currentQuiz.getQuizIsActive();
		
	}
	
	
	/**
	 * for setUpClient only
	 */
	@Override
	public synchronized void stopQuiz(int quizId) throws RemoteException {
			
		try {
			
			if (!containsQuizWithId(quizId)) {
				
				throw new NullPointerException();
				
			}
			
			for (Quiz quiz : quizList) {
		
				if (quiz.getQuizId() == quizId) {
				
					quiz.setQuizIsActive(false);
					quizView.updatePlayerView("\n-------------------------------------------\n"
							+ "quiz with id# " + quizId + " is now stopped\n");
					quizView.updatePlayerView("Player scored " + getScoreOutOf(quizId)
							+ "\n-------------------------------------------\n");
				
				}
			
			} 
			
		} catch (NullPointerException e) {
			
			String ExceptionMsg = "No quiz by that id# exists in QuizController's quizList";
			System.out.println(ExceptionMsg);
			quizView.updatePlayerView(ExceptionMsg);
			
		}
		
	}
	
	/**
	 * Generates a new position for the correct answer (originally at position 2 in the que_AnsList), 
	 * by random and immediately prior to being output to playerClient UI. It is only called from 
	 * answerQuestions(int). 
	 *  
	 * @param questionNo   an int position of the current que_AnsList held in the listOfQAndALists 
	 * @return             an int new position of the correct answer in the current que_AndAnsList.
	 * @throws RemoteException (in case anything goes wrong with network connectivity)
	 * (temporarily made public for JUnit test)  
	 */
	private int shuffleAnswers(int questionNo) throws RemoteException{
		
		Random randomObj = new Random();
		int randomNewCorrectAnswerIndex = ORIGINAL_CORRECT_ANSWER_INDEX + randomObj.nextInt(4);

		int temp = currentQuiz.getQueAndAns()[questionNo].getQue_AnsList()[randomNewCorrectAnswerIndex];
		currentQuiz.getQueAndAns()[questionNo].getQue_AnsList()[randomNewCorrectAnswerIndex] = currentQuiz.getQueAndAns()[questionNo].getQue_AnsList()[ORIGINAL_CORRECT_ANSWER_INDEX];
		currentQuiz.getQueAndAns()[questionNo].getQue_AnsList()[ORIGINAL_CORRECT_ANSWER_INDEX] = temp;
	
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
	private void keepScore(int userInput, int newCorrectAnswerIndex) throws RemoteException {
		
		if (userInput == newCorrectAnswerIndex - 1) {
			
			currentQuiz.incrementScore(); 
		
		}
			
	}
	
	@Override
	public String getScoreOutOf(int quizId) throws RemoteException {
		
		String scoreOutOf = "";
		
		for (Quiz quiz : quizList) {
			
			if (quiz.getQuizId() == quizId) {
				
				scoreOutOf = quiz.getScore() + " out of " + quiz.getNumberOfQuestionsAnswered();
				
			}
				
		}

		return scoreOutOf;
		
	}

	public synchronized boolean isQuizActive(int quizId) {
		
		boolean isQuizActive = false;
		
		for (Quiz quiz : quizList) {
			
			if (quiz.getQuizId() == quizId) {
				
				isQuizActive = quiz.getQuizIsActive();
			
			}
				
		}

		return isQuizActive; 
		
	}
	
	@Override
	public boolean containsQuizWithId(int id) {
		
		return quizList.contains(id);
		
	}
	
	@Override
	public void addNewQuiz(Quiz newQuiz) {
		
		quizList.add(newQuiz);
		
	}
	
}