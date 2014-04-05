package quiz;

public class QueAndAnsImpl implements QueAndAns {

	private int	questionListIndex;
	private int	countryListIndex;
	private QuizDatabase database;
	private int noOfAnswersPerQuestion;
	private int[] que_AnsList;

	public QueAndAnsImpl(int noOfAnswersPerQuestion) {
		
		this.noOfAnswersPerQuestion = noOfAnswersPerQuestion;
		que_AnsList	= new int[2+noOfAnswersPerQuestion];
		
	}
	
	public QueAndAnsImpl(){}

	
	@Override
	public void generateQueAndAnsList() {
		// TODO Auto-generated method stub

	}

	/**
	 * Composes a question made of two parts by random selection of each part from two lists stored in QuizDatabase.
	 * 
	 * @param listIndex   0 or 1, the position of data in the QueAndAns list. (0 holds index of question type, 1 holds 
	 *                    the index of country name).  
	 *                    
	 */
	private void composeQuestionIndices(int listIndex) {
		// TODO Auto-generated method stub

	}

	/**
	 * Generates a random numerical answer within pre-defined boundaries and with margins of separation from other 
	 * answers. 
	 * 
	 * @return int   returns an int that is within a valid range and is separated from 
	 *               the other 3 answers.
	 */
	private int composeFalseAnswer() {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * Computes whether a candidate wrong answer value is valid according to a predetermined range (via calling getMaxInRange())
	 * and margin of separation from other values. 
	 *  
	 * @param candidateValue   takes an int to be evaluated for its validity.
	 * @return                 returns true if candidate value is acceptable.
	 */

	private boolean isNicelyDistributed(int candidateValue) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Determines the maximum acceptable value according to the question type. 
	 * 
	 * @return      returns an maximum int value. 
	 */
	private int getMaxInRange() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int[] getQue_AnsList() {
		// TODO Auto-generated method stub
		return null;
	}

}
