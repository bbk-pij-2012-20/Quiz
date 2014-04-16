package quiz;

public class QuizImpl implements Quiz {

	private int noOfQuestionsPerQuiz; 
	private int quizId = 0;
	private int score = 0;
	private int numberOfQuestionsAnswered = 0;
	private QueAndAns[] queAndAns;
	private boolean quizIsActive = false;
	
	/**
	 * empty constructor
	 */
	public QuizImpl() {}
	
	@Override
	public void setNoOfQuestionsPerQuiz(int noOfQuestionsPerQuiz) {
		
		this.noOfQuestionsPerQuiz = noOfQuestionsPerQuiz;
		queAndAns = new QueAndAns[this.noOfQuestionsPerQuiz];
		
	}
	
	@Override
	public int getNoOfQuestionsPerQuiz() {
	
		return noOfQuestionsPerQuiz;
		
	}
	
	@Override
	public void setQuizId(int quizId) {
		
		this.quizId = quizId;
		
	}
	
	@Override
	public int getQuizId() {
		
		return quizId;
		
	}
	
	@Override
	public int getScore() {
		
		return score;
		
	}

	@Override
	public void incrementScore() {
		
		score++;
		incrementNumberOfQuestionsAnswered();
		
	}
	
	private void incrementNumberOfQuestionsAnswered() {
		
		numberOfQuestionsAnswered++;
		
	}
	
	@Override
	public int getNumberOfQuestionsAnswered() {
		
		return numberOfQuestionsAnswered;
		
	}
	
	@Override
	public QueAndAns[] getQueAndAns() {
		
		QueAndAns[] result = null;
		
		try {
						
			if (queAndAns == null) {
			
				System.out.println("No list of questions and answers created yet");
				throw new NullPointerException();
			
			}
			
			result = this.queAndAns;
		
		} catch (NullPointerException e) {
			
			e.printStackTrace();
			
		}
		
		return result;
		
	}

	@Override
	public boolean getQuizIsActive() {
	
		return quizIsActive;
	
	}

	@Override
	public void setQuizIsActive(boolean quizIsActive) {

		this.quizIsActive = quizIsActive;
		
	}

}
