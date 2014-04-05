package quiz;

public class QuizDatabaseImpl implements QuizDatabase {

	// data acquired from Wikipedia, and for some air distances http://www.prokerala.com/travel
	private String[] questionList = {"How many different colours are there in the flag of ","Roughly, how far (in km) is it from London to capital city of ","How high (in metres above sea level) is the highest point in "}; 	
	private String[] countryList = {"Albania?","Azerbaijan?","Bulgaria?","Burkina Faso?","Djibouti?","Estonia?","Grenada?","Guatemala?","Kazakhstan?","Lesotho?"};
	private int[][] answerList = {{2,2500,2800},{4,4500,4500},{3,2500,2900},{3,6000,750},{4,5900,2000},{3,1800,300},{3,7000,840},{5,8800,4200},{2,5600,7000},{4,9400,3500}}; 	
	protected int questionListLength = questionList.length;
	protected int countryListLength = countryList.length;

	@Override
	public String getQuestion(int questionListIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCountry(int countryListIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getAnswer(int questionListIndex, int countryListIndex) {
		// TODO Auto-generated method stub
		return 0;
	}

}
