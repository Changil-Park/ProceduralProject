package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.LayoutManager;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import control.CBasket;
import control.CSincheong;

@SuppressWarnings("serial")
public class BasketApplyPanel extends JPanel {

	public BasketTable basketTable;
	public ApplyTable applyTable;
	public JLabel basketname, applyname;
	private Font font;

	public BasketApplyPanel(CBasket cBasket, CSincheong cSincheong, String id) {// 생성자는 프로그램 실행시킨 후 단 한번 만 실행됌.

		setBackground(Color.WHITE);

		font = new Font("고딕", Font.BOLD, 15);

		LayoutManager layoutManager = new FlowLayout(FlowLayout.LEFT, 10, 10);

		this.setLayout(layoutManager);

		basketname = new JLabel("[장바구니]");
		this.add(basketname);
		basketname.setFont(font);

		JScrollPane scrollpane = new JScrollPane();
		this.basketTable = new BasketTable(cBasket, id);
		scrollpane.setViewportView(this.basketTable);
		scrollpane.setPreferredSize(new Dimension(380, 200));
		this.add(scrollpane);

		applyname = new JLabel("[신청]");
		this.add(applyname);
		scrollpane = new JScrollPane();
		this.applyTable = new ApplyTable(cSincheong, id);
		applyname.setFont(font);
		scrollpane.setViewportView(this.applyTable);
		scrollpane.setPreferredSize(new Dimension(380, 200));
		this.add(scrollpane);

		this.setPreferredSize(new Dimension(400, 450)); // BasketApplyPanel패널 레이아웃 맞추기

	}

}
