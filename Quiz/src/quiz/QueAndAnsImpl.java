package quiz;

import java.util.IllegalFormatException;
import java.util.Random;

public class QueAndAnsImpl implements QueAndAns {

	private int	questionListIndex;
	private int	countryListIndex;
	private QuizDatabaseImpl database;
	private int noOfAnswersPerQuestion;
	private int[] que_AnsList;
	
	/**
	 * Constructor takes a number of answers per question, to be specified
	 * by the setUpClient.
	 * 
	 * @param noOfAnswersPerQuestion
	 */
	public QueAndAnsImpl(int noOfAnswersPerQuestion) {
		
		this.noOfAnswersPerQuestion = noOfAnswersPerQuestion;
		que_AnsList	= new int[2+noOfAnswersPerQuestion];
		database = new QuizDatabaseImpl();
		generateQueAndAnsList();
		
	}
	
	public QueAndAnsImpl(){}

	@Override
	public int[] getQue_AnsList() {

		return que_AnsList;
	
	}

	@Override
	public int getQuestionListIndex() {
		
		return questionListIndex;
		
	}
	
	@Override
	public int getCountryListIndex() {
		
		return countryListIndex;
		
	}
	
	@Override
	public void setQuestionListIndex(int questionListIndex) {
		
		this.questionListIndex = questionListIndex;
		
	}
	
	@Override
	public void setCountryListIndex(int countryListIndex) {
		
		this.countryListIndex = countryListIndex;
		
	}

	@Override
	public void generateQueAndAnsList() {

		generateQuestionIndices(0);
		database = new QuizDatabaseImpl();
		que_AnsList[0] = questionListIndex;
		generateQuestionIndices(1);
		que_AnsList[1] = countryListIndex;
		que_AnsList[2] = database.getAnswer(questionListIndex, countryListIndex);
		que_AnsList[3] = composeFalseAnswer(); 		
		que_AnsList[4] = composeFalseAnswer();
		que_AnsList[5] = composeFalseAnswer();

	}

	/**
	 * Generates a random number, stored in one of two fields (questionListIndex, countryListIndex) that encode 
	 * the type of question or country name that make up the questions. Thus, it is called twice from 
	 * generateQueAndAnsList(). 
	 * 
	 * @param listIndex   0 or 1, the position of data in the QueAndAns list. (0 holds index of question 
	 *                    type, 1 holds the index of country name).  
	 * 
	 * (temporarily made public for JUnit test)             
	 */
	public void generateQuestionIndices(int listIndex) {
		
		Random randomObj = new Random();
		
		if (listIndex == 0) {
		
			questionListIndex = randomObj.nextInt(database.questionListLength);
		
		} else if (listIndex == 1) {
		
			countryListIndex = randomObj.nextInt(database.countryListLength);
			
		}

	}

	/**
	 * Generates a random numerical answer within pre-defined boundaries (by calling 
	 * getMaxInRange()) and within margins of separation from other answers (by 
	 * calling isNicelyDistributed(int)). 
	 * 
	 * @return int   returns an int that is within a valid range and is separated from 
	 *               the other 3 answers by predefined margins.
	 *               
	 * (temporarily made public for JUnit test)  
	 */
	public int composeFalseAnswer() {
		
		Random randomObj = new Random();
		int candidateValue = 0;
		
		do { 
		
			candidateValue = roundOff(randomObj.nextInt(getMaxInRange()));
			
		} while (!isNicelyDistributed(candidateValue)); 
	
		return candidateValue;
				
	}

	/**
	 * Rounds off candidate values to the nearest 10 (for numbers greater than 100) 
	 * or 100 (for number greater than 100).  
	 * 
	 * @param candidateValue   takes a candidate false answer int. 
	 * @return                 returns the rounded off int (if greater than 10), otherwise
	 *                         the same number is returned.
	 *                         
	 * (temporarily made public for JUnit test)  
	 */
	public int roundOff(int candidateValue){
			
		if (candidateValue > 100) {
			
			candidateValue = candidateValue / 100 * 100;
			
		} else if (candidateValue > 10) {
			
			candidateValue = candidateValue / 10 * 10;
		
		}

		return candidateValue; 
		
	}
	
	/**
	 * Computes whether a candidate wrong answer value is valid according to a two limits: 
	 * 1. pre-defined max and min values (via calling getMaxInRange()), 
	 * 2. pre-defined margins of separation between the answer values. 
	 *  
	 * @param candidateValue   takes an int to be evaluated for its validity.
	 * @return                 returns true if candidate value is acceptable.
	 *	 
	 * (temporarily made public for JUnit test)  
	 */
	public boolean isNicelyDistributed(int candidateValue) {
	
		boolean nicelyDistributed = true;
		
		for (int i = 2; i < que_AnsList.length; i++) {
						
			if (candidateValue > que_AnsList[i]) {
			
				if (candidateValue < que_AnsList[i] + getMaxInRange()/10) {
				
					nicelyDistributed = false;
				
				}
				
			} else if (candidateValue <= que_AnsList[i]) {
			
				if (candidateValue > que_AnsList[i] - getMaxInRange()/10) {
				
					nicelyDistributed = false;
				
				}			
			
			}
			
		}

		int minInRange = getMaxInRange()/10 + 1;

		return (nicelyDistributed && (candidateValue >= minInRange) && (candidateValue <= getMaxInRange()));		
		
	}

	/**
	 * Determines the maximum acceptable value according to the question type. 
	 * 
	 * @return      returns an maximum int value. 
	 * 
	 * (temporarily made public for JUnit test)  
	 */
	public int getMaxInRange() throws IllegalFormatException {
		
		int range = 0;
		
		try {
		
			if (questionListIndex < 0 || questionListIndex > 2) {
			
				System.out.println("questionListIndex is out of expected range (0-2): "+questionListIndex);
				throw new IllegalArgumentException();
			
			}
			
			switch (questionListIndex) {
			
				case 0: range = 5; break; // no. colours in flag 
				case 1: range = 10000; break; // distance from here
				case 2: range = 8000; break; //highest peak above sea level
				default: throw new NumberFormatException("the questionListIndex value is not a number"); 
			
			}
			
		} catch (IllegalArgumentException e) {
			
			e.printStackTrace();				
			
		}
		
		return range;
	}
	
	@Override
	public String toString() {
	
		return "" + database.getQuestion(que_AnsList[0]) + database.getCountry(que_AnsList[1]);
	
	}
	
}
