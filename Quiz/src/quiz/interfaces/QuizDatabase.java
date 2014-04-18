package quiz.interfaces;

public interface QuizDatabase {

	/**
	 * Gets the first part of the whole question from QuizDatabase, located by its index.
	 * 
	 * @param questionListIndex   takes an int that is the location of the first part of a question (stored in a list).
	 * @return                    returns the String that is the first part of the question (the type of question).
	 */
	String getQuestion(int questionListIndex);

	/**
	 * Gets the end part of the whole question, a country name, from QuizDatabase, located by its index. 
	 * 
	 * @param countryListIndex    an int that is the location of the second part of a question, stored in a list in QuizDatabase. 
	 * @return                    the String that is the second part of the question (the country name).
	 */
	String getCountry(int countryListIndex);
	
	/**
	 * Gets the answer to the question, from QuizDatabase, located by the combined location of both parts of the question. 
	 * 
	 * @param questionListIndex   an int that is the first of two coordinates to the correct answer, stored in QuizDatabase
	 * @param countryListIndex    an int that is the second of two coordinates to the correct answer, stored in QuizDatabase
	 * @return
	 */
	int getAnswer(int questionListIndex, int countryListIndex);
	
}
