package quiz;

import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
//import java.io.Serializable;

public class SetUpServer extends UnicastRemoteObject implements SetUpService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected SetUpServer() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

}
