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
		// attributes ���� ��
		this.setPreferredSize(new Dimension(100, 200)); 
		// service ��Ʈ�ѷ��� ����
		this.cLecture = new CLecture();
		// model JList�� �����ִ� �����͸� �����ϰ� ����
		String[] columnNames = { "���¸�", "��米��","����", "�ð�" };
		this.tableModel = new DefaultTableModel(null, columnNames) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int i, int c) {//���̺� ���� �����Ұ�
				return false;
			}
		};
		this.getTableHeader().setFont(new Font("���", Font.BOLD , 15));
		this.setModel(tableModel);
		this.getTableHeader().setReorderingAllowed(false); //�� �̵��Ұ�
	    this.getTableHeader().setResizingAllowed(false); //�� �ʺ� �����Ұ�
		Color color=new Color(204,255,255);	
		this.getTableHeader().setBackground(color);

		CenterAlignedTableCellRenderer renderer = new CenterAlignedTableCellRenderer();
		this.setDefaultRenderer(this.getColumnClass(0), renderer); // ����� ����
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

	public void refresh(String fileName) throws FileNotFoundException {// ������ ���̺��̴ϱ� �����͸� �� �ʿ������ void
		this.eLectures = this.cLecture.getItems(fileName);// service�ؼ� eLecture���� ������

		this.tableModel.setRowCount(0);
		for (ELecture eLecture : this.eLectures) {
			Vector<String> row = new Vector<String>();
			row.add(eLecture.getName());
			row.add(eLecture.getProfessor());
			row.add(Integer.toString(eLecture.getCredit()));
			row.add(eLecture.getTime());
			tableModel.addRow(row);
			this.setAutoCreateRowSorter(true); //������� ����
		}

		this.updateUI();// updateUI�� �����͸� �����ͼ� �ٽñ׷��� ��� ��, refresh�� �׳� �ٽ� �׸���� ��

	}

}
