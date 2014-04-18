package quiz.tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import quiz.model.QueAndAnsImpl;

public class QueAndAnsTest {

	QueAndAnsImpl qAObj; 
	int noAnsPerQue;
	
	@Before
	public void setUp() throws Exception {
	
		noAnsPerQue = 4;
		qAObj = new QueAndAnsImpl(noAnsPerQue);
		
	}

	@After
	public void tearDown() throws Exception {
	
		qAObj = null;
		
	}
	
	/**
	 * to confirm instantiation of QueAndAns is working, as this
	 * constructor will be called from QuizServer's makeListOfQueAndAnsList().
	 */
	@Test
	public void testConstructor() {
		
		qAObj = null;
		assertTrue(qAObj == null);
		
		int noOfAnswersPerQuestion = 4;
		qAObj = new QueAndAnsImpl(noOfAnswersPerQuestion);
		
		int actualAnswer = qAObj.getQue_AnsList().length;
		int expectedAnswer = 6;
		assertEquals(expectedAnswer, actualAnswer);
		
	}

	/**
	 * Should only be tested after tests for methods that are called 
	 * by generateQueAndAnsList() pass their tests (testGenerateQuestionIndices(),
	 * QuizDatabase's constructor, QuizDatabase's testGetAnswer(), 
	 * testComposeFalseAnswer(), testRoundOff(), testisNicelyDistributed()
	 * and testGetMaxInRange()).
	 */
	@Test
	public void testGenerateQueAndAnsList() {

		qAObj.generateQueAndAnsList();
		boolean emptyCellInArray = false;
		
		for (int i=0; i<qAObj.getQue_AnsList().length; i++) {
					
			if (qAObj.getQue_AnsList()[i] == 0) {
		
				emptyCellInArray = true;
				break;
			
			}
		
		}
		
		assertFalse(emptyCellInArray);
	
	}

	/**
	 * Tests whether the method generates numbers within the correct range.
	 * (However it is not ideal as it is does not fail if no numbers
	 * are generated, i.e. the index is a member field, so is always initialised to 0)
	 * 
	 * (needs to pass before testing its calling method (testGenerateQueAndAnsList()))
	 */
	@Test
	public void testGenerateQuestionIndices() {
		
		boolean actualAnswer1 = true;
		boolean actualAnswer2 = true;
		
		for (int i = 0; i < 20; i++) {
			
			qAObj.generateQuestionIndices(0);
			
			System.out.println("qLI: "+qAObj.getQuestionListIndex());
		
			if (	qAObj.getQuestionListIndex() > 2 || qAObj.getQuestionListIndex() < 0) {
			
				actualAnswer1 = false;
				break;
			}
		
		}

		for (int i = 0; i < 20; i++) {

			qAObj.generateQuestionIndices(1);

			System.out.println("cLI: "+qAObj.getCountryListIndex());			
			
			if (	qAObj.getCountryListIndex() > 9 || qAObj.getCountryListIndex() < 0) {
			
				actualAnswer2 = false;
				break;
			}
		
		}

		assertTrue(actualAnswer1);
		assertTrue(actualAnswer2);
		
	}

	/**
	 * needs to pass before testing its calling methods 
	 * (isNicelyDistributed(int) & composeFalseAnswer()).
	 */
	@Test
	public void testGetMaxInRange() {
				
		qAObj.setQuestionListIndex(0);// how many colours in flag
		int actualAnswer1 = qAObj.getMaxInRange();
		int expectedAnswer1 = 5;

		qAObj.setQuestionListIndex(1);// how far from here to there
		int actualAnswer2 = qAObj.getMaxInRange();
		int expectedAnswer2 = 10000;

		qAObj.setQuestionListIndex(2);// how high is highest peak
		int actualAnswer3 = qAObj.getMaxInRange();
		int expectedAnswer3 = 8000;

		assertEquals(expectedAnswer1, actualAnswer1);
		assertEquals(expectedAnswer2, actualAnswer2);
		assertEquals(expectedAnswer3, actualAnswer3);
		
	}

	@Test (expected = IllegalArgumentException.class)
	public void testIllegalArgumentException() {
		
		qAObj.setQuestionListIndex(3);// not in range		
	    qAObj.getMaxInRange();
	
	}
	
	/**
	 * needs to pass (together with testRoundOff()) before testing
	 * its calling method (composeFalseAnswer()).
	 */
	@Test
	public void testIsNicelyDistributed() {
		
		qAObj.setQuestionListIndex(1);// qli=1 how far (min=1000 max=10000, +/- 1000)
		qAObj.getQue_AnsList()[2] = 2000;// 1000-3000, so 3500 is allowed
		qAObj.getQue_AnsList()[3] = 2600;// 1600-3600, so 3500 is not allowed.
		qAObj.getQue_AnsList()[4] = 8000;// 7000-9000, so 3500 is allowed (but was already disallowed by prior value)
		qAObj.getQue_AnsList()[5] = 0;
		assertFalse(qAObj.isNicelyDistributed(3500));
		assertTrue(qAObj.isNicelyDistributed(3700));
		
		qAObj.setQuestionListIndex(0);// qli=0 flag colours (min=1 max=5, +/- 1)
		qAObj.getQue_AnsList()[2] = 2;// allowed
		qAObj.getQue_AnsList()[3] = 3;// allowed.
		qAObj.getQue_AnsList()[4] = 4;// allowed (but 3500 was already disallowed by prior 2 values)
		qAObj.getQue_AnsList()[5] = 0;
		assertTrue(qAObj.isNicelyDistributed(1));
		assertTrue(qAObj.isNicelyDistributed(1));

	}
			
	/**
	 * needs to pass (together with testIsNicelyDistributed()) before testing
	 * its calling method (composeFalseAnswer()).
	 */
	@Test
	public void testRoundOff() {

		int actualAnswer1 = qAObj.roundOff(1111);		
		int expectedAnswer1 = 1100;
		int actualAnswer2 = qAObj.roundOff(8);		
		int expectedAnswer2 = 8;
		int actualAnswer3 = qAObj.roundOff(95);		
		int expectedAnswer3 = 90;
		
		System.out.println("actualRoundOff: "+actualAnswer1);
		System.out.println("actualRoundOff2: "+actualAnswer2);
		System.out.println("actualRoundOff3: "+actualAnswer3);

		assertEquals(expectedAnswer1, actualAnswer1);
		assertEquals(expectedAnswer2, actualAnswer2);
		assertEquals(expectedAnswer3, actualAnswer3);

	}
	
	/**
	 * needs to pass before testing its calling method (testGenerateQueAndAnsList())
	 */
	@Test
	public void testComposeFalseAnswer() {
		
		qAObj.setQuestionListIndex(0);//colours in flag (1-5)
		boolean aValidFalseAnswer1 = true;
		int randomFalseAnswer1 = 0;
		
		for (int i = 0; i < 20; i++) {
	
			randomFalseAnswer1 = qAObj.composeFalseAnswer();	
			System.out.println("ans1: "+randomFalseAnswer1);

			if (randomFalseAnswer1 < 0 || randomFalseAnswer1 > 5) {
			
				aValidFalseAnswer1 = false;
				break;
			
			}
		
		}
		
		qAObj.setQuestionListIndex(1);//distance (1000-10000)
		boolean aValidFalseAnswer2 = true;
		int randomFalseAnswer2 = 0;

		for (int i = 0; i < 20; i++) {
	
			randomFalseAnswer2 = qAObj.composeFalseAnswer();	
			System.out.println("ans2: "+randomFalseAnswer2);

			if (randomFalseAnswer2 < 1000 || randomFalseAnswer2 > 10000) {
			
				aValidFalseAnswer2 = false;
				break;
			
			}
	
		}
		
		qAObj.setQuestionListIndex(2); //highest peak (800-8000)
		boolean aValidFalseAnswer3 = true;
		int randomFalseAnswer3 = 0;

		for (int i = 0; i < 20; i++) {
			
			randomFalseAnswer3 = qAObj.composeFalseAnswer();
			System.out.println("ans3: "+randomFalseAnswer3);
					
			if (randomFalseAnswer3 < 800 || randomFalseAnswer3 > 8000) {
			
				aValidFalseAnswer3 = false;
				break;
			
			}
			
		}
		
		System.out.println("ans1: "+randomFalseAnswer1);
		System.out.println("ans2: "+randomFalseAnswer2);
		System.out.println("ans3: "+randomFalseAnswer3);
		assertTrue(aValidFalseAnswer1);
		assertTrue(aValidFalseAnswer2);
		assertTrue(aValidFalseAnswer3);

	}
	
	@Test
	public void testToString() {
	
		qAObj.getQue_AnsList()[0] = 1;
		qAObj.getQue_AnsList()[1] = 2;
				
		String actualOutput = qAObj.toString();
		String expectedOutput = "Roughly, how far (in km) is it from London to capital city of Bulgaria?";
		
		System.out.println("toString output: "+ actualOutput);
		assertEquals(expectedOutput, actualOutput);
	
	}
	
}