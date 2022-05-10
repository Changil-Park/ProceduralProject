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
		font = new Font("고딕", Font.BOLD, 13);

		File f3 = new File("image/myongjiicon.png");
		try {
			img = ImageIO.read(f3);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		setIconImage(img); // 명지 아이콘 설정

		windownListener = new WindowHandler();// 윈도우 핸들러
		this.addWindowListener(windownListener);

		this.id = id;
		actionListener = new ActionHandler();

		this.setTitle("명지대학교 수강신청 시스템");
		cBasket = new CBasket();
		cSincheong = new CSincheong();
		eLectures = new Vector<ELecture>();
		lectures = new Vector<ELecture>();

		toolbarPanel = new JPanel();
		refreshLabel = new JLabel();

		LayoutManager layoutManager = new BorderLayout();
		this.setLayout(layoutManager);

		this.setMinimumSize((new Dimension(410, 0)));// 최소로 줄일 수 있는 x사이즈 410, y는 자유

		this.setSize(900, 670);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// 상단바 패널
		JPanel upPanel = new JPanel();
		upPanel.setLayout(new BoxLayout(upPanel, BoxLayout.Y_AXIS));// 박스 레이아웃,수직정렬
		this.add(upPanel, BorderLayout.NORTH);
		// 로그아웃버튼 패널

		this.logoutPanel = new LogoutPanel(name, actionListener);
		upPanel.add(this.logoutPanel); // 로그아웃+인사
		// 툴바 패널
		JToolBar tool = new JToolBar("Menu");
		tool.setBackground(new Color(0X44CCCCFF));
		toolbarPanel.add(tool);
		toolbarPanel.setBackground(new Color(0X44CCCCFF));

		refreshLabel = new JLabel();
		refreshLabel.setFont(font);
		refreshLabel.setText("글씨 색상 변경");
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
		this.add(this.selectionPanel, BorderLayout.WEST);// 리스트+테이블

		this.buttonpanel1 = new ButtonPanel(actionListener);
		this.add(this.buttonpanel1, BorderLayout.SOUTH); // 장바구니,신청 버튼

		this.buttonpanel2 = new ButtonPanel2(actionListener);
		this.add(this.buttonpanel2, BorderLayout.CENTER);// 와리가리 버튼

		this.basketapplypanel = new BasketApplyPanel(cBasket, cSincheong, id);
		this.add(this.basketapplypanel, BorderLayout.EAST);// 장바구니(담기,삭제),신청(담기,삭제)

	}

	private void addBasketLectures() {// 장바구니 담기
		try {
			eLectures = this.selectionPanel.lecture.getSelectedLectures(); // 선택 강좌
			this.cBasket.addLectures(eLectures, id); // 강좌를 추가해서 파일에 저장

			Vector<ELecture> refreshbring = cBasket.getLectures(id); // 파일 내용 가져오기
			this.basketapplypanel.basketTable.refresh(refreshbring); // 파일에 있는 강좌 그리기

			eLectures.removeAllElements(); // 중복으로 강좌추가 안되게 벡터 안 요소 제거
			lectures.removeAllElements();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	private void deleteBasketLectures() { // 장바구니 삭제
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

	private void addSincheongLectures() {// 신청
		try {
			eLectures = this.selectionPanel.lecture.getSelectedLectures(); // 선택 강좌
			this.cSincheong.addLectures(eLectures, id); // 강좌를 추가해서 파일에 저장

			Vector<ELecture> refreshbring = cSincheong.getLectures(id); // 파일 내용 가져오기
			this.basketapplypanel.applyTable.refresh(refreshbring); // 파일에 있는 강좌 그리기

			eLectures.removeAllElements(); // 중복으로 강좌추가 안되게 벡터 안 요소 제거
			lectures.removeAllElements();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	private void deleteSincheongLectures() {// 신청 삭제
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

	private void upLectures() { // 신청>장바구니
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

	private void downLectures() {// 장바구니>신청
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

	private void changeColor() {// 색 변경
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
			JOptionPane.showMessageDialog(null, "로그아웃 되었습니다.", "로그아웃", JOptionPane.PLAIN_MESSAGE);
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

	private class ActionHandler implements ActionListener { // 메인 핸들러

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
				JOptionPane.showMessageDialog(null, "로그아웃 되었습니다.", "로그아웃", JOptionPane.PLAIN_MESSAGE);
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
