package ui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class SignUpFrame extends JFrame {
	private JPanel signUpPanel;
	public JTextField idText;
	private JTextField nameText;
	private JPasswordField pwText;
	private JPasswordField pwCheckText;
	public JButton signUpbtn;
	private JLabel idlabel;
	private JLabel pwlabel;
	private JLabel pwChecklabel;
	private JLabel namelabel;
	private String scanId;
	public JButton checkOverLapbtn;
	boolean isChecked = false;
	private boolean validId = true;

	public SignUpFrame(ActionListener actionListener) throws IOException {
		this.setTitle("회원가입");

		this.signUpPanel = new JPanel(new GridLayout(11, 0));
		signUpPanel.setBackground(Color.WHITE);

		this.idlabel = new JLabel("아이디");
		this.pwlabel = new JLabel("비밀번호");
		this.pwChecklabel = new JLabel("비밀번호 확인");
		this.namelabel = new JLabel("이름");
		this.checkOverLapbtn = new JButton("아이디 중복확인");

		this.idText = new JTextField();
		this.idText.setEnabled(true);

		this.pwText = new JPasswordField();

		this.pwCheckText = new JPasswordField();

		this.nameText = new JTextField();

		this.signUpPanel.add(idlabel);
		this.signUpPanel.add(idText); // 아이디 입력

		this.signUpPanel.add(checkOverLapbtn);
		this.checkOverLapbtn.setActionCommand("overLapId");
		this.checkOverLapbtn.addActionListener(actionListener); // 아이디 중복확인 버튼
		this.checkOverLapbtn.setEnabled(true);

		this.signUpPanel.add(pwlabel);
		this.signUpPanel.add(pwText); // 비밀번호 입력

		this.signUpPanel.add(pwChecklabel);
		this.signUpPanel.add(pwCheckText); // 비밀번호 확인

		this.signUpPanel.add(namelabel);
		this.signUpPanel.add(nameText);

		this.signUpbtn = new JButton("회원가입");
		this.signUpbtn.setActionCommand("signupinformation");
		this.signUpbtn.addActionListener(actionListener); // 회원가입 버튼 + 리스너
		this.signUpbtn.setEnabled(false);
		this.signUpPanel.add(signUpbtn);

		this.setContentPane(signUpPanel);

		this.setSize(300, 500);

		this.setLocationRelativeTo(null);

	}

	public void read(Scanner sc) {
		scanId = sc.next(); // 파일 내 id
		sc.next();// password
		sc.next();// name
	}

	@SuppressWarnings("deprecation")
	public boolean checkValue() {

		if (idText.getText().trim().length() == 0) {
			JOptionPane.showMessageDialog(null, "아이디를 입력해 주세요.", "아이디 입력", JOptionPane.WARNING_MESSAGE);
			idText.grabFocus();
			isChecked = false;
			return isChecked;
		}

		if (pwText.getText().trim().length() == 0) {
			JOptionPane.showMessageDialog(null, "비밀번호를 입력해 주세요.", "비밀번호 입력", JOptionPane.WARNING_MESSAGE);
			pwText.grabFocus();
			isChecked = false;
			return isChecked;
		}

		if (pwCheckText.getText().trim().length() == 0) {
			JOptionPane.showMessageDialog(null, "비밀번호 확인을 입력해 주세요.", "비밀번호 확인 입력", JOptionPane.WARNING_MESSAGE);
			pwCheckText.grabFocus();
			isChecked = false;
			return isChecked;

		}

		if (!(pwText.getText().trim().equals(pwCheckText.getText().trim()))) {
			JOptionPane.showMessageDialog(null, "비밀번호가 같지 않습니다.", "비밀번호 확인", JOptionPane.WARNING_MESSAGE);
			isChecked = false;
			return isChecked;

		}
		if (nameText.getText().trim().length() == 0) {
			JOptionPane.showMessageDialog(null, "이름을 입력해 주세요.", "이름 입력", JOptionPane.WARNING_MESSAGE);
			nameText.grabFocus();
			isChecked = false;
			return isChecked;
		}

		isChecked = true;
		this.dispose();
		return isChecked;
	}

	public void signupInformation() throws IOException {

		File f = new File("data/login");
		FileWriter fw = new FileWriter(f, true);
		String id = idText.getText();
		String pw = "";
		String name = nameText.getText();

		for (char c : pwText.getPassword()) {// 저장할 때 배열로 한 글자씩 char로 넣음, 그 char을 이은것을 string에 저장함.
			pw += c;
		}
		fw.write(id + " " + pw + " " + name + "\r\n");

		fw.close();

		FileWriter fw2 = new FileWriter("data/basket" + id, false);
		fw2.close();

		FileWriter fw3 = new FileWriter("data/sincheong" + id, false);
		fw3.close();

	}

	public boolean overLapId() throws FileNotFoundException {
		validId = true; // 메소드 시작 할 때 true로 초기화
		Scanner scanner = new Scanner(new File("data/login"));

		while (scanner.hasNext()) {
			this.read(scanner);
			 if (this.scanId.equals(idText.getText())||this.idText.getText().trim().length() == 0) {
				validId = false;
			}
		}
		scanner.close();
		return validId;
	}

}
