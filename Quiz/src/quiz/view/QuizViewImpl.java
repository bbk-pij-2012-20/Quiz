package quiz.view;

public class QuizViewImpl implements QuizView {

	String view = "";
	
	@Override
	public synchronized void updateSetUpView(String view) {
	
		this.view += view;
		
	}
	
	@Override
	public synchronized void updatePlayerView(String view) {
		
		this.view += view;
		
	}
	
	@Override
	public String toString() {
		
		return view;
		
	}
	
}
