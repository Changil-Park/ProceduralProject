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
	private DirectoryList college; // ������ ���׸�Ʈ ����,�� ������ ���α׷��� ���� �� �� ����
	private DirectoryList department;
	public LectureTable lecture;
	private JLabel schoolinformaion, schoolinformaion2;
	private Font font;

	// �������� �Ҵ�� �޸� �ּҴ� stack�� �����. Ŭ������ �����͸� ���� �ʿ䰡 ����.
	// ��Ȯ�� �ڼ��ϰ� �����ڸ� �� Ŭ������ ���ǵ� �Լ��� ȣ���ϱ� ���ؼ� �ּҸ� �����صδ� ���̴�.
	// �Ҵ�� �޸� �ȿ� �ִ� �Լ��� ȣ���Ѵ�. �Լ��� ���¸� �� ������ �ֱ� ������
	// ���丮 ����Ʈ 3������ ������ �� �ּҰ� ������ �ִ� ������ ���� �� �ٸ��� ������ 3������.
	private ListSelectionHandler listSelectionListener;

	public SelectionPanel() {// �ڵ� ���׸�Ʈ > ���, ��깮(�Ҵ繮)

		font = new Font("���", Font.BOLD, 13);

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
		this.add(scrollpane); // scrollpane���� �μ�����

		this.setPreferredSize(new Dimension(400, 400));
		this.refresh(null);

		schoolinformaion = new JLabel("�ι�ķ�۽� (��)03674 ����Ư���� ���빮�� �źϰ�� 34");
		schoolinformaion.setFont(font);
		this.add(schoolinformaion);

		schoolinformaion2 = new JLabel("�ڿ�ķ�۽� (��)17058 ��⵵ ���ν� ó�α� ������ 116");
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
				// refresh�� �׸� �ٽ� �׷��� + scrollPane
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

	private class ListSelectionHandler implements ListSelectionListener {// ListSelectionListener�� �ڹٿ� �̹� �ִ� �������̽��� �̰�
																			// ��ӹ޾Ƽ� ����
		// ��� Ŭ������ ~~~~Handler��� �Ѵ�.
		@Override
		public void valueChanged(ListSelectionEvent event) {
			refresh(event.getSource());
			// String fileName = directoryPanel.refresh(e.getSource());
			// lecturePanel.refresh(fileName);
			// ListSelectionListener�� interface��
			// scroll pane���� �ٸ����
		}
	}
}