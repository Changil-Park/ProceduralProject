package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import control.CLogin;
import exception.InvalidUserException;

@SuppressWarnings("serial")
public class LoginFrame extends JFrame {

	private JButton btnLogin, btnInit, btnSignUp;
	private JTextField IdText;
	private JPasswordField passText;
	private JLabel label;
	private Image img, img2 = null;
	private ActionListener actionListener;
	private SignUpFrame signUpFrame;
	private Font font;
	boolean validId,empty;

	public LoginFrame() {

		font = new Font("고딕", Font.BOLD, 15);

		actionListener = new ActionHandler();

		// setting
		setTitle("명지대학교 로그인");
		File f2 = new File("image/myongjiicon.png");
		try {
			img2 = ImageIO.read(f2);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		setIconImage(img2); // 명지 아이콘 설정

		setResizable(false);
		setSize(300, 450);
		this.setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		File f = new File("image/myongji.png");
		try {
			img = ImageIO.read(f);
			Seeimage();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("이미지 파일이 존재하지 않습니다");
		} // 그림 설정

		JPanel panel = new JPanel();
		this.setLoginPanel(panel);
		this.add(panel);
		this.setVisible(true); // 패널 생성
		panel.setBackground(Color.WHITE);
	}

	private void Seeimage() {
		// TODO Auto-generated method stub
		JLabel imagelabel = new JLabel(new ImageIcon(img));
		imagelabel.setBounds(10, 10, 274, 250);
		add(imagelabel);
	}

	public void setLoginPanel(JPanel panel) {
		panel.setLayout(null);
		JLabel userLabel = new JLabel("ID :");
		userLabel.setBounds(10, 270, 80, 30);
		userLabel.setFont(font); // 폰트 추가

		panel.add(userLabel); // ID

		JLabel passLabel = new JLabel("PassWord :");
		passLabel.setBounds(10, 290, 80, 30);
		panel.add(passLabel); // PS
		passLabel.setFont(font);

		IdText = new JTextField(20);
		IdText.setBounds(100, 268, 180, 25);
		IdText.registerKeyboardAction(this.actionListener, "login", KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0),
				JComponent.WHEN_FOCUSED);

		panel.add(IdText); // ID FIELD

		passText = new JPasswordField(20);
		passText.setBounds(100, 293, 180, 25);
		passText.registerKeyboardAction(this.actionListener, "login", KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0),
				JComponent.WHEN_FOCUSED);

		panel.add(passText); // PS FIELD

		btnInit = new JButton("초기화");
		btnInit.setBounds(30, 330, 100, 25);
		btnInit.setActionCommand("initialize");
		btnInit.addActionListener(this.actionListener);
		btnInit.setToolTipText("Id,Pw 초기화하기");
		btnInit.setBackground(Color.white);
		panel.add(btnInit);

		btnLogin = new JButton("로그인");
		btnLogin.setActionCommand("login");
		btnLogin.addActionListener(this.actionListener);
		btnLogin.setBounds(170, 330, 100, 25);
		btnLogin.setToolTipText("로그인 하기");
		btnLogin.setBackground(Color.white);
		panel.add(btnLogin);

		btnSignUp = new JButton("회원가입");
		btnSignUp.setActionCommand("signup");
		btnSignUp.addActionListener(this.actionListener);
		btnSignUp.setBounds(30, 360, 100, 25);
		btnSignUp.setToolTipText("회원가입 하기");
		btnSignUp.setBackground(Color.white);
		panel.add(btnSignUp);

		label = new JLabel();
		label.setBounds(70, 315, 190, 180);
		label.setFont(font);
		panel.add(label); // 예외처리 라벨

	}

	@SuppressWarnings("unused")
	private class ActionHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (e.getActionCommand() == "login") {
				CLogin clogin = new CLogin();

				String id = IdText.getText();
				String pw = "";
				for (char c : passText.getPassword()) {// 저장할 때 배열로 한 글자씩 char로 넣음, 그 char을 이은것을 string에 저장함.
					pw += c;
				}

				try {
					clogin.authenticate(id, pw);
					String name = clogin.getName();
					MainFrame mainFrame = new MainFrame(id, name);
					mainFrame.setVisible(true);
					dispose();

				} catch (FileNotFoundException | InvalidUserException e1) {
					label.setText(e1.getMessage());
					label.setForeground(Color.red);
				}
			} else if (e.getActionCommand() == "initialize") {// 초기화
				IdText.setText("");
				passText.setText("");
				label.setText("");
			} else if (e.getActionCommand() == "signup") {
				try {
					signUpFrame = new SignUpFrame(actionListener);// 회원가입 창
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				signUpFrame.setVisible(true);
			} else if (e.getActionCommand() == "overLapId") {//아이디 중복확인
				try {
					validId = signUpFrame.overLapId();
					if (validId) {
						JOptionPane.showMessageDialog(null, "이 아이디는 사용하실 수 있습니다. ", "아이디 중복 확인",
								JOptionPane.PLAIN_MESSAGE);
						enabled();
					} else {
						JOptionPane.showMessageDialog(null, "이 아이디는 사용하실 수 없습니다.", "아이디 중복 확인",
								JOptionPane.WARNING_MESSAGE);
					}
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else if (e.getActionCommand() == "signupinformation") {// 파일에 정보등록
				boolean isChecked = signUpFrame.checkValue();
				if (isChecked == true && validId == true) {
					try {
						JOptionPane.showMessageDialog(null, "회원가입 되었습니다.", "회원가입", JOptionPane.PLAIN_MESSAGE);
						signUpFrame.signupInformation();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else if (isChecked == true && validId == false) {
					JOptionPane.showMessageDialog(null, "중복된 아이디이기 때문에 가입할 수 없습니다.", "아이디 중복 확인",
							JOptionPane.WARNING_MESSAGE);
				}

			}

		}
		public void enabled() {
			signUpFrame.signUpbtn.setEnabled(true);
			signUpFrame.checkOverLapbtn.setEnabled(false);
			signUpFrame.idText.setEnabled(false);
		}
	

	}

}
