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
		this.setTitle("ȸ������");

		this.signUpPanel = new JPanel(new GridLayout(11, 0));
		signUpPanel.setBackground(Color.WHITE);

		this.idlabel = new JLabel("���̵�");
		this.pwlabel = new JLabel("��й�ȣ");
		this.pwChecklabel = new JLabel("��й�ȣ Ȯ��");
		this.namelabel = new JLabel("�̸�");
		this.checkOverLapbtn = new JButton("���̵� �ߺ�Ȯ��");

		this.idText = new JTextField();
		this.idText.setEnabled(true);

		this.pwText = new JPasswordField();

		this.pwCheckText = new JPasswordField();

		this.nameText = new JTextField();

		this.signUpPanel.add(idlabel);
		this.signUpPanel.add(idText); // ���̵� �Է�

		this.signUpPanel.add(checkOverLapbtn);
		this.checkOverLapbtn.setActionCommand("overLapId");
		this.checkOverLapbtn.addActionListener(actionListener); // ���̵� �ߺ�Ȯ�� ��ư
		this.checkOverLapbtn.setEnabled(true);

		this.signUpPanel.add(pwlabel);
		this.signUpPanel.add(pwText); // ��й�ȣ �Է�

		this.signUpPanel.add(pwChecklabel);
		this.signUpPanel.add(pwCheckText); // ��й�ȣ Ȯ��

		this.signUpPanel.add(namelabel);
		this.signUpPanel.add(nameText);

		this.signUpbtn = new JButton("ȸ������");
		this.signUpbtn.setActionCommand("signupinformation");
		this.signUpbtn.addActionListener(actionListener); // ȸ������ ��ư + ������
		this.signUpbtn.setEnabled(false);
		this.signUpPanel.add(signUpbtn);

		this.setContentPane(signUpPanel);

		this.setSize(300, 500);

		this.setLocationRelativeTo(null);

	}

	public void read(Scanner sc) {
		scanId = sc.next(); // ���� �� id
		sc.next();// password
		sc.next();// name
	}

	@SuppressWarnings("deprecation")
	public boolean checkValue() {

		if (idText.getText().trim().length() == 0) {
			JOptionPane.showMessageDialog(null, "���̵� �Է��� �ּ���.", "���̵� �Է�", JOptionPane.WARNING_MESSAGE);
			idText.grabFocus();
			isChecked = false;
			return isChecked;
		}

		if (pwText.getText().trim().length() == 0) {
			JOptionPane.showMessageDialog(null, "��й�ȣ�� �Է��� �ּ���.", "��й�ȣ �Է�", JOptionPane.WARNING_MESSAGE);
			pwText.grabFocus();
			isChecked = false;
			return isChecked;
		}

		if (pwCheckText.getText().trim().length() == 0) {
			JOptionPane.showMessageDialog(null, "��й�ȣ Ȯ���� �Է��� �ּ���.", "��й�ȣ Ȯ�� �Է�", JOptionPane.WARNING_MESSAGE);
			pwCheckText.grabFocus();
			isChecked = false;
			return isChecked;

		}

		if (!(pwText.getText().trim().equals(pwCheckText.getText().trim()))) {
			JOptionPane.showMessageDialog(null, "��й�ȣ�� ���� �ʽ��ϴ�.", "��й�ȣ Ȯ��", JOptionPane.WARNING_MESSAGE);
			isChecked = false;
			return isChecked;

		}
		if (nameText.getText().trim().length() == 0) {
			JOptionPane.showMessageDialog(null, "�̸��� �Է��� �ּ���.", "�̸� �Է�", JOptionPane.WARNING_MESSAGE);
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

		for (char c : pwText.getPassword()) {// ������ �� �迭�� �� ���ھ� char�� ����, �� char�� �������� string�� ������.
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
		validId = true; // �޼ҵ� ���� �� �� true�� �ʱ�ȭ
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
