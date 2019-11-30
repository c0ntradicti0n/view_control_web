package me.cc.model;

import java.io.Serializable;
import java.util.List;

public class Document implements Serializable {

	private static final long serialVersionUID = 20111120L;

	private String name;
	private String code;
	private String sport;
	private List<Meta> leagues;

	public Document(String name, String code, String sport, List<Meta> leagues) {
		this.name = name;
		this.code = code;
		this.sport = sport;
		this.leagues = leagues;
	}

	public String getName() {
		return name;
	}

	public String getCode() {
		return code;
	}

	public String getSport() {
		return sport;
	}

	public List<Meta> getLeagues() {
		return leagues;
	}
}
