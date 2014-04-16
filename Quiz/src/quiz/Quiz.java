package quiz;

public interface Quiz {

	/**
	 * Getter for quizIsOver boolean 
	 * 
	 * @return quizIsOver boolean
	 */
	boolean getQuizIsActive();

	/**
	 * 
	 * @param noOfQuestionsPerQuiz
	 */
	void setNoOfQuestionsPerQuiz(int noOfQuestionsPerQuiz);

	/**
	 * 
	 * @param quizIsActive
	 */
	void setQuizIsActive(boolean quizIsActive);

	/**
	 * 
	 * @return list of questions and answers
	 */
	QueAndAns[] getQueAndAns();

	/**
	 * 
	 * @param quizId
	 */
	void setQuizId(int quizId);

	/**
	 * 
	 * @return the unique quiz id int
	 */
	int getQuizId();
	
	/**
	 * 
	 * @return questions int for current quiz
	 */
	int getNoOfQuestionsPerQuiz();

	/**
	 * getter for score
	 * 
	 * @return
	 */
	int getScore();

	void incrementScore();

}
