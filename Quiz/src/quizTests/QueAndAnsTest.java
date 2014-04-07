package quizTests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import quiz.QueAndAnsImpl;

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
		
		boolean actualAnswer = emptyCellInArray;
		boolean expectedAnswer = false;
		assertEquals(expectedAnswer, actualAnswer);
	
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
		
		qAObj.generateQuestionIndices(0);
		boolean actualAnswer = true;
		
		for (int i = 0; i < 20; i++) {
			System.out.println("qAObj.getQuestionListIndex()"+qAObj.getQuestionListIndex());
		
			if (	qAObj.getQuestionListIndex() > 2 || qAObj.getQuestionListIndex() < 0) {
			
				actualAnswer = false;
				System.out.println("qAObj.getQuestionListIndex()"+qAObj.getQuestionListIndex());
			}
		
		}

		qAObj.generateQuestionIndices(1);

		for (int i = 0; i < 20; i++) {
			System.out.println("qAObj.getCountryListIndex()"+qAObj.getCountryListIndex());			
			
			if (	qAObj.getCountryListIndex() > 9 || qAObj.getCountryListIndex() < 0) {
			
				actualAnswer = false;
			}
		
		}

		assertTrue(actualAnswer);
		
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
		
		qAObj.getQue_AnsList()[2] = 2000;
		qAObj.getQue_AnsList()[3] = 3100;
		qAObj.getQue_AnsList()[4] = 4200;
		boolean actualAnswer1 = qAObj.isNicelyDistributed(3500);
		boolean expectedAnswer1 = false;
		boolean actualAnswer2 = qAObj.isNicelyDistributed(5500);				
		boolean expectedAnswer2 = true;
		boolean actualAnswer3 = qAObj.isNicelyDistributed(7500);				
		boolean expectedAnswer3 = true;
		boolean actualAnswer4 = qAObj.isNicelyDistributed(10001);				
		boolean expectedAnswer4 = false;
		assertEquals(expectedAnswer1, actualAnswer1);
		assertEquals(expectedAnswer2, actualAnswer2);
		assertEquals(expectedAnswer3, actualAnswer3);
		assertEquals(expectedAnswer4, actualAnswer4);
		
	}
			
	/**
	 * needs to pass (together with testIsNicelyDistributed()) before testing
	 * its calling method (composeFalseAnswer()).
	 */
	@Test
	public void testRoundOff() {

		int actualAnswer = qAObj.roundOff(1111);		
		int expectedAnswer = 1100;
		int actualAnswer2 = qAObj.roundOff(8);		
		int expectedAnswer2 = 8;
		int actualAnswer3 = qAObj.roundOff(95);		
		int expectedAnswer3 = 90;
		
		System.out.println("actual: "+actualAnswer);
		System.out.println("actual2: "+actualAnswer2);
		System.out.println("actual3: "+actualAnswer3);
		assertEquals(expectedAnswer, actualAnswer);
		
	}
	
	/**
	 * needs to pass before testing its calling method (testGenerateQueAndAnsList())
	 */
	@Test
	public void testComposeFalseAnswer() {
		
		qAObj.getQue_AnsList()[0] = 0;
		int randomFalseAnswer1 = qAObj.composeFalseAnswer();
		boolean aValidFalseAnswer1 = true;
		
		if (randomFalseAnswer1 < 0 || randomFalseAnswer1 > 5) {
			
			aValidFalseAnswer1 = false;
			
		}
		
		qAObj.getQue_AnsList()[0] = 1;
		int randomFalseAnswer2 = qAObj.composeFalseAnswer();
		boolean aValidFalseAnswer2 = true;
		
		if (randomFalseAnswer2 < 1000 || randomFalseAnswer2 > 10000) {
			
			aValidFalseAnswer2 = false;
			
		}
		
		qAObj.getQue_AnsList()[0] = 2;
		int randomFalseAnswer3 = qAObj.composeFalseAnswer();
		boolean aValidFalseAnswer3 = true;
		
		if (randomFalseAnswer3 < 800 || randomFalseAnswer3 > 8000) {
			
			aValidFalseAnswer3 = false;
			
		}

		assertTrue(aValidFalseAnswer1);
		assertTrue(aValidFalseAnswer2);
		assertTrue(aValidFalseAnswer3);

	}
	
}