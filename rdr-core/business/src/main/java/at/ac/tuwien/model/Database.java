package at.ac.tuwien.model;

import java.util.ArrayList;

public class Database {

	private String id;
	private ArrayList<CreateTable> tables;
	private String name;

	public ArrayList<CreateTable> getTables() {
		return tables;
	}

	public void setTables(ArrayList<CreateTable> tables) {
		this.tables = tables;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
