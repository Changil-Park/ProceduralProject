package dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

import entity.ELecture;

public class DAOBasket {
	Vector<ELecture> basketItems = new Vector<ELecture>();

	public void addLectures (Vector<ELecture> eLectures, String userId) throws IOException { //���Ͽ� �߰�
		
		for(int i=0; i<basketItems.size(); i++) {//�ߺ� ���� ����
			ELecture basketItem = basketItems.get(i);
			for(int j=0; j<eLectures.size(); j++) {
				ELecture eLecture = eLectures.get(j);
				if(basketItem.getNumber()== eLecture.getNumber()) {
					eLectures.remove(eLecture);
				}
			}
		}
		
		
		for (ELecture eLecture : eLectures) {
			FileWriter fw = new FileWriter("data/basket" + userId, true);
			fw.write(eLecture.getNumber() + " " + eLecture.getName() + " " + eLecture.getProfessor() + " "
					+ eLecture.getCredit() + " " + eLecture.getTime() + "\r\n");

			fw.close();
		}
}
	
	public Vector<ELecture> getLectures(String userId) throws FileNotFoundException {// ���Ͽ� �߰��Ѱ� �޾ƿ���
		basketItems.removeAllElements();
		Scanner sc = new Scanner(new FileReader("data/basket" + userId));
		while (sc.hasNext()) {
			ELecture basketItem = new ELecture();
			basketItem.read(sc);
			basketItems.add(basketItem);

		}
		sc.close();

		return basketItems;
	}

	public void delete(Vector<ELecture> lectures, String id) throws IOException {
		for(ELecture lecture : lectures) {
			basketItems.remove(lecture); // ������ ���ǵ��� ���������� ����Ʈ���� ����
		}
		
		FileWriter fw = new FileWriter("data/basket" + id, false); //���Ͽ����� false�ϱ� �� ������� ���� ���ǵ鸸 ���Ͽ� �ٽ� ����.
		for (ELecture eLecture : basketItems) {
			fw.write(eLecture.getNumber() + " " + eLecture.getName() + " " + eLecture.getProfessor() + " "
					+ eLecture.getCredit() + " " + eLecture.getTime() + "\r\n");
		}
		fw.close();
		
	}
}
