package dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

import entity.ELecture;

public class DAOLecture {
	

	public Vector<ELecture> getItems(String filename) throws FileNotFoundException {
		Vector<ELecture> eItems = new Vector<ELecture>();
		Scanner sc = new Scanner(new File("data/" + filename));

		while (sc.hasNext()) {
			ELecture eItem = new ELecture();
			eItem.read(sc);
			eItems.addElement(eItem);
		}
		sc.close();
		return eItems;

	}

	

	
}
