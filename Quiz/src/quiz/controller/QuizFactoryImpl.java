package quiz.controller;

import java.util.ArrayList;
import java.util.List;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.rmi.RemoteException;
import java.util.Random;

import quiz.interfaces.Quiz;
import quiz.interfaces.QuizController;
import quiz.interfaces.QuizFactory;
import quiz.model.QueAndAnsImpl;
import quiz.model.QuizImpl;
import quiz.view.QuizView;
import quiz.view.QuizViewImpl;

/**
 * QuizFactoryImpl makes 3 quizzes, one quiz at a time.
 * They are currently set to differ by the number of questions they 
 * contain (6,8 or 10 unique questions). 
 * A unique and random quiz id# is generated here.
 * @author Shahin
 */
public class QuizFactoryImpl implements QuizFactory {
	
	private QuizView quizView;
	private QuizController quizController;
	
	@Override
	public QuizView make3Quizzes() throws RemoteException {
				
		quizController = new QuizControllerImpl();
		quizView = new QuizViewImpl();

		for (int noOfQuestions=6; noOfQuestions<=10; noOfQuestions=noOfQuestions+2) {
			
			makeQuiz(noOfQuestions);// not sure about this return here.. 
			
		}

		writeQuizListToFile();
		return quizView;	

	}
	
	/**
	 * Fills a list with question-and-answers lists (called que_AnsList). Includes
	 * one element to hold the status of the quiz - active or over. The setUp
	 * client sets up the number of questions per quiz (6, 8 or 10) according to 
	 * the selection made by the playerClient. 
	 * Assures no repeated questions by calling isRepeatedQuestion(QueAndAns, int). 
	 * 
	 * (temporarily made public for JUnit test)
	 */
	private void makeQuiz(int noOfQuestionsPerQuiz) throws RemoteException {
		
		Quiz quiz = new QuizImpl();
		quiz.setNoOfQuestionsPerQuiz(noOfQuestionsPerQuiz);		
		QueAndAnsImpl queAndAnsObj = null;
				
		for (int quizIndex=0; quizIndex<noOfQuestionsPerQuiz; quizIndex++) {
		
			queAndAnsObj = new QueAndAnsImpl();
			quiz.getQueAndAns()[quizIndex] = queAndAnsObj;			
				
			if (isDuplicateQuestion(queAndAnsObj, quizIndex, quiz)) {
			
				quizIndex--;
				
			}
				
		}

		generateAndSetId(quiz);
		
	}
	
	/**
	 * Checks if currently generated question has already been made and stored (in quizIndex)
	 * 
	 * @param queAndAnsObj      a queAndAnsObj, just created and passed by makeQuiz()
	 * @param quizIndex         the int position of the current queAndAnsObj created and passed from makeQuiz()
	 * @param quiz              the quiz which may contain other queAndAns to compare with the new one for duplication  
	 * @return                  true if the question was already created and stored at another position in the quiz
	 * 
	 * (temporarily made public for JUnit test)  
	 */
	private boolean isDuplicateQuestion(QueAndAnsImpl queAndAnsObj, int quizIndex, Quiz quiz) throws RemoteException {
	
		boolean questionIsDuplicate = false;
		int i = 0;
		
		while (!questionIsDuplicate && i < quizIndex) {
			
			if (queAndAnsObj.getQue_AnsList()[0] == quiz.getQueAndAns()[i].getQue_AnsList()[0]) {

				if (queAndAnsObj.getQue_AnsList()[1] == quiz.getQueAndAns()[i].getQue_AnsList()[1]) {
				
					questionIsDuplicate = true;
					
				} else {
				
					i++;
					continue;
				
				}
									
			} else {
				
				i++;
				continue;
				
			}
			
		}
		
		return questionIsDuplicate;

	}
	
	/**
	 * Generates a unique random id number for each quiz which has been passed to it 
	 * and stores it in a map. 
	 * 
	 * @param listOfQAndALists   a QueAndAns[] to be stored with the id# 
	 */ 
	private void generateAndSetId(Quiz quiz) throws RemoteException {
		
		int quizId = 0;
		Random randomObj = new Random();
		
		do {
			
			quizId = randomObj.nextInt(10000)+1;
		
		} while (quizController.containsQuizWithId(quizId));

		quiz.setQuizId(quizId);
		quizController.addToQuizList(quiz);
		initAndUpdateView(quiz);
		
	}	
	
	private void initAndUpdateView(Quiz quiz) {
		
		quizView.updateSetUpView(quiz.toString());
		
	}
	
	//turns the quizList, just created, into stream and writes out to file called QuizList.ser. 
	private 	void writeQuizListToFile() {
		
		final String OUT_QUIZLIST_FILENAME = "QuizList.ser";
		List<QuizImpl> quizList = new ArrayList<QuizImpl>();
		
		ObjectOutputStream encode = null;
			
		try {
			
			encode = new ObjectOutputStream(			
					 new BufferedOutputStream(
					 new FileOutputStream(OUT_QUIZLIST_FILENAME)));
	        
		} catch (FileNotFoundException e) {
	        
			System.out.println("file not found while trying to encoding..." + e);
	        
		} catch (IOException e) {
	        
			e.printStackTrace();
		
		}

		try {
	        
			encode.writeObject(quizList);
	        
		} catch (IOException ex) {
	        
			ex.printStackTrace();
	        
		}
	        
		try {
	        
			encode.close();
	        
		} catch (IOException ex2) {
	        
			ex2.printStackTrace();
	        
		}
		
	}
	
}