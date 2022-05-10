package control;

import java.io.FileNotFoundException;
import java.util.Vector;

import dao.DAOLecture;
import entity.ELecture;

public class CLecture {
	private DAOLecture dAOLecture;

	public CLecture() {
		this.dAOLecture = new DAOLecture();
	}

	public Vector<ELecture> getItems(String filename) throws FileNotFoundException {

		return this.dAOLecture.getItems(filename);
	}

}
