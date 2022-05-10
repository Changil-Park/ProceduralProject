package control;

import java.io.FileNotFoundException;
import java.util.Vector;

import dao.DAODirectory;
import entity.EDirectory;


public class CDirectory {
	private DAODirectory dAODirectory;
	
	public CDirectory() {
		this.dAODirectory = new DAODirectory();
	}

	public Vector<EDirectory> getItems(String filename) throws FileNotFoundException {
		
		return this.dAODirectory.getItems(filename) ;
	}


	
}
