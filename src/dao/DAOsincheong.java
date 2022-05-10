package dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

import entity.ELecture;

public class DAOsincheong {
	public Vector<ELecture> sincheongItems = new Vector<ELecture>();

public void addLectures (Vector<ELecture> eLectures, String userId) throws IOException {
	//sincheongItems= 기존에 추가 되어있는 것, eLectures=새로 받아온거
	for(int i=0; i<sincheongItems.size(); i++) { //중복강의 체크 이름으로 비교
		ELecture sincheongitem = sincheongItems.get(i);
		for(int j=0; j<eLectures.size(); j++) {
			ELecture eLecture = eLectures.get(j);
			if(sincheongitem.getNumber() == eLecture.getNumber()) {
				eLectures.remove(eLecture);
			}
		}
	}
		
		for (ELecture eLecture : eLectures) {
			FileWriter fw = new FileWriter("data/sincheong" + userId, true);
			fw.write(eLecture.getNumber() + " " + eLecture.getName() + " " + eLecture.getProfessor() + " "
					+ eLecture.getCredit() + " " + eLecture.getTime() + "\r\n");

			fw.close();
		}
}
	
	public Vector<ELecture> getLectures(String userId) throws FileNotFoundException {
		sincheongItems.removeAllElements();
		Scanner sc = new Scanner(new FileReader("data/sincheong" + userId));
		
		while (sc.hasNext()) {
			ELecture sincheongItem = new ELecture();
			sincheongItem.read(sc);
			sincheongItems.add(sincheongItem);

		}
		sc.close();
		return sincheongItems;
	}

	public void delete(Vector<ELecture> lectures, String id) throws IOException {
		for(ELecture lecture : lectures) {
			sincheongItems.remove(lecture); // 선택한 강의들을 순차적으로 리스트에서 제거
		}
		
		FileWriter fw = new FileWriter("data/sincheong" + id, false);
		for (ELecture eLecture : sincheongItems) {
			fw.write(eLecture.getNumber() + " " + eLecture.getName() + " " + eLecture.getProfessor() + " "
					+ eLecture.getCredit() + " " + eLecture.getTime() + "\r\n");
		}
		fw.close();
		
	}
}
