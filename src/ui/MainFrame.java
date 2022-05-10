package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import control.CBasket;
import control.CSincheong;
import entity.ELecture;

public class MainFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private SelectionPanel selectionPanel;
	private ButtonPanel buttonpanel1;
	private ButtonPanel2 buttonpanel2;
	private ActionListener actionListener;
	private String id;
	private CBasket cBasket;
	private CSincheong cSincheong;
	private Vector<ELecture> eLectures;
	private Vector<ELecture> lectures;
	private BasketApplyPanel basketapplypanel;
	private LogoutPanel logoutPanel;
	private Image img = null;
	private JPanel toolbarPanel;
	private JLabel refreshLabel;
	@SuppressWarnings("rawtypes")
	private JComboBox combobox;
	private Font font;
	private WindowListener windownListener;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public MainFrame(String id, String name) {
		font = new Font("���", Font.BOLD, 13);

		File f3 = new File("image/myongjiicon.png");
		try {
			img = ImageIO.read(f3);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		setIconImage(img); // ���� ������ ����

		windownListener = new WindowHandler();// ������ �ڵ鷯
		this.addWindowListener(windownListener);

		this.id = id;
		actionListener = new ActionHandler();

		this.setTitle("�������б� ������û �ý���");
		cBasket = new CBasket();
		cSincheong = new CSincheong();
		eLectures = new Vector<ELecture>();
		lectures = new Vector<ELecture>();

		toolbarPanel = new JPanel();
		refreshLabel = new JLabel();

		LayoutManager layoutManager = new BorderLayout();
		this.setLayout(layoutManager);

		this.setMinimumSize((new Dimension(410, 0)));// �ּҷ� ���� �� �ִ� x������ 410, y�� ����

		this.setSize(900, 670);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// ��ܹ� �г�
		JPanel upPanel = new JPanel();
		upPanel.setLayout(new BoxLayout(upPanel, BoxLayout.Y_AXIS));// �ڽ� ���̾ƿ�,��������
		this.add(upPanel, BorderLayout.NORTH);
		// �α׾ƿ���ư �г�

		this.logoutPanel = new LogoutPanel(name, actionListener);
		upPanel.add(this.logoutPanel); // �α׾ƿ�+�λ�
		// ���� �г�
		JToolBar tool = new JToolBar("Menu");
		tool.setBackground(new Color(0X44CCCCFF));
		toolbarPanel.add(tool);
		toolbarPanel.setBackground(new Color(0X44CCCCFF));

		refreshLabel = new JLabel();
		refreshLabel.setFont(font);
		refreshLabel.setText("�۾� ���� ����");
		tool.add(refreshLabel);
		upPanel.add(this.toolbarPanel);

		combobox = new JComboBox();
		combobox.addItem("Default");
		combobox.addItem("Red");
		combobox.addItem("Blue");
		combobox.addItem("Green");

//	      combo.setFont(font);
		combobox.setBackground(Color.white);
		combobox.addActionListener(actionListener);
		combobox.setActionCommand("combo");
		tool.add(combobox);

		this.selectionPanel = new SelectionPanel();
		this.add(this.selectionPanel, BorderLayout.WEST);// ����Ʈ+���̺�

		this.buttonpanel1 = new ButtonPanel(actionListener);
		this.add(this.buttonpanel1, BorderLayout.SOUTH); // ��ٱ���,��û ��ư

		this.buttonpanel2 = new ButtonPanel2(actionListener);
		this.add(this.buttonpanel2, BorderLayout.CENTER);// �͸����� ��ư

		this.basketapplypanel = new BasketApplyPanel(cBasket, cSincheong, id);
		this.add(this.basketapplypanel, BorderLayout.EAST);// ��ٱ���(���,����),��û(���,����)

	}

	private void addBasketLectures() {// ��ٱ��� ���
		try {
			eLectures = this.selectionPanel.lecture.getSelectedLectures(); // ���� ����
			this.cBasket.addLectures(eLectures, id); // ���¸� �߰��ؼ� ���Ͽ� ����

			Vector<ELecture> refreshbring = cBasket.getLectures(id); // ���� ���� ��������
			this.basketapplypanel.basketTable.refresh(refreshbring); // ���Ͽ� �ִ� ���� �׸���

			eLectures.removeAllElements(); // �ߺ����� �����߰� �ȵǰ� ���� �� ��� ����
			lectures.removeAllElements();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	private void deleteBasketLectures() { // ��ٱ��� ����
		// TODO Auto-generated method stub
		Vector<ELecture> bringed = this.basketapplypanel.basketTable.getSelectedLectures();
		try {
			cBasket.delete(bringed, id);
			Vector<ELecture> refreshbring = cBasket.getLectures(id);
			this.basketapplypanel.basketTable.refresh(refreshbring);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void addSincheongLectures() {// ��û
		try {
			eLectures = this.selectionPanel.lecture.getSelectedLectures(); // ���� ����
			this.cSincheong.addLectures(eLectures, id); // ���¸� �߰��ؼ� ���Ͽ� ����

			Vector<ELecture> refreshbring = cSincheong.getLectures(id); // ���� ���� ��������
			this.basketapplypanel.applyTable.refresh(refreshbring); // ���Ͽ� �ִ� ���� �׸���

			eLectures.removeAllElements(); // �ߺ����� �����߰� �ȵǰ� ���� �� ��� ����
			lectures.removeAllElements();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	private void deleteSincheongLectures() {// ��û ����
		// TODO Auto-generated method stub
		Vector<ELecture> bringed = this.basketapplypanel.applyTable.getSelectedLectures();
		try {
			cSincheong.delete(bringed, id);
			Vector<ELecture> refreshbring = cSincheong.getLectures(id);
			this.basketapplypanel.applyTable.refresh(refreshbring);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void upLectures() { // ��û>��ٱ���
		// TODO Auto-generated method stub
		Vector<ELecture> bringed = this.basketapplypanel.applyTable.getSelectedLectures();
		try {
			cBasket.addLectures(bringed, id);
			cSincheong.delete(bringed, id);
			Vector<ELecture> refreshbring = cSincheong.getLectures(id);
			this.basketapplypanel.applyTable.refresh(refreshbring);
			refreshbring = cBasket.getLectures(id);
			this.basketapplypanel.basketTable.refresh(refreshbring);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void downLectures() {// ��ٱ���>��û
		// TODO Auto-generated method stub
		Vector<ELecture> bringed = this.basketapplypanel.basketTable.getSelectedLectures();
		try {
			cSincheong.addLectures(bringed, id);
			cBasket.delete(bringed, id);
			Vector<ELecture> refreshbring = cSincheong.getLectures(id);
			this.basketapplypanel.applyTable.refresh(refreshbring);
			refreshbring = cBasket.getLectures(id);
			this.basketapplypanel.basketTable.refresh(refreshbring);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void changeColor() {// �� ����
		int index = combobox.getSelectedIndex();
		if (index == 0) {
			basketapplypanel.basketname.setForeground(Color.BLACK);
			basketapplypanel.applyname.setForeground(Color.BLACK);
			logoutPanel.logout.setForeground(Color.BLACK);
			logoutPanel.homepage.setForeground(Color.BLACK);
			logoutPanel.greet.setForeground(Color.BLACK);
			buttonpanel1.btnBasketgo.setForeground(Color.BLACK);
			buttonpanel1.btnBasketDelete.setForeground(Color.BLACK);
			buttonpanel1.btnSincheonggo.setForeground(Color.BLACK);
			buttonpanel1.btnSincheongDelete.setForeground(Color.BLACK);
			buttonpanel2.btnUp.setForeground(Color.BLACK);
			buttonpanel2.btnDown.setForeground(Color.BLACK);
			refreshLabel.setForeground(Color.BLACK);
		} else if (index == 1) {
			basketapplypanel.basketname.setForeground(Color.RED);
			basketapplypanel.applyname.setForeground(Color.RED);
			logoutPanel.logout.setForeground(Color.RED);
			logoutPanel.homepage.setForeground(Color.RED);
			logoutPanel.greet.setForeground(Color.RED);
			buttonpanel1.btnBasketgo.setForeground(Color.RED);
			buttonpanel1.btnBasketDelete.setForeground(Color.RED);
			buttonpanel1.btnSincheonggo.setForeground(Color.RED);
			buttonpanel1.btnSincheongDelete.setForeground(Color.RED);
			buttonpanel2.btnUp.setForeground(Color.RED);
			buttonpanel2.btnDown.setForeground(Color.RED);
			refreshLabel.setForeground(Color.RED);
		} else if (index == 2) {
			basketapplypanel.basketname.setForeground(Color.BLUE);
			basketapplypanel.applyname.setForeground(Color.BLUE);
			logoutPanel.logout.setForeground(Color.BLUE);
			logoutPanel.homepage.setForeground(Color.BLUE);
			logoutPanel.greet.setForeground(Color.BLUE);
			buttonpanel1.btnBasketgo.setForeground(Color.BLUE);
			buttonpanel1.btnBasketDelete.setForeground(Color.BLUE);
			buttonpanel1.btnSincheonggo.setForeground(Color.BLUE);
			buttonpanel1.btnSincheongDelete.setForeground(Color.BLUE);
			buttonpanel2.btnUp.setForeground(Color.BLUE);
			buttonpanel2.btnDown.setForeground(Color.BLUE);
			refreshLabel.setForeground(Color.BLUE);
		} else if (index == 3) {
			basketapplypanel.basketname.setForeground(Color.GREEN);
			basketapplypanel.applyname.setForeground(Color.GREEN);
			logoutPanel.logout.setForeground(Color.GREEN);
			logoutPanel.homepage.setForeground(Color.GREEN);
			logoutPanel.greet.setForeground(Color.GREEN);
			buttonpanel1.btnBasketgo.setForeground(Color.GREEN);
			buttonpanel1.btnBasketDelete.setForeground(Color.GREEN);
			buttonpanel1.btnSincheonggo.setForeground(Color.GREEN);
			buttonpanel1.btnSincheongDelete.setForeground(Color.GREEN);
			buttonpanel2.btnUp.setForeground(Color.GREEN);
			buttonpanel2.btnDown.setForeground(Color.GREEN);
			refreshLabel.setForeground(Color.GREEN);
		}

	}

	class WindowHandler implements WindowListener {

		@Override
		public void windowActivated(WindowEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowClosed(WindowEvent e) {
			// TODO Auto-generated method stub
//			logout();
		}

		@Override
		public void windowClosing(WindowEvent e) {
			// TODO Auto-generated method stub
			JOptionPane.showMessageDialog(null, "�α׾ƿ� �Ǿ����ϴ�.", "�α׾ƿ�", JOptionPane.PLAIN_MESSAGE);
			LoginFrame loginFrame = new LoginFrame();
			loginFrame.setVisible(true);
			dispose();
		}

		@Override
		public void windowDeactivated(WindowEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowDeiconified(WindowEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowIconified(WindowEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowOpened(WindowEvent e) {
			// TODO Auto-generated method stub

		}

	}

	private class ActionHandler implements ActionListener { // ���� �ڵ鷯

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (e.getActionCommand() == "basket") {
				addBasketLectures();
			} else if (e.getActionCommand() == "basketdelete") {
				deleteBasketLectures();
			} else if (e.getActionCommand() == "sincheong") {
				addSincheongLectures();
			} else if (e.getActionCommand() == "sincheongdelete") {
				deleteSincheongLectures();
			} else if (e.getActionCommand() == "Logout") {
				JOptionPane.showMessageDialog(null, "�α׾ƿ� �Ǿ����ϴ�.", "�α׾ƿ�", JOptionPane.PLAIN_MESSAGE);
				LoginFrame loginFrame = new LoginFrame();
				loginFrame.setVisible(true);
				dispose();
			} else if (e.getActionCommand() == "homepage") {
				Runtime runtime = Runtime.getRuntime();
				try {
					runtime.exec("explorer.exe http://www.mju.ac.kr/");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			} else if (e.getActionCommand() == "up") {
				upLectures();
			} else if (e.getActionCommand() == "down") {
				downLectures();
			} else if (e.getActionCommand() == "combo") {
				changeColor();
			}

		}

	}

}
