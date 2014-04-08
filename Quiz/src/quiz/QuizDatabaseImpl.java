package quiz;

public class QuizDatabaseImpl implements QuizDatabase {

	// data acquired from Wikipedia, and for some air distances http://www.prokerala.com/travel
	private String[] questionList = {"How many different colours are there in the flag of ","Roughly, how far (in km) is it from London to capital city of ","How high (in metres above sea level) is the highest point in "};
	private String[] countryList = {"Albania?","Azerbaijan?","Bulgaria?","Burkina Faso?","Djibouti?","Estonia?","Grenada?","Guatemala?","Kazakhstan?","Lesotho?"};
	public int[][] answerList = {{2,2500,2800},{4,4500,4500},{3,2500,2900},{3,6000,750},{4,5900,2000},{3,1800,300},{3,7000,840},{5,8800,4200},{2,5600,7000},{4,9400,3500}}; 	
	protected int questionListLength;
	protected int countryListLength;

	//explicit empty constructor.
	public QuizDatabaseImpl() {
		
		questionListLength = questionList.length;
		countryListLength = countryList.length;
		
	}
	
	public int getQuestionListLength() {
		
		return questionListLength;
		
	}
	
	public int getCountryListLength() {
		
		return countryListLength;
		
	}
	
	@Override
	public String getQuestion(int questionListIndex) {
		
		System.out.printf("%s", checkArgumentInBounds(questionListIndex, questionList)?"" : "The questionList "); 	
		
		return questionList[questionListIndex];
	
	}

	@Override
	public String getCountry(int countryListIndex) {
		
		System.out.printf("%s", checkArgumentInBounds(countryListIndex, countryList)?"" : "The countryList "); 	
		
		return countryList[countryListIndex];
	
	}

	@Override
	public int getAnswer(int questionListIndex, int countryListIndex) {
		
		try {
			
			if (questionListIndex >= answerList[0].length) {
			
				System.out.println("The questionListIndex passed is out of bounds of answerList");
				throw new ArrayIndexOutOfBoundsException();
			
			} 
			
			if (countryListIndex >= answerList.length) {
			
				System.out.println("The countryListIndex passed is out of bounds of answerList");
				throw new ArrayIndexOutOfBoundsException();
			
			}
					
		} catch (ArrayIndexOutOfBoundsException e) {
		
			e.printStackTrace();
		
		}
		
		return answerList[countryListIndex][questionListIndex];
	
	}
	
	/**
	 * Tests whether the index values passed to getQuestion(int,int) are within
	 * bounds of the questionList and countryList arrays.
	 * 
	 * @param listIndex  an int corresponding to index position in 1D array
	 * @param list       a String array that holds question types or country names.
 	 * @return           false if index is not within array dimensions, also throws/catches an ArrayIndexOutOfBoundsException
	 */
	private boolean checkArgumentInBounds(int listIndex, String[] list) {
		
		boolean outOfBounds = false;
		
		try {
			
			if (listIndex >= list.length) {
			
				System.out.println(" array is smaller than the index given. (Out of Bounds).");
				outOfBounds = true;
				throw new ArrayIndexOutOfBoundsException();
			
			}
					
		} catch (ArrayIndexOutOfBoundsException e) {
		
			e.printStackTrace();
		
		}

		return !outOfBounds;
		
	}


}
