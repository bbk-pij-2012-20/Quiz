package quizTests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import quiz.QueAndAnsImpl;

public class QueAndAnsTest {

	QueAndAnsImpl qAObj; 
	
	@Before
	public void setUp() throws Exception {
	
		qAObj = new QueAndAnsImpl();
	
	}

	@After
	public void tearDown() throws Exception {
	
		qAObj = null;
		
	}

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
		
		boolean actualAnswer = !emptyCellInArray;
		boolean expectedAnswer = true;
		assertEquals(expectedAnswer, actualAnswer);
	
	}

	@Test
	public void testComposeQuestionIndices() {
		
		int randomQuestionIndex = qAObj.composeQuestionIndices(0);
		boolean aValidQuestionIndex = true;
		
		if (randomQuestionIndex > 2 && randomQuestionIndex < 0) {
			
			aValidQuestionIndex = false;
			break;
			
		}
		
		boolean actualAnswer = aValidQuestionIndex;
		assertTrue(actualAnswer);
		
	}

	@Test
	public void testGetMaxInRange() {
		
		qAObj.getQue_AnsList()[0] = 0; // how many colours in flag
		int actualAnswer = qAObj.getMaxInRange();
		int expectedAnswer = 5;
				
		assertEquals(expectedAnswer, actualAnswer);
		
	}

	@Test
	public void testIsNicelyDistributed() {
		
		qAObj.getQue_AnsList()[2] = 2000;
		qAObj.getQue_AnsList()[3] = 3100;
		qAObj.getQue_AnsList()[4] = 4200;
		boolean actualAnswer = qAObj.isNicelyDistributed(3500);
		boolean expectedAnswer = false;
		boolean actualAnswer2 = qAObj.isNicelyDistributed(5500);				
		boolean expectedAnswer2 = true;
		boolean actualAnswer3 = qAObj.isNicelyDistributed(7500);				
		boolean expectedAnswer3 = true;
		boolean actualAnswer4 = qAObj.isNicelyDistributed(10001);				
		boolean expectedAnswer4 = false;
		assertEquals(expectedAnswer, actualAnswer);
		assertEquals(expectedAnswer2, actualAnswer2);
		assertEquals(expectedAnswer3, actualAnswer3);
		assertEquals(expectedAnswer4, actualAnswer4);
		
	}
			
	@Test
	public void testComposeFalseAnswer() {
		
	/*	int falseAnswer = qAObj.composeFalseAnswer();
		boolean aValidFalseAnswer = true;
		
		if (randomQuestionIndex > 2 && randomQuestionIndex < 0) {
			
			aValidQuestionIndex = false;
			break;
			
		}
		
		boolean actualAnswer = aValidQuestionIndex;
		assertTrue(actualAnswer);
*/

}
