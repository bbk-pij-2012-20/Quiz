package quiz;

public interface QueAndAns {

	/**
	 * Creates a list that encodes a single quiz question and 4 answers, one of
	 * which is the correct answer.
	 * 
	 */
	void generateQueAndAnsList();
	
	/**
	 * Composes a question made of two parts by random selection of each part from two lists stored in QuizDatabase.
	 * 
	 * @param listIndex   0 or 1, the position of data in the QueAndAns list. (0 holds index of question type, 1 holds 
	 *                    the index of country name).  
	 *                    
	 */
	void composeQuestionIndices(int listIndex);
	
	
	/**
	 * Generates a random numerical answer within pre-defined boundaries and with margins of separation from other 
	 * answers. 
	 * 
	 * @return int   returns an int that is within a valid range and is separated from 
	 *               the other 3 answers.
	 */
	int composeFalseAnswer();
	
}
