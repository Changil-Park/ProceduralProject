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
		
		font = new Font("고딕" , Font.BOLD, 13);
		
		setBackground(color);
		LayoutManager layoutManager = new FlowLayout(FlowLayout.CENTER, 25, 5);

		this.setLayout(layoutManager);

		greet = new JLabel();
		greet.setText("안녕하세요" + " " + name + "님 환영합니다!" + "  " + "명지대학교 수강신청 시스템입니다.");
		greet.setFont(font);
		this.add(greet);

		logout = new JButton("로그아웃");
		logout.setActionCommand("Logout");
		logout.addActionListener(actionListener);
		logout.setBackground(Color.white);
		logout.setToolTipText("로그아웃 하기");
		this.add(logout);
		
		homepage = new JButton("홈페이지");
		homepage.setActionCommand("homepage");
		homepage.addActionListener(actionListener);
		homepage.setBackground(Color.white);
		homepage.setToolTipText("명지대학교 홈페이지 이동");
		this.add(homepage);

		this.setPreferredSize(new Dimension(50, 60));
	}

}
