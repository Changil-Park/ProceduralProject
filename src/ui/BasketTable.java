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

		// attributes ���� ��

		// service ��Ʈ�ѷ��� ����

		// model JList�� �����ִ� �����͸� �����ϰ� ����
		String[] columnNames = { "���¸�", "��米��", "����", "�ð�" };
		this.tableModel = new DefaultTableModel(null, columnNames) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int i, int c) {
				return false;
			}
		};
		Color color = new Color(204, 255, 255);
		this.getTableHeader().setFont(new Font("���", Font.BOLD, 15));
		this.setModel(tableModel);
		this.getTableHeader().setReorderingAllowed(false); // �� �̵��Ұ�
		this.getTableHeader().setResizingAllowed(false); // �� �ʺ� �����Ұ�
		this.getTableHeader().setBackground(color);

		try {
			Vector<ELecture> lectures = cBasket.getLectures(id);// ���α׷� ���� �� ó�� �����ִ� ��
			this.refresh(lectures);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			
		}
		CenterAlignedTableCellRenderer renderer = new CenterAlignedTableCellRenderer();
		this.setDefaultRenderer(this.getColumnClass(0), renderer); // ���̺� ���� ����� ����

	}

	private class CenterAlignedTableCellRenderer extends DefaultTableCellRenderer {
		private static final long serialVersionUID = 1L;

		public CenterAlignedTableCellRenderer() {
			this.setHorizontalAlignment(SwingConstants.CENTER);
		}
	}

	public Vector<ELecture> getSelectedLectures() {
		Vector<ELecture> selected = new Vector<>(); // ������ ���Ǹ� ������ ����
		int[] selectedrow = this.getSelectedRows(); // ������ ������ index
		for (int n = 0; n < selectedrow.length; n++) {
			ELecture select = this.storedLectures.get(selectedrow[n]); // ������ ������ ����(1��)
			selected.add(select);

		}

		return selected; // ������ ���� ��ü
	}

	public void refresh(Vector<ELecture> refreshbring) throws FileNotFoundException {// ������ ���̺��̴ϱ� �����͸� �� �ʿ������ void

		this.tableModel.setRowCount(0); // ���̺� �ִ� �� �� ����

		storedLectures = refreshbring;

		for (ELecture lecture : storedLectures) {
			Vector<String> row = new Vector<>();
			row.add(lecture.getName());
			row.add(lecture.getProfessor());
			row.add(Integer.toString(lecture.getCredit()));
			row.add(lecture.getTime());
			this.tableModel.addRow(row);
			this.setAutoCreateRowSorter(true); //�ٽ� �޾ƿͼ� �� �׸�.
		}
		this.updateUI();// updateUI�� �����͸� �����ͼ� �ٽñ׷��� ��� ��, refresh�� �׳� �ٽ� �׸���� ��

	}

}
