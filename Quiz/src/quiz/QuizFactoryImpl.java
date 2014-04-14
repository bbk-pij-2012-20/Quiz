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
	
	private Map<Integer,QueAndAns[]> quizAndIds;
	private final int NO_OF_ANSWERS_PER_QUESTION = 4;
	private int totalNoOfQuestions; 
	private QueAndAns[] listOfQAndALists;

	protected void make3Quizzes() throws RemoteException {
		
		for (int i = 0; i < 3; i++) {
			
			makeListOfQAndALists(2*(i+1));
			
		}
		
	}
	
	/**
	 * Fills the listOfQueAndAnsLists with que_AndLists with as many as the setUpClient has specified. 
	 * Assures no repeated questions by calling isRepeatedQuestion(QueAndAns, int). 
	 * 
	 * (temporarily made public for JUnit test)
	 */
	private synchronized void makeListOfQAndALists(int noOfQuestions) throws RemoteException {
		
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
	
	/**
	 * Checks if currently generated question has already been made and stored (in listOfQAndAListsIndex)
	 * 
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
	 * Generates a unique random id number for each quiz which has been passed to it 
	 * and stores it in a map. 
	 * 
	 * @param listOfQAndALists   a QueAndAns[] to be stored with the id# 
	 */
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
	public QueAndAns[] getListOfQAndALists() throws RemoteException{
		
		return listOfQAndALists;
		
	}
	
	@Override
	public void setListOfQAndALists(QueAndAns[] listOfQAndALists) throws RemoteException{
		
		this.listOfQAndALists = listOfQAndALists;
		
	}
	
	@Override
	public int getTotalNoOfQuestions() throws RemoteException {
	
		return totalNoOfQuestions; 

	}

	@Override
	public int getNoOfAnswersPerQuestion() throws RemoteException {
		
		return NO_OF_ANSWERS_PER_QUESTION;

	}

}
