package quiz;

import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.io.Serializable;

public class PlayerServer extends UnicastRemoteObject implements PlayerService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7708290464329235363L;

	protected PlayerServer() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public char[] getPlayerView(char playerInput) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isGameOver() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public char[] updateView(String readLine) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getGameList() {
		// TODO Auto-generated method stub
		return null;
	}

}
