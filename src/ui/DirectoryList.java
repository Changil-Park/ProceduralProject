package ui;

import java.awt.Dimension;
import java.io.FileNotFoundException;
import java.util.Vector;

import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;

import control.CDirectory;
import entity.EDirectory;

public class DirectoryList extends JList<String> {
	// attributes 속성
	private static final long serialVersionUID = 1L;
	// service
	private CDirectory cDirectory;
	private Vector<EDirectory> eDirectories;
	// model
	private Vector<String> listData;

	public DirectoryList(ListSelectionListener listSelectionListener) {
		// attributes 고유 값
		this.setPreferredSize(new Dimension(100, 200));
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		// service 컨트롤러와 연결
		this.cDirectory = new CDirectory();
		// model jlist가 보여주는 데이터를 보유하고 관리
		this.listData = new Vector<String>();
		this.setListData(this.listData); // JList에다가 모델을 연결해놈
		// event handler
		this.addListSelectionListener(listSelectionListener);
	}

	public String getSelectedFileName() {
		int selectedIndex = this.getSelectedIndex();
		return this.eDirectories.get(selectedIndex).getHyperLink();
	}

	public String refresh(String fileName) throws FileNotFoundException {
		this.eDirectories = this.cDirectory.getItems("data/" + fileName);

		this.listData.clear();

		for (EDirectory eDirectory : eDirectories) {
			this.listData.add(eDirectory.getName());
		}

		this.setSelectedIndex(0);
		this.updateUI();// updateUI는 데이터를 가져와서 다시그려라 라는 뜻, refresh는 그냥 다시 그리라는 뜻

		return this.eDirectories.get(0).getHyperLink();
	}

	// JList는 겉에 그림 있고 벡터 만들어져 있지 - control에서 가져온 directory의 fileName을 찾아내야지 - 찾아서
	// 다음 디렉토리로 또 줘
}