package quizTests;

import static org.junit.Assert.*;

import java.rmi.RemoteException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import quiz.QueAndAns;
import quiz.QueAndAnsImpl;
import quiz.QuizServer;

public class QuizServerTest {

	QuizServer serverObj;
	
	@Before
	public void setUp() throws Exception {
	
		serverObj = new QuizServer();
	
	}

	@After
	public void tearDown() throws Exception {
	
		serverObj = null;
		
	}


	@Test
	public void testSetUpQuiz() throws RemoteException {
/*		
		int numOfQ = 7;
		int numOfAnsPerQ = 5;

		serverObj.setUpQuiz(numOfQ,numOfAnsPerQ);
		
		int memberFieldQue = serverObj.totalNoOfQuestions;//visibility needs to be temporarily made public for test. 
		int memberFieldAns = serverObj.noOfAnswersPerQuestion;//visibility needs to be temporarily made public for test.
		String actualValue = "" + memberFieldQue + memberFieldAns;
		String expectedValue = "75"; 
		
		assertEquals(expectedValue, actualValue);
*/		
	}

	/**
	 * This test needs to be done after testGenerateQueAndAnsList() passes.
	 * It will need to be passing before testMakeListOfQAndALists() passes.
	 * @throws RemoteException 
	 */
	@Test
	 public void testIsRepeatedQuestion() throws RemoteException {
		
		QueAndAnsImpl[] listOfQAndALists = new QueAndAnsImpl[10];
		serverObj.setListOfQAndALists(listOfQAndALists);
			
		QueAndAnsImpl qAObj1 = new QueAndAnsImpl(4);
		qAObj1.setQuestionListIndex(2); //highest peak of
		qAObj1.setCountryListIndex(2); // Bulgaria 
		qAObj1.getQue_AnsList()[0] = qAObj1.getQuestionListIndex();
		qAObj1.getQue_AnsList()[1] = qAObj1.getCountryListIndex();
		System.out.println(qAObj1); 
		serverObj.getListOfQAndALists()[0] = qAObj1; 
		System.out.println(serverObj.getListOfQAndALists()[0]);
	 
		QueAndAnsImpl qAObj2 = new QueAndAnsImpl(4);
		qAObj2.setQuestionListIndex(2); //highest peak of
		qAObj2.setCountryListIndex(1); // Azerbaijan
		qAObj2.getQue_AnsList()[0] = qAObj2.getQuestionListIndex();
		qAObj2.getQue_AnsList()[1] = qAObj2.getCountryListIndex();
		System.out.println(qAObj2);
		serverObj.getListOfQAndALists()[1] = qAObj2; 

		QueAndAnsImpl qAObj3 = new QueAndAnsImpl(4);
		qAObj3.setQuestionListIndex(1); //how far to
		qAObj3.setCountryListIndex(4); // Djibouti 
		qAObj3.getQue_AnsList()[0] = qAObj3.getQuestionListIndex();
		qAObj3.getQue_AnsList()[1] = qAObj3.getCountryListIndex();
		System.out.println(qAObj3);
		serverObj.getListOfQAndALists()[2] = qAObj3; 

		QueAndAnsImpl qAObj4 = new QueAndAnsImpl(4);
		qAObj4.setQuestionListIndex(1); //how far to
		qAObj4.setCountryListIndex(1); // Azerbaijan
		qAObj4.getQue_AnsList()[0] = qAObj4.getQuestionListIndex();
		qAObj4.getQue_AnsList()[1] = qAObj4.getCountryListIndex();
		System.out.println(qAObj4);
		
		assertFalse(serverObj.isRepeatedQuestion(qAObj4, 3));

		QueAndAnsImpl qAObj5 = new QueAndAnsImpl(4);
		qAObj5.setQuestionListIndex(2); //highest peak of
		qAObj5.setCountryListIndex(1); // Azerbaijan
		qAObj5.getQue_AnsList()[0] = qAObj5.getQuestionListIndex();
		qAObj5.getQue_AnsList()[1] = qAObj5.getCountryListIndex();
		System.out.println(qAObj5);
		
		assertTrue(serverObj.isRepeatedQuestion(qAObj5, 4));

	 }
	
	 @Test
	public void testMakeListOfQAndALists() throws RemoteException {
		
		serverObj = new QuizServer(10,4);
		serverObj.makeListOfQAndALists();
		boolean listOfListsFilledUp = true;
		
		for (int i = 0; i < serverObj.getListOfQAndALists().length; i++) {
			
			System.out.println(serverObj.getListOfQAndALists()[i]);
	
			if (serverObj.getListOfQAndALists()[i] == null) {
				
				listOfListsFilledUp = false;
				break;
				
			}
			
		}

		assertTrue(listOfListsFilledUp);

	}
	
	/**
	 * playQuiz() will call print the userPrompt, call makeListOfQAndALists() 
	 */
	@Test
	public void testPlayQuiz() {

		//todo
		//assertEquals(expectedValue, actualValue);

	}
	
}

