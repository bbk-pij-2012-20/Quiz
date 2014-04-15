package quiz;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
/**
 * A class which makes quizzes in the form of a list of 
 * question and answers lists and a unique id# for each.
 * 
 * @author Shahin
 */
public class QuizFactoryImpl implements QuizFactory {

	private final int NO_OF_ANSWERS_PER_QUESTION = 4;
	private int noOfQuestionsPerQuiz; 
	private QueAndAns[] quiz;

	/**
	 * Generates three quizzes, one with 6 questions, one with 8 questions
	 * and one with 10 questions. Calls generateAndStoreId() 
	 * 
	 * @return HashMap              map of quizIds Integers and quizzes (an array with 6,8, or 10 questions, 
	 *                              with 4 answers each).
	 * @throws RemoteException
	 */
	protected Map<Integer,QueAndAns[]> make3Quizzes() throws RemoteException {
		
		Map<Integer,QueAndAns[]> quizAndIds = new HashMap<>();
		
		for (int noOfQuestions=6; noOfQuestions<=10; noOfQuestions=noOfQuestions+2) {
			
			quizAndIds = makeQuiz(noOfQuestions);
			
		}
		
		return quizAndIds;
		
	}
	
	/**
	 * Fills a list with question-and-answers lists (called que_AnsList). The setUp
	 * client sets up the number of questions per quiz (6, 8 or 10) according to 
	 * the selection made by the playerClient. 
	 * Assures no repeated questions by calling isRepeatedQuestion(QueAndAns, int). 
	 * 
	 * (temporarily made public for JUnit test)
	 */
	private synchronized Map<Integer,QueAndAns[]> makeQuiz(int noOfQuestions) throws RemoteException {
		
		this.noOfQuestionsPerQuiz = noOfQuestions;
		quiz = new QueAndAns[this.noOfQuestionsPerQuiz];
		QueAndAnsImpl queAndAnsObj;

		for (int quizIndex=0; quizIndex<quiz.length; quizIndex++) {
		
			queAndAnsObj = new QueAndAnsImpl(NO_OF_ANSWERS_PER_QUESTION);
			quiz[quizIndex] = queAndAnsObj;			
				
			if (isDuplicateQuestion(queAndAnsObj, quizIndex)) {
			
				quizIndex--;
				
			}
				
		}
		
		return generateIdMap(quiz);
		
	}
	
	/**
	 * Checks if currently generated question has already been made and stored (in listOfQAndAListsIndex)
	 * 
	 * @param queAndAnsObj            a queAndAnsObj, created and passed from makeListOfQAndALists()
	 * @param listOfQAndAListsIndex   an int that is the position of the current queAndAnsObj created and passed from makeListOfQAndALists()  
	 * @return                        true if the question was already created and stored at another position in the listOfQAndALists
	 * 
	 * (temporarily made public for JUnit test)  
	 */
	private boolean isDuplicateQuestion(QueAndAnsImpl queAndAnsObj, int quizIndex) throws RemoteException {
	
		boolean questionIsDuplicate = false;
		int i = 0;
		
		while (!questionIsDuplicate && i < quizIndex) {
			
			if (queAndAnsObj.getQue_AnsList()[0] == quiz[i].getQue_AnsList()[0]) {

				if (queAndAnsObj.getQue_AnsList()[1] == quiz[i].getQue_AnsList()[1]) {
				
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
	private Map<Integer,QueAndAns[]> generateIdMap(QueAndAns[] quiz) {
		
		int id = 0;
		Map<Integer,QueAndAns[]> quizAndIds = new HashMap<>();
		Random randomObj = new Random();
		
		do {
			
			id = randomObj.nextInt(10000)+1;
		
		} while (quizAndIds.containsKey(id));

		quizAndIds.put(id, quiz);
		
		return quizAndIds;

	}
	
	@Override
	public QueAndAns[] getQuiz() throws RemoteException{
		
		return quiz;
		
	}
	
	@Override
	public void setQuiz(QueAndAns[] quiz) throws RemoteException{
		
		this.quiz = quiz;
		
	}
	
	@Override
	public int getNoOfQuestionsPerQuiz() throws RemoteException {
	
		return noOfQuestionsPerQuiz; 

	}

	@Override
	public int getNoOfAnswersPerQuestion() throws RemoteException {
		
		return NO_OF_ANSWERS_PER_QUESTION;

	}

}
