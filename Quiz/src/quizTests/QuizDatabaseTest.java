package quizTests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import quiz.QuizDatabaseImpl;

public class QuizDatabaseTest {

	QuizDatabaseImpl dbObj;
	
	
	@Before
	public void setUp() throws Exception {
		
		dbObj = new QuizDatabaseImpl();
	
	}

	@After
	public void tearDown() throws Exception {
	
		dbObj = null;
	
	}

	/**
	 * to confirm QuizDatabase instantiation is working (as this	and getAnswer(int,int) 
	 * as well as QueAndAns's generatQuestionIndices(int) and composeFalseAnswer()
	 * will be called by QueAndAns's generateQueAndAnsList()).
	 * 
	 * If the constructor is not successfully called, the fields will remain with the
	 * value == 0. 
	 */
	@Test
	public void testConstructor() {
	
		dbObj = null;
		assertTrue(dbObj == null);
				
		dbObj = new QuizDatabaseImpl();
		
		int actualAnswer = dbObj.getQuestionListLength();
		int expectedAnswer = 3;
		
		assertEquals(expectedAnswer, actualAnswer);

	}
	@Test
	public void testGetQuestion() {
		
		String actualAnswer = dbObj.getQuestion(2);
		String expectedAnswer = "How high (in metres above sea level) is the highest point in ";
		System.out.println("actualAnswer: "+ actualAnswer);
		System.out.println("expectedAnswer: "+ expectedAnswer);
		assertEquals(expectedAnswer, actualAnswer);

	}

	@Test
	public void testGetCountry() {
		
		String actualAnswer = dbObj.getCountry(8);
		String expectedAnswer = "Kazakhstan?";
		assertEquals(expectedAnswer, actualAnswer);
		
	}

	@Test
	public void testGetAnswer() {
		
		int actualAnswer1 = dbObj.getAnswer(2,9);
		int expectedAnswer1 = 3500;
		assertEquals(expectedAnswer1, actualAnswer1);

		int actualAnswer2 = dbObj.getAnswer(1,6);
		int expectedAnswer2 = 7000;
		assertEquals(expectedAnswer2, actualAnswer2);

		int actualAnswer3 = dbObj.getAnswer(0,3);
		int expectedAnswer3 = 3;
		assertEquals(expectedAnswer3, actualAnswer3);

	}

}
