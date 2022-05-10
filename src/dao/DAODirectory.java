package dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

import entity.EDirectory;

public class DAODirectory {
	

	public Vector<EDirectory> getItems(String fileName) throws FileNotFoundException {
		Vector<EDirectory> eDirectories = new Vector<EDirectory>();
		Scanner sc = new Scanner(new File(fileName));

		while (sc.hasNext()) {
			EDirectory eDirectory = new EDirectory();
			eDirectory.read(sc);
			eDirectories.add(eDirectory);
		}
		sc.close();
		return eDirectories;

	}

}
