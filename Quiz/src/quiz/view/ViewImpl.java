package quiz.view;

public class ViewImpl implements View {

	String view = "";
	
	@Override
	public synchronized void updateSetUpView(String view) {
	
		this.view += view;
		
	}
	
	@Override
	public synchronized void updatePlayerView(String view) {
		
		this.view += view;
		
	}
	
}
