package quiz;

import java.rmi.RemoteException;

public interface QuizFactory {

	/**
	 * 
	 * @return
	 * @throws RemoteException
	 */
	QueAndAns[] getListOfQAndALists() throws RemoteException;

	/**
	 * 
	 * @param listOfQAndALists
	 * @throws RemoteException
	 */
	void setListOfQAndALists(QueAndAns[] listOfQAndALists) throws RemoteException;
	
	/**
	 * 
	 * @return
	 * @throws RemoteException
	 */
	int getNoOfAnswersPerQuestion() throws RemoteException;

	/**
	 * 
	 * @return
	 * @throws RemoteException
	 */
	int getTotalNoOfQuestions() throws RemoteException;

}
