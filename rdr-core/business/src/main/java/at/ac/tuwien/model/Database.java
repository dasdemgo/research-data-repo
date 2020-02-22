package at.ac.tuwien.model;

import java.util.ArrayList;

public class Database {

	private String id;
	private ArrayList<Table> tables;
	private String name;

	public ArrayList<Table> getTables() {
		return tables;
	}

	public void setTables(ArrayList<Table> tables) {
		this.tables = tables;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
