package quiz;

public interface QueAndAns {

	/**
	 * Creates a list that encodes a single quiz question and 4 answers, one of
	 * which is the correct answer.
	 * 
	 */
	void generateQueAndAnsList();
	
	/**
	 * Composes a question made of two parts by randomly selecting them from a database.
	 * 
	 * @param listIndex
	 */
	void composeQuestionIndices(int listIndex);
	
	
	/**
	 * Generates a random numerical answer. 
	 * 
	 * @return int   an int that is within a valid range and is not too similar to 
	 *               the other 3 answers.
	 */
	int composeFalseAnswer();
	
}
