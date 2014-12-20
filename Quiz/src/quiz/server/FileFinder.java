package quiz.server;

import java.io.File;

public class FileFinder {
	
	public static void main(String[] args){
		FileFinder ff = new FileFinder();
		ff.launch();
	}
	
	private void launch(){
		File f = new File("src/QSecurity.policy");
		System.out.println(f.exists());
	}
	

	
}
