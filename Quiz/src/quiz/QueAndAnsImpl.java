package quiz;

public class QueAndAnsImpl implements QueAndAns {

	private int	questionListIndex;
	private int	countryListIndex;
	private QuizDatabase database;
	private int noOfAnswersPerQuestion;
	private int[] que_AnsList;

	@Override
	public int getQuestionListIndex() {
		
		return questionListIndex;
		
	}
	
	@Override
	public int getCountryListIndex() {
		
		return countryListIndex;
		
	}
	
	/**
	 * Creates QueAndAns object a number of answers per question, specified
	 * by the setUpClient.
	 * 
	 * @param noOfAnswersPerQuestion
	 */
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
	 * Generates a random number, stored in one of two fields that encode the type of question
	 * or country name that make up the questions. Thus, it is called twice from generateQueAndAnsList(). 
	 * 
	 * @param listIndex   0 or 1, the position of data in the QueAndAns list. (0 holds index of question 
	 *                    type, 1 holds the index of country name).  
	 * 
	 * (temporarily made public for JUnit test)             
	 */
	public void generateQuestionIndices(int listIndex) {
		// TODO Auto-generated method stub

	}

	/**
	 * Generates a random numerical answer within pre-defined boundaries (by calling 
	 * getMaxInRange()) and within margins of separation from other answers (by 
	 * calling isNicelyDistributed(int)). 
	 * 
	 * @return int   returns an int that is within a valid range and is separated from 
	 *               the other 3 answers by predefined margins.
	 */
	private int composeFalseAnswer() {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * Rounds off candidate values to the nearest 10 (for numbers greater than 100) 
	 * or 100 (for number greater than 100).  
	 * 
	 * @param candidateValue   takes a candidate false answer int. 
	 * @return                 returns the rounded off int (if greater than 10), otherwise
	 *                         the same number is returned.
	 */
	private int roundOff(int candidateValue){
		//TODO
		return 1;
		
	}
	
	/**
	 * Computes whether a candidate wrong answer value is valid according to a two limits: 
	 * 1. pre-defined max and min values (via calling getMaxInRange()), 
	 * 2. pre-defined margins of separation between the answer values. 
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
