package entity;

import java.util.Scanner;

public class EDirectory {

	private int number;
	private String name;
	private String hyperLink;

	public void read(Scanner scanner) {
		this.number = scanner.nextInt();
		this.name = scanner.next();
		this.hyperLink = scanner.next();

	}

	public String getHyperLink() {
		// TODO Auto-generated method stub
		return hyperLink;
	}

	public int getNumber() {
		// TODO Auto-generated method stub
		return number;
	}

	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

}
