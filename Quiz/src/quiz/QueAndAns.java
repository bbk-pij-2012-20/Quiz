package quiz;

public interface QueAndAns {

	/**
	 * Getter for questionListIndex
	 */
	int getQuestionListIndex();

	/**
	 * Getter for countryListIndex
	 */
	int getCountryListIndex();
	
	/**
	 * Creates a list that encodes a single quiz question and 4 answers, one of
	 * which is the correct answer.
	 */
	void generateQueAndAnsList();	
		
	/**
	 * Getter for question and answer list que_AnsList.
	 * 
	 * @return   returns the list of 2 question indices and 4 answers.
	 */
	int[] getQue_AnsList();
		
}
