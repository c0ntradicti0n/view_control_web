package me.cc.model;

import java.io.Serializable;

public class Meta implements Serializable {

	private static final long serialVersionUID = 20111120L;

	private String name;
	private int numberOfTeam;

	public Meta(String name, int numberOfTeam) {
		this.name = name;
		this.numberOfTeam = numberOfTeam;
	}

	public String getName() {
		return name;
	}

	public int getNumberOfTeam() {
		return numberOfTeam;
	}
}
            