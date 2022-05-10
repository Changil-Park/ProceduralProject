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

		font = new Font("���", Font.BOLD, 15);

		actionListener = new ActionHandler();

		// setting
		setTitle("�������б� �α���");
		File f2 = new File("image/myongjiicon.png");
		try {
			img2 = ImageIO.read(f2);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		setIconImage(img2); // ���� ������ ����

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
			System.out.println("�̹��� ������ �������� �ʽ��ϴ�");
		} // �׸� ����

		JPanel panel = new JPanel();
		this.setLoginPanel(panel);
		this.add(panel);
		this.setVisible(true); // �г� ����
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
		userLabel.setFont(font); // ��Ʈ �߰�

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

		btnInit = new JButton("�ʱ�ȭ");
		btnInit.setBounds(30, 330, 100, 25);
		btnInit.setActionCommand("initialize");
		btnInit.addActionListener(this.actionListener);
		btnInit.setToolTipText("Id,Pw �ʱ�ȭ�ϱ�");
		btnInit.setBackground(Color.white);
		panel.add(btnInit);

		btnLogin = new JButton("�α���");
		btnLogin.setActionCommand("login");
		btnLogin.addActionListener(this.actionListener);
		btnLogin.setBounds(170, 330, 100, 25);
		btnLogin.setToolTipText("�α��� �ϱ�");
		btnLogin.setBackground(Color.white);
		panel.add(btnLogin);

		btnSignUp = new JButton("ȸ������");
		btnSignUp.setActionCommand("signup");
		btnSignUp.addActionListener(this.actionListener);
		btnSignUp.setBounds(30, 360, 100, 25);
		btnSignUp.setToolTipText("ȸ������ �ϱ�");
		btnSignUp.setBackground(Color.white);
		panel.add(btnSignUp);

		label = new JLabel();
		label.setBounds(70, 315, 190, 180);
		label.setFont(font);
		panel.add(label); // ����ó�� ��

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
				for (char c : passText.getPassword()) {// ������ �� �迭�� �� ���ھ� char�� ����, �� char�� �������� string�� ������.
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
			} else if (e.getActionCommand() == "initialize") {// �ʱ�ȭ
				IdText.setText("");
				passText.setText("");
				label.setText("");
			} else if (e.getActionCommand() == "signup") {
				try {
					signUpFrame = new SignUpFrame(actionListener);// ȸ������ â
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				signUpFrame.setVisible(true);
			} else if (e.getActionCommand() == "overLapId") {//���̵� �ߺ�Ȯ��
				try {
					validId = signUpFrame.overLapId();
					if (validId) {
						JOptionPane.showMessageDialog(null, "�� ���̵�� ����Ͻ� �� �ֽ��ϴ�. ", "���̵� �ߺ� Ȯ��",
								JOptionPane.PLAIN_MESSAGE);
						enabled();
					} else {
						JOptionPane.showMessageDialog(null, "�� ���̵�� ����Ͻ� �� �����ϴ�.", "���̵� �ߺ� Ȯ��",
								JOptionPane.WARNING_MESSAGE);
					}
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else if (e.getActionCommand() == "signupinformation") {// ���Ͽ� �������
				boolean isChecked = signUpFrame.checkValue();
				if (isChecked == true && validId == true) {
					try {
						JOptionPane.showMessageDialog(null, "ȸ������ �Ǿ����ϴ�.", "ȸ������", JOptionPane.PLAIN_MESSAGE);
						signUpFrame.signupInformation();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else if (isChecked == true && validId == false) {
					JOptionPane.showMessageDialog(null, "�ߺ��� ���̵��̱� ������ ������ �� �����ϴ�.", "���̵� �ߺ� Ȯ��",
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
