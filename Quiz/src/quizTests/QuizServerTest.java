package quizTests;

import static org.junit.Assert.*;

import java.rmi.RemoteException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import quiz.QueAndAns;
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
	public void testQuizServer() {
		//constructor ...todo
	}

	@Test
	public void testSetUpQuiz() throws RemoteException {
		
		int numOfQ = 7;
		int numOfAnsPerQ = 5;

		serverObj.setUpQuiz(numOfQ,numOfAnsPerQ);
		
		int memberFieldQue = serverObj.totalNoOfQuestions;//visibility needs to be temporarily made public for test. 
		int memberFieldAns = serverObj.noOfAnswersPerQuestion;//visibility needs to be temporarily made public for test.
 		String actualValue = "" + memberFieldQue + memberFieldAns;
		String expectedValue = "75"; 
		
		
		assertEquals(expectedValue, actualValue);
		
	}

	@Test
	public void testPlayQuiz() {

		//todo
		//assertEquals(expectedValue, actualValue);

	}
/*
	@Test
	public void testMakeListOfQAndAList() {
	
		int actualValue2 = serverObj.listOfQAndALists.length;//visibility needs to be temporarily made public for test.
		int expectedValue2 = 7;	
}
*/
