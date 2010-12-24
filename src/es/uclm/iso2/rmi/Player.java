package es.uclm.iso2.rmi;

import java.io.Serializable;
import java.util.ArrayList;

public class Player implements Serializable {

	private static final long serialVersionUID = -282411403193425543L;

	private String name;
	private int money;
	private boolean online;
	private boolean hasTurn;
	private ArrayList<Spy> spies;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public boolean isOnline() {
		return online;
	}

	public void setOnline(boolean online) {
		this.online = online;
	}

	public boolean isHasTurn() {
		return hasTurn;
	}

	public void setHasTurn(boolean hasTurn) {
		this.hasTurn = hasTurn;
	}

	public ArrayList<Spy> getSpies() {
		return spies;
	}

	public void setSpies(ArrayList<Spy> spies) {
		this.spies = spies;
	}

}
