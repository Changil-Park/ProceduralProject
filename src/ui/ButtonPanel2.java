package ui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ButtonPanel2 extends JPanel {

	public JButton btnUp, btnDown;
	private  Font font;
	private static final long serialVersionUID = 1L;

	public ButtonPanel2(ActionListener actionListener) {
		font = new Font("고딕", Font.BOLD, 20);
		setBackground(Color.WHITE);
		
		LayoutManager layoutManager = new FlowLayout(FlowLayout.CENTER, 10, 10);

		this.setLayout(layoutManager);
		
		btnUp = new JButton("↑");

		btnDown = new JButton("↓");

		
		btnUp.setActionCommand("up");
		btnUp.addActionListener(actionListener);
		btnUp.setBackground(Color.white);
		btnUp.setFont(font);
		btnUp.setToolTipText("신청→장바구니");
		this.add(btnUp);

		btnDown.setActionCommand("down");
		btnDown.addActionListener(actionListener);
		btnDown.setBackground(Color.white);
		btnDown.setFont(font);
		btnDown.setToolTipText("장바구니→신청");
		this.add(btnDown);
	}
}
