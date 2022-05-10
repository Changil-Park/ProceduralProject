package ui;

import java.awt.Color;
import java.awt.Font;
import java.io.FileNotFoundException;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import control.CBasket;
import entity.ELecture;

public class BasketTable extends JTable {
	Vector<ELecture> storedLectures;
	private static final long serialVersionUID = 1L;
	private DefaultTableModel tableModel;

	// model

	public BasketTable(CBasket cBasket, String id) {

		// attributes 고유 값

		// service 컨트롤러와 연결

		// model JList가 보여주는 데이터를 보유하고 관리
		String[] columnNames = { "강좌명", "담당교수", "학점", "시간" };
		this.tableModel = new DefaultTableModel(null, columnNames) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int i, int c) {
				return false;
			}
		};
		Color color = new Color(204, 255, 255);
		this.getTableHeader().setFont(new Font("고딕", Font.BOLD, 15));
		this.setModel(tableModel);
		this.getTableHeader().setReorderingAllowed(false); // 열 이동불가
		this.getTableHeader().setResizingAllowed(false); // 열 너비 조정불가
		this.getTableHeader().setBackground(color);

		try {
			Vector<ELecture> lectures = cBasket.getLectures(id);// 프로그램 켰을 때 처음 보여주는 것
			this.refresh(lectures);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			
		}
		CenterAlignedTableCellRenderer renderer = new CenterAlignedTableCellRenderer();
		this.setDefaultRenderer(this.getColumnClass(0), renderer); // 테이블 내용 가운데로 정렬

	}

	private class CenterAlignedTableCellRenderer extends DefaultTableCellRenderer {
		private static final long serialVersionUID = 1L;

		public CenterAlignedTableCellRenderer() {
			this.setHorizontalAlignment(SwingConstants.CENTER);
		}
	}

	public Vector<ELecture> getSelectedLectures() {
		Vector<ELecture> selected = new Vector<>(); // 선택한 강의를 저장할 공간
		int[] selectedrow = this.getSelectedRows(); // 선택한 강의의 index
		for (int n = 0; n < selectedrow.length; n++) {
			ELecture select = this.storedLectures.get(selectedrow[n]); // 실제로 선택한 강의(1개)
			selected.add(select);

		}

		return selected; // 선택한 강의 전체
	}

	public void refresh(Vector<ELecture> refreshbring) throws FileNotFoundException {// 어차피 테이블이니까 데이터를 줄 필요없으니 void

		this.tableModel.setRowCount(0); // 테이블에 있는 것 다 지움

		storedLectures = refreshbring;

		for (ELecture lecture : storedLectures) {
			Vector<String> row = new Vector<>();
			row.add(lecture.getName());
			row.add(lecture.getProfessor());
			row.add(Integer.toString(lecture.getCredit()));
			row.add(lecture.getTime());
			this.tableModel.addRow(row);
			this.setAutoCreateRowSorter(true); //다시 받아와서 쫙 그림.
		}
		this.updateUI();// updateUI는 데이터를 가져와서 다시그려라 라는 뜻, refresh는 그냥 다시 그리라는 뜻

	}

}
