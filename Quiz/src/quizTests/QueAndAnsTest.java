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
		fail("Not yet implemented");
	}

	@Test
	public void testComposeFalseAnswer() {
		fail("Not yet implemented");
	}

}
