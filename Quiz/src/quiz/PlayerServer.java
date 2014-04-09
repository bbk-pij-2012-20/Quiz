package quiz;

import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
//import java.io.Serializable;

public class PlayerServer extends UnicastRemoteObject implements PlayerService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected PlayerServer() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

}
