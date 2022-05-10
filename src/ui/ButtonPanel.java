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
		btnBasketgo = new JButton("��ٱ��� ���");

		btnBasketDelete = new JButton("��ٱ��� ����");

		btnSincheonggo=new JButton("��û");
		
		btnSincheongDelete=new JButton("��û ����");
		
		btnBasketgo.setActionCommand("basket");
		btnBasketgo.addActionListener(actionListener);
		btnBasketgo.setBackground(Color.white);
		btnBasketgo.setToolTipText("��ٱ��Ͽ� ���");
		this.add(btnBasketgo);

		btnBasketDelete.setActionCommand("basketdelete");
		btnBasketDelete.addActionListener(actionListener);
		btnBasketDelete.setBackground(Color.white);
		btnBasketDelete.setToolTipText("��ٱ��Ͽ��� ����");
		this.add(btnBasketDelete);
		
		btnSincheonggo.setActionCommand("sincheong");
		btnSincheonggo.addActionListener(actionListener);
		btnSincheonggo.setBackground(Color.white);
		btnSincheonggo.setToolTipText("���¸� ��û");
		this.add(btnSincheonggo);
		
		btnSincheongDelete.setActionCommand("sincheongdelete");
		btnSincheongDelete.addActionListener(actionListener);
		btnSincheongDelete.setBackground(Color.white);
		btnSincheongDelete.setToolTipText("��û�� ���� ����");
		this.add(btnSincheongDelete);
		
		
	}

}
