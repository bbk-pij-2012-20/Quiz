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

	@Override
	public void composeQuestionIndices(int listIndex) {
		// TODO Auto-generated method stub

	}

	@Override
	public int composeFalseAnswer() {
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
