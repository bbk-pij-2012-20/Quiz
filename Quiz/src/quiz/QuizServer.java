package quiz;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.io.Serializable;

public class QuizServer extends UnicastRemoteObject implements QuizService {

	protected QuizServer() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setUpQuiz(int numberOfQuestions, int numberOfAnswersPerQuestions)
			throws RemoteException {
		// TODO Auto-generated method stub

	}

	@Override
	public void playQuiz() throws RemoteException {
		// TODO Auto-generated method stub

	}

}
