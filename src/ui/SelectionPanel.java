package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.LayoutManager;
import java.io.FileNotFoundException;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class SelectionPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private DirectoryList campus;
	private DirectoryList college; // 데이터 세그먼트 스택,힙 힙에는 프로그램이 실행 된 후 저장
	private DirectoryList department;
	public LectureTable lecture;
	private JLabel schoolinformaion, schoolinformaion2;
	private Font font;

	// 정적으로 할당된 메모리 주소는 stack에 저장됨. 클래스는 포인터를 가질 필요가 없다.
	// 정확히 자세하게 말하자면 그 클래스에 정의된 함수를 호출하기 위해서 주소를 저장해두는 것이다.
	// 할당된 메모리 안에 있는 함수를 호출한다. 함수는 상태를 안 가지고 있기 때문에
	// 디렉토리 리스트 3개만든 이유가 그 주소가 가지고 있는 데이터 값이 다 다르기 때문에 3개만듬.
	private ListSelectionHandler listSelectionListener;

	public SelectionPanel() {// 코드 세그먼트 > 제어문, 계산문(할당문)

		font = new Font("고딕", Font.BOLD, 13);

		setBackground(Color.WHITE);
		this.listSelectionListener = new ListSelectionHandler();

		LayoutManager layoutManager = new FlowLayout(FlowLayout.LEFT, 10, 10);

		this.setLayout(layoutManager);

		this.setSize(400, 600);

		JScrollPane scrollpane = new JScrollPane();
		this.campus = new DirectoryList(this.listSelectionListener);
		scrollpane.setViewportView(this.campus);
		this.add(scrollpane);

		scrollpane = new JScrollPane();
		this.college = new DirectoryList(this.listSelectionListener);
		scrollpane.setViewportView(this.college);
		this.add(scrollpane);

		scrollpane = new JScrollPane();
		this.department = new DirectoryList(this.listSelectionListener);
		scrollpane.setViewportView(this.department);
		this.add(scrollpane);

		scrollpane = new JScrollPane();
		this.lecture = new LectureTable();
		scrollpane.setViewportView(this.lecture);
		scrollpane.setPreferredSize(new Dimension(380, 200));
		this.add(scrollpane); // scrollpane으로 싸서붙임

		this.setPreferredSize(new Dimension(400, 400));
		this.refresh(null);

		schoolinformaion = new JLabel("인문캠퍼스 (우)03674 서울특별시 서대문구 거북골로 34");
		schoolinformaion.setFont(font);
		this.add(schoolinformaion);

		schoolinformaion2 = new JLabel("자연캠퍼스 (우)17058 경기도 용인시 처인구 명지로 116");
		schoolinformaion2.setFont(font);
		this.add(schoolinformaion2);
	}

	private void refresh(Object source) { // event.getSource() = source

		try {
			if (source == null) {
				String fileName = this.campus.refresh("root");
				fileName = this.college.refresh(fileName);
				fileName = this.department.refresh(fileName);
				this.lecture.refresh(fileName);
			} else if (source == this.campus) {
				// refresh는 그림 다시 그려라 + scrollPane
				String fileName = this.campus.getSelectedFileName();
				fileName = this.college.refresh(fileName);
				fileName = this.department.refresh(fileName);
				this.lecture.refresh(fileName);
			} else if (source == this.college) {
				String fileName = this.college.getSelectedFileName();
				fileName = this.department.refresh(fileName);
				this.lecture.refresh(fileName);
			} else if (source == this.department) {
				String fileName = this.department.getSelectedFileName();
				this.lecture.refresh(fileName);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private class ListSelectionHandler implements ListSelectionListener {// ListSelectionListener는 자바에 이미 있는 인터페이스고 이걸
																			// 상속받아서 만든
		// 모든 클래스는 ~~~~Handler라고 한다.
		@Override
		public void valueChanged(ListSelectionEvent event) {
			refresh(event.getSource());
			// String fileName = directoryPanel.refresh(e.getSource());
			// lecturePanel.refresh(fileName);
			// ListSelectionListener은 interface임
			// scroll pane으로 줄만들기
		}
	}
}