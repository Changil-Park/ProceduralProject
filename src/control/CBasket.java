
package control;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Vector;

import dao.DAOBasket;
import entity.ELecture;

public class CBasket {
	private DAOBasket dAOBasket;

	public CBasket() {
		this.dAOBasket = new DAOBasket();
	}

	public void addLectures(Vector<ELecture> eLectures, String userId) throws IOException {

		dAOBasket.addLectures(eLectures, userId);
	}

	public Vector<ELecture> getLectures(String userId) throws FileNotFoundException { // print
		
		return dAOBasket.getLectures(userId);

	}

	public void delete(Vector<ELecture>lectures,String id) throws IOException {
		 dAOBasket.delete(lectures,id);
	}
}
