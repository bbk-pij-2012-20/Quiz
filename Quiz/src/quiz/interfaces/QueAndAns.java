package quiz.interfaces;

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

	/**
	 * Setter for questionListIndex
	 */
	void setCountryListIndex(int countryListIndex);

	/**
	 * Setter for questionListIndex
	 */
	void setQuestionListIndex(int questionListIndex);
		
	/**
	 * Called from QuizServer's playQuiz() to present the quiz 
	 * question to the playerClient.
	 * 
	 * @return  returns the whole question sentence.
	 */
	String toString();

	/**
	 * a setter for que_AnsList (primarily for JUnit)
	 * 
	 * @param que_AnsList an int array encoding questions and containing answers
	 * @return
	 */
	void setQue_AnsList(int[] que_AnsList);

	/**
	 * Getter for NoOfAnswersPerQuestion
	 * 
	 * @return
	 */
	int getNoOfAnswersPerQuestion();

}
