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
		fail("Not yet implemented");
	}

	@Test
	public void testGetAnswer() {
		fail("Not yet implemented");
	}

}
