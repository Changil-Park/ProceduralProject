package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class LogoutPanel extends JPanel {

	public JLabel greet;
	public JButton logout,homepage; 
	private Font font;
	

	public LogoutPanel(String name, ActionListener actionListener) {

		Color color=new Color(204,255,255);
		
		font = new Font("���" , Font.BOLD, 13);
		
		setBackground(color);
		LayoutManager layoutManager = new FlowLayout(FlowLayout.CENTER, 25, 5);

		this.setLayout(layoutManager);

		greet = new JLabel();
		greet.setText("�ȳ��ϼ���" + " " + name + "�� ȯ���մϴ�!" + "  " + "�������б� ������û �ý����Դϴ�.");
		greet.setFont(font);
		this.add(greet);

		logout = new JButton("�α׾ƿ�");
		logout.setActionCommand("Logout");
		logout.addActionListener(actionListener);
		logout.setBackground(Color.white);
		logout.setToolTipText("�α׾ƿ� �ϱ�");
		this.add(logout);
		
		homepage = new JButton("Ȩ������");
		homepage.setActionCommand("homepage");
		homepage.addActionListener(actionListener);
		homepage.setBackground(Color.white);
		homepage.setToolTipText("�������б� Ȩ������ �̵�");
		this.add(homepage);

		this.setPreferredSize(new Dimension(50, 60));
	}

}
