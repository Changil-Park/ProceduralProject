package control;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Vector;

import dao.DAOsincheong;
import entity.ELecture;

public class CSincheong {
	private DAOsincheong dAOsincheong;

	public CSincheong() {
		this.dAOsincheong = new DAOsincheong();
	}

	public void addLectures(Vector<ELecture> eLectures, String userId) throws IOException {

		dAOsincheong.addLectures(eLectures, userId);
	}

	public Vector<ELecture> getLectures(String userId) throws FileNotFoundException { // print
		
		return dAOsincheong.getLectures(userId);

	}

	public void delete(Vector<ELecture>lectures,String id) throws IOException {
		dAOsincheong.delete(lectures,id);
	}

}
