package ui;

import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;


public class ButtonPanel extends JPanel {
	public JButton btnBasketgo, btnBasketDelete,btnSincheonggo,btnSincheongDelete;

	private static final long serialVersionUID = 1L;

	public ButtonPanel(ActionListener actionListener) {
		Color color=new Color(153,204,255);
		
		setBackground(color);
		btnBasketgo = new JButton("장바구니 담기");

		btnBasketDelete = new JButton("장바구니 삭제");

		btnSincheonggo=new JButton("신청");
		
		btnSincheongDelete=new JButton("신청 삭제");
		
		btnBasketgo.setActionCommand("basket");
		btnBasketgo.addActionListener(actionListener);
		btnBasketgo.setBackground(Color.white);
		btnBasketgo.setToolTipText("장바구니에 담기");
		this.add(btnBasketgo);

		btnBasketDelete.setActionCommand("basketdelete");
		btnBasketDelete.addActionListener(actionListener);
		btnBasketDelete.setBackground(Color.white);
		btnBasketDelete.setToolTipText("장바구니에서 삭제");
		this.add(btnBasketDelete);
		
		btnSincheonggo.setActionCommand("sincheong");
		btnSincheonggo.addActionListener(actionListener);
		btnSincheonggo.setBackground(Color.white);
		btnSincheonggo.setToolTipText("강좌를 신청");
		this.add(btnSincheonggo);
		
		btnSincheongDelete.setActionCommand("sincheongdelete");
		btnSincheongDelete.addActionListener(actionListener);
		btnSincheongDelete.setBackground(Color.white);
		btnSincheongDelete.setToolTipText("신청된 강좌 삭제");
		this.add(btnSincheongDelete);
		
		
	}

}
