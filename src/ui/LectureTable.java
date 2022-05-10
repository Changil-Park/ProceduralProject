package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.io.FileNotFoundException;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import control.CLecture;
import entity.ELecture;


public class LectureTable extends JTable {
	private static final long serialVersionUID = 1L;
	private DefaultTableModel tableModel;
	private CLecture cLecture;
	private Vector<ELecture> eLectures;
	// model

	public LectureTable() {
		// attributes 고유 값
		this.setPreferredSize(new Dimension(100, 200)); 
		// service 컨트롤러와 연결
		this.cLecture = new CLecture();
		// model JList가 보여주는 데이터를 보유하고 관리
		String[] columnNames = { "강좌명", "담당교수","학점", "시간" };
		this.tableModel = new DefaultTableModel(null, columnNames) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int i, int c) {//테이블 내용 수정불가
				return false;
			}
		};
		this.getTableHeader().setFont(new Font("고딕", Font.BOLD , 15));
		this.setModel(tableModel);
		this.getTableHeader().setReorderingAllowed(false); //열 이동불가
	    this.getTableHeader().setResizingAllowed(false); //열 너비 조정불가
		Color color=new Color(204,255,255);	
		this.getTableHeader().setBackground(color);

		CenterAlignedTableCellRenderer renderer = new CenterAlignedTableCellRenderer();
		this.setDefaultRenderer(this.getColumnClass(0), renderer); // 가운데로 정렬
	}
	private class CenterAlignedTableCellRenderer extends DefaultTableCellRenderer {
		private static final long serialVersionUID = 1L;

		public CenterAlignedTableCellRenderer() {
			this.setHorizontalAlignment(SwingConstants.CENTER);
		}
	}

	public Vector<ELecture> getSelectedLectures() {
		Vector<ELecture> selected = new Vector<>();
		int[] selectedrow = this.getSelectedRows();
		for (int n = 0; n < selectedrow.length; n++) {
			ELecture select = this.eLectures.get(selectedrow[n]);
			selected.add(select);
		}

		return selected;
	}

	public void refresh(String fileName) throws FileNotFoundException {// 어차피 테이블이니까 데이터를 줄 필요없으니 void
		this.eLectures = this.cLecture.getItems(fileName);// service해서 eLecture벡터 가져옴

		this.tableModel.setRowCount(0);
		for (ELecture eLecture : this.eLectures) {
			Vector<String> row = new Vector<String>();
			row.add(eLecture.getName());
			row.add(eLecture.getProfessor());
			row.add(Integer.toString(eLecture.getCredit()));
			row.add(eLecture.getTime());
			tableModel.addRow(row);
			this.setAutoCreateRowSorter(true); //순서대로 정렬
		}

		this.updateUI();// updateUI는 데이터를 가져와서 다시그려라 라는 뜻, refresh는 그냥 다시 그리라는 뜻

	}

}
